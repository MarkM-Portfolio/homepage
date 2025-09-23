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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.discover;


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

public class FVT_Discover_Public_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicBlogCommunity = "";
	private static String PublicBlogEntry = "";
	
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
	public void testCreateBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PublicComm");
	
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
		PublicBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_COMM_BLOG, PublicBlogCommunity, null, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2, BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PublicComm");
		
	}
	
	
	
	@Test (dependsOnMethods = { "testCreateBlog_PublicComm" })
	public void testCreateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		// Created a new blog entry
		PublicBlogEntry = CreateANewBlogEntry(PublicBlogCommunity + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory(" created a blog entry named "+PublicBlogEntry+" in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
		
	}

	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testUpdateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory(" updated the "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testAddCommentToBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMENT_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory("commented on their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testCreateATrackbackTOEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
		
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_TB_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory("left a trackback on their own RE: "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testRecommendEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_ENTRY, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory("liked their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PublicComm" })
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption8);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, PublicBlogEntry, PublicBlogCommunity, testUser1.getDisplayName());
		
		//Verify that news story appears in  Activity Steam -> Discover;
		verifyNewsStory(testUser2,  BlogNewsStory,FVT_NewsData.DISCOVER_VIEW,FVT_NewsData.BLOGS, true);
		
		//verifyNewsStory("liked their own comment on "+PublicBlogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
	
	
}
