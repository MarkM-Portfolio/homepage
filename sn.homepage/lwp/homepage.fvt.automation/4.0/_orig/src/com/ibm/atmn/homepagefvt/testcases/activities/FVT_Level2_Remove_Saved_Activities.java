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

package com.ibm.atmn.homepagefvt.testcases.activities;




import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.homepagefvt.tasks.activities.FVT_ActivitiesMethods;



public class FVT_Level2_Remove_Saved_Activities extends FVT_ActivitiesMethods {

	private FormInputHandler typist = getFormInputHandler();
	
	

	private static String publicActivityName = "FVT Level2 Public Activity 1350143945";
	private static String publicActivityPublicEntryTitle = "FVT Level2 Public Activity 1350143945 Entry";
	
		
	@Test 
	public void testRemovingSavedActivityStories() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testRemovingSavedActivityStories");
		
			// Login to communities
							
			LoadComponent(CommonObjects.ComponentNews);

			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

			clickLink("link=Saved");
			
			FilterBy("Activities");
			
			cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

			clickLink(FVT_NewsObjects.DeleteSavedStory);
			
			clickLink(FVT_NewsObjects.RemoveSavedStory);

			assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	


			clickLink("link=For me");
			
			FilterBy("Activities");
			
			cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

			assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
			
			//Logout
			Logout();
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testRemovingSavedActivityStories");
			
		}
	
	//@Test (dependsOnMethods = { "testRemovingSavedActivityStories" })
	public void testRemovingSavedActivityStoriesPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRemovingSavedActivityStoriesPart2");
	
		
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
		
		
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");

		FilterBy("Activities");

		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");
		
		cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));			
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Logout
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testRemovingSavedActivityStoriesPart2");
		
	}
	
	//@Test (dependsOnMethods = { "testRemovingSavedActivityStoriesPart2" })
	public void testRemovingSavedActivityStoriesPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testRemovingSavedActivityStoriesPart3");
	
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
		
		
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=For me");
		
		FilterBy("Activities");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
	
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testRemovingSavedActivityStoriesPart3");
		
	}

}