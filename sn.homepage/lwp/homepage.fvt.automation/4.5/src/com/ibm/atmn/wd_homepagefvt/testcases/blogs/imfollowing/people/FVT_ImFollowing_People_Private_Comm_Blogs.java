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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing.people;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

public class FVT_ImFollowing_People_Private_Comm_Blogs extends FVT_BlogsMethods{
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
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser1);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser3);
		
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
	
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login(testUser1);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		FollowPerson(testUser1.getEmail());
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })
	public void testCreateBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PrivateBlogCommunity);
	
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		LogoutAndClose();
		
		verifyNewsStory_ThreeFilters(testUser2," created a blog named "+PrivateBlogCommunity+".","I'm Following","Communities","People","Blogs", false);
	
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_PrivateComm" })
	public void testCreateBlogEntry_PrivateComm() throws Exception {
			
			
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
				
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
			
		// Created a new blog entry
		PrivateBlogEntry = CreateANewBlogEntry(PrivateBlogCommunity + " Blog Entry");
	
		//Logout
		LogoutAndClose();
		
		verifyNewsStory_ThreeFilters(testUser2," created a blog entry named "+PrivateBlogEntry+" in the "+PrivateBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", false);
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testUpdateBlogEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		
		verifyNewsStory_ThreeFilters(testUser2," updated the "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testAddCommentToBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		
		verifyNewsStory_ThreeFilters(testUser2,"commented on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testCreateATrackbackTOEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PrivateBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		//Logout
		LogoutAndClose();
		
		verifyNewsStory_ThreeFilters(testUser2,"left a trackback on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testRecommendEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		
		verifyNewsStory_ThreeFilters(testUser2,"liked their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PrivateComm" }, groups = { "brokenIE"})
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
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
		
		verifyNewsStory_ThreeFilters(testUser2,"liked their own comment on "+PrivateBlogEntry+".","I'm Following","Communities","People","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
}
