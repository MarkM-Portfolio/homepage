/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.actionmenu.ActionMenuPopup");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.declare("lconn.homepage.as.actionmenu.ActionMenuPopup", 
	[dijit._Widget, dijit._Templated],
	{
		/** Instance variables */ 
		templatePath: dojo.moduleUrl("lconn.homepage", "as/actionmenu/templates/actionMenuPopup.html"),
		
		// Options that are available in the menu popup
		menuOptions: null,
		
		postCreate: function(){
			this.updateMenuOptions();
		},
		
		/** Dummy function, override to close the parent menu */
		closeMenu : function(){},
		
		onClick: function(){},
    
    	/** Called when a menu item has been clicked */
    	menuItemClicked : function(e){
    		as_console_debug("You clicked '" + e.target.innerHTML + "'");
    		
    		this.onClick(e);
    		
    		// Close the menu
    		this.closeMenu();
    	},
    	
    	/**
    	 * Update the action menu popup with options
    	 */
    	updateMenuOptions: function(){
    		this.removeMenuOptions();
    		
    		var fragment = document.createDocumentFragment();

			for(var i = 0; i < this.menuOptions.length; i++){
				fragment.appendChild(this.createMenuItemNode(this.menuOptions[i]));
			}
			
			this.domNode.appendChild(fragment);
    	},
    	
    	/**
    	 * Remove any current menu options on show
    	 */
    	removeMenuOptions: function(){
    		dojo.empty(this.domNode);
    	},
    	
    	/**
    	 * Create a single menu item node
    	 * @param menuItemText - The text that will be displayed in the menu item
    	 * @returns li - List node populated with menuItemText
    	 */
    	createMenuItemNode: function(menuItemText){
    		var li = dojo.create("li", {role: "menuitem"});
    		dojo.create("a", {href: "javascript:;", innerHTML: menuItemText}, li);
    		return li;
    	}
	}
);
