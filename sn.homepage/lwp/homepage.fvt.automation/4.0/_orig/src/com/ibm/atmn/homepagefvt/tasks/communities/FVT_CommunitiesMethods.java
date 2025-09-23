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

package com.ibm.atmn.homepagefvt.tasks.communities;


import com.ibm.atmn.homepagefvt.appobjects.communities.CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.news.FVT_CommonNewsMethods;

import java.awt.event.*;

import org.junit.*;
import static org.testng.AssertJUnit.*;



public class FVT_CommunitiesMethods extends FVT_CommonNewsMethods{
	
	public void StartACommunity() throws Exception{
		sel.click("link=Start a Community");
		try{
			int i = 0;
		     while (i < 60){ 
		      if (sel.isElementPresent(CommunitiesObjects.CommunityName)){
		    	  sleep(1000);
		    	  i=i+1;
		      }
		      break;
		     }
		     sleep(1000);		
		}
		catch (Exception e) {
			sleep(2000);
		}
	}
	
	
	public void clickFilesSidebar() throws Exception{
		//Click on the Files sidebar link
		sel.click(CommunitiesObjects.CommunityFilesSidebar);
		Thread.sleep(3000);
		
		assertTrue(sel.isTextPresent("Files for this community"));
	}
	
	
	public void clickShareFiles() throws Exception{
		//Click on the Share Files link
		sel.click(CommunitiesObjects.CommunityShareFiles);
		Thread.sleep(3000);

		assertTrue(sel.isTextPresent("Share Files with this Community"));
	}
	
	
	public void clickBrowseFilesOnMyComputer() throws Exception{
		//Click on the "Browse files on my computer..." link
		sel.click(CommunitiesObjects.BrowseFilesOnMyComputer);
		Thread.sleep(3000);

		assertTrue(sel.isTextPresent("Upload File to Community"));
	}

	/*	FAILS in IE
		public void clickBrowse() throws Exception{
		//Click on the "Browse..." link
		sel.click(Objects.Browse_Button);
		Thread.sleep(3000);
	}
	*/
	
