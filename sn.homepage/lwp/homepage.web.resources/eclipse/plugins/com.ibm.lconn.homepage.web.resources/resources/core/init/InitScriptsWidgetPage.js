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

dojo.provide("lconn.homepage.core.init.InitScriptsWidgetPage");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.core.widget.IWidgetWrapper");
dojo.require("lconn.homepage.core.factory.WidgetFactory");
dojo.require("lconn.homepage.core.common.DashboardPanel");
dojo.require("lconn.homepage.core.widget.WidgetTypeEnum");
dojo.require("lconn.homepage.core.base.baseMyPageSupport");
dojo.require("lconn.homepage.core.init.InitJapaneseStyles");

//TODO: refactor this part to elimitate global variables and functions
dojo.addOnLoad(function(){
	window.factory = new lconn.homepage.core.factory.WidgetFactory();
	window.myPageSupport = new lconn.homepage.core.base.baseMyPageSupport();
	window.dndSources = [];

	_putCustomizeLinkOnRightTabOrder();
});

/*
 * a11y fix.
 * wraps the customize link with side links that will enable keyboard navigation as if this link was in the widget content pane.
 */
function _putCustomizeLinkOnRightTabOrder(){

	//widget pane start
	var hiddenLink = dojo.byId("firstColumn");
	var customizeLink = dojo.byId("paletteLink");

	//jump to customize on focus
	if(hiddenLink && customizeLink){
		dojo.connect(hiddenLink, "onfocus", function(){
			customizeLink.focus();
		});

		dojo.attr(customizeLink, "tabindex", "-1");

		//wrap customize
		var beforeCustomize = dojo.create("a", {href:"javascript:;", id:"beforePaletteLink", innerHTML:"&nbsp;"});
		var afterCustomize = dojo.create("a", {href:"javascript:;", id:"afterPaletteLink", innerHTML:"&nbsp;"});

		dojo.place(beforeCustomize, customizeLink, "before");
		dojo.place(afterCustomize, customizeLink, "after");

		//the previous focusable element before "customize link" is the last div from homepageLeftNavigationMenu
		var leftItemArray = dojo.query(".lotusMenuSection:last-child a","homepageLeftNavigationMenu");
		var previous = null;
		if(leftItemArray && leftItemArray.length == 1)
			previous = leftItemArray[0];
		if(previous){
			dojo.connect(beforeCustomize, "onfocus", function(){
				previous.focus();
			});
		}

		//the next focusable element
		dojo.connect(afterCustomize, "onfocus", function(){
			var actionMenuArray = determinePointOfFocusAfterCustomizeLink();
			actionMenuArray[0].focus();
		});
	}
}

// Returns the desired point of focus at index 0 of the returned node list
function determinePointOfFocusAfterCustomizeLink(){
	var actionMenuArray;

	if(dojo.query("#dnd a[dojoattachpoint=\"maxNode\"]").length !== 0) {
		actionMenuArray = dojo.query("#dnd a[dojoattachpoint=\"maxNode\"]");
	} else if(dojo.query("#dnd2 a[dojoattachpoint=\"maxNode\"]").length !== 0) {
		actionMenuArray = dojo.query("#dnd2 a[dojoattachpoint=\"maxNode\"]");
	} else {
		actionMenuArray = dojo.query("#lotusFooter a");
	}

	return actionMenuArray;
}

// TODO: refactor this part to elimitate global variables and functions
function initDndForWidget(widget, dndContainer) {
	dojo.addClass(widget.domNode, "dojoDndItem");
	dojo.addClass(widget.titleBar, "ibmDndDragHandle");

	dndContainer.setItem(widget.domNode.id, {
		data :widget.title,
		type : [ "text" ]
	});
}

function insertWidget(widget, appendChild, dndContainer) {

	var dndContainer = dndContainer ? dndContainer : dndSources[0];

	if ( layoutModel.canCustomize() ) {
		initDndForWidget(widget, dndContainer);
	}

	if (!appendChild) {
		dojo.place(widget.domNode, dndContainer.node, "first");
	} else {
		dojo.place(widget.domNode, dndContainer.node, "last");
	}

	widget.startup();

	// SPR PMAN7QAEPL
	if (dojo.isIE) {
		if (layoutModel.getNumberOfColumns() == 3) {
			dojo.addClass(widget.domNode, "lotusWidget3Col");
		}
	}
}

// remove properly the reference related to dnd
function removeWidget(widget) {
	var dnd = getDndSourceObj(widget.domNode.parentNode);
	dnd.delItem(widget.domNode.id);
}

dojo.addOnLoad( function() {
	dojo.subscribe(lconn.homepage.events.widget.DESTROY, "removeWidget");
});

function moveInCol(widget, up) {
	var pos = layoutModel.getWidgetPosition(widget.id);

	var swapWithWidget;

	if (up) {
		pos.y--;
		swapWithWidget = dijit.byId(layoutModel.getWidget(pos));
		dojo.place(widget.domNode, swapWithWidget.domNode, "before");
	} else {
		pos.y++;
		swapWithWidget = dijit.byId(layoutModel.getWidget(pos));
		dojo.place(widget.domNode, swapWithWidget.domNode, "after");
	}

	dojo.publish(lconn.homepage.events.dnd.DROP, [ null, null, widget.domNode ]);
}

