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

/*  
 *  Note : If TTT is found for this test , please append to this comment and 
 *  verify that the tests are matching the scenarios correctly.
 *  
 *  ToDO :> Clean up code and Comment Code
 */


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.core.TestConfiguration.BrowserType;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;



public class FVT_Saved_AllUpdates_Blogs extends FVT_BlogsMethods {


	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	private static User testUser1 = null;
	private static User testUser2 = null;
	private static String URL = "";
	private List<Element> list = null;
	
		
	@Test 
	public void testMarkingBlogStoriesAsSaved_AllUpdates() throws Exception {
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of Blogs Level 2 testMarkingBlogStoriesAsSaved_AllUpdates");	
		String DateTime = CommonMethods.genDateBasedRandVal();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Start a Blog button
		clickLink(BlogsObjects.StartABlog);
		
		//Add code for creating a blog
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		driver.getFirstElement("css=a:contains('"+FVT_BlogsData.StandaloneBlog+DateTime+"')").click();
		URL = driver.getCurrentUrl();
		
		//add code to check that the blog was created
		//BlogsVerifyUIMessage("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");	
			
		//Save the blogs news story
		LogoutAndClose();
		
		LoadComponent(CommonObjects.ComponentNews);
		Login(testUser2);

		clickLink("link=Discover");
			
		filterBy("All Updates");
			
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		driver.getFirstElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		driver.getFirstElement(FVT_HomepageObjects.SaveThis_PostBox+":nth(0)").click();
		
		//Check that the story saved
		
		clickLink("link=Home");
			
		clickLink("link=Discover");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");
		
		//Reporter.log("Fail: Link still there!! "+driver.getFirstElement(FVT_HomepageObjects.SaveThis_PostBox+":nth(0)").isTextPresent("Already Saved"));
		
		/*
		driver.getFirstElement("link="+FVT_BlogsData.StandaloneBlog+DateTime).click();
		Reporter.log("Box found on page = "+driver.isElementPresent("css=[class='activityStreamNewsItemContainer lotusPost lotusPostHover']"));
		boolean saved = driver.getFirstElement("css=[class='activityStreamNewsItemContainer lotusPost lotusPostHover']").isTextPresent("Already Saved");
		Reporter.log("= "+saved);
		
		
		if (saved)
			Assert.fail("Fail: Link still there!!");
		*/
		list = driver.getElements("css=li[id^='com_ibm_social_as_item_NewsItem_']");
		WebElement we;
		
		for(Element e:list){
			e.hover();

			//New Lines - testing
			we = (WebElement) e.getBackingObject();

			if(we.getText().contains(FVT_BlogsData.StandaloneBlog+DateTime)){
				Reporter.log(" -> "+we.getText());
				we.findElement(By.cssSelector("li.lotusFirst a[role='button']")).click();
				break;
			}

		}
		
		//assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").isTextPresent("Already Saved"));
		
		clickLink("link=Saved");
		//Check that the news story is visible in Saved
		
		driver.getFirstElement("css=a[title='View the updates you have saved']").click();
			
		Reporter.log("Debug "+StandaloneBlogName);

		smartSleep("css=div.lotusPostContent div.lotusPostAction:nth(0)");
		
		//assertTrue("Fail: text is not present!!", driver.isTextPresent("created a blog named "+StandaloneBlogName+"."));
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a blog named "+StandaloneBlogName));
	
		//Delete the news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		clickLink("link="+StandaloneBlogName);
		//clickLink("css=[role='presentation']");
		driver.getFirstElement("css=div.lotusPostAvatar img.activityStreamPhotoOf:nth(0)").hover();
		driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).click();
		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		driver.getFirstElement("css=div.lotusPostAvatar img.activityStreamPhotoOf:nth(0)").hover();
		driver.getFirstElement(FVT_NewsObjects.RemoveSavedStory).click();
		//clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		driver.getFirstElement("link=Discover").click();
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		LogoutAndClose();
			
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSaved_AllUpdates");
			
	}
	
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSaved_AllUpdates" })
	public void testMarkingBlogStoriesAsSavedPart2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2_AllUpdates");
	
		
		//Follow Blog
		
		//LoadComponent(CommonObjects.ComponentBlogs);
		
		//Log in
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		//Go back to blogs list
		//clickLink("link=Public Blogs");
		//clickLink("link=Blogs Listing");
		//smartSleep("css=a:contains('"+StandaloneBlogName+"')");
		//driver.getFirstElement("css=a:contains('"+StandaloneBlogName+"')").click();
		

		driver.load(URL);
		
		if (testConfig.browserIs(BrowserType.IE)) {
			sleep(2000);
			WebDriver wd = (WebDriver) driver.getBackingObject();
			wd.navigate().to("javascript:document.getElementById('overridelink').click()");
		}
		
		
		//LoadComponent(URL);
		driver.getFirstElement("css=a:contains('Log in')").click();
		Login(testUser2);		
		
		//Follow
		clickLink("link=Follow this Blog");
		
		//Logout
		LogoutAndClose();
		
		//Create blog entry

		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones450)
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		//Click on the My Blogs tab
		clickLink(BlogsObjects.MyBlogs);
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Add code for creating a blog entry
		StandaloneBlogEntry = StandaloneBlogName + " Blog Entry";
		CreateANewBlogEntry(StandaloneBlogName + " Blog Entry");		
		
		//Logout
		LogoutAndClose();
		
		//Save the blogs news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		clickLink("link=Home");
		
		//clickLink("link=I'm following");
		driver.getFirstElement("css=[id='_imFollowing']").click();
		
		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		driver.getFirstElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		driver.getFirstElement(FVT_HomepageObjects.SaveThis).click();
		//clickLink("link=Save this");
		
		//Check taht the story saved 
		
		clickLink("link=Home");
		
		clickLink("css=a[id='_imFollowing']:nth(0)");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");
		
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").hover();
		assertTrue("Fail: Link still there!!", driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent").isTextPresent("Saved"));
		
		
		//Check that the story appears in Saved
		
		clickLink("link=Saved");
		
		Reporter.log("created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.");
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog."));
		
		//Delete the news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		driver.getFirstElement("css=a:contains('"+StandaloneBlogEntry+"')").hover();
		
		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link 
		
		driver.getFirstElement("css=[id='_imFollowing']").click();
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		LogoutAndClose();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2_AllUpdates");
		
	}
	// Verify if the last part is needed 
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSavedPart2_AllUpdates" })
	public void testMarkingBlogStoriesAsSavedPart3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3_AllUpdates");
	
		//Comment on the blog entry
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones451)
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		Login(testUser2);
		
		//Click on the My Blogs tab
		//clickLink("link=Blogs Listing");
		driver.getFirstElement("css=a:contains('Public Blogs')").click();
		
		//Click on the Blog name
		//clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
			
		//Logout
		LogoutAndClose();
		
		//Save the blog news story
		
		LoadComponent(CommonObjects.ComponentNews);

		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		System.out.print("Current page URL "+driver.getCurrentUrl());
		
		driver.getFirstElement("css=a[id='_myNotifications']").click();
		driver.getSingleElement("css=span.lotusLeft span span#com_ibm_social_as_filter_FilterMenu_0.asFilterMenu select").useAsDropdown().selectOptionByVisibleText("Blogs");
		driver.getSingleElement("css=span.lotusLeft span span#com_ibm_social_as_filter_FilterMenu_2.asFilterMenu select").useAsDropdown().selectOptionByVisibleText("For Me");

		//clickLink("link=For me");
		if (!driver.getFirstElement("css= div.activityStreamNewsItemContainer").isTextPresent(BlogsData.BlogsCommentText))
			Assert.fail("Comment hasn't found the page! -> "+BlogsData.BlogsCommentText);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		driver.getFirstElement("css=div.activityStreamNewsItemContainer").hover();
		
		clickLink("link=Save this");
	
		//Check that the story is saved 
		
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");
	
		//driver.getFirstElement("css=div.lotusPostContent div.lotusPostAction").hover();
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		//assertTrue("Fail: Link still there!!", driver.getFirstElement(FVT_HomepageObjects.SaveThis_PostBox).isTextPresent("Saved"));
		
		list = driver.getElements("css=li[id^='com_ibm_social_as_item_NewsItem_']");
		WebElement we;
		
		for(Element e:list){
			e.hover();
			we = (WebElement) e.getBackingObject();
			if(we.getText().contains("commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.")){
				we.findElement(By.cssSelector("li.lotusFirst a[role='button']")).click();
				assertTrue("Fail: Link still there!!", we.getText().contains("Saved"));
				break;
			}
		}
		
		//Check that the story appears in Saved
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog."));
		
		//Delete the news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		driver.getFirstElement("css= div.activityStreamNewsItemContainer").hover();
		driver.getFirstElement(FVT_NewsObjects.DeleteSavedStory).click();
		
		//clickLink(FVT_NewsObjects.RemoveSavedStory);
		driver.getFirstElement("css= div.activityStreamNewsItemContainer").hover();
		driver.getFirstElement(FVT_NewsObjects.RemoveSavedStory).click();

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		//clickLink("link=For me");

		driver.getFirstElement("css=a[id='_myNotifications']").click();
	
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		
		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		LogoutAndClose();
		
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3_AllUpdates");
		
	}
	
	

}