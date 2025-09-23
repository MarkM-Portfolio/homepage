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

package com.ibm.lconn.homepage.services.config.gettingstarted;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.SEVERE;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import com.ibm.ventura.internal.service.admin.was.WASAdminService;

public class GettingStartedBeanHelper implements ServletContextListener {
	private static String CLASS_NAME = GettingStartedBeanHelper.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);
	
	
    public static final String CONFIG_DIR = "LotusConnections-config";
	public static final String GETSTART_CONFIG_FILE = "gettingstarted-config.xml";
	
	private static Set<GettingStartedItem> gettingStartedConfig;
	private static GettingStartedUrl gettingStartedUrl;
	private static boolean isUrlMode;
	
	public void contextDestroyed(ServletContextEvent event) {
		//Do nothing
	}

	public void contextInitialized(ServletContextEvent event) {
		gettingStartedConfig = new TreeSet<GettingStartedItem>();
		gettingStartedUrl = null;
		isUrlMode = false;
		
		loadConfig();
	}
	
	public static Set<GettingStartedItem> getStartConfigurationData() {
		return gettingStartedConfig;
	}
	
	public static boolean isUrlMode() {
		return isUrlMode;
	}
	
	public static GettingStartedUrl getGettingStartedUrl() {
		return gettingStartedUrl;
	}
	
	
    /**
     * Loads Getting started config from gettingstarted-config.xml
     *
     * @throws GettingStartedConfigException if the config load fails
     */
    private synchronized void loadConfig() {
    	if ( logger.isLoggable(FINER) ) {
    		logger.entering(CLASS_NAME, "loadConfig");
    	}
    	
    	try {
                String configFilePath = null;
                if (System.getProperty("was.install.root") != null) {
                    String userInstallRoot = System.getProperty("user.install.root");
                    final String cellName = WASAdminService.getCellName();
                    configFilePath = userInstallRoot + java.io.File.separator
                            + "config" + java.io.File.separator + "cells"
                            + java.io.File.separator + cellName
                            + java.io.File.separator + CONFIG_DIR
                            + java.io.File.separator + GETSTART_CONFIG_FILE;
                } else {
                    URL configURL = getClass().getClassLoader().getResource(GETSTART_CONFIG_FILE);
                    configFilePath = configURL.getPath();
                }
                if(logger.isLoggable(FINEST)){
                    logger.logp(FINEST, CLASS_NAME, "loadConfig", "Reading notification configuration from {0}", configFilePath);
                }
            
                File configFile = new File(configFilePath);
                if (!configFile.exists() || !configFile.canRead()) {
        			String errorStr = "error accessing file "+configFilePath;
                    if(logger.isLoggable(SEVERE)){
                        logger.logp(SEVERE, CLASS_NAME, "read config file", errorStr);
                    }
        			throw new GettingStartedConfigException(errorStr);
                }

                validate(configFile);
	
                XMLConfiguration config = new XMLConfiguration(configFile);
	            
                gettingStartedUrl = readUrlData(config);
                if ( gettingStartedUrl != null ) {
	            	isUrlMode = true;
	            	if ( logger.isLoggable(FINER) ) {
	            		logger.logp(FINER, CLASS_NAME, "loadConfig", "Getting started is in URL mode");
	            	}
	            } else {
	            	ComponentUrlInterpolator variablesLookup = new ComponentUrlInterpolator();
	            	config.getInterpolator().registerLookup(ComponentUrlInterpolator.LC_URL_PREFIX, variablesLookup);
	            	gettingStartedConfig = readStepsData(config);
	            	if (!variablesLookup.getInterpolationErrors().isEmpty()) {
	            		String errorStr = "error interpolating references "+ToStringBuilder.reflectionToString(variablesLookup.getInterpolationErrors());
	            		if(logger.isLoggable(SEVERE)){
	            			logger.logp(SEVERE, CLASS_NAME, "loadConfig", errorStr);
	            		}
	            		throw new GettingStartedConfigException(errorStr);
	            	}
	            }
	        } catch (Exception e) {
	            if(! (e instanceof GettingStartedConfigException)) {
	                if(logger.isLoggable(SEVERE)){
	                    logger.logp(SEVERE, CLASS_NAME, "loadConfig", "error loading", e);
	                }
	            }
	        }
    	
    	if ( logger.isLoggable(FINER) ) {
    		logger.exiting(CLASS_NAME, "loadConfig");
    	}
    }

    /**
     * Validates the configuration file against its schema. If an exception
     * occurs it is likely that a hand-edit to the file has broken it.
     *
     * @param xml the config xml
     *
     * @throws GettingStartedConfigException if any validation exception occurs
     */
    private void validate(File xml) throws GettingStartedConfigException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute(
                "http://java.sun.com/xml/jaxp/properties/schemaLanguage",
                "http://www.w3.org/2001/XMLSchema");

        try {
            DocumentBuilder parser = factory.newDocumentBuilder();
            DefaultHandler handler = new DefaultHandler() {
                public void warning(SAXParseException e) throws SAXException {
                    throw e;
                }

                public void error(SAXParseException e) throws SAXException {
                    throw e;
                }

                public void fatalError(SAXParseException e) throws SAXException {
                    throw e;
                }
            };

            parser.setErrorHandler(handler);
            parser.parse(xml);

        } catch (Exception e) {
            if(logger.isLoggable(SEVERE)){
                logger.logp(SEVERE, CLASS_NAME, "loadConfig","error validating", e);
            }
            throw new GettingStartedConfigException("error validating", e);
        }

    }

	/**
	 * Reads configuration file and parses data out of it into the set of steps.
	 * Only enabled steps are returned.
	 * 
	 * @param config configuration file
	 * @return Set of GettingStartedItem objects
	 * @throws GettingStartedConfigException
	 */
    private Set<GettingStartedItem> readStepsData(XMLConfiguration config) throws GettingStartedConfigException {
		
		Set<GettingStartedItem> result = new TreeSet<GettingStartedItem>();
		
		try {
			int itemCounter = 0;
			List<?> steps = config.configurationsAt("steps.step");
			if ( steps != null ) {
				for (Object step:steps) {
					HierarchicalConfiguration stepConfig = (HierarchicalConfiguration)step;
					GettingStartedItem newItem = new GettingStartedItem(++itemCounter);
					
					newItem.setEnabled(stepConfig.getBoolean("[@enabled]"));
					newItem.setTitle(stepConfig.getString("title"));
					newItem.setBundleName(stepConfig.getString("title[@bundle]"));
					String unsecureLink = stepConfig.getString("body-links[@unsecure]"); 
					newItem.setUrl(unsecureLink);
					String secureLink = stepConfig.getString("body-links[@secure]");
					newItem.setSecureUrl(secureLink);
					if (newItem.isEnabled()) {
						result.add(newItem);
					}
				}
			}
		} catch (Exception e) {
            if(logger.isLoggable(SEVERE)){
                logger.logp(SEVERE, CLASS_NAME, "readStepsData", "error parsing", e);
            }
            throw new GettingStartedConfigException("error parsing", e);
		}
		
		return result;
		
	}
    
    private GettingStartedUrl readUrlData(XMLConfiguration config) throws GettingStartedConfigException {
    	if ( logger.isLoggable(FINER) ) {
    		logger.entering(CLASS_NAME, "readUrlData", config);
    	}
    	
    	GettingStartedUrl ret = null;
    	try {
    		String url = config.getString("url");
    		String target = config.getString("url[@target]");
    		
    		if ( url != null ) {
    			ret = new GettingStartedUrl(url, target);
    		}
    	} catch ( Exception e ) {
    		if ( logger.isLoggable(SEVERE) ) {
    			logger.logp(SEVERE, CLASS_NAME, "readUrlData", "error parsing", e);
    		}
    	}
    	
    	if ( logger.isLoggable(FINER) ) {
    		logger.exiting(CLASS_NAME, "readUrlData", ret == null ? null : ret.dump());
    	}
    	return ret;
    }

    
}
