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

package com.ibm.atmn.wd_homepagefvt.tasks.communities;


import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;
import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
import com.ibm.atmn.waffle.extensions.user.User;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;

import static org.testng.AssertJUnit.*;



public class FVT_CommunitiesMethods extends FVT_CommonNewsMethods{
	
	private String UpdateStatus;

	public FVT_CommunitiesMethods() {

	UpdateStatus = stamp(FVT_NewsData.UpdateStatus);
	

	}

	public void StartACommunity() throws Exception{
		//clickLink("link=Start a Community");
		clickLink("css=a:contains('Start a Community')");
		try{
			int i = 0;
		     while (i < 60){ 
		      if (driver.isElementPresent(CommunitiesObjects.CommunityName)){
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
		clickLink(CommunitiesObjects.CommunityFilesSidebar);
		Thread.sleep(3000);
		
		assertTrue(driver.isTextPresent("Files for this community"));
	}
	
	
	public void clickShareFiles() throws Exception{
		//Click on the Share Files link
		clickLink(CommunitiesObjects.CommunityShareFiles);
		Thread.sleep(3000);

		assertTrue(driver.isTextPresent("Share Files with this Community"));
	}
	
	
	public void clickBrowseFilesOnMyComputer() throws Exception{
		//Click on the "Browse files on my computer..." link
		clickLink(CommunitiesObjects.BrowseFilesOnMyComputer);
		Thread.sleep(3000);

		assertTrue(driver.isTextPresent("Upload File to Community"));
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
			
		//driver.getSingleElement("css=#uploadFile0").typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));
		driver.getSingleElement(FVT_FilesObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));

		driver.getSingleElement(CommunitiesObjects.UploadFiles_Name).type(NameOfFile);
	
		clickLink(CommunitiesObjects.Upload_Button);
		Thread.sleep(3000);
		
		//Error check
		//CheckForErrorsOnPage();
		Thread.sleep(3000);
		
		//Confirm successful file upload/update text displays
		if(driver.isTextPresent(NameOfFile + TypeOfFile + " updated")){
			Assert.assertTrue(driver.isTextPresent(NameOfFile + TypeOfFile + " updated"),"FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded");
		}
		else{ //initial file upload
			Assert.assertTrue( driver.isTextPresent("Successfully uploaded " + NameOfFile + TypeOfFile), "FAIL: file " + NameOfFile + TypeOfFile + " was not uploaded");
		}
		
	}
	
	
	
	public String CreateNewCommunity(String CommunityName, String CommunityHandle, String CommunityType, String MembersType, String MembersTypeAhead, String MembersToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception{
		
		  //Create a public community with tag, member, description, image and handle
		 
		Thread.sleep(1000);
		StartACommunity();
		Thread.sleep(1000);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityName).type(CommunityName);
		Thread.sleep(500);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityTag).type(FVT_CommunitiesData.CommunityTag);
		Thread.sleep(500);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityHandle).type(CommunityHandle);
		Thread.sleep(500);
		//Choose Type of Community
		clickLink(CommunityType);
		//Add a member
		AddMembersToCommunity(MembersType, MembersTypeAhead, SelectUserFromDropdown, MembersToAdd, PopupIdentifier);
		Thread.sleep(500);	
		//Enter the description in CKEditor
		// TODO typeNativeInCkEditor having issues with iframes
		//typeNativeInCkEditor(FVT_CommunitiesData.CommunityDescription,"1");
		
		//Thread.sleep(500);
		//clickLink(FVT_CommunitiesObjects.CommunityThemeLink);
		//Thread.sleep(1000);
		//clickLink(FVT_CommunitiesObjects.CommunityThemeGreen);
		//Thread.sleep(1000);
		//sel.click(FVT_CommunitiesObjects.CheckContentApproval);
		//Thread.sleep(1000);
		//Then the code for file upload - leaving this for the moment
		
		//Save the community
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try{
			int i = 0;
		     while (i < 60){ 
		      if (driver.isElementPresent(CommunitiesObjects.CommunityOverview)){
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


	
	
	public String CreateNewCommunity(String CommunityName, String CommunityHandle, String CommunityType, String MembersType, String MembersTypeAhead,String MembersToAdd) throws Exception{
		/*
		 * Create a public community with tag, member, description, image and handle
		 */
		Thread.sleep(1000);
		StartACommunity();
		Thread.sleep(1000);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityName).type(CommunityName);
		Thread.sleep(500);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityTag).type(FVT_CommunitiesData.CommunityTag);
		Thread.sleep(500);
		
		//Choose Type of Community
		clickLink(CommunityType);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityHandle).type(CommunityHandle);
		Thread.sleep(500);
		
		
		//Add a member
		//selectComboValue(CommunitiesObjects.CommunityMembersDropdown, MembersType);
		driver.getSingleElement(FVT_CommunitiesObjects.CommunityMembersTypeAhead).type(MembersToAdd);

		Thread.sleep(500);
		
		/*
		if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			Reporter.log("Community name");
			*/
			//driver.getSingleElement(MembersTypeAhead).type(MembersToAdd);
			//sleep(1000);
			driver.getSingleElement(FVT_CommunitiesObjects.fullUserSearchIdentifier).click();
			
			sleep(2000);
			
			driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier).click();
			
			//driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier+":contains('"+MembersToAdd+"')").click();
			/*	
		}
		
		
		else if (driver.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			Reporter.log("Add members");
			driver.getSingleElement(MembersTypeAhead).type(MembersToAdd);
			
			//driver.getSingleElement(FVT_CommunitiesObjects.fullUserSearchIdentifier).click();
			//driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier).click();
			sleep(1000);
			driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier+":contains('"+MembersToAdd+"')").click();

		
		}
		Thread.sleep(500);
		
		/*
		 * Check to see if you need to click on the Save Button only needed if you are adding members to an existing
		 * community
		 */
		if (driver.isElementPresent(CommunitiesObjects.VerifyInviteMembers)) {
			Thread.sleep(1500);
			driver.getFirstElement(CommonObjects.SaveButton).click();
			Thread.sleep(3000);
		}
		else if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			//do nothing
		}

		
		
		Thread.sleep(500);	
		//Enter the description in CKEditor
		// TODO typeNativeInCkEditor having issues with iframes
		//typeNativeInCkEditor(FVT_CommunitiesData.CommunityDescription,"1");
		
		//Thread.sleep(500);
		//clickLink(FVT_CommunitiesObjects.CommunityThemeLink);
		//Thread.sleep(1000);
		//clickLink(FVT_CommunitiesObjects.CommunityThemeGreen);
		//Thread.sleep(1000);
		//sel.click(FVT_CommunitiesObjects.CheckContentApproval);
		//Thread.sleep(1000);
		//Then the code for file upload - leaving this for the moment
		
