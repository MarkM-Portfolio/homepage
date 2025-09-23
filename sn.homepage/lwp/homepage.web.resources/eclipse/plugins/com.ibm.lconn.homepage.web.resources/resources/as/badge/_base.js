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
dojo.provide("lconn.homepage.as.badge._base");

dojo.require("dijit._Widget");
dojo.require("dijit._Templated");

/**
 * The base widget for showing a badge in the SideNavigation widget. 
 * @author Claudio Procida <procidac@ie.ibm.com>
 */

dojo.declare("lconn.homepage.as.badge._base", [dijit._Widget, dijit._Templated],
{
   /* CSS class name to be applied to the badge. Subclassers must override this. */
   badgeClass: "",

   templateString: "<span class='${badgeClass}'></span>"

});