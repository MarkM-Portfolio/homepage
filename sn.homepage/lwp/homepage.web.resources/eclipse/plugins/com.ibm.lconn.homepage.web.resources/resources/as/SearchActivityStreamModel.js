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

dojo.provide("lconn.homepage.as.SearchActivityStreamModel");

/**
 * Custom model for the Search Activity Stream. Instead of making an XHR
 * request, it calls into an external function that fetches data and returns a
 * JSON response.
 */

dojo.declare(
"lconn.homepage.as.SearchActivityStreamModel", null,
{
	/**
	 * Get a new stream of data based on the options passed.
	 * @param options {
	 *   newsUrl: {String} URL that should be called to fetch data
	 *   onLoad: {Function} Callback function that should be passed JSON data
	 * }
	 */
	getStream: function(options){
		// Insert any code you wish here to load Activity Stream data.
		// 'searchHelper' is just a sample global JS object.
		searchHelper.getStreamData(options);
	}
});
