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

dojo.provide("lconn.homepage.as.ee.EEPopupWidget");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

dojo.require("dojo.dnd.Moveable");

/**
 * Popup widget that shows the embedded experience to the user
 */

dojo.declare(
"lconn.homepage.as.ee.EEPopupWidget", [dijit._Widget, dijit._Templated],
{
	gadgetUrl: "",
	
	context: "",
	
	top: 0,
	
	templatePath: dojo.moduleUrl("lconn.homepage", "as/ee/templates/eEPopupWidget.html"),
	
	postCreate: function(){
		new dojo.dnd.Moveable(this.domNode, {
			handle: this.header
		});
	},
	
	xClicked: function(){
		this.destroy();
	}
});
