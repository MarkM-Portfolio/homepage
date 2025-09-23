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

dojo.provide("lconn.homepage.core.base.baseMyPageSupport");

dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.declare(
		// widget name and class
		"lconn.homepage.core.base.baseMyPageSupport",
		
		// superclass
		null,
		
		{		
		
		resizeColumns: function () {
				var elements = dojo.query("td.lotus2Col");		

				dojo.forEach(elements, function(element) {
					if(dojo.byId("dnd3")) {
						dojo.addClass(element, "lotus3Col");
					} else {	
						dojo.removeClass(element, "lotus3Col");
					}

				});

				elements = dojo.query("div.lotusWidget");		

				dojo.forEach(elements, function(element) {
					if(dojo.byId("dnd3")) {
						dojo.addClass(element, "lotusWidget3Col");
					} else {		
						dojo.removeClass(element, "lotusWidget3Col");

					}
					
				});		
				
				if(dojo.isIE) {
					elements = dojo.query("a.lotusWidgetLink");
					dojo.forEach(elements, function(element) {
						if(dojo.byId("dnd3")) {
							dojo.addClass(element, "lotusWidgetLink3Col");
						} else {
							dojo.removeClass(element, "lotusWidgetLink3Col");
			
						}
						
					});
				}
				
				var lotusFrameElement = dojo.byId("lotusFrame");
				if(dojo.byId("dnd3")) {
					hp_console_debug("resizeColumns() --> adding lotusFrame3Col");
					dojo.addClass(lotusFrameElement, "lotusFrame3Col");
				} else {
					hp_console_debug("resizeColumns() --> removing lotusFrame3Col");
					dojo.removeClass(lotusFrameElement, "lotusFrame3Col");
				}

			},
			
			addThirdColumn: function(evt, shouldNotPersist) {
				
				if(dojo.byId("dnd3") == null) {

					var newtd3 = document.createElement("td");
					
					newtd3.setAttribute("id", "dnd3");
					dojo.addClass(newtd3, "lotus3Col");
					newtd3.innerHTML = "<span style='position:absolute;'><a name='thirdColumn' id='thirdColumn'>&nbsp;</a></span>";
					
					var tr = dojo.byId("dndtable").getElementsByTagName('tr')[0];
					tr.appendChild(newtd3);
			
					var dnd3 = new lconn.homepage.core.dnd.Source(dojo.byId("dnd3"));
					dndSources.push(dnd3);
					
					if (!shouldNotPersist){
					// Vincent: I've added onLoad arg as this method is used for two different purposes: 
					//			- adding 3rd column on page load 
					//			- adding 3rd column after selection in menu
					//	There is no need to persist the layout after pageload, hense the shouldNotPersist param.
					
						layoutModel.setNumberOfColumns(3);			
						//publish this to update the move menu's on the widgets
						dojo.publish(lconn.homepage.events.layout.UPDATED);	
					}		
					this.resizeColumns();
					return dnd3;
				}
			
			},
			
			removeThirdColumn: function() {
				var dnd3node = dojo.byId("dnd3");
				if(dnd3node != null) {
					if(dndSources[2]) {
						var dndSrc = dndSources.pop();
						var ids = this.getWidgetIdsInDndSource(dndSrc);
						hp_console_debug("moving [" + ids.length + "] widgets");
						var widget = null;
						for(var j=0;j<ids.length;j++){				
							hp_console_debug("moving id [" + ids[j] + "]");
							var widget = dijit.byId(ids[j]);
							hp_console_debug("widget [" + widget + "]");
							dndSrc.delItem(widget.root.id);
							if(widget!=null) {
								dndSources[0].setItem(widget.root.id, {
												data: widget.root.title,
												type: ["text"]
												});					
								dojo.place(widget.root, dojo.byId("dnd"), "first");
							}
						}
						if(widget != null) {
							this.persistAllModelUpdates(widget, true);
						}
						this.removeColumnNode(dnd3node);
					} else {
						this.removeColumnNode(dnd3node);
					}
					this.resizeColumns();
					
					// Vincent: added
					layoutModel.setNumberOfColumns(2);			
					//publish this to update the backend
					dojo.publish(lconn.homepage.events.layout.UPDATED);		
				}
			},
			

			getWidgetIdsInDndSource: function(dndSrc) {
				var ids = [];
				var dndNodes = dndSrc.node.childNodes;
				for(var j=0;j<dndNodes.length;j++){
					if(dndNodes[j].nodeName=="DIV"){
						if(dndNodes[j].getAttribute("widgetid"))
							ids.push(dndNodes[j].getAttribute("widgetid"));
					}
				}
				return ids;
			},
			
			removeColumnNode: function(node) {
				hp_console_debug("removeColumnNode [" + node.id + "]");
				var tr = dojo.byId("dndtable").getElementsByTagName('tr')[0];
				tr.removeChild(node);
			},
			
			persistAllModelUpdates: function(widget, dontPersist) {
				hp_console_debug("publish " + lconn.homepage.events.dnd.DROP + " [" + widget.root.id + "]");
				dojo.publish(lconn.homepage.events.dnd.DROP, [null, null, widget.root, dontPersist]);		
			}
		}
);
