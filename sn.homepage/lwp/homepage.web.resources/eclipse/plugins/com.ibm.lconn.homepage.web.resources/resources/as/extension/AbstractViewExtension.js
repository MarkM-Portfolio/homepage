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

dojo.provide("lconn.homepage.as.extension.AbstractViewExtension");

dojo.require("com.ibm.social.as.extension.interfaces.IExtension");
dojo.require("com.ibm.social.as.item.NewsItem");
dojo.require("lconn.core.DialogUtil");
dojo.require("com.ibm.social.as.constants.events");

/**
 * Abstract Extension to be subclassed by actual view extensions
 * subclasses should :
 * define strings to be used
 * define an action and rootClass
 * override any default functions with non default behaviour
 * add any additional functions to customExtensions in onLoad, before calling this.inherited()
 * 
 * this class provides a delete dialog interface and always displays the X by default but 
 * you *must* override onDelete or stop the X from displaying entirely
 */

dojo.declare("lconn.homepage.as.extension.AbstractViewExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	// String object used to override the default news item strings
	strings: null,
	
	// Backup of the onDeleteClicked function held by the news item
	onDeleteClickedFunction: null,
	
	// Backup the isXDisplayed of the news item
	isXDisplayedFunction: null,
	
	// Reference to the news item class function
	newsItemClass: null,
	
	// Reference to the NewsItem class's prototype
	newsItemPrototype: null,
	
	// Event to create message.
	createMessageEvent: com.ibm.social.as.constants.events.CREATEMESSAGE,
	
	// extra functions to extend, should be filled in subclasses
	customExtensions: null,
	
	// holder for all extensions applied
	allExtensions: null,
	
	constructor: function(){
		// Make local references to these objects
		this.newsItemClass = com.ibm.social.as.item.NewsItem;
		this.newsItemPrototype = this.newsItemClass.prototype;
		this.allExtensions = {};
	},
	
	/**
	 * Called when the view loads on the page.
	 */
	onLoad: function(){
		// Save a copy of the current function
		this.onDeleteClickedFunction = this.newsItemPrototype.onDeleteClicked;
		this.isXDisplayedFunction = this.newsItemPrototype.isXDisplayed;
		

		this.newsItemPrototype.disableAction(this.action);
		
		var defaultExtensions = {
				isXDisplayed: this.isXDisplayed,
				onDeleteClicked: this.onDeleteClicked,
				onDeleteCallback: this.onDelete,
				onCancelCallback: this.onCancel,
				rootClass: this.rootClass,
				extraStrings: dojo.mixin(this.newsItemClass.prototype.extraStrings, this.strings)
			};
		this.allExtensions = dojo.mixin(defaultExtensions, this.customExtensions);
		dojo.extend(this.newsItemClass, this.allExtensions);
	},
	
	/**
	 * Called when the Saved view is moved away from.
	 */
	onUnload: function(){
		this.newsItemPrototype.enableAction(this.action);
		
		for(var key in this.allExtensions){
			if(this.allExtensions.hasOwnProperty(key)) {
				this.allExtensions[key] = null;
			}
		}
		
		this.allExtensions["onDeleteClicked"] = this.onDeleteClickedFunction;
		this.allExtensions["isXDisplayed"] = this.isXDisplayedFunction;
		
		// Restore the old properties to the NewsItem prototype
		dojo.extend(this.newsItemClass, this.allExtensions);
		
	},
	
	/**
	 * Should the X icon be displayed for default view news items.
	 * @returns {Boolean} true - always show the X by default
	 */
	isXDisplayed: function(){
		return true;
	},
	
	/**
	 * Called when the 'X' icon from the view is clicked.
	 * Popup a confirm dialog.
	 * Click 'Remove' to confirm removal of the entry(onDeleteCallback)
	 * Click 'Cancel' to cancel removal of the entry(onCancelCallbcak)
	 */
	onDeleteClicked: function(){
		
		
		var messageNode = dojo.create("div",{
			innerHTML:this.strings.removeConfirmMessageText
		});
		// This class for setting the 'width' of the dialog 
		dojo.addClass(messageNode,"confirmDialogMessageNode");
		this.dialogBundle = lconn.core.DialogUtil.popupForm(
			this.strings.removeNewsItemTextTitle,
			messageNode,
			this.strings.removeConfirmText,
			this.strings.removeCancelText,
			dojo.hitch(this,this.onDeleteCallback),
			dojo.hitch(this,this.onCancelCallback)
		);
		
	},
	
	/**
	 * Delete the entry on click the "Remove" button
	 * This needs to be overridden with a specific implementation
	 */
	onDelete: function(){
		console.error(this.action + " should override method onDelete");
	},
	
	/**
	 * Keep the entry when click cancel button
	 */
	onCancel: function(){
		this.dialogBundle.hide();

		// Ensure the item is highlighted in the AS. The event is handled by the focus handler.
		dojo.publish(com.ibm.social.as.constants.events.ITEMGOTFOCUS,[this,true]);
	}
});