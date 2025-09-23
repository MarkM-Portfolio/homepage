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

dojo.provide("lconn.homepage.core.dnd.avatar");

dojo.require("dojo.dnd.Avatar");
dojo.require("dojo.dnd.common");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

// avatar displayed when the user is re-arranging the layout of the dashboard
dojo.declare("lconn.homepage.core.dnd.avatar", dojo.dnd.Avatar,
	{
		// summary: avatar displayed when the user is re-arranging the layout of the dashboard / homepage
    	// description: our avatar is just a clone of the source node that should look exactly the same
		//      except for the opacity (50%)
		
		_bodyNode: null,
		_titleNode: null,
		_draggedWidgetData: null,
		_isPaletteItem: null,
		_resourceBundle: null,
		
		construct: function(){
			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
			
			if (this.manager.nodes.length){			
				this._isPaletteItem = dojo.hasClass(this.manager.nodes[0], "paletteItem");
				
				var source = this.manager.source;
				this._draggedWidgetData = source.getItem(this.manager.nodes[0].id).data;			
								
				this.node = dojo.doc.createElement("div");
				dojo.addClass(this.node, "idbc");
				
				this._titleNode = dojo.doc.createElement("div");
				dojo.addClass(this._titleNode, "idbt");
				if (this._isPaletteItem)
					this._titleNode.innerHTML = dojo.string.substitute(this._resourceBundle.TITLE_CREATE_WIDGET, [this._draggedWidgetData]); // "Creating " + this._draggedWidgetData + " widget";
				else
					this._titleNode.innerHTML = dojo.string.substitute(this._resourceBundle.TITLE_MOVE_WIDGET, [this._draggedWidgetData]); // "Moving " + this._draggedWidgetData + " widget";
					
				this._bodyNode = dojo.doc.createElement("div");
				dojo.addClass(this._bodyNode, "idbi");
				
				this.node.appendChild(this._titleNode);
				this.node.appendChild(this._bodyNode);
				
				this.node.style.position = "absolute";
				this.node.style.zIndex = 1999;
				
				this.update();
				
			}
		},
			
		update: function(){			
			var cannotDropIconUrl = dojo.moduleUrl("lconn.homepage.core.dnd", "cannotDrop.gif").toString();
			var canDropIconUrl = dojo.moduleUrl("lconn.homepage.core.dnd", "okDrop.gif").toString();		
				
			if (this._isPaletteItem){				
				if (this.manager.canDropFlag){
					var img = "<img src='" + canDropIconUrl + "'></img>";				
					this._bodyNode.innerHTML = img + " " + dojo.string.substitute(this._resourceBundle.ADD_WIDGET, [this._draggedWidgetData]); //" Release mouse to add " + this._draggedWidgetData + " widget to the page";
				}
				else{
					var img = "<img src='" + cannotDropIconUrl + "'></img>";
					this._bodyNode.innerHTML = img + " " + dojo.string.substitute(this._resourceBundle.CANNOT_ADD_WIDGET, [this._draggedWidgetData]);
				}				
			}
			else {
				if (this.manager.canDropFlag){
					var img = "<img src='" + canDropIconUrl + "'></img>";				
					this._bodyNode.innerHTML = img + " " + dojo.string.substitute(this._resourceBundle.MOVE_WIDGET, [this._draggedWidgetData]);
				}
				else{
					var img = "<img src='" + cannotDropIconUrl + "'></img>";
					this._bodyNode.innerHTML = img + " " + dojo.string.substitute(this._resourceBundle.CANNOT_DROP_WIDGET, [this._draggedWidgetData]);
				}
			}
		}
	}	
);
