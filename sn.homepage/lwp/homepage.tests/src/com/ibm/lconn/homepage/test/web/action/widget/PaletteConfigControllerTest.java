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

package com.ibm.lconn.homepage.test.web.action.widget;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.homepage.test.web.AbstractWidgetControllerTest;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.controller.palette.PaletteConfigController;

import junit.framework.Assert;

/**
 * Tests for the PaletteConfigController (returning list of available widgets)
 */
public class PaletteConfigControllerTest extends AbstractWidgetControllerTest {

    /**
     * Name of request param containing tab instance id sent (from the client)
     */
    private static final String TAB_INSTANCE_ID_PARAM = "tabInstanceId";

    private static final String MOCK_USER_INTERNAL_ID = "111";

    /*
     * private IWidgetServices mockWidgetSvc; private IConfigurationService mockConfigSvc;
     */

    @Before
    public void setUp() throws Exception {
        super.setUp();

        /*
         * setMockWidgetServices(createMock(IWidgetServices.class)); setMockConfigServices(createMock(IConfigurationService.class)); setMockUserServices(createMock(IUserServices.class));
         */

    }

    private void updateMockRequestWithSessionData(MockHttpServletRequestBuilder requestBuilder) {
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_INTERNAL_ID, MOCK_USER_INTERNAL_ID);
    }

    /*
     * public IWidgetServices getMockWidgetSvc() { return mockWidgetSvc; }
     * 
     * public void setMockWidgetSvc(IWidgetServices mockWidgetSvc) { this.mockWidgetSvc = mockWidgetSvc; }
     * 
     * public IConfigurationService getMockConfigSvc() { return mockConfigSvc; }
     * 
     * public void setMockConfigSvc(IConfigurationService mockConfigSvc) { this.mockConfigSvc = mockConfigSvc; }
     */

    @Test
    public void testActionConfiguration() throws Exception {
        PaletteConfigController servlet = new PaletteConfigController();
        Assert.assertNotNull(servlet);
        Assert.assertTrue(servlet instanceof PaletteConfigController);
    }

    /**
     * Test whether the service layer is invoked correctly when an admin user hits the action and test mode (disabled widgets) enabled . Also ensure the action does not throw any exception if the
     * widget svc return null values (defensive programming)
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testPaletteAdminRole() throws Exception {
        final String expectedTabInstanceId = "myDummyTestTabId";
        final boolean expectedIncludeDisabled = true;

        final PaletteConfigController servlet = new PaletteConfigController();
        servlet.setAdminRoleForTest();

        // widget service should be called to include disabled widgets for the
        // admin
        // tab instance id sent to the action should be forwarded to the widget
        // service
        expect(mockWidgetServices.getAvailableWidgetsByTabInstanceId(expectedTabInstanceId, Locale.ENGLISH, expectedIncludeDisabled)).andReturn(null);

        expect(mockWidgetServices.getAvailableWidgetsTree(expectedTabInstanceId, Locale.ENGLISH, expectedIncludeDisabled)).andReturn(null);

        expect(mockUserServices.isAdminTestModeActive(MOCK_USER_INTERNAL_ID)).andReturn(true);

        getMockUrlNormalizer().translateUrls(Collections.EMPTY_LIST, false);
        getMockUrlNormalizer().translateUrls(Collections.EMPTY_MAP, false);

        servlet.setWidgetServices(mockWidgetServices);
        servlet.setUrlNormalizer(getMockUrlNormalizer());
        servlet.setUserServices(mockUserServices);

        replay(mockWidgetServices);
        replay(getMockUrlNormalizer());
        replay(mockUserServices);
        
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/getPaletteConfig.action");
        this.updateMockRequestWithSessionData(requestBuilder);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, expectedTabInstanceId);
        requestBuilder.locale(Locale.ENGLISH);

        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
        
        verify(mockWidgetServices);
        verify(getMockUrlNormalizer());
        verify(mockUserServices);
    }

    /**
     * Test whether the service layer is invoked correctly when a NON admin user hits the action.
     * 
     * @throws Exception
     */
    @Test
    public void testPaletteNonAdminRole() throws Exception {
        final String expectedTabInstanceId = "myDummyTestTabId";
        final boolean expectedIncludeDisabled = false;

        final PaletteConfigController servlet = new PaletteConfigController();

		// CNXSERV-12778 - likely unnecessary now 
        // not an admin
        // mockRequest.addUserRole("dummyRole");

        // widget service should be called to NOT include disabled widgets for
        // the admin
        // tab instance id sent to the action should be forwarded to the widget
        // service
        expect(mockWidgetServices.getAvailableWidgetsByTabInstanceId(expectedTabInstanceId, Locale.ENGLISH, expectedIncludeDisabled)).andReturn(null);

        expect(mockWidgetServices.getAvailableWidgetsTree(expectedTabInstanceId, Locale.ENGLISH, expectedIncludeDisabled)).andReturn(null);

        getMockUrlNormalizer().translateUrls(Collections.<Widget> emptyList(), false);
        getMockUrlNormalizer().translateUrls(Collections.<String, WidgetCategory> emptyMap(), false);

        servlet.setWidgetServices(mockWidgetServices);
        servlet.setConfigurationService(mockConfigurationService);
        servlet.setUrlNormalizer(getMockUrlNormalizer());

        replay(mockWidgetServices);
        replay(mockConfigurationService);
        replay(getMockUrlNormalizer());
        replay(mockUserServices);
        
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/getPaletteConfig.action");
        this.updateMockRequestWithSessionData(requestBuilder);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, expectedTabInstanceId);
        requestBuilder.locale(Locale.ENGLISH);

        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
        
        verify(mockWidgetServices);
        verify(mockConfigurationService);
        verify(getMockUrlNormalizer());
        verify(mockUserServices);
    }

    /**
     * Test values "returned" by the action (and passed to the Result object in a real scenario) getAvailWidget and getWidgetTree should be set with values returned from the service
     * 
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAvailableWidget() throws Exception {

        final List<Widget> expectedWidgetsList = (List<Widget>) context.getBean("mockAvailableWidgetList");
        // just double check that the test file was not edited without updating
        // the unit test
        Assert.assertEquals(2, expectedWidgetsList.size());

        final String tabInstanceId = "111";

        final PaletteConfigController servlet = new PaletteConfigController();
        servlet.setAdminRoleForTest();

        expect(mockWidgetServices.getAvailableWidgetsByTabInstanceId(tabInstanceId, Locale.ENGLISH, true)).andReturn(expectedWidgetsList);

        expect(mockWidgetServices.getAvailableWidgetsTree(tabInstanceId, Locale.ENGLISH, true)).andReturn(null);

        expect(mockUserServices.isAdminTestModeActive(MOCK_USER_INTERNAL_ID)).andReturn(true);

        getMockUrlNormalizer().translateUrls(expectedWidgetsList, false);
        getMockUrlNormalizer().translateUrls(Collections.EMPTY_MAP, false);

        servlet.setWidgetServices(mockWidgetServices);
        servlet.setConfigurationService(mockConfigurationService);
        servlet.setUrlNormalizer(getMockUrlNormalizer());
        servlet.setUserServices(mockUserServices);

        replay(mockWidgetServices);
        replay(mockConfigurationService);
        replay(getMockUrlNormalizer());
        replay(mockUserServices);
        
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/getPaletteConfig.action");
        this.updateMockRequestWithSessionData(requestBuilder);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, tabInstanceId);
        requestBuilder.locale(Locale.ENGLISH);

        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
        
        verify(mockWidgetServices);
        verify(mockConfigurationService);
        verify(getMockUrlNormalizer());
        verify(mockUserServices);

        Assert.assertEquals(expectedWidgetsList, servlet.getAvailWidgets());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testAvailableWidgetsTree() throws Exception {
        final Map<String, WidgetCategory> expectedWidgetsTree = (Map<String, WidgetCategory>) context.getBean("mockAvailableWidgetTree");
        // just double check that the test file was not edited without updating
        // the unit test
        Assert.assertEquals(2, expectedWidgetsTree.size());

        final String tabInstanceId = "111";

        final PaletteConfigController servlet = new PaletteConfigController();
        servlet.setAdminRoleForTest();

        expect(mockWidgetServices.getAvailableWidgetsByTabInstanceId(tabInstanceId, Locale.ENGLISH, true)).andReturn(null);

        expect(mockWidgetServices.getAvailableWidgetsTree(tabInstanceId, Locale.ENGLISH, true)).andReturn(expectedWidgetsTree);

        expect(mockUserServices.isAdminTestModeActive(MOCK_USER_INTERNAL_ID)).andReturn(true);

        getMockUrlNormalizer().translateUrls(Collections.EMPTY_LIST, false);
        getMockUrlNormalizer().translateUrls(expectedWidgetsTree, false);

        servlet.setWidgetServices(mockWidgetServices);
        servlet.setConfigurationService(mockConfigurationService);
        servlet.setUrlNormalizer(getMockUrlNormalizer());
        servlet.setUserServices(mockUserServices);

        replay(mockWidgetServices);
        replay(mockConfigurationService);
        replay(getMockUrlNormalizer());
        replay(mockUserServices);
        
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/getPaletteConfig.action");
        this.updateMockRequestWithSessionData(requestBuilder);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, tabInstanceId);
        requestBuilder.locale(Locale.ENGLISH);

        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
        
        verify(mockWidgetServices);
        verify(getMockUrlNormalizer());
        verify(mockUserServices);

        // is there more elegant way to compare Collections with List?
        Assert.assertEquals(new ArrayList<WidgetCategory>(expectedWidgetsTree.values()), servlet.getCategoriesTree());
    }
}
