/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.init.InitScriptsUpdatesPage");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.core.widget.IWidgetWrapper");
dojo.require("lconn.homepage.core.factory.WidgetFactory");
dojo.require("lconn.homepage.core.dnd.HomepageMovable");
dojo.require("lconn.homepage.core.init.InitJapaneseStyles");

//TODO: refactor this part to elimitate global variables and functions
dojo.addOnLoad(function(){
	window.factory = new lconn.homepage.core.factory.WidgetFactory();
	window.dndContainer;
});

function insertWidget(widget, appendChild){
	if (!appendChild){
		dojo.place(widget.domNode, dndContainer.node, "first");
	} else {
		dojo.place(widget.domNode, dndContainer.node, "last");
	}
	widget.startup();
}

//TODO: automate and get values from db/catalog
function initWidgets(){
	var sideBarContainer = dojo.byId("1");

	if (sideBarContainer != null) {


		var batchLoadWidgets = false;

		if(typeof(lconn.core.config.services.nocreui) == "undefined"){
			var cxnWidgetLoadQueue = [];
			var nonCxnWidgetLoadQueue = [];
			var numWidgets = 0;
			var container = com.ibm.lconn.gadget.container.iContainer2;

			com.ibm.lconn.gadget.container.iContainer2.getCommonContainer().then(function() {
				// Define endpoint for persistence for iWidget settings
				cre$.endpoint.registerEndpoints([{
					name: "persistence",
					url: lconn.core.url.getServiceUrl(lconn.core.config.services.homepage) + '/web/itemSetPersistence.action'
				}]);

				// Define a 'pageId' (which is our TabInstId). This will be passed to persistence endpoint by CRE
				cre$.config.pageId = layoutModel.getPageId();
			});

			batchLoadWidgets = true;
			var getWidgetHandleMixin = function(def){
				var promise = new lconn.core.util.LCDeferred();
				var queueEntry = {"promise": promise, "def": def};
				if (this.isSystem == "true") {
					cxnWidgetLoadQueue.push(queueEntry);
				}
				else {
					nonCxnWidgetLoadQueue.push(queueEntry);
				}
				if(--numWidgets == 0)
					batchLoad();
				return promise;
			};

			var loadCxnWidgets = function() {
				// When the pre-load is resolved, pop the EE gadget and do the full load of the remaining widgets.
				cxnWidgetLoadQueue.pop();
				loadWidgetsQueue(cxnWidgetLoadQueue);
			};

			var loadNonCxnWidgets = function() {
				loadWidgetsQueue(nonCxnWidgetLoadQueue);
			};

			var batchLoad = function(){
				// First do a pre-load so we can pre-load AS gadget
				var urlContext = lconn.core.url.getServiceUrl(lconn.core.config.services.webresources);
				var eeUrl = urlContext.uri + "/web/com.ibm.social.ee/ConnectionsEE.xml";
				var asDef = {
					"def": {
						componentType: "gadget",
						definitionUrl: eeUrl
						}
				};
				cxnWidgetLoadQueue.push(asDef);
				preLoadWidgetsQueue(cxnWidgetLoadQueue, loadCxnWidgets);

				// Then do non-connections widgets.
				preLoadWidgetsQueue(nonCxnWidgetLoadQueue, loadNonCxnWidgets);
			};

			var preLoadWidgetsQueue = function(queue, loadFunction) {
				var defs = [];
				var i;
				for (i=0; i<queue.length; i++) {
					defs.push(queue[i]["def"]);
				}
				if (defs.length > 0) {
					container.preloadWidgets(defs).then(loadFunction, loadFunction);
				}
			};

			var loadWidgetsQueue = function(queue) {
				var defs = [];
				var i;
				for (i=0; i<queue.length; i++) {
					defs.push(queue[i]["def"]);
				}
				var widgetHandles = com.ibm.lconn.gadget.container.iContainer2.loadWidgets(defs);
				for(i = 0; i < queue.length; i++){
					queue[i]["promise"] && queue[i]["promise"].callback(widgetHandles[i]);
				}
			};

			var mixinGetHandle = function(widget){
				widget._bodyWidget.getWidgetHandle = getWidgetHandleMixin;
			};
		}

		dndContainer = new lconn.homepage.core.dnd.Source(sideBarContainer);
		numWidgets = firstColWidgets.length;
    	//hard-coded meetings widget id to check App Registry
        var MEETINGS_WIDGET_ID = "1520aa1-c2fa-48ef-be05-8dee630c0054";
        var appRegistryPromise;
				
		for(i=0; i<firstColWidgets.length; i++){
			//Only check App Registry settings if the widget is meetings.
            if (firstColWidgets[i].widgetId === MEETINGS_WIDGET_ID) {
             	 //let other widgets load first
                 appRegistryPromise = checkShowHideMeetingsWidgetByAppRegistry(firstColWidgets[i], (batchLoadWidgets)?mixinGetHandle:null);
             }
             else {
                  var widget = factory.createWidgetInstanceById(firstColWidgets[i].widgetId, firstColWidgets[i].widgetInstanceId, firstColWidgets[i].setting, true);
                  if(batchLoadWidgets)
                      mixinGetHandle(widget);
                  insertWidget(widget, true);
             }
		}
		if(appRegistryPromise) {
			appRegistryPromise.then(function(hideMeetings) {
				//only handle numWidgets if meetings widget needs to be hidden, otherwise initWidgets() will take care of it.
				if (hideMeetings) {
		            if(numWidgets == 1) {
		                //run batchLoad explicitly, as other widgets has already ready.
		                batchLoad();
		            } else {
		                //other widgets not yet ready, just decrease numWidgets and let remaining widget to run batchLoad.
		                numWidgets--;
		            }
				}		
	        });
		}
		
	}
	
	dojo.publish(lconn.homepage.events.init.COMPLETED);
}

