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
dojo.provide("lconn.homepage.as.message.MessageItem");
dojo.requireLocalization("lconn.homepage", "activitystream");
/**
 * Widget used to build a single message item
 * @author Rui Qi Shi
 */

dojo.declare("lconn.homepage.as.message.MessageItem", [dijit._Widget, dijit._Templated],
{	
	// Message item template
	templatePath: dojo.moduleUrl("lconn.homepage", "as/message/templates/messageItem.html"),
	
	// The message content
	msgText: "",
	
	// The message type. "WARNING"|"ERROR"|"CONFIRM".
	msgType: null,
	
	// Whether contain a close button
	canClose: false,
	
	// Parameters in template
	msgClass: "",
	imgClass: "",
	typeAlt: "",
	closeAlt: "",
	role: "",
	/**
	 * Set the properties for every message type. 
	 * To mix the matched params to template
	 */
	constructor: function(obj) {
		this.strings = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.WARNING = {
			type: "WARNING",
			msgClass: "lotusWarning",
			imgClass: "lconnIconMsgWarning",
			typeAlt: this.strings.msgWarningAlt,
			role: "alert"
		};
		this.ERROR = {
			type: "ERROR",
			msgClass: "lotusError",
			imgClass: "lotusIconMsgError",
			typeAlt: this.strings.msgErrorAlt,
			role: "alert"
		};
		this.CONFIRM = {
			type: "CONFIRM",
			msgClass: "lotusSuccess",
			imgClass: "lotusIconMsgSuccess",
			typeAlt: this.strings.msgConfirmAlt,
			role: "alert"
		};
		this.INFO = {
			type: "INFO",
			msgClass: "lotusInfo",
			imgClass: "lotusIconMsgInfo",
			typeAlt: this.strings.msgInfoAlt,
			role: "alert"
		};
		dojo.mixin(this,this[obj.msgType]);
	},
	/**
	 * Called after the widget is rendered in the UI.
	 * 	Render the message item  
	 */
	postCreate: function() {
		this.renderItem();
	},
	renderItem: function() {
		if(this.canClose){
			dojo.removeClass(this.closeBtn,"lotusHidden");
		}
	},
	/**
	 * 	Called when click the close button 'X'.
	 */
	clearItem: function(){
		this.destroyRecursive();
	}
});
