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

package com.ibm.atmn.homepagefvt.tasks.files;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.files.*;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesData;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesObjects;

import java.awt.event.KeyEvent;

import org.junit.*;
import static org.testng.AssertJUnit.*;


public class FVT_FilesMethods extends com.ibm.atmn.homepagefvt.tasks.communities.FVT_CommunitiesMethods {
	
	private FormInputHandler typist = getFormInputHandler();
	
	/*
	 * Replace file with same name as current file being uploaded
	 */
	public void replaceFile() throws Exception{
		//Replace existing file with same name
		if(sel.isTextPresent("A file with this name already exists")){
			sel.click(FilesObjects.Replace_Link);
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
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadFiles_Button);
							
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (CommonObjects.TestBrowser.contains("firefox")){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		 		
 		//In File Upload dialog enter the name and path to the file to upload
 		if (CommonObjects.TestOS.contains("Windows")) {
 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}else{
 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
		
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void uploadFile(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadFiles_Button);
		Thread.sleep(2000);		
		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		 		
 		//In File Upload dialog enter the name and path to the file to upload
 		if (sConfig.browserEnvironmentIsWindows) {
 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}else{
 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(500);
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
		if (Access=="Public"){
			cautiousClick(FVT_FilesObjects.AccessPublic);
		}else if (Access=="People or Communities"){
			cautiousClick(FVT_FilesObjects.AccessPeopleOrCommunities);
		}
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void uploadFile_Win_IE(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadFiles_Button);
		Thread.sleep(1500);				
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(3000);
				
 		//In File Upload dialog enter the name and path to the file to upload
 		typeNative(CommonData.WindowsFileLocation+FileUploadName);
 		Thread.sleep(500);
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(500);
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
		if (Access=="Public"){
			cautiousClick(FVT_FilesObjects.AccessPublic);
		}else if (Access=="People or Communities"){
			cautiousClick(FVT_FilesObjects.AccessPeopleOrCommunities);
		}
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void uploadFile_Community(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Browse Files
		//clickLink(FVT_FilesObjects.BrowseFiles);
							
		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		 		
 		//In File Upload dialog enter the name and path to the file to upload
 		if (sConfig.browserEnvironmentIsWindows) {
 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}else{
 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button_Comm);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void updateFile(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadNewVersion);
		Thread.sleep(2000);
		
		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		 		
 		//In File Upload dialog enter the name and path to the file to upload
 		if (sConfig.browserEnvironmentIsWindows) {
 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}else{
 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		/*
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
		if (Access=="Public"){
			cautiousClick(FVT_FilesObjects.AccessPublic);
		}else if (Access=="People or Communities"){
			cautiousClick(FVT_FilesObjects.AccessPeopleOrCommunities);
		}
		
		*/
		
		sel.type(FVT_FilesObjects.ChangeSummary, "Updated FVT File Summary");
		Thread.sleep(500);
		//Upload the file
		//sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		clickLink(FVT_FilesObjects.Upload_Button);
		
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
	}
	
	public void updateFile_Win_IE(String NameOfFile, String TypeOfFile, String FileUploadName, String Access) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadNewVersion);
		Thread.sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(3000);
			
 		//In File Upload dialog enter the name and path to the file to upload
 		typeNative(CommonData.WindowsFileLocation+FileUploadName);
 		Thread.sleep(500);
 		
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		/*
		//Rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(FVT_FilesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);	
			
		if (Access=="Public"){
			cautiousClick(FVT_FilesObjects.AccessPublic);
		}else if (Access=="People or Communities"){
			cautiousClick(FVT_FilesObjects.AccessPeopleOrCommunities);
		}
		
		*/
		
		sel.type(FVT_FilesObjects.ChangeSummary, "Updated FVT File Summary");
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		/*
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}*/
	}
	
	public void updateFile_Community(String TypeOfFile, String FileUploadName) throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Open "File Upload" dialog
		cautiousClick(FVT_FilesObjects.UploadNewVersion);
		Thread.sleep(500);
		
		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		 		
 		//In File Upload dialog enter the name and path to the file to upload
 		if (sConfig.browserEnvironmentIsWindows) {
 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}else{
 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
 			Thread.sleep(500);
 		}
 		
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);
		
		
		sel.type(FVT_FilesObjects.ChangeSummary, "Updated FVT File Summary");
			
		//Upload the file
		clickLink(FVT_FilesObjects.Upload_Button_Comm);
		
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		/*
		//Confirm successful file upload/update text displays
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}*/
	}
	
	public void MultipleFileUpload(String NameOfFile, String TypeOfFile, String FileUploadName)throws Exception{
		//Ensure that the main window is selected
		sel.selectWindow("null");
		
		//Click on Upload button and wait for dialog
		cautiousClick(FilesObjects.UploadFiles_Button);
		
		/*
		 * Loop now to add multiple files for upload
		 */
		try{
			int i = 0;
		     while (i < 7){ 
		    	//Using Enter to open the File Upload dialog
		 		if (CommonObjects.TestBrowser.contains("iexplore")) {
		 			//sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		 		}
		 		else if (CommonObjects.TestBrowser.contains("firefox")){
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		 		}
		 		Thread.sleep(3000);
		 		
		 		//In File Upload dialog enter the name and path to the file to upload
		 		if (CommonObjects.TestOS.contains("Windows")) {
		 			typeNative(CommonData.WindowsFileLocation+FileUploadName);
		 			Thread.sleep(500);
		 		}else{
		 			typeNative(CommonData.LinuxMacFileLocation+FileUploadName);
		 			Thread.sleep(500);
		 		}
		 		
		 		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		 		Thread.sleep(1500);
		 		
		 		//Rename the file
		 		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		 		Thread.sleep(500);
		 		typeNative(NameOfFile+i);
		 		Thread.sleep(500);
		 		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		 		Thread.sleep(500);
		 		if (CommonObjects.TestBrowser.contains("iexplore")) {
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 		}
		 		else if (CommonObjects.TestBrowser.contains("firefox")){
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		 		}
		  		i++;
		    }
		    sleep(1000);		
		}
		catch (Exception e) {
			sleep(2000);
		}
		
		//Upload the file
		clickLink(FilesObjects.Upload_Button);
				
		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(1000);
		
	}
	
	/**Click At item*/
	public void clickAtItem(String Link1, String Link2) throws Exception {
		//Click at the item provided
		sel.clickAt(Link1, Link2);
		//Thread.sleep(500);
	}
	
	public void CreateFolder(String FolderName, String FolderDescription, String ShareWithOption, String MemberToShare) throws Exception{
		//Click on the New Folder button
		cautiousClick(FilesObjects.NewFolder_Button);
						
		//Fill in the form
		sel.type(FilesObjects.CreateFolderName, FolderName);
		sel.type(FilesObjects.CreateFolderDescription, FolderDescription);
		sel.click(ShareWithOption);
		if (ShareWithOption.equals(FilesObjects.CreateFolderPublic)){
			sel.click(FilesObjects.CreateFolderEveryoneContribute);
		}
		else if (ShareWithOption.equals(FilesObjects.CreateFolderPeople)){
			//need to add code here for adding members to this folder
			
			//activate typeahead
			sel.focus(FVT_FilesObjects.FileMembersTypeAhead);
			typist.typeAndWait(FVT_FilesObjects.FileMembersTypeAhead, MemberToShare.replace(
					"ajones", "Amy Jones"), null);
			
			//select member from prompt or search and select
			selectMemberFromDropdownFiles(MemberToShare);
			
			//check member added and name appears
			Assert.assertTrue(sel.isTextPresent(MemberToShare.replace("ajones", "Amy Jones")));
			
			
		}
		//Save the form
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
		
	}
	
	public void PerformActionsOnFile (String Filename) throws Exception{
		//Verify the File name
		//assertEquals(Filename, sel.getText(FilesObjects.VerifyFileName));
		
		//Pin the file
		sel.click(FilesObjects.PinFile);
		
		//Recommend the file
		sel.click(FilesObjects.RecommendFile);
		
		//Add a Tag
		sel.click(FilesObjects.AddATagToFile);
		sel.type(FilesObjects.EnterTag, FilesData.FileTagName);
		sel.click(FilesObjects.SaveTag);
		
		//Add a comment
		sel.click(FilesObjects.AddACommentLink);
		sel.type("id=addCommentBody", FilesData.CommentText);
		sel.click(CommonObjects.SaveButton);
		
		//Stop following and then follow again
		sel.click(FilesObjects.ClickForActions);
		sel.click(FilesObjects.ClickForActionsOptionStopFollowing);
		Thread.sleep(1000);
		assertEquals(FilesData.StopFollowingMessage, sel.getText(FilesObjects.Messagediv));
		sel.click(FilesObjects.ClickForActions);
		sel.click(FilesObjects.ClickForActionsOptionFollow);
		Thread.sleep(1000);
		assertEquals(FilesData.FollowingMessage, sel.getText(FilesObjects.Messagediv));
		
		//Verify the download link
		assertTrue(sel.isElementPresent(FilesObjects.DownloadLink));
		
	}
	
	public void MoveFileToTrash(String Filename) throws Exception{
		//Select the file to move to trash
		sel.click(FilesObjects.FirstCheckbox);
		
		//Click on the Move to Trash button
		cautiousClick(FilesObjects.MoveToTrashButton);
		
		//Click OK to move file to trash
		clickLink(CommonObjects.OKButton);
		
		//Verify that the message appears stating that the file was moved to trash
		//assertEquals(Filename+FilesData.DeleteFileMessage, sel.getText(FilesObjects.FileMovedToTrashMessage));
		
		//Click on the Trash link in the nav to open the trash view
		//and verify that the file is in trash
		clickLink(FilesObjects.TrashLinkinNav);
		
		//Verify that the file is in trash
		//assertEquals(Filename, sel.getText(FilesObjects.FileNameInTrash));
		
		//Empty Trash
		clickLink(FilesObjects.EmptyTrash);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);
		
		//Verify that Trash is empty
		//assertEquals(FilesData.AllFilesDeleted, sel.getText(FilesObjects.TrashEmptyMessage));
		
	}
	
	public void AddFileToFolder(String FolderName, String FileName)throws Exception{
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
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			assertEquals(FileName, sel.getText(FilesObjects.VerifyFileInFolder));
 		}
 		else if (CommonObjects.TestBrowser.contains("firefox")){
 			sel.isElementPresent(FilesObjects.VerifyFileInFolder);
 			//String CheckFilesExists = sel.getText(FilesObjects.VerifyFileInFolder);
 			//assertTrue(FileName.contains(CheckFilesExists));
 		}
		
	}
	
