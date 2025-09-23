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

dojo.provide("lconn.homepage.core.common.ScrollerModel");

dojo.require("dojox.collections.Dictionary");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.common.ScrollerModel",
	
	null ,
	
	// properties and methods
	{
		// some "consts"
		NEXT_CALLBACK: "next",
		PREVIOUS_CALLBACK: "previous",		
		FETCH_NEEDED_CALLBACK: "fetch",
		REFRESHED_VIEW_CALLBACK: "refresh",
		SUCCESS: 1,

		// private variables
		_begin: null,
		_initBegin: null,
		_view: null,
		_step: null,	
		_cbFunctions: null,
		_alertThreshold: null,
		_pageSize: null,
		_pageIndex: null,
		_nbItems: -1,
		_overallTotal: 0,
		_childNodes: null,
		_isFetchCompleted: null,
		
		// node
		content: null,
		
		getCurrentViewRange: function(){
			var end = Math.min(this._begin + this._view - 1, this._childNodes.length - 1); //inclusive end			
			return {begin: this._begin, end: end};		
		},
		
		getTotal: function(){
			return this._childNodes.length;
		},
		
		setInitParams: function(/* int */ begin,/* int */ view,/* int? */ step){
			this._begin = begin >= 0 ? begin : 0;	
			this._initBegin = this._begin;		
			this._view = view >= 0 ? view : 0;
			this._step = step ? step : 1; // default
		},	
		getView: function(){
			return this._view;
		},		
		
		setAlertThreshold: function(alert, pageSize){
			this._alertThreshold = alert > 0 ? alert : 1;
			this._pageSize = pageSize;
		},
		
		setOverallTotal: function(overallTotal){
			this._overallTotal = overallTotal;
		},
		
		resetView: function(view, /* int? */ step){
			this._view = view;
			this._step = step ? step : 1; // default
			// for now, just reset begin
			this._begin = this._initBegin == null ? this_initBegin : 0;			
			
			this.refreshView();
		},
		
		constructor: function(){		
			this._cbFunctions = new dojox.collections.Dictionary();		
			this._childNodes = [];
			this._pageIndex = 1;
			this._isFetchCompleted = true;
		},
		
		_initChildNodes: function(){
			this._childNodes = [];
			
			for (var i=0; i<this.containerNode.childNodes.length; i++){
				var node = this.containerNode.childNodes[i];
				if (node.nodeType == 1)
					this._childNodes.push(node);
			}
						
			// update counter
			this._nbItems = this._childNodes.length;
		},
		
		_hideAll: function(){
			for (var i=0; i<this._childNodes.length; i++){				
				var node = this._childNodes[i];				
				node.style.display = "none";
			}			
		},
		
		_displayNodes: function(){			
			var begin = this._begin;
			var view = this._view;
			
			for (var i=begin; i<begin+view && i<this._childNodes.length; i++){
				this._childNodes[i].style.display = "block";
			}
		},
		
		appendContent: function(/* array of div entry nodes */ nodes){			
			if (nodes == null)
				return;
			
			for (var i=0; i<nodes.length; i++){
				var node = nodes[i];
				
				if (node.nodeType == 1){
					//this.containerNode.appendChild(node);
					this._childNodes.push(node);
				}
			}
						
			this._nbItems = this._childNodes.length;					
		},		
				
		onNext: function(){	
			this._fireFetchEventIfNeeded();
		},
		
		onNextComplete: function(){
			var newEnd = this._begin + this._view - 1;			
				
			if (newEnd < this._childNodes.length - 1)
			{			
				this._begin = this._begin + this._step;	
				this.refreshView();
				this.fireEvent(this.NEXT_CALLBACK);	
			}
			
			this.fetchCompleted();
		},
		
		onPrevious: function(){
			var newBegin = this._begin - this._step;
						
			if (newBegin >= 0)
			{
				this._begin = newBegin;
				this.refreshView();		
				this.fireEvent(this.PREVIOUS_CALLBACK);			
			}				
		},
		
		setCurrentPageBeingFetched: function(pageIndex){
			this._currentPageBeingFetched = pageIndex;
		},
		
		fetchCompleted: function(){
			this._isFetchCompleted = true;
		},
		
		_fireFetchEventIfNeeded: function(){			
			if (!this._alertThreshold)
				return;
				
			// last fetch has not completed yet
			// this is needed due to the potential asynchronous nature of the callback
			if (!this._isFetchCompleted)
				return;
			
			// are we near the end of the available nodes?
			
			// last displayed node
			var lastIndex = this._begin + this._view;
			var remainingNodes = this._nbItems - lastIndex;  

			// Only fetch content on demand. In essence, only retrieve items just
			// before the user wants them, e.g. when onClick is clicked.
			if((lastIndex + this._view) > this._nbItems && this._nbItems < this._overallTotal){
				this._isFetchCompleted = false;
				this.fireEvent(this.FETCH_NEEDED_CALLBACK, this._pageIndex);
			}else{
				this.onNextComplete();
			}
		},
		
		incPage: function(){
			this._pageIndex++;
		},
		
		refreshView: function(){		
			this._hideAll();
			this._displayNodes();
			this.fireEvent(this.REFRESHED_VIEW_CALLBACK);	
		},
		
		addCallback: function(/* string */ eventName, /* object */ scopeOrCb, /* string? */ fctName){
			if (!(eventName && dojo.isString(eventName)))
				return;
			
			var cbFct = null;
						
			if (fctName == null){
				// first param should be the callback fct
				if (!dojo.isFunction(scopeOrCb))
					return;
				
				cbFct = scopeOrCb;				
			}
			else{
				cbFct = dojo.hitch(scopeOrCb, fctName);
			}
			
			this._cbFunctions.add(eventName, cbFct);			
		},
		
		unregisterCallback: function(/* string */ eventName){
			this._cbFunctions.remove(eventName);
		},
		
		fireEvent: function(/* string */eventName, /* array? */ args){
			var cb = this._cbFunctions.item(eventName);
			
			if (cb)
				return cb(args);
		},
		
		setNextCallback: function(/* object */ scopeOrCb, /* string? */ fctName){
			this.addCallback(this.NEXT_CALLBACK, scopeOrCb, fctName);
		},		
		
		setPreviousCallback: function(/* object */ scopeOrCb, /* string? */ fctName){
			this.addCallback(this.PREVIOUS_CALLBACK, scopeOrCb, fctName);
		},		
		
		setFetchMoreCallback: function(/* object */ scopeOrCb, /* string? */ fctName){
			this.addCallback(this.FETCH_NEEDED_CALLBACK, scopeOrCb, fctName);			
		},
		
		setRefreshedViewCallback: function(/* object */ scopeOrCb, /* string? */ fctName){
			this.addCallback(this.REFRESHED_VIEW_CALLBACK, scopeOrCb, fctName);			
		},
		
		unregisterNextCallback: function(){
			this.unregisterCallback(this.NEXT_CALLBACK);
		},
		
		unregisterPreviousCallback: function(){
			this.unregisterCallback(this.NEXT_CALLBACK);
		},
		
		unregisterFetchMoreCallback: function(){
			this.unregisterCallback(this.FETCH_NEEDED_CALLBACK);
		},
		
		unregisterRefreshedViewCallback: function(){
			this.unregisterCallback(this.REFRESHED_VIEW_CALLBACK);
		}
	}
);