function moveLeft(widget) {
	var currentCol = widget.domNode.parentNode.id == "dnd" ? 0
			: widget.domNode.parentNode.id == "dnd2" ? 1 : 2;
	var dndTarget = widget.domNode.parentNode.id == "dnd2" ? "dnd" : "dnd2";
	dndSources[currentCol].delItem(widget.domNode.id);

	dndSources[(currentCol - 1)].setItem(widget.domNode.id, {
		data :widget.title,
		type : [ "text" ]
	});

	dojo.place(widget.domNode, dojo.byId(dndTarget), "first");
	dojo.publish(lconn.homepage.events.dnd.DROP, [ null, null, widget.domNode ]);
}

function moveRight(widget) {
	var currentCol = widget.domNode.parentNode.id == "dnd" ? 0
			: widget.domNode.parentNode.id == "dnd2" ? 1 : 2;
	var dndTarget = widget.domNode.parentNode.id == "dnd" ? "dnd2" : "dnd3";
	dndSources[currentCol].delItem(widget.domNode.id);

	dndSources[(currentCol + 1)].setItem(widget.domNode.id, {
		data :widget.title,
		type : [ "text" ]
	});

	dojo.place(widget.domNode, dojo.byId(dndTarget), "first");
	dojo.publish(lconn.homepage.events.dnd.DROP, [ null, null, widget.domNode ]);
}

// TODO: automate and get values from db/catalog
function initWidgets() {
	if ( !layoutModel.canCustomize() ) {
		// remove the 'move' cursor from widget titlebars
		dojo.addClass(dojo.byId('dndtable'), "hpNoCustomize");
	}

	var dnd = new lconn.homepage.core.dnd.Source(dojo.byId("dnd"));
	var dnd2 = new lconn.homepage.core.dnd.Source(dojo.byId("dnd2"));

	dndSources.push(dnd);
	dndSources.push(dnd2);

	var batchLoadWidgets = false;

	if(typeof(lconn.core.config.services.nocreui) == "undefined"){
		var cxnWidgetLoadQueue = [];
		var nonCxnWidgetLoadQueue = [];
		var container = com.ibm.lconn.gadget.container.iContainer2;
		var numWidgets = 0;


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
				if(--numWidgets == 0){ //check if no cxn widgets present and 3rd party exist - ensure loading
					if(typeof cxnWidgetLoadQueue != 'undefined' && cxnWidgetLoadQueue.length == 0){
						batchLoad(nonCxnWidgetLoadQueue, loadNonCxnWidgets);
					} else { //cxn widgets exist - 3rd parties are loaded after
						batchLoad(cxnWidgetLoadQueue, loadCxnWidgets);
					}					
				}					
				return promise;
			};

		var loadCxnWidgets = function() {
				loadWidgetsQueue(cxnWidgetLoadQueue);

				// Then do non-connections widgets.
				preLoadWidgetsQueue(nonCxnWidgetLoadQueue, loadNonCxnWidgets);
			};

			var loadNonCxnWidgets = function() {
				loadWidgetsQueue(nonCxnWidgetLoadQueue);
			};

			var batchLoad = function(queue, loadFunction){
				preLoadWidgetsQueue(queue, loadFunction);
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
					queue[i]["promise"].callback(widgetHandles[i]);
				}
			};

		var mixinGetHandle = function(widget){
			widget.getBodyWidget().getWidgetHandle = getWidgetHandleMixin;
		};
	}

	numWidgets = (firstColWidgets.length + secondColWidgets.length + thirdColWidgets.length);
	for ( var i = 0; i < firstColWidgets.length; i++) {
		var widget = factory.createWidgetInstanceById(
				firstColWidgets[i].widgetId,
				firstColWidgets[i].widgetInstanceId,
				firstColWidgets[i].setting);
		if(batchLoadWidgets)
			mixinGetHandle(widget);
		insertWidget(widget, true, dnd);
	}

	for ( var i = 0; i < secondColWidgets.length; i++) {
		var widget = factory.createWidgetInstanceById(
				secondColWidgets[i].widgetId,
				secondColWidgets[i].widgetInstanceId,
			 secondColWidgets[i].setting);
		if(batchLoadWidgets)
			mixinGetHandle(widget);
		insertWidget(widget, true, dnd2);
	}

	if (layoutModel.getNumberOfColumns() == 3) {
		var dnd3 = myPageSupport.addThirdColumn(null, true);

		for ( var i = 0; i < thirdColWidgets.length; i++) {
			var widget = factory.createWidgetInstanceById(
					thirdColWidgets[i].widgetId,
					thirdColWidgets[i].widgetInstanceId,
					thirdColWidgets[i].setting);
			if(batchLoadWidgets)
				mixinGetHandle(widget);
			insertWidget(widget, true, dnd3);
		}
	}

	dojo.publish(lconn.homepage.events.init.COMPLETED);
}

dojo.addOnLoad(initWidgets);

function getDndSourceObj(dndNode) {
	for ( var i = 0; i < dndSources.length; i++) {
		if (dndSources[i].node == dndNode)
			return dndSources[i];
	}

	return null;
}
