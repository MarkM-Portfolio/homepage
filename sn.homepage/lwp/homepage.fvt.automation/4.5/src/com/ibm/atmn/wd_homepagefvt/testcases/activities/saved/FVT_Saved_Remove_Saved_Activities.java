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




import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;

//****************************************
//NEEDS TO BE UPDATED
//****************************************


public class FVT_Saved_Remove_Saved_Activities extends FVT_ActivitiesMethods {

	FormInputHandler typist;	

	private static String publicActivityName = "";
	private static String publicActivityPublicEntryTitle = "";
	
		
	@Test 
	public void testRemovingSavedActivityStories() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testRemovingSavedActivityStories");
		
			// Login to communities
							
			LoadComponent(CommonObjects.ComponentNews);

			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

			clickLink("link=Saved");
			
			filterBy("Activities");
			
			//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

			clickLink(FVT_NewsObjects.DeleteSavedStory);
			
			clickLink(FVT_NewsObjects.RemoveSavedStory);

			assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	


			clickLink("link=For me");
			
			filterBy("Activities");
			
			//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

			assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
			
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
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
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

		filterBy("Activities");

		//cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
		
		clickLink("link=Home");
		
		clickLink("link=I'm following");
		
		//cautiousFocus("css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
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
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
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
		
		filterBy("Activities");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
	
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		//cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
	
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testRemovingSavedActivityStoriesPart3");
		
	}

}