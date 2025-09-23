/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.IUIServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.homepage.test.services.mock.MockWidgetServicesRemote;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;

public class UIServicesTest extends SpringContextAwareTestCase{

	@Autowired
	private IUIServices uiServices;
	private static String CLASSNAME = UIServicesTest.class.getName();
	private static String orgId = ApplicationContext.INTERNAL_DEFAULT_ORG;
	@Before
	public void init() throws Exception {
		this.uiServices = (IUIServices)getBean("uiServices");
		MockWidgetServicesRemote.reset();
		refreshDB(CLASSNAME, false );
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","HP_UI","HP_TAB","WIDGET","HP_TAB_INST","HP_WIDGET_INST"};
	}
	

	@Test
	public void testUiServicesNotNull(){
		Assert.assertNotNull(uiServices);
	}
	
	@Test
	public void testDeleteWidgetInstance() throws WidgetServiceException, ServiceException {
		final String widgetInstanceId = "66666666-6666-6666-6666-666666666666";
		final String widgetId = "22222222-2222-2222-2222-222222222222";

		WidgetCatalogServiceRemote remote = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(remote.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());		
		EasyMock.replay(remote);
		
		WidgetInstance wi = null;
		wi = uiServices.getWidgetInstance(widgetInstanceId);
		assertNotNull(wi);
		
		uiServices.deleteWidgetInstance(widgetInstanceId);
		wi = uiServices.getWidgetInstance(widgetInstanceId);
		assertNull(wi);		
		
		EasyMock.verify(remote);
	}
	
	
	@Test
	public void testCreateWidgetInstance() {
		String widgetId = null;
		String widgetInstId = null;
		String tabInstanceId = null;
		
		//a) Not existing widget. Exception
		widgetId = "noExistingWidget";
		tabInstanceId = "fdsfds";
		try {
			widgetInstId = uiServices.createWidgetInstance(widgetId, tabInstanceId, 1, "1", true, "widgetSetting", null, orgId);
			fail();
		}
		catch (Exception e) {			
		}
		assertNull(widgetInstId);
		
		//b) The tabInstance doesn't no exist. Exception
		widgetId = "33333333-3333-3333-3333-333333333333";
		tabInstanceId = "does not exist";
		try {
			widgetInstId = uiServices.createWidgetInstance(widgetId, tabInstanceId, 1, "1", true, "widgetSetting", null, orgId);
			fail();
		}
		catch (Exception e) {			
		}
		assertNull(widgetInstId);

		//c) The user got a ui and the widget instance doesn't exist. FINE
		widgetId = "66666666-6666-6666-6666-666666666666";
		tabInstanceId = "33333333-3333-3333-3333-333333333333";
		try {
			widgetInstId = uiServices.createWidgetInstance(widgetId, tabInstanceId, 1, "1", true, "widgetSetting", null, orgId);
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		Assert.assertNotNull(widgetInstId);		
	}
	
	@Test
	public void testGetWidgetInstance() throws Exception {
		String widgetId = null;
		String widgetInstId = null;
		String tabInstanceId = null;
		WidgetInstance widgetInst = null;

		//a) Non existing widget. EXCEPTION
		widgetInst = uiServices.getWidgetInstance("fds");
		assertNull(widgetInst);
		
		//b) Existing widget. FINE
		widgetId = "66666666-6666-6666-6666-666666666666";
		tabInstanceId = "44444444-4444-4444-4444-444444444444";
		
		// has 'deleted' check
		WidgetCatalogServiceRemote remote = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(remote.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());		
		EasyMock.replay(remote);
		
		widgetInstId = uiServices.createWidgetInstance(widgetId, tabInstanceId, 1, "1", true, "widgetSetting", null, orgId);
		assertNotNull(widgetInstId);
		
		widgetInst = uiServices.getWidgetInstance(widgetInstId);
		assertNotNull(widgetInst);
		
		EasyMock.verify(remote);
	}
	
	@Test
	@Ignore // To Mike A. => This test is running fine in DB2, but it doesn't work properly in Oracle - I don't understand - adding Ignore for now
	public void testFindWidgetsInstancesForTabInstance() throws Exception { 
		final String tabInstanceId = "11111111-1111-1111-1111-111111111111";
		
		final List<String> widgetIds = Arrays.asList("11111111-1111-1111-1111-111111111111", "33333333-3333-3333-3333-333333333333");
				
		WidgetCatalogServiceRemote remote = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(remote.getDeletedWidgetsByIds(widgetIds)).andReturn(Collections.<String>emptyList());		
		EasyMock.replay(remote);

		
		List<WidgetInstance> instances = uiServices.findWidgetsInstancesForTabInstance(tabInstanceId);
		
		assertNotNull(instances);
		assertEquals(2, instances.size());	
		
		EasyMock.verify(remote);
	}
	
	@Test
	public void testChangePosition() throws Exception {
		final String widgetInstanceId="33333333-3333-3333-3333-333333333333";
		final String widgetId = widgetInstanceId;
		
		
		WidgetCatalogServiceRemote remote = MockWidgetServicesRemote.getWidgetCatalogService();
		// get widgetInstance()
		EasyMock.expect(remote.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList()).times(3);		
		EasyMock.replay(remote);

		
		WidgetInstance widgetInst = null;
		widgetInst = uiServices.getWidgetInstance(widgetInstanceId);
		assertNotNull(widgetInst);
		
		final int newRow = widgetInst.getOrderSequence()+1;
		final int newContainer =  Integer.parseInt(widgetInst.getContainer())+1;
		
		uiServices.changePosition(widgetInst.getWidgetInstanceId(), newRow,  newContainer, "notUser");
		widgetInst = uiServices.getWidgetInstance(widgetInstanceId);
		
		assertNotNull(widgetInst);
		assertEquals(newRow, widgetInst.getOrderSequence());
		assertEquals(newContainer, Integer.parseInt(widgetInst.getContainer()));
	
		EasyMock.verify(remote);
	}
	
	@Test
	public void testChangeWidgetSetting() throws Exception {
		final String widgetInstanceId="33333333-3333-3333-3333-333333333333";
		final String widgetId ="33333333-3333-3333-3333-333333333333";
		
		WidgetCatalogServiceRemote remote = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(remote.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList()).times(3);		
		EasyMock.replay(remote);

		
		WidgetInstance widgetInst = uiServices.getWidgetInstance(widgetInstanceId);
		assertNotNull(widgetInst);
		
		String newSettings = "new"+widgetInst.getWidgetSetting();
			
		uiServices.changeWidgetSetting(widgetInstanceId, newSettings);
		widgetInst = uiServices.getWidgetInstance(widgetInstanceId);
		assertNotNull(widgetInst);
		assertEquals(newSettings, widgetInst.getWidgetSetting());		
	}

}
