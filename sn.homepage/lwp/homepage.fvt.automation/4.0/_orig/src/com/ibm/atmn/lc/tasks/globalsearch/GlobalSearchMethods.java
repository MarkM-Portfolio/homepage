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

package com.ibm.atmn.lc.tasks.globalsearch;

// import com.ibm.automation.bvt.setup.common.Initialize;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesData;
import com.ibm.atmn.lc.appobjects.activities.ActivitiesObjects;
import com.ibm.atmn.lc.appobjects.blogs.BlogsData;
import com.ibm.atmn.lc.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesData;
import com.ibm.atmn.lc.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.lc.appobjects.globalsearch.SearchObjects;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesData;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesObjects;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.Assert;
import org.testng.AssertJUnit;

public class GlobalSearchMethods extends CommonMethods {

	private FormInputHandler typist = getFormInputHandler();

	public void AddBlogForSearch() throws Exception {

		//Use the Blogs methods to create the blog

		// Enter the details for the blogs
		typist.type(BlogsObjects.BlogsNameField, BlogsData.BlogsNameForSearch);
		typist.type(BlogsObjects.BlogsAddress, BlogsData.BlogsAddressForSearch);
		typist.type(BlogsObjects.BlogsTags, BlogsData.BlogsTagForSearch);
		typist.type(BlogsObjects.BlogsDescription, BlogsData.BlogsDescriptionForSearch);
		sel.select(BlogsObjects.BlogsTimeZone, BlogsData.BlogsTimeZoneOption);
		sleep(500);
		sel.select(BlogsObjects.BlogsTheme, BlogsData.BlogsThemeOption);
		sleep(500);
		// Save the form
		FormSaveButton(CommonObjects.SaveButton);

	}

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

