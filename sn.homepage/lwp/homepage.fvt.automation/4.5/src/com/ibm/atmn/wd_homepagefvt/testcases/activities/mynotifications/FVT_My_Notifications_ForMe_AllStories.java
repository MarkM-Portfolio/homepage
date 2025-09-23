/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2013                                          */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.testcases.activities.mynotifications;

import static org.testng.AssertJUnit.assertTrue;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;

/*
 * TTT ->  AS - My Notifications - For Me - 00011 - All Stories can be viewed in For Me
 * 
 * Author : Michael Tallant Murphy
 * Description: User one creates a public status update, public blog ,public wiki , public forum , public activity, public file and public community with a public file
 * attached to it. User two comments makes a comment on each of the above , user one logs back in and verify that the comments where made etc..
 * 
 *  ToDO : I have skipped over uploading a file to public file to a community and uploading a public file , as there are issues with selenium in doing so.
 */

// Note that java doesn't allow multiple inheritance , hence why i didn't use previously existing methods for creating community/blogs/wiki/etc...
public class FVT_My_Notifications_ForMe_AllStories extends
		FVT_ActivitiesMethods {

	private User testUser1 = null;
	private User testUser2 = null;
	private String PublicCommunity;
	private String StatusMessage = "My Notifications Status Updates - For Me - All "
			+ CommonMethods.genDateBasedRandVal();
	private String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	private String PublicActivity;
	private FormInputHandler typist;
	private String ActivityName;
	private String ActivityComment;
	private String Comment;

	@Test
	public void Setup() {

		Reporter.log("Setup");

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		Reporter.log("User 1 -> " + testUser1.getDisplayName());
		Reporter.log("User 2 -> " + testUser2.getDisplayName());

	}

	@Test(dependsOnMethods = { "Setup" })
	public void updateStatus() {
		Reporter.log("Start of Test :: updateStatus");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.StatusUpdates).click();

		// Post Status Update message
		driver.getFirstElement("css=div#mentionstextAreaNode_0.lotusText")
				.click();
		driver.getFirstElement("css=div#mentionstextAreaNode_0.lotusText")
				.type(StatusMessage);
		driver.getFirstElement(FVT_HomepageObjects.PostStatus).click();

		assertTrue("FAIL :: Status Update message wasn't posted ", driver
				.isTextPresent(StatusMessage));

		LogoutAndClose();

		Reporter.log("End of Test :: updateStatus");
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startAPublicActivity() throws Exception {

		Reporter.log("Start of Test :: createAPublicActivity");

		// Load Activities
		LoadComponent(CommonObjects.ComponentActivities);

		Login(testUser1);

		// Create a Public Activity
		Reporter.log("Creating a Public Activity");
		PublicActivity = startAnActivity(
				FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data
						+ DateTimeStamp, typist, null);
		Reporter.log("Public Activity -> " + PublicActivity);

		// Add Entry
		assertTrue("FAIL :: Add Entry Butten is present", driver
				.isElementPresent(FVT_ActivitiesObjects.New_Entry2));

		ActivityName = addEntryToActivity_Basic(
				FVT_ActivitiesData.Start_An_Activity_InputText_Name_Data
						+ DateTimeStamp, typist, false);
		Reporter.log("Activity Name -> " + ActivityName);

		smartSleep("css=[class='entryTitleBar']");

		// Verify that the post is made
		assertTrue("FAIL :: Entry made ", driver.getFirstElement(
				"css=[class='entryTitleBar']").isTextPresent(ActivityName));

		// Go to Members view
		driver.getFirstElement(FVT_ActivitiesObjects.Members).click();
		// Click change access
		driver.getFirstElement(FVT_ActivitiesObjects.ChangeAccess).click();
		// Select public and save
		driver.getFirstElement(FVT_ActivitiesObjects.CheckPublic).click();
		driver.getFirstElement(FVT_ActivitiesObjects.SaveAccessChange).click();

		// Check that the Access has changed to public on the Members View
		assertTrue(
				"FAIL :: Access changed to Public ",
				driver
						.isElementPresent("css=[id='pubAccesLink_id'][title='Public Access']"));

		// Verify that activity is on the Public Activities view/page
		driver.getFirstElement(FVT_ActivitiesObjects.Activities_Tab).click();
		//driver.getFirstElement(FVT_ActivitiesObjects.Activities_LeftNav_Active).click();

		assertTrue("FAIL :: Activity wasn't found on Public Activities page ",
				driver.isElementPresent("css=a.oaActivityNameNode:contains('"
						+ PublicActivity + "')"));

		LogoutAndClose();

		Reporter.log("End of Test :: createAPublicActivity");

	}

	@Test(dependsOnMethods = { "Setup" })
	public void startAPublicBlog() throws Exception {

		Reporter.log("Start of Test :: createAPublicBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);

		Login(testUser1);

		// new
		// FVT_BlogsMethods().CreateABlogAsStandardUser(FVT_BlogsData.BlogsName1+DateTimeStamp,
		// FVT_BlogsData.BlogsAddress1+DateTimeStamp);
		// Creating a Blog - Note that java doesn't allow multiple inheritance ,
		// hence why i didn't use FVT_BlogsMethods.CreateABlogAsStandardUser()

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement(FVT_BlogsObjects.StartABlog).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsNameField).type(
				FVT_BlogsData.BlogsName1 + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.BlogsAddress).type(
				FVT_BlogsData.BlogsAddress1 + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.BlogsTags).type(
				FVT_BlogsData.BlogsTag);
		driver.getFirstElement(FVT_BlogsObjects.BlogsDescription).type(
				FVT_BlogsData.BlogsDescription + DateTimeStamp);
		//driver.getFirstElement(FVT_BlogsObjects.SaveBtn).click();

		assertTrue("FAIL :: Blog title wasn't found ", driver
				.isTextPresent(FVT_BlogsData.BlogsName1 + DateTimeStamp));
		assertTrue("FAIL :: Blog description wasn't found ", driver
				.isTextPresent(FVT_BlogsData.BlogsDescription + DateTimeStamp));

		driver.getFirstElement(
				"link=" + FVT_BlogsData.BlogsName1 + DateTimeStamp).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsNewEntry).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsNewEntryTitle).type(
				"New Blog Entry - startAPublicBlog()" + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.BlogsNewEntryPost).click();

		assertTrue("FAIL :: New Blog Entry wasn't found ", driver
				.isTextPresent("New Blog Entry - startAPublicBlog()"
						+ DateTimeStamp));

		Reporter.log("End of Test :: createAPublicBlog");
	}

	// @Test (dependsOnMethods = { "Setup" })
	public void uploadAPublicFile() throws Exception, AWTException {
		// TODO: Have issues with Uploading files

		Robot OptimusPrime = new Robot();

		Reporter.log("Start of Test :: uploadAPublicFile");

		// Load Activities
		LoadComponent(CommonObjects.ComponentFiles);

		Login(testUser1);

		driver.getFirstElement(
				"css=button[title='Upload files from your computer']").click();
		Element coordinates = driver
				.getFirstElement("css=button[class='lotusBtn']:nth(3)");

		OptimusPrime.mouseMove(coordinates.getLocation().x, coordinates
				.getLocation().y);
		OptimusPrime.mousePress(InputEvent.BUTTON3_MASK);
		OptimusPrime.mouseRelease(InputEvent.BUTTON3_MASK);

		sleep(50000);

		Reporter.log("End of Test :: uploadAPublicFile");
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startPublicForum() {

		Reporter.log("Start of Test :: startPublicForum");

		// Load Activities
		LoadComponent(CommonObjects.ComponentForums);

		Login(testUser1);

		// Creating a Forum
		driver.getFirstElement(FVT_ForumsObjects.My_Forums_Tab)
				.click();
		driver.getFirstElement(FVT_ForumsObjects.Start_A_Forum).click();
		driver.getFirstElement(FVT_ForumsObjects.Start_A_Forum_InputText_Name)
				.type(
						FVT_ForumsData.Start_A_Topic_InputText_Title_Data
								+ DateTimeStamp);
		driver.getFirstElement(FVT_ForumsObjects.Start_A_Forum_InputText_Tags)
				.type(FVT_ForumsData.Start_A_Topic_InputText_Tags_Data);
		driver.getFirstElement(FVT_ForumsObjects.SaveButton).click();

		driver.getFirstElement(FVT_ForumsObjects.Start_A_Topic).click();
		driver.getFirstElement(FVT_ForumsObjects.Start_A_Topic_InputText_Title)
				.type("New Forum Topic" + DateTimeStamp);
		driver.getFirstElement(FVT_ForumsObjects.Start_A_Topic_InputText_Tags)
				.type(FVT_ForumsData.Start_A_Topic_InputText_Tags_Data);
		driver.getFirstElement(FVT_ForumsObjects.SaveButton).click();
		driver.getFirstElement(FVT_ForumsObjects.ConfirmationOKBtn).click();

		assertTrue(
				"FAIL :: Forum title wasn't found ",
				driver
						.isTextPresent(FVT_ForumsData.Start_A_Topic_InputText_Title_Data
								+ DateTimeStamp));

		Reporter.log("End of Test :: startPublicForum");
	}

	@Test(dependsOnMethods = { "Setup" })
	public void startPublicWiki() {

		Reporter.log("Start of Test :: startPublicWiki");

		// Load Activities
		LoadComponent(CommonObjects.ComponentWikis);

		Login(testUser1);

		driver.getFirstElement(FVT_WikisObjects.Start_New_Wiki_Button).click();

		// Create a Wiki
		driver.getFirstElement(FVT_WikisObjects.Wiki_Name_Field).type(
				FVT_WikisData.CI_Box_Public_Wiki);
		driver.getFirstElement(FVT_WikisObjects.Tags_Field).type(
				FVT_WikisData.Tag_For_Public_Wiki);
		driver.getFirstElement(FVT_WikisObjects.Description_Field).type(
				DateTimeStamp + FVT_WikisData.CI_Box_Public_Wiki
						+ DateTimeStamp);
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		assertTrue("FAIL :: Wiki title wasn't found ", driver
				.isTextPresent(FVT_WikisData.CI_Box_Public_Wiki));

		Reporter.log("End of Test :: startPublicWiki");

	}

	// @Test (dependsOnMethods = { "Setup" })
	public void startPublicCommunity() {

		Reporter.log("Start of Test :: startPublicCommunity");

		// Load Activities
		LoadComponent(CommonObjects.ComponentCommunities);

		Login(testUser1);

		// Create a Community
		driver.getFirstElement("css=span#createPlaceButton.lotusBtn a").click();
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityName).type(
				FVT_CommunitiesData.CommunityName + DateTimeStamp);
		driver.getFirstElement(FVT_CommunitiesObjects.CommunityTag).type(
				FVT_CommunitiesData.CommunityTag);
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		assertTrue("FAIL :: Community title wasn't found ", driver
				.isTextPresent(FVT_CommunitiesData.CommunityName
						+ DateTimeStamp));

		Reporter.log("End of Test :: startPublicCommunity");

	}

	@Test(dependsOnMethods = { "updateStatus" })
	public void makeCommentOnStatus() {

		Reporter.log("Start of Test :: makeCommentOnStatus");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser2);

		driver.getFirstElement(FVT_HomepageObjects.Discover).click();

		WebDriver wd = (WebDriver) driver.getBackingObject();
		List<Element> list = driver
				.getElements("css=li[id^='com_ibm_social_as_item_StatusStandaloneNewsItem_']");

		WebElement we = null;

		for (Element e : list) {
			e.hover();

			we = (WebElement) e.getBackingObject();
			if (we.getText().contains(
					testUser1.getDisplayName() + " " + StatusMessage)) {
				we
						.findElement(
								By
										.cssSelector("div.lotusPostMore ul.lotusInlinelist li.lotusFirst a"))
						.click();
				driver.getFirstElement(
						"css=div[id^='mentionstextAreaNode_']:nth(1)").click();
				driver.getFirstElement(
						"css=div[id^='mentionstextAreaNode_']:nth(1)").type(
						"Status Update Comment Made" + DateTimeStamp);
				driver
						.getFirstElement(
								"css= div.lotusActions ul.lotusInlinelist li.lotusFirst a[dojoattachpoint='commentPostActoin']")
						.click();
				break;
			}

		}

		Reporter.log("End of Test :: makeCommentOnStatus");

	}

	@Test(dependsOnMethods = { "startAPublicActivity" })
	public void makeCommentOnActivity() {

		Reporter.log("Start of Test :: makeCommentOnActivity");

		// Load Activities
		LoadComponent(CommonObjects.ComponentActivities);

		Login(testUser2);

		//driver.getFirstElement(FVT_ActivitiesObjects.Activities_LeftNav_Active).click();

		driver
				.getFirstElement(
						"link="
								+ FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data
								+ DateTimeStamp).click();
		driver.getFirstElement(FVT_ActivitiesObjects.ActivityMoreBtn).click();
		driver.getFirstElement(FVT_ActivitiesObjects.ActivityAddCommentBtn).click();

		ActivityComment = addCommentToActivityEntry(typist, false);

		/*
		 * driver.getFirstElement(FVT_ActivitiesObjects.ActivityAddCommentTxtField
		 * ).click();
		 * driver.getFirstElement(FVT_ActivitiesObjects.ActivityAddCommentTxtField
		 * ).type("Activities Update Comment Made"+DateTimeStamp);
		 * driver.getFirstElement(FVT_ActivitiesObjects.SaveBtn).click();
		 */

		Reporter.log("End of Test :: makeCommentOnActivity");

	}

	@Test(dependsOnMethods = { "startAPublicBlog" })
	public void makeCommentOnBlog() {

		Reporter.log("Start of Test :: makeCommentOnBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);

		Login(testUser2);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();
		driver.getFirstElement(
				"link=New Blog Entry - startAPublicBlog()" + DateTimeStamp)
				.click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsAddACommentLink).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsCommentTextArea).type(
				"Blog Update Comment Made" + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.BlogsCommentSubmit).click();

		Reporter.log("End of Test :: makeCommentOnBlog");

	}

	public void makeCommentOnFile() {

	}

	@Test(dependsOnMethods = { "startPublicForum" })
	public void makeCommentOnForum() {

		Reporter.log("Start of Test :: makeCommentOnWiki");

		// Load Activities
		LoadComponent(CommonObjects.ComponentForums);

		Login(testUser2);

		driver.getFirstElement(FVT_ForumsObjects.Public_Forums_Tab).click();
		driver.getFirstElement(
				"link=" + FVT_ForumsData.Start_A_Topic_InputText_Title_Data
						+ DateTimeStamp).click();
		driver.getFirstElement("link=New Forum Topic" + DateTimeStamp).click();
		driver.getFirstElement("link=Reply").click();
		typeNativeInCkEditor("Forum Update Comment Made" + DateTimeStamp);
		driver.getFirstElement(FVT_ForumsObjects.SaveButton).click();

		Reporter.log("End of Test :: makeCommentOnWiki");

	}

	@Test(dependsOnMethods = { "startPublicWiki" })
	public void makeCommentOnWiki() {

		Reporter.log("Start of Test :: makeCommentOnWiki");

		// Load Activities
		LoadComponent(CommonObjects.ComponentWikis);

		Login(testUser2);

		driver.getFirstElement(FVT_WikisObjects.Public_Wikis_Tab).click();
		driver.getFirstElement("link=" + FVT_WikisData.CI_Box_Public_Wiki)
				.click();
		driver.getFirstElement(FVT_WikisObjects.Add_Comment_Link).click();
		driver.getFirstElement(FVT_WikisObjects.Add_Comment_Editor).type(
				"Wiki Update Comment Made" + DateTimeStamp);
		driver.getFirstElement(FVT_WikisObjects.Save_Button).click();

		Reporter.log("End of Test :: makeCommentOnWiki");

	}

	@Test(dependsOnMethods = { "makeCommentOnStatus" })
	public void verifyStatusComment() {

		Reporter.log("Start of Test :: verifyStatusComment");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue("FAIL :: Status wasn't found ", driver
				.isTextPresent(testUser2.getDisplayName()
						+ " commented on your message."));
		assertTrue("FAIL :: Status comment wasn't  found ", driver
				.isTextPresent("Status Update Comment Made" + DateTimeStamp));

		Reporter.log("End of Test :: verifyStatusComment");

	}

	@Test(dependsOnMethods = { "makeCommentOnActivity" })
	public void verifyActivityComment() {

		Reporter.log("Start of Test :: verifyActivityComment");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue("FAIL :: Status comment wasn't  found ", driver
				.isTextPresent(ActivityComment));

		Reporter.log("End of Test :: verifyActivityComment");

	}

	@Test(dependsOnMethods = { "makeCommentOnBlog" })
	public void verifyBlogComment() {

		Reporter.log("Start of Test :: verifyBlogComment");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue(
				"FAIL :: Status wasn't found ",
				driver
						.isTextPresent(testUser2.getDisplayName()
								+ " commented on your New Blog Entry - startAPublicBlog()"
								+ DateTimeStamp));
		assertTrue("FAIL :: Status comment wasn't  found ", driver
				.isTextPresent("Blog Update Comment Made" + DateTimeStamp));

		Reporter.log("End of Test :: verifyBlogComment");

	}

	@Test(dependsOnMethods = { "makeCommentOnWiki" })
	public void verifyWikiComment() {

		Reporter.log("Start of Test :: verifyWikiComment");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue("FAIL :: Wiki wasn't found ", driver.isTextPresent(testUser2
				.getDisplayName()
				+ " commented on the wiki page Welcome to "
				+ FVT_WikisData.CI_Box_Public_Wiki
				+ " in the "
				+ FVT_WikisData.CI_Box_Public_Wiki + " wiki."));
		assertTrue("FAIL :: Status comment wasn't  found ", driver
				.isTextPresent("Wiki Update Comment Made" + DateTimeStamp));

		Reporter.log("End of Test :: verifyWikiComment");

	}

	@Test(dependsOnMethods = { "makeCommentOnForum" })
	public void verifyForumsComment() {

		Reporter.log("Start of Test :: verifyForumsComment");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue("FAIL :: Wiki wasn't found ", driver.isTextPresent(testUser2
				.getDisplayName()
				+ " replied to the Re: New Forum Topic"
				+ DateTimeStamp
				+ " topic thread started by you in the FVT New Topic "
				+ DateTimeStamp + " forum."));
		assertTrue("FAIL :: Status comment wasn't  found ", driver
				.isTextPresent("Forum Update Comment Made" + DateTimeStamp));

		Reporter.log("End of Test :: verifyForumsComment");

	}
}
