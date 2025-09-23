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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.activitystream.config.IConfigService;
import com.ibm.lconn.homepage.test.web.AbstractWidgetControllerTest;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.controller.widget.DisplayWidgetPageController;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.UserInfo;

public class DisplayWidgetPageControllerTest extends AbstractWidgetControllerTest {

    // private static String FLAG_PARAM = "flag";
    private static String TAB_INSTANCE_ID_PARAM = "tabInstanceId";

    private ITabServices mockTabServices;
    private IConfigService mockConfigService;

    public ITabServices getMockTabServices() {
        return mockTabServices;
    }

    public void setMockTabServices(ITabServices tabServices) {
        this.mockTabServices = tabServices;
    }

    public IConfigService getMockConfigService() {
        return mockConfigService;
    }

    public void setMockConfigService(IConfigService configService) {
        this.mockConfigService = configService;
    }

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setMockTabServices(createMock(ITabServices.class));
        setMockConfigService(createMock(IConfigService.class));
    }

    private void updateMockRequestWithSessionData(MockHttpServletRequestBuilder requestBuilder, String personId, String orgId) {
        
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_INTERNAL_ID, personId);
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_NAME, "Johann Ott");
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_EXTERNAL_ID, personId);
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_ORGID, orgId);
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_LANG, "en");
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_BOARD_ENABLED, true);
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_TIMESTAMP, "123123213");
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_IS_EXTERNAL, true);
    }

    /**
     * Basic tests only for now, session and request params are set as expected. We might want to review this during 3.0 dev and build more robust unit tests
     * 
     * TODO: rewrite
     * 
     * @throws Exception
     */
    @Test
    public void testDisplayWidgetPage() throws Exception {
        String personId = "22222222-2222-2222-2222-222222222222";
        String orgId = "00000000-0000-0000-0000-000000000000";

        // String flagValue = "blah";
        String tabInstanceIdValue = "22222222-1111-1111-1111-222222222222";

        List<Widget> mockWidgets = new ArrayList<Widget>();
        Widget widget = new Widget();
        widget.setWidgetId("1111");
        mockWidgets.add(widget);

        List<WidgetInstance> mockInstances = new ArrayList<WidgetInstance>();
        WidgetInstance instance = new WidgetInstance();
        instance.setWidgetId("1111");
        instance.setContainer(tabInstanceIdValue);
        mockInstances.add(instance);

        final DisplayWidgetPageController servlet = new DisplayWidgetPageController();
        servlet.setAdminRoleForTest();

        expect(getMockUserServices().isUserInWelcomeMode(personId)).andReturn(true);

        expect(getMockTabServices().getNumberOfColumns(tabInstanceIdValue)).andReturn(2);

        // getMockUserServices().updateLastVisitForUser(personId);

        expect(getMockWidgetServices().getAvailableWidgetsByTabInstanceId(tabInstanceIdValue, Locale.ENGLISH, true)).andReturn(mockWidgets);

        expect(getMockWidgetServices().getWidgetInstancesByTabInstanceId(tabInstanceIdValue)).andReturn(mockInstances).times(2);

        expect(getMockUserServices().isAdminTestModeActive(personId)).andReturn(true);

        expect(getMockConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), false)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), true)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PROFILES.getName(), false)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PROFILES.getName(), true)).andReturn("");

        getMockUrlNormalizer().translateUrls(mockWidgets, false);
        
        expect(getMockConfigService().checkConnectionsNewUIEnabled(EasyMock.isA(HttpServletRequest.class))).andReturn(false).times(2);

        expect(getMockConfigService().getConfigObject(EasyMock.eq(Locale.ENGLISH), EasyMock.isA(UserInfo.class))).andReturn(new JSONObject());

        servlet.setTabServices(getMockTabServices());
        servlet.setUserServices(getMockUserServices());
        servlet.setWidgetServices(getMockWidgetServices());
        servlet.setConfigurationService(getMockConfigurationService());
        servlet.setUrlNormalizer(getMockUrlNormalizer());
        servlet.setHomepageActionRequiredService(getMockHomepageActionRequiredService());
        servlet.setActivityStreamConfigService(getMockConfigService());

        replay(getMockTabServices());
        replay(getMockUserServices());
        replay(getMockWidgetServices());
        replay(getMockConfigurationService());
        replay(getMockUrlNormalizer());
        replay(getMockConfigService());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/widgets/");
        this.updateMockRequestWithSessionData(requestBuilder, personId, orgId);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, tabInstanceIdValue);
        requestBuilder.locale(Locale.ENGLISH);
        
        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));

        verify(getMockTabServices());
        verify(getMockUserServices());
        verify(getMockWidgetServices());
        verify(getMockConfigurationService());
        verify(getMockUrlNormalizer());
        verify(getMockConfigService());
    }

    /**
     * Basic tests only for now, session and request params are set as expected. We might want to review this during 3.0 dev and build more robust unit tests
     * 
     * TODO: rewrite
     * 
     * @throws Exception
     */
    @Test
    public void testDisplayWidgetPageForFirstTimeUser() throws Exception {
        String personId = "22222222-2222-2222-2222-222222222222";
        String orgId = "00000000-0000-0000-0000-000000000000";

        // String flagValue = "blah";
        String tabInstanceIdValue = "22222222-1111-1111-1111-222222222222";

        List<Widget> mockWidgets = new ArrayList<Widget>();
        Widget widget = new Widget();
        widget.setWidgetId("1111");
        mockWidgets.add(widget);

        List<WidgetInstance> mockEmptyInstances = new ArrayList<WidgetInstance>();
        List<WidgetInstance> mockInstances = new ArrayList<WidgetInstance>();
        WidgetInstance instance = new WidgetInstance();
        instance.setWidgetId("1111");
        instance.setContainer(tabInstanceIdValue);
        mockInstances.add(instance);

        // mockRequest.addParameter(FLAG_PARAM, flagValue);


        final DisplayWidgetPageController servlet = new DisplayWidgetPageController();
        servlet.setAdminRoleForTest();

        expect(getMockUserServices().isUserInWelcomeMode(personId)).andReturn(true);

        expect(getMockTabServices().getNumberOfColumns(tabInstanceIdValue)).andReturn(2);

        // getMockUserServices().updateLastVisitForUser(personId);

        expect(getMockWidgetServices().getAvailableWidgetsByTabInstanceId(tabInstanceIdValue, Locale.ENGLISH, true)).andReturn(mockWidgets);

        expect(getMockWidgetServices().getWidgetInstancesByTabInstanceId(tabInstanceIdValue)).andReturn(mockEmptyInstances).times(2);

        expect(getMockUserServices().isAdminTestModeActive(personId)).andReturn(true).times(2);

        expect(getMockWidgetServices().createDefaultWidgetInstancesSetForTabInstanceId(tabInstanceIdValue, true)).andReturn(mockInstances);

        expect(getMockConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), false)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PERSON_TAG.getName(), true)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PROFILES.getName(), false)).andReturn("");

        expect(getMockConfigurationService().getComponentUrl(Component.PROFILES.getName(), true)).andReturn("");

        getMockUrlNormalizer().translateUrls(mockWidgets, false);
        
        expect(getMockConfigService().checkConnectionsNewUIEnabled(EasyMock.isA(HttpServletRequest.class))).andReturn(false).times(2);

        expect(getMockConfigService().getConfigObject(EasyMock.eq(Locale.ENGLISH), EasyMock.isA(UserInfo.class))).andReturn(new JSONObject());

        servlet.setTabServices(getMockTabServices());
        servlet.setUserServices(getMockUserServices());
        servlet.setWidgetServices(getMockWidgetServices());
        servlet.setConfigurationService(getMockConfigurationService());
        servlet.setUrlNormalizer(getMockUrlNormalizer());
        servlet.setHomepageActionRequiredService(getMockHomepageActionRequiredService());
        servlet.setActivityStreamConfigService(getMockConfigService());

        replay(getMockTabServices());
        replay(getMockUserServices());
        replay(getMockWidgetServices());
        replay(getMockConfigurationService());
        replay(getMockUrlNormalizer());
        replay(getMockConfigService());
        
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(servlet).build();
        
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/web/widgets/");
        this.updateMockRequestWithSessionData(requestBuilder, personId, orgId);
        requestBuilder.param(TAB_INSTANCE_ID_PARAM, tabInstanceIdValue);
        requestBuilder.locale(Locale.ENGLISH);

        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
        
        verify(getMockTabServices());
        verify(getMockUserServices());
        verify(getMockWidgetServices());
        verify(getMockConfigurationService());
        verify(getMockUrlNormalizer());
        verify(getMockConfigService());

    }

}
