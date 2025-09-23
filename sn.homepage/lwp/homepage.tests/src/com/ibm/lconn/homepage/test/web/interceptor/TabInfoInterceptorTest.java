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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.web.method.HandlerMethod;

import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.test.web.AbstractLoggedInUserTest;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.controller.admin.OpenAdminPageController;
import com.ibm.lconn.homepage.web.controller.widget.DisplayWidgetPageController;
import com.ibm.lconn.homepage.web.interceptor.TabInfoInterceptor;

public class TabInfoInterceptorTest extends AbstractLoggedInUserTest {

	/**
	 * ActionName as configured in struts-interceptor-tests
	 */
	private static final String ACTION_SUPPORT_NAME = "tabInfoUnderTestSupportAction";
	private static final String ACTION_SUPPORT_MOCK_NAME = "tabInfoUnderTestSupportActionWithMock";

	private static final String INTERCEPTOR_NAME = "tabInfoUnderTest";

	private static final String TAB_INSTANCE_PARAM = "tabInstanceId";
        
        protected MockHttpServletRequest mockRequest;
        protected MockHttpServletResponse mockResponse;

	@Before
	@Override
	public void setUp() throws Exception {
	    mockRequest = new MockHttpServletRequest();
	    mockResponse = new MockHttpServletResponse();
	    super.setUp();
	}

	/**
	 * Associated action does not extend TabInstanceAware interface, the
	 * interceptor should not do anything
	 * 
	 * @throws Exception
	 */


	@Test
	public void testNotTabInstanceAction() throws Exception {
		TabInfoInterceptor interceptorUnderTest = createInterceptor(null);

		assertNotNull(interceptorUnderTest);
			
		ITabServices mockTabSvc = EasyMock.createMock(ITabServices.class);
		EasyMock.expect(mockTabSvc.isTabEnabled(TabType.WIDGET.getDBValue())).andReturn(Boolean.TRUE);
			
		interceptorUnderTest.setTabServices(mockTabSvc);
			
		EasyMock.replay(mockTabSvc);
                
                HandlerMethod mockHandlerMethod = getHandlerMethodIsTabInstanceMock(false);
		boolean result = interceptorUnderTest.preHandle(mockRequest, mockResponse, mockHandlerMethod);

		EasyMock.verify(mockTabSvc);
		
		assertEquals(true, result);
			
	}

	/**
	 * Testing that our setTabInstance is called with the correct params in our
	 * support action. <br />
	 * Case1: the tab instance id is passed as request param.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetTabInstanceWithRequestParam() throws Exception {

		final String expectedTabInstanceId = "tabInstance001";
		final String internalUserId = "user001";

		TabInfoInterceptor interceptorUnderTest = createInterceptor(internalUserId);

		ITabServices mockTabSvc = EasyMock.createMock(ITabServices.class);
		
		EasyMock.expect(mockTabSvc.isTabEnabled(TabType.WIDGET.getDBValue())).andReturn(Boolean.TRUE);
		
		interceptorUnderTest.setTabServices(mockTabSvc);
		
		mockRequest.setParameter(TAB_INSTANCE_PARAM, expectedTabInstanceId);

		EasyMock.replay(mockTabSvc);

                HandlerMethod mockHandlerMethod = getHandlerMethodIsTabInstanceMock(true);
		boolean result = interceptorUnderTest.interceptInternal(mockRequest, mockResponse, mockHandlerMethod);
                
                EasyMock.verify(mockTabSvc);
                
                assertEquals(true, result);
	}

	/**
	 * Testing that our setTabInstance is called with the correct params in our
	 * support action. <br />
	 * Case2: the tab instance id is NOT passed as request param, the instance
	 * id should be determined from the logged user + tab type configured in
	 * struts config file
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSetTabInstanceWithoutRequestParam() throws Exception {
		final String expectedTabInstanceId = "tabInstance001";
		final String internalUserId = "user001";

		TabInfoInterceptor interceptorUnderTest = createInterceptor(internalUserId);

		ITabServices mockTabSvc = EasyMock.createMock(ITabServices.class);
		EasyMock.expect(mockTabSvc.getTabInstanceId(internalUserId, TabType.UPDATE)).andReturn(expectedTabInstanceId);
		EasyMock.expect(mockTabSvc.isTabEnabled(TabType.WIDGET.getDBValue())).andReturn(Boolean.TRUE);
		
		interceptorUnderTest.setTabServices(mockTabSvc);
		EasyMock.replay(mockTabSvc);

                HandlerMethod mockHandlerMethod = getHandlerMethodIsTabInstanceMock(true);
		boolean result = interceptorUnderTest.interceptInternal(mockRequest, mockResponse, mockHandlerMethod);
                
		EasyMock.verify(mockTabSvc);
                assertEquals(true, result);
	}
        
        /**
         * Get reference to interceptor configured in struts xml file. Note that
         * this method also registers the interceptor in the mock config environment
         * (proxy.execute will execute the interceptor)
         * 
         * @param internalUserId
         * @return
         * @throws Exception
         */
        private TabInfoInterceptor createInterceptor(String internalUserId)
                        throws Exception {
                TabInfoInterceptor interceptorUnderTest = new TabInfoInterceptor();

                assertNotNull(interceptorUnderTest);
                
                ResourceBundle mockResourceBundle = EasyMock.createNiceMock(ResourceBundle.class);
                EasyMock.expect(mockResourceBundle.getString((String) EasyMock.anyObject())).andReturn("foo");
                EasyMock.expect(mockResourceBundle.getString((String) EasyMock.anyObject(), (String) EasyMock.anyObject())).andReturn("bar");

                // set internal user id (normally set by UserInfoInterceptor in running
                // WAS environment)
                mockRequest.getSession().setAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID, internalUserId);

                interceptorUnderTest.setWebResourceBundle((mockResourceBundle) );
                EasyMock.replay(mockResourceBundle);

                return interceptorUnderTest;
        }

        private HandlerMethod getHandlerMethodIsTabInstanceMock(boolean isTabInstanceBasedHandlerMethod) {
            
            try {
            if (isTabInstanceBasedHandlerMethod) {
                DisplayWidgetPageController instance = new DisplayWidgetPageController();
                return new HandlerMethod(instance, instance.getClass().getMethod("execute", Model.class, HttpServletRequest.class, HttpServletResponse.class, String.class));
            } else {
                OpenAdminPageController instance = new OpenAdminPageController();
                return new HandlerMethod(instance, instance.getClass().getMethod("execute", Model.class, HttpServletRequest.class, HttpServletResponse.class));
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

}