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

dojo.provide("lconn.homepage.as.actionmenu.ActionMenuController");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.require("lconn.homepage.as.actionmenu.ActionMenuPopup");

// TODO: Rename to FilterMenuController
dojo.declare("lconn.homepage.as.actionmenu.ActionMenuController", 
	[dijit._Widget, dijit._Templated],
	{
		/** Instance variables */ 
		templatePath: dojo.moduleUrl("lconn.homepage", "as/actionmenu/templates/actionMenuController.html"),

		// A reference to the menu popup widget	
		actionMenuPopup: null,
		
		// Options that are available in the menu popup
		menuOptions: null,
		
		/** Override to handle menu item clicks */
		onClick: function(){},
		
		postCreate : function(){
	    	// Connect an onclick event to the controller's button
	    	dojo.connect(this.button, "onclick", dojo.hitch(this, "openPopup"));
    	},
    
		openPopup : function(){
	    	var popup = this.getPopupWidget();
    	
    		var closeAndRestore = dojo.hitch(this, "closeAndRestoreFocus");
		
	    	dijit.popup.open({
    			parent: this,
    			popup: popup,
    			around: this.button,
	    		onCancel: closeAndRestore,
    			onClose: closeAndRestore,
    			onExecute: closeAndRestore
	    	});
    	
	    	this._onBlur = function(){
				this.inherited('_onBlur', arguments);
				dijit.popup.close(popup);
			};
	    },
    
		closeAndRestoreFocus : function(){
			dijit.focus(dijit.getFocus(this));
			dijit.popup.close(this.actionMenuPopup);
		},
		
		/** Create a popup widget if necessary */
		getPopupWidget : function(){
			// If the action menu popup isn't created
    		if(this.actionMenuPopup == null){
    			// Create one, passing in a close menu callback function 
    			this.actionMenuPopup = new lconn.homepage.as.actionmenu.ActionMenuPopup({
    				closeMenu: dojo.hitch(this, "closeAndRestoreFocus"),
    				menuOptions: this.menuOptions,
    				onClick: this.onClick
    			});
    		}
    	
	    	return this.actionMenuPopup;
    	},
    	
    	/**
    	 * Update the action menu popup with options
    	 */
    	updateMenuOptions: function(options){
    		as_console_debug("ActionMenuController updateMenuOptions");
    		this.menuOptions = options;
    		
    		if(this.actionMenuPopup != null){
    			this.actionMenuPopup.menuOptions = options;
    			this.actionMenuPopup.updateMenuOptions();
    		}
    	}
	}
);
