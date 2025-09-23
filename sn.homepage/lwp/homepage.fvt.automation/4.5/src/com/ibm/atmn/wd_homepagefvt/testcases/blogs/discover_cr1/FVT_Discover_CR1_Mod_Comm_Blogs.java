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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.discover_cr1;


import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Discover_CR1_Mod_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedBlogCommunity = "";
	private static String ModeratedBlogEntry = "";
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testLoginUsers");
	
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);

		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testLoginUsers");
		
	}
	
@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreateBlog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);

		
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_COMM_BLOG, ModeratedBlogCommunity, null, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_ModComm" })
	public void testCreateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		// Created a new blog entry
		ModeratedBlogEntry = CreateANewBlogEntry(ModeratedBlogCommunity + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testUpdateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testAddCommentToBlog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMENT_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_ModComm" })
	public void testCreateATrackbackToEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_TB_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToEntry_ModComm" })
	public void testRecommendEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
			
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_ModComm" }, groups = { "brokenIE"})
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//driver.getFirstElement(FVT_BlogsObjects.BlogsCommentRecommend).hover();
		
		//Recommend entry
		likeBlogComment_CR1();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, ModeratedBlogEntry, ModeratedBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
}
