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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.mynotifications;

import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;


public class FVT_My_Notifications_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	User testUser1 = userAllocator.getUser();
	User testUser2 = userAllocator.getUser();
	User testUser3 = userAllocator.getUser();
	
	@Test
	public void testInviteCollegue () throws Exception {
		System.out.println("INFO: Start of Profiles FVT testInviteCollegue");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Search for user
		SearchForUser(testUser2.getDisplayName());
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		LogoutAndClose();	
		
		//Replace news story text strings
		String FromMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_NETWORK_INVITE_FROM_ME, null, null, testUser2.getDisplayName());
		
		String ForMeNewsStory = replaceNewsStory(NewsStoryData.MY_NOTIFICATIONS_NETWORK_INVITE_FOR_ME, null, null, testUser1.getDisplayName());
	
		//verify from me news story
		verifyMyNotificationsNewsStory(testUser1,FromMeNewsStory,FVT_NewsData.FROM_ME,FVT_NewsData.PROFILES, true);
		
		//verify for me news story
		verifyMyNotificationsNewsStory(testUser2,ForMeNewsStory,FVT_NewsData.FOR_ME,FVT_NewsData.PROFILES, true);
		
		
		System.out.println("INFO: End of Profiles FVT testInviteCollegue");
	}
	
	
}
