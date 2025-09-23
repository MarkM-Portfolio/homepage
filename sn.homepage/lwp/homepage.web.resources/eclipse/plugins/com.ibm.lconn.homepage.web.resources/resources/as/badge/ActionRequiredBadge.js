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
dojo.provide("lconn.homepage.as.badge.ActionRequiredBadge");

dojo.require("lconn.homepage.as.badge._base");
dojo.require("com.ibm.social.as.constants.events");

/**
 * The badge widget for showing the badge number of items in the action required list. 
 * @author Rui Qi Shi
 */

dojo.declare("lconn.homepage.as.badge.ActionRequiredBadge", lconn.homepage.as.badge._base,
{	
	badgeClass: "lotusUnreadBadge",
	
	// The actionRequired badge number
	badgeNum: "",

	// Event name for update badge number
	updateBadgeEventName: com.ibm.social.as.constants.events.ACTIONREQUIREDBADGEUPDATE,

	// Event name for decrease badge number
	decreaseBadgeEventName: com.ibm.social.as.constants.events.ACTIONREQUIREDBADGEDECREASE,
	
	badgeLoadEvent: com.ibm.social.as.constants.events.BADGE_DATA_LOADED,
	
	populateEventName: com.ibm.social.as.constants.events.POPULATE,
	
	initalized: false,

	/**
	 * Called after the widget is rendered in the UI.
	 */
	postCreate: function(){
        dojo.addClass(this.domNode,"lotusHidden");
        this.subscribe(this.populateEventName, dojo.hitch(this, "getInitValue"));
        this.setupSharedSubscriptions();
	},
	
	setupSharedSubscriptions: function(){	
		this.subscribe(this.badgeLoadEvent, dojo.hitch(this, "getInitValue"));
		this.subscribe(this.updateBadgeEventName,dojo.hitch(this,"updateBadgeNum"));
		this.subscribe(this.decreaseBadgeEventName,dojo.hitch(this,"decreaseBadgeNum"));		
	},
	
	/**
	 * Decrease the badge number when remove item from actionRequired
	 */
	decreaseBadgeNum: function(){
		if(this.badgeNum>0){
			this.badgeNum--;
			// If badgeNum come to 0, set it to empty string for hiding the badge node
			if(this.badgeNum == 0){
				this.badgeNum = "";
				dojo.addClass(this.domNode,"lotusHidden");
			}
			this.domNode.innerHTML = this.badgeNum;
		}
	},
	
	/**
	 * Update the badge number of actionRequired
	 */
	updateBadgeNum: function(totalNum) {
		this.badgeNum = totalNum?totalNum:0;
		if(this.badgeNum == 0){
			this.badgeNum = "";
			dojo.addClass(this.domNode,"lotusHidden");
        }else{
			dojo.removeClass(this.domNode,"lotusHidden");
		}
		this.domNode.innerHTML = this.badgeNum;
	},
	
	/**
	 * Get the global action required total number for this user, if available.
	 * @returns {Int}
	 */
	getInitValue: function(){
		if(!this.initialized){
			var lh = lconn.homepage;
			var val = lh.global && !isNaN(lh.global.actionRequiredTotal) && lh.global.actionRequiredTotal > 0 
			? lh.global.actionRequiredTotal : 0;
			
	        this.updateBadgeNum(val);
	        this.initialized = true;			
		}
	}
});