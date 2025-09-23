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


dojo.provide("lconn.homepage.core.base.widgets.profiles.personal.MyProfileHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");

dojo.declare("lconn.homepage.core.base.widgets.profiles.personal.MyProfileHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	_profilesWidget: null,	
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.profiles.personal.MyProfileHomepageClass.superclass.onLoad.apply(this);
	},	
	
	postOnLoad: function () {			
		this._loadMode("MyProfile");	
	},
	loadMyProfileMode: function() {
		this._destroyCurrentView();
		
		dojo.require("lconn.homepage.widgets.profiles.MyProfile");
		
		var bundle = this.getResourceBundle();
		
		var widget = {};
		//feed maps
		var feeds = 
		{
			myProfileFds: this.getFeedUrl("myProfileFds"),
			friendingFds: this.getFeedUrl("friendingFds"),
			pendingFriendURL: this.getFeedUrl("pendingFriendURL"),
			simpleSearchResultURL: this.getFeedUrl("simpleSearchResultURL"),
			keywordSearchResultURL: this.getFeedUrl("keywordSearchResultURL"),
			profilePicture: this.getFeedUrl("profilePicture")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{			 				 
			xsltUrl: this.getDefaultParam("profileXsltUrl"),
			friendingXsltURL:this.getDefaultParam("friendingXsltURL"),
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this._currentView = new lconn.homepage.widgets.profiles.MyProfile({_feedUrls: widget.feedUrls,
		  						_parameters: widget.parameters,
								_modes: this.getSupportedModesMapping(),
								_iContext: this.iContext,
								_optionSet: this.getOptionSet(),
								modeTitle: bundle.PROFILES_MY_PROFILE_TITLE,
								_ownerClass:this});	
											
		
		dojo.place(this._currentView.domNode, this.getWidgetDomNode("root"), "last");	
	},	
	onSaved:function() {
		lconn.homepage.core.base.widgets.profiles.personal.MyProfileHomepageClass.superclass.onSaved.apply(this);
		if (!this.loadPersistedMode())
			this._loadMode("MyProfile");
	},
	_destroyCurrentView: function(){
		if (this._currentView)
			this._currentView.destroy();	
	},
	onDestroy: function(){
		this._destroyCurrentView();
	},
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	_currentView: null,
	removeEditMenuOption: true
});
