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

import static junit.framework.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.ibm.icu.util.Calendar;
import com.ibm.lconn.hpnews.data.dao.interfaces.ICrud;
import com.ibm.lconn.homepage.dao.interfaces.IMetricStatDao;

import com.ibm.lconn.hpnews.data.model.IModel;
import com.ibm.lconn.homepage.test.sandbox.CrudDaoTest;

public class MetricStatDaoTest extends CrudDaoTest {
	
	private static String CLASSNAME = MetricStatDaoTest.class.getName();
	private IMetricStatDao metricStatDao;
	
	@Before
	public void init() throws Exception {
		metricStatDao = (IMetricStatDao) getBean("metricStatDao",IMetricStatDao.class);
		Assert.assertNotNull(metricStatDao);
		refreshDB(CLASSNAME, false );
		
	}

	@Override
	protected String[] getDBTableNames() {
		String[] filenames = {"MT_ORGANIZATION", "METRIC_STAT"};
		return filenames;
	};

	
	
	public void setMetricStatDao(IMetricStatDao metricStatDao) {
		this.metricStatDao = metricStatDao;
	}

	@Before
	public void initDao() {

	}

	@Override
	protected IModel getBaseObjectToInsert() {
		return MockBuilder.getMetricStatToInsert();
	
	}

	@Override
	protected IModel getBaseObjectToUpdate(IModel model) {
		return MockBuilder.getMetricStatToUpdate(model);
	}

	@Override
	protected ICrud getCrudDAO() {
		return this.metricStatDao;
	}
	@Test
	public void testSelectByDate(){
		Calendar c = Calendar.getInstance();
		c.set(2010, Calendar.MAY, 10, 12, 0, 0);
		Date d = c.getTime();
		List<IModel> metrics = metricStatDao.selectByDate(d);
		assertEquals(4, metrics.size());
		c = Calendar.getInstance();
	}


}
