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



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class FVT_ImFollowing_Mod_Comm_Ideablog extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedIdeablogCommunity = "";
	private static String ModeratedIdeablogEntry = "";
	
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
	public void testCreateModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateModComm");
	
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
		ModeratedIdeablogCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());
	
		//Logout
		LogoutAndClose();
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateModComm" })
	public void testFollowModCommunity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPublicActivity");
	
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_CommunitiesObjects.PublicCommunityView);
			
			sleep(1000);
			
			clickLink("link="+ModeratedIdeablogCommunity);
			
			clickLink(FVT_CommunitiesObjects.FollowCommunity);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPublicActivity");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowModCommunity" })
	public void testCreateIdeablog_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of blogs FVT_Level_2 testCreateIdeablog_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);	
		
		clickLink("link="+ModeratedIdeablogCommunity);
				
		//Add the blogs widget
		AddWidgetToOverview("Ideation Blog");
		
		//Logout
		Logout();
				
		System.out.println("INFO: End of blogs FVT_Level_2 testCreateIdeablog_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdeablog_ModComm" })
	public void testFollowModCommunityIdeablog() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowModCommunityIdeablog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentCommunities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink(FVT_HomepageObjects.Home);
			clickLink(FVT_HomepageObjects.Updates);			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			filterBy("Communities");
			
			Thread.sleep(2000);
			
			assertTrue("Text not present!",driver.isTextPresent(" created the "+ModeratedIdeablogCommunity+" community Ideation Blog."));
			
			filterBy("Blogs");
			
			Thread.sleep(2000);
			
			assertTrue("Text is present!",driver.isTextPresent(" created the "+ModeratedIdeablogCommunity+" community Ideation Blog."));
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowModCommunityIdeablog");
			
		}
	
	
	
	@Test (dependsOnMethods = { "testFollowModCommunityIdeablog" })
	public void testCreateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testCreateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		// Created a new blog idea
		ModeratedIdeablogEntry = CreateANewIdea(ModeratedIdeablogCommunity + " Blog Idea");

		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		//verifyImFollowingNewsStory(testUser2," created the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateIdea_ModComm" })
	public void testUpdateIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testUpdateIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Update blog entry
		CreateAUpdatedIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.UPDATE_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
		//verifyImFollowingNewsStory(testUser2," updated the "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testUpdateIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testUpdateIdea_ModComm" }, groups = { "broken" })
	public void testAddCommentToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Check blog has opened correctly and retry if not.
		smartSleep(FVT_BlogsObjects.BlogsAddACommentLink);
		
		if(!driver.isElementPresent(FVT_BlogsObjects.BlogsAddACommentLink)){
			
			clickLink("link=" + ModeratedIdeablogEntry);
			
		}
		
		//Update blog entry
		AddACommentToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.COMMENT_IDEATION_BLOG_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);
		//verifyImFollowingNewsStory(testUser2,"commented on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testAddCommentToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testAddCommentToIdea_ModComm" }, groups = { "broken" })
	public void testCreateATrackbackToIdea_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blog FVT_Level_2 testCreateATrackbackToIdea_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Create a trackback to an entry
		CreateATrackbackToIdea();
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.CREATE_TRACKBACK_IDEATION, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);		
		//verifyImFollowingNewsStory(testUser2,"left a trackback on their own "+ModeratedIdeablogEntry+" idea in the "+ModeratedIdeablogCommunity+" Ideation Blog.","Blogs", true);

		System.out.println("INFO: End of Blogs FVT_Level_2 testCreateATrackbackToIdea_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testCreateATrackbackToIdea_ModComm" }, groups = { "broken" })
	public void testIdeaVoted_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Vote for Idea
		clickLink(FVT_BlogsObjects.Vote);
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.VOTE_FOR_BLOG, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		//recurring issues with newsstory on ideation blogs
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	@Test (dependsOnMethods = { "testIdeaVoted_ModComm" }, groups = { "brokenIE" })
	public void testRecommendComment_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRecommendComment_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450); 
		Login(testUser1); 
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAMember,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsCommentRecommend);
		
		//driver.getFirstElement("css=span[dojoattachpoint='inlineLikeActions']").click();
		
		//driver.getFirstElement("css=a[id^='TOGGLE_comment_rating_'][id$='_widget']").click();
		//driver.getFirstElement("css=a:contains('Like'):nth(1)").click();
		//List<Element> e = driver.getElements("css=a[role='button'][title='Like this']:nth(0)");
		///WebDriver wd = (WebDriver) driver.getBackingObject();
		//List<WebElement> list = wd.findElements(By.cssSelector("=[class='lotusLike']"));
		//Reporter.log("Number of elements in the 'e' list  "+e.size());
		
		//for (WebElement a:list){

			//Reporter.log ("Element " + a.getText());
		//for(Element x:e){
			//Reporter.log("e to string = "+e.toString());
			//Reporter.log("id = "+x.getAttribute("id"));
			//Reporter.log("id = "+x.getAttribute("innerText"));
//			if (x.getText().trim().compareTo("Like") == 0){
//				x.click();
//				Reporter.log("Made click");
//				break;
//			}else{
//				Reporter.log(x.getText());
//			}
		//	a.click();
		//	break;
			//if (x.getText().equals("Like")){
			//	x.click();
			//}
		//}
		
		sleep(60000);
		//Logout
		LogoutAndClose();
		
		Reporter.log("User1 = "+testUser1.getEmail()+ " Password = "+testUser1.getPassword());
		Reporter.log("User2 = "+testUser2.getEmail()+ " Password = "+testUser2.getPassword());
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.LIKE_BLOG_COMMENT, ModeratedIdeablogEntry, null, testUser1.getDisplayName());
		
		//verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.COMMUNITIES, true);
		verifyNewsStory(BlogNewsStory, "I'm Following" , "Communities", testUser2, true);
		sleep(500);
		verifyNewsStory_TwoFilters(testUser2, BlogNewsStory,FVT_NewsData.IM_FOLLOWING_VIEW, FVT_NewsData.ALL, FVT_NewsData.BLOGS, false);
		//verifyImFollowingNewsStory(testUser2,"liked their own comment on "+ModeratedIdeablogEntry+".","Blogs", true);

		System.out.println("INFO: End of Activities FVT_Level_2 testRecommendComment_ModComm");
		
	}
	
	//@Test (dependsOnMethods = { "testRecommendComment_ModComm" }, groups = { "broken" })
	public void testIdeaGraduated_ModComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testIdeaVoted_ModComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//Open Community
		openCommunity(ModeratedIdeablogCommunity,FVT_CommunitiesObjects.ImAnOwner,FVT_CommunitiesObjects.LeftNavIdeationBlog);
		
		//Go to blog entry
		clickLink("link=" + ModeratedIdeablogEntry);
		
		//Graduate Idea
		clickLink(FVT_BlogsObjects.Graduate);
			
		//Graduate Ok
		//sel.focus(FVT_BlogsObjects.GraduateOk);
		clickLink(FVT_BlogsObjects.GraduateOk);
	
		//Logout
		LogoutAndClose();
		
		String BlogNewsStory = replaceNewsStory(NewsStoryData.GRADUATE_IDEA, ModeratedIdeablogEntry, ModeratedIdeablogCommunity, testUser1.getDisplayName());
		
		//Checking for tags
		verifyImFollowingNewsStory(testUser2,BlogNewsStory,FVT_NewsData.BLOGS, true);			
				
		System.out.println("INFO: End of Blogs FVT_Level_2 testIdeaVoted_ModComm");
		
	}
	
	
}
