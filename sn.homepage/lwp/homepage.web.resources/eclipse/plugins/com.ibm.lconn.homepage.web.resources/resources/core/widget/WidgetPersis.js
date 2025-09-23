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

dojo.provide("lconn.homepage.core.widget.WidgetPersis");


dojo.declare("lconn.homepage.core.widget.WidgetPersis", null, {
	constructor: function(){
	},
	
	_id: null,
	_pageID: null,
	_widgetSetting: null,
	_rowNum: null,
	_colNum: null,
	_maxOrmin: null,
	
	getId: function(){
		return this._id;
	},
	
	setId: function(id){
		this._id = id
	},
	
	getPageID: function(){
		return this._pageID;
	},
	
	setPageID:function(pageID){
		this._pageID = pageID;
	},
	
	getWidgetSetting: function(){
		return this._widgetSetting;
	},
	
	setWidgetSetting: function(widgetSetting){
		this._widgetSetting = widgetSetting
	},
	
	getRowNum: function(){
		return this._rowNum;
	},
	
	setRowNum: function(row){
		this._rowNum = row;
	},
	
	getColNum: function(){
		return this._colNum;
	},
	
	setColNum: function(col){
		this._colNum = col;
	},
	
	isMaxOrMin: function(){
		return this._maxOrmin;
	},
	
	setMaxOrMin: function(maxOrmin){
		this._maxOrmin = maxOrmin;
	}
});
