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

dojo.provide("lconn.homepage.metrics.track");

dojo.require("com.ibm.lconn.layout.track");



/**
 * A Homepage wrapper/utility for the Connections core metrics class.
 * Designed especially for Homepage and its pages/views.
 * 
 * @author Robert Campion
 */

lconn.homepage.metrics.track = {
	// The core layout tracker
	track: com.ibm.lconn.layout.track,
	
	// The amount of time in ms to wait before firing metrics
	fireTimeDelay: 2000,
	
	/**
	 * Fire off a metric track for the metric passed.
	 * @param metrics {Object}
	 */
	fire: function(metrics){
		this.debug("Firing metric for " + metrics.contentId);
		
		if(metrics){
			setTimeout(dojo.hitch(this, function(){
				// Call the global tracker to read the passed metrics
				this.track.read(metrics.contentId, metrics.itemType, metrics);
				this.debug("Fired metric for " + metrics.contentId);
			}), this.fireTimeDelay);
		}
	},

	/**
	 * Read the item name and process metrics
	 * @param itemName {String} name of the page/view
	 * @param items {Object} optional item lookup
	 */
	read: function(itemName, items){
		// If items is passed, use it, else assume pages
		items = items || lconn.homepage.metrics.pages;
		
		if(!items){
			// If not available, warn and return
			this.warn("Items not available for " + itemName);
			return;
		}
		
		// Get the item
		var item = items[itemName];
		if(!item){
			// If not available, warn and return
			this.warn("Item not available for " + itemName);
			return;
		}
		
		// Fire off item metrics
		this.fire(item.metrics);
		
		// Set up the listener if available
		var listener = item.listener;
		
		// Fire off the listener function if it exists
		if(listener && typeof listener === "function"){
			item.listener();
		}
	},
	
	/**
	 * Debug helper function.
	 * @param messge {String} will be appended after context
	 */
	debug: function(message){
		hp_console_debug("lconn.homepage.metrics.track - " + message);
	},
	
	/**
	 * Warning that there may have been a problem.
	 * @param messge {String} will be appended after context
	 */
	warn: function(message){
		console.warn("lconn.homepage.metrics.track - " + message);
	}
};