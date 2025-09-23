/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.core.functions.openHelpWindow");
dojo.require("lconn.core.config.services");
dojo.require("lconn.core.url");
dojo.require("lconn.core.help");

/* 
 * global openHelpWindow function that is used to open help windows. 
 */
openHelpWindow = function(topic) {
	hp_console_debug("lconn.homepage.core.functions.openHelpWindow arguments: %o", arguments);
	if (topic) {
		// Remove Help URL from topic
		var helpSvc = lconn.core.config.services.help;
		if (helpSvc) {
			var helpUrl = lconn.core.url.getServiceUrl(helpSvc).toString();
			if (topic.indexOf(helpUrl) === 0)
				topic = topic.substring(helpUrl.length);
		} // If help service not defined let it go as-is
	}
	lconn.core.help.launchHelp(topic);
}

/* 
 * global openDemoWindow function that is used to open demo videos. 
 */
openDemoWindow = function(url) {
	hp_console_debug("lconn.homepage.core.functions.openDemoWindow arguments: %o", arguments);
	// TODO: move default demo movie URL to lconn.core.help
	url = url || 'http://ibmtvdemo.edgesuite.net/software/lotus/uxid/connections/homepage45/homepage_demo.html';
	lconn.core.help.launchDemo(url);
}
