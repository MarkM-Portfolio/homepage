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

dojo.provide("lconn.homepage.core.base.widgets.wiki.WikiHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.homepage.widgets.wikis.WikiWidget");

dojo.declare("lconn.homepage.core.base.widgets.wiki.WikiHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.wiki.WikiHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function(){	
		this.bundle = this.getResourceBundle();	
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),
			photoSvc: this.getFeedUrl("photoSvc"),
			myWikiURL: this.getFeedUrl("myWikiURL"),
			popularURL: this.getFeedUrl("popularURL"),
			latestURL: this.getFeedUrl("latestWikisURL")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{						 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this.wikiWidget = new lconn.homepage.widgets.wikis.WikiWidget({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters,
			_iContext: this.iContext,
			_optionSet: this.getOptionSet(),
			//_modes: this.getSupportedModesMapping(),
			_modes: this.getModeName("currentMode") 
		});
		
		dojo.place(this.wikiWidget.root, this.getWidgetDomNode("root"), "last");
		
		if (!this.loadPersistedMode()){
			try{
				this._loadMode(this.getModeName("currentMode"));//only one mode per widget as of 2.5	
			}
			catch (e){
				hp_console_debug(e);
			}
		}
	},	
	
	loadLatestWikiEntriesMode: function(){
		//this.wikiWidget.loadLatestWikiEntriesMode();
		this.wikiWidget.LatestWikis(this.bundle.WIKIS_TITLE_LATEST_ENTRIES);
		this._currentModeFct = this.loadLatestWikisMode;
	},
	
	loadMyWikisMode:function() {
		this.wikiWidget.MyWikis(this.bundle.WIKIS_TITLE_MY_ENTRIES);		
		this._currentModeFct = this.loadMyWikisMode;		
	},
	loadPopularWikisMode:function() {
		this.wikiWidget.PopularWikis(this.bundle.WIKIS_TITLE_POPULAR_ENTRIES);
		this._currentModeFct = this.loadPopularWikisMode;
	},
		
	onUnload: function(){
	},
	
	onDestroy: function(){
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.wiki.WikiHomepageClass.superclass.onSaved.apply(this);
		var newView = this.wikiWidget._getAmountOfEntriesToDisplay();
		//if more entries needed to load them
		if(this.wikiWidget.entriesPerPage < newView) {
			this.wikiWidget.entriesPerPage = newView;
			if (!this.loadPersistedMode()){
				try{
					this._loadMode(this.getModeName("currentMode"));
				}
				catch (e){
					hp_console_debug(e);
				}
			}
		//if show less entries just hidden the extra ones
		} else {
			this.wikiWidget.update();
		}
		
		if (this._currentModeFct != null)
			this._currentModeFct();
	},
	
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	wikiWidget: null,
	_currentModeFct: null,
	bundle: null
		
});
