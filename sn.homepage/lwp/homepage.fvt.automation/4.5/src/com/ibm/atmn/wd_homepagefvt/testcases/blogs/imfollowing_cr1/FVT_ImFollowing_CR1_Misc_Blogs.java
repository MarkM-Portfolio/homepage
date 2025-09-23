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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.imfollowing_cr1;


import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;


public class FVT_ImFollowing_CR1_Misc_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	private static String StandaloneBlogEntry2 = "";
	private static String StandaloneBlogEntry3 = "";
	
	
	//private static String GetConfirmation = "";
	
	@Test
	public void testCreateStandAloneBlog() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlog");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
	
		
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		driver.isTextPresent("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");
		
		//Logout
		Logout();
			
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlog");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlog" })
	public void testFollowPublicBlog() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPublicBlog");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentBlogs);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_BlogsObjects.PublicBlogs);
			
			clickLink(FVT_BlogsObjects.BlogsPublicListing);
			
			clickLink("link="+StandaloneBlogName);
			
			clickLink(FVT_BlogsObjects.FollowBlog);
			
			Logout();			
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPublicBlog");
			
		}
	
	@Test (dependsOnMethods = { "testFollowPublicBlog" })
	public void testCreateStandAloneBlogEntry() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		Logout();
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry" })
		public void testUnfollowFromNewsFeed() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testUnfollowFromNewsFeed");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Blogs");
			
			Thread.sleep(5000);
			
			assertTrue("Blogs entry story not appearing in Im Following View.",driver.isTextPresent(" created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog."));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();			
			Actions builder = new Actions(wd);
			
			String ActivitiesStory = "css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)";
			
			driver.getSingleElement(ActivitiesStory).hover();
			builder.moveToElement((WebElement) driver.getFirstElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement)driver.getFirstElement(FVT_HomepageObjects.StopFollowingCR1).getBackingObject()).click().build().perform();

			smartSleep(FVT_HomepageObjects.StopFollowingSave);
			clickLink(FVT_HomepageObjects.StopFollowingSave);
			
			sleep(500);
			
			clickLink(FVT_HomepageObjects.ImFollowing);
			
			driver.getSingleElement("css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)").hover();
			clickLink(FVT_HomepageObjects.StopFollowingCR1);
			//builder.moveToElement((WebElement) driver.getSingleElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing3).getBackingObject()).click();
			
			sleep(1000);
			
			assertTrue("Still following this story!", driver.isTextPresent("You are not currently following any items for this story."));
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testUnfollowFromNewsFeed");
			
		}
	
	@Test (dependsOnMethods = { "testUnfollowFromNewsFeed" })
	public void testCreateStandAloneBlogEntry_Second() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry_Second");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry2 = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		Logout();
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry_Second");
		
	}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry_Second" })
	public void testVerifyNoBlogsStoryInNF() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testVerifyNoBlogsStoryInNF");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Blogs");
			
			assertTrue("Blogs entry story still appearing in Im Following View.", driver.isTextPresent(" created a blog entry named "+StandaloneBlogEntry2+" in the "+StandaloneBlogName+" blog."));
			
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testVerifyNoBlogsStoryInNF");
			
		}
	
	
	@Test (dependsOnMethods = { "testVerifyNoBlogsStoryInNF" })
	public void testFollowPublicBlog_SecondTime() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowPublicBlog_SecondTime");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentBlogs);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_BlogsObjects.PublicBlogs);
			
			clickLink(FVT_BlogsObjects.BlogsPublicListing);
			
			clickLink("link="+StandaloneBlogName);
			
			clickLink(FVT_BlogsObjects.FollowBlog);
			
			Logout();			
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowPublicBlog_SecondTime");
			
		}
	
	@Test (dependsOnMethods = { "testUnfollowFromNewsFeed" })
	public void testCreateStandAloneBlogEntry_Third() throws Exception{
		
		System.out.println("INFO: Start of Blogs Level 2 testCreateStandAloneBlogEntry_Third");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry3 = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");
		
		//Logout
		Logout();
	  	
		System.out.println("INFO: End of Blogs Level 2 testCreateStandAloneBlogEntry_Third");
		
	}
	
	@Test (dependsOnMethods = { "testVerifyNoBlogsStoryInNF" })
	public void testUnfollowPublicBlog_SecondTime() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testUnfollowPublicBlog_SecondTime");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentBlogs);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_BlogsObjects.PublicBlogs);
			
			clickLink(FVT_BlogsObjects.BlogsPublicListing);
			
			clickLink("link="+StandaloneBlogName);
			
			clickLink(FVT_BlogsObjects.UnfollowBlog);
			
			Logout();			
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testUnfollowPublicBlog_SecondTime");
			
		}
	
	@Test (dependsOnMethods = { "testCreateStandAloneBlogEntry_Second" })
	public void testVerifyNoBlogsStoryInNF_Second() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testVerifyNoBlogsStoryInNF_Second");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			//Login(testUser1);
			Login(testUser2);
			
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Blogs");
			
			assertTrue("Blogs entry story still appearing in Im Following View.", driver.isTextPresent(" created a blog entry named "+StandaloneBlogEntry3+" in the "+StandaloneBlogName+" blog."));
			
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testVerifyNoBlogsStoryInNF_Second");
			
		}
}
