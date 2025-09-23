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

dojo.provide("lconn.homepage.core.common.Scroller");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");

dojo.require("lconn.homepage.core.common.ScrollerModel");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.common.Scroller",	
	[dijit._Widget, dijit._Templated, dijit._Container],	
	// properties and methods
	{
		templatePath: dojo.moduleUrl("lconn.homepage", "core/common/templates/scroller.html"),
		
		postCreate: function(){
			this._scrollerModel = new lconn.homepage.core.common.ScrollerModel();
			this._isVariableNumberOfEntries = false;
			this._scrollerModel.setRefreshedViewCallback(this, "updateView");
		},
		
		/**
		 * Set numerous properties for the scroller
		 */
		setProperties: function(args){
			this.setScrollingStatusTemplateString(args.templateString);
			
			this._scrollerModel.setInitParams(0, args.view, args.view);
			this._scrollerModel.setAlertThreshold(args.view);
			this._scrollerModel.setFetchMoreCallback(args.fetchCallbackObj, args.fetchCallbackFunc);
			
			args.domNode.innerHTML = "";	
			args.domNode.style.display = "";
			dojo.place(this.domNode, args.domNode, "first");	
		},
		
		getModel: function(){
			return this._scrollerModel;
		},
		
		postMixInProperties: function(){
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
		},
		
		_onPrevious: function(e){			
			this._scrollerModel.onPrevious();
			dojo.stopEvent(e);			
		},
		
		_onNext: function(e){			
			this._scrollerModel.onNext();	
			dojo.stopEvent(e);		
		},
		
		updateView: function(){
			// update displayed status
			var template = this.scrollingStatusTemplate ? this.scrollingStatusTemplate : this._resourceBundle['SCROLLING_STATUS'];	
			this.statusNode.innerHTML = this._formatStatusString(template);
			
			this._updateButtons();
		},
		
		_updateButtons: function(){
			var status = this._scrollerModel.getCurrentViewRange();
			
			if (status.begin == 0){
				this._disablePrevious();
			}
			else {
				this._enablePrevious();
			}
			
			if (status.end+1 >= this.getTotal()){
				this._disableNext();
			}
			else {
				this._enableNext();
			}
		},
		
		_disableNext: function(){			
			dojo.query("span", this.nextActionNode)[0].style.display = "";
			dojo.query("a", this.nextActionNode)[0].style.display = "none";
		},
		
		_enableNext: function(){			
			dojo.query("span", this.nextActionNode)[0].style.display = "none";
			dojo.query("a", this.nextActionNode)[0].style.display = "";
		},
		
		_disablePrevious: function(){			
			dojo.query("span", this.previousActionNode)[0].style.display = "";
			dojo.query("a", this.previousActionNode)[0].style.display = "none";
		},
		
		_enablePrevious: function(){
			dojo.query("span", this.previousActionNode)[0].style.display = "none";
			dojo.query("a", this.previousActionNode)[0].style.display = "";
		},	
		
		_formatStatusString: function(template){
			var status = this._scrollerModel.getCurrentViewRange();
			var total = this.getTotal();			
			
			return dojo.string.substitute(template, {begin: status.begin+1, end: status.end+1, total: total});
		},
		
		setScrollingStatusTemplateString: function(str){
			if (str != null){
				this.scrollingStatusTemplate = str;
			}
		},
		
		setTotal: function(total){
			if (total != null){
				this._total = total;
				this._isVariableNumberOfEntries = true;
				this.updateView();
				
				this._scrollerModel.setOverallTotal(total);
			}
		},
		
		getTotal: function(){
			var total = -1;
			
			if (this._isVariableNumberOfEntries){
				total = this._total;
			}
			else{
				total = this._scrollerModel.getTotal();
			}
			
			return total;
		},
		
		// node
		statusNode: null,
		nextActionNode: null,
		previousActionNode: null,
		
		// i18n strings
		scrollingStatusTemplate: null,		
		
		// private
		_resourceBundle: null,
		_scrollerModel: null,
		_isVariableNumberOfEntries: null,
		_total: null
		
	}
);
