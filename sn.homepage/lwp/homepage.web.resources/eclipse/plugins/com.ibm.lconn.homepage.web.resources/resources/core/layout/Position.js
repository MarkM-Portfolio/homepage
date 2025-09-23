/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.layout.Position");

dojo.require("lconn.homepage.core.layout.ContainerToColumnAdapter");


dojo.declare("lconn.homepage.core.layout.Position", null, {
	// summary: position in the column-layout
	// description: (x,y) couple with x being the column number and y the row number
	//	 we start to count from the top left (0,0)
	
	// _containerToColAdapter: lconn.homepage.core.layout.ContainerToColumnAdapter
	_containerToColAdapter: null,
	
	// x: Number
	// 		Row number in column/container. From top to bottom. Index is 0 based
	x: null,
	
	// y: Number
	//		Column number on the page. 
	//		Start at 0. Set a ContainerToColumn adapter to do the translation between containerId and column if needed
	y: null,
		
	constructor: function(){
		this.x = -1;
		this.y = -1;
	},
	
	constructor: function(column,row){
		this.x = column;
		this.y = row;
	},
	
	clone: function(){
		// summary: create new position object with the (x,y) values from this.		
		var clone = new lconn.homepage.core.layout.Position(this.x, this.y);
		
		if (this._containerToColAdapter != null){
			// just pass the reference here. No need to clone adapter object
			clone.setContainerToColumnAdapter(this._containerToColAdapter);
		}
		
		return clone;		
	},
	
	getColumn: function(){
		
		var column = null;
		
		if (this.x >= 0)
			column = this.x;
		
		return column;
	},
	
	getRow: function(){
		var row = null;
		
		if (this.y >= 0)
			row = this.y;
		
		return row;
	},
	
	getContainerId: function(){
		var containerId = null;
		
		if (this._containerToColAdapter != null){
			containerId = this._containerToColAdapter.getContainerId(this.x);
		}
		
		return containerId;
	},
	
	setColumn: function(x){
		this.x = x;
	},
	
	setRow: function(y){
		this.y = y;
	},
	
	incColumn: function(){
		if (this.x >= 0)
			this.x++;
		
		return this.getColumn();
	},
	
	decColumn: function(){
		if (this.x >= 0)
			this.x--;
		
		return this.getColumn();
	},
	
	incRow: function(){
		if (this.y >= 0)
			this.y++;
		
		return this.getRow();
	},
	
	decRow: function(){
		if (this.y >= 0)
			this.y--;
		
		return this.getRow();
	},
	
	setContainerId: function(/* String */ containerId){
		var col = -1;
		
		if (this._containerToColAdapter != null){
			col = this._containerToColAdapter.getColumn(containerId);
		} 
		
		this.setColumn(col);		
	},
	
	setContainerToColumnAdapter: function(/* lconn.homepage.core.layout.ContainerToColumnAdapter */ containerToColAdapter){
		if (containerToColAdapter instanceof lconn.homepage.core.layout.ContainerToColumnAdapter){
			this._containerToColAdapter = containerToColAdapter;		
		}
	},
	
	getContainerToColumnAdapter: function(){
		return this._containerToColAdapter;
	}
	
});
