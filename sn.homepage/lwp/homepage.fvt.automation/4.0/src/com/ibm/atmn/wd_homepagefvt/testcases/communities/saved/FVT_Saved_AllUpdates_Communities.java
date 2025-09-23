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

package com.ibm.atmn.wd_homepagefvt.testcases.communities.saved;




import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.blogs.FVT_BlogsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

//****************************************
//NEEDS TO BE UPDATED
//****************************************

public class FVT_Saved_AllUpdates_Communities extends FVT_BlogsMethods {


	

	private static String PublicCommunity = "";
	private static String PublicCalendarEntry = "";
	
	
		
	@Test 
	public void testMarkingCommunitiesStoriesAsSaved_AllUpdates() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingCommunitiesStoriesAsSaved_AllUpdates");	
		
		//Create community
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+101, FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Add the blogs widget
		AddWidgetToOverview("Calendar");
		
		Thread.sleep(2000);
		
		//Logout
		Logout();
		
		//Follow Blog
		
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go back to the main page of the Blog created above
		clickLink("link=" + PublicCommunity);
		
		//Follow
		clickLink("link=Follow this Community");
		
		//Join
		clickLink("link=Join this Community");
		
		//Logout
		Logout();
			
		//Save the communities news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("All Updates");
			
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved 
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created a new community named "+PublicCommunity+"."));
			
		//Delete the saved news story 
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		Logout();
			
			
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved_AllUpdates");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved_AllUpdates" })
	public void testMarkingCommunitiesStoriesAsSaved2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved2_AllUpdates");

		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + PublicCommunity);
	
		// Created a new calendar entry
		
		PublicCalendarEntry = "FVT Calendar Event " + genDateBasedRandVal();

		//CreateCalendarEntry(PublicCalendarEntry);
		
		Thread.sleep(2000);
		
		//Logout
		Logout();
		
		//Save the calendar news story 
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
		//Delete the saved news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved2_AllUpdates" })
	public void testMarkingCommunitiesStoriesAsSaved3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved3_AllUpdates");
	
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Click on the Blog name
		clickLink("link=" + PublicCommunity);
		
		//Click on the Blog entry
		clickLink("link=Calendar");
		
		//Click on the Blog entry
		clickLink("link=" + PublicCalendarEntry);
		
		//Update blog entry
		AddACommentToEventEntry();
			
		//Logout
		Logout();
		
		//Save the news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//check that the news story saved
	
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
		//Delete the saved news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved3_AllUpdates");
		
	}
	

}