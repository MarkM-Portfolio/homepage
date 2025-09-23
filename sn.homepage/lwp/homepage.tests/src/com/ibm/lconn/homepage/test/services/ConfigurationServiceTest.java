/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

public class ConfigurationServiceTest extends SpringContextAwareTestCase {

	private static String CLASSNAME = ConfigurationServiceTest.class.getName();
	@Autowired
	private IConfigurationService configurationService;
	
	
	
	@Test
	public void testGetComponentUrl() {
		try {
			assertEquals("https://junit.dyn.webahead.ibm.com:9443/profiles", 
						 configurationService.getComponentUrl(Component.PROFILES.getName(), true));
			assertEquals("https://junit.dyn.webahead.ibm.com:9443/profiles/ibm_semanticTagServlet",
						 configurationService.getComponentUrl(Component.PERSON_TAG.getName(), true));			
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}

	@Before
	public void init() throws Exception {
		this.configurationService = (IConfigurationService) getBean("configurationService");
		refreshDB(CLASSNAME, false);		
	}

	@Override
	protected String[] getDBTableNames() {
		return null;
	}
}
