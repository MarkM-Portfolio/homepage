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
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.homepagefvt.tasks.files.FVT_FilesMethods;

import static org.testng.AssertJUnit.*;


public class FVT_Level2_Public_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 */
	
	private static String PublicFile="";
	private static String PublicFolder="";
	
	/**
	 * Log into Files and upload a private file
	 */
	
	

	
	@Test 
	public void testUploadPublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testUploadPublicFile");
		
		//Load the component
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
		
		VerifyNewsStory(" created the file "+PublicFile+".jpg.","Discover","Files", true);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testUploadPublicFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testUpdatePublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testUpdatePublicFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");		
		
		//Upload public file
		updateFile(PublicFile, ".jpg", "Desert.jpg", "Private");
		
		//Logout of Connections
		Logout();	
		
		VerifyNewsStory(" edited the file "+PublicFile+".jpg.","Discover","Files", true);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testUpdatePublicFile");
	}
	

	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testAddTagToPublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddTagToPublicFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");
		
		//Add Tag
		AddATag();
		
		//Logout of Connections
		Logout();	
		
		VerifyNewsStory(" tagged the file "+PublicFile+".jpg with "+FVT_FilesData.FileTagName+".","Discover","Files", true);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testAddTagToPublicFile");
	}
	
	
	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testSharePublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testSharePublicFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");
		
		//Click Share
		clickLink("link=Share");
		
		//Share
		ShareFileWith("a Person", "as Reader",CommonData.IC_LDAP_Username333);
		
		//Logout of Connections
		Logout();	
		
		VerifyNewsStory(" shared the file "+PublicFile+".jpg.","Discover","Files", true);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testSharePublicFile");
	}
	
	
	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testAddCommentToPublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddTagToPublicFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout of Connections
		Logout();	
		
		VerifyNewsStory(" commented on the file "+PublicFile+".jpg.","Discover","Files", true);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testAddTagToPublicFile");
	}
	
	
	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testAddRecommendToPublicFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddRecommendToPublicFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//My Files
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");
		
		//Add Recommend
		RecommendAFile();
		
		//Logout of Connections
		Logout();	
	  	
		VerifyNewsStory(" recommended the file "+PublicFile+".jpg.","Discover","Files", true);
		
		System.out.println("INFO: End of Files FVT_Level_2 testAddRecommendToPublicFile");
	}
	
	
	
	@Test (dependsOnMethods = { "testUploadPublicFile" })
	public void testFileAdded_PublicSharedFolder () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testFileAdded_PublicSharedFolder");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		PublicFolder = "FVT Public Folder "+genDateBasedRandVal();
		
		//Create Public Folder
		CreateFolder(PublicFolder,"FVT Folder Description",FVT_FilesObjects.CreateFolderPublic,CommonData.IC_LDAP_Password222);
		
		Thread.sleep(500);
		sel.refresh();
		Thread.sleep(500);
		
		clickLink("link=Files");
		
		//Add a file to folder
		addFileToFolder(PublicFolder, FVT_FilesObjects.CheckPublicFileAdd);
			
		//Logout of Connections
		Logout();	
		
		VerifyNewsStory(" created the folder "+PublicFolder+".","Discover","Files", true);

		System.out.println("INFO: End of Files FVT_Level_2 testFileAdded_PublicSharedFolder");
	}
	
}

