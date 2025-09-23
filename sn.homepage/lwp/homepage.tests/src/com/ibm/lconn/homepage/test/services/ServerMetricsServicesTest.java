/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import com.ibm.lconn.homepage.dao.model.MetricStat;
import com.ibm.lconn.homepage.model.Metric;
import com.ibm.lconn.homepage.model.ServerMetricsData;
import com.ibm.lconn.homepage.services.impl.ServerMetricsServicesImpl;
import com.ibm.lconn.homepage.test.dao.ibatis.MockBuilder;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * To test server metrics services
 *
 * @author Yishan Sun
 *
 */
public class ServerMetricsServicesTest extends SpringContextAwareTestCase {

	@Autowired
	private ServerMetricsServicesImpl serverMetricsServices;
	private static String CLASSNAME = ServerMetricsServicesTest.class.getName();

	@Before
	public void init() throws Exception {
		this.serverMetricsServices = (ServerMetricsServicesImpl) getBean("serverMetricsServices");
		serverMetricsServices.setToday(new Date());
		refreshDB(CLASSNAME, false);
	}


	@Override
	protected String[] getDBTableNames() {
		return new String[]{"METRIC_STAT"};
	}




	public ServerMetricsServicesImpl getServerMetricsServices() {
		return serverMetricsServices;

	}
	@Test
	public void testIsUpdateRequired(){
		Calendar now = Calendar.getInstance();
		Calendar lastupdate = Calendar.getInstance();
		now.setTime(new Date());
		boolean updateRequired;
		updateRequired = serverMetricsServices.isUpdateRequired(now, lastupdate);
		//this should return false as the time the service starts is used as the reference point
		//and this test should be run on the same day as the service is started
		Assert.assertFalse("Should be false 1",updateRequired);
		now.add(Calendar.DATE, +1);//this date is now tommorrow so a
		updateRequired = serverMetricsServices.isUpdateRequired(now, lastupdate);
		Assert.assertTrue("Should be true 1",updateRequired);
		now.add(Calendar.DATE, -2);//a day before, so no update should be required
		updateRequired = serverMetricsServices.isUpdateRequired(now, lastupdate);
		Assert.assertFalse("Should be false 2",updateRequired);

	}

