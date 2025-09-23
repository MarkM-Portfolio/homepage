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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.saved;




import java.util.ArrayList;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_Saved_AllUpdates_Activities extends FVT_ActivitiesMethods {

	private FormInputHandler typist = getFormInputHandler();
	private ArrayList<String> assertList;
	

	private static String publicActivityName = "";
	private static String publicActivityPublicEntryTitle = "";
	
		
	
	@Test 
	public void testMarkingActivityStoriesAsSaved_AllUpdates() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates");
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			assertTrue("Fail: Activities is not open", sel.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,CommonData.IC_LDAP_Username451);
			
			//Go back to activities list
			clickLink(FVT_ActivitiesObjects.Activities_Tab);
	
			//Expand top activity (activity created above)
			clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
			
			//Check all 'start activity' form entries were saved and are now displayed
			assertList = typist.getAssertList();
			assertAllTextPresent(assertList);
			typist.dumpList();
			
			//Go back to the main page of the activity created above
			clickLink("link=" + publicActivityName);
			
			//expand activity description
			clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
			
			changeActivityAccess();
			
			//Save the news story in Discover
			
			LoadComponent(CommonObjects.ComponentNews);

			clickLink("link=Discover");
			
			FilterBy("All Updates");
			
			cautiousFocus("css=div.lotusPostContent:contains('created a public activity named "+publicActivityName+".')");

			clickLink("link=Save this");
		
			//Check that the news story saved
			
			clickLink("link=Home");
			
			clickLink("link=Discover");
			
			cautiousFocus("css=div.lotusPostContent:contains('created a public activity named "+publicActivityName+".')");
		
			assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
			//Go to Saved to check that the news story appears here
			
			clickLink("link=Saved");
			
			assertTrue("Fail: text is not present!!", sel.isTextPresent(" created a public activity named "+publicActivityName+"."));
			
			//Delete the news story
			
			FilterBy("All Updates");
			
			cautiousFocus("css=div.lotusPostContent:contains('created a public activity named "+publicActivityName+".')");

			clickLink(FVT_NewsObjects.DeleteSavedStory);
			
			clickLink(FVT_NewsObjects.RemoveSavedStory);

			assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

			//Check that the Save this link is visible again 

			clickLink("link=Discover");
			
			FilterBy("All Updates");
			
			cautiousFocus("css=div.lotusPostContent:contains('created a public activity named "+publicActivityName+".')");

			assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
			
			//Logout
			Logout();
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates");
			
		}
	
	@Test (dependsOnMethods = { "testMarkingActivityStoriesAsSaved_AllUpdates" })
	public void testMarkingActivityStoriesAsSaved_AllUpdates_Part2() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates_Part2");
	
		
		//Follow Activity
		//followActivity(publicActivityName);
		LoadComponent(CommonObjects.ComponentActivities);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//cautiousFocus(CommonObjects.Banner_Apps_Activities);
		//cautiousClick(CommonObjects.Banner_Apps_Activities);
		
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);
		
		//Go back to the main page of the activity created above
		clickLink("link=" + publicActivityName);
		
		//Follow
		clickLink(FVT_ActivitiesObjects.FollowActivity);
		
		//Logout
		Logout();
		
		//Add entry to activity 
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", sel.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
		
		// Create a new entry within this activity
		publicActivityPublicEntryTitle = addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
		
		//Logout
		Logout();
		
		//Save activity news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");

		FilterBy("All Updates");

		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
		
		//Check that the story saved
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");
		
		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the story is visible in Saved
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Delete news story
		
		FilterBy("All Updates");

		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again
		
		clickLink("link=I'm following");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates_Part2");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingActivityStoriesAsSaved_AllUpdates_Part2" })
	public void testMarkingActivityStoriesAsSaved_AllUpdates_Part3() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates_Part3");
	
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
		assertTrue("Fail: Activities is not open", sel.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
			
		//Logout
		Logout();
		
		//Save the news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=For me");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
	
		//Check that the story is saved
		
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that the story appears in Saved
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Delete the news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the story 
		
		clickLink("link=For me");
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityStoriesAsSaved_AllUpdates_Part3");
		
	}
	
	

}