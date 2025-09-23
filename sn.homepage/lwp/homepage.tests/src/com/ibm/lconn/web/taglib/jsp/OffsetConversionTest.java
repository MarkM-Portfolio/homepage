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

package com.ibm.lconn.web.taglib.jsp;

import junit.framework.TestCase;

public class OffsetConversionTest extends TestCase {

	public void testOffsetConversionNull() {
		assertEquals(0, JspFunctions.getTimezoneOffsetAsMinutes(null));
	}
	
	public void testOffsetConversionEmpty() {
		assertEquals(0, JspFunctions.getTimezoneOffsetAsMinutes(""));
	}
	
	public void testOffsetConversionNaN() {
		assertEquals(0, JspFunctions.getTimezoneOffsetAsMinutes("foo"));
	}
	
	public void testOffsetConversionPosZero() {
		assertEquals(0, JspFunctions.getTimezoneOffsetAsMinutes("0"));
	}
	
	public void testOffsetConversionNegZero() {
		assertEquals(0, JspFunctions.getTimezoneOffsetAsMinutes("-0"));
	}
	
	public void testOffsetConversionPosInt() {
		assertEquals(60, JspFunctions.getTimezoneOffsetAsMinutes("1"));
	}
	
	public void testOffsetConversionNegInt() {
		assertEquals(-60, JspFunctions.getTimezoneOffsetAsMinutes("-1"));
	}
	
	public void testOffsetConversionPosDec() {
		assertEquals(90, JspFunctions.getTimezoneOffsetAsMinutes("1.5"));
	}
	
	public void testOffsetConversionNegDec() {
		assertEquals(-90, JspFunctions.getTimezoneOffsetAsMinutes("-1.5"));
	}
}
