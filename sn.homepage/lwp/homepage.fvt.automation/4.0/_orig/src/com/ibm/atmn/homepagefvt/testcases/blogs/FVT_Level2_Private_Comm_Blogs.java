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


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;
import org.testng.annotations.Test;

public class FVT_Level2_Private_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PrivateBlogCommunity = "";
	private static String PrivateBlogEntry = "";
	
	@Test
	public void testCreateBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
		
		VerifyNewsStory(" created a new blog named "+PrivateBlogCommunity+".","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_PrivateComm" })
	public void testCreateBlogEntry_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			//Go to private community
			clickLink("link=" + PrivateBlogCommunity);
			
			//Go to blogs
			clickLink("link=Blog");
			
			// Created a new blog entry
			PrivateBlogEntry = CreateANewBlogEntry(PrivateBlogCommunity + " Blog Entry");
	
			//Logout
			Logout();
			
			VerifyNewsStory(" created a new blog entry named "+PrivateBlogEntry+" in the "+PrivateBlogCommunity+" blog.","Discover","Blogs", false);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
			
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testUpdateBlogEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PrivateBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		CreateAUpdatedBlogEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" updated the "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateBlogEntry_PrivateComm");
		
	}
	

	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testAddCommentToBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PrivateBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" commented on their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testCreateATrackbackTOEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PrivateBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToEntry();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" left a trackback on their own RE: "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackTOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PrivateComm" })
	public void testRecommendEntry_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PrivateBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		Logout();
		
		VerifyNewsStory(" recommended their own "+PrivateBlogEntry+" blog entry in the "+PrivateBlogCommunity+" blog.","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendOEntry_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToBlog_PrivateComm" })
	public void testRecommendComment_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to Private community
		clickLink("link=" + PrivateBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PrivateBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		VerifyNewsStory(" recommended their own comment on "+PrivateBlogEntry+".","Discover","Blogs", false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PrivateComm");
		
	}
	
}
