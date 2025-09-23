/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.web;

import static com.ibm.lconn.homepage.test.data.util.DAOUnitTestConstants.TEST_CONFIG_FILES_SYSTEM_PROPERTY_NAME;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.junit.BeforeClass;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.ibm.lconn.homepage.test.TestHome;

/**
 * Base class for testing Spring controllers. Initialize Spring
 * WebApplicationContext
 */
public abstract class BaseStrutsSpringTestCase {

	private static final String CONTEXT_XML = "test/spring/homepage/web/TestWebContext.xml";

	private ServletContext servletContext;
	protected WebApplicationContext context;

	@BeforeClass
	public static void initSpring() {		
		System.setProperty(TEST_CONFIG_FILES_SYSTEM_PROPERTY_NAME, TestHome.getTestConfigFilesDir());		
	}
	
	/**
	 * Initialize Struts and Spring context
	 */
	protected void setUp() throws Exception {		
		initSpringWebContext();
	}

	private void initServletContext() {
		MockServletContext servletContext = new MockServletContext();
		servletContext.setContextPath("");
		servletContext.addInitParameter(ContextLoader.CONFIG_LOCATION_PARAM,
				CONTEXT_XML);

		this.servletContext = servletContext;
	}

	protected ServletContext getServletContext() {

		if (servletContext == null) {
			initServletContext();
		}

		return servletContext;
	}

	private void initSpringWebContext() {
		context = (new ContextLoader())
				.initWebApplicationContext(getServletContext());
	}
}