/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2009, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services.mbean.admin;

import static org.junit.Assert.assertEquals;

import java.lang.management.ManagementFactory;
import java.util.Map;

import javax.management.ObjectName;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;

/**
 * test for MBeanExporterImpl
 * 
 * @author Guo Du
 * 
 */
public class MBeanExporterTest extends SpringContextAwareTestCase {

    private String mbeanNameFormat = "TEST_DEFAULT_DOMAIN:cell=TEST_CELL_NAME,name=%s,node=TEST_NODE_NAME,process=TEST_PROCESS_NAME,type=LotusConnections";
    private static String CLASSNAME = MBeanExporterTest.class.getName();
    private Object[] args = null;
    private Class<?>[] argTypes = null;
    private String orgId = "00000000-0000-0000-0000-000000000000";

    @Before
    public void init() throws Exception {
        refreshDB(CLASSNAME, false);
    }

    @Override
    protected String[] getDBTableNames() {
        return new String[] { "METRIC_STAT" };
    }
    
    @SuppressWarnings("unchecked")
	@Test
	public void testInvokeMbeanService() throws Exception{
		String mbeanName=String.format(mbeanNameFormat, "HomepageMetricsService");
		ObjectName mbeanObjectName=new ObjectName(mbeanName);
		Map<String,Object> metrics = (Map<String,Object>)ManagementFactory.getPlatformMBeanServer().invoke(mbeanObjectName, "fetchMetrics", new Object[] {},new String[] {});


		assertEquals(14,metrics.size());	
		assertEquals((long)25,metrics.get("homepage.metric.totals.visitor"));//total
		assertEquals((long)1,metrics.get("homepage.metric.totals.visitor.today"));
		assertEquals((long)2,metrics.get("homepage.metric.totals.visitor.week"));
		assertEquals((long)3,metrics.get("homepage.metric.totals.visitor.month"));
		assertEquals("me widget:3;blogs:2;wiki:1;news:1;homepage:0;", metrics.get("homepage.metric.topWidgets"));
		assertEquals((long)22, metrics.get("homepage.metric.totals.enabled.widgets"));

		
		
	}
}
