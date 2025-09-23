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

dojo.provide("lconn.homepage.dijit.popup.PopupWidgetFactory");

dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.dijit.popup.PopupWidget");

// base class that contains some common functionalities of the dashboard widgets
dojo.declare(
	// widget name and class
	"lconn.homepage.dijit.popup.PopupWidgetFactory",
	
	// superclass
	null,
	
	// properties and methods
	{
		_this: null,
		_dlg: null,
		_subscriptions: [],
			
		getInstance: function(){
			this._this = new lconn.homepage.dijit.popup.PopupWidgetFactory();
			this._subscriptions.push(dojo.subscribe(lconn.homepage.events.popup.DESTROY, dojo.hitch(this, "_destroyPopup")));
			return this._this;
		},
		
		showPopup: function(title) {	
			this._createDlg();
			this._dlg.showPopup(title);
		},
		setContent: function(content) {
			return this._dlg.setContent(content);
		},
		
		_createDlg: function() {
			hp_console_debug("_createDlg");
			this._dlg = new lconn.homepage.dijit.popup.PopupWidget(); 
		},
		
		position: function() {
			this._dlg.layout();		
		},
		
		_destroyPopup: function(dlg) {
			hp_console_debug("_destroyPopup [" + dlg + "]");
			dlg.hidePopup();
			dlg = null;
		}	
	}
);
