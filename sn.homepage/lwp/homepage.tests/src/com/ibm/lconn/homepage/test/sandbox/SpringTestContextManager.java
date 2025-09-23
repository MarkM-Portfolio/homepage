/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.sandbox;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import com.ibm.lconn.homepage.spring.context.ApplicationContextLoader;
import com.ibm.lconn.homepage.spring.context.ConfigurableOptionalApplicationContextLoader;
import com.ibm.lconn.homepage.test.TestHome;

public class SpringTestContextManager {
	
	private static ApplicationContextLoader applicationContextLoader;
	public static final String PJ_HOME = TestHome.getPJ_HOME();
	public static final String LOGGING_CONFIG_FILE = PJ_HOME+"/src/logging.properties";
	private static Set<String> contextLocations;
	
	private static HashMap<String, ApplicationContextLoader> contexts = new HashMap<String,ApplicationContextLoader>();
	
	
	private SpringTestContextManager(){
		
	}	

	/*
	 * Provides the default context locations to be loaded 
	 * the first time the application manager is called
	 */
	public static String[] getContxtLocations(){
		String[] locations = {
			"test/spring/news/database/TestServiceContext.xml",
			"test/spring/news/consumer/ConsumerContext.xml",
			"test/spring/news/database/TestDatabaseContext" + TestHome.getDbVendor() + ".xml"};
		return locations;
	}
	
	public static void destroyApplicationContext() {
		if(applicationContextLoader!=null){
			applicationContextLoader.destory();
			applicationContextLoader=null;
		}
	}
	private static void initApplicationContext(String[] locations) {
		TestHome.initLogging();			
		System.setProperty("test.config.files", TestHome.getTestConfigFilesDir());
		System.setProperty("foo.system.property", "I am from system");
		if(locations!=null){
			destroyApplicationContext();
			applicationContextLoader= new ConfigurableOptionalApplicationContextLoader(locations);
			//refreshLocations(locations);
		}
	}
	/*
	 * Called in the setup of the test case, loads up default context
	 * the first time the manager is called
	 */
	public static ApplicationContextLoader getSpringTestContext(){
		if(applicationContextLoader == null){
			System.out.println("XXX: applicationContextLoader is null, reading: "+ printLocations(getContxtLocations()));
			initApplicationContext(getContxtLocations());			
		}		
		return applicationContextLoader;
	
	}

	/*
	 * Compares the current contexts to those passed in and returns the old
	 * contextLoader if they are the same or creates a new one if they are 
	 * different 
	 */
	public static ApplicationContextLoader refreshSpringTestContextWithLocations(String[] locations){
		//System.out.println("XXX:refresh"+printLocations(locations));
		initApplicationContext(locations);

//		System.out.println("returning from application loader"+ applicationContextLoader);
		return applicationContextLoader;
	}
	
	private static void initContextLocations(String[] locations){
		contextLocations = new LinkedHashSet<String>();
		for(String location: locations){
			contextLocations.add(location);
		}
	}
	
	/**
	 * This method will init the contextLocations the first time and it will refresh it in case it is different from the prev.
	 * 
	 */
	public static boolean isRefreshNeeded(String[] locations){
		boolean isRefreshNeeded = false;
		Set<String> locationsSet = new LinkedHashSet<String>(Arrays.asList(locations));

		System.out.println("OLD: "+contextLocations);
		System.out.println("NEW: "+locationsSet);
		
		if(contextLocations == null) {
			isRefreshNeeded = true;
		}
		else {
			Iterator<String> it = contextLocations.iterator();
			Iterator<String> itLocationsSet = locationsSet.iterator();
			if(contextLocations.size() != locationsSet.size()) {
				isRefreshNeeded = true;
			}
			else {
				int i =0;
				while(it.hasNext() && i <locationsSet.size()){
					String currentLocation = it.next();
					String newLocation = itLocationsSet.next();
					if	(currentLocation.equalsIgnoreCase(newLocation)) {
						isRefreshNeeded = false;
					}
					else {
						isRefreshNeeded = true;
						break;
					}
				}
			}
		}
		
		if (isRefreshNeeded) {
			System.out.println("--- SPRING: isRefreshNeeded: "+isRefreshNeeded);
			initContextLocations(locations);
		}
		else {
			System.out.println("--- SPRING: isRefreshNeeded: "+isRefreshNeeded);
		}

		return isRefreshNeeded;
	}
	

	
	
	/**************************************** WORKING IN PROGRESS METHODS *************************************/
	
	
	private static String createKeyFromContextLocations(Set<String> locations) {
		String key = locations.toString();
		return key;
	}
	
	/**
	 * This method will init the contextLocations the first time and it will refresh it in case it is different from the prev.
	 * 
	 */
	public static ApplicationContextLoader getSpringContext(String[] locations) {	
		Set<String> locationsSet = new LinkedHashSet<String>(Arrays.asList(locations));
		String key = createKeyFromContextLocations(locationsSet);
		saveContext(key,locationsSet);
		return getContext(key);
	}
		
	/** Get a context linked to a key */
	private static ApplicationContextLoader getContext(String key) {
		return contexts.get(key);
	}
	
	/** Save and a context */
	private static void  saveContext(String key, Set<String> locationsSet) {
		if (contexts == null)
			contexts = new HashMap<String, ApplicationContextLoader>();
		
		if (!contexts.containsKey(key)) {
			ApplicationContextLoader contextLoader = new ConfigurableOptionalApplicationContextLoader(key.split(", "));
			System.out.println("saving context");
			contexts.put(key, contextLoader);
		}		
	}
	/**************************************** WORKING IN PROGRESS METHODS *************************************/
	

	private static String printLocations(String[] locs){
		StringBuffer buf = new StringBuffer(1000);
		for(String locations : locs){
			buf.append(locations);
			buf.append(", ");
		}
		buf.append("\n");
		return buf.toString();
	}
	
}
