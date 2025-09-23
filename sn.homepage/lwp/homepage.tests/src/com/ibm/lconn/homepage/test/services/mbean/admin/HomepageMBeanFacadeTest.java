/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services.mbean.admin;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import javax.management.RuntimeMBeanException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.easymock.EasyMock;

import com.ibm.lconn.homepage.services.mbean.admin.impl.HomepageMBeanFacade;
import com.ibm.lconn.homepage.utils.impl.ResourceBundleImpl;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.homepage.services.mbean.admin.MBeanExporter;

public class HomepageMBeanFacadeTest {
    private HomepageMBeanFacade homepageMBeanFacade = new HomepageMBeanFacade();
    private ResourceBundleImpl resourceBundle = new ResourceBundleImpl();
    private Object[] args = null;
    private Class<?>[] argTypes = null;
    private String orgID = "00000000-0000-0000-0000-000000000000";
    private MBeanExporter exporter;
    
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void init() throws Exception {
        resourceBundle.setBundleName("com.ibm.lconn.homepage.resources.nls.service.ServiceLogMessages");
        homepageMBeanFacade.setServiceResourceBundle(resourceBundle);
        HomepageMBeanFacadeDummyObject obj = new HomepageMBeanFacadeDummyObject();
        HashMap<String, Object> beans = new HashMap<String, Object>();
        beans.put("HomepageMBeanFacadeDummyObject",  obj);
        exporter = EasyMock.createMock(MBeanExporter.class);
        homepageMBeanFacade.setMbeanExporter(exporter);
        EasyMock.expect(exporter.getBeansToExport()).andReturn(beans).anyTimes();
        EasyMock.replay(exporter);
    }

    @Test
    public void InvokeOverloadWithNoOrgID_ValidParams_ReturnsSuccessfullyFromInvikedMethod() {
        // given - including init()
        
        // when
        Object result = homepageMBeanFacade.invoke("HomepageMBeanFacadeDummyObject", "returnTrue", args, argTypes);
        
        // then
        assertEquals(result, true);
    }

    @Test
    public void InvokeOverloadWithNoOrgID_InvalidMethodParam_ReturnsCorrectRuntimeMBeanException() {
        // given - including init()

        // then
        expectedEx.expect(RuntimeMBeanException.class);
        expectedEx.expectMessage("Unable to invoke method");

        // when
        homepageMBeanFacade.invoke("HomepageMBeanFacadeDummyObject", "Invalid", args, argTypes);
    }

    @Test
    public void InvokeOverloadWithNoOrgID_InvalidServiceParam_ReturnsCorrectRuntimeMBeanException() {
        // given - including init()

        // then
        expectedEx.expect(RuntimeMBeanException.class);
        expectedEx.expectMessage("Unable to find service");

        // when
        homepageMBeanFacade.invoke("InvalidDummyObject", "returnTrue", args, argTypes);
    }

    @Test
    public void InvokeOverloadWithNoOrgID_InvalidMethodParameters_ReturnsCorrectRuntimeMBeanException() {
        // given - including init()
        Object[] invalidArgs = new Object[1];
        Class<?>[] invalidArgTypes = new Class<?>[1];

        // then
        expectedEx.expect(RuntimeMBeanException.class);

        // when
        homepageMBeanFacade.invoke("HomepageMBeanFacadeDummyObject", "returnTrue", invalidArgs, invalidArgTypes);
    }

    @Test
    public void InvokeOverloadWithOrgID_ValidParams_SetsValidOrgIDAndInvokesAccordingly() {
        // given - including init()

        // when
        homepageMBeanFacade.invoke("HomepageMBeanFacadeDummyObject", "returnTrue", args, argTypes, orgID);

        // then
        assertEquals(ApplicationContext.getOrganizationId(), orgID);
    }
}
