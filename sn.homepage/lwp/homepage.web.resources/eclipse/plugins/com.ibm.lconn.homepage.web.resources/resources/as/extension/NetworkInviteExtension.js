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

dojo.provide("lconn.homepage.as.extension.NetworkInviteExtension");

dojo.require("com.ibm.social.as.constants.events");
dojo.require("com.ibm.social.as.controller.DynamicLoadController");
dojo.require("com.ibm.social.as.extension.interfaces.IExtension");

/**
 * Special extension for the Action Required view. Essentially we want
 * to make sure that network invites accepted from the EE are properly
 * removed from this view. The DynamicLoadController, in usual 
 * circumstances, will accept an id and query the server for this id
 * looking for results. However, for network invites, we want to see
 * if a network invite has been deleted from the server, so an empty
 * response is a good thing. Therefore, in this extension, if a
 * response comes back empty for a particular id, and that id exists in
 * the stream, we delete that item.
 * 
 * @author Robert Campion
 */

dojo.declare("lconn.homepage.as.extension.NetworkInviteExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	dynamicLoadController: null,
	
	dynamicLoadControllerProto: null,
	
	// Original functions that we store and override
	onFeedLoadFunction: null,
	
	constructor: function(){
		this.dynamicLoadControllerClass = com.ibm.social.as.controller.DynamicLoadController;
		this.dynamicLoadControllerProto = this.dynamicLoadControllerClass.prototype;
	},
	
	/**
	 * Called when the extension loads on the page.
	 */
	onLoad: function(){
		this.onFeedLoadFunction = this.dynamicLoadControllerProto.onFeedLoad;
		
		dojo.extend(this.dynamicLoadControllerClass, {
			onFeedLoad: this.onFeedLoadOverride
		});
	},
	
	/**
	 * Called when the extension is unloaded from the page.
	 */
	onUnload: function(){
		// Restore the old functions
		dojo.extend(this.dynamicLoadControllerClass, {
			onFeedLoad: this.onFeedLoadFunction
		});
	},
	
	onFeedLoadOverride: function(destroyNewsFeed, insertAtTop, url, clickEvent, scrollTop, response, request){
		// Reset the load request flag (that's been set in the 'onLoadRequest')
		this.resetLoadRequest();
		
		if(insertAtTop){
			// SHINDIG-1758
			if (response.list) {
				response.entry = response.list;
			}
			// May be a network invite acceptance event. If so, and nothing comes back in the feed, 
			// we know to safely remove the current network invite from the stream (it has been 
			// accepted in the EE)
			if(!response.entry || response.entry.length == 0){
				// Search through all dijits in the activity stream
				dojo.forEach(dijit.findWidgets(this.view.newsFeedNode), dojo.hitch(this, function(widget){
					var activityId = widget.newsData && widget.newsData.getActivityId();
					
					// If we find the activity we're looking for
					if(activityId && activityId.indexOf(this.objectId) >= 0){
						// This is the network invite dijit that's been accepted, delete it
						widget.deleteFromView();
					}
				}));
				this.resetLoadRequestTimeout();
				this.nextFromQueue();
			}
		}else{
			// Continue with loading the data
			this.inherited("onFeedLoad", arguments);
		}
	}
});