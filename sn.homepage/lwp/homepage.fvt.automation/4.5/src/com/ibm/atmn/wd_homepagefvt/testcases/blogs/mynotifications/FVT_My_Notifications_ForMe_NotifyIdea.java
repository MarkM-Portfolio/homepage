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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.mynotifications;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

/*
 * TTT ->  AS - My Notifications - For Me - 00022 - Notify-Idea - Notify user about Idea
 * 
 * Author : Michael Tallant Murphy
 * Description:
 */

public class FVT_My_Notifications_ForMe_NotifyIdea extends FVT_BlogsMethods {

	private User testUser1 = null;
	private User testUser2 = null;
	private String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	private String Entry = FVT_BlogsData.IdeablogNewEntryEntry;
	private String Community = FVT_CommunitiesData.CommunityName
			+ DateTimeStamp;

	@Test
	public void Setup() {

		Reporter.log("Setup");

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		Reporter.log("User 1 -> " + testUser1.getDisplayName());
		Reporter.log("User 2 -> " + testUser2.getDisplayName());

		LoadComponent(CommonObjects.ComponentCommunities);
		Login(testUser2);
		LogoutAndClose();
	}

	@Test(dependsOnMethods = { "Setup" })
	public void createCommunityWithIdeationBlog() throws Exception {

		Reporter.log("Start of Test :: createCommunityWithIdeationBlog");

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

		// Click on the Communities Action button
		clickLink(CommunitiesObjects.Community_Actions_Button);

		// Choose the 3 option - Customize
		driver.getSingleElement(CommunitiesObjects.Menu_Item_3).click();
		driver.getFirstElement(CommunitiesObjects.WidgetIdeationBlog).click();

		driver.getFirstElement(CommunitiesObjects.CloseWidgetSection).click();

		// Click on the Ideation Blog Nav bar Link
		driver.getFirstElement(CommunitiesObjects.LeftNav_IdeationBlog).click();
		driver.getFirstElement(FVT_BlogsObjects.NewIdea).click();

		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryTitle).type(
				Entry);
		typeNativeInCkEditor(FVT_BlogsData.BlogsDescription + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.IdeablogNewEntryPost).click();

		assertTrue("FAIL :: Ideation Blog wasn't found ", driver
				.isTextPresent(Entry));

		notifyAboutBlogEntry(testUser2);

		Reporter.log("End of Test :: createCommunityWithIdeationBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "createCommunityWithIdeationBlog" })
	public void verifyNotification_ForMe() throws Exception {

		Reporter.log("Start of Test :: verifyNotification_ForMe");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser2);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue(
				"FAIL :: (Filter All) Notification wasn't found for Ideation Blog ",
				driver.isTextPresent(testUser1.getDisplayName()
						+ " notified you about the " + Entry + " idea in the "
						+ Community + " Ideation Blog."));

		filterBy("Blogs");

		assertTrue(
				"FAIL :: (Filter Blogs) Notification wasn't found for Ideation Blog ",
				driver.isTextPresent(testUser1.getDisplayName()
						+ " notified you about the " + Entry + " idea in the "
						+ Community + " Ideation Blog."));

		Reporter.log("End of Test :: verifyNotification_ForMe");

		LogoutAndClose();

	}

}
