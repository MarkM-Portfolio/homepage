/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2016                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.unfollow.UnfollowWidget");

dojo.require("lconn.core.DialogUtil");
dojo.require("lconn.homepage.as.unfollow.NoFollowingWidget");

dojo.requireLocalization("lconn.homepage", "activitystream");

/**
 * This should be a singleton class, to popup dialog call doUnfollow(storyId)
 * @author Yi Ming Huang
 */
dojo.declare("lconn.homepage.as.unfollow.UnfollowWidget", null, {
	
	/**
	 * Construct the widget
	 */
	constructor: function() {
		as_console_debug("lconn.homepage.as.unfollow.UnfollowWidget - constructor");
		
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.unfollowTypeMap = {
			"profile": this.strings.unfollowPersonText,
			"community": this.strings.unfollowCommunityText,
			"file": this.strings.unfollowFileText,
			"activity": this.strings.unfollowActivityText,
			"forum": this.strings.unfollowForumText,
			"wiki": this.strings.unfollowWikiText,
			"blog": this.strings.unfollowBlogText,
			"tag": this.strings.unfollowTagText,
			"file_folder": this.strings.unfollowFolderText
		};
	},
	
	/**
	 * Get the following data source name by the type attribute
	 * @param type {String} following data type (eg. profiles, community, file, etc)
	 * @returns {String} readable strings for the types (eg. Person, Community, File, etc)
	 */
	getUnfollowTypeName: function(type) {
		var name = this.unfollowTypeMap[type];
		if (!name) name = this.strings.unfollowOthersText;
		return name;
	},
	
	/**
	 * Popup the unfollow dialog for certain activity entry
	 * @param storyId {String} activity entry id
	 */
    doUnfollow: function(storyId) {
    	as_console_debug("lconn.homepage.as.unfollow.UnfollowWidget - doUnfollow");
    	
    	this.storyId = storyId;
    	this.data = null;
    	
    	activityStreamAbstractHelper.xhrGet({
    		url: lconn.core.url.getServiceUrl(lconn.core.config.services.homepage) + "/web/doFetchFollowingData.action",
    		content: {storyId: storyId},
    		handleAs: "json",
    		load: dojo.hitch(this, function(data) {
    			this.data = data;
    			this._openDialog();
    		}),
    		error: function(error) {
    			console.error(error);
    		}
    	});
    },
    
    /**
     * Utility function to pop up a dialog 
     */
    _openDialog: function() {
    	var content = this._createUnfollowDom();
    	// Bidi support
      	lconn.core.globalization.bidiUtil.enforceTextDirectionOnPage(content);
    	// it's OK, the dialog is reused by DialogUtil
    	this.dialogHandle = lconn.core.DialogUtil.popupForm(
    		this.strings.unfollowTextTitle,					// dialog title
    		content, 									// content DOM
    		this.strings.unfollowDialogSaveText, 		// Save text
    		this.strings.unfollowDialogCancelText,		// Cancel text
    		dojo.hitch(this, function() {				// onSave function
    			this.onUnfollow();
    			dojo.publish(com.ibm.social.as.constants.events.ITEMGOTFOCUS,[this.newsItem,true]);
    			this.dialogHandle.hide();
    		}), 
    		dojo.hitch(this, function() {				// onCancel function
    			dojo.publish(com.ibm.social.as.constants.events.ITEMGOTFOCUS,[this.newsItem,true]);
    			this.dialogHandle.hide();
    		})
    	);
    	
    	var currentDialog = this.dialogHandle._dialog;
    	
		// prevent this widget from being highlighted again while dialog closing
		// currentDialog.refocus = false;
    	
    	/* Find the "X" button and take it out of the tab sequence. */
    	dojo.query(".lotusDialog a.lotusDialogClose", currentDialog.domNode).attr("tabindex",-1);
    	
    	if (this.data && this.data.length > 0) {
    		// have following data, do nothing
    	} else {
    		// no following data, hide the 'save' button
    		dojo.addClass(currentDialog.lotusSubmitNode, "lotusHidden");
    		currentDialog.lotusCancelNode.value = this.strings.unfollowDialogOKText;
    	}
    },
    
    /**
     * The DOM inside unfollow dialog
     * @returns {DOM} DOM node
     */
    _createUnfollowDom: function() {
    	var root = dojo.create("div");
    	if (this.data && this.data.length > 0) {
    		dojo.create("p", {innerHTML: this.strings.unfollowDialogPromptText, 
    			style: {marginBottom:"10px"}}, root);
        	var table = dojo.create("table", {}, root);
        	
        	for (var i=0,l=this.data.length; i<l; i++) {
        		var tr = table.insertRow(i);
        		this._setupOneUnfollowRow(tr, this.data[i]);
        	}
    	} else {
    		// not following anything
    		root = new lconn.homepage.as.unfollow.NoFollowingWidget({
        			strings: this.strings
        	}).domNode;
    	}
    	
    	return root;
    },
    
    /**
     * Setup the DOM for one unfollow line (eg. Person: [ ] Amy Jones1)
     * @param tr {DOM.TR} the TR in the table
     * @param data {object} following data
     */
    _setupOneUnfollowRow: function(tr, data) {
      var id = this.storyId + data.resourceId + "checkbox";

      var td0 = tr.insertCell(0);
      dojo.create("label", {innerHTML: this.getUnfollowTypeName(data.type), role: "presentation", id: id + "_applabel"}, td0);

      var td1 = tr.insertCell(1);
      var checkbox = dojo.create("input", {type: "checkbox", id: id, "aria-describedby": id + "_applabel"}, td1);
      checkbox.checked = true;	// it's not work on IE to set this in dojo.create

      var td2 = tr.insertCell(2);

      var headingLabel = dojo.create("label", {"for": id}, td2);
      dojo.create("h3", {innerHTML: data.title, className: "bidiAware", style: {margin:"0px"}}, headingLabel);	// override default margin -- 14
    },
    
    /**
     * Perform unfollow action
     */
    onUnfollow: function() {
    	as_console_debug("lconn.homepage.as.unfollow.UnfollowWidget - onUnfollow");
    	
    	var postData = [];
    	
    	var refreshTag = false;
		
    	if (this.data && this.data.length > 0) {
    		for (var i=0,l=this.data.length; i<l; i++) {
        		var id = this.storyId + this.data[i].resourceId + "checkbox";
        		if (dojo.byId(id).checked) {
        			postData.push(this.data[i]);
        			if (this.data[i].type == "tag")
        				refreshTag = true;
        		}
        	}
    	}
    	
    	if (postData.length > 0) {
    		activityStreamAbstractHelper.xhrPost({
    			url: lconn.core.url.getServiceUrl(lconn.core.config.services.homepage) + "/web/doUnfollowResources.action",
    			postData: dojo.toJson(postData),
    			handleAs: "json",
    			headers: {"Content-Type": "application/json"},
    			load: dojo.hitch(this, function(data) {
    				if (data.result == "success") {
    					this.dialogHandle.hide();
    					if (refreshTag)
    						dojo.publish("lconn.homepage.activitystream.event.tagfilter.unfollowTag");
    					// TODO: message 1
    				} else {
    					// TODO: message 2
    				}
    			}),
    			error: function(error){
    				console.error("error %o", error);
	    		}
    		});
    	} else {
    		this.dialogHandle.hide();
    		// TODO: message 3
    	}
    }
});

lconn.homepage.as.unfollow.UnfollowWidget = 
	new lconn.homepage.as.unfollow.UnfollowWidget();