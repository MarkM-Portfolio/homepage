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

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.hpnews.data.dao.interfaces.ILoginNameDao;
import com.ibm.lconn.hpnews.data.model.LoginName;


public class LoginNameDaoTest extends com.ibm.lconn.hpnews.test.data.dao.impl.ibatis.LoginNameDaoTest {
	
	public ILoginNameDao loginNameDaoSub;
	private static String CLASSNAME = LoginNameDaoTest.class.getName();
	
	
	@Before
	public void init() throws Exception{
		// load spring beans
        loginNameDaoSub = getBean("HPNEWS-DATABASE:loginNameDao",ILoginNameDao.class);
        refreshDB(CLASSNAME, false ); 
        super.init();
	}
	
	@Test
	public void testFindLoginNameByPersonId() {
	       
		int expected = 0;
		String personId = "55555555-5555-5555-5555-555555555556";
		
		List<LoginName> result = loginNameDaoSub.findLoginNameByPersonId(personId);
		assertEquals(expected, result.size());
	}




}
