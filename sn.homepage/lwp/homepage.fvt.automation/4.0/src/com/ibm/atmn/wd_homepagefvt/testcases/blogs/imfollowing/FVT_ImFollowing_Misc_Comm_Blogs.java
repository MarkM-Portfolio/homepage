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


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

public class FVT_ImFollowing_Misc_Comm_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	//private static String PrivateBlogCommunity = "";
	//private static String PrivateBlogEntry = "";
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PublicBlogCommunity = "";
	private static String PublicBlogEntry = "";
	private static String PublicBlogEntry2 = "";
	
	
	/*
	
	@Test
	public void testCreatePrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a private community
		PrivateBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.IC_LDAP_UserName451_Typeahead, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreatePrivateComm" })
	public void testFollowPrivateCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPrivateCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.ImAMember);
			
			clickLink("link="+PrivateBlogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPrivateCommunity");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunity" })
	public void testCreateBlog_PrivateComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		clickLink("link="+PrivateBlogCommunity);
	
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PrivateComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlog_PrivateComm" })
	public void testFollowPrivateCommunityBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPrivateCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.Home);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			FilterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created a blog named "+PrivateBlogCommunity+"."));
			
			FilterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created a blog named "+PrivateBlogCommunity+"."));
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPrivateCommunityBlog");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunityBlog" })
	public void testRemoveMember_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testRemoveMember_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			//Go to private community
			clickLink("link=" + PrivateBlogCommunity);
			
			clickLink(FVT_CommunitiesObjects.LeftNavOption2);
			
			
			//Need to remove member but this is 
	
			//Logout
			Logout();
		
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testRemoveMember_PrivateComm");
			
	}
	
	
	
	@Test (dependsOnMethods = { "testFollowPrivateCommunityBlog" })
	public void testCreateBlogEntry_PrivateComm() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			//Go to private community
			clickLink("link=" + PrivateBlogCommunity);
			
			//Go to blogs
			clickLink("link=Blog");
			
			// Created a new blog entry
			PrivateBlogEntry = CreateANewBlogEntry(PrivateBlogCommunity + " Blog Entry");
	
			//Logout
			Logout();
			
			VerifyImFollowingNewsStory(" created a blog entry named "+PrivateBlogEntry+" in the "+PrivateBlogCommunity+" blog.","Blogs", false);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PrivateComm");
			
	}
	
	*/

	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testLoginUsers");
	
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);

		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreatePublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreatePublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with moderated access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a moderated community
		PublicBlogCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3.getEmail());

		//Logout
		Logout();
	
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreatePublicComm");
		
	}
	
	
	
	@Test (dependsOnMethods = { "testCreatePublicComm" })
	public void testFollowPublicCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicCommunity");
	
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			clickLink("link="+PublicBlogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicCommunity");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPublicCommunity" })
	public void testCreateBlog_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlog_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		clickLink("link="+PublicBlogCommunity);
		
		//Add the blogs widget
		AddWidgetToOverview("Blogs");

		//Logout
		Logout();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlog_PublicComm");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateBlog_PublicComm" })
	public void testFollowPublicCommunityBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicCommunityBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.Home);
						
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created a blog named "+PublicBlogCommunity+"."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created a blog named "+PublicBlogCommunity+"."));
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicCommunityBlog");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPublicCommunityBlog" })
	public void testCreateBlogEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		// Created a new blog entry
		PublicBlogEntry = CreateANewBlogEntry(PublicBlogCommunity + " Blog Entry");

		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2," created a blog entry named "+PublicBlogEntry+" in the "+PublicBlogCommunity+" blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateBlogEntry_PublicComm" })
	public void testChangeAccess_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Edit Community
		clickLink(FVT_CommunitiesObjects.Community_Actions_Button);

		clickLink(FVT_CommunitiesObjects.Menu_Item_2);
		
		clickLink(FVT_CommunitiesObjects.CommunityAccessOption3);
		
		clickLink(CommonObjects.SaveButton);
		
		clickLink("link=Blogs");
		
		// Created a new blog entry
		PublicBlogEntry2 = CreateANewBlogEntry(PublicBlogCommunity + " Blog Entry");
		
		//Logout
		LogoutAndClose();
		
		verifyImFollowingNewsStory(testUser2," created a blog entry named "+PublicBlogEntry2+" in the "+PublicBlogCommunity+" blog.","Blogs", false);

		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateBlogEntry_PublicComm");
		
	}
	
	
	
	
}
