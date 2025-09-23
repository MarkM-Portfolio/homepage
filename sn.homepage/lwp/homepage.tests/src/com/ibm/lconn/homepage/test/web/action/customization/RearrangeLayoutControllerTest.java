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

package com.ibm.lconn.homepage.test.web.action.customization;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.model.TabUI;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.web.controller.customization.RearrangeLayoutController;
import com.ibm.lconn.homepage.web.utils.IEventUtils;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

/**
 * 
 * @author piyush.jain
 * 
 */
public class RearrangeLayoutControllerTest extends AbstractCustomizationControllerTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testParsePosition() throws Exception {
        String personId = "11111";
        String jsonString = "{\"pageId\":\"page001\", \"column\": 3, \"layout\":[{\"containerId\":\"containerId0\",\"widgets\":[\"test-001\",\"test-002\"]},{\"containerId\":\"containerId1\",\"widgets\":[{\"widgetId\": \"test-003\", \"widgetInstanceId\": \"test-instance-003\"}]}]}";

        final RearrangeLayoutController actionUnderTest = new RearrangeLayoutController();

        TabUI UIBean = actionUnderTest.parsePositionString(personId, jsonString);

        assertEquals("page001", UIBean.getTabInstanceId());
        assertEquals(3, UIBean.getNumberColumns());

        List<WidgetInstance> widgets = UIBean.getWidgetInstances();

        WidgetInstance widget = widgets.get(0);

        // note: assuming an order in the list here for the tests, when it is
        // not really needed in fact

        assertEquals("test-001", widget.getWidgetInstanceId());
        assertEquals(0, widget.getOrderSequence());
        assertEquals("containerId0", widget.getContainer());

        WidgetInstance widget1 = widgets.get(1);

        assertEquals("test-002", widget1.getWidgetInstanceId());
        assertEquals(1, widget1.getOrderSequence());
        assertEquals("containerId0", widget1.getContainer());

        WidgetInstance widget2 = widgets.get(2);

        assertEquals("test-003", widget2.getWidgetId());
        assertEquals("test-instance-003", widget2.getWidgetInstanceId());
        assertEquals(0, widget2.getOrderSequence());
        assertEquals("containerId1", widget2.getContainer());
    }

    @Test
    // Test standard case, with no error
    // TODO: need additional test cases...
    public void testRearrangeLayout() throws Exception {
        String personId = "22222";
        String simpleJsonString = "{\"pageId\":\"page001\", \"column\": 2, \"layout\":[{\"containerId\":\"containerId0\",\"widgets\":[{\"widgetId\": \"test-001\", \"widgetInstanceId\": \"test-instance-001\", \"organizationId\": \"00000000-0000-0000-0000-000000000000\"}]}]}";

        // match values in SIMPLE_JSON_STRING
        final String expectedTabId = "page001";
        final String expectedWidgetId = "test-001";
        final String expectedWidgetInstanceId = "test-instance-001";
        final String expectedContainerId = "containerId0";
        final int expectedNumberColumn = 2;

        HttpServletRequest mockHttpRequest = EasyMock.createMock(HttpServletRequest.class);
        expect(mockHttpRequest.getParameter("Pos")).andReturn(simpleJsonString).times(2);
        HttpSession mockSession = EasyMock.createMock(HttpSession.class);
        expect(mockHttpRequest.getSession()).andReturn(mockSession).anyTimes();
        updateMockRequestWithSessionData(mockSession, personId);

        RearrangeLayoutController actionUnderTest = new RearrangeLayoutController();

        ITabServices mockTabSvc = getMockTabServices();
        ISecurityServices mockSecuritySvc = getMockSecurityServices();
        IWidgetServices mockWidgetSvc = getMockWidgetServices();
        IEventUtils mockEventUtils = getMockEventUtils();

        expect(mockSecuritySvc.checkTabInstanceOwnership(personId, expectedTabId)).andReturn(true);

        expect(mockSecuritySvc.checkWidgetAllowedOnWidgetTab(expectedWidgetId, expectedTabId)).andReturn(true);

        expect(mockWidgetSvc.getWidgetInstance(expectedWidgetInstanceId)).andReturn(null).anyTimes();

        expect(mockWidgetSvc.getWidgetsInstancesForTabInstance(expectedTabId)).andReturn(new ArrayList<WidgetInstance>());

        expect(mockWidgetSvc.createWidgetInstance(expectedWidgetId, expectedTabId, 0, expectedContainerId, true, null, expectedWidgetInstanceId, ApplicationContext.INTERNAL_DEFAULT_ORG))
                .andReturn(null);

        expect(mockEventUtils.isRequiredPost(IEventUtils.USER_WIDGET_ADD, Type.CREATE)).andReturn(false);

        mockTabSvc.updateNumberColumn(expectedTabId, expectedNumberColumn);

        // mockSecuritySvc.

        replay(mockSession);
        replay(mockHttpRequest);
        replay(mockTabSvc);
        replay(mockSecuritySvc);
        replay(mockWidgetSvc);
        replay(mockEventUtils);

        actionUnderTest.setTabServices(mockTabSvc);
        actionUnderTest.setSecurityServices(mockSecuritySvc);
        actionUnderTest.setWidgetServices(mockWidgetSvc);
        actionUnderTest.setWebResourceBundle(getMockResourceBundle());
        actionUnderTest.setEventUtils(mockEventUtils);

        actionUnderTest.executeInternal(mockHttpRequest);

        verify(mockHttpRequest);
        verify(mockTabSvc);
        verify(mockSecuritySvc);
        verify(mockWidgetSvc);
        verify(mockEventUtils);
    }
}
