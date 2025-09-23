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

package com.ibm.atmn.lc.tasks.wikis;

import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.wikis.Data;
import com.ibm.atmn.lc.appobjects.wikis.WikisObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import java.awt.event.*;
import org.testng.Assert;
import org.testng.AssertJUnit;

@SuppressWarnings("unused")
public class WikisMethods extends CommonMethods {

	/** Logout of the current user session & log back in to another user session */
	public void LogoutCurrentUserAndLoginDifferentUser(String UserName, String Password) throws Exception {

		clickLink(WikisObjects.Logout_Link);

		for (int second = 0;; second++) {
			if (second >= 60) Assert.fail("Login Link has not appeared after 60 seconds");
			try {
				sel.waitForPageToLoad("60000");
				if (sel.isVisible(WikisObjects.Login_Link)) System.out.println("INFO: Login Link is present.");
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		sel.click(WikisObjects.Login_Link);
		sel.waitForPageToLoad("60000");
		Thread.sleep(2000);

		this.Login(UserName, Password);
	}

	/** Click specified link */
	public void clickLink(String Link) {

		//Click the link provided
		sel.click(Link);
		//sleep(3500);
		try {
			int i = 0;
			while (i < 120) {
				if (sel.isTextPresent("Submit Feedback")) {
					sleep(500);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(4000);
		}
	}

	/** Create a private wiki. Add 3 users & 3 groups with Owner, Editor & Reader access */
	public void createPrivateWikiWithUsersAndGroups(String wikiTitle) throws Exception {

		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);

		//Create a wiki with wiki level tags
		typeInTextField(WikisObjects.Wiki_Name_Field, wikiTitle);

		//Select the Wiki Members Only radio button under 'Who can read this wiki' section 
		clickLink(WikisObjects.WikiMembersOnly_RadioButton);

		//Select the Wiki Editors and Members Only radio button under 'Who can edit this wiki' section 
		clickLink(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);

		String BrowserInfo = ReturnBrowserInfo();

		//select one user as Owner
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Owner_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Owner_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Owner_Username_Typeahead);

		//select another User as Editor
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Editor_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Editor_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Editor_Username_Typeahead);

		//select another User as Reader
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Reader_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Reader_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Reader_Username_Typeahead);

		//Click on Add Groups Link to open the drop down and the Groups field to Add Groups
		clickLink(WikisObjects.AddGroups_Link);

		//select one Group as Owner
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesGroupsDropdown, Data.Owner_Role);
		ActivateTypeAheadForGroupsField(BrowserInfo, Data.Wiki_LDAP_Owners_Group_Typeahead);
		selectMemberFromGroupDropdown(Data.Wiki_LDAP_Owners_Group);

		//select one Group as Editor
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesGroupsDropdown, Data.Editor_Role);
		ActivateTypeAheadForGroupsField(BrowserInfo, Data.Wiki_LDAP_Editors_Group_TypeAhead);
		selectMemberFromGroupDropdown(Data.Wiki_LDAP_Editors_Group);

		//select one Group as Reader
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesGroupsDropdown, Data.Reader_Role);
		ActivateTypeAheadForGroupsField(BrowserInfo, Data.Wiki_LDAP_Readers_Group_TypeAhead);
		selectMemberFromGroupDropdown(Data.Wiki_LDAP_Readers_Group);

		//Click Create Wiki button
		clickLink(WikisObjects.Save_Button);
	}

	/** Create a private wiki. Add 3 users with Owner, Editor & Reader access */
	public void createPrivateWikiWithUsers(String wikiTitle) throws Exception {

		//Click Start a Wiki button
		clickLink(WikisObjects.Start_New_Wiki_Button);

		//Create a wiki with wiki level tags
		typeInTextField(WikisObjects.Wiki_Name_Field, wikiTitle);

		//Select the Wiki Members Only radio button under 'Who can read this wiki' section 
		clickLink(WikisObjects.WikiMembersOnly_RadioButton);

		//Select the Wiki Editors and Members Only radio button under 'Who can edit this wiki' section 
		clickLink(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);

		String BrowserInfo = ReturnBrowserInfo();

		//select one user as Owner
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Owner_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Owner_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Owner_Username_Typeahead);

		//select another User as Editor
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Editor_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Editor_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Editor_Username_Typeahead);

		//select another User as Reader
		SelectMembershipRoleOnCreateWikiForm(WikisObjects.MembershipRolesUsersDropdown, Data.Reader_Role);
		ActivateTypeAheadForMembersField(BrowserInfo, Data.Reader_Username_Typeahead);
		selectMemberFromUserDropdown(Data.Reader_Username_Typeahead);

		clickLink(WikisObjects.Save_Button);
	}

	/** Input text in field */
	public void typeInTextField(String textField, String textContent) throws Exception {

		//Type text provided in textfield identified
		sel.type(textField, textContent);
	}

	/** Change role in membership dropdown as specified */
	public void SelectMembershipRoleOnCreateWikiForm(String MembershipList, String Role) throws Exception {

		sel.select(MembershipList, Role);
		Thread.sleep(3000);
	}

	/** Add a comment to wiki page */
	public void AddAComment(String CommentToBeAdded) throws Exception {

		//Open the Comment Editor
		if (sel.isElementPresent(WikisObjects.Add_Comment_Link)) {
			clickLink(WikisObjects.Add_Comment_Link);
		}

		//Add a comment to the page
		sel.type(WikisObjects.Add_Comment_Editor, CommentToBeAdded);
		clickLink(WikisObjects.Save_Button);
	}

	/** Verify new homepage UI */
	public void verifyNewHomePageUI(String WikiName, String UserFullName, String TagName) throws Exception {

		//Allow time for page to load
		Thread.sleep(4000);
		//Verify wiki name & breadcrumbs
		AssertJUnit.assertTrue("FAIL: Wiki name isn't visible", sel.isTextPresent(WikiName));
		AssertJUnit.assertTrue("FAIL: Breadcrumbs aren't visible", sel.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		//Verify username of wiki creator
		//String UserNameonPageViewintheWiki = sel.getText(Objects.Username_on_PageView);
		//AssertJUnit.assertTrue("FAIL: Username for the user who created the wiki is not displayed", UserNameonPageViewintheWiki.contains(UserFullName));

		//Verify tagging UI
		AssertJUnit.assertTrue("FAIL: Tag Name isn't visible", sel.isTextPresent(WikisObjects.NoTagsIndicator));
		AssertJUnit.assertTrue("FAIL: Add tags link isn't visible", sel.isElementPresent(WikisObjects.Add_tags_Link));
		//Verify recommendations
		AssertJUnit.assertTrue("FAIL: Recommendations isn't correct", sel.isTextPresent(WikisObjects.NoRecommendationsIndicator));

		//Verify that all page buttons are visible
		AssertJUnit.assertTrue("FAIL: Edit button is missing", sel.isElementPresent(WikisObjects.Edit_Button));
		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button));
		AssertJUnit.assertTrue("FAIL: Follow button is missing", sel.isElementPresent(WikisObjects.Follow_Button));
		AssertJUnit.assertTrue("FAIL: Wiki Actions button is missing", sel.isElementPresent(WikisObjects.Wiki_Actions_Button));

		//Verify all inline tabs are visible
		AssertJUnit.assertTrue("FAIL: Comments tab is missing", sel.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions tab is missing", sel.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments tab is missing", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About tab is missing", sel.isElementPresent(WikisObjects.About_Tab));

	}

	/** Open Wiki from My Wikis */
	public void OpenWikiFromMyWikisTab(String Wiki_Title) throws Exception {
		//There is only 1 view now
		clickLink(WikisObjects.My_Wikis_Tab);
		
		//Click on the I'am a Reader link in the nav
		clickLink(WikisObjects.MyWikis_Reader_Filter);
		
		//Click on the wiki that we are testing
		clickLink("//a[contains(@title, '" + Wiki_Title + "')]");

		//Verify the correct wiki is loaded
		String PlacebarText = (sel.getText(WikisObjects.Place_Bar_Title));
		AssertJUnit.assertTrue("FAIL: placebar text doesn't match wiki title", Wiki_Title.equals(PlacebarText));
	}

	public void OpenWikiIamFollowingFromMyWikisTab(String Wiki_Title) throws Exception {
		//There is only 1 view now
		clickLink(WikisObjects.My_Wikis_Tab);
		
		//Click on the I'am following link in the nav
		clickLink(WikisObjects.MyWikis_Follow_Filter);

		//Navigate through pages to locate the wiki title
		/*while (!sel.isElementPresent("link=" + Wiki_Title)) {
			if ((sel.isElementPresent(WikisObjects.Link_toshow_25Wikis_perpage)) & (!sel.isElementPresent("link=" + Wiki_Title)))
				clickLink(WikisObjects.Link_toshow_25Wikis_perpage);

			if ((sel.isElementPresent(WikisObjects.Link_toshow_50Wikis_perpage)) & (!sel.isElementPresent("link=" + Wiki_Title)))
				clickLink(WikisObjects.Link_toshow_50Wikis_perpage);

			while (!sel.isElementPresent("link=" + Wiki_Title)) {
				if ((sel.isVisible(WikisObjects.Top_Next_Page_Link_Active)) && (!sel.isElementPresent("link=" + Wiki_Title))) clickLink(WikisObjects.Top_Next_Page_Link_Active);

				if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive)) && (!sel.isElementPresent("link=" + Wiki_Title))) break;
			}

			AssertJUnit.assertTrue("FAIL: Wiki doesn't appear to have been created", sel.isElementPresent("link=" + Wiki_Title));
		}*/
		
		//Click on the wiki that we are testing
		clickLink("//a[contains(@title, '" + Wiki_Title + "')]");
		
		//Verify the correct wiki is loaded
		String PlacebarText = (sel.getText(WikisObjects.Place_Bar_Title));
		AssertJUnit.assertTrue("FAIL: placebar text doesn't match wiki title", Wiki_Title.equals(PlacebarText));
	}

	public void VerifyIamFollowingFilterIsEmpty(String Wiki_Title) throws Exception {

		clickLink(WikisObjects.My_Wikis_Tab);

		clickLink(WikisObjects.MyWikis_Follow_Filter);

		//Navigate through pages to locate the wiki title
		try {
			AssertJUnit.assertTrue("FAIL: Wiki is appearing in the I'm Following filter when it should not!", !sel.isElementPresent("link=" + Wiki_Title));
		} catch (Exception e) {
			System.out.println("FAIL: Wiki is appearing in the I'm Following filter when it should not!" + e);
		}

		//AssertJUnit.assertTrue("FAIL: placebar text doesn't match wiki title", Wiki_Title.equals(PlacebarText));	
	}

	/** Verify recommendation UI */
	public void verifyRecommendationUI(int pageRecommends, boolean pageRecommended) throws Exception {

		String InfoText = "";

		if (sel.isElementPresent(WikisObjects.Recommendations_Info_Alternate))
			InfoText = sel.getText(WikisObjects.Recommendations_Info_Alternate);
		else InfoText = sel.getText(WikisObjects.Recommendations_Info);

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

	/** Click At item */
	public void clickAtItem(String Link1, String Link2) throws Exception {

		//Click at the item provided
		sel.clickAt(Link1, Link2);
		Thread.sleep(3000);
	}

	/** Open in Context menu */
	public void openContextMenu(String Item1, String Item2) throws Exception {

		//Click at the item provided
		sel.contextMenuAt(Item1, Item2);
		Thread.sleep(3000);
	}

	/** Verify link in Create wiki page */
	public void ValidateLinksInCreateWikiPageMode() throws Exception {

		//Check for Links at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: Save & Close link is missing", sel.isElementPresent(WikisObjects.Save_and_Close_Link));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
		AssertJUnit.assertTrue("FAIL: Save link is missing", sel.isElementPresent(WikisObjects.Save_Link));
	}

	/** Create a public wiki with all authenticated users as editors */
	public void CreatePublicWikiwithAllAuthenticatedUsersasEditors(String WikiName, String TagName) throws InterruptedException {

		//Launch Create a New Wiki lightbox
		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(2000);

		//Enter a wiki name & tags in respective textfields
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);

		//Click Create Wiki button
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(3000);
	}

	/** Add peer pages to wiki as a user with owner access */
	public void AddAPeer(String PageName, String PageContent) throws Exception {

		String ItemText = "";

		if (sel.isElementPresent(WikisObjects.Page_Actions_Button))
			clickAtItem(WikisObjects.Page_Actions_Button, "Page Actions");
		else clickAtItem(WikisObjects.Page_Actions_Button_For_Editors, "Page Actions");

		if (sel.isElementPresent(WikisObjects.Menu_Item_2)) {
			ItemText = sel.getText(WikisObjects.Menu_Item_2);
			AssertJUnit.assertTrue("FAIL: The first item isn't 'Create Peer'", ItemText.contains("Create Peer"));

			clickLink(WikisObjects.Menu_Item_2);

			sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
			Thread.sleep(2000);

			AddContentintheEditorforaNewPage(PageContent);
		}
	}

	/** Add child pages to wiki as a user with owner access */
	public void AddAChild(String PageName, String PageContent) throws Exception {

		String ItemText = "";

		if (sel.isElementPresent(WikisObjects.Page_Actions_Button))
			clickAtItem(WikisObjects.Page_Actions_Button, "Page Actions");
		else clickAtItem(WikisObjects.Page_Actions_Button_For_Editors, "Page Actions");

		if (sel.isElementPresent(WikisObjects.Menu_Item_1)) {
			ItemText = sel.getText(WikisObjects.Menu_Item_1);
			AssertJUnit.assertTrue("FAIL: The first item isn't 'Create Child'", ItemText.contains("Create Child"));

			clickLink(WikisObjects.Menu_Item_1);

			sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
			Thread.sleep(2000);

			AddContentintheEditorforaNewPage(PageContent);
		}
	}

	/** Check that new content appears in page view & username meta data is updated */
	public void VerifyAddedContentInHomepage(String SpecificContent, String UserUpdated) throws Exception {

		String NewContent = sel.getText(WikisObjects.Text_Content_In_Homepage);

		AssertJUnit.assertTrue("FAIL: Newly added content isn't correct", NewContent.contains(SpecificContent));

		//Verify that username in meta data has been updated
		//String MetaUsername = sel.getText(Objects.Username_on_PageView);
		//AssertJUnit.assertTrue("FAIL: Username hasn't been updated in meta data", MetaUsername.contains(UserUpdated));
	}

	/** Return a count of specific elements */
	public static int ElementCount(String ToBeParsed) throws Exception {

		//This method is used to count the number of elements in software features (e.g. Child Pages, Attachments & Comments)
		String TextToBeParsed = ToBeParsed;
		StringBuffer ParsedDigitInTextFormat = new StringBuffer();

		for (int index = 0; index < TextToBeParsed.length(); index++) {
			if (TextToBeParsed.charAt(index) == '(') {

				int innerIndex = index + 1;

				while (TextToBeParsed.charAt(innerIndex) != ')') {
					ParsedDigitInTextFormat.append(TextToBeParsed.charAt(innerIndex));
					innerIndex++;
				}
			}
		}

		int ParsedDigit = Integer.parseInt(ParsedDigitInTextFormat.toString());

		return ParsedDigit;
	}

	/** Open Edit wiki form */
	public void OpenEditWikiForm() throws Exception {

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);

		//Open Edit Wiki
		clickLink(WikisObjects.Menu_Item_1);
	}

	/** Change wiki title in Edit Wiki form */
	public void changeWikiTitle(String titleChange) throws Exception {

		sel.type(WikisObjects.Edit_Wiki_Name_Textfield, titleChange);
		clickLink(WikisObjects.Save_Button);
	}

	/** Verify wiki title has been changed */
	public void validateChangedWikiTitle(String titleChange) throws Exception {

		String placeBarText = sel.getText(WikisObjects.Place_Bar_Title);
		String rootBreadcrumbText = sel.getText(WikisObjects.Breadcrumbs_Root_Page);

		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in placebar", titleChange.equals(placeBarText));
		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in breadcrumb root", titleChange.equals(rootBreadcrumbText));
	}

	/** Delete Wiki */
	public void DeleteThisWiki() throws Exception {

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);

		//Open Edit Wiki
		clickAtItem(WikisObjects.Menu_Item_2, "Delete Wiki");

		//Check box to acknowledge the delete
		sel.click(WikisObjects.Permanently_Delete_This_Wiki);

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.OK_Button_In_Popup);
		sel.setTimeout("10000");
	}

	/** Use Edit Wiki form to change/add a description */
	public void changeDescriptionThroughEditWikiForm(String Description) throws Exception {

		sel.type(WikisObjects.Description_field_on_EditWiki, Description);
		clickLink(WikisObjects.Save_Button);
	}

	/** Delete a page from the wiki */
	public void deletePage(String PageName) throws Exception {

		clickLink(WikisObjects.Page_Actions_Button);

		//Click on the Delete Page link in Page Actions dropdown
		clickLink(WikisObjects.Menu_Item_6);

		//Click Delete button
		clickLink(WikisObjects.OK_Button_In_Popup);
		//clickLink(Objects.Delete_Button_In_Popup);
	}

	/** Verify 'Back to' link */
	public void ValidateBackToLink(String WikiTitle) {

		String BackToText = sel.getText(WikisObjects.Back_To_Link);
		AssertJUnit.assertTrue("FAIL: Back to text link isn't correct", BackToText.equals("< Back to " + WikiTitle));
	}

	/** Check both My Wikis & Public Wikis list feeds for wiki name to confirm its existence */
	public boolean verifyWikiExists(String WikiName, String WikiType) throws Exception {

		boolean wikiExists = false;

		if (WikiType.equals("Private")) {
			clickLink(WikisObjects.My_Wikis_Tab);
			clickLink(WikisObjects.MyWikis_Reader_Filter);
			clickLink(WikisObjects.PageID_Showing_50Members_perpage);
			if (sel.isElementPresent("link=" + WikiName)) wikiExists = true;

			clickLink(WikisObjects.My_Wikis_Tab);
			clickLink(WikisObjects.PageID_Showing_50Members_perpage);

			if (sel.isElementPresent("link=" + WikiName)) wikiExists = true;
		}
		else {
			clickLink(WikisObjects.My_Wikis_Tab);
			clickLink(WikisObjects.PageID_Showing_50Members_perpage);
			if (sel.isElementPresent("link=" + WikiName)) wikiExists = true;
		}
		return wikiExists;
	}

	public void LoginUIValidation() {

		// Validation of UI controls on Login screen
		AssertJUnit.assertTrue("FAIL: username field isn't visible", sel.isElementPresent(WikisObjects.WIKI_USERNAME_FIELD));
		AssertJUnit.assertTrue("FAIL: password field isn't visible", sel.isElementPresent(WikisObjects.WIKI_Password_FIELD));
		AssertJUnit.assertTrue("FAIL: login button isn't visible", sel.isElementPresent(WikisObjects.WIKI_Login_Button));

		System.out.println("PASS: Login page UI appears fine");
	}

	public void ServicePageUIValidation() throws Exception {

		//Verify that the tabs appear & 'Public Wikis' is in focus
		AssertJUnit.assertTrue("FAIL: Public Wikis tab isn't visible", sel.isElementPresent(WikisObjects.Public_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: My Wikis tab isn't visible", sel.isElementPresent(WikisObjects.My_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: Public Wikis tab isn't in focus", sel.isElementPresent(WikisObjects.Public_Wikis_Tab_Focus));
	}

	public void clickObject(String Object) throws Exception {

		//Click the object provided
		sel.focus(Object);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(3000);
	}

	/** Select Table display view */
	public void ChangeToTableView() throws Exception {

		if (sel.isElementPresent(WikisObjects.UnPressedTableIcon)) clickLink(WikisObjects.UnPressedTableIcon);
	}

	/** Select List display view */
	public void ChangeToListView() throws Exception {

		if (sel.isElementPresent(WikisObjects.UnPressedListIcon)) clickLink(WikisObjects.UnPressedListIcon);
	}

	/** Verify recommendation UI */
	public void verifyRecommendationUI_OLDCode(int pageRecommends, boolean pageRecommended) throws Exception {

		//Verify the Recommendation UI before a user recommends the page
		if ((pageRecommends == 0) && (!pageRecommended))
			AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.getText(WikisObjects.Recommendations_Info).equals("No recommendations"));

		else {
			String numericValue = "";
			String InfoText = sel.getText(WikisObjects.Recommendations_Info);
			if (InfoText.contains(" person")) {
				int x = InfoText.length() - 1;

				while (InfoText.charAt(x) != ' ') {
					x--;
				}
				numericValue = InfoText.substring(0, x--);
				AssertJUnit.assertTrue("FAIL: Numeric value should reflect how many people have recommended this page", pageRecommends == Integer.parseInt(numericValue));

				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.isTextPresent(numericValue + " person"));
				hoverMouseOver(WikisObjects.Recommendations_Info);
				AssertJUnit.assertTrue("FAIL: Recommend this page link in recommendation info is incorrect", sel.isElementPresent(WikisObjects.Recommend_This_Page_Link));
				AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(numericValue + " other person recommended this:"));
				if (!pageRecommended)
					AssertJUnit.assertTrue("FAIL: Recommending members list is incorrect", sel
							.isElementPresent("xpath=//*[contains(@id,'lconn_wikis_widget_tooltip_Recommend_')]/div[1]/div/div/div/ul/li[" + pageRecommends + "]/a"));
				else AssertJUnit.assertTrue("FAIL: Recommending members list is incorrect", sel
						.isElementPresent("xpath=//*[contains(@id,'lconn_wikis_widget_tooltip_Recommend_')]/div[1]/div/div/div/ul/li[" + pageRecommends++ + "]/a"));
			}

			else if (InfoText.contains(" people")) {
				int x = InfoText.length() - 1;

				while (InfoText.charAt(x) != ' ') {
					x--;
				}
				numericValue = InfoText.substring(0, x--);
				AssertJUnit.assertTrue("FAIL: Numeric value should reflect how many people have recommended this page", pageRecommends == Integer.parseInt(numericValue));

				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.isTextPresent(numericValue + " people"));
				hoverMouseOver(WikisObjects.Recommendations_Info);
				AssertJUnit.assertTrue("FAIL: Recommend this page link in recommendation info is incorrect", sel.isElementPresent(WikisObjects.Recommend_This_Page_Link));
				AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(numericValue + " other people recommended this:"));

				if (!pageRecommended)
					AssertJUnit.assertTrue("FAIL: Recommending members list is incorrect", sel
							.isElementPresent("xpath=//*[contains(@id,'lconn_wikis_widget_tooltip_Recommend_')]/div[1]/div/div/div/ul/li[" + pageRecommends + "]/a"));
				else AssertJUnit.assertTrue("FAIL: Recommending members list is incorrect", sel
						.isElementPresent("xpath=//*[contains(@id,'lconn_wikis_widget_tooltip_Recommend_')]/div[1]/div/div/div/ul/li[" + pageRecommends++ + "]/a"));
			}

			else if (InfoText.contains("You and ")) {
				int y = InfoText.length() - 1;

				while (InfoText.charAt(y) != ' ') {
					y--;
				}

				numericValue = InfoText.substring(8, y--);
				AssertJUnit.assertTrue("FAIL: Numeric value should reflect how many other people have recommended this page", pageRecommends == Integer.parseInt(numericValue));

				if (Integer.parseInt(numericValue) == 1) {
					AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.isTextPresent("You and " + numericValue + " other"));
					hoverMouseOver(WikisObjects.Recommendations_Info);
					AssertJUnit.assertTrue("FAIL: Recommend this page link in recommendation info is incorrect", sel.isElementPresent(WikisObjects.Recommend_This_Page_Link));
					AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(numericValue
							+ " other person recommended this:"));
				}
				else {
					AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.isTextPresent("You and " + numericValue + " others"));
					hoverMouseOver(WikisObjects.Recommendations_Info);
					AssertJUnit.assertTrue("FAIL: Recommend this page link in recommendation info is incorrect", sel.isElementPresent(WikisObjects.Recommend_This_Page_Link));
					AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(numericValue
							+ " other people recommended this:"));
				}
			}
			else {
				AssertJUnit.assertTrue("FAIL: Recommendation text is incorrect", sel.getText(WikisObjects.Recommendations_Info).equals("You"));
				hoverMouseOver(WikisObjects.Recommendations_Info);
				AssertJUnit.assertTrue("FAIL: Popup text for recommendation info is incorrect", sel.isTextPresent("You have recommended this page."));
			}
		}
	}

	/** Validation of Page Actions items depending on user access */
	public void ValidatePageActionsItems(String UserAccess) throws Exception {

		if (UserAccess.equals("Owner")) {
			clickAtItem(WikisObjects.Page_Actions_Button, "Page Actions");

			String NewChildPage = sel.getText(WikisObjects.Menu_Item_1);
			String NewPeerPage = sel.getText(WikisObjects.Menu_Item_2);
			String PrintPage = sel.getText(WikisObjects.Menu_Item_3);
			String MovePage = sel.getText(WikisObjects.Menu_Item_4);
			String DownloadPage = sel.getText(WikisObjects.Menu_Item_5);
			String DeletePage = sel.getText(WikisObjects.Menu_Item_6);

			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Create Child", NewChildPage.contains("Create Child"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Create Peer", NewPeerPage.contains("Create Peer"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Print Page", PrintPage.contains("Print Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Move Page", MovePage.contains("Move Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Download Page", DownloadPage.contains("Download Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Move to Trash", DeletePage.contains("Move to Trash"));
			AssertJUnit.assertTrue("FAIL: Page Actions contains more items than expected", !sel.isElementPresent(WikisObjects.Menu_Item_7));
		}
		else if (UserAccess.equals("Editor")) {
			clickAtItem(WikisObjects.Page_Actions_Button_For_Editors, "Page Actions");

			String NewChildPage = sel.getText(WikisObjects.Menu_Item_1);
			String NewPeerPage = sel.getText(WikisObjects.Menu_Item_2);
			String PrintPage = sel.getText(WikisObjects.Menu_Item_3);
			String MovePage = sel.getText(WikisObjects.Menu_Item_4);
			String DownloadPage = sel.getText(WikisObjects.Menu_Item_5);

			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Create Child", NewChildPage.contains("Create Child"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Create Peer", NewPeerPage.contains("Create Peer"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Print Page", PrintPage.contains("Print Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Move Page", MovePage.contains("Move Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Download Page", DownloadPage.contains("Download Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions contains more items than expected", !sel.isElementPresent(WikisObjects.Menu_Item_6));
		}
		else {
			clickAtItem(WikisObjects.Page_Actions_Button_For_Readers, "Page Actions");

			String PrintPage = sel.getText(WikisObjects.Menu_Item_1);
			String DownloadPage = sel.getText(WikisObjects.Menu_Item_2);

			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Print Page", PrintPage.contains("Print Page"));
			AssertJUnit.assertTrue("FAIL: Page Actions item is missing - Download Page", DownloadPage.contains("Download Page"));
		}
	}

	public void hoverMouseOver(String item1) throws Exception {

		//Hover mouse over item identified
		sel.mouseOver(item1);
		Thread.sleep(2000);
	}

	public boolean CreateWikiPage(String Wiki_Title, String Wiki_Tag_Title, String DefaultPermissions) throws Exception {

		boolean NewWiki = true;

		//Create a new Wiki having a name with less than 252 characters and set with with 'Open' membership
		clickLink(WikisObjects.My_Wikis_Tab);
		clickLink(WikisObjects.Start_New_Wiki_Button);
		//Validate 'Create a New Wiki' tab UI 
		this.ValidateCreateNewWikiUI();

		//Make radio button selections to ensure wiki is "Public"
		if (DefaultPermissions.equals("Public"))
			this.MakeWikiPublic();

		else
		//Make radio button selections to ensure wiki is "Private"
		this.MakeWikiPrivate();

		sel.type(WikisObjects.Wiki_Name_Field, Wiki_Title);
		sel.type(WikisObjects.Tags_Field, Wiki_Tag_Title);
		clickLink(WikisObjects.Save_Button);

		//Verifying that Wiki is new & that the same name doesn't already exist
		if (sel.isTextPresent("A Wiki with this title already exists, please choose another")) {
			clickLink(WikisObjects.Cancel_Link);
			NewWiki = false;
		}

		return NewWiki;
	}

	public void MakeWikiPublic() throws Exception {

		clickLink(WikisObjects.AllUsers_RadioButton);
		clickLink(WikisObjects.AllLoggedInUsers_RadioButton);
	}

	public void CheckTagsLinks() throws Exception {

		if (sel.isElementPresent(WikisObjects.Link_for_Tag_List)) clickLink(WikisObjects.Link_for_Tag_List);

		AssertJUnit.assertTrue("FAIL: Cloud link is missing", sel.isElementPresent(WikisObjects.Link_for_Tag_Cloud));
	}

	public void MakeWikiPrivate() throws Exception {

		clickLink(WikisObjects.WikiMembersOnly_RadioButton);
		clickLink(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);
	}

	public void VerifyMyWikisICanUI() throws Exception {

		AssertJUnit.assertTrue("FAIL: I'm a Reader filter is missing", sel.isElementPresent(WikisObjects.MyWikis_Reader_Filter));
		AssertJUnit.assertTrue("FAIL: I'm an Editor filter is missing", sel.isElementPresent(WikisObjects.MyWikis_Editor_Filter));
		AssertJUnit.assertTrue("FAIL: I'm an Owner filter is missing", sel.isElementPresent(WikisObjects.MyWikis_Owner_Filter));
	}

	public void checkFilterResultsUI() throws Exception {

		AssertJUnit.assertTrue("FAIL: Filter with the tag name isn't visible on tag filter page", sel.isElementPresent(WikisObjects.Filter_with_TagName));
		AssertJUnit.assertTrue("FAIL: Sort by Name Option on TagFilter Page is not Present", sel.isElementPresent(WikisObjects.Sortby_Name_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Created Option on TagFilter Page is not Present", sel.isElementPresent(WikisObjects.Sortby_Created_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Updated Option on TagFilter Page is not Present", sel.isElementPresent(WikisObjects.Sortby_Updated_Option));
		AssertJUnit
				.assertTrue("FAIL: Link toshow 25 Wikis per Page on TagFilter Page is not Present", sel.isElementPresent(WikisObjects.Link_toshow_25Wikis_perpage_TagFilterPage));
		AssertJUnit.assertTrue("FAIL: Link to show 50 Wikis per Page on TagFilter Page is not Present", sel
				.isElementPresent(WikisObjects.Link_toshow_50Wikis_perpage_TagFilterPage));
		AssertJUnit.assertTrue("FAIL: PageId showing 10 Wikis per Page on the TagFilter Page is not Present", sel
				.isElementPresent(WikisObjects.PageID_Showing_10Wikis_perpage_on_TagFilterPage));
	}

	public void ValidateNewWikiInMyWikisTab(String Wiki_Title) throws Exception {

		//Click on the My Wiki's tab
		clickLink(WikisObjects.My_Wikis_Tab);

		clickLink(WikisObjects.MyWikis_Reader_Filter);

		AssertJUnit.assertTrue("FAIL: Wiki doesn't appear in 'My Wikis'", FindWikiNameInMyWikisList(Wiki_Title));
	}

	public void CancelWikiPageCreation(String Temp_Wiki, String Temp_Tag) throws Exception {

		//Create a new Wiki having a name with less than 252 characters and set with with 'Open' membership
		clickLink(WikisObjects.Start_New_Wiki_Button);
		sel.type(WikisObjects.Wiki_Name_Field, Temp_Wiki);
		sel.type(WikisObjects.Tags_Field, Temp_Tag);
		clickLink(WikisObjects.Cancel_Link);
	}

	public void OpenPublicWikisTab() throws Exception {

		clickLink(WikisObjects.Public_Wikis_Tab);
	}

	public boolean ValidateNewWikiInPublicWikisTab(String Wiki_Title, String WikisState) throws Exception {

		boolean ItemsAvailable = true, stopSearch = false;

		String TextFromSearch = sel.getText(WikisObjects.Search_Textfield_Content);
		//Click Public Wikis tab if My Wikis or the wiki homepage is open
		if (!TextFromSearch.equals("Public Wikis")) OpenPublicWikisTab();

		//Validate that a new public wiki appears in the 'Public Wikis' tab
		if (WikisState.equals("Public")) {
			//Navigate through pages to locate the wiki title
			while (!sel.isElementPresent("link=" + Wiki_Title)) {
				if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_Active)) & (!sel.isElementPresent("link=" + Wiki_Title)))
					clickLink(WikisObjects.Top_Next_Page_Link_Active);

				else if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive)) & (!sel.isElementPresent("link=" + Wiki_Title))) break;
			}

			//Validate a new public wiki appears in the 'Public Wikis' tab
			AssertJUnit.assertTrue("FAIL: wiki doesn't appear in 'Public Wikis'", sel.isElementPresent("link=" + Wiki_Title));
		}

		//Validate that a new private wiki doesn't appear in the 'Public Wikis' tab
		else {

			//Navigate through pages to locate the wiki title
			while ((!sel.isElementPresent("link=" + Wiki_Title)) && (stopSearch == false)) {

				if (!sel.isElementPresent(WikisObjects.Public_Wikis_TopCell)) {
					AssertJUnit.assertTrue("FAIL: 'No items found' text should appear if no public wikis have been created", sel.isTextPresent("No items found"));
					ItemsAvailable = false;
					stopSearch = true;
				}

				else if ((sel.isVisible(WikisObjects.Top_Next_Page_Link_Active)) && (!sel.isElementPresent("link=" + Wiki_Title)))
					clickLink(WikisObjects.Top_Next_Page_Link_Active);

				else if ((sel.isVisible(WikisObjects.Top_Next_Page_Link_InActive)) && (!sel.isElementPresent("link=" + Wiki_Title))) {
					stopSearch = true;
				}

				else if (!sel.isElementPresent("link=" + Wiki_Title)) {
					stopSearch = true;
				}
			}

			//Validate a new private wiki doesn't appear in the 'Public Wikis' tab
			AssertJUnit.assertTrue("FAIL: wiki shouldn't appear in 'Public Wikis'", !sel.isElementPresent("link=" + Wiki_Title));
		}

		return ItemsAvailable;
	}

	public void ValidatePublicWikisUI(boolean ItemsCheck, boolean Authenticated) throws Exception {

		//Select the 'Public Wikis' tab
		clickLink(WikisObjects.Public_Wikis_Tab);

		//Call method to validate 'New to Wikis?' section
		this.ValidateNewToWikisSectionUI();

		if (Authenticated) {
			//Verify that Public Wikis title is visible when authenticated
			this.VerifyAuthenticatedPublicWikisTitle();
		}
		else
		//Verify that Public Wikis title is visible anonymously
		this.VerifyAnonymousPublicWikisTitle();

		//Validate Display icons
		this.ValidatePageDisplayViews();

		if (ItemsCheck == true) {
			//Validate Public Wikis UI & functionality
			this.ValidatePagePaginationUI();

			int PageNo = getNumberOfPagesFromPagesIndex();

			//Verify Pages Index functionality
			VerifyPagingFunctionality(PageNo);
		}

		this.ValidateSortByLinksInMainTabs();

		//Verify existence of tag search UI & removal of wiki search
		AssertJUnit.assertTrue("FAIL: Find a wiki header shouldn't be visible", !sel.isTextPresent(WikisObjects.Find_A_Wiki_Header));
		AssertJUnit.assertTrue("FAIL: Popular Tags header isn't visible", sel.isTextPresent(WikisObjects.Public_Tags_Header));
		AssertJUnit.assertTrue("FAIL: Find a wiki section shouldn't be visible", !sel.isElementPresent(WikisObjects.Find_In_Wiki_List));
		AssertJUnit.assertTrue("FAIL: Popular Tags search box isn't visible", sel.isElementPresent(WikisObjects.Tag_Search_Textfield));
		AssertJUnit.assertTrue("FAIL: Popular Tags search icon isn't visible", sel.isElementPresent(WikisObjects.Public_Tag_Search_Field_SearchIcon));
		AssertJUnit.assertTrue("FAIL: Popular Tags container isn't visible", sel.isElementPresent(WikisObjects.Tags_Container));

		//Verify some UI elements of 'Public Wikis' page
		AssertJUnit.assertTrue("FAIL: 'Start a Wiki' button is missing", sel.isElementPresent(WikisObjects.Start_New_Wiki_Button));
		String TabTitle = sel.getText("class=lotusHeader");
		AssertJUnit.assertTrue("FAIL: Title doesn't appear correctly", TabTitle.equals("Public Wikis"));
	}

	public void VerifyAuthenticatedPublicWikisTitle() throws Exception {

		//Verify that the main service page appears as expected
		String PublicWikisTitle = sel.getText(WikisObjects.Authenticated_Public_Wikis_Title);
		AssertJUnit.assertTrue("FAIL: Public Wikis header isn't visible", PublicWikisTitle.equals("Public Wikis"));
	}

	public void VerifyAnonymousPublicWikisTitle() throws Exception {

		//Verify that the main service page appears as expected
		String PublicWikisTitle = sel.getText(WikisObjects.Anonymous_Public_Wikis_Title);
		AssertJUnit.assertTrue("FAIL: Public Wikis header isn't visible", PublicWikisTitle.equals("Public Wikis"));
	}

	/** Choose a specified page in navigation pane */
	public void SelectPageinWikiNavigation(String IndexCell) throws Exception {

		//Method to select a specific page in the navigation pane
		clickLink("link=" + IndexCell);
	}

	/** Enter new page name, Save & Close */
	public void EnterNewPageNameSaveAndClose(String WikiTitle) throws Exception {

		//Enter new page title
		for (int second = 0;; second++) {
			if (second >= 60) Assert.fail("Page name textfield hasn't appeared after 60 seconds");
			try {
				//Verify that title texfield is visible
				if (sel.isElementPresent(WikisObjects.New_Page_Title_Textfield)) {
					sel.type(WikisObjects.New_Page_Title_Textfield, WikiTitle);
					Thread.sleep(3000);
					break;
				}
			} catch (Exception e) {
			}
			Thread.sleep(2000);
		}

		clickLink(WikisObjects.Save_and_Close_Link);
		Thread.sleep(5000);

		if (sel.isTextPresent(WikisObjects.Duplicate_Page_Warning)) clickLink(WikisObjects.Cancel_Link);
	}

	/** Enter new page name & Save */
	public void EnterNewPageNameAndSave(String WikiTitle) throws Exception {

		//Enter new page title
		Thread.sleep(1000);
		sel.type(WikisObjects.New_Page_Title_Textfield, WikiTitle);
		Thread.sleep(1000);
		clickLink(WikisObjects.Save_Link);
	}

	public void ValidateNewToWikisSectionUI() throws Exception {

		AssertJUnit.assertTrue("FAIL: 'New to Wikis?' section isn't visible", sel.isElementPresent(WikisObjects.Welcome_Section));
		AssertJUnit.assertTrue("FAIL: Learn more button isn't visible", sel.isElementPresent(WikisObjects.Learn_More_Button));
		AssertJUnit.assertTrue("FAIL: Watch demo graphic link isn't visible", sel.isElementPresent(WikisObjects.Watch_Demo_Button));
	}

	public void ValidateCreateNewWikiUI() throws Exception {

		//Validate 'Create a new Wiki' lightbox UI
		AssertJUnit.assertTrue("FAIL: Start a Wiki title isn't correct", sel.isTextPresent(WikisObjects.Start_A_Wiki_Title));

		AssertJUnit.assertTrue("FAIL: Name heading is missing", sel.isTextPresent("Name:"));
		AssertJUnit.assertTrue("FAIL: Wiki name field is missing", sel.isElementPresent(WikisObjects.Wiki_Name_Field));

		AssertJUnit.assertTrue("FAIL: Tags heading is missing", sel.isTextPresent("Tags:"));
		AssertJUnit.assertTrue("FAIL: Wiki name field is missing", sel.isElementPresent(WikisObjects.Start_A_Wiki_Tags_Field));

		//Verify controls to Manage Access
		this.VerifyManageAccessUI(true);

		AssertJUnit.assertTrue("FAIL: Members heading is missing", sel.isTextPresent("Members:"));
		AssertJUnit.assertTrue("FAIL: Members dropdown is missing", sel.isElementPresent(WikisObjects.Add_Members_Dropdown));
		AssertJUnit.assertTrue("FAIL: Members textfield is missing", sel.isElementPresent(WikisObjects.Members_Field));

		clickLink(WikisObjects.AddGroups_Link);

		AssertJUnit.assertTrue("FAIL: Groups heading is missing", sel.isTextPresent("Groups:"));
		AssertJUnit.assertTrue("FAIL: Groups dropdown is missing", sel.isElementPresent(WikisObjects.Start_A_Wiki_Groups_Dropdown));
		AssertJUnit.assertTrue("FAIL: Groups textfield is missing", sel.isElementPresent(WikisObjects.Groups_Field));

		AssertJUnit.assertTrue("FAIL: Description heading is missing", sel.isTextPresent("Description:"));
		AssertJUnit.assertTrue("FAIL: Description field is missing", sel.isElementPresent(WikisObjects.Description_Field));

		AssertJUnit.assertTrue("FAIL: Save button is missing", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void OpenWikiFromPublicWikisTab(String Wiki_Title) throws Exception {

		clickLink(WikisObjects.Public_Wikis_Tab);

		FindWikiNameInMyWikisList(Wiki_Title);

		clickLink("link=" + Wiki_Title);

		String PlacebarText = sel.getText(WikisObjects.Place_Bar_Title);
		AssertJUnit.assertTrue("FAIL: placebar text doesn't match wiki title", Wiki_Title.equals(PlacebarText));
	}

	public void ValidateWikiPageUI(String UserRole) throws Exception {

		String CheckTitle = "";
		boolean DraftsPresent = false;

		//Validate the wiki title is correct in the homepage
		if (sel.isTextPresent("This page has unsaved changes that were autosaved")) {
			CheckTitle = sel.getText(WikisObjects.WikiHomePageTitleFieldWithDrafts);
			DraftsPresent = true;
		}
		else CheckTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		String FullBreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		StringBuffer PageTitleInBreadcrumbs = new StringBuffer();

		int x = FullBreadcrumbText.length() - 1;

		while ((FullBreadcrumbText.charAt(x) != '>') || (x == 0)) {
			PageTitleInBreadcrumbs.append(FullBreadcrumbText.charAt(x));
			x--;
		}
		String ExtractedHomepageText = PageTitleInBreadcrumbs.reverse().substring(2).toString();

		AssertJUnit.assertTrue("FAIL: homepage title isn't correct", ExtractedHomepageText.equals(CheckTitle));

		if ((UserRole.equals(Data.Owner_Role)) || (UserRole.equals(Data.Editor_Role))) {
			//Validate the more general elements of the Wiki homepage
			AssertJUnit.assertTrue("FAIL: Navigation pane is missing", sel.isElementPresent(WikisObjects.Wiki_Nav_Table));
			AssertJUnit.assertTrue("FAIL: New Page link is missing", sel.isElementPresent(WikisObjects.Create_New_Page_Link));
			AssertJUnit.assertTrue("FAIL: Edit Button is missing", sel.isElementPresent(WikisObjects.Edit_Button));

			//Check existence of Edit button depending on whether the user is a manager or an editor
			if (UserRole.equals(Data.Owner_Role)) {
				AssertJUnit.assertTrue("FAIL: Edit Button is missing", sel.isElementPresent(WikisObjects.Edit_Button));
				AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button));
			}

			else AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors));

			//Verify tags link on homepage
			if (sel.isTextPresent(WikisObjects.NoTagsIndicator)) {
				AssertJUnit.assertTrue("FAIL: Add a Tag link isn't appearing", sel.isElementPresent(WikisObjects.Add_tags_Link));
				System.out.println("PASS: No Tags have been added to this wiki. 'Add tags' link is available.");
			}
			else if (!sel.isTextPresent(WikisObjects.NoTagsIndicator)) {
				AssertJUnit.assertTrue("FAIL: Add or Remove Tags link isn't appearing", sel.isElementPresent(WikisObjects.Add_or_RemoveTags_Link));
				System.out.println("PASS: Tags have been added to this wiki. 'Add and remove tags' link is available.");
			}
			else Assert.fail("FAIL: No 'Tags' link is appearing in the wiki homepage.");
		}

		else {
			//Validate the more general elements of the Wiki homepage as user with less than editor access
			AssertJUnit.assertTrue("FAIL: Navigation pane is missing", sel.isElementPresent(WikisObjects.Wiki_Nav_Table));
			AssertJUnit.assertTrue("FAIL: New Page link shouldn't appear for users with less than editor access", !sel.isElementPresent(WikisObjects.Create_New_Page_Link));
			AssertJUnit.assertTrue("FAIL: Edit Button shouldn't appear for users with less than editor access", !sel.isElementPresent(WikisObjects.Edit_Button));
			AssertJUnit.assertTrue("FAIL: Page Actions button should appear for users with less than editor access", sel
					.isElementPresent(WikisObjects.Page_Actions_Button_For_Readers));

			//Verify tags link isn't visible on homepage
			AssertJUnit.assertTrue("FAIL: Add tags link shouldn't appear for users with less than editor access", !sel.isElementPresent(WikisObjects.Add_tags_Link));
			AssertJUnit.assertTrue("FAIL: Add or Remove Tags link shouldn't appear for users with less than editor access", !sel
					.isElementPresent(WikisObjects.Add_or_RemoveTags_Link));
		}
	}

	public String ExtractCommunityLinkfromCommunityWikiBreadcrumbs() throws Exception {

		String FullBreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		StringBuffer CommunityLinkInBreadcrumbs = new StringBuffer();
		int y = 0;
		int x = FullBreadcrumbText.length() - 1;
		while ((FullBreadcrumbText.charAt(y) != ':') || (y == x)) {
			y++;
		}
		//We are moving the Cursor 2 spaces to go up to the start of the link
		y = y + 2;
		while ((FullBreadcrumbText.charAt(y) != '>') || (y == x)) {
			CommunityLinkInBreadcrumbs.append(FullBreadcrumbText.charAt(y));
			y++;
		}
		int z = CommunityLinkInBreadcrumbs.length();
		String ExtractedCommunityLinkText = CommunityLinkInBreadcrumbs.substring(1, z - 1).toString();
		return (ExtractedCommunityLinkText);
	}

	public String ExtractWikiLinkfromCommunityWikiBreadcrumbs() throws Exception {

		String FullBreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		int y = 0;
		int x = FullBreadcrumbText.length() - 1;

		while ((FullBreadcrumbText.charAt(y) != '>') || (y == x)) {
			y++;
		}
		//We are moving the Cursor 2 spaces to go up to the start of the Wiki link
		y = y + 2;
		StringBuffer WikiLinkInBreadcrumbs = new StringBuffer();
		while ((FullBreadcrumbText.charAt(y) != '>') || (y == x)) {
			WikiLinkInBreadcrumbs.append(FullBreadcrumbText.charAt(y));
			y++;
		}
		int z = WikiLinkInBreadcrumbs.length();
		String ExtractedWikiLinkText = WikiLinkInBreadcrumbs.substring(1, z - 1).toString();
		return (ExtractedWikiLinkText);
	}

	public void ValidateLeftSideMenuBar() throws Exception {

		//Verify that Popular Tags section appears
		AssertJUnit.assertTrue("FAIL: Tags section is missing", sel.isTextPresent("Tags"));
		AssertJUnit.assertTrue("FAIL: Tags expand/collapse icon is missing", sel.isElementPresent(WikisObjects.Tags_Expand_Collapse_Arrow));
		AssertJUnit.assertTrue("FAIL: Incorrect ID for Popular Tags expand/collapse section", sel.getText(WikisObjects.Tags_Expand_Collapse_Arrow).contains(
				"Expand and collapse Tags section"));

		//Verify that Members section appears
		AssertJUnit.assertTrue("FAIL: Members expand/collapse icon is missing", sel.isElementPresent(WikisObjects.Members_Expand_Collapse_Arrow));
		AssertJUnit.assertTrue("FAIL: Incorrect ID for Members expand/collapse section", sel.getText(WikisObjects.Members_Expand_Collapse_Arrow).contains(
				"Expand and collapse Members section"));
	}

	public boolean ValidateOfExpandCollapseOfTagsUI() throws Exception {

		boolean contentVisible = false;

		AssertJUnit.assertTrue("FAIL: Tags arrow isn't visible", sel.isElementPresent(WikisObjects.MyWikis_Tags_Collapsible_Icon));
		sel.click(WikisObjects.MyWikis_Tags_Collapsible_Icon);
		Thread.sleep(1000);

		if (!sel.isElementPresent(WikisObjects.My_Tags_Search_Section)) contentVisible = true;

		sel.click(WikisObjects.MyWikis_Tags_Collapsible_Icon);
		Thread.sleep(1000);

		return contentVisible;
	}

	public void ValidatePageFooterElements() throws Exception {

		AssertJUnit.assertTrue("FAIL: Comments link is missing", sel.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions is missing", sel.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments link is missing", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About link is missing", sel.isElementPresent(WikisObjects.About_Tab));
		//AssertJUnit.assertTrue("FAIL: Username doesn't appear in footer", sel.isElementPresent(Objects.Username_on_PageView));
		AssertJUnit.assertTrue("FAIL: Updated by field is missing", sel.isElementPresent(WikisObjects.Updated_By_Field));
		String updatedText = sel.getText(WikisObjects.Updated_By_Field);
		AssertJUnit.assertTrue("FAIL: Updated by field is incorrect", updatedText.contains("Updated"));
		AssertJUnit.assertTrue("FAIL: 'Subscribe to this page' link is missing", sel.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));
	}

	public void InitializeShowChangesChecks(String WikiTitle) throws Exception {

		String TextInEditor = "";

		//Open Versions page to get the number of page versions
		this.clickLink(WikisObjects.Versions_Tab);
		int versionValue = ValidateVersionsNumericValue();
	}

	public void CheckContentOfUpdatedField(String FullUsername) throws Exception {

		String UpdatedFieldText = sel.getText(WikisObjects.Updated_By_Field);

		AssertJUnit.assertTrue("FAIL: Some of the 'Updated by' text isn't correct", (UpdatedFieldText.substring(0, 8)).equals("Updated "));
		AssertJUnit.assertTrue("FAIL: Some of the 'Updated by' text isn't correct", UpdatedFieldText.contains(" at "));
		AssertJUnit.assertTrue("FAIL: Some of the 'Updated by' text isn't correct", UpdatedFieldText.contains(" by "));

		//String ExtractUsername = sel.getText(Objects.Username_on_PageView);
		//AssertJUnit.assertTrue("FAIL: Username text isn't correct", ExtractUsername.contains(FullUsername));
	}

	public void ValidateHomePageJavelinCard() throws Exception {

		//sel.mouseOver(Objects.Username_on_PageView);
		//Thread.sleep(3000);
		//AssertJUnit.assertTrue("FAIL: business card text doesn't appear", sel.isElementPresent("class=javlinHover"));
	}

	public void ValidateActionButtonsInPageView(String UserRole) throws Exception {

		if ((UserRole.equals("Owner")) || (UserRole.equals("Editor"))) {
			AssertJUnit.assertTrue("FAIL: Edit button doesn't appear for Owner", sel.isElementPresent(WikisObjects.Edit_Button));
			AssertJUnit.assertTrue("FAIL: Page Actions button doesn't appear for Owner", sel.isElementPresent(WikisObjects.Page_Actions_Button));
		}
		else {
			AssertJUnit.assertFalse("FAIL: Edit button shouldn't appear for user with less than Editor access", sel.isElementPresent(WikisObjects.Edit_Button));
			AssertJUnit.assertTrue("FAIL: Page Actions button should appear for user with less than Editor access", sel.isElementPresent(WikisObjects.Page_Actions_Button));
		}
	}

	public void ValidatePageBreadcrumbElements(String Wiki_Title) throws Exception {

		AssertJUnit.assertTrue("FAIL: Breadcrumbs rootpage is missing", sel.isElementPresent(WikisObjects.Breadcrumbs_Root_Page));
		AssertJUnit.assertTrue("FAIL: Some breadcrumbs text is incorrect", sel.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		String RootPage = sel.getText(WikisObjects.Breadcrumbs_Root_Page);
		String BreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		String HomePageTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		AssertJUnit.assertTrue("FAIL: Problem encountered with breadcrumb text", BreadcrumbText.contains("You are in:  " + RootPage + " >"));
		AssertJUnit.assertTrue("FAIL: Problem encountered with homepage in breadcrumb text", BreadcrumbText.contains(HomePageTitle));
		AssertJUnit.assertTrue("FAIL: Root page in breadcrumbs doesn't correspond to Wiki title", RootPage.equals(Wiki_Title));
	}

	public String ValidatePageInfoBreadcrumbElements(String Wiki_Title) throws Exception {

		AssertJUnit.assertTrue("FAIL: breadcrumbs rootpage is missing", sel.isElementPresent(WikisObjects.Breadcrumbs_Root_Page));
		AssertJUnit.assertTrue("FAIL: breadcrumbs are missing", sel.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		String RootPage = sel.getText(WikisObjects.Breadcrumbs_Root_Page);
		AssertJUnit.assertTrue("FAIL: Rootpage breadcrumbs don't match Wiki title", RootPage.equals(Wiki_Title));

		String PageTitleText = ReturnHomepageTextInBreadcrumbs();

		return PageTitleText;
	}

	public void ValidateAttachmentsBreadcrumbElements(String Wiki_Title) throws Exception {

		AssertJUnit.assertTrue("FAIL: breadcrumbs rootpage is missing", sel.isElementPresent(WikisObjects.Breadcrumbs_Root_Page));
		AssertJUnit.assertTrue("FAIL: breadcrumbs are missing", sel.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		String RootPage = sel.getText(WikisObjects.Breadcrumbs_Root_Page);

		AssertJUnit.assertTrue("FAIL: Rootpage breadcrumbs don't match Wiki title", RootPage.equals(Wiki_Title));
	}

	public void ValidatePageInfoTitleQualifiers(String PageTitle) throws Exception {

		String HomePageTitle = sel.getText(WikisObjects.WikiHomePageTitleField);
		AssertJUnit.assertTrue("FAIL: Page title breadcrumbs don't correspond to homepage title", HomePageTitle.equals(PageTitle));
	}

	public String GetPageTitle() throws Exception {

		String PageInfoTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		return PageInfoTitle;
	}

	public void ValidateAttachmentsTitleQualifiers() throws Exception {

		String AttachmentsTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		String FullBreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		StringBuffer PageTitleInBreadcrumbs = new StringBuffer();
		StringBuffer HomePageInBreadcrumbs = new StringBuffer();

		int x = FullBreadcrumbText.length() - 1;
		int count = 1;

		while ((FullBreadcrumbText.charAt(x) != '>') || (x == 0)) {
			PageTitleInBreadcrumbs.append(FullBreadcrumbText.charAt(x));
			x--;
		}

		x = x - 1;

		while ((FullBreadcrumbText.charAt(x) != '>') || (x == 0)) {
			HomePageInBreadcrumbs.append(FullBreadcrumbText.charAt(x));
			x--;
		}
		String ExtractedHomePageTitleText = HomePageInBreadcrumbs.reverse().substring(2).toString();

		AssertJUnit.assertTrue("FAIL: Attachments title isn't correct", AttachmentsTitle.equals(ExtractedHomePageTitleText + ": Page Attachments"));
	}

	public void ValidateTaggingUIAfterTagsAreAdded(String UserRole) throws Exception {

		if ((UserRole.equals("Owner")) || (UserRole.equals("Editor"))) {
			AssertJUnit.assertTrue("FAIL: Add or Remove Tags link doesn't appear for Owner or Editor", sel.isElementPresent(WikisObjects.Add_or_RemoveTags_Link));
			AssertJUnit.assertTrue("FAIL: No tags indicator shouldn't appear when tags are added", !sel.isTextPresent(WikisObjects.NoTagsIndicator));
		}
		else {
			AssertJUnit.assertTrue("FAIL: Add or Remove Tags link shouldn't appear for user with less than editor access", !sel
					.isElementPresent(WikisObjects.Add_or_RemoveTags_Link));
			AssertJUnit.assertTrue("FAIL: No tags indicator shouldn't appear when tags are added", !sel.isTextPresent(WikisObjects.NoTagsIndicator));
		}
	}

	public int CountTags() throws Exception {

		int index = 1, TagCount = 0;

		while (sel.isElementPresent("xpath=//*[contains(@id,'quickr_widget_Tagger')]/a[" + index + "]")) {
			TagCount++;
			index++;
		}
		return TagCount - 1;
	}

	public void ValidateTaggingUIBeforeTagsAreAdded(String UserRole) throws Exception {

		if ((UserRole.equals("Owner")) || (UserRole.equals("Editor"))) {
			AssertJUnit.assertTrue("FAIL: Add tags link don't appear for Owner or Editor", sel.isElementPresent(WikisObjects.Add_tags_Link));
			AssertJUnit.assertTrue("FAIL: No tags indicator doesn't appear for Owner or Editor", sel.isTextPresent(WikisObjects.NoTagsIndicator));
		}
		else {
			AssertJUnit.assertTrue("FAIL: Add tags link is appearing for anonymous user", !sel.isElementPresent(WikisObjects.Add_tags_Link));
			AssertJUnit.assertTrue("FAIL: No tags indicator doesn't appear for user with less than editor access", sel.isTextPresent(WikisObjects.NoTagsIndicator));
		}
	}

	public void VerifyOwnerOfBottomComment(String CommentOwner, int index) throws Exception {

		if ((index > 20) & (index <= 40))
			index = index - 20;

		else if ((index > 40) & (index <= 60))
			index = index - 40;

		else if (index > 60) index = index - 60;

		String UserName = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + index + "]/div[2]/div[1]/a[1]");

		AssertJUnit.assertTrue("FAIL: Last comment wasn't added by the correct user", UserName.contains(CommentOwner));
	}

	public void ValidateCommentsAreDeleted(int PreviousCommentsTotal, int NewCommentCount) throws Exception {

		//verify whether the comments page link is showing the correct number of comments
		AssertJUnit.assertTrue("FAIL: on comments being deleted", NewCommentCount == PreviousCommentsTotal);
	}

	public void ValidateCommentsEditor() throws Exception {

		//Verify that the Editor opens and that the Save Button and the Cancel Link appear
		AssertJUnit.assertTrue("FAIL: on Add a comment editor", sel.isElementPresent(WikisObjects.Add_Comment_Editor));
		AssertJUnit.assertTrue("FAIL: on Save button in comment editor", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: on Cancel button in comment editor", sel.isElementPresent(WikisObjects.Cancel_Comments_Link));
	}

	public void ValidateCommentsAddedByUsers(String Current_Contributor, String Contributor_Role, int index) throws Exception {

		if (index <= 20) {
			ValidateLinksInComments(Current_Contributor, Contributor_Role, index);
		}

		if ((index > 20) & (index <= 40)) {
			index = index - 20;

			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated2)) {
				clickLink(WikisObjects.CommentPagination_Activated2);

			}
			ValidateLinksInComments(Current_Contributor, Contributor_Role, index);
			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);
		}

		else if ((index > 40) & (index <= 60)) {
			index = index - 40;
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated3)) {
				clickLink(WikisObjects.CommentPagination_Activated3);
			}

			ValidateLinksInComments(Current_Contributor, Contributor_Role, index);
			clickLink(WikisObjects.CommentPagination_Activated2);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);

			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);
		}

		else if (index > 60) {
			index = index - 60;
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated4)) {
				clickLink(WikisObjects.CommentPagination_Activated4);
			}

			ValidateLinksInComments(Current_Contributor, Contributor_Role, index);
			clickLink(WikisObjects.CommentPagination_Activated3);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);
			clickLink(WikisObjects.CommentPagination_Activated2);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);
			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(Current_Contributor, Contributor_Role, 20);
		}
	}

	public void ValidateCommentsAddedAsUnauthenticatedUsers(int index) throws Exception {

		if (index <= 20) {
			ValidateLinksInComments(index);
		}

		if ((index > 20) & (index <= 40)) {
			index = index - 20;

			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated2)) {
				clickLink(WikisObjects.CommentPagination_Activated2);
			}
			ValidateLinksInComments(index);
			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(20);
		}

		else if ((index > 40) & (index <= 60)) {
			index = index - 40;
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated3)) {
				clickLink(WikisObjects.CommentPagination_Activated3);
			}

			ValidateLinksInComments(index);
			clickLink(WikisObjects.CommentPagination_Activated2);

			ValidateLinksInComments(20);
			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(20);
		}

		else if (index > 60) {
			index = index - 60;
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated4)) {
				sel.click(WikisObjects.CommentPagination_Activated4);
				Thread.sleep(1000);
			}

			ValidateLinksInComments(index);
			clickLink(WikisObjects.CommentPagination_Activated3);

			ValidateLinksInComments(20);
			clickLink(WikisObjects.CommentPagination_Activated2);

			ValidateLinksInComments(20);
			clickLink(WikisObjects.CommentPagination_Activated1);

			ValidateLinksInComments(20);
		}
	}

	public void ValidateLinksInComments(String Current_Contributor, String Contributor_Role, int index) throws Exception {

		for (int UserNo = index; UserNo > 0; UserNo--) {
			String UserName = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[1]/a[1]");

			if (UserName.contains("Bill User")) UserName = UserName.replace("Bill User", "buser");

			/*
			 * If current user is the owner of the comment and has Owner/Editor/Reader access to the wiki, the Edit link
			 * & Delete icon should be available for his/her comment
			 */
			if ((UserName.contains(Current_Contributor))
					& ((Contributor_Role.equals(Data.Owner_Role)) || (Contributor_Role.equals(Data.Editor_Role)) || (Contributor_Role.equals(Data.Reader_Role)))) {
				if (UserName.length() > 10) {
					if (!UserName.substring(0, 11).equals(Data.Wiki_LDAP_Reader_Username1_Groups)) {
						AssertJUnit.assertTrue("FAIL: Comments Edit link isn't available", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo
								+ "]/div[2]/div[2]/div/ul/li/a"));
						AssertJUnit.assertTrue("FAIL: Comments Delete link isn't available", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo
								+ "]/div[2]/div[2]/a/img"));

						String Link1 = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/div/ul/li/a");

						AssertJUnit.assertTrue("FAIL: Edit link isn't correct (being viewed by a manager)", Link1.equals("Edit"));
					}
				}
			}

			//If current user isn't the owner of the comment but has Owner access to the wiki, the Delete icon should be available for all comments.*/
			else if ((!UserName.contains(Current_Contributor)) & (Contributor_Role.equals(Data.Owner_Role))) {
				AssertJUnit.assertFalse("FAIL: Comments Edit link shouldn't appear if you aren't the contributor of this comment", sel
						.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/div/ul/li/a"));
				AssertJUnit.assertTrue("FAIL: Comments Delete link isn't available (being viewed by a manager)", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["
						+ UserNo + "]/div[2]/div[2]/a/img"));
			}

			//If current user isn't the owner of the comment and has Editor/Reader access to the wiki, the Edit link & Delete icon shouldn't be available for comments not owned by the contributor.*/
			else if ((!UserName.contains(Current_Contributor)) & ((Contributor_Role.equals(Data.Editor_Role)) || (Contributor_Role.equals(Data.Reader_Role)))) {
				AssertJUnit.assertFalse("FAIL: Comments Edit link shouldn't appear if you aren't the contributor of this comment", sel
						.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/div/ul/li/a"));
				AssertJUnit.assertFalse("FAIL: Comments Delete link shouldn't appear if you aren't the contributor of this comment", sel
						.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/a/img"));
			}

			//If current user is anonymous all comments should appear without Edit links & Delete icons*/
			else {
				AssertJUnit.assertFalse("FAIL: Comments Edit link shouldn't appear if you access the wiki anonymously", sel
						.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/div/ul/li/a"));
				AssertJUnit.assertFalse("FAIL: Comments Delete link shouldn't appear if you access the wiki anonymously", sel
						.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[2]/div[2]/a/img"));
			}
		}
	}

	public void ValidateLinksInComments(int index) throws Exception {

		for (int UserNo = index; UserNo > 0; UserNo--) {
			String UserName = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + UserNo + "]/div[1]/div/span/a[1]");

			AssertJUnit.assertTrue("FAIL: Edit link exists for comment (being viewed by unauthenticated user)", !sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["
					+ UserNo + "]/div[2]/div[2]/div/ul/li/a"));
			AssertJUnit.assertTrue("FAIL: Delete link exists for comment (being viewed by unauthenticated user)", !sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div["
					+ UserNo + "]/div[2]/div[2]/a/img"));
		}
		AssertJUnit.assertFalse("FAIL: 'Add a comment' link exists for comment (being viewed by unauthenticated user)", sel.isVisible(WikisObjects.Add_Comment_Link));
	}

	public void VerifyWikiVisibilityInLatestUpdates(String Link_for_Wiki_Title, String Wiki_Title_Text) throws Exception {

		//Verify that the wiki isn't visible when a user isn't logged in
		AssertJUnit.assertTrue("FAIL: Wiki shouldn't be visible when a user is anonymous", !sel.isElementPresent(Link_for_Wiki_Title));
		AssertJUnit.assertTrue("FAIL: Wiki shouldn't be visible when a user is anonymous", !sel.isTextPresent(Wiki_Title_Text));
		AssertJUnit.assertTrue("FAIL: Start a Wiki button shouldn't appear when a user is anonymous", !sel.isElementPresent(WikisObjects.Start_New_Wiki_Button));

	}

	public void ValidateNewlyDeletedComment(int OldCommentCount, int NewCommentCount) throws Exception {

		int index = 0;

		if ((NewCommentCount > 20) & (NewCommentCount <= 40))
			index = NewCommentCount - 20;

		else if ((NewCommentCount > 40) & (NewCommentCount <= 60))
			index = NewCommentCount - 40;

		else if (NewCommentCount > 60) index = NewCommentCount - 60;

		AssertJUnit.assertTrue("FAIL: Comment field has been deleted so shouldn't be visible", !sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + index
				+ "]/div[2]/div[1]/div"));
		AssertJUnit.assertTrue("FAIL: Comments should reflect that a comment has been deleted", NewCommentCount == OldCommentCount - 1);
	}

	public void ValidateNewlyCancelledDeletion(int CommentCount, int LatestCommentCount) throws Exception {

		int index = 0;

		if (LatestCommentCount <= 20)
			index = LatestCommentCount;
		else if ((LatestCommentCount > 20) & (LatestCommentCount <= 40))
			index = LatestCommentCount - 20;

		else if ((LatestCommentCount > 40) & (LatestCommentCount <= 60))
			index = LatestCommentCount - 40;

		else if (LatestCommentCount > 60) index = LatestCommentCount - 60;

		AssertJUnit.assertTrue("FAIL: Comment field is missing even though the deletion was cancelled", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + index
				+ "]"));
		AssertJUnit.assertTrue("FAIL: The comments count shouldn't have changed as no comments have been deleted", CommentCount == LatestCommentCount);
	}

	public void BackToWikiHome(String WikiTitle, String MenuItem) throws Exception {

		String BackToText = "";

		if ((MenuItem.equals("Site Map")) || (MenuItem.equals("Members and Roles"))) {
			BackToText = sel.getText(WikisObjects.Back_To_Link);
			sel.click(WikisObjects.Back_To_Link);
			Thread.sleep(1000);
		}

		else {
			BackToText = sel.getText(WikisObjects.Back_To_Link);
			sel.click(WikisObjects.Back_To_Link);
			Thread.sleep(1000);
		}

		ValidateBackToLink(WikiTitle);
	}

	public void ValidateTitleAndBreadcrumbs(String Breadcrumb_Header_Link, String Wiki_Title) {

		AssertJUnit.assertTrue("FAIL: on breadcrumb header", sel.isElementPresent(Breadcrumb_Header_Link));
		sel.isTextPresent(Wiki_Title);
	}

	public int AddAComment(String CommentToBeAdded, int CurrentCommentCount) throws Exception {

		//Add a comment to the page.
		sel.type(WikisObjects.Add_Comment_Editor, CommentToBeAdded);
		Thread.sleep(2000);
		clickLink(WikisObjects.Save_Button);

		CurrentCommentCount = CurrentCommentCount + 1;
		return CurrentCommentCount;
	}

	public void OpenCommentsEditAndValidate(int CommentsIndex, String CommentForEditing) throws Exception {

		String EditableText = "", InactiveLink = "";

		if (sel.isElementPresent(WikisObjects.CommentPagination_Deactivated)) InactiveLink = sel.getText(WikisObjects.CommentPagination_Deactivated);

		if ((CommentsIndex > 20) & (CommentsIndex <= 40)) {
			if (InactiveLink.equals("1"))
				clickLink(WikisObjects.CommentPagination_Activated2);

			else Assert.fail("FAIL: Page 1 should be active when Comments are opened");

			CommentsIndex = CommentsIndex - 20;
			EditableText = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/p");
		}

		else if ((CommentsIndex > 40) & (CommentsIndex <= 60)) {
			if (InactiveLink.equals("1"))
				clickLink(WikisObjects.CommentPagination_Activated3);

			else Assert.fail("FAIL: Page 1 should be active when Comments are opened");

			CommentsIndex = CommentsIndex - 40;
			EditableText = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/p");
		}

		else if (CommentsIndex > 60) {
			if (InactiveLink.equals("1"))
				clickLink(WikisObjects.CommentPagination_Activated4);

			else Assert.fail("FAIL: Page 1 should be active when Comments are opened");

			CommentsIndex = CommentsIndex - 60;
			EditableText = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/p");
		}
		else EditableText = sel.getText("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/p");

		//Click on the Edit Link next to the Comment added by a specific user
		AssertJUnit.assertTrue("FAIL: Edit link in comments isn't available", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex
				+ "]/div[2]/div[2]/div/ul/li/a"));
		sel.clickAt("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/div/ul/li/a", "Edit");
		Thread.sleep(2000);

		//Verify that the Editor opens and that the Save Button and the Cancel Link appear
		AssertJUnit.assertTrue("FAIL: Add comments editor is missing", sel.isElementPresent(WikisObjects.Add_Comment_Editor));
		AssertJUnit.assertTrue("FAIL: Save button in comments editor is missing", sel.isElementPresent(WikisObjects.Save_Button));

		AssertJUnit.assertTrue("FAIL: Editable comment in editor is missing", EditableText.equals(CommentForEditing));
		AssertJUnit.assertTrue("FAIL: Cancel button in comments editor is missing", sel.isElementPresent(WikisObjects.Cancel_Comments_Link));
	}

	public void EditAComment(String Comment_For_Editing) throws Exception {

		sel.type(WikisObjects.Edit_Comment_Editor, Comment_For_Editing);
		clickLink(WikisObjects.Save_Button);
	}

	public void SelectPageInNavigator(String NewPageInNavPane) throws Exception {

		sel.click(NewPageInNavPane);
		Thread.sleep(2000);
	}

	public void OpenDeleteAndValidateUI(int CommentsIndex) throws Exception {

		String EditableText = "";

		if ((CommentsIndex > 20) & (CommentsIndex <= 40)) {
			CommentsIndex = CommentsIndex - 20;
			clickLink(WikisObjects.Link_For_2nd_Page);
		}

		else if ((CommentsIndex > 40) & (CommentsIndex <= 60)) {
			CommentsIndex = CommentsIndex - 40;
			clickLink(WikisObjects.Link_For_3rd_Page);
		}

		else if (CommentsIndex > 60) {
			CommentsIndex = CommentsIndex - 60;
			clickLink(WikisObjects.Link_For_4th_Page);
		}

		//Click on the Delete Link next to the Comment
		AssertJUnit.assertTrue("FAIL: Delete link in comments is missing", sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex
				+ "]/div[2]/div[2]/a/img"));

		clickLink("xpath=id('wikiPageComments')/div[2]/div[" + CommentsIndex + "]/div[2]/div[2]/a/img");

		String warningMsg = sel.getText(WikisObjects.Warning_Msg_Text);

		AssertJUnit.assertTrue("FAIL: confirmation text in popup is missing/incorrect", warningMsg.equals("Are you sure you wish to delete this comment?"));
	}

	public void CancelWikiEditMode() throws Exception {

		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
		clickLink(WikisObjects.Cancel_Link);

		if (sel.isConfirmationPresent()) {
			Assert.assertTrue(sel.getConfirmation().matches("^Are you sure you want to abandon your changes[\\s\\S]  Press OK to continue or cancel to return to editing\\.$"));
			Thread.sleep(1000);
		}
	}

	public void ValidateWikiEditMode() throws Exception {

		//verify elements are present in the UI as per the mockup
		AssertJUnit.assertTrue("FAIL: Rich Text tab isn't visible", sel.isElementPresent(WikisObjects.Rich_Text_Tab));
		AssertJUnit.assertTrue("FAIL: HTML Source tab isn't visible", sel.isElementPresent(WikisObjects.HTML_Source_Tab));
		AssertJUnit.assertTrue("FAIL: Wiki Text tab isn't visible", sel.isElementPresent(WikisObjects.Wiki_Text_tab));
		AssertJUnit.assertTrue("FAIL: Preview tab isn't visible", sel.isElementPresent(WikisObjects.Preview_Tab));
	}

	/** Add new root page through 'New Page' link */
	public void AddANewPage() throws Exception {

		//Click 'New Page' link
		sel.click(WikisObjects.Create_New_Page_Link);
		Thread.sleep(2000);
	}

	public void EnterNewPageNameAndCancel(String WikiTitle) throws Exception {

		//Enter new page title
		sel.type(WikisObjects.New_Page_Title_Textfield, WikiTitle);

		CancelWikiEditMode();
	}

	public void AddNumerousNewPagesToTestPagination(int repetitions) throws Exception {

		for (int index = 1; index <= repetitions; index++) {
			AddANewPage();
			EnterNewPageNameSaveAndClose("Page" + index);
		}
	}

	public void ValidateNewPageInEditMode() throws Exception {

		//Verify that elements of New Page are visible in Edit Mode
		AssertJUnit.assertTrue("FAIL: New Page textfield is missing", sel.isElementPresent("class=lotusText wikiPageTitle"));
		String AddTagsText = sel.getText("class=qkrAdd");
		AssertJUnit.assertTrue("FAIL: Add Tags link is missing", AddTagsText.equals("Add tags"));

		//Validate links
		ValidateLinksInCreateWikiPageMode();

		//Validate Editor tabs
		ValidateWikiEditMode();

		//Validate tagging UI
		ValidateTaggingUIInEditMode();

		//Validate tagging UI
		ValidateTaggingUIInEditModeForNewPage();
	}

	public void ValidateTaggingUIInEditMode() throws Exception {

		//Check for Links at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: Tags heading is missing", sel.isTextPresent("Tags:"));

		if ((sel.isElementPresent(WikisObjects.Add_tags_Link)) & (sel.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links appear fine. No tags are added.");
		}
		else if ((sel.isElementPresent(WikisObjects.Add_or_RemoveTags_Link)) & (!sel.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links & tags appear fine");
		}
		else Assert.fail("FAIL: Problem with tagging links & tags");
	}

	public void ValidateTaggingUIInEditModeForNewPage() throws Exception {

		//Check for Links at the top of the Create New Page UI
		if ((sel.isElementPresent(WikisObjects.Add_tags_Link)) & (sel.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links appear fine. No tags are added.");
		}
		else if ((sel.isElementPresent(WikisObjects.Add_or_RemoveTags_Link)) & (!sel.isElementPresent(WikisObjects.NoTags))) {
			System.out.println("PASS: Tagging links & tags appear fine");
		}
		else Assert.fail("FAIL: Problem with tagging links & tags");
	}

	public void ValidateLinksInEditMode() throws Exception {

		//Check for Links at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: Save & Close link is missing", sel.isElementPresent(WikisObjects.Save_and_Close_Link));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
		AssertJUnit.assertTrue("FAIL: Save link is missing", sel.isElementPresent(WikisObjects.Save_Link));
		System.out.println("PASS: Links at top of create page appear fine");

		//Verify that Edit Page title appears in wiki edit mode
		AssertJUnit.assertTrue("FAIL: Edit Page title is missing", sel.isElementPresent(WikisObjects.Edit_Page_Title));
	}

	public void VerifyBreadcrumbsInWikiEditMode() throws Exception {

		//Check for bread crumbs for the New Page at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: breadcrumbs in edit mode are missing", sel.isElementPresent(WikisObjects.Breadcrumbs_In_Edit_Mode));
		AssertJUnit.assertTrue("FAIL: root page breadcrumb link is missing", sel.isElementPresent(WikisObjects.Breadcrumbs_In_Edit_Mode_Root));
	}

	public void VerifyBreadcrumbsInNewPageEditor(String WikiTitle) throws Exception {

		//Check for breadcrumbs for the New Page at the top of the Create Page UI
		AssertJUnit.assertTrue("FAIL: breadcrumbs parent in New Page edit mode is missing", sel.isElementPresent(WikisObjects.Breadcrumbs_In_Edit_Mode));
		String TitleInBreadcrumbs = sel.getText(WikisObjects.Breadcrumbs_In_Edit_Mode_Root);

		AssertJUnit.assertTrue("FAIL: on root page breadcrumb link", TitleInBreadcrumbs.equals(WikiTitle));
	}

	public void ValidateRootAndHomePagesMatchBreadcrumbs(String Wiki_Title) throws Exception {

		String RootPageTitle = sel.getText(WikisObjects.Breadcrumbs_Root_Page);
		String HomePageTitle = sel.getText(WikisObjects.All_Breadcrumb_Text);

		AssertJUnit.assertTrue("FAIL: on root page being correct", RootPageTitle.equals(RootPageTitle));
		AssertJUnit.assertTrue("FAIL: on root page matching wiki title", RootPageTitle.equals(Wiki_Title));
	}

	public void ValidatePageInfoHeadings(int NumberOfChildPages) throws Exception {

		//Verify all the expected headings are present
		AssertJUnit.assertTrue("FAIL: About this file heading is missing", sel.isTextPresent("About this file"));

		AssertJUnit.assertTrue("FAIL: Child pages section is missing", sel.isElementPresent(WikisObjects.ChildPages_Heading));
		String ChildPagesTitle = sel.getText(WikisObjects.ChildPages_Heading);
		AssertJUnit.assertTrue("FAIL: Child pages heading isn't correct", ChildPagesTitle.equals("Child Pages (" + NumberOfChildPages + ")"));

		AssertJUnit.assertTrue("FAIL: Hierarchy heading is missing", sel.isTextPresent("Hierarchy"));
	}

	public int VerifyAttachmentsOnAttachmentsPage() throws Exception {

		int NumberOfAttachments = 0, index = 1;
		boolean ElementExists = true;

		//Verify that the Attachments heading is showing the number of attachments
		if ((sel.isElementPresent(WikisObjects.Attachments_Tab))) {
			String AttachmentsText = sel.getText(WikisObjects.Attachments_Tab);
			try {
				NumberOfAttachments = ElementCount(AttachmentsText);
			} catch (NumberFormatException nfe) {
				AssertJUnit.assertNull("FAIL: Problem with number being parsed", nfe);
			}
		}

		return NumberOfAttachments;
	}

	public int VerifyChildPagesOnPageInfoPage(String UserRole) throws Exception {

		int NumberOfChildPages = 0;

		//Verify that the Child Pages heading is showing the number of Child Pages
		if ((sel.isElementPresent(WikisObjects.ChildPages_Heading))) {

			String ChildPagesText = sel.getText(WikisObjects.ChildPages_Heading);

			try {
				NumberOfChildPages = ElementCount(ChildPagesText);
			} catch (NumberFormatException nfe) {
				AssertJUnit.assertNull("FAIL: Problem with number being parsed", nfe);
			}
		}

		if ((UserRole.equals(Data.Owner_Role)) || (UserRole.equals(Data.Editor_Role)))
			AssertJUnit.assertTrue("FAIL: Create a new child page link doesn't appear in Page Info", sel.isElementPresent(WikisObjects.Create_newChildPage_Link));

		else AssertJUnit.assertTrue("FAIL: Create a new child page link shouldn't appear for user with less than Editor access", !sel
				.isElementPresent(WikisObjects.Create_newChildPage_Link));

		return NumberOfChildPages;
	}

	public void VerifyHierarchyOnPageInfoPage() throws Exception {

		//Verify the elements under the Hierarchy heading
		AssertJUnit.assertTrue("FAIL: Move link is missing in Hierarchy section", sel.isElementPresent(WikisObjects.MovePage_Link));
		AssertJUnit.assertTrue("FAIL: Hierarchy rootpage breadcrumb is missing", sel.isElementPresent(WikisObjects.Hierarchy_breadcrumb_RootPage));
		AssertJUnit.assertTrue("FAIL: Hierarchy homepage breadcrumb is missing", sel.isElementPresent(WikisObjects.Hierarchy_breadcrumb_HomePage));
		String HierarchyRootPage = sel.getText(WikisObjects.Hierarchy_breadcrumb_RootPage);
		String HierarchyHomePage = sel.getText(WikisObjects.Hierarchy_breadcrumb_HomePage);

		AssertJUnit.assertTrue("FAIL: Rootpage isn't the same as homepage in Hierarchy", HierarchyRootPage.equals(HierarchyHomePage));
	}

	public void VerifyRemainingAboutThisFileFields() throws Exception {

		//verify the elements under 'About This File' heading.
		AssertJUnit.assertTrue("FAIL: Updated field title isn't correct", sel.isTextPresent(WikisObjects.Updated_Field));
		AssertJUnit.assertTrue("FAIL: Added field title isn't correct", sel.isTextPresent(WikisObjects.Added_Field));
		AssertJUnit.assertTrue("FAIL: Size field title isn't correct", sel.isTextPresent(WikisObjects.Size_Field));
		AssertJUnit.assertTrue("FAIL: Page Views field title isn't correct", sel.isTextPresent(WikisObjects.PageViews_Field));
		AssertJUnit.assertTrue("FAIL: Page Viewed by field title shouldn't appear", !sel.isTextPresent(WikisObjects.PageViewedby_Field));
	}

	public void VerifyAboutThisFileUpdatedField(String username) throws Exception {

		StringBuffer NameField = new StringBuffer();

		for (int index = 0; index < username.length(); index++) {
			String UpdatedBy = sel.getText(WikisObjects.About_This_File_Updated_Field_Username);
			NameField = NameField.append(UpdatedBy.charAt(index));
		}

		AssertJUnit.assertTrue("FAIL: About This File Updated field is missing", sel.isElementPresent(WikisObjects.About_This_File_Updated_Field));
		AssertJUnit.assertTrue("FAIL: update by specified user is incorrect", username.equals(NameField.toString()));

		sel.mouseOver(WikisObjects.About_This_File_Updated_Field_Username);
		Thread.sleep(2000);
		AssertJUnit.assertTrue("FAIL: javelin text is missing", sel.isTextPresent("Click here to view business card"));
	}

	public void VerifyAboutThisFileAddedField(String username) throws Exception {

		StringBuffer NameField = new StringBuffer();

		for (int index = 0; index < username.length(); index++) {
			String AddedBy = sel.getText(WikisObjects.About_This_File_Added_Field_Username);
			NameField = NameField.append(AddedBy.charAt(index));
		}

		AssertJUnit.assertTrue("FAIL: About this file 'Added' field is missing", sel.isElementPresent(WikisObjects.About_This_File_Added_Field));
		AssertJUnit.assertTrue("FAIL: added by specified user is incorrect", username.equals(NameField.toString()));

		sel.mouseOver(WikisObjects.About_This_File_Added_Field_Username);
		Thread.sleep(2000);
		AssertJUnit.assertTrue("FAIL: javelin text is missing", sel.isTextPresent("Click here to view business card"));
	}

	public int VerifyAboutThisFileSizeForPage() throws Exception {

		String FileSizeText = sel.getText(WikisObjects.About_This_File_Size_Field);
		int PageSize = parseFirstNumber(FileSizeText);

		AssertJUnit.assertTrue("FAIL: Size field is missing", sel.isElementPresent(WikisObjects.About_This_File_Size_Field));
		System.out.println("PASS: 'Size' field indicates a page size of " + PageSize);

		return PageSize;
	}

	public int VerifyAboutThisFileSizeOverall(int PageSize) throws Exception {

		String FileSizeText = sel.getText(WikisObjects.About_This_File_Size_Field);
		int OverallSize = 0;

		AssertJUnit.assertTrue("FAIL: Size field element doesn't appear", sel.isElementPresent(WikisObjects.About_This_File_Size_Field));

		if (sel.isTextPresent("including page, attachments, and all versions")) {
			OverallSize = parseSecondNumber(FileSizeText);
			System.out.println("PASS: 'Size' field indicates an overall size of " + OverallSize);
		}
		else OverallSize = PageSize;

		return OverallSize;
	}

	public boolean VerifyBaseFileSizeForPage(int BaseSize) throws Exception {

		boolean CorrectBaseSize = false;
		/*
		 * String WhatBrowser=sel.getEval("navigator.appName");
		 * 
		 * if(WhatBrowser.equals("Netscape")){ if (BaseSize == 225) CorrectBaseSize = true; } else if
		 * (WhatBrowser.equals("Microsoft Internet Explorer")){ if (BaseSize == 202) CorrectBaseSize = true; }
		 */

		if (BaseSize == 202) CorrectBaseSize = true;

		return CorrectBaseSize;
	}

	public int VerifyAboutThisFilePageViewsField() throws Exception {

		AssertJUnit.assertTrue("FAIL: Page Views field doesn't appear in Page Info", sel.isElementPresent(WikisObjects.About_This_File_PageViews_Field));
		String PageViewsTextFormat = sel.getText(WikisObjects.About_This_File_PageViews_Field);
		int PageViews = 0, AnonymousViews = 0;

		if (PageViewsTextFormat.contains("anonymous")) {
			PageViews = parseFirstNumber(PageViewsTextFormat);
			AnonymousViews = parseSecondNumber(PageViewsTextFormat);

			System.out.println("PASS: 'Page Views' field indicates that the page has been viewed " + PageViews + " times & " + AnonymousViews + " times anonymously");
		}
		else {
			PageViews = parseViewsNumber(PageViewsTextFormat);

			System.out.println("PASS: 'Page Views' field indicates that the page has been viewed " + PageViews + " times");
		}

		return PageViews;
	}

	public void VerifyAboutThisFilePageViewedByField(String username) throws Exception {

		int index = 1;
		int NumberOfViewers = 0;
		String PageViewedByName = sel.getText("xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span[" + index + "]/a");

		AssertJUnit.assertTrue("FAIL: Page Viewed by field is missing", sel.isElementPresent(WikisObjects.About_This_File_Page_Viewed_By_Field));
		AssertJUnit.assertTrue("FAIL: First element of Page Viewed by field should be current user", PageViewedByName.contains(username));

		do {
			AssertJUnit.assertTrue("FAIL: Page Viewed by username is missing", sel.isElementPresent("xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span["
					+ index + "]/a"));
			AssertJUnit.assertTrue("FAIL: bullet point missing beside Page Viewed by username", sel
					.isElementPresent("xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span[" + index + "]/img"));

			index++;
			NumberOfViewers++;

		} while (sel.isElementPresent("xpath=//*[contains(@id,'quickr_widget_DownloadInfoStream')]/div[1]/span[" + index + "]/a"));

		System.out.println("PASS: Page has been viewed by " + NumberOfViewers + " people");
	}

	public void OpenTaggingEditor() throws Exception {

		//Verify whether the 'Add tags' link is visible
		if (sel.isElementPresent(WikisObjects.Add_tags_Link))
			this.clickLink(WikisObjects.Add_tags_Link);

		else this.clickLink(WikisObjects.Add_or_RemoveTags_Link);
	}

	public void ValidateTaggingEditor() throws Exception {

		//verify the all the elements are present on the Tagging Editor.
		AssertJUnit.assertTrue("FAIL: Tag editor textfield is missing", sel.isElementPresent(WikisObjects.TagEditorTextField));
		AssertJUnit.assertTrue("FAIL: Tag editor Save button is missing", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Tag editor Cancel button is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void ValidateXinEditor(int tagCount) throws Exception {

		int tagElements = tagCount * 2, index = 1, XCount = 0;

		//Verify that added tags have 'x' next to them
		while (index <= tagElements) {
			index++;
			AssertJUnit.assertTrue("FAIL: 'x' is missing from a tag", sel.isElementPresent("xpath=//*[contains(@id,'quickr_widget_Tagger')]/a[" + index + "]/img"));
			index++;
			XCount++;
		}

		System.out.println(tagCount);
		System.out.println(XCount);

		AssertJUnit.assertTrue("FAIL: 'x' doesn't appear next to all tags", tagCount == XCount);
	}

	public void AddATag(String New_Tag) throws Exception {

		sel.type(WikisObjects.TagEditorTextFieldInput, New_Tag);
		clickLink(WikisObjects.Save_Button);
	}

	public void VerifyCommentIsAdded(String Added_Comment) throws Exception {

		AssertJUnit.assertTrue("FAIL: Comment hasn't been added correctly", sel.isTextPresent(Added_Comment));
	}

	public void ActivateTagDeletionPopup(String Delete_Tag_Title) throws Exception {

		//Click on the Delete icon next to the tag added
		AssertJUnit.assertTrue("FAIL: Delete icon next to tag is missing", sel.isElementPresent("css=img[title='Remove tag " + Delete_Tag_Title + "']"));
		clickLink("css=img[title='Remove tag " + Delete_Tag_Title + "']");

		//Validate popup dialog
		AssertJUnit.assertTrue("FAIL: Remove Tag confirmation popup text is incorrect", sel.isTextPresent("Are you sure you want to remove the tag " + Delete_Tag_Title));
		String Remove_Tag_Title = sel.getText(WikisObjects.Lightbox_Header);

		AssertJUnit.assertTrue("FAIL: Remove Tag title is incorrect", Remove_Tag_Title.contains("Confirm"));
		AssertJUnit.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.OK_Button_In_Popup));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void RemoveRemainingTags(int NumberOfTags) throws Exception {

		int xIndex, yIndex;

		while (NumberOfTags > 0) {

			if (NumberOfTags == 1) {
				xIndex = 2;
				yIndex = NumberOfTags;
			}
			else {
				xIndex = NumberOfTags * 2;
				yIndex = (NumberOfTags * 2) - 1;
			}

			String TagTitle = sel.getText("xpath=//*[contains(@id,'quickr_widget_Tagger')]/a[" + yIndex + "]");

			//Click on the Delete icon next to the tag added
			AssertJUnit.assertTrue("FAIL: Delete icon next to tag is missing", sel.isElementPresent("xpath=//*[contains(@id,'quickr_widget_Tagger')]/a[" + xIndex + "]/img"));
			sel.click("xpath=//*[contains(@id,'quickr_widget_Tagger')]/a[" + xIndex + "]/img");
			Thread.sleep(1000);

			//Validate popup dialog
			ValidateTagDeletionPopupForRemainingTags(NumberOfTags, TagTitle);

			clickLink(WikisObjects.OK_Button_In_Popup);

			NumberOfTags--;
		}
	}

	public void ValidateTagDeletionPopupForRemainingTags(int NumberOfTags, String Delete_Tag_Title) throws Exception {

		String TagLowercaseText = Delete_Tag_Title.toLowerCase();

		//Validate popup dialog
		AssertJUnit.assertTrue("FAIL: Remove Tag confirmation popup text is incorrect", sel.isTextPresent("Are you sure you want to remove the tag " + TagLowercaseText));
		String Remove_Tag_Header = sel.getText(WikisObjects.Lightbox_Header);

		AssertJUnit.assertTrue("FAIL: Remove Tag title is incorrect", Remove_Tag_Header.contains("Remove tag"));
		AssertJUnit.assertTrue("FAIL: Remove button is missing", sel.isElementPresent(WikisObjects.OK_Button_In_Popup));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void VerifyTagIsDeleted(String Deleted_Tag_Title) throws Exception {

		AssertJUnit.assertTrue("FAIL: Tag is still visible, it should have been removed", !sel.isTextPresent(Deleted_Tag_Title));
	}

	public void VerifyTagIsNotDeleted(String Deleted_Tag_Title) throws Exception {

		AssertJUnit.assertTrue("FAIL: tag is no longer visible, it hasn't been removed", sel.isTextPresent(Deleted_Tag_Title));
	}

	public void CancelFromTaggingEditor() throws Exception {

		if ((sel.isElementPresent(WikisObjects.Cancel_Link))) {
			sel.click(WikisObjects.Cancel_Link);
			Thread.sleep(2000);
		}
	}

	public void VerifyAllTagsRemoved() throws Exception {

		//Add code to verify that 'None' is displayed also
		AssertJUnit.assertTrue("FAIL: Add tags link is missing", sel.isElementPresent(WikisObjects.Add_tags_Link));
		System.out.println("PASS: All tags have been removed");
	}

	public void ValidateDraftWarningMessageClearedFromHomepage() throws Exception {

		AssertJUnit.assertTrue("FAIL: Draft warning message shouldn't be visible", !sel.isTextPresent("This page has unsaved changes that were autosaved today at "));
		AssertJUnit.assertTrue("FAIL: Edit link shouldn't be visible", !sel.isElementPresent(WikisObjects.Drafts_Homepage_Edit_Link));
		AssertJUnit.assertTrue("FAIL: Discard link shouldn't be visible", !sel.isElementPresent(WikisObjects.Drafts_Homepage_Discard_Link));
	}

	public void ValidateDraftWarningMessageCleared(String WikiTitle) throws Exception {

		if (!sel.isElementPresent("xpath=id('lotusContent')/div[1]/div[2]/span/span[1]")) {
			AssertJUnit.assertTrue("FAIL: Draft warning message shouldn't be visible", !sel.isTextPresent("You have unsaved changes to the following pages:"));
			AssertJUnit.assertTrue("FAIL: Draft text shouldn't be visible", !sel.isElementPresent(WikisObjects.Draft_Text));
			AssertJUnit.assertTrue("FAIL: Edit link shouldn't be visible", !sel.isElementPresent(WikisObjects.Drafts_Edit_Link));
			AssertJUnit.assertTrue("FAIL: Discard link shouldn't be visible", !sel.isElementPresent(WikisObjects.Drafts_Discard_Link));
		}
		else {
			String TitleInDraftWarning = "";
			int index = 1;

			if (sel.isElementPresent("xpath=id('lotusContent')/div[1]/div[" + index + "]/span/span[2]")) {
				TitleInDraftWarning = sel.getText("xpath=id('lotusContent')/div[1]/div[" + index + "]/span/span[2]");
				AssertJUnit.assertTrue("FAIL: " + WikiTitle + " draft hasn't been discarded properly", TitleInDraftWarning.equals(WikiTitle));
				index++;
			}
		}
	}

	public void ValidateDiscardWarningMessage() throws Exception {

		AssertJUnit.assertTrue("FAIL: Discard lightbox heading is incorrect", sel.isTextPresent("Confirm"));
		AssertJUnit.assertTrue("FAIL: Discard lightbox text content is incorrect", sel.isTextPresent("Are you sure you wish to discard these unsaved changes?"));
		AssertJUnit.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void ValidateAttachmentDeletionWarningMessage(String fileNameForDeletion) throws Exception {

		AssertJUnit.assertTrue("FAIL: Attachment deletion lightbox heading is incorrect", sel.isTextPresent("Confirm"));
		AssertJUnit.assertTrue("FAIL: Attachment deletion lightbox text content is incorrect", sel.isTextPresent("Are you sure you wish to delete this file? "
				+ fileNameForDeletion));
		AssertJUnit.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void ValidateDraftWarningMessage(String WikiTitle) throws Exception {

		AssertJUnit.assertTrue("FAIL: Draft warning message isn't visible", sel.isTextPresent("You have unsaved changes to the following pages:"));
		String DraftWarningText = sel.getText(WikisObjects.Draft_Text);

		AssertJUnit.assertTrue("FAIL: Draft warning text isn't correct", DraftWarningText.contains("Welcome page in the " + WikiTitle + " wiki autosaved today at "));

		String EditLink = sel.getText(WikisObjects.Drafts_Edit_Link);
		String DiscardLink = sel.getText(WikisObjects.Drafts_Discard_Link);
		AssertJUnit.assertTrue("FAIL: Edit link isn't visible", EditLink.equals("Edit"));
		AssertJUnit.assertTrue("FAIL: Discard link isn't visible", DiscardLink.equals("Discard"));
	}

	public void ValidateDraftWarningMessageInHomepage() throws Exception {

		AssertJUnit.assertTrue("FAIL: Draft warning message isn't visible", sel.isTextPresent("This page has unsaved changes that were autosaved today at "));
		String EditLink = sel.getText(WikisObjects.Drafts_Homepage_Edit_Link);
		String DiscardLink = sel.getText(WikisObjects.Drafts_Homepage_Discard_Link);
		AssertJUnit.assertTrue("FAIL: Edit link isn't visible", EditLink.equals("Edit"));
		AssertJUnit.assertTrue("FAIL: Discard link isn't visible", DiscardLink.equals("Discard"));
	}

	public void ValidateAutosaveMessage() throws Exception {

		AssertJUnit.assertTrue("FAIL: Autosave message isn't visible", sel.isElementPresent(WikisObjects.Autosave_Indicator));
		String AutosaveText = sel.getText(WikisObjects.Autosave_Indicator);
		AssertJUnit.assertTrue("FAIL: Autosave message isn't correct", AutosaveText.contains("Autosaved at "));
	}

	public void SaveAndCloseWithDraftOpen() throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor Save & Close link is missing", sel.isElementPresent(WikisObjects.FckEditor_Save_And_Close));
		sel.click(WikisObjects.FckEditor_Save_And_Close);
		Thread.sleep(2000);
	}

	public void CancelWithDraftOpen() throws Exception {

		AssertJUnit.assertTrue("FAIL: on FCKEditor Cancel link", sel.isElementPresent(WikisObjects.Cancel_Link));
		sel.click(WikisObjects.Cancel_Link);
		Thread.sleep(2000);
	}

	public void ClickDraftDiscard() throws Exception {

		sel.clickAt(WikisObjects.Drafts_Discard_Link, WikisObjects.Drafts_Discard_Text);
		Thread.sleep(2000);
	}

	public void ClickHomepageDraftDiscard() throws Exception {

		sel.clickAt(WikisObjects.Drafts_Homepage_Discard_Link, WikisObjects.Drafts_Discard_Text);
		Thread.sleep(2000);
	}

	public void AcceptDraftDiscard() throws Exception {

		sel.click(WikisObjects.Discard_Button);
		Thread.sleep(2000);
	}

	public void CancelDraftDiscard() throws Exception {

		sel.focus(WikisObjects.Cancel_Link);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}

	public void ClickDraftEdit() throws Exception {

		sel.click(WikisObjects.Drafts_Edit_Link);
		Thread.sleep(2000);
	}

	public void ClickDraftEditInHomepage() throws Exception {

		sel.click(WikisObjects.Drafts_Homepage_Edit_Link);
		Thread.sleep(2000);
	}

	public int ElementCountForOverallFileSize(String ToBeParsed) throws Exception {

		//This method is used to count the number of elements in software features (e.g. Child Pages, Attachments & Comments)
		String TextToBeParsed = ToBeParsed;
		StringBuffer ParsedDigitInTextFormat = new StringBuffer();

		for (int index = 0; index < TextToBeParsed.length() - 1; index++) {
			while (TextToBeParsed.charAt(index) != ' ') {
				ParsedDigitInTextFormat.append(TextToBeParsed.charAt(index));
				index++;
			}
		}

		int ParsedDigit = Integer.parseInt(ParsedDigitInTextFormat.toString());

		return ParsedDigit;
	}

	public int ElementCountForContentFileSize(String ToBeParsed) throws Exception {

		//This method is used to count the number of elements in software features (e.g. Child Pages, Attachments & Comments)
		String TextToBeParsed = ToBeParsed;
		StringBuffer ParsedDigitInTextFormat = new StringBuffer();

		for (int index = 0; index < TextToBeParsed.length() - 1; index++) {
			while (TextToBeParsed.charAt(index) != ' ') {
				ParsedDigitInTextFormat.append(TextToBeParsed.charAt(index));
				index++;
			}
		}

		int ParsedDigit = Integer.parseInt(ParsedDigitInTextFormat.toString());

		return ParsedDigit;
	}

	public void CreateNewPageForWiki(String TitleOfPageForExistingWiki) throws Exception {

		sel.click(WikisObjects.Create_Wiki_Navigation);
		sel.type(WikisObjects.Editor_Title_Field, TitleOfPageForExistingWiki);
		sel.click(WikisObjects.New_Save_and_Close_Link);
		Thread.sleep(2000);
	}

	public void ToggleBetweenNewPageAndRoot() throws Exception {

		String RootText = sel.getText(WikisObjects.Breadcrumbs_Root_Page);
		//Check breadcrumbs at the top of the homepage
		this.ValidatePageBreadcrumbElements(RootText);

		String HomepageText = this.ReturnHomepageTextInBreadcrumbs();
		String HomeTitleText = sel.getText(WikisObjects.WikiHomePageTitleField);

		AssertJUnit.assertTrue("FAIL: Homepage text in breadcrumbs doesn't correspond to page title", HomepageText.equals(HomeTitleText));

		sel.click(WikisObjects.Breadcrumbs_Root_Page);
		Thread.sleep(2000);

		String RootpageText = this.ReturnHomepageTextInBreadcrumbs();
		String RootTitleText = sel.getText(WikisObjects.WikiHomePageTitleField);
		AssertJUnit.assertTrue("FAIL: Rootpage text in breadcrumbs doesn't correspond to page title", RootpageText.equals(RootTitleText));
	}

	public String ReturnHomepageTextInBreadcrumbs() throws Exception {

		String HomepageText = "";
		String FullBreadcrumbText = sel.getText(WikisObjects.All_Breadcrumb_Text);
		StringBuffer PageTitleInBreadcrumbs = new StringBuffer();

		int x = FullBreadcrumbText.length() - 1;

		while ((FullBreadcrumbText.charAt(x) != '>') || (x == 0)) {
			PageTitleInBreadcrumbs.append(FullBreadcrumbText.charAt(x));
			x--;
		}
		HomepageText = PageTitleInBreadcrumbs.reverse().substring(2).toString();

		return HomepageText;
	}

	public void CompareFirstAndLastCommentsTimeStamps(String firstxpath, String lastxpath) throws Exception {

		//first timestamp
		sel.getText(firstxpath);

		//last timestamp
		sel.getText(lastxpath);
	}

	public boolean ValidateUIForCommentsGreaterThan20() throws Exception {

		boolean PaginationExists = false;
		String InactiveLink = "";
		if (sel.isElementPresent(WikisObjects.CommentPagination_Deactivated)) {
			InactiveLink = sel.getText(WikisObjects.CommentPagination_Deactivated);
		}

		if ((InactiveLink.equals("2")) || (sel.isElementPresent(WikisObjects.CommentPagination_Activated2))) {
			PaginationExists = true;
		}
		return PaginationExists;
	}

	public boolean ValidateUIForCommentsGreaterThan40() throws Exception {

		boolean PaginationExists = false;
		String InactiveLink = "";
		if (sel.isElementPresent(WikisObjects.CommentPagination_Deactivated)) {
			InactiveLink = sel.getText(WikisObjects.CommentPagination_Deactivated);
		}
		if ((InactiveLink.equals("3")) || (sel.isElementPresent(WikisObjects.CommentPagination_Activated3))) {
			PaginationExists = true;
		}
		return PaginationExists;
	}

	public int CountNumberOfCommentsPerPage() throws Exception {

		int DivCount = 0;

		for (int DivIndex = 1; DivIndex <= 20; DivIndex++) {
			if (sel.isElementPresent("xpath=id('wikiPageComments')/div[2]/div[" + DivIndex + "]/div[2]")) {
				DivCount++;
			}
			else break;
		}
		return DivCount;
	}

	public int countNumberOfTestRolesWikis(String wikiSubText) throws Exception {

		int DivCount = 0;

		for (int DivIndex = 1; DivIndex <= 50; DivIndex++) {
			if (sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + DivIndex + "]/td[2]/h4/a")) {
				String wikiLink = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + DivIndex + "]/td[2]/h4/a");
				if ((!wikiLink.equals("")) && (wikiLink.contains(wikiSubText))) {
					DivCount++;
				}
				DivIndex++;
			}
			else break;
		}
		return DivCount;
	}

	public int ValidateCommentsMatchNumberInCommentsLink(int NumberOfCommentsShownInLink) throws Exception {

		int NumberOfComments = 0;

		if (NumberOfCommentsShownInLink <= 20) {
			NumberOfComments = CountNumberOfCommentsPerPage();
		}
		else if ((NumberOfCommentsShownInLink > 20) & (NumberOfCommentsShownInLink <= 40)) {
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated2)) {
				sel.focus(WikisObjects.CommentPagination_Activated2);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(2000);
			}
			NumberOfComments = ((CountNumberOfCommentsPerPage()) + 20);
		}
		else if ((NumberOfCommentsShownInLink > 40) & (NumberOfCommentsShownInLink <= 60)) {
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated3)) {
				sel.focus(WikisObjects.CommentPagination_Activated3);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(2000);
			}
			NumberOfComments = ((CountNumberOfCommentsPerPage()) + 40);
		}
		else if ((NumberOfCommentsShownInLink > 60) & (NumberOfCommentsShownInLink <= 80)) {
			if (sel.isElementPresent(WikisObjects.CommentPagination_Activated4)) {
				sel.focus(WikisObjects.CommentPagination_Activated4);
				sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
				Thread.sleep(2000);
			}
			NumberOfComments = ((CountNumberOfCommentsPerPage()) + 60);
		}
		else System.out.println("More than 80 added comments haven't been accounted for in automated testcases");

		AssertJUnit.assertTrue("FAIL: " + NumberOfComments + " comments have not been added to the database", NumberOfCommentsShownInLink == NumberOfComments);

		return NumberOfComments;
	}

	public void NavigateToFirstPageOfComments() throws Exception {

		if (sel.isElementPresent(WikisObjects.CommentPagination_Activated1)) clickLink(WikisObjects.CommentPagination_Activated1);
	}

	public void CheckPaginationFunctions() throws Exception {

		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.CommentPagination_Deactivated));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.CommentPagination_Activated2));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isVisible(WikisObjects.Top_Previous_Page_Link_InActive));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.Top_Next_Page_Link_Active));

		System.out.println("PASS: All pagination controls appear fine on first comments page");

		clickLink(WikisObjects.CommentPagination_Activated2);

		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.CommentPagination_Activated1));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.CommentPagination_Deactivated));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.Top_Previous_Page_Link_Active));
		AssertJUnit.assertTrue("FAIL: comments page pagination", sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive));

		System.out.println("PASS: All pagination controls appear fine on subsequent pages");
	}

	public void ValidateCommentsPagination(int AllComments) throws Exception {

		//Verify that pagination exists when there are more than 20 comments added
		if ((AllComments <= 20) & (!ValidateUIForCommentsGreaterThan20())) {
			System.out.println("PASS: Pagination is not required for " + AllComments + " comments");
		}
		else if ((AllComments > 20) & (ValidateUIForCommentsGreaterThan20())) {
			System.out.println("PASS: Pagination exists as there are " + AllComments + " comments");
			this.NavigateToFirstPageOfComments();
			this.CheckPaginationFunctions();
		}
		else Assert.fail("FAIL: Problem with pagination controls");
	}

	public void VerifyUtilityIcons() throws Exception {

		//Check for icons at the top of the the Create Page UI
		AssertJUnit.assertTrue("FAIL: on Download icon", sel.isElementPresent(WikisObjects.DownLoad_Icon));
		AssertJUnit.assertTrue("FAIL: on Print icon", sel.isElementPresent(WikisObjects.Print_Icon));
		AssertJUnit.assertTrue("FAIL: on RSS icon", sel.isElementPresent(WikisObjects.RSS_Icon));

		System.out.println("PASS: Icons appear fine in page header");
	}

	public void RemoveContentFromPageForSizeCheck() throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		clickLink(WikisObjects.Wiki_Text_tab);

		sel.type(WikisObjects.CKEditor_WikiTextTab, "");
		sel.selectFrame("relative=top");
	}

	public int AddContentToPageForSizeCheck() throws Exception {

		AssertJUnit.assertTrue("FAIL: problem with CKEditor", sel.isElementPresent(WikisObjects.WikiText_Editor));
		AddContentintheEditorforanExistingPage("1234567891");

		return 10;
	}

	public void VerifyAddedContentInNewPage(String SpecificContent) throws Exception {

		String NewContent = sel.getText(WikisObjects.Text_Content_In_Homepage);
		AssertJUnit.assertTrue("FAIL: Newly added content isn't correct", NewContent.equals(SpecificContent));
	}

	public void ClickAddAttachmentLinkInAttachmentsPage() throws Exception {

		clickLink(WikisObjects.AddanAttachment_Link);
	}

	public void AddAttachmentThroughAttachmentsDialog(String filename) throws Exception {

		sel.focus("css=input[id='file']");
		sel.type("css=input[id='file']", filename);
		//sel.typeKeys("css=input[id='file']", filename);
		clickLink("link=Cancel");

		//if (sel.isTextPresent("Upload Attachment"))
		//clickLink(Objects.Cancel_Link);		
	}

	public String DeleteAttachment() throws Exception {

		AssertJUnit.assertTrue("FAIL: Attachments Delete link is missing", sel.isElementPresent(WikisObjects.Delete_Link));
		String TitleChange = sel.getText("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr/td[1]/a");

		sel.click(WikisObjects.Delete_Link);
		Thread.sleep(2000);

		//Check warning message
		ValidateAttachmentDeletionWarningMessage(TitleChange);

		//Delete file attached
		clickLink(WikisObjects.OK_Button);

		return TitleChange;
	}

	public void ValidateAttachmentDeleting(int InitialAttachmentsCount, int CurrentAttachmentsCount, String Filename) throws Exception {

		AssertJUnit.assertTrue("FAIL: File hasn't been deleted", !sel.isTextPresent(Filename));
		AssertJUnit.assertTrue("FAIL: Latest attachments count isn't correct", CurrentAttachmentsCount == InitialAttachmentsCount - 1);
		if (CurrentAttachmentsCount == 0) {
			String NoAttachmentText = sel.getText("xpath=id('wikiPageAttachments')/div[2]/div");
			AssertJUnit.assertTrue("FAIL: No attachment text isn't visible", NoAttachmentText.equals("No attachments exist"));
		}
	}

	public void OpenEditAttachment() throws Exception {

		AssertJUnit.assertTrue("FAIL: Attachments Edit link is missing", sel.isElementPresent(WikisObjects.Edit_Link));
		sel.click(WikisObjects.Edit_Link);
		Thread.sleep(2000);
	}

	public void ValidateEditAttachmentPopup() throws Exception {

		AssertJUnit.assertTrue("FAIL: Edit Attachments title is missing", sel.isTextPresent("Edit Attachment"));
		AssertJUnit.assertTrue("FAIL: Edit Attachments File name label is missing", sel.isTextPresent("*File name:"));
		AssertJUnit.assertTrue("FAIL: Edit Attachments Description label is missing", sel.isTextPresent("Description:"));
		AssertJUnit.assertTrue("FAIL: Edit Attachments Replace Contents label is missing", sel.isTextPresent("Replace contents:"));

		AssertJUnit.assertTrue("FAIL: Edit Attachments File name textfield is missing", sel.isElementPresent(WikisObjects.Filename_Textfield));
		AssertJUnit.assertTrue("FAIL: Edit Attachments Description textfield is missing", sel.isElementPresent(WikisObjects.Description_Textfield));
		AssertJUnit.assertTrue("FAIL: Edit Attachments Replace Contents textfield is missing", sel.isElementPresent(WikisObjects.Replace_Contents_Textfield));

		AssertJUnit.assertTrue("FAIL: Edit Attachments OK button is missing", sel.isElementPresent(WikisObjects.OK_Button));
		AssertJUnit.assertTrue("FAIL: Edit Attachments Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void EditAttachmentFilenameAndDescription(String NewTitle, String NewDescription) throws Exception {

		sel.type(WikisObjects.Filename_Textfield, NewTitle);
		sel.type(WikisObjects.Description_Textfield, NewDescription);
		sel.click(WikisObjects.OK_Button);
		Thread.sleep(2000);
	}

	public void ValidateAttachmentEditing(String NewTitle, String NewDescription) throws Exception {

		String TitleChange = sel.getText("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr/td[1]/a");
		String DescriptionChange = sel.getText("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr/td[6]");

		AssertJUnit.assertTrue("FAIL: Title hasn't been edited", TitleChange.equals(NewTitle));
		AssertJUnit.assertTrue("FAIL: Description hasn't been edited", DescriptionChange.equals(NewDescription));
	}

	public void CancelAttachmentThroughAttachmentsPage() throws Exception {

		sel.click(WikisObjects.Cancel_Link);
		Thread.sleep(2000);
	}

	public void ReplaceAttachmentContents(String Filename) throws Exception {

		sel.type("file", Filename);
		sel.click(WikisObjects.OK_Button);
		Thread.sleep(2000);
	}

	public void NavigateBreadcrumbsToHomepage() throws Exception {

		if (sel.isElementPresent(WikisObjects.Breadcrumbs_Root_Page)) {
			sel.click(WikisObjects.Breadcrumbs_Root_Page);
			Thread.sleep(2000);
		}
	}

	public void VerifyIfAttachmentIsAddedToPageInfoPage(int InitialAttachCount, int CurrentAttachCount) throws Exception {

		AssertJUnit.assertTrue("FAIL: on Attachments visible in Page Info", sel.isElementPresent(WikisObjects.Wiki_Page_Attachments));

		//Verify the elements under the Attachments heading
		AssertJUnit.assertTrue("FAIL: on Add an Attachment link in Page Info", sel.isElementPresent(WikisObjects.AddanAttachment_Link));
		AssertJUnit.assertTrue("FAIL: on whether attachment heading has been incremented", CurrentAttachCount == InitialAttachCount + 1);

		for (int index = 1; index <= CurrentAttachCount; index++) {
			AssertJUnit.assertTrue("FAIL: on attachment content1", sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[1]"));
			AssertJUnit.assertTrue("FAIL: on attachment content2", sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[4]/a"));
			AssertJUnit.assertTrue("FAIL: on attachment content3", sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[5]/a"));
		}

	}

	public void VerifyChangeInPageSizeValue(int PreviousPageSize, int CurrentPageSize, int OverallSize, int NewOverallSize, int BytesAdded) throws Exception {

		int markerBytes = 4;

		AssertJUnit.assertTrue("FAIL: 'Size' field hasn't increased", CurrentPageSize > PreviousPageSize);
		AssertJUnit.assertTrue("FAIL: Pagesize value hasn't increased by the correct number of bytes", CurrentPageSize == (PreviousPageSize + BytesAdded + markerBytes));
	}

	public void VerifyChildPagesAreVisible(int ChildPagesCount, String UserRole) throws Exception {

		//Verify the elements under the Child Pages heading
		int ChildrenAdded = 0, index = 1;
		boolean ElementExists = true;
		String ValidXPath = "";

		if (ChildPagesCount == 0) {
			if ((UserRole.equals(Data.Owner_Role)) || (UserRole.equals(Data.Editor_Role)))
				AssertJUnit.assertTrue("FAIL: on Create a new child page link", sel.isElementPresent(WikisObjects.Create_newChildPage_Link));
			else System.out.println("No 'Create a new child page' link expected for users with less than editor access");
		}

		else {
			while (ElementExists) {
				if (sel.isElementPresent("xpath=id('childList')/div[" + index + "]/a")) {

					ValidXPath = sel.getText("xpath=id('childList')/div[" + index + "]/a");

					if (ValidXPath.equals("Create new child page"))
						break;
					else {
						index++;
						ChildrenAdded++;
					}

				}
				else ElementExists = false;
			}

			AssertJUnit.assertTrue("FAIL: Child pages heading doesn't reflect number of child pages", ChildrenAdded == ChildPagesCount);
		}
	}

	public void VerifyAttachmentsUI(int AttachmentsCount, String UserRole) throws Exception {

		int AttachmentsAdded = 0, index = 1;
		boolean ElementExists = true;
		String HeadingText = sel.getText(WikisObjects.Attachment_Heading);

		if ((UserRole.equals(Data.Owner_Role)) || (UserRole.equals(Data.Editor_Role))) {
			//Verify the 'Add an attachment' link under the Attachments heading
			AssertJUnit.assertTrue("FAIL: Add an Attachment link is missing", sel.isElementPresent(WikisObjects.AddanAttachment_Link));
		}
		else {
			//Verify the 'Add an attachment' link under the Attachments heading
			AssertJUnit
					.assertTrue("FAIL: Add an Attachment link shouldn't appear for users with less than Editor access", !sel.isElementPresent(WikisObjects.AddanAttachment_Link));
		}
		//Verify the 'Subscribe to these attachments' link under the Attachments heading
		AssertJUnit.assertTrue("FAIL: Subscribe to these attachments link is missing", sel.isElementPresent(WikisObjects.Subscribe_To_These_Attachments_Link));

		if (AttachmentsCount == 0) {
			String NoAttachmentText = sel.getText(WikisObjects.No_Attachments_Text);
			AssertJUnit.assertTrue("FAIL: No attachment text isn't correct", NoAttachmentText.equals("No attachments exist"));
			AssertJUnit.assertTrue("FAIL: Attachments heading isn't correct", HeadingText.equals("Attachments (" + AttachmentsCount + ")"));
		}

		else {
			while (ElementExists) {
				if (AttachmentsCount >= 11) {
					AssertJUnit.assertTrue("FAIL: Second page Next link is missing", sel.isElementPresent(WikisObjects.Top_Next_Page_Link_Active));
					this.clickLink(WikisObjects.Top_Next_Page_Link_Active);
				}

				if (sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[1]/a")) {
					AssertJUnit.assertTrue("FAIL: Link to attachment is missing", sel
							.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[1]"));
					AssertJUnit.assertTrue("FAIL: Delete link is missing", sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[4]/a"));
					AssertJUnit.assertTrue("FAIL: Edit link is missing", sel.isElementPresent("xpath=id('wikiPageAttachments')/div[2]/table/tbody/tr[" + index + "]/td[5]/a"));
					index++;
					AttachmentsAdded++;
				}
				else ElementExists = false;
			}
			//Verify that Attachment heading contains the correct value
			AssertJUnit.assertTrue("FAIL: Attachments heading isn't correct", HeadingText.equals("Attachments (" + AttachmentsCount + ")"));
		}
		//Verify the Attachments attachment correspond to the number in the heading
		AssertJUnit.assertTrue("FAIL: Attachments content doesn't match heading", AttachmentsCount == AttachmentsAdded);
	}

	//Adds a jpg as an attachment to the wiki because the FCKEditor function isn't available
	public void AlternativeForAddingAttachment() throws Exception {

		sel.click("xpath=id('lotusContent')/div[2]/div[2]/ul/li[2]/a");
		Thread.sleep(2000);

		sel.click("xpath=id('wikiFooter')/ul/li[3]/a");
		Thread.sleep(2000);

		sel.click("xpath=id('addAttachmentDiv')/a");
		Thread.sleep(2000);

		sel.type("class=lotusText lotusLTR qkrFile", "Sample2.jpg");

		sel.chooseOkOnNextConfirmation();

		sel.click("xpath=id('dijit_Dialog_0')/div[2]/div/form/div[2]/input");
		Thread.sleep(2000);

		if (sel.isConfirmationPresent())
			System.out.println("PASS: The confirmation dialog appears because an item of this name has already been attached");
		else System.out.println("FAIL: Confirmation dialog doesn't appear");

		sel.click("xpath=id('wikiBreadcrumbs')/p/a[1]");
		Thread.sleep(2000);

		sel.click("xpath=id('lotusContent')/div[1]/div[2]/div[1]/span[2]/a");
		Thread.sleep(2000);
	}

	public void CheckWikiNameLengthRestrictionCondition() throws Exception {

		AssertJUnit.assertTrue("FAIL: warning text is incorrect", sel.isTextPresent("The page name is too long."));
		AssertJUnit.assertTrue("FAIL: remaining warning text is incorrect", sel.isElementPresent("link=Shorten page name?"));
	}

	public void CheckWikiNameAsDuplicate() throws Exception {

		AssertJUnit.assertTrue("FAIL: duplication verification of wiki isn't correct", sel
				.isTextPresent("A page or file with the name you have specified already exists. Please choose a different name."));
		AssertJUnit.assertTrue("FAIL: Wiki name duplication error message isn't correct", sel.isTextPresent("There were problems with some of your entries."));
	}

	public void CheckWikiNameAsBlank() throws Exception {

		AssertJUnit.assertTrue("FAIL: existence of text warning about blank data input is incorrect", sel.isTextPresent("Please enter a name."));
	}

	public void SelectRootPageInNavPane(String LinkInNavPane) throws Exception {

		sel.click(LinkInNavPane);
		Thread.sleep(2000);
	}

	public void MoveThisPageFromRootToChild(String PageName, String PageNameInList) throws Exception {

		String ItemText = "";

		if (sel.isElementPresent(WikisObjects.Page_Actions_Button))
			clickAtItem(WikisObjects.Page_Actions_Button, "Page Actions");
		else clickAtItem(WikisObjects.Page_Actions_Button_For_Editors, "Page Actions");

		if (sel.isElementPresent(WikisObjects.Menu_Item_4)) {
			ItemText = sel.getText(WikisObjects.Menu_Item_4);
			AssertJUnit.assertTrue("FAIL: The fourth item isn't 'Move Page'", ItemText.contains("Move Page"));

			clickLink(WikisObjects.Menu_Item_4);
		}

		//Click on the make this page a top level page and then click on the Move button to move the page
		clickLink(WikisObjects.MoveThisPageLightBox);
		clickLink(PageNameInList);
		clickLink(WikisObjects.OK_Button);

		System.out.println("Passed: The Root Page has being moved to being a child successfully.");
	}

	public void ValidateWikiActionsItems() throws Exception {

		sel.clickAt(WikisObjects.Wiki_Actions_Button, "Wiki Actions");
		Thread.sleep(2000);

		String Edit_Wiki_Link = sel.getText(WikisObjects.Menu_Item_1);
		String Delete_Wiki_Link = sel.getText(WikisObjects.Menu_Item_2);

		AssertJUnit.assertTrue("FAIL: Wiki Actions item is missing - Edit Wiki", Edit_Wiki_Link.contains("Edit Wiki"));
		AssertJUnit.assertTrue("FAIL: Wiki Actions item is missing - Delete Wiki", Delete_Wiki_Link.contains("Delete Wiki"));

		AssertJUnit.assertTrue("FAIL: Wiki Actions contains more items than expected", !sel.isElementPresent(WikisObjects.Menu_Item_3));
	}

	public void ValidateFollowMenuItems() throws Exception {

		this.clickLink(WikisObjects.Follow_Button);

		String Follow_this_Page = sel.getText(WikisObjects.Menu_Item_1);
		String Follow_this_Wiki = sel.getText(WikisObjects.Menu_Item_2);

		AssertJUnit.assertTrue("FAIL: Follow menu item is missing - Edit Wiki", Follow_this_Page.contains("Follow this Page"));
		AssertJUnit.assertTrue("FAIL: Follow menu is missing - Delete Wiki", Follow_this_Wiki.contains("Follow this Wiki"));
		AssertJUnit.assertTrue("FAIL: Follow menu contains more items than expected", !sel.isElementPresent(WikisObjects.Menu_Item_3));
	}

	public void VerifyHomepageVisibilityInNavigator(String WikiTitle) throws Exception {

		AssertJUnit.assertTrue("FAIL: Wiki page isn't visible in Navigator", sel.isElementPresent(WikisObjects.Wiki_Title_In_Nav));
		String HompageInNavigator = sel.getText(WikisObjects.Wiki_Title_In_Nav);

		AssertJUnit.assertTrue("FAIL: Navigator doesn't show wiki page title", HompageInNavigator.contains(WikiTitle));
	}

	public void ReturnToLatestUpdatesIfWikiAlreadyExists(boolean NewWikiCreated) throws Exception {

		if (NewWikiCreated == false) {
			sel.click(WikisObjects.Cancel_Link);
			Thread.sleep(2000);
		}
	}

	public void returnToLoginScreen() throws Exception {

		sel.click("link=Log in");
		Thread.sleep(1000);
	}

	public void ReturnToPreviousPage() throws Exception {

		sel.goBack();
		Thread.sleep(2000);
	}

	public void VerifyAttachmentIncrementation(int InitialAttachNumber, int CurrentAttachNumber) throws Exception {

		AssertJUnit.assertTrue("FAIL: Attachments link hasn't been incremented correctly", CurrentAttachNumber == (InitialAttachNumber + 1));
		AssertJUnit.assertTrue("FAIL: Attachments link should corresond to attachments count", ElementCount(sel.getText(WikisObjects.Attachments_Tab)) == CurrentAttachNumber);
	}

	public void VerifyAttachmentDecrementation(int InitialAttachNumber, int CurrentAttachNumber) throws Exception {

		AssertJUnit.assertTrue("FAIL: Attachments count hasn't decremented correctly", CurrentAttachNumber == (InitialAttachNumber - 1));
		AssertJUnit.assertTrue("FAIL: Attachments link should corresond to attachments count", ElementCount(sel.getText(WikisObjects.Attachments_Tab)) == CurrentAttachNumber);
	}

	public void verifyAttachmentsPaging() throws Exception {

		AssertJUnit.assertTrue("FAIL: Second page link hasn't been created for more than 10 attachments", sel.isElementPresent(WikisObjects.Link_For_2nd_Page));
		AssertJUnit.assertTrue("FAIL: Second page Next link hasn't been created for more than 10 attachments", sel.isElementPresent(WikisObjects.Top_Next_Page_Link_Active));
	}

	public void VerifyPageViewsIncrementation(int InitialPageViewsNumber, int CurrentPageViewsNumber, int Increment) throws Exception {

		AssertJUnit.assertTrue("FAIL: 'Page views' field hasn't been incremented by " + Increment, CurrentPageViewsNumber == (InitialPageViewsNumber + Increment));
	}

	public int ValidateVersionsNumericValue() throws Exception {

		AssertJUnit.assertTrue("FAIL: Versions heading is missing", sel.isElementPresent(WikisObjects.Versions_Tab));
		String VersionsText = sel.getText(WikisObjects.Versions_Tab);
		int ValueFromHeading = ElementCount(VersionsText);

		AssertJUnit.assertTrue("FAIL: value in Versions heading is incorrect", ValueFromHeading > 0);

		return ValueFromHeading;
	}

	public void ValidateVersionsPageTitleQualifiers(String PageTitle) throws Exception {

		String VersionsTitle = sel.getText(WikisObjects.WikiHomePageTitleField);

		System.out.println(VersionsTitle);
		System.out.println(PageTitle + " : Versions");

		AssertJUnit.assertTrue("FAIL: Versions title isn't correct", VersionsTitle.equals(PageTitle + " : Versions"));
	}

	public void ValidateVersionsJavelinCard(int NoOfVersions) throws Exception {

		String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);

		for (int index = 1; index <= NoOfVersions; index++) {

			if ((NoOfVersions > 10) && (ActivePage.equals("2"))) {
				AssertJUnit.assertTrue("FAIL: on existence of pagination for versions", (sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))
						& (sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));

				clickLink("link=" + ActivePage);
				NoOfVersions = NoOfVersions - 10;
				index = index - 10;
			}

			AssertJUnit
					.assertTrue("FAIL: on existence of username for each version", sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index + "]/td[3]/a"));
			sel.mouseOver("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index + "]/td[3]/a");
			Thread.sleep(3000);
			AssertJUnit.assertTrue("FAIL: javelin card text is missing", sel.isTextPresent("Click here to view business card"));
		}
	}

	public void VerifyViewAndRestoreInVersionsPage(int NoOfVersions, String UserRole) throws Exception {

		String ActivePage = sel.getText(WikisObjects.Versions_Active_Page);
		AssertJUnit.assertTrue("FAIL: Number of versions should be greater than 0", NoOfVersions > 0);

		if (NoOfVersions == 1) {
			AssertJUnit.assertTrue("FAIL: View link doesn't appear in most recent version info", sel.isElementPresent(WikisObjects.Versions_Inactive_Version_View_Link));
		}

		else {
			for (int index = 2; index <= NoOfVersions; index++) {

				if ((index > 10) && (ActivePage.equals("2"))) {
					AssertJUnit.assertTrue("FAIL: on existence of pagination for versions", (sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))
							& (sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));

					clickLink("link=" + ActivePage);
					NoOfVersions = NoOfVersions - 10;
					index = index - 10;
				}
				else if ((NoOfVersions > 10) && (ActivePage.equals("1"))) {
					AssertJUnit.assertTrue("FAIL: on existence of pagination for versions", (sel.isElementPresent(WikisObjects.Versions_Top_Pagination_Section))
							& (sel.isElementPresent(WikisObjects.Versions_Bottom_Pagination_Section)));

					clickLink("link=" + ActivePage);
				}
				AssertJUnit.assertTrue("FAIL: View link doesn't appear in version number " + index, sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index
						+ "]/td[4]/a"));
				String ViewText = sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index + "]/td[4]/a");
				AssertJUnit.assertTrue("FAIL: Link number " + index + " isn't View", ViewText.equals("View"));

				if ((UserRole.equals(Data.Owner_Role)) || (UserRole.equals(Data.Editor_Role))) {

					AssertJUnit.assertTrue("FAIL: Restore link doesn't appear in version number " + index, sel.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr["
							+ index + "]/td[5]/a"));
					String RestoreText = sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index + "]/td[5]/a");
					AssertJUnit.assertTrue("FAIL: Link number " + index + " isn't Restore", RestoreText.equals("Restore"));
				}
				else AssertJUnit.assertTrue("FAIL: Restore link shouldn't appear for user with less than editor access ", !sel
						.isElementPresent("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + index + "]/td[5]/a"));
			}
		}
	}

	public void ActivateVersionComparisonDropdown1(int NumberOfVersions) throws Exception {

		//Populate variable with value for top version number
		int TopVersionInt = Integer.parseInt(sel.getText(WikisObjects.Versions_Top_Version_Item_Number));
		String ActivePage = "";

		//Populate first item in dropdown & array of dropdown items for first comparison dropdown
		String Dropdown1_Options[] = sel.getSelectOptions(WikisObjects.Versions_Comparison_Dropdown1);

		int FirstItemInDropdown1 = Integer.parseInt(Dropdown1_Options[0]);
		int DD1ItemCount = 0;

		AssertJUnit.assertTrue("FAIL: the first item in the first dropdown isn't correct", FirstItemInDropdown1 == TopVersionInt);

		for (int index = 0; index < Dropdown1_Options.length; index++) {
			//System.out.print(Dropdown1_Options[index]);
			DD1ItemCount++;
		}

		//Verify that the items in the first dropdown correctly match the number of versions
		int LastItemInDropdown1 = Integer.parseInt(Dropdown1_Options[Dropdown1_Options.length - 1]);

		AssertJUnit.assertTrue("FAIL: the last item in the first dropdown isn't correct", LastItemInDropdown1 == (FirstItemInDropdown1 - (DD1ItemCount - 1)));

		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)) ActivePage = sel.getText(WikisObjects.Versions_Active_Page);

		if ((NumberOfVersions > 10) & (ActivePage.equals("2"))) {
			AssertJUnit.assertTrue("FAIL: pagination for versions is missing", ActivePage.equals("2"));
			clickLink("link=" + ActivePage);

			NumberOfVersions = NumberOfVersions - 10;
		}

		String VersionValue = sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + NumberOfVersions + "]/td[1]");
		int BottomVersion = Integer.parseInt(VersionValue);

		AssertJUnit.assertTrue("FAIL: the last item in the first dropdown isn't correct", LastItemInDropdown1 == (BottomVersion + 1));
	}

	public void ActivateVersionComparisonDropdown2(int NumberOfVersions) throws Exception {

		String ActivePage = "";

		if (sel.isElementPresent(WikisObjects.Versions_Active_Page)) {
			ActivePage = sel.getText(WikisObjects.Versions_Active_Page);

			if ((NumberOfVersions > 10) && (ActivePage.equals("1"))) clickLink("link=" + ActivePage);
		}

		//Populate variable with value for top version number
		int TopVersionInt = Integer.parseInt(sel.getText(WikisObjects.Versions_Top_Version_Item_Number));

		//Populate first item in dropdown & array of dropdown items for second comparison dropdown
		String Dropdown2_Options[] = sel.getSelectOptions(WikisObjects.Versions_Comparison_Dropdown2);
		int FirstItemInDropdown2 = Integer.parseInt(Dropdown2_Options[0]);
		int DD2ItemCount = 0;

		AssertJUnit.assertTrue("FAIL: the first item in the second dropdown isn't correct", FirstItemInDropdown2 == (TopVersionInt - 1));

		for (int index = 0; index < Dropdown2_Options.length; index++) {
			//System.out.print (Dropdown2_Options[index]);
			DD2ItemCount++;
		}

		//Verify that the items in the second dropdown correctly match the number of versions
		int LastItemInDropdown2 = Integer.parseInt(Dropdown2_Options[Dropdown2_Options.length - 1]);

		AssertJUnit.assertTrue("FAIL: the last item in the second dropdown isn't correct", LastItemInDropdown2 == (FirstItemInDropdown2 - (DD2ItemCount - 1)));

		if ((NumberOfVersions > 10) & (ActivePage.equals("2"))) {
			AssertJUnit.assertTrue("FAIL: pagination for versions is missing", ActivePage.equals("2"));
			clickLink("link=" + ActivePage);

			NumberOfVersions = NumberOfVersions - 10;
		}

		String VersionValue = sel.getText("xpath=id('pageVersions')/div[2]/table/tbody/tr[" + NumberOfVersions + "]/td[1]");
		int BottomVersion = Integer.parseInt(VersionValue);

		AssertJUnit.assertTrue("FAIL: the last item in the second dropdown isn't correct", LastItemInDropdown2 == BottomVersion);
	}

	public void ValidateVersionDeletionDropdown() throws Exception {

		//Populate variable with value for top version number
		int TopVersionInt = Integer.parseInt(sel.getText(WikisObjects.Versions_Top_Version_Item_Number));

		//Populate first item in dropdown & array of dropdown items for first comparison dropdown
		String Delete_Dropdown_Options[] = sel.getSelectOptions(WikisObjects.Versions_Delete_Dropdown);
		int FirstItemInDeleteDropdown = Integer.parseInt(Delete_Dropdown_Options[0]);
		int DDD1ItemCount = 0;

		AssertJUnit.assertTrue("FAIL: the first item in the delete versions dropdown isn't correct", FirstItemInDeleteDropdown == TopVersionInt);

		for (int index = 0; index < Delete_Dropdown_Options.length; index++) {
			DDD1ItemCount++;
		}

		//Verify that the items in the first dropdown correctly match the number of versions
		int LastItemInDeleteDropdown = Integer.parseInt(Delete_Dropdown_Options[Delete_Dropdown_Options.length - 1]);

		AssertJUnit.assertTrue("FAIL: the last item in the delete versions dropdown isn't correct", LastItemInDeleteDropdown == (FirstItemInDeleteDropdown - (DDD1ItemCount - 1)));
	}

	public void VerifyNewContentInPageForVersioning(String Content) throws Exception {

		//Verify newly added content in editor
		String NewContent = sel.getText(WikisObjects.Text_Content_In_Homepage);
		AssertJUnit.assertTrue("FAIL: on newly added content", NewContent.equals(Content));
	}

	public void SelectAndValidateRefreshOnComparisonPage() throws Exception {

		sel.clickAt(WikisObjects.Versions_Comparison_Page_Refresh_Icon, WikisObjects.Versions_Comparison_Page_Refresh);
		Thread.sleep(2000);

		AssertJUnit.assertTrue("FAIL: comparison textfield is missing", sel.isElementPresent(WikisObjects.Versions_Comparison_Page_Compare_Textfield));
	}

	public void CheckVersionsIncrementation(int FirstValue, int SecondValue) throws Exception {

		AssertJUnit.assertTrue("FAIL: Versions heading hasn't been incremented", FirstValue == SecondValue - 1);
	}

	public String ReturnVersionFromCompareDropdown2(String SelectionFromFirstDropdown) throws Exception {

		String Dropdown2_Options[] = sel.getSelectOptions(WikisObjects.Versions_Comparison_Dropdown2);
		String Selection2b = Dropdown2_Options[Dropdown2_Options.length - 2];

		int Dropdown2Selection = Integer.parseInt(Selection2b);
		int Dropdown1Selection = Integer.parseInt(SelectionFromFirstDropdown);

		AssertJUnit.assertTrue("FAIL: Value in second dropdown should default to 1 less than the value in the first dropdown", Dropdown2Selection == Dropdown1Selection - 1);
		return Selection2b;
	}

	public String ReturnBrowserInfo() throws Exception {

		String WhatBrowser = sel.getEval("navigator.appName");
		return WhatBrowser;
	}

	public void CheckNoResultsTypeAheadEntries(String TypeAheadValue) throws Exception {

		String NoResultText = "";

		if (TypeAheadValue.equals("temp")) {
			NoResultText = sel.getText(WikisObjects.No_Results_Dropdown);
			AssertJUnit.assertTrue("FAIL: 'No results' text in type ahead field isn't correct", NoResultText.equals("No results for 'temp'"));
		}
		else Assert.fail("FAIL: Problem encountered with Page Search type ahead");
	}

	public void ValidateNoAccessPage() throws Exception {

		AssertJUnit.assertTrue("FAIL: No access text is incorrect", sel.isTextPresent(WikisObjects.No_Access_Text1));
		AssertJUnit.assertTrue("FAIL: No access text description is incorrect", sel.isTextPresent(WikisObjects.No_Access_Text2));
		AssertJUnit.assertTrue("FAIL: Back to Wikis link is missing", sel.isElementPresent(WikisObjects.BacktoWikis));
		sel.click(WikisObjects.BacktoWikis);
		Thread.sleep(2000);
	}

	public void ValidatePageCannotBeDisplayed() throws Exception {

		AssertJUnit.assertTrue("FAIL: Cannot display error message is incorrect", sel.isTextPresent(WikisObjects.Cannot_Display_Text));
		AssertJUnit.assertTrue("FAIL: Back to Wikis link is missing", sel.isElementPresent(WikisObjects.BacktoWikis));
		sel.click(WikisObjects.BacktoWikis);
		Thread.sleep(2000);
	}

	public void ValidateDraftTitleInEditMode(String DraftTitle) throws Exception {

		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: CKEditor menu bar has failed to load within 30 seconds");
			try {
				if (sel.isVisible("class=cke_toolbar")) ;
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}

		AssertJUnit.assertTrue("FAIL: Editable page title field isn't visible", sel.isElementPresent(WikisObjects.Page_Name_Textfield_In_Editor));
		String EditTitle = sel.getValue(WikisObjects.Page_Name_Textfield_In_Editor);

		AssertJUnit.assertTrue("FAIL: Draft title doesn't appear in edit title textfield", EditTitle.equals(DraftTitle));
	}

	public String GetDraftMessageTitle() throws Exception {

		AssertJUnit.assertTrue("FAIL: Draft message is missing", sel.isElementPresent(WikisObjects.Draft_Message_Title));
		String DraftTitle = sel.getText(WikisObjects.Draft_Message_Title);
		Thread.sleep(2000);

		return DraftTitle;
	}

	public String GetTitle() throws Exception {

		AssertJUnit.assertTrue("FAIL: Homepage title is missing", sel.isElementPresent(WikisObjects.WikiHomePageTitleFieldWithDrafts));
		String DraftTitle = sel.getText(WikisObjects.WikiHomePageTitleFieldWithDrafts);
		Thread.sleep(2000);

		return DraftTitle;
	}

	public void NavigateBackToHomepageUsingBreadcrumbs(String WikiHomePageLink) throws Exception {

		AssertJUnit.assertTrue("FAIL: home page breadcrumb link is missing", sel.isElementPresent(WikiHomePageLink));
		sel.click(WikiHomePageLink);
		Thread.sleep(2000);
	}

	public void NavigateConfirmationPopup() throws Exception {

		Assert.assertTrue(sel.getConfirmation().matches("^Are you sure you want to abandon your changes[\\s\\S]  Press OK to continue or cancel to return to editing\\.$"));
	}

	public void verifyWikiSizingText() throws Exception {

		String AllWikiSizingData = sel.getText(WikisObjects.Wiki_Sizing_Text);

		//"This wiki is using 1MB (30%) of the 2MB limit."

		AssertJUnit.assertTrue("FAIL: Wiki sizing data text is incorrect", AllWikiSizingData.contains("This wiki is using "));
		AssertJUnit.assertTrue("FAIL: Wiki sizing data text is incorrect", AllWikiSizingData.contains(" of the 2MB limit."));
	}

	public int getCurrentWikiSize() throws Exception {

		String AllWikiSizingData = sel.getText(WikisObjects.Wiki_Sizing_Text);
		StringBuffer sizingBuffer = new StringBuffer();
		int index = 20;

		while ((AllWikiSizingData.charAt(index) != 'M') && (index <= AllWikiSizingData.length() - 1)) {
			sizingBuffer.append(AllWikiSizingData.charAt(index));
			index++;
		}

		return Integer.parseInt(sizingBuffer.toString());
	}

	public int getCurrentWikiSizePercentageOfQuota() throws Exception {

		String AllWikiSizingData = sel.getText(WikisObjects.Wiki_Sizing_Text);
		StringBuffer sizingBuffer = new StringBuffer();
		int index = 0;

		while ((AllWikiSizingData.charAt(index) != ')') && (index <= AllWikiSizingData.length() - 1)) {
			if (AllWikiSizingData.charAt(index) != '(') {
				sizingBuffer.append(AllWikiSizingData.charAt(index + 1));
				index++;
			}
		}
		return Integer.parseInt(sizingBuffer.toString());
	}

	public void SelectAllPagesMenuItem(String AllPagesOption) throws Exception {

		if (AllPagesOption.equals("Go to Page...")) {
			sel.click(WikisObjects.Go_To_Page_Link);
			Thread.sleep(1000);

			AssertJUnit.assertTrue("FAIL: Pages search field is missing", sel.isElementPresent(WikisObjects.Search_Page_Field));
			AssertJUnit.assertTrue("FAIL: Pages search icon is missing", sel.isElementPresent(WikisObjects.Search_Page_Button));
		}

		if (AllPagesOption.equals("I Edited")) {
			sel.click(WikisObjects.IEdited_Link);
			Thread.sleep(1000);
		}
		else if (AllPagesOption.equals("Edited by...")) {
			sel.click(WikisObjects.EditedBy_Link);
			Thread.sleep(1000);

			AssertJUnit.assertTrue("FAIL: Pages Edited by search field is missing", sel.isElementPresent(WikisObjects.Search_EditedBy_Field));
			AssertJUnit.assertTrue("FAIL: Pages Edited by search icon is missing", sel.isElementPresent(WikisObjects.Search_EditedBy_Button));
		}
	}

	public void showPersonOnly() throws Exception {

		sel.click(WikisObjects.Person_Filter_Link);
		Thread.sleep(2000);
	}

	public void showGroupsOnly() throws Exception {

		sel.click(WikisObjects.Group_Filter_Link);
		Thread.sleep(2000);
	}

	public void showReaderOnly() throws Exception {

		sel.click(WikisObjects.Reader_Filter_Link);
		Thread.sleep(2000);
	}

	public void showEditorOnly() throws Exception {

		sel.click(WikisObjects.Editor_Filter_Link);
		Thread.sleep(2000);
	}

	public void showOwnerOnly() throws Exception {

		sel.click(WikisObjects.Owner_Filter_Link);
		Thread.sleep(2000);
	}

	public void verifySingleFilterUI(String filterText) throws Exception {

		String personFilterText = sel.getText(WikisObjects.Role_Or_Kind_Filter_Icon);
		AssertJUnit.assertTrue("FAIL: Person filter text isn't correct", personFilterText.contains(filterText));
	}

	public void verifyDoubleFilterUI(String filterText1, String filterText2) throws Exception {

		String personFilterText = sel.getText(WikisObjects.Role_Or_Kind_Filter_Icon);
		AssertJUnit.assertTrue("FAIL: First filter text isn't correct", personFilterText.contains(filterText1));
		String roleFilterText = sel.getText(WikisObjects.Role_Or_Kind_Filter_Icon2);
		AssertJUnit.assertTrue("FAIL: Second filter text isn't correct", roleFilterText.contains(filterText2));
	}

	public String getMemberRoleFilterText() throws Exception {

		//Validate UI for filtering
		AssertJUnit.assertTrue("Filtered by text is incorrect", sel.isTextPresent(WikisObjects.MyWikis_Filter_Text));

		String filterText = sel.getText(WikisObjects.Role_Or_Kind_Filter_Icon);

		if ((filterText.contains("Groups only")) || (filterText.contains("Users only"))) filterText = sel.getText(WikisObjects.Role_Or_Kind_Filter_Icon);

		return filterText;
	}

	//Verify that Setting lightbox appears as expected
	public void ValidateEditWikiUI() throws Exception {

		AssertJUnit.assertTrue("FAIL: Edit Wiki header is missing", sel.isTextPresent("Edit Wiki"));

		AssertJUnit.assertTrue("FAIL: Wiki title label is missing", sel.isTextPresent("*Name:"));
		AssertJUnit.assertTrue("FAIL: Wiki title textfield is missing", sel.isElementPresent(WikisObjects.Edit_Wiki_Name_Textfield));

		AssertJUnit.assertTrue("FAIL: Tags label is missing", sel.isTextPresent("Tags:"));
		AssertJUnit.assertTrue("FAIL: Tags textfield is missing", sel.isElementPresent(WikisObjects.Edit_Wiki_Tags_Textfield));

		AssertJUnit.assertTrue("FAIL: Wiki creator label is missing", sel.isTextPresent("Created by:"));

		AssertJUnit.assertTrue("FAIL: Description label is missing", sel.isTextPresent("Description:"));
		AssertJUnit.assertTrue("FAIL: Wiki description textfield is missing", sel.isElementPresent(WikisObjects.Edit_Wiki_Description_Textfield));

		AssertJUnit.assertTrue("FAIL: Save button is missing", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public String getTitleWithMultipleSpaces(String wikiTitle) throws Exception {

		int endOfText = wikiTitle.length(), startOfNumeric = 0;
		boolean firstLeadingZero = true;

		for (int index = 0; index < wikiTitle.length(); index++) {
			if ((wikiTitle.charAt(index) == ' ') && (firstLeadingZero)) {
				endOfText = index;
				firstLeadingZero = false;
			}

			if ((wikiTitle.charAt(index) != ' ') && (index > endOfText)) startOfNumeric = index;

			if ((wikiTitle.charAt(index) != ' ') && (index > endOfText)) startOfNumeric = index;
		}

		StringBuffer newtitleChange = new StringBuffer(wikiTitle.substring(0, endOfText));
		newtitleChange.append(" ");
		newtitleChange.append(wikiTitle.substring(startOfNumeric));

		return newtitleChange.toString();
	}

	public void validateWikiTitle(String wikiTitle) throws Exception {

		String placeBarText = sel.getText(WikisObjects.Place_Bar_Title);
		String rootBreadcrumbText = sel.getText(WikisObjects.Breadcrumbs_Root_Page);

		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in placebar", wikiTitle.equals(placeBarText));
		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in breadcrumb root", wikiTitle.equals(rootBreadcrumbText));
	}

	public void verifyTitleChangesAreFunctional() throws Exception {

		this.clickObject(WikisObjects.Comments_Tab);

		this.clickObject(WikisObjects.Breadcrumbs_Root_Page);

		this.clickObject(WikisObjects.Versions_Tab);

		this.clickObject(WikisObjects.Place_Bar_Title);

		//Validate main UI in new Wiki homepage
		ValidateWikiPageUI(Data.Owner_Role);
	}

	public void verifyMetaDataUpdatesInMyWikis(String wikiTitle, String Username) throws Exception {

		//Verify My Wikis tab meta data
		sel.click(WikisObjects.My_Wikis_Tab);
		Thread.sleep(1000);

		sel.click(WikisObjects.MyWikis_Reader_Filter);
		Thread.sleep(1000);

		String wikiTitleInMyWikis = sel.getText(WikisObjects.My_Wikis_TopCell);
		String updatedByUsername = sel.getText(WikisObjects.TopCell_Username);

		AssertJUnit.assertTrue("FAIL: Changed title doesn't appear in My Wikis list", wikiTitleInMyWikis.equals(wikiTitle));
		AssertJUnit.assertTrue("FAIL: Username hasn't changed accordingly in My Wikis", updatedByUsername.contains(Username));
	}

	public void verifySubView(String wikiTitle, String pageTitle, String subView, boolean isPublicOrAuthenticated) throws Exception {

		sel.setTimeout("0");

		//Open specified subview & verify it is opened automatically
		if (subView.equals("Comments")) {
			sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/comment");
			Thread.sleep(5000);

			if (!isPublicOrAuthenticated)
				LoginUIValidation();

			else
			//Verify Add a Comment link is visible
			AssertJUnit.assertTrue("FAIL: Comments subview isn't opened automatically through address bar", sel.isTextPresent(WikisObjects.Subscribe_To_Comments_Text));
		}
		else if (subView.equals("Versions")) {
			sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/versions");
			Thread.sleep(5000);

			if (!isPublicOrAuthenticated)
				LoginUIValidation();

			else
			//Verify Versions heading is visible
			AssertJUnit.assertTrue("FAIL: Versions subview isn't opened automatically through address bar", sel.isTextPresent(WikisObjects.Subscribe_To_Versions_Text));
		}
		else if (subView.equals("Attachments")) {
			sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/attachments");
			Thread.sleep(5000);

			if (!isPublicOrAuthenticated)
				LoginUIValidation();

			else
			//Verify Add an attachment link is visible
			AssertJUnit.assertTrue("FAIL: Attachments subview isn't opened automatically through address bar", sel.isTextPresent(WikisObjects.Subscribe_To_Attachments_Text));
		}
		else if (subView.equals("About")) {
			sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/info");
			Thread.sleep(5000);

			if (!isPublicOrAuthenticated)
				LoginUIValidation();

			else {
				//Verify About this page elements are visible
				AssertJUnit.assertTrue("FAIL: Updated label doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.Updated_Field));
				AssertJUnit.assertTrue("FAIL: Added label doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.Added_Field));
				AssertJUnit.assertTrue("FAIL: Size label doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.Size_Field));
				AssertJUnit.assertTrue("FAIL: Page views label doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.PageViews_Field));
				AssertJUnit.assertTrue("FAIL: Child Pages doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.Child_Pages_Text));
				AssertJUnit.assertTrue("FAIL: Hierarchy label doesn't appear so About subview isn't opened automatically through address bar", sel
						.isTextPresent(WikisObjects.Hierarchy_Text));
			}
		}
	}

	public void verifyParticularComment(String wikiTitle, String pageTitle, String[] commentUUID, String commentText, boolean isPublicOrAuthenticated) throws Exception {

		sel.setTimeout("0");

		//Verify that both Comment UUIDs passed point to the corresponding comments
		sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/comment/" + commentUUID[0]);
		Thread.sleep(5000);

		if (!isPublicOrAuthenticated)
			LoginUIValidation();

		else
		//Verify particular comment is visible
		AssertJUnit.assertTrue("FAIL: Specified comment isn't opened automatically through address bar", sel.isTextPresent(commentText + 1));

		sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/comment/" + commentUUID[1]);
		Thread.sleep(5000);

		if (!isPublicOrAuthenticated)
			LoginUIValidation();

		else
		//Verify particular comment is visible
		AssertJUnit.assertTrue("FAIL: Specified comment isn't opened automatically through address bar", sel.isTextPresent(commentText + 15));
	}

	public void verifyParticularVersion(String wikiTitle, String pageTitle, String versionUUID, boolean isPublicOrAuthenticated) throws Exception {

		sel.setTimeout("0");

		sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/version/" + versionUUID);
		Thread.sleep(5000);

		if (!isPublicOrAuthenticated)
			LoginUIValidation();

		else {
			//Verify particular version is visible
			AssertJUnit.assertTrue("FAIL: Specified version isn't opened automatically through address bar", sel.isTextPresent("Welcome [1] : Versions"));
			AssertJUnit.assertTrue("FAIL: Specified wiki & page aren't visible in breadcrumbs", sel.isTextPresent(wikiTitle + " >"));
		}
	}

	public void verifyParticularComparison(String wikiTitle, String pageTitle, boolean isPublicOrAuthenticated) throws Exception {

		sel.setTimeout("0");

		sel.open("wikis/home?lang=en#/wiki/" + wikiTitle + "/page/" + pageTitle + "/compare?to=2&from=1");
		Thread.sleep(5000);

		if (!isPublicOrAuthenticated)
			LoginUIValidation();

		else {
			//Verify particular comparison is visible
			AssertJUnit.assertTrue("FAIL: Specified comparison isn't opened automatically through address bar", sel.isTextPresent("Welcome : Version Comparison"));
			AssertJUnit.assertTrue("FAIL: Specified wiki & page aren't visible in breadcrumbs", sel.isTextPresent(wikiTitle + " >"));
		}
	}

	public void ValidateManageAccessPopup() throws Exception {

		this.VerifyManageAccessUI(false);

		AssertJUnit.assertTrue("FAIL: Save button is missing", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
		AssertJUnit.assertTrue("FAIL: Close icon is missing", sel.isElementPresent(WikisObjects.Close_Icon));
	}

	public void VerifyManageAccessUI(boolean CreateNewWiki) throws Exception {

		String ReadAccess1, ReadAccess2, EditAccess1, EditAccess2;

		if (CreateNewWiki) {
			ReadAccess1 = sel.getText(WikisObjects.Start_A_Wiki_Radio_Button_Text1);
			ReadAccess2 = sel.getText(WikisObjects.Start_A_Wiki_Radio_Button_Text2);
			EditAccess1 = sel.getText(WikisObjects.Start_A_Wiki_Radio_Button_Text3);
			EditAccess2 = sel.getText(WikisObjects.Start_A_Wiki_Radio_Button_Text4);
		}
		else {
			ReadAccess1 = sel.getText(WikisObjects.Manage_Access_Radio_Button_Text1);
			ReadAccess2 = sel.getText(WikisObjects.Manage_Access_Radio_Button_Text2);
			EditAccess1 = sel.getText(WikisObjects.Manage_Access_Radio_Button_Text3);
			EditAccess2 = sel.getText(WikisObjects.Manage_Access_Radio_Button_Text4);
		}
		AssertJUnit.assertTrue("FAIL: First radio button is invalid", sel.isElementPresent(WikisObjects.Radio_Button1));
		AssertJUnit.assertTrue("FAIL: Second radio button is invalid", sel.isElementPresent(WikisObjects.Radio_Button2));
		AssertJUnit.assertTrue("FAIL: Third radio button is invalid", sel.isElementPresent(WikisObjects.Radio_Button3));
		AssertJUnit.assertTrue("FAIL: Fourth radio button is invalid", sel.isElementPresent(WikisObjects.Radio_Button4));

		AssertJUnit.assertTrue("FAIL: Read Access heading is missing", sel.isTextPresent("Read Access:"));
		AssertJUnit.assertTrue("FAIL: Edit Access heading is missing", sel.isTextPresent("Edit Access:"));

		AssertJUnit.assertTrue("FAIL: Wiki title label is missing", ReadAccess1.equals("All users"));
		AssertJUnit.assertTrue("FAIL: Wiki title label is missing", ReadAccess2.equals("Wiki members only"));

		AssertJUnit.assertTrue("FAIL: Wiki title label is missing", EditAccess1.equals("All logged in users"));
		AssertJUnit.assertTrue("FAIL: Wiki title label is missing", EditAccess2.equals("Wiki editors and managers only"));
	}

	public void VerifyMembersAndRolesPageHeader(String WikiTitle) throws Exception {

		String HeaderText = sel.getText(WikisObjects.Members_Header);
		AssertJUnit.assertTrue("FAIL: Members and Roles popup header isn't correct", HeaderText.equals("Members of " + WikiTitle));
	}

	public void VerifyManageAccessPopupHeader() throws Exception {

		String HeaderText = sel.getText(WikisObjects.Manage_Access_Header);
		AssertJUnit.assertTrue("FAIL: Manage Access popup header isn't correct", HeaderText.contains("Manage Access"));
	}

	public void changeWikiAccess() throws Exception {

		sel.click(WikisObjects.Manage_Access_Radio_Button_Text1);
		Thread.sleep(2000);
	}

	public void changeWikiAccessToMakeWikiPublic() throws Exception {

		//First Click on Manage Access Button.
		this.clickLink(WikisObjects.Manage_Access_Button);

		//Click on All Users under Who can Read section.
		this.clickLink(WikisObjects.Manage_Access_Radio_Button_Text1);

		//Click on Save Button to Save the Changed Access rights.
		this.clickLink(WikisObjects.Save_Button);
	}

	public void saveManageAccessChanges() throws Exception {

		sel.click(WikisObjects.Save_Button);
		Thread.sleep(2000);
	}

	public void CloseManageAccessPopup() throws Exception {

		sel.focus(WikisObjects.Close_Icon);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}

	public void CancelManageAccessPopup() throws Exception {

		sel.focus(WikisObjects.Cancel_Link);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}

	public void ChangeUserAccessRightsAsAdministrator(String Role, String MemberType, String Name) throws Exception {

		String BrowserInfo = this.ReturnBrowserInfo();

		sel.click(WikisObjects.Add_Members_Button);
		Thread.sleep(2000);

		if (MemberType.equals("User")) {
			if (Role.equals("Owner"))
				this.SelectMembershipRoleasOwnerforAddingUsersonAddMembersLightBox();

			else if (Role.equals("Editor"))
				this.SelectMembershipRoleasEditorforAddingUsersonAddMembersLightBox();

			else if (Role.equals("Reader"))
				this.SelectMembershipRoleasReaderforAddingUsersonAddMembersLightBox();

			else System.out.println("FAIL: Role is incorrectly input!");

			this.ActivateTypeAheadForMembersField(BrowserInfo, Name);
			this.selectMemberFromUserDropdown(Name);
		}

		if (MemberType.equals("Group")) {
			String Typeahead = "";

			//Condition to use the Groups typeahead instead of group names
			if (Name.equals(Data.Wiki_LDAP_Owners_Group))
				Typeahead = Data.Wiki_LDAP_Owners_Group_Typeahead;

			else if (Name.equals(Data.Wiki_LDAP_Editors_Group))
				Typeahead = Data.Wiki_LDAP_Editors_Group_TypeAhead;

			else if (Name.equals(Data.Wiki_LDAP_Readers_Group)) Typeahead = Data.Wiki_LDAP_Readers_Group_TypeAhead;

			//Condition for selecting the type of users to be added
			if (Role.equals("Owner")) this.SelectMembershipRoleasOwnerforAddingGroupsonAddMembersLightBox();

			if (Role.equals("Editor"))
				this.SelectMembershipRoleasEditorforAddingGroupsonAddMembersLightBox();

			else if (Role.equals("Reader"))
				this.SelectMembershipRoleasReaderforAddingGroupsonAddMembersLightBox();

			else System.out.println("FAIL: Role is incorrectly input!");

			this.ActivateAddGroups();
			this.ActivateTypeAheadForGroupsField(BrowserInfo, Name);
			this.selectMemberFromGroupDropdown(Name);
		}

		sel.click(WikisObjects.Add_Members_OK_Button);
		Thread.sleep(2000);
	}

	public void VerifyUserNameAppearsInListFormat(String Username) throws Exception {

		boolean memberNotFound = true;
		int index1 = 1, index2;
		String NameCandidate = "";

		//Select user from Members
		while (memberNotFound) {
			index2 = 1;
			while ((sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a")) && (memberNotFound)) {
				NameCandidate = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a");

				if (NameCandidate.contains(Username))
					memberNotFound = false;

				else index2++;
			}

			if (memberNotFound) index1++;
		}
		AssertJUnit.assertTrue("FAIL: Newly added user doesn't appear in Members page list format", memberNotFound == false);
	}

	public void VerifyUserUIInTableFormat(String Username, String Role) throws Exception {

		boolean memberNotFound = true;
		int index1 = 1, index2 = 1;
		String NameCandidate = "";

		//Select user from Members
		while (memberNotFound) {
			index2 = 1;
			while ((sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a")) && (memberNotFound)) {
				NameCandidate = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a");

				if (NameCandidate.contains(Username))
					memberNotFound = false;

				else index2++;
			}

			if (memberNotFound) index1++;
		}

		if (memberNotFound == false) {
			AssertJUnit.assertTrue("FAIL: Members Edit link isn't appearing", sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2
					+ "]/table/tbody/tr/td[2]/div[2]/ul/li[1]/a"));
			sel.clickAt("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/div[2]/ul/li[1]/a", "Edit");
			Thread.sleep(2000);

			this.ValidateEditMemberLightbox(Username);
			this.VerifyAccessIsCorrect(Role);

			clickLink(WikisObjects.Cancel_Link);
		}
	}

	public void ValidateEditMemberLightbox(String Username) throws Exception {

		String EditMembersTitle = sel.getText(WikisObjects.Lightbox_Header);

		AssertJUnit.assertTrue("FAIL: Edit Members title is incorrect", EditMembersTitle.contains("Edit Member"));
		AssertJUnit.assertTrue("FAIL: Reader radio button is missing", sel.isElementPresent(WikisObjects.Reader_Role_Option_onEditMemberLightBox));
		AssertJUnit.assertTrue("FAIL: Editor radio button is missing", sel.isElementPresent(WikisObjects.Editor_Role_Option_onEditMemberLightBox));
		AssertJUnit.assertTrue("FAIL: Owner radio button is missing", sel.isElementPresent(WikisObjects.Owner_Role_Option_onEditMemberLightBox));
		AssertJUnit.assertTrue("FAIL: Role label is missing", sel.isTextPresent("Role:"));
		AssertJUnit.assertTrue("FAIL: on OK button", sel.isElementPresent(WikisObjects.Members_And_Permissions_OK));
		AssertJUnit.assertTrue("FAIL: on Cancel link", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void OpenAddMembers() throws Exception {

		sel.click(WikisObjects.Add_Members_Button);
		Thread.sleep(1000);
	}

	public void ActivateAddGroups() throws Exception {

		sel.click(WikisObjects.AddGroups_Link);
		Thread.sleep(1000);
	}

	public void VerifyAccessIsCorrect(String Role) throws Exception {

		if (Role.equals("Owner")) {
			AssertJUnit.assertTrue("FAIL: Owner radio button isn't selected", sel.isChecked(WikisObjects.Owner_Role_Option_onEditMemberLightBox));
		}
		else if (Role.equals("Editor")) {
			AssertJUnit.assertTrue("FAIL: Editor radio button isn't selected", sel.isChecked(WikisObjects.Editor_Role_Option_onEditMemberLightBox));
		}
		else if (Role.equals("Reader")) {
			AssertJUnit.assertTrue("FAIL: Reader radio button isn't selected", sel.isChecked(WikisObjects.Reader_Role_Option_onEditMemberLightBox));
		}
	}

	public void ValidateMembersAndRolesPopupContent() throws Exception {

		AssertJUnit.assertTrue("FAIL: on Members label", sel.isTextPresent("Members"));
		AssertJUnit.assertTrue("FAIL: on All Authenticated Users field", sel.isElementPresent(WikisObjects.All_Authenticated_Users));
		AssertJUnit.assertTrue("FAIL: on Anonymous Users field", sel.isElementPresent(WikisObjects.Anonymous_Users_Field));
		AssertJUnit.assertTrue("FAIL: on Owner field", sel.isElementPresent(WikisObjects.Owner_Field));
		AssertJUnit.assertTrue("FAIL: on User/Group dropdown", sel.isElementPresent(WikisObjects.User_Group_Dropdown));
		String UserGroup_Options[] = sel.getSelectOptions(WikisObjects.User_Group_Dropdown);
		AssertJUnit.assertTrue("FAIL: on number of items in User/Group dropdown", UserGroup_Options.length == 2);
		Assert.assertEquals("FAIL: on 1st item in User/Group dropdown", UserGroup_Options[0], "User");
		Assert.assertEquals("FAIL: on 2nd item in User/Group dropdown", UserGroup_Options[1], "Group");

		AssertJUnit.assertTrue("FAIL: on User Role dropdown", sel.isElementPresent(WikisObjects.User_Role_Dropdown));
		String UserRole_Options[] = sel.getSelectOptions(WikisObjects.User_Role_Dropdown);
		AssertJUnit.assertTrue("FAIL: on number of items in User Roles dropdown", UserRole_Options.length == 3);
		Assert.assertEquals("FAIL: on 1st item in User Roles dropdown", UserRole_Options[0], "Owner");
		Assert.assertEquals("FAIL: on 2nd item in User Roles dropdown", UserRole_Options[1], "Editor");
		Assert.assertEquals("FAIL: on 3rd item in User Roles dropdown", UserRole_Options[2], "Reader");

		AssertJUnit.assertTrue("FAIL: on username textfield", sel.isElementPresent(WikisObjects.Username_Textfield));
		AssertJUnit.assertTrue("FAIL: on OK button", sel.isElementPresent(WikisObjects.Members_And_Permissions_OK));
		AssertJUnit.assertTrue("FAIL: on Cancel link", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void ValidateAddMembersPopupContent() throws Exception {

		String AddMembersTitle = sel.getText("xpath=//*[contains(@id,'quickr_widget_Dialog_')]/div[2]/div/form/h1");

		AssertJUnit.assertTrue("FAIL: Add Members title is incorrect", AddMembersTitle.contains("Add Members"));
		AssertJUnit.assertTrue("FAIL: Members label is missing", sel.isTextPresent("Add members:"));

		//Verify that Members can be added
		AssertJUnit.assertTrue("FAIL: Members dropdown is missing", sel.isElementPresent(WikisObjects.MembershipRolesUsersDropdown));
		String Members_Options[] = sel.getSelectOptions(WikisObjects.MembershipRolesUsersDropdown);

		AssertJUnit.assertTrue("FAIL: Number of items in Members dropdown is incorrect", Members_Options.length == 3);
		Assert.assertEquals("FAIL: 1st item in Members dropdown is incorrect", Members_Options[0], "Owner");
		Assert.assertEquals("FAIL: 2nd item in Members dropdown is incorrect", Members_Options[1], "Editor");
		Assert.assertEquals("FAIL: 3rd item in Members dropdown is incorrect", Members_Options[2], "Reader");

		AssertJUnit.assertTrue("FAIL: Members textfield is missing", sel.isElementPresent(WikisObjects.Members_Textfield_Typeahead));

		//Verify that Groups can be added
		AssertJUnit.assertTrue("FAIL: Add groups link is missing", sel.isElementPresent(WikisObjects.AddGroups_Link));
		sel.click(WikisObjects.AddGroups_Link);
		Thread.sleep(1000);

		AssertJUnit.assertTrue("FAIL: Groups dropdown is missing", sel.isElementPresent(WikisObjects.MembershipRolesGroupsDropdown));
		String Groups_Options[] = sel.getSelectOptions(WikisObjects.MembershipRolesGroupsDropdown);
		AssertJUnit.assertTrue("FAIL: Number of items in Groups dropdown is incorrect", Groups_Options.length == 3);
		Assert.assertEquals("FAIL: 1st item in Groups dropdown is incorrect", Groups_Options[0], "Owner");
		Assert.assertEquals("FAIL: 2nd item in Groups dropdown is incorrect", Groups_Options[1], "Editor");
		Assert.assertEquals("FAIL: 3rd item in Groups dropdown is incorrect", Groups_Options[2], "Reader");

		AssertJUnit.assertTrue("FAIL: Groups textfield is missing", sel.isElementPresent(WikisObjects.Groups_Textfield_Typeahead));

		AssertJUnit.assertTrue("FAIL: OK button is missing", sel.isElementPresent(WikisObjects.Members_And_Permissions_OK));
		AssertJUnit.assertTrue("FAIL: Cancel link is missing", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void ValidateAllPagesResultantPage(String AllPagesOption, String FullUsername) throws Exception {

		if (AllPagesOption.equals("Index")) {
			AssertJUnit.assertTrue("FAIL: Index page doesn't appear", sel.isElementPresent(WikisObjects.Pages_Index_Title));
			String PI = sel.getText(WikisObjects.Pages_Index_Title);
			AssertJUnit.assertTrue("FAIL: Resultant page doesn't have the correct title for Pages Index", PI.equals(AllPagesOption));

		}
		else if (AllPagesOption.equals("Pages Edited by Me")) {
			AssertJUnit.assertTrue("FAIL: Pages Edited by Me page doesn't appear", sel.isElementPresent(WikisObjects.Pages_Edited_By_Me_Title));
			String PEBM = sel.getText(WikisObjects.Pages_Edited_By_Me_Title);
			AssertJUnit.assertTrue("FAIL: Resultant page doesn't have the correct title for Pages Edited By Me", PEBM.equals("Pages Edited by " + FullUsername));
		}
		else if (AllPagesOption.equals("Pages Edited by ...")) {
			AssertJUnit.assertTrue("FAIL: 'Pages Edited by ...' page doesn't appear", sel.isElementPresent(WikisObjects.Pages_Edited_By_Me_Title));
			String PEBM = sel.getText(WikisObjects.Pages_Edited_By_Me_Title);
			AssertJUnit.assertTrue("FAIL: Resultant page doesn't have the correct title for Pages Edited By Me", PEBM.equals("Pages Edited by " + FullUsername));
		}
	}

	public String ValidateIndexPlaceBarAndTitle(String WikiTitle) throws Exception {

		String PlaceBarTitle = sel.getText(WikisObjects.Place_Bar_Title);
		AssertJUnit.assertTrue("FAIL: Placebar title isn't correct", PlaceBarTitle.equals(WikiTitle));

		AssertJUnit.assertTrue("FAIL: Follow button is missing", sel.isElementPresent(WikisObjects.Follow_Button));

		return sel.getText(WikisObjects.PageTitleField);
	}

	public void ValidatePageEditedByMePlaceBarAndTitle(String WikiTitle) throws Exception {

		String PlaceBarTitle = sel.getText(WikisObjects.Place_Bar_Title);
		AssertJUnit.assertTrue("FAIL: Placebar title isn't correct", PlaceBarTitle.equals(WikiTitle));

		String PageTitle = sel.getText(WikisObjects.PageTitleField);
		AssertJUnit.assertTrue("FAIL: Page title isn't correct", PageTitle.equals("Pages Edited by You"));
	}

	public void ValidatePageDisplayViews() throws Exception {

		AssertJUnit.assertTrue("FAIL: 'Display' label is missing", sel.isTextPresent("Display:"));

		if (sel.isElementPresent(WikisObjects.PressedTableIcon))
			AssertJUnit.assertTrue("FAIL: inactive list view icon isn't visible", sel.isElementPresent(WikisObjects.UnPressedListIcon));

		else if (sel.isElementPresent(WikisObjects.PressedListIcon))
			AssertJUnit.assertTrue("FAIL: inactive table view icon isn't visible", sel.isElementPresent(WikisObjects.UnPressedTableIcon));
	}

	public void ValidatePagePaginationUI() throws Exception {

		AssertJUnit.assertTrue("FAIL: top pagination bar is missing", sel.isVisible(WikisObjects.Page_Index_Top_Pagination));
		AssertJUnit.assertTrue("FAIL: 'Previous' in top pagination bar is missing", sel.isVisible(WikisObjects.Top_Previous_Page_Link_InActive));
		AssertJUnit.assertTrue("FAIL: bottom pagination bar is missing", sel.isVisible(WikisObjects.Page_Index_Bottom_Pagination));
		AssertJUnit.assertTrue("FAIL: 'Previous' in bottom pagination bar is missing", sel.isVisible(WikisObjects.Bottom_Previous_Page_Link_InActive));

		String tempString = sel.getText(WikisObjects.Page_Index_Text_In_Top_Pagination);
		String NumberOfWikis = "";
		int NoOfWikis = 0;
		//Get password for extracted username
		for (int i = 0; i < tempString.length(); i++) {
			if (tempString.charAt(i) == 'f') {
				NumberOfWikis = tempString.substring(i + 2, tempString.length());
				NoOfWikis = Integer.parseInt(NumberOfWikis);
			}
		}

		if (NoOfWikis < 10) {
			AssertJUnit.assertTrue("FAIL: 'Next' in top pagination bar is missing", sel.isVisible(WikisObjects.Top_Next_Page_Link_InActive));
			AssertJUnit.assertTrue("FAIL: 'Next' in bottom pagination bar is missing", sel.isVisible(WikisObjects.Bottom_Next_Page_Link_InActive));
			AssertJUnit.assertTrue("FAIL: Jump to page section shouldn't appear for a single page", !sel.isElementPresent(WikisObjects.Jump_To_Page));
		}
		else {
			AssertJUnit.assertTrue("FAIL: 'Next' in top pagination bar is missing", sel.isVisible(WikisObjects.Top_Next_Page_Link_Active));
			AssertJUnit.assertTrue("FAIL: 'Next' in bottom pagination bar is missing", sel.isVisible(WikisObjects.Bottom_Next_Page_Link_Active));
			AssertJUnit.assertTrue("FAIL: Jump to page section is missing", sel.isElementPresent(WikisObjects.Jump_To_Page));
		}

		AssertJUnit.assertTrue("FAIL: 'Show' text missing from bottom pagination bar is missing", sel.isTextPresent("Show"));
		if (sel.isElementPresent(WikisObjects.Link_toshow_10Members_perpage)) {
		}
		else AssertJUnit.assertTrue("FAIL: numeric link '10' in bottom pagination bar is missing", sel.isElementPresent("xpath=id('10')"));
		if (sel.isElementPresent(WikisObjects.Link_toshow_25Members_perpage)) {
		}
		else AssertJUnit.assertTrue("FAIL: numeric link '25' in bottom pagination bar is missing", sel.isElementPresent("xpath=id('25')"));
		if (sel.isElementPresent(WikisObjects.Link_toshow_50Members_perpage)) {
		}
		else AssertJUnit.assertTrue("FAIL: numeric link '50' in bottom pagination bar is missing", sel.isElementPresent("xpath=id('50')"));
		AssertJUnit.assertTrue("FAIL: Closing text missing from bottom pagination bar is missing", sel.isTextPresent("items"));
	}

	public void CancelAddMembersPopup() throws Exception {

		sel.focus(WikisObjects.Cancel_Link);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2000);
	}

	public void ValidateSortByLinksInMainTabs() throws Exception {

		AssertJUnit.assertTrue("FAIL: Sort by Name Option is not present", sel.isElementPresent(WikisObjects.Sortby_Name_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Created Option is not present", sel.isElementPresent(WikisObjects.Sortby_Created_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Updated Option is not present", sel.isElementPresent(WikisObjects.Sortby_Updated_Option));
	}

	public void ValidateSortByLinksInPagesTableLinks() throws Exception {

		AssertJUnit.assertTrue("FAIL: Sort by Name option is missing in table view", sel.isElementPresent(WikisObjects.Sortby_Name_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Updated option is missing in table view", sel.isElementPresent(WikisObjects.Sortby_Updated_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Visits option is missing in table view", sel.isElementPresent(WikisObjects.Sortby_Visits_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Recommendations option is missing in table view", sel.isElementPresent(WikisObjects.Sortby_Recommendations_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Comments option is missing in table view", sel.isElementPresent(WikisObjects.Sortby_Comments_Option));
	}

	public void ValidateSortByLinksInPagesListLinks() throws Exception {

		AssertJUnit.assertTrue("FAIL: Sort by Name option is missing in list view", sel.isVisible(WikisObjects.Sortby_Name_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Updated option is missing in list view", sel.isVisible(WikisObjects.Sortby_Updated_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Created option is missing in list view", sel.isVisible(WikisObjects.Sortby_Created_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Page Views option is missing in list view", sel.isVisible(WikisObjects.Sortby_Page_Views_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Size option is missing in list view", sel.isVisible(WikisObjects.Sortby_Size_Option));
	}

	public void ValidatePageWithoutContents(String FormatView) throws Exception {

		if (FormatView.equals("Table")) ValidateSortByLinksInPagesTableLinks();
		AssertJUnit.assertTrue("FAIL: 'No items found' text doesn't appear", sel.isTextPresent("No items found"));
		AssertJUnit.assertTrue("FAIL: empty top pagination bar doesn't appear", sel.isElementPresent("xpath=id('list')/div[1]"));
		AssertJUnit.assertTrue("FAIL: empty bottom pagination bar doesn't appear", sel.isElementPresent("xpath=id('list')/div[3]"));
	}

	public void VerifyPageInTableFormat() throws Exception {

		//Verify that all 'Sort by' links appear
		ValidateSortByLinksInPagesTableLinks();

		AssertJUnit.assertTrue("FAIL: Page Icon doesn't exist in Table format", sel.isVisible(WikisObjects.Page_Icons));
		AssertJUnit.assertTrue("FAIL: Page link doesn't exist in Table format", sel.isVisible(WikisObjects.Page_Links));
		AssertJUnit.assertTrue("FAIL: Most Visited Icon doesn't exist in Table format", sel.isVisible(WikisObjects.PageIndex_MostVisitedIcon));
		AssertJUnit.assertTrue("FAIL: Most Recommended Icon doesn't exist in Table format", sel.isVisible(WikisObjects.PageIndex_MostRecommendedIcon));
		AssertJUnit.assertTrue("FAIL: Most Commented Icon doesn't exist in Table format", verifyCommentsIcon());
		AssertJUnit.assertTrue("FAIL: More link doesn't exist in Table format", sel.isVisible(WikisObjects.More_Link));

		clickLink(WikisObjects.More_Link);
		if (!sel.isElementPresent(WikisObjects.Add_or_RemoveTags_Link))
			AssertJUnit.assertTrue("FAIL: Tags link doesn't exist in Table format", sel.isElementPresent(WikisObjects.Add_tags_Link));
		AssertJUnit.assertTrue("FAIL: Download Page link doesn't exist in Table format", sel.isElementPresent(WikisObjects.Download_Page_Link));

	}

	public boolean verifyCommentsIcon() throws Exception {

		if (sel.isElementPresent(WikisObjects.PageIndex_MostCommentedIcon))
			return true;
		else if (sel.isElementPresent(WikisObjects.PageIndex_NoCommentsIcon))
			return true;
		else return false;
	}

	public void VerifyPageInListFormat() throws Exception {

		//Verify that all 'Sort by' links appear
		ValidateSortByLinksInPagesListLinks();

		AssertJUnit.assertTrue("FAIL: Page icon doesn't exist in List format", sel.isVisible(WikisObjects.Page_Icons));
		AssertJUnit.assertTrue("FAIL: Page link doesn't exist in List format", sel.isVisible(WikisObjects.Page_Links));
		AssertJUnit.assertTrue("FAIL: More link doesn't exist in List format", sel.isVisible(WikisObjects.More_Link));
	}

	public void VerifyPageTitle(String GetTitle, String ActualTitle) throws Exception {

		AssertJUnit.assertTrue("FAIL: Page title isn't correct", GetTitle.equals(ActualTitle));
	}

	public void ValidateWikiForAllowedCharacters() throws Exception {

		AssertJUnit.assertTrue("FAIL: on appearance of warning message for allowed characters", !sel.isTextPresent("Page name may not contain the following characters:"));
	}

	public void ValidatePageForDisallowedCharacters() throws Exception {

		AssertJUnit.assertTrue("FAIL: Warning message for invalid characters is incorrect", sel.isTextPresent("Page name may not contain the following characters:"));
		AssertJUnit.assertTrue("FAIL: Warning message link to insert allowed characters is incorrect", sel.isElementPresent("link=Replace invalid characters with '_'?"));
	}

	public void ValidateWikiForDisallowedCharacters() throws Exception {

		AssertJUnit.assertTrue("FAIL: Warning message for invalid characters is incorrect", sel.isTextPresent("Wiki title may not contain the following characters:"));
		AssertJUnit.assertTrue("FAIL: Warning message link to insert allowed characters is incorrect", sel.isElementPresent("link=Replace invalid characters with '_'?"));
	}

	public void LogoutWithConfirmation() throws Exception {

		sel.click("link=Log out");
		Thread.sleep(2000);
		Assert.assertTrue(sel.getConfirmation().matches("^Are you sure you want to abandon your changes[\\s\\S]  Press OK to continue or cancel to return to editing\\.$"));
	}

	public int parseViewsNumber(String inputString1) throws Exception {

		StringBuffer bufferedText = new StringBuffer();
		int ParsedNumber = 0;

		for (int index = 0; index < inputString1.length(); index++) {
			bufferedText.append(inputString1.charAt(index));
		}

		ParsedNumber = Integer.parseInt(bufferedText.toString());

		return ParsedNumber;
	}

	public int parseFirstNumber(String inputString1) throws Exception {

		StringBuffer bufferedText = new StringBuffer();
		int index = 0, firstParsedNumber = 0;

		while ((inputString1.charAt(index)) != ' ') {
			bufferedText.append(inputString1.charAt(index));
			index++;
		}

		firstParsedNumber = Integer.parseInt(bufferedText.toString());

		return firstParsedNumber;
	}

	public int parseSecondNumber(String inputString2) throws Exception {

		StringBuffer bufferedText = new StringBuffer();
		int index = 0, secondParsedNumber = 0;
		int startIndexSubString = inputString2.indexOf('(');
		int endIndexSubString = inputString2.indexOf(')');

		String subString = inputString2.substring(startIndexSubString + 1, endIndexSubString);

		while ((subString.charAt(index)) != ' ') {
			bufferedText.append(subString.charAt(index));
			index++;
		}

		secondParsedNumber = Integer.parseInt(bufferedText.toString());

		return secondParsedNumber;
	}

	public void AddContentToEditor(String NewContentText) throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Rich Text tab		
		sel.clickAt(WikisObjects.Rich_Text_Tab, WikisObjects.Rich_Text_Tab_Label);
		Thread.sleep(2000);

		sel.selectFrame(WikisObjects.CKEditorFrame);
		sel.type(WikisObjects.CKEditor, NewContentText);
		sel.selectFrame("relative=top");
		sel.click(WikisObjects.Save_and_Close_Link);
		Thread.sleep(1000);

		if (sel.isTextPresent(WikisObjects.Duplicate_Page_Warning)) {
			sel.click(WikisObjects.Cancel_Link);
			Thread.sleep(1000);
			Assert.assertTrue(sel.getConfirmation().matches("^Are you sure you want to abandon your changes[\\s\\S]  Press OK to continue or cancel to return to editing\\.$"));
		}
	}

	public void CreateNewChildPage() throws Exception {

		sel.click(WikisObjects.Create_newChildPage_Link);
		Thread.sleep(2000);
	}

	public void VerifyUIOfPagesListing() throws Exception {

		//Ensure that all the elements are present on the displayed wiki's list	
		int ParsedDigit = getNumberOfPagesFromPagesIndex();

		if (ParsedDigit > 10) {
			AssertJUnit.assertTrue("FAIL: Top Next page link on Pages Index is missing", sel.isVisible(WikisObjects.Top_Next_Page_Link_Active));
			AssertJUnit.assertTrue("FAIL: Bottom Next page link on Pages Index is missing", sel.isVisible(WikisObjects.Bottom_Next_Page_Link_Active));
		}
		else {
			AssertJUnit.assertTrue("FAIL: Top Next page link on Pages Index is missing", sel.isVisible(WikisObjects.Top_Next_Page_Link_InActive));
			AssertJUnit.assertTrue("FAIL: Bottom Next page link on Pages Index is missing", sel.isVisible(WikisObjects.Bottom_Next_Page_Link_InActive));
		}
		AssertJUnit.assertTrue("FAIL: Top Previous page link on Pages Index is missing", sel.isVisible(WikisObjects.Top_Previous_Page_Link_InActive));
		AssertJUnit.assertTrue("FAIL: Bottom Previous page link on Pages Index is missing", sel.isVisible(WikisObjects.Bottom_Previous_Page_Link_InActive));
	}

	public int getNumberOfPagesFromPagesIndex() throws Exception {

		String TextToBeParsed = sel.getText(WikisObjects.Page_Index_Text_In_Top_Pagination);
		StringBuffer ParsedDigitInTextFormat = new StringBuffer();

		for (int index = 0; index < TextToBeParsed.length(); index++) {
			if (TextToBeParsed.charAt(index) == 'f') {
				int innerIndex = index + 2;

				while (innerIndex <= (TextToBeParsed.length() - 1)) {
					ParsedDigitInTextFormat.append(TextToBeParsed.charAt(innerIndex));
					innerIndex++;
				}
			}
		}

		return Integer.parseInt(ParsedDigitInTextFormat.toString());

	}

	public void VerifyPagingFunctionality(int NumberOfPages) throws Exception {

		//Method to check that numeric links (ie. 10, 25 & 50) are functioning
		int numRowsinMyWikiTable = 0, index = 1;

		while (sel.isElementPresent("[class='lotusTable lotusFixedTable qkrFixedWidth'] tr:nth-child(" + index + ") h4:nth-child(1)")) {
			numRowsinMyWikiTable++;
			index = index + 2;
		}

		System.out.println(numRowsinMyWikiTable);
		AssertJUnit.assertTrue("FAIL: Pages doesn't reflect correct number of wikis when '10' is selected", numRowsinMyWikiTable == 10);

		clickLink(WikisObjects.Link_toshow_25Wikis_perpage);
		AssertJUnit.assertTrue("FAIL: Pages doesn't reflect correct number of wikis when '25' is selected", numRowsinMyWikiTable == 25);

		clickLink(WikisObjects.Link_toshow_50Wikis_perpage);
		AssertJUnit.assertTrue("FAIL: Pages doesn't reflect correct number of wikis when '50' is selected", numRowsinMyWikiTable == NumberOfPages);
	}

	public void AddCommentsForPagesIndexSortingCheck(int MaxValue) throws Exception {

		//Click sort by name option on the Pages Index page
		this.clickObject(WikisObjects.Comments_Tab);

		for (int index = 1; index <= MaxValue; index++) {
			this.AddAComment("Comment" + index + " - adding to test sort by Most Comments functionality on Pages Index");
		}
	}

	public void clickPageInPagesIndex(String pageName) throws Exception {

		//Select a specific page in Pages Index page
		clickLink("link=" + pageName);
	}

	public void ReturnToLatestUpdatesTabandClickSecondTopWikiineNewWikisList() throws Exception {

		//Method to return to homepage & select next page in the navigation pane
		clickLink(WikisObjects.Public_Wikis_Tab);
		clickLink(WikisObjects.Wiki_Name_in_PublicWikisList_SecondTopCell);
	}

	public void CreateWiki(String WikiName, String TagName) throws InterruptedException {

		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(1000);
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(2000);
	}

	public void SelectMembershipRoleasOwnerforAddingUsersonAddMembersLightBox() throws Exception {

		//Select Membership Role as Owner for Adding Users on Add MembersLightBox
		sel.select(WikisObjects.Add_Members_Dropdown, "Owner");
		Thread.sleep(3000);
	}

	public void SelectMembershipRoleasEditorforAddingUsersonAddMembersLightBox() throws Exception {

		//Select Membership Role as Editor for Adding Users on AddMembersLightBox
		sel.select(WikisObjects.Add_Members_Dropdown, "Editor");
		Thread.sleep(3000);
	}

	public void SelectMembershipRoleasReaderforAddingUsersonAddMembersLightBox() throws Exception {

		//Select Membership Role as Reader for Adding Users on AddMembersLightBox
		sel.select(WikisObjects.Add_Members_Dropdown, "Reader");
		Thread.sleep(3000);
	}

	public void SelectMembershipRoleasOwnerforAddingGroupsonAddMembersLightBox() throws Exception {

		//Select Membership Role as Owner for Adding Groups on AddMembersLightBox
		sel.select(WikisObjects.Add_Groups_Dropdown, "Owner");
		Thread.sleep(3000);
	}

	public void SelectMembershipRoleasEditorforAddingGroupsonAddMembersLightBox() throws Exception {

		//Select Membership Role as Editor for Adding Groups on AddMembersLightBox
		sel.select(WikisObjects.Add_Groups_Dropdown, "Editor");
		Thread.sleep(3000);
	}

	public void SelectMembershipRoleasReaderforAddingGroupsonAddMembersLightBox() throws Exception {

		//Select Membership Role as Reader for Adding Groups on AddMembersLightBox
		sel.select(WikisObjects.Add_Groups_Dropdown, "Reader");
		Thread.sleep(3000);
	}

	public void verifyQuotaWarningMsg() throws Exception {

		//Verify that quota warning appears as expected
		AssertJUnit.assertTrue("FAIL: Quota warning is incorrect", sel.isTextPresent("You are nearly at you wiki quota"));
		Thread.sleep(2000);
	}

	public void verifyQuotaExcess() throws Exception {

		//Verify that quota excess is as expected
		AssertJUnit.assertTrue("FAIL: Behaviour isn't correct for quota excess", sel.isTextPresent("You are nearly at you wiki quota"));
		Thread.sleep(2000);
	}

	public void CreatePageAtRootLevel(String PageName, String PageContent) throws Exception {

		clickLink(WikisObjects.Create_New_Page_Link);
		sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
		Thread.sleep(2000);
		this.AddContentintheEditorforaNewPage(PageContent);
	}

	public void CreatePageAtChildLevel(String PageName, String PageContent) throws Exception {

		CreateNewChildPage();

		sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
		Thread.sleep(2000);
		this.AddContentintheEditorforaNewPage(PageContent);
	}

	public void AddContentintheEditorforaNewPage(String NewContentText) throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		clickLink(WikisObjects.Wiki_Text_tab);

		sel.type(WikisObjects.CKEditor_WikiTextTab, NewContentText);
		sel.selectFrame("relative=top");
		clickLink(WikisObjects.Save_and_Close_Link);
	}

	public void AddContentintheEditorforanExistingPage(String NewContentText) throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		clickLink(WikisObjects.Wiki_Text_tab);

		sel.type(WikisObjects.CKEditor_WikiTextTab, NewContentText);
		sel.selectFrame("relative=top");
		clickLink(WikisObjects.Save_and_Close_Link);
	}

	public void AddContentInEditorAndActivateAutosave(String NewContentText) throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		clickLink(WikisObjects.Wiki_Text_tab);

		clickLink(WikisObjects.CKEditor_WikiTextTab);

		sel.type(WikisObjects.CKEditor_WikiTextTab, NewContentText);
		Thread.sleep(20000);
	}

	public void AddContentInEditorWithoutActivatingAutosave(String NewContentText) throws Exception {

		AssertJUnit.assertTrue("FAIL: CKEditor isn't visible", sel.isElementPresent(WikisObjects.WikiText_Editor));

		//Click on the Wiki Text tab
		clickLink(WikisObjects.Wiki_Text_tab);

		sel.click(WikisObjects.CKEditor_WikiTextTab);
		Thread.sleep(2000);
		sel.type(WikisObjects.CKEditor_WikiTextTab, NewContentText);
		Thread.sleep(2000);
	}

	public void FunctionIfPageAlreadyExists() throws Exception {

		if (sel.isTextPresent("A page or file with the name you have specified already exists. Please choose a different name.")) {
			sel.click(WikisObjects.Cancel_Link);
			Thread.sleep(2000);

			//AssertJUnit.assertTrue("FAIL: on confirmation popup", sel.isConfirmationPresent());
			//AssertJUnit.assertTrue("FAIL: on confirmation text in popup", sel.getConfirmation().matches("^Are you sure you want to abandon your changes[\\s\\S]  Press OK to continue or cancel to return to editing\\.$"));
		}
	}

	public void ActivateTypeAheadForTagField(String BrowserType, String ValueForTypeAhead) throws Exception {

		//Verify that the type-ahead text is present
		if (BrowserType.equals("Netscape")) {
			sel.typeKeys(WikisObjects.Tags_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			sel.type(WikisObjects.Tags_Field, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.Tags_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
	}

	public void ActivateTypeAheadForGoToWikiSearchTextfieldBox(String BrowserType, String ValueForTypeAhead) throws Exception {

		sel.click(WikisObjects.GoToWiki_Search_Textfield);
		Thread.sleep(1000);

		//Verify that the type-ahead text is present
		if (BrowserType.equals("Netscape")) {
			sel.typeKeys(WikisObjects.GoToWiki_Search_Textfield, ValueForTypeAhead);
			Thread.sleep(3000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			sel.type(WikisObjects.GoToWiki_Search_Textfield, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.GoToWiki_Search_Textfield, ValueForTypeAhead);
			Thread.sleep(3000);
		}
	}

	/** Activate Members typeahead dropdown */
	public void ActivateTypeAheadForMembersField(String BrowserType, String ValueForTypeAhead) throws Exception {

		clickLink(WikisObjects.Members_Field);

		//Verify that the typeahead text is present
		if (BrowserType.equals("Netscape")) {
			clickLink(WikisObjects.Members_Field);
			sel.typeKeys(WikisObjects.Members_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			clickLink(WikisObjects.Members_Field);
			sel.type(WikisObjects.Members_Field, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.Members_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
	}

	/** Activate Groups typeahead dropdown */
	public void ActivateTypeAheadForGroupsField(String BrowserType, String ValueForTypeAhead) throws Exception {

		//Verify that typeahead text is present		
		if (BrowserType.equals("Netscape")) {
			clickLink(WikisObjects.Groups_Field);
			sel.typeKeys(WikisObjects.Groups_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			clickLink(WikisObjects.Groups_Field);
			sel.type(WikisObjects.Groups_Field, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.Groups_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
	}

	public void ActivateTypeAheadForEditedBy(String BrowserType, String ValueForTypeAhead) throws Exception {

		clickLink(WikisObjects.Search_EditedBy_Field);

		//Verify that the typeahead text is present
		if (BrowserType.equals("Netscape")) {
			sel.typeKeys(WikisObjects.Search_EditedBy_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			sel.type(WikisObjects.Search_EditedBy_Field, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.Search_EditedBy_Field, ValueForTypeAhead);
			Thread.sleep(2000);
		}
	}

	public void VerifyUIofFooterElementsonMyWikisMainpage() throws Exception {

		//Ensure that the footer headers and footer links are present and correct		
		AssertJUnit.assertTrue("FAIL: IBM Connections heading isn't present", sel.isTextPresent(WikisObjects.IBM_Lotus_Connections_Heading));
		AssertJUnit.assertTrue("FAIL: Home link isn't present", sel.isElementPresent(WikisObjects.Home_Link));
		AssertJUnit.assertTrue("FAIL: Demo link isn't present", sel.isElementPresent(WikisObjects.Demo_Link));

		AssertJUnit.assertTrue("FAIL: Help heading isn't present", sel.isTextPresent(WikisObjects.Help_Heading));
		AssertJUnit.assertTrue("FAIL: Help link isn't present", sel.isElementPresent(WikisObjects.Help_Link));
		AssertJUnit.assertTrue("FAIL: IBM Lotus Support Forums link isn't present", sel.isElementPresent(WikisObjects.IBMLotusSupportForums_Link));

		AssertJUnit.assertTrue("FAIL: Tools heading isn't present", sel.isTextPresent(WikisObjects.Tools_Heading));

		AssertJUnit.assertTrue("FAIL: About heading isn't present", sel.isTextPresent(WikisObjects.About_Heading));
		AssertJUnit.assertTrue("FAIL: About link isn't present", sel.isElementPresent(WikisObjects.About_Tab));
		AssertJUnit.assertTrue("FAIL: IBM Connections on ibm.com Link isn't present", sel.isElementPresent(WikisObjects.IBMLotusConnectionson_ibmcom_Link));
		AssertJUnit.assertTrue("FAIL: Submit Feedback link isn't present", sel.isElementPresent(WikisObjects.Submit_Feedback_Link));
	}

	public void VerifyUIofHeaderElementsonMyWikisMainpage() throws Exception {

		//Ensure that the Header Elements Links are present and correct		
		AssertJUnit.assertTrue("FAIL: Help link isn't present", sel.isElementPresent(WikisObjects.Help_Link));
		AssertJUnit.assertTrue("FAIL: Logout link isn't present", sel.isElementPresent(WikisObjects.Logout_Link));
		AssertJUnit.assertTrue("FAIL: Page Header Login Name isn't present", sel.isElementPresent(WikisObjects.Page_Header_Login_Name));

		AssertJUnit.assertTrue("FAIL: Page Header IBM Connections Image isn't present", sel.isElementPresent(WikisObjects.Page_Header_Lotus_Connections_Image));
		AssertJUnit.assertTrue("FAIL: Page Header Files Link isn't present", sel.isElementPresent(WikisObjects.Page_Header_Files_Link));
		AssertJUnit.assertTrue("FAIL: Page Header Wikis Link isn't present", sel.isElementPresent(WikisObjects.Page_Header_Wikis_Link));

		if (sel.isElementPresent(WikisObjects.PressedListIcon))
			AssertJUnit.assertTrue("FAIL: Table Display option isn't present", sel.isElementPresent(WikisObjects.UnPressedTableIcon));
		else AssertJUnit.assertTrue("FAIL: List Display option isn't present", sel.isElementPresent(WikisObjects.UnPressedTableIcon));
		System.out.println("PASS: All Page Header objects, links and images are present on the My Wikis Page.");
	}

	public void VerifyStartaNewWikiButtonFunctionalityonLatestUpdatestab() throws Exception {

		//Verifying that the Create Wiki Form is appeared and all the Elements are present on the Wiki Create Form
		AssertJUnit.assertTrue("FAIL: Mandatory Field Indicator is not Present", sel.isElementPresent(WikisObjects.Mandatory_Field_Indicator));
		AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Present", sel.isElementPresent(WikisObjects.Wiki_Name_Field));
		AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Editable", sel.isEditable(WikisObjects.Wiki_Name_Field));
		AssertJUnit.assertTrue("FAIL: Members Field is not Present", sel.isElementPresent(WikisObjects.Members_Field));
		AssertJUnit.assertTrue("FAIL: Members Field is not Editable", sel.isEditable(WikisObjects.Members_Field));
		AssertJUnit.assertTrue("FAIL: Description Field is not Present", sel.isElementPresent(WikisObjects.Description_Field));
		AssertJUnit.assertTrue("FAIL: Description Field is not Editable", sel.isEditable(WikisObjects.Description_Field));
		AssertJUnit.assertTrue("FAIL: Tags Field is not Present", sel.isElementPresent(WikisObjects.Tags_Field));
		AssertJUnit.assertTrue("FAIL: Tags Field is not Editable", sel.isEditable(WikisObjects.Tags_Field));
		AssertJUnit.assertTrue("FAIL: Create Wiki Button is not Present", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Cancel Link is not Present", sel.isElementPresent(WikisObjects.Cancel_Link));
		System.out
				.println("The Start Wiki Button on Wiki Service Main Page is Working.Create Wiki Light Box is present and all the Elements are Present and in the Right Place and the Wiki Name,Members,Description and Tags Fields are also Editable..");
		sel.goBack();
		Thread.sleep(1000);
	}

	public boolean checkComponent(String ComponentName) throws Exception {

		int index = 0;
		boolean found = false;
		//Verify that the dropdown item appears in the component list
		String[] components = { "Activities", "Blogs", "Bookmarks", "Communities", "Files", "Forums", "Profiles" };

		for (; index < components.length; index++) {
			if (components[index].equals(ComponentName)) found = true;
		}
		return found;
	}

	public void clickItemInSearchDropdown(String dropdownItem) throws Exception {

		int index = 0;
		String searchItem = "";

		Thread.sleep(100);
		for (; index < 200; index++) {
			if (sel.isElementPresent("dijit_MenuItem_" + index + "_text")) searchItem = sel.getText("dijit_MenuItem_" + index + "_text");
			if (searchItem.equals(dropdownItem)) break;
		}
		sel.clickAt("dijit_MenuItem_" + index + "_text", dropdownItem);
		Thread.sleep(3000);
	}

	public void clickAtItemInDropdown(String dropdownItem, String WikiName) throws Exception {

		sel.clickAt(dropdownItem, WikiName);
		Thread.sleep(2000);
	}

	public void validateErrorPage(boolean IsAuthenticated) throws Exception {

		sel.isTextPresent("We can't find that Wiki");
		sel.isTextPresent("The Wiki can't be found - click the back button and try again. If this doesn't work the wiki may have been deleted.");
		sel.isElementPresent(WikisObjects.BacktoWikis);
		if (!IsAuthenticated) sel.isElementPresent(WikisObjects.LinktotheLoginPage);
	}

	public void validatePageDisplayErrorPage(boolean IsAuthenticated) throws Exception {

		sel.isTextPresent("That page cannot be displayed.");
		sel.isElementPresent(WikisObjects.ReturnToApplication);
		if (!IsAuthenticated) sel.isElementPresent(WikisObjects.LinktotheLoginPage);
	}

	public void VerifySearchUIForAuthenticatedUserInPageView() throws Exception {

		int index = 8;
		String SearchOption = "";

		//Validate Search dropdown items in page view when authenticated
		String First_SearchOption = sel.getText(WikisObjects.First_Search_Dropdown_Option_inPageView);
		String Second_SearchOption = sel.getText(WikisObjects.Second_Search_Dropdown_Option_inPageView);
		String Third_SearchOption = sel.getText(WikisObjects.Third_Search_Dropdown_Option_inPageView);
		String Fourth_SearchOption = sel.getText(WikisObjects.Fourth_Search_Dropdown_Option_inPageView);
		String Fifth_SearchOption = sel.getText(WikisObjects.Fifth_Search_Dropdown_Option_inPageView);
		String Sixth_SearchOption = sel.getText(WikisObjects.Sixth_Search_Dropdown_Option_inPageView);

		AssertJUnit.assertTrue("FAIL: First Search option isn't 'Public Wikis' when you are in Page View", First_SearchOption.equals("Public Wikis"));
		AssertJUnit.assertTrue("FAIL: Second Search option isn't 'My Wikis' when you are in Page View", Second_SearchOption.equals("My Wikis"));
		AssertJUnit.assertTrue("FAIL: Third Search option isn't 'All Wiki' when you are in Page View", Third_SearchOption.equals("All Wikis"));
		AssertJUnit.assertTrue("FAIL: Fourth Search option isn't 'This Wiki' when you are in Page View", Fourth_SearchOption.equals("This Wiki"));
		AssertJUnit.assertTrue("FAIL: Fifth Search option isn't 'Go to Wiki...' when you are in Page View", Fifth_SearchOption.equals("Go to Wiki..."));
		AssertJUnit.assertTrue("FAIL: Sixth Search option isn't 'All Connections' when you are in Page View", Sixth_SearchOption.equals("All Connections"));

		while (sel.isElementPresent("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]")) {
			SearchOption = sel.getText("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]");
			AssertJUnit.assertTrue("FAIL: There is a problem with this Search option in Public Wikis dropdown", this.checkComponent(SearchOption));
			index++;
		}
		index++;
		String Last_SearchOption = sel.getText("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]");
		AssertJUnit.assertTrue("FAIL: Last Search option is not 'Advanced' search when you are in Public Wikis Tab", Last_SearchOption.equals("Advanced"));

	}

	public void VerifySearchUIForAnonymousUserInPageView() throws Exception {

		int index = 6;
		String SearchOption = "";

		//Verify the Options in the Search dropdown list
		String First_SearchOption = sel.getText(WikisObjects.First_Search_Dropdown_Option_inPageView_For_AnonymousUser);
		String Second_SearchOption = sel.getText(WikisObjects.Second_Search_Dropdown_Option_inPageView_For_AnonymousUser);
		String Third_SearchOption = sel.getText(WikisObjects.Third_Search_Dropdown_Option_inPageView_For_AnonymousUser);
		String Fourth_SearchOption = sel.getText(WikisObjects.Fourth_Search_Dropdown_Option_inPageView_For_AnonymousUser);

		AssertJUnit.assertTrue("FAIL: First Search option isn't 'Public Wikis' in Page View", First_SearchOption.equals("Public Wikis"));
		AssertJUnit.assertTrue("FAIL: Second Search option isn't 'This Wiki' in Page View", Second_SearchOption.equals("This Wiki"));
		AssertJUnit.assertTrue("FAIL: Third Search option isn't 'Go to Wiki...' in Page View", Third_SearchOption.equals("Go to Wiki..."));
		AssertJUnit.assertTrue("FAIL: Fourth Search option isn't 'All Connections' in Page View", Fourth_SearchOption.equals("All Connections"));

		while (sel.isElementPresent("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]")) {
			SearchOption = sel.getText("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]");
			AssertJUnit.assertTrue("FAIL: There is a problem with this Search option in Public Wikis dropdown", this.checkComponent(SearchOption));
			index++;
		}
		index++;
		String Last_SearchOption = sel.getText("xpath=//table[@class='dijit dijitMenu dijitReset dijitMenuTable lotusActionMenu']/tbody/tr[" + index + "]/td[2]");
		AssertJUnit.assertTrue("FAIL: Last Search option is not 'Advanced' search when you are in Public Wikis Tab", Last_SearchOption.equals("Advanced"));
	}

	public void VerifyStartAWikiButtonFunctionality() throws Exception {

		//Verifying that the Create Wiki Light Box is appeared and all the Elements are present on the Craete Wiki Light Box	
		//Click on Add Groups Link to open the Drop down and the Groups Field to Add Groups
		sel.click(WikisObjects.AddGroups_Link);
		Thread.sleep(1000);

		AssertJUnit.assertTrue("FAIL: Mandatory Field Indicator is not Present", sel.isElementPresent(WikisObjects.Mandatory_Field_Indicator));
		AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Present", sel.isElementPresent(WikisObjects.Wiki_Name_Field));
		AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Editable", sel.isEditable(WikisObjects.Wiki_Name_Field));
		AssertJUnit.assertTrue("FAIL: Members Field is not Present", sel.isElementPresent(WikisObjects.Members_Field));
		AssertJUnit.assertTrue("FAIL: Members Field is not Editable", sel.isEditable(WikisObjects.Members_Field));
		AssertJUnit.assertTrue("FAIL: Groups Field is not Present", sel.isElementPresent(WikisObjects.Groups_Field));
		AssertJUnit.assertTrue("FAIL: Groups Field is not Editable", sel.isEditable(WikisObjects.Groups_Field));
		AssertJUnit.assertTrue("FAIL: Description Field is not Present", sel.isElementPresent(WikisObjects.Description_Field));
		AssertJUnit.assertTrue("FAIL: Description Field is not Editable", sel.isEditable(WikisObjects.Description_Field));
		AssertJUnit.assertTrue("FAIL: Tags Field is not Present", sel.isElementPresent(WikisObjects.Tags_Field));
		AssertJUnit.assertTrue("FAIL: Tags Field is not Editable", sel.isEditable(WikisObjects.Tags_Field));
		AssertJUnit.assertTrue("FAIL: Create Wiki Button is not Present", sel.isElementPresent(WikisObjects.Save_Button));
		AssertJUnit.assertTrue("FAIL: Cancel Link is not Present", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void VerifyPagingFunctionalityonMyWikisMainpage() throws Exception {

		//Check that the links to show 10, 25 & 50 wikis per page are working
		if (!sel.isTextPresent(WikisObjects.No_Wikis_In_List)) {
			int numRowsinMyWikiTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));

			AssertJUnit.assertTrue("FAIL: My Wikis Page isn't showing correct no of wikis per page when we enter MyWikis page for the First Time", numRowsinMyWikiTable == 20);

			sel.click(WikisObjects.Link_toshow_25Wikis_perpage);
			Thread.sleep(2000);
			numRowsinMyWikiTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
			AssertJUnit.assertTrue("FAIL: My Wikis Page isn't showing correct no of wikis per page when we clicked the link to show 25 Wikis per page", numRowsinMyWikiTable == 50);

			sel.click(WikisObjects.Link_toshow_50Wikis_perpage);
			Thread.sleep(2000);
			numRowsinMyWikiTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
			AssertJUnit
					.assertTrue("FAIL: My Wikis Page isn't showing correct no of wikis per page when we clicked the link to show 50 Wikis per page", numRowsinMyWikiTable == 100);
		}
	}

	public void VerifyUIofFooterElementsonWikiServiceMainpage() throws Exception {

		//Ensure that the Footer Headers and Footer links are present and correct		
		//AssertJUnit.assertTrue("FAIL: IBM Connections Heading is not Present", sel.isTextPresent(Objects.IBM_Lotus_Connections_Heading));
		//AssertJUnit.assertTrue("FAIL: Help Heading is not Present", sel.isTextPresent(Objects.Help_Heading));
		//AssertJUnit.assertTrue("FAIL: Tools Heading is not Present", sel.isTextPresent(Objects.Tools_Heading));
		//AssertJUnit.assertTrue("FAIL: About Heading is not Present", sel.isTextPresent(Objects.About_Heading));
		AssertJUnit.assertTrue("FAIL: Home Link is not Present", sel.isElementPresent(WikisObjects.Home_Link));
		AssertJUnit.assertTrue("FAIL: Demo Link is not Present", sel.isElementPresent(WikisObjects.Demo_Link));
		AssertJUnit.assertTrue("FAIL: Help Link is not Present", sel.isElementPresent(WikisObjects.Help_Link));
		AssertJUnit.assertTrue("FAIL: IBM Lotus Support Forums Link is not Present", sel.isElementPresent(WikisObjects.IBMLotusSupportForums_Link));
		AssertJUnit.assertTrue("FAIL: About Link is not Present", sel.isElementPresent(WikisObjects.About_Tab));
		AssertJUnit.assertTrue("FAIL: IBM Connections on ibm.com Link is not Present", sel.isElementPresent(WikisObjects.IBMLotusConnectionson_ibmcom_Link));
		AssertJUnit.assertTrue("FAIL: Submit Feedback Link is not Present", sel.isElementPresent(WikisObjects.Submit_Feedback_Link));
	}

	public void VerifyUIofHeaderElementsonWikiServiceMainpage() throws Exception {

		//Ensure that the Header Elements Links are present and correct		
		AssertJUnit.assertTrue("FAIL: Help Link is not Present", sel.isElementPresent(WikisObjects.Help_Link));
		AssertJUnit.assertTrue("FAIL: Logout Link is not Present", sel.isElementPresent(WikisObjects.Logout_Link));
		AssertJUnit.assertTrue("FAIL: Page Header IBM Connections Image is not Present", sel.isElementPresent(WikisObjects.Page_Header_Lotus_Connections_Image));
		//AssertJUnit.assertTrue("FAIL: Page Header Files Link is not Present",sel.isElementPresent(Objects.Page_Header_Files_Link));
		//AssertJUnit.assertTrue("FAIL: Page Header Wikis Link is not Present",sel.isElementPresent(Objects.Page_Header_Wikis_Link));
		AssertJUnit.assertTrue("FAIL: Page Header Login Name is not Present", sel.isElementPresent(WikisObjects.Page_Header_Login_Name));

	}

	public void VerifyUIofTabsandWelcomeSectiononWikiServiceMainpage() throws Exception {

		//Verify the Tabs and Welcome Section on Wiki Service Main Page
		AssertJUnit.assertTrue("FAIL: Public Wikis tab is not Present", sel.isElementPresent(WikisObjects.Public_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: MyWikis tab is not Present", sel.isElementPresent(WikisObjects.My_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: Welcome Section is not Present", sel.isElementPresent(WikisObjects.Welcome_Section));
		AssertJUnit.assertTrue("FAIL: Learn More Button is not Present", sel.isElementPresent(WikisObjects.Learn_More_Button));
		AssertJUnit.assertTrue("FAIL: Watch Demo Button is not Present", sel.isElementPresent(WikisObjects.Watch_Demo_Button));
		AssertJUnit.assertTrue("FAIL: Start a New Wiki Buton is not Present", sel.isElementPresent(WikisObjects.Start_New_Wiki_Button));

	}

	public void VerifyPublicTagsUI() throws Exception {

		//Ensure that all the elements under Public Tags heading are visible
		AssertJUnit.assertTrue("FAIL: Public Tag Search section isn't visible", sel.isElementPresent(WikisObjects.Public_Tags_Search_Section));
		AssertJUnit.assertTrue("FAIL: Public Tag Search field Search icon isn't visible", sel.isElementPresent(WikisObjects.Public_Tag_Search_Field_SearchIcon));
		AssertJUnit.assertTrue("FAIL: Public Tag Search field Tooltip isn't visible", sel.isElementPresent(WikisObjects.Tooltip_for_Public_Tags_Section));
		VerifyRemainingTagsUI();
	}

	public void VerifyMyTagsUI() throws Exception {

		//Ensure that all the elements under Tags heading are visible & functional in My Wikis
		AssertJUnit.assertTrue("FAIL: My Tag heading is incorrect", "Tags".equals(sel.getText(WikisObjects.Tags_Section_Title)));
		AssertJUnit.assertTrue("FAIL: My Tag expand/collapse icon isn't functioning correctly", ValidateOfExpandCollapseOfTagsUI());

		AssertJUnit.assertTrue("FAIL: My Tag Search section isn't visible", sel.isElementPresent(WikisObjects.My_Tags_Search_Section));
		AssertJUnit.assertTrue("FAIL: My Tag Search field Search icon isn't visible", sel.isElementPresent(WikisObjects.My_Tag_Search_Field_SearchIcon));
		AssertJUnit.assertTrue("FAIL: My Tag Search field Tooltip isn't visible", sel.isElementPresent(WikisObjects.Tooltip_for_My_Tags_Section));
		VerifyRemainingTagsUI();
	}

	public boolean verifyTopTaggedTimesValue(int taggedValue) throws Exception {

		boolean valueCorrect = false;

		//Return the number of times the top tag has been tagged
		String taggedTimes = sel.getText(WikisObjects.No_oftimes_Tagused_attheTop_of_OrderedList);
		int numberTaggedTimes = Integer.parseInt(taggedTimes);

		if (numberTaggedTimes == taggedValue) valueCorrect = true;

		return valueCorrect;
	}

	public void VerifyRemainingTagsUI() throws Exception {

		//Ensure that all the remaining elements under the Tags heading are visible
		sel.click(WikisObjects.Link_for_Find_a_Tag);
		Thread.sleep(1000);

		AssertJUnit.assertTrue("FAIL: Tag Search field isn't visible", sel.isElementPresent(WikisObjects.Tag_Search_Textfield));

		if (sel.isElementPresent(WikisObjects.Link_for_Tag_List)) {
			sel.click(WikisObjects.Link_for_Tag_List);
			Thread.sleep(1000);
		}
		if (!sel.isTextPresent(WikisObjects.Tags_NoTags_Text))
			AssertJUnit.assertTrue("FAIL: Link for Tag cloud isn't visible", sel.isElementPresent(WikisObjects.Link_for_Tag_Cloud));
	}

	public void VerifyBacktoWikisLinkFunctionalityonUnAuthorisedAccesspage() throws Exception {

		//Verify the Back to Wikis link functionality on the unauthorised access page
		AssertJUnit.assertTrue("FAIL: Public Wikis tab isn't visible", sel.isElementPresent(WikisObjects.Public_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: My Wikis tab isn't visible", sel.isElementPresent(WikisObjects.My_Wikis_Tab));
		AssertJUnit.assertTrue("FAIL: Public Wikis list isn't visible", sel.isElementPresent(WikisObjects.Public_Wikis_List));
	}

	public void VerifythatthePageisOpenedinEditMode() throws Exception {

		//Method to verify that the Page is opened in Edit Mode
		AssertJUnit.assertTrue("FAIL: CK Editor isn't visible", sel.isElementPresent(WikisObjects.CKEditor));
		AssertJUnit.assertTrue("FAIL: Save and Close button isn't visible", sel.isElementPresent(WikisObjects.Save_and_Close_Link));
		AssertJUnit.assertTrue("FAIL: Cancel button isn't visible", sel.isElementPresent(WikisObjects.Cancel_Link));
		AssertJUnit.assertTrue("FAIL: Save button isn't visible", sel.isElementPresent(WikisObjects.Save_Link));
	}

	public void VerifytheUIofCreateaNewWikiPage() throws Exception {

		//Verify the UI of the create new Wiki page
		AssertJUnit.assertTrue("FAIL: Page Name Field isn't present", sel.isElementPresent(WikisObjects.New_Page_Title_Textfield));
		AssertJUnit.assertTrue("FAIL: CK Editor isn't present", sel.isElementPresent(WikisObjects.CKEditor));
		AssertJUnit.assertTrue("FAIL: Link to add the Tag isn't present", sel.isElementPresent(WikisObjects.Add_tags_Link));
		AssertJUnit.assertTrue("FAIL: Save and Close button isn't present", sel.isElementPresent(WikisObjects.Save_and_Close_Link));
		AssertJUnit.assertTrue("FAIL: Cancel button isn't present", sel.isElementPresent(WikisObjects.Cancel_Link));
		AssertJUnit.assertTrue("FAIL: Save button isn't present", sel.isElementPresent(WikisObjects.Save_Link));
	}

	public void TypethePageNameinPageNameField(String PageName) throws Exception {

		//Method to Type the Page Name in the Page Name Field
		sel.type(WikisObjects.New_Page_Title_Textfield, PageName);
		Thread.sleep(2000);
	}

	public void VerifyNewPageCreation(String PageName, String PageContent) throws Exception {

		//Verify that the New Page is created
		Thread.sleep(2000);

		AssertJUnit.assertTrue("FAIL: Page is created but page title information isn't visible", sel.isTextPresent(PageName));
		AssertJUnit.assertTrue("FAIL: Page is created but tagging information isn't visible", sel.isElementPresent(WikisObjects.Add_tags_Link));
		AssertJUnit.assertTrue("FAIL: Page is created but breadcrumb information isn't visible", sel.isElementPresent(WikisObjects.All_Breadcrumb_Text));

		AssertJUnit.assertTrue("FAIL: Page title isn't correct", PageName.equals(sel.getText(WikisObjects.WikiHomePageTitleField)));

		//Verify whether page content is visible
		AssertJUnit.assertTrue("FAIL: Create New Page functionality is incorrect. Page content isn't displayed", sel.isTextPresent(PageContent));
	}

	public void VerifyUIofUnAuthorisedAccessofMyWikisPage() throws Exception {

		//Verify the UI of the UnAuthorised Access page of MyWikis Page
		AssertJUnit.assertTrue("FAIL: The Error Message Heading is not Present", sel.isElementPresent(WikisObjects.ErrorMessageHeading));
		AssertJUnit.assertTrue("FAIL: The Link to the Login Page is not Present", sel.isElementPresent(WikisObjects.LinktotheLoginPage));
		AssertJUnit.assertTrue("FAIL: The Link to the Wiki Main Page is not Present", sel.isElementPresent(WikisObjects.BacktoWikis));
	}

	public void VerifytheUIofUnAuthorisedAccesstoCreatingaNewWikiPage() throws Exception {

		//Verify the UI of the UnAuthorised Access page to Creating a New Wiki Page
		AssertJUnit.assertTrue("FAIL: The Error Message Heading isn't present", sel.isElementPresent(WikisObjects.ErrorMessageHeading));
		AssertJUnit.assertTrue("FAIL: The Error Message Text saying You can't create a new page isn't present", sel.isTextPresent("You can't create a new page"));
		AssertJUnit.assertTrue("FAIL: The Link to the Login Page isn't present", sel.isElementPresent(WikisObjects.BacktoPage));
		AssertJUnit.assertTrue("FAIL: The Link to the Wiki Main Page isn't present", sel.isElementPresent(WikisObjects.BacktoWikis));

	}

	public void VerifytheUIofUnAuthorisedAccesstoEditaWikiPage() throws Exception {

		//Verify the UI of the UnAuthorised Access page to Edit a Wiki Page
		AssertJUnit.assertTrue("FAIL: The Error Message Heading isn't present", sel.isElementPresent(WikisObjects.ErrorMessageHeading));
		AssertJUnit.assertTrue("FAIL: The Error Message Text saying You Can't Edit this Page isn't present", sel.isTextPresent("You can't edit this page"));
		AssertJUnit.assertTrue("FAIL: The Link to the Login Page isn't present", sel.isElementPresent(WikisObjects.BacktoPage));
		AssertJUnit.assertTrue("FAIL: The Link to the Wiki Main Page isn't present", sel.isElementPresent(WikisObjects.BacktoWikis));
	}

	public void ClickonSortbyNameOptiononWikisTableView() throws Exception {

		//Click the Sort by Name Option on Wikis Table View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Name_Option)) {
					sel.click(WikisObjects.Sortby_Name_Option);
					Thread.sleep(2000);
				}
				break;

			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void ClickonSortbyNameOptiononWikisListView() throws Exception {

		//Click the Sort by Name Option on Wikis List View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Name_Option)) {
					sel.click(WikisObjects.Sortby_Name_Option);
					Thread.sleep(2000);
				}
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void ClickonSortbyMostRecentOptiononWikisTableView() throws Exception {

		//Click the Sort by Most Recent Option on the Wikis Table View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Updated_Option)) {
					sel.click(WikisObjects.Sortby_Updated_Option);
					Thread.sleep(2000);
				}
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void ClickonSortbyAddedOptiononWikisTableView() throws Exception {

		//Click the Sort by Added Option on Wikis Table View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Created_Option)) {
					sel.click(WikisObjects.Sortby_Created_Option);
					Thread.sleep(2000);
				}
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void ClickonSortbyAddedOptiononWikisListView() throws Exception {

		//Click the Sort by Added Option on Wikis List View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Created_Option)) {
					sel.click(WikisObjects.Sortby_Created_Option);
					Thread.sleep(2000);
				}
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void ClickonSortbyUpdatedOptiononWikisListView() throws Exception {

		//Click the Sort by Updated Option on Wikis List View
		for (int second = 0;; second++) {
			if (second >= 30) Assert.fail("TIMEOUT ERROR: Tag Filter list is not displaying after 30 seconds");
			try {
				if (sel.isElementPresent(WikisObjects.Sortby_Updated_Option)) {
					sel.click(WikisObjects.Sortby_Updated_Option);
					Thread.sleep(2000);
				}
				break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	public void SetUptheHomePageforWikiwithOnlyOwnerasMemberfortheWiki() throws InterruptedException {

		//Setup the Home Page for the Wiki Which has Owner as the Only Member 
		try {
			sel.open(sConfig.browserURL + "wikis/home?lang=en#/wiki/Wiki_with_NoMembers_Except_Owner/page/Wiki_with_NoMembers_Except_Owner");
			sel.waitForPageToLoad("5000");
		} catch (Exception e) {
		}
	}

	public void VerifyTagsSectionUIOnPageView() throws Exception {

		if (!sel.isTextPresent(WikisObjects.Tags_NoTags_Text)) {
			//Ensure all elements under Tags Heading are visible
			if (sel.isElementPresent(WikisObjects.Link_for_Find_a_Tag)) clickLink(WikisObjects.Link_for_Find_a_Tag);
			AssertJUnit.assertTrue("FAIL: Tag Search field is missing", sel.isElementPresent(WikisObjects.Tag_Search_Textfield));
			AssertJUnit.assertTrue("FAIL: Tag Search field Submit button is missing", sel.isElementPresent(WikisObjects.Tag_Search_Field_SubmitButton));

			CheckTagsLinks();
		}
	}

	public void ActivateTypeAheadonTagsSearchField(String BrowserType, String ValueForTypeAhead) throws Exception {

		//Verify that the type-ahead text is present
		if (BrowserType.equals("Netscape")) {
			sel.typeKeys(WikisObjects.Tag_Search_Textfield, ValueForTypeAhead);
			Thread.sleep(2000);
		}
		else if (BrowserType.equals("Microsoft Internet Explorer")) {
			sel.type(WikisObjects.Tag_Search_Textfield, ValueForTypeAhead);
			sel.typeKeys(WikisObjects.Tag_Search_Textfield, ValueForTypeAhead);
			Thread.sleep(2000);
		}
	}

	public void VerifytheUIofUnAuthorisedAccesstoaWikiHomePagebyEnteringtheURLfortheWikiHomePage() throws Exception {

		//Verify the UI of the UnAuthorised Access to a Wiki Home page 
		AssertJUnit.assertTrue("FAIL: The Error Message Heading is not present", sel.isElementPresent(WikisObjects.ErrorMessageHeading));
		AssertJUnit.assertTrue("FAIL: The Error Message Heading is not present", sel.isTextPresent("You do not have access to this page"));
		AssertJUnit.assertTrue("FAIL: The Link to the Wiki Main Page is not present", sel.isElementPresent(WikisObjects.BacktoWikis));
		System.out.println("The Wiki is displaying the Error Message Properly when we try to access a Wiki Home Page Illlegaly by entering the URL for thw Wiki Home Page");
	}

	public void SetUptheCreateRootPageforWikiwithRolesAssignedtoIndividualUsers() throws InterruptedException {

		//Setup the Home Page for the Wiki Which has Owner as the Only Member 

		//Changed the sel.open from using the full URL that would only work on Satya's machine
		//to use variable for the URL which is set in Objects. 
		try {
			sel.open(sConfig.browserURL + "wikis/home?lang=en#/wiki/Wiki1_with_MembersSelected_for_DifferentRoles/pages/create?rel=root");
			sel.waitForPageToLoad("5000");
		} catch (Exception e) {
		}
	}

	public void SetUptheCreateChildPageforWikiwithRolesAssignedtoIndividualUsers() throws InterruptedException {

		//setup the Home Page for the Wiki Which has Owner as the Only Member 

		//Changed the sel.open from using the full URL that would only work on Satya's machine
		//to use variable for the URL which is set in Objects.
		try {
			sel.open(sConfig.browserURL + "wikis/home?lang=en#/wiki/Wiki1_with_MembersSelected_for_DifferentRoles/pages/create");
			sel.waitForPageToLoad("5000");
		} catch (Exception e) {
			//System.err.println("ERROR: was the page loaded correctly????");
		}
	}

	public void SetUptheCreatePeerPageforWikiwithRolesAssignedtoIndividualUsers() throws InterruptedException {

		//setup the Home Page for the Wiki Which has Owner as the Only Member 

		//Changed the sel.open from using the full URL that would only work on Satya's machine
		//to use variable for the URL which is set in Objects.
		try {
			sel.open(sConfig.browserURL + "wikis/home?lang=en#/wiki/Wiki1_with_MembersSelected_for_DifferentRoles/pages/create?rel=sibling");
			sel.waitForPageToLoad("5000");
		} catch (Exception e) {
			//System.err.println("ERROR: was the page loaded correctly????");
		}
	}

	public void VerifyUIofUnAuthorisedAccessofCreateChildPagebyAnonymousUser() throws Exception {

		//Verify the UI of the UnAuthorised Access page of Create Child Page by an Anonymous User
		AssertJUnit.assertTrue("FAIL: Error message heading isn't visible", sel.isElementPresent(WikisObjects.ErrorMessageHeading));
		AssertJUnit.assertTrue("FAIL: Error message heading text isn't correct", sel.isTextPresent("Certain portions of Wikis can only be accessed when you are logged in."));
		AssertJUnit.assertTrue("FAIL: Login link isn't visible", sel.isElementPresent(WikisObjects.LinktotheLoginPage));
		AssertJUnit.assertTrue("FAIL: Back to Wikis link isn't visible", sel.isElementPresent(WikisObjects.BacktoWikis));
		System.out.println("The Wiki is displaying the Error Message Properly when Anonymous User try to access the Create New Child Page Illlegaly by entering the URL");
	}

	public void VerifyUIofPageViewforaReaderoftheWiki() throws Exception {

		//Verify the UI of the Page View for a User who is a Reader for the Wiki
		AssertJUnit.assertFalse("FAIL: Add Tags or Add or Remove Tags link shouldn't be visible since a Reader has no privilege to Add Tags for the Wiki", sel
				.isElementPresent(WikisObjects.Add_or_RemoveTags_Link)
				|| sel.isElementPresent(WikisObjects.Add_Tag_Link));
		AssertJUnit.assertFalse("FAIL: Create New Page Link shouldn't be visible since a Reader has no privilege to Add New Pages to the Wiki", sel
				.isElementPresent(WikisObjects.Create_New_Page_Link));
		AssertJUnit
				.assertTrue("FAIL: Index Link isn't displayed, a Reader has the privilege View Pages Index Sections for the Wiki", sel.isElementPresent(WikisObjects.Index_Link));
		AssertJUnit.assertTrue("FAIL: Members link isn't displayed, a Reader has the privilege to View Members Page", sel.isElementPresent(WikisObjects.Members_Link));
		AssertJUnit.assertTrue("FAIL: Members section isn't displayed, a Reader has the privilege to access the Members page", sel
				.isElementPresent(WikisObjects.Members_Expand_Collapse_Arrow));

		AssertJUnit.assertTrue("FAIL: Comments Tab isn't displayed", sel.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions Tab isn't displayed", sel.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments Tab isn't displayed", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About Tab isn't displayed", sel.isElementPresent(WikisObjects.About_Tab));

		AssertJUnit.assertTrue("FAIL: Recommend link isn't displayed, a Reader has the privilege to Recommend a Wiki Page.", sel.isElementPresent(WikisObjects.Recommend_Link));
		AssertJUnit.assertTrue("FAIL: Recommend image isn't displayed, a Reader has the privilege to Recommend a Wiki Page.", sel.isElementPresent(WikisObjects.Recommend_Image));
		AssertJUnit.assertTrue("FAIL: Navigation table isn't displayed, a Reader has the privilege to View the Navigation Table of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Table));
		AssertJUnit.assertTrue("FAIL: Navigation tree isn't displayed, a Reader has the privilege to View the Navigation Tree of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Tree));
		AssertJUnit.assertTrue("FAIL: 'Subscribe to this page' link isn't displayed, a Reader has the privilege to View the Subscribe to this Page Link.", sel
				.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));
		AssertJUnit.assertTrue("FAIL: 'Subscribe to these comments' link isn't displayed, a Reader has the privilege to Subscribe to these comments", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Comments_Link));
		AssertJUnit.assertTrue("FAIL: Page Actions Button isn't displayed, a Reader has the privilege to Work with Options in Page Actions.", sel
				.isElementPresent(WikisObjects.Page_Actions_Button_For_Readers));
		AssertJUnit.assertFalse("FAIL: Wiki Actions Button shouldn't be visible since a Reader has no privilege to Access these Options", sel
				.isElementPresent(WikisObjects.Wiki_Actions_Button));
		AssertJUnit.assertFalse("FAIL: Edit button shouldn't appear", sel.isElementPresent(WikisObjects.Edit_Button));

		//Click Follow button menu	
		clickLink(WikisObjects.Follow_Button);

		//Verify that it is displaying Follow this page and Follow this Wiki options for the Reader		
		this.ValidateFollowMenuItems();

		//Verify the UI of Tags section.
		this.VerifyTagsSectionUIOnPageView();

		//click on Index Link and verify UI of Pages & Date Expand & Collapse sections.
		this.clickLink(WikisObjects.Index_Link);
		this.VerifyUIofDateExpandAndCollapseMenuonPageView();
		this.VerifyUIofPagesExpandAndCollapseMenuonPageView();

		//To go back to Page view
		sel.goBack();
		Thread.sleep(5000);

		//Click on Page Actions and verify that it is displaying Print Page Option for the Reader	
		this.clickLink(WikisObjects.Page_Actions_Button_For_Readers);
		this.ValidatePageActionsItems(Data.Reader_Role);

		//Click on Versions Tab which is in the Page Footer.
		this.clickLink(WikisObjects.Versions_Tab);
		AssertJUnit.assertTrue("FAIL: 'Subscribe to these page versions' link isn't displayed, a Reader has the privilege to Subscribe to these page versions", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Page_Versions_Link));

		//Click on Attachments Tab and verify that it isn't displaying Add an Attachment Link for the Reader
		this.clickLink(WikisObjects.Attachments_Tab);
		AssertJUnit.assertTrue("FAIL: 'Subscribe to these attachments' link isn't displayed, a Reader has the privilege to Subscribe to these attachments", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Attachments_Link));
		AssertJUnit.assertTrue("FAIL: Attachments tab title isn't displayed but should be for a member with Reader access", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertFalse("FAIL: Add an Attachment Link should not be Present.Since a Reader has no privilege to Add Attachments for the Page.", sel
				.isElementPresent(WikisObjects.AddanAttachment_Link));
	}

	public void VerifyUIofPagesExpandAndCollapseMenuonPageView() throws Exception {

		//First click on Pages Expand and Collpase section to open it.
		this.clickLink(WikisObjects.Pages_Expand_Collapse_Arrow);
		AssertJUnit.assertTrue("FAIL: Go to page... link isn't displayed, an user has the privilege to jump to any page in the wiki", sel
				.isElementPresent(WikisObjects.Go_To_Page_Link));
		AssertJUnit.assertTrue("FAIL: I Edited Link isn't displayed, a user has the privilige to view the pages that he has edited before.", sel
				.isElementPresent(WikisObjects.IEdited_Link));
		AssertJUnit.assertTrue("FAIL: Edited By... link isn't displayed", sel.isElementPresent(WikisObjects.EditedBy_Link));
	}

	public void VerifyUIofDateExpandAndCollapseMenuonPageView() throws Exception {

		//First Click on Date Expand and Collpase section to open it.
		this.clickLink(WikisObjects.Date_Expand_Collapse_Arrow);
		AssertJUnit.assertTrue("FAIL: Today link isn't displayed, an user has the privilege to view pages that are updated Today in the wiki", sel
				.isElementPresent(WikisObjects.Date_Links_Today));
		AssertJUnit.assertTrue("FAIL: Last week link isn't displayed, an user has the privilige to view pages that are updated in the last week in the Wiki.", sel
				.isElementPresent(WikisObjects.Date_Links_Last_Week));
		AssertJUnit.assertTrue("FAIL: Last 30 days link isn't displayed, an user has the privilige to view pages that are updated in last 30 days in the Wiki.", sel
				.isElementPresent(WikisObjects.Date_Links_Last_30_Days));
		AssertJUnit.assertTrue("FAIL: Last year link isn't displayed, an user has the privilige to view pages that are updated in the last year in the Wiki.", sel
				.isElementPresent(WikisObjects.Date_Links_Last_Year));
	}

	public void VerifyUIofPageViewforanEditoroftheWiki() throws Exception {

		//Verify the UI of the Page View for a User who is an Editor for the Wiki
		AssertJUnit.assertTrue("FAIL: Add Tags or Add or Remove Tags link is not displayed, an Editor has the privilege to Add Tags for the Wiki.", (sel
				.isElementPresent(WikisObjects.Add_or_RemoveTags_Link))
				|| (sel.isElementPresent(WikisObjects.Add_tags_Link)));
		AssertJUnit.assertTrue("FAIL: Create New Page Link is not displayed, an Editor has the privilege to Add New Pages to the Wiki.", sel
				.isElementPresent(WikisObjects.Create_New_Page_Link));
		AssertJUnit.assertTrue("FAIL: Index Link is not displayed, an Editor has the privilege to view Pages Index section", sel.isElementPresent(WikisObjects.Index_Link));
		AssertJUnit.assertTrue("FAIL: Members link is not displayed, an Editor has the privilege to View members for the Wiki.", sel.isElementPresent(WikisObjects.Members_Link));
		AssertJUnit.assertTrue("FAIL: Members section isn't displayed, a Editor has the privilege to access the Members page", sel
				.isElementPresent(WikisObjects.Members_Expand_Collapse_Arrow));

		AssertJUnit.assertTrue("FAIL: Comments Tab is not displayed, an Editor has the privilege to Add Comments for the Wiki.", sel.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions Tab is not displayed, an Editor has the privilege to view page versions", sel.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments Tab is not displayed, an Editor has the privilege to View the Attachments for the Page.", sel
				.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About Page Tab is not displayed, an Editor has the privilege to View About Tab Info.", sel.isElementPresent(WikisObjects.About_Tab));

		AssertJUnit.assertTrue("FAIL: Recommend Image isn't displayed, an Editor has the privilege to Recommend a Wiki Page.", sel.isElementPresent(WikisObjects.Recommend_Image));
		AssertJUnit.assertTrue("FAIL: Recommend Link isn't displayed, an Editor has the privilege to Recommend a Wiki Page.", sel.isElementPresent(WikisObjects.Recommend_Link));
		AssertJUnit.assertTrue("FAIL: Navigation Table is not displayed, an Editor has the privilege to View the Navigation Table of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Table));
		AssertJUnit.assertTrue("FAIL: Navigation Tree is not displayed, an Editor has the privilege to View the Navigation Tree of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Tree));
		AssertJUnit.assertTrue("FAIL: 'Subscribe to this page' link is Not displayed, an Editor has the privilege to Subscribe for the Page.", sel
				.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));

		AssertJUnit.assertTrue("FAIL: Edit Button is not displayed, an Editor has the privilege to Edit the Wiki.", sel.isElementPresent(WikisObjects.Edit_Button));
		AssertJUnit.assertTrue("FAIL: Page Actions Button is not displayed, an Editor has the privilege to Work with the Options in Page Actions.", sel
				.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors));
		AssertJUnit.assertFalse("FAIL: Wiki Actions Button shouldn't be visible since an Editor has no Privilege to Access these options", sel
				.isElementPresent(WikisObjects.Wiki_Actions_Button));

		AssertJUnit.assertTrue("FAIL: 'Subscribe to these 'comments' link isn't displayed, but an Editor has the privilege to Subscribe to page comments", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Comments_Link));

		//Click Follow button menu	
		clickLink(WikisObjects.Follow_Button);

		//Verify that it is displaying Follow this page and Follow this Wiki options for the Editor	
		this.ValidateFollowMenuItems();

		//click on Index Link and verify UI of Pages & Date Expand & Collapse sections.
		this.clickLink(WikisObjects.Index_Link);
		this.VerifyUIofPagesExpandAndCollapseMenuonPageView();
		this.VerifyUIofDateExpandAndCollapseMenuonPageView();

		//go back to Page view
		sel.goBack();
		Thread.sleep(3000);

		this.VerifyTagsSectionUIOnPageView();

		//Click on Page Actions	
		clickLink(WikisObjects.Page_Actions_Button_For_Editors);

		//Verify its displaying all required options for an editor
		this.ValidatePageActionsItems(Data.Editor_Role);

		//Click on the Versions Tab which is in the Page Footer.
		clickLink(WikisObjects.Versions_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these page versions link isn't displayed", sel.isElementPresent(WikisObjects.Subscribe_To_These_Page_Versions_Link));

		//Click on Attachments Tab and verify that it isn't displaying Add an Attachment Link for the Editor
		clickLink(WikisObjects.Attachments_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these attachments link isn't displayed", sel.isElementPresent(WikisObjects.Subscribe_To_These_Attachments_Link));
		AssertJUnit.assertTrue("FAIL: Attachments heading isn't displayed", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: Add an Attachment isn't displayed", sel.isElementPresent(WikisObjects.AddanAttachment_Link));
	}

	public void VerifyUIofPageViewforaOwneroftheWiki() throws Exception {

		//Verify the UI of the Page View for a User who is a Owner for the Wiki
		AssertJUnit.assertTrue("FAIL: Add Tags or Add or Remove Tags link is not displayed, an Editor has the privilege to Add Tags for the Wiki.", (sel
				.isElementPresent(WikisObjects.Add_or_RemoveTags_Link))
				|| (sel.isElementPresent(WikisObjects.Add_tags_Link)));
		AssertJUnit.assertTrue("FAIL: Create New Page Link isn't displayed, an Owner has the privilege to Add New Pages to the Wiki.", sel
				.isElementPresent(WikisObjects.Create_New_Page_Link));
		AssertJUnit.assertTrue("FAIL: Members link is not displayed, an Owner has the privilege to View members for the Wiki.", sel.isElementPresent(WikisObjects.Members_Link));
		AssertJUnit.assertTrue("FAIL: Members section isn't displayed, an Editor has the privilege to access the Members page", sel
				.isElementPresent(WikisObjects.Members_Expand_Collapse_Arrow));
		AssertJUnit.assertTrue("FAIL: Index Link is not displayed, an Owner has the privilege to view Pages Index section", sel.isElementPresent(WikisObjects.Index_Link));

		AssertJUnit.assertTrue("FAIL: Comments Tab isn't displayed, an Owner has the privilege to Add Comments for the Wiki", sel.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Versions Tab is n't displayed, an Owner has the privilege to view Versions page", sel.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments Tab isn't displayed, an Owner has the privilege to view the Attachments for the Page", sel
				.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: About Tab isn't displayed, an Owner has the privilege to view About Info", sel.isElementPresent(WikisObjects.About_Tab));
		AssertJUnit.assertTrue("FAIL: Recommendation UI isn't displayed, an Owner has the privilege to Recommend a Wiki Page", sel
				.isElementPresent(WikisObjects.Recommendations_Info));
		AssertJUnit.assertTrue("FAIL: Navigation Table isn't displayed, an Owner has the privilege to View the Navigation Table of the Wiki", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Table));
		AssertJUnit.assertTrue("FAIL: Navigation Tree isn't displayed, an Owner has the privilege to view the Navigation Tree of the Wiki", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Tree));
		AssertJUnit.assertTrue("FAIL: Subscribe to this Page Link isn't displayed, an Owner has the privilege to Subscribe for the Page", sel
				.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));
		AssertJUnit.assertTrue("FAIL: Subscribe to these Comments Link isn't displayed, an Owner has the privilege to Subscribe for Page comments.", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Comments_Link));

		AssertJUnit.assertTrue("FAIL: Edit Button isn't displayed, an Owner has the privilege to Edit the Wiki.", sel.isElementPresent(WikisObjects.Edit_Button));
		AssertJUnit.assertTrue("FAIL: Page Actions Button should be Present, an Owner has the privilege to Add New Child or Peer Pages to the Wiki Pages.", sel
				.isElementPresent(WikisObjects.Page_Actions_Button));
		AssertJUnit.assertTrue("FAIL: Wiki Actions Button isn't visible", sel.isElementPresent(WikisObjects.Wiki_Actions_Button));

		//Click Follow button menu	
		clickLink(WikisObjects.Follow_Button);

		//Verify that it is displaying Follow this page and Follow this Wiki options for the Owner		
		this.ValidateFollowMenuItems();

		//click on Index Link and verify UI of Pages & Date Expand & Collapse sections.
		clickLink(WikisObjects.Index_Link);
		this.VerifyUIofPagesExpandAndCollapseMenuonPageView();
		this.VerifyUIofDateExpandAndCollapseMenuonPageView();

		//Go back to Page view
		sel.goBack();
		Thread.sleep(3000);

		this.VerifyTagsSectionUIOnPageView();

		//Click on the Versions Tab which is in the Page Footer.
		clickLink(WikisObjects.Versions_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these page versions link isn't displayed", sel.isElementPresent(WikisObjects.Subscribe_To_These_Page_Versions_Link));

		//Click on Attachments Tab and verify that it isn't displaying Add an Attachment Link for the Owner
		clickLink(WikisObjects.Attachments_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these attachments link isn't displayed.", sel.isElementPresent(WikisObjects.Subscribe_To_These_Attachments_Link));
		AssertJUnit.assertTrue("FAIL: Attachments tab title is not displayed.", sel.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: Add an Attachment is Not displayed.", sel.isElementPresent(WikisObjects.AddanAttachment_Link));

		//Click Page Actions
		if (sel.isElementPresent(WikisObjects.Page_Actions_Button))
			clickLink(WikisObjects.Page_Actions_Button);

		else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)) clickLink(WikisObjects.Page_Actions_Button_For_Editors);

		//Verify that its displaying Create New Child, Create New Peer, Print Page and Delete Page options for the Owner		
		this.ValidatePageActionsItems(Data.Owner_Role);
	}

	public void VerifyUIofPageViewforanAnonymousUseroftheWiki() throws Exception {

		//Verify the UI of the Page View for a User who is an Anonymous user for the Wiki
		AssertJUnit.assertFalse("FAIL: Add or Remove Tags Link should not be Present. Since an Anonymous User has no Privilege to Add Tags for the Wiki.", sel
				.isElementPresent(WikisObjects.Add_or_RemoveTags_Link));
		AssertJUnit.assertFalse("FAIL: Edit Page Button should not be Present. Since an Anonymous User has no Privilege to Edit the Wiki.", sel
				.isElementPresent(WikisObjects.Edit_Button));
		AssertJUnit.assertFalse("FAIL: Create New Page Link should not be Present. Since an Anonymous User has no Privilege to Add New Pages to the Wiki.", sel
				.isElementPresent(WikisObjects.Create_New_Page_Link));
		//AssertJUnit.assertFalse("FAIL: Recommend a Page Image should not be Present. Since an Anonymous Reader has no Privilege to Recommend a Wiki Page.", sel.isElementPresent(Objects.Recommendations_Info));

		AssertJUnit.assertTrue("FAIL: Members Link is not present,but Anonymous User has Privilige to view Members Page.", sel.isElementPresent(WikisObjects.Members_Link));
		AssertJUnit.assertFalse("FAIL: Wiki Actions Button shouldn't be visible", sel.isElementPresent(WikisObjects.Wiki_Actions_Button));
		AssertJUnit.assertTrue("FAIL: About Tab is Not dislplayed but an Anonymous User has the Privilege to View About Tab Info.", sel.isElementPresent(WikisObjects.About_Tab));
		AssertJUnit.assertTrue("FAIL: Versions Tab is not displayed but an Anonymous User has the Privilege to View Versions Tab Info.", sel
				.isElementPresent(WikisObjects.Versions_Tab));
		AssertJUnit.assertTrue("FAIL: Index Link is not displayed but an Anonymous User has the Privilege to View Pages Index section.", sel
				.isElementPresent(WikisObjects.Index_Link));
		//AssertJUnit.assertTrue("FAIL: Search for Page Field is not displayed but an Anonymous User has the Privilege to Search for Pages in the Wiki.", sel.isElementPresent(Objects.Search_Page_Field));
		//AssertJUnit.assertTrue("FAIL: Search for Page Button is not displayed but an Anonymous User has the Privilege to Search for Pages in the Wiki.", sel.isElementPresent(Objects.Search_Page_Button));
		AssertJUnit.assertTrue("FAIL: Navigation Table is not displayed but an Anonymous User has the Privilege to View the Navigation Table of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Table));
		AssertJUnit.assertTrue("FAIL: Navigation Tree  is not displayed but an Anonymous User has the Privilege to View the Navigation Tree of the Wiki.", sel
				.isElementPresent(WikisObjects.Wiki_Nav_Tree));
		AssertJUnit.assertTrue("FAIL: Comments Tab is not displayed but an Anonymous Reader has the Privilege to View Comments for a Wiki Page.", sel
				.isElementPresent(WikisObjects.Comments_Tab));
		AssertJUnit.assertTrue("FAIL: Attachments Tab is Not displayed but an Anonymous User has the Privilege to View the Attachments for the Page.", sel
				.isElementPresent(WikisObjects.Attachments_Tab));
		AssertJUnit.assertTrue("FAIL: Subscribe to this Page Link is Not displayed but an Anonymous User has the Privilege to Subscribe for the Page.", sel
				.isElementPresent(WikisObjects.Subscribe_To_This_Page_Link));
		AssertJUnit.assertTrue("FAIL: Subscribe to these Comments Link is Not displayed but an Anonymous User has the Privilege to Subscribe to comments for the Page.", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Comments_Link));
		AssertJUnit.assertTrue("FAIL: Page Actions Button is not displayed but an Anonymous User has the Privilege to Work with Options in Page Actions.", sel
				.isElementPresent(WikisObjects.Page_Actions_Button_For_Readers));
		AssertJUnit.assertTrue("FAIL: Login or Start Contributing Button is not displayed.", sel.isElementPresent(WikisObjects.LoginStartContributing_Button));

		//click on Index Link and verify UI of Pages & Date Expand & Collapse sections.
		this.clickLink(WikisObjects.Index_Link);
		this.VerifyUIofPagesExpandAndCollapseMenuonPageView();
		this.VerifyUIofDateExpandAndCollapseMenuonPageView();

		//go to page view..
		sel.goBack();
		Thread.sleep(3000);

		this.VerifyTagsSectionUIOnPageView();

		//Click on Page Actions and Verify that it is displaying Print Page Option for the Reader	
		this.clickLink(WikisObjects.Page_Actions_Button_For_Readers);
		this.ValidatePageActionsItems(Data.Reader_Role);

		//Click on Comments Tab and Verify that it is not displaying Add a Comment Link for the Anonymous User
		this.clickLink(WikisObjects.Comments_Tab);
		AssertJUnit.assertFalse("FAIL: Add a Comment Link should not be displayed since an Anonymous User has no privilege to add Comments for a Page.", sel
				.isVisible(WikisObjects.Add_Comment_Link));

		//Click on Versions Tab which is in the Page Footer.
		this.clickLink(WikisObjects.Versions_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these page versions link isn't displayed, an anonymous user has the privilege to Subscribe to these page versions", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Page_Versions_Link));

		//Click on Attachments Tab and verify that it isn't displaying Add an Attachment Link for an anonymous user
		this.clickLink(WikisObjects.Attachments_Tab);
		AssertJUnit.assertTrue("FAIL: Subscribe to these attachments link isn't displayed, an anonymous user has the privilege to Subscribe to these attachments", sel
				.isElementPresent(WikisObjects.Subscribe_To_These_Attachments_Link));
		AssertJUnit.assertTrue(
				"FAIL: Attachments tab title is not displayed.But an Anonymous User has the privilege to View all the Attachments for a Page on the Attachments Page.", (sel
						.isElementPresent(WikisObjects.Attachments_Tab)));
		AssertJUnit.assertFalse("FAIL: Add an Attachment Link should not be Present.Since an Anonymous User has no privilege to Add Attachments for the Page.", (sel
				.isElementPresent(WikisObjects.AddanAttachment_Link)));
		System.out.println("All the Elements are displayed on Page View for an Anonymous User as per the privileges.");
	}

	public void VerifyVersionsPageUIAsAReader() throws Exception {

		//Verify the UI of the Versions page for a User who is a Reader for the Wiki
		AssertJUnit
				.assertFalse(
						"FAIL: Restore Link to Restore Second Most Recent Version should not be Present.Since a Reader has no privilege to Restore a Page to Previous Versions in the Wiki.",
						(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreSecondMostRecent_Version)));
		AssertJUnit
				.assertFalse(
						"FAIL: Restore Link to Restore Third Most Recent Version should not be Present.Since a Reader has no privilege to Restore a Page to Previous Versions in the Wiki.",
						(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreThirdMostRecent_Version)));

		//Verify that for the User(bgossens) who is a Reader is not able to see the  Drop down List and the Delete Link to Delete the Prior Versions in the Versions Page
		AssertJUnit
				.assertFalse(
						"FAIL: Delete drop down List to Delete the Prior Versions should not be Present.Since a Reader has no privilege to Delete Previous Versions of a Page in the Wiki.",
						(sel.isVisible(WikisObjects.Versions_Delete_Dropdown)));
		AssertJUnit.assertFalse(
				"FAIL: Delete Link next to Delete drop down List should not be Present.Since a Reader has no privilege to Delete Previous Versions of a Page in the Wiki.", (sel
						.isVisible(WikisObjects.Delete_Link)));

		//Verify that for the User(bgossens) who is a Reader is able to see the View Links for the Versions in the Versions Page
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Second Most Recent Version is not displayed.But a Reader has the privilege to View any Previous Versions of a Page in the Wiki.", sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewSecondMostRecent_Version));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Third Most Recent Version is not displayed.But a Reader has the privilege to View any Previous Versions of a Page in the Wiki.", sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewThirdMostRecent_Version));

		//Verify that for the User(bgossens) who is a Reader is able to see the two Drop down Lists to Compare Versions and also the Show Comparision Link in the Versions Page
		AssertJUnit.assertTrue(
				"FAIL: First Comparision Drop down List is not displayed.But a Reader has the privilege to View the Comparision between Versions of a Page in the Wiki.", sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown1));
		AssertJUnit.assertTrue(
				"FAIL: Second Comparision Drop down List is not displayed.But a Reader has the privilege to View the Comparision between Versions of a Page in the Wiki.", sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown2));
		AssertJUnit
				.assertTrue(
						"FAIL: Text before the Comparision Drop down Lists is not displayed.But a Reader has the privilege to View the Comparision between Versions of a Page in the Wiki.",
						sel.isTextPresent(WikisObjects.Versions_Compare_Text));
		AssertJUnit.assertTrue("FAIL: Show Comparision Link is not displayed.But a Reader has the privilege to View the Comparision between the Versions of a Page in the Wiki",
				sel.isElementPresent(WikisObjects.Versions_Show_Comparison_Link));

		System.out.println("All the Elements are displayed on the Versions Page for a Reader as per the privileges.");
	}

	public void VerifyVersionsPageUIAsAnEditor() throws Exception {

		//Verify the UI of the Versions page for an User who is an Editor for the Wiki
		AssertJUnit.assertTrue(
				"FAIL: Restore Link to Restore Second Most Recent Version is not displayed.But an Editor has the privilege to Restore a Page to Previous Versions in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreSecondMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: Restore Link to Restore Third Most Recent Version is not displayed.But an Editor has the privilege to Restore a Page to Previous Versions in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreThirdMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Second Most Recent Version is not displayed.But an Editor has the privilege to View any Previous Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewSecondMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Third Most Recent Version is not displayed.But an Editor has the privilege to View any Previous Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewThirdMostRecent_Version)));

		//Verify that for the User who is an Editor is not able to see the  Drop down List and the Delete Link to Delete the Prior Versions in the Versions page
		AssertJUnit
				.assertFalse(
						"FAIL: Delete drop down List to Delete the Prior Versions should not be Present.Since a Reader has no privilege to Delete Previous Versions of a Page in the Wiki.",
						(sel.isVisible(WikisObjects.Versions_Delete_Dropdown)));
		AssertJUnit.assertFalse(
				"FAIL: Delete Link next to Delete drop down List should not be Present.Since a Reader has no privilege to Delete Previous Versions of a Page in the Wiki.", (sel
						.isVisible(WikisObjects.Delete_Link)));

		//Verify that for the User(ablanks) who is an Editor is able to see the two Drop down Lists to Compare Versions and also the Show Comparision Link in the Versions page
		AssertJUnit.assertTrue(
				"FAIL: First Comparision Drop down List is not displayed.But an Editor has the privilege to View the Comparision between Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown1)));
		AssertJUnit.assertTrue(
				"FAIL: Second Comparision Drop down List is not displayed.But an Editor has the privilege to View the Comparision between Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown2)));
		AssertJUnit
				.assertTrue(
						"FAIL: Text before the Comparision Drop down Lists is not displayed.But an Editor has the privilege to View the Comparision between the Versions of a Page in the Wiki.",
						(sel.isTextPresent(WikisObjects.Versions_Compare_Text)));
		AssertJUnit.assertTrue("FAIL: Show Comparision Link is not displayed.But an Editor has the privilege to View the Comparision between Versions of a Page in the Wiki.", (sel
				.isElementPresent(WikisObjects.Versions_Show_Comparison_Link)));

		System.out.println("All the Elements are displayed on the Versions page for an Editor as per the privileges.");
	}

	public void VerifyVersionsPageUIAsAnOwner() throws Exception {

		//Verify the UI of the Versions Page for an User who is a Owner for the Wiki
		AssertJUnit.assertTrue(
				"FAIL: Restore Link to Restore Second Most Recent Version is not displayed.But a Owner has the privilege to Restore a Page to Previous Versions in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreSecondMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: Restore Link to Restore Third Most Recent Version is not displayed.But a Owner has the privilege to Restore a Page to Previous Versions in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreThirdMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Second Most Recent Version is not displayed.But a Owner has the privilege to View any Previous Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewSecondMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Third Most Recent Version is not displayed.But a Owner has the privilege to View any Previous Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewThirdMostRecent_Version)));

		//Verify that for the User(bheinz) who is a Owner is able to see the  Drop down List and the Delete Link to delete the prior Versions in the Versions page
		AssertJUnit.assertTrue(
				"FAIL: Delete drop down List to Delete the Prior Versions is not displayed.But a Owner has the privilege to Delete Previous Versions of a Page in the Wiki.", (sel
						.isVisible(WikisObjects.Versions_Delete_Dropdown)));
		AssertJUnit.assertTrue("FAIL: Text before the Delete drop down List is not displayed.But a Owner has the privilege to Delete Previous Versions of a Page in the Wiki.",
				(sel.isTextPresent(WikisObjects.Versions_Deletion_Text)));
		AssertJUnit.assertTrue(
				"FAIL: Delete Link next to the Delete drop down List is not displaayed.But a Owner has the privilege to Delete Previous Versions of a Page in the Wiki.", (sel
						.isVisible(WikisObjects.Delete_Link)));

		//Verify that for the User(bheinz) who is a Owner is able to see the two Drop down Lists to Compare Versions and also the Show Comparision Link in the Versions page
		AssertJUnit.assertTrue("FAIL: First Comparision Drop down List is not displayed.But a Owner has the privilege to View Comparision between Versions of a Page in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Comparison_Dropdown1)));
		AssertJUnit.assertTrue(
				"FAIL: Second Comparision Drop down List is not displayed.But a Owner has the privilege to View Comparision between Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown2)));
		AssertJUnit.assertTrue(
				"FAIL: Text before the Comparision Drop down Lists is not displayed.But a Owner has the privilege to View Comparision between Versions of a Page in the Wiki.",
				(sel.isTextPresent(WikisObjects.Versions_Compare_Text)));
		AssertJUnit.assertTrue("FAIL: Show Comparision Link is not displayed.But a Owner has the privilege to View Comparision between Versions of a Page in the Wiki", (sel
				.isElementPresent(WikisObjects.Versions_Show_Comparison_Link)));

		System.out.println("All the Elements are displayed on the Versions page for a Owner as per the privileges.");
	}

	public void VerifyVersionsPageUIAsAnAnonymousUser() throws Exception {

		//Verify the UI of the Versions Page for an Anonymous User of the Wiki
		AssertJUnit
				.assertFalse(
						"FAIL: Restore Link to Restore Second Most Recent Version should not be Present.Since an Anonymous User has no privilege to Restore a Page to Previous Versions in the Wiki.",
						(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreSecondMostRecent_Version)));
		AssertJUnit
				.assertFalse(
						"FAIL: Restore Link to Restore Third Most Recent Version should not be Present.Since an Anonymous User has no privilege to Restore a Page to Previous Versions in the Wiki.",
						(sel.isElementPresent(WikisObjects.Versions_Page_Restore_Link_to_RestoreThirdMostRecent_Version)));

		//Verify that the Anonymous User is not able to see the  Drop down List and the Delete Link to Delete the Prior Versions in the Versions page
		AssertJUnit
				.assertFalse(
						"FAIL: Delete drop down List to Delete the Prior Versions should not be Present.Since an Anonymous User has no privilege to Delete Previous Versions of a Page in the Wiki.",
						(sel.isVisible(WikisObjects.Versions_Delete_Dropdown)));
		AssertJUnit
				.assertFalse(
						"FAIL: Delete Link next to the Delete drop down List should not be Present.Since an Anonymous User has no privilege to Delete Previous Versions of a Page in the Wiki.",
						(sel.isVisible(WikisObjects.Delete_Link)));

		//Verify that for the Anonymous User is able to see the View Links for the Versions in the Versions page
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Second Most Recent Version is not displayed.But an Anonymous User has the privilege to View any Previous Versions of a Page in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewSecondMostRecent_Version)));
		AssertJUnit.assertTrue(
				"FAIL: View Link to View Third Most Recent Version is not displayed.But an Anonymous User has the privilege to View any Previous Versions of a Page in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Page_View_Link_to_ViewThirdMostRecent_Version)));

		//Verify that the Anonymous User is able to see the two Drop down Lists to Compare Versions and also the Show Comparision Link in the Versions page
		AssertJUnit.assertTrue(
				"FAIL: First Comparision Drop down List is not displayed.But Anonymous User has the privilege to View Comparision between Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown1)));
		AssertJUnit.assertTrue(
				"FAIL: Second Comparision Drop down List is not displayed.But Anonymous User has the privilege to View Comparision between Versions of a Page in the Wiki.", (sel
						.isElementPresent(WikisObjects.Versions_Comparison_Dropdown2)));
		AssertJUnit
				.assertTrue(
						"FAIL: Text before the Comparision Drop down Lists is not displayed.But Anonymous User has the privilege to View Comparision between Versions of a Page in the Wiki.",
						(sel.isTextPresent(WikisObjects.Versions_Compare_Text)));
		AssertJUnit.assertTrue("FAIL: Show Comparision Link is not displayed.But Anonymous User has the privilege to View Comparision between Versions of a Page in the Wiki.",
				(sel.isElementPresent(WikisObjects.Versions_Show_Comparison_Link)));
	}

	public void VerifyAboutTabUI(String Role) throws Exception {

		//Verify the UI of About Tab for any user
		AssertJUnit.assertTrue("FAIL: About tab title is missing, all users have the privilege to view About page", sel.isElementPresent(WikisObjects.About_Tab));
		AssertJUnit.assertTrue("FAIL: Updated Field is missing, all users have the privilege to view Updated information in About", sel.isTextPresent(WikisObjects.Updated_Field
				+ ":"));
		AssertJUnit.assertTrue("FAIL: Created Field is missing, all users have the privilege to view Created information in About", sel.isTextPresent(WikisObjects.Created_Field
				+ ":"));
		AssertJUnit.assertTrue("FAIL: Size Field is missing, all users have the privilege to view Size information in About", sel.isTextPresent(WikisObjects.Size_Field + ":"));
		AssertJUnit.assertTrue("FAIL: Page views is missing, all users have the privilege to view Page views in About", sel.isTextPresent(WikisObjects.PageViews_Field + ":"));
		AssertJUnit.assertTrue("FAIL: Hierarchy label is missing, all users should see the hierarchy of a Page in About", sel.isTextPresent(WikisObjects.Hierarchy_Text + ":"));
		AssertJUnit.assertTrue("FAIL: Child Pages Heading is missing, all users have the privilege to view child pages in About", sel.isTextPresent(WikisObjects.Child_Pages_Text));

		if ((Role.equals("Owner")) || (Role.equals("Editor")))
			AssertJUnit.assertTrue("FAIL: Create New Child Page link should appear for Owners & Editors, as they have privilege to add child pages", sel
					.isElementPresent(WikisObjects.Create_newChildPage_Link));
		else AssertJUnit.assertFalse("FAIL: Create New Child Page link shouldn't appear for users with less than Editor access, as they don't have privilege to add child pages",
				sel.isElementPresent(WikisObjects.Create_newChildPage_Link));
	}

	public void verifyRecommendationUpdatesToIcons(int pageRecommends, int index) throws Exception {

		//Verify the Recommendation icons after a user recommends the page
		clickLink("xpath=//*[contains(@id,'lconn_wikis_widget_RecommendInfo_')]");
		AssertJUnit.assertTrue("FAIL: First line of popup text for recommendation info is incorrect", sel.isTextPresent("You have recommended this page."));

		if (pageRecommends >= 1)
			AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(pageRecommends + " other person recommended this:"));
		else AssertJUnit.assertTrue("FAIL: Second line of popup text for recommendation info is incorrect", sel.isTextPresent(pageRecommends + " other people recommended this:"));
	}

	public void viewMoreContentInTableView(String wikiTitle) throws Exception {

		//Open Table Display view
		ChangeToTableView();

		//Open More content for specified page in Table view
		openMoreContent(wikiTitle, "Table");
	}

	public void viewMoreContentInListView(String wikiTitle) throws Exception {

		//Open List Display view
		ChangeToListView();

		//Open More content for specified page in List view
		openMoreContent(wikiTitle, "List");
	}

	public void openMoreContent(String wikiTitle, String displayView) throws Exception {

		int index = 1;
		String titleText = "";

		while (sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[2]/h4/a")) {
			titleText = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[2]/h4/a");

			if ((titleText.equals(wikiTitle)) && (!titleText.equals("")) && (displayView.equals("Table")))
				clickLink("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[6]/a");

			else if ((titleText.equals(wikiTitle)) && (!titleText.equals("")) && (displayView.equals("List")))
				clickLink("xpath=id('list')/div[2]/table/tbody/tr[" + index + "]/td[8]/a");

			index = index + 2;
		}
	}

	public boolean waitForObject(String checkObject) throws Exception {

		for (int second = 0;; second++) {
			if (second >= 60) Assert.fail("TIMEOUT ERROR: Newly added tag failed to appear after 60 seconds");
			try {
				if (sel.isVisible(checkObject)) break;
			} catch (Exception e) {
				return false;
			}
			Thread.sleep(1000);

		}
		return true;
	}

	/** Select a group from activated Groups textfield typeahead dropdown */
	public void selectMemberFromUserDropdown(String UserName) throws Exception {

		if (UserName.contains("Amy Jones")) UserName = UserName.replace("Amy Jones", "ajones");

		AssertJUnit.assertTrue("FAIL: Member names dropdown list isn't visible", sel.isElementPresent(WikisObjects.Members_Textfield_Typeahead));

		sel.focus(WikisObjects.Members_Textfield_Typeahead);
		clickAtItem(WikisObjects.fullUserSearchIdentifier, WikisObjects.fullSearchLink);

		String DropdownText = sel.getText(WikisObjects.selectedUserIdentifier);
		AssertJUnit.assertTrue("FAIL: Member names dropdown list doesn't contain correct user", DropdownText.contains(UserName));

		clickAtItem(WikisObjects.selectedUserIdentifier, "Amy " + UserName);
	}

	/** Select a group from activated Groups textfield typeahead dropdown */
	public void selectMemberFromGroupDropdown(String GroupName) throws Exception {

		AssertJUnit.assertTrue("FAIL: Group names typeahead dropdown list isn't visible", sel.isElementPresent(WikisObjects.Groups_Textfield_Typeahead));

		clickAtItem(WikisObjects.fullGroupSearchIdentifier, WikisObjects.fullSearchLink);

		String DropdownText = sel.getText(WikisObjects.selectedGroupIdentifier);
		AssertJUnit.assertTrue("FAIL: Group names dropdown list doesn't contain correct group", DropdownText.equals(GroupName));

		clickAtItem(WikisObjects.selectedGroupIdentifier, GroupName);
	}

	public void OpenAddMembersLightBox() throws Exception {

		//Open the Add Members Light Box
		sel.click(WikisObjects.Add_Members_Button);
		Thread.sleep(2000);
	}

	public void verifyCorrectlyFilteredWikisInMyWikis(String wikiPartName, int loopInt) throws Exception {

		int totalWikisCreated = 6, index = (totalWikisCreated - loopInt) + 1;

		clickLink(WikisObjects.Link_toshow_25Members_perpage);
		Thread.sleep(1000);

		//Verify that the correct number of wikis appear when clicking on any of the My Wikis filters
		for (; index < totalWikisCreated; index++) {
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiPartName + index + " doesn't exist", sel.isElementPresent("link=" + wikiPartName + index));
		}
	}

	public void checkChangedMembership(String newRole, String oldRole, String wikiName) throws Exception {

		//Click Start a Wiki button
		this.clickLink(WikisObjects.My_Wikis_Tab);

		//Verify changes to Owner role
		if ((oldRole.equals(Data.Owner_Role)) && (newRole.equals(Data.Editor_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Editor_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}
		else if ((oldRole.equals(Data.Owner_Role)) && (newRole.equals(Data.Reader_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Editor_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Reader_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}

		//Verify changes to Editor role
		if ((oldRole.equals(Data.Editor_Role)) && (newRole.equals(Data.Owner_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}
		else if ((oldRole.equals(Data.Editor_Role)) && (newRole.equals(Data.Reader_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Editor_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Reader_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}

		//Verify changes to Reader role
		if ((oldRole.equals(Data.Reader_Role)) && (newRole.equals(Data.Owner_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}
		else if ((oldRole.equals(Data.Reader_Role)) && (newRole.equals(Data.Editor_Role))) {
			this.clickLink(WikisObjects.MyWikis_Owner_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " shouldn't appear in this filter", !sel.isElementPresent(wikiName));

			this.clickLink(WikisObjects.MyWikis_Editor_Filter);
			AssertJUnit.assertTrue("FAIL: Wiki " + wikiName + " doesn't appear in this filter", sel.isElementPresent(wikiName));
		}

	}

	public boolean findWiki(String wikiTitle) throws Exception {

		//Navigate through pages to locate the wiki title
		while (!sel.isElementPresent("link=" + wikiTitle)) {
			if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_Active)) & (!sel.isElementPresent("link=" + wikiTitle))) {
				clickLink(WikisObjects.Top_Next_Page_Link_Active);
			}
			else if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive)) & (!sel.isElementPresent("link=" + wikiTitle))) break;
		}
		if (sel.isElementPresent("link=" + wikiTitle))
			return true;
		else return false;
	}

	public void VerifySpecialGroupsDropdownListandClickonAuthenticatedSpecialGroupintheDropdownList() throws Exception {

		//Verify the Special Groups Drop down List and click on the Correct Sopecial Group
		AssertJUnit.assertTrue("FAIL: Wiki_Group_Names_dropdownList is not present.", (sel.isElementPresent(WikisObjects.Add_Groups_Dropdown)));
		AssertJUnit.assertTrue("FAIL: Wiki_Group_Names_dropdownList is not bringing the Matching Groups in the Directory.", ("All Authenticated Users".equals(sel
				.getText("xpath=//*[contains(@id,'_selectGroup_popup1')]"))));
		sel.clickAt("xpath=//*[contains(@id,'_selectGroup_popup1')]", "All Authenticated Users");
		Thread.sleep(1000);
	}

	public void VerifySpecialGroupsDropdownListandClickonAnonymousUserpecialGroupintheDropdownList() throws Exception {

		//Verify the Special Groups Drop down List and click on the Anonymous User Special Group
		AssertJUnit.assertTrue("FAIL: Wiki_Group_Names_dropdownList is not present.", (sel.isElementPresent(WikisObjects.Add_Groups_Dropdown)));
		AssertJUnit.assertTrue("FAIL: Wiki_Group_Names_dropdownList is not bringing the Matching Groups in the Directory.", ("Anonymous User".equals(sel
				.getText("xpath=//*[contains(@id,'_selectGroup_popup0')]"))));
		sel.clickAt("xpath=//*[contains(@id,'_selectGroup_popup0')]", "Anonymous User");
		Thread.sleep(1000);
	}

	public void RemoveAllAuthenticatedUsersasEditorsGroup() throws Exception {

		//Remove the ALL Authenticated Users as Editors Group Link on the Create Wiki Light Box
		sel.click(WikisObjects.All_AuthenticatedUsers_as_EditorsGroup_Link);
		Thread.sleep(2000);
	}

	public void RemoveAnonymousUsersasReadersGroup() throws Exception {

		//Remove the Anonymous Users as Readers Group Link on the Create Wiki Light Box
		sel.click(WikisObjects.All_AnonymousUsers_as_ReadersGroup_Link);
		Thread.sleep(2000);
	}

	public boolean VerifyAdditionofCommentsPrivilegefortheUser(String CommenttobeAdded) throws Exception {

		//Verify the Addition of Comments Privilege for the User 
		int NoOfCommentsBeforeAddition = ElementCount(sel.getText(WikisObjects.Comments_Tab));

		//Open Comments Page
		clickObject(WikisObjects.Comments_Tab);

		//Open the Comment Editor
		if (sel.isElementPresent(WikisObjects.Add_Comment_Link)) {
			clickLink(WikisObjects.Add_Comment_Link);
		}

		//Add a comment to the page
		sel.type(WikisObjects.Add_Comment_Editor, CommenttobeAdded);
		clickLink(WikisObjects.Comments_Save_Button);

		//Verify added comment
		VerifyCommentIsAdded(CommenttobeAdded);

		int NoOfCommentsAfterAddition = ElementCount(sel.getText(WikisObjects.Comments_Tab));

		if (NoOfCommentsAfterAddition == (NoOfCommentsBeforeAddition + 1))
			return true;
		else return false;
	}

	public void VerifyEditingPrivilegefortheUser(String TexttobeAdded) throws Exception {

		//Verify the Editing Privilege for the User
		clickLink(WikisObjects.Edit_Button);

		//Verify that the Page is opened in Edit Mode
		this.VerifythatthePageisOpenedinEditMode();

		//Verify that the User is able to Add the Text in the Editor and then Save the Page
		this.AddContentintheEditorforanExistingPage(TexttobeAdded);

		//Verify that the Text Typed in the Editor is Added to the Page
		AssertJUnit.assertTrue("FAIL: Text typed in the Editor isn't present on the page", sel.isTextPresent(TexttobeAdded));
	}

	public void VerifyCreatingNewPageatRootLevelPrivilegefortheUser(String PageName, String PageContent) throws Exception {

		//Verify the Creating a New Page at Root Level privilege for the User 
		clickLink(WikisObjects.Create_New_Page_Link);

		//Verify that a Page is opened with an Editor and Fields for Page Name and also the Tag List Widget
		this.VerifytheUIofCreateaNewWikiPage();

		//Type the Page Name and enter the Content for the Page and click on save and Close Link
		this.TypethePageNameinPageNameField(PageName);

		//Add content in Wiki Text
		this.AddContentintheEditorforaNewPage(PageContent);

		//Verify that the Page is created
		this.VerifyNewPageCreation(PageName, PageContent);
	}

	public void openPageActionsAsOwner() throws Exception {

		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button));
		sel.clickAt(WikisObjects.Page_Actions_Button, "Page Actions");
		Thread.sleep(2000);
	}

	public void openPageActionsAsEditor() throws Exception {

		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors));
		sel.clickAt(WikisObjects.Page_Actions_Button_For_Editors, "Page Actions");
		Thread.sleep(2000);
	}

	public void openPageActionsAsReader() throws Exception {

		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Readers));
		sel.clickAt(WikisObjects.Page_Actions_Button_For_Readers, "Page Actions");
		Thread.sleep(2000);
	}

	public void openPageActionsAsAnonymousUser() throws Exception {

		AssertJUnit.assertTrue("FAIL: Page Actions button is missing", sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Readers));
		sel.clickAt(WikisObjects.Page_Actions_Button_For_Readers, "Page Actions");
		Thread.sleep(2000);
	}

	public void VerifyCreatingChildPagePrivilegeForAUser(String PageName, String PageContent) throws Exception {

		clickLink(WikisObjects.Menu_Item_1);

		//Verify that a Page is opened with an Editor and Fields for Page Name and also the Tag List Widget
		this.VerifytheUIofCreateaNewWikiPage();

		//Type the Page Name and enter the Content for the Page and click on save and Close Link
		this.TypethePageNameinPageNameField(PageName);

		//Add content to the new page
		this.AddContentintheEditorforaNewPage(PageContent);

		//Verify that the Page is created
		this.VerifyNewPageCreation(PageName, PageContent);
	}

	public void VerifyLoginForm() throws Exception {

		//Code for checking that the login form is present - will all the required fields
		//We are waiting for a max of 30 secs to verify login form is displayed
		for (int i = 1; i <= 30; i++) {
			if (sel.isElementPresent(WikisObjects.WIKI_USERNAME_FIELD)) break;
			Thread.sleep(1000);
		}
		//Verify that Username,Password and Login buttons are availabel on Login form
		AssertJUnit.assertTrue("Username field is not present", sel.isElementPresent(WikisObjects.WIKI_USERNAME_FIELD));
		AssertJUnit.assertTrue("Password field is not present", sel.isElementPresent(WikisObjects.WIKI_Password_FIELD));
		AssertJUnit.assertTrue("Login Button is not present", sel.isElementPresent(WikisObjects.WIKI_Login_Button));
	}

	public void OpenPageActionsButtonMenuAndVerifyMenuItemsAvailableForAnonymousUser() throws Exception {

		this.openPageActionsAsAnonymousUser();
		String PageActionsMenuItem1_Text = sel.getText(WikisObjects.Menu_Item_1);
		String PageActionsMenuItem2_Text = sel.getText(WikisObjects.Menu_Item_2);

		//Verify that first Menu option is Print Page for Anonymous User
		AssertJUnit.assertTrue("Print Page is not First Page Actions Menu item for Anonymous User", (PageActionsMenuItem1_Text.equalsIgnoreCase("Print Page")));
		AssertJUnit.assertTrue("Download Page is not Second Page Actions Menu item for Anonymous User", (PageActionsMenuItem2_Text.equalsIgnoreCase("Download Page")));
	}

	public void VerifyCreatingPeerPagePrivilegeForAUser(String PageName, String PageContent) throws Exception {

		clickLink(WikisObjects.Menu_Item_2);

		//Verify that a Page is opened with an Editor and Fields for Page Name and also the Tag List Widget
		this.VerifytheUIofCreateaNewWikiPage();

		//Type the page name in the name textfield
		this.TypethePageNameinPageNameField(PageName);

		//Enter the content for the Page and click on Save and Close link
		this.AddContentintheEditorforaNewPage(PageContent);

		//Verify that the Page is created
		this.VerifyNewPageCreation(PageName, PageContent);
	}

	public void VerifyPagingFunctionalityonMembersandRolesPage() throws Exception {

		//Check that the Links to show 10,25 50 Members per page is working
		int NoofRowsinMembersandRolesTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));

		AssertJUnit.assertTrue("FAIL: Members page isn't showing correct number of Members", NoofRowsinMembersandRolesTable == 8);

		sel.click(WikisObjects.Link_toshow_25Members_perpage);
		Thread.sleep(1000);

		NoofRowsinMembersandRolesTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
		AssertJUnit.assertTrue("FAIL: Members and Roles Page is not showing Correct no of Members per page when we clicked the link to show 25 Members per page",
				NoofRowsinMembersandRolesTable == 25);

		sel.click(WikisObjects.Link_toshow_50Members_perpage);
		Thread.sleep(1000);

		NoofRowsinMembersandRolesTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
		AssertJUnit.assertTrue("FAIL: Members and Roles Page is not showing Correct no of Members per page when we clicked the link to show 50 Members per page",
				NoofRowsinMembersandRolesTable == 50);
	}

	public void selectMemberFromMemberAndRoles(String Username) throws Exception {

		boolean memberNotFound = true;
		String userText = "";
		int index1 = 1, index2;

		//Select user from Members
		while (memberNotFound) {
			index2 = 1;
			while ((sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a")) && (memberNotFound)) {
				userText = sel.getText("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/span/a");

				if (userText.equals(Username)) {
					sel.clickAt("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/div[2]/ul/li/a", "Edit");
					Thread.sleep(2000);
					memberNotFound = false;
				}
				index2++;
			}
			index1++;
		}
	}

	public void changeMembershipOfUser(String newRole) throws Exception {

		//Change membership radio button
		if (newRole.equals("Owner")) {
			sel.click("xpath=//*[contains(@id,'_role_manager')]");
			Thread.sleep(2000);
		}
		else if (newRole.equals("Editor")) {
			sel.click("xpath=//*[contains(@id,'_role_editor')]");
			Thread.sleep(2000);
		}
		else if (newRole.equals("Reader")) {
			sel.click("xpath=//*[contains(@id,'_role_reader')]");
			Thread.sleep(2000);
		}

		sel.click(WikisObjects.OK_Button);
		Thread.sleep(2000);
	}

	public boolean FindWikiNameInMyWikisList(String Wiki_Title) throws Exception {

		boolean Wiki_Found = true;

		//Navigate through pages to locate the wiki title
		if ((sel.isVisible(WikisObjects.Link_toshow_25Wikis_perpage)) && (!sel.isElementPresent("link=" + Wiki_Title))) {
			clickLink(WikisObjects.Link_toshow_25Wikis_perpage);
		}
		if ((sel.isVisible(WikisObjects.Link_toshow_50Wikis_perpage)) && (!sel.isElementPresent("link=" + Wiki_Title))) {
			clickLink(WikisObjects.Link_toshow_50Wikis_perpage);
		}

		/*
		 * String WhatBrowser = sel.getEval("navigator.appName");
		 * 
		 * if(WhatBrowser.equals("Netscape")){ //Navigate through pages to locate the wiki title while
		 * (!sel.isElementPresent("link=" + Wiki_Title)){
		 * if((sel.isVisible(Objects.Top_Next_Page_Link_Active))&&(!sel.isElementPresent("link=" + Wiki_Title))){
		 * clickLink(Objects.Top_Next_Page_Link_Active);
		 * 
		 * } if ((sel.isElementPresent(Objects.Top_Next_Page_Link_InActive ))&&(!sel.isElementPresent("link=" +
		 * Wiki_Title))){ Wiki_Found=false; break; } } } else if (WhatBrowser.equals("Microsoft Internet Explorer")){
		 */
		//Navigate through pages to locate the wiki title
		while (!sel.isElementPresent("link=" + Wiki_Title)) {
			if ((sel.isVisible(WikisObjects.Top_Next_Page_Link_Active)) && (!sel.isElementPresent("link=" + Wiki_Title))) {
				clickLink(WikisObjects.Top_Next_Page_Link_Active);
			}
			else if ((sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive)) && (!sel.isElementPresent("link=" + Wiki_Title))) {
				Wiki_Found = false;
				break;
			}
		}
		return (Wiki_Found);
	}

	public void VerifyUIofDeleteWikiLightBox() throws Exception {

		//Ensure that all the Elements are Present on the Delete Wiki Light Box
		AssertJUnit.assertTrue("FAIL: Delete Wiki Light Box Heading Text is not Present", sel.isTextPresent(" Are you sure you want to delete this wiki?"));
		AssertJUnit
				.assertTrue(
						"FAIL: Delete Wiki Light Box Body Text is not Present",
						sel
								.isTextPresent("Deleting this wiki will permanently delete all data associated with this wiki. This action cannot be undone, so make sure you want to delete this wiki."));
		AssertJUnit.assertTrue("FAIL: Delete this Wiki Button is not Present", sel.isElementPresent(WikisObjects.Delete_Button));
		AssertJUnit.assertTrue("FAIL: Cancel Link is not Present", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public void VerifyUIofDeletePageLightBox() throws Exception {

		//Ensure that all the Elements are Present on the Delete Page Light Box		
		AssertJUnit.assertTrue("FAIL: Delete Page Light Box Heading Text is not Present", sel.isTextPresent("Are you sure you want to move page"));
		AssertJUnit.assertTrue("FAIL: Delete this Wiki Button is not Present", sel.isElementPresent(WikisObjects.OK_Button));
		AssertJUnit.assertTrue("FAIL: Cancel Link is not Present", sel.isElementPresent(WikisObjects.Cancel_Link));
	}

	public boolean VerifyPageExists(String pageName) throws Exception {

		boolean foundPage = false;

		if (sel.isElementPresent("link=" + pageName)) foundPage = true;

		return foundPage;
	}

	public boolean VerifyEditLinkDisplayOnMembersPageForOwners(String UserFullName) throws Exception {

		//Verify the UI the Display of Edit and Remove Links for the Logged in Owner on Members Page
		int index1 = 1, index2 = 1, linkCount = 0;

		this.clickLink(WikisObjects.Link_toshow_50Members_perpage);

		while (sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/a[1]")) {
			while (sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/a[1]")) {
				String UsernameText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/a[1]");

				if (UsernameText.contains(UserFullName)) {
					if (sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/div[2]/ul/li/a")) linkCount = 1;
				}
				index2++;
			}
			index1++;
			index2 = 1;
		}

		if (linkCount == 0)
			return true;
		else return false;
	}

	public int getAllUsersOnMembersandRoles() throws Exception {

		//Click the 'users' filter & get the number of users shown from the heading
		sel.click(WikisObjects.Person_Filter_Link);
		Thread.sleep(1000);

		String noOfElements = getNoOfElementsFromText();

		//Convert extracted string to an integer 
		int userValue = Integer.parseInt(noOfElements);

		return userValue;
	}

	public int getAllGroupsOnMembersandRoles() throws Exception {

		//Click the 'groups' filter & get the number of groups shown from the heading
		sel.click(WikisObjects.Group_Filter_Link);
		Thread.sleep(1000);

		String noOfElements = getNoOfElementsFromText();

		//Convert extracted string to an integer 
		int groupValue = Integer.parseInt(noOfElements);

		return groupValue;
	}

	public String getNoOfElementsFromText() throws Exception {

		//Capture the number of users/groups by isolating the numeric value
		String noOfUsersText = sel.getText(WikisObjects.Page_Index_Text_In_Top_Pagination);
		String noOfUsers = "";

		//Loop to extract the number of users from the page text
		for (int y = 0; y < noOfUsersText.length(); y++) {
			if (noOfUsersText.charAt(y) == 'f') {
				noOfUsers = noOfUsersText.substring(y + 2);
			}
		}

		return noOfUsers;
	}

	public boolean verifyAdditionOfNewUserToMembersList(String UserFullName, String Role) throws Exception {

		//Verify whether new user added from Members and Roles page is added to the list in Table view
		int index1 = 1, index2 = 1;
		boolean memberAdded = false;
		String UsernameText;
		String UsernameTrimmed;

		if (sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage)) clickLink(WikisObjects.PageID_Showing_64Members_perpage);

		//while(sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" +index1+ "]/td[" +index2+ "]/table/tbody/tr/td[2]/h4/a[1]")){
		while (sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/")) {
			UsernameText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4/a");
			//System.out.println(UsernameText);

			//Trim out the 51a43040-0101-102e-8948-f78755f7e0ed to return only the User Name
			UsernameTrimmed = UsernameText.substring(36, UsernameText.length());//replaced 48
			UsernameTrimmed.trim();
			System.out.println(UsernameTrimmed);

			//Now print out the Username that we will compare
			//System.out.println(UserFullName);

			//Verify the username is in the members list and if so then verify the role of the user
			//otherwise loop and check the next column of the members view
			if (UsernameTrimmed.equals(UserFullName)) {
				String UserTypeText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/div[1]");
				//System.out.println(UserTypeText);

				if (Role.equals(UserTypeText)) {
					System.out.println("Members role is confirmed as: " + UserTypeText);
					memberAdded = true;
				}
				//if((Role.equals(UserTypeText))&&(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" +index1+ "]/td[" +index2+ "]/table/tbody/tr/td[2]/div[2]/ul/li[1]/a")))
				//memberAdded = true;
			}
			index2++;
		}

		if (index2 > 4) {
			index1++;
			index2 = 1;
		}
		return memberAdded;
	}

	/*
	 * Add this method for groups as the xpath has changed so that I can not use the previous method for groups any
	 * more.
	 */
	public boolean verifyAdditionOfNewGroupToMembersList(String UserFullName, String Role) throws Exception {

		//Verify whether new user added from Members and Roles page is added to the list in Table view
		int index1 = 1, index2 = 1;
		boolean memberAdded = false;
		String UsernameText;
		String UsernameTrimmed;

		if (sel.isElementPresent(WikisObjects.PageID_Showing_64Members_perpage)) clickLink(WikisObjects.PageID_Showing_64Members_perpage);

		//while(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" +index1+ "]/td[" +index2+ "]/table/tbody/tr/td[2]/h4"))
		//while(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr/td/table/tbody/tr/td[2]/h4/"))
		while (index2 < 4) {
			UsernameText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/h4");
			//System.out.println(UsernameText);

			//Now print out the Username that we will compare
			//System.out.println(UserFullName);

			//Verify the username is in the members list and if so then verify the role of the user
			//otherwise loop and check the next column of the members view
			if (UsernameText.equals(UserFullName)) {
				String UserTypeText = sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr[" + index1 + "]/td[" + index2 + "]/table/tbody/tr/td[2]/div[1]");
				//sel.getText("xpath=//div[@id='list']/div[2]/table/tbody/tr/td/table/tbody/tr/td[2]/div[1]");
				//System.out.println(UserTypeText);

				if (Role.equals(UserTypeText)) {
					System.out.println("Members role is confirmed as: " + UserTypeText);
					memberAdded = true;
				}
				//if((Role.equals(UserTypeText))&&(sel.isElementPresent("xpath=//div[@id='list']/div[2]/table/tbody/tr[" +index1+ "]/td[" +index2+ "]/table/tbody/tr/td[2]/div[2]/ul/li[1]/a")))
				//memberAdded = true;
			}
			index2++;
		}

		return memberAdded;
	}

	public void ClickonSortbyAddedOption() throws Exception {

		//Click the Sort by Added Option on the My Wikis Page
		clickLink(WikisObjects.Sortby_Created_Option);
	}

	public int getMemberNumbers() throws Exception {

		//Get the value from the View All link in Members section
		return ElementCount(sel.getText(WikisObjects.View_All_Link));
	}

	public int validateMembersUI() throws Exception {

		//Get the value from the View All link in Members section
		int CurrentNumberOfMembers = ElementCount(sel.getText(WikisObjects.View_All_Link));

		return CurrentNumberOfMembers;
	}

	public void ClickonSortbyUpdatedOption() throws Exception {

		//Click the Sort by Name Option on the My Wikis Page
		sel.click(WikisObjects.Sortby_Updated_Option);
		Thread.sleep(1000);
	}

	public void OpenSettingsLightBoxandAddDescription(String Description) throws Exception {

		//Activate Wiki Actions dropdown
		clickLink(WikisObjects.Wiki_Actions_Button);

		//Open Edit Wiki form and add description for the Wiki
		sel.clickAt(WikisObjects.Menu_Item_1, "Settings");
		Thread.sleep(2000);
		sel.type(WikisObjects.Description_Field, Description);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(2000);
	}

	public void VerifyDisplayofPageTitleandLinkforPageTitle(String PageTitle, String PageLink) throws Exception {

		//Verify the Display of Page Title and Link for Page Title
		AssertJUnit.assertTrue("FAIL: Wiki Title is not Present", (sel.getText(WikisObjects.WikiHomePageTitleField)).equals(PageTitle));
		AssertJUnit.assertTrue("FAIL: Link for Wiki Title is not Present", sel.isElementPresent(PageLink));
	}

	public void ReturnToPublicWikisAndRefreshPage() throws Exception {

		//Return to Public Wikis tab
		sel.click(WikisObjects.Public_Wikis_Tab);
		Thread.sleep(30000);
		sel.refresh();
		Thread.sleep(3000);
	}

	public void RefreshPage() throws Exception {

		//Allow a period of sleep before refreshing the page
		Thread.sleep(2000);
		sel.refresh();
		Thread.sleep(3000);
	}

	public void invokeRefresh() throws Exception {

		//Invoke page refresh to remove popup
		sel.refresh();
		Thread.sleep(4000);
	}

	public void ReturnToPublicWikis() throws Exception {

		//Return to My Wikis tab
		sel.click(WikisObjects.Public_Wikis_Tab);
		Thread.sleep(1000);
	}

	public void ReturnToMyWikis() throws Exception {

		//Return to My Wikis tab
		sel.click(WikisObjects.My_Wikis_Tab);
		Thread.sleep(1000);
	}

	public void VerifyWikisGenericListUI() throws Exception {

		//Ensure all elements are present on Public Wikis/My Wikis in Table view
		AssertJUnit.assertTrue("FAIL: Next Page Link at the Top of the list isn't present", sel.isElementPresent(WikisObjects.Next_PageLink_atthe_Topof_PublicWikisList));
		AssertJUnit.assertTrue("FAIL: Previous Page Link at the Top of the list isn't present", sel.isElementPresent(WikisObjects.Previous_PageLink_atthe_Topof_PublicWikisList));
		AssertJUnit.assertTrue("FAIL: Next Page Link at the Bottom of the list isn't present", sel.isElementPresent(WikisObjects.Next_PageLink_atthe_Bottomof_PublicWikisList));
		AssertJUnit.assertTrue("FAIL: Previous Page Link at the Bottom of the list isn't present", sel
				.isElementPresent(WikisObjects.Previous_PageLink_atthe_Bottomof_PublicWikisList));

		AssertJUnit.assertTrue("FAIL: Link toshow 25 Wikis per Page on the list isn't present", sel.isElementPresent(WikisObjects.Link_toshow_25Wikis_perpage));
		AssertJUnit.assertTrue("FAIL: Link to show 50 Wikis per Page on the list isn't present", sel.isElementPresent(WikisObjects.Link_toshow_50Wikis_perpage));
		AssertJUnit.assertTrue("FAIL: PageId showing 10 Wikis per Page on the Public Wikis Main Page isn't present", sel
				.isElementPresent(WikisObjects.PageID_Showing_10Wikis_perpage));

		Assert.assertTrue(sel.isTextPresent("Jump to page  "));
	}

	public void VerifyWikisListUIInTableView() throws Exception {

		//Ensure all elements are present on Public Wikis/My Wikis in Table view
		if (!sel.isTextPresent(WikisObjects.No_Wikis_In_List)) VerifyWikisGenericListUI();
		AssertJUnit.assertTrue("FAIL: Sort by Name Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Name_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Created Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Created_Option));
		AssertJUnit.assertTrue("FAIL: Sort by Updated Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Updated_Option));
		AssertJUnit.assertTrue("FAIL: 'Subscribe to public wikis' link isn't present", sel.isElementPresent(WikisObjects.Subscribe_To_Public_Wikis_Link));
	}

	public String[] AddCommentsForUUIDCheck(String commentText) throws Exception {

		String fullLocator = "";
		String[] UUIDs = new String[2];
		int num = 0;

		for (int index = 1; index < 20; index++) {

			//Add a comment to the wiki homepage
			this.AddAComment(commentText + index);

			if ((index == 1) || (index == 15)) {
				sel.click("xpath=id('wikiPageComments')/div[2]/div[" + index + "]/div[2]/div[1]/a[2]");
				Thread.sleep(1000);
				fullLocator = (sel.getLocation());

				int x = fullLocator.length() - 1;
				StringBuffer UUIDExtracted = new StringBuffer();

				while ((fullLocator.charAt(x) != '/') || (x == 0)) {
					UUIDExtracted.append(fullLocator.charAt(x));
					x--;
				}
				UUIDs[num] = UUIDExtracted.reverse().toString();
				num++;
			}
		}

		return UUIDs;
	}

	public String getUUIDFromLocator() throws Exception {

		String fullLocator = sel.getLocation(), UUID;
		int x = fullLocator.length() - 1;
		StringBuffer UUIDExtracted = new StringBuffer();

		while ((fullLocator.charAt(x) != '/') || (x == 0)) {
			UUIDExtracted.append(fullLocator.charAt(x));
			x--;
		}
		UUID = UUIDExtracted.reverse().toString();

		return UUID;
	}

	public void validateFilterIcon(String filterSelected) throws Exception {

		//Validate UI for filtering
		sel.isTextPresent(WikisObjects.MyWikis_Filter_Text);
		String filterText = sel.getText(WikisObjects.Tagged_With_Icon);
		AssertJUnit.assertTrue("FAIL: Filter text isn't correct", filterText.equals("Wikis I can " + filterSelected + "X"));
	}

	public void VerifyWikisListUIInListView() throws Exception {

		//Ensure all elements are present on Public Wikis/My Wikis in List view
		if (!sel.isTextPresent(WikisObjects.No_Wikis_In_List)) {
			VerifyWikisGenericListUI();
			AssertJUnit.assertTrue("FAIL: Sort by Name Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Name_Option));
			AssertJUnit.assertTrue("FAIL: Sort by Added Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Created_Option));
			AssertJUnit.assertTrue("FAIL: Sort by Updated Option isn't present", sel.isElementPresent(WikisObjects.Sortby_Updated_Option));
		}
		AssertJUnit.assertTrue("FAIL: 'Subscribe to public wikis' link isn't present", sel.isElementPresent(WikisObjects.Subscribe_To_Public_Wikis_Link));
	}

	public void VerifyPagingFunctionalityonPublicWikisMainpage() throws Exception {

		//Method to check that the Links to show 10,25 50 wikis per page is working

		int numRowsinPublicWikisTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));

		AssertJUnit.assertTrue("FAIL: Public Wikis Page not showing Correct no of wikis per page when we enter Public Wikis page for first time.",
				(numRowsinPublicWikisTable == 20));

		sel.click(WikisObjects.Link_toshow_25Wikis_perpage);
		Thread.sleep(2000);
		numRowsinPublicWikisTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
		AssertJUnit.assertTrue("FAIL: Public Wikis Page not showing Correct no of wikis per page when we click link to show 25 Wikis per page.", (numRowsinPublicWikisTable == 50));

		sel.click(WikisObjects.Link_toshow_50Wikis_perpage);
		Thread.sleep(2000);
		numRowsinPublicWikisTable = Integer.parseInt(String.valueOf(sel.getXpathCount("//div[@id='list']/div[2]/table/tbody/tr")));
		AssertJUnit
				.assertTrue("FAIL: Public Wikis Page not showing Correct no of wikis per page when we click link to show 50 Wikis per page.", (numRowsinPublicWikisTable == 100));
	}

	public void VerifyStartaNewWikiButtonFunctionalityonPublicWikisTab() throws Exception {

		//Verifying that Start a Wiki form appears and all the elements are present on Start a Wiki form	
		//Click on Add Groups Link to open the Drop down and the Groups Field to Add Groups
		sel.click(WikisObjects.AddGroups_Link);
		Thread.sleep(2000);
		try {
			AssertJUnit.assertTrue("FAIL: Mandatory Field Indicator is not Present", sel.isElementPresent(WikisObjects.Mandatory_Field_Indicator));
			AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Present", sel.isElementPresent(WikisObjects.Wiki_Name_Field));
			AssertJUnit.assertTrue("FAIL: Wiki Name Field is not Editable", sel.isEditable(WikisObjects.Wiki_Name_Field));
			AssertJUnit.assertTrue("FAIL: Members Field is not Present", sel.isElementPresent(WikisObjects.Members_Field));
			AssertJUnit.assertTrue("FAIL: Members Field is not Editable", sel.isEditable(WikisObjects.Members_Field));
			AssertJUnit.assertTrue("FAIL: Groups Field is not Present", sel.isElementPresent(WikisObjects.Groups_Field));
			AssertJUnit.assertTrue("FAIL: Groups Field is not Editable", sel.isEditable(WikisObjects.Groups_Field));
			AssertJUnit.assertTrue("FAIL: Description Field is not Present", sel.isElementPresent(WikisObjects.Description_Field));
			AssertJUnit.assertTrue("FAIL: Description Field is not Editable", sel.isEditable(WikisObjects.Description_Field));
			AssertJUnit.assertTrue("FAIL: Tags Field is not Present", sel.isElementPresent(WikisObjects.Tags_Field));
			AssertJUnit.assertTrue("FAIL: Tags Field is not Editable", sel.isEditable(WikisObjects.Tags_Field));
			AssertJUnit.assertTrue("FAIL: Create Wiki Button is not Present", sel.isElementPresent(WikisObjects.Save_Button));
			AssertJUnit.assertTrue("FAIL: Cancel Link is not Present", sel.isElementPresent(WikisObjects.Cancel_Link));
			System.out.println("Start a Wiki button on Public Wikis page is working.Start a Wiki form is present and all elements are present.");

		} catch (Exception e) {
			Assert.fail("FAIL: some of the objects in the start a wiki form are missing: " + e);
		}

	}

	public void CreatePublicWikiwithEditorAccessOnlytoOwnersandMembers(String WikiName, String TagName) throws InterruptedException {

		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(1000);
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);

		//Select the All Users Radio Button Under Who Can Read this Wiki Section 
		sel.click(WikisObjects.AllUsers_RadioButton);

		//Select the Wiki Editors and Owners Only Radio Button Under Who Can Edit this Wiki Section 		
		sel.click(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(3000);
	}

	public void CreatePrivateWikiwithAllAuthenticatedUsersasEditors(String WikiName, String TagName) throws InterruptedException {

		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(1000);
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);

		//Select the Wiki Members Only Radio Button Under Who Can Read this Wiki Section 
		sel.click(WikisObjects.WikiMembersOnly_RadioButton);
		//Select the All Logged in Users Radio Button Under Who Can Edit this Wiki Section 
		sel.click(WikisObjects.AllLoggedInUsers_RadioButton);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(3000);
	}

	public void CreatePrivateWikiwithEditorAccessOnlytoOwnersandMembers(String WikiName, String TagName) throws InterruptedException {

		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(1000);
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);

		//Select the Wiki Members Only Radio Button Under Who Can Read this Wiki Section 
		sel.click(WikisObjects.WikiMembersOnly_RadioButton);
		//Select the Wiki Editors and Members Only Radio Button Under Who Can Edit this Wiki Section 
		sel.click(WikisObjects.WikiEditorsAndOwnersOnly_RadioButton);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(2000);
	}

	public void CreatePrivateWikiwithOnlyOwnerastheMember(String WikiName, String TagName) throws InterruptedException {

		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(1000);
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);
		sel.click(WikisObjects.Save_Button);
		Thread.sleep(2000);
	}

	public void CancelCreateAWiki(String WikiName, String TagName, String Description) throws InterruptedException {

		//Launch Create a New Wiki lightbox
		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(2000);

		//Enter a wiki name & tags in respective textfields
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);
		sel.type(WikisObjects.Description_Field, Description);

		//Click Create Wiki button
		sel.click(WikisObjects.Cancel_Link);
		Thread.sleep(2000);

		try {
			//AssertJUnit.assertTrue("FAIL: the lightbox to confirm cancelling of the wiki has not appeared", sel.isTextPresent("Confirm"));
			sel.chooseOkOnNextConfirmation();
			System.out.println("PASS: the Confirm lightbox is present after cancelling the Start a Wiki form");
		} catch (Exception e) {
			Assert.fail("FAIL: the lightbox to confirm cancelling of the wiki has not appeared" + e);
		}

		//Verify that you are returned to the My Wikis - Wikis I created after cancel
		try {
			AssertJUnit.assertTrue("FAIL: 'I'm a Reader' is not present after I cancelled a wiki", sel.isTextPresent("I'm a Reader"));

		} catch (Exception e) {
			Assert.fail("FAIL: 'I'm a Reader' is not present after I cancelled a wiki" + e);
		}

	}

	public void WaitforPagetoLoad(String ObjectName) throws InterruptedException {

		for (int second = 0;; second++) {
			if (second >= 60) Assert.fail("Page is not loaded in the required time");
			try {
				if (sel.isElementPresent(ObjectName)) break;
			} catch (Exception e) {
			}
			Thread.sleep(1000);
		}
	}

	/*
	 * Verify that the current page has a child page
	 */
	public void VerifyPageAsAChildPage(String ChildPage, String InfoMessage) throws InterruptedException {

		sel.click(WikisObjects.About_Tab);
		sel.setTimeout("60000");
		Thread.sleep(2500);
		Assert.assertEquals(ChildPage, sel.getText(WikisObjects.ChildPageExists));
		Thread.sleep(1000);
	}

	/*
	 * Verify that the current page has a child page
	 */
	public void VerifyPagesAddedAtChildLevel(String ChildPage1, String ChildPage2, String ChildPage3, String ChildPage4, String ChildPage5, String VerifyChildPageExistsMessage)
			throws InterruptedException {

		sel.click(WikisObjects.About_Tab);
		sel.setTimeout("60000");
		Thread.sleep(2500);
		Assert.assertEquals(ChildPage1, sel.getText("//*[@id='childList']/li[1]/a"));
		Assert.assertEquals(ChildPage2, sel.getText("//*[@id='childList']/li[2]/a"));
		Assert.assertEquals(ChildPage3, sel.getText("//*[@id='childList']/li[3]/a"));
		Assert.assertEquals(ChildPage4, sel.getText("//*[@id='childList']/li[4]/a"));
		Assert.assertEquals(ChildPage5, sel.getText("//*[@id='childList']/li[5]/a"));
		Thread.sleep(1000);
	}

	public void VerifyArrowInNavTreeAfterChildPageAdded() throws InterruptedException {

		try {
			if (sel.isElementPresent(WikisObjects.NavTreeArrow)) ;
			System.out.println("PASS: Arrow in the Nav tree has being verified as present. Child page exists.");
		} catch (Exception e) {
			System.out.println("FAIL: Arrow doesn't exist on the Nav tree - was the child page added?");
		}
	}

	/*
	 * Verify that the current page has a child page
	 */
	public void VerifyPageHasChildPages(String ChildPage1, String ChildPage2, String VerifyChildPageExistsMessage) throws InterruptedException {

		sel.click(WikisObjects.About_Tab);
		this.WaitforPagetoLoad(WikisObjects.AboutPageTitle);
		Thread.sleep(1000);
		Assert.assertEquals(ChildPage1, sel.getText(WikisObjects.ChildPageExists));
		Assert.assertEquals(ChildPage2, sel.getText(WikisObjects.SecondChildPageExists));
		Thread.sleep(1000);
	}

	//Create a random number of root pages on the navigation
	public void DeleteAllPages(int NumberOfPage) throws InterruptedException {

		String ItemText = "";

		for (int i = 1; i <= NumberOfPage; i++) {
			if (sel.isElementPresent(WikisObjects.Page_Actions_Button)) {
				sel.click(WikisObjects.Page_Actions_Button);
				Thread.sleep(2000);
			}

			else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)) {
				sel.click(WikisObjects.Page_Actions_Button_For_Editors);
				Thread.sleep(2000);
			}

			if (sel.isElementPresent(WikisObjects.Menu_Item_6)) {
				ItemText = sel.getText(WikisObjects.Menu_Item_6);
				AssertJUnit.assertTrue("FAIL: The first item isn't 'Create Child'", ItemText.contains("Move to Trash"));

				sel.click(WikisObjects.Menu_Item_6);
				Thread.sleep(4000);
			}
			try {
				if (sel.isTextPresent("Move to Trash")) {
					sel.click(WikisObjects.OK_Button_In_Popup);
					Thread.sleep(5000);
				}
			} catch (Exception e) {
				System.out.println("FAIL: Page was not deleted successfully");
			}
		}
	}

	//Drag the nav pane
	public void NavPaneResize() throws InterruptedException {

		sel.dragAndDrop(WikisObjects.NavResize, "");

	}

	/*
	 * Insert Macro Methods
	 */

	public void SelectOptionInInsertMacroLightbox(String MacroOption) throws Exception {

		//Select the radio button for this test and then click OK
		sel.click(MacroOption);
		System.out.println("INFO: Selected the macro: " + MacroOption + "");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(100);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(100);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_ENTER));
		System.out.println("PASS: Insert Marco lightbox was closed properly");
		Thread.sleep(3000);
	}

	public void SelectOptionInInsertMacroLightboxCancel(String MacroOption) throws Exception {

		//Select the radio button for this test and then click OK
		sel.click(MacroOption);
		System.out.println("INFO: Selected the macro: " + MacroOption + "");
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(100);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(100);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		Thread.sleep(100);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_ENTER));
		System.out.println("PASS: Insert Marco lightbox was closed properly");
		Thread.sleep(3000);
	}

	public void VerifyMacroTextIsAddedToTheContent(String MacroContent) throws Exception {

		//Verify that the macro content is added to the content of the editor
		if (sel.isTextPresent(MacroContent)) {
			System.out.println("PASS: Macro content added successfully to the content of the page");
		}
		else if (sel.isTextPresent("macro content will appear here")) {
			System.out.println("FAIL: This is a defect as the content is not added at all - just a placeholder.");
		}
		else if (!sel.isTextPresent(MacroContent)) {
			System.out.println("FAIL: Macro content not added successfully to the content of the page");
		}

	}

	public void VerifyMacroTextIsNotAddedToTheContent(String MacroContent) throws Exception {

		//Verify that the macro content is not added to the content of the editor
		if (!sel.isTextPresent(MacroContent)) {
			System.out.println("PASS: Macro content has not being added to the editor, as expected.");
		}
		else if (!sel.isTextPresent("macro content will appear here")) {
			System.out.println("Pass: PASS: Macro content has not being added to the editor, as expected.");
		}
	}

	public void MoveToTheEndOfTheTextInTheEditor() throws Exception {

		sel.focus("//Html/body/div/p");
		sel.click("//Html/body/div/p");
		Thread.sleep(1000);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_CONTROL));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_END));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_CONTROL));
		Thread.sleep(100);
		sel.keyDownNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		sel.keyUpNative(String.valueOf(KeyEvent.VK_ENTER));
	}

	public void IncrementPageVisits(String pageLink, int NoOfTimes) throws Exception {

		for (int index = 0; index <= NoOfTimes; index++)
			this.clickObject(pageLink);
	}

	public int countPages() throws Exception {

		//Count pages available to user
		int index1 = 1, index2 = 2, pageCount = 0;

		this.clickLink(WikisObjects.Link_toshow_50Members_perpage);

		while (sel.isElementPresent("link=" + index2)) {
			pageCount = pageCount + 50;
			if (sel.isElementPresent("link=" + (index2 + 1)))
				index2++;
			else {
				sel.click("link=" + index2);
				Thread.sleep(1000);
			}
		}

		//Change to List Display view
		if (sel.isElementPresent(WikisObjects.UnPressedListIcon)) clickLink(WikisObjects.UnPressedListIcon);
		while (sel.isElementPresent("xpath=id('list')/div[2]/table/tbody/tr[" + index1 + "]/td[2]/h4/a")) {
			index1 = index1 + 2;
			pageCount++;
		}

		String valueFromPlacebar = sel.getText(WikisObjects.Page_Index_Text_In_Top_Pagination);
		String NumberOfPagesText = "";
		int NoOfPages = 0;
		//Get value from placebar
		for (int i = 0; i < valueFromPlacebar.length(); i++) {
			if (valueFromPlacebar.charAt(i) == 'f') {
				NumberOfPagesText = valueFromPlacebar.substring(i + 2, valueFromPlacebar.length());
				NoOfPages = Integer.parseInt(NumberOfPagesText);
			}
		}

		AssertJUnit.assertTrue("FAIL: Automated page count doesn't match value in placebar", NoOfPages == pageCount);
		return pageCount;
	}

	public void VerifyWikiCreation(String wikiname) throws Exception {

		//sel.click(Objects.AboutTabInline);
		Thread.sleep(1000);
		if (sel.isTextPresent(wikiname)) {
			System.out.println("PASS: The wiki has being created as expected.");;
		}
		else {
			System.out.println("FAIL: Wiki was not created! Problem");;
		}

	}

	public void CreatePublicWikiButDoNotCreate(String WikiName, String TagName, String Description) throws InterruptedException {

		//Launch Create a New Wiki lightbox
		sel.click(WikisObjects.Start_New_Wiki_Button);
		Thread.sleep(2000);

		//Enter a wiki name & tags in respective textfields
		sel.type(WikisObjects.Wiki_Name_Field, WikiName);
		sel.type(WikisObjects.Tags_Field, TagName);

		//Select the All Users Radio Button Under Who Can Read this Wiki Section 
		sel.click(WikisObjects.AllUsers_RadioButton);
		Thread.sleep(500);

		//Select the All Logged in Users Radio Button Under who can Edit this Wiki Section
		sel.click(WikisObjects.AllLoggedInUsers_RadioButton);
		Thread.sleep(500);

		//Add a description
		sel.type(WikisObjects.Description_Field, Description);

		//Click Create Wiki button
		sel.click(WikisObjects.Cancel_Link);
		Thread.sleep(1000);

		//Add code to check that a popup appears - Claudio has not checked this code in yet.
	}

	public void VerifyAllPagesDeleted() throws Exception {

		//Verify that all pages have being deleted
		Assert.assertEquals("No pages have been created in this wiki.", sel.getText(WikisObjects.All_Page_Deletion_Warning));
		Assert.assertTrue(sel.isElementPresent(WikisObjects.Create_A_Page_Button));
		Assert.assertTrue(sel.isElementPresent(WikisObjects.TakeMeBackOnCreatePage));
	}

	public void VerifyReturntoMyWikisView(String WikiName) throws Exception {

		clickLink(WikisObjects.TakeMeBackOnCreatePage);
		Assert.assertTrue(sel.isElementPresent("link=" + WikiName));
		clickLink("link=" + WikiName);
	}

	public void GetPageIndexValueInNavTree(String PageNameInNav) throws Exception {

		this.SelectPageinWikiNavigation(PageNameInNav);
		System.out.println("Info: " + PageNameInNav + " has being selected in the Navigation");
		String PageIndexBeforeMovingPage;
		PageIndexBeforeMovingPage = sel.getAttribute("//*[@class='dijitTreeRowSelected dijitTreeRow lotusSelected']");
		System.out.println("Page Index for page" + PageNameInNav + " is: " + PageIndexBeforeMovingPage);

	}

	public void MovePagesUptheNav(int NumberOfPage) throws Exception {

		//Select the lightbox
		clickLink(WikisObjects.MoveThisPageLightBox);

		//Now click on the up button
		for (int i = 1; i <= NumberOfPage; i++) {
			sel.click(WikisObjects.MovePageUp);
			Thread.sleep(500);
		}
		//Click on the Move button to Move the Page
		sel.click(WikisObjects.OK_Button);
		sel.setTimeout("60000");
		Thread.sleep(3000);
		System.out.println("PASS: Page has being moved up the nav using the arrows");

	}

	public void MovePagesDowntheNav(int NumberOfPage) throws Exception {

		//Select the lightbox
		sel.click(WikisObjects.MoveThisPageLightBox);
		Thread.sleep(1000);

		//Now click on the up button
		for (int i = 1; i <= NumberOfPage; i++) {

			sel.click(WikisObjects.MovePageDown);
			Thread.sleep(500);
		}
		//Click on the Move button to Move the Page
		sel.click(WikisObjects.OK_Button);
		sel.setTimeout("60000");
		Thread.sleep(3000);
		System.out.println("PASS: Page has being moved down the nav using the arrows");
	}

	/*
	 * Choose the Print Page option from Page Actions
	 */
	public void PrintPage() throws InterruptedException {

		if (sel.isElementPresent(WikisObjects.Page_Actions_Button)) {
			sel.click(WikisObjects.Page_Actions_Button);
			Thread.sleep(2000);
		}

		else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)) {
			sel.click(WikisObjects.Page_Actions_Button_For_Editors);
			Thread.sleep(2000);
		}

		int index = 0;
		String temp = "", PrintPageLink = "";
		do {
			if (sel.isElementPresent("xpath=id('dijit_MenuItem_" + index + "')/td[2]")) temp = sel.getText("xpath=id('dijit_MenuItem_" + index + "')/td[2]");
			if (temp.equals("Print Page")) {
				PrintPageLink = "xpath=id('dijit_MenuItem_" + index + "')/td[2]";
			}
			else index++;

		} while (!(temp.equals("Print Page")));

		//Click on the Print Page link
		sel.click(PrintPageLink);
	}

	/*
	 * Choose the Download Page option from Page Actions
	 */
	public void DownloadPage() throws InterruptedException {

		if (sel.isElementPresent(WikisObjects.Page_Actions_Button)) {
			sel.click(WikisObjects.Page_Actions_Button);
			Thread.sleep(2000);
		}

		else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)) {
			sel.click(WikisObjects.Page_Actions_Button_For_Editors);
			Thread.sleep(2000);
		}

		int index = 0;
		String temp = "", DownloadPageLink = "";
		do {
			if (sel.isElementPresent("xpath=id('dijit_MenuItem_" + index + "')/td[2]")) temp = sel.getText("xpath=id('dijit_MenuItem_" + index + "')/td[2]");
			if (temp.equals("Download Page")) {
				DownloadPageLink = "xpath=id('dijit_MenuItem_" + index + "')/td[2]";
			}
			else index++;

		} while (!(temp.equals("Download Page")));

		//Click on the Download Page link
		sel.click(DownloadPageLink);
		Thread.sleep(2500);
	}

	/*
	 * Print each page in the wiki.
	 */
	public void PrintAllPages(int NumberOfPage, String PageName, String PageTitleName) throws Exception {

		//Select the first page and start looping from there
		//this.SelectPageinWikiNavigation(FirstPageName);

		//Loop for i number of time
		for (int i = 1; i <= NumberOfPage; i++) {
			this.SelectPageinWikiNavigation(PageName + i);
			if (sel.isElementPresent(WikisObjects.Page_Actions_Button)) {
				sel.click(WikisObjects.Page_Actions_Button);
				Thread.sleep(2000);
			}

			else if (sel.isElementPresent(WikisObjects.Page_Actions_Button_For_Editors)) {
				sel.click(WikisObjects.Page_Actions_Button_For_Editors);
				Thread.sleep(2000);
			}
			this.PrintPage();
			Thread.sleep(1500);
			//Now verify page to be printed
			String ActualPageTitle;
			ActualPageTitle = sel.getTitle();
			String PrintPageTitle = PageTitleName + i;

			//Now Verify that the Actual Page Title matches the Expected Page Title				
			try {
				Assert.assertEquals("PASS: Print page title has not being verified correctly", PrintPageTitle, ActualPageTitle);
				System.out.println("PASS: " + ActualPageTitle + " page title has being verified as correct.");
			} catch (Exception e) {
				System.out.println("FAIL: Print Page title has not being verified correctly" + e);
			}

			//Now close the popup and return to the original page
			sel.selectWindow(PageTitleName + i);
			sel.close();
			sel.selectWindow(PageTitleName + i);
			Thread.sleep(1000);

		}

	}

	public void EnterNewPageWithInvalidChars(String InvalidChar1, String InvalidChar2, String InvalidChar3, String InvalidChar4, String InvalidChar5, String InvalidChar6,
			String InvalidChar7, String InvalidChar8, String InvalidChar9, String InvalidChar10) throws Exception {

		//Enter new page title
		Thread.sleep(2000);

		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar1);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar2);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar3);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar4);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar5);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar6);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar7);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar8);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar9);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);
		sel.type(WikisObjects.New_Page_Title_Textfield, InvalidChar10);
		Assert.assertTrue(sel.isElementPresent(WikisObjects.InvalidCharErrorMessage));
		Thread.sleep(500);

		clickLink(WikisObjects.Cancel_Link);

		System.out.println("PASS: Can't create a page with invalid charactors");
	}

	/*
	 * Date: 06/05/2011 Updated: Conor Pelly Comment out due to a defect (37018) Objects.Username_on_PageView and all
	 * asert.assertTrue that rely on this object
	 */

	public void FollowAWiki(String FollowingOption, String FollowingOptionText, String ObjectUIMessage, String UIMessage) {

		try {
			sel.click(WikisObjects.Follow_Button);
			Thread.sleep(1000);
			clickAtItem(FollowingOption, FollowingOptionText);
			Thread.sleep(1000);
			AssertJUnit.assertTrue("Fail - the following message is not present in the UI", sel.getText(ObjectUIMessage).equals(UIMessage));
		} catch (Exception e) {
			System.out.println("FAILED: Could not follow the wiki: " + e);
		}
	}

	public void StopFollowingAWiki(String StopFollowingOption, String StopFollowingOptionText, String ObjectStopUIMessage, String UIStopMessage) {

		try {
			sel.click(WikisObjects.Follow_Button);
			Thread.sleep(2000);
			clickAtItem(StopFollowingOption, StopFollowingOptionText);
			Thread.sleep(2000);
			AssertJUnit.assertTrue("Fail - the following message is not present in the UI", sel.getText(ObjectStopUIMessage).equals(UIStopMessage));
		} catch (Exception e) {
			System.out.println("FAILED: Could not stop following the wiki: " + e);
		}
	}

	public void VerifyMyWikisFollowingFilter() throws Exception {

		//clickLink(Objects.My_Wikis_Tab);

		//clickLink(Objects.MyWikis_Follow_Filter);

		try {
			//Verify the pagination options
			sel.isElementPresent(WikisObjects.Link_toshow_10Wikis_perpage);
			sel.isElementPresent(WikisObjects.Link_toshow_25Wikis_perpage);
			sel.isElementPresent(WikisObjects.Link_toshow_50Wikis_perpage);

			//Verify that the Next/Previous links are present in the top and bottom of the filter
			sel.isElementPresent(WikisObjects.Top_Next_Page_Link_InActive);
			sel.isElementPresent(WikisObjects.Top_Previous_Page_Link_InActive);
			sel.isElementPresent(WikisObjects.Bottom_Next_Page_Link_InActive);
			sel.isElementPresent(WikisObjects.Bottom_Previous_Page_Link_InActive);

			//Verify that there are no sort option for this filter
			if (!sel.isElementPresent(WikisObjects.Sortby_Name_Option)) ;
			if (!sel.isElementPresent(WikisObjects.Sortby_Created_Option)) ;
			if (!sel.isElementPresent(WikisObjects.Sortby_Updated_Option)) ;
		} catch (Exception e) {
			System.out.println("FAIL: Could not verify all objects in the Following filter" + e);
		}

	}

	public void VerifyFollowingFilterObjectsAfterMultipleWikisFollowed() throws Exception {

		//Verify objects 
		try {
			Assert.assertTrue(sel.isElementPresent("css=div[class='lotusPaging'] div[class='lotusLeft']"));
			Assert.assertTrue(sel.isElementPresent("css=div.lotusPaging > div > label"));

			//Verify all Page links up to the ...
			for (int i = 1; i <= 4; i++) {
				String aString = Integer.toString(i);
				AssertJUnit.assertTrue("FAIL: Pagination is not correct for this filter", sel.getText("css=ul[class='lotusInlinelist'] li:nth-child(" + i + ")").matches(aString));
				Assert.assertTrue(!sel.isTextPresent("undefined"));
			}

			//Verify the ... in the page links
			AssertJUnit.assertTrue("FAIL: check for ecilpse in the pagination failed", sel.getText("css=ul[class='lotusInlinelist'] li:nth-child(5)").matches("..."));

			//Verify the page links after the ...
			for (int i = 5; i <= 9; i++) {
				sel.fireEvent("link=Next", "click");
				Thread.sleep(1500);
				String aString = Integer.toString(i);
				AssertJUnit.assertTrue("FAIL: Pagination is not correct for this filter", sel.getText("css=ul[class='lotusInlinelist'] li:nth-child(" + i + ")").matches(aString));
				Assert.assertTrue(!sel.isTextPresent("undefined"));
			}

			//Verify that all page links after the ... after clicking Next multiple times 
			for (int i = 4; i <= 7; i++) {
				//sel.fireEvent("link=Next", "click");
				//Thread.sleep(1500);
				String aString = Integer.toString(i);
				AssertJUnit.assertTrue("FAIL: Pagination is not correct for this filter", sel.getText("css=ul[class='lotusInlinelist'] li:nth-child(" + i + ")").matches(aString));
				Assert.assertTrue(!sel.isTextPresent("undefined"));
			}

			//Verify page links after clicking the previous link multiple times
			for (int i = 8; i > 4; i--) {
				sel.fireEvent("link=Previous", "click");
				Thread.sleep(1500);
				String aString = Integer.toString(i);
				AssertJUnit.assertTrue("FAIL: Pagination is not correct for this filter", sel.getText("css=ul[class='lotusInlinelist'] li:nth-child(" + i + ")").matches(aString));
				Assert.assertTrue(!sel.isTextPresent("undefined"));
			}
		} catch (Exception e) {
			System.out.println("FAIL: could not verify all objects for the Following Filter: " + e);
		}
	}

	/*
	 * Upload an attachment
	 */
	public void uploadAttachment(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Ensure that the main window is selected
		sel.selectWindow("null");

		//Click on the attachment tab
		cautiousClick(WikisObjects.Attachments_Tab);
		Thread.sleep(1000);
		//Click on the attachment link
		cautiousClick(WikisObjects.AddAttachmentLink);
		Thread.sleep(3500);

		//Open "File Upload" dialog
		//sel.click("Add an attachment");
		/*try {
			int i = 0;
			while (i < 15) {
				if (!sel.isTextPresent("Upload Attachment")) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1500);
		} catch (Exception e) {
			sleep(2000);
		}*/

		//Open the File Upload dialog
		if (sConfig.browserIsIE) {
			/*sel.focus(CommonObjects.CancelLink);
			Thread.sleep(500);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
			Thread.sleep(500);*/
			NumberOfTimeTabs(6);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}else if (sConfig.browserIsFirefox) {
			sel.focus(WikisObjects.AttachmentFileName);
			ShiftTab(2);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}

		//In File Upload dialog enter the name and path to the file to upload
		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));

		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(1500);

		//Upload the file
		cautiousClick(CommonObjects.OKButton);

		//Check if the file is still uploading
		FileUploading(5);

	}

	public void RenameAttachment(String NameOfFile, String TypeOfFile, String FileUploadName) throws Exception {

		//Check File Exists
		System.out.println(FileUploadName);
		sel.isTextPresent(FileUploadName);

		//Click on the Edit button
		clickLink(WikisObjects.EditAttachment);
		sleep(3500);

		/*try {
			int i = 0;
			while (i < 120) {
				if (!sel.isTextPresent("Edit Attachment")) {
					sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(1000);
		}*/

		//Upload the file
		if (sConfig.browserIsIE) {
			Thread.sleep(1000);
			NumberOfTimeTabs(4);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}
		else if (sConfig.browserIsFirefox) {
			Thread.sleep(1000);
			NumberOfTimeTabs(3);
			sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
			Thread.sleep(3000);
		}

		typeNative(getBrowserEnvironmentAbsoluteFilePath(sConfig.uploadFilesDir, FileUploadName));

		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));
		Thread.sleep(2500);

		//Upload the file
		NumberOfTimeTabs(2);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_ENTER));

		//Wait for the file to be uploaded
		try {
			int i = 0;
			while (i < 10) {
				if (sel.isTextPresent("Edit Attachment")) {
					sleep(3000);
					i = i + 1;
				}
				break;
			}
		} catch (Exception e) {
			sleep(3000);

		}
		Thread.sleep(1000);

		Assert.assertTrue(sel.isTextPresent("The attachment was updated."));
	}

	public void ShiftTab(int NoOfShifts) throws Exception {

		try {
			int i = 0;
			while (i < NoOfShifts) {
				Thread.sleep(500);
				sel.keyDownNative(String.valueOf(KeyEvent.VK_SHIFT));
				sel.keyDownNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_TAB));
				sel.keyUpNative(String.valueOf(KeyEvent.VK_SHIFT));
				Thread.sleep(1000);
				i = i + 1;
			}
			//break;
		} catch (Exception e) {
			sleep(3000);
		}
		Thread.sleep(1000);
	}

	public void FileUploading(int UploadTime) throws Exception {

		try {
			int i = 0;
			while (i < UploadTime) {
				if (sel.isTextPresent("Upload Attachment")) {
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

	public void NumberOfTimeTabs(int TabNo) throws Exception {

		try {
			int i = 0;
			while (i < TabNo) {
				sel.keyPressNative(String.valueOf(KeyEvent.VK_TAB));
				sleep(500);
				i++;
			}
			//break;
			sleep(500);
		} catch (Exception e) {
			sleep(2000);
		}
	}
}
