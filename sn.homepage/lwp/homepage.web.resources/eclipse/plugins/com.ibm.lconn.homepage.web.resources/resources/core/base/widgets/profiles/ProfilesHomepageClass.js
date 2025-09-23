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


dojo.provide("lconn.homepage.core.base.widgets.profiles.ProfilesHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");

dojo.declare("lconn.homepage.core.base.widgets.profiles.ProfilesHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	_profilesWidget: null,	
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.profiles.ProfilesHomepageClass.superclass.onLoad.apply(this);
	},	
	
	postOnLoad: function () {			
		if (!this.loadPersistedMode())
			this._loadMode("MyProfile");	
	},
	loadMyProfileMode: function(keepfocus) {
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
		
		// if required, put focus back onto the mode button (vs <body>)
		if ( keepfocus ) {
			this.focusModeSwitchButton();
		}
	},
	loadMyColleaguesMode: function(keepfocus){
		this._destroyCurrentView();

		dojo.require("lconn.homepage.widgets.profiles.MyColleagues");

		var bundle = this.getResourceBundle();
	
		var widget = {};
		//feed maps
		var feeds = 
		{
			myProfileFds: this.getFeedUrl("myProfileFds"),
			simpleSearchResultURL: this.getFeedUrl("simpleSearchResultURL"),
			keywordSearchResultURL: this.getFeedUrl("keywordSearchResultURL"),
			profilePicture: this.getFeedUrl("profilePicture"),
			friendingFds: this.getFeedUrl("friendingFds"),
			blogFds: this.getFeedUrl("blogFds"),
			dogearFds: this.getFeedUrl("dogearFds"),
			recentActivitiesURL: this.getFeedUrl("recentActivitiesURL"),
			commonActivitiesFds: this.getFeedUrl("commonActivitiesFds"),
			pendingFriendURL: this.getFeedUrl("pendingFriendURL"),
			profileServer: this.getDefaultParam("profileServer")
			
		};
		widget.feedUrls = feeds;
		var services = {
			activitiesService: this.iContext.getItemSet("services").getItemValue("activities"),
			dogearService: this.iContext.getItemSet("services").getItemValue("dogear"),
			blogsService: this.iContext.getItemSet("services").getItemValue("blogs")
		};
		widget.services = services;
		//parameter maps
		var params =
		{			 				 
			xsltUrl: this.getDefaultParam("friendingXsltURL"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this._currentView = new lconn.homepage.widgets.profiles.MyColleagues({_feedUrls: widget.feedUrls,
		  						_parameters: widget.parameters,
								_modes: this.getSupportedModesMapping(),
								_iContext: this.iContext,
								_optionSet: this.getOptionSet(),
								modeTitle: bundle.PROFILES_MY_COLLEAGUES_TITLE,
								_services: widget.services,
								_ownerClass:this});	
											
		
		dojo.place(this._currentView.domNode, this.getWidgetDomNode("root"), "last");
		
		// if required, put focus back onto the mode button (vs <body>)
		if ( keepfocus ) {
			this.focusModeSwitchButton();
		}
	},
	onSaved:function() {
		lconn.homepage.core.base.widgets.profiles.ProfilesHomepageClass.superclass.onSaved.apply(this);
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
	_currentView: null
});
