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
dojo.provide("lconn.homepage.as.tagmanager.TagPopupWidget");

dojo.require("lconn.core.DialogUtil");
dojo.require("lconn.homepage.as.tagmanager.TagManager");
dojo.require("com.ibm.social.as.constants.events");
dojo.require("dojox.uuid.generateTimeBasedUuid");
dojo.requireLocalization("lconn.homepage", "activitystream");

/**
 * Class used to build the tag manager light box
 * @author Rui Qi Shi
 */

dojo.declare("lconn.homepage.as.tagmanager.TagPopupWidget", null,
{	
	// The instance of the light box, reuse from lconn.core.DialogUtil
	dialog:null,
	
	// Template of DialogUtil
	templatePath: dojo.moduleUrl("lconn.core", "templates/DialogUtil.html"),

	constructor: function(options){
		// Mix the options in with this class
		if(options){
			dojo.mixin(this, options);
		}
		this.setupStrings();
		// Create the tag manager widget which will be appended into the light box.
		this.contentWidget = new lconn.homepage.as.tagmanager.TagManager({tagsMap:this.tagsMap});
		// Reuse the lconn.core.DialogUtil initialize the light box
		this.init(this.strings.manageTagText,this.contentWidget.domNode,this.strings.submitTagText,'',dojo.hitch(this,this.onSubmit),dojo.hitch(this,this.onCancel));
	},
	/**
	 * Create the tag manager light box 
	 * @param title {String} the text of the light box title.
	 * @param node {DOM} the widget of TagManager's dom node. 
	 * @param submit {String} the text of the submit button.
	 * @param cancel {String} the text of the cancel button.
	 * @param onSubmit {Function} the function when submit button clicked.
	 * @param onCancel {Function} the function when cancel button clicked.
	 */
	init: function(title, node, submit, cancel, onSubmit, onCancel){
		// Use lconn.core.DialogUtil._getDialog to build a dialog has a conflict with the unfollow
		// dialog which also build by it , because the common method will destroy all the dialogs
		// in the page before build a new one, so comment it, and overwrite it here
		// this.dialog = lconn.core.DialogUtil._getDialog(title);
	    var bundle = dojo.i18n.getLocalization('lconn.core', 'strings');
		var blankGif = dojo.config.blankGif;
		// set up the dialog template
		var html = dojo.string.substitute(dojo.cache("lconn.core", "templates/DialogUtil.html"),
			dojo.mixin(bundle, {
			blankGif: blankGif,
			titleID: dojox.uuid.generateTimeBasedUuid(),
			contentID: dojox.uuid.generateTimeBasedUuid()
		})); 	
		this.dialog = new dijit.Dialog();
		var d = this.dialog;
		// put the customized template into the dojo dialog
		d.containerNode.innerHTML = html;
		// query the node as objects
		d.closeBtn = dojo.query(".lotusDialogClose", d.containerNode)[0];
		d.lotusTitleNode = dojo.query("h1 .title", d.containerNode)[0];
		d.lotusContentNode = dojo.query(".lotusDialogContent", d.containerNode)[0];
		d.lotusFooterNode = dojo.query(".lotusDialogFooter", d.containerNode)[0];
		d.lotusSubmitNode = dojo.query(".lotusDialogFooter .submit", d.containerNode)[0];
		d.lotusCancelNode = dojo.query(".lotusDialogFooter .cancel", d.containerNode)[0];
		// set up values and methods
	    d.lotusTitleNode.innerHTML = title;
	    d.lotusContentNode.innerHTML = '';
	    d.lotusContentNode.appendChild(node);
	    d.lotusSubmitNode.value = submit;
	    d.lotusCancelNode.value = cancel;
	    d.lotusCancelNode.style.display = 'none';
	    d.lotusSubmitNode.onclick = function() { onSubmit(); };
	    d.lotusCancelNode.onclick = function() { onCancel(); };
	    d.closeBtn.onclick = function() { onCancel(); };
	    dojo.addClass(d.domNode,"tagManagerDialog");
	},
	/**
	 * Set stings for i18n
	 */
	setupStrings: function(){
		 this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
	},
	/**
	 * Called when submit button clicked
	 */
	onSubmit: function() {
		this.dialog.hide();
		this.contentWidget.clearMessage();
		dojo.publish(com.ibm.social.as.constants.events.UPDATETAGFILTER);
	},
	/**
	 * Called when close button clicked
	 */
	onCancel: function() {
		this.onSubmit();
	},
	/**
	 * Show the light box
	 */
	onShow: function() {
		this.dialog.show();
	}
});