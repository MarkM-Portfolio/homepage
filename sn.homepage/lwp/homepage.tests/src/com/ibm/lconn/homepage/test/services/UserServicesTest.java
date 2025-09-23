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
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.core.services.cre.remote.widget.model.TabType;
import com.ibm.lconn.homepage.dao.ibatis.WidgetInstDao;
import com.ibm.lconn.homepage.dao.interfaces.IDeletedWidgetChecker;
import com.ibm.lconn.homepage.dao.interfaces.ITabInstDao;
import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.dao.interfaces.IWidgetInstDao;
import com.ibm.lconn.homepage.dao.model.TabInst;
import com.ibm.lconn.homepage.dao.model.UI;
import com.ibm.lconn.homepage.dao.model.WidgetInst;
import com.ibm.lconn.homepage.model.User;
import com.ibm.lconn.homepage.model.UserUI;
import com.ibm.lconn.homepage.services.IUserServices;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.test.dao.ibatis.MockDeletedWidgetChecker;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;


public class UserServicesTest extends SpringContextAwareTestCase {
	
	@Autowired
	private IUserServices userServices;
	@Autowired
	private IWidgetInstDao widgetInstDao;
	@Autowired
	private ITabInstDao tabInstDao;
	@Autowired
	private IUIDao uiDao;
	private static String CLASSNAME = UserServicesTest.class.getName();
	
	@Before
	public void init() throws Exception {
		this.userServices = (IUserServices)getBean("userServices");	
		this.widgetInstDao = (IWidgetInstDao)getBean("widgetInstDao");
		this.tabInstDao = (ITabInstDao)getBean("tabInstDao");
		this.uiDao = (IUIDao)getBean("uiDao");
		ApplicationContext.setOrganizationId(ICPlatform.ORG_ONPREM);
		refreshDB(CLASSNAME, false );		
	}

	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION","PERSON","WIDGET","HP_UI", "HP_TAB","LOGINNAME","HP_TAB_INST", "HP_WIDGET_INST"};
	}
	
	
	public IUserServices getUserServices() {
		return userServices;
	}


	@Test
	public void testNotNullUserServices() {
		Assert.assertNotNull(userServices);
	}
	
	@Test
	public void testGetUserByLoginName() {
		User user = null;
		try {
			user = userServices.getUserLoginName("vincent.burckhardt");			
		}
		catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(user);		
		assertEquals("vincent@google.com", user.getMail());
	}
	
	@Test
	public void testUpdateWelcomeMode() {
		String internalId = "22222222-2222-2222-2222-222222222222";
		UserUI ui = null;
		try {
			ui = userServices.getUserUIByPersonId(internalId);
			assertTrue(ui.isWelcomeMode());

			userServices.updateWelcome(internalId, false);
			ui = userServices.getUserUIByPersonId(internalId);
			assertFalse(ui.isWelcomeMode());

			userServices.updateWelcome(internalId, true);
			ui = userServices.getUserUIByPersonId(internalId);
			assertTrue(ui.isWelcomeMode());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testDisableAdminTestMode(){
		String internalId = "11111111-1111-1111-1111-111111111111";
		UserUI ui = null;
		try {
			ui = userServices.getUserUIByPersonId(internalId);
			assertTrue(ui.getShowDisabledWidgets());

			userServices.disableAdminTestMode(internalId);
			ui = userServices.getUserUIByPersonId(internalId);
			assertFalse(ui.getShowDisabledWidgets());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testEnableAdminTestMode(){
		String internalId = "22222222-2222-2222-2222-222222222222";
		UserUI ui = null;
		try {
			ui = userServices.getUserUIByPersonId(internalId);
			assertFalse(ui.getShowDisabledWidgets());

			userServices.enableAdminTestMode(internalId);
			ui = userServices.getUserUIByPersonId(internalId);
			assertTrue(ui.getShowDisabledWidgets());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetUserUIByPersonId() {
		String personId = "11111111-1111-1111-1111-111111111111";
		UserUI ui = null;
		try {
			ui = userServices.getUserUIByPersonId(personId);
			assertNotNull(ui);
			assertEquals(personId, ui.getPersonId());
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testGetUserUIByPersonId_orgIdUpdate() {
		MockDeletedWidgetChecker.reset();
		IDeletedWidgetChecker cachedChecker = this.widgetInstDao.getDeletedWidgetChecker();
		this.widgetInstDao.setDeletedWidgetChecker(MockDeletedWidgetChecker.getMockChecker());
		ApplicationContext.setOrganizationId(ICPlatform.ORG_ONPREM);
		String personId = "11111111-1111-1111-1111-111111111111";
		String newOrgId = "asdf";
		UserUI ui = null;
		try {
			TabInst tabInst = tabInstDao.getPersonUIPageByDefaultName(personId, TabType.WIDGET.getDBValue());
			assertEquals(ICPlatform.ORG_DEFAULT, tabInst.getOrganizationId());
			tabInst.setOrganizationId(newOrgId);
			tabInstDao.update(tabInst);
			UI uiDaoObject = uiDao.getUserUIByPersonId(personId);
			uiDaoObject.setOrganizationId(newOrgId);
			uiDao.update(uiDaoObject);
			
			List<WidgetInst> widgetInsts = widgetInstDao.findWidgetsInstancesByTabInstance(personId);
			for (WidgetInst widgetInst : widgetInsts) {
				widgetInst.setOrganizationId(newOrgId);
				widgetInstDao.update(widgetInst);
			}
			
			ApplicationContext.setIgnoreOverride();
			tabInst = tabInstDao.getPersonUIPageByDefaultName(personId, TabType.WIDGET.getDBValue());
			ApplicationContext.unsetOrganizationIdOverride();
			assertEquals(newOrgId, tabInst.getOrganizationId());

			//run userServices which should set org back to the person id
			ui = userServices.getUserUIByPersonId(personId);
			assertNotNull(ui);
			assertEquals(personId, ui.getPersonId());
			assertEquals(ICPlatform.ORG_DEFAULT, ui.getOrganizationId());
			
			tabInst = tabInstDao.getPersonUIPageByDefaultName(personId, TabType.WIDGET.getDBValue());
			assertEquals(ICPlatform.ORG_DEFAULT, tabInst.getOrganizationId());
			
			widgetInsts = widgetInstDao.findWidgetsInstancesByTabInstance(personId);
			for (WidgetInst widgetInst : widgetInsts) {
				assertEquals(ICPlatform.ORG_DEFAULT, widgetInst.getOrganizationId());
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
			fail();
		}
		finally{
			this.widgetInstDao.setDeletedWidgetChecker(cachedChecker);
		}
		try{
			refreshDB(CLASSNAME, true );
		}catch(Exception ex){
			
		}
		
	}

}
