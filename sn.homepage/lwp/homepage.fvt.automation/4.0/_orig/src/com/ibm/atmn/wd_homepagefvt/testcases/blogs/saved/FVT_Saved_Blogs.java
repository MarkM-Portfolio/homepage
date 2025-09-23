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

package com.ibm.atmn.wd_homepagefvt.testcases.blogs.saved;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;



public class FVT_Saved_Blogs extends FVT_BlogsMethods {


	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	
	private static String StandaloneBlogName2 = "";
	private static String StandaloneBlogEntry2 = "";
	
	private static String StandaloneBlogName3 = "";
	private static String StandaloneBlogEntry3 = "";
	
	
	private static String SecondBlogComment = "";
	private static String Comment = "";
	
		
	@Test 
	public void testMarkingBlogStoriesAsSaved() throws Exception {
				
		System.out.println("INFO: Start of Blogs Level 2 testMarkingBlogStoriesAsSaved");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Open up new blog
		startABlog();
		
		//Create blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Save the blogs news story
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Blogs");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String BlogsStory = "css=div.lotusPostContent:contains('created a blog named "+StandaloneBlogName+".')";
		
		//
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
		
		//check that the news story saved
		clickLink(FVT_HomepageObjects.ImFollowing);
			
		Thread.sleep(1000);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//Thread.sleep(1000);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a blog named "+StandaloneBlogName+".')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created a blog named "+StandaloneBlogName+"."));
			
		//Delete the blogs news story
		
		filterBy("Blogs");
				
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();

		clickLink(FVT_NewsObjects.RemoveSavedStory);

		clickLink(FVT_HomepageObjects.Discover);	
		
		filterBy("Blogs");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a blog named "+StandaloneBlogName+".')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSaved");
			
	}
	
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSaved" })
	public void testMarkingBlogStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2");
		
		//Follow Blog
		//followBlog(StandaloneBlogName);
		LoadComponent(CommonObjects.ComponentBlogs);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go back to blogs list
		clickLink("link=Public Blogs");
		
		clickLink("link=Blogs Listing");
		
		//Go back to the main page of the Blog created above
		clickLink("link=" + StandaloneBlogName);
		
		//Follow
		clickLink("link=Follow this Blog");
		
		//Logout
		Logout();
		
		driver.close();
		
		//Add blog entry
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");		
		
		//Logout
		Logout();
		
		driver.close();
		
		//Save the blog news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("Blogs");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String BlogsEntryStory = "css=div.lotusPostContent:contains('created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')";
		
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsEntryStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();

		//Check that the news story saved
		
		clickLink(FVT_HomepageObjects.GettingStarted);
		
		Thread.sleep(1000);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog."));
		
		//Delete the news story
		
		filterBy("Blogs");
		
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsEntryStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
			
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		//assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("Blogs");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		Logout();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSavedPart2" })
	public void testMarkingBlogStoriesAsSavedPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3");
	
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Click on the My Blogs tab
		clickLink("link=Public Blogs");
		
		clickLink("link=Blogs Listing");
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
			
		//Logout
		Logout();
		
		driver.close();
		
		//Save the blogs news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.MyNotifications);
			
		filterBy("Blogs");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String BlogsCommentStory = "css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')";
		
		String BlogsCommentStory2 = "css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')";

		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsCommentStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
	
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.GettingStarted);
		
		Thread.sleep(1000);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')").hover();
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story is visible in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog."));
	
		//Delete the news story
		
		filterBy("Blogs");
		
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsCommentStory2).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		//assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("Blogs");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')").hover();

		assertTrue("Fail: Link still there!!", !driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3");
		
	}
	
	//@Test //(dependsOnMethods = { "testMarkingBlogStoriesAsSavedPart3" })
	public void testSavedBlogsStory_TwoComments() throws Exception {
		
		//WebDriver wd = (WebDriver) driver.getBackingObject();
			
		System.out.println("INFO: Start of Blogs Level 2 testSavedBlogsStory_TwoComments");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName2 = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName2);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry2 = CreateANewBlogEntry(StandaloneBlogName2 + " Blog Entry");	
		
		//Add first comment
		AddACommentToEntry(FVT_BlogsData.BlogsComment1);
		
		//Add second comment
		SecondBlogComment = AddACommentToEntry(FVT_BlogsData.BlogsComment2);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Save the blogs news story
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Blogs");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String BlogsCommentsStory = "css=div.lotusPostContent:contains('commented on their own "+StandaloneBlogEntry2+" blog entry in the "+StandaloneBlogName2+" blog.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsCommentsStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
			
		//check that the news story saved
		clickLink(FVT_HomepageObjects.Home);
			
		Thread.sleep(1000);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//Thread.sleep(1000);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on their own "+StandaloneBlogEntry2+" blog entry in the "+StandaloneBlogName2+" blog.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on their own "+StandaloneBlogEntry2+" blog entry in the "+StandaloneBlogName2+" blog."));
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(SecondBlogComment));
		
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsCommentsStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);
		
		//Logout
		Logout();
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testSavedBlogsStory_TwoComments");
			
	}
	
	//@Test (dependsOnMethods = { "testSavedBlogsStory_TwoComments" })
	public void testSavedBlogsStory_Recommend() throws Exception {
		
		//WebDriver wd = (WebDriver) driver.getBackingObject();
			
		System.out.println("INFO: Start of Blogs Level 2 testSavedBlogsStory_Recommend");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName3 = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName3);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry3 = CreateANewBlogEntry(StandaloneBlogName3 + " Blog Entry");	
		
		//Add first comment
		Comment =  AddACommentToEntry(FVT_BlogsData.BlogsComment1);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Save the blogs news story
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("Blogs");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String BlogsCommentsStory = "css=div.lotusPostContent:contains('commented on their own "+StandaloneBlogEntry3+" blog entry in the "+StandaloneBlogName3+" blog.')";
		
		//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
		builder.moveToElement((WebElement) driver.getFirstElement(BlogsCommentsStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.SaveThis).getBackingObject()).click().build().perform();
			
		//check that the news story saved
		clickLink(FVT_HomepageObjects.Home);
			
		Thread.sleep(1000);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//Thread.sleep(1000);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('commented on their own "+StandaloneBlogEntry3+" blog entry in the "+StandaloneBlogName3+" blog.')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on their own "+StandaloneBlogEntry3+" blog entry in the "+StandaloneBlogName3+" blog."));
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(Comment));
		
		//Logout
		Logout();
		
		driver.close();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName3);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogEntry3);
		
		//
		clickLink(FVT_BlogsObjects.Like);
		
		//Logout
		Logout();
		
		driver.close();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("liked their own "+StandaloneBlogEntry3+" blog entry in the "+StandaloneBlogName3+" blog."));

		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on their own "+StandaloneBlogEntry3+" blog entry in the "+StandaloneBlogName3+" blog."));
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(Comment));
		
		//Logout
		Logout();
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testSavedBlogsStory_Recommend");
			
	}
	

}