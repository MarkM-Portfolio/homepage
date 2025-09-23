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

dojo.provide("lconn.homepage.core.base.widgets.activitiesSideBar.ActivitiesHomepageClass");
dojo.require("lconn.homepage.core.base.baseDashboardIWidget");

dojo.declare("lconn.homepage.core.base.widgets.activitiesSideBar.ActivitiesHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{	
	
	onLoad: function(){		
		this._domID = "_" + this.iContext.widgetId + "_";	
		//base.iWidget.activitiesSideBar.ActivitiesHomepageClass.superclass.onLoad.apply(this);
		this.loadNormalMode();
	},
	
	postOnLoad: function(){
		//if (!this.loadPersistedMode())
		//	this._loadMode("Normal");		
	},
	
	loadNormalMode: function(){		
		this._destroyCurrentView();		
		
		dojo.require("lconn.homepage.widgets.activities.ActivitiesSideBar");				
		
		var bundle = this.getResourceBundle();		
		
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),
			activities: this.getFeedUrl("activities"),
			responses: this.getFeedUrl("responses"),
			todos: this.getFeedUrl("todos")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{			    				 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		var actualWidget = new lconn.homepage.widgets.activities.ActivitiesSideBar({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters,
			_optionSet: this.getOptionSet(),
			_iContext: this.iContext
		});
		
		this._currentView = actualWidget;
		hp_console_debug(this._currentView.domNode);		
		hp_console_debug(this.getWidgetDomNode("root"));
		
		dojo.place(this._currentView.domNode, this.getWidgetDomNode("root"), "last");
	},	
	
	_destroyCurrentView: function(){
		if (this._currentView)
			this._currentView.destroy();
	},
	
	onDestroy: function(){
		this._destroyCurrentView();
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.activitiesSideBar.ActivitiesHomepageClass.superclass.onSaved.apply(this);
		
		// just refresh the current mode for now
		//this.onRefresh();
		this.iContext.iEvents.fireEvent("onRefreshNeeded");
	},
	
	_loadResourceBundle: function(){		
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	_currentView: null	
		
});
