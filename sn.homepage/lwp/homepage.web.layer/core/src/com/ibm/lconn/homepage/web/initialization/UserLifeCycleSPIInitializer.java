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

package com.ibm.lconn.homepage.web.initialization;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Register HP user life cycle SPI instance
 * 
 * @author vincent
 * 
 */
public class UserLifeCycleSPIInitializer implements ServletContextListener {

	private static String CLASS_NAME = UserLifeCycleSPIInitializer.class
			.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	public void contextDestroyed(ServletContextEvent arg0) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "contextDestroyed");
		
		

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "contextDestroyed");
	}

	public void contextInitialized(ServletContextEvent arg0) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "contextInitialized");

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "contextInitialized");
	}

}
