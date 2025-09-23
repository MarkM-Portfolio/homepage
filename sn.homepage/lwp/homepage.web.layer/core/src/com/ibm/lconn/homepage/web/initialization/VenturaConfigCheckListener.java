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

import static java.util.logging.Level.SEVERE;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.ibm.connections.mtconfig.ConfigEngine;
import com.ibm.connections.mtconfig.interfaces.ConfigEngineConstants;
import com.ibm.connections.mtconfig.interfaces.ConfigEngineNotReadyException;
import com.ibm.connections.mtconfig.interfaces.IConfigEngine;
import com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;

/**
 * Servlet context listener which checks to see if LotusConnections-config.xml
 * is correctly configured for Homepage. Implemented as a servlet listener rather 
 * than a Spring init bean in the service layer just to keep it out of the way
 * of unit test issues and to be consistent with HP.
 * 
 * @author Adrian Spender
 *
 */
public class VenturaConfigCheckListener implements ServletContextListener {

	private static String CLASS_NAME = VenturaConfigCheckListener.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	private final String name = "homepage";
	
	public void contextDestroyed(ServletContextEvent arg0) {		
	}

	public void contextInitialized(ServletContextEvent arg0) {
		
		try{		
			VenturaConfigurationProvider.Factory.getInstance().verify(name);
			
			VenturaConfigurationProvider vcp = VenturaConfigurationProvider.Factory.getInstance();
			if(vcp.isMultiTenantConfigEngineEnabled()) {
				IConfigEngine configEngine = ConfigEngine.getInstance();
				if(!configEngine.isConfigurationLoaded(ConfigEngineConstants.HOMEPAGE))
					configEngine.loadConfiguration(ConfigEngineConstants.HOMEPAGE);
			}
			
		} catch (VenturaConfigException vce) {
			if(logger.isLoggable(Level.SEVERE)){ 
				logger.logp(Level.SEVERE, CLASS_NAME, "contextInitialized", vce.getMessage(), vce);
			}
		} catch(ConfigEngineNotReadyException cenre) {
			if (logger.isLoggable(SEVERE)) {
				logger.logp(SEVERE, CLASS_NAME, "contextInitialized", cenre.getMessage());
			}
		}
	}
}
