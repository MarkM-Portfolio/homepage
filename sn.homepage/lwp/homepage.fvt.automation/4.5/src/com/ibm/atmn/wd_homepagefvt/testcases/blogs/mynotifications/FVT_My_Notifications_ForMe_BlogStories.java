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
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

/*
 * TTT ->  AS - My Notifications - For Me - 00011 - Blog Stories can be viewed in For Me
 * 
 * Author : Michael Tallant Murphy
 * Description: 
 */

public class FVT_My_Notifications_ForMe_BlogStories extends FVT_BlogsMethods {

	private User testUser1 = null;
	private User testUser2 = null;
	private String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	private String Blog = FVT_BlogsData.BlogsName1 + DateTimeStamp;
	private String NewEntry = FVT_BlogsData.BlogsNewEntryTitle;

	@Test
	public void Setup() {

		Reporter.log("Setup");

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		Reporter.log("User 1 -> " + testUser1.getDisplayName());
		Reporter.log("User 2 -> " + testUser2.getDisplayName());
		Reporter.log("Blog -> " + Blog);

	}

	@Test(dependsOnMethods = { "Setup" })
	public void startAPublicBlog() throws Exception {

		Reporter.log("Start of Test :: startAPublicBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);

		Login(testUser1);

		driver.getFirstElement(FVT_BlogsObjects.StartABlog).click();

		Blog = CreateABlogAsStandardUser(Blog, FVT_BlogsData.BlogsAddress1
				+ DateTimeStamp);

		driver.getFirstElement("link=" + Blog).click();

		CreateANewBlogEntry(NewEntry);

		assertTrue("FAIL :: New Blog Entry wasn't found ", driver
				.isTextPresent(NewEntry));

		Reporter.log("End of Test :: startAPublicBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "startAPublicBlog" })
	public void addEntryToPublicBlog() throws Exception {

		Reporter.log("Start of Test :: addEntryToBlog");

		// Load Activities
		LoadComponent(CommonObjects.ComponentBlogs);

		Login(testUser2);

		driver.getFirstElement(FVT_BlogsObjects.PublicBlogs).click();

		driver.getFirstElement(FVT_BlogsObjects.BlogsPublicListing).click();

		Reporter.log(NewEntry);
		// Open Blog that user one created
		driver.getFirstElement("link=" + Blog).click();
		driver.getFirstElement("link=" + NewEntry).click();

		driver.getFirstElement(FVT_BlogsObjects.BlogsAddACommentLink).click();
		driver.getFirstElement(FVT_BlogsObjects.BlogsCommentTextArea).type(
				FVT_BlogsData.BlogsCommentText + DateTimeStamp);
		driver.getFirstElement(FVT_BlogsObjects.BlogsCommentSubmit).click();

		assertTrue("FAIL :: Blog Comment wasn't found ", driver
				.isTextPresent(FVT_BlogsData.BlogsCommentText + DateTimeStamp));

		Reporter.log("End of Test :: addEntryToBlog");

		LogoutAndClose();

	}

	@Test(dependsOnMethods = { "addEntryToPublicBlog" })
	public void verifyPublicBlogComments() throws Exception {

		Reporter.log("Start of Test :: verifyPublicBlogComments");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		assertTrue("FAIL :: Comment wasn't found ", driver
				.isTextPresent(FVT_BlogsData.BlogsCommentText + DateTimeStamp));
		assertTrue("FAIL :: Comment wasn't found ", driver
				.isTextPresent(testUser2.getDisplayName()
						+ " commented on your " + NewEntry
						+ " blog entry in the " + Blog + " blog."));

		filterBy("Blogs");

		assertTrue("FAIL :: Filter set to Blogs -> Comment wasn't found ",
				driver.isTextPresent(FVT_BlogsData.BlogsCommentText
						+ DateTimeStamp));
		assertTrue("FAIL :: Filter set to Blogs -> Comment wasn't found ",
				driver.isTextPresent(testUser2.getDisplayName()
						+ " commented on your " + NewEntry
						+ " blog entry in the " + Blog + " blog."));

		Reporter.log("End of Test :: verifyPublicBlogComments");

		LogoutAndClose();

	}

}
