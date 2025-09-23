/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2008, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import static junit.framework.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.core.services.cre.remote.widget.WidgetServiceException;
import com.ibm.lconn.core.services.cre.remote.widget.model.Tab;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.core.services.cre.remote.widget.model.Widget;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetCategory;
import com.ibm.lconn.core.services.cre.remote.widget.model.WidgetTab;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.Category;
import com.ibm.lconn.homepage.model.TabInstance;
import com.ibm.lconn.homepage.model.WidgetInstance;
import com.ibm.lconn.homepage.services.ITabServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.services.widget.impl.WidgetServicesImpl;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test class for com.ibm.lconn.homepage.services.impl.WidgetServicesImpl
 * 
 * @author Guo Du Yishan Sun
 * 
 */

public class WidgetServicesTest {

	public static final String TEST_ID = "11111111-1111-1111-1111-111111111111";
	public static final String TEST_APP_ID = "Test App";

	@Autowired
	private WidgetServicesImpl widgetServices;
	@Autowired
	private WidgetCatalogServiceRemote widgetCatalog;
	@Autowired
	private ModelConverter modelConverter;
	@Autowired
	private ResourceBundle resourceBundle;
	@Autowired
	private IWidgetInstDao widgetInstDao;
	@Autowired
	private ITabServices tabServices;

	@Before
	public void setWidgetServices() {
		widgetServices = new WidgetServicesImpl();
		widgetInstDao = EasyMock.createMock(IWidgetInstDao.class);
		modelConverter = EasyMock.createMock(ModelConverter.class);
		resourceBundle = EasyMock.createMock(ResourceBundle.class);
		tabServices = EasyMock.createMock(ITabServices.class);

		// Now mocked...
		widgetCatalog = EasyMock.createMock(WidgetCatalogServiceRemote.class);
		
		widgetServices.setModelConverter(modelConverter);
		widgetServices.setCatalogService(widgetCatalog);
		widgetServices.setCatalogServiceUIResourceBundle(resourceBundle);
		widgetServices.setWidgetInstDao(widgetInstDao);
		widgetServices.setTabServices(tabServices);
	}

	@Test
	public void testGetAvailableWidgetsByTabInstanceId()
			throws WidgetServiceException, ServiceException 
	{
		TabInstance tabInstance = new TabInstance();
		tabInstance.setTabId(TEST_ID);

		WidgetTab widgetTab2 = new WidgetTab();
		widgetTab2.setTabId(TEST_ID);
		widgetTab2.setWidgetId(TEST_ID);

		Widget mockWidget = new Widget();
		mockWidget.setCategoryName(Category.BLOGS.name());
		mockWidget.setWidgetId(TEST_ID);

		List<WidgetTab> mockWidgetTabList2 = new ArrayList<WidgetTab>();
		mockWidgetTabList2.add(widgetTab2);

		Tab tab = new Tab();
		tab.setDefaultName(TabType.WIDGET.getDBValue());
		
		//
		// set up returns...
		//
		EasyMock.expect(tabServices.getTabInstance(TEST_ID)).andReturn(tabInstance);
		EasyMock.expect(widgetCatalog.getWidget(TEST_ID, Locale.getDefault(), true)).andReturn(mockWidget);

		EasyMock.expect(tabServices.getWidgetTabsByTabId(TEST_ID))
			.andReturn(new ArrayList<WidgetTab>(Arrays.asList(widgetTab2)));		
						
		//
		// replay mock
		//
		EasyMock.replay(tabServices);
		EasyMock.replay(widgetCatalog);
		
		//
		// verify...
		//
		List<Widget> widgetList = widgetServices
				.getAvailableWidgetsByTabInstanceId(TEST_ID, true);
		
		Assert.assertEquals(1, widgetList.size());
		EasyMock.verify(tabServices);
		EasyMock.verify(widgetCatalog);
	}

	@Test
	public void testGetAvailableWidgetsTree() throws WidgetServiceException, ServiceException {
		TabInstance tabInstance = new TabInstance();
		tabInstance.setTabId(TEST_ID);

		WidgetTab widgetTab2 = new WidgetTab();
		widgetTab2.setTabId(TEST_ID);
		widgetTab2.setWidgetId(TEST_ID);

		Widget mockWidget = new Widget();
		mockWidget.setCategoryName(Category.BLOGS.name());
		mockWidget.setWidgetId(TEST_ID);

		List<WidgetTab> mockWidgetTabList2 = new ArrayList<WidgetTab>();
		mockWidgetTabList2.add(widgetTab2);

		Tab tab = new Tab();
		tab.setDefaultName(TabType.WIDGET.getDBValue());
		
		//
		// set up returns...
		//
		EasyMock.expect(tabServices.getTabInstance(TEST_ID)).andReturn(tabInstance);
		EasyMock.expect(widgetCatalog.getWidget(TEST_ID, Locale.getDefault(), true)).andReturn(mockWidget);

		EasyMock.expect(tabServices.getWidgetTabsByTabId(TEST_ID))
			.andReturn(new ArrayList<WidgetTab>(Arrays.asList(widgetTab2)));	

		//
		// replay mock
		//
		EasyMock.replay(tabServices);
		EasyMock.replay(widgetCatalog);
		
		//
		// verify...
		//
		Map<String, WidgetCategory> availableWidgetsTree = widgetServices
				.getAvailableWidgetsTree(TEST_ID, true);
		
		assertNotNull(availableWidgetsTree);
		
		EasyMock.verify(tabServices);
		EasyMock.verify(widgetCatalog);
	}
	
