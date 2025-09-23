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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.discover;


import java.util.ArrayList;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;



public class FVT_Discover_Private_Activities extends FVT_ActivitiesMethods {

	User testUser1;
	User testUser2;
	
	FormInputHandler typist;
	private ArrayList<String> assertList;
	
	private static String privateActivityName = "";
	private static String privateEntryTitle = "";
	private static String privateToDoName = "";
	
	
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Communities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Communities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreateStandPrivateActivity() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity");
	
		typist = new FormInputHandler(driver);
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Created a new activity 
		privateActivityName = startAnActivity(FVT_ActivitiesData.Start_Private_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);

		sleep(2000);
		//Expand top activity (activity created above)
		clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
		
		//Check all 'start activity' form entries were saved and are now displayed
		assertList = typist.getAssertList();
		assertAllTextPresent(assertList);
		typist.dumpList();
		
		//Go back to the main page of the activity created above
		clickLink("link=" + privateActivityName);
		
		//expand activity description
		clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
		
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_PRIVATE_ACTIVTY, privateActivityName, null, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2," created a private activity named "+privateActivityName+".","Discover","All","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity");
		
	}
		
	
	
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity" })
	public void testCreateStandPrivateActivity_PrivateEntry() throws Exception {
			
			
			
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity_PrivateEntry");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
			
		// Create a new entry within this activity
		privateEntryTitle = addEntryToActivity_Basic(privateActivityName+" Entry",typist, false);
			
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_ACTIVITY_ENTRY, privateEntryTitle, privateActivityName, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2," created the "+privateEntryTitle+" entry in the "+privateActivityName+" activity.","Discover","All","Activities", false);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity_PrivateEntry");
			
	}
		
	
	
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_PrivateEntry" })
	public void testCreateStandPrivateActivity_Comment() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity_Comment");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
		
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
			
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.COMMENT_ON_ACTIVITY, privateEntryTitle, privateActivityName, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2,"commented on their own "+privateEntryTitle+" entry in the "+privateActivityName+" activity.","Discover","All","Activities", false);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity_Comment");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_Comment" })
	public void testCreateStandPrivateActivity_ToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
	
		// Create a new to do
		privateToDoName = addToDoToActivity(privateActivityName+" To-Do",typist, false, false, testUser1);
			
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.CREATE_TODO_ITEM, privateToDoName, privateActivityName, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2," created a to-do named "+privateToDoName+" in the "+privateActivityName+" activity.","Discover","All","Activities", false);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_ToDo" })
	public void testMarkToDoAsCompleted_PrivateActivity() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateActivity");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
	
		//Open To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		sleep(2000);
		
		// Mark to do as complete			
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);
			
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.COMPLETE_TODO_ITEM, privateToDoName, privateActivityName, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, false);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2,"completed their own "+privateToDoName+" to-do item in the "+privateActivityName+" activity.","Discover","All","Activities", false);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateActivity");
			
	}
	
	
	
	@Test (dependsOnMethods = { "testMarkToDoAsCompleted_PrivateActivity" })
	public void testChangeActivityToPublicAccess() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testChangeActivityToPublicAccess");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
	
		//expand activity description
		clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
		
		//Change from Private to Public
		changeActivityAccess("Public");
			
		//Logout
		LogoutAndClose();
		
		String ActivityNewsStory = replaceNewsStory(NewsStoryData.MAKE_ACTIVITY_PUBLIC, privateActivityName, null, testUser1.getDisplayName());
		verifyNewsStory_TwoFilters(testUser2, ActivityNewsStory, FVT_NewsData.DISCOVER_VIEW, FVT_NewsData.ALL, FVT_NewsData.ACTIVITIES, true);
		
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyNewsStory_TwoFilters(testUser2," made the "+privateActivityName+" activity public.","Discover","All","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testChangeActivityToPublicAccess");
			
	}
	
	

}