/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.files;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.files.FilesData;
import com.ibm.atmn.lc.appobjects.files.FilesObjects;
import com.ibm.atmn.lc.tasks.files.FilesMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Files extends FilesMethods {

	/*
	 * This is a functional test for the Files Component of IBM Connections Created By: Adrian Strock Date: 08/10/2011
	 */

	@Test
	public void deleteAnyExistingFilesAndFolders() throws Exception {

		System.out.println("INFO: Start of Files BVT_Level_2 Test Cleanup");

		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Remove any existing files and folders before running the BVT level 2 test
		RemoveAnyExistingFilesFolders();

		//Logout of Connections
		Logout();

		System.out.println("INFO: End of Files BVT_Level_2 Cleanup");
	}

	/**
	 * Log into Files and upload a private file
	 */
	@Test
	public void uploadPrivateFile() throws Exception {

		System.out.println("INFO: Start of Files BVT_Level_2 Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));

		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload private file
		uploadPrivateFile(randomName, ".jpg", "Desert.jpg");

		//Click the upload file
		clickLink("link=" + randomName + ".jpg");

		//Now Pin/Comment/Recommend/Add Folder
		PerformActionsOnFile(randomName + ".jpg");

		//Logout of Connections
		Logout();

		System.out.println("INFO: End of Files BVT_Level_2 Test 1");
	}

	@Test(dependsOnMethods = { "uploadPrivateFile" })
	public void uploadFileAndMoveToTrash() throws Exception {

		System.out.println("INFO: Start of Files BVT_Level_2 Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload private file
		uploadPrivateFile(randomName, ".jpg", "Koala.jpg");

		//Move file to trash and verify the files is deleted
		MoveFileToTrash(randomName + ".jpg");

		//Logout of Connections
		Logout();

		System.out.println("INFO: End of Files BVT_Level_2 Test 2");
	}

	@Test(dependsOnMethods = { "uploadFileAndMoveToTrash" })
	public void uploadFileAndAddToFolder() throws Exception {

		System.out.println("INFO: Start of Files BVT_Level_2 Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload private file
		uploadPrivateFile(randomName, ".jpg", "Lighthouse.jpg");

		//Create a folder
		CreateFolder("Public " + FilesData.FolderName + randomName, FilesData.FolderDescription, FilesObjects.CreateFolderPublic);

		//Open the My Files view
		clickLink(FilesObjects.MyFilesInNav);

		//Now add the file to the folder
		AddFileToFolder("Public " + FilesData.FolderName + randomName, randomName + ".jpg");

		//Logout of Connections
		Logout();

		System.out.println("INFO: End of Files BVT_Level_2 Test 3");
	}

	@Test(dependsOnMethods = { "uploadFileAndAddToFolder" })
	public void loadMultipleFiles() throws Exception {

		System.out.println("INFO: Start of Files BVT_Level_2 Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Generate random file name
		String randomName = genDateBasedRandVal();

		//Upload Multiple files to use in this test
		MultipleFileUpload(randomName, ".jpg", "Hydrangeas.jpg");

		//Logout of Connections
		Logout();

		System.out.println("INFO: End of Files BVT_Level_2 Test 4");
	}
}
