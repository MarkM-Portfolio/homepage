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

package com.ibm.lconn.homepage.web.utils.impl;

import static java.util.logging.Level.FINER;

import java.util.Properties;
import java.util.logging.Logger;

import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;

public class HomepageConfigUtils {

	private static String CLASS_NAME = HomepageConfigUtils.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private final static String PROP_NOCUSTOMIZE = "com.ibm.lconn.homepage.noCustomize";
	
	private boolean noCustomize = false;

	private static HomepageConfigUtils instance = null;
	
	private static HomepageConfigUtils getInstance() {
		if ( instance == null ) {
			instance = new HomepageConfigUtils();
			instance.init();
		}
		return instance;
	}

	private void init() {
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME, "init");
		}
		
		VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory.getInstance();
		Properties props = vch.getGenericProperites();
		if ( props != null ) {
			noCustomize = props.containsKey(PROP_NOCUSTOMIZE) ? Boolean.parseBoolean(props.getProperty(PROP_NOCUSTOMIZE)) : false;
		}
		
		if ( logger.isLoggable(FINER) ) {
			logger.logp(FINER, CLASS_NAME, "init", "noCustomize = " + noCustomize);
			logger.exiting(CLASS_NAME, "init");
		}
	}
	
	
	public static boolean getNoCustomize() {
		return getInstance().noCustomize;
	}
	
}
