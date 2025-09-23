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

dojo.provide("lconn.homepage.core.widget.GadgetAdapter");
dojo.require("lconn.homepage.core.widget.iwExtension.HPItemSetHelper"); 
dojo.require('lconn.core.util.LCDeferredList');
dojo.require("dojo.NodeList-traverse");
dojo.require("com.ibm.lconn.gadget.support.WidgetAdapter");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

/**
 * Implementation of WidgetAdapter.Gadget for Gadgets used on HomePage
 * @name lconn.homepage.core.widget.GadgetAdapter
 * @class 
 * @extends com.ibm.lconn.gadget.support.WidgetAdapter.Gadget
 */

dojo.declare("lconn.homepage.core.widget.GadgetAdapter", [com.ibm.lconn.gadget.support.WidgetAdapter.Gadget], {
	
	isEditing: false,
	editCallback: null,
	_meta: null,
	_userPrefs: null, // JSON for user prefs
	_userPrefUI: null, // Helper code to deal with edit form setup and save
	_editNode: null, // Node to contain edit form
	_mContainer: null,
	_userPrefDefaults: null, // User pref default values to be used when composing edit form.
	strings: dojo.i18n.getLocalization("lconn.homepage", "jsp"), // Resource strings
	_widgetId: null, // id of div containing gadget
	_helpUrl: null, // URL for help
	_subscription: null, // Subscription to setPrefs

	/**
	 * @param {Object} obj - Object containing initialization values
	 * @param obj.handle {com.ibm.lconn.gadget.container.Handle} - handle to gadget
	 * @param obj.widgetSpec {Object} - Widget spec for gadget
	 * @param obj.mContainer {com.ibm.lconn.gadget.container.Container} - OS Container
	 * @param obj.meta {com.ibm.lconn.gadget.container.Meta} - Metadata object
	 * @name constructor
	 * @function
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @constructor
	 */
	constructor: function(obj) {
		this.handle = obj.handle; 
		this._widgetSpec = obj.widgetSpec;
		this._mContainer = obj.mContainer;
		this._userPrefDefaults = obj.userPrefDefaults;
		this._meta = obj.meta;
		this._userPrefUI = new lconn.homepage.core.base.userPrefUI;
		this._buttonResourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
		this._hpHelper = new lconn.homepage.core.widget.iwExtension.HPItemSetHelper(null);
		this._widgetId = this._widgetSpec.placement;
		
		var topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(obj.widgetSpec.definitionUrl, com.ibm.lconn.gadget.container.Topics.GadgetWindow.SET_PREFS);
		this._subscription = dojo.subscribe(topic, dojo.hitch(this, "_setPrefs"));
	},
	
	destroy: function() {
		if (this._subscription != null) {
			dojo.unsubscribe(this._subscription);
			this._subscription = null;
		}
	},
	
	_setPrefs: function(msg) {
		var prefs = msg.preferences;
		var newPrefs = this._userPrefDefaults;
		
		// Override default with new values, then update gadget.
		for (var i in prefs) {
		// Boolean pref are logical true/false - we use strings
			newPrefs[i] = prefs[i].toString();
		}
		this._updateUserPrefs(newPrefs, false);
		
	},
	
	/**
	 * Gets the list of actions supported by this site.
	 * 
	 * @see com.ibm.lconn.gadget.support.WidgetAdapter#getSupportedActionsAsync
	 * @example
	 * 	this.site = ...;
	 * 	site.getSupportedActionsAsync().then(
	 * 		function(items) {
	 * 			var i = 0, len = items.length;
	 * 			for (; i < len; i++) {
	 * 				addToMenu(items[i]);
	 * 			}
	 * 		}, function(error) {
	 *  		console.log('Failed to retrieve items');
	 *  	});
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name getSupportedActionsAsync
	 * @function
	 * @public
	 * @return {Object} Promise object that retrieves the supported actions.
	 */
	getSupportedActionsAsync: function() {
		var actionsPromise  = new lconn.core.util.LCDeferred();
		var metaPromise = new lconn.core.util.LCDeferredList([this._meta.userPrefs(), this._meta.helpLink()]);
		
		// Resolve the action promise when the two meta promises resolve.
		//
		metaPromise.then(dojo.hitch(this, function(results) {
			var actions = [];
			if ((results[0])[0] == true) {
				this._userPrefs = userPrefs = (results[0])[1];  // Second array has result, first is 'true'
				if (userPrefs != null && (this._userPrefUI.isEditable(userPrefs) == true))	 {
					actions.push(com.ibm.lconn.gadget.support.Site.Modes.EDIT);
				}
			}
			if ((results[1])[0] == true) {
				var help = (results[1])[1];
				if (typeof(help) != "undefined") {
					this._helpUrl = help;
					actions.push(com.ibm.lconn.gadget.support.Site.Modes.HELP);
				}
			}
			actions.push(com.ibm.lconn.gadget.support.Site.Modes.REFRESH);
			actionsPromise.resolve(actions);
		}));
		return(actionsPromise);
	},
	
	/**
	 * Invokes the help action.  
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name help
	 * @function
	 * @public
	 */
	 help: function() {
		// Launch separate window with help url
		// Global function!
		openHelpWindow(this._helpUrl);
	 },
	
	/**
	 * Invokes the edit action.  Shows UI to user for editing user preferences.
	 * Attaches action handlers to Save button.
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name edit
	 * @function
	 * @public
	 */
	edit: function(callback) {
		this.editCallback = callback;
		
		if (this.isEditing == false) {
			if (this._editNode == null)
			{
				var meta = this._meta;
				meta.userPrefs().then(dojo.hitch(this, function(userPrefs) {
					// Convert User Prefs to markup
					var editFormNode = this._userPrefUI.userPrefsToMarkup(userPrefs, this._userPrefDefaults, this._widgetSpec.placement );
					
					// Hook up buttons
					this._userPrefUI.attachActionsToButtons(editFormNode, this, "_onCancel", "_onSave", this._buttonResourceBundle["CANCEL_BUTTON"], this._buttonResourceBundle["SAVE_BUTTON"]);
					// Insert markup
					var widgetBodyDiv = dojo.query(".lotusWidgetBody, .lotusSectionBody", dojo.byId(this._widgetId + "_container"));
					
					// If we're in a right column (width restricted), override One UI length of fields; use '240px'
					var isInRightColumn = this._isInRightColumn(widgetBodyDiv);
					if (isInRightColumn == true) { 
						dojo.query(".widgetOptionText, .widgetOptionScroll", editFormNode).forEach(function(node) { 
							dojo.style(node, { width: "100%", minWidth: "200px", maxWidth: "240px"} ); 
						});
					}
					this._editNode = dojo.place(editFormNode, widgetBodyDiv[0], "before");
				}));	
			}
			else {
				dojo.removeClass(this._editNode, "lotusHidden");
			}
			this.isEditing = true;
		}
		else { // this.isEditing == true
			dojo.addClass(this._editNode, "lotusHidden");	
			this.isEditing = false;
		}
	},
	
	/**
	 * Returns true if the node is in the right, narrow column (class="lotusColRight")
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name _isInRightColumn
	 * @function
	 * @private
	 */
	_isInRightColumn: function(node) {
		var result = false;
		var nlist = dojo.query(node).parents(".lotusColRight");
		if (nlist.length > 0) {	
			result = true;
		}
		return(result);
		
		
	},
	
	/**
	 * Save handler
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name _onSave
	 * @function
	 * @private
	 */
	_onSave: function() {
		var userPrefs = this._userPrefUI.UserPrefFromForm(this._editNode);
		if (this._userPrefUI.hasAllRequired(this._userPrefs, userPrefs)) {
			// Remove error node
			var errorNode = dojo.query("#widgetError", this._editNode);
			if (errorNode.length != 0) dojo.destroy(errorNode[0]);
			dojo.addClass(this._editNode, "lotusHidden");
			this.isEditing = false;
			this._updateUserPrefs(userPrefs, true);
		}
		else {
			var errorNode = dojo.query("#widgetError", this._editNode);
			if (errorNode.length == 0) {
				var errorMessage = this.strings['jsp.widget.required'];
				var errorMarkup = '<span id="widgetError" style="display: block;" class="lotusMessage" > <img src="' + djConfig.blankGif + '" class="lconnSprite lconnSprite-iconError16" alt="Error"><span>' + errorMessage + '</span> </span>';
				dojo.place(errorMarkup, this._editNode, "first");
			}
		}
	},
	
	/**
	 * Cancel handler
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name _onSave
	 * @function
	 * @private
	 */
	_onCancel: function() {
		dojo.addClass(this._editNode, "lotusHidden");
		this.isEditing = false;
		if(this.editCallback) {this.editCallback();}
	},
	
	/**
	 * Updates and persists user prefs.  re-loads gadget to display with new user prefs.
	 * 
	 * @memberOf lconn.homepage.core.widget.GadgetAdapter
	 * @name updateUserPrefs
	 * @function
	 * @private
	 */
	 _updateUserPrefs: function(userPrefs, reload) {
		this._userPrefDefaults = userPrefs;
		var postBody = this._userPrefUI.serializeToString(userPrefs);
		this._hpHelper.post(this._widgetId, postBody);
		if (reload == true) {
			this.handle.unload();
		}
		if (this._widgetSpec.instanceData == null) this._widgetSpec.instanceData = {};
		if (this._widgetSpec.instanceData.renderParams == null) this._widgetSpec.instanceData.renderParams = {};
		this._widgetSpec.instanceData.renderParams.userPrefs = userPrefs;
		this._widgetSpec.placement = this._widgetId; // Reset placement id - not sure why CRE changes the placement attribute
		// Have to re-subscribe based on new gadget site id
		var topic = com.ibm.lconn.gadget.container.Topics.getSiteTopic(this._widgetId, com.ibm.lconn.gadget.container.Topics.GadgetWindow.SET_PREFS);
		dojo.subscribe(topic, dojo.hitch(this, "_setPrefs"));
		
		this.isEditing = false;
		if (this._editNode != null) {
			dojo.destroy(this._editNode);
			this._editNode = null;
		}
		
		if (reload == true) {
			this.handle = this._mContainer.loadWidget(this._widgetSpec);
		}
		if(this.editCallback) {this.editCallback();}
	 }
});
