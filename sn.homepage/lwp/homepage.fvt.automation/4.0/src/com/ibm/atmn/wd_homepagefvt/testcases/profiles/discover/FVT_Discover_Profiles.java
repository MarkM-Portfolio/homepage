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


import org.junit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageData;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Discover_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	private static String LinkName="";
	
	public void testProfilesPersonSelftagged_Multiple () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
		//Add multiple tags
		ProfilesAddMultipleTags();			
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonSelftagged_Multiple");
	}
	
	public void testProfilesPersonSelftagged_Single () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonSelftagged_Single");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		ProfilesAddATag();			
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonSelftagged_Single");
	}
	
	
	public void testProfilesPersonTagged_Multiple () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonTagged_Multiple");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Select user's profile
		driver.getFirstElement(FVT_ProfilesObjects.SelectProfile).click();
		
		//Add multiple tags
		ProfilesAddMultipleTags();			
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonTagged_Multiple");
	}

	
	public void testProfilesPersonTagged_Single () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPersonTagged_Single");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Select user's profile
		driver.getFirstElement(FVT_ProfilesObjects.SelectProfile).click();
		
		//Add tag
		ProfilesAddATag();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPersonTagged_Single");
	}
	
	@Ignore 
	public void testProfilesPhotoUpdated_Create () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		//UploadPhoto_JO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_Create");
	}
	
	@Ignore 
	public void testProfilesPhotoUpdated_UpdateExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		//UploadPhoto_JO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_UpdateExisting");
	}
	
	@Ignore 
	public void testProfilesPhotoUpdated_RemoveExisting () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add tag
		//UploadPhoto_JO();		
		
		//Logout of profiles
		Logout();	
		
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfilesPhotoUpdated_RemoveExisting");
	}
	
	@Ignore 
	public void testCollegueAdded_Accepted () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Accepted");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Accept Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		clickLink(FVT_ProfilesObjects.AcceptInvite);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Accepted");
	}
	
	@Ignore 
	public void testCollegueAdded_Rejected () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testCollegueAdded_Rejected");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Search for user
		SearchForUser(CommonData.IC_LDAP_Username_Fullname451);
		
		//Invite User To You network
		InviteToNetwork();		
		
		//Logout of profiles
		Logout();	
		
		//Log in as other user
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Reject Invite
		clickLink("link=My Network");
		
		clickLink("link=Invitations");
		
		clickLink(FVT_ProfilesObjects.RejectInvite);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testCollegueAdded_Rejected");
	}
	
	public void testProfile_addLink () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfile_addLink");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add Link
		LinkName = ProfilesAddLink();
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfile_addLink");
	}
	
	public void testProfile_removeLink () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testProfile_removeLink");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Remove Link
		ProfilesRemoveLink();	
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testProfile_removeLink");
	}
	
	@Ignore 
	public void testAboutUpdated_AddContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_AddContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add content to About
		//UpdateAbout();
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_AddContent");
	}
	
	@Ignore 
	public void testAboutUpdated_RemoveContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_RemoveContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Remove content from About
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_RemoveContent");
	}
	
	@Ignore 
	public void testAboutUpdated_EditContent () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Edit content from About
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	@Ignore 
	public void testAboutUpdated_OtherFieldsUpdated () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testAboutUpdated_EditContent");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update other fields
		
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testAboutUpdated_EditContent");
	}
	
	
	public void testStatusUpdate_ExtendedCharacters () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_ExtendedChars);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_ExtendedCharacters");
	}
	
	@Ignore 
	public void testStatusUpdate_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		clickLink("link=I'm following");
		
		driver.getFirstElement(FVT_HomepageObjects.StatusUpdate).type(FVT_HomepageData.HomepageBoardEntry);
		
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	
	public void testStatusUpdate_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_Link);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LinkInStatus");
	}
	
	
	public void testStatusUpdate_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry_Long);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_LongStatus");
	}
	
	public void testWallpostCreated_ExtendedCharacters () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddResponse(FVT_ProfilesData.ProfilesBoardEntry_ExtendedChars);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_ExtendedCharacters");
	}
	
	@Ignore 
	public void testWallpostCreated_FromHomepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testStatusUpdate_FromHomepage");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add Response
		AddResponse_Homepage(FVT_HomepageData.HomepageBoardEntry);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testStatusUpdate_FromHomepage");
	}
	
	public void testWallpostCreated_LinkInStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddResponse(FVT_ProfilesData.ProfilesBoardEntry_Link);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LinkInStatus");
	}
	
	
	public void testWallpostCreated_LongStatus () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_LongStatus");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddResponse(FVT_ProfilesData.ProfilesBoardEntry_Long);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_LongStatus");
	}
	
	public void testWallpostCreated_PostOnAnotherUser () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Add entry to other user's wall
		AddBoardEntry_OtherWall(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testWallpostCreated_PostOnAnotherUser");
	}
	
	
	public void testFollowPerson () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testFollowPerson ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Follow Person
		FollowPerson(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testFollowPerson ");
	}

	public void testRecommendAdded_Profiles () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testRecommendAdded_Profiles ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		AddBoardEntry(FVT_ProfilesData.ProfilesBoardEntry);
		
		//Logout of profiles
		Logout();	
		
		
		
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Recommend User Status
		RecommendStatus_Profiles(CommonData.IC_LDAP_Username_Fullname450);
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testRecommendAdded_Profiles ");
	}
	
	
	public void testRecommendAdded_Homepage () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testRecommendAdded_Homepage ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink("link=Discover");
		
		driver.getFirstElement(FVT_HomepageObjects.StatusUpdate).type(FVT_HomepageData.HomepageBoardEntry);
		
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
		
		//Logout of profiles
		Logout();
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Update Status
		RecommendStatus_Homepage(CommonData.IC_LDAP_Username450);
		
		//Logout of profiles
		Logout();
		
		
	}
}
