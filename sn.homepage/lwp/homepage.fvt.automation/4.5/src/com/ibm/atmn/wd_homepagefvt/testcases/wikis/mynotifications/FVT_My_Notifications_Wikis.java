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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.mynotifications;

import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_My_Notifications_Wikis extends FVT_WikisMethods{
	
		User testUser1;
		User testUser2;
		User testUser3;
	
		//Create a public wiki
	
		@Test
		public void testAddMemberWiki () throws Exception {
			System.out.println("INFO: Start of testAddMemberWiki");	
			
			//Load the component		
			LoadComponent(CommonObjects.ComponentWikis);
				
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
			Login(testUser1);
			
			//Click Start a Wiki button
			clickLink(WikisObjects.Start_New_Wiki_Button);
	
			//Create a new public wiki
			typeInTextField(FVT_WikisObjects.Wiki_Name_Field, FVT_WikisData.CI_Box_Public_Wiki);
	
			//Click Save button
			clickLink(FVT_WikisObjects.Save_Button);
	
			//Verify homepage UI
			verifyNewHomePageUI(FVT_WikisData.CI_Box_Public_Wiki, testUser1.getDisplayName(), "");
				
			//
			addMemberToWiki(testUser2.getEmail());
			
			//Logout of Wiki
			LogoutAndClose();	
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FROM_ME, FVT_WikisData.CI_Box_Public_Wiki, null, testUser2.getDisplayName());
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FOR_ME, FVT_WikisData.CI_Box_Public_Wiki, null, testUser1.getDisplayName());

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.WIKIS, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.WIKIS, false);
			

			System.out.println("INFO: End of testAddMemberWiki");
		}
		
		
		@Test (dependsOnMethods = { "testAddMemberWiki" },groups={"broken"})
		public void testChangeMemberRoleWiki () throws Exception {
			System.out.println("INFO: Start of testAddMemberWiki");	
			
			//Load the component		
			LoadComponent(CommonObjects.ComponentWikis);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
			Login(testUser1);
			
			clickLink("link="+FVT_WikisData.CI_Box_Public_Wiki);
			
			//Change Member Role
			changeMemberRole(testUser2.getDisplayName());
			
			//Logout of Wiki
			LogoutAndClose();	
			
			//Replace news story strings
			String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FROM_ME, FVT_WikisData.CI_Box_Public_Wiki, null, testUser2.getDisplayName());
			
			String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_WIKI_MEMBER_ADDED_FOR_ME, FVT_WikisData.CI_Box_Public_Wiki, null, testUser1.getDisplayName());

			
			//Verify that news story appears in  My Notifications -> From Me
			verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.WIKIS, true);
				
			//Verify that news story appears in  My Notifications -> For Me
			verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.WIKIS, false);
			

			System.out.println("INFO: End of testAddMemberWiki");
		}
	
		
}
	
		