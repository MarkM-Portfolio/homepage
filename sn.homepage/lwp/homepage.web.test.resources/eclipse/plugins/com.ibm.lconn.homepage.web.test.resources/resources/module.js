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

/**
 * Overall test suite module called to run all Homepage tests
 */

dojo.provide("lconn.homepage.tests.module");

try{
	dojo.require("com.ibm.social.test.scripts.results.ResultCollector");
	dojo.require("com.ibm.social.test.scripts.results.ResultConnects");
	
	/*
	 * Child modules to be included in the overall test suite should be added here
	 */
	dojo.require("lconn.homepage.tests.integration.as.module");
	dojo.require("lconn.homepage.tests.unit.as.module");
}catch(e){
	doh.debug(e);
}
