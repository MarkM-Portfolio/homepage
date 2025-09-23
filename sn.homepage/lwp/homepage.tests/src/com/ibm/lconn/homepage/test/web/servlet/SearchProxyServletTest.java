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

package com.ibm.lconn.homepage.test.web.servlet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import junit.framework.TestCase;

import org.junit.Test;

import com.ibm.lconn.homepage.web.servlet.SearchProxyServlet;

public class SearchProxyServletTest extends TestCase {

	private SearchProxyServlet servlet;
	private MockHttpURLConnection connection;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		servlet = new SearchProxyServlet();
		connection = new MockHttpURLConnection(null);
	}
	
	@Test
	public void testCheckForContentTypeGzipHeader() {
		connection.setContentEncoding("gzip");
		boolean test = servlet.checkForContentTypeGzipHeader(connection, "Content-Encoding");
		assertTrue(test);
	}
	
	@Test
	public void testCheckForContentTypeGzipHeader_notGzip() {
		connection.setContentEncoding("text");
		boolean test = servlet.checkForContentTypeGzipHeader(connection, "Content-Encoding");
		assertFalse(test);
	}
	
	@Test
	public void testCheckForContentTypeGzipHeader_wrongHeader() {
		connection.setContentEncoding("gzip");
		boolean test = servlet.checkForContentTypeGzipHeader(connection, "Content-Type");
		assertFalse(test);
	}
	
	public class MockHttpURLConnection extends HttpURLConnection {

		private String encoding;
		
		public MockHttpURLConnection(URL u) {
			super(u);
		}

		@Override
		public void disconnect() {			
		}

		@Override
		public boolean usingProxy() {
			return false;
		}

		@Override
		public void connect() throws IOException {
		}
		
		@Override
		public String getContentEncoding() {
			return encoding;
		}
		
		public void setContentEncoding(String encoding){
			this.encoding = encoding;
		}
		
	}
}
