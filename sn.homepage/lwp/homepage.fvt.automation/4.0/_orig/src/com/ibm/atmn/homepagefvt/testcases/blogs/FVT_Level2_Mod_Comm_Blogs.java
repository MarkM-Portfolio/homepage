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

package com.ibm.atmn.homepagefvt.testcases.blogs;


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_Level2_Mod_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String ModeratedBlogCommunity = "";
	private static String ModeratedBlogEntry = "";
	
	@Test
	public void testCreateBlog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		ModeratedBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
		
		//
		VerifyNewsStory(" created a new blog named "+ModeratedBlogCommunity+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_ModComm" })
	public void testCreateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		// Created a new blog entry
		ModeratedBlogEntry = CreateANewBlogEntry(ModeratedBlogCommunity + " Blog Entry");

		//Logout
		Logout();
		
		//
		VerifyNewsStory(" created a new blog entry named "+ModeratedBlogEntry+" in the "+ModeratedBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testUpdateBlogEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" updated the "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Discover","Blogs", true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testAddCommentToBlog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" commented on their own "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testCreateATrackbackToEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" left a trackback on their own RE: "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_ModComm" })
	public void testRecommendEntry_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		Logout();
		
		//
		VerifyNewsStory(" recommended their own "+ModeratedBlogEntry+" blog entry in the "+ModeratedBlogCommunity+" blog.","Discover","Blogs", true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_ModComm" })
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + ModeratedBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + ModeratedBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		//
		VerifyNewsStory(" recommended their own comment on "+ModeratedBlogEntry+".","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
}
