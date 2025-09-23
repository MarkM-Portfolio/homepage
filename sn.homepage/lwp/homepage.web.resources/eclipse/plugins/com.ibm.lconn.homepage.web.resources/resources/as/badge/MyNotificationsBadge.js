/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2016                                    */
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

dojo.provide("lconn.homepage.as.badge.MyNotificationsBadge");

dojo.require("lconn.homepage.as.badge.ActionRequiredBadge");
dojo.require("com.ibm.social.as.constants.events");
dojo.require("com.ibm.social.as.config.ConfigManager");

dojo.declare("lconn.homepage.as.badge.MyNotificationsBadge", lconn.homepage.as.badge.ActionRequiredBadge,
{
    updateBadgeEventName: com.ibm.social.as.constants.events.BADGE_UPDATE_MY_NOTIFICATIONS,
    decreaseBadgeEventName: com.ibm.social.as.constants.events.BADGE_DECREASE_MY_NOTIFICATIONS,
    resetBadgeEventName: com.ibm.social.as.constants.events.BADGE_RESET,
    fadeDummyValue: -1001,
    maxFetchValue: 100,
    badgeGlobal: "unreadNotificationsTotal",
    fadeArgs: {
    	duration: 2000
     },
    urlSegment: "@responses",  //if urlCalled contains urlSegment fadeOut - so badge fades when you click link beside badge!
    view: "myNotifications",
    
    /**
	 * Called after the widget is rendered in the UI.
	 */
	postCreate: function(){
        dojo.addClass(this.domNode,"lotusHidden");
        this.setupSharedSubscriptions();
	},
    
    updateBadgeNum: function(totalNum,calledUrl) {
        if (!totalNum || totalNum == ""){
            totalNum = this.fadeDummyValue;
        }
        //this.setFetchValue(totalNum);
        
        if(totalNum == this.fadeDummyValue || totalNum < 1){
           this.updateBadgeNodeNum(totalNum, calledUrl);
           this.fadeOutBadge();
        }else if((calledUrl && calledUrl.indexOf(this.urlSegment) != -1)){
        	if(this.badgeNum < totalNum){
        		//badge has increase since badge display
        		this.updateBadgeThenFadeOut(totalNum);              
        	}
        	else{
                this.updateBadgeNodeNum(totalNum, calledUrl);
                this.fadeOutBadge();
        	}
        	//call to reset the badge
        	dojo.publish(this.resetBadgeEventName, [this.urlSegment]);
        }else{        	
           this.updateBadgeNodeNum(totalNum, calledUrl);
           this.fadeInBadge();
        }

    },
    
    /**
     * Update the node itself with the value
     * @param totalNum - badge number
     */
    updateBadgeNodeNum: function(totalNum, calledUrl){
    	this.badgeNum = totalNum;
    	var badgeSync = totalNum;
    	if (totalNum !== this.fadeDummyValue) { 			
            this.domNode.innerHTML = totalNum;
        }
    	else {  
    		badgeSync = 0;
    	}
    	if(this.urlSegment === '@responses'){
			dojo.publish(com.ibm.social.as.constants.events.BADGE_SYNC_MY_NOTIFICATIONS, badgeSync);    
		}   
    },
    
    /**
     * Take the new badge number, update the badge straight away
     * then after short period remove
     * @param totalNum
     */
    updateBadgeThenFadeOut: function(totalNum){
    	this.updateBadgeNodeNum(totalNum);
    	this.fadeInBadge(0);
    	setTimeout(dojo.hitch(this, function(){
    		this.fadeOutBadge(1000);
		}), 2000);
    },
        
    /**
     * Fade in the badge updating the internal badge number
     * @param durationVal - MS, overrides the default fade transition
     */
    fadeInBadge: function(durationVal){
    	this.fadeArgs.node = this.domNode;
    	if(durationVal){
    		this.fadeArgs.duration = durationVal;
   	 	}
    	 dojo.fadeIn(this.fadeArgs).play();

         if (dojo.hasClass(this.domNode, "lotusHidden")){
             dojo.removeClass(this.domNode,"lotusHidden");
         }
    },
    
    /**
     * Fade out the badge clearing the internal badge number
     * @param durationVal - MS, overrides the default fade transition
     */
    fadeOutBadge: function(durationVal){
    	 this.fadeArgs.node = this.domNode;
    	 if(durationVal){
    		 this.fadeArgs.duration = durationVal;
    	 }
    	
    	 //0.0 - fully transparent, 1.0 - fully opaque
         var opacity = dojo.style(this.domNode, "opacity");
    	 if (opacity > 0 && !dojo.hasClass(this.domNode, "lotusHidden")){
             dojo.fadeOut(this.fadeArgs).play();
         }
    	 this.clearInitValue();
    },

    /**
     * @override
     * Only required in non Activity Stream views.
     */
    getInitValue: function(){
    	var lh = lconn.homepage;
		var val = lh.global && !isNaN(lh.global[this.badgeGlobal]) && lh.global[this.badgeGlobal] > 0 
		? lh.global[this.badgeGlobal] : 0;
		
        this.updateBadgeNum(val);
    },
    
    /**
     * Remove the globals for this badge
     */
    clearInitValue: function(){
    	var lh = lconn.homepage;
    	if(lh.global && lh.global[this.badgeGlobal]){
    		lh.global[this.badgeGlobal] = 0;
    	}
    },
    
    /**
     * Update the current view config with the badge number. This will make the AS query
     * The Feeds with count=fetchValue
     * 
     * @param fetchValue
     */
    setFetchValue: function(fetchValue){
        if (typeof activityStreamConfig != 'undefined' && activityStreamConfig.views[this.view]){
            if (fetchValue > 0 && fetchValue <= this.maxFetchValue){
                activityStreamConfig.views[this.view].params.count = fetchValue;
            } else if (fetchValue > this.maxFetchValue){
                activityStreamConfig.views[this.view].params.count = this.maxFetchValue;
            } else {
                delete activityStreamConfig.views[this.view].params.count;
            }
        }
    }
});