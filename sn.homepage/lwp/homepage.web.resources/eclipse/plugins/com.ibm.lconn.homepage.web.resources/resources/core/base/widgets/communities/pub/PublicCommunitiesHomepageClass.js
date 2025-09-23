/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2007, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.base.widgets.communities.pub.PublicCommunitiesHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.homepage.widgets.communities.CommunitiesWidget");

dojo.declare("lconn.homepage.core.base.widgets.communities.pub.PublicCommunitiesHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	_communitiesWidget: null,
	bundle: null,	
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.communities.pub.PublicCommunitiesHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function () {			
		this.bundle = this.getResourceBundle();
			
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),   			 
			publicURL: this.getFeedUrl("publicURL")
		};
		widget.feedUrls = feeds
		
		//parameter maps
		var params =
		{			  				 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this._communitiesWidget = new lconn.homepage.widgets.communities.CommunitiesWidget({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters, 
			_modes: this.getSupportedModesMapping(),
			_iContext: this.iContext,
			_optionSet: this.getOptionSet() 
		});				
		
		dojo.place(this._communitiesWidget.communityRoot, this.getWidgetDomNode("root"), "last");
		
		this._loadMode("PublicCommunity");
			
	},
	
	loadPublicCommunityMode:function() {
		this._communitiesWidget.PublicCommunity(this.bundle.COMMUNITIES_PUBLIC_TITLE);
		this._currentModeFct = this.loadPublicCommunityMode;
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.communities.pub.PublicCommunitiesHomepageClass.superclass.onSaved.apply(this);
		this._communitiesWidget.update();
		
		//if (this._currentModeFct != null)
		//	this._currentModeFct();
	},
	
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	_currentModeFct: null	
		
});
