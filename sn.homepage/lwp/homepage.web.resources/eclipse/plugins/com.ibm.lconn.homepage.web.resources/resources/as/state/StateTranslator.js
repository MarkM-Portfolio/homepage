/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2011, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.as.state.StateTranslator");

/**
 * Translates old LC 3.0 into Connections 4.0 states.
 * @author Robert Campion
 */

dojo.declare("lconn.homepage.as.state.StateTranslator", null,
{
	// Map containing translations
	translateMap: null,
	
	//String literals for legacy myStream state
	imFollowing : "imFollowing",
	statusUpdates : "statusUpdates",
	discover : "discover",
		
	constructor: function(){
		// For completeness, and to avoid omissions in future updates,
		// all possible 3.x fragments are listed (with their 4.0 counterpart),
		// however where they match they are commented out.
		this.translateMap = {
			"status/follow-network": "statusUpdates/networkAndFollow",
			"status/network": "statusUpdates/myNetwork",
			"status/following": "statusUpdates/people", 
			"status/all": "statusUpdates/all",
			"status/myupdates": "statusUpdates/myUpdates",
			
			"newsfeed": "myStream/imFollowing/all",
			"newsfeed/responses": "myNotifications/forme/all",
			"newsfeed/profiles": "myStream/imFollowing/people",
			"newsfeed/activities": "myStream/imFollowing/activities",
			"newsfeed/blogs": "myStream/imFollowing/blogs",
			"newsfeed/communities": "myStream/imFollowing/communities",
			"newsfeed/files": "myStream/imFollowing/files",
			"newsfeed/forums": "myStream/imFollowing/forums",
			"newsfeed/wikis": "myStream/imFollowing/wikis",
			"newsfeed/tags": "myStream/imFollowing/tags",
			
			"notifications": "myNotifications/forme/all",
			"notifications/sent": "myNotifications/fromme/all",
			
			"discover": "discover/all",
//			"discover/profiles": "discover/profiles",
//			"discover/activities": "discover/activities",
//			"discover/blogs": "discover/blogs",
			"discover/dogear": "discover/bookmarks",
//			"discover/communities": "discover/communities",
//			"discover/files": "discover/files",
//			"discover/forums": "discover/forums",
//			"discover/wikis": "discover/wikis",

			"saved": "saved/saved/all",
			"saved/dogear": "saved/saved/bookmarks",
			"saved/all" : "saved/saved/all",
			"saved/statusUpdates" : "saved/saved/statusUpdates",
			"saved/activities" : "saved/saved/activities",
			"saved/blogs" : "saved/saved/blogs",
			"saved/bookmarks" : "saved/saved/bookmarks",
			"saved/communities" : "saved/saved/communities",
			"saved/files" : "saved/saved/files",
			"saved/forums" : "saved/saved/forums",
			"saved/profiles" : "saved/saved/profiles",
			"saved/wikis" : "saved/saved/wikis",

			"actionRequired/all" : "actionRequired/actionRequired/all",
			"actionRequired/statusUpdates" : "actionRequired/actionRequired/statusUpdates",
			"actionRequired/activities" : "actionRequired/actionRequired/activities",
			"actionRequired/blogs" : "actionRequired/actionRequired/blogs",
			"actionRequired/bookmarks" : "actionRequired/actionRequired/bookmarks",
			"actionRequired/communities" : "actionRequired/actionRequired/communities",
			"actionRequired/files" : "actionRequired/actionRequired/files",
			"actionRequired/forums" : "actionRequired/actionRequired/forums",
			"actionRequired/profiles" : "actionRequired/actionRequired/profiles",
			"actionRequired/wikis" : "actionRequired/actionRequired/wikis",

			"myNotifications/all/forme":"myNotifications/forme/all",
			"myNotifications/activities/forme":"myNotifications/forme/activities",
			"myNotifications/blogs/forme":"myNotifications/forme/blogs",
			"myNotifications/bookmarks/forme":"myNotifications/forme/bookmarks",
			"myNotifications/communities/forme":"myNotifications/forme/communities",
			"myNotifications/files/forme":"myNotifications/forme/files",
			"myNotifications/forums/forme":"myNotifications/forme/forums",
			"myNotifications/wikis/forme":"myNotifications/forme/wikis",
			"myNotifications/profiles/forme":"myNotifications/forme/profiles",
			
			"myNotifications/all/fromme":"myNotifications/fromme/all",
			"myNotifications/activities/fromme":"myNotifications/fromme/activities",
			"myNotifications/blogs/fromme":"myNotifications/fromme/blogs",
			"myNotifications/bookmarks/fromme":"myNotifications/fromme/bookmarks",
			"myNotifications/communities/fromme":"myNotifications/fromme/communities",
			"myNotifications/files/fromme":"myNotifications/fromme/files",
			"myNotifications/forums/fromme":"myNotifications/fromme/forums",
			"myNotifications/wikis/fromme":"myNotifications/fromme/wikis",
			"myNotifications/profiles/fromme":"myNotifications/fromme/profiles"
		};
	},
	
	/**
	 * Attempts to translate a state passed in to a newer state.
	 * @param oldState (String) 3.0 hash state
	 * @returns (String) 4.0 hash state or undefined if not available.
	 */
	translateState: function(oldState){
		return this.translateMap[oldState];
	},
	
	/**
	 * Check for legacy myStream state and returns true if matches
	 * @param stateValue (String) 
	 * @returns (boolean)
	 */
	checkForLegacyMyStreamState: function(stateValue) {

		if(stateValue && (stateValue.match("^"+this.imFollowing) || stateValue.match("^"+this.statusUpdates) 
				|| stateValue.match("^"+this.discover))){
			return true;
		}else{
			return false;
		}
	}
});