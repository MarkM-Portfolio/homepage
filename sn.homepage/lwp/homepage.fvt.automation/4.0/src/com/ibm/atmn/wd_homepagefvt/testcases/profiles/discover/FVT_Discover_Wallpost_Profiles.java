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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Wallpost_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	

	@Test
	public void testWallpostCreated_ExtendedCharacters () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		String StatusUpdate = statusUpdate(FVT_NewsData.UpdateStatus);
		
		String StatusComment = FVT_NewsData.StatusComment_Special;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
	
		driver.close();
		
		verifyNewsStory(FVT_NewsData.StatusComment_Special,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");
	}
	
	@Test
	public void testWallpostCreated_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		//Update Status
		String StatusUpdate = statusUpdate(FVT_NewsData.UpdateStatus);
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.StatusComment,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	@Test
	public void testWallpostCreated_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		String StatusUpdate = statusUpdate(FVT_NewsData.UpdateStatus_Link);
		
		String StatusComment = FVT_NewsData.StatusComment_Link;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.StatusComment_Link,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");
	}
	
	@Test
	public void testWallpostCreated_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		String StatusUpdate = statusUpdate(FVT_NewsData.UpdateStatus);
		
		String StatusComment = FVT_NewsData.StatusComment_Long;
		
		//Comment on status
		CommentOnStatus(StatusUpdate, StatusComment);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(FVT_NewsData.StatusComment_Long,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LongStatus");
	}
	
	@Test
	public void testWallpostCreated_PostOnAnotherUser () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add entry to other user's wall
		String BoardEntry = addBoardEntry_AnotherWall(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();	
		
		driver.close();
		
		verifyNewsStory(BoardEntry,"Discover","Profiles", true);
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");
	}
	
	
}
