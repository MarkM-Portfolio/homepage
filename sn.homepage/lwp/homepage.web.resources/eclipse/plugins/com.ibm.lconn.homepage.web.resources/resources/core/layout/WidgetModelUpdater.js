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

dojo.provide("lconn.homepage.core.layout.WidgetModelUpdater");
dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.declare("lconn.homepage.core.layout.WidgetModelUpdater", null, {	
	// summary: listen to dnd event and update WidgetLayoutModel instance accordingly
	
	constructor: function(/* lconn.homepage.core.layout.WidgetLayoutModel */layoutModel){		
		if (layoutModel != null){
			this._layoutModel = layoutModel;
			
			dojo.subscribe(lconn.homepage.events.dnd.DROP, this, "updateFromDnd");
			dojo.subscribe(lconn.homepage.events.widget.DESTROY, this, "removeWidgetFromDnd");			
		}
	},
	
	updateFromDnd: function(source, target, node, /* boolean?*/ dontPersist){	
		this._layoutModel.removeAllWidgets();
		
		var pageType = this._layoutModel.getPageType();		
		if (pageType == lconn.homepage.core.layout.WidgetLayoutModel.prototype.WIDGET_TAB){
			this._updateLayoutWidgetPage(source, target, node, dontPersist);
		} else {
			this._updateLayoutUpdatePage(source, target, node, dontPersist);
		}		
	},
	
	_updateLayoutWidgetPage: function(source, target, node, dontPersist){
		var currentTableNode = node.parentNode.parentNode;
		var childNodes = currentTableNode.childNodes;
		var updatePosString = "";

		for(var i=0;i<childNodes.length;i++){
			// mapping for column index and dnd style. might be worth refactoring when we have time
			var col = childNodes[i].id=="dnd" ? 0 : (childNodes[i].id=="dnd2" ? 1 : 2);
	
			var dndNodes = childNodes[i].childNodes;
		
			for(var j=0;j<dndNodes.length;j++){				
				if(dndNodes[j].nodeName=="DIV"){
					this._layoutModel.appendWidgetToColumn(dndNodes[j].getAttribute("widgetid"), col);
				}
			}			
		}	
	
		if (typeof dontPersist == "undefined" || ((typeof dontPersist != "undefined") && (!dontPersist))){
			this._fireUpdatedEvent();
		}
	},
	
	_updateLayoutUpdatePage: function(source, target, node, dontPersist){
		// TODO: change id to something more obvious
		var containerBar = dojo.byId("1");
		
		var widgetContainers = dojo.query("[widgetId]", containerBar);		
		
		var that = this;
		
		dojo.forEach(widgetContainers, function(container){
			if (container.parentNode == containerBar){
				that._layoutModel.appendWidgetToColumn(container.getAttribute("widgetid"), 0);
			}
		});
		
		if (typeof dontPersist == "undefined" || ((typeof dontPersist != "undefined") && (!dontPersist))){
			this._fireUpdatedEvent();
		}
	},
	
	_fireUpdatedEvent: function(){		
		dojo.publish(lconn.homepage.events.layout.UPDATED);
	},
	
	removeWidgetFromDnd: function(widgetObj/*, action*/){
		
		var widgetId = widgetObj.domNode.getAttribute("widgetid");
		this._layoutModel.removeWidget(widgetId);
		
		this._fireUpdatedEvent();
	},
	
	_layoutModel: null
});
