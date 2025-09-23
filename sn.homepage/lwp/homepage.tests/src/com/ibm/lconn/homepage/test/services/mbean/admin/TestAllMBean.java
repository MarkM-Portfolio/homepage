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

package com.ibm.lconn.homepage.test.services.mbean.admin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)	
@Suite.SuiteClasses(
		{  
			MBeanExporterTest.class,
			HomepageMBeanFacadeTest.class,
			com.ibm.lconn.homepage.test.services.mbean.admin.HomepageMetricsServiceTest.class
		}
)

/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class TestAllMBean {

	
}
