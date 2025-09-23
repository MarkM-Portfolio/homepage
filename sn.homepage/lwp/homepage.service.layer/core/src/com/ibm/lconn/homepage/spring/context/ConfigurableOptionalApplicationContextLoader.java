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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
import org.springframework.util.SystemPropertyUtils;

import com.ibm.lconn.homepage.spring.context.config.ComponentPropertiesProcessor;


/*
 * This ApplicationContextLoader has two additional feature added compare to default spring ApplicationContext loaders.
 * 
 * 1. Support optional files. Any file passed to the loader will be optional. This is useful to provide env related 
 * configuration file.
 * 
 * 2. Support component properties. You may specify the properties for you component. And properties files are also 
 * optional.
 * 
 */

public class ConfigurableOptionalApplicationContextLoader implements ApplicationContextLoader {

	private static final Log logger = LogFactory.getLog(ConfigurableOptionalApplicationContextLoader.class);
	
	private ClassPathXmlApplicationContext applicationContext;
	
	public ConfigurableOptionalApplicationContextLoader(String... optionalLocations){
		initApplicationContext(optionalLocations);
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getBean(String beanName,Class<T> beanType) {
		return (T)applicationContext.getBean(beanName);
    }
	
	public synchronized void destory(){
		if(applicationContext!=null){
			long startTime = System.currentTimeMillis();
			applicationContext.destroy();
			if (logger.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				logger.info("Spring ApplicationContext destroy completed in " + elapsedTime + " ms");
			}
			applicationContext=null;
		}
	}

	private void initApplicationContext(String[] optionalLocations) {
		long startTime = System.currentTimeMillis();
		String[] configLocations = buildConfigLocations(optionalLocations);
		applicationContext=new ClassPathXmlApplicationContext(configLocations,false);
		applicationContext.addBeanFactoryPostProcessor(new ComponentPropertiesProcessor());
		applicationContext.refresh();
		if (logger.isInfoEnabled()) {
			long elapsedTime = System.currentTimeMillis() - startTime;
			logger.info("Spring ApplicationContext initialization completed in " + elapsedTime + " ms");
		}
	}

	private String[] buildConfigLocations(String[] optionalLocations) {
		List<String> newConfigurationList=new ArrayList<String>();
		if(optionalLocations.length==0){
			optionalLocations=new String[]{ApplicationContextLoader.DEFAULT_APPLICATION_CONTEXT_FILES};
		}
		for(String currentLocation:optionalLocations){
			currentLocation = cleanUpClasspathLocation(currentLocation);
			Resource location=new FileSystemResourceLoader().getResource(SystemPropertyUtils.resolvePlaceholders(currentLocation));
			if(location.exists()){
				newConfigurationList.add(currentLocation);
			}else{
				logger.info("Resource doesn't exist: " + currentLocation);
			}
		}
		String[] configLocations=newConfigurationList.toArray(new String[newConfigurationList.size()]);
		return configLocations;
	}

	public static String cleanUpClasspathLocation(String currentLocation) {
		currentLocation=currentLocation.trim();
		if(!currentLocation.startsWith(ResourceUtils.FILE_URL_PREFIX) && !currentLocation.startsWith(ResourceUtils.CLASSPATH_URL_PREFIX)){
			currentLocation=ResourceUtils.CLASSPATH_URL_PREFIX+currentLocation;
		}
		return currentLocation;
	}
}
