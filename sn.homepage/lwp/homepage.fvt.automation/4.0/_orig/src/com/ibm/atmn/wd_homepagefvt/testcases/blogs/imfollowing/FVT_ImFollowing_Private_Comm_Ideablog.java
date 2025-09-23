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
	
	private static String PrivateIdeablogCommunity = "";
	private static String PrivateIdeablogEntry = "";
	
	@Test
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Bogs FVT_Level_2 testFollowPrivateCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
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
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
	public void testFollowPrivateCommunityBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.Home);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created an Ideation Blog named "+PrivateIdeablogCommunity+"."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created an Ideation Blog named "+PrivateIdeablogCommunity+"."));
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPrivateCommunityBlog");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunityBlog" })
	public void testCreateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		PrivateIdeablogEntry = CreateANewIdea(PrivateIdeablogCommunity + " Blog Idea");

		//Logout
		Logout();
		
		verifyImFollowingNewsStory(" created the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PrivateComm" })
	public void testUpdateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		Logout();
		
		verifyImFollowingNewsStory(" updated the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PrivateComm" }, groups = { "broken" })
	public void testAddCommentToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		Logout();
		
		verifyImFollowingNewsStory("commented on their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PrivateComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		Logout();
		
		verifyImFollowingNewsStory("left a trackback on their own RE: "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PrivateComm" }, groups = { "broken" })
	public void testIdeaVoted_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		Logout();
		
		verifyImFollowingNewsStory(" voted for the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PrivateComm" }, groups = { "broken" })
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		Logout();
		
		verifyImFollowingNewsStory("liked their own comment on "+PrivateIdeablogEntry+".","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_PrivateComm" }, groups = { "broken" })
	public void testIdeaGraduated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		verifyImFollowingNewsStory("graduated their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
		
	}
	
	
}