		//Save the community
		clickLink(CommonObjects.SaveButton);
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try{
			int i = 0;
		     while (i < 60){ 
		      if (driver.isElementPresent(CommunitiesObjects.CommunityOverview)){
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
	
	/**
	 * Create a new community based upon the parameters specified
	 * @param CommunityName String name of the community
	 * @param CommunityHandle String handle of the community
	 * @param CommunityType String type of the community (public, moderated, or private)
	 * @param MembersType String type of member to add
	 * @param MembersTypeAhead String the object to select member from type ahead input
	 * @param MembersToAdd String the member to add
	 * @param SelectUserFromDropdown String the user drop down object
	 * @param PopupIdentifier String user identifier to select
	 * @param CommunityDescription String description for the community (used to specify level 2/level 3 BVT, etc.)
	 * @param CommunityTheme String color of the the community theme
	 * @param ConfirmThemes boolean if true confirm all community themes are available
	 * @throws Exception
	 */
	public void CreateNewCommunityWithOptions(String CommunityName, String CommunityHandle, String CommunityType, String MembersType, String MembersTypeAhead, String MembersToAdd,
			String SelectUserFromDropdown, String PopupIdentifier, String CommunityDescription, String CommunityTheme, boolean ConfirmThemes) throws Exception {

		/*
		 * Create a community with name, tag, handle, type, members, description, image
		 */
		StartACommunity();
		Thread.sleep(10000);
		
		driver.getSingleElement(CommunitiesObjects.CommunityName).type(CommunityName);
		Thread.sleep(500);
		
		driver.getSingleElement(CommunitiesObjects.CommunityTag).type(FVT_CommunitiesData.CommunityTag);
		Thread.sleep(500);
		
		driver.getSingleElement(CommunitiesObjects.CommunityHandle).type( CommunityHandle);
		Thread.sleep(500);
		
		//Choose Type of Community
		driver.getSingleElement(CommunityType).click();
		
		//Add a member if specified
		if(MembersTypeAhead != null) {
			AddMembersToCommunity(MembersType, MembersTypeAhead, SelectUserFromDropdown, MembersToAdd, PopupIdentifier);
			Thread.sleep(500);
		}
		//Enter the description in CKEditor
		if(CommunityDescription != null) {
			typeNativeInCkEditor(CommunityDescription);
			Thread.sleep(500);
		}
		
		if(CommunityTheme != null) {
			driver.getSingleElement(CommunitiesObjects.CommunityThemeLink).click();
			Thread.sleep(1000);
			
			//Verify all community themes are available
			if(ConfirmThemes) {
				AllCommuntyThemesExist();
			}
			driver.getSingleElement(CommunityTheme).click();
			Thread.sleep(1000);
		}
		//Then the code for file upload - leaving this for the moment

		//Save the community
		driver.getSingleElement(CommonObjects.SaveButton).click();
		Thread.sleep(1000);
		//Wait for text Overview to appear before continuing
		try {
			int i = 0;
			while (i < 60) {
				if (driver.getElements(CommunitiesObjects.CommunityOverview).size() == 1 ) {
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

	/**
	 * Confirm all community theme selections are available on the current page
	 * @throws Exception
	 */
	public void AllCommuntyThemesExist() throws Exception {
		
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeDefalut));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeRed));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeGreen));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeGold));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeSilver));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeBlue));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemePink));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeOnyx));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemePurple));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.CommunityThemeOrange));	
	}

	public void CommunitiesHelpAndAbout() throws Exception {

		//Verify the Version number
		clickLink(CommonObjects.AboutPageLink);

		WebDriver wd = (WebDriver) driver.getBackingObject();
		String OriginalWindow = wd.getWindowHandle();
		
		driver.getSingleElement(CommunitiesObjects.CommunitiesHelp).click();
		Thread.sleep(30000);
		
		// TODO Issue with switching to frameset on Help window
		
		Set set = new HashSet();
		
		set = wd.getWindowHandles();
		int size = set.size();
		
		Reporter.log("Number of window handlers " + size);
		
		Iterator it = set.iterator();
		while (it.hasNext()) {
		    // Get element
		    Object element = it.next();
		    Reporter.log("Set elements :: " + element.toString());
		    
		    wd.switchTo().window(element.toString());//CommunitiesObjects.HelpFrame
			Thread.sleep(500);
			
			String Title = wd.getTitle();
			Reporter.log("Window title : " + Title);
			if (Title.compareTo(CommunitiesObjects.HelpFrame) == 0 ){
				
				Reporter.log("Looking for Connections help");

				wd.switchTo().frame("1");
				//wd.switchTo().frame("HelpFrame");

				Reporter.log("In help");
								
				//WebElement Test = wd.findElement(By.xpath(CommunitiesObjects.HelpPageTitle));
				String Helptitle = driver.getSingleElement(CommunitiesObjects.HelpPageTitle).getText();
				//WebElement Test = wd.findElement(By.cssSelector(CommunitiesObjects.HelpPageTitle)); 
				//String Helptitle = Test.getText();
					//driver.getSingleElement(CommunitiesObjects.HelpPageTitle).getText();
				Reporter.log(Helptitle);
				Assert.assertTrue(Helptitle.contains("Communities"));
				
			}
			wd.close();
		}
		
		wd.switchTo().window(OriginalWindow);
		
		
	}

	public void VerifyCommunityDetails(String CommunityType, String CommunityName, String CommunityHandle) throws Exception {

		/*
		 * Method to verify description, tag, image and theme
		 */
		
		// Return to the Communities view
		driver.getSingleElement(CommunitiesObjects.CommunitiesLink).click();
		
		if (CommunityType.equals("Public")) {
			
			clickLink(CommunitiesObjects.PublicCommunityView);
			if  (driver.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(driver.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(driver.isTextPresent(CommunityName));
		}
		else if (CommunityType.equals("Moderated")) {
			
			
			clickLink(CommunitiesObjects.PublicCommunityView);
			if  (driver.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(driver.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(driver.isTextPresent(CommunityName));
		}
		else if (CommunityType.equals("Restricted")) {
			clickLink(CommunitiesObjects.PublicCommunityView);
			if  (driver.isTextPresent(CommunityName)){
				System.out.println("Community present in Public Communities");
			}
			Assert.assertTrue(!driver.isTextPresent(CommunityName));
			clickLink("link=I'm an Owner");
			Assert.assertTrue(driver.isTextPresent(CommunityName));
		}

		//Verify that Community is loaded correctly using the Handle
		System.out.println(CommunityHandle);
		WebDriver wd = (WebDriver) driver.getBackingObject();
		wd.navigate().to(testConfig.getBrowserURL() + "communities/community/" + CommunityHandle);
		Thread.sleep(1000);

		//Verify the Tag and Description
		driver.isTextPresent(FVT_CommunitiesData.CommunityTag);
		driver.isTextPresent(FVT_CommunitiesData.CommunityDescription);

		//Verify the theme
		CustomizeCommunity(CommunitiesObjects.Menu_Item_2); //Edit the community option

		driver.getSingleElement(CommunitiesObjects.ChangeCommmunityThemeLink).click();
		String Temp = driver.getSingleElement(CommunitiesObjects.EditCommunityThemeSelected).getAttribute("value");
		Reporter.log(Temp);

		Assert.assertEquals("green",Temp );
		driver.getSingleElement(CommunitiesObjects.EditCommunityCancel).click();
		Thread.sleep(1000);
	}

	public void CustomizeCommunity(String CommunityActionOption) throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		//Choose the 3 option - Customize
		driver.getSingleElement(CommunityActionOption).click();
		Thread.sleep(3000);

	}

	public void AddMembersToExistingCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier)
			throws Exception {

		driver.getFirstElement(CommunitiesObjects.LeftNavOption1).click();
		
		//Choose members from the left nav
		driver.getFirstElement(CommunitiesObjects.LeftNavOption2).click();
		
		//Then click on the Add Members button
		clickLink(CommunitiesObjects.AddMembersToExistingCommunity);

		//Now add another member
		AddMembersToCommunity(MemberType, MembersTypeAhead, MemberToAdd, SelectUserFromDropdown, PopupIdentifier);

	}
	
	public void InviteMembersToExistingCommunity(String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier)
		throws Exception {

		//Choose members from the left nav
		driver.getSingleElement(FVT_CommunitiesObjects.LeftNavMembers).click();
		Thread.sleep(2000);
		
		//Then click on the Invite Members button
		clickLink(CommunitiesObjects.InviteMembers);
		Thread.sleep(2000);
		
		//Now invite a member
		InviteMembersToCommunity(MembersTypeAhead, MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		
	}
	
//	public void VerifyNewMembers() throws Exception {
//
//		//Choose members from the left nav
//		clickLink(CommunitiesObjects.LeftNavOption2);
//
//		WebDriver wd = (WebDriver) driver.getBackingObject();
//		
//		//Now verify the members and roles that were just added
//		//wd.switchTo().window("null");
//		//sel.selectWindow("null");
//		
//		//sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]");
//		//sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
//		//sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
//		Thread.sleep(2000);
//		Assert.assertEquals("on", driver.getSingleElement(CommunitiesObjects.VerifyMemberAsOwner).getAttribute("value"));
//		Thread.sleep(500);
//		clickLink(CommonObjects.SaveButton);
//		//Now verify the second member
//		wd.switchTo().window("null");
//		//sel.selectWindow("null");
//		//sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 111 + "')]");
//		//sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
//		//sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
//		Thread.sleep(2000);
//		Assert.assertEquals("on", driver.getSingleElement(CommunitiesObjects.VerifyMemberAsOwner).getAttribute("value"));
//		Thread.sleep(500);
//		clickLink(CommonObjects.SaveButton);
//		Thread.sleep(1000);
//	}

	/**
	 * Verify new members of a community.
	 *
	 * @param LookAheadMembers the look ahead members of the community
	 * @param LookAheadOwners the look ahead owners of the community
	 * @throws Exception the exception
	 */
	public void VerifyNewMembers(String[] LookAheadMembers,String[] LookAheadOwners) throws Exception {

		//Choose members from the left nav
		driver.getFirstElement(CommunitiesObjects.LeftNavOption2).click();
		
		
		
		for(String user:LookAheadMembers){
			
			// Edit the user and verify that the user is a member
			// Now verify the members and roles that were just added

			driver.getSingleElement("//a[contains(text(),'" + user + "')]").hover();
			driver.typeNative("\t");
			driver.typeNative("\n");
			Thread.sleep(2000);

			Reporter.log(driver.getFirstElement(CommunitiesObjects.VerifyMemberAsMember).getAttribute("value"));
			if (driver.getFirstElement(CommunitiesObjects.VerifyMemberAsMember).isSelected()){
				Reporter.log("Member : " + user + " radiobutton selected.");
			}else{
				Assert.fail("Member : " + user + " radiobutton selected.");
			}

			// TODO
			if (testConfig.browserIs(BrowserType.FIREFOX)){
				driver.getFirstElement(CommonObjects.EditSaveButton).hover();
				driver.getFirstElement(CommonObjects.EditSaveButton).click();
			}
			if (testConfig.browserIs(BrowserType.IE)){
				SaveAForm();
			}
			driver.getFirstElement(CommunitiesObjects.LeftNavOption2).click();
	
		}
		
		// Edit the user and verify the user is an owner
		for(String owner:LookAheadOwners){

			//Now verify the second member
			driver.getSingleElement("//a[contains(text(),'" + owner + "')]").hover();	
			driver.typeNative("\t");
			driver.typeNative("\n");
			Thread.sleep(2000);

			if (driver.getFirstElement(CommunitiesObjects.VerifyMemberAsOwner).isSelected()){
				Reporter.log("User " + owner + " radiobutton not selected");
			}else{
				Assert.fail("User " + owner + "  radiobutton not selected.");
			}
			Thread.sleep(500);
			
			// TODO
			if (testConfig.browserIs(BrowserType.FIREFOX)){
				driver.getFirstElement(CommonObjects.EditSaveButton).hover();
				driver.getFirstElement(CommonObjects.EditSaveButton).click();
			}
			if (testConfig.browserIs(BrowserType.IE)){
				SaveAForm();
			}
			driver.getFirstElement(CommunitiesObjects.LeftNavOption2).click();		
		}
	}
	
//	public void RemoveMembersToExistingCommunity() throws Exception {
//
//		//Choose members from the left nav
//		clickLink(CommunitiesObjects.LeftNavOption2);
//
//		WebDriver wd = (WebDriver) driver.getBackingObject();
//		
//		//Now remove member that was just added
//		// 
//		//sel.focus("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]");
//		driver.typeNative("\t");
//		driver.typeNative("\t");
//		driver.typeNative("\n");
//		wd.switchTo().alert().getText();
//		Assert.assertTrue(wd.switchTo().alert().getText().matches("^Are you sure you want to remove " + CommonData.LDAP_FullUsername + 110 + " from this community[\\s\\S]$"));
//
//		//Verify that the member is not displaying in the UI
//		Assert.assertTrue(!driver.isElementPresent("//a[contains(text(),'" + CommonData.LDAP_User_Typeahead + 110 + "')]"));
//
//	}

	/**
	 * Removes the members from an existing community.
	 *
	 * @param Users Array of user instances
	 * @throws Exception the exception
	 */
	
	/*
	public void RemoveMembersToExistingCommunity(User[] Users) throws Exception {

		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		for(User user:Users){
			
			//Now remove member that was just added
			driver.getFirstElement("//a[contains(text(),'" + user.getLastName() + "')]").hover();
			driver.typeNative("\t");
			driver.typeNative("\t");
			driver.typeNative("\n");
			
			Reporter.log(wd.switchTo().alert().getText());
			Assert.assertTrue(wd.switchTo().alert().getText().matches("^Are you sure you want to remove " + user.getDisplayName() + " from this community[\\s\\S]$"));
			wd.switchTo().alert().accept();
			
			//Verify that the member is not displaying in the UI
			wd.switchTo().defaultContent();
			clickLink(CommunitiesObjects.LeftNavOption2);
			Assert.assertTrue(!driver.isElementPresent("//a[contains(text(),'" + user.getDisplayName() + "')]"));
		}
	}
	
	*/
	
	public void RemoveMembersToExistingCommunity(String User) throws Exception {

		//Choose members from the left nav
		clickLink(CommunitiesObjects.LeftNavOption2);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
			//Now remove member that was just added
			driver.getFirstElement("//a[contains(text(),'" + User + "')]").hover();
			driver.typeNative("\t");
			driver.typeNative("\t");
			driver.typeNative("\n");
			
			clickLink(CommonObjects.OKButton);
			
			//Reporter.log(wd.switchTo().alert().getText());
			//Assert.assertTrue(wd.switchTo().alert().getText().matches("^Are you sure you want to remove " + User + " from this community[\\s\\S]$"));
			//wd.switchTo().alert().accept();
			
			//Verify that the member is not displaying in the UI
			//wd.switchTo().defaultContent();
			clickLink(CommunitiesObjects.LeftNavOption2);
			Assert.assertTrue(!driver.isElementPresent("//a[contains(text(),'" + User + "')]"));
		
	}
	
	/*
	 * All the following methods are for adding members to a community
	 */

	
	public void AddMembersToCommunity(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		
		selectComboValue(CommunitiesObjects.CommunityMembersDropdown, MemberType);

		Thread.sleep(5000);
		if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			Reporter.log("Community name");
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		}
		else if (driver.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			Reporter.log("Add members");
			MembersTypeAhead(MemberToAdd, MembersTypeAhead);
			selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		}
		Thread.sleep(500);
		
		
		 //Check to see if you need to click on the Save Button only needed if you are adding members to an existing
		 //community
		 
		if (driver.isElementPresent(CommunitiesObjects.VerifyInviteMembers)) {
			Thread.sleep(1500);
			driver.getFirstElement(CommonObjects.SaveButton).click();
			Thread.sleep(3000);
		}
		else if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			//do nothing
		}

	}


	public void AddMembersToCommunity_2(String MemberType, String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		
		selectComboValue(CommunitiesObjects.CommunityMembersDropdown, MemberType);

		Thread.sleep(5000);
		if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			Reporter.log("Community name");
			driver.getSingleElement(MembersTypeAhead).type(MemberToAdd);
			driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier).click();
		}
		else if (driver.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			Reporter.log("Add members");
			driver.getSingleElement(MembersTypeAhead).type(MemberToAdd);
			
		}
		Thread.sleep(500);
		
		/*
		 * Check to see if you need to click on the Save Button only needed if you are adding members to an existing
		 * community
		 */
		if (driver.isElementPresent(CommunitiesObjects.VerifyInviteMembers)) {
			Thread.sleep(1500);
			driver.getFirstElement(CommonObjects.SaveButton).click();
			Thread.sleep(3000);
		}
		else if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			//do nothing
		}

	}
	
	public void InviteMembersToCommunity(String MembersTypeAhead, String MemberToAdd, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		//Select the member to invite
		MembersTypeAhead(MemberToAdd, MembersTypeAhead);
		selectMemberFromUserDropdown(MemberToAdd, SelectUserFromDropdown, PopupIdentifier);
		Thread.sleep(500);
		
		//Click Send Invites button
		driver.getSingleElement(CommunitiesObjects.SendInvitesButton).click();
		Thread.sleep(2000);
		
		Assert.assertTrue(driver.isTextPresent(FVT_CommunitiesData.CommunityMemberInvited));
	}

	
	private void selectComboValue(final String elementId, final String value) {
	    final Select selectBox = new Select((WebElement) driver.getSingleElement(elementId).getBackingObject());
	    selectBox.selectByVisibleText(value);
	}
	
	public void clickAtItem(String Link1, String Link2) throws Exception {

		
		
		//Click at the item provided
		driver.getFirstElement(Link1).click();
		
	}

	public void MembersTypeAhead(String MemberToAdd, String MembersTypeAhead) throws Exception {

		
		
		//Execute the follow code depending on browser
		/*if (testConfig.browserIs(BrowserType.IE)){
			driver.getSingleElement(MembersTypeAhead).click();
			driver.getSingleElement(MembersTypeAhead).type(MemberToAdd);
			Thread.sleep(1000);
		}
		//Verify that the typeahead text is present
		if (testConfig.browserIs(BrowserType.FIREFOX)){*/
			driver.getSingleElement(MembersTypeAhead).clickAt(0, 0);	
			driver.getSingleElement(MembersTypeAhead).type(MemberToAdd);
			driver.getSingleElement(MembersTypeAhead).type(" ");
			driver.getSingleElement(MembersTypeAhead).type("\b");
			Thread.sleep(1000);
		/*}
		else if (testConfig.browserIs(BrowserType.CHROME)) {
			driver.getSingleElement(MembersTypeAhead).clickAt(0, 0);
			driver.getSingleElement(MembersTypeAhead).type(MemberToAdd);
			driver.getSingleElement(MembersTypeAhead).type("");
			Thread.sleep(1000);
		} */
	}

	/** Select a group from activated Groups textfield typeahead dropdown */
	public void selectMemberFromUserDropdown(String UserName, String SelectUserFromDropdown, String PopupIdentifier) throws Exception {
		
		Reporter.log(PopupIdentifier);
		//Depending on what widget you are in use different locators
		if (driver.isElementPresent(CommunitiesObjects.CommunityName)) {
			driver.getFirstElement(PopupIdentifier).click();
		}
		else if (driver.isElementPresent(CommunitiesObjects.AddMembersToExistingCommunity)) {
			driver.getFirstElement(PopupIdentifier).click();
		}
		Thread.sleep(5000);
		clickAtItem(SelectUserFromDropdown, UserName);
	}

	//Bookmark methods
	public void AddBookmarkInCommunity() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);

		//Create a bookmark, tag
		clickLink(CommunitiesObjects.AddBookmarkButton);

		//Enter the Bookmark details
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkURL).type(FVT_CommunitiesData.BookmarkURL);
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkName).type(FVT_CommunitiesData.BookmarkName);
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkDescription).type(FVT_CommunitiesData.BookmarkTag);
		driver.getFirstElement(CommunitiesObjects.AddAsImportant).click();

		//Save the form
		clickLink(CommonObjects.SaveButton);
	}

	public String AddBookmarkInCommunity(String BookmarkName) throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);

		//Create a bookmark, tag
		clickLink(CommunitiesObjects.AddBookmarkButton);

		//Enter the Bookmark details
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkURL).type(FVT_CommunitiesData.BookmarkURL);
		sleep(1000);
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkName).type(BookmarkName);
		sleep(1000);
		driver.getSingleElement(CommunitiesObjects.EnterBookmarkTag).type(FVT_CommunitiesData.BookmarkTag);
		sleep(1000);
		driver.getFirstElement(CommunitiesObjects.AddAsImportant).click();

		//Save the form
		driver.getFirstElement(CommonObjects.SaveButton).click();
		
		return BookmarkName;
	}

	
	public void VerifyBookmark(String Bookmarkname, String Bookmarkname1) throws Exception {

		//Verify that the bookmark is appearing in the bookmark list view
		Assert.assertTrue(driver.getSingleElement(Bookmarkname).isVisible());
				
		//Now return to the overview page
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the bookmark is appearing in the Overview
		String GetBookmarkLink = driver.getSingleElement(CommunitiesObjects.OverviewBookmarksLink).getText();
		if (GetBookmarkLink.contains(Bookmarkname1)) {
			Assert.assertTrue(driver.getSingleElement("link=" + GetBookmarkLink).isVisible());
		}

	}

	public void EditBookmark() throws Exception {

		//Click on the bookmark link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption4);

		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);

		//Edit the bookmark
		Element EditBookMarkURL = driver.getFirstElement(CommunitiesObjects.EditBookmarkURL);
		EditBookMarkURL.clear();
		EditBookMarkURL.type(FVT_CommunitiesData.EditBookmarkURL);
		//Element EditBookMarkName = driver.getFirstElement(CommunitiesObjects.EditBookmarkName);
		//EditBookMarkName.clear();
		//EditBookMarkName.type(FVT_CommunitiesData.EditBookmarkName);
		Element EditBookMarkDesc = driver.getFirstElement(CommunitiesObjects.EditBookmarkDescription);
		EditBookMarkDesc.clear();
		EditBookMarkDesc.type(FVT_CommunitiesData.EditBookmarkDescription);

		// TODO Define EditBookMarktag
		//driver.getFirstElement(CommunitiesObjects.EditBookmarkTag).type(FVT_CommunitiesData.EditBookmarkTag);

		driver.getFirstElement(CommunitiesObjects.EditAddAsImportant).click();
		
		// TODO
		if (testConfig.browserIs(BrowserType.FIREFOX)){
			driver.getFirstElement(CommonObjects.EditSaveButton).hover();
			driver.getFirstElement(CommonObjects.EditSaveButton).click();
		}
		if (testConfig.browserIs(BrowserType.IE)){
			SaveAForm();
		}
		//Save the form
		//driver.getFirstElement(CommonObjects.SaveButton).click();

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
		String forumTopicTitle = stamp(FVT_CommunitiesData.ForumTopicTitle);
		driver.getSingleElement(CommunitiesObjects.TopicTitle).type(forumTopicTitle);
		driver.getSingleElement(CommunitiesObjects.TopicTag).type(stamp(FVT_CommunitiesData.ForumTopicTag));
		//Enter the description in CKEditor
		typeNativeInCkEditor(FVT_CommunitiesData.ForumTopicDescription);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		try {
			int i = 0;
			while (i < 60) {
				if (driver.isTextPresent("Loading...")) {
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
		String GetPostTitle = driver.getSingleElement(CommunitiesObjects.PostTitle).getText();
		GetPostTitle.contains(forumTopicTitle);

		//Click on the overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		//Verify that the post is appear in the overview
		Assert.assertTrue(driver.getSingleElement("link=" + forumTopicTitle).isVisible());
	}

	/*
	 * Methods for Feeds
	 */
	public void CustomizeCommunity() throws Exception {

		//Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);
		
		if(!driver.isElementPresent(CommunitiesObjects.Menu_Item_3)){
			
			clickLink(CommunitiesObjects.Community_Actions_Button);
			
		}
		
		
		//Choose the 3 option - Customize
		driver.getSingleElement(CommunitiesObjects.Menu_Item_3).click();
		Thread.sleep(1500);
	}

	public void CheckForAddingWidget() throws Exception {

		/*
		 * Method to check if the Adding Widget text is present and if it is sleep for 1000 millisecond and then check
		 * again if the text is not present then the script continues
		 */
		int i = 0;
		while (i < 10) {
			if (driver.isTextPresent("Adding Widget")) {
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
		driver.getSingleElement(CommunitiesObjects.WidgetFeeds).click();

		//Check if the widget is still be added
		CheckForAddingWidget();

		//Close the widget section
		driver.getSingleElement(CommunitiesObjects.WidgetSectionClose).click();

		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		clickLink(CommunitiesObjects.LeftNavOption6);

		//Click on link to open the Feed form
		// Click Add Feed
		driver.getSingleElement("css=*[id='addFeedBtn']").click();

		//Fill in the Feeds form
		String feedsTag = stamp(FVT_CommunitiesData.FeedsTag);
		
		driver.getSingleElement(CommunitiesObjects.AddFeedFormFeed).type(testConfig.getBrowserURL() + FVT_CommunitiesData.FeedsURL);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormTitle).type (FVT_CommunitiesData.FeedsTitle);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormDescription).type(FVT_CommunitiesData.FeedsDescription);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormTag).type(feedsTag);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		//Verify that the feeds are there
		//sel.focus("link=" + FVT_CommunitiesData.FeedsTitle);
		Assert.assertTrue(driver.getSingleElement("link=" + FVT_CommunitiesData.FeedsTitle).isVisible());

		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		clickLink(CommunitiesObjects.LeftNavOption6);
		
		//Verify that the feeds are there
		Assert.assertTrue(driver.getSingleElement("link=" + FVT_CommunitiesData.FeedsTitle).isVisible());
		
		//Click on the Feeds link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption6);

		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);

		//Now Edit the Feeds title
		Element Feed = driver.getFirstElement(CommunitiesObjects.EditFeedFormFeed);
		Feed.clear();
		Feed.type(testConfig.getBrowserURL() + FVT_CommunitiesData.EditedFeedsURL);
		
		Element Title = driver.getFirstElement(CommunitiesObjects.EditFeedFormTitle);
		Title.clear();
		Title.type(FVT_CommunitiesData.EditedFeedsTitle);

		Element Desc = driver.getFirstElement(CommunitiesObjects.EditFeedFormDescription);
		Desc.clear();
		Desc.type(FVT_CommunitiesData.EditedFeedsDescription);
		
		// TODO define new edit tags fields. No longer same def as Add Feed tags field
//		Element Tags = driver.getSingleElement(CommunitiesObjects.EditFeedFormTag);
//		Tags.clear();
//		Tags.type(feedsTag);
		
		// TODO
		if (testConfig.browserIs(BrowserType.FIREFOX)){
			driver.getFirstElement(CommonObjects.EditSaveButton).hover();
			driver.getFirstElement(CommonObjects.EditSaveButton).click();
		}
		if (testConfig.browserIs(BrowserType.IE)){
			SaveAForm();
		}
		
		//Verify that the feeds are there
		if (driver.getElements("link=" + FVT_CommunitiesData.EditedFeedsTitle).size() == 0 ){
			Assert.fail("Feeds are not present.");
		}
		
		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		//Click on the Feeds link
		clickLink(CommunitiesObjects.LeftNavOption6);
		
		//Verify that the feeds are there
		if (driver.getElements("link=" + FVT_CommunitiesData.EditedFeedsTitle).size() == 0){
			Assert.fail("Feeds are not present.");	
		}
		
	}
	
	public String AddFeedsWidgetToOverview(String FeedName) throws Exception {

		//Choose to Customize Community from the Community Actions
		CustomizeCommunity();

		//Add the Feeds widget
		driver.getSingleElement(CommunitiesObjects.WidgetFeeds).click();

		//Check if the widget is still be added
		CheckForAddingWidget();

		//Close the widget section
		driver.getSingleElement(CommunitiesObjects.WidgetSectionClose).click();

		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		clickLink(CommunitiesObjects.LeftNavOption6);

		//Click on link to open the Feed form
		// Click Add Feed
		driver.getSingleElement("css=*[id='addFeedBtn']").click();

		//Fill in the Feeds form
		
		
		driver.getSingleElement(CommunitiesObjects.AddFeedFormFeed).type(testConfig.getBrowserURL() + FVT_CommunitiesData.FeedsURL);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormTitle).type (FeedName);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormDescription).type(FVT_CommunitiesData.FeedsDescription);
		driver.getSingleElement(CommunitiesObjects.AddFeedFormTag).type(FVT_CommunitiesData.FeedsTag);

		//Save the form
		clickLink(CommonObjects.SaveButton);

		//Verify that the feeds are there
		//sel.focus("link=" + FVT_CommunitiesData.FeedsTitle);
		Assert.assertTrue(driver.getSingleElement("link=" + FeedName).isVisible());

		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);

		clickLink(CommunitiesObjects.LeftNavOption6);
		
		//Verify that the feeds are there
		Assert.assertTrue(driver.getSingleElement("link=" + FeedName).isVisible());
		
		//Click on the Feeds link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption6);

		/*
		
		//Now click on the more link and then edit
		clickLink(CommunitiesObjects.MoreLink);
		clickLink(CommunitiesObjects.EditLink);

		//Now Edit the Feeds title
		Element Feed = driver.getFirstElement(CommunitiesObjects.EditFeedFormFeed);
		Feed.clear();
		Feed.type(testConfig.getBrowserURL() + FVT_CommunitiesData.EditedFeedsURL);
		
		Element Title = driver.getFirstElement(CommunitiesObjects.EditFeedFormTitle);
		Title.clear();
		Title.type(FVT_CommunitiesData.EditedFeedsTitle);

		Element Desc = driver.getFirstElement(CommunitiesObjects.EditFeedFormDescription);
		Desc.clear();
		Desc.type(FVT_CommunitiesData.EditedFeedsDescription);
		
		// TODO define new edit tags fields. No longer same def as Add Feed tags field
//		Element Tags = driver.getSingleElement(CommunitiesObjects.EditFeedFormTag);
//		Tags.clear();
//		Tags.type(feedsTag);
		
		// TODO
		if (testConfig.browserIs(BrowserType.FIREFOX)){
			driver.getFirstElement(CommonObjects.EditSaveButton).hover();
			driver.getFirstElement(CommonObjects.EditSaveButton).click();
		}
		if (testConfig.browserIs(BrowserType.IE)){
			SaveAForm();
		}
		
		//Verify that the feeds are there
		if (driver.getElements("link=" + FVT_CommunitiesData.EditedFeedsTitle).size() == 0 ){
			Assert.fail("Feeds are not present.");
		}
		
		//Click on the Overview link in the left nav
		clickLink(CommunitiesObjects.LeftNavOption1);
		//Click on the Feeds link
		clickLink(CommunitiesObjects.LeftNavOption6);
		
		//Verify that the feeds are there
		if (driver.getElements("link=" + FVT_CommunitiesData.EditedFeedsTitle).size() == 0){
			Assert.fail("Feeds are not present.");	
		}
		
		*/
		
		return FeedName;
		
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
	
	/**
	 * Add the subcommunity widget to a parent community and confirm all subcommunities appear
	 * @throws Exception
	 */
	public void AddSubcommunityWidgetToOverview() throws Exception {
		//Choose to Customize Community from the Community Actions
		CustomizeCommunity();

		//Add the Subcommunities widget
		driver.getSingleElement(CommunitiesObjects.WidgetSubCommunities).click();

		//Check if the widget is still being added
		CheckForAddingWidget();

		//Close the widget section
		driver.getSingleElement(CommunitiesObjects.WidgetSectionClose).click();
		Thread.sleep(5000);

		//Verify that the subcommunities are displaying in the widget
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.SubcommunityWidget));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.PublicSubcommunity));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.ModeratedSubcommunity));
		Assert.assertTrue(driver.isElementPresent(CommunitiesObjects.RestrictedSubcommunity));
	}
	
	/**
	 * Create a new subcommunity with option to add parent community members
	 * @param CommunityName String name of new subcommmunity
	 * @param CommunityType String type of new subcommunity
	 * @param addAllMembers boolean option to add all members of parent community
	 * @param communityMember User to confirm is added to subcommunity
	 * @throws Exception
	 */
	public void CreateNewSubcommunity(String CommunityName, String CommunityType, boolean addAllMembers, User communityMember) throws Exception {
		CustomizeCommunity(CommunitiesObjects.Menu_Item_1); // create subcommunity option
		Thread.sleep(500);
		driver.getSingleElement(CommunitiesObjects.CommunityName).type(CommunityName);
		Thread.sleep(500);
		
		//Choose Type of Community
		driver.getSingleElement(CommunityType).click();
		Thread.sleep(1000);
		
		//Add members to subcommunity
		if(addAllMembers) {
			driver.getSingleElement(CommunitiesObjects.SubcommunityAddAllMembers).click();
			sleep(1000);
		}

		//Save the community
		driver.getSingleElement(CommonObjects.SaveButton).click();
		Thread.sleep(1000);
		
		//Wait for text Overview to appear before continuing
		try {
			int i = 0;
			while (i < 60) {
				if (driver.isElementPresent(CommunitiesObjects.CommunityOverview)) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(4000);
		}
		
		//Verify members were added to subcommunity
		if(addAllMembers) {
			driver.getSingleElement(CommunitiesObjects.LeftNavOption2).click();
			sleep(5000);
			Assert.assertTrue(driver.isElementPresent("css=img[alt='" + communityMember.getFirstName() + " " + communityMember.getLastName() + "']"));
		}
	}

	/**
	 * Resend community invitation assuming there is only 1 active invitation
	 * @throws Exception
	 */
	public void ResendCommunityInivte() throws Exception {
		//View Invitations tab under Members section of the community
		driver.getSingleElement(CommunitiesObjects.InvitationsTab).click();
		sleep(1000);
		
		//Click the Resend link
		driver.getSingleElement(CommunitiesObjects.ResendLink).click();
		sleep(500);
		
		//Confirm invitation resend text appears
		Assert.assertTrue(driver.isTextPresent(FVT_CommunitiesData.CommunityMemberInvited));
	}
	
	/**
	 * Revoke community invitation assuming there is only 1 active invitation
	 * @throws Exception
	 */
	public void RevokeCommunityInivte() throws Exception {
		//View Invitations tab under Members section of the community
		driver.getSingleElement(CommunitiesObjects.InvitationsTab).click();
		sleep(2000);
		
		//Click the revoke link and confirm propmt appears
		driver.getSingleElement(CommunitiesObjects.RevokeLink).click();
		sleep(2000);
		Assert.assertTrue(driver.isTextPresent(FVT_CommunitiesData.CommunityMemberRevokePrompt));
		
		//Click OK button to confirm revoke action
		driver.getSingleElement(CommonObjects.OKButton).click();
		sleep(2000);
		
		//Confirm there are no active invitations
		Assert.assertTrue(driver.isTextPresent(FVT_CommunitiesData.CommunityNoPendingInvitations));
	}
	
	/**
	 * Verify invited user can see specified community invite
	 * @param CommunityInvitedTo String the community the user has been invited to
	 * @throws Exception
	 */
	public void VerifyCommunityInvite(String CommunityInvitedTo) throws Exception {
		//View Community Invites
		driver.getSingleElement(CommunitiesObjects.ImInvited).click();
		Thread.sleep(2000);
		
		//Confirm Community Invite appears
		Assert.assertTrue(driver.isElementPresent("//a[contains(text(), '" + CommunityInvitedTo + "')]"));
	}
	
	/**
	 * Follow community
	 * @param CommunityToFollow String the community the user will follow
	 * @throws Exception
	 */
	public void FollowCommunity(String CommunityToFollow) throws Exception {	
		//View the community to follow
		Thread.sleep(2000);
		driver.getSingleElement("//a[contains(text(), '" + CommunityToFollow + "')]").click();
		Thread.sleep(2000);
		
		//Follow the community
		driver.getSingleElement(CommunitiesObjects.FollowACommunity).click();
		Thread.sleep(2000);
		
		//View My Communities
		driver.getSingleElement(CommunitiesObjects.MyCommunityView).click();
		Thread.sleep(2000);
		
		//View Communities I'm Following
		driver.getSingleElement(CommunitiesObjects.ImFollowing).click();
		Thread.sleep(2000);
		
		//Confirm Community link appears
		Assert.assertTrue(driver.isElementPresent("//a[contains(text(), '" + CommunityToFollow + "')]"));
	}

	private void SaveAForm(){
		
		//Save the form
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		try {
			wd.findElement(By.cssSelector("input[value='Save'][class='lotusFormButton'][role='button']")).submit();			
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}finally{
			
		}
		try {
			wd.findElement(By.cssSelector("input[value='Save'][class='lotusFormButton'][type='submit']")).submit();
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}finally{
				
		}
		try {
			wd.findElement(By.cssSelector("input[value='Save'][class='lotusFormButton'][name='submit']")).submit();
		} catch (Exception e) {
			Reporter.log(e.getMessage());
		}finally{
			
		}
	}
	
public void AddWidgetToOverview(String widget) throws Exception{
		
		//Choose to Customize Community from the Community Actions
		CustomizeCommunity();
		
		//Add the widget
		
		if (widget=="Blogs"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetBlog).click();
		}
		else if (widget=="Ideation Blog"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetIdeationBlog).click();
		}
		else if (widget=="Activities"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetActivities).click();
		}	
		else if (widget=="Wikis"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetWikis).click();
		}
		else if (widget=="Subcommunities"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetSubCommunities).click();
		}
		else if (widget=="Feeds"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetFeeds).click();
		}
		else if (widget=="Media Gallery"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetMediaGallery).click();
		}
		else if (widget=="Events"){
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetEvents).click();
		}
		
		sleep(500);
		//Check if the widget is still be added
		//CheckForAddingWidget();
		
		//smartSleepText("1 item added");
		//assertTrue("Widget not added", driver.isTextPresent("1 item added"));
		
		//Check that the widget was added correctly and close widget section.
		
		if(!isTextPresent("1 item added")){
			
			closeWidgetSection();
			
			sleep(500);
			
			AddWidgetToOverview(widget);
		}
		
		else{
			
			closeWidgetSection();
			
		}
	    		
	}
	

	//Add activity widget and create community for first time
	public String CreateInitialCommunityActivity(String DateTimeStamp) throws Exception{
		
		String ActivityName ="FVT Automation Test Activity "+DateTimeStamp;
		
		//Add theS widget
		driver.getSingleElement(CommunitiesObjects.WidgetActivities).click();
		
		//Check if the widget is still be added
		CheckForAddingWidget();
				
		//Close the widget section
		driver.getSingleElement(CommunitiesObjects.WidgetSectionClose).click();
		
		//Click on the activity link in the left nav
		clickLink(FVT_CommunitiesObjects.LeftNavOption7);
		
		//Click on link to open the Feed form
		clickLink(FVT_NewsObjects.CreateAnActivity);
		
		//Fill in the Activities form
		driver.getSingleElement(FVT_NewsObjects.ActivityName).click();
		driver.getSingleElement(FVT_NewsObjects.ActivityName).type(ActivityName);
		driver.getSingleElement(FVT_NewsObjects.ActivityTag).type("automation");
		driver.getSingleElement(FVT_NewsObjects.ActivityGoal).type("Create a community activity...");
		
		
		//Save the form
		clickLink(CommonObjects.SaveButton);
		
		//Click on the activity link in the left nav
		clickLink(FVT_CommunitiesObjects.LeftNavOption7);
		
		driver.isElementPresent("link=FVT Automation Test Activity "+DateTimeStamp);

		return ActivityName;
	}
	
	//Create a calendar entry
	public String createCalendarEntry( String EventName, Boolean Repeating, Boolean Notify, String Username) throws Exception{
		
		//Click on the bookmark link in the left nav
		
		//clickLink(FVT_CommunitiesObjects.LeftNavOption1);
		clickLink(FVT_CommunitiesObjects.LeftNavEvents);
		
		sleep(500);
		
		//Create event
		clickLink(FVT_CommunitiesObjects.CreateCalendarEvent);
		
		if(Repeating==true){
			
			clickLink(FVT_CommunitiesObjects.REPEATS);
			//driver.getSingleElement(FVT_CommunitiesObjects.SelectEventType).useAsDropdown().selectOptionByVisibleText(FVT_CommunitiesObjects.RepeatDaily);
		
		}
			
		if(Notify==true){
			
			clickLink(FVT_CommunitiesObjects.NOTIFY);
			
			driver.getFirstElement(FVT_CommunitiesObjects.NOTIFY_FILTER).type(Username);
			
			clickLink(FVT_CommunitiesObjects.NOTIFY_SELECTED);
		
		}
		
		//Fill in the Calendar form
		driver.getSingleElement(FVT_CommunitiesObjects.EventTags).type("FVT");
		
		driver.getSingleElement(FVT_CommunitiesObjects.EventTitle).type(EventName);
		
		
		//sleep(10000);
		//Save the form
		//driver.getSingleElement(CommunitiesObjects.CalendarSave).hover();
		sleep(1000);
		clickLink(CommunitiesObjects.CalendarSave);
		
		
		
		
		return EventName;
		
		
	}
	