function checkShowHideMeetingsWidgetByAppRegistry(meetings_widget, mixinGetHandle) {
    var placeholder = dojo.place("<div style='display:none;'></div>", dndContainer.node, "last");
    var APP_SERVICE = "TopNavigationBar";
    var EXTENSION_PATH = ".stmeetings";
    var EXTENSION_TYPE = "com.ibm.action.delete";

    var xhrArgs = {
        	url: "/appregistry/api/v2/services/" + APP_SERVICE,
        	handleAs: 'json'
    }    
    var promise = dojo.xhrGet(xhrArgs).then(
    	function success(data) {
    		var hide_widget = false;
        	for (var i = 0; i < data.applications.length; i++) {
    			var app = data.applications[i];
        		for (var j = 0; j < app.extensions.length; j++) {
    				var ext = app.extensions[j];
        			if ((ext.path === EXTENSION_PATH) && (ext.type === EXTENSION_TYPE)) {
        		    	console.log("hide SameTime meetings App Registry exist, hide meetings widget.");
        		        dojo.destroy(placeholder);
        		        hide_widget = true;
        		        break;
        			}
        		}
        		if (hide_widget) {
        			break;
        		}	
        	}
        	if (!hide_widget) {
           		console.log("hide SameTime meetings App Registry not exist, show meetings widget.");
           		var widget = factory.createWidgetInstanceById(meetings_widget.widgetId, meetings_widget.widgetInstanceId, meetings_widget.setting, true);
           		dojo.place(widget.domNode, placeholder, "replace");
           		if(mixinGetHandle)
               		mixinGetHandle(widget);
           		widget.startup();
        	}
        	return hide_widget;
    	},
    	function error() {
    		var hide_widget = false;
    		console.log("got error whening getting hide SameTime meetings App Registry, show meetings widget.");
    		var widget = factory.createWidgetInstanceById(meetings_widget.widgetId, meetings_widget.widgetInstanceId, meetings_widget.setting,true);
    		dojo.place(widget.domNode, placeholder, "replace");
    		if(mixinGetHandle)
    			mixinGetHandle(widget);
    		widget.startup();
    		return hide_widget;
    	}
    );
    return promise;
}

function initDndForWidget(widget){

	new lconn.homepage.core.dnd.HomepageMovable(widget.domNode, {
		area: "content",
		within: true,
		handle: widget.titleBar
	});
}

function moveInCol(widget, up){
	var pos = layoutModel.getWidgetPosition(widget.id);

	var swapWithWidget;

	if (up){
		pos.y--;
		swapWithWidget = dijit.byId(layoutModel.getWidget(pos));
		dojo.place(widget.domNode, swapWithWidget.domNode, "before");
	}
	else{
		pos.y++;
		swapWithWidget = dijit.byId(layoutModel.getWidget(pos));
		dojo.place(widget.domNode, swapWithWidget.domNode, "after");
	}

	dojo.publish(lconn.homepage.events.dnd.DROP, [null, null, widget.domNode]);
}

dojo.addOnLoad(initWidgets);
