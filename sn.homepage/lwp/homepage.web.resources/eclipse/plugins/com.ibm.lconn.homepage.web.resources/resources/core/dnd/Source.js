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

dojo.provide("lconn.homepage.core.dnd.Source");
dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.require("lconn.core.dnd.Source");

dojo.declare("lconn.homepage.core.dnd.Source", lconn.core.dnd.Source, {
	
	flagNotHide: false,
	isPalette: false,	
	
	checkAcceptance: function(source, nodes){
		// summary: overriden to add support for our palette
		if(this.isPalette) 
			return false;
		else
			return lconn.homepage.core.dnd.Source.superclass.checkAcceptance.apply(this, arguments);
	},
	
	onDndDrop: function(/* dojo.dnd.Source */ source,/* DOM Node array*/ nodes,/* Boolean */ copy){	
		// summary: Overriden method to add support for the palette
		// description: If the dragged item has the class "paletteItem":
		//				- the event "/lconn/dboard/dropPaletteItem" is published 
		//					with the params [dragged item node, dropIndicator node, destination container node]
		//					The dropIndicator node can be used to determine the location where to insert a new node for instance
		//				- the dnd operation is cancelled
		//				For all the other elements:
		//				- the event "/lconn/dboard/dnd/drop" is published 
		//					with the params [source container node, destination container node, dragged item node]
			
		if(this.containerState == "Over" && dojo.hasClass(nodes[0], "paletteItem")){
			// palette item dropped on this container
			dojo.publish(lconn.homepage.events.palette.DROP_ITEM, [nodes[0], this.dropIndicator, this.node]);
			this.deleteDropIndicator();			
			lconn.core.dnd.Source.superclass.onDndCancel.call(this);
		}		
		else{
			// palette item dropped on another target container ==> set a flag to avoid the item to be displayed in onDndCancel()			
			if (dojo.hasClass(nodes[0], "paletteItem"))
				this.flagNotHide = true;			
			
			// some processing needed before calling the parent method
			var oldCurrent = this.current;
			this.current = this.dropIndicator;
			lconn.homepage.core.dnd.Source.superclass.onDndDrop.apply(this, arguments);	
			
			this.flagNotHide = false;
			this.current = oldCurrent;
			
			// drop any other element (not a palette item)
			if (this.containerState == "Over")
				dojo.publish(lconn.homepage.events.dnd.DROP, [source.node, this.node, nodes[0]]);
		}
		
		// reenable selection as the dnd operation is done
		dojo.body().onselectstart = null;
    	dojo.body().unselectable = "off";
		
		// SPR #DMCE79MCDA reset can drop flag			
		dojo.dnd.manager().canDropFlag = false;
	},
	
	/* OVERRIDES THE METHOD IN lconn.core.dnd.Source THATS PART OF DOJO IN SN-INFRA
	 * WE DON't WANT TO IMPACT OVER FEATURES THAT MAY USE THIS
	 */
	createDropIndicator: function(){	
		// summary: Creates the dropIndicator DOM node (div) and set this.dropIndicator with a reference to this node
		// description: Creates an empty DOM div node, sets the borders and height/width
		//		according to the size of the dragged item and stores the reference of the node in the dropIndicator proprety. 
		// returns: nothing (this.dropIndicator is set)
				
		var width = 19;
		this.dropIndicator = document.createElement("div");
		// TODO : use css instead
		this.dropIndicator.style.borderWidth = "2px";
		this.dropIndicator.style.marginLeft = "4px";
		this.dropIndicator.style.marginBottom="10px";
		this.dropIndicator.style.borderColor = "gray";			
		this.dropIndicator.style.borderStyle = "dashed";
		this.dropIndicator.style.cursor = "default";		
							
		// height of the source		
		// dojo.dnd.manager().avatar.node is the dragged widget (our avatar has the same size as the source widget)					
		this.dropIndicator.style.height = dojo.marginBox(dojo.dnd.manager().avatar.node).h + "px";				
		// width of the destination table cell				
		hp_console_debug("width: " + dojo.marginBox(this.node).w);
		this.dropIndicator.style.width = dojo.marginBox(this.node).w - width + "px";
	},
	
	/*
	 * Override to allow selecting text inside the widget.
	 * 
	 * @Override {dojo.dnd.Container.onSelectStart}
	 */
	onSelectStart: function(e){
		// summary:
		//		event processor for onselectevent and ondragevent
		// e: Event
		//		mouse event
		
		return true;	
	}
});
