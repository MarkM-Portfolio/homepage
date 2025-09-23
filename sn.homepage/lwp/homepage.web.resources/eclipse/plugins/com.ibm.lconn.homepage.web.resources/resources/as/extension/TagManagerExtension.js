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

dojo.provide("lconn.homepage.as.extension.TagManagerExtension");

dojo.require("com.ibm.social.as.extension.interfaces.IExtension");
dojo.require("lconn.homepage.as.tagmanager.TagPopupWidget");
dojo.require("com.ibm.social.as.constants.events");
dojo.require("com.ibm.social.as.util.ConfigObjectUtil");

dojo.declare("lconn.homepage.as.extension.TagManagerExtension", 
[com.ibm.social.as.extension.interfaces.IExtension],
{
	tagsPopupManager:'',
	placeholderAddEventName: com.ibm.social.as.constants.events.PLACEHOLDERADD,
	placeholderRemoveEventName:	com.ibm.social.as.constants.events.PLACEHOLDERREMOVE,
	tags:null,
	constructor: function(){
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");

		// find the list of tags
		var obj = activityStreamConfig.filters;
		while(obj.selectedItem && obj.selectedItem !== "tags") {
			obj = com.ibm.social.as.util.ConfigObjectUtil.getSelectedOrDefaultOptionObj(obj).filters;
		}
		this.tags = obj.options["tags"].filters.options;
		//Initialize the popup lightbox
		this.tagsPopupManager = new lconn.homepage.as.tagmanager.TagPopupWidget({tagsMap:this.tags});
	},
	
	/**
	 * Called when the FilterBy tags view loads on the page.
	 */
	onLoad: function(){
		this.tagsFollowing(com.ibm.social.as.util.ConfigObjectUtil.getNumRealOptions(this.tags));
		
		// build the manage tag link node
		var manageLinksNode = dojo.create("a", {
			 innerHTML: this.strings.tagManagerText,
			 role: "button",				 
			 href:"javascript:;"
		});
		
		manageLinksNode.onclick = dojo.hitch(this,function(){this.tagsPopupManager.onShow();});
		manageLinksNode.onkeypress = dojo.hitch(this, "showTagManagerKey");
		dojo.publish(this.placeholderAddEventName,[com.ibm.social.as.view.placeholder.location.headerRightSub, manageLinksNode]);
	},
	
	/**
	 * Called when the FilterBy tags view is moved away from.
	 */
	onUnload: function(){
		// Remove the tagmanager widget from placeholder
 		dojo.publish(this.placeholderRemoveEventName,[com.ibm.social.as.view.placeholder.location.headerCenter]);
		dojo.publish(this.placeholderRemoveEventName,[com.ibm.social.as.view.placeholder.location.headerRightSub]);
		// Reset the noCantentItem
		com.ibm.social.as.item.NoContentItem.prototype.noContentInnerHtml = "";
		com.ibm.social.as.item.NoContentItem.prototype.hereClicked = undefined;
	},
	
	/**
	 * Function to show the Tag Manager if the spacebar is pressed on the button.
	 * @param e {Event} - Event that triggered the function.
	 */
	showTagManagerKey: function(e) {
		if (e && (e.keyCode || e.which) === 32) {
			this.tagsPopupManager.onShow();
			dojo.stopEvent(e);
		}
	},
	
	/**
	 * Decide what content to display in noContentItem widget
	 */
	tagsFollowing: function(tagsLength){
		if(tagsLength>0){
			com.ibm.social.as.item.NoContentItem.prototype.noContentInnerHtml = this.strings.noTagsNewsText;
		}else{
			// customize the noContentItem if there's no tags followed 
			var btnLabel = dojo.string.substitute(this.strings.noTagsItemsText,[this.strings.hereText]);
			var manageLinkString = "<a href='javascript:;' role='button' dojoAttachEvent='onclick: hereClicked' aria-label='"+btnLabel+"'>"+this.strings.hereText+"</a>" ;
			com.ibm.social.as.item.NoContentItem.prototype.noContentInnerHtml = dojo.string.substitute(this.strings.noTagsItemsText,[manageLinkString]);
			com.ibm.social.as.item.NoContentItem.prototype.hereClicked = dojo.hitch(this,function(){this.tagsPopupManager.onShow();});
		}
		
	}
});
