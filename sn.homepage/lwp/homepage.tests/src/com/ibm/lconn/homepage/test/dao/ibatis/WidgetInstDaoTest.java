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

package com.ibm.lconn.homepage.test.dao.ibatis;


import com.ibm.lconn.core.services.cre.remote.widget.WidgetCatalogServiceRemote;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.test.sandbox.CrudDaoTest;
import com.ibm.lconn.homepage.test.services.mock.MockWidgetServicesRemote;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;


public class WidgetInstDaoTest extends CrudDaoTest{

	private static String CLASSNAME = WidgetInstDaoTest.class.getName();


	@Before
	public void init() throws Exception {
		widgetInstDao = (IWidgetInstDao)getBean("widgetInstDao");
		assertNotNull(widgetInstDao);
		MockDeletedWidgetChecker.reset();
		refreshDB(CLASSNAME, false);
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","WIDGET","HP_UI","HP_TAB","HP_TAB_INST","HP_WIDGET_INST"};
	}

	private IWidgetInstDao widgetInstDao;


	protected ICrud getCrudDAO(){
		return widgetInstDao;
	}

	protected IModel getBaseObjectToInsert() {
		return MockBuilder.getWidgetInstToInsert();
	}

	protected IModel getBaseObjectToUpdate(IModel model) {
		return MockBuilder.getWidgetInstToUpdate((WidgetInst)model);
	}

	//@Before
	//public void resetMock() {
	//	MockWidgetServicesRemote.reset();
	//}

	//@Before
	//public void reset_mock_checker_between_tests() {
	//	MockDeletedWidgetChecker.reset();
	//}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindWidgetsInstancesByPersonId() throws Exception {
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		String personId = "11111111-1111-1111-1111-111111111111";
		String pageDefaultName = "%panel.update";
		List<WidgetInst> instances = null;

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		instances = widgetInstDao.findWidgetsInstancesByPersonId(personId, pageDefaultName);

		assertNotNull(instances);
		assertEquals(4, instances.size());

		//EasyMock.verify(mockChecker);
		//resetMock();

