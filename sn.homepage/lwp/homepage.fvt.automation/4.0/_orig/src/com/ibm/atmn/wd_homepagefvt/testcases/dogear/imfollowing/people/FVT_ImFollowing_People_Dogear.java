/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.dogear.imfollowing.people;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.dogear.FVT_DogearMethods;

public class FVT_ImFollowing_People_Dogear extends FVT_DogearMethods {
	
	private static String TEST_USER1_USERNAME = CommonData.IC_LDAP_Username450;
	private static String TEST_USER1_PASSWORD = CommonData.IC_LDAP_Password450;
	private static String TEST_USER2_USERNAME = CommonData.IC_LDAP_Username451;
	private static String TEST_USER2_PASSWORD = CommonData.IC_LDAP_Password451;
	private static String TEST_USER3_USERNAME = CommonData.IC_LDAP_Username452;
	private static String TEST_USER3_PASSWORD = CommonData.IC_LDAP_Password452;
	
	private static String PRIVATE_BOOKMARK_NAME;
	private static String PUBLIC_BOOKMARK_NAME;
	
	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();

	@Test
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(CommonData.IC_LDAP_Username_Fullname450);
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testPrivateBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);

		//Enter text into the form
		PRIVATE_BOOKMARK_NAME = addPrivateBookmark(FVT_DogearData.PrivateBookmarkTitle+DateTimeStamp, FVT_DogearData.PrivateBookmarkURL+DateTimeStamp, FVT_DogearData.PrivateBookmarkTag, FVT_DogearData.BookmarkDescription);
				
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PrivateBookmarkTitle+DateTimeStamp));
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," created a bookmark named "+PRIVATE_BOOKMARK_NAME+".","I'm Following","Bookmarks","People", false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}
	
	@Test (dependsOnMethods = { "testPrivateBookmarkAdded" })
	public void testPrivateBookmarkEdited() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);

		editBookmark();
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," updated the bookmark named "+PRIVATE_BOOKMARK_NAME+".","I'm Following","Bookmarks","People",  false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}

	@Test  (dependsOnMethods = { "testPrivateBookmarkAdded" })
	public void testPublicBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);

		//Enter text into the form
		PUBLIC_BOOKMARK_NAME = addPublicBookmark(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp, FVT_DogearData.PublicBookmarkURL+DateTimeStamp, FVT_DogearData.PublicBookmarkTag, FVT_DogearData.BookmarkDescription);
			
		sleep(1000);
		
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp));
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," created a bookmark named "+PUBLIC_BOOKMARK_NAME+".","I'm Following","Bookmarks","People", true);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkAdded Test");


	}

	@Test  (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testPublicBookmarkEdited() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkEdited Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);

		editBookmark();
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," updated the bookmark named "+PUBLIC_BOOKMARK_NAME+".","I'm Following","Bookmarks","People", true);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkEdited Test");


	}

	
	@Test (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testAddTagToWatchlist() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testAddTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(TEST_USER2_USERNAME, TEST_USER2_PASSWORD);

		//Add tag to watchlist
		toggleAddTagToWatchlist("automation");
		
		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," added the following tag to their Bookmarks watchlist: fvttesttag","I'm Following","Bookmarks","People", false);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testAddTagToWatchlist Test");

	}
	
	@Test (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testAddPersonToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(TEST_USER2_USERNAME, TEST_USER2_PASSWORD);

		//Add person to watchlist
		toggleAddPersonToWatchlist(TEST_USER1_USERNAME);

		// Logout of Connections
		Logout();
		
		driver.close();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory_TwoFilters(TEST_USER2_USERNAME,TEST_USER2_PASSWORD," added to their Bookmarks watchlist.","I'm Following","Bookmarks","People", true);		
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonToWatchlist Test");

	}
	

	
	
	
	
}
