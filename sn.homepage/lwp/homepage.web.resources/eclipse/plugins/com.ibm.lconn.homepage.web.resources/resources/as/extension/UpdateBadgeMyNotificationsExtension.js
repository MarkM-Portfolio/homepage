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
dojo.provide("lconn.homepage.as.extension.UpdateBadgeMyNotificationsExtension");

dojo.require("com.ibm.social.as.constants.events");
dojo.require("lconn.homepage.as.extension.UpdateBadgeExtension");

dojo.declare("lconn.homepage.as.extension.UpdateBadgeMyNotificationsExtension",
[lconn.homepage.as.extension.UpdateBadgeExtension],
{	
	updateBadgeEventName: com.ibm.social.as.constants.events.BADGE_UPDATE_MY_NOTIFICATIONS,
    fadeDummyValue: -1001,
    responseBadgeField: "unreadNotifications",
    
    onLoad: function () {
        this.inherited(arguments);
    },

    /**
     *
     * @param response
     * @param destroyNewsFeed
     * @param dynamicAdd
     * @param clickAction
     * @param url
     */
    updateBadgeNum: function (response, destroyNewsFeed, dynamicAdd, clickAction, scrollTop, url) {
        if (response && response.connections) {
            if (response.connections[this.responseBadgeField]){
                var val = response.connections[this.responseBadgeField];

                if (val && val > 0) {
                    dojo.publish(this.updateBadgeEventName, [val,url]);
                }
            } else {
                dojo.publish(this.updateBadgeEventName, [this.fadeDummyValue,url]);
            }

        }
    }

});