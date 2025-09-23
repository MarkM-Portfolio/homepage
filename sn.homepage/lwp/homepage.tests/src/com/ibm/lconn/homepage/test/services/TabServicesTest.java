/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010,2021                     */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static org.easymock.EasyMock.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.services.cre.remote.widget.WidgetLayoutServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.Tab;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.impl.TabServicesImpl;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.homepage.test.services.mock.MockWidgetServicesRemote;

public class TabServicesTest extends SpringContextAwareTestCase  {
	
	private static String CLASSNAME = TabServicesTest.class.getName();
	
	@Autowired
	private ITabServices tabServices;
	@Autowired
	private WidgetLayoutServiceRemote layoutService;
	
	@Before
	public void init() throws Exception {
		this.tabServices = (ITabServices)getBean("tabServices");
		this.layoutService = (WidgetLayoutServiceRemote)getBean("widgetLayoutServices");
		MockWidgetServicesRemote.reset();
		refreshDB(CLASSNAME, true);
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","HP_UI","HP_TAB","WIDGET","HP_TAB_INST","HP_WIDGET_INST"};
	}
	
	
	public ITabServices getTabServices() {
		return tabServices;
	}
	
	

	@Test
	public void testPostiveGetTabInstance() {
		
		try {
			TabInstance inst = getTabServices().getTabInstance("11111111-1111-1111-1111-111111111111");
			assertEquals(2, inst.getNumColumns());
			
		} catch (ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void testNegativeGetTabInstace() {		
		try {
			getTabServices().getTabInstance("123456789");
			fail();			
		} catch (ServiceException e) {
			assertEquals("CLFRQ0348E: An error occurred while getting the tab instance with ID 123456789. Check nested exception for more details.", e.getMessage());
		}		
	}
	
	@Test
	public void testGetNumberOfColumns() {
		try {
			assertEquals(2, getTabServices().getNumberOfColumns("11111111-1111-1111-1111-111111111111"));
		}
		catch(ServiceException e) {
			fail();
		}
	}
	
	@Test
	public void testCreateCustomUserPanel() throws WidgetServiceException, ServiceException {
		String tabInstId = null;
		String notExistingUiPersonId = "66666666-6666-6666-6666-666666666666";
		String tabName = "myTestPanel";
		
		//
		// Mock
		//
		MockWidgetServicesRemote.expectTabCustom(2);
		EasyMock.replay(layoutService);
		
		//
		// Test
		//
		tabInstId = tabServices.createTabInstance(tabName, notExistingUiPersonId);
		// Test invalidated by the fact that we need to create a UI for a 
		// user if there isn't one. The user could exist in the PERSON
		// table without having logged into Home Page yet
		assertNotNull(tabInstId);
		
		String personId = "55555555-5555-5555-5555-555555555555";
		String customName = "myTestPanel";
		tabInstId = tabServices.createTabInstance(customName, personId);
		
		TabInstance tabInst = null;
		tabInst = tabServices.getTabInstance(tabInstId);

		assertNotNull(tabInst);
		assertEquals(tabInstId,tabInst.getTabInstId());
		assertEquals(tabName, tabInst.getTabName());
		assertNotNull(tabInstId);
		EasyMock.verify(layoutService);

	}
	
	@Test
	public void testCreateStandardUserPanel() throws ServiceException, WidgetServiceException {
		String panelId = null;
		String notExistingUiPersonId = "66666666-6666-6666-6666-666666666666";
		String tabName = "myTestPanel";

		//
		// Mock
		MockWidgetServicesRemote.expectTabCustom(1);
		MockWidgetServicesRemote.expectTabUpdate(1);
		EasyMock.replay(layoutService);
		
		// Test
		panelId = tabServices.createTabInstance(tabName, notExistingUiPersonId);
		// Test invalidated by the fact that we need to create a UI for a 
		// user if there isn't one. The user could exist in the PERSON
		// table without having logged into Home Page yet
		//fail();
		
		assertNotNull(panelId);
		
		String personId = "55555555-5555-5555-5555-555555555555";
		String standardName = TabServicesImpl.UPDATE_PANEL;
			
		TabInstance tabInst = null;
		panelId = tabServices.createTabInstance(standardName, personId);
		tabInst = tabServices.getTabInstance(panelId);
		
		assertNotNull(tabInst);
		assertEquals(panelId,tabInst.getTabInstId());			
		assertEquals(standardName, tabInst.getTabName());	

		assertNotNull(panelId);
		
		EasyMock.verify(layoutService);
	}
	
	@Test
	public void testGetUserPanel() throws WidgetServiceException, ServiceException {
		String panelId = null;
		// a) Case that a user 66666666-6666-6666-6666-666666666666 doesn't have a related info into the ui table. Exception
		String notExistingUiPersonId = "66666666-6666-6666-6666-666666666661";
		
		//
		// Mock
		MockWidgetServicesRemote.expectTabCustom(1);
		EasyMock.replay(layoutService);

		try {
			panelId = tabServices.getTabInstanceId(notExistingUiPersonId, TabType.UPDATE);
			fail();
		} catch (Exception e) {
			//e.printStackTrace();
		}
		assertNull(panelId);
		
		// b) Case that a user already have a panel
		String personId = "11111111-1111-1111-1111-111111111111";
		try {
			panelId = tabServices.getTabInstanceId(personId, TabType.UPDATE);			
		} catch (Exception e) {
			fail();
		}
		assertNotNull(panelId);

		// c) Case that a user got an UI but not a panel. The panel must to be created during the getUserPanel
		String personIdWithUiButWithoutPanel = "55555555-5555-5555-5555-555555555555";
		try {
			panelId = tabServices.getTabInstanceId(personIdWithUiButWithoutPanel, TabType.CUSTOM);			
		} catch (Exception e) {
			fail();
		}
		assertNotNull(panelId);

		EasyMock.verify(layoutService);
	}
	
	@Test
	public void testGetUserPanels() throws ServiceException, WidgetServiceException {
		WidgetLayoutServiceRemote layout = MockWidgetServicesRemote.getWidgetLayoutService();
		MockWidgetServicesRemote.expectTabUpdate(1);
		MockWidgetServicesRemote.expectTabWidget(1);
		MockWidgetServicesRemote.expectTabUpdate(1);
		MockWidgetServicesRemote.expectTabWidget(1);
		EasyMock.replay(layout);
		
		
		List<TabInstance> panels = null;
		String personId = null;

		// a) Case that the user doesn't have any panels into the db and the  UI doesn't exist. Exception
		personId = "66666666-6666-6666-6666-666666666666";
		panels = tabServices.getAllTabInstancesForPerson(personId);
		// Test invalidated by the fact that we need to create a UI for a 
		// user if there isn't one. The user could exist in the PERSON
		// table without having logged into Home Page yet

		
		// b) Case that the user doesn't have any panels into the db but he has an UI		
		personId = "44444444-4444-4444-4444-444444444444";	
		panels = tabServices.getAllTabInstancesForPerson(personId);
		
		assertNotNull(panels);
		assertEquals(TabServicesImpl.DEFAULT_USER_PANELS.length, panels.size());
		
		// c) Case that the user has a custom panel into the db
		personId = "22222222-2222-2222-2222-222222222222";
		panels = tabServices.getAllTabInstancesForPerson(personId);
		
		assertNotNull(panels);
		assertTrue(panels.size()>=TabServicesImpl.DEFAULT_USER_PANELS.length);	
		
		EasyMock.verify(layout);
	}
	
	@Test
	public void testDisableTab() {

		try {
			tabServices.disableTab(TabType.WIDGET.getDBValue());
			assert(!tabServices.isTabEnabled(TabType.WIDGET.getDBValue()));			
			
		}
		catch(Exception e) {
			fail();
		}
	}
	
	@Test
	public void testEnableTab() {

		try {
			tabServices.enableTab(TabType.WIDGET.getDBValue());
			assert(tabServices.isTabEnabled(TabType.WIDGET.getDBValue()));					
		}
		catch(Exception e) {
			fail();
		}
	}

	@Test
	public void testCreateTabInstanceDuplicateUser() {
		final String TAB_INSTANCE = "myTabInstance"; 
		
		String personId = "11111111-1111-1111-1111-111111111111";
		String tabName = "%panel.update";
		
		TabInst tabInst = new TabInst();
		tabInst.setTabId("mytab");
		tabInst.setTabInstId(TAB_INSTANCE);
		
		Tab mockTab = new Tab();
		mockTab.setTabId("mytab");
		
		String returnedTabInstId = "";
		
		// Create some mock objects to use.
		IMocksControl mockController = EasyMock.createControl();
		IUserServices mockUserServices = mockController.createMock(IUserServices.class);
		ITabInstDao mockTabInstDao = mockController.createMock(ITabInstDao.class);
		WidgetLayoutServiceRemote mockWidgetServices = mockController.createMock(WidgetLayoutServiceRemote.class);
		
		// Create some other objects to use in the test that will be returned from methods on the mocks.
		UserUI mockUser = new UserUI();
		
		// Create a new UserServicesImpl object and insert the mocks.
		TabServicesImpl myTabServices = new TabServicesImpl();
		myTabServices.setUserServices(mockUserServices);
		myTabServices.setTabInstDao(mockTabInstDao);
		myTabServices.setWidgetLayoutServices(mockWidgetServices);
		
		try {
			// Calls to the initial fetch for the user which we'll mock to return null.
			EasyMock.expect(mockUserServices.getUserUIByPersonId(personId)).andReturn(null);
			
			/* Call fired by the createUI statement that throws an exception. Recreates the user
			 * being created by another browser instance. */ 
			EasyMock.expect(mockUserServices.createUI(personId))
				.andThrow(new DataIntegrityViolationException("duplicate"));
			
			// Now expect calls back to the fetch.
			EasyMock.expect(mockUserServices.getUserUIByPersonId(personId)).andReturn(mockUser);

			// Expect the call to the widget services to get the tab.
			EasyMock.expect(mockWidgetServices.getTabByName(tabName)).andReturn(mockTab);
			
			// Now test an attempt to create a duplicate tab.
			EasyMock.expect(mockTabInstDao.insert(isA(TabInst.class))).andThrow(new DataIntegrityViolationException("duplicate"));
			
			// We get the existing tab and return.
			EasyMock.expect(mockTabInstDao.getPersonUIPageByDefaultName(personId, tabName)).andReturn(tabInst);
			
		} catch (ServiceException ex) {
			fail();
		} catch (WidgetServiceException e) {
			fail();
		}

		mockController.replay();

		try {
			returnedTabInstId = myTabServices.createTabInstance(tabName, personId);
		} catch (ServiceException ex) {
			fail();
		}
		mockController.verify();

		// Assert that we got the expected welcomeMode back.
		assertTrue(TAB_INSTANCE.equalsIgnoreCase(returnedTabInstId));
		
		mockController.verify();
	}

	
	@Test
	public void testCreateTabInstanceDataIntegrityViolationForUser() {
		String personId = "11111111-1111-1111-1111-111111111111";
		String tabName = "%panel.update";
		
		TabInst tabInst = new TabInst();
		tabInst.setTabId("mytab");
		
		Tab mockTab = new Tab();
		mockTab.setTabId("mytab");
		
		String returnedTabInstId = "";
		
		// Create some mock objects to use.
		IMocksControl mockController = EasyMock.createControl();
		IUserServices mockUserServices = mockController.createMock(IUserServices.class);
		ITabInstDao mockTabInstDao = mockController.createMock(ITabInstDao.class);
		WidgetLayoutServiceRemote mockWidgetServices = mockController.createMock(WidgetLayoutServiceRemote.class);
		
		// Create some other objects to use in the test that will be returned from methods on the mocks.
		UserUI mockUser = new UserUI();
		
		// Create a new UserServicesImpl object and insert the mocks.
		TabServicesImpl myTabServices = new TabServicesImpl();
		myTabServices.setUserServices(mockUserServices);
		myTabServices.setTabInstDao(mockTabInstDao);
		myTabServices.setWidgetLayoutServices(mockWidgetServices);
		
		try {
			// Calls to the initial fetch for the user which we'll mock to return null.
			EasyMock.expect(mockUserServices.getUserUIByPersonId(personId)).andReturn(null);
			
			/* Call fired by the createUI statement that throws an exception. Recreates the user
			 * being created by another browser instance. 
			 */ 
			EasyMock.expect(mockUserServices.createUI(personId))
				.andThrow(new DataIntegrityViolationException("integrityviolation"));
			
			// Now expect calls back to the fetch.
			EasyMock.expect(mockUserServices.getUserUIByPersonId(personId)).andThrow(new ServiceException("error"));
		} catch (ServiceException ex) {
			fail();
		}

		mockController.replay();

		try {
			returnedTabInstId = myTabServices.createTabInstance(tabName, personId);
			fail();
		} catch (ServiceException ex) {
			assertTrue(true);
		}
		mockController.verify();
	}


}
