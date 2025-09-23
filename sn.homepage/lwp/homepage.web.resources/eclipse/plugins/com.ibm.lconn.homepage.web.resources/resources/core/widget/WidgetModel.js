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

dojo.provide("lconn.homepage.core.widget.WidgetModel");


dojo.declare("lconn.homepage.core.widget.WidgetModel", null, {
	constructor: function(){
	},
	
	_id: null,
	_title: null,
	//_text:null,
	_url: null,
	_iconUrl: null,
	_feedUrls: null,
	_parameters: null,
	_isSystem: null,
	_isEnabled: null,
	
	
	getId: function(){
		return this._id;
	},
	
	setId: function(id){
		this._id = id
	},
	
	setTitle: function(title){
		this._title = title;
	},
	
	getTitle: function(){
		return this._title;
	},
	
	getUrl: function(){
		return this._url;
	},
	
	setUrl: function(url){
		this.url = url;
	},
	
	getIconURL: function(){
		return this._iconUrl;
	},
	
	setIconURL:function(iconURL){
		this._iconUrl = iconURL;
	},
	
	getFeedURL: function(){
		return this._feedUrls;
	},
	
	setFeedURL: function(feedURL){
		this._feedUrls = feedURL;
	},
	
	getParameters: function(){
		return this._parameters;
	},
	
	setParameters: function(param){
		this._parameters = param;
	},
	
	isSystem: function(){
		return this._isSystem;
	},
	
	setSystem: function(system){
		this._isSystem = system;
	},
	
	isEnabled: function(){
		return this._isEnabled;
	},
	
	setEnabled: function(enable){
		this._isEnabled = enable;
	}
	
	
});




