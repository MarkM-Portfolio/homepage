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

dojo.provide("lconn.homepage.core.base.widgets.activities.pub.PublicActivitiesHomepageClass");
dojo.require("lconn.homepage.core.base.baseDashboardIWidget");

dojo.declare("lconn.homepage.core.base.widgets.activities.pub.PublicActivitiesHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{	
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.activities.pub.PublicActivitiesHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function(){
		this._loadMode("Public");		
	},
	
	loadPublicMode: function(){
		this._destroyCurrentView();
		
		dojo.require("lconn.homepage.widgets.activities.PublicActivitiesWidget");
		
		var bundle = this.getResourceBundle();
		
		var widget = {};
		//feed maps
		var feeds = 
		{
			publicActivities: this.getFeedUrl("publicActivities")
		};
		widget.feedUrls = feeds
		
		//parameter maps
		var params =
		{			 				 
			xsltUrl: this.getDefaultParam("xsltUrlPublic"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this._currentView = new lconn.homepage.widgets.activities.PublicActivitiesWidget(
									{_feedUrls: widget.feedUrls,
		  							_parameters: widget.parameters,
									_modes: this.getSupportedModesMapping(),
									_iContext: this.iContext,
									_optionSet: this.getOptionSet(),
									modeTitle: bundle.ACTIVITIES_PUBLIC_ACTIVITIES_TITLE
									});												
		
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
		lconn.homepage.core.base.widgets.activities.pub.PublicActivitiesHomepageClass.superclass.onSaved.apply(this);
		
		this.onRefresh();
	},
	
	_loadResourceBundle: function(){		
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	_currentView: null		
});
