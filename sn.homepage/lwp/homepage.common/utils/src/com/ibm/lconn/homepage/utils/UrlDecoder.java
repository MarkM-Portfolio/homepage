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

package com.ibm.lconn.homepage.utils;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.SEVERE;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;

public class UrlDecoder {
	
	private static String CLASS_NAME = UrlDecoder.class.getName();
	private static String ENCODING = "utf-8";
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	public static String decode(String url) {
		try {
			if(url != null) {
				if (logger.isLoggable(FINER))
					logger.entering(CLASS_NAME, "decode", new Object[] { url });
				
				return URLDecoder.decode(url, ENCODING);
			}
			else
				return "";
		} catch (UnsupportedEncodingException e) {
			logger.logp(SEVERE, CLASS_NAME, "decode", "unable to decode [" + url  +"]",  e);
			return url;
		}
	}
}
