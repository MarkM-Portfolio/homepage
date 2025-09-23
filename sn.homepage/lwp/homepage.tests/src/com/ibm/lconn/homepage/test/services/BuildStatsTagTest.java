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

package com.ibm.lconn.homepage.test.services;

import static org.junit.Assert.fail;

import java.util.MissingResourceException;

import org.junit.Before;
import org.junit.Test;

import com.ibm.lconn.homepage.test.sandbox.SpringContextAwareTestCase;
import com.ibm.lconn.web.taglib.jsp.BuildNumberTag;
//import com.ibm.lconn.web.taglib.jsp;

public class BuildStatsTagTest extends SpringContextAwareTestCase {
	
	BuildNumberTag buildNumberTag;
	@Before
	public void setBuildStats() {
		buildNumberTag = new BuildNumberTag();
	}
	
	@Test
	public void testGetBuildVersion() {
		try {
			buildNumberTag.getBuildString();
		}catch(MissingResourceException ep){
			org.junit.Assert.assertTrue(true);
		}catch(Exception e){
			e.printStackTrace();
			fail();
		}
	}

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String[] getDBTableNames() {
		// TODO Auto-generated method stub
		return null;
	}

}
