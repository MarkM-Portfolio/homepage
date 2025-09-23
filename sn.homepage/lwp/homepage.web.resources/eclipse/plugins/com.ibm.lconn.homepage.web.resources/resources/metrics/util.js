/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

dojo.provide("lconn.homepage.metrics.util");

/**
 * Object representing all the views on display in the Activity Stream page.
 * 
 * @author Robert Campion
 */

lconn.homepage.metrics.util = {
	/**
	 * Base Homepage metrics object
	 */
	baseHomepageMetrics: {
		source: "HOMEPAGE",
		userId: lconn.homepage.global.getUserId()
	},
	
	/**
	 * Build an item (page/view)
	 * @param itemName {String}
	 * @returns {Object} containing metrics item
	 */
	buildItem: function(itemName){
		return {
			metrics: this.buildMetricsItem(itemName)
		};
	},
	
	/**
	 * Build a metric item for an item
	 * @param itemName {String}
	 * @returns {Object} with id and type, mixed into base
	 */
	buildMetricsItem: function(itemName){
		return dojo.mixin({
			contentId: itemName,
			itemType: itemName
		}, this.baseHomepageMetrics);
	}
};