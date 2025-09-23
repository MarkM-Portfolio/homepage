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

dojo.provide("lconn.homepage.metrics.pages");

dojo.require("com.ibm.social.as.constants.events");

dojo.require("lconn.homepage.metrics.track");
dojo.require("lconn.homepage.metrics.util");
dojo.require("lconn.homepage.metrics.views");

/**
 * Object representing all the pages on display in Homepage.
 * 
 * @author Robert Campion
 */

lconn.homepage.metrics.pages = {
	/** Getting started page */
	gettingStarted: lconn.homepage.metrics.util.buildItem("gettingstarted"),
	
	/** Activity Stream page */
	activityStream: dojo.mixin({
		// Homepage tracker
		track: lconn.homepage.metrics.track,
		
		// Activity Stream page views
		views: lconn.homepage.metrics.views,
		
		updateStateEvent: com.ibm.social.as.constants.events.UPDATESTATE,
		
		listener: function(){
			// Listen out for AS "update state" calls and filter by view
			dojo.subscribe(this.updateStateEvent, dojo.hitch(this, function(stateArr){
				if(stateArr && stateArr.length>0){
					// Read the view, passing the views object as lookup
					this.track.read(stateArr[0], this.views);
				}
			}));
		}
	}, lconn.homepage.metrics.util.buildItem("activitystream")),
	
	/** Widgets page */
	widgets:lconn.homepage.metrics.util.buildItem("widgets")
};