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

import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
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

public class FVT_Discover_Mod_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedIdeablogCommunity = "";
	private static String ModeratedIdeablogEntry = "";
	
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
	public void testCreateIdeablog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_ModComm");
	
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
		ModeratedIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.CREATE_IDEATION_BLOG, ModeratedIdeablogCommunity, null, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);			
		//verifyNewsStory(testUser2, " added an Ideation Blog to the "+ModeratedIdeablogCommunity+" community.","Discover","Blogs", true);
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_ModComm" })
	public void testCreateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		// Created a new blog idea
		ModeratedIdeablogEntry = CreateANewIdea(ModeratedIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.CREATE_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);			
		//verifyNewsStory(testUser2, " created the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_ModComm" })
	public void testUpdateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.UPDATE_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		//verifyNewsStory(testUser2, " updated the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_ModComm" })
	public void testAddCommentToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.COMMENT_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		//verifyNewsStory(testUser2, "commented on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_ModComm" })
	public void testCreateATrackbackToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.CREATE_TRACKBACK_IDEATION, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		//verifyNewsStory(testUser2, "left a trackback on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_ModComm" })
	public void testIdeaVoted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.VOTE_FOR_BLOG, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser2.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		//verifyNewsStory(testUser2, " voted for the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_ModComm" }, groups = { "brokenIE"})
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		// Test will fail in IE - Cannot hit like button
		// Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
		
		//Logout
		LogoutAndClose();
			
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, ModeratedIdeablogEntry, null, testUser1.getDisplayName());
			
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
	//@Test (dependsOnMethods = { "testRecommendComment_ModComm" })
	public void testIdeaGraduated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//sel.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		String BlogsNewsStory = replaceNewsStory(NewsStoryData.GRADUATE_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyNewsStory(BlogsNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.BLOGS, true);		
		//verifyNewsStory(testUser2, "graduated their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	
}
