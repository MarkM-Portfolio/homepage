/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.spring.context;

import java.util.Locale;

import org.springframework.context.support.ResourceBundleMessageSource;

public class LocalizableMessageSource extends ResourceBundleMessageSource implements MessageSource {
	
	private Locale defaultLocale=Locale.getDefault();

	public String getMessage(String code, Object... args) {
		return getMessage(code, args, defaultLocale);
	}

	public String getLocaleMessage(Locale locale,String code, Object... args) {
		return getMessage(code, args, locale);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}
	
	
}
