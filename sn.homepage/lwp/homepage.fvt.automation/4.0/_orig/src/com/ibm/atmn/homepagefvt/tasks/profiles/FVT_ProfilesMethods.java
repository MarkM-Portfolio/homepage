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

package com.ibm.atmn.homepagefvt.tasks.profiles;


import com.ibm.atmn.homepagefvt.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.homepagefvt.appobjects.profiles.ProfilesData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.homepagefvt.appobjects.profiles.FVT_ProfilesObjects;

import static org.testng.AssertJUnit.*;

import java.awt.event.*;




public class FVT_ProfilesMethods extends com.ibm.atmn.homepagefvt.tasks.news.FVT_CommonNewsMethods{
	/*
	 * All the methods for the Profiles component
	 */
	
	
	public void UpdateProfile() throws Exception{
		//Update a users Profile
		sel.type(ProfilesObjects.EditProfileBuilding, ProfilesData.FieldBuilding);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileFloor, ProfilesData.FieldFloor);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileOffice, ProfilesData.FieldOffice);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileOfficeNo, ProfilesData.FieldOfficeNo);
		Thread.sleep(1000);
		clickLink(ProfilesObjects.EditProfileSaveAndClose);
	}
	
	public void UpdateAbout() throws Exception{
		//Update a users Profile
		
		clickLink(FVT_ProfilesObjects.EditProfile);
		Thread.sleep(1000);
		clickLink(FVT_ProfilesObjects.AboutMe);
		Thread.sleep(1000);
		sel.focus(FVT_ProfilesObjects.AboutCKEditor);
		Thread.sleep(1000);
		sel.type(FVT_ProfilesObjects.AboutCKEditor, "About");
	}
	
	public void VerifyUserProfile() throws Exception{
		/*
		 * Verify that the Profile has being updated correctly
		 */
		
		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		
		String VerifyTextFieldUsername = sel.getValue(ProfilesObjects.EditProfileName);
		String VerifyTextFieldBuilding = sel.getValue(ProfilesObjects.EditProfileBuilding);
		String VerifyTextFieldFloor = sel.getValue(ProfilesObjects.EditProfileFloor);
		String VerifyTextFieldOffice = sel.getValue(ProfilesObjects.EditProfileOffice);
		String VerifyTextFieldOfficeNo = sel.getValue(ProfilesObjects.EditProfileOfficeNo);
		Thread.sleep(1000);
		assertTrue(VerifyTextFieldUsername.equals(CommonData.IC_LDAP_Username_Fullname));
		assertTrue(VerifyTextFieldBuilding.equals(ProfilesData.FieldBuilding));
		assertTrue(VerifyTextFieldFloor.equals(ProfilesData.FieldFloor));
		assertTrue(VerifyTextFieldOffice.equals(ProfilesData.FieldOffice));
		assertTrue(VerifyTextFieldOfficeNo.equals(ProfilesData.FieldOfficeNo));
		Thread.sleep(1000);	
	}
	
	public void SearchForUser(String User) throws Exception{
		/*
		 * Enter the Username in the search field and search for that user and 
		 * verify that the correct user is returned
		 */
		sel.type(ProfilesObjects.ProfilesSearchForUser, User);
		clickLink(ProfilesObjects.ProfileSearch);
		
		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		assertTrue(sel.isTextPresent(User));
	}

	public void ProfilesAddATag() throws Exception{
		/*
		 * Add a tag to a user in Profiles, click on the tag
		 * and verify that the user's profile is displayed
		 */
		Thread.sleep(500);
		sel.type(FVT_ProfilesObjects.ProfilesTagTypeAhead, FVT_ProfilesData.ProfilesTag);
		Thread.sleep(500);
		sel.focus(FVT_ProfilesObjects.ProfilesAddTag);
		sel.click(FVT_ProfilesObjects.ProfilesAddTag);
		Thread.sleep(2000);
		
		clickLink(FVT_ProfilesObjects.ProfilesTagForFVT);
		
		if (sel.isElementPresent(FVT_ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
				
	}
	
	public void ProfilesAddMultipleTags() throws Exception{
		/*
		 * Add tags to a user in Profiles, click on the tag
		 * and verify that the user's profile is displayed
		 */
		Thread.sleep(500);
		cautiousFocus(FVT_ProfilesObjects.ProfilesTagTypeAhead);
		sel.type(FVT_ProfilesObjects.ProfilesTagTypeAhead, FVT_ProfilesData.ProfilesTags);
		Thread.sleep(500);
		sel.focus(FVT_ProfilesObjects.ProfilesAddTag);
		sel.click(FVT_ProfilesObjects.ProfilesAddTag);
		Thread.sleep(2000);
			
		
	}
	
	public String ProfilesAddLink() throws Exception{
		clickLink(ProfilesObjects.ProfilesAddLink);
		String LinkName =  FVT_ProfilesData.AddLinkName;
		
		Thread.sleep(500);
		sel.type(ProfilesObjects.ProfilesAddLinkName, LinkName);
		Thread.sleep(500);
		sel.type(ProfilesObjects.ProfilesAddLinkLinkname, FVT_ProfilesData.AddLinkURL);
		Thread.sleep(500);
		sel.focus(ProfilesObjects.AddLinkSave);
		sel.click(ProfilesObjects.AddLinkSave);
		Thread.sleep(5000);
		
		return LinkName;
		
	}
	
	public void ProfilesRemoveLink() throws Exception{
	
		clickLink(FVT_ProfilesObjects.RemoveLink);
		
	}
	
	public void AddBoardEntry() throws Exception{
		//Add an entry to the board
		sel.type(ProfilesObjects.ProfilesBoard, ProfilesData.ProfilesBoardEntry);
		Thread.sleep(1000);
		clickLink(ProfilesObjects.PostStatus);
		Thread.sleep(1000);

	}
	
	public void AddBoardEntry(String Entry) throws Exception{
		//Add an entry to the board
		sel.type(FVT_ProfilesObjects.ProfilesBoard, Entry);
		clickLink(FVT_ProfilesObjects.PostStatus);
	}
	
	public String AddBoardEntry_OtherWall(String User) throws Exception{
		
		
		String BoardEntry = FVT_ProfilesData.OtherProfileBoardEntry;
		
		//Search for user
		SearchForUser(User);
		
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//Post on their wall
		cautiousFocus(FVT_ProfilesObjects.StatusTextArea);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_F));
		Thread.sleep(1000);
		sel.type(FVT_ProfilesObjects.StatusTextArea, BoardEntry);
		clickLink(FVT_ProfilesObjects.PostMessage);
		
		return BoardEntry;
	}
	
	public String AddResponse(String Entry) throws Exception{
		//Add a response to an entry
		
		//Click add comment button
		sel.focus(FVT_ProfilesObjects.AddResponse);
		clickLink(FVT_ProfilesObjects.AddResponse);
		Thread.sleep(1000);
		//Enter comment
		sel.type(FVT_ProfilesObjects.ResponseTextArea, Entry);
		
		//Click post comment button
		clickLink(FVT_ProfilesObjects.PostResponse);
		
		return Entry;
	}
	
	public void AddResponse_Homepage(String Entry) throws Exception{
		//Add a response to an entry
		
		//Update Status
		clickLink("link=I'm following");
		
		// Focus on first status update
		sel.focus(FVT_HomepageObjects.FocusOnPost);
		
		//Click comment button
		cautiousClick(FVT_HomepageObjects.Comment);
		
		//Add response
		sel.type(FVT_HomepageObjects.CommentArea, Entry);
		
		//Click post button
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
	}
	
