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

package com.ibm.lconn.homepage.test.services.mbean.admin;

import static org.junit.Assert.assertEquals;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.services.mbean.admin.impl.HomepageMetricsServiceMBean;
import com.ibm.lconn.homepage.test.TestHome;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class HomepageMetricsServiceTest extends SpringContextAwareTestCase {

	private static String CLASSNAME = HomepageMetricsServiceTest.class.getName();
	
	public static final String HOMEPAGE_TEST_HOME = TestHome.getPJ_HOME();
	
	@Autowired
	private HomepageMetricsServiceMBean homepageMetricsService;

	
	@Before
	public void init() throws Exception {
		this.homepageMetricsService = (HomepageMetricsServiceMBean)getBean("homepageMetricsService");
		Assert.assertNotNull(homepageMetricsService);
		refreshDB(CLASSNAME, true);
	}
	
	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION", "PERSON", "WIDGET", "HP_TAB", "HP_UI", "HP_TAB_INST", "HP_WIDGET_TAB", "HP_WIDGET_INST", "METRIC_STAT"};
	}
	

	@Test
    public void testFetchMetrics() {
		Map<String,Object> metrics = homepageMetricsService.fetchMetrics();
		assertEquals(14,metrics.size());
		
		assertEquals((long)25, metrics.get("homepage.metric.totals.visitor"));
		assertEquals((long)65, metrics.get("homepage.metric.totals.stories.added"));
		assertEquals("me widget:3;blogs:2;wiki:1;news:1;homepage:0;", metrics.get("homepage.metric.topWidgets"));
		//add some more here to test the counts for today, week and month
		assertEquals((long)25,metrics.get("homepage.metric.totals.visitor"));//total
		assertEquals((long)1,metrics.get("homepage.metric.totals.visitor.today"));
		assertEquals((long)2,metrics.get("homepage.metric.totals.visitor.week"));
		assertEquals((long)3,metrics.get("homepage.metric.totals.visitor.month"));
	
		assertEquals((long)22, metrics.get("homepage.metric.totals.enabled.widgets"));

	}
    

	@Test
    public void fetchMetric() {
		Object n = homepageMetricsService.fetchMetric("homepage.metric.totals.visitor.month");
		assertEquals((long)3,n);
	}
    
    
	@Test
    public void fetchMetricsFields() {
		String metricsUsers = "homepage.metric.totals.visitor";
		String metricsEnabledWidgets = "homepage.metric.totals.enabled.widgets";
		
		String[] metrics = new String[] {metricsUsers, metricsEnabledWidgets};
		
		Map<String,Object> metricsMap = homepageMetricsService.fetchMetricsFields(metrics);
		 
		assertEquals(2, metricsMap.size());
		assertEquals((long)25, metricsMap.get("homepage.metric.totals.visitor"));
		assertEquals((long)22, metricsMap.get("homepage.metric.totals.enabled.widgets"));
	}

}
