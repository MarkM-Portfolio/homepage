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

package com.ibm.lconn.homepage.test.utils;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

import com.ibm.lconn.homepage.utils.UrlDecoder;

public class UrlDecoderTest {

	@Test
	public void testDecode() {
		String expected = "";
		String url = null;
		String result = UrlDecoder.decode(url);
		assertEquals(expected, result);
		
		url = "+http://www.ibm.com+";
		expected = " http://www.ibm.com ";
		result = UrlDecoder.decode(url);
		assertEquals(expected, result);
	}
	
}

