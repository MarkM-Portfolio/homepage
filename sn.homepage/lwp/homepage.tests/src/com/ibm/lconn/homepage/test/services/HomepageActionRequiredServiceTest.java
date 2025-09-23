/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2012, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.test.services;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.dao.interfaces.IHomepageActionRequiredDao;
import com.ibm.lconn.homepage.services.impl.HomepageActionRequiredServiceImpl;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the HomepageActionRequired service.
 * 
 * Uses Easymock to create a mock of the DAO.
 * 
 * @author Jim Antill
 *
 */
public class HomepageActionRequiredServiceTest extends SpringContextAwareTestCase {
	
	@Autowired
	private IHomepageActionRequiredDao mockHomepageActionRequiredDao;
	@Autowired
	private HomepageActionRequiredServiceImpl homepageActionRequiredService;
	
	@Override
	public void init() throws Exception {
		homepageActionRequiredService = new HomepageActionRequiredServiceImpl();
		mockHomepageActionRequiredDao = EasyMock.createMock(IHomepageActionRequiredDao.class);
		homepageActionRequiredService.setHomepageActionRequiredDao(mockHomepageActionRequiredDao);
		
	}

	@Override
	protected String[] getDBTableNames() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Test
	public void testHomepageActionRequiredService() {
		// Mock a DAO response that will accept an external ID and return a value of 1.
		EasyMock.expect(mockHomepageActionRequiredDao.getActionRequiredTotalByExtId("1234")).andReturn(1l);
		EasyMock.replay(mockHomepageActionRequiredDao);

		long expected = 1;
		long count = homepageActionRequiredService.getActionRequiredTotalByExtId("1234");
		
		assertEquals(count, expected);
	}
	
	@After
	public void tearDown() {
		
	}


}
