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

package com.ibm.lconn.homepage.test.web.action.customization;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ com.ibm.lconn.homepage.test.web.action.customization.SaveCustomizationControllerTest.class,
        com.ibm.lconn.homepage.test.web.action.customization.RearrangeLayoutControllerTest.class, com.ibm.lconn.homepage.test.web.action.customization.LegacyCustomizationControllerTest.class })
public class TestAllCustomizationController {

}
