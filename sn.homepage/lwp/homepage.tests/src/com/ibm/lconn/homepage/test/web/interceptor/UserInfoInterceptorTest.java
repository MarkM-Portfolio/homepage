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

package com.ibm.lconn.homepage.test.web.interceptor;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.test.web.BaseStrutsSpringTestCase;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.interceptor.UserInfoInterceptor;

public class UserInfoInterceptorTest extends BaseStrutsSpringTestCase {

	/**
	 * ActionName as configured in struts-interceptor-tests
	 */
	private static final String ACTION_SPPORT_NAME = "userInfoUnderTestSupportAction";
	private static final String INTERCEPTOR_NAME = "userInfoUnderTest";

	private IConfigurationService mockConfigurationService;
	private ResourceBundle mockWebResourceBundle;
	
	@Before
	@Override
	public void setUp() throws Exception {
		setMockConfigurationService(createMock(IConfigurationService.class));
		setMockResourceBundle(createMock(ResourceBundle.class));
		super.setUp();
	}
	
	public void setMockConfigurationService(IConfigurationService mockConfigurationService) {
		this.mockConfigurationService = mockConfigurationService;
	}

	public IConfigurationService getMockConfigurationService() {
		return mockConfigurationService;
	}
	
	public ResourceBundle getMockResourceBundle() {
		return mockWebResourceBundle;
	}
	
	public void setMockResourceBundle(ResourceBundle bundle) {
		this.mockWebResourceBundle = bundle;
	}

	/**
	 * Basic test of init() method
	 * 
	 * @throws Exception
	 */
	@Test
	public void testInit() throws Exception {

		UserInfoInterceptor interceptorUnderTest = new UserInfoInterceptor();

		assertNotNull(interceptorUnderTest);
				
		expect(getMockConfigurationService().getExposeEmail()).andReturn(true);
		expect(getMockConfigurationService().getWpiEnabled()).andReturn(false);		
		replay(getMockConfigurationService());
		
		interceptorUnderTest.setConfigurationService(getMockConfigurationService());
		
		expect(getMockResourceBundle().getString("info.wpi.off")).andReturn("BLAH");		
		replay(getMockResourceBundle());
		
		interceptorUnderTest.setWebResourceBundle(getMockResourceBundle());

		interceptorUnderTest.init();

		verify(getMockConfigurationService());
	}

	/**
	 * Test whether session attributes are correctly set from the DAO layer
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testUserLoggedInNotInSession() throws Exception {

		final String expectedRemoteUser = "vincent007";
		final User expectedUser = new User();
		expectedUser.setId("007");
		expectedUser.setExternalId("external-007");
		expectedUser.setDisplayname("vincent");

		MockHttpServletRequest mockRequest = new MockHttpServletRequest();
		MockHttpServletResponse mockResponse = new MockHttpServletResponse();
		mockRequest.setRemoteUser(expectedRemoteUser);

		HttpSession session = mockRequest.getSession();

		UserInfoInterceptor interceptorUnderTest = new UserInfoInterceptor();

		IUserServices mockUserSvc = createMock(IUserServices.class);

		expect(mockUserSvc.getUserLoginName(expectedRemoteUser)).andReturn(expectedUser);
		replay(mockUserSvc);
		
		interceptorUnderTest.setUserServices(mockUserSvc);
		
		expect(getMockConfigurationService().getExposeEmail()).andReturn(true);
		expect(getMockConfigurationService().getWpiEnabled()).andReturn(true);
		replay(getMockConfigurationService());
		
		interceptorUnderTest.setConfigurationService(getMockConfigurationService());
		
		expect(getMockResourceBundle().getString("info.wpi.on")).andReturn("BLAH").anyTimes();
		expect(getMockResourceBundle().getString("error.action.error.general")).andReturn("BLAH").anyTimes();		
		replay(getMockResourceBundle());
		
		interceptorUnderTest.setWebResourceBundle(getMockResourceBundle());

		interceptorUnderTest.preHandle(mockRequest, mockResponse, null); // have to invoke manually for tests

		verify(mockUserSvc);

		// check if session attributes were correctly set by the interceptor
		// TODO: test deferent behaviors with email enabled
		assertEquals(expectedRemoteUser, session
				.getAttribute(GlobalSessionConstants.USER_LOGIN_ID));
		assertEquals("vincent", session
				.getAttribute(GlobalSessionConstants.USER_INFO_NAME));
		assertEquals("007", session
				.getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID));
		assertEquals("external-007", session
				.getAttribute(GlobalSessionConstants.USER_INFO_EXTERNAL_ID));

	}
}

