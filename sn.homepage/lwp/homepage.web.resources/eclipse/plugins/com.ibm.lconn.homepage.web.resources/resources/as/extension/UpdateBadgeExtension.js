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

dojo.provide("lconn.homepage.as.extension.UpdateBadgeExtension");

dojo.require("com.ibm.social.as.constants.events");
dojo.require("com.ibm.social.as.extension.interfaces.IExtension");

dojo.declare("lconn.homepage.as.extension.UpdateBadgeExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{	
	// Event to indicate end of populating the news feed
	populateEventName: com.ibm.social.as.constants.events.POPULATE,
	
	// Event to update the badge count.
	updateBadgeEventName: com.ibm.social.as.constants.events.ACTIONREQUIREDBADGEUPDATE,

    view: "actionRequired",

	/**
	 * Called when the Action Required/All view loads on the page.
	 */
	onLoad: function(){
		// Subscribe the populateEvent
		this.populateEventHandle = dojo.subscribe(this.populateEventName, dojo.hitch(this, "updateBadgeNum"));
	},
	
	/**
	 * Called when the Action Required/All view is moved away from.
	 */
	onUnload: function(){
		dojo.unsubscribe(this.populateEventHandle);
	},
	/*
	 * Update the badge number
	 */
	updateBadgeNum: function(response, destroyNewsFeed, dynamicAdd, clickAction, scrollTop, url){
		// Fire the updateBadgeEventName to pass the model.totalResults
		dojo.publish(this.updateBadgeEventName,[response.totalResults]);
	}
});