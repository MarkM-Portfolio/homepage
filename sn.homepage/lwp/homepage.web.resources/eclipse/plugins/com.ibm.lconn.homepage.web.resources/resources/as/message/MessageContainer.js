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

dojo.provide("lconn.homepage.as.message.MessageContainer");
/**
 * Widget used to build the globe messages container in ActivityStream
 * @author Rui Qi Shi
 */
dojo.require("dijit._Widget");
dojo.require("lconn.homepage.as.message.MessageItem");
dojo.require("com.ibm.social.as.constants.events");

dojo.declare("lconn.homepage.as.message.MessageContainer", [dijit._Widget], {
	
	//Create message event name
	createMessageEventName: com.ibm.social.as.constants.events.CREATEMESSAGE,
	//Clear message event name
	clearMessageEventName: com.ibm.social.as.constants.events.CLEARMESSAGE,
	// State listened to in case the AS updates its view.
	stateUpdatedEvent: com.ibm.social.as.constants.events.STATEUPDATED,
	/**
	 * Called after the widget is rendered in the UI.
	 * Subscribe add and clear event for message.
	 */
	postCreate: function(){
		this.subscribe(this.createMessageEventName,dojo.hitch(this,"createMessage"));
		this.subscribe(this.clearMessageEventName,dojo.hitch(this,"clearMessage"));
		// Subscribe to Activity Stream state updates. 
		this.subscribe(this.stateUpdatedEvent, dojo.hitch(this,"clearMessage"));
	},
	/**
	 * Create a message item in GlobeMessageContainer
	 */
	createMessage: function(msg,type,canClose){
		// Clear out the current messages (if any)
		this.clearMessage();

		// Clear any other messages on the main page.
		this._clearExistingMainMessages();

		// Create a new message item and add to this widget
		var messageItem = new lconn.homepage.as.message.MessageItem({
			msgText: msg,
			msgType: type,
			canClose: canClose
		});
		this.domNode.appendChild(messageItem.domNode);
		// For accessibility
		messageItem.closeBtn.focus();
	},
	/**
	 * Clear the MessageContainer
	 */
	clearMessage: function(){
		// Destroy all children dijits
		dojo.forEach(dijit.findWidgets(this.domNode), function(widget){
			widget.destroyRecursive();
		});
	},
	
    _clearExistingMainMessages: function() {
    	// Make sure any previous message from the global sharebox is hidden.
    	var globalMessageArea = dojo.query(".shareStatus");
    	
    	if (globalMessageArea && globalMessageArea.length > 0) {
    		dojo.style(globalMessageArea[0],"display","none");
    	}
    	
    	// Make sure any previous MicroBlogging messages are hidden too.
    	globalMessageArea = dojo.query("#messageContainer");
    	
    	if (globalMessageArea && globalMessageArea.length > 0) {
    		globalMessageArea[0].innerHTML="";
    	}
    }
});

