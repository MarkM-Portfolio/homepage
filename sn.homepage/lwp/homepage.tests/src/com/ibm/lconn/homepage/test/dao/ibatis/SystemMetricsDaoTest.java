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

package com.ibm.lconn.homepage.test.dao.ibatis;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.homepage.dao.ibatis.SystemMetricsDao;
import com.ibm.lconn.homepage.dao.interfaces.ISystemMetricsDao;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * Expected values based directly on homepage dataset
 * 
 * @author John Reddin
 *
 */
public class SystemMetricsDaoTest extends SpringContextAwareTestCase {
	
	private static String CLASSNAME = SystemMetricsDaoTest.class.getName();
	
	@Autowired
	private ISystemMetricsDao systemMetricsDao;

	// test public methods not exposed @ interface level
	public void set_SystemMetricsDao(ISystemMetricsDao systemMetricsDao) {
		this.systemMetricsDao = systemMetricsDao;
	}

	@Before
	public void init() throws Exception {
		// load spring beans
        systemMetricsDao = (ISystemMetricsDao) getBean("systemMetricsDao",ISystemMetricsDao.class);
		Assert.assertNotNull(systemMetricsDao);
		refreshDB(CLASSNAME, false);
	}
	
	
	@Override
	protected String[] getDBTableNames() {
		String[] filenames = {"PERSON", "WIDGET", "HP_TAB", "HP_UI", "HP_TAB_INST", "HP_WIDGET_TAB", "HP_WIDGET_INST"};
		return filenames;
	}
	
	@Test
	public void testGetNumberUniqueVisit() {
		long expected = 5;
		Date begin = new Date(0);
		Date end = new Date();
		long result = systemMetricsDao.getNumberUniqueVisit(begin, end);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCountHPPerson() {
		long expected = 5;
		long result = systemMetricsDao.getCountHPPerson();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCountAllWidgets() {
		long expected = 13;
		long result = systemMetricsDao.getCountAllWidgets();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetCountEnabledWidgets() {
		long expected = 7;
		long result = systemMetricsDao.getCountEnabledWidgets();
		assertEquals(expected, result);
	}

	@Test
	public void testGetCountOpenWidgets() {
		long expected = 10;
		long result = systemMetricsDao.getCountOpenWidgets();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetStoriesAddedSince() {
		long expected = 0;
		Date begin;
		try {
			begin = new SimpleDateFormat("yyyymmdd").parse("2010-06-01");
			Date end = new Date();
			long result = systemMetricsDao.getStoriesAddedSince(begin, end);
			assertEquals(expected, result);
		} catch (ParseException e) {
			fail();
		}
	}
	
	@Test
	public void testGetStoriesAdded() {
		long expected = 0;
		long result = systemMetricsDao.getStoriesAdded();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetStoriesSaved() {
		long expected = 0;
		long result = systemMetricsDao.getStoriesSaved();
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetStoriesSavedSince() {
		long expected = 0;
		Date begin = new Date(0);
		Date end = new Date();
		long result = systemMetricsDao.getStoriesSavedSince(begin, end);
		assertEquals(expected, result);
	}
	
	@Test
	public void testGetPopularWidgets() {
		long expected = 4;
		List result = systemMetricsDao.getPopularWidgets();
		assertEquals(expected, result.size());
	}
	
	@Test
	public void testGetCountCustomizedPages() {
		long expected = 1;
		long result = systemMetricsDao.getCountCustomizedPages();
		assertEquals(expected, result);
	}
	
}
