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

import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.TEST_CONFIG_FILES_SYSTEM_PROPERTY_NAME;
import static org.junit.Assert.assertNotNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.lconn.homepage.test.TestHome;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

public class SpringBaseTest extends SpringContextAwareTestCase {
	
	protected static ClassPathXmlApplicationContext context;
		
	@BeforeClass
	public static void initSpring() {
		
		System.setProperty(TEST_CONFIG_FILES_SYSTEM_PROPERTY_NAME, TestHome.getTestConfigFilesDir());
		
		String[] configLocations = {"test/spring/homepage/database/TestDatabaseContext.xml","test/spring/homepage/services/TestServicesContext.xml" };
		context = new ClassPathXmlApplicationContext(configLocations);
		assertNotNull(context);
	}
	
	@AfterClass
	public static void destroySpring() {
		if(context!=null){
			context.destroy();
			context=null;
		}
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getDBTableNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
