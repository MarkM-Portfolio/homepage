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

package com.ibm.atmn.lc.tasks.blogs;

import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.blogs.BlogsData;
import com.ibm.atmn.lc.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;

public class BlogsMethods extends CommonMethods {

	private FormInputHandler typist = getFormInputHandler();

	public void CreateABlogAsAdmin(String BlogsName, String BlogsAddress) throws Exception {

		// Enter the details for the blogs
		typist.type(BlogsObjects.BlogsNameField, BlogsName);
		typist.type(BlogsObjects.BlogsAddress, BlogsAddress);
		typist.type(BlogsObjects.BlogsTags, BlogsData.BlogsTag);
		typist.type(BlogsObjects.BlogsDescription, BlogsData.BlogsDescription);
		sel.select(BlogsObjects.BlogsTimeZone, BlogsData.BlogsTimeZoneOption);
		sleep(500);
		sel.select(BlogsObjects.BlogsTheme, BlogsData.BlogsThemeOption);
		sleep(500);
		// Save the form
		FormSaveButton(CommonObjects.SaveButton);

	}

	public void SetBlogAsPrimary(String BlogsName) throws Exception {

		// Set as default
		clickLink(BlogsObjects.BlogsSetAsPrimaryBlog);

		// Verify the change
		Assert.assertTrue(sel.isTextPresent("You have set [" + BlogsName + "] to be your primary blog"));

	}

	public void ChangeAdminSettings() throws Exception {

		// Click on Admistration
		clickLink(BlogsObjects.Administration);

		// Edit the Handle of Blog to server as Blogs Homepage
		sel.type(BlogsObjects.BlogsSiteSettingsHandleOfBlog, stamp(BlogsData.BlogsHandleOfBlog));

		// Save the change
		clickLink(BlogsObjects.BlogsSiteSettingsSave);

		// Verify the change
		Assert.assertTrue(sel.isTextPresent("Change saved."));
	}

	public void CreateABlogAsStandardUser(String BlogsName, String BlogsAddress) throws Exception {

		// Enter the details for the blogs
		sel.type(BlogsObjects.BlogsNameField, BlogsName);
		Thread.sleep(500);
		sel.type(BlogsObjects.BlogsAddress, BlogsAddress);
		Thread.sleep(500);
		sel.type(BlogsObjects.BlogsTags, BlogsData.BlogsTag);
		Thread.sleep(500);
		sel.type(BlogsObjects.BlogsDescription, BlogsData.BlogsDescription);
		Thread.sleep(500);
		sel.select(BlogsObjects.BlogsTimeZone, BlogsData.BlogsTimeZoneOption);
		Thread.sleep(500);
		sel.select(BlogsObjects.BlogsTheme, BlogsData.BlogsThemeOption);
		Thread.sleep(500);
		// Save the form
		FormSaveButton(CommonObjects.SaveButton);
	}

