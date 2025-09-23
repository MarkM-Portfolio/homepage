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

dojo.provide("lconn.homepage.core.base.widgets.files.FilesHomepageClass");

dojo.require("lconn.homepage.core.base.baseDashboardIWidget");
dojo.require("lconn.homepage.widgets.files.FilesWidget");

dojo.declare("lconn.homepage.core.base.widgets.files.FilesHomepageClass", lconn.homepage.core.base.baseDashboardIWidget,{
	
	onLoad: function(){	
		lconn.homepage.core.base.widgets.files.FilesHomepageClass.superclass.onLoad.apply(this);
	},
	
	postOnLoad: function(){	
		this.bundle = this.getResourceBundle();	
		var widget = {};
		//feed maps
		var feeds = 
		{
			defaultURL: this.getFeedUrl("defaultURL"),
			imageURL: this.getFeedUrl("imageURL"),
			myFilesURL: this.getFeedUrl("myFilesURL"),
			sharedFilesURL: this.getFeedUrl("sharedFilesURL"),
			latestURL: this.getFeedUrl("latestFilesURL")
		};
		widget.feedUrls = feeds;
		
		//parameter maps
		var params =
		{						 
			xsltUrl: this.getDefaultParam("xsltUrl"),    				 
			mappingRemoteUrl: this.getDefaultParam("mappingRemoteUrl"),
			uploadURL: this.getDefaultParam("uploadURL")				
		};
		
		widget.parameters = params;
		
		this.filesWidget = new lconn.homepage.widgets.files.FilesWidget({ 
			_feedUrls: widget.feedUrls,
		  	_parameters: widget.parameters,
			_iContext: this.iContext,
			_optionSet: this.getOptionSet(),
			_modes: this.getModeName("currentMode") 
		});
		
		dojo.place(this.filesWidget.root, this.getWidgetDomNode("root"), "last");
		
		if (!this.loadPersistedMode()){
			try{
				this._loadMode(this.getModeName("currentMode"));//only one mode per widget as of 2.5	
			}
			catch (e){
				hp_console_debug(e);
			}
		}
	},	
	
	loadSharedFilesMode: function(){
		this.filesWidget.SharedFiles(this.bundle.FILES_TITLE_SHARED_ENTRIES);
		this._currentModeFct = this.loadSharedFilesMode;
	},
	
	loadMyFilesMode:function() {
		this.filesWidget.MyFiles(this.bundle.MY_TITLE);		
		this._currentModeFct = this.loadMyFilesMode;		
	},
	loadPopularFilesMode:function() {
		this.filesWidget.PopularFiles(this.bundle.POP_TITLE);
		this._currentModeFct = this.loadPopularFilesMode;
	},
		
	onUnload: function(){
	},
	
	onDestroy: function(){
	},
	
	onSaved: function(){
		lconn.homepage.core.base.widgets.files.FilesHomepageClass.superclass.onSaved.apply(this);
		this.filesWidget.update();	
		if (this._currentModeFct != null)
			this._currentModeFct();
	},
	
	_loadResourceBundle: function(){
		dojo.requireLocalization("lconn.homepage", "widgetstrings");
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "widgetstrings");
	},
	
	filesWidget: null,
	_currentModeFct: null,
	bundle: null
		
});
