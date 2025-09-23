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

dojo.provide("lconn.homepage.core.base._IWidgetSupport");

dojo.require("dojox.data.dom");
dojo.require("lconn.homepage.core.common.DashboardDropDownButton");
dojo.require("dijit.Menu");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare(
	// widget name and class
	"lconn.homepage.core.base._IWidgetSupport",
	
	// superclass
	null,
	
	// properties and methods
	{
		// summary: Base/utility class regrouping common methods to support iWidget specific operations
		// description: Base class that regroups all the functionalities common to Dojo widget used as 
		// 	content of an iWidget. 
		//	Note: This is an utility class, NOT an interface to make an iWidget. iWidget does not
		//	need any interface (see iWidget specification). 
		//  List of features:
		//		- Mode menu builder and selector
		//		- _iContext		
		
		_buildModeMenu: function(/* String? */altTitle){	
			// summary: Builds a menu popup at the location of _menuModeButtonNode DOM node with the labels stored in 
			//	the this._menu array
  			// description: 
			//	- This class is typically used with baseDashboardIWidget (getSupportedModes()) which retrieves the
			// 	list of available modes from the iWidget descriptor, i18n the label and set the this._menu member
			//	- The creation of the MenuItems is delegated to _buildMenuItem to easier the customization
			//	- A click on one of the menu item called _onModeSelected with the dijit.MenuItem object clicked
			if (this._menuModeButtonNode == null)
				return false;						
			
			this._menu = new dijit.Menu();
			
			this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "hpuistrings");
			
			var title = altTitle? altTitle: this._resourceBundle.IWIDGET_MENU_TITLE;
			//var iconUrl = dojo.moduleUrl("lconn.dboard","images/menu_arrow.gif");
			var iconUrl = djConfig.blankGif;

			var buttonMenu = new lconn.homepage.core.common.DashboardDropDownButton({dropDown: this._menu, title: title, buttonIconUrl: iconUrl, label: title, describedby: this._describedbyId}, this._menuModeButtonNode);
													
			if (this._modes == null)
				return false;
			
			if(!this._alwaysShowMenu) {
				if (this._modes.length <= 1) {
					return(false);
				}
			}
			
			for(var i in this._modes) {				
				this._menu.addChild(this._buildMenuItem(i, this._modes[i]));
			}
			
			dojo.connect(this._menu, "onItemClick", this, "_onModeSelected");
			return true;
			
		},
		
		_onModeSelected: function(/* dijit.MenuItem */obj){
			// summary: Callback called when the user select a item in the menu mode
  			// description: 
			//	- This class is typically used with baseDashboardIWidget (getSupportedModes()) which retrieves the
			// 	list of available modes from the iWidget descriptor, i18n the label and set the this._menu member
			//	- The creation of the MenuItems is delegated to _buildMenuItem to easier the customization
			//	- A click on one of the menu item called _onModeSelected with the dijit.MenuItem object clicked
			var payload = { "newMode" : obj.id };		
			
			// Firing an "internal" event has problems.  Leave this commented out code in place for CRE team to trace through the issue.
			//
			//
			//this._iContext.iEvents.fireEvent("onModeChangedHomepage", null, payload); /** Doesn't work **/
			//this._iContext.iEvents.fireEvent("onModeChangedHomepage", payload, null); /** Triggers onModeChangedHomepage callback but params are in reverse order			}
			
			// Instead, call the onModeChanged function directly.
			var iScope = this._iContext.iScope();
			var evt = new Object;
			evt.payload = obj.id;
			iScope.onModeChangedHomepage(evt);
			
		},
		
		_buildMenuItem: function(id, label){
			var item = new dijit.MenuItem({label: label});					
			// dynamically add an id to the object so that we can identify it when the user select it
			item.id = id;			
			return item;
		},	
		
		setAlwaysShowMenu: function(val) {
			this._alwaysShowMenu = val;
		},
		
		setMenuNode: function(/* DOM Node */ node){
			this._menuModeButtonNode = node;
		},
		
		setModes: function(/* String[] */ modes){
			this._modes = modes;
		},
		
		setIContext: function(/* com.ibm.mm.enabler.iw.iContext */ iContext){
			this._iContext = iContext;
		},
		
		setDescribedbyId: function(/* String*/ id){
			this._describedbyId = id;
		},
		
		// _menuModeButtonNode: DOM Node
		//		Place holder for the menu mode. Used by _buildModeMenu()	
		_menuModeButtonNode: null,		
				
		// private members
		
		// _modes: Array of string
		//		Labels used by the menu mode	
		_modes: null,
		
		// _menu: dijit.Menu
		//		Used for a closure temporarly. Should not be manipulated
		_menu: null,
		
		// _iContext: iContext reference as defined in the iWidget specs
		//		Should be set by the parent iWidget container
		_iContext: null,
		
		// Display menu regardless of supportedModes length
		_alwaysShowMenu: false,
		
		// _describedbyId: Used for a11y, bind the dorpdownbutton to the label
		_describedbyId: null
	}
);
