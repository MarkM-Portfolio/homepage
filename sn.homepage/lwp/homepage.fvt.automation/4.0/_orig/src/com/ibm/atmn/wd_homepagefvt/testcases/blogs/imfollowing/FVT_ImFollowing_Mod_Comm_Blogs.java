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


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Mod_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String ModeratedBlogCommunity = "";
	private static String ModeratedBlogEntry = "";
	
	
	@Test
	public void testCreateModComm() throws Exception {
		
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Logout
		Logout();
	
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateModComm");
		
		
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testFollowModCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicActivity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+ModeratedBlogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicActivity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowModCommunity" })
	public void testCreateBlog_ModComm() throws Exception {
		
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		clickLink(FVT_CommunitiesObjects.PublicCommunityView);
		
		clickLink("link="+ModeratedBlogCommunity);

		//Add the blogs widget
		AddWidgetToOverview("Blogs");
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateBlog_ModComm");
		
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateBlog_ModComm" })
	public void testFollowModCommunityBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowModCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" added a blog to the "+ModeratedBlogCommunity+" community."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" added a blog to the "+ModeratedBlogCommunity+" community."));
			
			//Logout
			Logout();
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowModCommunityBlog");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowModCommunityBlog" })
	public void testCreateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));		
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		// Created a new blog entry
		ModeratedBlogEntry = CreateANewBlogEntry(ModeratedBlogCommunity + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//
		//verifyImFollowingNewsStory(" created a blog entry named "+ModeratedBlogEntry+" in the "+ModeratedBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testUpdateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory(" updated the "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Blogs", true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testAddCommentToBlog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_COMMENT_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("commented on their own "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testCreateATrackbackToEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.ADD_TB_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//verifyImFollowingNewsStory("left a trackback on their own RE: "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testRecommendEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_ENTRY, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//
		//verifyImFollowingNewsStory("liked their own "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Blogs", true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_ModComm" })
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		LogoutAndClose();
		
		//Replace news story string
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, ModeratedBlogEntry, ModeratedBlogCommunity, CommonData.IC_LDAP_Username_Fullname450);
		
		verifyImFollowingNewsStory(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451,BlogNewsStory,FVT_NewsData.BLOGS, true);
		
		//
		//verifyImFollowingNewsStory("liked their own comment on "+ModeratedBlogEntry+".","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
}
