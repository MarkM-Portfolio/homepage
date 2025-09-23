/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.services;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.ibm.lconn.homepage.services.config.gettingstarted.ComponentUrlInterpolator;
import com.ibm.lconn.homepage.test.TestHome;

/**
 * @author scrawford
 *
 */
public class ComponentUrlInterpolatorTest {

	private ComponentUrlInterpolator interpolator;
	
	@Before
	public void init() throws Exception {
		this.interpolator = new ComponentUrlInterpolator();
		System.setProperty("test.config.files", TestHome.getTestConfigFilesDir());
	}
	
	@Test
	public void testLookup(){		
		assertEquals ("{homepage.test}", this.interpolator.lookup("homepage.test"));
	}
	
	@Test
	public void testConvertGettingStartConfigToUrl(){		
		String result = ComponentUrlInterpolator.convertGettingStartConfigToUrl("{homepage.secure}/web/action");
		assertEquals("https://junit.dyn.webahead.ibm.com:9443/homepage/web/action", result);
		
		result = ComponentUrlInterpolator.convertGettingStartConfigToUrl("{homepage.unsecure}/web/action");
		assertEquals("http://junit.dyn.webahead.ibm.com:9080/homepage/web/action", result);
	}
	
	@Test
	public void testConvertGettingStartConfigToUrl_fail(){		
		String result = ComponentUrlInterpolator.convertGettingStartConfigToUrl("{homadfaepage.secure}/web/action");
		assertEquals("{homadfaepage.secure}/web/action", result);
	}
	
}
