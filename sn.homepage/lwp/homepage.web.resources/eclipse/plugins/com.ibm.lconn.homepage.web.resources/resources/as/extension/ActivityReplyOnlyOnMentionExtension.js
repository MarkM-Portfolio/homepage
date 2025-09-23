/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2015                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.extension.ActivityReplyOnlyOnMentionExtension");

dojo.require("lconn.homepage.as.extension.ActivityReplyExtension");

dojo.declare("lconn.homepage.as.extension.ActivityReplyOnlyOnMentionExtension",
    [lconn.homepage.as.extension.ActivityReplyExtension],
    {
        //Called when the view loads.
        onLoad: function(){
            //override NewsDataAccessor getActivityReplies
            this.getActivityRepliesFunction = this.newsDataAccessorPrototype.getActivityReplies;

            dojo.extend(this.newsDataAccessorClass, {
                getActivityReplies: this.customGetActivityReplies
            });

            //override NewsItem newsItemPostCreateAction
            this.newsItemPostCreateActionOrig = this.newsItemClass.newsItemPostCreateAction;

            dojo.extend(this.newsItemClass, {
                newsItemPostCreateAction: this.customNewsItemPostCreateAction
            });
        },

        //Called when the view is moved away from.
        onUnload: function(){
            //reset NewsDataAccessor getActivityReplies
            dojo.extend(this.newsDataAccessorClass, {
                getActivityReplies: this.getActivityRepliesFunction
            });

            //reset NewsItem newsItemPostCreateAction
            dojo.extend(this.newsItemClass, {
                newsItemPostCreateAction: this.newsItemPostCreateActionOrig
            });
        },

        customGetActivityReplies: function(){
            var obj = this.object;

            if(obj && obj.objectType === "comment" && this.getVerb() === "mention"){
                console.debug("ENTER: overridden com.ibm.social.as.item.data.NewsDataAccessor customGetActivityReplies - verb is " +  this.getVerb())

                var replies = {

                    items: [obj],
                    totalItems: 1
                };
                replies.items[0].content = replies.items[0].summary;
                replies.items[0].updated = (replies.items[0].published) ? replies.items[0].published
                    : replies.items[0].updated;

                return replies;
            } else {
                return (this.activity && this.activity.replies) || undefined;
            }
        },

        customNewsItemPostCreateAction: function(){
            var verb = this.newsData.getVerb();
            console.info("ENTER: overridden com.ibm.social.as.item.NewsItem newsItemPostCreateAction - verb is " +  verb)

            if ( verb === 'mention'){
                this.disableAction("comment");
            } else {
                this.enableAction("comment")
            }
        }

    });