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

/*  
 *  Note : If TTT is found for this test , please append to this comment and 
 *  verify that the tests are matching the scenarios correctly.
 *  
 *  ToDO :> Clean up code and Comment Code
 */



import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
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
	private User testUser1;
	private User testUser2;
	private User testUser3;

	
		
	@Test 
	public void testMarkingCommunitiesStoriesAsSaved_AllUpdates() throws Exception {
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of Communities Level 2 testMarkingCommunitiesStoriesAsSaved_AllUpdates");	
		
		//Create community
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		// Created a new community with public access

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PublicCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, testUser3.getEmail(), FVT_CommunitiesObjects.fullUserSearchIdentifier);

		//Add the blogs widget
		//AddWidgetToOverview("Calendar");
		AddWidgetToOverview("Events");
		
		Thread.sleep(2000);
		
		//Logout
		LogoutAndClose();
		
		//Follow Blog
		
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Log in
		Login(testUser2);
		
		//Go back to the main page of the Blog created above
		//clickLink("link=" + PublicCommunity);
		driver.getFirstElement("css=a[title='View public communities']").click();
		driver.getFirstElement("css=a:contains('"+PublicCommunity+"')").click();
		
		//Follow
		clickLink("link=Follow this Community");
		
		//driver.getFirstElement("css=a:contains('"+PublicCommunity+"')").click();
		driver.getFirstElement("link="+PublicCommunity).click();
		//Join
		clickLink("link=Join this Community");
		
		//Logout
		LogoutAndClose();
			
		//Save the communities news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser1);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		filterBy("All Updates");
			
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");
		
		if (!driver.isTextPresent("created a community named "+PublicCommunity))
			Assert.fail("Community hasn't been created -> "+PublicCommunity);

		driver.getFirstElement("css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)").hover();
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").click();
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");
		
		driver.getFirstElement("link="+PublicCommunity).hover();
		
		//if (driver.getFirstElement("css=div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").isTextPresent("Saved") == true)
		//	Assert.fail("Fail: Link still there!!");
		
		driver.getSingleElement("css= div.activityStreamNewsItemContainer div.lotusPostContent:nth(1)").hover();
		//driver.getFirstElement("link=Save this").click();
		driver.getFirstElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(1)").click();
		
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved 
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created a community named "+PublicCommunity+"."));
			
		//Delete the saved news story 
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");
		driver.getSingleElement("css= div.activityStreamNewsItemContainer div.lotusPostContent:nth(1)").hover();

		//clickLink(FVT_NewsObjects.DeleteSavedStory);
		driver.getSingleElement(FVT_NewsObjects.DeleteSavedStory+":nth(1)").click();
		
		//clickLink(FVT_NewsObjects.RemoveSavedStory);
		driver.getFirstElement(FVT_NewsObjects.RemoveSavedStory).click();

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again

		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new community named "+PublicCommunity+".')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		LogoutAndClose();
			
			
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved_AllUpdates");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved_AllUpdates" })
	public void testMarkingCommunitiesStoriesAsSaved2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved2_AllUpdates");

		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(testUser1);
		
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to moderated community
		clickLink("link=" + PublicCommunity);
	
		// Created a new calendar entry
		
		PublicCalendarEntry = "FVT Calendar Event " + genDateBasedRandVal();

		//CreateCalendarEntry(PublicCalendarEntry);
		driver.getFirstElement("css=a:contains('Events'):nth(0)").click();
		//driver.getFirstElement("css=[itemidx_='7']").click();
		smartSleep("css=a:contains('Create an Event')");
		driver.getFirstElement("css=a:contains('Create an Event')").click();
		driver.getFirstElement("css=input[id='calendar_event_editor-subject']").type(PublicCalendarEntry);
		/*driver.getFirstElement("css=input[type='checkbox'][id='calendar_event_editor-notify']").click();
		List<Element> eList= driver.getElements("css=input[name='lconn_calendar_FilteringCheckbox_0notifyPerson']");
		
		for (Element e:eList)
			e.click();
		*/
		driver.getFirstElement("css=input[type='submit'][value='Save'][id='calendar_event_editor-submit']").click();
		
		Thread.sleep(2000);
		
		//Logout
		LogoutAndClose();
		
		//Save the calendar news story 
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser2);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("All Updates");

		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		driver.getSingleElement("css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)").hover();
		driver.getSingleElement("css= div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").click();
		//clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created a new event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");
		
		driver.getSingleElement("css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)").hover();
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li a:nth(0)").click(); // Error here - Stop Following
		
		driver.getSingleElement("css=a[id='_saved']").click();
		
		driver.getSingleElement("css=div.activityStreamNewsItemContainer div.lotusPostContent:nth(0)").hover();
		assertTrue("Fail: text is not present!!", driver.isTextPresent("created the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
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
		
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingCommunitiesStoriesAsSaved2_AllUpdates" })
	public void testMarkingCommunitiesStoriesAsSaved3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved3_AllUpdates");
	
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login as a user (ie. Amy Jones451)
		Login(testUser2);
		
		driver.getFirstElement("css=a[title='View public communities'][role='button']").click();
		//Click on the Blog name
		clickLink("link=" + PublicCommunity);
		
		//Click on the Blog entry
		//clickLink("link=Calendar");
		//driver.getFirstElement("link=Events").click();
		driver.getFirstElement("css=[itemidx_='7']").click(); // Clicking on the left Nav bar -> Events
		
		//Click on the Blog entry
		clickLink("link=" + PublicCalendarEntry);
		
		//Update blog entry
		AddACommentToEventEntry();
			
		//Logout
		LogoutAndClose();
		
		//Save the news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(testUser1);

		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		driver.getSingleElement("css= div.lotusPostContent div.lotusMeta ul.lotusInlinelist:nth(0)").hover();
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//check that the news story saved
	
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");
	
		driver.getSingleElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		//assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		assertTrue("Fail: Link is present", driver.getSingleElement("css=div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li.lotusFirst a:nth(0)").isTextPresent("Saved"));
		
		//Check that the news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		//assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community."));
		
		//Delete the saved news story
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		driver.getSingleElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		driver.getSingleElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("All Updates");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on your event "+PublicCalendarEntry+" in the "+PublicCommunity+" community.')");

		driver.getSingleElement(FVT_HomepageObjects.Hover_Over_PostBox+":nth(0)").hover();
		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout
		LogoutAndClose();
		
		
		System.out.println("INFO: End of Communities FVT_Level_2 testMarkingCommunitiesStoriesAsSaved3_AllUpdates");
		
	}
	

}