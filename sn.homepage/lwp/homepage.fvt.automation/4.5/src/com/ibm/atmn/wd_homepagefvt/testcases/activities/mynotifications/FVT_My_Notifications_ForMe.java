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

import org.testng.AssertJUnit;
import org.testng.AssertJUnit.*;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;

/*
 * TTT ->  AS - My Notifications - For Me - 00011 - Activity Stories can be viewed in For Me
 * 
 * Author : Michael Tallant Murphy
 * Description: User 1 creates a public activity , user 2 adds a comment to that activity 
 * user 1 verify the comment on My Notifications page 
 */

public class FVT_My_Notifications_ForMe extends FVT_ActivitiesMethods {

	private User testUser1 = null;
	private User testUser2 = null;
	private String PublicCommunity;
	private String DateTimeStamp = CommonMethods.genDateBasedRandVal();
	private String PublicActivity;
	private FormInputHandler typist;
	private String ActivityName;
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
	public void createAPublicActivity() throws Exception {

		Reporter.log("Start of Test :: createAPublicActivity");

		// Load Activities
		LoadComponent(CommonObjects.ComponentActivities);

		Login(testUser1);

		// Create a Public Activity
		Reporter.log("Creating a Public Activity");
		PublicActivity = startAnActivity(
				FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data
						+ genDateBasedRandVal(), typist, null, "Community",
				"Author");
		Reporter.log("Public Activity -> " + PublicActivity);

		// Add Entry
		assertTrue("PASS :: Add Entry Butten is present", driver
				.isElementPresent(FVT_ActivitiesObjects.New_Entry2));

		ActivityName = addEntryToActivity_Basic(
				FVT_ActivitiesData.Start_An_Activity_InputText_Name_Data
						+ DateTimeStamp, typist, false);
		Reporter.log("Activity Name -> " + ActivityName);

		smartSleep("css=[class='entryTitleBar']");

		// Verify that the post is made
		assertTrue("PASS :: Entry made ", driver.getFirstElement(
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
				"PASS :: Access changed to Public ",
				driver
						.isElementPresent("css=[id='pubAccesLink_id'][title='Public Access']"));

		// Verify that activity is on the Public Activities view/page
		driver.getFirstElement(FVT_ActivitiesObjects.Activities_Tab).click();
		driver.getFirstElement(FVT_ActivitiesObjects.Activities_LeftNav_Active)
				.click();

		assertTrue("PASS :: Activity was found on Public Activities page ",
				driver.isElementPresent("css=a.oaActivityNameNode:contains('"
						+ PublicActivity + "')"));

		LogoutAndClose();

		Reporter.log("End of Test :: createAPublicActivity");

	}

	@Test(dependsOnMethods = { "createAPublicActivity" })
	public void addCommentToEntry() {

		Reporter.log("Start of Test :: createAPublicActivity");

		// Load Activities
		LoadComponent(CommonObjects.ComponentActivities);

		Login(testUser2);

		driver.getFirstElement(FVT_ActivitiesObjects.Activities_LeftNav_Active)
				.click();

		// Find User 1 Activity
		assertTrue("PASS :: Activity was found on Public Activities page ",
				driver.isElementPresent("css=a.oaActivityNameNode:contains('"
						+ PublicActivity + "')"));

		// Open Activity
		driver.getFirstElement(
				"css=a.oaActivityNameNode:contains('" + PublicActivity + "')")
				.click();

		// Click on the Hide button
		driver.getFirstElement("link=More").click();

		// Add a comment
		Comment = addCommentToActivityEntry(typist, false);

		// Verify the Comment
		assertTrue("PASS :: Comment Was Found on Page ", driver
				.isTextPresent(Comment));

		Reporter.log("End of Test :: createAPublicActivity");

	}

	@Test(dependsOnMethods = { "addCommentToEntry" })
	public void verifyComment() throws Exception {

		Reporter.log("Start of Test :: addCommentToEntry");

		// Load Activities
		LoadComponent(CommonObjects.ComponentHomepage);

		Login(testUser1);

		driver.getFirstElement(FVT_HomepageObjects.MyNotifications).click();

		String commentPost = testUser2.getDisplayName() + " commented on the "
				+ ActivityName + " entry thread added by you in the "
				+ PublicActivity + " activity.";
		Reporter.log("commentPost ->  " + commentPost);

		// Checking if comment changes are on the My Notifications Page
		assertTrue("PASS :: Activity was found on Public Activities page ",
				driver.isTextPresent(commentPost));

		filterBy("Activities");

		// Checking if comment changes are on the My Notifications Page
		assertTrue("PASS :: Activity filter applied, Comment found on page ",
				driver.isTextPresent(commentPost));

		Reporter.log("End of Test :: addCommentToEntry");
	}
}
