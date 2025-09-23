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

dojo.provide("lconn.homepage.as.extension.SavedViewExtension");

dojo.require("lconn.homepage.as.extension.AbstractViewExtension");

dojo.declare("lconn.homepage.as.extension.SavedViewExtension", 
[lconn.homepage.as.extension.AbstractViewExtension],
{
	action: "saved",
	
	rootClass: "savedViewItem",
	
	// String object used to override the default news item strings
	strings: null,
	
	constructor: function(){
		// Setup the strings we're going to override on the NewsItem
		var bundleStrings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.strings = {
			removeNewsItemText: bundleStrings.savedRemoveText,
			removeNewsItemTextTitle: bundleStrings.savedRemoveTextTitle,
			removeConfirmText: bundleStrings.savedRemoveConfirmText,
			removeCancelText: bundleStrings.savedRemoveCancelText,
			removeConfirmMessageText:bundleStrings.savedRemoveMessageText,
			removeConfirmationMsg:bundleStrings.savedRemoveConfirmationMsg,
			removeErrorMsg:bundleStrings.savedRemoveErrorMsg
		};
	},
	
	/**
	 * Called when the Saved view loads on the page.
	 */
	onLoad: function(){
		this.customExtensions = {
				deleteSaved: this.deleteSaved,
				createMessageEvent: this.createMessageEvent,
				newsItemDeleteEvent: this.newsItemDeleteEvent
		};
		this.inherited(arguments);
	},
	
	/**
	 * Delete the entry on click the "Remove" button
	 */
	onDelete: function(){
		var load = dojo.hitch(this, function(){
			// Fade out this news item
			dojo.fadeOut({
		    	node: this.domNode,
    			onEnd: dojo.hitch(this, function(){
    				// Destroy news item (important for EE linked list)
    				this.destroyRecursive();
    				dojo.publish(com.ibm.social.as.constants.events.NEWSITEMDELETE);
				})
			}).play();
			// Show the confirmation message
			dojo.publish(this.createMessageEvent,[this.strings.removeConfirmationMsg,"CONFIRM",true]);
		});
		var error = dojo.hitch(this, function(e){
			console.error("Item could not be removed from Saved - error: %o", e);
			// Show the error message
			dojo.publish(this.createMessageEvent,[this.strings.removeErrorMsg,"ERROR",true]);
		});
		
		this.deleteSaved(load, error);
		this.dialogBundle.hide();
	},
	
	/**
	 * Delete the saved event on the server.
	 * @param saved {String} "true" | "false" - is this item saved.
	 * @param load {Function} callback for when the request loads
	 * @param error {Function} callback for when the request fails
	 */
	deleteSaved: function(load, error){
		// Get the current news item's id
		var id = this.newsData.id;
		
		var connectionsRoot = lconn.core.url.getServiceUrl(lconn.core.config.services.opensocial).uri;
		// Post request to the server update the saved setting for this news item.
		// Uses POST, but overrides it to PUT, as PUT may be disabled on the server.
		// Note that some dummy data must be passed in order to be successful.
		activityStreamAbstractHelper.xhrPut({
			url : activityStreamAbstractHelper.getUnsaveUrl(id),
			postData: dojo.toJson({
				id: "",	actor: {id: ""}, verb: "", object: {id: ""},
				connections: {
					saved: "false"
				}
			}),
			handleAs: "json",
			headers : {
				"Content-Type": "application/json"
			},
			load: load,
			error: error
		});
	}
});