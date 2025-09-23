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

dojo.provide("lconn.homepage.core.dnd.HomepageMovable");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("dojo.dnd.move");

dojo.declare("lconn.homepage.core.dnd.HomepageMovable", dojo.dnd.move.parentConstrainedMoveable, {

    // div node showing the target position
    dropIndicator: null,
    
    _siblingsNode: null,
    
    _cachedContainerHeight: null,
    
    onMoveStop: function(/* dojo.dnd.Mover */mover){
        this.inherited("onMoveStop", arguments);
        hp_console_debug("onMoveStop");
        
        var node = mover.host.node;
        
        this.dropIndicator.parentNode.replaceChild(mover.host.node, this.dropIndicator);
		
        hp_console_debug("onMoveStop2");
        
        if (this.dropIndicator && this.dropIndicator.parentNode) 
            this.dropIndicator.parentNode.removeChild(this.dropIndicator);
			
        hp_console_debug("onMoveStop3");
        hp_console_debug(mover.host.node);
        hp_console_debug(mover.host.node.style.position);
		
		mover.host.node.style.position = "static";
		mover.host.node.style.left = "";
		mover.host.node.style.top = "";
		
		
		hp_console_debug(mover.host.node.style.position);
		
		hp_console_debug("onMoveStop4");
        
        //var cachedHeight = this._cachedContainerHeight;
        //dojo.marginBox(node.parentNode, {h: cachedHeight});
        mover.host.node.parentNode.style.height = "auto";
		
        hp_console_debug("onMoveStop5");
        
        dojo.publish(lconn.homepage.events.dnd.DROP, [null, null, node]);
    },
    
    onMoveStart: function(/* dojo.dnd.Mover */mover){
        var node = mover.host.node;
        
        //var containerHeight = dojo.marginBox(node.parentNode).h;
       // node.parentNode.style.height = containerHeight + dojo.marginBox(mover.host.node).h + "px";
        //this._cachedContainerHeight = containerHeight;
        
        this.inherited("onMoveStart", arguments);
    },
    
    _firstMove: null,
    
    onMove: function(/* dojo.dnd.Mover */mover, /* Object */ leftTop){
        this.inherited("onMove", arguments);
        
		// workaround: found that e.pageY is wrong in FF for the very first call to onMove
		// did not find anything wrong in Dojo code so I suppose the pb comes from FF itself...but left for investigations
		// for now: ignore the first call to onMove as the leftTop position is wrong
        if (!this._firstMove) {            
            var fakeEvent = {};
            fakeEvent.pageX = leftTop.l;
            fakeEvent.pageY = leftTop.t;
            
            var pos = this.getInsertPosition(fakeEvent);
            this.placeIndicator(pos[0], pos[1]);
        }
        else {
        	hp_console_debug("first move ignored");
            this._firstMove = false;
        }
    },
    
    onFirstMove: function(/* dojo.dnd.Mover */mover){
        this.inherited("onFirstMove", arguments);
        
        this._firstMove = true;
		
		dojo.marginBox(mover.host.node, {w: 190});		
		       
        this._cacheSiblings(mover.host.node);
        this._createIndicator(mover.host.node);
    },
    
    _cacheSiblings: function(widgetNode){
        var nodes = dojo.query(".lotusSection", widgetNode.parentNode);
        nodes = dojo.filter(nodes, function(node){
            return node != widgetNode
        });
        this._siblingsNode = nodes;
    },
    
    placeIndicator: function(/* DOM Node */node,/* Boolean */ before){
        // summary: Inserts drop indicator at the specified location
        // description: Insert the dropIndicator before (true) or after (false) the specified DOM node.
        //				The parameters usually come from getInsertPosition.
        //				If node is null, the drop indicator is placed as the first element of this container
        
        if (this.dropIndicator && this.dropIndicator.parentNode) 
            this.dropIndicator.parentNode.removeChild(this.dropIndicator);
        
        if (node == null) {
            node.parentNode.appendChild(this.dropIndicator);
        }
        else {
            // insert before node
            if (before) {
                node.parentNode.insertBefore(this.dropIndicator, node);
            }
            else {
                // insert after node
                if (node.parentNode.lastChild == node) 
                    node.parentNode.appendChild(this.dropIndicator);
                else 
                    node.parentNode.insertBefore(this.dropIndicator, node.nextSibling);
            }
        }
    },
    
    _createIndicator: function(node){
        //if (this.dropIndicator == null){
        
        var width = 19;
        this.dropIndicator = document.createElement("div");
        // TODO : use css instead
        this.dropIndicator.style.borderWidth = "2px";
        this.dropIndicator.style.marginLeft = "4px";
        this.dropIndicator.style.borderColor = "orange";
        this.dropIndicator.style.borderStyle = "dashed";
        this.dropIndicator.style.cursor = "default";
        
        // height of the source		
        // dojo.dnd.manager().avatar.node is the dragged widget (our avatar has the same size as the source widget)					
        this.dropIndicator.style.height = dojo.marginBox(node).h + "px";
        // width of the container
        this.dropIndicator.style.width = (dojo.marginBox(node.parentNode).w - width) + "px";
        //}
    },
    
    getInsertPosition: function(e){
        // summary: Calculate the location where to insert the drop indicator depending on the current mouse cursor position
        // description: The position is calculated as follows:
        //    				- if the cursor is over a node in a dnd source container, 
        //						a node reference is returned as the first item of the result array
        //    				- if there is no node in a dnd source container under the mouse cursor,
        //						 the first item of the result array is null
        //    				- some additional handling had to be done to ignore the drop indicator 
        //						 (it is a node in a dnd source container by definition!)
        //    				- the "before" boolean is determined by computing the position of the mouse cursor 
        // 						  relative to the gravity center of the underneath DOM node.
        // returns: Array of 2 elements [DOM node, boolean before]
        // 		 For instance [nodeX, true] means that the drop indicator node should be inserted 
        //		 before "nodeX" in the parent container. 	
        //		 Returns [null, false] if no location was found	==> error
        
        
        // init
        var pos = -1;
        var before = false;
        
        // this._children contains all the node in the source
        // this._children is initilized by onDndStart()
        // however, during the drag operation, the dragged source widget is hidden (optimizations)
        // so we have to search for only the displayed nodes/widgets to calculate the location
        //var displayedChildren = [];				
        //for(var i=0; i<this._children.length; i++){			
        //	if (this._children[i].style.display != "none")
        //		displayedChildren.push(this._children[i]);
        //}
        
        // start searching for the right location	
        
        
        var lastIndex = this._siblingsNode.length - 1;
        if (this._siblingsNode.length > 0) {
            // north to the first node?				
            if (this.isCursorNorthNode(this._siblingsNode[0], e)) {
                pos = 0;
                before = true;
            }
            // south to the last node?
            else 
                if (!this.isCursorNorthNode(this._siblingsNode[lastIndex], e)) {
                    pos = lastIndex;
                    before = false;
                }
                // otherwise search for the right location
                else {
                    for (var i = 0; i < lastIndex; i++) {
                        // between two nodes
                        if (!this.isCursorNorthNode(this._siblingsNode[i], e) &&
                        this.isCursorNorthNode(this._siblingsNode[i + 1], e)) {
                            pos = i;
                            before = false;
                            break;
                        }
                    }
                }
            return [this._siblingsNode[pos], before];
        }
        else {
            return [null, false];
        }
    },
    
    onMouseDown: function(evt){
        if (!dojo.hasClass(evt.target, "section-titleBar")) 
            return;
        
        this.inherited("onMouseDown", arguments);
    },
    
    
    isCursorNorthNode: function(/* DOM Node */node,/* Event */ evt){
        // summary: Calculate the location of the mouse cursor relative to the gravity center of node
        // returns: boolean. True if mouse cursor is north from gravity center of the passed node	
        var coords = dojo.coords(node, true);
        var absY = node.offsetHeight;
        
        return ((evt.pageY - coords.y) < (absY / 2));
    }
});
