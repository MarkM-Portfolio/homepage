/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.widget.IWidgetWrapper");

dojo.require("lconn.homepage.core.base._BodyWidget");
dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.require("lconn.core.util.LCDeferred");

dojo.require("com.ibm.lconn.gadget.container.Meta");
dojo.require("com.ibm.lconn.gadget.container._Meta");

dojo.require('com.ibm.lconn.gadget.support.WidgetAdapter');
dojo.require('com.ibm.lconn.gadget.support.Site');
dojo.require("lconn.homepage.core.base.userPrefUI");
dojo.require("lconn.homepage.core.widget.GadgetAdapter");

dojo.declare("lconn.homepage.core.widget.IWidgetWrapper", [lconn.homepage.core.base._BodyWidget],
{
    templatePath: dojo.moduleUrl("lconn.homepage", "core/widget/templates/IWidgetWrapper.html"),

	_creWidget: null, // Set to the CRE Widget object after succesfull loading of the widget/gadget
	_mContainer: null,
	_handle: null, // iContainer handle for this widget.
	_maxUrlPromise: null, // Promise to return max URL
	_widgetInfo: null, // Widget object
	_userPrefUI: null, // Helper code to deal with edit form setup and save
	_userPrefDefaults: null, // User preference defaults
	isSystem: true, // True if this is a Connections Widget, false otherwise.

	// params
    widgetDef: "", // Modified url to widget def for Enabler.
	widgetId: "",
    widgetInstanceId: "",
	widgetType: "",
	creUrl: "", // Unmodified url for CRE

	_widgetAdapter: null, //created for Gadgets.  iWidgetWrapper uses this (if non-null)

    _iWidget: null,

	// attached DOM nodes
    iWidgetRootNode: null,

    // option view node
    optionViewNode: null,

		setting: null,

    constructor: function() {
    	// load MM if we actually need it
    	if ( !this.usingCre() ) {
    		// dont just require directly, fool net.jazz.ajax into not adding to dep chain
    		var iw = "com.ibm.mm.enabler.iw";
    		var itemSet = "lconn.homepage.core.widget.iwExtension.ItemSet";
    		var defImpl = "lconn.homepage.core.widget.iwExtension.iWidgetWrapperDefaultImpl";
    		var iCtx = "lconn.homepage.core.widget.iwExtension.iContext";

    		dojo.require(iw);
    		dojo.require(itemSet);
    		dojo.require(defImpl);
    		dojo.require(iCtx);
    	}
    },

	postCreate: function(){
        lconn.homepage.core.widget.IWidgetWrapper.superclass.postCreate.apply(this);
    },

    startup: function(){
        this.loadIWidget();
    },

    loadIWidget: function(){
        this.domNode.widgetInstanceId = this.widgetInstanceId;
        var defNode = dojo.query(".iw-Definition", this.iWidgetRootNode)[0];
        defNode.setAttribute("href", this.widgetDef);
        this.iWidgetRootNode.id = this.widgetId;
		if (!this.usingCre()) { // Use Mashup Enabler
			this._iWidget = iWidgetContainer.createWidget(this.iWidgetRootNode);

			// render is asynchronous, we have to listen to the standard onLoad iWidget event
			dojo.connect(this._iWidget, "update", this, "onWrappedWidgetUpdated");
			dojo.connect(this._iWidget, "onLoad", this, "onWrappedWidgetLoaded");
			this._iWidget.render();
			}
		else { // Use CRE
			// Initialize CRE
			this._mContainer = com.ibm.lconn.gadget.container.iContainer2;
			this._mContainer.init();

			// Create Enabler iWidget to satisfy other code that requires it - but don't render it.
			this._iWidget = {};
			this._iWidget.id = this.widgetId;
			var def = {
					componentType: this.widgetType,
					placement: this.widgetId,
					id: this.widgetId, // preset an ID, required for persistence endpoint support
					definitionUrl: this.creUrl, // Use unmodified URL for CRE
					instanceData: {
						renderParams: {
						}
					}
			};

			if ( this.widgetType == "gadget" ) {
				def.instanceData.renderParams.width = "100%"; // give gadget all width available
			}

			this._mContainer.getIRuntime().then(dojo.hitch(this, function(iRuntime) {
			// Set user profile info
				var pageData = {
					"userProfile":{
						"id":"userProfile",
						"item":[{
							"id":"viewer",
							"value":{
								"userid": lconn.homepage.global.getUserId(),
								"displayName": lconn.homepage.userName,
								"email": lconn.homepage.userEmail
							}
						}]
					}
				};


				// Get any persisted user prefs and add to def.
				if (def.componentType == "gadget") {
					this._userPrefUI = new lconn.homepage.core.base.userPrefUI;
					var userPrefString = lconn.homepage.global.beans[this.widgetId];
					if (typeof(userPrefString) != "undefined") {
						var userPrefs = this._userPrefUI.deserializeFromString(userPrefString);
						this._userPrefDefaults = def.instanceData.renderParams.userPrefs = userPrefs;
					}
					else {
						// set _userPrefDefaults to values in metadata later.
					}
				} else if ( def.componentType == "iWidget" ) {
					// see if we have itemSet settings for the widget
					// we will only have JSON settings from non-HomepageSpecific iWidgets
					// that are using itemSet persistence
					var settings = null;
					try {
						settings = dojo.fromJson(this.widgetSetting);
					} catch (e) {}

					if ( settings && (settings.id === "attributes" || settings.name === "attributes" )) {
						def.instanceData = def.instanceData || {};

						//remap some keys because of known issue, key name -> id, items -> item
						if (settings.name){
							settings.id = settings.name;
							delete settings.name;
						}

						if (settings.items){
							settings.item = settings.items;
							delete settings.items;
						}

						settings.item.forEach(function(entry) {
							if (entry.name) {
								entry.id = entry.name;
								delete entry.name;
							}
						});


						def.instanceData.itemSet = [ settings ];
					}
				}

				iRuntime.init(pageData); //** Move to iContainer2.init() ?? **/

				this.getWidgetHandle(def).then(dojo.hitch(this, function(handle){
					this._handle = handle;
					this._extendHandle(this._handle); // Add methods for our use.

					var meta = this._handle.getMetaDataObject();

					// Create WidgetAdapter to use for Gadget functionality.
					//
					if (def.componentType == "gadget") {
						meta.userPrefs().then(dojo.hitch(this, function(userPrefs) {
							if (this._userPrefDefaults == null) { // User prefs weren't persisted - use defaults.
								this._userPrefDefaults = this._userPrefUI.getFromDefaults(userPrefs);
							}
							this._widgetAdapter = new lconn.homepage.core.widget.GadgetAdapter({widgetSpec: def, handle: this._handle, mContainer: this._mContainer, userPrefDefaults: this._userPrefDefaults, meta: meta});
						}),
						// Error resolution
						dojo.hitch(this, function(err) {
							this._widgetAdapter = new lconn.homepage.core.widget.GadgetAdapter({widgetSpec: def, handle: this._handle, mContainer: this._mContainer, userPrefDefaults: this._userPrefDefaults, meta: meta});
							this._showError(err);
						}));
					}

					if (this._maxUrlPromise != null) { // Need to resolve promise
						meta.maxUrl().then(dojo.hitch(this, function(url) {
							this._maxUrlPromise.resolve(url);
							this._maxUrlPromise = null;
						}),
						// Error Resolution
						dojo.hitch(this, function(err) {
							this._showError(err);
						}));
					}

					var prom = this._handle.getWidgetInfo();
					if (prom != null) {
						prom.then(dojo.hitch(this, function(data) {
							this._widgetInfo = data;
							this.onWrappedWidgetLoaded();
						}),
						// Error Resolution
						dojo.hitch(this, function(err) {
							this._showError(err);
						}));
					}
				}));
			}));
		}
    },

	isSystem: function() {
		return(this.isSystem);
	},

    /**
	 * Returns a promise that resolves to a widget handle, can be overriden to modify widget loading behaviour
	 * @memberOf lconn.homepage.core.widget.IWidgetWrapper
	 * @name getWidgetHandle
	 * @function
	 * @param def {Object} A widget definition matching @see cre$.iRuntime.loadWidget
	 * @returns {Object} A promise that resolves to the widget handle
	 */
    getWidgetHandle: function(def){
		var promise = new lconn.core.util.LCDeferred ();
		promise.callback(this._mContainer.loadWidget(def));
		return promise;
    },


	/**
	 * Async call to maxUrl for the widget
	 * @memberOf lconn.homepage.core.widget.IWidgetWrapper
	 * @name getMaxUrlAsync
	 * @function
	 * @returns lconn.core.util.LCDeferred
	 */
    getMaxUrlAsync: function() {
		var promise = null;
		if (this._handle != null) {
			var meta = this._handle.getMetaDataObject();
			promise = meta.maxUrl();
		}
		else { // Remember this promise so we can resolve when we get a handle to the widget.
			if (this._maxUrlPromise == null) {
				this._maxUrlPromise = new lconn.core.util.LCDeferred();
			}
			promise = this._maxUrlPromise;
		}
		return(promise);
    },

	//
	// com.ibm.lconn.gadget.support.Site method overrides
	//

    getiWidget: function(){
        return this._iWidget;
    },

    edit: function(callback){
		if (this._widgetAdapter != null) {
			this._widgetAdapter.edit(callback);
		}
		else {
			var evName = "edit";
			if ( this.usingCre() && this.isHomepageSpecificWidget()) {evName = "onEdit";}
			this._triggerModeChangeEvent(evName);
		}
    },

    help: function(){
		if (this._widgetAdapter != null) {
			this._widgetAdapter.help();
		}
		else {
			var evName = "help";
			if (this.usingCre() && this.isHomepageSpecificWidget()) {evName = "onHelp";}
			this._triggerModeChangeEvent(evName);
		}
    },

    refresh: function(){
		// Use Widget Adapter if we have one
		if (this._widgetAdapter != null) {
			this._widgetAdapter.refresh();
		}
		else {
			// fire event as defined in the specs
			var evName = "onRefreshNeeded";
			if (this.usingCre()) {evName = "onRefresh";}
			this.fireEvent(this._iWidget.id, evName, null);
		}
    },

	  destroy: function(){
        // fire onUnload event as defined in the specs
		var evName = "onUnload";
		if (this.usingCre()) {evName = "onDestroy";}
       this.fireEvent(this._iWidget.id, evName, null);
    },

    _triggerModeChangeEvent: function(eventName){
        // due to the possibility to activate several modes at a given time in the Homepage
        // we fire the event directly to the iWidget without trying to change to mode markup as MuM does
        var _optionViewNode = this.optionViewNode;

        if (this.isHomepageSpecificWidget()) {
            if (!dojo.isString(eventName))
                return;

            var payload = {};
			this.fireEvent(this._iWidget.id, eventName, payload);
        }
        else {
            // standard iWidget = fire standard onModeChanged event for edit and help
            var payload = {
                "newMode": eventName
            };
            this.fireEvent(this._iWidget.id, "onModeChanged", payload);
        }
    },

	fireEvent: function(id, eventName, payload) {
		if (this.usingCre()) {
			if (this._widgetInfo != null) {
				this._getIContext(this._widgetInfo).iEvents.fireEvent(eventName, null, payload);
			}
		}
		else {
			return(this.fireEnablerEvent(id, eventName, payload));
		}
	},

	fireEnablerEvent: function(id, eventName, payload) {
		var eventService = serviceManager.getService("eventService");
        eventService.fireEvent(this._iWidget.id, eventName, payload);
	},

    isHomepageSpecificWidget: function(){
       if (this.usingCre()) {
		return(this.CREisHomepageSpecificWidget());
	   }
	   else {
		return(this.isHomepageSpecificEnablerWidget());
	   }
    },

	CREisHomepageSpecificWidget: function () {
		var result = true;
		result = this._getIContext(this._widgetInfo).getiWidgetAttributes().getItemValue("useHomepageEnhancedFeatures") === "true";
		return(result);
	},

	isHomepageSpecificEnablerWidget: function() {
	 if (this._iWidget && this._iWidget.iScope) {
            var iContext = this._iWidget.iScope.iContext;

            return (iContext.getiWidgetAttributes().getItemValue("useHomepageEnhancedFeatures") == "true");
        }
        else
            return null;
	},

    onWrappedWidgetUpdated: function(){
        this._loadUserPreferences();
    },

    onWrappedWidgetLoaded: function(){
        dojo.publish(lconn.homepage.events.widget.LOADED, [this]);
    },

    _loadUserPreferences: function(){

		if (!this.usingCre()) {
			// load preference in item set named "options" for backward compatibility with 2.0
			// 2.5 uses the standard "attributes" managed item set
			var is = this._iWidget.iScope;
			if (typeof(is) != "undefined" && is != null) {
				if (is.iContext.getItemSet("options").getAllNames() != null) {
					console.log("ItemSet named 'options' detected. Loading for backward compatibility with 2.0");
					console.log("Use 'attributes' standard managed itemset for user preferences in 2.5");

					var widgetPref = lconn.homepage.global.beans[this._iWidget.id];

					if (widgetPref != null) {
						this._unserializeOptions(widgetPref, is);
					}
				}
			}
		}
    },


    _unserializeOptions: function(data, iscope){
	// Only called if NOT using CRE
        if (!dojo.isString(data))
            return;

        data = window.unescape(data);
        var options = data.split(",");

        for (var i = 0; i < options.length; i++) {
            if (options[i] != "") {
                var option = options[i].split("=");
                var name = option[0];
                var value = window.unescape(option[1]);
				iscope.iContext.getItemSet("options").setItemValue(name, value);
            }
        }
    },

	/**
	 * Async call to get supported actions for widget
	 * @memberOf lconn.homepage.core.widget.IWidgetWrapper
	 * @name getSupportedActionsAsync
	 * @function
	 * @returns lconn.core.util.LCDeferred
	 */
	getSupportedActionsAsync: function() {
		var prom = null;

		if ( this.usingCre() ) {
			if (this._widgetAdapter != null) {
				prom = this._widgetAdapter.getSupportedActionsAsync();
			}
			else {
				prom = this.CREGetSupportedActions(); // this will be a promise
			}
		} else {
			var promise = new lconn.core.util.LCDeferred();
			supportedActions = this.EnablerGetSupportedActions(); // just a value
			promise.resolve(supportedActions);
			prom = promise;
		}

		// return the promise
		return prom;
	},

	CREGetSupportedActions: function() {
		return new lconn.core.util.LCChainedDeferred(this._handle.getWidgetInfo().then(dojo.hitch(this, function(data) {
			var supportedActions = [];
			var promise = new lconn.core.util.LCDeferred;

			if (data != null) {
				if ( this.isHomepageSpecificWidget() ) {
					var events = this._getIContext(data).iEvents.getEvents();
					for (var i=0; i<events.length; i++) {
						supportedActions.push(events[i].name);
					}
				} else {
					var metadata = this._getMetadata(data);
					if(metadata && metadata.supportedModes){
						supportedActions = metadata.supportedModes.split(' ');
					}
				}
			}
			promise.resolve(supportedActions);
			return(promise);
		})));
	},
	
	_getIContext: function(widgetRef){
		return widgetRef.getContainer() || widgetRef.widgetObject.getIContext(); 
	},

	_getMetadata: function(widgetRef){
		return widgetRef.getMetadata(); 
	},

	EnablerGetSupportedActions: function() {
		var supportedActions = [];

        var widget = serviceManager.getService("queryService").getWidgetById(this._iWidget.id);
        if (this.isHomepageSpecificWidget()) {
            // supported mode only based on the supported modes for homepage specific widgets
            supportedActions = widget.getHandledEventsNames();
        }
        else {
			if (widget != null){
				supportedActions = widget.getSupportedModes();
			}
        }

        return supportedActions;
	},

    getEventHandler: function(eventId){
        var widget = serviceManager.getService("queryService").getWidgetById(this._iWidget.id);
        return widget.getHandledEvent(eventId);
    },

	// Returns TRUE iff we're using CRE to load widgets
	usingCre: function() {
		return(typeof(lconn.core.config.services.nocreui) == "undefined");
	},

	_extendHandle: function(handle) {
		dojo.mixin(handle, {

			getMetaDataObject: function() {
				var meta = null;

				if (this._widgetSpec.componentType == "gadget") {
					meta = new com.ibm.lconn.gadget.container._Meta._GADGET_Meta_(this);
					}
				else
					meta = new com.ibm.lconn.gadget.container._Meta._WIDGET_Meta_(this);
				return(meta);
			}
		});
	},

	_showError: function(err) {
	// See if this is really an error
		var isError = false;

		// First see if handle is in an error state
		if (this._handle.getState() == com.ibm.lconn.gadget.container.Handle.ERROR) {
			isError = true;
		}

		// Otherwise, check the error object
		else if (typeof(err) != "string") {
			for (var i=0; i<err.length; i++) {
				if (err[i].widgetObject.domId == this.widgetId) {
				var er = err[i].isError();
					if (er == true) {
						isError = true;
					}
					break;
				}
			}
		}

		else {
			isError = true;
		}

		if (isError == true) {
			this._displayError();
		}
	},

	_displayError: function() {
		var rootNode = dojo.byId(this._iWidget.id + "_container");
		var errNode = dojo.query(".lotusMessage2", rootNode);
		if (errNode[0] != null) {
			dojo.style(errNode[0], 'display', 'block');
		}
	}
});

