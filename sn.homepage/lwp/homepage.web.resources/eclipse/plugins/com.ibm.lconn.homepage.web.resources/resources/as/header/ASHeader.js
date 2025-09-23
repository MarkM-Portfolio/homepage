/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.header.ASHeader");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");
dojo.require("com.ibm.social.as.constants.events");

dojo.requireLocalization("lconn.homepage", "activitystream");

dojo.declare("lconn.homepage.as.header.ASHeader", 
[dijit._Widget, dijit._Templated],
{
	// Event to be called for changing the AS view
	updateStateEvent: com.ibm.social.as.constants.events.UPDATESTATE,
	
	templatePath: dojo.moduleUrl("lconn.homepage", "as/header/templates/asHeader.html"),
	
	postMixInProperties: function(){
		// Setup the strings
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
	},
	
	postCreate: function(){
		// Subscribe to state updates. 
		this.subscribe(this.updateStateEvent, dojo.hitch(this, function(stateObj){
			if(stateObj.view){
				this.showHeader(stateObj.view);
			}
		}));
	},
	
	showHeader: function(newViewId){
		this.asTitle.innerHTML = this.strings[newViewId+"Text"];
		this.asDesc.innerHTML = "<p>"+this.strings[newViewId+"Desc"]+"</p>";
	},
	
	/**
	 * Simply refresh the activity stream UI.
	 */
	refreshStream: function(){
		dojo.publish(this.updateStateEvent, [{}]);
	}
});