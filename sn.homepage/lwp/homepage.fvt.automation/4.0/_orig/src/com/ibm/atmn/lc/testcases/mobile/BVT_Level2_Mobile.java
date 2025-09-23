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

package com.ibm.atmn.lc.testcases.mobile;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.mobile.MobileData;
import com.ibm.atmn.lc.appobjects.mobile.MobileObjects;
import com.ibm.atmn.lc.tasks.mobile.MobileMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Mobile extends MobileMethods {

	/*
	 * This is a functional test for the Mobile Component of IBM Connections Created By: Conor Pelly Date: 09/29/2011
	 */

	//Log into News and then logout
	@Test
	public void testOpenMobileComponent() throws Exception {

		System.out.println("INFO: Start of Mobile BVT_Level_2 Test");

		//Load the component
		LoadComponent(CommonObjects.ComponentMobile);

		if (sConfig.browserIsIE) {
			Thread.sleep(1500);
			//Mobile is not supported on IE
			assertTrue("Fail: check bitmap for failure", sel.isTextPresent("Connections Mobile does not support this platform"));
		}
		else {
			//Login as a user (ie. Amy Jones66)
			Mobile_Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

			assertTrue("Fail: Mobile is not open", sel.isTextPresent("Connections"));

			//Check for any error messages
			CheckForErrorsOnPage();

			//From the homepage launcher open and verify Updates
			VerifyMobileApp(MobileObjects.MobileUpdates, MobileData.UpdatesPageTitle);

			//From the homepage launcher open and verify Profiles
			VerifyMobileApp(MobileObjects.MobileProfiles, MobileData.ProfilesPageTitle);

			//From the homepage launcher open and verify Communities
			VerifyMobileApp(MobileObjects.MobileCommunities, MobileData.CommunitiesPageTitle);

			//From the homepage launcher open and verify Activities
			VerifyMobileApp(MobileObjects.MobileActivities, MobileData.ActivitiesPageTitle);

			//From the homepage launcher open and verify Blogs
			VerifyMobileApp(MobileObjects.MobileBlogs, MobileData.BlogsPageTitle);

			//From the homepage launcher open and verify Bookmarks
			VerifyMobileApp(MobileObjects.MobileBookmarks, MobileData.BookmarksPageTitle);

			//From the homepage launcher open and verify Files
			VerifyMobileApp(MobileObjects.MobileFiles, MobileData.FilesPageTitle);

			//From the homepage launcher open and verify Forums
			VerifyMobileApp(MobileObjects.MobileForums, MobileData.ForumsPageTitle);

			//From the homepage launcher open and verify Wikis
			VerifyMobileApp(MobileObjects.MobileWikis, MobileData.WikisPageTitle);

			//Logout of Wiki
			Logout();
		}

		System.out.println("INFO: End of Mobile BVT_Level_2 Test");
	}

	@Test
	public void testMobileWikisComponent() throws Exception {

		System.out.println("INFO: Start of Mobile BVT_Level_2 Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentMobile);

		if (sConfig.browserIsIE) {
			Thread.sleep(1500);
			//Mobile is not supported on IE
			assertTrue("Fail: check bitmap for failure", sel.isTextPresent("Connections Mobile does not support this platform"));
		}
		else {
			//Login as a user (ie. Amy Jones66)
			Mobile_Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

			assertTrue("Fail: Mobile is not open", sel.isTextPresent("Connections"));

			//Check for any error messages
			CheckForErrorsOnPage();

			//Verify the objects in the wikis app
			VerifyWikisApp(MobileObjects.MobileWikis, MobileData.WikisPageTitle);

			//Logout of Wiki
			Logout();
		}

		System.out.println("INFO: End of Mobile BVT_Level_2 Test 2");
	}

}
