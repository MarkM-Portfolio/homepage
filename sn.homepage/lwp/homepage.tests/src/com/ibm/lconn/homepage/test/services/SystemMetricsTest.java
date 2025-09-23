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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.core.platform.ICPlatform;
import com.ibm.lconn.homepage.services.ISystemMetrics;
import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;


/**
 * 
 * @author Lorenzo Notarfonzo
 *
 */
public class SystemMetricsTest extends SpringContextAwareTestCase {
	
	private static String CLASSNAME = SystemMetricsTest.class.getName();
	
	@Autowired
	private ISystemMetrics systemMetrics;
	
	@Before
	public void init() throws Exception {
		this.systemMetrics = (ISystemMetrics)getBean("systemMetrics");
		refreshDB(CLASSNAME, true);
	}


	@Override
	protected String[] getDBTableNames() {
		return new String[]{"MT_ORGANIZATION", "PERSON", "WIDGET", "HP_TAB", "HP_UI", "HP_TAB_INST", "HP_WIDGET_TAB", "HP_WIDGET_INST", "METRIC_STAT"};
	}
	
	@Test
	public void testFetchMetrics(){
		ApplicationContext.setOrganizationId(ICPlatform.ORG_DEFAULT);
		@SuppressWarnings("rawtypes")
		Map metrics = systemMetrics.fetchMetrics();
		String[] metricsKeys = systemMetrics.getMetricKeyNameArray();
		int size = metricsKeys.length;
		assertEquals(14,metrics.size());
		
		assertEquals((long)25,metrics.get("homepage.metric.totals.visitor"));//total
		assertEquals((long)1,metrics.get("homepage.metric.totals.visitor.today"));
		assertEquals((long)2,metrics.get("homepage.metric.totals.visitor.week"));
		assertEquals((long)3,metrics.get("homepage.metric.totals.visitor.month"));
		assertEquals("me widget:3;blogs:2;wiki:1;news:1;homepage:0;", metrics.get("homepage.metric.topWidgets"));
		assertEquals((long)22, metrics.get("homepage.metric.totals.enabled.widgets"));
	}
	
	@Test
	public void testGetMetricValueForKey() {
		ApplicationContext.setOrganizationId(ICPlatform.ORG_DEFAULT);
		@SuppressWarnings("rawtypes")
		Map metrics = systemMetrics.fetchMetrics();
		assertEquals((long)2, systemMetrics.getMetricValueForKey("homepage.metric.totals.visitor.week"));
	}
	
	@Test
	public void testFetchMetrics_MT(){
		ApplicationContext.setOrganizationId("11111111-1111-1111-1111-111111111111");
		@SuppressWarnings("rawtypes")
		Map metrics = systemMetrics.fetchMetrics();
		String[] metricsKeys = systemMetrics.getMetricKeyNameArray();
		int size = metricsKeys.length;
		assertEquals(0,metrics.size());
		
		assertNull(metrics.get("homepage.metric.totals.visitor"));//total
		assertNull(metrics.get("homepage.metric.totals.visitor"));//total
		assertNull(metrics.get("homepage.metric.totals.visitor.today"));
		assertNull(metrics.get("homepage.metric.totals.visitor.week"));
		assertNull(metrics.get("homepage.metric.totals.visitor.month"));
		assertNull(metrics.get("homepage.metric.topWidgets"));
		assertNull(metrics.get("homepage.metric.totals.enabled.widgets"));
		ApplicationContext.unset();
	}	
	
}
