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
 * ActionRequired badge tests.
 */

dojo.provide("lconn.homepage.tests.unit.as.badge.testActionRequiredBadge");

dojo.require("lconn.homepage.as.badge.ActionRequiredBadge");

dojo.require("com.ibm.social.test.scripts.results.ResultCollector");
dojo.require("com.ibm.social.test.testUtil");

// TODO : kill all globals
if (!(lconn.homepage)) lconn.homepage={};
if (!(lconn.homepage.global)) lconn.homepage.global={};

var eventConstants = com.ibm.social.as.constants.events;
com.ibm.social.test.testUtil.registerGroup("unit.as.badge.testActionRequiredBadge", 
	[
		{	name: "testGetGlobalActionRequiredTotal",
		 	description: "Test that the number of action required is obtained successfully",
			runTest: function(){
				// Test the function to get the Action Required total in error and success scenarios.
				lconn.homepage.global.actionRequiredTotal="invalid";
				doh.is(this.badgeObject.getGlobalActionRequiredTotal(), 0);
				
				lconn.homepage.global.actionRequiredTotal = -1;
				doh.is(this.badgeObject.getGlobalActionRequiredTotal(), 0);

				lconn.homepage.global.actionRequiredTotal = 1;
				doh.is(this.badgeObject.getGlobalActionRequiredTotal(), 1);
			}
		},
		{	
			name: "testEventSubscription",
		 	description: "Test that the number of action required is obtained successfully",
		 	subscribeSpy: null,
		 	// override for custom init
			setUp: function(){
				// We want to test that after construction the events are subscribed to.
				// Spy on the subscribe method so we can detect what's been subscribed to.
				this.subscribeSpy = this.spyOn(this.badgeClass.prototype, "subscribe");
			
				this.badgeObject = new this.badgeClass();
			},
			runTest: function(){
				// Make sure that subscription of the events is recorded.
				doh.t(this.subscribeSpy.calledWith(eventConstants.ACTIONREQUIREDBADGEUPDATE));
				doh.t(this.subscribeSpy.calledWith(eventConstants.ACTIONREQUIREDBADGEDECREASE));
			}
		},
		{
			name: "testBadgeDecreaseNum",
			description: "Tests the routine to decrease the badge number",
			runTest: function() {
				this.badgeObject.badgeNum=2;
				
				this.badgeObject.decreaseBadgeNum();
				doh.is(this.badgeObject.badgeNum, 1);
				doh.is(this.badgeObject.domNode.innerHTML, "1");
				
				this.badgeObject.decreaseBadgeNum();
				doh.is(this.badgeObject.badgeNum, "");
				doh.is(this.badgeObject.domNode.innerHTML, "");
			
				this.badgeObject.decreaseBadgeNum();
				doh.is(this.badgeObject.badgeNum, "");
				doh.is(this.badgeObject.domNode.innerHTML, "");
			}
		},
		{
			name: "testUpdateBadgeNum",
			description: "Tests the routine to update the badge number",
			runTest: function() {
				this.badgeObject.updateBadgeNum("23");
				doh.is(this.badgeObject.badgeNum, 23);
				doh.is(this.badgeObject.domNode.innerHTML, "23");

				this.badgeObject.updateBadgeNum("50");
				doh.is(this.badgeObject.badgeNum, 50);
				doh.is(this.badgeObject.domNode.innerHTML, "50");
			}
		}
	],
	function (){},
	function(){},
	null,
	{
		badgeObject: null,
		badgeClass: lconn.homepage.as.badge.ActionRequiredBadge,
		setUp: function(){
			this.badgeObject = new this.badgeClass();
		},
		tearDown: function() {
			this.badgeObject.destroyRecursive();
		}
	}
);