	public void CreateACommunityForSearch() throws Exception {

		/*
		 * Create a public community with tag, member, description, image and handle
		 */
		StartACommunity();
		sel.type(CommunitiesObjects.CommunityName, CommunitiesData.CommunityNameForSearch);
		Thread.sleep(500);
		sel.type(CommunitiesObjects.CommunityTag, CommunitiesData.CommunityTagForSearch);
		Thread.sleep(500);
		sel.type(CommunitiesObjects.CommunityHandle, CommunitiesData.CommunityHandleForSearch);
		Thread.sleep(500);
		//Enter the description in CKEditor
		typeNativeInCkEditor(CommunitiesData.CommunityDescriptionForSearch);
		Thread.sleep(500);

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

	public void ProfilesAddATagForSearch() throws Exception {

		/*
		 * Add a tag to a user in Profiles, click on the tag and verify that the user's profile is displayed
		 */

		sel.type(ProfilesObjects.ProfilesTagTypeAhead, ProfilesData.ProfilesTagForSearch);
		Thread.sleep(500);
		sel.focus(ProfilesObjects.ProfilesAddTag);
		clickLink(ProfilesObjects.ProfilesAddTag);
		Thread.sleep(1000);

		Assert.assertTrue(sel.isElementPresent("link=" + ProfilesData.ProfilesTagForSearch));

	}

	/** Select a member from activated Add member textfield typeahead dropdown */
	public void selectMemberFromDropdown(String userName) {

		if (userName.contains("Amy Jones")) userName = userName.replace("Amy Jones", "ajones");

		sel.focus(ActivitiesObjects.Start_An_Activity_Members_TypeaheadPromptList);
		sel.clickAt(ActivitiesObjects.usersActivitySearchIdentifier, "1,1");

		String DropdownText = sel.getText(ActivitiesObjects.selectedActivityUserIdentifier);
		AssertJUnit.assertTrue("FAIL: Member names dropdown list doesn't contain correct user", DropdownText.contains(userName)
				|| DropdownText.contains(userName.replace("ajones", "Amy Jones")));

		sel.clickAt(ActivitiesObjects.selectedActivityUserIdentifier, "1,1");
	}

	public String CreatAnActivityForSearch(FormInputHandler typist, String personAsOwner) {

		clickLink(ActivitiesObjects.Start_An_Activity);

		//Fill form to create activity
		String part1ActivityName = ActivitiesData.Start_An_Activity_InputText_Name_Data_For_Search;

		//Activity Name
		typist.type(ActivitiesObjects.Start_An_Activity_InputText_Name, part1ActivityName);

		//Tags
		typist.type(ActivitiesObjects.Start_An_Activity_InputText_Tags, ActivitiesData.Start_An_Activity_InputText_Tags_Data_For_Search, "tags");

		//type activity description ('goals')
		typist.type(ActivitiesObjects.Start_An_Activity_Textarea_Activity_Goals, ActivitiesData.Start_An_Activity_Textarea_Description_Data_For_Search);

		//Enter due date
		typist.pickADojoDate(ActivitiesObjects.Start_An_Activity_InputText_DueDate);
		//typist.type(Objects.Start_An_Activity_InputText_DueDate,
		//ActivitiesData.Start_An_Activity_InputText_DueDate_Data, "date", "type", "typeKeysBackspace");

		//Save activity
		clickLink(CommonObjects.SaveButton);

		return part1ActivityName;
	}

	public void AddBookmarkForSearch(String Title, String URL, String Tag) throws Exception {

		String BookmarkForm = "connections/bookmarklet/post?lang=en";
		//String PublicBookmarks = "dogear/html?lang=en";

		if (sConfig.browserIsFirefox) {
			sel.open(sConfig.browserURL + BookmarkForm);
			sel.type("id=title", Title);
			sel.type("id=urlField", URL);
			sel.type("id=ptags", Tag);
			sel.type("id=rteDiv", "This is a test bookmark for search bvt");
			sel.click("id=publicDogear");
			clickLink(CommonObjects.SaveButton);
			Thread.sleep(3000);
		}
		else {
			clickLink("link=Add a Bookmark");
			sel.waitForPopUp("dogear", "30000");
			sel.selectWindow("name=dogear");
			sel.type("id=title", Title);
			sel.type("id=urlField", URL);
			sel.type("id=ptags", Tag);
			sel.type("id=rteDiv", "This is a test bookmark for search bvt");
			sel.click("id=publicDogear");
			clickLink(CommonObjects.SaveButton);
			Thread.sleep(3000);
		}
	}

	//Now do the search in the advanced search form
	public void UnCheckAllCheckboxes() throws Exception {

		//first uncheck all checkboxes
		sel.uncheck(SearchObjects.ActivitiesCheckbox);
		//sel.uncheck(SearchObjects.BlogsCheckbox);
		//sel.uncheck(SearchObjects.BookmarkCheckbox);
		sel.uncheck(SearchObjects.CommunitiesCheckbox);
		sel.uncheck(SearchObjects.FilesCheckbox);
		sel.uncheck(SearchObjects.ForumsCheckbox);
		sel.uncheck(SearchObjects.ProfilesCheckbox);
		sel.uncheck(SearchObjects.WikisCheckbox);
		sel.uncheck(SearchObjects.StatusUpdatesCheckbox);
	}

	public void ComponentAndTagToSearchFor(String ComponentName, String Tagname) throws Exception {

		sel.check(ComponentName);
		sleep(1000);

		cautiousFocus(SearchObjects.AdvancedSearchTag);
		sel.type(SearchObjects.AdvancedSearchTag, Tagname);
		sleep(1000);

		cautiousFocus(SearchObjects.SearchButton);
		cautiousClick(SearchObjects.SearchButton);

		//Wait for the Search Results to appear
		try {
			int i = 0;
			while (i < 100) {
				if (sel.isTextPresent("Loading...")) {
					Thread.sleep(1000);
					i = i + 1;
				}
				break;
			}
			sleep(1000);
		} catch (Exception e) {
			sleep(2000);
		}
	}

	public void CheckForSearchError() throws Exception {

		Assert.assertFalse(sel.isTextPresent("500: CLFRW0075W: Failed to load the index at startup, it may not have being created yet."));
	}

	public void CheckForResults() throws Exception {

		Assert.assertFalse(sel.isTextPresent("No results were found for that search"));
	}

	public void VerifySearchResults(String Username, String Tagname, String Title, String Description) throws Exception {

		String UsernameReturned = sel.getValue(SearchObjects.ReturnedUsername);
		String TagReturned = sel.getValue(SearchObjects.ReturnedTag);
		String TitleOfObject = sel.getValue(SearchObjects.ReturnedObjectName);
		String DescriptionReturned = sel.getValue(SearchObjects.ReturnedDescription);

		Assert.assertTrue(UsernameReturned.equals(Username));
		Assert.assertTrue(TagReturned.equals(Tagname));
		Assert.assertTrue(TitleOfObject.equals(Title));
		Assert.assertTrue(DescriptionReturned.equals(Description));

	}

}
