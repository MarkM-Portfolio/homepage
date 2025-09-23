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


package com.ibm.atmn.wd_homepagefvt.testcases.files.discover;



import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.files.FVT_FilesMethods;
import static org.testng.AssertJUnit.*;

public class FVT_Discover_Public_Comm_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 */
	
	private static String PublicFileCommunity="";
	private static String Public_Comm_File="";
	private static String PublicFile="";

	
	/**
	 * Log into Files and upload a private file
	 */
	
	
	@Test
	public void testFileCreated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCreated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicFileCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open files from left nav
		clickLink("link=Files");
		
		//Share Files
		clickLink(FVT_FilesObjects.ShareFiles);
		
		//Open files from left nav
		clickLink("link=Browse files on my computer...");
		
		//Generate random file name
		Public_Comm_File = "FVT Public Community File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile_Community(Public_Comm_File, ".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		verifyNewsStory(" shared the file "+Public_Comm_File+".jpg with the community "+PublicFileCommunity+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCreated_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_PublicComm" })
	public void testFileUpdated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileUpdated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PublicFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Public_Comm_File+".jpg");
		
		//Upload private file
		updateFile_Community(".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		verifyNewsStory(" edited the file "+Public_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileUpdated_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileUpdated_PublicComm" })
	public void testFileTagAdded_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileTagAdded_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PublicFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Public_Comm_File+".jpg");
		
		//Add Tag
		AddATag();
		
		//Logout
		Logout();
		
		verifyNewsStory(" tagged the file "+Public_Comm_File+".jpg with "+FVT_FilesData.FileTagName+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileTagAdded_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileUpdated_PublicComm" })
	public void testShareFile_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testShareFile_PublicComm");
	
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
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
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//
		clickLink("link=I'm an Owner");
		
		//Open community
		clickLink("link=" + PublicFileCommunity);
		
		//Share public file with community
		SharePublicFile();
		
		//Logout
		Logout();
		
		verifyNewsStory(" shared the file "+PublicFile+".jpg with the community "+PublicFileCommunity+".","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testShareFile_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileUpdated_PublicComm" })
	public void testFileCommentAdded_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCommentAdded_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PublicFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Public_Comm_File+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout
		Logout();
		
		verifyNewsStory(" commented on the file "+Public_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCommentAdded_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileUpdated_PublicComm" })
	public void testFileRecommendAdded_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileRecommendAdded_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PublicFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Public_Comm_File+".jpg");
		
		//Add Tag
		RecommendAFile();
		
		//Logout
		Logout();
		
		verifyNewsStory(" recommended the file "+Public_Comm_File+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileRecommendAdded_PublicComm");
		
	}
	
}

