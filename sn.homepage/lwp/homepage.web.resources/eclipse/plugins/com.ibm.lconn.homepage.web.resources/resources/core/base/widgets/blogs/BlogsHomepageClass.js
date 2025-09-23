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

dojo.provide("lconn.homepage.core.base.widgets.blogs.BlogsHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.homepage.widgets.blogs.BlogsWidget");

dojo.declare("lconn.homepage.core.base.widgets.blogs.BlogsHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.blogs.BlogsHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function(){		
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),
			photoSvc: this.getFeedUrl("photoSvc")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{						 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl")				
		};
		
		widget.parameters = params;
		
		this.blogsWidget = new lconn.homepage.widgets.blogs.BlogsWidget({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters,
			_iContext: this.iContext,
			_optionSet: this.getOptionSet() 
		});
		
		dojo.place(this.blogsWidget.root, this.getWidgetDomNode("root"), "last");
		
		try{
		this._loadMode("LatestBlogEntries");	
		}
		catch (e){
			hp_console_debug(e);
		}
	},	
	
	loadLatestBlogEntriesMode: function(){
		this.blogsWidget.loadLatestBlogEntriesMode();
	},
	
	onUnload: function(){
	},
	
	onDestroy: function(){
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.blogs.BlogsHomepageClass.superclass.onSaved.apply(this);
		this.blogsWidget.update();	
	},
	
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	blogsWidget: null	
});
