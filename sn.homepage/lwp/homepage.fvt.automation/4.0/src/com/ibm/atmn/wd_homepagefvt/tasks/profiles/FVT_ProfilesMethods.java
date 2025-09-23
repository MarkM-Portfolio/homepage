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

package com.ibm.atmn.wd_homepagefvt.tasks.profiles;


import org.testng.Reporter;

import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.ProfilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;

import static org.testng.AssertJUnit.*;





public class FVT_ProfilesMethods extends FVT_CommonNewsMethods{
	/*
	 * All the methods for the Profiles component
	 */
	
	
	public void UpdateProfile() throws Exception{
		//Update a users Profile
		driver.getSingleElement(ProfilesObjects.EditProfileBuilding).type(ProfilesData.FieldBuilding);
		Thread.sleep(1000);
		driver.getSingleElement(ProfilesObjects.EditProfileFloor).type(ProfilesData.FieldFloor);
		Thread.sleep(1000);
		driver.getSingleElement(ProfilesObjects.EditProfileOffice).type(ProfilesData.FieldOffice);
		Thread.sleep(1000);
		driver.getSingleElement(ProfilesObjects.EditProfileOfficeNo).type(ProfilesData.FieldOfficeNo);
		Thread.sleep(1000);
		clickLink(ProfilesObjects.EditProfileSaveAndClose);
	}
	
	public void updateAbout() throws Exception{
		//Update a users Profile
		
		clickLink(FVT_ProfilesObjects.EditProfile);
		Thread.sleep(1000);
		clickLink(FVT_ProfilesObjects.AboutMe);
		Thread.sleep(1000);
		//driver.focus(FVT_ProfilesObjects.AboutCKEditor);
		Thread.sleep(1000);
		//typeNativeInCkEditor("   More About");
		//driver.getSingleElement(FVT_ProfilesObjects.AboutCKEditor).type("About");
		clickLink(FVT_ProfilesObjects.SaveandCloseAbout);
		
	}
	
