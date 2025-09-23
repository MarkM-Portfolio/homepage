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

dojo.provide("lconn.homepage.modules.activityStream");

dojo.require("lconn.homepage.modules.common");


dojo.require("com.ibm.lconn.gadget.container.iContainer2");

dojo.require("com.ibm.social.as.util.AbstractHelper");
dojo.require("com.ibm.social.as.util.hashtag.HashtagUtil");
dojo.require("lconn.homepage.as.state.StateHandler");

dojo.require("com.ibm.social.as.ActivityStream");
dojo.require("com.ibm.social.as.nav.ASSideNav");
dojo.require("com.ibm.social.as.nav.ASHeader");
dojo.require("com.ibm.social.as.ee.EEManager");

// Load all the extensions
dojo.require("lconn.homepage.modules.extension");

dojo.require("lconn.homepage.core.onload.activityStreamPageLoader");

dojo.require("com.ibm.social.sharebox.ContextualSharebox");


//Load layout code
dojo.require("lconn.homepage.core.layout.WidgetLayoutModel");
dojo.require("lconn.homepage.core.layout.WidgetModelUpdater");
dojo.require("lconn.homepage.core.layout.WidgetPersister");
dojo.require("lconn.homepage.core.init.InitScriptsUpdatesPage");
dojo.require("com.ibm.social.as.util.hashtag.ASHashtagUtil");
//This is only required as-needed - so pull it into module to include in initial load
dojo.require("com.ibm.social.as.util.xhr.DojoXhrHandler");