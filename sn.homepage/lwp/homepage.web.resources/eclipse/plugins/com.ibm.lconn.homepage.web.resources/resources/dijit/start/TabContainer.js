/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.dijit.start.TabContainer");

dojo.require("dijit.layout.TabContainer");
dojo.require("dijit._Templated");

dojo.declare("lconn.homepage.dijit.start.TabContainer", dijit.layout.TabContainer, {

	// _controllerWidget: String
	//		An optional parameter to overrider the default TabContainer controller used.
	_controllerWidget: "lconn.homepage.dijit.start.TabController",

    doLayout: false,
    
	
	selectChild: function() {
		this.inherited(arguments);
		
		// remove any aria-pressed attribuet from the tab nodes
		// they are applied by dojo.layout.StackContainer but are invalid
		dojo.query(".dijitTabContent", this.domNode).forEach(
			function(node) { dojo.removeAttr(node, "aria-pressed"); }
		);
	}

});

//TODO: make private?
dojo.declare("lconn.homepage.dijit.start.TabController", dijit.layout.StackController, {
    // summary:
    // 	Set of tabs (the things with titles and a close button, that you click to show a tab panel).
    // description:
    //	Lets the user select the currently shown pane in a TabContainer or StackContainer.
    //	TabController also monitors the TabContainer, and whenever a pane is
    //	added or deleted updates itself accordingly.
    
    templateString: "<div wairole='tablist' dojoAttachEvent='onkeypress:onkeypress'></div>",
    
    // doLayout: Boolean
    doLayout: true,
    
    // buttonWidget: String
    //	The name of the tab widget to create to correspond to each page
    buttonWidget: "lconn.homepage.dijit.start.CheckableTabButton",
    
	onAddChild: function(/*Widget*/ page, /*Integer?*/ insertIndex){
			// summary:
			//   Called whenever a page is added to the container.
			//   Create button corresponding to the page.

			// add a node that will be promoted to the button widget
			var refNode = dojo.doc.createElement("span");
			this.domNode.appendChild(refNode);
			// create an instance of the button widget
			var cls = dojo.getObject(this.buttonWidget);
			var button = new cls({label: page.title, step: insertIndex + 1, closeButton: page.closable}, refNode);
			this.addChild(button, insertIndex);
			this.pane2button[page] = button;
			page.controlButton = button;	// this value might be overwritten if two tabs point to same container

			var handles = [];
			handles.push(dojo.connect(button, "onClick", dojo.hitch(this,"onButtonClick",page)));
			if(page.closable){
				handles.push(dojo.connect(button, "onClickCloseButton", dojo.hitch(this,"onCloseButtonClick",page)));
				// add context menu onto title button
				var _nlsResources = dojo.i18n.getLocalization("dijit", "common");
				var closeMenu = new dijit.Menu({targetNodeIds:[button.id], id:button.id+"_Menu"});
				var mItem = new dijit.MenuItem({label:_nlsResources.itemClose});
				handles.push(dojo.connect(mItem, "onClick", dojo.hitch(this, "onCloseButtonClick", page)));
				closeMenu.addChild(mItem);
				this.pane2menu[page] = closeMenu;
			}
			this.pane2handles[page] = handles;
			if(!this._currentChild){ // put the first child into the tab order
				button.focusNode.setAttribute("tabIndex", "0");
				this._currentChild = page;
			}
			//make sure all tabs have the same length
			if(!this.isLeftToRight() && dojo.isIE && this._rectifyRtlTabList){
				this._rectifyRtlTabList();
			}
		},

	
    _rectifyRtlTabList: function(){
        //summary: Rectify the width of all tabs in rtl, otherwise the tab widths are different in IE
        if (0 >= this.tabPosition.indexOf('-h')) {
            return;
        }
        if (!this.pane2button) {
            return;
        }
        
        var maxWidth = 0;
        for (var pane in this.pane2button) {
            var ow = this.pane2button[pane].innerDiv.scrollWidth;
            maxWidth = Math.max(maxWidth, ow);
        }
        //unify the length of all the tabs
        for (pane in this.pane2button) {
            this.pane2button[pane].innerDiv.style.width = maxWidth + 'px';
        }
    },
    
    onSelectChild: function() {
    	this.inherited(arguments);
    }
});

dojo.declare("lconn.homepage.dijit.start.CheckableTabButton", dijit.layout._TabButton, {

    baseClass: "dijitTab",
    
    templatePath: dojo.moduleUrl("lconn.homepage", "dijit/start/templates/_TabButton.html"),
    
    _scroll: false, // don't scroll the whole tab container into view when the button is focused
	
    postCreate: function(){
    
        this.closeNode.style.display = "none";
        
        this.inherited(arguments);
        dojo.setSelectable(this.containerNode, false);
    }
});



