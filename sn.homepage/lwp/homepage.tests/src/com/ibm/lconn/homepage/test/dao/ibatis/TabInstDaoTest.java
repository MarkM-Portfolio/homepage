/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.dao.ibatis;


import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.test.sandbox.CrudDaoTest;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.logging.Logger;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class TabInstDaoTest extends CrudDaoTest {


	private ITabInstDao tabInstDao;
	private static String CLASSNAME = TabInstDaoTest.class.getName();
	private static Logger logger = Logger.getLogger(CLASSNAME);

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","HP_UI","HP_TAB","HP_TAB_INST"};
	}

	@Before
	public void init() throws Exception {
		tabInstDao = (ITabInstDao)getBean("tabInstDao");
		assertNotNull(tabInstDao);
		refreshDB(CLASSNAME, false );
	}

	protected ICrud getCrudDAO(){
		return tabInstDao;
	}

	protected IModel getBaseObjectToInsert() {
		return MockBuilder.getUIPageToInsert();
	}

	protected IModel getBaseObjectToUpdate(IModel model) {
		return MockBuilder.getUIPageToUpdate((TabInst)model);
	}

	@Test
	public void testGetPersonUIPages() {
		String personId = null;
		List<TabInst> pages = null;
		personId = "11111111-1111-1111-1111-111111111111";
		try {
			pages = tabInstDao.getPersonUIPages(personId);
		}
		catch (Exception e) {
			fail();
		}

		assertNotNull(pages);
		assertEquals(2, pages.size());
		//assertEquals("it_IT", pages.get(0).getPersonLang());
		//assertEquals("it_IT", pages.get(1).getPersonLang());
	}

	@Test
	public void testGetPersonUIPages_MTProtect() {
		String personId = "11111111-1111-1111-1111-111111111111";
		List<TabInst> pages = null;

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		pages = tabInstDao.getPersonUIPages(personId);
		assertEquals("Assert that data cannot be retrieved for the current organization.",0, pages.size());
	}

	@Test
	public void testGetPersonUIPage() {
		String personId = null;
		TabInst page = null;
		personId = "11111111-1111-1111-1111-111111111111";
		try {
			page = tabInstDao.getPersonUIPageByDefaultName(personId, "%panel.update");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail();
		}

		assertNotNull(page);
		//assertEquals("it_IT", page.getPersonLang());
	}

	@Test
	public void testGetPersonUIPage_MTProtect() {
		TabInst page = null;
		String personId = "11111111-1111-1111-1111-111111111111";

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		page = tabInstDao.getPersonUIPageByDefaultName(personId, "%panel.update");
		assertNull(page);
	}
}