	/**
	 * Upload a community-owned file
	 */
	public void uploadFileToCommunity(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Upload file to the community
		clickFilesSidebar();
		clickShareFiles();
		clickBrowseFilesOnMyComputer();
		
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		else if (CommonObjects.TestBrowser.contains("firefox")){
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		}
		Thread.sleep(3000);
		
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
		if(sel.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent(NameOfFile + TypeOfFile + " updated"));
		}
		else{ //initial file upload
			Assert.assertTrue("FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded", sel.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile));
		}
		
	}
	
	public String CreateNewCommunity(String CommunityName, String CommunityHandle, String CommunityType, String MembersType, String MembersTypeAhead, String MembersToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception{
		/*
		 * Create a public community with tag, member, description, image and handle
		 */
		Thread.sleep(1000);
		StartACommunity();
		Thread.sleep(1000);
		sel.type(FVT_CommunitiesObjects.CommunityName, CommunityName);
		Thread.sleep(500);
		sel.type(FVT_CommunitiesObjects.CommunityTag, FVT_CommunitiesData.CommunityTag);
		Thread.sleep(500);
		sel.type(FVT_CommunitiesObjects.CommunityHandle, CommunityHandle);
		Thread.sleep(500);
		//Choose Type of Community
		sel.click(CommunityType);
		//Add a member
		AddMembersToCommunity(MembersType, MembersTypeAhead, SelectUserFromDropdown, MembersToAdd, PopupIdentifier);
		Thread.sleep(500);	
		//Enter the description in CKEditor
		typeNativeInCkEditor(FVT_CommunitiesData.CommunityDescription);
		Thread.sleep(500);
		sel.click(FVT_CommunitiesObjects.CommunityThemeLink);
		Thread.sleep(1000);
		sel.click(FVT_CommunitiesObjects.CommunityThemeGreen);
		Thread.sleep(1000);
		//sel.click(FVT_CommunitiesObjects.CheckContentApproval);
		//Thread.sleep(1000);
		//Then the code for file upload - leaving this for the moment
		
		//Save the community
		sel.click(CommonObjects.SaveButton);
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try{
			int i = 0;
		     while (i < 60){ 
		      if (sel.isElementPresent(CommunitiesObjects.CommunityOverview)){
		    	  sleep(1000);
		    	  i = i + 1;
		      }
		      break;	
		     }
		     sleep(1500);		
		}
		catch (Exception e) {
			sleep(4000);
		}
		
		return CommunityName;
	}
	
	public void CommunitiesHelpAndAbout() throws Exception{
		//Verify the Version number
		clickLink(CommonObjects.AboutPageLink);
		
		String VersionNo = sel.getText(CommonObjects.ReleaseVersion);
		assertTrue(VersionNo.contains("Release v3.5.0.0"));
		
		//Return to the Public community view
		clickLink(CommunitiesObjects.PublicCommunityView);
		
		clickLink(CommunitiesObjects.CommunitiesHelp);
		
		if (sel.isElementPresent(CommunitiesObjects.HelpFrame)){
			sel.selectWindow(CommunitiesObjects.HelpFrame);
			Thread.sleep(1000);
		}else{
			Thread.sleep(8000);
			sel.selectWindow(CommunitiesObjects.HelpFrame);
			Thread.sleep(500);
		}
		String Helptitle = sel.getText(CommunitiesObjects.HelpPageTitle);
		//System.out.println(Helptitle);
		assertTrue(Helptitle.contains("Communities"));
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
				
		if (CommunityType.equals("Public")){
			clickLink(CommunitiesObjects.MyCommunityView);
			assertTrue(sel.isTextPresent(CommunityName));
			clickLink(CommunitiesObjects.PublicCommunityView);
			assertTrue(sel.isTextPresent(CommunityName));
		}else if (CommunityType.equals("Moderated")){
			clickLink(CommunitiesObjects.MyCommunityView);
			assertTrue(sel.isTextPresent(CommunityName));
			clickLink(CommunitiesObjects.PublicCommunityView);
			assertTrue(sel.isTextPresent(CommunityName));
		}else if (CommunityType.equals("Restricted")){
			clickLink(CommunitiesObjects.PublicCommunityView);
			assertTrue(!sel.isTextPresent(CommunityName));
			clickLink(CommunitiesObjects.MyCommunityView);
			assertTrue(sel.isTextPresent(CommunityName));
		}
		
		//Verify that Community is loaded correctly using the Handle
		System.out.println(CommunityHandle);
		LoadComponent("communities/community/"+CommunityHandle);
		Thread.sleep(1000);
		
		//Verify the Tag and Description
		sel.isTextPresent(CommunitiesData.CommunityTag);
		sel.isTextPresent(CommunitiesData.CommunityDescription);
	
		//Verify the theme
		CustomizeCommunity(CommunitiesObjects.Menu_Item_2); //Edit the community option
		
		sel.click(CommunitiesObjects.ChangeCommmunityThemeLink);
		assertEquals("on", sel.getValue(CommunitiesObjects.EditCommunityThemeSelected));
		sel.click(CommunitiesObjects.EditCommunityCancel);
		Thread.sleep(1000);
	}
		
	public void CustomizeCommunity(String CommunityActionOption) throws Exception{
		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);
		
		//Choose the 3 option - Customize
		sel.click(CommunityActionOption);
		Thread.sleep(3000);

	}	
	
	public void AddMembersToExistingCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception{
		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);
		
		//Then click on the Add Members button
		clickLink(CommunitiesObjects.AddMembersToExistingCommunity);
		
		//Now add another member
		AddMembersToCommunity(MemberType, MembersTypeAhead, MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		
	}
	
	public void VerifyNewMembers() throws Exception{
		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);
						
		//Now verify the members and roles that were just added
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'"+CommonData.LDAP_User_Typeahead+110+"')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
		assertEquals("on", sel.getValue(CommunitiesObjects.VerifyMemberAsMember));
		Thread.sleep(500);
		clickLink(CommonObjects.SaveButton);
		//Now verify the second member
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'"+CommonData.LDAP_User_Typeahead+111+"')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
		assertEquals("on", sel.getValue(CommunitiesObjects.VerifyMemberAsOwner));
		Thread.sleep(500);
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
	}
	
	public void RemoveMembersToExistingCommunity() throws Exception{
		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);
					
		//Now remove member that was just added
		sel.selectWindow("null");
		sel.focus("//a[contains(text(),'"+CommonData.LDAP_User_Typeahead+110+"')]");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		assertTrue(sel.getConfirmation().matches("^Are you sure you want to remove "+CommonData.LDAP_FullUsername+110+" from this community[\\s\\S]$"));
		
		//Verify that the member is not displaying in the UI
		assertTrue(!sel.isElementPresent("//a[contains(text(),'"+CommonData.LDAP_User_Typeahead+110+"')]"));
		
	}
	
	
	/*
	 * All the following methods are for adding members to a community
	 * 
	 */
	
	public void AddMembersToCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception{
		sel.select(CommunitiesObjects.CommunityMembersDropdown, MemberType);
		Thread.sleep(5000);
		if (sel.isElementPresent(CommunitiesObjects.CommunityName)){
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		}else if (sel.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)){
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier); 
		}
		Thread.sleep(500);
		
		/*
		 * Check to see if you need to click on the Save Button
		 * only needed if you are adding members to an existing 
		 * community
		 */
		if (sel.isElementPresent(CommunitiesObjects.VerifyInviteMembers)){
			sel.focus(CommonObjects.SaveButton);
			sel.click(CommonObjects.SaveButton);
			//clickLink(CommonObjects.SaveButton);
			Thread.sleep(3000);
		}else if (sel.isElementPresent(CommunitiesObjects.CommunityName)){
			//do nothing
		}
		
	}
	
	/**Click At item*/
	public void clickAtItem(String Link1, String Link2) throws Exception {
		//Click at the item provided
		sel.clickAt(Link1, Link2);
		//Thread.sleep(500);
	}
	
	public void MembersTypeAhead(String MemberToAdd, String MembersTypeAhead) throws Exception{
		//Execute the follow code depending on browser
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, MemberToAdd);
			sel.typeKeys(MembersTypeAhead, MemberToAdd);
			Thread.sleep(1000);
		}
		//Verify that the typeahead text is present
		if(CommonObjects.TestBrowser.contains("firefox")){
			sel.fireEvent(MembersTypeAhead, "click");
			//sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, "");
			sel.typeKeys(MembersTypeAhead, MemberToAdd);
			Thread.sleep(1000);
		}
		else if (CommonObjects.TestBrowser.contains("googlechrome")){
			sel.fireEvent(MembersTypeAhead, "click");
			//sel.click(MembersTypeAhead);
			sel.type(MembersTypeAhead, MemberToAdd);
			sel.typeKeys(MembersTypeAhead, "");
			Thread.sleep(1000);
		}
	}
	
	/**Select a group from activated Groups textfield typeahead dropdown*/
	public void selectMemberFromUserDropdown(String UserName, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		//Depending on what widget you are in use different locators
		if (sel.isElementPresent(CommunitiesObjects.CommunityName)){
			clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		}else if (sel.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)){
			clickAtItem(PopupIdentifier, CommunitiesObjects.fullSearchLink);
		}
		
		clickAtItem(SelectUserFromDropdown, "Amy " + UserName);
	}
	
	//Bookmark methods
	public void AddBookmarkInCommunity() throws Exception{
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);
		
		//Create a bookmark, tag
		clickLink(CommunitiesObjects.AddBookmarkButton);
		
		//Enter the Bookmark details
		sel.type(FVT_CommunitiesObjects.EnterBookmarkURL, FVT_CommunitiesData.BookmarkURL);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkName, FVT_CommunitiesData.BookmarkName);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkDescription, FVT_CommunitiesData.BookmarkDescription);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkTag, FVT_CommunitiesData.BookmarkTag);
		sel.click(FVT_CommunitiesObjects.AddAsImportant);
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
	}
	
	public String AddBookmarkInCommunity(String BookmarkName) throws Exception{
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);
		
		//Create a bookmark, tag
		clickLink(CommunitiesObjects.AddBookmarkButton);
		
		//Enter the Bookmark details
		sel.type(FVT_CommunitiesObjects.EnterBookmarkURL, FVT_CommunitiesData.BookmarkURL);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkName, BookmarkName);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkDescription, FVT_CommunitiesData.BookmarkDescription);
		sel.type(FVT_CommunitiesObjects.EnterBookmarkTag, FVT_CommunitiesData.BookmarkTag);
		sel.click(FVT_CommunitiesObjects.AddAsImportant);
		
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		return BookmarkName;
	}
	
	public void VerifyBookmark(String Bookmarkname, String Bookmarkname1) throws Exception{
		//Verify that the bookmark is appearing in the bookmark list view
		sel.focus(Bookmarkname);//+CommunitiesData.BookmarkName
		
		//Now return to the overview page
		clickLink(CommunitiesObjects.LeftNavOption1);
		
		//Verify that the bookmark is appearing in the Overview
		String GetBookmarkLink = sel.getText(CommunitiesObjects.OverviewBookmarksLink);
		if (GetBookmarkLink.contains(Bookmarkname1)){
			sel.focus("link="+GetBookmarkLink);
		}
		
		//Now focus on the second link in important bookmarks section
		sel.focus(Bookmarkname);
			
	}
	
	public void EditBookmark() throws Exception{
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
	public void AddForumEntry() throws Exception{
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption3);
		
		//Click on the Topic tab
		clickLink("link=Topics");
		
		//Click on Start a Topic button
		clickLink("link=Start a Topic");
		
		//Fill in the form and save
		sel.type(CommunitiesObjects.TopicTitle, CommunitiesData.ForumTopicTitle);
		sel.type(CommunitiesObjects.TopicTag, CommunitiesData.ForumTopicTag);
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
		
		//Verify that the topic is displayed in the list view
		String GetPostTitle = sel.getText(CommunitiesObjects.PostTitle);
		GetPostTitle.contains(CommunitiesData.ForumTopicTitle);
		
		//Click on the overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		
		//Verify that the post is appear in the overview
		sel.focus("link="+CommunitiesData.ForumTopicTitle);
		
	}
	
	/*
	 * Methods for Feeds
	 */
	public void CustomizeCommunity() throws Exception{
		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);
		
		//Choose the 3 option - Customize
		sel.click(CommunitiesObjects.Menu_Item_3);
		Thread.sleep(3000);

		//Verify that there are 8 widgets
		//assertTrue(sel.isTextPresent("1-8 widgets of 8"));
	}
	
	public void CheckForAddingWidget() throws Exception{
		/*
		 * Method to check if the Adding Widget text is present
		 * and if it is sleep for 1000 millisecond and then check again
		 * if the text is not present then the script continues
		 */
		 int i = 0;
	     while (i < 10){ 
	      if (sel.isTextPresent("Adding Widget")){
	    	  Thread.sleep(1000);
	    	  i = i + 1;
	      	}
	        break;
	     }
	}
	
	public void AddFeedsWidgetToOverview() throws Exception{
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
		sel.type(CommunitiesObjects.AddFeedFormFeed, CommonObjects.TestURL+CommunitiesData.FeedsURL);
		sel.type(CommunitiesObjects.AddFeedFormTitle, CommunitiesData.FeedsTitle);
		sel.type(CommunitiesObjects.AddFeedFormDescription, CommunitiesData.FeedsDescription);
		sel.type(CommunitiesObjects.AddFeedFormTag, CommunitiesData.FeedsTag);
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		//Verify that the feeds are there
		sel.focus("link="+CommunitiesData.FeedsTitle);
		
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		
		//Verify that the feeds are there
		sel.focus("link="+CommunitiesData.FeedsTitle);
		
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption6);
		
		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);
		
		//Now Edit the Feeds title
		sel.type(CommunitiesObjects.AddFeedFormFeed, " ");
		sel.type(CommunitiesObjects.AddFeedFormFeed, CommonObjects.TestURL+CommunitiesData.EditedFeedsURL);
		sel.type(CommunitiesObjects.AddFeedFormTitle, " ");
		sel.type(CommunitiesObjects.AddFeedFormTitle, CommunitiesData.EditedFeedsTitle);
		sel.type(CommunitiesObjects.AddFeedFormDescription, " ");
		sel.type(CommunitiesObjects.AddFeedFormDescription, CommunitiesData.EditedFeedsDescription);
		sel.type(CommunitiesObjects.AddFeedFormTag, " ");
		sel.type(CommunitiesObjects.AddFeedFormTag, CommunitiesData.FeedsTag);
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		//Verify that the feeds are there
		sel.focus("link="+CommunitiesData.EditedFeedsTitle);
		
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		
		//Verify that the feeds are there
		sel.focus("link="+CommunitiesData.EditedFeedsTitle);
		
	}
	
	public String AddFeedsWidgetToOverview(String FeedName) throws Exception{
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
		sel.type(CommunitiesObjects.AddFeedFormFeed, CommonObjects.TestURL+CommunitiesData.FeedsURL);
		sel.type(CommunitiesObjects.AddFeedFormTitle, FeedName);
		sel.type(CommunitiesObjects.AddFeedFormDescription, CommunitiesData.FeedsDescription);
		sel.type(CommunitiesObjects.AddFeedFormTag, CommunitiesData.FeedsTag);
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		return FeedName;
		
	}
	
	public void AddWidgetToOverview(String widget) throws Exception{
		
		//Choose to Customize Community from the Community Actions
		CustomizeCommunity();
		
		//Add the widget
		
		if (widget=="Blogs"){
			sel.click(FVT_CommunitiesObjects.WidgetBlog);
		}
		else if (widget=="Ideation Blog"){
			sel.click(FVT_CommunitiesObjects.WidgetIdeationBlog);
		}
		else if (widget=="Activities"){
			sel.click(FVT_CommunitiesObjects.WidgetActivities);
		}	
		else if (widget=="Wikis"){
			sel.click(FVT_CommunitiesObjects.WidgetWikis);
		}
		else if (widget=="Subcommunities"){
			sel.click(FVT_CommunitiesObjects.WidgetSubCommunities);
		}
		else if (widget=="Feeds"){
			sel.click(FVT_CommunitiesObjects.WidgetFeeds);
		}
		else if (widget=="Media Gallery"){
			sel.click(FVT_CommunitiesObjects.WidgetMediaGallery);
		}
		else if (widget=="Calendar"){
			sel.click(FVT_CommunitiesObjects.WidgetCalendar);
		}
		
		//Check if the widget is still be added
		CheckForAddingWidget();
				
		//Close the widget section
		sel.click(FVT_CommunitiesObjects.WidgetSectionClose);
	
		//Click on the bookmark link in the left nav
		clickLink(FVT_CommunitiesObjects.LeftNavOption1);
		
		
	}
	
	//Files methods
	public void AddFilesToCommunity() throws Exception{
		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption5);
		
		//Click on the Share files button
		clickLink(CommunitiesObjects.CommunityShareFiles);
		
		//Browser to a file on your computer and upload
		clickLink(CommunitiesObjects.BrowseComputerForFiles);
		
		
		
	}
	
	
	//Add activity widget and create community for first time
	public String CreateInitialCommunityActivity(String DateTimeStamp) throws Exception{
		
		String ActivityName ="FVT Automation Test Activity "+DateTimeStamp;
		
		//Add the Feeds widget
		sel.click(CommunitiesObjects.WidgetActivities);
		
		//Check if the widget is still be added
		CheckForAddingWidget();
				
		//Close the widget section
		sel.click(CommunitiesObjects.WidgetSectionClose);
		
		//Click on the activity link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption7);
		
		//Click on link to open the Feed form
		clickLink(FVT_NewsObjects.CreateAnActivity);
		
		//Fill in the Activities form
		sel.type(FVT_NewsObjects.ActivityName, ActivityName);
		sel.type(FVT_NewsObjects.ActivityTag, "FVT");
		sel.type(FVT_NewsObjects.ActivityGoal, "Create a community activity...");
		
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		//Click on the activity link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption7);
		
		sel.focus("link=FVT Automation Test Activity "+DateTimeStamp);

		return ActivityName;
	}
	
	//Create a calendar entry
	public void CreateCalendarEntry( String EventName) throws Exception{
		
		//Click on the bookmark link in the left nav
		clickLink(FVT_CommunitiesObjects.LeftNavOption9);
		
		//Create event
		clickLink(FVT_CommunitiesObjects.CreateCalendarEvent);
		
		//Fill in the Calendar form
		sel.type(FVT_CommunitiesObjects.EventTitle,EventName);
		sel.type(FVT_CommunitiesObjects.EventTags, "FVT");
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		
	}
	
	public void AddACommentToEventEntry() throws Exception {
		
		
		// Click on the Add a comment link for entry
		clickLink(FVT_CommunitiesObjects.EventAddACommentLink);

		// Fill in the comment form
		sel.type(FVT_CommunitiesObjects.EventCommentTextArea, FVT_CommunitiesData.EventCommentText);

		//Save comment
		clickLink(CommonObjects.SaveButton);

	}
	
	
}