	public void addFileToFolder(String FolderName, String FileType)throws Exception{
		
		//Add A File To Folder
		clickLink(FVT_FilesObjects.MyFoldersInNav);
		
		clickLink("link=" + FolderName);
		
		clickLink(FVT_FilesObjects.AddFiles);
		
		sel.focus(FileType);
		sel.click(FileType);
		Thread.sleep(1000);
		
		cautiousClick(FVT_FilesObjects.AddFileSubmit);
	}
	
	public void RemoveAnyExistingFilesFolders() throws Exception{
		//Ensure that the focus on the main window
		sel.selectWindow("null");
		
		//Select All Files in the My Files view to be deleted
		if (sel.isTextPresent(FilesData.NofilesFound)){
			assertEquals(FilesData.NofilesFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}else{
			clickLink(FilesObjects.SelectAllCheckbox);
			clickLink(FilesObjects.MoveToTrashButton);
			clickLink(CommonObjects.OKButton);
			Thread.sleep(1000);
			assertEquals(FilesData.NofilesFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}
				
		//Click on the My Folders link in nav 
		clickLink(FilesObjects.MyFoldersInNav);
				
		if (sel.isTextPresent(FilesData.NoFoldersFound)){
			assertEquals(FilesData.NoFoldersFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}else{
			while (sel.isElementPresent(FilesObjects.CheckForEntryInMyFolders)){
				sel.selectWindow("null");
				clickLink(FilesObjects.CheckForEntryInMyFolders);
				clickLink(FilesObjects.ClickForActions);
				clickLink(FilesObjects.ClickForActionsOptionDelete);
				//Thread.sleep(1000);
				if (CommonObjects.TestBrowser.contains("iexplore")) {
					sel.focus(CommonObjects.OKButton);
					clickLink(CommonObjects.OKButton);
		 		}
		 		else if (CommonObjects.TestBrowser.contains("firefox")){
		 			Thread.sleep(2000);
		 			sel.focus(CommonObjects.OKButton); 
		 			clickLink(CommonObjects.OKButton);
		 		}
		}
			assertEquals(FilesData.NoFoldersFound, sel.getText(FilesObjects.MyFilesEmptyMessage));
		}

	}
	
	public void AddATag() throws Exception {
		
		//Add tag
		clickLink(FVT_FilesObjects.AddATagToFile);
		
		//Enter tag
		sel.type(FVT_FilesObjects.EnterTag,FVT_FilesData.FileTagName);
		
		//Save tag
		clickLink(FVT_FilesObjects.SaveTag);
	}
	
	public void AddAComment() throws Exception {
		
		//Add comment
		clickLink(FVT_FilesObjects.AddACommentLink);
		
		//Enter comment
		sel.type(FVT_FilesObjects.EnterTheComment,FVT_FilesData.CommentText);
		
		//Save tag
		clickLink(CommonObjects.SaveButton);
	}
	
	public void RecommendAFile() throws Exception {
		
		//Add recommend
		clickLink(FVT_FilesObjects.RecommendFile);
		
	}
	

	
	
	public void ShareFileWith( String ShareType, String ShareRole,String MemberToShare) throws Exception {
		sel.select(FVT_FilesObjects.ShareType, ShareType);
		Thread.sleep(5000);
		sel.select(FVT_FilesObjects.ShareRole, ShareRole);
		Thread.sleep(5000);
		//activate typeahead
		sel.focus(FVT_FilesObjects.FileMembersTypeAhead);
		typist.typeAndWait(FVT_FilesObjects.FileMembersTypeAhead, MemberToShare.replace(
				"ajones", "Amy Jones"), null);
		
		//select member from prompt or search and select
		selectMemberFromDropdownFiles(MemberToShare);
		
		//check member added and name appears
		Assert.assertTrue(sel.isTextPresent(MemberToShare.replace("ajones", "Amy Jones")));
		
		
		//Save Share
		clickLink(FVT_FilesObjects.ShareSave);
	}
	
	public void SharePublicFile() throws Exception {
		
		//Open files from left nav
		clickLink("link=Files");
			
		//Open file to update
		clickLink(FVT_FilesObjects.ShareFiles);
		
		//Select the file to share
		//if(Type=="Public"){
		sel.focus(FVT_FilesObjects.SelectPublicFile);
		clickLink(FVT_FilesObjects.SelectPublicFile);
		//}
		/*else if(Type=="Moderated Community"){
			sel.focus(FVT_FilesObjects.SelectModCommFile);
			clickLink(FVT_FilesObjects.SelectModCommFile);
			}
		else if(Type=="Public Community"){
			sel.focus(FVT_FilesObjects.SelectPubCommFile);
			clickLink(FVT_FilesObjects.SelectPubCommFile);
			}*/
		
		//Confirm File Share
		clickLink(FVT_FilesObjects.ShareFilesConfirm);
	}
	
	public void selectMemberFromDropdownFiles(String userName) throws Exception {
		if (userName.contains("Amy Jones"))
			userName = userName.replace("Amy Jones", "ajones");

		cautiousFocus(FVT_FilesObjects.FileMembersTypeAhead_PromptList);
		sel.clickAt(FVT_FilesObjects.fullUserSearchIdentifierFiles, FVT_FilesObjects.fullSearchLinkFiles);
		sel.clickAt(FVT_FilesObjects.selectedUserIdentifierFiles, userName);
		
		
		//sel.clickAt(FVT_FilesObjects.fullUserSearchIdentifierFiles, "1,1");
		//Thread.sleep(500);
		//clickAtAndWait(FVT_FilesObjects.selectedUserIdentifierFiles, "1,1");
		//Thread.sleep(500);
	}
	
	/*
	public void ShareFileWith( String ShareType, String ShareRole, String MembersTypeAhead,String MemberToAdd,String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		sel.select(FVT_FilesObjects.ShareType, ShareType);
		Thread.sleep(5000);
		sel.select(FVT_FilesObjects.ShareRole, ShareRole);
		Thread.sleep(5000);
		MembersTypeAhead(MemberToAdd,MembersTypeAhead);
		selectMemberFromUserDropdown_Files(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		Thread.sleep(500);
		
		//Save Share
		clickLink(FVT_FilesObjects.ShareSave);
	}
	
	//Select a group from activated Groups textfield typeahead dropdown
	public void selectMemberFromUserDropdown_Files(String UserName, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		//Depending on what widget you are in use different locators
		
		clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		
		clickAtItem(SelectUserFromDropdown, "Amy " + UserName);
	}
	*/
	
}