	public void CreateANewBlogEntry(String BlogsName, String BlogEntryName) throws Exception {

		// Select the blog that you just created
		clickLink("link=" + BlogsName);
		Thread.sleep(1000);

		// Click on the New Entry button
		clickLink(BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		sel.type(BlogsObjects.BlogsNewEntryTitle, BlogEntryName);
		Thread.sleep(500);
		clickLink(BlogsObjects.BlogsNewEntryAddTags);
		sel.type(BlogsObjects.BlogsNewEntryAddTagsTextfield, BlogsData.BlogsNewEntryTag);
		clickLink(BlogsObjects.BlogsNewEntryAddTagsOK);
		Thread.sleep(500);
		sel.click(BlogsObjects.BlogsNewEntryHTML);
		Thread.sleep(500);
		sel.type(BlogsObjects.BlogsNewEntryRichText, BlogsData.BlogsNewEntryEntry);
		Thread.sleep(500);
		clickLink(BlogsObjects.BlogsNewEntryPost);

	}

	public void CreateANewBlogEntryWithImage(String BlogEntryName) throws Exception {

		// Select the blog that you just created
		// clickLink("link="+BlogsData.BlogsName);
		// Thread.sleep(1000);

		// Click on the New Entry button
		clickLink(BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		sel.type(BlogsObjects.BlogsNewEntryTitle, BlogEntryName);
		Thread.sleep(1000);
		if (sConfig.browserIsIE) {
			sel.mouseDown(BlogsObjects.BlogsCKEInsertImageButton);
			sel.mouseUp(BlogsObjects.BlogsCKEInsertImageButton);
			Thread.sleep(2500);
			sel.click(BlogsObjects.BlogsCKEInsertFromRecent);
			Thread.sleep(1000);
			sel.click(BlogsObjects.BlogsCKEChoosePhoto);
			Thread.sleep(1000);
			sel.click(BlogsObjects.BlogsCKEInsertButton);

		}
		else if (sConfig.browserIsGoogleChrome) {
			/*
			 * Will need to come back to this on chrome at a later stage as I could not get the code to work to open the
			 * Choose File dialog and then select a image - working on IE & FF
			 */

		}
		else {
			sel.click(BlogsObjects.BlogsCKEInsertImageButton);
			Thread.sleep(2500);
			sel.click(BlogsObjects.BlogsCKEInsertFromRecent);
			Thread.sleep(1000);
			sel.click(BlogsObjects.BlogsCKEChoosePhoto);
			Thread.sleep(1000);
			sel.click(BlogsObjects.BlogsCKEInsertButton);

		}

		Thread.sleep(2500);
		sel.focus(BlogsObjects.BlogsNewEntryPost);
		clickLink(BlogsObjects.BlogsNewEntryPost);
	}

	public void AddACommentToEntry() throws Exception {

		// Click on the Add a comment link for entry
		clickLink(BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		sel.type(BlogsObjects.BlogsCommentTextArea, BlogsData.BlogsCommentText);

		// Submit comment
		clickLink(BlogsObjects.BlogsCommentSubmit);

	}

	public void BlogsFileUpload(String FileUploadName) throws Exception {

		// Click on the Settings button
		clickLink(BlogsObjects.BlogsManageBlog);

		clickLink("link=File Uploads");

		if (sConfig.browserIsGoogleChrome) {
			/*
			 * Will need to work on this further as can not upload with Chrome currently!!
			 */
		}
		else {
			//Tab into the field
			TabToBrowse();

			InsertImageToEntry(FileUploadName);

			clickLink(BlogsObjects.BlogsFileUploadButton);

			CheckForNoFilesUploaded(FileUploadName);
		}
	}

	public void BlogsVerifyUIMessage(String UIMessage) throws Exception {

		// verify that the blog was created
		Assert.assertTrue(sel.isTextPresent(UIMessage));

	}

	public void CheckForNoFilesUploaded(String FileUploadName) throws Exception {

		if (sel.isTextPresent("You have chosen no files to upload")) {
			//Close the message dialog
			clickLink(CommonObjects.OKButton);

			//Ensure that the focus is on the Upload button
			sel.focus(BlogsObjects.BlogsFileUploadButton);

			//Then tab back to browse button
			sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
			sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
			Thread.sleep(500);
			sel.keyDownNative(String.valueOf(KeyEvent.VK_SPACE));
			sel.keyUpNative(String.valueOf(KeyEvent.VK_SPACE));
			//sel.keyDownNative(String.valueOf(KeyEvent.VK_SPACE));
			//sel.keyUpNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(2000);

			//Enter the image name & location
			InsertImageToEntry(FileUploadName);

			//Upload file
			clickLink(BlogsObjects.BlogsFileUploadButton);
			Thread.sleep(1500);
		}
		else {

		}

	}

	public void TabToBrowse() throws Exception {

		sel.selectWindow("null");
		Thread.sleep(1000);
		sel.focus("link=File Uploads");
		sel.focus("link=File Uploads");
		Thread.sleep(1000);
		try {
			int count = 1;
			while (count < 5) {
				Thread.sleep(500);
				sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
				Thread.sleep(500);
				count++;
			}
			sel.keyDownNative(String.valueOf(KeyEvent.VK_SPACE));
			sel.keyUpNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(1500);
		} catch (Exception e) {

		}
	}

	public void InsertImageToEntry(String FileUploadName) throws Exception {

		// For adding Images
		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);

	}
}