		MockWidgetServicesRemote.reset();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindWidgetsInstancesByPersonId_MTProtect() throws Exception {
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		String personId = "11111111-1111-1111-1111-111111111111";
		String pageDefaultName = "%panel.update";
		List<WidgetInst> instances = null;

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		instances = widgetInstDao.findWidgetsInstancesByPersonId(personId, pageDefaultName);
		assertEquals("MT Protection, using different orgId should not return anything.",0, instances.size());

		MockWidgetServicesRemote.reset();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserWidgetInstanceByWidgetId() throws Exception {
		//IDeletedWidgetChecker mockChecker = expectCallToMockChecker();
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();


		//String personId = "11111111-1111-1111-1111-111111111111";
		String pageDefaultName = "%panel.update";
		String widgetId = "33333333-3333-3333-3333-333333333333";
		String tabInstanceId = "11111111-1111-1111-1111-111111111111";
		List<WidgetInst> widgetInstances = null;

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		ApplicationContext.setIgnoreOrganizationId();
		widgetInstances = widgetInstDao.getWidgetInstancesByTabInstanceId(tabInstanceId, widgetId);
		ApplicationContext.unsetOrganizationIdOverride();

		EasyMock.verify(catalog);
		//resetMock();
		//EasyMock.verify(mockChecker);

		assertNotNull(widgetInstances);
		assertEquals(3, widgetInstances.size());
		assertEquals(widgetId, widgetInstances.get(0).getWidgetId());
		MockWidgetServicesRemote.reset();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetUserWidgetInstanceByWidgetId_MTProtect() throws Exception {
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();
		String pageDefaultName = "%panel.update";
		String widgetId = "33333333-3333-3333-3333-333333333333";
		String tabInstanceId = "11111111-1111-1111-1111-111111111111";
		List<WidgetInst> widgetInstances = null;

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		String tmpOrgId=ApplicationContext.getOrganizationId();
		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		widgetInstances = widgetInstDao.getWidgetInstancesByTabInstanceId(tabInstanceId, widgetId);
		assertEquals("MT Protection, using different orgId should not return anything.",0, widgetInstances.size());

		ApplicationContext.setOrganizationId(tmpOrgId);
		widgetInstances = widgetInstDao.getWidgetInstancesByTabInstanceId(tabInstanceId, widgetId);
		assertEquals("MT Protection, using original orgId should return data.",3, widgetInstances.size());

		MockWidgetServicesRemote.reset();
	}

	@Test
	public void testGetPersonIdByWidgetInstanceId() {
		String widgetInstanceId = "33333333-3333-3333-3333-333333333333";
		String expectedPersonId = "11111111-1111-1111-1111-111111111111";
		String personId = null;
		personId = widgetInstDao.getPersonIdByWidgetInstanceId(widgetInstanceId);
		assertNotNull(personId);
		assertEquals(expectedPersonId, personId);
	}

	@Test
	public void testGetPersonIdByWidgetInstanceId_MTProtect() {
		String widgetInstanceId = "33333333-3333-3333-3333-333333333333";
		String expectedPersonId = "11111111-1111-1111-1111-111111111111";
		String personId = null;
		String tmpOrgId=ApplicationContext.getOrganizationId();
		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		personId = widgetInstDao.getPersonIdByWidgetInstanceId(widgetInstanceId);
		assertNull("MT Protection, using different orgId should not return anything.", personId);

		personId = null;
		ApplicationContext.setOrganizationId(tmpOrgId);
		personId = widgetInstDao.getPersonIdByWidgetInstanceId(widgetInstanceId);
		assertEquals("MT Protection, using original orgId should return data.",expectedPersonId,personId);
	}

	@Test
	public void testGetCountOpenWidgets() {
		long res = -1;
		res = widgetInstDao.getCountOpenWidgets();
		assertTrue(res > 0);
	}

	@Test
	public void testGetCountOpenWidgets_MTProtect() {
		long res = -1;
		String tmpOrgId=ApplicationContext.getOrganizationId();
		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		res = widgetInstDao.getCountOpenWidgets();
		assertFalse("MT Protection, using different orgId should not return anything.", res > 0);

		res = -1;
		ApplicationContext.setOrganizationId(tmpOrgId);
		res = widgetInstDao.getCountOpenWidgets();
		assertTrue("MT Protection, using original orgId should return data.", res > 0);
	}

	@Test
	public void testRemoveByWidgetId() {
		String widgetId = "11111111-1111-1111-1111-111111111111";
		int widgetsInstancesBeforeDelate = widgetInstDao.selectAll().size();
		widgetInstDao.removeByWidgetId(widgetId);
		int widgetsInstancesAfterDelate = widgetInstDao.selectAll().size();
		assertTrue(widgetsInstancesBeforeDelate > widgetsInstancesAfterDelate);
		assertEquals(widgetsInstancesBeforeDelate -3 , widgetsInstancesAfterDelate);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindWidgetsInstancesByTabInstance() throws Exception {
		//IDeletedWidgetChecker mockChecker = expectCallToMockChecker();
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		String tabInstId = "11111111-1111-1111-1111-111111111111";
		ApplicationContext.setOrganizationId(ORG_ID_DEFAULT);
		List<WidgetInst> list = widgetInstDao.findWidgetsInstancesByTabInstance(tabInstId);

		EasyMock.verify(catalog);
		//resetMock();
		//EasyMock.verify(mockChecker);


		assertNotNull(list);
		assertTrue(!list.isEmpty());
		assertEquals(3, list.size());

		MockWidgetServicesRemote.reset();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindWidgetsInstancesByTabInstance_MTProtect() throws Exception {
		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.expectLastCall().anyTimes();
		EasyMock.replay(catalog);

		String tabInstId = "11111111-1111-1111-1111-111111111111";

		String tmpOrgId=ApplicationContext.getOrganizationId();
		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		List<WidgetInst> list = widgetInstDao.findWidgetsInstancesByTabInstance(tabInstId);
		assertEquals("MT Protection, using different orgId should not return anything.",0, list.size());

		MockWidgetServicesRemote.reset();
	}

	private static final String TO_FILTER = "66666666-6666-6666-6666-666666666666";
	private static final String TO_NOT_FILTER = "11111111-1111-1111-1111-111111111111";

	@SuppressWarnings("unchecked")
	@Test
	public void test_filterDeleted() throws Exception {
		IWidgetInstDao iDao = widgetInstDao;

		// mock check
		//IDeletedWidgetChecker mockChecker = MockDeletedWidgetChecker.getResetRealMockObject();
		//EasyMock.expect(
		//	mockChecker.getDeletedList((List<String>) EasyMock.anyObject()))
		//	.andReturn(Collections.singletonList(TO_FILTER));
		//EasyMock.replay(mockChecker);

		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.singletonList(TO_FILTER));
		EasyMock.replay(catalog);

		// setup input list
		WidgetInst toFilter = new WidgetInst(), toKeep = new WidgetInst();
		toFilter.setWidgetId(TO_FILTER);
		toKeep.setWidgetId(TO_NOT_FILTER);
		List<WidgetInst> filterCheckList = new ArrayList<WidgetInst>(Arrays.asList(toFilter, toKeep));

		iDao.filterDeleted(filterCheckList);

		// check mock
		//EasyMock.verify(mockChecker);
		EasyMock.verify(catalog);
		MockWidgetServicesRemote.reset();
		//resetMock();

		// check deleted
		assertNull(iDao.getByPK(TO_FILTER));

		// check list filtered
		assertEquals(1, filterCheckList.size());
		assertEquals(toKeep.getWidgetId(), filterCheckList.get(0).getWidgetId());


	}

	@SuppressWarnings("unchecked")
	@Test
	public void test_filterSingle() throws Exception {
		final String TO_TEST = "33333333-3333-3333-3333-333333333333";

		// mock check
		//final IDeletedWidgetChecker mockChecker = MockDeletedWidgetChecker.getResetRealMockObject();

		//WidgetInst toFilter = new WidgetInst();
		//toFilter.setWidgetId(TO_TEST);

		// first return empty
		//EasyMock.expect(
		//		mockChecker.getDeletedList((List<String>) EasyMock.anyObject()))
		//		.andReturn(Collections.<String>emptyList());

		// second return TO_TEST
		//EasyMock.expect(
		//	mockChecker.getDeletedList((List<String>) EasyMock.anyObject()))
		//	.andReturn(Collections.singletonList(TO_TEST));

		//EasyMock.replay(mockChecker);

		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.singletonList(TO_TEST));

		EasyMock.replay(catalog);

		// check exist
		assertNotNull(widgetInstDao.getByPK(TO_TEST));

		// now should get null
		assertNull(widgetInstDao.getByPK(TO_TEST));

		// verify
		//EasyMock.verify(mockChecker);

		EasyMock.verify(catalog);
		MockWidgetServicesRemote.reset();
		//resetMock();
	}

	private static final String[] IDS_TO_DELETE = {
		"44444444-4444-4444-4444-444444444444",
		"55555555-5555-5555-5555-555555555555"
	};

	@SuppressWarnings("unchecked")
	@Test
	public void test_removeByInstanceIds() throws Exception {

		WidgetCatalogServiceRemote catalog = MockWidgetServicesRemote.getWidgetCatalogService();

		EasyMock.expect(catalog.getDeletedWidgetsByIds((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList()).times(2);

		//EasyMock.expect(catalog.getDeletedWidgetsByIds(Arrays.asList(IDS_TO_DELETE))).andReturn(Collections.<String>emptyList());

		EasyMock.replay(catalog);

		// before
		for (String id : IDS_TO_DELETE) {
			assertNotNull(widgetInstDao.getByPK(id));
		}

		widgetInstDao.removeByInstanceIds(Arrays.asList(IDS_TO_DELETE));

		// after
		for (String id : IDS_TO_DELETE) {
			assertNull(widgetInstDao.getByPK(id));
		}

		EasyMock.verify(catalog);
		MockWidgetServicesRemote.reset();
		//resetMock();
	}


	/*
	@SuppressWarnings("unchecked")
	private IDeletedWidgetChecker expectCallToMockChecker() throws Exception {
		// mock check
		IDeletedWidgetChecker mockChecker = MockDeletedWidgetChecker.getResetRealMockObject();
		EasyMock.expect(mockChecker.getDeletedList((List<String>) EasyMock.anyObject())).andReturn(Collections.<String>emptyList());
		EasyMock.replay(mockChecker);
		return mockChecker;
	}

	*/
}
