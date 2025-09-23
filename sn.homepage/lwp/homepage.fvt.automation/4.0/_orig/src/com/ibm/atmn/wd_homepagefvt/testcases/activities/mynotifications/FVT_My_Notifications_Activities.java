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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_My_Notifications_Activities extends FVT_ActivitiesMethods {

	FormInputHandler typist;

	private static String publicActivityName = "";
	private static String publicActivityPublicEntryTitle = "";
			
	@Test (groups={"broken"})
	public void testNotifyCreateActivity_AddMember() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMember");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,CommonData.IC_LDAP_UserName451_Typeahead);
			
			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FROM_ME, publicActivityName, null, CommonData.IC_LDAP_Username_Fullname451);
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FOR_ME, publicActivityName, null, CommonData.IC_LDAP_Username_Fullname450);

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMember");
			
		}
	
	@Test (groups={"broken"})
	public void testNotifyCreateActivity_AddMemberAfter() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,CommonData.IC_LDAP_UserName452_Typeahead);
			
			addMember(typist, CommonData.IC_LDAP_UserName451_Typeahead);
			
			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FROM_ME, publicActivityName, null, CommonData.IC_LDAP_Username_Fullname451);
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_MEMBER_ADDED_FOR_ME, publicActivityName, null, CommonData.IC_LDAP_Username_Fullname450);

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
		}
	
	
	
	
	@Test 
	public void testNotifyMemberAboutEntry() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			sleep(2000);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,CommonData.IC_LDAP_UserName451_Typeahead);
			
			publicActivityPublicEntryTitle = addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
			
			notifyAboutEntry();

			//Logout
			LogoutAndClose();
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FROM_ME, publicActivityPublicEntryTitle, null, CommonData.IC_LDAP_Username_Fullname450);
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_ACTIVITY_NOTIFY_ENTRY_FOR_ME, publicActivityPublicEntryTitle, null, CommonData.IC_LDAP_Username_Fullname450);

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username450,CommonData.IC_LDAP_Password450,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.ACTIVITIES, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(CommonData.IC_LDAP_Username451,CommonData.IC_LDAP_Password451,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.ACTIVITIES, true);

			
			System.out.println("INFO: End of Activities FVT testNotifyCreateActivity_AddMemberAfter");
			
		}
	
	
}