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
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Discover_Public_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicIdeablogCommunity = "";
	private static String PublicIdeablogEntry = "";
	
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
	public void testCreateIdeablog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
	
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
		PublicIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " added an Ideation Blog to the "+PublicIdeablogCommunity+" community.","Discover","Blogs", true);
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PublicComm" })
	public void testCreateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to moderated community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		// Created a new blog idea
		PublicIdeablogEntry = CreateANewIdea(PublicIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " created the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PublicComm" })
	public void testUpdateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
		
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " updated the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PublicComm" })
	public void testAddCommentToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, "commented on their own "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PublicComm" })
	public void testCreateATrackbackToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, "left a trackback on their own "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PublicComm" })
	public void testIdeaVoted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAMember);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, " voted for the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PublicComm" })
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.Like);
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, "liked their own comment on "+PublicIdeablogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
	//@Test (dependsOnMethods = { "testRecommendComment_PublicComm" })
	public void testIdeaGraduated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink(FVT_CommunitiesObjects.LeftNavOption10);
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//sel.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory(testUser2, "graduated their own "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
		
	}
	
	
}
