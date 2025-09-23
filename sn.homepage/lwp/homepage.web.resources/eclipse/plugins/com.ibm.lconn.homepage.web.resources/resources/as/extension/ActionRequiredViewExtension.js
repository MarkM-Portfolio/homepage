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

dojo.provide("lconn.homepage.as.extension.ActionRequiredViewExtension");

dojo.require("lconn.homepage.as.extension.AbstractViewExtension");

dojo.declare("lconn.homepage.as.extension.ActionRequiredViewExtension", 
[lconn.homepage.as.extension.AbstractViewExtension],
{
	
	action: "actionRequired",
	
	rootClass: "actionRequiredViewItem",
	
	decreaseBadgeEventName: com.ibm.social.as.constants.events.ACTIONREQUIREDBADGEDECREASE,
	
	// String object used to override the default news item strings
	strings: null,
	
	constructor: function(){
		// Setup the strings we're going to override on the NewsItem
		var bundleStrings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.strings = {
			removeNewsItemText: bundleStrings.actionRequiredRemoveText,
			removeNewsItemTextTitle: bundleStrings.actionRequiredRemoveTextTitle,
			removeConfirmText: bundleStrings.actionRequiredConfirmText,
			removeCancelText: bundleStrings.actionRequiredCancelText,
			removeConfirmMessageText:bundleStrings.actionRequiredRemoveMessageText,
			removeConfirmationMsg:bundleStrings.actionRequiredRemoveConfirmationMsg,
			removeErrorMsg:bundleStrings.actionRequiredRemoveErrorMsg
		};
	},
	
	/**
	 * Called when the view loads on the page.
	 */
	onLoad: function(){
		this.customExtensions = {
				decreaseBadgeEventName: this.decreaseBadgeEventName,
				createMessageEvent: this.createMessageEvent,
				deleteFromView: this.deleteFromView,
				deleteSuccessful: this.deleteSuccessful
		};
		this.inherited(arguments);
	},
	
	deleteFromView: function(){
		this.deleteSuccessful();
		if(this.dialogBundle){
			this.dialogBundle.hide();
		}
	},

	deleteSuccessful: function(){
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
		dojo.publish(this.createMessageEvent,
			[this.strings.removeConfirmationMsg,"CONFIRM",true]);
		
		// Decrease the badge number
		dojo.publish(this.decreaseBadgeEventName);
	},
	
	/*
	 * Delete the entry on click the "Remove" button
	 */
	onDelete: function(){
		// Get the current news item's id
		var id = this.newsData.id;
		// Post request to the server update the saved setting for this news item.
		// Uses POST, but overrides it to PUT, as PUT may be disabled on the server.
		// Note that some dummy data must be passed in order to be successful.
		activityStreamAbstractHelper.xhrPut({
			url : activityStreamAbstractHelper.getActionRequiredUrl(id),
			postData: dojo.toJson({
				id: "",	actor: {id: ""}, verb: "", object: {id: ""},
				connections: {
					actionable: "false"
				}
			}),
			handleAs: "json",
			headers : {
				"Content-Type": "application/json"
			},
			load: dojo.hitch(this, function(){
				this.deleteSuccessful();
			}),
			error: dojo.hitch(this,function(e){
				console.error("Item could not be removed from Action Required - error: %o", e);
				// Show the error message
				dojo.publish(this.createMessageEvent,
					[this.strings.removeErrorMsg,"ERROR",true]);
			})
		});
		
		if(this.dialogBundle){
			this.dialogBundle.hide();
		}
	}
});