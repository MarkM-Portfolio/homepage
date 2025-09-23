/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2011, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.dao.ibatis;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.homepage.dao.ibatis.HomepageActionRequiredDao;
import com.ibm.lconn.homepage.dao.interfaces.IHomepageActionRequiredDao;
import com.ibm.lconn.homepage.dao.interfaces.ISystemMetricsDao;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * JUnit test for the HomepageActionRequiredDao class.
 * 
 * @author Jim Antill
 *
 */
public class HomepageActionRequiredTest extends SpringContextAwareTestCase {

	@Autowired
	private IHomepageActionRequiredDao homepageActionRequiredDao;
	
	public void setHomepageStartupDao(IHomepageActionRequiredDao d) {
		this.homepageActionRequiredDao = d;
	}

	
	
	@Before
	public void init() {
		homepageActionRequiredDao = (IHomepageActionRequiredDao)getBean("homepageActionRequiredDao",IHomepageActionRequiredDao.class);
		assertNotNull(homepageActionRequiredDao);
	}
	
	@Test
	public void testActionRequiredCountMultipleFound() {
		long count = this.homepageActionRequiredDao.getActionRequiredTotalByExtId("11111111-1111-1111-1111-111111111111");
		long expected = 0;
		assertEquals(count, expected);
	}
	
	@Test
	public void testActionRequiredCountSingleFound() {
		long count = this.homepageActionRequiredDao.getActionRequiredTotalByExtId("22222222-2222-2222-2222-222222222222");
		long expected = 0;
		assertEquals(count, expected);
	}
	
	@Test
	public void testActionRequiredNoneFound() {
		long count = this.homepageActionRequiredDao.getActionRequiredTotalByExtId("33333333-3333-3333-3333-333333333333");
		long expected = 0;
		assertEquals(count, expected);
	}
	
	@After
	public void tearDown() {
		
	}

	@Override
	protected String[] getDBTableNames() {
		// Intentionally left blank
		return null;
	}

	
}