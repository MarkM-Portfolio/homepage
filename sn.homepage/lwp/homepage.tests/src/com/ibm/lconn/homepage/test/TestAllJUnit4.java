/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)	
@Suite.SuiteClasses({  
    com.ibm.lconn.homepage.test.dao.ibatis.TestAllDaoIbatisJunit4.class,
    com.ibm.lconn.homepage.test.services.TestAllServices.class,
    com.ibm.lconn.homepage.test.utils.TestAllUtils.class,
    com.ibm.lconn.homepage.test.services.userlifecycle.TestAllUserLifeCycleServices.class,
    com.ibm.lconn.homepage.test.services.utils.TestAllServiceUtils.class,
    com.ibm.lconn.homepage.test.services.mbean.admin.TestAllMBean.class,
    com.ibm.lconn.homepage.test.web.TestAllWeb.class,
    com.ibm.lconn.web.taglib.TestAllTaglib.class
})

/**
 *  
 * @autor Lorenzo Notarfonzo
 */
public class TestAllJUnit4  {


}