public void FollowPerson( String User) throws Exception{
		
		//Search for user
		SearchForUser(User);
		
		//Select User
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//Follow person
		clickLink(FVT_ProfilesObjects.FollowPerson);
	}

public void RecommendStatus_Profiles( String User) throws Exception{
	
	//Search for user
	SearchForUser(User);
	
	//Select User
	clickLink(FVT_ProfilesObjects.SelectProfile);
	
	//Recommend status
	//clickLink(FVT_ProfilesObjects.);
}

public void RecommendStatus_Homepage( String User) throws Exception{
	
	//
	clickLink("link=Discover");

	//
	FilterBy("Status Updates");
	
	//
	sel.focus("css= ");
	
}
	
	/*public void InviteToNetwork() throws Exception{
		//Invite person to network
		
		//Select user's profile
		sel.mouseOver(FVT_ProfilesObjects.SelectProfile);
		
		//
		cautiousClick(FVT_ProfilesObjects.ViewBusinessCard);
		
		//
		cautiousClick(FVT_ProfilesObjects.BusinessCard_MoreActions);
		
		//
		cautiousClick(FVT_ProfilesObjects.MoreActions_Invite);
		
		//
		cautiousClick(FVT_ProfilesObjects.SendInvite);
		
	}*/
	
	public void InviteToNetwork() throws Exception{
		//Invite person to network
		
		//Select user's profile
		clickLink(FVT_ProfilesObjects.SelectProfile);
		Thread.sleep(500);
		//
		sel.focus(FVT_ProfilesObjects.Invite);
		clickLink(FVT_ProfilesObjects.Invite);
		Thread.sleep(500);
		//
		clickLink(FVT_ProfilesObjects.SendInvite);
		Thread.sleep(500);
	}
	
	public void UploadPhoto()throws Exception{
		//Click on the Photo tab in Edit Profile
		//clickLink(Objects.EditPhoto);
		
		//Enter the location and name of the image to upload 
		/*sel.focus(Objects.EnterPhoto);
		sel.type(Objects.EnterPhoto, "C:\\TEMP\\desert.jpg");
		Thread.sleep(1000);
		//Save the image and close
		clickLink(Objects.SaveandClosePhoto);*/
		
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			clickLink(FVT_ProfilesObjects.EditPhoto);
			//Ensure that the window has focus
			sel.selectWindow("null");
			
			//Add code to click 
			sel.focus(FVT_ProfilesObjects.EditPhotoBrowse);
			sel.click(FVT_ProfilesObjects.EditPhotoBrowse);
			
			Thread.sleep(1000);
			
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
			InsertImageToEntry();
			
			sel.selectWindow("null");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
		}
		else if (CommonObjects.TestBrowser.contains("googlechrome")) {
			/*
			 * File Upload is a problem on chrome
			 * will need to revisit at a later date 
			 */
			
			/*clickLink("link=Photo");
			
			sel.focus(Objects.EditPhotoBrowse);
			sel.click(Objects.EditPhotoBrowse);*/			
		} else {
			clickLink(FVT_ProfilesObjects.EditPhoto);
			
			sel.focus(FVT_ProfilesObjects.EnterPhoto);
			sel.type(FVT_ProfilesObjects.EnterPhoto, "C:\\jotemp\\Uploads\\desert.jpg");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
			
		}

		
		//Verify that the header info is present
		if (sel.isElementPresent(FVT_ProfilesObjects.EditProfileHeader)){
			Thread.sleep(1000);
		}
		else{
			Thread.sleep(3000);
		}
		
		//assertTrue(sel.isTextPresent("Update information that you wish to change in your profile."));
	}
	
	public void UploadPhoto_JO()throws Exception{
		//Click on the Photo tab in Edit Profile
		//clickLink(Objects.EditPhoto);
		
		//Enter the location and name of the image to upload 
		/*sel.focus(Objects.EnterPhoto);
		sel.type(Objects.EnterPhoto, "C:\\TEMP\\desert.jpg");
		Thread.sleep(1000);
		//Save the image and close
		clickLink(Objects.SaveandClosePhoto);*/
		
		clickLink(FVT_ProfilesObjects.EditPhoto);
			//Ensure that the window has focus
			sel.selectWindow("null");
			
			//Add code to click 
			sel.focus(FVT_ProfilesObjects.EditPhotoBrowse);
			sel.click(FVT_ProfilesObjects.EditPhotoBrowse);
			Thread.sleep(1000);
		
		if (CommonObjects.TestBrowser.contains("iexplore")) {
			
			
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
			
			if (CommonObjects.TestOS.contains("Windows")) {
	 			typeNative(CommonData.WindowsFileLocation+"Desert.jpg");
	 			Thread.sleep(500);
	 		}else{
	 			typeNative(CommonData.LinuxMacFileLocation+"Desert.jpg"	 );
	 			Thread.sleep(500);
	 		}
			
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
			Thread.sleep(1500);
			
			sel.selectWindow("null");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
		}
		else if (CommonObjects.TestBrowser.contains("googlechrome")) {
			/*
			 * File Upload is a problem on chrome
			 * will need to revisit at a later date 
			 */
			
			/*clickLink("link=Photo");
			
			sel.focus(Objects.EditPhotoBrowse);
			sel.click(Objects.EditPhotoBrowse);*/			
		} else {
			clickLink(FVT_ProfilesObjects.EditPhoto);
			
			sel.focus(FVT_ProfilesObjects.EnterPhoto);
			sel.type(FVT_ProfilesObjects.EnterPhoto, "C:\\jotemp\\Uploads\\desert.jpg");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
			
		}

		
		//Verify that the header info is present
		if (sel.isElementPresent(FVT_ProfilesObjects.EditProfileHeader)){
			Thread.sleep(1000);
		}
		else{
			Thread.sleep(3000);
		}
		
		//assertTrue(sel.isTextPresent("Update information that you wish to change in your profile."));
	}
	
	public void UploadPhoto_JDO()throws Exception{
		
		
		clickLink(FVT_ProfilesObjects.EditPhoto);
			//Ensure that the window has focus
			sel.selectWindow("null");
			
			//Add code to click 
			//sel.focus(FVT_ProfilesObjects.EditPhotoBrowse);
			//sel.click(FVT_ProfilesObjects.EditPhotoBrowse);
			cautiousClick(FVT_ProfilesObjects.EditPhotoBrowse);		
			if (CommonObjects.TestBrowser.contains("iexplore")) {
				
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
				Thread.sleep(3000);
			}
			else if (CommonObjects.TestBrowser.contains("firefox")){
				sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
				Thread.sleep(3000);
			}
			
			
			if (CommonObjects.TestOS.contains("Windows")) {
	 			typeNative(CommonData.WindowsFileLocation+"Desert.jpg");
	 			Thread.sleep(500);
	 		}else{
	 			typeNative(CommonData.LinuxMacFileLocation+"Desert.jpg"	 );
	 			Thread.sleep(500);
	 		}
			
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
			Thread.sleep(1500);
			
			sel.selectWindow("null");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
		}
		
		
	
	
	public void VerifyNewWindow() throws Exception{
		//sel.selectWindow("null");
		//String BrowserTitle = sel.getTitle();
		//System.out.println(BrowserTitle);
		//sel.selectWindow(BrowserTitle);
		Thread.sleep(3000);
		String BrowserTitle = sel.getTitle();
		System.out.println(BrowserTitle);
		if (BrowserTitle.equals(ProfilesData.AddLinkURL)){
			Thread.sleep(1000);
			sel.close();
			sel.selectWindow("null");
		}	
	}
	
	public void InsertImageToEntry2() throws Exception {

		// For adding Images
		sel.keyPressNative(String.valueOf(KeyEvent.VK_C));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SEMICOLON));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SEMICOLON));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_J));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_O));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_T));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_M));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_U));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_L));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_O));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_A));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_D));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_S));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_D));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_S));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_R));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_T));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_PERIOD));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_J));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_G));
		Thread.sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}
	
	public void InsertImageToEntry() throws Exception {

		// For adding Images
		sel.keyPressNative(String.valueOf(KeyEvent.VK_C));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_SEMICOLON));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SEMICOLON));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_J));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_O));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_T));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_M));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_U));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_L));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_O));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_A));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_D));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_S));
		sel.keyDownNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_BACK_SLASH));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_D));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_S));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_E));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_R));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_T));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_PERIOD));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_J));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_P));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_G));
		Thread.sleep(500);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}
	
}