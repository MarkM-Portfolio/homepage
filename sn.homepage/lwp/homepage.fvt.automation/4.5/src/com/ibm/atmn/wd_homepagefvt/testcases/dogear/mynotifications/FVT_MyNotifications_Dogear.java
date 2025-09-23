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

package com.ibm.atmn.wd_homepagefvt.testcases.dogear.mynotifications;



import org.testng.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.dogear.FVT_DogearMethods;

public class FVT_MyNotifications_Dogear extends FVT_DogearMethods {
	
	User testUser1;
	User testUser2;
	User testUser3;

	private static String PUBLIC_BOOKMARK_NAME;
	
	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();

	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Dogear FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentDogear);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Dogear FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testNotifyUserAboutBookmark() throws Exception {

		System.out.println("INFO: Start of Dogear FVT testNotifyUserAboutBookmark Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);
		
		// Login as a user
		//Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);
		Login(testUser1);

		//Enter text into the form
		PUBLIC_BOOKMARK_NAME = addPublicBookmark(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp, FVT_DogearData.PublicBookmarkURL+DateTimeStamp, FVT_DogearData.PublicBookmarkTag, FVT_DogearData.BookmarkDescription);
			
		sleep(1000);
		
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp));
		
		notifyUserAboutBookmark(testUser2.getDisplayName());
		
		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FROM_ME, PUBLIC_BOOKMARK_NAME, null, testUser2.getDisplayName());
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FOR_ME, PUBLIC_BOOKMARK_NAME, null, testUser1.getDisplayName());
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BOOKMARKS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BOOKMARKS, true);
	
		System.out.println("INFO: End of Dogear FVT testNotifyUserAboutBookmark Test");


	}
	
	
	@Test (dependsOnMethods = { "testNotifyUserAboutBookmark" })
	public void testNotifyUserAboutBrokenUrl() throws Exception {

		System.out.println("INFO: Start of Dogear FVT testNotifyUserAboutBrokenUrl Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(testUser2);
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
			
		notifyUserAboutBrokenUrl(testUser1.getEmail());
		
		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_BROKEN_FROM_ME, PUBLIC_BOOKMARK_NAME, null, testUser1.getDisplayName());
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_BROKEN_FOR_ME, PUBLIC_BOOKMARK_NAME, null, testUser2.getDisplayName());
		
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser2,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BOOKMARKS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser1,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BOOKMARKS, true);
	
		System.out.println("INFO: End of Dogear FVT testNotifyUserAboutBrokenUrl Test");


	}
	
	
}
