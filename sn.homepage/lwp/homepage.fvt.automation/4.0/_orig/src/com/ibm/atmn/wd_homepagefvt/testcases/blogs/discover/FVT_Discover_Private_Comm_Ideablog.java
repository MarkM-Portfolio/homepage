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

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;


public class FVT_Discover_Private_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PrivateIdeablogCommunity = "";
	private static String PrivateIdeablogEntry = "";
	
	@Test
	public void testCreateIdeablog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		Logout();
		
		verifyNewsStory(" created an Ideation Blog named "+PrivateIdeablogCommunity+".","Discover","Blogs", false);
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PrivateComm" })
	public void testCreateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		// Created a new blog idea
		PrivateIdeablogEntry = CreateANewIdea(PrivateIdeablogCommunity + " Blog Idea");

		//Logout
		Logout();
		
		verifyNewsStory(" created the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PrivateComm" })
	public void testUpdateIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		Logout();
		
		verifyNewsStory(" updated the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PrivateComm" }, groups = { "broken" })
	public void testAddCommentToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		Logout();
		
		verifyNewsStory("commented on their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PrivateComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
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
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		Logout();
		
		verifyNewsStory(" voted for the "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PrivateComm" }, groups = { "broken" })
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		Logout();
		
		verifyNewsStory("liked their own comment on "+PrivateIdeablogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_PrivateComm" }, groups = { "broken" })
	public void testIdeaGraduated_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PrivateIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PrivateIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//driver.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		Logout();
		
		verifyNewsStory("graduated their own "+PrivateIdeablogEntry+" idea in the "+PrivateIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PrivateComm");
		
	}
	
	
}