	@Test
	public void testChangePosition() throws ServiceException {
		WidgetInst mockWidgetInst = new WidgetInst();

		EasyMock.expect(widgetInstDao.getByPK(TEST_ID)).andReturn(
				mockWidgetInst);
		widgetInstDao.update((WidgetInst) EasyMock.anyObject());
		EasyMock.replay(widgetInstDao);
		widgetServices.changePosition(TEST_ID, 1, 1, TEST_ID);

		EasyMock.verify(widgetInstDao);
	}

	@Test
	public void testChangeWidgetSetting() throws ServiceException {
		WidgetInst mockWidgetInst = new WidgetInst();

		EasyMock.expect(widgetInstDao.getByPK(TEST_ID)).andReturn(
				mockWidgetInst);
		widgetInstDao.update((WidgetInst) EasyMock.anyObject());
		EasyMock.replay(widgetInstDao);
		widgetServices.changeWidgetSetting(TEST_ID, TEST_ID);

		EasyMock.verify(widgetInstDao);
	}

	@Test
	public void testCreateWidgetInstance() throws ServiceException {

		EasyMock
				.expect(widgetInstDao.insert((WidgetInst) EasyMock.anyObject()))
				.andReturn(TEST_ID);
		EasyMock.replay(widgetInstDao);
		String resultId = widgetServices.createWidgetInstance(TEST_ID, TEST_ID,
				1, TEST_ID, false, TEST_ID, TEST_ID, ApplicationContext.INTERNAL_DEFAULT_ORG);

		Assert.assertEquals(TEST_ID, resultId);
		EasyMock.verify(widgetInstDao);
	}

	@Test
	public void testDeleteWidgetInstance() throws ServiceException {

		EasyMock.expect(widgetInstDao.remove(TEST_ID)).andReturn(0);
		EasyMock.replay(widgetInstDao);
		widgetServices.deleteWidgetInstance(TEST_ID);

		EasyMock.verify(widgetInstDao);
	}

	@Test
	public void testGetWidgetInstance() throws ServiceException {
		WidgetInst mockWidgetInst = new WidgetInst();
		WidgetInstance mockWidgetInstance = new WidgetInstance();
		EasyMock
				.expect(modelConverter.convertFromDaoWidgetInst(mockWidgetInst))
				.andReturn(mockWidgetInstance);
		EasyMock.expect(widgetInstDao.getByPK(TEST_ID)).andReturn(
				mockWidgetInst);

		EasyMock.replay(widgetInstDao);
		EasyMock.replay(modelConverter);
		WidgetInstance widgetInst = widgetServices.getWidgetInstance(TEST_ID);

		assertNotNull(widgetInst);
		EasyMock.verify(widgetInstDao);
		EasyMock.verify(modelConverter);
	}

	@Test
	public void testGetWidgetInstances() throws ServiceException {
		List<WidgetInst> mockWidgetInstList = new ArrayList<WidgetInst>();
		List<WidgetInstance> mockConvertedWidgetInstList = new ArrayList<WidgetInstance>();

		EasyMock
				.expect(
						modelConverter
								.convertFromDaoWidgetInstList(mockWidgetInstList))
				.andReturn(mockConvertedWidgetInstList);
		EasyMock.expect(
				widgetInstDao.getWidgetInstancesByTabInstanceId(TEST_ID,
						TEST_ID)).andReturn(mockWidgetInstList);

		EasyMock.replay(widgetInstDao);
		EasyMock.replay(modelConverter);
		List<WidgetInstance> widgetInstList = widgetServices
				.getWidgetInstances(TEST_ID, TEST_ID);

		assertNotNull(widgetInstList);
		EasyMock.verify(widgetInstDao);
		EasyMock.verify(modelConverter);
	}

	@Test
	public void testGetWidgetInstancesByTabInstanceId() throws ServiceException {
		List<WidgetInst> mockWidgetInstList = new ArrayList<WidgetInst>();
		WidgetInst widgetInst = new WidgetInst();
		widgetInst.setWidgetId(TEST_ID);
		widgetInst.setWidgetInstId(TEST_ID);
		mockWidgetInstList.add(widgetInst);
		EasyMock.expect(
				widgetInstDao.findWidgetsInstancesByTabInstance(TEST_ID))
				.andReturn(mockWidgetInstList);

		EasyMock.replay(widgetInstDao);
		List<WidgetInstance> widgetInstList = widgetServices
				.getWidgetInstancesByTabInstanceId(TEST_ID);

		Assert.assertEquals(1, widgetInstList.size());
		EasyMock.verify(widgetInstDao);
	}

	@Test
	public void testGetWidgetsInstancesForTabInstance() throws ServiceException {
		List<WidgetInst> mockWidgetInstList = new ArrayList<WidgetInst>();
		List<WidgetInstance> mockConvertedWidgetInstList = new ArrayList<WidgetInstance>();
		EasyMock.expect(
				widgetInstDao.findWidgetsInstancesByTabInstance(TEST_ID))
				.andReturn(mockWidgetInstList);
		EasyMock
				.expect(
						modelConverter
								.convertFromDaoWidgetInstList(mockWidgetInstList))
				.andReturn(mockConvertedWidgetInstList);

		EasyMock.replay(widgetInstDao);
		EasyMock.replay(modelConverter);
		List<WidgetInstance> widgetInstList = widgetServices
				.getWidgetsInstancesForTabInstance(TEST_ID);

		assertNotNull(widgetInstList);
		EasyMock.verify(widgetInstDao);
		EasyMock.verify(modelConverter);
	}

}
