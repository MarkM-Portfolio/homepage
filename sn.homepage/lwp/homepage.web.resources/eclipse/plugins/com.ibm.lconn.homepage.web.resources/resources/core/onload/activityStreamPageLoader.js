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

dojo.provide("lconn.homepage.core.onload.activityStreamPageLoader");

dojo.require("dojo.parser");
dojo.require("lconn.homepage.as.message.MessageContainer");
dojo.require("com.ibm.social.as.nav.ASHeader");
dojo.require("com.ibm.social.as.util.xhr.XhrHandler");

/**
 * Loads up the Activity Stream page and entities.
 */

//Define the global abstractHelper
var abstractHelper = new com.ibm.social.as.util.AbstractHelper();

dojo.addOnLoad(function() {
	// Parse the left hand side column (thereby loading the side nav)
	dojo.parser.parse(dojo.byId("lotusColLeft"));
	
	// Create the globe message container
	new lconn.homepage.as.message.MessageContainer({}, dojo.byId("messageContainer"));
		
	if(window.activityStreamConfig && window.activityStreamConfig.userInfo
			&& window.activityStreamConfig.userInfo.org !== "0" && !window.activityStreamConfig.userInfo.isExternal){
		new com.ibm.social.as.nav.ASHeader({},dojo.byId("activityStreamHeader"));
	}
	// Load up the state persistence handler
	window.stateHandler = new lconn.homepage.as.state.StateHandler();
	
	// tell the xhr handler that normal xhr is fine
	com.ibm.social.as.util.xhr.XhrHandler.init();
	
	// Create the ActivityStream dijit, passing the config
	new com.ibm.social.as.ActivityStream({
		configObject: window.activityStreamConfig,
		domNode: dojo.byId("activityStream")
	});
 
	//arm twisted late in Nov - putting in special handling to remove the 
	//Sharebox for Guest users - please remove once Visitor model in place
	if(window.activityStreamConfig && window.activityStreamConfig.userInfo
			&& (window.activityStreamConfig.userInfo.org !== "0" && !window.activityStreamConfig.userInfo.isExternal)){
		// Create the sharebox.
		new com.ibm.social.sharebox.ContextualSharebox({
			newsSvcUrl : lconn.core.url.getServiceUrl(lconn.core.config.services.news),
			context : {
				resourceId : userid,
				resourceType : "person"
			}
		}, dijit.byId("activityStream").shareboxNode );
	}
});