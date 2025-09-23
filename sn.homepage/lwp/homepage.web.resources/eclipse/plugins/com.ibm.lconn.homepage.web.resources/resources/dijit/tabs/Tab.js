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

dojo.provide("lconn.homepage.dijit.tabs.Tab");

dojo.require("lconn.homepage.core.constants.DojoEvents");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("dijit._Container");

dojo.declare(
		"lconn.homepage.dijit.tabs.Tab",
		[dijit._Widget, dijit._Templated, dijit._Container],
		{
			templatePath: dojo.moduleUrl("lconn.homepage", "dijit/tabs/templates/Tab.html"),
			title: "",
			url: "",
			
			postCreate: function() {
			},
			
			postMixInProperties: function() {
				
			},
			
			setTitle: function(title) {
				this.title=title;
				this.buildRendering();
			},
			
			setUrl: function(url) {
				this.url=url;
				this.buildRendering();
			},
			
			onTabClicked: function(evt) {
				dojo.publish(lconn.homepage.events.tabs.TAB_CLICKED, [evt]);
				dojo.stopEvent(evt);
			}
		}
);
