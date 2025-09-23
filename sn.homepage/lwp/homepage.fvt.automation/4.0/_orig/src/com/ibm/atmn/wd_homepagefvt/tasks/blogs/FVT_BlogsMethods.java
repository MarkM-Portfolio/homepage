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

package com.ibm.atmn.wd_homepagefvt.tasks.blogs;


import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;


import org.testng.Assert;

import static org.testng.AssertJUnit.*;

public class FVT_BlogsMethods extends FVT_CommunitiesMethods {

	public void startABlog() throws Exception {
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);

	}

	public void CreateABlogAsAdmin(String BlogsName, String BlogsAddress) throws Exception {
		// Enter the details for the blogs
		//Fill form
		driver.getSingleElement(BlogsObjects.BlogsNameField).type(BlogsName);
		driver.getSingleElement(BlogsObjects.BlogsAddress).type(BlogsAddress);
		driver.getSingleElement(BlogsObjects.BlogsTags).type(BlogsData.BlogsTag);
		driver.getSingleElement(BlogsObjects.BlogsDescription).type(BlogsData.BlogsDescription);

		//select
		driver.getSingleElement(BlogsObjects.BlogsTimeZone).useAsDropdown().selectOptionByVisibleText(BlogsData.BlogsTimeZoneOption);
		driver.getSingleElement(BlogsObjects.BlogsTheme).useAsDropdown().selectOptionByVisibleText(BlogsData.BlogsThemeOption);

		// Save the form
		driver.getSingleElement(CommonObjects.SaveButton).click();
		sleep(2000);
		Assert.assertTrue(driver.isTextPresent(BlogsName));
		Assert.assertTrue(driver.isTextPresent(BlogsData.BlogsDescription));

		
	}
	
	public void SetBlogAsPrimary(String BlogsName) throws Exception{
		// Set as default
		clickLink(BlogsObjects.BlogsSetAsPrimaryBlog);

		// Verify the change
		assertTrue(driver.isTextPresent("You have set ["+BlogsName+"] to be your primary blog"));

	}

	public void ChangeAdminSettings() throws Exception {
		// Click on Admistration
		clickLink(BlogsObjects.Administration);

		// Edit the Handle of Blog to server as Blogs Homepage
		driver.getSingleElement(BlogsObjects.BlogsSiteSettingsHandleOfBlog).type(stamp(BlogsData.BlogsHandleOfBlog));

		// Save the change
		clickLink(BlogsObjects.BlogsSiteSettingsSave);

		// Verify the change
		Assert.assertTrue(driver.isTextPresent("Change saved."));
	}

	public String CreateABlogAsStandardUser(String BlogsName, String BlogsAddress) throws Exception {
		//Fill form
		driver.getSingleElement(FVT_BlogsObjects.BlogsNameField).type(BlogsName);
		driver.getSingleElement(FVT_BlogsObjects.BlogsAddress).type(BlogsAddress);
		driver.getSingleElement(FVT_BlogsObjects.BlogsTags).type(FVT_BlogsData.BlogsTag);
		driver.getSingleElement(FVT_BlogsObjects.BlogsDescription).type(FVT_BlogsData.BlogsDescription);

		//select
		driver.getSingleElement(FVT_BlogsObjects.BlogsTimeZone).useAsDropdown().selectOptionByVisibleText(BlogsData.BlogsTimeZoneOption);
		driver.getSingleElement(FVT_BlogsObjects.BlogsTheme).useAsDropdown().selectOptionByVisibleText(BlogsData.BlogsThemeOption);

		// Save the form
		driver.getSingleElement(CommonObjects.SaveButton).click();
		sleep(2000);
		Assert.assertTrue(driver.isTextPresent(BlogsName));
		Assert.assertTrue(driver.isTextPresent(FVT_BlogsData.BlogsDescription));
		return BlogsName;
	}

	public void CreateANewBlogEntry(String BlogsName, String BlogEntryName) throws Exception {
		// Select the blog that you just created
		clickLink("link=" + BlogsName);
		sleep(1000);

		// Click on the New Entry button
		clickLink(FVT_BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryTitle).type(BlogEntryName);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTags);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryAddTagsTextfield).type(FVT_BlogsData.BlogsNewEntryTag);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTagsOK);
		typeNativeInCkEditor(BlogsData.BlogsNewEntryEntry);

		//save
		clickLink(FVT_BlogsObjects.BlogsNewEntryPost);
		sleep(2000);
		Assert.assertTrue(driver.isTextPresent(BlogEntryName));
		Assert.assertTrue(driver.isTextPresent(FVT_BlogsData.BlogsNewEntryEntry));

	}
	
	public String CreateANewBlogEntry(String BlogEntryName) throws Exception {
		
		// Click on the New Entry button
		clickLink(FVT_BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryTitle).type(BlogEntryName);
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTags);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryAddTagsTextfield).type(FVT_BlogsData.BlogsNewEntryTag);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTagsOK);
		Thread.sleep(500);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryHTML).click();
		Thread.sleep(500);
		driver.getSingleElement(BlogsObjects.BlogsNewEntryRichText).type(FVT_BlogsData.BlogsNewEntryEntry);		
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.BlogsNewEntryPost);
		
		return BlogEntryName;

	}
	
	public String CreateANewBlogEntryDraft(String BlogEntryName) throws Exception {
		
		// Click on the New Entry button
		clickLink(FVT_BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryTitle).type(BlogEntryName);
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTags);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryAddTagsTextfield).type(FVT_BlogsData.BlogsNewEntryTag);
		clickLink(FVT_BlogsObjects.BlogsNewEntryAddTagsOK);
		Thread.sleep(500);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryHTML).click();
		Thread.sleep(500);
		driver.getSingleElement(FVT_BlogsObjects.BlogsNewEntryRichText).type(FVT_BlogsData.BlogsNewEntryEntry);		
		Thread.sleep(500);
		
		clickLink(FVT_BlogsObjects.BlogsNewEntrySaveAsDraft);
		
		//clickLink(FVT_BlogsObjects.BlogsNewEntryCancel);
		
		return BlogEntryName;

	}
	
	public void CreateAUpdatedBlogEntry() throws Exception {
			
		//Edit blog entry
		clickLink(FVT_BlogsObjects.BlogsEdit);
		
		typeNativeInCkEditor(FVT_BlogsData.BlogsNewUpdatedEntry);
		Thread.sleep(1000);
		driver.getFirstElement(FVT_BlogsObjects.BlogsNewEntryPost).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsNewEntryPost).click();
		}
	
	
	public void updateBlogSettings() throws Exception {
		
		//Edit blog entry
		clickLink(FVT_BlogsObjects.BlogsSettings);
		
		
		clickLink(FVT_BlogsObjects.BlogsSettingsUpdateSettings);
	
		}
	
	

	public void CreateANewBlogEntryWithImage(String BlogEntryName)
			throws Exception {
		// Click on the New Entry button
		clickLink(BlogsObjects.BlogsNewEntry);

		// Fill in the form for new entry
		driver.getSingleElement(BlogsObjects.BlogsNewEntryTitle).type(BlogEntryName);

		//add image
		driver.getSingleElement(BlogsObjects.BlogsCKEInsertImageButton).click();
		sleep(1000);
		driver.getSingleElement(BlogsObjects.BlogsCKEInsertFromRecent).doubleClick();
		driver.getSingleElement(BlogsObjects.BlogsCKEChoosePhoto).click();
		sleep(500);
		driver.getSingleElement(BlogsObjects.BlogsCKEInsertButton).click();
		sleep(2500);
		//save
		clickLink(BlogsObjects.BlogsNewEntryPost);

			
	}
	
	

	public void AddACommentToEntry() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(FVT_BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_BlogsObjects.BlogsCommentTextArea).type(BlogsData.BlogsCommentText);

		// Submit comment
		clickLink(FVT_BlogsObjects.BlogsCommentSubmit);

	}
	
	public String AddACommentToEntry(String Comment) throws Exception {
		// Click on the Add a comment link for entry
		clickLink(FVT_BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_BlogsObjects.BlogsCommentTextArea).type(Comment);

		// Submit comment
		clickLink(FVT_BlogsObjects.BlogsCommentSubmit);
		
		return Comment;

	}
	
	public void CreateATrackbackToEntry() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(FVT_BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_BlogsObjects.BlogsCommentTextArea).type(BlogsData.BlogsCommentText);

		// Add Trackback	
		clickLink(FVT_BlogsObjects.BlogsCommentAddEntryToBlog);
		
		// Submit comment
		clickLink(FVT_BlogsObjects.BlogsCommentSubmit);

	}
	
	
	public String CreateANewIdea(String IdeablogEntryName) throws Exception {
		
		// Click on the New Entry button
		clickLink(FVT_BlogsObjects.NewIdea);

		// Fill in the form for new entry
		driver.getSingleElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(IdeablogEntryName);
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.IdeablogNewEntryAddTags);
		driver.getSingleElement(FVT_BlogsObjects.IdeablogNewEntryAddTagsTextfield).type(FVT_BlogsData.BlogsNewEntryTag);
		clickLink(FVT_BlogsObjects.IdeablogNewEntryAddTagsOK);
		Thread.sleep(500);
		driver.getSingleElement(FVT_BlogsObjects.IdeablogNewEntryHTML).click();
		Thread.sleep(500);
		driver.getSingleElement(FVT_BlogsObjects.IdeablogNewEntryRichText).type(FVT_BlogsData.IdeablogNewEntryEntry);		
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.IdeablogNewEntryPost);
		
		return IdeablogEntryName;

	}
	
	public void CreateAUpdatedIdea() throws Exception {
		
		//Edit blog entry
		clickLink(FVT_BlogsObjects.BlogsEdit);
		
		typeNativeInCkEditor("");
		typeNativeInCkEditor(FVT_BlogsData.IdeablogNewEntryUpdated);
		Thread.sleep(500);
		clickLink(FVT_BlogsObjects.BlogsNewEntryPost);
		Thread.sleep(500);
	
		}
	
	public void AddACommentToIdea() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(FVT_BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_BlogsObjects.BlogsCommentTextArea).type(FVT_BlogsData.BlogsCommentText);

		// Submit comment
		clickLink(FVT_BlogsObjects.BlogsCommentSubmit);

	}
	
	public void CreateATrackbackToIdea() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(FVT_BlogsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		driver.getSingleElement(FVT_BlogsObjects.BlogsCommentTextArea).type(BlogsData.BlogsCommentText);

		// Add Trackback	
		clickLink(FVT_BlogsObjects.BlogsCommentAddEntryToBlog);
		
		// Submit comment
		clickLink(FVT_BlogsObjects.BlogsCommentSubmit);

	}
	
	public void openBlog(String BlogName) throws Exception {

		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + BlogName);
		

	}
	
	public void followBlog(String BlogName) throws Exception {

		//Open blog
		clickLink(FVT_BlogsObjects.PublicBlogs);
		
		clickLink(FVT_BlogsObjects.BlogsPublicListing);
		
		clickLink("link="+BlogName);
		
		//Follow blog
		clickLink(FVT_BlogsObjects.FollowBlog);
		

	}
	
	public void notifyAboutBlogEntry() throws Exception {
		
		//Click more actions button
		clickLink(FVT_BlogsObjects.MORE_ACTIONS);
		
		//Click notify other peoplr button
		clickLink(FVT_BlogsObjects.NOTIFY_OTHER_PEOPLE);
		
		//Type and select user from typeahead
		driver.getSingleElement(FVT_BlogsObjects.NOTIFY_TYPEAHEAD).type(CommonData.IC_LDAP_UserName451_Typeahead);
		
		driver.getSingleElement(FVT_BlogsObjects.NOTIFY_TYPEAHEAD_SEARCH).click();
		
		driver.getSingleElement(FVT_BlogsObjects.NOTIFY_TYPEAHEAD_IDENTIFIER).click();
		
		//Send Notification
		clickLink(CommonObjects.SEND_BUTTON);
		
		sleep(500);
		
		assertTrue("Fail: Notification not sent correctly", driver.isTextPresent("The notification has been sent."));	

		
	}
	
	public void assignUserRole(String Role, String User_Typeahead, String User_Fullname) throws Exception {
		
		//Click Author link
		clickLink(FVT_BlogsObjects.AUTHOR_LINK);
		
		//Click add member link
		clickLink(FVT_BlogsObjects.ADD_MEMBERS);
		
		getActiveElement(FVT_BlogsObjects.MEMBER_ROLE).useAsDropdown().selectOptionByVisibleText(Role);

		//Type and select user from typeahead
		driver.getSingleElement(FVT_BlogsObjects.MEMBER_TYPEAHEAD).type(User_Typeahead);
		
		driver.getSingleElement(FVT_BlogsObjects.MEMBER_TYPEAHEAD_SEARCH).click();
		
		driver.getSingleElement(FVT_BlogsObjects.MEMBER_TYPEAHEAD_IDENTIFIER).click();
		
		//Send Notification
		driver.getFirstElement(CommonObjects.SaveButton).click();
		
		assertTrue("Fail: User not added correctly!", driver.isTextPresent("User ["+User_Fullname+"] has been added as "+Role+" on this blog."));	

		
			
	}
	
	public void changeUserRole(String Role) throws Exception {
		
		//Click Author link
		clickLink(FVT_BlogsObjects.AUTHOR_LINK);
		
		if(Role=="Owner"){
			clickLink(FVT_BlogsObjects.OWNER_CHECK);
		}else if(Role=="Author"){
			clickLink(FVT_BlogsObjects.AUTHOR_CHECK);
		}else if (Role=="Draft"){
			clickLink(FVT_BlogsObjects.DRAFT_CHECK);
		}
		
		//Send Notification
		getActiveElement(CommonObjects.SaveButton).click();
		

		
	}
	
	
}
