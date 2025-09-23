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

package com.ibm.lconn.web.taglib.i18n;

import java.io.File;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.homepage.model.Component;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.web.taglib.jsp.AbstractValueTransformer;

// adapted from Dogear
public class HelpLangUrl extends AbstractValueTransformer<Object> {

	private static final long serialVersionUID = 7529298660404490281L;
	private static String CLASS_NAME = HelpLangUrl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private static final String HELP_URI = "/topic/com.ibm.lotus.connections.homepage.help";
	
	/**
	 * TODO: REMOVE
	 */
	private static final String HELPPATH = "/help/doc";
	private static Set<String> helpDirectories = null;

	protected IConfigurationService getConfigurationService() {
		ServletContext sc = pageContext.getServletContext();
		return (IConfigurationService) WebApplicationContextUtils.getWebApplicationContext(sc).getBean("configurationService");
	}
	
	@Override
	protected String transform(Object pathRemainder) {
		
		String helpComponentUrl="";
		try {
			helpComponentUrl = getConfigurationService().getComponentUrl(Component.HELP.getName(), pageContext.getRequest().isSecure());
		} catch (ServiceException e) {
			if (logger.isLoggable(Level.SEVERE)) {
				logger.logp(Level.SEVERE, CLASS_NAME, "transform", "Exception", e);
			}
		}
		String helppath = "";
		if (pathRemainder instanceof String) {
			// SPR KGUY7PKLNK - ensure no XSS in the URL. Expect only *.html
			// We only use this taglib to redirect to html pages so far, but
			// if that changes, so this code will need to be updated.
			String safePath = (String) pathRemainder;
			int htmlEnd = safePath.indexOf(".html");
			safePath = safePath.substring(0, htmlEnd + 5);
			helppath = helpComponentUrl + HELP_URI + "/" + safePath;
			/*
			 * } else { helppath = HELPPATH + "/" +helpsubdir; }
			 */
		}
		return helppath;
	}
	
	/**
	 * TODO: Incorporate locale for new help system
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private String getLang() {
		
		Set helpDirectories = getHelpDirectories();
		String helpsubdir = null;
		
		Enumeration<Locale> locales = pageContext.getRequest().getLocales();
		Locale locale = null;

		do {
			if (locales == null || !locales.hasMoreElements()) {
				locale = Locale.getDefault();
			} else {
				locale = locales.nextElement();
			}

			String language = locale.getLanguage();
			String country = locale.getCountry();
			// match lang_country
			helpsubdir = (country == null || country.length() == 0) ? language: language + "_" + country;
			if (!helpDirectories.contains(helpsubdir)) {
				// try matching just lang
				helpsubdir = language;
				if (!helpDirectories.contains(helpsubdir)) {
					helpsubdir = null; // try again
				}
			}

		} while (helpsubdir == null && locales != null
				&& locales.hasMoreElements());

		if (helpsubdir == null) {
			helpsubdir = "en"; // default to English if nothing found
		}
		
		return helpsubdir;
	}

	private Set<String> getHelpDirectories() {
		if (helpDirectories == null) {
			helpDirectories = new HashSet<String>(10);
			String rootHelpPath = pageContext.getServletContext().getRealPath(HELPPATH);
			File dir = new File(rootHelpPath);
			if (dir.isDirectory()) {
				File[] dirs = dir.listFiles();
				for (int i = 0; i < dirs.length; i++) {
					if (dirs[i].isDirectory()) {
						helpDirectories.add(dirs[i].getName());
					}
				}
			}
		}
		return helpDirectories;
	}
}
