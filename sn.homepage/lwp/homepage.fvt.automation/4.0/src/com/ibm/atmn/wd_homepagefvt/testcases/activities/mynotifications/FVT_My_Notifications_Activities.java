/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.activities.mynotifications;


import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_My_Notifications_Activities extends FVT_ActivitiesMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	FormInputHandler typist;

	private static String publicActivityName = "";
	private static String publicActivityPublicEntryTitle = "";
			
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testNotifyCreateActivity_AddMember() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMember");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
			
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FROM_ME, publicActivityName, null, testUser2.getDisplayName());
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FOR_ME, publicActivityName, null, testUser1.getDisplayName());

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMember");
			
		}
	
	//@Test (groups={"broken"})
	public void testNotifyCreateActivity_AddMemberAfter() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist, testUser3.getDisplayName());
			
			addMember(typist, testUser2.getDisplayName());
			
			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FROM_ME, publicActivityName, null, testUser2.getDisplayName());
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FOR_ME, publicActivityName, null, testUser1.getDisplayName());

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(testUser1 ,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(testUser2 ,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
		}
	
	
	
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testNotifyMemberAboutEntry() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist, testUser2.getEmail());
			
			publicActivityPublicEntryTitle = addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
			
			notifyAboutEntry();

			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FROM_ME, publicActivityPublicEntryTitle, null, testUser2.getDisplayName());
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FOR_ME, publicActivityPublicEntryTitle, null, testUser1.getDisplayName());

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(testUser1 ,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(testUser2 ,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
		}
	
	
}