public String createCalendarEntry( String EventName, Boolean Repeating, Boolean Notify, String Username, String Community) throws Exception{
		
		//Click on the bookmark link in the left nav
		if(!driver.isElementPresent(FVT_CommunitiesObjects.LeftNavEvents)){
			
			clickLink(Community);
			
			smartSleep(FVT_CommunitiesObjects.LeftNavEvents);
		}
		
		//clickLink(FVT_CommunitiesObjects.LeftNavOption1);
		clickLink(FVT_CommunitiesObjects.LeftNavEvents);
		
		sleep(500);
		
		//Create event
		clickLink(FVT_CommunitiesObjects.CreateCalendarEvent);
		
		if(Repeating==true){
			
			clickLink(FVT_CommunitiesObjects.REPEATS);
			//driver.getSingleElement(FVT_CommunitiesObjects.SelectEventType).useAsDropdown().selectOptionByVisibleText(FVT_CommunitiesObjects.RepeatDaily);
		
		}
			
		if(Notify==true){
			
			clickLink(FVT_CommunitiesObjects.NOTIFY);
			
			driver.getFirstElement(FVT_CommunitiesObjects.NOTIFY_FILTER).type(Username);
			
			clickLink(FVT_CommunitiesObjects.NOTIFY_SELECTED);
		
		}
		
		//Fill in the Calendar form
		driver.getSingleElement(FVT_CommunitiesObjects.EventTags).type("FVT");
		
		driver.getSingleElement(FVT_CommunitiesObjects.EventTitle).type(EventName);
		
		
		//sleep(10000);
		//Save the form
		//driver.getSingleElement(CommunitiesObjects.CalendarSave).hover();
		sleep(1000);
		clickLink(CommunitiesObjects.CalendarSave);
		
		
		
		
		return EventName;
		
		
	}
	
	//Create a calendar entry
	public void updateCalendarEntry(String EventName, Boolean Repeating, Boolean EntireSeries) throws Exception{
		
		//Click on the bookmark link in the left nav
		clickLink(FVT_CommunitiesObjects.LeftNavEvents);
		
		//Click on event
		clickLink("link="+EventName);
		
		//Click edit
		clickLink("link=Edit");
		
		//If its a repeating event choose whether to update series or instance
		if(Repeating==true){
			
			if(EntireSeries==true){
				clickLink(FVT_CommunitiesObjects.EntireSeriesCheckbox);
			}else{
				clickLink(FVT_CommunitiesObjects.InstanceCheckbox);
			}
		
		} 
		
		//Update location
		driver.getSingleElement(FVT_CommunitiesObjects.EventLocation).type(FVT_CommunitiesData.UpdatedEventLocation);
		
		//Save
		clickLink(FVT_CommunitiesObjects.SaveEventEdit);	
		
		
		
	}
	
	public void AddACommentToEventEntry() throws Exception {
		
		
		// Click on the Add a comment link for entry
		clickLink(FVT_CommunitiesObjects.EventAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_CommunitiesObjects.EventCommentTextArea).type(FVT_CommunitiesData.EventCommentText);

		//Save comment
		clickLink(CommonObjects.SaveButton);

	}
	
	public String StatusUpdate() throws Exception{
		

		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(UpdateStatus);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(FVT_NewsObjects.PostStatus);
		
		return UpdateStatus;
			
	}
	
	public void openCommunity(String CommunityName, String UserTypeView, String LeftNavOption) throws Exception {

		//Click on view
		clickLink(UserTypeView);
		
		sleep(2000);
		
		if(!driver.isElementPresent("link=" + CommunityName)){
			
			for(int x=0; x<10; x++){
				
				sleep(60000);
				clickLink(UserTypeView);
				sleep(2000);
				
				if(driver.isElementPresent("link=" + CommunityName)){					
					break;
				}				
			}			
		}
		
		//Click community
		clickLink("link=" + CommunityName);
		Reporter.log("CommunityName "+CommunityName);
		sleep(1000);
		
		// Condition to prevent issues when community doesn't open properly
		if(!driver.isElementPresent(LeftNavOption)){
			clickLink("link=" + CommunityName);
		}
		
		//Go to left nav option 
		clickLink(LeftNavOption);

	}
	
	
	public void requestToJoin(String CommunityName) throws Exception {

		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		clickLink("link="+CommunityName);
		
		clickLink(FVT_CommunitiesObjects.REQUEST_JOIN);

		clickLink(CommonObjects.SEND_BUTTON);
		
		assertTrue("Fail: Request not sent correctly", driver.isTextPresent("Your membership request has been sent."));	

		
	}
	
	//Method to handle issues when closing add widget section of communities
	public void closeWidgetSection() throws Exception {
		
		if(driver.isElementPresent(FVT_CommunitiesObjects.WidgetSectionClose)){	
			
			driver.getSingleElement(FVT_CommunitiesObjects.WidgetSectionClose).click();			
		}
		
		else{	
			
			clickLink(FVT_CommunitiesObjects.LeftNavOverview);
		}
		
	}
	
	//Method to Add users for community invite
	public void InviteUserToCommunity(User user){
		
		//Type member to invite
		driver.getFirstElement(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead).clear();
		driver.getFirstElement(FVT_CommunitiesObjects.InviteCommunityMembersTypeAhead).type(user.getDisplayName());
		
		smartSleep(FVT_CommunitiesObjects.selectedUserIdentifier_Invite1);
		
		//Click on Selected user
		driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier_Invite1).click();
		
	}
	
	
}
