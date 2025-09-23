/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetLayoutServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.lconn.homepage.services.ISecurityServices;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.homepage.test.services.mock.MockWidgetServicesRemote;

public class SecurityServicesTest extends SpringContextAwareTestCase {
	
	@Autowired
	private ISecurityServices securityServices;
	
	@Before
	public void init() throws Exception {
		this.securityServices = (ISecurityServices) getBean("securityServices");
		
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"PERSON","HP_UI","HP_TAB","WIDGET","HP_TAB_INST","HP_WIDGET_INST"};
	}


	public ISecurityServices getSecurityServices() {
		return securityServices;
	}
	
	@Before
	public void resetMock() {
		MockWidgetServicesRemote.reset();
	}
	
	@Test
	public void checkValidTabInstanceOwnershipTest()  throws Exception {
		String personId = "11111111-1111-1111-1111-111111111111";
		String tabInstanceId = "22222222-2222-2222-2222-222222222222";
		assertEquals(true, getSecurityServices().checkTabInstanceOwnership(personId, tabInstanceId));
	}
	
	@Test
	public void checkInvalidTabInstanceOwnershipTest()  throws Exception {
		String personId = "11111111-1111-1111-1111-111111111111";
		String tabInstanceId = "33333333-3333-3333-3333-333333333333";
		assertEquals(false, getSecurityServices().checkTabInstanceOwnership(personId, tabInstanceId));
	}
	
	@Test
	public void checkValidWidgetAllowedOnWidgetTab() throws Exception {
		final String widgetId = "22222222-2222-2222-2222-222222222222";
		final String tabInstanceId = "55555555-5555-5555-5555-555555555555";
		final String tabId = "%panel.updatex4a43x82aaxb00187218631";
		
		WidgetLayoutServiceRemote catalog = MockWidgetServicesRemote.getWidgetLayoutService();
		WidgetTab mockWidgetTab = new WidgetTab();
		mockWidgetTab.setWidgetId(widgetId);
		mockWidgetTab.setTabId(tabId);
		EasyMock.expect(catalog.getWidgetTabsByWidgetId(widgetId)).andReturn(Arrays.asList(mockWidgetTab));
		EasyMock.replay(catalog);
		
		assertEquals(true, getSecurityServices().checkWidgetAllowedOnWidgetTab(widgetId, tabInstanceId));
		
		EasyMock.verify(catalog);
	}
	
	@Test
	public void checkInvalidWidgetAllowedOnWidgetTab() throws Exception  {
		String widgetId = "22222222-2222-2222-2222-222222222222";
		String tabInstanceId = "77777777-7777-7777-7777-777777777777";
		
		WidgetLayoutServiceRemote catalog = MockWidgetServicesRemote.getWidgetLayoutService();
		EasyMock.expect(catalog.getWidgetTabsByWidgetId(widgetId)).andReturn(Collections.<WidgetTab>emptyList());
		EasyMock.replay(catalog);
		
		assertEquals(false, getSecurityServices().checkWidgetAllowedOnWidgetTab(widgetId, tabInstanceId));
		
		EasyMock.verify(catalog);
	}
	
	@Test
	public void checkValidWidgetInstanceAllowedOnWidgetTab()  throws Exception {
		final String widgetInstanceId = "33333333-3333-3333-3333-333333333333";
		final String tabInstanceId = "22222222-2222-2222-2222-222222222222";
		
		final String widgetId = widgetInstanceId;
		final String tabId = "%panel.widgetx4a43x82aaxb00187218631";
		
		WidgetLayoutServiceRemote layout = MockWidgetServicesRemote.getWidgetLayoutService();
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		WidgetTab mockWidgetTab = new WidgetTab();
		mockWidgetTab.setTabId(tabId);
		EasyMock.expect(layout.getWidgetTabsByWidgetId((String)EasyMock.anyObject())).andReturn(Arrays.asList(mockWidgetTab));
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		EasyMock.replay(layout);
		
		assertEquals(true, getSecurityServices().checkWidgetInstanceAllowedOnWidgetTab(widgetInstanceId, tabInstanceId));
		
		EasyMock.verify(catalog, layout);
		EasyMock.verify(layout);
	}
	
	@Test
	public void checkInvalidWidgetInstanceAllowedOnWidgetTab()  throws Exception {
		final String widgetInstanceId = "11111111-1111-1111-1111-111111111111";
		final String tabInstanceId = "77777777-7777-7777-7777-777777777777";
		
		final String tabId = "%panel.customx4a43x82aaxb00187218631";
		final String widgetId = widgetInstanceId;
		
		WidgetLayoutServiceRemote layout = MockWidgetServicesRemote.getWidgetLayoutService();
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		WidgetTab mockWidgetTab = new WidgetTab();
		mockWidgetTab.setTabId(tabId);
		EasyMock.expect(layout.getWidgetTabsByWidgetId((String)EasyMock.anyObject())).andReturn(Collections.<WidgetTab>emptyList());
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		EasyMock.replay(layout);
		
		assertEquals(false, getSecurityServices().checkWidgetInstanceAllowedOnWidgetTab(widgetInstanceId, tabInstanceId));
	}
	
	@Test
	public void checkValidWidgetInstanceOwnership()  throws Exception {
		String personId = "11111111-1111-1111-1111-111111111111";
		String widgetInstanceId = "33333333-3333-3333-3333-333333333333";
		
		final String widgetId = widgetInstanceId;
		
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		
		assertEquals(true, getSecurityServices().checkWidgetInstanceOwnership(personId, widgetInstanceId));
		
		EasyMock.verify(catalog);
	}
	
	@Test
	public void checkInvalidWidgetInstanceOwnership()  throws Exception {
		final String personId = "11111111-1111-1111-1111-111111111111";
		final String widgetInstanceId = "66666666-6666-6666-6666-666666666666";
		
		final String widgetId = "22222222-2222-2222-2222-222222222222";
		
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		
		assertEquals(false, getSecurityServices().checkWidgetInstanceOwnership(personId, widgetInstanceId));
		
		EasyMock.verify(catalog);
	}
	
	@Test
	public void checkValidWidgetInstanceType()  throws Exception {
		final String widgetId = "33333333-3333-3333-3333-333333333333";
		final String widgetInstanceId = "44444444-4444-4444-4444-444444444444";
		
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(widgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		
		assertTrue(getSecurityServices().checkWidgetInstanceType(widgetInstanceId, widgetId));
		
		EasyMock.verify(catalog);
	}
	
	@Test
	public void checkInvalidWidgetInstanceType()  throws Exception {
		final String widgetId = "33333333-3333-3333-3333-333333333333";
		final String widgetInstanceId = "66666666-6666-6666-6666-666666666666";
		
		final String instActualWidgetId = "22222222-2222-2222-2222-222222222222";
		
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(instActualWidgetId))).andReturn(Collections.<String>emptyList());
		EasyMock.replay(catalog);
		
		assertFalse(getSecurityServices().checkWidgetInstanceType(widgetInstanceId, widgetId));
		
		EasyMock.verify(catalog);
	}



}
