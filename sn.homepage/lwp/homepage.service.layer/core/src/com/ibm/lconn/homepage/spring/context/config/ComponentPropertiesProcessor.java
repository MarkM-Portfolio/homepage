/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2008, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */
package com.ibm.lconn.homepage.spring.context.config;

import static java.util.logging.Level.FINER;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.ManagedList;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;
import org.springframework.util.SystemPropertyUtils;

import com.ibm.lconn.homepage.spring.context.ConfigurableOptionalApplicationContextLoader;

/*
 * PostProcessBeanFactory to merge and apply properties
 * 
 */

public class ComponentPropertiesProcessor extends PropertyPlaceholderConfigurer {

    private static String CLASS_NAME = ComponentPropertiesProcessor.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    private Properties mergedProperties = new Properties();
    private PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        for (String beanName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            String parentBeanName = beanDefinition.getParentName();

            if (logger.isLoggable(FINER))
                logger.exiting(CLASS_NAME, "postProcessBeanFactory", "BeanName: " + beanName + " Parent:" + parentBeanName);

            if (beanDefinition.getBeanClassName() != null) {
                if (beanDefinition.getBeanClassName().equals(ComponentProperties.CLASS_NAME)) {
                    MutablePropertyValues configPropertyValues = beanDefinition.getPropertyValues();
                    processPropertiesProperty(configPropertyValues);
                    processLocationsProperty(configPropertyValues);
                }
            }

        }
        super.postProcessBeanFactory(beanFactory);
    }

    protected Properties mergeProperties() throws IOException {
        return mergedProperties;
    }

    @SuppressWarnings("unchecked")
    private void processPropertiesProperty(MutablePropertyValues configPropertyValues) {
        PropertyValue propertyValue = configPropertyValues.getPropertyValue("properties");
        if (propertyValue != null) {
            ManagedMap propertiesValues = (ManagedMap) propertyValue.getValue();
            for (Object propertyPair : propertiesValues.entrySet()) {
                Map.Entry keyValue = (Map.Entry) propertyPair;
                String key = ((TypedStringValue) keyValue.getKey()).getValue();
                String value = ((TypedStringValue) keyValue.getValue()).getValue();
                mergedProperties.put(key, value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void processLocationsProperty(MutablePropertyValues configPropertyValues) {
        PropertyValue propertyValue = configPropertyValues.getPropertyValue("locations");
        if (propertyValue != null) {
            ManagedList locationsValues = (ManagedList) propertyValue.getValue();
            for (Iterator iterator = locationsValues.iterator(); iterator.hasNext();) {
                TypedStringValue locationValue = (TypedStringValue) iterator.next();
                loadPropertiesFromSingleFile(locationValue.getValue());
            }
        }
    }

    private void loadPropertiesFromSingleFile(String resourceName) {
        resourceName = ConfigurableOptionalApplicationContextLoader.cleanUpClasspathLocation(resourceName);
        Resource location = new FileSystemResourceLoader().getResource(SystemPropertyUtils.resolvePlaceholders(resourceName));
        if (location != null && location.exists()) {
            if (logger.isLoggable(Level.INFO)) {
                logger.logp(Level.INFO, CLASS_NAME, "loadPropertiesFromSingleFile", "Loading properties file from " + location);
            }
            InputStream is = null;
            try {
                try {
                    is = location.getInputStream();
                    if (location.getFilename().endsWith("xml")) {
                        propertiesPersister.loadFromXml(mergedProperties, is);
                    } else {
                        propertiesPersister.load(mergedProperties, is);
                    }
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            } catch (IOException ex) {
                throw new RuntimeException("Could not load properties from " + location + ": " + ex.getMessage(), ex);
            }
        } else {
            logger.logp(Level.INFO, CLASS_NAME, "loadPropertiesFromSingleFile", "Resource doesn't exist: " + resourceName);
        }
    }

}
