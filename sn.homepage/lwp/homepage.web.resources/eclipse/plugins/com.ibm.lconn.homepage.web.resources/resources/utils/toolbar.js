/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                              */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.utils.toolbar");

dojo.require("lconn.homepage.utils.SideNavigationAriaHelper");

/**
 * Homepage utility to build toolbar navigation, wrapping existing markup in
 * a Toolbar Aria helper.
 * 
 * @author Jim Antill
 */

lconn.homepage.utils.toolbar = {
		
	/**
	 * Build the toolbar by using the ARIA helpers.
	 * @param topNode - Top node of the navigation toolbar.
	 */
	build: function(topNode) {
		dojo.query("ul[role='toolbar']", topNode)
		.forEach(function(node) {
			new lconn.homepage.utils.SideNavigationAriaHelper(node);
		});
	}
}