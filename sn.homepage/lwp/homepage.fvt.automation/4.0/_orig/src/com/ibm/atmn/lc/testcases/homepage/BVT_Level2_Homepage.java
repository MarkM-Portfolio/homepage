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

package com.ibm.atmn.lc.testcases.homepage;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.homepage.HomepageData;
import com.ibm.atmn.lc.appobjects.homepage.HomepageObjects;
import com.ibm.atmn.lc.tasks.homepage.HomepageMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Homepage extends HomepageMethods {

	/*
	 * This is a functional test for the Blogs Component of IBM Connections Created By: Conor Pelly Date: 08/16/2011
	 */

	//Log into News and then logout
	@Test
	public void addActivitiesWidget() throws Exception {

		System.out.println("INFO: Start of Homepage Level 2 BVT Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(HomepageObjects.Widgets);

		//Click on the Link to bring to the component and verify
		if (sel.isElementPresent(HomepageObjects.ActivityWidget)) {
			clickLink(HomepageObjects.ActivityWidget);
			assertTrue(sel.isTextPresent("My Activities"));
			//Thread.sleep(1000);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			assertTrue(sel.isTextPresent("Home"));
			OpenHelp(HomepageObjects.ActivityWidget, HomepageObjects.ActivitiesClickForActions);
			VerifyHelp(HomepageObjects.UsingActivities, HomepageData.HomepageHelp, HomepageData.ActivitiesWidgetHelp);
		}
		else {
			//Add the Widgets for Activities
			WidgetsCustomize(HomepageObjects.ActivitiesWidget, HomepageObjects.AddActivityWidget);

			//Close the customize widgets
			clickLink(HomepageObjects.WidgetsCustomize);

			clickLink(HomepageObjects.ActivityWidget);
			Thread.sleep(3000);
			assertTrue(sel.isTextPresent("Activities"));
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			assertTrue(sel.isTextPresent("Home"));
			OpenHelp(HomepageObjects.ActivityWidget, HomepageObjects.ActivitiesClickForActions);
			VerifyHelp(HomepageObjects.UsingActivities, HomepageData.HomepageHelp, HomepageData.ActivitiesWidgetHelp);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Homepage Level 2 BVT Test 1");
	}

	//Log into News and then logout
	@Test
	public void addBlogsWidget() throws Exception {

		System.out.println("INFO: Start of Homepage Level 2 BVT Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(HomepageObjects.Widgets);

		//Click on the Link to bring to the component and verify
		if (sel.isElementPresent(HomepageObjects.BlogsWidget)) {
			clickLink(HomepageObjects.BlogsWidget);
			//Thread.sleep(1000);
			VerifyComponentName(HomepageData.ComponentBlogs);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.BlogsWidget, HomepageObjects.BlogsClickForActions);
			VerifyHelp(HomepageObjects.UsingBlogs, HomepageData.HomepageHelp, HomepageData.BlogsWidgetHelp);
		}
		else {
			//Add the Widgets for Activities
			WidgetsCustomize(HomepageObjects.BlogsWidget, HomepageObjects.AddBlogsWidget);

			//Close the customize widgets
			clickLink(HomepageObjects.WidgetsCustomize);

			clickLink(HomepageObjects.BlogsWidget);
			Thread.sleep(3000);
			VerifyComponentName(HomepageData.ComponentBlogs);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.BlogsWidget, HomepageObjects.BlogsClickForActions);
			VerifyHelp(HomepageObjects.UsingBlogs, HomepageData.HomepageHelp, HomepageData.BlogsWidgetHelp);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Homepage Level 2 BVT Test 2");
	}

	//Log into News and then logout
	@Test
	public void addBookmarksWidget() throws Exception {

		System.out.println("INFO: Start of Homepage Level 2 BVT Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(HomepageObjects.Widgets);

		//Click on the Link to bring to the component and verify
		if (sel.isElementPresent(HomepageObjects.BookmarksWidget)) {
			clickLink(HomepageObjects.BookmarksWidget);
			//Thread.sleep(1000);
			VerifyComponentName(HomepageData.ComponentBookmarks);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.BookmarksWidget, HomepageObjects.BookmarksClickForActions);
			VerifyHelp(HomepageObjects.UsingBookmarks, HomepageData.HomepageHelp, HomepageData.BookmarksWidgetHelp);
		}
		else {
			//Add the Widgets for Activities
			WidgetsCustomize(HomepageObjects.BookmarksWidget, HomepageObjects.AddBookmarkWidget);

			//Close the customize widgets
			clickLink(HomepageObjects.WidgetsCustomize);

			clickLink(HomepageObjects.BookmarksWidget);
			Thread.sleep(3000);
			VerifyComponentName(HomepageData.ComponentBookmarks);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.BookmarksWidget, HomepageObjects.BookmarksClickForActions);
			VerifyHelp(HomepageObjects.UsingBookmarks, HomepageData.HomepageHelp, HomepageData.BookmarksWidgetHelp);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Homepage Level 2 BVT Test 3");
	}

	//Log into News and then logout
	@Test
	public void addCommunitiesWidget() throws Exception {

		System.out.println("INFO: Start of Homepage Level 2 BVT Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(HomepageObjects.Widgets);

		//Click on the Link to bring to the component and verify
		if (sel.isElementPresent(HomepageObjects.CommunitiesWidget)) {
			clickLink(HomepageObjects.CommunitiesWidget);
			//Thread.sleep(1000);
			VerifyComponentName(HomepageData.ComponentCommunities);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.CommunitiesWidget, HomepageObjects.CommunitiesClickForActions);
			VerifyHelp(HomepageObjects.UsingCommunities, HomepageData.HomepageHelp, HomepageData.CommunitiesWidgetHelp);
		}
		else {
			//Add the Widgets for Activities
			WidgetsCustomize(HomepageObjects.CommunitiesWidget, HomepageObjects.AddCommunityWidget);

			//Close the customize widgets
			clickLink(HomepageObjects.WidgetsCustomize);

			clickLink(HomepageObjects.CommunitiesWidget);
			Thread.sleep(3000);
			VerifyComponentName(HomepageData.ComponentCommunities);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.CommunitiesWidget, HomepageObjects.CommunitiesClickForActions);
			VerifyHelp(HomepageObjects.UsingCommunities, HomepageData.HomepageHelp, HomepageData.CommunitiesWidgetHelp);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Homepage Level 2 BVT Test 4");
	}

	//Log into News and then logout
	@Test
	public void addProfilesWidget() throws Exception {

		System.out.println("INFO: Start of Homepage Level 2 BVT Test 5");

		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		clickLink(HomepageObjects.Widgets);

		//Click on the Link to bring to the component and verify
		if (sel.isElementPresent(HomepageObjects.ProfilesWidget)) {
			clickLink(HomepageObjects.ProfilesWidget);
			//Thread.sleep(3000);
			VerifyComponentName(HomepageData.ComponentProfiles);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.ProfilesWidget, HomepageObjects.ProfilesClickForActions);
			VerifyHelp(HomepageObjects.UsingProfiles, HomepageData.HomepageHelp, HomepageData.ProfilesWidgetHelp);
		}
		else {
			//Add the Widgets for Activities
			WidgetsCustomize(HomepageObjects.ProfilesWidget, HomepageObjects.AddProfilesWidget);

			//Close the customize widgets
			clickLink(HomepageObjects.WidgetsCustomize);

			clickLink(HomepageObjects.ProfilesWidget);
			Thread.sleep(3000);
			VerifyComponentName(HomepageData.ComponentProfiles);
			MoveBetweenTabs(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);
			VerifyComponentName(HomepageData.ComponentHomepage);
			OpenHelp(HomepageObjects.ProfilesWidget, HomepageObjects.ProfilesClickForActions);
			VerifyHelp(HomepageObjects.UsingProfiles, HomepageData.HomepageHelp, HomepageData.ProfilesWidgetHelp);
		}

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of Homepage Level 2 BVT Test 5");
	}
}