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


package com.ibm.atmn.homepagefvt.testcases.files;



import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.homepagefvt.tasks.files.FVT_FilesMethods;

import static org.testng.AssertJUnit.*;


public class FVT_Level2_Mod_Comm_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 */
	
	private static String ModeratedFileCommunity="";
	private static String Mod_Comm_File="";
	private static String PublicFile="";

	
	/**
	 * Log into Files and upload a private file
	 */
	
	@Test
	public void testFileCreated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCreated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedFileCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open files from left nav
		clickLink("link=Files");
		
		//Share Files
		clickLink(FVT_FilesObjects.ShareFiles);
		
		//Open files from left nav
		clickLink("link=Browse files on my computer...");
		
		//Generate random file name
		Mod_Comm_File = "FVT Moderated Community File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile_Community(Mod_Comm_File, ".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		VerifyNewsStory(" shared the file "+Mod_Comm_File+".jpg with the community "+ModeratedFileCommunity+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCreated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_ModComm" })
	public void testFileUpdated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileUpdated_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + ModeratedFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Mod_Comm_File+".jpg");
		
		//Upload private file
		updateFile_Community(".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		VerifyNewsStory(" edited the file "+Mod_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileUpdated_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_ModComm" })
	public void testFileTagAdded_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileTagAdded_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + ModeratedFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Mod_Comm_File+".jpg");
		
		//Add Tag
		AddATag();
		
		//Logout
		Logout();
		
		VerifyNewsStory(" tagged the file "+Mod_Comm_File+".jpg with "+FVT_FilesData.FileTagName+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileTagAdded_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileUpdated_ModComm" })
	public void testShareFile_ModComm() throws Exception {
	

		System.out.println("INFO: Start of Files FVT_Level_2 testShareFile_ModComm");
	
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Generate random file name
		PublicFile = "FVT Public File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile(PublicFile, ".jpg", "Desert.jpg", "Public");
				
		//Logout of Connections
		Logout();	
		
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Login
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		//
		clickLink("link=I'm an Owner");
		
		//Open community
		clickLink("link=" + ModeratedFileCommunity);
		
		//Share public file with community
		SharePublicFile();
		
		//Logout
		Logout();
		
		VerifyNewsStory(" shared the file "+PublicFile+".jpg with the community "+ModeratedFileCommunity+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testShareFile_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_ModComm" })
	public void testFileCommentAdded_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCommentAdded_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + ModeratedFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Mod_Comm_File+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout
		Logout();
		
		VerifyNewsStory(" commented on the file "+Mod_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCommentAdded_ModComm");
		
	}

	@Test (dependsOnMethods = { "testFileCreated_ModComm" })
	public void testFileRecommendAdded_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileRecommendAdded_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + ModeratedFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Mod_Comm_File+".jpg");
		
		//Add Tag
		RecommendAFile();
		
		//Logout
		Logout();
		
		VerifyNewsStory(" recommended the file "+Mod_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileRecommendAdded_ModComm");
		
	}
	
}

