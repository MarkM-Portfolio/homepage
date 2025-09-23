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

dojo.provide("lconn.homepage.as.extension.SavedActionExtension");

dojo.require("com.ibm.social.as.extension.interfaces.IExtension");
dojo.require("com.ibm.social.as.item.NewsItem");
dojo.requireLocalization("lconn.homepage", "activitystream");

dojo.declare("lconn.homepage.as.extension.SavedActionExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	// String object used to override the default news item strings
	strings: null,
	
	// Extra strings to be mixed into NewsItem
	extraStrings: null,
	
	// Reference to the news item class function
	newsItemClass: null,
	
	// Reference to the NewsItem class's prototype
	newsItemPrototype: null,
	
	constructor: function(){
		// Make local references to this objects
		this.newsItemClass = com.ibm.social.as.item.NewsItem;
		this.newsItemPrototype = this.newsItemClass.prototype;
		
		// Setup the strings we're going to override on the NewsItem
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.extraStrings = {
				savedText: this.strings.savedText,
				savedDisabledText: this.strings.savedDisabledText,
				savedSuccessText: this.strings.savedSuccessText
		};
	},
	
	/**
	 * Called when we wish to show the 'Saved' action
	 */
	onLoad: function(){
		// Add the saved action into the list of news item actions
		this.newsItemPrototype.addAction({
			name: "saved",
			text: this.strings.saveThisText,
			callback: "onSavedClicked",
			ordinal: 200,
			isActionDisplayed: function(newsData) {
				return (newsData.id && newsData.id != "");
			}
		});
		
		// Update various functions on the NewsItem prototype
		dojo.extend(this.newsItemClass, {
			updateSavedState: this.updateSavedState,
			isSaved: this.isSaved,
			disableSaved: this.disableSaved,
			onSavedClicked: this.onSavedClicked,
			updateSaved: this.updateSaved
		});
		this.newsItemClass.prototype.extraStrings = !this.newsItemClass.prototype.extraStrings ? 
				{} : this.newsItemClass.prototype.extraStrings;
		dojo.mixin(this.newsItemClass.prototype.extraStrings, this.extraStrings);
	},
	
	/**
	 * Called when we wish to hide the 'Saved' action
	 */
	onUnload: function(){
		// Remove the saved action
		this.newsItemPrototype.removeAction("saved");
		
		// Revert various functions on the NewsItem prototype
		dojo.extend(this.newsItemClass, {
			updateSavedState: undefined,
			isSaved: undefined,
			disableSaved: undefined,
			onSavedClicked: undefined,
			updateSaved: undefined
		});
	},
	
	/**
	 * Update the state of saved for this news item. If this has already
	 * been saved, disable the "Save this" functionality
	 * @param connectionsObject
	 */
	updateSavedState: function(connectionsObject){
		// If saved is already activated
		if(this.isSaved(connectionsObject) && this.savedItemNode){
			// Disable the link
			this.disableSaved(true);
		}
	},
	
	/**
	 * Check to see what state saved is for this news item.
	 * @param connectionsObject
	 * @returns {Boolean} true if has been saved, false otherwise
	 */
	isSaved: function(connectionsObject){
		// If there is a connetionsObject and it has an "true" saved
		// property, return true, else return false
		return (connectionsObject && connectionsObject.saved === "true") ? true : false;
	},
	
	/**
	 * Disable the saved functionality for this news item.
	 * @param onLoad {Boolean} indicates whether we are disabling the 
	 * saved link on load of the news item (i.e. not dynamically).
	 */
	disableSaved: function(onLoad){
		// Add the 'activityStreamActionInactive' class
		dojo.addClass(this.savedItemNode, "activityStreamActionInactive");
		// Override the saved function (so that it does nothing)
		this.onSavedClicked = function(){};
		
		// Add title text to the disabled link
		// If we're loading show a "Already marked ..." otherwise show "Success..." message
		var titleText = (onLoad === true) ? 
				this.strings.savedDisabledText : this.strings.savedSuccessText;
		this.savedLinkNode.setAttribute("title", titleText);
		this.savedLinkNode.setAttribute("aria-label", titleText);
		this.savedLinkNode.innerHTML = this.strings.savedText;

        if (!this.savedItemNode.id){
            this.savedItemNode.id = "savedItemNode_"+new Date().getTime();
        }


        dojo.publish(com.ibm.social.as.constants.events.SAVEACTIONCOMPLETE,  {recommendationsNode: this.savedItemNode});
	},

	/**
	 * Called when the 'Action Required' link is clicked.
	 */
	onSavedClicked: function(){
		var error = function(e){
			console.error("Item could not be marked as Action Required - error: %o", e);
		};
		
		this.updateSaved("true", dojo.hitch(this, "disableSaved"), error);
	},
	
	/**
	 * Update the saved event on the server.
	 * @param saved {String} "true" | "false" - is this item saved.
	 * @param load {Function} callback for when the request loads
	 * @param error {Function} callback for when the request fails
	 */
	updateSaved: function(saved, load, error){
		// Get the current news item's id
		var id = this.newsData.id;
		
		var connectionsRoot = lconn.core.url.getServiceUrl(lconn.core.config.services.opensocial).uri;
		// Post request to the server update the saved setting for this news item.
		// Uses POST, but overrides it to PUT, as PUT may be disabled on the server.
		// Note that some dummy data must be passed in order to be successful.
		activityStreamAbstractHelper.xhrPut({
			url : activityStreamAbstractHelper.getSaveUrl(id),
			postData: dojo.toJson({
				id: "",	actor: {id: ""}, verb: "", object: {id: ""},
				connections: {
					saved: saved
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