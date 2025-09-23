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

dojo.provide("lconn.homepage.core.layout.WidgetLayoutModel");

dojo.require("lconn.homepage.core.layout.Position");
dojo.require("lconn.homepage.core.layout.ContainerToColumnAdapter");
dojo.require("lconn.homepage.core.factory.WidgetFactory");

dojo.declare("lconn.homepage.core.layout.WidgetLayoutModel", null, {	
	// summary: Internal representation of the layout of the page
	//		This object must be updated (see WidgetModelUpdater) when the user rearrange the layout of the page
	//		The persistence is based on the layout stored in this model
	
	// _pageId: String
	//		Id of the page/tab
	_pageId: null,
	
	// _pageType: String
	//		Type of the page. Either WIDGET_TAB or UPDATE_TAB
	_pageType: null,
	
	WIDGET_TAB: "widgetTab",
	UPDATE_TAB: "updateTab",
	
	// numberOfColumn: int
	//		Number of column for the current page
	_numberOfColumns: null,
	
	// _layout: Array[colNum][rowNum]
	//		Internal representation of the layout. 
	_layout:null,
	
	// _allWidget: flat array of all widgets
	_allWidget: null,
	
	// _containerToColAdapter: lconn.homepage.core.layout.ContainerToColumnAdapter
	_containerToColAdapter: null,
	
	// _canCustomize: boolean
	//  determines if user can add/move/remove widgets
	_canCustomize: true,
	
	constructor: function(){
		this._layout = [];
		this._allWidget = [];
		
		// default value
		this._numberOfColumns = 2;
	},	
	
	appendWidgetToColumn: function(widget, /* colNumber. 0 based */ colNumber){
		// summary: append the widget to the last position of the column
		if (widget == null /*|| !dojo.isInteger(colNumber)*/)
			return;		
				
		this._createColumnsIfNeeded(colNumber);
		this._layout[colNumber].push(widget);
				
		this._allWidget[widget.id] = widget;
	},
	
	appendWidgetToContainer: function(widget, /* String */ containerId){
		var colNum = null;
		
		if (this._containerToColAdapter != null){
			colNum = this._containerToColAdapter.getColumn(containerId);
		}
		
		if (colNum != null){
			this.appendWidgetToColumn(widget, colNum);
		}
	},
	
	_createColumnsIfNeeded: function(colNumber){		
		var i=0;
		
		while(i<=colNumber){
			if (this._layout[i] == null){
				this._layout[i] = [];
			}
				
			i++;
		}
	},
	
	getWidgetsInCol: function(colNumber){
		this._createColumnsIfNeeded(colNumber);
		return this._layout[colNumber];
	},
	
	setPageType: function(pageType){
		if ((pageType == this.WIDGET_TAB) || (pageType == this.UPDATE_TAB)){
			this._pageType = pageType;
		} 
		else {
			throw new Error("Unknown pageType " + pageType);
		}
	},
	
	getPageType: function(){
		return this._pageType;
	},
	
	getWidget: function(pos){
		return this._layout[pos.x][pos.y];
	},
	
	removeWidget: function(widget){
		if (widget != null){			
			var pos = this.getWidgetPosition(widget);	
			
			if (pos != null){
				// move up all the widgets in the column			
				currentPos = pos.clone();
				
				var nextPos = currentPos.clone();
				nextPos.y++;				
						
				while(this.isWidgetAtPos(nextPos)){					
					// move down
					this._layout[currentPos.x][currentPos.y] = this._layout[currentPos.x][currentPos.y + 1];
					currentPos.y++;	
					nextPos.y++;		
				}	
				
				// delete item
				this._layout[currentPos.x].splice(currentPos.y, 1);				
			}
			
			
			this._allWidget[widget.id] = null;
			delete this._allWidget[widget.id];
		}
	},
	
	getAllWidgets: function(){
		return this._allWidget;
	},
	
	addWidget: function(widget, /* lconn.homepage.core.layout.Position */ pos){
		if (widget == null || pos == null)
			return;		
		
		// can only add a widget after another widget (next row), except for first widget in a column
		//var nextRow = pos.clone();
		//nextRow.y++;
		if (!this.isWidgetAtPos(pos)){
			// just append the widget in the column
			this.appendWidgetToColumn(widget, pos.x);
		}
		else{
			var lastPos = pos.clone();
			
			// point to the last widget of the column
			while(this.isWidgetAtPos(lastPos))
				lastPos.y++;
			
			var currentPos = lastPos.clone();
					
			if (this._layout[currentPos.x] != null){						
				while(currentPos.y > pos.y){
					// move down				
					this._layout[currentPos.x][currentPos.y] = this._layout[currentPos.x][currentPos.y - 1];							
					currentPos.y--;		
				}			
			}
			
			this._createColumnsIfNeeded(pos.x);			
			this._layout[pos.x][pos.y] = widget;
			this._allWidget[widget.id] = widget;
		}
	},
	
	moveWidget: function(/*lconn.homepage.core.layout.Position */posBegin, /*lconn.homepage.core.layout.Position */posEnd){		
		var widget = this.getWidget(posBegin);
		if (widget != null){
			this.removeWidget(widget);	
			this.addWidget(widget, posEnd);
		}		
	},
	
	removeAllWidgets: function(){
		this._layout = [];
		this._allWidget = [];
	},
	
	getWidgetPosition: function(widget){
		// summary: returns current widget position
		// returns: lconn.homepage.layout.Position
		
		for(var i=0; i<this._layout.length; i++){
			for (var j=0; j<this._layout[i].length; j++){
				var widgetAtPos = this._layout[i][j];
				if (widgetAtPos == widget)
					return new lconn.homepage.core.layout.Position(i,j);
			}
		}
		
		// not found
		return null;
	},
	
	isWidgetAtPos: function(/*lconn.homepage.core.layout.Position */pos){		
		return ((pos != null) && (this._layout[pos.x] != null) && (this._layout[pos.x][pos.y] != null) && (this._layout[pos.x][pos.y] != "undefined"));
	},
	
		
	setContainerToColumnAdapter: function(/* lconn.homepage.core.layout.ContainerToColumnAdapter */ containerToColAdapter){
		if (containerToColAdapter instanceof lconn.homepage.core.layout.ContainerToColumnAdapter){
			this._containerToColAdapter = containerToColAdapter;		
		}
	},
	
	setPageId: function(/* String */ id){
		this._pageId = id;
	},
	
	getPageId: function(){
		return this._pageId;
	},
	
	setNumberOfColumns: function(numberOfColumns){
		this._numberOfColumns = numberOfColumns;
	},
	
	getNumberOfColumns: function(){
		return this._numberOfColumns;
	},
	
	setCanCustomize: function(canCustomize) {
		// defaults to true, if otherwise, then change
		if ( typeof canCustomize === 'string' && canCustomize.toLowerCase() === 'false' ) {
			this._canCustomize = false;
		}
	},
	
	canCustomize: function() {
		return this._canCustomize;
	},
	
	generateLayoutObject: function(){
		// summary: returns an object representing the layout of the page, 
		//		Can be used by dojo.objectToQuery to be send to the server
		
		if (this._containerToColAdapter == null)
			return null;
		
		var adapter = this._containerToColAdapter;
		
		var obj = {};
		
		obj.pageId = this.getPageId();
		obj.column = this.getNumberOfColumns();
		obj.layout = [];
		
		dojo.forEach(this._layout, function(widgetsCol, index){			
			var containerObj = {};
			containerObj.containerId = adapter.getContainerId(index);
			containerObj.widgets = [];
			
			dojo.forEach(widgetsCol, function(widget){
				var widgetId = factory.getWidgetId(widget);
				
				var widgetInfo = {
						widgetId: widgetId,
						widgetInstanceId: widget
				};
				containerObj.widgets.push(widgetInfo);
			});
			
			obj.layout.push(containerObj);			
		});
		
		return obj;
	}
});
