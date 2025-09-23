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

dojo.provide("lconn.homepage.metrics.views");

dojo.require("lconn.homepage.metrics.util");

/**
 * Object representing all the side navigation views on display in the 
 * Activity Stream page. Any changes here require a notification be
 * sent to the Metrics team so that they can catch it.
 * 
 * @author Robert Campion
 */

lconn.homepage.metrics.views = {
	imFollowing: lconn.homepage.metrics.util.buildItem("activitystream.imfollowing"),
	statusUpdates: lconn.homepage.metrics.util.buildItem("activitystream.statusupdates"),
	actionRequired: lconn.homepage.metrics.util.buildItem("activitystream.actionrequired"), 
	saved: lconn.homepage.metrics.util.buildItem("activitystream.saved"),
	myNotifications: lconn.homepage.metrics.util.buildItem("activitystream.mynotifications"), 
	atMentions: lconn.homepage.metrics.util.buildItem("activitystream.atmentions"),
	discover: lconn.homepage.metrics.util.buildItem("activitystream.discover")
};