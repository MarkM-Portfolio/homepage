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
dojo.provide("lconn.homepage.as.message.UIMessage");

/**
 * Widget used to build the status messages UI(confirm,error,warning)
 * @author Rui Qi Shi
 */

dojo.declare("lconn.homepage.as.message.UIMessage", [dijit._Widget, dijit._Templated],
{	
	// Message container node
	templateString: '<DIV></DIV>',
	
	// The list object of the messages
	messages: null,
	
	// Template for message UI
	messageTemplate: 
		'<DIV class="lotusMessage2 ${0}">' +
			'<IMG class="lotusIcon ${1}" alt="${2}" src="${3}" />' +
			'<SPAN dojoAttachPoint="textAP">${4}</SPAN>' +
		'</DIV>',
	// The container domNode for message
	containerNode:null,	
	//Create message event name
	createMessageEventName: "lconn.homepage.activitystream.event.UIMessage.create",
	//Clear message event name
	clearMessageEventName: "lconn.homepage.activitystream.event.UIMessage.clear",
	/**
	 * Called before the widget is rendered in the UI.
	 * Set the properties for every message type.
	 */
	postMixInProperties: function() {
		
		this.WARNING = {
			type: "WARNING",
			msgClass: "lotusWarning",
			imgClass: "lotusIconMsgWarning",
			alt: "Warning"
		};
		this.ERROR = {
			type: "ERROR",
			msgClass: "lotusError",
			imgClass: "lotusIconMsgError",
			alt: "Error"
		};
		this.CONFIRM = {
			type: "CONFIRM",
			msgClass: "lotusConfirm",
			imgClass: "lotusIconMsgSuccess",
			alt: "Confirm"
		};
		this.SUCCESS = {
			type: "SUCCESS",
			msgClass: "lotusSuccess",
			imgClass: "lotusIconMsgSuccess",
			alt: "Success"
		};
	},
	/**
	 * Called after the widget is rendered in the UI.
	 * Subscribe add and clear event for message.
	 */
	postCreate: function() {
		this.subscribe(this.createMessageEventName,dojo.hitch(this,"addMessages"));
		this.subscribe(this.clearMessageEventName,dojo.hitch(this,"clearMessage"));
	},
	/**
	 * Called when dojo publish create event
	 * @param msg {String} the content text of the message.
	 * @param type {String} "WARNING"|"ERROR"|"CONFIRM" message type.
	 * @param node {DOM} the message UI's container node.
	 */
	addMessages: function(msg,type,node) {
		dojo.empty(this.domNode);
		var t = this[type];
		var ui = dojo.string.substitute(this.messageTemplate,[t.msgClass, t.imgClass, t.alt, this._blankGif , msg]);
		this.domNode.innerHTML += ui;
		this.containerNode = node;
		this.containerNode.appendChild(this.domNode);
		
		// Reapply the alert role otherwise the item doesn't get announced.
		dojo.attr(this.domNode,"role","alert");
	},
	/**
	 * Called when dojo publish clear event
	 */
	clearMessage: function() {
		dojo.empty(this.domNode);
	}
});
