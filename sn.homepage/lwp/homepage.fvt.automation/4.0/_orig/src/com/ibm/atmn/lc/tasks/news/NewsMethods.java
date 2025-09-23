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

package com.ibm.atmn.lc.tasks.news;

// import com.ibm.automation.bvt.setup.common.Initialize;
import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.news.NewsData;
import com.ibm.atmn.lc.appobjects.news.NewsObjects;
import com.ibm.atmn.lc.appobjects.profiles.ProfilesData;
import com.ibm.atmn.lc.tasks.common.CommonMethods;

import org.testng.AssertJUnit;

public class NewsMethods extends CommonMethods {

	private String UpdateStatus;
	protected String ProfilesBoardEntryNewsBVT;

	public NewsMethods() {

		UpdateStatus = stamp(NewsData.UpdateStatus);
		ProfilesBoardEntryNewsBVT = stamp(ProfilesData.ProfilesBoardEntryNewsBVT);

	}

	public void AddBoardEntry() throws Exception {

		//Add an entry to the board
		sel.type(NewsObjects.ProfilesBoard, ProfilesBoardEntryNewsBVT);
		clickLink(NewsObjects.PostStatus);
	}

	public void OpenDiscoverPeopleView() throws Exception {

		//Load the URL to the people view in homepage
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login again
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the updates tab
		clickLink("link=Updates");

		//Click on the Discover link
		clickLink("link=Discover");

		//Click on the People link
		//clickLink("link=People");

	}

	public void StatusUpdate() throws Exception {

		//Click on the Status Updates link to open the Status Updates page
		clickLink(NewsObjects.LeftNavStatusUpdates);

		//Click on the What are you working on right now? Link
		clickLink(NewsObjects.ClickStatusUpdateLink);

		//Enter and Save a status update
		sel.type(NewsObjects.EnterStatusUpdate, UpdateStatus);

		//clickLink(Objects.SaveStatusUpdate);
		FormSaveButton(NewsObjects.SaveStatusUpdate);

	}

	public void VerifyStatusUpdatedInDiscover() throws Exception {

		//Click on the Status Updates link to open the Status Updates page
		clickLink(NewsObjects.LeftNavDiscover);

		//Now verify that the update is displayed
		AssertJUnit.assertTrue("FAIL: the status update is not appearing as expected", sel.isTextPresent(CommonData.LDAP_FullUsername + "1"));
		AssertJUnit.assertTrue("FAIL: the status update is not appearing as expected", sel.isTextPresent(UpdateStatus));

	}

	public void CreateABlogAsAdmin(String BlogsName, String BlogsAddress) throws Exception {

		// Enter the details for the blogs
		sel.type(NewsObjects.BlogsNameField, BlogsName);
		Thread.sleep(500);
		sel.type(NewsObjects.BlogsAddress, BlogsAddress);
		Thread.sleep(500);
		sel.type(NewsObjects.BlogsTags, NewsData.BlogsTagForNewsBVT);
		Thread.sleep(500);
		sel.type(NewsObjects.BlogsDescription, NewsData.BlogsDescriptionForNewsBVT);
		Thread.sleep(500);
		sel.select(NewsObjects.BlogsTimeZone, NewsData.BlogsTimeZoneOptionForNewsBVT);
		Thread.sleep(500);
		sel.select(NewsObjects.BlogsTheme, NewsData.BlogsThemeOptionForNewsBVT);
		Thread.sleep(500);
		// Save the form
		FormSaveButton(CommonObjects.SaveButton);

	}

	public void CreateANewBlogEntry(String BlogsName, String BlogEntryName) throws Exception {

		// Select the blog that you just created
		clickLink("link=" + BlogsName);
		Thread.sleep(1000);

		// Click on the New Entry button
		clickLink(NewsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		sel.type(NewsObjects.BlogsNewEntryTitle, BlogEntryName);
		Thread.sleep(500);
		clickLink(NewsObjects.BlogsNewEntryAddTags);
		sel.type(NewsObjects.BlogsNewEntryAddTagsTextfield, NewsData.BlogsNewEntryTagForNewsBVT);
		clickLink(NewsObjects.BlogsNewEntryAddTagsOK);
		Thread.sleep(500);
		sel.click(NewsObjects.BlogsNewEntryHTML);
		Thread.sleep(500);
		sel.type(NewsObjects.BlogsNewEntryRichText, NewsData.BlogsNewEntryEntryForNewsBVT);
		Thread.sleep(500);
		clickLink(NewsObjects.BlogsNewEntryPost);

	}

	public void AddACommentToEntry() throws Exception {

		// Click on the Add a comment link for entry
		clickLink(NewsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		sel.type(NewsObjects.BlogsCommentTextArea, NewsData.BlogsCommentText);

		// Submit comment
		clickLink(NewsObjects.BlogsCommentSubmit);

	}

	public void FollowABlog(String BlogsName) throws Exception {

		clickLink("link=" + BlogsName);
		Thread.sleep(1000);

	}
}