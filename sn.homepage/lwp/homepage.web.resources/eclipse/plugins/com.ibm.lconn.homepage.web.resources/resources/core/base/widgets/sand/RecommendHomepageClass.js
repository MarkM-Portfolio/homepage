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

dojo.provide("lconn.homepage.core.base.widgets.sand.RecommendHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.sand.RecommendWidget");

dojo.declare("lconn.homepage.core.base.widgets.sand.RecommendHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.sand.RecommendHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function(){	
		this.bundle = this.getResourceBundle();	
		var widget = {};
				
		this.recommendWidget = new lconn.sand.RecommendWidget({ remoteUrl : this.getFeedUrl("defaultURL"),
																feedbackUrl: this.getFeedUrl("feedBackURL"),	
																xsltUrl: this.getDefaultParam("xsltUrl"),
																sourceList: this.getDefaultParam("sourceList") });
		
		dojo.place(this.recommendWidget.root, this.getWidgetDomNode("root"), "last");
		
		if (!this.loadPersistedMode()){
			try{
				this._loadMode(this.getModeName("currentMode"));//only one mode per widget as of 2.5	
			}
			catch (e){
				hp_console_debug(e);
			}
		}
	},	
	
	loadRecommendMode: function(){
		this.recommendWidget.Recommend();
		this._currentModeFct = this.loadRecommendMode;
	},
		
	onUnload: function(){
	},
	
	onDestroy: function(){
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.sand.RecommendHomepageClass.superclass.onSaved.apply(this);
		this.recommendWidget.update();	
		if (this._currentModeFct != null)
			this._currentModeFct();
	},
	
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	recommendWidget: null,
	_currentModeFct: null,
	bundle: null
		
});
