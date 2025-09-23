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

package com.ibm.atmn.lc.tasks.profiles;

// import com.ibm.automation.bvt.setup.common.Initialize;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesData;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;

public class ProfilesMethods extends CommonMethods {

	/*
	 * All the methods for the Profiles component
	 */

	private String FieldBuilding;
	private String FieldFloor;
	private String FieldOffice;
	private String FieldOfficeNo;
	private String ProfilesTag;
	private String ProfilesBoardEntry;
	protected String AddLinkName;

	public ProfilesMethods() {

		FieldBuilding = stamp(ProfilesData.FieldBuilding);
		FieldFloor = stamp(ProfilesData.FieldFloor);
		FieldOffice = stamp(ProfilesData.FieldOffice);
		FieldOfficeNo = stamp(ProfilesData.FieldOfficeNo);
		ProfilesBoardEntry = stamp(ProfilesData.ProfilesBoardEntry);

		AddLinkName = stamp(ProfilesData.AddLinkName);
		ProfilesTag = stamp(ProfilesData.ProfilesTag);
	}

	public void UpdateProfile() throws Exception {

		//Update a users Profile
		sel.type(ProfilesObjects.EditProfileBuilding, FieldBuilding);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileFloor, FieldFloor);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileOffice, FieldOffice);
		Thread.sleep(1000);
		sel.type(ProfilesObjects.EditProfileOfficeNo, FieldOfficeNo);
		Thread.sleep(1000);
		clickLink(ProfilesObjects.EditProfileSaveAndClose);
	}

	public void VerifyUserProfile() throws Exception {

		/*
		 * Verify that the Profile has being updated correctly
		 */

		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)) {
			Thread.sleep(500);
		}
		else {
			Thread.sleep(3000);
		}

		String VerifyTextFieldUsername = sel.getValue(ProfilesObjects.EditProfileName);
		String VerifyTextFieldBuilding = sel.getValue(ProfilesObjects.EditProfileBuilding);
		String VerifyTextFieldFloor = sel.getValue(ProfilesObjects.EditProfileFloor);
		String VerifyTextFieldOffice = sel.getValue(ProfilesObjects.EditProfileOffice);
		String VerifyTextFieldOfficeNo = sel.getValue(ProfilesObjects.EditProfileOfficeNo);
		Thread.sleep(1000);
		Assert.assertTrue(VerifyTextFieldUsername.equals(CommonData.IC_LDAP_Username_Fullname));
		Assert.assertTrue(VerifyTextFieldBuilding.equals(FieldBuilding));
		Assert.assertTrue(VerifyTextFieldFloor.equals(FieldFloor));
		Assert.assertTrue(VerifyTextFieldOffice.equals(FieldOffice));
		Assert.assertTrue(VerifyTextFieldOfficeNo.equals(FieldOfficeNo));
		Thread.sleep(1000);
	}

	public void SearchForUser() throws Exception {

		/*
		 * Enter the Username in the search field and search for that user and verify that the correct user is returned
		 */
		sel.type(ProfilesObjects.ProfilesSearchForUser, CommonData.IC_LDAP_Username_Fullname);
		clickLink(ProfilesObjects.ProfileSearch);

		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)) {
			Thread.sleep(500);
		}
		else {
			Thread.sleep(3000);
		}
		Assert.assertTrue(sel.isTextPresent(CommonData.IC_LDAP_Username_Fullname));
	}

	public void ProfilesAddATag() throws Exception {

		/*
		 * Add a tag to a user in Profiles, click on the tag and verify that the user's profile is displayed
		 */

		sel.type(ProfilesObjects.ProfilesTagTypeAhead, ProfilesTag);
		Thread.sleep(500);
		sel.focus(ProfilesObjects.ProfilesAddTag);
		sel.click(ProfilesObjects.ProfilesAddTag);
		Thread.sleep(2000);

		clickLink(ProfilesObjects.ProfilesTagForBVT + ProfilesTag);

		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)) {
			Thread.sleep(500);
		}
		else {
			Thread.sleep(3000);
		}

		Assert.assertTrue(sel.isTextPresent(CommonData.IC_LDAP_Username_Fullname));

	}

	public void ProfilesAddLink() throws Exception {

		clickLink(ProfilesObjects.ProfilesAddLink);
		//Add the name and url
		Thread.sleep(500);
		sel.type(ProfilesObjects.ProfilesAddLinkName, AddLinkName);
		Thread.sleep(500);
		sel.type(ProfilesObjects.ProfilesAddLinkLinkname, sConfig.browserURL + ProfilesData.AddLinkURL);
		Thread.sleep(500);
		sel.focus(ProfilesObjects.AddLinkSave);
		sel.click(ProfilesObjects.AddLinkSave);
		Thread.sleep(5000);

	}

	public void AddBoardEntry() throws Exception {

		//Add an entry to the board
		sel.type(ProfilesObjects.ProfilesBoard, ProfilesBoardEntry);
		clickLink(ProfilesObjects.PostStatus);
	}

	public void UploadPhoto() throws Exception {

		//Click on the Photo tab in Edit Profile
		//clickLink(Objects.EditPhoto);

		//Enter the location and name of the image to upload 
		/*
		 * sel.focus(Objects.EnterPhoto); sel.type(Objects.EnterPhoto, "C:\\TEMP\\desert.jpg"); Thread.sleep(1000);
		 * //Save the image and close clickLink(Objects.SaveandClosePhoto);
		 */

		if (sConfig.browserIsIE) {
			clickLink(ProfilesObjects.EditPhoto);
			//Ensure that the window has focus
			sel.selectWindow("null");

			//Add code to click 
			sel.focus(ProfilesObjects.EditPhotoBrowse);
			sel.click(ProfilesObjects.EditPhotoBrowse);
			Thread.sleep(1000);

			InsertImageToEntry();

			sel.selectWindow("null");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(ProfilesObjects.SaveandClosePhoto);
		}
		else if (sConfig.browserIsGoogleChrome) {
			/*
			 * File Upload is a problem on chrome will need to revisit at a later date
			 */

			/*
			 * clickLink("link=Photo");
			 * 
			 * sel.focus(Objects.EditPhotoBrowse); sel.click(Objects.EditPhotoBrowse);
			 */
		}
		else {
			clickLink(ProfilesObjects.EditPhoto);

			sel.focus(ProfilesObjects.EnterPhoto);
			sel.type(ProfilesObjects.EnterPhoto, "C:\\TEMP\\desert.jpg");
			Thread.sleep(1000);
			//Save the image and close
			clickLink(ProfilesObjects.SaveandClosePhoto);

		}

		//Verify that the header info is present
		if (sel.isElementPresent(ProfilesObjects.EditProfileHeader)) {
			Thread.sleep(1000);
		}
		else {
			Thread.sleep(3000);
		}

		//assertTrue(sel.isTextPresent("Update information that you wish to change in your profile."));
	}

	public void VerifyNewWindow() throws Exception {

		//sel.selectWindow("null");
		//String BrowserTitle = sel.getTitle();
		//System.out.println(BrowserTitle);
		//sel.selectWindow(BrowserTitle);
		Thread.sleep(3000);
		String BrowserTitle = sel.getTitle();
		System.out.println(BrowserTitle);
		if (BrowserTitle.equals(sConfig.browserURL + ProfilesData.AddLinkURL)) {
			Thread.sleep(1000);
			sel.close();
			sel.selectWindow("null");
		}
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