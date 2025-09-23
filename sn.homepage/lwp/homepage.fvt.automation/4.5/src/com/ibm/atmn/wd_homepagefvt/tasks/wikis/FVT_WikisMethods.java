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

package com.ibm.atmn.wd_homepagefvt.tasks.wikis;


import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;

import org.testng.Assert;
import org.testng.AssertJUnit;

public class FVT_WikisMethods extends FVT_CommunitiesMethods {
	
	public void CreateAPublicWiki(String WikiName)throws Exception{
		
		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);
		sleep(1000);

		//Create a new public wiki
		if (testConfig.browserIs(BrowserType.IE)){
			typeInTextField(FVT_WikisObjects.Wiki_Name_Field_IE, WikiName);
		}else{
			typeInTextField(FVT_WikisObjects.Wiki_Name_Field_IE, WikiName);
		}

		//Click Save button
		clickLink(WikisObjects.Save_Button);

	}
	
	/**Create a private wiki. Add 3 users with Owner, Editor & Reader access*/
	public void createPrivateWikiWithUsers(String wikiTitle, String Username) throws Exception{
		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);

		//Create a wiki with wiki level tags
		typeInTextField(FVT_WikisObjects.Wiki_Name_Field, wikiTitle);
		
		//Select the Wiki Members Only radio button under 'Who can read this wiki' section 
		clickLink(WikisObjects.WikiMembersOnly_RadioButton);
		
		//Select the Wiki Editors and Members Only radio button under 'Who can edit this wiki' section 
		clickLink(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);
	
		//String BrowserInfo = ReturnBrowserInfo();
	
		//select one user as Reader
		
		driver.getSingleElement(WikisObjects.MembershipRolesUsersDropdown).useAsDropdown().selectOptionByVisibleText("Reader");
		
		driver.getSingleElement(WikisObjects.Wiki_Member_Typeahead).type(Username);
			
		clickLink(WikisObjects.SearchDir);
		
		clickLink(WikisObjects.SelectUser+":contains("+Username+")");

		clickLink(WikisObjects.Save_Button);
	}
	
	/** Input text in field */
	public void typeInTextField(String textField, String textContent) throws Exception {

		//Add a tag 
		driver.getFirstElement(WikisObjects.Tags_Field).type("automation");
		
		//Type text provided in textfield identified
		smartSleep(textField);
		
		driver.getSingleElement(textField).clear();
		driver.getSingleElement(textField).type(textContent);
		
		/*
		if (driver.isElementPresent(textField)){
			driver.getSingleElement(textField).clear();
			driver.getSingleElement(textField).type(textContent);
		}else{
			sleep(5000);
			driver.getSingleElement(textField).clear();
			driver.getSingleElement(textField).type(textContent);
		}
		*/
	}
	
	/** Click At item
	public void clickAtItem(String Link1, String Link2) throws Exception {

		//Click at the item provided
		driver.getSingleElement("").clickAt(Link1, Link2);
		
		//sel.clickAt(Link1, Link2);
		Thread.sleep(3000);
	} */


	/** Verify new homepage UI */
	public void verifyNewHomePageUI(String WikiName, String UserFullName, String TagName) throws Exception {

		//Verify wiki name & breadcrumbs
		AssertJUnit.assertTrue("FAIL: Wiki name isn't visible", driver.isTextPresent(WikiName));
		AssertJUnit.assertTrue("FAIL: Breadcrumbs aren't visible", driver.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		//Verify tagging UI
		AssertJUnit.assertTrue("FAIL: Tag Name isn't visible", driver.isTextPresent(WikisObjects.NoTagsIndicator));
		AssertJUnit.assertTrue("FAIL: Add tags link isn't visible", driver.isElementPresent(WikisObjects.Add_tags_Link));
		//Verify recommendations
		//AssertJUnit.assertTrue("FAIL: Recommendations isn't correct", driver.isTextPresent(WikisObjects.NoRecommendationsIndicator));

		//Verify that all page buttons are visible
		AssertJUnit.assertTrue("FAIL: Edit button is missing", driver.isElementPresent(WikisObjects.Edit_Button));
		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", driver.isElementPresent(WikisObjects.Page_Actions_Button));
		AssertJUnit.assertTrue("FAIL: Follow button is missing", driver.isElementPresent(WikisObjects.Follow_Button));
		AssertJUnit.assertTrue("FAIL: Wiki Actions button is missing", driver.isElementPresent(WikisObjects.Wiki_Actions_Button));

		//Verify all inline tabs are visible
		AssertJUnit.assertTrue("FAIL: Comments tab is missing", driver.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions tab is missing", driver.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments tab is missing", driver.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About tab is missing", driver.isElementPresent(WikisObjects.About_Tab));

	}
	
	/** Open Wiki from My Wikis */
	public void OpenWikiFromMyWikisTab(String Wiki_Title) throws Exception {
		//There is only 1 view now
		//cautiousFocus(WikisObjects.My_Wikis_Tab);
		//clickLink(WikisObjects.My_Wikis_Tab);
		
		//Click on the I'am a Reader link in the nav
		//clickLink(WikisObjects.MyWikis_Reader_Filter);
		
		if (testConfig.browserIs(BrowserType.IE)){
			//nothing to be be as IE is already in the Public Wikis view
		}else{
			clickLink(WikisObjects.Public_Wikis_Tab);
		}
		sleep(1000);
		clickLink("link=" + Wiki_Title);
		sleep(1000);
		
		//Click on the wiki that we are testing
		/*if (driver.isTextPresent(Wiki_Title)){
			clickLink("css=a.entry-title:contains['" + Wiki_Title + "']");
		}else{
			sleep(2000);
			clickLink("css=a.entry-title:contains['" + Wiki_Title + "']");
		}*/
			
		//Verify the correct wiki is loaded
		String PlacebarText = driver.getSingleElement(WikisObjects.Place_Bar_Title).getText();
		AssertJUnit.assertTrue("FAIL: placebar text doesn't match wiki title", Wiki_Title.equals(PlacebarText));
	}

	/** Add peer pages to wiki as a user with owner access */
	public void AddAPeer(String PageName, String PageContent) throws Exception {

		if (driver.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)){
			clickLink(WikisObjects.Page_Actions_Button_For_Editors);
		}else if (driver.isElementPresent(WikisObjects.Page_Actions_Button)){
			clickLink(WikisObjects.Page_Actions_Button);
		}
			
		sleep(2000);
		
		//Choose the Create a Peer option
		if (testConfig.browserIs(BrowserType.IE)){
			clickLink(FVT_WikisObjects.Menu_Create_Peer);
		}else{
			clickLink(WikisObjects.Menu_Item_2);
		}
				
		//Give the new page a name
		driver.getSingleElement(WikisObjects.New_Page_Title_Textfield).type(PageName);
		addATag();
		//sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
		Thread.sleep(2000);

		//Add content and save
		AddContentintheEditorforaNewPage(PageContent);
		
	}
	
	/** Add child pages to wiki as a user with owner access */
	public void AddAChild(String PageName, String PageContent) throws Exception {

		if (driver.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)){
			clickLink(WikisObjects.Page_Actions_Button_For_Editors);
		}else if (driver.isElementPresent(WikisObjects.Page_Actions_Button)){
			clickLink(WikisObjects.Page_Actions_Button);
		}
				
		//Choose add a child page
		if (testConfig.browserIs(BrowserType.IE)){
			clickLink(FVT_WikisObjects.Menu_Create_Child);
		}else{
			clickLink(WikisObjects.Menu_Item_1);
		}
	
		//give the new page a name
		driver.getSingleElement(WikisObjects.New_Page_Title_Textfield).type(PageName);
		addATag();
		//Add content and save
		AddContentintheEditorforaNewPage(PageContent);
		
	}

	public void AddContentintheEditorforaNewPage(String NewContentText) throws Exception {

		//AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", driver.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		//clickLink(WikisObjects.Wiki_Text_tab);
		//sleep(500);

		//driver.getSingleElement(WikisObjects.CKEditor_WikiTextTab).clear();
		//driver.getSingleElement(WikisObjects.CKEditor_WikiTextTab).type(NewContentText);
		
		//typeNativeInCkEditorForWikis(NewContentText);
		driver.getSingleElement("css=ul#qkrEditorTabs.lotusTabs li span a:nth(2)").click();
		Thread.sleep(500);
		driver.getSingleElement("css=textarea#htmlSourceEditor.qkrPageEditor").type(NewContentText);
		
		clickLink(WikisObjects.Save_and_Close_Link);
		sleep(3000);
		
		/*
		 * Add this code as there have being a number of failures as the CKEditor 
		 * is still saving the page.
		 */
		if (driver.isElementPresent(WikisObjects.Edit_Button)){
			//do nothing
		}else if(driver.isElementPresent(WikisObjects.Save_and_Close_Link)){
			sleep(5000);
		}
	
	}
	
	public boolean VerifyPageExists(String pageName) throws Exception {

		boolean foundPage = false;

		if (driver.isTextPresent(pageName)) foundPage = true;

		return foundPage;
	}
	
	/*
	 * Upload an attachment
	 */
	public void uploadAttachment(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Click on the attachment tab
		clickLink(WikisObjects.Attachments_Tab);
		//Click on the attachment link
		clickLink(FVT_WikisObjects.AddAttachmentLink);
		
		//In File Upload dialog enter the name and path to the file to upload		
		driver.getSingleElement(FVT_WikisObjects.Browse_Button).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));

		//Upload the file
		clickLink(CommonObjects.OKButton);

		//Check if the file is still uploading
		FileUploading(5);

	}
	
	/*
	 * Rename the attachment using edit
	 */
	public void RenameAttachment(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Check File Exists
		System.out.println(FileUploadName);
		driver.isTextPresent(FileUploadName);

		//Click on the Edit button
		clickLink(FVT_WikisObjects.EditAttachment);
		
		driver.getSingleElement(FVT_WikisObjects.Browse_Button_Rename).typeFilePath(testConfig.getBrowserEnvironment().getAbsoluteFilePath(getUploadFilesDir(), FileUploadName));

		Assert.assertTrue(driver.isTextPresent("The attachment was updated."));
	}
	
	/*
	 * Method to check if the file is still uploading, sleeps if so
	 */
	public void FileUploading(int UploadTime) throws Exception {

		try {
			int i = 0;
			while (i < UploadTime) {
				if (driver.getPageSource().contains("Upload Attachment")) {
					sleep(3000);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(1000);
	}
	
	public void ValidateTaggingUIInEditMode() throws Exception {

		//Check for Links at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: Tags heading is missing", driver.isTextPresent("Tags:"));

		if ((driver.isElementPresent(WikisObjects.Add_tags_Link)) & (driver.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links appear fine. No tags are added.");
		}
		else if ((driver.isElementPresent(WikisObjects.Add_or_RemoveTags_Link)) & (!driver.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links & tags appear fine");
		}
		else Assert.fail("FAIL: Problem with tagging links & tags");
	}
	
	public void ValidateWikiEditMode() throws Exception {

		//verify elements are present in the UI as per the mockup
		AssertJUnit.assertTrue("FAIL: Rich Text tab isn't visible", driver.isElementPresent(WikisObjects.Rich_Text_Tab));
		AssertJUnit.assertTrue("FAIL: HTML Source tab isn't visible", driver.isElementPresent(WikisObjects.HTML_Source_Tab));
		//AssertJUnit.assertTrue("FAIL: Wiki Text tab isn't visible", driver.isElementPresent(WikisObjects.Wiki_Text_tab));
		AssertJUnit.assertTrue("FAIL: Preview tab isn't visible", driver.isElementPresent(WikisObjects.Preview_Tab));
	}
	
	public void AddContentintheEditorforanExistingPage(String NewContentText) throws Exception {

		//AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", driver.isElementPresent(WikisObjects.WikiText_Editor));
		
		typeNativeInCkEditor(NewContentText);
		clickLink(WikisObjects.Save_and_Close_Link);
	}
	
	public void AddContentintheEditorforanExistingPage(String NewContentText,String layer) throws Exception {

		//AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", driver.isElementPresent(WikisObjects.WikiText_Editor));
		
		typeNativeInCkEditor(NewContentText,layer);
		clickLink(WikisObjects.Save_and_Close_Link);
	}
	
	public void AddContentintheEditorforanExistingPagePubCom(String NewContentText) throws Exception {

		//AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", driver.isElementPresent(WikisObjects.WikiText_Editor));
		
		typeNativeInCkEditor(NewContentText, "1");
		clickLink(WikisObjects.Save_and_Close_Link);
	}
		
	/** Check that new content appears in page view & username meta data is updated */
	public void VerifyAddedContentInHomepage(String SpecificContent) throws Exception {

		AssertJUnit.assertTrue("FAIL: Newly added content isn't correct", driver.getSingleElement(WikisObjects.Text_Content_In_Homepage).isTextPresent(SpecificContent));
		//String NewContent = driver.getSingleElement(WikisObjects.Text_Content_In_Homepage).getText();
		//AssertJUnit.assertTrue("FAIL: Newly added content isn't correct", NewContent.contains(SpecificContent));

	}
	

	/** Add a comment to wiki page */
	public String AddAComment(String CommentToBeAdded) throws Exception {

		//Open the Comment Editor
		//if (driver.isElementPresent(WikisObjects.Add_Comment_Link)) {
			//clickLink(WikisObjects.Add_Comment_Link);
		//}
		
		getActiveElement(WikisObjects.Add_Comment_Link).click();

		sleep(1000);
		
		//Add a comment to the page
		driver.getSingleElement(WikisObjects.Add_Comment_Editor).type(CommentToBeAdded);
		clickLink(WikisObjects.Save_Button);
		
		return CommentToBeAdded;
	}
	
	/** Edit comment to wiki page */
	public void editComment(String CommentToBeAdded) throws Exception {

		//Open the Comment Editor
		if (driver.isElementPresent(FVT_WikisObjects.Edit_Comment)) {
			clickLink(FVT_WikisObjects.Edit_Comment);
		}

		//Add a comment to the page
		driver.getSingleElement(WikisObjects.Edit_Comment_Editor).clear();
		driver.getSingleElement(WikisObjects.Edit_Comment_Editor).type(CommentToBeAdded);
		clickLink(WikisObjects.Save_Button);
		
		
	}
	
	public void VerifyCommentIsAdded(String Added_Comment) throws Exception {

		AssertJUnit.assertTrue("FAIL: Comment hasn't been added correctly", driver.isTextPresent(Added_Comment));
	}
	
	public void OpenTaggingEditor() throws Exception {

		//Verify whether the 'Add tags' link is visible
		if (driver.isElementPresent(WikisObjects.Add_tags_Link))
			this.clickLink(WikisObjects.Add_tags_Link);

		else this.clickLink(WikisObjects.Add_or_RemoveTags_Link);
	}
	
	public void AddATag(String New_Tag) throws Exception {

		driver.getSingleElement(WikisObjects.TagEditorTextFieldInput).type(New_Tag);
		//sel.type(WikisObjects.TagEditorTextFieldInput, New_Tag);
		getActiveElement(WikisObjects.OK_Button).click();
	}
	
	/** Verify recommendation UI */
	public void verifyRecommendationUI(int pageRecommends, boolean pageRecommended) throws Exception {

		String InfoText = "";

		if (driver.isElementPresent(WikisObjects.Recommendations_Info_Alternate))
			InfoText = driver.getSingleElement(WikisObjects.Recommendations_Info_Alternate).getText();
		else InfoText = driver.getSingleElement(WikisObjects.Recommendations_Info).getText();

		//Verify the Recommendation UI before a user recommends the page
		if ((pageRecommends == 0) && (!pageRecommended))
			AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals("No recommendations"));

		else if ((pageRecommends == 1) && (pageRecommended))
			AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals("You have recommended"));

		else {
			//Verify recommendation text when you haven't recommended
			if ((pageRecommends == 1) && (!pageRecommended))
				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals(pageRecommends + " person"));

			else if (!pageRecommended)
				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals(pageRecommends + " people"));

			//Verify recommendation text when you have recommended
			else if ((pageRecommends == 2) && (pageRecommended))
				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals("You and " + (pageRecommends - 1) + " other"));

			else if (pageRecommended) AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", InfoText.equals("You and " + (pageRecommends - 1) + " others"));
		}
	}
	
	//Recommend a page
	public void RecommendCurrentPage() throws Exception {

		//Recommend the current page
		if (driver.isElementPresent(WikisObjects.Recommendations_Info_Alternate)) {
			clickLink(WikisObjects.Recommendations_Info_Alternate);
		}
		else {
			clickLink(WikisObjects.Recommendations_Info);
		}

	}
	
	//Like a page
	public void LikeCurrentPage() throws Exception {

		//Recommend the current page
		//driver.getSingleElement(WikisObjects.LikeWikiPage).hover();
		clickLink(WikisObjects.LikeWikiPage);
		
	}
	
	//Add a tag
	public void addATag() throws Exception {

		driver.getSingleElement(WikisObjects.Add_Tag_Link).click();
		driver.getSingleElement(WikisObjects.Tag_Textbox_2).type("automation");
		
	}
	
	/** Delete a page from the wiki */
	public void deletePage(String PageName) throws Exception {

		clickLink(WikisObjects.Page_Actions_Button);

		//Click on the Delete Page link in Page Actions dropdown
		clickLink(WikisObjects.Menu_Item_6);

		//Click Delete button
		clickLink(WikisObjects.OK_Button_In_Popup);
	
	}

	/** Open Edit wiki form */
	public void OpenEditWikiForm() throws Exception {

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);

		//Open Edit Wiki
		
		if (testConfig.browserIs(BrowserType.IE)){
			clickLink(FVT_WikisObjects.Menu_Edit_Wiki);
		}else{
			clickLink(WikisObjects.Menu_Item_1);
		}
			
		
	}
	
	/** Change wiki title in Edit Wiki form */
	public void changeWikiTitle(String titleChange) throws Exception {
		
		//Clear the existing wiki title and then enter the new title
		driver.getSingleElement(WikisObjects.Edit_Wiki_Name_Textfield).clear();
		driver.getSingleElement(WikisObjects.Edit_Wiki_Name_Textfield).type(titleChange);
		//Save and close
		clickLink(WikisObjects.Save_Button);
	}
	
	/** Verify wiki title has been changed */
	public void validateChangedWikiTitle(String titleChange) throws Exception {

		String placeBarText = driver.getSingleElement(WikisObjects.Place_Bar_Title).getText();
		String rootBreadcrumbText = driver.getSingleElement(WikisObjects.Breadcrumbs_Root_Page).getText();

		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in placebar", titleChange.equals(placeBarText));
		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in breadcrumb root", titleChange.equals(rootBreadcrumbText));
	}

	/** Delete Wiki */
	public void deleteThisWiki(String Username) throws Exception {

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);

		//Delete Wiki
		if (testConfig.browserIs(BrowserType.IE)){
			sleep(500);
			clickLink(FVT_WikisObjects.Menu_Delete_Wiki);
		}else{
			sleep(500);
			clickLink(WikisObjects.Menu_Item_2);
		}
		
		driver.getSingleElement(WikisObjects.Delete_Signature).type(Username);
				
		driver.getSingleElement(WikisObjects.Delete_Check).click();
		
		//Check box to acknowledge the delete
		driver.getSingleElement(WikisObjects.Permanently_Delete_This_Wiki).click();
		
		sleep(1500);
				
	}
	
	/**Delete Wiki*/
	public void DeleteCommunityWiki() throws Exception {    
		//Activate Wiki Actions dropdown
		clickLink(FVT_WikisObjects.Overview_Tab_Comm);
		
		//Open Edit Wiki
		
		//driver.getFirstElement(FVT_WikisObjects.Wiki_Widget_Dropdown).hover();
		driver.getFirstElement(FVT_WikisObjects.Wiki_Widget_Dropdown).click();
		//driver.getFirstElement(FVT_WikisObjects.Wiki_Widget_Dropdown).doubleClick();
		//clickLink(FVT_WikisObjects.Wiki_Widget_Dropdown);
		//
		clickLink(FVT_WikisObjects.Remove);
		
		//Activate Wiki Actions dropdown
		clickLink(FVT_WikisObjects.Remove_Confirm);

	}

	
	public void ClickButtonAndChooseOptions()throws Exception{
		
		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);
		
		//Choose the option
		clickLink("Delete Wiki");
		
		
	}
	
	
	/**Check both My Wikis & Public Wikis list feeds for wiki name to confirm its existence*/
	public boolean verifyWikiExists(String WikiName, String WikiType) throws Exception {
		boolean wikiExists = false;
		
		if (WikiType.equals("Private")){
			clickLink("link=I'm an Owner");
			if(driver.isElementPresent("link=" + WikiName))
				wikiExists = true;
		}
		else {
			clickLink(WikisObjects.Public_Wikis_Tab);
			if(driver.isElementPresent("link=" + WikiName))
				wikiExists = true;
		}
		return wikiExists;
	}
	
	public void typeNativeInCkEditorForWikis(String text) {
		
		//Click into the CK Editor and then type
		driver.getSingleElement(WikisObjects.CKEditor_iFrame_Wikis).click();
		sleep(1000);
		driver.getSingleElement(WikisObjects.CKEditor_iFrame_Wikis).type(text);
		sleep(1000);
		
	}
	
	public void followWiki(String WikiName, String WikiType) {
		
		if(WikiType=="Public"){
		
			clickLink(FVT_WikisObjects.Public_Wikis_Tab);
	
		}else{
		
			clickLink(FVT_WikisObjects.ImAReader);
		
		}
	
		clickLink("link="+WikiName);
	
		clickLink(FVT_WikisObjects.Follow);
	
		clickLink(FVT_WikisObjects.FollowWiki);
		
			}
	
	
	public void addMemberToWiki(String Username) {
		
		//Click into the CK Editor and then type
		clickLink(FVT_WikisObjects.Members_Link);
		
		
		clickLink(FVT_WikisObjects.Add_Members_Button);
		
		driver.getFirstElement(FVT_WikisObjects.ADD_MEMBER_TEXTFIELD).type(Username);
		
		clickLink(FVT_WikisObjects.fullUserSearchIdentifier);
		
		clickLink(FVT_WikisObjects.selectedUserIdentifier);
		
		clickLink(CommonObjects.OKButton);
		
	}
	
	
public void changeMemberRole(String Username) {
		
		//Click into the CK Editor and then type
		clickLink(FVT_WikisObjects.Members_Link);
		
		
		driver.getFirstElement("//a[contains(text(),'" + Username + "')]").hover();
		driver.typeNative("\t");
		driver.typeNative("\n");
		
		clickLink(FVT_WikisObjects.ChangeRoleRadio_Reader);
		
		clickLink(CommonObjects.SaveButton);
		
	}
	
	
	
	}
