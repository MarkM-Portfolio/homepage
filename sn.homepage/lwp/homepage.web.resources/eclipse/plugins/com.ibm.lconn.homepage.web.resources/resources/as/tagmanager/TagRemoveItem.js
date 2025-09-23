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
dojo.provide("lconn.homepage.as.tagmanager.TagRemoveItem");
dojo.requireLocalization("lconn.homepage", "activitystream");
dojo.require("com.ibm.social.incontext.util.html");
dojo.declare("lconn.homepage.as.tagmanager.TagRemoveItem", [dijit._Widget, dijit._Templated],
{	// The API url foremove tags subscription 
	removeUrl: '',
	// The tag object e.g.{text:"tag",value:"xxxx"}
	tagItem: null,
	// Tag's text
	tagText: '',
	// Tag's Id
	tagId: '',
	// Call back to hide tags component when last tag removed
	onRemoveCallback: null,
	// Create message event name
	createMessageEventName: "lconn.homepage.activitystream.event.UIMessage.create",
	// Status node
	statusMessageNode: null,
	templatePath: dojo.moduleUrl("lconn.homepage", "as/tagmanager/templates/tagRemoveItem.html"),

	/**
	 * Called before the widget is rendered in the UI.
	 */
	postMixInProperties: function() {
		this._resourceBundle = dojo.i18n.getLocalization("lconn.homepage", "activitystream");
		this.tagText=com.ibm.social.incontext.util.html.decodeHtml(this.tagItem.text);
		this.tagId= this.tagItem.value;
	},
	/**
	 * Called when the 'x' clicked
	 */
	onRemove: function(){
		this.removeTagSubscription();
	},
	
	/**
	 * Capture the user pressing the space key to delete the tag.
	 * @param e {Event} event that triggered the action.
	 */
	keyPressRemove: function(e) {
		if (e && (e.keyCode || e.which)===32) {
			this.onRemove();
			dojo.stopEvent(e);
		}
	},
	
	/**
	 * Xhr call to remove tag
	 */
	removeTagSubscription: function(){
		as_console_debug("lconn.homepage.as.tagmanager.TagRemoveItem --> removeTagSubscription -url [" + this.removeUrl + "]");
		var args = { 
			url:  this.removeUrl,
			load: dojo.hitch(this, "handleRemoveTagSubscription"),
			handleAs: "json",
			headers: {"X-Update-Nonce": "true"},
			content: {
				tagId: this.tagId,
				tag: this.tagText
			},
			preventCache:true
		};
		activityStreamAbstractHelper.xhr("put",args);
	},
	/**
	 * The handle function for XHR call
	 * @param json{Json} return data from the xhr call
	 * @param ioArgs{Object} pass the xhr parameters 
	 */
	handleRemoveTagSubscription: function(json, ioArgs) {
		as_console_debug("lconn.homepage.as.tagmanager.TagRemoveItem --> handleRemoveTagSubscription [" + json + "]");
		if(json.error) {
			//TODO: error message
			console.error("remove tag error");
		} else {
			this._widgetDestroy();
			if(this.onRemoveCallback){
				this.onRemoveCallback(json.items);	
			}
			var confirmText = dojo.string.substitute(this._resourceBundle.tagsRemovedConfirm,[this.tagText]);
			dojo.publish(this.createMessageEventName,[confirmText,"CONFIRM",this.statusMessageNode]);
		}
	},
	/**
	 * Destroy the removed tag on UI
	 */
	_widgetDestroy: function(){
		this.destroy();
	}
});