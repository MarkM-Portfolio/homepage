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

package com.ibm.atmn.homepagefvt.testcases.dogear;




import org.junit.Assert;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.dogear.FVT_DogearMethods;
import com.ibm.atmn.homepagefvt.tasks.forums.FVT_ForumsMethods;



public class FVT_Level2_Saved_Dogear extends FVT_DogearMethods {


	

	private static String part1BookmarkPublicTitle = "";
	@SuppressWarnings("unused")
	private static String part1BookmarkModifiedPublicToPrivate = "";
	

	//NOTE: Only the ajones[number](Amy Jones[number]) class of users are valid here without further changes.
	private static String testUser1Username = CommonData.IC_LDAP_Username450;
	private static String testUser1Password = CommonData.IC_LDAP_Password450;
	
	
	private FormInputHandler typist = getFormInputHandler();
	
		
	@Test 
	public void testMarkingForumsStoriesAsSaved() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingForumsStoriesAsSaved");	
		
		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		// Login as a user (ie. Amy Jones450)
		Login(testUser1Username, testUser1Password);

		//Add a public bookmark 
		part1BookmarkPublicTitle = addBookmark(typist);
		//ArrayList<String> part1BookmarkPublicList = typist.getListCopy();
		//assertList = typist.getAssertList();
		//verifyBookmarkPresence(assertList, true, true);
		//typist.dumpList();
		
		// Logout of Connections
		Logout();
		
		
		//
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		FilterBy("Bookmarks");
			
		cautiousFocus("css=div.lotusPostContent:contains('created a new bookmark named "+part1BookmarkPublicTitle+".')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new bookmark named "+part1BookmarkPublicTitle+".')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent("created a new bookmark named "+part1BookmarkPublicTitle+"."));
			
		/*
		
		//Logout
		Logout();
		
		//
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=Saved");
		*/
		
		FilterBy("Bookmarks");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new bookmark named "+part1BookmarkPublicTitle+".')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		clickLink(FVT_HomepageObjects.Discover);
		
		FilterBy("Bookmarks");
		
		cautiousFocus("css=div.lotusPostContent:contains('created a new bookmark named "+part1BookmarkPublicTitle+".')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSaved");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingBookmarksStoriesAsSaved" })
	public void testMarkingBookmarksStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSavedPart3");
	
		// Load the component
		LoadComponent(CommonObjects.ComponentDogear);

		
		// Logout of Connections
		Logout();
		
		
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("Bookmarks");
		
		cautiousFocus("css=div.lotusPostContent:contains('')");

		clickLink(FVT_HomepageObjects.SaveThis);
	
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(""));
		
		/*
		//Logout
		Logout();
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=Saved");
		
		*/
		
		FilterBy("Bookmarks");
		
		cautiousFocus("css=div.lotusPostContent:contains('')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	


		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("Bookmarks");
		
		cautiousFocus("css=div.lotusPostContent:contains('')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Bookmarks FVT_Level_2 testMarkingBookmarksStoriesAsSavedPart2");
		
	}
	

}