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

package com.ibm.atmn.lc.tasks.files;

import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.files.*;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.KeyEvent;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class FilesMethods extends CommonMethods {

	/*
	 * Replace file with same name as current file being uploaded
	 */
	public void replaceFile() throws Exception {

		//Replace existing file with same name
		if (sel.isTextPresent("A file with this name already exists")) {
			sel.click(FilesObjects.Replace_Link);
			Thread.sleep(3000);
		}
	}

	public void getPath(String FileUploadName) throws Exception {

		String ProjectPath = System.getProperty("user.dir");
		String FileLocation = "\\CommonTestObjects\\";
		String PathToCommonTestObjects = ProjectPath + FileLocation;
		System.out.println(PathToCommonTestObjects + FileUploadName);

	}

	/*
	 * Upload a private file
	 */
	public void uploadPrivateFile(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Ensure that the main window is selected
		sel.selectWindow("null");

		//Open "File Upload" dialog
		sel.click(FilesObjects.UploadFiles_Button);
		try {
			int i = 0;
			while (i < 120) {
				if (sel.isTextPresent(FilesData.CheckThatFileUploadExists)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}

		if (sConfig.browserIsIE) {
			sel.focus(CommonObjects.CancelLink);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}

		//In File Upload dialog enter the name and path to the file to upload
		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));

		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);

		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);

		//Upload the file
		clickLink(FilesObjects.Upload_Button);

		//Check if the file is still uploading
		FileUploading(5);

		//Error check
		//CheckForErrorsOnPage();
		//Thread.sleep(3000);

		//Confirm successful file upload/update text displays
		if (sel.isTextPresent(NameOfFile + TypeOfFile + " updated")) {
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else { //initial file upload
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}

	public void MultipleFileUpload(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Ensure that the main window is selected
		sel.selectWindow("null");

		//Click on Upload button and wait for dialog
		sel.click(FilesObjects.UploadFiles_Button);
		try {
			int i = 0;
			while (i < 120) {
				if (sel.isTextPresent(FilesData.CheckThatFileUploadExists)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}

		//Using Enter to open the File Upload dialog
		if (sConfig.browserIsIE) {
			sel.focus(CommonObjects.CancelLink);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		}
		else if (sConfig.browserIsFirefox) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		Thread.sleep(3000);

		/*
		 * Loop now to add multiple files for upload
		 */
		try {
			int i = 1;
			while (i < 6) {
				//In File Upload dialog enter the name and path to the file to upload
				typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));

				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(1500);

				//Rename the file
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(500);
				typeNative(NameOfFile + i);
				Thread.sleep(500);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(500);
				//Focus on the tag field and then use shift tab to return to
				//to the Browse button and enter
				sel.focus(FilesObjects.UploadFiles_Tag);
				Thread.sleep(500);
				ShiftTab();
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(1500);
				i++;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}

		//close the file upload dialog
		NumberOfTimeTabs(3);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);

		//Upload the file
		clickLink(FilesObjects.Upload_Button);

		//Check if the file is still uploading
		FileUploading(10);

		//Verify that the success message in the UI
		Assert.assertEquals(FilesData.UploadMessage, sel.getText(FilesObjects.FilesUploadedMessage));
	}

	public void FileUploading(int UploadTime) throws Exception {

		try {
			int i = 0;
			while (i < UploadTime) {
				if (sel.isTextPresent(FilesData.CheckThatFileUploadExists)) {
					sleep(3000);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(2000);
	}

	public void ShiftTab() throws Exception {

		Thread.sleep(500);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
		Thread.sleep(500);
	}

	public void NumberOfTimeTabs(int TabNo) throws Exception {

		try {
			int i = 0;
			while (i < TabNo) {
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sleep(500);
				i++;
			}
			//break;
			sleep(500);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	/** Click At item */
	public void clickAtItem(String Link1, String Link2) throws Exception {

		//Click at the item provided
		sel.clickAt(Link1, Link2);
		//Thread.sleep(500);
	}

	public void CreateFolder(String FolderName, String FolderDescription, String ShareWithOption) throws Exception {

		//Click on the New Folder button
		clickLink(FilesObjects.NewFolder_Button);

		//Fill in the form
		sel.type(FilesObjects.CreateFolderName, FolderName);
		sel.type(FilesObjects.CreateFolderDescription, FolderDescription);
		sel.click(ShareWithOption);
		if (ShareWithOption.equals(FilesObjects.CreateFolderPublic)) {
			sel.click(FilesObjects.CreateFolderEveryoneContribute);
		}
		else if (ShareWithOption.equals(FilesObjects.CreateFolderPeople)) {
			//need to add code here for adding members to this folder			
		}
		//Save the form
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);

	}

	public void PerformActionsOnFile(String Filename) throws Exception {

		//Verify the File name
		//assertEquals(Filename, sel.getText(FilesObjects.VerifyFileName));

		//Pin the file
		cautiousClick(FilesObjects.PinFile);

		//Recommend the file
		cautiousClick(FilesObjects.RecommendFile);

		//Add a Tag
		cautiousClick(FilesObjects.AddATagToFile);
		sel.type(FilesObjects.EnterTag, FilesData.FileTagName);
		cautiousClick(FilesObjects.SaveTag);

		//Add a comment
		cautiousClick(FilesObjects.AddACommentLink);
		sel.type("id=addCommentBody", FilesData.CommentText);
		clickLink(CommonObjects.SaveButton2);

		//Stop following and then follow again
		cautiousClick(FilesObjects.ClickForActions);
		cautiousClick(FilesObjects.ClickForActionsOptionStopFollowing);
		Thread.sleep(1000);
		Assert.assertEquals(FilesData.StopFollowingMessage, sel.getText(FilesObjects.Messagediv));
		cautiousClick(FilesObjects.ClickForActions);
		cautiousClick(FilesObjects.ClickForActionsOptionFollow);
		Thread.sleep(1000);
		Assert.assertEquals(FilesData.FollowingMessage, sel.getText(FilesObjects.Messagediv));

		//Verify the download link
		Assert.assertTrue(sel.isElementPresent(FilesObjects.DownloadLink));

	}

	public void MoveFileToTrash(String Filename) throws Exception {

		//Select the file to move to trash
		sel.click(FilesObjects.FirstCheckbox);

		//Click on the Move to Trash button
		cautiousClick(FilesObjects.MoveToTrashButton);

		//Click OK to move file to trash
		cautiousClick(CommonObjects.OKButton);

		//Wait for the file to be moved to Trash
		WaitForTrash();

		//Verify that the message appears stating that the file was moved to trash
		System.out.println(Filename + FilesData.DeleteFileMessage);
		Assert.assertFalse(sel.isTextPresent(Filename + FilesData.DeleteFileMessage));
 
		
		//Click on the Trash link in the nav to open the trash view
		//and verify that the file is in trash
		clickLink(FilesObjects.TrashLinkinNav);

		//Verify that the file is in trash
		System.out.println(Filename);
		Assert.assertTrue(sel.isTextPresent(Filename));

		//Empty Trash
		clickLink(FilesObjects.EmptyTrash);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(5000);

		//Verify that Trash is empty
		Assert.assertTrue(sel.isTextPresent(FilesData.AllFilesDeleted));

	}

	public void AddFileToFolder(String FolderName, String FileName) throws Exception {

		//Click on the more link
		clickLink(FilesObjects.MyFilesViewMoreLink);

		//Click on Add To Folder link
		clickLink(FilesObjects.AddToFoldersLink);

		//Click on the folder to add the file too
		sel.click(FilesObjects.ChooseFolder);

		//Click on the add to folder button
		Thread.sleep(500);
		sel.focus(FilesObjects.AddtoFolderCheckbox);
		Thread.sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);

		//Load the My Folders view
		clickLink("link=My Folders");

		//Open the folder that added the file too and verify that it is there
		clickLink(FilesObjects.CheckForEntryInMyFolders);

		//Verify that the file has being added to the folder
		if (sConfig.browserIsIE) {
			Assert.assertEquals(FileName, sel.getText(FilesObjects.VerifyFileInFolder));
		}
		else if (sConfig.browserIsFirefox) {
			sel.isElementPresent(FilesObjects.VerifyFileInFolder);
			//String CheckFilesExists = sel.getText(FilesObjects.VerifyFileInFolder);
			//assertTrue(FileName.contains(CheckFilesExists));
		}

	}

	public void RemoveAnyExistingFilesFolders() throws Exception {

		//Ensure that the focus on the main window
		sel.selectWindow("null");

		//Click on the My files link in nav 
		clickLink(FilesObjects.MyFilesInNav);

		//Select All Files in the My Files view to be deleted
		if (sel.isTextPresent(FilesData.NofilesFound)) {
			Assert.assertEquals(FilesData.NofilesFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}
		else {
			clickLink(FilesObjects.SelectAllCheckbox);
			clickLink(FilesObjects.MoveToTrashButton);
			clickLink(CommonObjects.OKButton);
			WaitForTrash();
			Assert.assertEquals(FilesData.NofilesFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}

		//Click on the My Folders link in nav 
		clickLink(FilesObjects.MyFoldersInNav);

		if (sel.isTextPresent(FilesData.NoFoldersFound)) {
			Assert.assertEquals(FilesData.NoFoldersFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}
		else {
			while (sel.isElementPresent(FilesObjects.CheckForEntryInMyFolders)) {
				sel.selectWindow("null");
				clickLink(FilesObjects.CheckForEntryInMyFolders);
				cautiousClick(FilesObjects.ClickForActions);
				cautiousClick(FilesObjects.ClickForActionsOptionDelete);
				Thread.sleep(500);
				if (sConfig.browserIsIE) {
					sel.focus(CommonObjects.FormButton);
					sleep(1000);
					cautiousClick(CommonObjects.FormButton);
					sleep(3000);
					if (sel.isTextPresent(CommonObjects.FormButton)){
						sel.focus(CommonObjects.FormButton);
						sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
						sleep(3000);
					}
				}
				else if (sConfig.browserIsFirefox) {
					sel.focus(CommonObjects.FormButton);
					sleep(1000);
					//ShiftTab();
					//sleep(1000);
					sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
					sleep(5000);
				}
			}
			Assert.assertEquals(FilesData.NoFoldersFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}

	}

	public void WaitForTrash() throws Exception {

		try {
			int i = 0;
			while (i < 10) {
				if (sel.isTextPresent(FilesData.CheckThatMoveToTrashExists)) {
					sleep(2000);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(1500);
	}

}
