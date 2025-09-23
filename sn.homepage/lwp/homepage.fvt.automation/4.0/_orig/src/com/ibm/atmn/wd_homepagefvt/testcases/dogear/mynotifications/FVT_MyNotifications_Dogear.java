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

import com.ibm.atmn.lc.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearData;
import com.ibm.atmn.wd_homepagefvt.appobjects.dogear.FVT_DogearObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.dogear.FVT_DogearMethods;

public class FVT_MyNotifications_Dogear extends FVT_DogearMethods {
	
	private static String TEST_USER1_USERNAME = CommonData.IC_LDAP_Username450;
	private static String TEST_USER1_PASSWORD = CommonData.IC_LDAP_Password450;
	private static String TEST_USER1_FULLNAME = CommonData.IC_LDAP_Username_Fullname450;
	private static String TEST_USER2_USERNAME = CommonData.IC_LDAP_Username451;
	private static String TEST_USER2_PASSWORD = CommonData.IC_LDAP_Password451;
	private static String TEST_USER2_FULLNAME = CommonData.IC_LDAP_Username_Fullname451;

	private static String PUBLIC_BOOKMARK_NAME;
	
	//Now Get the DateTime
	String DateTimeStamp = CommonMethods.genDateBasedRandVal();

	
	@Test
	public void testNotifyUserAboutBookmark() throws Exception {

		System.out.println("INFO: Start of Dogear FVT testNotifyUserAboutBookmark Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER1_USERNAME, TEST_USER1_PASSWORD);

		//Enter text into the form
		PUBLIC_BOOKMARK_NAME = addPublicBookmark(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp, FVT_DogearData.PublicBookmarkURL+DateTimeStamp, FVT_DogearData.PublicBookmarkTag, FVT_DogearData.BookmarkDescription);
			
		sleep(1000);
		
		//Verify that the bookmark is present
		Assert.assertTrue(driver.isTextPresent(FVT_DogearData.PublicBookmarkTitle+DateTimeStamp));
		
		notifyUserAboutBookmark(TEST_USER2_FULLNAME);
		
		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FROM_ME, PUBLIC_BOOKMARK_NAME, null, TEST_USER2_FULLNAME);
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_NOTIFY_FOR_ME, PUBLIC_BOOKMARK_NAME, null, TEST_USER1_FULLNAME);
		
		//verify from me news story
		verifyMyNotificationsNewsStory(TEST_USER1_USERNAME,TEST_USER1_PASSWORD,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BOOKMARKS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(TEST_USER2_USERNAME,TEST_USER2_PASSWORD,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BOOKMARKS, true);
	
		System.out.println("INFO: End of Dogear FVT testNotifyUserAboutBookmark Test");


	}
	
	
	@Test (dependsOnMethods = { "testNotifyUserAboutBookmark" })
	public void testNotifyUserAboutBrokenUrl() throws Exception {

		System.out.println("INFO: Start of Dogear FVT testNotifyUserAboutBrokenUrl Test");

		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user
		Login(TEST_USER2_USERNAME, TEST_USER2_PASSWORD);
		
		clickLink(FVT_DogearObjects.Nav_PublicBookmarks);
			
		notifyUserAboutBrokenUrl(TEST_USER1_FULLNAME);
		
		// Logout of Connections
		LogoutAndClose();
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_BROKEN_FROM_ME, PUBLIC_BOOKMARK_NAME, null, TEST_USER1_FULLNAME);
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_BOOKMARK_BROKEN_FOR_ME, PUBLIC_BOOKMARK_NAME, null, TEST_USER2_FULLNAME);
		
		//verify from me news story
		verifyMyNotificationsNewsStory(TEST_USER2_USERNAME,TEST_USER2_PASSWORD,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.BOOKMARKS, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(TEST_USER1_USERNAME,TEST_USER1_PASSWORD,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.BOOKMARKS, true);
	
		System.out.println("INFO: End of Dogear FVT testNotifyUserAboutBrokenUrl Test");


	}
	
	
}
