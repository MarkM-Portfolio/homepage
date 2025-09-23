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
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.homepagefvt.appobjects.blogs.BlogsObjects;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;



public class FVT_Level2_Saved_AllUpdates_Blogs extends FVT_BlogsMethods {


	

	private static String StandaloneBlogName = "";
	private static String StandaloneBlogEntry = "";
	
		
	@Test 
	public void testMarkingBlogStoriesAsSaved_AllUpdates() throws Exception {
			
			
		System.out.println("INFO: Start of Blogs Level 2 testMarkingBlogStoriesAsSaved_AllUpdates");	
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
		StandaloneBlogName = CreateABlogAsStandardUser(FVT_BlogsData.StandaloneBlog+DateTime, FVT_BlogsData.StandaloneBlogsAddress+DateTime);
		
		//add code to check that the blog was created
		BlogsVerifyUIMessage("New blog ["+FVT_BlogsData.StandaloneBlog+DateTime+"] has been successfully created.");	
			
		//Save the blogs news story
		
		LoadComponent(CommonObjects.ComponentNews);

		clickLink("link=Discover");
			
		FilterBy("All Updates");
			
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		clickLink("link=Save this");
		
		//Check that the story saved
		
		clickLink("link=Home");
			
		clickLink("link=Discover");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the news story is visible in Saved
		
		clickLink("link=Saved");
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" created a new blog named "+StandaloneBlogName+"."));
	
		//Delete the news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink("link=Discover");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog named "+StandaloneBlogName+".')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSaved_AllUpdates");
			
	}
	
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSaved_AllUpdates" })
	public void testMarkingBlogStoriesAsSavedPart2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2_AllUpdates");
	
		
		//Follow Blog
		
		LoadComponent(CommonObjects.ComponentBlogs);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go back to blogs list
		//clickLink("link=Public Blogs");
		clickLink("link=Blogs Listing");
		
		//Go back to the main page of the Blog created above
		clickLink("link=" + StandaloneBlogName);
		
		//Follow
		clickLink("link=Follow this Blog");
		
		//Logout
		Logout();
		
		//Create blog entry

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
		
		//Save the blogs news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");

		FilterBy("All Updates");

		cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		clickLink("link=Save this");
		
		//Check taht the story saved 
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent("created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog."));
		
		//Delete the news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link 
		
		clickLink("link=I'm following");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new blog entry named "+StandaloneBlogEntry+" in the "+StandaloneBlogName+" blog.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingBlogStoriesAsSavedPart2_AllUpdates" })
	public void testMarkingBlogStoriesAsSavedPart3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3_AllUpdates");
	
		//Comment on the blog entry
		
		//Load the component
		LoadComponent(CommonObjects.ComponentBlogs);
			
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Click on the My Blogs tab
		clickLink("link=Blogs Listing");
		
		//Click on the Blog name
		clickLink("link=" + StandaloneBlogName);
		
		//Click on the Blog entry
		clickLink("link=" + StandaloneBlogEntry);
		
		//Update blog entry
		AddACommentToEntry();
			
		//Logout
		Logout();
		
		//Save the blog news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=For me");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		clickLink("link=Save this");
	
		//Check that the story is saved 
		
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog."));
		
		//Delete the news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink("link=For me");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your "+StandaloneBlogEntry+" blog entry in the "+StandaloneBlogName+" blog.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testMarkingBlogStoriesAsSavedPart3_AllUpdates");
		
	}
	
	

}