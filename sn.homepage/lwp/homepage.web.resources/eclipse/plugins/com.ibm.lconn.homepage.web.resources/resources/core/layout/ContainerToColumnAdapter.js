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

dojo.provide("lconn.homepage.core.layout.ContainerToColumnAdapter");


dojo.declare("lconn.homepage.core.layout.ContainerToColumnAdapter", null, {
	
	_containerToColumn: null,
	
	constructor: function(){
		this._containerToColumn = {};
	},

	addMapping: function(/* String */containerId, /* Number */ columnNumber){
		if ((dojo.isString(containerId)) && (typeof columnNumber == "number")){
			this._containerToColumn[containerId] = columnNumber;
		}		
	},
	
	getColumn: function(/* String */containerId){
		return this._containerToColumn[containerId];
	},
	
	getContainerId: function(/* Number */ columnNumber){
		var containerId = null;
		
		for(var key in this._containerToColumn){
			if (this._containerToColumn[key] == columnNumber){
				containerId = key;
				break;
			}				
		}		
		return containerId;
	}	
});
