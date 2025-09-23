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

public class FVT_Discover_Mod_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String ModeratedIdeablogCommunity = "";
	private static String ModeratedIdeablogEntry = "";
	
	@Test
	public void testCreateIdeablog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		Logout();
		
		verifyNewsStory(" created an Ideation Blog named "+ModeratedIdeablogCommunity+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_ModComm" })
	public void testCreateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		// Created a new blog idea
		ModeratedIdeablogEntry = CreateANewIdea(ModeratedIdeablogCommunity + " Blog Idea");

		//Logout
		Logout();
		
		verifyNewsStory(" created the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_ModComm" })
	public void testUpdateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		verifyNewsStory(" updated the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_ModComm" }, groups = { "broken" })
	public void testAddCommentToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		verifyNewsStory("commented on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_ModComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		verifyNewsStory("left a trackback on their own RE: "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_ModComm" }, groups = { "broken" })
	public void testIdeaVoted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
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
		Logout();
		
		verifyNewsStory(" voted for the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_ModComm" }, groups = { "broken" })
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + ModeratedIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		verifyNewsStory("liked their own comment on "+ModeratedIdeablogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_ModComm" }, groups = { "broken" })
	public void testIdeaGraduated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
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
		Logout();
		
		verifyNewsStory("graduated their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	
}
