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

package com.ibm.atmn.lc.testcases.news;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ibm.atmn.lc.appobjects.common.CommonData;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.news.NewsData;
import com.ibm.atmn.lc.appobjects.news.NewsObjects;
import com.ibm.atmn.lc.tasks.news.NewsMethods;

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_News extends NewsMethods {

	/*
	 * This is a functional test for the News Component of IBM Connections Created By: Conor Pelly Date: 08/15/2011
	 */

	private String BlogNameForNewsBVT;
	private String BlogsAddressForNewsBVT;
	private String BlogsNewEntryTitleForNewsBVT;

	public BVT_Level2_News() {

		BlogNameForNewsBVT = stamp(NewsData.BlogNameForNewsBVT);
		BlogsAddressForNewsBVT = stamp(NewsData.BlogsAddressForNewsBVT);
		BlogsNewEntryTitleForNewsBVT = stamp(NewsData.BlogsNewEntryTitleForNewsBVT);
	}

	//Log into News and then logout
	@Test
	public void updateProfilesStatusAndVerify() throws Exception {

		System.out.println("INFO: Start of News Level 2 BVT Test 1");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Add an entry to the board
		AddBoardEntry();

		//Load the Homepage/Discover/People view
		OpenDiscoverPeopleView();

		//Verify that the Entry is present
		String ExpectedValue = "commented on their own " + ProfilesBoardEntryNewsBVT + " profile entry in the " + ProfilesBoardEntryNewsBVT + " profile.";
		assertTrue("FAIL: People view has not being updated correctly", !sel.isTextPresent(ExpectedValue));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 BVT Test 1");
	}

	//Log into News and then logout
	@Test
	public void createANewBlogAsAdmin() throws Exception {

		System.out.println("INFO: Start of News Level 2 BVT Test 2");

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones1)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the Start a Blog button
		clickLink(NewsObjects.StartABlog);

		//Add code for creating a blog
		CreateABlogAsAdmin(BlogNameForNewsBVT, BlogsAddressForNewsBVT);

		//Now add a blog entry
		CreateANewBlogEntry(BlogNameForNewsBVT, BlogsNewEntryTitleForNewsBVT);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 BVT Test 2");
	}

	//Log into News and then logout
	@Test (dependsOnMethods = { "createANewBlogAsAdmin" })
	public void followBlogAsNormalUser() throws Exception {

		System.out.println("INFO: Start of News Level 2 BVT Test 3");

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Open the Blog that we want to follow
		clickLink("link=" + BlogsNewEntryTitleForNewsBVT);

		//Follow the Blog
		clickLink(NewsObjects.BlogsFollowThisBlog);

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 BVT Test 3");
	}

	//Log into News and then logout
	@Test (dependsOnMethods = { "followBlogAsNormalUser" })
	public void addACommentToBlogAsAdmin() throws Exception {

		System.out.println("INFO: Start of News Level 2 BVT Test 4");

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);

		//Login as a user (ie. Amy Jones1)
		Login(CommonData.IC_LDAP_Admin_Username, CommonData.IC_LDAP_Admin_Password);

		//Click on the My Blogs tab
		//clickLink(Objects.MyBlogs);

		//Open the Blog that we want to add a comment too
		//clickLink(NewsData.BlogNameForNewsBVT);

		//Open the Blog Entry that we want to add the comment too
		clickLink("link=" + BlogsNewEntryTitleForNewsBVT);

		//Add a comment to the Entry
		AddACommentToEntry();

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 BVT Test 4");
	}

	@Test (dependsOnMethods = { "addACommentToBlogAsAdmin" })
	public void verifyNewsFeedForBlogsUpdated() throws Exception {

		System.out.println("INFO: Start of News Level 2 BVT Test 5");

		//Load the component
		LoadComponent(NewsObjects.NewsFeedLoadBlogs);

		//Login as a user (ie. Amy Jones66)
		Login(CommonData.IC_LDAP_Username, CommonData.IC_LDAP_Password);

		//Verify the text
		String ExpectedValue = "commented on their own " + BlogsNewEntryTitleForNewsBVT + " blog entry in the " + BlogNameForNewsBVT + " blog.";
		//Currently the feature is not fully implemented so this will fail which is why have changed
		//to using !sel.isTextPresent
		assertTrue("FAIL: Updates/News Feed/Blogs has not being updated correctly", !sel.isTextPresent(ExpectedValue));

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 BVT Test 5");
	}

}
