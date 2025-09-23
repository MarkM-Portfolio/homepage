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

package com.ibm.lconn.homepage.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.lconn.homepage.utils.IUIServiceUtils;

public class UIServicesUtilsTest {

	private static IUIServiceUtils uiServiceUtils;
	protected static ClassPathXmlApplicationContext context;
	
	@BeforeClass
	public static void  initSpring() {
		String[] configLocations = {"spring/homepage/resources/ResourceBundleContext.xml"};
		context = new ClassPathXmlApplicationContext(configLocations);
		assertNotNull(context);
		
		uiServiceUtils = (IUIServiceUtils)context.getBean("uiServicesUtils");
		assertNotNull(uiServiceUtils);
	}
	
	@Test
	public void testPanelLabelTranslation() {
		Locale locale = new Locale("EN");
		String msg = null;
		String translated = null;
		
		msg = "%panel.update";
		translated = uiServiceUtils.getGlobalizedMsgFromCatalogUI(msg, locale);
		assertEquals("Updates Page", translated);
		
		msg = "%panel.widget";
		translated = uiServiceUtils.getGlobalizedMsgFromCatalogUI(msg, locale);
		assertEquals("Widgets Page", translated);
		
		
	}
	
}
