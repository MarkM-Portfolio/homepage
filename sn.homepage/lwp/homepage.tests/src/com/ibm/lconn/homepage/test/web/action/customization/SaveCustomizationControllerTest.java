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
import static org.easymock.EasyMock.verify;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.events.internal.EventConstants.Type;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.web.controller.customization.SaveCustomizationController;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

/**
 * 
 * @author piyush.jain
 * 
 */
public class SaveCustomizationControllerTest extends AbstractCustomizationControllerTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    // ideal test case for now
    public void testSaveCustomization() throws Exception {
        String widgetIdParam = "FId";
        String dataParam = "data";
        String personId = "111";

        final String expectedWidgetInstanceId = "widgetInstance001";
        final String expectedData = "data";

        final WidgetInstance mockWidgetInstance = new WidgetInstance();
        mockWidgetInstance.setWidgetInstanceId(expectedWidgetInstanceId);

        HttpServletRequest mockHttpRequest = EasyMock.createMock(HttpServletRequest.class);
        expect(mockHttpRequest.getParameter("data")).andReturn(dataParam).times(2);
        HttpSession mockSession = EasyMock.createMock(HttpSession.class);
        expect(mockHttpRequest.getSession()).andReturn(mockSession).anyTimes();
        updateMockRequestWithSessionData(mockSession, personId);

        SaveCustomizationController actionUnderTest = new SaveCustomizationController();

        ISecurityServices mockSecuritySvc = getMockSecurityServices();
        IWidgetServices mockWidgetSvc = getMockWidgetServices();
        IEventUtils mockEventUtils = getMockEventUtils();

        // TODO: calling twice getWidgetInstance --> check performance impact of
        // this
        EasyMock.expect(mockWidgetSvc.getWidgetInstance((String) EasyMock.anyObject())).andReturn(mockWidgetInstance).anyTimes();

        mockWidgetSvc.changeWidgetSetting(expectedWidgetInstanceId, expectedData);

        EasyMock.expect(mockSecuritySvc.checkWidgetInstanceOwnership(personId, expectedWidgetInstanceId)).andReturn(true);

        EasyMock.expect(mockEventUtils.isRequiredPost(IEventUtils.USER_WIDGET_EDIT, Type.UPDATE)).andReturn(false);

        EasyMock.replay(mockSession);
        EasyMock.replay(mockHttpRequest);
        EasyMock.replay(mockSecuritySvc);
        EasyMock.replay(mockWidgetSvc);
        EasyMock.replay(mockEventUtils);

        actionUnderTest.setSecurityServices(mockSecuritySvc);
        actionUnderTest.setWidgetServices(mockWidgetSvc);
        actionUnderTest.setEventUtils(mockEventUtils);

        actionUnderTest.executeInternal(mockHttpRequest, widgetIdParam);

        verify(mockHttpRequest);
        EasyMock.verify(mockSecuritySvc);
        EasyMock.verify(mockWidgetSvc);
        EasyMock.verify(mockEventUtils);
    }

}
