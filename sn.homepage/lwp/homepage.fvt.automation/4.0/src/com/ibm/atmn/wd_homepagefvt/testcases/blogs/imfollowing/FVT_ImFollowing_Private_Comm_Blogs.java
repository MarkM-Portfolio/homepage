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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

public class FVT_ImFollowing_Private_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateBlogCommunity = "";
	private static String PrivateBlogEntry = "";
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testLoginUsers");
	
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPrivateCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateBlogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPrivateCommunity");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testCreateBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateBlogCommunity);
	
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_PrivateComm" })
	public void testFollowPrivateCommunityBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			Login(testUser2);
									
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" added a blog to the "+PrivateBlogCommunity+" community."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" added a blog to the "+PrivateBlogCommunity+" community."));
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunityBlog" })
	public void testCreateBlogEntry_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser1);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
				
			//Open Community
			openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
			
			// Created a new blog entry
			PrivateBlogEntry = CreateANewBlogEntry(PrivateBlogCommunity + " Blog Entry");
	
			//Logout
			LogoutAndClose();
			
			//Replace news story string
			String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG_ENTRY, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
			
			verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
			
			//verifyImFollowingNewsStory(" created a blog entry named "+PrivateBlogEntry+" in the "+PrivateBlogCommunity+" blog.","Blogs", true);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testUpdateBlogEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory(" updated the "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testAddCommentToBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMENT_BLOG_ENTRY, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("commented on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testCreateATrackbackTOEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_TB_BLOG_ENTRY, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("left a trackback on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testRecommendEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_ENTRY, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("liked their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PrivateComm" })
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, PrivateBlogEntry, PrivateBlogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("liked their own comment on "+PrivateBlogEntry+".","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
}
