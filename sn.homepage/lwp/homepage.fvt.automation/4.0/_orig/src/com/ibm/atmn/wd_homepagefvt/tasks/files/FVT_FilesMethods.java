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

package com.ibm.atmn.wd_homepagefvt.tasks.files;

import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.*;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;


import org.testng.Assert;
import org.testng.AssertJUnit;

import static org.testng.AssertJUnit.*;


public class FVT_FilesMethods extends FVT_CommunitiesMethods {
	
	private FormInputHandler typist;
	
	/*
	 * Replace file with same name as current file being uploaded
	 */
	public void replaceFile() throws Exception{
		//Replace existing file with same name
		if(driver.isTextPresent("A file with this name already exists")){
			clickLink(FilesObjects.Replace_Link);
			Thread.sleep(3000);
		}
	}
	
	public void getPath(String FileUploadName) throws Exception{
		String ProjectPath = System.getProperty("user.dir");
		String FileLocation = "\\CommonTestObjects\\";
		String PathToCommonTestObjects = ProjectPath+FileLocation;
		System.out.println(PathToCommonTestObjects+FileUploadName);
		
	}
	
	/*
	 * Upload a private file
	 */
	public void uploadPrivateFile(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception{
		//Ensure that the main window is driverected
		//driver.selectWindow("null");
		
		//Open "File Upload" dialog
		clickLink(FVT_FilesObjects.UploadFiles_Button);
					
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FVT_FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		
		//Rename the file
		driver.getSingleElement("link="+FileUploadName).click();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).clear();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).type(NameOfFile);
		sleep(500);
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Check if the file is still uploading
		FileUploading(10);
		
