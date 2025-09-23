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

/**
 * @author decarey
 * @date 09/08/13
 */

dojo.provide("lconn.homepage.as.badge.MentionsBadge");

dojo.require("lconn.homepage.as.badge.ActionRequiredBadge");
dojo.require("com.ibm.social.as.constants.events");

dojo.declare("lconn.homepage.as.badge.MentionsBadge", lconn.homepage.as.badge.MyNotificationsBadge,
{
    updateBadgeEventName: com.ibm.social.as.constants.events.BADGE_UPDATE_MENTIONS,
    decreaseBadgeEventName: com.ibm.social.as.constants.events.BADGE_DECREASE_MENTIONS,
    urlSegment: "@mentions",
    view: "atMentions",
    badgeGlobal: "unreadMentionsTotal"

});