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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.discover;




import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Wallpost_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	

	@Test
	public void testWallpostCreated_ExtendedCharacters () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		testUser1 = userAllocator.getUser();
		testUser2 = userAllocator.getUser();
		
		Login(testUser1);
		
		//Update Status
		String StatusUpdate = FVT_NewsData.UpdateStatus;
		//statusUpdate(FVT_NewsData.UpdateStatus);
		statusUpdate(StatusUpdate);
		
		String StatusComment = FVT_NewsData.StatusComment_Special;
		System.out.println(StatusUpdate + ": " + StatusComment);
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		LogoutAndClose();
		
		//verifyNewsStory(FVT_NewsData.StatusComment_Special,"Discover","Profiles", true);
		verifyNewsStory(StatusComment,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");
	}
	
	@Test
	public void testWallpostCreated_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		clickLink(FVT_HomepageObjects.Updates);
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		//Update Status
		String StatusUpdate = FVT_NewsData.UpdateStatus;
		statusUpdate(FVT_NewsData.UpdateStatus);
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		//verifyNewsStory(FVT_NewsData.StatusComment,"Discover","Profiles", true);
		verifyNewsStory(StatusComment,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	@Test
	public void testWallpostCreated_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Update Status
		String StatusUpdate = FVT_NewsData.UpdateStatus_Link;
		//statusUpdate(FVT_NewsData.UpdateStatus);
		statusUpdate(StatusUpdate);
		
		String StatusComment = FVT_NewsData.StatusComment_Link;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		//verifyNewsStory(FVT_NewsData.StatusComment_Link,"Discover","Profiles", true);
		verifyNewsStory(StatusComment,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");
	}
	
	@Test
	public void testWallpostCreated_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Update Status
		String StatusUpdate = FVT_NewsData.UpdateStatus;			
		statusUpdate(FVT_NewsData.UpdateStatus);
		
		String StatusComment = FVT_NewsData.StatusComment_Long;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		LogoutAndClose();
		
		//Verify Story
		//verifyNewsStory(FVT_NewsData.StatusComment_Long,"Discover","Profiles", true);
		verifyNewsStory(StatusComment,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LongStatus");
	}
	
	@Test
	public void testWallpostCreated_PostOnAnotherUser () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Add entry to other user's wall
		String BoardEntry = addBoardEntry_AnotherWall(testUser2.getEmail());
		
		//Logout of profiles
		LogoutAndClose();
		
		verifyNewsStory(BoardEntry,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");
	}
	
	
}