	public void VerifyUserProfile() throws Exception{
		/*
		 * Verify that the Profile has being updated correctly
		 */
		
		if (driver.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		
		String VerifyTextFieldUsername = driver.getSingleElement(ProfilesObjects.EditProfileName).getAttribute("value");
		String VerifyTextFieldBuilding = driver.getSingleElement(ProfilesObjects.EditProfileBuilding).getAttribute("value");
		String VerifyTextFieldFloor = driver.getSingleElement(ProfilesObjects.EditProfileFloor).getAttribute("value");
		String VerifyTextFieldOffice = driver.getSingleElement(ProfilesObjects.EditProfileOffice).getAttribute("value");
		String VerifyTextFieldOfficeNo = driver.getSingleElement(ProfilesObjects.EditProfileOfficeNo).getAttribute("value");
		Thread.sleep(1000);
		assertTrue(VerifyTextFieldUsername.equals(CommonData.IC_LDAP_Username_Fullname));
		assertTrue(VerifyTextFieldBuilding.equals(ProfilesData.FieldBuilding));
		assertTrue(VerifyTextFieldFloor.equals(ProfilesData.FieldFloor));
		assertTrue(VerifyTextFieldOffice.equals(ProfilesData.FieldOffice));
		assertTrue(VerifyTextFieldOfficeNo.equals(ProfilesData.FieldOfficeNo));
		Thread.sleep(1000);	
	}
	
	/*
	
	public void SearchForUser(String User) throws Exception{
		
		  //Enter the Username in the search field and search for that user and 
		  //verify that the correct user is returned
		 
		driver.getFirstElement(ProfilesObjects.ProfilesSearchByKeyword).click();
		driver.getFirstElement(ProfilesObjects.ProfilesSearchForUser).type(User);
		clickLink(ProfilesObjects.ProfileSearch);
		
		if (driver.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		assertTrue(driver.isTextPresent(User));
	}

*/

	public void ProfilesAddATag() throws Exception{
		/*
		 * Add a tag to a user in Profiles, click on the tag
		 * and verify that the user's profile is displayed
		 */
		Thread.sleep(500);
		driver.getSingleElement(FVT_ProfilesObjects.ProfilesTagTypeAhead).type(FVT_ProfilesData.ProfilesTag);
		Thread.sleep(500);
		//driver.focus(FVT_ProfilesObjects.ProfilesAddTag);
		clickLink(FVT_ProfilesObjects.ProfilesAddTag);
		Thread.sleep(2000);
		
		clickLink(FVT_ProfilesObjects.ProfilesTagForFVT);
		
		if (driver.isElementPresent(FVT_ProfilesObjects.EditProfileHeader)){
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
		//cautiousFocus(FVT_ProfilesObjects.ProfilesTagTypeAhead);
		driver.getSingleElement(FVT_ProfilesObjects.ProfilesTagTypeAhead).type(FVT_ProfilesData.ProfilesTags);
		Thread.sleep(500);
		//driver.focus(FVT_ProfilesObjects.ProfilesAddTag);
		clickLink(FVT_ProfilesObjects.ProfilesAddTag);
		Thread.sleep(2000);
			
		
	}
	
	public String ProfilesAddLink() throws Exception{
		clickLink(ProfilesObjects.ProfilesAddLink);
		String LinkName =  FVT_ProfilesData.AddLinkName;
		
		Thread.sleep(500);
		driver.getSingleElement(ProfilesObjects.ProfilesAddLinkName).type(LinkName);
		Thread.sleep(500);
		driver.getSingleElement(ProfilesObjects.ProfilesAddLinkLinkname).type(FVT_ProfilesData.AddLinkURL);
		Thread.sleep(500);
		//driver.focus(ProfilesObjects.AddLinkSave);
		clickLink(ProfilesObjects.AddLinkSave);
		Thread.sleep(5000);
		
		return LinkName;
		
	}
	
	public void ProfilesRemoveLink() throws Exception{
	
		clickLink(FVT_ProfilesObjects.RemoveLink);
		
	}
	
	public void AddBoardEntry() throws Exception{
		//Add an entry to the board
		driver.getSingleElement(ProfilesObjects.ProfilesBoard).type(ProfilesData.ProfilesBoardEntry);
		Thread.sleep(1000);
		clickLink(ProfilesObjects.PostStatus);
		Thread.sleep(1000);

	}
	
	public void AddBoardEntry(String Entry) throws Exception{
		//Add an entry to the board
		driver.getSingleElement(FVT_ProfilesObjects.ProfilesBoard).type(Entry);
		clickLink(FVT_ProfilesObjects.PostStatus);
	}
	
	public String addBoardEntry_AnotherWall(String User) throws Exception{
		
		String BoardEntry = FVT_ProfilesData.ProfilesBoardEntry;
		
		//Search for user
		SearchForUser(User);
		
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		
		//Enter and Save a status update
		driver.getSingleElement(FVT_ProfilesObjects.ProfilesBoard).type(BoardEntry);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
	
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(BoardEntry));
		
		return BoardEntry;
			
	}
	
	
	public String AddBoardEntry_OtherWall(String User) throws Exception{
		
		
		String BoardEntry = FVT_NewsData.UpdateStatus;
		
		//Search for user
		SearchForUser(User);
		
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		//driver.getSingleElement(FVT_ProfilesObjects.StatusTextArea).type(BoardEntry);
		//clickLink(FVT_ProfilesObjects.PostMessage);
		statusUpdate(BoardEntry);
		
		return BoardEntry;
	}
	
	public String AddResponse(String Entry) throws Exception{
		//Add a response to an entry
		
		//Click add comment button
		//driver.focus(FVT_ProfilesObjects.AddResponse);
		clickLink(FVT_ProfilesObjects.AddResponse);
		Thread.sleep(1000);
		//Enter comment
		driver.getSingleElement(FVT_ProfilesObjects.ResponseTextArea).type(Entry);
		
		//Click post comment button
		clickLink(FVT_ProfilesObjects.PostResponse);
		
		return Entry;
	}
	
	public void AddResponse_Homepage(String Entry) throws Exception{
		//Add a response to an entry
		
		//Update Status
		clickLink("link=I'm following");
		
		// Focus on first status update
		//driver.focus(FVT_HomepageObjects.FocusOnPost);
		
		//Click comment button
		clickLink(FVT_HomepageObjects.Comment);
		
		//Add response
		driver.getSingleElement(FVT_HomepageObjects.CommentArea).type(Entry);
		
		//Click post button
		clickLink(FVT_HomepageObjects.PostStatus);  //Button currently disabled
	}
	
	/*
	
public void FollowPerson( String User) throws Exception{
		
		//Search for user
		SearchForUser(User);
		
		//Select User
		clickLink(FVT_ProfilesObjects.SelectProfile);
		
		if(driver.isElementPresent(FVT_ProfilesObjects.FollowLinkHidden)){
			return;
		}else{
			clickLink(FVT_ProfilesObjects.FollowPerson);
		}
	}

*/

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
	filterBy("Status Updates");
	
	//
	//driver.focus("css= ");
	
}
	
