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

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;

import javax.servlet.http.HttpSession;

import org.easymock.EasyMock;
import org.junit.Before;

import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.test.web.AbstractLoggedInUserTest;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.utils.IEventUtils;

public abstract class AbstractCustomizationControllerTest extends AbstractLoggedInUserTest {

    private ITabServices mockTabServices;
    private ISecurityServices mockSecurityServices;
    private ResourceBundle mockResourceBundle;
    private IWidgetServices mockWidgetServices;
    private IEventUtils mockEventUtils;

    @Before
    @Override
    protected void setUp() throws Exception {

        super.setUp();

        // setMockWidgetSvc(createMock(IWidgetServices.class));
        setMockTabServices(createMock(ITabServices.class));
        setMockSecurityServices(createMock(ISecurityServices.class));
        setMockWidgetServices(createMock(IWidgetServices.class));
        setMockEventUtils(createMock(IEventUtils.class));

        // nice mock and already replayed. we don't test the calls to the
        // resource bundle...
        ResourceBundle mockBundle = createNiceMock(ResourceBundle.class);
        replay(mockBundle);
        setMockResourceBundle(mockBundle);
    }

    public void updateMockRequestWithSessionData(HttpSession mockSession, String personId) {
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_EXTERNAL_ID)).andReturn(personId);
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_INTERNAL_ID)).andReturn(personId);
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_EMAIL)).andReturn("jhon@iris.com");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_NAME)).andReturn("Johann Ott");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_ORGID)).andReturn("orgId");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_LANG)).andReturn("en");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_BOARD_ENABLED)).andReturn(true);
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_INFO_TIMESTAMP)).andReturn("123123123");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.USER_IS_EXTERNAL)).andReturn(true);
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.VIEW_TABINSTANCE_ID)).andReturn("tabInstanceId");
        EasyMock.expect(mockSession.getAttribute(GlobalSessionConstants.VIEW_ISWIDGETTABENABLED)).andReturn(false);
    }

    public ITabServices getMockTabServices() {
        return mockTabServices;
    }

    public void setMockTabServices(ITabServices mockTabServices) {
        this.mockTabServices = mockTabServices;
    }

    public ISecurityServices getMockSecurityServices() {
        return mockSecurityServices;
    }

    public void setMockSecurityServices(ISecurityServices mockSecurityServices) {
        this.mockSecurityServices = mockSecurityServices;
    }

    public ResourceBundle getMockResourceBundle() {
        return mockResourceBundle;
    }

    public void setMockResourceBundle(ResourceBundle mockResourceBundle) {
        this.mockResourceBundle = mockResourceBundle;
    }

    public IWidgetServices getMockWidgetServices() {
        return mockWidgetServices;
    }

    public void setMockWidgetServices(IWidgetServices mockWidgetServices) {
        this.mockWidgetServices = mockWidgetServices;
    }

    public IEventUtils getMockEventUtils() {
        return mockEventUtils;
    }

    public void setMockEventUtils(IEventUtils mockEventUtils) {
        this.mockEventUtils = mockEventUtils;
    }
}
