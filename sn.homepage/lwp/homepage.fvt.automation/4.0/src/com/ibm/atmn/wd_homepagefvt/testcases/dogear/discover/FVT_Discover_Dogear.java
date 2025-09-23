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

package com.ibm.atmn.wd_homepagefvt.testcases.dogear.discover;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;
import com.ibm.atmn.wd_homepagefvt.tasks.dogear.FVT_DogearMethods;

public class FVT_Discover_Dogear extends FVT_DogearMethods {
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PRIVATE_BOOKMARK_NAME;
	private static String PUBLIC_BOOKMARK_NAME;
	
	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();

	
	
	@Test
	public void testPrivateBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		// Login as a user
		//Login(testUser1);
		Login(testUser1);
		
		//Enter text into the form
		PRIVATE_BOOKMARK_NAME = addPrivateBookmark(FVT_DogearData.PrivateBookmarkTitle+DateTimeStamp, FVT_DogearData.PrivateBookmarkURL+DateTimeStamp, FVT_DogearData.PrivateBookmarkTag, FVT_DogearData.BookmarkDescription);
				
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PrivateBookmarkTitle+DateTimeStamp));
		
		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " created a bookmark named "+PRIVATE_BOOKMARK_NAME+".","Discover","Bookmarks", false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}
	
	@Test (dependsOnMethods = { "testPrivateBookmarkAdded" })
	public void testPrivateBookmarkEdited() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(testUser1);

		editBookmark();
		
		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " updated the bookmark named "+PRIVATE_BOOKMARK_NAME+".","Discover","Bookmarks", false);

		System.out.println("INFO: End of Dogear FVT Level 2 testPrivateBookmarkAdded Test");

	}

	@Test  (dependsOnMethods = { "testPrivateBookmarkAdded" })
	public void testPublicBookmarkAdded() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkAdded Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(testUser1);

		//Enter text into the form
		PUBLIC_BOOKMARK_NAME = addPublicBookmark(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp, FVT_DogearData.PublicBookmarkURL+DateTimeStamp, FVT_DogearData.PublicBookmarkTag, FVT_DogearData.BookmarkDescription);
			
		sleep(1000);
		
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp));
		
		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " created a bookmark named "+PUBLIC_BOOKMARK_NAME+".","Discover","Bookmarks", true);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkAdded Test");


	}

	@Test  (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testPublicBookmarkEdited() throws Exception {

		System.out.println("INFO: Start of Dogear FVT Level 2 testPublicBookmarkEdited Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(testUser1);

		editBookmark();
		
		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " updated the bookmark named "+PUBLIC_BOOKMARK_NAME+".","Discover","Bookmarks", true);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testPublicBookmarkEdited Test");


	}

	
	
	@Test (dependsOnMethods = { "testPublicBookmarkAdded" })
	public void testAddPersonToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(testUser2);

		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1.getDisplayName());

		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " added to their Bookmarks watchlist.","Discover","Bookmarks", false);		
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonToWatchlist Test");

	}
	
	@Test (dependsOnMethods = { "testAddPersonToWatchlist" })
	public void testAddTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones451)
		Login(testUser2);

		//Add tag to watchlist
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		LogoutAndClose();
		
		// Verify story appears in AS -> Discover
		verifyNewsStory(testUser2, " added the following tag to their Bookmarks watchlist: fvttesttag","Discover","Bookmarks", false);		
		
		System.out.println("INFO: End of Dogear FVT Level 2 testAddTagToWatchlist Test");

	}
	
	//@Test (dependsOnMethods = { "testAddTagToWatchlist" })
	public void testAddPersonAndTagToWatchlist() throws Exception {

		
		
		System.out.println("INFO: Start of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones452)
		Login(testUser3);
		
		//Add person to watchlist
		toggleAddPersonToWatchlist(testUser1.getDisplayName());
		
		//Add tag to watchlist 
		toggleAddTagToWatchlist("fvttesttag");
		
		// Logout of Connections
		Logout();
		
		// Verify story appears in AS -> Discover
		//VerifyNewsStory_JO("FVTTestTag", "Bookmarks", "Public");
		

		System.out.println("INFO: End of Dogear FVT Level 2 testAddPersonAndTagToWatchlist Test");

	}
	
	
	
	
}
