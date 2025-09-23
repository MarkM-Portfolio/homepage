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

dojo.provide("lconn.homepage.core.base.widgets.dogear.watching.WatchListHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.homepage.widgets.bookmarks.BookmarkWidget");

dojo.declare("lconn.homepage.core.base.widgets.dogear.watching.WatchListHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	_dogearWidget: null,
	bundle: null,	
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.dogear.watching.WatchListHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function () {	
		this.bundle = this.getResourceBundle();
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),
			myBookURL: this.getFeedUrl("myBookURL"),
			watchURL: this.getFeedUrl("watchURL"),
			popularURL: this.getFeedUrl("popularURL")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{			  				 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this._dogearWidget = new lconn.homepage.widgets.bookmarks.BookmarkWidget({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters,
			_modes: this.getSupportedModesMapping(),
			_iContext: this.iContext,
			_optionSet: this.getOptionSet() 
		});
		
		dojo.place(this._dogearWidget.dogearRoot, this.getWidgetDomNode("root"), "last");
		
		this._loadMode("WatchList");
				
	},
	loadWatchListMode:function() {
		this._dogearWidget.WatchList(this.bundle.BOOKMARKS_WATCH_TITLE);
		this._currentModeFct = this.loadWatchListMode;
	},
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
		
	onSaved: function(){
		lconn.homepage.core.base.widgets.dogear.watching.WatchListHomepageClass.superclass.onSaved.apply(this);
		this._dogearWidget.update();
		
		if (this._currentModeFct != null)
			this._currentModeFct();
	},
	
	_currentModeFct: null	
});
