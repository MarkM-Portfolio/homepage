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
package com.ibm.lconn.homepage.test.sandbox;

import com.ibm.lconn.hpnews.test.TestHomes;
import com.ibm.lconn.hpnews.test.sandbox.HPNewsSpringContextAwareTestCase;

/**
 */
public abstract class SpringContextAwareTestCase extends HPNewsSpringContextAwareTestCase {

	@Override
	public String[] getContxtLocations() {
		String[] locations = {
			"spring/homepage/services/ServicesContext.xml",	
			"test/spring/homepage/services/TestServiceContext.xml",
			"test/spring/home-news/database/TestDatabaseContext.xml",
			"test/spring/home-news/database/TestDatabaseContext" + TestHomes.getDbVendor() + ".xml"};
		return locations;
	}
			
	
	
	
	
}
