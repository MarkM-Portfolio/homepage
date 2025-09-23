/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
dojo.provide("lconn.homepage.as.extension.UpdateBadgeMentionsExtension");

dojo.require("com.ibm.social.as.constants.events");
dojo.require("lconn.homepage.as.extension.UpdateBadgeMyNotificationsExtension");

dojo.declare("lconn.homepage.as.extension.UpdateBadgeMentionsExtension",
[lconn.homepage.as.extension.UpdateBadgeMyNotificationsExtension],
{	
	updateBadgeEventName: com.ibm.social.as.constants.events.BADGE_UPDATE_MENTIONS,
    responseBadgeField: "unreadMentions"

});