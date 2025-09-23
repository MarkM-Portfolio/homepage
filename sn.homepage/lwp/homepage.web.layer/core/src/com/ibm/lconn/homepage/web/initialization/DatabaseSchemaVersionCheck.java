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
import static java.util.logging.Level.FINEST;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.ibm.lconn.hpnews.service.interfaces.IDatabaseSchemaVersionService;

/**
 * 
 * To verify if the current database schema version is up to date.
 * This class will raise a warning in case the database schema version is not aligned
 * 
 * @author lorenzo
 *
 */
public class DatabaseSchemaVersionCheck implements ServletContextListener {
	
	private static String CLASS_NAME = DatabaseSchemaVersionCheck.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	
	
	/**
	 * To verify if the database is the right version
	 */
	public void contextInitialized(ServletContextEvent servletContext) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "contextInitialized", new Object[] { servletContext });
		
		ServletContext ctx = servletContext.getServletContext();
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(ctx);
		
		IDatabaseSchemaVersionService databaseSchemaVersionService = (IDatabaseSchemaVersionService) springContext.getBean("HPNEWS-SERVICE:databaseSchemaVersionService");

		if (logger.isLoggable(FINEST))
			logger.logp(FINEST, CLASS_NAME, "contextInitialized", "databaseSchemaVersionService is "+databaseSchemaVersionService);
		
		databaseSchemaVersionService.checkDatabaseSchemaVersion();

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "contextInitialized", servletContext);
	}
	
	
	public void contextDestroyed(ServletContextEvent servletContext) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "contextDestroyed",	new Object[] { servletContext });
		
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "contextDestroyed", servletContext);		
	}


}
