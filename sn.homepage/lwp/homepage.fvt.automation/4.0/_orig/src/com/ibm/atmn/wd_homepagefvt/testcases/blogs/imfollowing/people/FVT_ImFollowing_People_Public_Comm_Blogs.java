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


import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_People_Public_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PublicBlogCommunity = "";
	private static String PublicBlogEntry = "";
	
	@Test
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		PublicBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Logout
		Logout();
	
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePublicComm");
		
	}
	
	
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testFollowUser() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowUser");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(CommonData.IC_LDAP_Username_Fullname450);
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testFollowUser");
		
	}
	
	
	@Test (dependsOnMethods = { "testFollowUser" })		
	public void testCreateBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PublicBlogCommunity);
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(" created a blog named "+PublicBlogCommunity+".","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateBlog_PublicComm" })
	public void testCreateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		// Created a new blog entry
		PublicBlogEntry = CreateANewBlogEntry(PublicBlogCommunity + " Blog Entry");

		//Logout
		Logout();
		
		verifyNewsStory_ThreeFilters(" created a blog entry named "+PublicBlogEntry+" in the "+PublicBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
		
	}

	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testUpdateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters(" updated the "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testAddCommentToBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters("commented on their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testCreateATrackbackTOEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters("left a trackback on their own RE: "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testRecommendEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters("liked their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PublicComm" })
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(PublicBlogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavBlogs);
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		driver.close();
		
		verifyNewsStory_ThreeFilters("liked their own comment on "+PublicBlogEntry+".","I'm Following","Communities","People","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
}
