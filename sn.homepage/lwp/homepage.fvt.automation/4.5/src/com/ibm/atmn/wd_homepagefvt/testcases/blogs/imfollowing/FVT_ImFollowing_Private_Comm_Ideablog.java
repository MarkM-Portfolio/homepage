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

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;


public class FVT_ImFollowing_Private_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateIdeablogCommunity = "";
	private static String PrivateIdeablogEntry = "";
	
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
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
	
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Bogs FVT_Level_2 testFollowPrivateCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateIdeablogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPrivateCommunity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testCreateIdeablog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateIdeablogCommunity);
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		Logout();
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PrivateComm" })
	public void testFollowPrivateCommunityIdeaBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.Home);
			clickLink(FVT_HomepageObjects.Updates);			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created the "+PrivateIdeablogCommunity+" community Ideation Blog."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created the "+PrivateIdeablogCommunity+" community Ideation Blog."));
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunityBlog" })
	public void testCreateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		PrivateIdeablogEntry = CreateANewIdea(PrivateIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_IDEATION_BLOG_IDEA, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		//verifyImFollowingNewsStory(testUser2," created the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PrivateComm" })
	public void testUpdateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_IDEATION_BLOG_IDEA, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
		//verifyImFollowingNewsStory(testUser2," updated the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PrivateComm" }, groups = { "broken" })
	public void testAddCommentToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Check blog has opened correctly and retry if not.
		smartSleep(FVT_BlogsObjects.BlogsAddACommentLink);
		
		if(!driver.isElementPresent(FVT_BlogsObjects.BlogsAddACommentLink)){
			
			clickLink("link=" + PrivateIdeablogEntry);
			
		}
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.COMMENT_IDEATION_BLOG_IDEA, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);	
		//verifyImFollowingNewsStory(testUser2,"commented on their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PrivateComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_TRACKBACK_IDEATION, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);	
		//verifyImFollowingNewsStory(testUser2,"left a trackback on their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PrivateComm" }, groups = { "broken" })
	public void testIdeaVoted_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
		
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.VOTE_FOR_BLOG, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		//recurring issues with ideation blogs newsstories - commented out pending fix
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PrivateComm" }, groups = { "brokenIE"})
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, PrivateIdeablogEntry, null, testUser1.getDisplayName());
		
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
		//verifyImFollowingNewsStory(testUser2,"liked their own comment on "+PrivateIdeablogEntry+".","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
	//@Test (dependsOnMethods = { "testRecommendComment_PrivateComm" }, groups = { "broken" })
	public void testIdeaGraduated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//driver.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.GRADUATE_IDEA, PrivateIdeablogEntry, PrivateIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		//verifyImFollowingNewsStory(testUser2,"graduated their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
		
	}
	
	
}