		if (driver.getPageSource().contains("Successfully uploaded: "+ NameOfFile + TypeOfFile)) {
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", driver.getPageSource().contains("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void FileUploading(int UploadTime) throws Exception {

		try {
			int i = 0;
			while (i < UploadTime) {
				if (!driver.getPageSource().contains(FVT_FilesObjects.FilesUploadedMessage)) {
					sleep(5000);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(2000);
	}
	
	public void uploadFile(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{
		//Ensure that the main window is selected
		;
		
		//Open "File Upload" dialog
		clickLink(FVT_FilesObjects.UploadFiles_Button);
		
		
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FVT_FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		
		//Rename the file
		driver.getSingleElement("link="+FileUploadName).click();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).clear();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).type(NameOfFile);
		sleep(500);
			
		if (Access=="Public"){
			clickLink(FVT_FilesObjects.AccessPublic);
		}else if (Access=="People or Communities"){
			clickLink(FVT_FilesObjects.AccessPeopleOrCommunities);
		}
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Check if the file is still uploading
		FileUploading(10);
		
		if (driver.getPageSource().contains("Successfully uploaded: "+ NameOfFile + TypeOfFile)) {
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", driver.getPageSource().contains("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	
	
	public void uploadFile_Community(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception{
		//Ensure that the main window is selected
		
		
		//Browse Files
		//clickLink(FVT_FilesObjects.BrowseFiles);
							
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		
		//Rename the file
		driver.getSingleElement("link="+FileUploadName).click();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).clear();
		driver.getSingleElement(FVT_FilesObjects.UploadFiles_Name).type(NameOfFile);
		sleep(500);
			
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button_Comm);
		
		//Check if the file is still uploading
		FileUploading(10);
		
		if (driver.getPageSource().contains("Successfully uploaded: "+ NameOfFile + TypeOfFile)) {
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", driver.getPageSource().contains("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void updateFile(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{

		//Open "File Upload" dialog
		clickLink(FVT_FilesObjects.UploadNewVersion);
		
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
	
		//
		driver.getSingleElement(FVT_FilesObjects.ChangeSummary).type("Updated FVT File Summary");
		Thread.sleep(500);
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Check if the file is still uploading
		FileUploading(10);
		
		//Confirm successful file upload/update text displays
		if(driver.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", driver.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		
	}
	
	
	
	public void updateFile_Community(String TypeOfFile, String FileUploadName) throws Exception{
		//Open "File Upload" dialog
		clickLink(FVT_FilesObjects.UploadNewVersion);
		
		//In File Upload dialog enter the name and path to the file to upload
		driver.getSingleElement(FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
	
		//
		driver.getSingleElement(FVT_FilesObjects.ChangeSummary).type("Updated FVT File Summary");
		Thread.sleep(500);
		//Upload the file
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button_Comm);
		
		//Check if the file is still uploading
		FileUploading(10);
	
	}
	
	public void MultipleFileUpload(String NameOfFile, String TypeOfFile, String FileUploadName)throws Exception{
		
		//Click on Upload button and wait for dialog
		driver.getSingleElement(FilesObjects.UploadFiles_Button).click();
	
		/*
		 * Loop now to add multiple files for upload
		 */
		try {
			int i = 1;
			while (i < 6) {
				//In File Upload dialog enter the name and path to the file to upload
				//driver.typeNative(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
				driver.getSingleElement(FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
				
				//Rename the file
				driver.getSingleElement("link="+FileUploadName).click();
				sleep(500);
				driver.typeNative(NameOfFile+i);
				sleep(500);

				//Click into tags field in order to get the focus out of the rename field
				driver.getSingleElement(FVT_FilesObjects.UploadFiles_Tag).click();
				i++;
				
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}
		
		//Upload the file
		clickLink(FilesObjects.Upload_Button);

		//Check if the file is still uploading
		FileUploading(10);

		//Verify that the success message in the UI
		Assert.assertTrue(driver.getPageSource().contains(FVT_FilesData.UploadMessage));
		
	}
	
	
	public void CreateFolder(String FolderName, String FolderDescription, String ShareWithOption, String MemberToShare) throws Exception{
		//Click on the New Folder button
		clickLink(FilesObjects.NewFolder_Button);
						
		//Fill in the form
		driver.getSingleElement(FilesObjects.CreateFolderName).type(FolderName);
		driver.getSingleElement(FilesObjects.CreateFolderDescription).type(FolderDescription);
		clickLink(ShareWithOption);
		if (ShareWithOption.equals(FilesObjects.CreateFolderPublic)){
			clickLink(FilesObjects.CreateFolderEveryoneContribute);
		}
		else if (ShareWithOption.equals(FilesObjects.CreateFolderPeople)){
			//need to add code here for adding members to this folder
			
			//activate typeahead
			//driver.focus(FVT_FilesObjects.FileMembersTypeAhead);
			typist.typeAndWait(FVT_FilesObjects.FileMembersTypeAhead, MemberToShare.replace(
					"ajones", "Amy Jones"), null);
			
			//select member from prompt or search and select
			selectMemberFromDropdownFiles(MemberToShare);
			
			//check member added and name appears
			Assert.assertTrue(driver.isTextPresent(MemberToShare.replace("ajones", "Amy Jones")));
			
			
		}
		//Save the form
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
		
	}
	
	public void PerformActionsOnFile (String Filename) throws Exception{
		//Verify the File name
		//assertEquals(Filename, driver.getText(FilesObjects.VerifyFileName));
		
		//Pin the file
		clickLink(FilesObjects.PinFile);
		
		//Recommend the file
		clickLink(FilesObjects.RecommendFile);
		
		//Add a Tag
		clickLink(FilesObjects.AddATagToFile);
		driver.getSingleElement(FilesObjects.EnterTag).type(FilesData.FileTagName);
		clickLink(FilesObjects.SaveTag);
		
		//Add a comment
		clickLink(FilesObjects.AddACommentLink);
		driver.getSingleElement("id=addCommentBody").type(FilesData.CommentText);
		clickLink(CommonObjects.SaveButton);
		
		//Stop following and then follow again
		clickLink(FilesObjects.ClickForActions);
		clickLink(FilesObjects.ClickForActionsOptionStopFollowing);
		Thread.sleep(1000);
		assertEquals(FilesData.StopFollowingMessage, driver.getSingleElement(FilesObjects.Messagediv).getText());
		clickLink(FilesObjects.ClickForActions);
		clickLink(FilesObjects.ClickForActionsOptionFollow);
		Thread.sleep(1000);
		assertEquals(FilesData.FollowingMessage, driver.getSingleElement(FilesObjects.Messagediv).getText());
		
		//Verify the download link
		assertTrue(driver.isElementPresent(FilesObjects.DownloadLink));
		
	}
	
	public void MoveFileToTrash(String Filename) throws Exception {

		//Select the file to move to trash
		clickLink(FilesObjects.FirstCheckbox);

		//Click on the Move to Trash button
		clickLink(FilesObjects.MoveToTrashButton);

		//Click OK to move file to trash
		clickLink(CommonObjects.OKButton);

		//Wait for the file to be moved to Trash
		WaitForTrash();

		//Verify that the message appears stating that the file was moved to trash
		driver.getPageSource().contains(Filename + FilesData.DeleteFileMessage);
		
		//Click on the Trash link in the nav to open the trash view
		//and verify that the file is in trash
		clickLink(FilesObjects.TrashLinkinNav);

		//Verify that the file is in trash
		System.out.println(Filename);
		//Assert.assertTrue(sel.isTextPresent(Filename));
		driver.getPageSource().contains(Filename);
		
		//Empty Trash
		clickLink(FilesObjects.EmptyTrash);
		
		//Confirm to Empty the Trash
		clickLink(CommonObjects.OKButton);
		
		//Verify that Trash is empty
		Assert.assertTrue(driver.getPageSource().contains(FilesData.AllFilesDeleted));

	}
	
	public void WaitForTrash() throws Exception {

		try {
			int i = 0;
			while (i < 10) {
				if (driver.getPageSource().contains(FilesData.CheckThatMoveToTrashExists)) {
					sleep(3500);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(1500);
	}
	
	public void AddFileToFolder(String FolderName, String FileName)throws Exception{
		//Click on the more link
		clickLink(FilesObjects.MyFilesViewMoreLink);
		
		//Click on Add To Folder link
		clickLink(FilesObjects.AddToFoldersLink);
		
		//Click on the folder to add the file too
		clickLink(FilesObjects.ChooseFolder);
		
		//Click on the add to folder button
		Thread.sleep(500);
		clickLink(FilesObjects.AddtoFolderCheckbox);
	
			
		//Load the My Folders view
		clickLink("link=My Folders");
		
		//Open the folder that added the file too and verify that it is there
		clickLink(FilesObjects.CheckForEntryInMyFolders);
		
		//Verify that the file has being added to the folder
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			assertTrue(isTextPresent(FileName));
 		}
 		else if (CommonObjects.TestBrowser.contains("firefox")){
 			driver.isElementPresent(FilesObjects.VerifyFileInFolder);
 			//String CheckFilesExists = driver.getText(FilesObjects.VerifyFileInFolder);
 			//assertTrue(FileName.contains(CheckFilesExists));
 		}
		
	}
	
	public void addFileToFolder(String FolderName, String FileType)throws Exception{
		
		//Add A File To Folder
		clickLink(FVT_FilesObjects.MyFoldersInNav);
		
		clickLink("link=" + FolderName);
		
		clickLink(FVT_FilesObjects.AddFiles);

		clickLink(FileType);
		Thread.sleep(1000);
		
		clickLink(FVT_FilesObjects.AddFileSubmit);
	}
	
	
	public void AddATag() throws Exception {
		
		//Add tag
		clickLink(FVT_FilesObjects.AddATagToFile);
		
		//Enter tag
		driver.getSingleElement(FVT_FilesObjects.EnterTag).type(FVT_FilesData.FileTagName);
		
		//Save tag
		clickLink(FVT_FilesObjects.SaveTag);
	}
	
	public void AddAComment() throws Exception {
		
		//Add comment
		clickLink(FVT_FilesObjects.AddACommentLink);
		
		//Enter comment
		driver.getSingleElement(FVT_FilesObjects.EnterTheComment).type(FVT_FilesData.CommentText);
		
		//Save tag
		clickLink(CommonObjects.SaveButton);
	}
	
	public void RecommendAFile() throws Exception {
		
		//Add recommend
		clickLink(FVT_FilesObjects.RecommendFile);
		
	}
	

	
	
	public void ShareFileWith( String ShareType, String ShareRole,String MemberToShare) throws Exception {
		driver.getSingleElement(FVT_FilesObjects.ShareType).useAsDropdown().deselectByVisibleText(ShareType);
		Thread.sleep(5000);
		driver.getSingleElement(FVT_FilesObjects.ShareRole).useAsDropdown().deselectByVisibleText(ShareRole);
		Thread.sleep(5000);
		//activate typeahead
		//driver.focus(FVT_FilesObjects.FileMembersTypeAhead);
		typist.typeAndWait(FVT_FilesObjects.FileMembersTypeAhead, MemberToShare.replace(
				"ajones", "Amy Jones"), null);
		
		//select member from prompt or search and select
		selectMemberFromDropdownFiles(MemberToShare);
		
		//check member added and name appears
		Assert.assertTrue(driver.isTextPresent(MemberToShare.replace("ajones", "Amy Jones")));
		
		
		//Save Share
		clickLink(FVT_FilesObjects.ShareSave);
	}
	
	public void SharePublicFile() throws Exception {
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink(FVT_FilesObjects.ShareFiles);
		
		//select the file to share
		//if(Type=="Public"){
		//driver.focus(FVT_FilesObjects.selectPublicFile);
		clickLink(FVT_FilesObjects.SelectPublicFile);
		//}
		/*else if(Type=="Moderated Community"){
			driver.focus(FVT_FilesObjects.selectModCommFile);
			clickLink(FVT_FilesObjects.selectModCommFile);
			}
		else if(Type=="Public Community"){
			driver.focus(FVT_FilesObjects.selectPubCommFile);
			clickLink(FVT_FilesObjects.selectPubCommFile);
			}*/
		
		//Confirm File Share
		clickLink(FVT_FilesObjects.ShareFilesConfirm);
	}
	
	public void selectMemberFromDropdownFiles(String userName) throws Exception {
		if (userName.contains("Amy Jones"))
			userName = userName.replace("Amy Jones", "ajones");

		driver.getSingleElement(FVT_FilesObjects.FileMembersTypeAhead_PromptList).click();
		//sel.clickAt(FVT_FilesObjects.fullUserSearchIdentifierFiles, FVT_FilesObjects.fullSearchLinkFiles);
		//sel.clickAt(FVT_FilesObjects.selectedUserIdentifierFiles, userName);
		
		driver.getSingleElement(FVT_FilesObjects.fullUserSearchIdentifierFiles).click();
		driver.getSingleElement(FVT_FilesObjects.fullSearchLinkFiles).click();
		driver.getSingleElement(FVT_FilesObjects.selectedUserIdentifierFiles).click();
		driver.getSingleElement(userName).click();
		
		//clickLinkAt(FVT_FilesObjects.fullUserSearchIdentifierFiles, "1,1");
		//Thread.sleep(500);
		//clickAtAndWait(FVT_FilesObjects.selectedUserIdentifierFiles, "1,1");
		//Thread.sleep(500);
	}
	
	/*
	public void ShareFileWith( String ShareType, String ShareRole, String MembersTypeAhead,String MemberToAdd,String selectUserFromDropdown, String PopupIdentifier) throws Exception {
		driver.select(FVT_FilesObjects.ShareType, ShareType);
		Thread.sleep(5000);
		driver.select(FVT_FilesObjects.ShareRole, ShareRole);
		Thread.sleep(5000);
		MembersTypeAhead(MemberToAdd,MembersTypeAhead);
		selectMemberFromUserDropdown_Files(MemberToAdd, selectUserFromDropdown, PopupIdentifier);
		Thread.sleep(500);
		
		//Save Share
		clickLink(FVT_FilesObjects.ShareSave);
	}
	
	//select a group from activated Groups textfield typeahead dropdown
	public void selectMemberFromUserDropdown_Files(String UserName, String selectUserFromDropdown, String PopupIdentifier) throws Exception {
		//Depending on what widget you are in use different locators
		
		clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		
		clickAtItem(selectUserFromDropdown, "Amy " + UserName);
	}
	*/
	
}
