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

package com.ibm.atmn.lc.tasks.communities;

// import com.ibm.automation.bvt.setup.common.Initialize;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesData;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;
import org.testng.AssertJUnit;

public class CommunitiesMethods extends CommonMethods {

	public void StartACommunity() throws Exception {

		sel.click("link=Start a Community");
		try {
			int i = 0;
			while (i < 60) {
				if (sel.isElementPresent(CommunitiesObjects.CommunityName)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void clickFilesSidebar() throws Exception {

		//Click on the Files sidebar link
		sel.click(CommunitiesObjects.CommunityFilesSidebar);
		Thread.sleep(3000);

		Assert.assertTrue(sel.isTextPresent("Files for this community"));
	}

	public void clickShareFiles() throws Exception {

		//Click on the Share Files link
		sel.click(CommunitiesObjects.CommunityShareFiles);
		Thread.sleep(3000);

		Assert.assertTrue(sel.isTextPresent("Share Files with this Community"));
	}

	public void clickBrowseFilesOnMyComputer() throws Exception {

		//Click on the "Browse files on my computer..." link
		sel.click(CommunitiesObjects.BrowseFilesOnMyComputer);
		Thread.sleep(3000);

		Assert.assertTrue(sel.isTextPresent("Upload File to Community"));
	}

	/*
	 * FAILS in IE public void clickBrowse() throws Exception{ //Click on the "Browse..." link
	 * sel.click(Objects.Browse_Button); Thread.sleep(3000); }
	 */

	/**
	 * Upload a community-owned file
	 */
	public void uploadFileToCommunity(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

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
		if (sel.isTextPresent(NameOfFile + TypeOfFile + " updated")) {
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else { //initial file upload
			AssertJUnit.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}

	}

	public void CreateNewCommunity(String CommunityName, String CommunityHandle, String CommunityType, String MembersType, String MembersTypeAhead, String MembersToAdd,
			String SelectUserFromDropdown, String PopupIdentifier) throws Exception {

		/*
		 * Create a public community with tag, member, description, image and handle
		 */
		StartACommunity();
		Thread.sleep(10000);
		sel.type(CommunitiesObjects.CommunityName, CommunityName);
		Thread.sleep(500);
		sel.type(CommunitiesObjects.CommunityTag, CommunitiesData.CommunityTag);
		Thread.sleep(500);
		sel.type(CommunitiesObjects.CommunityHandle, CommunityHandle);
		Thread.sleep(500);
		//Choose Type of Community
		sel.click(CommunityType);
		//Add a member
		AddMembersToCommunity(MembersType, MembersTypeAhead, SelectUserFromDropdown, MembersToAdd, PopupIdentifier);
		Thread.sleep(500);
		//Enter the description in CKEditor
		typeNativeInCkEditor(CommunitiesData.CommunityDescription);
		Thread.sleep(500);
		sel.click(CommunitiesObjects.CommunityThemeLink);
		Thread.sleep(1000);
		sel.click(CommunitiesObjects.CommunityThemeGreen);
		Thread.sleep(1000);
		//Then the code for file upload - leaving this for the moment

		//Save the community
		sel.click(CommonObjects.SaveButton);
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try {
			int i = 0;
			while (i < 60) {
				if (sel.isElementPresent(CommunitiesObjects.CommunityOverview)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(4000);
		}
	}

	public void CommunitiesHelpAndAbout() throws Exception {

		//Verify the Version number
		clickLink(CommonObjects.AboutPageLink);

		//String VersionNo = sel.getText(CommonObjects.ReleaseVersion);
		//Assert.assertTrue(VersionNo.contains("Release v3.5.0.0"));

		//Return to the Public community view
		clickLink(CommunitiesObjects.PublicCommunityView);

		cautiousClick(CommunitiesObjects.CommunitiesHelp);
		Thread.sleep(10000);
		sel.selectWindow(CommunitiesObjects.HelpFrame);
		Thread.sleep(500);

		String Helptitle = sel.getText(CommunitiesObjects.HelpPageTitle);
		//System.out.println(Helptitle);
		Assert.assertTrue(Helptitle.contains("Communities"));
		Thread.sleep(500);
		sel.close();
		Thread.sleep(500);
		sel.selectWindow("null");
		Thread.sleep(1000);
	}

	public void VerifyCommunityDetails(String CommunityType, String CommunityName, String CommunityHandle) throws Exception {

		/*
		 * Method to verify description, tag, image and theme
		 */
		// TODO replace My Communities with I'm an owner here : pending confirmation from FVT
		
		if (CommunityType.equals("Public")) {
			
			clickLink(CommunitiesObjects.PublicCommunityView);
			Thread.sleep(30000);
			sel.refresh();
			Thread.sleep(3000);
			if  (sel.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(sel.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(sel.isTextPresent(CommunityName));
		}
		else if (CommunityType.equals("Moderated")) {

			clickLink(CommunitiesObjects.PublicCommunityView);
			Thread.sleep(30000);
			sel.refresh();
			Thread.sleep(3000);
			if  (sel.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(sel.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(sel.isTextPresent(CommunityName));
		}
		else if (CommunityType.equals("Restricted")) {
			clickLink(CommunitiesObjects.PublicCommunityView);
			Thread.sleep(30000);
			sel.refresh();
			Thread.sleep(3000);
			if  (sel.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(!sel.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(sel.isTextPresent(CommunityName));
		}

		//Verify that Community is loaded correctly using the Handle
		System.out.println(CommunityHandle);
		LoadComponent("communities/community/" + CommunityHandle);
		Thread.sleep(1000);

		//Verify the Tag and Description
		sel.isTextPresent(CommunitiesData.CommunityTag);
		sel.isTextPresent(CommunitiesData.CommunityDescription);

		//Verify the theme
		CustomizeCommunity(CommunitiesObjects.Menu_Item_2); //Edit the community option

		sel.click(CommunitiesObjects.ChangeCommmunityThemeLink);
		Assert.assertEquals("on", sel.getValue(CommunitiesObjects.EditCommunityThemeSelected));
		sel.click(CommunitiesObjects.EditCommunityCancel);
		Thread.sleep(1000);
	}

	public void CustomizeCommunity(String CommunityActionOption) throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		sel.click(CommunityActionOption);
		Thread.sleep(3000);

	}

	public void AddMembersToExistingCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier)
			throws Exception {

		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);

		//Then click on the Add Members button
		clickLink(CommunitiesObjects.AddMembersToExistingCommunity);

		//Now add another member
		AddMembersToCommunity(MemberType, MembersTypeAhead, MemberToAdd, SelectUserFromDropdown, PopupIdentifier);

	}

	public void VerifyNewMembers() throws Exception {

		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);

		//Now verify the members and roles that were just added
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
		Assert.assertEquals("on", sel.getValue(CommunitiesObjects.VerifyMemberAsMember));
		Thread.sleep(500);
		clickLink(CommonObjects.SaveButton);
		//Now verify the second member
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 111 + "')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
		Assert.assertEquals("on", sel.getValue(CommunitiesObjects.VerifyMemberAsOwner));
		Thread.sleep(500);
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
	}

	public void RemoveMembersToExistingCommunity() throws Exception {

		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);

		//Now remove member that was just added
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Assert.assertTrue(sel.getConfirmation().matches("^Are you sure you want to remove " + CommonData.LDAP_FullUsername + 110 + " from this community[\\s\\S]$"));

		//Verify that the member is not displaying in the UI
		Assert.assertTrue(!sel.isElementPresent("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]"));

	}

	/*
	 * All the following methods are for adding members to a community
	 */

	public void AddMembersToCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {

		sel.select(CommunitiesObjects.CommunityMembersDropdown, MemberType);
		Thread.sleep(5000);
		if (sel.isElementPresent(CommunitiesObjects.CommunityName)) {
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		}
		else if (sel.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		}
		Thread.sleep(500);

		/*
		 * Check to see if you need to click on the Save Button only needed if you are adding members to an existing
		 * community
		 */
		if (sel.isElementPresent(CommunitiesObjects.VerifyInviteMembers)) {
			sel.focus(CommonObjects.SaveButton);
			sel.click(CommonObjects.SaveButton);
			//clickLink(CommonObjects.SaveButton);
			Thread.sleep(3000);
		}
		else if (sel.isElementPresent(CommunitiesObjects.CommunityName)) {
			//do nothing
			System.out.println("doing nothing");
		}

	}

	/** Click At item */
	public void clickAtItem(String Link1, String Link2) throws Exception {

		//Click at the item provided
		sel.clickAt(Link1, Link2);
		//Thread.sleep(500);
	}

	public void MembersTypeAhead(String MemberToAdd, String MembersTypeAhead) throws Exception {

		//Execute the follow code depending on browser
		if (sConfig.browserIsIE) {
			sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, MemberToAdd);
			sel.typeKeys(MembersTypeAhead, MemberToAdd);
			Thread.sleep(1000);
		}
		//Verify that the typeahead text is present
		if (sConfig.browserIsFirefox) {
			sel.fireEvent(MembersTypeAhead, "click");
			//sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, "");
			sel.typeKeys(MembersTypeAhead, MemberToAdd);
			Thread.sleep(1000);
		}
		else if (sConfig.browserIsGoogleChrome) {
			sel.fireEvent(MembersTypeAhead, "click");
			//sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, MemberToAdd);
			sel.typeKeys(MembersTypeAhead, "");
			Thread.sleep(1000);
		}
	}

	/** Select a group from activated Groups textfield typeahead dropdown */
	public void selectMemberFromUserDropdown(String UserName, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {

		//Depending on what widget you are in use different locators
		if (sel.isElementPresent(CommunitiesObjects.CommunityName)) {
			clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		}
		else if (sel.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		}

		clickAtItem(SelectUserFromDropdown, "Amy " + UserName);
	}

	//Bookmark methods
	public void AddBookmarkInCommunity() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);

		//Create a bookmark, tag
		clickLink(CommunitiesObjects.AddBookmarkButton);

		//Enter the Bookmark details
		sel.type(CommunitiesObjects.EnterBookmarkURL, CommunitiesData.BookmarkURL);
		sel.type(CommunitiesObjects.EnterBookmarkName, CommunitiesData.BookmarkName);
		sel.type(CommunitiesObjects.EnterBookmarkDescription, CommunitiesData.BookmarkDescription);
		sel.type(CommunitiesObjects.EnterBookmarkTag, CommunitiesData.BookmarkTag);
		sel.click(CommunitiesObjects.AddAsImportant);

		//Save the form
		clickLink(CommonObjects.SaveButton);
	}

	public void VerifyBookmark(String Bookmarkname, String Bookmarkname1) throws Exception {

		//Verify that the bookmark is appearing in the bookmark list view
		sel.focus(Bookmarkname);//+CommunitiesData.BookmarkName

		//Now return to the overview page
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the bookmark is appearing in the Overview
		String GetBookmarkLink = sel.getText(CommunitiesObjects.OverviewBookmarksLink);
		if (GetBookmarkLink.contains(Bookmarkname1)) {
			sel.focus("link=" + GetBookmarkLink);
		}

		//Now focus on the second link in important bookmarks section
		sel.focus(Bookmarkname);

	}

	public void EditBookmark() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);

		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);

		//Edit the bookmark
		sel.type(CommunitiesObjects.EnterBookmarkURL, " ");
		sel.type(CommunitiesObjects.EnterBookmarkURL, CommunitiesData.EditBookmarkURL);
		sel.type(CommunitiesObjects.EnterBookmarkName, " ");
		sel.type(CommunitiesObjects.EnterBookmarkName, CommunitiesData.EditBookmarkName);
		sel.type(CommunitiesObjects.EnterBookmarkDescription, " ");
		sel.type(CommunitiesObjects.EnterBookmarkDescription, CommunitiesData.EditBookmarkDescription);
		sel.type(CommunitiesObjects.EnterBookmarkTag, " ");
		sel.type(CommunitiesObjects.EnterBookmarkTag, CommunitiesData.EditBookmarkTag);
		sel.click(CommunitiesObjects.AddAsImportant);
		//Save the form
		clickLink(CommonObjects.SaveButton);

	}

	//Forums methods
	public void AddForumEntry() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption3);

		//Click on the Topic tab
		clickLink("link=Topics");

		//Click on Start a Topic button
		clickLink("link=Start a Topic");

		//Fill in the form and save
		String forumTopicTitle = stamp(CommunitiesData.ForumTopicTitle);
		sel.type(CommunitiesObjects.TopicTitle, forumTopicTitle);
		sel.type(CommunitiesObjects.TopicTag, stamp(CommunitiesData.ForumTopicTag));
		//Enter the description in CKEditor
		typeNativeInCkEditor(CommunitiesData.ForumTopicDescription);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		try {
			int i = 0;
			while (i < 60) {
				if (sel.isTextPresent("Loading...")) {
					sleep(500);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(4000);
		}
		
		Thread.sleep(35000);
		//Verify that the topic is displayed in the list view
		String GetPostTitle = sel.getText(CommunitiesObjects.PostTitle);
		GetPostTitle.contains(forumTopicTitle);

		//Click on the overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the post is appear in the overview
		sel.focus("link=" + forumTopicTitle);

	}

	/*
	 * Methods for Feeds
	 */
	public void CustomizeCommunity() throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		sel.click(CommunitiesObjects.Menu_Item_3);
		Thread.sleep(1500);

		//Verify that there are 8 widgets
		Assert.assertTrue(sel.isTextPresent("1-6 widgets of 6"));
	}

	public void CheckForAddingWidget() throws Exception {

		/*
		 * Method to check if the Adding Widget text is present and if it is sleep for 1000 millisecond and then check
		 * again if the text is not present then the script continues
		 */
		int i = 0;
		while (i < 10) {
			if (sel.isTextPresent("Adding Widget")) {
				Thread.sleep(1000);
				i = i + 1;
			}
			break;
		}
	}

	public void AddFeedsWidgetToOverview() throws Exception {

		//Choose to Customize Community from the Community Actions
		CustomizeCommunity();

		//Add the Feeds widget
		sel.click(CommunitiesObjects.WidgetFeeds);

		//Check if the widget is still be added
		CheckForAddingWidget();

		//Close the widget section
		sel.click(CommunitiesObjects.WidgetSectionClose);

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the link Add your first feed exists
		//sel.focus(Objects.AddAFeed);

		//Click on link to open the Feed form
		clickLink(CommunitiesObjects.AddAFeed);

		//Fill in the Feeds form
		String feedsTag = stamp(CommunitiesData.FeedsTag);
		sel.type(CommunitiesObjects.AddFeedFormFeed, sConfig.browserURL + CommunitiesData.FeedsURL);
		sel.type(CommunitiesObjects.AddFeedFormTitle, CommunitiesData.FeedsTitle);
		sel.type(CommunitiesObjects.AddFeedFormDescription, CommunitiesData.FeedsDescription);
		sel.type(CommunitiesObjects.AddFeedFormTag, feedsTag);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		//Verify that the feeds are there
		sel.focus("link=" + CommunitiesData.FeedsTitle);

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the feeds are there
		sel.focus("link=" + CommunitiesData.FeedsTitle);

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption6);

		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);

		//Now Edit the Feeds title
		sel.type(CommunitiesObjects.AddFeedFormFeed, " ");
		sel.type(CommunitiesObjects.AddFeedFormFeed, sConfig.browserURL + CommunitiesData.EditedFeedsURL);
		sel.type(CommunitiesObjects.AddFeedFormTitle, " ");
		sel.type(CommunitiesObjects.AddFeedFormTitle, CommunitiesData.EditedFeedsTitle);
		sel.type(CommunitiesObjects.AddFeedFormDescription, " ");
		sel.type(CommunitiesObjects.AddFeedFormDescription, CommunitiesData.EditedFeedsDescription);
		sel.type(CommunitiesObjects.AddFeedFormTag, " ");
		sel.type(CommunitiesObjects.AddFeedFormTag, feedsTag);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		//Verify that the feeds are there
		sel.focus("link=" + CommunitiesData.EditedFeedsTitle);

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the feeds are there
		sel.focus("link=" + CommunitiesData.EditedFeedsTitle);

	}

	//Files methods
	public void AddFilesToCommunity() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption5);

		//Click on the Share files button
		clickLink(CommunitiesObjects.CommunityShareFiles);

		//Browser to a file on your computer and upload
		clickLink(CommunitiesObjects.BrowseComputerForFiles);

	}

}
