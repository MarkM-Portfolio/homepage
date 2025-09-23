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

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ibm.lconn.homepage.web.GlobalSessionConstants;
import com.ibm.lconn.homepage.web.controller.customization.LegacyCustomizationController;

public class LegacyCustomizationControllerTest extends AbstractCustomizationControllerTest {

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    private void updateMockRequestWithSessionData(MockHttpServletRequestBuilder requestBuilder, String personId) {
        requestBuilder.sessionAttr(GlobalSessionConstants.USER_INFO_INTERNAL_ID, personId);
    }

    @Test
    public void testSaveCustomizationRedirect() throws Exception {
        testMapping("SaveCustomization", "doSaveCustomization");
    }

    @Test
    public void testLoadCustomizationRedirect() throws Exception {
        testMapping("LoadCustomization", "doLoadCustomization");
    }

    @Test
    public void testChangePosRedirect() throws Exception {
        testMapping("ChangePos", "doRearrangeLayout");
    }

    @Test
    public void testInvalidParam() throws Exception {
        String actParam = "act";
        String personId = "2222";

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/web/handleUP.action");
        this.updateMockRequestWithSessionData(requestBuilder, personId);
        requestBuilder.param(actParam, "somerandomdummystuff");

        LegacyCustomizationController actionUnderTest = new LegacyCustomizationController();

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(actionUnderTest).build();
        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));
    }

    private void testMapping(String actParamValue, String expectedActionName) throws Exception {
        String actParam = "act";
        String personId = "2222";

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/web/handleUP.action");
        this.updateMockRequestWithSessionData(requestBuilder, personId);
        requestBuilder.param(actParam, actParamValue);

        LegacyCustomizationController actionUnderTest = new LegacyCustomizationController();
        actionUnderTest.setActionName(expectedActionName);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(actionUnderTest).build();
        ResultActions result = mockMvc.perform(requestBuilder);
        result.andExpect(MockMvcResultMatchers.status().is(200));

        String actualActionName = actionUnderTest.getActionName();
        assertEquals(expectedActionName, actualActionName);
    }
}
