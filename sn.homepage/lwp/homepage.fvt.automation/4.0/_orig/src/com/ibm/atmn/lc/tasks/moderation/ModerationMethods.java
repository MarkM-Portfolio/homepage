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

package com.ibm.atmn.lc.tasks.moderation;

import java.awt.event.KeyEvent;

import com.ibm.atmn.lc.appobjects.blogs.BlogsData;
import com.ibm.atmn.lc.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.appobjects.forums.ForumsData;
import com.ibm.atmn.lc.appobjects.forums.ForumsObjects;
import com.ibm.atmn.lc.tasks.communities.CommunitiesMethods;

public class ModerationMethods extends CommunitiesMethods {

	public void AddWidgetToCommunity(String CommunityWidget) throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		sel.click(CommunitiesObjects.Menu_Item_3);
		Thread.sleep(3000);

		//Add the widget
		sel.click(CommunityWidget);

		//Wait for the widget to loaded
		CheckForAddingWidget(20);

		//Close the widget section
		sel.click(CommunitiesObjects.WidgetSectionClose);

	}

	public void ModerateMyCommunity(String CommunityName) throws Exception {
		
		try {
			System.out.println(CommunityName);
			
			// If My Communities is available click My Communities
			if (sel.isElementPresent(CommunitiesObjects.MyCommunityView)){
				clickLink(CommunitiesObjects.MyCommunityView);
			}
			
			//Open community
			String Community = "link=" + CommunityName;
			if (sel.isElementPresent(Community)){
				clickLink(Community);
			}else{
				// refresh the page
				sel.refresh();
				if (sel.isElementPresent(Community)== false){
					throw new Exception("Community " + CommunityName + " not present on the page.");
				}else{
					clickLink(Community);
				}	
			}
			
			//Click on the Communities Action button
			clickLink(CommunitiesObjects.Community_Actions_Button);

			//Choose the option - Moderate Community
			sel.click(CommunitiesObjects.Menu_Item_Moderate);
			Thread.sleep(3000);

			//Wait for text Loading... to appear before continuing
			try {
				int i = 0;
				while (i < 120) {
					if (sel.isTextPresent("Loading...")) {
						sleep(1000);
						i = i + 1;
					}else{
						break;
					}
				}
			} catch (Exception e) {
				sleep(2000);
			}
			sleep(1500);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
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

	public void CreateANewBlogEntry(String BlogEntryName) throws Exception {

		try {
			clickLink("link=Blog");
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
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void CreateAForumTopic(String TopicTitle){
		
		try {
			clickLink("link=Forums");
			
			// Start a topic
			clickLink(ForumsObjects.Start_A_Topic);

			sel.type(ForumsObjects.Start_A_Topic_InputText_Title, TopicTitle);
			if (Math.round(Math.random()) == 0) {
				sel.click(ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
			}

			sel.type(ForumsObjects.Start_A_Topic_InputText_Tags, ForumsData.Start_A_Topic_InputText_Tags_Data + TopicTitle);
			// type description
			typeNativeInCkEditor(ForumsData.CK_Editor_Textarea_RichText_Data + TopicTitle + ". \n\nIn forum... ");
			// Save form
			clickLink(CommonObjects.SaveButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void uploadFileToModeratedCommunity(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Upload file to the community
		clickFilesSidebar();
		clickShareFiles();
		clickBrowseFilesOnMyComputer();

		if (sConfig.browserIsIE) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		else if (sConfig.browserIsFirefox) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		Thread.sleep(3000);

		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));

		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);

		//rename the file
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.type(CommunitiesObjects.UploadFiles_Name, NameOfFile);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);

		sel.click(CommunitiesObjects.Upload_Button);
		Thread.sleep(3000);

		//Error check
		CheckForErrorsOnPage();
		Thread.sleep(3000);

		//Confirm successful file upload/update text displays
//		if (sel.isTextPresent(NameOfFile + TypeOfFile + " updated")) {
//			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
//		}
//		else { //initial file upload
//			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
//		}

	}
	public void CreateANewIdea(String IdeaTitle){
		
		try {
			clickLink("link=Ideation Blog");
			
			// Start a New idea
			clickLink(BlogsObjects.Ideation_NewIdea);

			sel.type(BlogsObjects.NewIdea_Title, IdeaTitle);

			// type description
			typeNativeInCkEditor(IdeaTitle + ". \n\nIdeation blog new idea ... ");
			
			// Save form
			clickLink(BlogsObjects.BlogsNewEntryPost);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void CheckForErrorOnPage() throws Exception {
		
	}
}
