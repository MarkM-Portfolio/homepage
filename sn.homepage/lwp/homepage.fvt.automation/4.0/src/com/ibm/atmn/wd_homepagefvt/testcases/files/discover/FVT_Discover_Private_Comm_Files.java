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

//****************************************
//NEEDS TO BE UPDATED
//****************************************

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

public class FVT_Discover_Private_Comm_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 */
	
	private static String PrivateFileCommunity="";
	private static String Private_Comm_File="";
	private static String PrivateFile="";

	
	/**
	 * Log into Files and upload a private file
	 */
	
	@Test
	public void testFileCreated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCreated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateFileCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
		//Open files from left nav
		clickLink("link=Files");
		
		//Share Files
		clickLink(FVT_FilesObjects.ShareFiles);
		
		//Open files from left nav
		clickLink("link=Browse files on my computer...");
		
		//Generate random file name
		Private_Comm_File = "FVT Private Community File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile_Community(Private_Comm_File, ".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		verifyNewsStory(" shared the file "+Private_Comm_File+".jpg with the community "+PrivateFileCommunity+".","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCreated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_PrivateComm" })
	public void testFileUpdated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileUpdated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" +PrivateFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Private_Comm_File+".jpg");
		
		//Upload private file
		updateFile_Community(".jpg", "Desert.jpg");
		
		//Logout
		Logout();
		
		verifyNewsStory(" edited the file "+Private_Comm_File+".jpg.","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileUpdated_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_PrivateComm" })
	public void testFileTagAdded_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Files FVT_Level_2 testFileTagAdded_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			clickLink("link=" +PrivateFileCommunity);
			
			//Open files from left nav
			clickLink("link=Files");
				
			//Open file to update
			clickLink("link="+Private_Comm_File+".jpg");
			
			//Add Tag
			AddATag();
			
			//Logout
			Logout();
			
			verifyNewsStory(" tagged the file "+Private_Comm_File+".jpg with "+FVT_FilesData.FileTagName+".","Discover","Files", false);
			
			System.out.println("INFO: End of Files FVT_Level_2 testFileTagAdded_PrivateComm");
			
		}
	
	@Test (dependsOnMethods = { "testFileCreated_PrivateComm" })
	public void testShareFile_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testShareFile_PrivateComm");
	
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Generate random file name
		PrivateFile = "FVT Private File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile(PrivateFile, ".jpg", "Desert.jpg", "Private");
				
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
		clickLink("link=" + PrivateFileCommunity);
		
		//Share public file with community
		SharePublicFile();
		
		//Logout
		Logout();
		
		verifyNewsStory(" shared the file "+PrivateFile+".jpg with the community "+PrivateFileCommunity+".","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testShareFile_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testFileCreated_PrivateComm" })
	public void testFileCommentAdded_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileCommentAdded_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PrivateFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Private_Comm_File+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout
		Logout();
		
		verifyNewsStory(" commented on the file "+Private_Comm_File+".jpg.","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileCommentAdded_PrivateComm");
		
	}

	@Test (dependsOnMethods = { "testFileCreated_PrivateComm" })
	public void testFileRecommendAdded_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testFileRecommendAdded_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link=" + PrivateFileCommunity);
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink("link="+Private_Comm_File+".jpg");
		
		//Add Tag
		RecommendAFile();
		
		//Logout
		Logout();
		
		verifyNewsStory(" recommended the file "+Private_Comm_File+".jpg.","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testFileRecommendAdded_PrivateComm");
		
	}
	
}

