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

dojo.provide("lconn.homepage.modules.common");

dojo.require("dojo.data.ItemFileReadStore");
dojo.require("dojo.data.ItemFileWriteStore");
dojo.require("dojox.uuid");
dojo.require("lconn.core.paletteOneUI.AddContentPane");
dojo.require("lconn.core.paletteOneUI.Palette");
dojo.require("lconn.core.paletteOneUI.PaletteContentPanel");
dojo.require("lconn.core.paletteOneUI.PaletteDataStoreBuilder");
dojo.require("lconn.core.paletteOneUI.PaletteList");
dojo.require("lconn.core.paletteOneUI.WidgetButton");
dojo.require("lconn.homepage.core.constants.DojoEvents");
dojo.require("lconn.homepage.palette.ChangeLayoutAddContentPane");
dojo.require("lconn.homepage.palette.ChangeLayoutButton");
dojo.require("lconn.homepage.palette.ChangeLayoutContentPanel");
dojo.require("lconn.homepage.palette.paletteWrapper");
dojo.require("com.ibm.social.as.nav.ASSideNav");

dojo.require("lconn.core.auth.whiteListHelper");
dojo.require("lconn.homepage.core.auth.formBasedUtility");
dojo.require("lconn.homepage.core.functions.openHelpWindow");
dojo.require("lconn.homepage.core.onload.userLinkGenerator");
dojo.require("lconn.homepage.core.widget.iwExtension.WidgetDescriptorStore");

dojo.require("lconn.homepage.metrics.pages");

dojo.require("lconn.homepage.utils.DateFormater");
dojo.require("lconn.homepage.utils.toolbar");

dojo.require("lconn.core.people");
dojo.require("com.ibm.lconn.layout.people");

dojo.require("com.ibm.social.as.config.ActivityStreamConfigProcessor");
