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

dojo.provide("lconn.homepage.core.widget.WidgetGadgetPanel");

dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");
dojo.require("dijit.Menu");

dojo.requireLocalization("lconn.homepage", "hpuistrings");

dojo.declare( // widget name and class
"lconn.homepage.core.widget.WidgetGadgetPanel", // superclass
null, // properties and methods
{

	_hpuistringsResourceBundle: dojo.i18n.getLocalization("lconn.homepage", "hpuistrings"),
	
   _buildMenuItem: function(label, action, iconClass) {
      var item = new dijit.MenuItem({
         label: label,
         iconClass: iconClass
      });
      item.action = action;
      
      // aria-describedby needed for Remove as the focus is returned to the top of the page
      if(label === this._hpuistringsResourceBundle.CLOSE) {
    	  item.domNode.setAttribute("aria-label", 
    			  item.domNode.getAttribute("aria-label") + this._hpuistringsResourceBundle.CLOSE_ARIADESCRIBEDBY);
      }
      return item;
   },

   _updateMenu: function() {
      this._mustUpdateMenu = true;
   },

   _contains: function(array, value) {
      return dojo.some(array, function(item) {
         return item == value;
      });
   },

   downKeyOpenMenu: function(evt) {

      if (evt.keyCode == "40") {
         this.openMenu(evt);
      } else {
         this.actionMenuOpener.focus();
      }
   }
});
