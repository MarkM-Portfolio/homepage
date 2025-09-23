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


import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.homepage.test.sandbox.CrudDaoTest;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.logging.Logger;

import static junit.framework.Assert.*;
import static junit.framework.Assert.assertEquals;

public class UIDaoTest extends CrudDaoTest {

	private static String CLASSNAME = UIDaoTest.class.getName();
	private IUIDao uiDao;
	private static Logger logger = Logger.getLogger(CLASSNAME);


	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","HP_UI"};
	}

	@Before
	public void init() throws Exception {
		uiDao = (IUIDao)getBean("uiDao");
		assertNotNull(uiDao);
		refreshDB(CLASSNAME, true);
	}

	protected ICrud getCrudDAO(){
		return uiDao;
	}

	protected IModel getBaseObjectToInsert() {
		return MockBuilder.getUIToInsert();
	}

	protected IModel getBaseObjectToUpdate(IModel model) {
		return MockBuilder.getUIToUpdate((UI)model);
	}

	@Test
	public void testUpdateLang(){
		UI ui = MockBuilder.getUIToInsert();
		String pk = null;
		String newLocale = "fr_FR";

		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLanguage(newLocale, ui);
		assertEquals("One record only to be updated", 1,count);

		assertNotNull(pk);
		ui = (UI) uiDao.getByPK(pk);
		//System.out.println("----->"+ui);
		assertEquals(ui.getPersonLang(),newLocale);

	}

	@Test
	public void testUpdateLangByPersonId(){
		logger.fine(CLASSNAME + " testUpdateLangByPersonId");
		UI ui = MockBuilder.getUIToInsert();
		//ui.setUiId("66666666-6666-6666-6666-666666666661");
		String pk = null;
		String newLocale = "de";

		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLanguageByPersonId(ui.getPersonId(), newLocale);
		assertEquals("One record only to be updated", 1,count);

		assertNotNull(pk);
		ui = (UI) uiDao.getByPK(pk);
		assertEquals(ui.getPersonLang(),newLocale);
	}

	@Test
	public void testUpdateLangByPersonId_MTProtect(){
		UI ui = MockBuilder.getUIToInsert();
		String pk = null;
		String newLocale = "de";
		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLanguageByPersonId(ui.getPersonId(), newLocale);
		assertEquals("One record only to be updated", 1,count);

//		ApplicationContext.setOrganizationId(ORG_ID_ONE);
//		count = uiDao.updateLanguageByPersonId(ui.getPersonId(), newLocale);
//		assertEquals("MT Protection, no row should be updated", 1, count);
	}

	@Test
	public void testUpdateLastVisitByPersonId(){
		UI ui = MockBuilder.getUIToInsert();
		//ui.setUiId("66666666-6666-6666-6666-666666666662");
		String pk = null;
		Date now = new Date();

		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLastVisitByPersonId(ui.getPersonId(), now);
		assertEquals("One record only to be updated", 1,count);
		assertNotNull(pk);
		ui = (UI) uiDao.getByPK(pk);
		assertEquals(ui.getLastVisit(),now);
	}

	@Test
	public void testUpdateLastVisitByPersonId_MTProtect(){
		UI ui = MockBuilder.getUIToInsert();
		String pk = null;
		Date now = new Date();
		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLastVisitByPersonId(ui.getPersonId(), now);
		assertEquals("One record only to be updated", 1,count);

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		count = uiDao.updateLastVisitByPersonId(ui.getPersonId(), now);
		assertEquals("MT Protection, no row should be updated", 0, count);
	}

	@Test
	public void testUpdateLangAndLastVisitByPersonId(){
		UI ui = MockBuilder.getUIToInsert();
		//ui.setUiId("66666666-6666-6666-6666-666666666663");
		String pk = null;
		String newLocale = "de";
		Date now = new Date();

		pk = this.uiDao.insert(ui);

		int count = uiDao.updateLanguageAndLastVisitByPersonId(ui.getPersonId(), newLocale, now);
		assertEquals("One record only to be updated", 1,count);

		assertNotNull(pk);
		ui = (UI) uiDao.getByPK(pk);
		assertEquals(ui.getPersonLang(),newLocale);
		assertEquals(ui.getLastVisit(), now);
}

	@Test
	public void testUpdateLangAndLastVisitByPersonId_MTProtect(){
		UI ui = MockBuilder.getUIToInsert();
		this.uiDao.insert(ui);
		String newLocale = "de";
		Date now = new Date();

		int count = uiDao.updateLanguageAndLastVisitByPersonId(ui.getPersonId(), newLocale, now);
		assertEquals("One record only to be updated", 1,count);

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		count = uiDao.updateLanguageAndLastVisitByPersonId(ui.getPersonId(), newLocale, now);
		assertEquals("MT Protection, no row should be updated", 0, count);
	}


	@Test
	public void testGetUserUIByPersonId(){
		String personId = "11111111-1111-1111-1111-111111111111";
		UI ui = null;
		ui = uiDao.getUserUIByPersonId(personId);
		assertNotNull(ui);
		assertEquals("it_IT",ui.getPersonLang());
		assertEquals(true,ui.isWelcomeMode());
		assertEquals(true, ui.isShowDisabledWidgets());
		assertEquals(personId, ui.getPersonId());
	}

	@Test
	public void testGetUserUIByPersonId_MTProtect(){
		String personId = "11111111-1111-1111-1111-111111111111";
		UI ui = null;

		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		ui = uiDao.getUserUIByPersonId(personId);
		assertNotNull("MT Protection, no data should be returned", ui);
	}

	@Test
	public void testDisableEnableWelcomeMode() {

		UI ui = null;

		ui = MockBuilder.getUIToInsert();
		ui.setWelcomeMode(false);
		//ui.setUiId("66666666-6666-6666-6666-666666666664");

		String pk = null;
		pk = uiDao.insert(ui);
		assertNotNull(pk);
		ui = (UI)uiDao.getByPK(pk);
		assertNotNull(ui);

		assertEquals(false,ui.getWelcomeMode());
	}

	@Test
	public void testCountNumberOfUniqueVisitors() {
		int n = uiDao.countNumberOfUniqueVisitors();
		assertEquals(5, n);
	}

	@Test
	public void testCountNumberOfUniqueVisitors_MTProtect() {
		ApplicationContext.setOrganizationId(ORG_ID_ONE);
		int n = uiDao.countNumberOfUniqueVisitors();
		assertEquals("MT Protection, no data should be returned", 0,n);
	}

	@Test
	public void testResetWelcomeModeAll() {
		int result = uiDao.resetWelcomeModeFlag(null);

		assertTrue(result>0);
	}

	@Test
	public void testResetWelcomeModeUser() {
		String personId = "11111111-1111-1111-1111-111111111111";
		int result = uiDao.resetWelcomeModeFlag(personId);

		assertEquals(result,1);
	}



}
