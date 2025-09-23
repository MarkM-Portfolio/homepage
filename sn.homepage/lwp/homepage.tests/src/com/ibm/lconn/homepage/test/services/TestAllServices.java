/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)	
@Suite.SuiteClasses(
		{  
			com.ibm.lconn.homepage.test.services.ConfigurationServiceTest.class,
			com.ibm.lconn.homepage.test.services.TabServicesTest.class,
			com.ibm.lconn.homepage.test.services.UserServicesTest.class,
			com.ibm.lconn.homepage.test.services.ServerMetricsServicesTest.class,
			com.ibm.lconn.homepage.test.services.UIServicesTest.class,
			com.ibm.lconn.hpnews.test.service.impl.DatabaseSchemaVersionServiceTest.class,
			com.ibm.lconn.homepage.test.services.WidgetServicesTest.class,
			com.ibm.lconn.homepage.test.services.WidgetServicesIntegationTest.class,
			com.ibm.lconn.homepage.test.services.SecurityServicesTest.class,
			com.ibm.lconn.homepage.test.services.SystemMetricsTest.class,
			com.ibm.lconn.homepage.test.services.ComponentUrlInterpolatorTest.class,
			//com.ibm.lconn.homepage.test.services.BuildStatsTagTest.class,
			com.ibm.lconn.homepage.test.services.HomepageActionRequiredServiceTest.class
		}
)

/**
 * 
 * To test the service layer
 * 
 * @author Lorenzo Notarfonzo
 */
public class TestAllServices {

	
}
