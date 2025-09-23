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

dojo.provide("lconn.homepage.as.extension.ActivityReplyExtension");

dojo.require("com.ibm.social.as.extension.interfaces.IExtension");
dojo.require("com.ibm.social.as.item.NewsItem");
dojo.require("com.ibm.social.as.item.data.NewsDataAccessor");

dojo.declare("lconn.homepage.as.extension.ActivityReplyExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	
	// Reference to the news item class function
	newsItemClass: null,
	
	// Reference to the NewsItem class's prototype
	newsItemPrototype: null,
	
	// Backup of the Accessor's 'getActivityReplies'
	getActivityRepliesFunction: null,
	
	// Reference to the news data accessor class function
	newsDataAccessorClass: null,
	
	// Reference to the news data accessor class's prototype
	newsDataAccessorPrototype: null,
	
	
	constructor: function(){
		
		// Make local references to these objects
		this.newsItemClass = com.ibm.social.as.item.NewsItem;
		this.newsItemPrototype = this.newsItemClass.prototype;
		
		this.newsDataAccessorClass = com.ibm.social.as.item.data.NewsDataAccessor;
		this.newsDataAccessorPrototype = this.newsDataAccessorClass.prototype;
	},
	
	/**
	 * Called when the Action Required view loads on the page.
	 */
	onLoad: function(){
		this.newsItemPrototype.disableAction("comment");
		
		// Update the news data accessor class
		this.getActivityRepliesFunction = this.newsDataAccessorPrototype.getActivityReplies;
		dojo.extend(this.newsDataAccessorClass, {
			getActivityReplies: this.customGetActivityReplies
		});
	},
	
	/**
	 * Called when the Saved view is moved away from.
	 */
	onUnload: function(){
		this.newsItemPrototype.enableAction("comment");
		
		// Restore overriden function
		dojo.extend(com.ibm.social.as.item.data.NewsDataAccessor, {
			getActivityReplies: this.getActivityRepliesFunction
		});
	},
	
	/**
	 * Custom get activity replies function to 
	 * get news item comments in the saved view.
	 * @returns {Object} replies if available
	 */
	customGetActivityReplies: function(){
		var obj = this.object;
		
		if(obj && obj.objectType === "comment"){
			var replies = {
			    items: [obj],
			    totalItems: 1
			};
			replies.items[0].content = replies.items[0].summary;
			replies.items[0].updated = (replies.items[0].published) ? replies.items[0].published 
					: replies.items[0].updated;
			
			return replies;
		}
	}
});