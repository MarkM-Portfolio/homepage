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

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)	
@Suite.SuiteClasses(
		{
			com.ibm.lconn.hpnews.test.data.dao.impl.ibatis.PersonDaoTest.class,
			com.ibm.lconn.hpnews.test.data.dao.impl.ibatis.HomepageSchemaDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.SystemMetricsDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.LoginNameDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.TabInstDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.UIDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.WidgetInstDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.HomepageSchemaDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.MetricStatDaoTest.class,
			com.ibm.lconn.homepage.test.dao.ibatis.HomepageActionRequiredTest.class
		} 
)

public class TestAllDaoIbatisJunit4 {

	
}
