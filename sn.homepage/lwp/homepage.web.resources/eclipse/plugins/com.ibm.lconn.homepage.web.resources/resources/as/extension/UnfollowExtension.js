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

dojo.provide("lconn.homepage.as.extension.UnfollowExtension");

dojo.require("com.ibm.social.as.extension.interfaces.IExtension");
dojo.require("com.ibm.social.as.item.NewsItem");
dojo.require("lconn.homepage.as.unfollow.UnfollowWidget");

dojo.declare("lconn.homepage.as.extension.UnfollowExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	// String object used to override the default news item strings
	strings: null,
	
	// Backup of the onDeleteClicked function held by the news item
	onDeleteClickedFunction: null,
	
	// Reference to the news item class function
	newsItemClass: null,
	
	// Reference to the NewsItem class's prototype
	newsItemPrototype: null,
	
	constructor: function(){
		// Make local references to these objects
		this.newsItemClass = com.ibm.social.as.item.NewsItem;
		this.newsItemPrototype = this.newsItemClass.prototype;
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
	},
	
	/**
	 * Called when the Action Required view loads on the page.
	 */
	onLoad: function(){
		as_console_debug("lconn.homepage.as.extension.UnfollowExtension - onLoad");
		// Extend the news item class, adding an unfollow function
		dojo.extend(this.newsItemClass, {
			onUnfollowClicked: this.onUnfollowClicked
		});
		
		// Add an unfollow action to all news items
		this.newsItemPrototype.addAction({
			name: "unfollow",
			text: this.strings.unfollowText,
			callback: "onUnfollowClicked",
			isActionDisplayed: dojo.hitch(this, "isStopFollowingDisplayed"),
			ordinal: 300
		});
	},
	
	/**
	 * Called when the Action Required view is moved away from.
	 */
	onUnload: function(){
		// Remove the unfollow action
		this.newsItemPrototype.removeAction("unfollow");
		
		// Delete the custom unfollow function we added
		dojo.extend(this.newsItemClass, {
			onUnfollowClicked: undefined
		});
	},
	
	/**
	 * The 'Unfollow' link was clicked.
	 */
	onUnfollowClicked: function(){
		as_console_debug("lconn.homepage.as.extension.UnfollowExtension - onUnfollowClicked");
		
		lconn.homepage.as.unfollow.UnfollowWidget.newsItem = this;
		lconn.homepage.as.unfollow.UnfollowWidget.doUnfollow(this.newsData.id);
	},
	
	/**
	 * Should the 'Stop Following' action be displayed.
	 * @param newsData {object} news item data
	 */
	isStopFollowingDisplayed: function(newsData) {
		return (newsData.getConnectionsFollowedResource() === "true");
	}
});