	/*public void InviteToNetwork() throws Exception{
		//Invite person to network
		
		//Select user's profile
		driver.mouseOver(FVT_ProfilesObjects.SelectProfile);
		
		//
		cautiousClick(FVT_ProfilesObjects.ViewBusinessCard);
		
		//
		cautiousClick(FVT_ProfilesObjects.BusinessCard_MoreActions);
		
		//
		cautiousClick(FVT_ProfilesObjects.MoreActions_Invite);
		
		//
		cautiousClick(FVT_ProfilesObjects.SendInvite);
		
	}*/
	
	public boolean InviteToNetwork() throws Exception{
		
		boolean Connected = false;
		
		//Select user's profile
		clickLink(FVT_ProfilesObjects.SelectProfile);
		Thread.sleep(500);
		//
		
		if(driver.isElementPresent(FVT_ProfilesObjects.Invite)){
		
		//driver.focus(FVT_ProfilesObjects.Invite);
		clickLink(FVT_ProfilesObjects.Invite);
		Thread.sleep(500);
		//
		clickLink(FVT_ProfilesObjects.SendInvite);
		Thread.sleep(500);
		}
		else
		{
			Reporter.log("Already in network.");
			Connected = true;
			
		}
		
		return Connected;
	}
	
	public void uploadPhoto(String FileUploadName)throws Exception{
		clickLink(FVT_ProfilesObjects.EditPhoto);
		Thread.sleep(1000);
		driver.getSingleElement(FVT_ProfilesObjects.EditPhotoBrowse).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		Thread.sleep(1000);
		clickLink(FVT_ProfilesObjects.SaveandClosePhoto);
		Thread.sleep(1000);
		
		//Verify that the header info is present
		if (driver.getElements(ProfilesObjects.EditProfileHeader).size() == 1) {
			Thread.sleep(1000);
		}
		else {
			Thread.sleep(3000);
		}
	}
	
	public void removePhoto()throws Exception{
		
		clickLink(FVT_ProfilesObjects.EditPhoto);
		Thread.sleep(1000);
		clickLink(FVT_ProfilesObjects.REMOVE_PHOTO);
	
	}
	

	public void VerifyNewWindow() throws Exception{
		//driver.selectWindow("null");
		//String BrowserTitle = driver.getTitle();
		//System.out.println(BrowserTitle);
		//driver.selectWindow(BrowserTitle);
		Thread.sleep(3000);
		String BrowserTitle = driver.getTitle();
		System.out.println(BrowserTitle);
		if (BrowserTitle.equals(ProfilesData.AddLinkURL)){
			Thread.sleep(1000);
			driver.close();
			//driver.selectWindow("null");
		}	
	}
	

	
}