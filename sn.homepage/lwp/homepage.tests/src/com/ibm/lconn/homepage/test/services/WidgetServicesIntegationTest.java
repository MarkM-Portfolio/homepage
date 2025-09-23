/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.widget.IWidgetServices;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.homepage.test.services.mock.MockWidgetServicesRemote;

/**
 * Integrated tests of DAO and service code
 * 
 * @author Michael Ahern (michael.ahern@ie.ibm.com)
 */
public class WidgetServicesIntegationTest extends SpringContextAwareTestCase {
	
	private static String CLASSNAME = WidgetServicesIntegationTest.class.getName();
	
	@Before
	public void resetMock() {
		MockWidgetServicesRemote.reset();
	}
	
	@Before
	public void init() throws Exception {
		widgetServices = (IWidgetServices)getBean("widgetServices");
		assertNotNull(widgetServices);
		MockWidgetServicesRemote.reset();
		refreshDB(CLASSNAME, false );
		
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","HP_UI","HP_TAB","WIDGET","HP_TAB_INST","HP_WIDGET_INST"};
	}

	public static final String WIDGET_ID = "22222222-2222-2222-2222-222222222222";
		
	// -- 1
	public static final String W_INDT_ID_1 = "55555555-5555-5555-5555-555555555555";
	public static final String TAB_INST_ID_1 = "22222222-2222-2222-2222-222222222222";

	// -- 2
	public static final String W_INDT_ID_2 = "66666666-6666-6666-6666-666666666666";
	public static final String TAB_INST_ID_2 = "33333333-3333-3333-3333-333333333333";
	
	public static final String[][] TABS = {
		{
			TAB_INST_ID_1,
			W_INDT_ID_1
		},
		{
			TAB_INST_ID_2,
			W_INDT_ID_2
		}		
	};

	private IWidgetServices widgetServices;


	
	@Test
	public void test_remove_widget_and_instance_behaves_correctly() throws Exception
	{
		// MIA - Validation not applicable in 'Mocked' environment
		//final int BEFORE_COUNT = widgetServices.getAllWidgets(Locale.getDefault()).size();
		
		//System.out.print(BEFORE_COUNT);
		
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		
		// check seed data exists
		// MIA - not applic with mocking
		//assertNotNull(widgetServices.getWidget(WIDGET_ID, false));

		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(WIDGET_ID))).andReturn(Collections.<String>emptyList()).times(2);
		EasyMock.replay(catalog);
		
		assertNotNull(widgetServices.getWidgetInstance(W_INDT_ID_1));
		assertNotNull(widgetServices.getWidgetInstance(W_INDT_ID_2));

		EasyMock.verify(catalog);
		resetMock();
		
		// delete widget
		// NA in 'mock' env
		//widgetServices.removeWidget(WIDGET_ID);
		
		EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(WIDGET_ID))).andReturn(Arrays.asList(WIDGET_ID)).times(2);
		EasyMock.replay(catalog);
		
		// check service level classes behave correctly with soft delete
		assertNull(widgetServices.getWidgetInstance(W_INDT_ID_1));
		assertNull(widgetServices.getWidgetInstance(W_INDT_ID_2));
		
		EasyMock.verify(catalog);
		resetMock();
		
		
		for (String[] tabPair : TABS) {
			final String tabInstanceId = tabPair[0];
			final String softDeletedWidgetInstId = tabPair[1];
			
			// check not available on palate
			// NA - due to mocking
			//List<Widget> available = widgetServices.getAvailableWidgetsByTabInstanceId(tabInstanceId, true);
			//for (Widget w : available) {
			//	assertNotSame(WIDGET_ID, w.getWidgetId());
			//}
			
			// check not in instance list
			// should not be deleted ID.  We can catch error case via 'notSame' check as instanceId will == softDeletedInstanceId
			EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList((String)EasyMock.anyObject()))).andReturn(Collections.<String>emptyList());
			EasyMock.replay(catalog);
			
			List<WidgetInstance> instances = widgetServices.getWidgetInstancesByTabInstanceId(tabInstanceId);
			for (WidgetInstance i : instances) {
				assertNotSame(softDeletedWidgetInstId, i.getWidgetInstanceId());
			}
			
			EasyMock.verify(catalog);
			resetMock();
		}
		
		// MIA - Validation not applicable in 'Mocked' environment
		//assertEquals(BEFORE_COUNT-1, widgetServices.getAllWidgets(Locale.getDefault()).size());
	}



}
