/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.model.ComponentConfiguration;
import com.ibm.lconn.homepage.services.IConfigurationService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.configuration.caching.IComponentConfigurationCachingService;
import com.ibm.ventura.internal.config.exception.VenturaConfigException;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;

public class ConfigurationServiceImpl extends AbstractVenturaService implements
		IConfigurationService {

	private final static String CLASS_NAME = ConfigurationServiceImpl.class
			.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static final String DIRECTORY_SERVICE = "directory";
	private static final String WPI_ENABLED_ATTR = "[@profiles_directory_service_extension_enabled]";
    
	@Autowired
    private IComponentConfigurationCachingService componentConfigurationCachingService;
	private boolean exposeEmail;
	private boolean wpiEnabled;
	private boolean configException;
	
	@Override
	protected void internalAfterPropertiesSet() throws Exception {
		initCache();
		exposeEmail = getVenturaConfigurationHelper().getExposeEmail();
		try {
			setWpiEnabled();
		} catch (VenturaConfigException vex) {
			configException = true;
		}
	}

	private ComponentConfiguration initComponentConfiguration(
			String componentName) throws ServiceException {
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, "initComponentConfiguration",
					componentName);
		}
		ComponentConfiguration componentConfiguration = new ComponentConfiguration();
		ComponentEntry componentEntry = getVenturaConfigurationHelper()
				.getComponentConfig(componentName);
		
		componentConfiguration.setComponentConfiguration(componentEntry);
		
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, "initComponentConfiguration",
					componentConfiguration);
		}
		return componentConfiguration;

	}

	private void initCache() throws ServiceException {
		if (logger.isLoggable(Level.FINER)) {
			logger.entering(CLASS_NAME, "initCache");
		}
		Collection<String> componentNames = getVenturaConfigurationHelper()
				.getInstalledComponents();

		for (String componentName : componentNames) {
			getComponentConfigurationCachingService().addCache(componentName,
					initComponentConfiguration(componentName), true);
		}
		
		if (logger.isLoggable(Level.FINER)) {
			logger.exiting(CLASS_NAME, "initCache");
		}
	}

	public String getComponentUrl(String componentName, boolean isSecureURL)
			throws ServiceException {

		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getComponentUrl", new Object[] {
					componentName, isSecureURL });
		}

		ComponentConfiguration componentConfiguration = getComponentConfigurationCachingService()
				.retrieveCache(componentName);

		String url = "";
		if (isSecureURL) {
			url = componentConfiguration.getSecureUrl();
		} else {
			url = componentConfiguration.getUrl();
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getComponentUrl", url);
		}

		return url;
	}

	public List<String> getInstalledComponents() {

		return getComponentConfigurationCachingService()
				.getInstalledComponents();
	}

	public void setComponentConfigurationCachingService(
			IComponentConfigurationCachingService componentConfigurationCachingService) {
		this.componentConfigurationCachingService = componentConfigurationCachingService;
	}

	protected IComponentConfigurationCachingService getComponentConfigurationCachingService() {
		return componentConfigurationCachingService;
	}

	public ComponentConfiguration getComponentConfiguration(String componentName)
			throws ServiceException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getComponentConfiguration",
					componentName);
		}

		ComponentConfiguration componentConfiguration = getComponentConfigurationCachingService()
				.retrieveCache(componentName);

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getComponentConfiguration",
					componentConfiguration);
		}
		return componentConfiguration;
	}

	public boolean getExposeEmail() {
		return exposeEmail;
	}

	protected void setWpiEnabled() throws VenturaConfigException {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "setWpiEnabled");
		}
		Configuration directoryConfig = getVenturaConfigurationProvider()
				.getServiceConfiguration(DIRECTORY_SERVICE);
		String wpiEnabledStr = directoryConfig.getString(WPI_ENABLED_ATTR);
		if (wpiEnabledStr != null) {
			wpiEnabled = Boolean.parseBoolean(wpiEnabledStr);
		} else {
			logger
					.logp(FINER, CLASS_NAME, "init",
							"Could not retrieve enabled value for Profiles Directory Service Extenstion");
			configException = true;
		}
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "setWpiEnabled", wpiEnabled);
		}
	}

	public boolean getWpiEnabled() throws ServiceException {
		if (!configException)
			return wpiEnabled;
		else
			throw new ServiceException(
					"Could not retrieve enabled value for Profiles Directory Service Extenstion");
	}

	public boolean isComponentInstalled(String componentName) {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "isComponentEnabled", componentName);

		// TODO: Vincent - put result in cache?

		boolean result = false;

		if (componentName != null) {
			ComponentEntry entry = getVenturaConfigurationHelper()
					.getComponentConfig(componentName);

			result = false;

			if (entry != null) {
				result = entry.isUrlEnabled() || entry.isSecureUrlEnabled();
			} else {
				result = false;
			}
		} else {
			result = false;
		}

		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "isComponentEnabled", result);

		return result;
	}

    @Override
    public boolean isMultiTenantEnvironment() {
        return getVenturaConfigurationHelper().isMultiTenantEnvironment();
    }
}
