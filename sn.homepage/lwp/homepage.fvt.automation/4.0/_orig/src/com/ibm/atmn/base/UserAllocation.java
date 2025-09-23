/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.base;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import com.ibm.atmn.base.CSVHandler;
import com.ibm.atmn.base.User;

/**
 * The Class UserAllocation.
 */
public class UserAllocation {

	/** The User map. */
	private HashMap<Integer, User> UserMap;

	/** The Admin map. */
	private HashMap<Integer, User> AdminMap;

	/** The Map size. */
	private Integer MapSize;

	/** The Admin map size. */
	private Integer AdminMapSize;

	// Private constructor prevents instantiation from other classes
	/**
	 * Instantiates a new user allocation.
	 */
	private UserAllocation() {

		try {
			CSVHandler CHandler = new CSVHandler(RunConfigManager.getInstance().testConfigFolder + File.separator
					+ RunConfigManager.getInstance().fileManifest.getProperty(RunConfigManager.USER_DATA_PROPERTY_NAME));
			//Load the user map
			UserMap = CHandler.ReadFileSplit(true);

			//Create an admin user map
			AdminMap = new HashMap<Integer, User>();

			// load admin user list
			FileIOHandler fileHandler = new FileIOHandler();
			Properties usersProperties = fileHandler.getConf(RunConfigManager.getInstance().fileManifest.getProperty(RunConfigManager.USER_CONF_PROPERTY_NAME));
			String[] all = usersProperties.getProperty(RunConfigManager.ADMIN_USERS_PROPERTY_NAME).split(RunConfigManager.SPECIAL_USERS_DELIMITER);

			ArrayList<Integer> al = new ArrayList<Integer>(); // arraylist to hold admin keys
			String User = ""; // user from original map
			int AdminCount = 0; // number of admin users found
			Set<Map.Entry<Integer, User>> set; // search set in map
			Iterator<Map.Entry<Integer, User>> iter; // map iterator
			Map.Entry<Integer, User> entry; // map entry

			set = UserMap.entrySet();
			iter = set.iterator();

			while (iter.hasNext()) {

				entry = (Map.Entry<Integer, User>) iter.next();

				// Get the user map entry's display name
				User = entry.getValue().getDisplayName();

				// Loop through admin list to determine if available in user list
				for (int i = 0; i < all.length; i++) {

					if (User.toString().equals(all[i].toString())) {

						// Increment admin user found					  
						AdminCount++;

						// put the user object into new map
						AdminMap.put(AdminCount, entry.getValue());

						// record the key from the user map to be removed
						// cannot delete due to concurrency modification while iterating
						al.add(entry.getKey());

					}
				}
			}

			// Remove the admin entries from the usermap.
			for (int i = 0; i < al.size(); i++) {
				UserMap.remove(al.get(i));
			}

			AdminMapSize = UserMap.size();
			MapSize = UserMap.size();
			if (MapSize < 1 || AdminMapSize < 1) {
				throw new IllegalArgumentException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * SingletonHolder is loaded on the first execution of Singleton.getInstance() or the first access to
	 * SingletonHolder.INSTANCE, not before.
	 */
	private static class SingletonHolder {

		/** The Constant instance. */
		public static final UserAllocation instance = new UserAllocation();
	}

	/**
	 * Gets the single instance of UserAllocation.
	 * 
	 * @return single instance of UserAllocation
	 */
	public static UserAllocation getInstance() {

		return SingletonHolder.instance;
	}

	/**
	 * Gets a user.
	 * 
	 * The user is a random user selected from a map which has excluded admin users.
	 * 
	 * @return User object representing a non admin user.
	 * @throws Exception
	 *             the exception
	 */
	public synchronized User getUser() {

		return getAvailableUserFromMap(UserMap, MapSize);
	}

	/**
	 * Gets the specified user moderator.
	 *
	 * @param strings 
	 * 				String representing the unique id of the user to be used as moderator
	 * @return the specified user
	 */
	public synchronized User getModUser(String ...strings) {

		// Priority : 1) Check the passed in string value then 2) check the mod_users key
		String User = "";
		
		try {
			if (strings.length != 0) {
				// check if the first entry of the string is null
				if (strings[0].toString() != "") {
					// set the user name
					User = strings[0];
				}else{
					throw new Exception("No user specified for search.");
				}
				
			}else{
				
				// load mod user list
				FileIOHandler fileHandler = new FileIOHandler();
				Properties usersProperties = fileHandler.getConf(RunConfigManager.getInstance().fileManifest.getProperty(RunConfigManager.USER_CONF_PROPERTY_NAME));
				if (usersProperties.containsKey(RunConfigManager.MODERATOR_USERS_PROPERTY_NAME)){
					String[] mod = usersProperties.getProperty(RunConfigManager.MODERATOR_USERS_PROPERTY_NAME).split(RunConfigManager.SPECIAL_USERS_DELIMITER);
					if (mod.length != 0) {
						// get random moderator from the list
						Random rand = new Random();						
						int x = rand.nextInt(mod.length);						
						User = mod[x];	
					} else{
						throw new Exception("No user specified for search.");	
					}
				}
				else{
					throw new Exception("No mod user key contained in user.properties.");					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return getSpecifiedUserFromMap(UserMap, User);
	}
	
	/**
	 * Gets an admin user from the map. The admin user list available will be provided in user.properties
	 * 
	 * @return User object representing an admin user.
	 * @throws Exception
	 *             the exception
	 */
	public synchronized User getAdminUser() {

		return getAvailableUserFromMap(AdminMap, AdminMapSize);
	}

	private User getAvailableUserFromMap(HashMap<Integer, User> map, Integer mapSize) {

		User UserInfo = null;
		Boolean SetId = false;
		int UserNo = 1;

		try {
			// Use the returned hashmap			
			// Randomise the map OR get random entry in map

			// Return an integer between 1 and the mapsize
			// Random number generator
			Random rand = new Random();
			int min = 1;

			// Set the thread id of the user
			Long ThreadId = Thread.currentThread().getId();
			UserNo = rand.nextInt(mapSize - min) + min;

			//check if the value is null			
			while (map.get(UserNo) == null) {
				UserNo = rand.nextInt(mapSize - min) + min;
			}

			// Check that the random user does not have their thread id set
			while (SetId == false) {

				// if the user has a thread number greater than 1, the user is locked
				if (map.get(UserNo).getThreadUID() < 1) {
					// The thread id has not been set
					SetId = true;
				}
				else {
					// new random user from the list
					UserNo = rand.nextInt(mapSize - min) + min;
				}
			}

			// set the thread is on the user and return the user object
			map.get(UserNo).setThreadUID(ThreadId);

			UserInfo = map.get(UserNo);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// return the user object
		return UserInfo;

	}


	/**
	 * Gets the specified user from map.
	 *
	 * @param map 
	 * 			the user map
	 * @param UserOfInterest 
	 * 						the user of interest
	 * @return the specified user from map
	 */
	private User getSpecifiedUserFromMap(HashMap<Integer, User> map, String UserOfInterest) {

		User UserInfo = null;
		Boolean FoundId = false;
		Boolean SetId = false;

		try {
			String User = ""; // user from original map
			Set<Map.Entry<Integer, User>> set; // search set in map
			Iterator<Map.Entry<Integer, User>> iter; // map iterator
			Map.Entry<Integer, User> entry; // map entry
			
			// Gets the current thread id
			Long ThreadId = Thread.currentThread().getId();
			
			set = map.entrySet();
			iter = set.iterator();

			while ((iter.hasNext()) && (FoundId == false)){
				
				// iterate through the map
				entry = (Map.Entry<Integer, User>) iter.next();

				// Get the user map entry's uid
				User = entry.getValue().getUid();

				if (User.toString().equals(UserOfInterest)) {
					
					FoundId = true;
					
					// check if the user has their thread id set			
					// if it is set , wait for 10mins while checking it for change
					for (int i = 0; i < 120; i++) {
						if (entry.getValue().getThreadUID() > 1) {
							Thread.sleep(5000);
						}else{
							// set the id and return the user
							entry.getValue().setThreadUID(ThreadId);
							SetId = true;
							UserInfo = entry.getValue();
							break;
						}
					}
				}				
			}
			if (FoundId == false){
				throw new Exception(UserOfInterest + " was not found in the map provided.");
			}
			if (SetId == false){
				throw new Exception(UserOfInterest + " did not become available within 10 mins of request.");
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		return UserInfo;
	}
	
	public void clearAllUsers() {

		clearAdminUsers();
		clearUsers();
	}

	public void clearAdminUsers() {

		clearUserMap(AdminMap);
	}

	public void clearUsers() {

		clearUserMap(UserMap);
	}

	/**
	 * Clear users.
	 */
	private synchronized void clearUserMap(HashMap<Integer, User> map) {

		// Use the returned hashmap
		Integer ThreadCount = 0;
		Long UserThreadId = 0L;
		long Resetvalue = 0L;

		try {

			// Iterate through the map OR get all occurrences of the thread id			
			// Set the thread id of the users to 0
			Long ThreadId = Thread.currentThread().getId();

			Set<Map.Entry<Integer, User>> set = map.entrySet();
			Iterator<Map.Entry<Integer, User>> iter = set.iterator();

			while (iter.hasNext()) {
				Map.Entry<Integer, User> entry = (Map.Entry<Integer, User>) iter.next();
				UserThreadId = entry.getValue().getThreadUID();
				if (UserThreadId.compareTo(ThreadId) == 0) {
					//reset the thread id to 0				  
					entry.getValue().setThreadUID(Resetvalue);
					ThreadCount++;
				}
			}
			System.out.println("The current thread " + ThreadId + " had " + ThreadCount + " users checked out.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