	@Test
	public void testMetricMap(){
		Calendar c = Calendar.getInstance();
		c.set(2010, Calendar.MAY, 10, 12, 0, 0);
		Date d = c.getTime();
		Map<String, Object> metrics = serverMetricsServices.getMetricMap(d);
		assertEquals(metrics.size(),14);
		assertEquals((long)25,metrics.get("homepage.metric.totals.visitor"));//total
		assertEquals((long)1,metrics.get("homepage.metric.totals.visitor.today"));
		assertEquals((long)2,metrics.get("homepage.metric.totals.visitor.week"));
		assertEquals((long)3,metrics.get("homepage.metric.totals.visitor.month"));
		assertEquals("me widget:3;blogs:2;wiki:1;news:1;homepage:0;", metrics.get("homepage.metric.topWidgets"));
		assertEquals((long)22, metrics.get("homepage.metric.totals.enabled.widgets"));
	}
	@Test
	public void testMetricKeys(){
		Calendar c = Calendar.getInstance();
		c.set(2010, Calendar.MAY, 10, 12, 0, 0);
		Date d = c.getTime();
		List<String> keys = serverMetricsServices.getMetricKeys(d);
		assertEquals(keys.size(),27);
		assertTrue(keys.contains("homepage.metric.totals.visitor.week"));
		assertTrue(keys.contains("homepage.metric.totals.enabled.widgets"));

	}
	@Test
	public void testSplitTop5MetricList(){
		String top5List = "blogs:5;activities:4;wikis:3;files:2;news:1;";
		String expectedOrder = "blogs,activities,wikis,files,news";
		Map<String, Long> top5Map = serverMetricsServices.splitTop5String(top5List);
		assertTrue(top5Map.containsKey("blogs"));
		assertEquals(top5Map.size(), 5);
		assertEquals((long)top5Map.get("blogs"), (long)5);
		assertTrue(top5Map.containsKey("news"));
		assertEquals((long)top5Map.get("news"), (long)1);
		//check to see that they are in order
		Set<String> keys = top5Map.keySet();
		StringBuffer buf = new StringBuffer();
		for(String key : keys){
			buf.append(key);
			buf.append(",");
		}
		buf.deleteCharAt(buf.length()-1);
		String actualOrder = buf.toString();
		assertEquals(expectedOrder, actualOrder);

	}
	@Test
	public void testSeparatingMetric(){
		List<ServerMetricsData> data= new ArrayList<ServerMetricsData>();
		MetricStat metric = null;
		//try the three different types of metric, 0=trend over time, 1= top5 list, 2= scalar value
		metric = MockBuilder.getMetricStat();
		metric.setMetricType(Metric.NO_STORIES_SAVED.getType());//trend type
		serverMetricsServices.seperateAndAddMetrics(data, metric);
		Assert.assertNotNull(data);
		assertEquals(data.size(), 4);
		ServerMetricsData singleStat = data.get(2);
		assertEquals(singleStat.getKey(), "homepage.metric.totals.stories.saved.week");
		assertEquals((long)singleStat.getScalarData(), 3l);
		assertEquals(singleStat.getType(), "scalar");
	//test the scalar value
		metric = MockBuilder.getMetricStat();
		metric.setMetricType(Metric.NO_AVAIL_WIDGETS.getType());//scalar type
		serverMetricsServices.seperateAndAddMetrics(data, metric);
		Assert.assertNotNull(data);
		assertEquals(data.size(), 5);
		singleStat = data.get(4);
		assertEquals(singleStat.getKey(), "homepage.metric.totals.stories.saved");
		assertEquals((long)singleStat.getScalarData(), 25l);
		assertEquals(singleStat.getType(), "scalar");
	//test the list value
		metric = MockBuilder.getMetricStat();
		metric.setMetricType(Metric.POPULAR_WIDGETS.getType());//list type
		serverMetricsServices.seperateAndAddMetrics(data, metric);
		Assert.assertNotNull(data);
		assertEquals(data.size(), 6);
		singleStat = data.get(5);
		assertEquals(singleStat.getKey(), "homepage.metric.totals.stories.saved");
		assertEquals(singleStat.getType(), "list");
		Map<String, Long> top5Map = singleStat.getTop5list();
		Assert.assertNotNull(top5Map);
		assertTrue(top5Map.containsKey("news"));
		assertEquals((long)top5Map.get("news"), (long)1);


	}
	@Test
	public void testGetMetricsByCategory(){
		List<ServerMetricsData> widgetListStats = serverMetricsServices.getServerMetricsData(Metric.NO_ENABLED_WIDGETS.getType());
		List<ServerMetricsData> userStats = serverMetricsServices.getServerMetricsData(Metric.NO_VISITORS.getType());
		List<ServerMetricsData> savedStoryStats = serverMetricsServices.getServerMetricsData(Metric.NO_STORIES.getType());
		assertEquals(widgetListStats.size(), 1);
		assertEquals(userStats.size(), 20);
		assertEquals(savedStoryStats.size(),4);
		ServerMetricsData stat1 = widgetListStats.get(0);
		ServerMetricsData stat2 = userStats.get(17);
		ServerMetricsData stat3 = savedStoryStats.get(2);
		assertEquals(stat1.getKey(), Metric.NO_ENABLED_WIDGETS.getResourceKey());
		assertEquals((long)stat1.getScalarData(), (long)22);
		assertEquals("res.today",stat2.getKey());
		assertEquals((long)stat2.getScalarData(), (long)1);
		assertEquals("homepage.metric.totals.stories.added.week",stat3.getKey());
		assertEquals((long)stat3.getScalarData(), (long)4);


	}




}
