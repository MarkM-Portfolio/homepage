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
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.files.FVT_FilesMethods;

import static org.testng.AssertJUnit.*;

//****************************************
//NEEDS TO BE UPDATED
//****************************************

public class FVT_Discover_Private_Files extends FVT_FilesMethods{
	
	/*
	 * This is a functional test for the Files Component of IBM Connections
	 */
	
	private static String PrivateFile="";
	private static String PrivateFolder="";
	
	/**
	 * Log into Files and upload a private file
	 */
	
	
	@Test
	public void testUploadPrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testUploadPrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
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
	  	
		verifyNewsStory(" created the file "+PrivateFile+".jpg.","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testUploadPrivateFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testUpdatePrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testUpdatePrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//
		clickLink("link="+PrivateFile+".jpg");
		
		//Upload private file
		updateFile(PrivateFile, ".jpg", "Desert.jpg", "Private");
		
		//Logout of Connections
		Logout();	
		
		verifyNewsStory(" edited the file "+PrivateFile+".jpg.","Discover","Files", false);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testUpdatePrivateFile");
	}
	

	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testAddTagToPrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddTagToPrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PrivateFile+".jpg");
		
		//Add Tag
		AddATag();
		
		//Logout of Connections
		Logout();	
	  	
		verifyNewsStory(" tagged the file "+PrivateFile+".jpg with "+FVT_FilesData.FileTagName+".","Discover","Files", false);
		
		System.out.println("INFO: End of Files FVT_Level_2 testAddTagToPrivateFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testSharePrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testSharePrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PrivateFile+".jpg");
		
		//Click Share
		clickLink("link=Share");
		
		//Share
		ShareFileWith("a Person", "as Reader",CommonData.IC_LDAP_Username222);
		
		//Logout of Connections
		Logout();	
		
		verifyNewsStory(" shared the file "+PrivateFile+".jpg.","Discover","Files", false);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testSharePrivateFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testAddCommentToPrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddTagToPrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PrivateFile+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout of Connections
		Logout();
		
		verifyNewsStory(" commented on the file "+PrivateFile+".jpg.","Discover","Files", false);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testAddTagToPrivateFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testAddRecommendToPrivateFile () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testAddRecommendToPrivateFile");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//My files
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PrivateFile+".jpg");
		
		//Add Recommend
		RecommendAFile();
		
		//Logout of Connections
		Logout();	
		
		verifyNewsStory(" recommended the file "+PrivateFile+".jpg.","Discover","Files", false);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testAddRecommendToPrivateFile");
	}
	
	
	@Test (dependsOnMethods = { "testUploadPrivateFile" })
	public void testFileAdded_PrivateSharedFolder () throws Exception {
		System.out.println("INFO: Start of Files FVT_Level_2 testFileAdded_PrivateSharedFolder");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		PrivateFolder = "FVT Private Folder "+genDateBasedRandVal();
		
		//Create Private Folder
		CreateFolder(PrivateFolder,"FVT Folder Description",FVT_FilesObjects.CreateFolderPeople,CommonData.IC_LDAP_Password222);
		
		Thread.sleep(500);
		//sel.refresh();
		//Thread.sleep(500);
		
		clickLink("link=Files");
		
		//Add a file to folder
		addFileToFolder(PrivateFolder, FVT_FilesObjects.CheckPrivateFileAdd);
		
		//Logout of Connections
		Logout();
		
		verifyNewsStory(" created the folder "+PrivateFolder+".","Discover","Files", false);
	  	
		System.out.println("INFO: End of Files FVT_Level_2 testFileAdded_PrivateSharedFolder");
	}
	
	
}

