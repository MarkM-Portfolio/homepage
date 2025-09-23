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

public class FVT_Level2_Public_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PublicIdeablogCommunity = "";
	private static String PublicIdeablogEntry = "";
	
	@Test
	public void testCreateIdeablog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PublicIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
		
		
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");

		//Logout
		Logout();
		
		VerifyNewsStory(" created a new Ideation Blog named "+PublicIdeablogCommunity+".","Discover","Blogs", true);		
		
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_PublicComm" })
	public void testCreateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		// Created a new blog idea
		PublicIdeablogEntry = CreateANewIdea(PublicIdeablogCommunity + " Blog Idea");

		//Logout
		Logout();
		
		VerifyNewsStory(" created a new "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_PublicComm" })
	public void testUpdateIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		Logout();
		
		VerifyNewsStory(" updated the "+PublicIdeablogEntry+" idea in the "+PublicIdeablogCommunity+" Ideation Blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_PublicComm" }, groups = { "broken" })
	public void testAddCommentToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm a Member");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		Logout();
		
		//VerifyNewsStory(DateTimeStamp, "Activities", "Moderated");
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_PublicComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm a Member");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		Logout();
		
		//VerifyNewsStory(DateTimeStamp, "Activities", "Moderated");
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_PublicComm" }, groups = { "broken" })
	public void testIdeaVoted_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm a Member");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		Logout();
		
		//VerifyNewsStory(DateTimeStamp, "Activities", "Moderated");
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_PublicComm" }, groups = { "broken" })
	public void testRecommendComment_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendComment_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm a Member");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
	
		//Logout
		Logout();
		
		//VerifyNewsStory(DateTimeStamp, "Activities", "Moderated");
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendComment_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testRecommendComment_PublicComm" }, groups = { "broken" })
	public void testIdeaGraduated_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to private community
		clickLink("link=" + PublicIdeablogCommunity);
		
		//Go to blogs
		clickLink("link=Ideation Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		sel.focus(FVT_BlogsObjects.GraduateOk);
		cautiousClick(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		Logout();
		
		//VerifyNewsStory(DateTimeStamp, "Activities", "Moderated");
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaGraduated_PublicComm");
		
	}
	
	
}
