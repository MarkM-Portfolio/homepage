/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.tasks.mediagallery;

import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.appobjects.mediagallery.MediaGalleryObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;

public class MediaGalleryMethods extends CommonMethods {

	public void CheckForAddingWidget(int Timeout) throws Exception {

		/*
		 * Method to check if the Adding Widget text is present and if it is sleep for 1000 millisecond and then check
		 * again if the text is not present then the script continues
		 */
		int i = 0;
		while (i < Timeout) {
			if (sel.isTextPresent("Adding Widget")) {
				Thread.sleep(1000);
				i = i + 1;
			}
			break;
		}

	}

	public void AddMediaGalleryWidgetToCommunity(String CommunityActionOption) throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		sel.click(CommunityActionOption);
		Thread.sleep(3000);

		//Add the Media Gallery widget
		sel.click(CommunitiesObjects.WidgetMediaGallery);

		//Wait for the widget to loaded
		CheckForAddingWidget(20);

		//Close the widget section
		sel.click(CommunitiesObjects.WidgetSectionClose);

	}

	public void TabToUploadButtons(int TabAmount) throws Exception {

		try {
			int count = 0;
			while (count < TabAmount) {
				Thread.sleep(500);
				sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
				Thread.sleep(500);
				count++;
			}
			sel.keyDownNative(String.valueOf(KeyEvent.VK_ENTER));
			sel.keyUpNative(String.valueOf(KeyEvent.VK_ENTER));
			Thread.sleep(1500);
		} catch (Exception e) {

		}

	}

	public void UploadFileFromMediaGallery(String TypeOfFile, String FileUploadName) throws Exception {

		//click on the Media Gallery link in the left nav
		clickLink("link=Media Gallery");

		//Choose to add a photo or video
		sel.focus("link=Media Gallery");
		sel.focus("link=Media Gallery");

		if (FileUploadName.contains(".mp4")) {
			TabToUploadButtons(3);
		}
		else if (FileUploadName.contains(".jpg")) {
			TabToUploadButtons(2);
		}

		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		else if (sConfig.browserIsFirefox) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		sleep(3000);

		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));
		//close the file upload dialog
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1000);

		//Add a Tag and Description
		sel.type("name=uploadFileTaggerTypeAhead", "tagfor" + FileUploadName);
		sel.type("name=_description", "this is a test description");

		//Upload
		sel.click(MediaGalleryObjects.Upload_Button);

		//If the file type is mp4 then sleep for 10 seconds
		if (FileUploadName.contains(".mp4")) {
			sleep(10000);
		}
		else {
			sleep(3000);
		}

	}

	public void UploadFileFromMediaGalleryWidget(String TypeOfFile, String FileUploadName) throws Exception {

		/*
		 * Upload a file to the Media Gallery The Media Gallery widget must be added prior to upload
		 */

		try {
			//Click on the Upload link in the Widget
			cautiousClick(MediaGalleryObjects.MediaGalleryUploadLink);

			//Choose to add a photo
			cautiousClick(TypeOfFile);

			//Firefox workaround
			if (sConfig.browserIsFirefox) {
				sel.waitForPageToLoad("5000");
				sel.type("name=_label", FileUploadName);
			}

			if (sConfig.browserIsIE) {
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			}
			else if (sConfig.browserIsFirefox) {
				sleep(1000);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sleep(1000);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));

			}
			Thread.sleep(8000);

			//Add a Tag and Description
			sel.type("name=uploadFileTaggerTypeAhead", "tagfor" + FileUploadName);
			sel.type("name=_description", "this is a test description");
			typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));
			sleep(5000);

			//close the file upload dialog
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sleep(1000);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
			sleep(5000);

			//Upload file now
			sel.click(MediaGalleryObjects.Upload_Button);

			//If the file type is mp4 then sleep for 10 seconds
			if (FileUploadName.contains(".mp4")) {
				sleep(10000);
			}
			else {
				sleep(3000);
			}
			//Check for the upload successful message
			if (sel.isTextPresent("Upload successful")) {
				System.out.println("\nTEST STEP::The video was uploaded successfully.");
			}
			else {
				Assert.fail(FileUploadName + " . No success message for upload found.");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void EditingUploadFileMediaGallery(String FileUploadName) throws Exception {

		//Click on the file to edit
		clickLink(FileUploadName);

		//Recommend the file
		clickLink(MediaGalleryObjects.RecommendMediaGalleryFile);

		//Add a Comment
		clickLink(MediaGalleryObjects.AddAComment);

		//Edit a comment

		//Delete a comment

	}

	public void RecommendMediaGalleryItem() throws Exception {

		/*
		 * Recommed an uploaded file The uploaded file must be open
		 */

		//Click the recommendations link
		clickLink(MediaGalleryObjects.RecommendMediaGalleryFile);

	}

	public void EditMediaGalleryItemProperties() throws Exception {

		/*
		 * Edit the properties of an uploaded file The uploaded file must be open
		 */

		//Click the Edit Properties button
		clickLink(MediaGalleryObjects.MediaGalleryEditProperties);

		//Edit Tag, Description, Metadata
		sel.type("name=uploadFileTaggerTypeAhead", "Edited Tag");
		sel.type("name=_description", "Edited Description");

		//Save the changes
		clickLink(MediaGalleryObjects.MediaGalleryEditPropertiesOk);

		//Check that the edit was made. was save successfully message expected
		if (!sel.isTextPresent("was saved successfully")) {
			Assert.fail("The properties Saved Successfully message was not found.");
		}

	}

	public void VerifyMediaGalleryEditProperties() throws Exception {

		/*
		 * Verify the edit properties of an uploaded file The uploaded file must be open
		 */

		//Get the info from the About this summary
		String UploadInformation = sel.getText(MediaGalleryObjects.AboutThisUpload);
		System.out.println(UploadInformation);

		//Check the return information for the new description
		if (!UploadInformation.contains("Description: Edited Description")) {
			Assert.fail("Edited Description not present");
		}
		else {
			System.out.println("Edited description present.");
		}

		Assert.assertEquals(sel.getText(MediaGalleryObjects.AboutPhotoDecription), "Edited Description");

		//Get the Tags information
		String UploadTagsInformation = sel.getText(MediaGalleryObjects.MediaGalleryTags);
		System.out.println(UploadTagsInformation);

		//Check the Tags for the new tag
		Assert.assertEquals(UploadTagsInformation, "Tags: edited, tag");

	}

	public void AddComments(String Comment) throws Exception {

		/*
		 * Add a comment to an uploaded file The uploaded file must be open
		 */

		//Add a Comment
		clickLink(MediaGalleryObjects.AddAComment);

		//Add a comment
		typeNative(Comment);

		//Save the comment
		clickLink(MediaGalleryObjects.AddACommentSave);

	}

	public void EditComments(String Comment) throws Exception {

		/*
		 * Edit an existing comment in an uploaded file The uploaded file must be open
		 */

		//Edit the comment
		clickLink(MediaGalleryObjects.AddACommentEdit);

		//Add a comment
		typeNative(Comment);

		//Save the comment
		clickLink(MediaGalleryObjects.AddACommentSave);

	}

	public void DeleteComment() throws Exception {

		/*
		 * Delete a comment from an uploaded file The uploaded file must be open
		 */

		//Delete a comment
		clickLink(MediaGalleryObjects.AddACommentDelete);

		//Click OK on the delete verification dialog
		clickLink(MediaGalleryObjects.AddACommentOK);
	}

	public void DeleteMediaGalleryFile(String FileToDelete) throws Exception {

		//click on the Media Gallery link in the left nav
		clickLink("link=Media Gallery");
		sel.focus("link=Media Gallery");

		//Open the media gallery file
		String Name = sel.getText(MediaGalleryObjects.SingleUpload);
		if (Name.contains(FileToDelete)) {
			clickLink(MediaGalleryObjects.SingleUpload);
		}
		else {
			System.out.println("\nTEST NOTICE: Uploaded file not found. Link is : " + Name);
			Assert.fail("Uploaded file not found. Link is : " + Name);
		}

		//Delete the file
		clickLink(MediaGalleryObjects.MediaGalleryDeleteFile);

		//Confirm the file deletion
		clickLink(MediaGalleryObjects.ConfirmDelete_Button);

		//Verfiy that the file has been deleted
		Assert.assertTrue(sel.isTextPresent("The file was deleted"), "The file was deleted");

	}

	public void ShiftTab(int TabNo) throws Exception {

		try {
			int i = 0;
			while (i < TabNo) {
				Thread.sleep(500);
				sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
				sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
				Thread.sleep(500);
				i++;
			}
			sleep(500);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void NumberOfTimeTabs(int TabNo) throws Exception {

		try {
			int i = 0;
			while (i < TabNo) {
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sleep(500);
				i++;
			}
			sleep(500);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void WaitForUploadDialog(String FileTypeToUpload) throws Exception {

		try {
			int i = 0;
			while (i < 20) {
				if (sel.isTextPresent(FileTypeToUpload)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(2000);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void WaitForUploadToComplete() throws Exception {

		try {
			int i = 0;
			while (i < 10) {
				if (sel.isElementPresent(MediaGalleryObjects.MediaGalleryFileUploadTags)) {
					sleep(4000);
					i++;
				}
				break;
			}
		} catch (Exception e) {
			sleep(2000);
		}
	}
}
