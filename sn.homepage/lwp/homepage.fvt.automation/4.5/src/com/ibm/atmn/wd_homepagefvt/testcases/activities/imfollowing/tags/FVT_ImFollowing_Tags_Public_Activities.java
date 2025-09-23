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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.imfollowing.tags;




import java.util.ArrayList;

import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;


public class FVT_ImFollowing_Tags_Public_Activities extends FVT_ActivitiesMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	FormInputHandler typist;
	private ArrayList<String> assertList;

	private static String publicActivityName = "";
	private static String publicActivityPrivateEntryTitle = "";
	private static String publicActivityPublicEntryTitle = "";
	private static String publicActivityPrivateToDoName = "";
	private static String publicToDoName = "";
	private static String publicAssignedToDoName = "";
	

	
	@Test
	public void testFollowTag() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowTag");
			
			typist = new FormInputHandler(driver);
		
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
			
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			//Follow Tag
			followTag(CommonData.AutomationTag);
			
			//Logout
			LogoutAndClose();
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowTag");
			
		}
		
	@Test (dependsOnMethods = { "testFollowTag" })
	public void testCreateStandPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser1.getEmail());
			
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
			
			//Logout
			LogoutAndClose();
			
			//Verify that news story appears in  Activity Steam -> Discover
			verifyImFollowingTagsNewsStory(testUser2, " created an activity named "+publicActivityName+".","Activities","automation",false, false);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity");
			
		}
	
		
	@Test (dependsOnMethods = { "testCreateStandPublicActivity" })
	public void testCreateStandPublicActivity_PublicEntry() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_PublicEntry");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
		
		// Create a new entry within this activity
		addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
		
		publicActivityPublicEntryTitle = publicActivityName+" Entry";
		
		//Logout
		LogoutAndClose();
		
		Reporter.log("Debug  ::>>  created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.");
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, " created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.","Activities","automation",false, true);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_PublicEntry");
		
	}
	
	@Test 	(dependsOnMethods = { "testCreateStandPublicActivity_PublicEntry" })
	public void testCreateStandPublicActivity_Comment() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_Comment");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		String description = addCommentToActivityEntry(typist, false);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, "commented on the "+publicActivityPublicEntryTitle+" entry thread in the "+publicActivityName+" activity.","Activities","automation", false, false);
		//verifyImFollowingTagsNewsStory(testUser2, description,"Activities","automation", false, true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_Comment");
			
	}
	
	@Test 	(dependsOnMethods = { "testCreateStandPublicActivity_Comment" })
	public void testCreateStandPublicActivity_EditComment() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_Comment");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		String description = editCommentToActivityEntry(typist, false);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		//verifyImFollowingTagsNewsStory(testUser2, " updated the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.","Activities","automation", false, true);
		verifyImFollowingTagsNewsStory(testUser2, description,"Activities","automation", false, true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_Comment");
			
	}
	
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_EditComment" })
	public void testCreateStandPublicActivity_PrivateEntry() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_PrivateEntry");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
		
		// Create a new entry within this activity
		publicActivityPrivateEntryTitle = addEntryToActivity_Basic("Private Entry "+genDateBasedRandVal(),typist, true);
		
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, " created the "+publicActivityPrivateEntryTitle+" entry in the "+publicActivityName+" activity.","Activities","automation", false, false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_PrivateEntry");
		
	}     

	@Test (dependsOnMethods = { "testCreateStandPublicActivity_PrivateEntry" })
	public void testCreateStandPublicActivity_ToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		// Create a new to do
		publicToDoName = addToDoToActivityTags(publicActivityName+" To-Do",CommonData.AutomationTag, typist, false, false, testUser1);
			
		Reporter.log("Debug ::>  created a to-do item named "+publicToDoName+" in the "+publicActivityName+" activity.");
		//Logout
		LogoutAndClose();
		
		
		verifyImFollowingTagsNewsStory(testUser2, " created a to-do item named "+publicToDoName+" in the "+publicActivityName+" activity.","Activities","automation",false,  true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_ToDo" })
	public void testMarkToDoAsCompleted_PublicActivity() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicActivity");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
	
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, "completed their own "+publicToDoName+" to-do item in the "+publicActivityName+" activity.","Activities","automation", false, true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PublicActivity");
			
	}
	
	@Test (dependsOnMethods = { "testMarkToDoAsCompleted_PublicActivity" })
	public void testCreateStandPublicActivity_PrivateToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_PrivateToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		// Create a new to do
		publicActivityPrivateToDoName = addToDoToActivityTags(publicActivityName+" Private To-Do", CommonData.AutomationTag, typist, true, false, testUser1);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, " created a to-do item named "+publicActivityPrivateToDoName+" in the "+publicActivityName+" activity.","Activities","automation", false, false);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_PrivateToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_PrivateToDo" })
	public void testCreateStandPublicActivity_AssignedToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		// Create a new to do
		publicAssignedToDoName = addToDoToActivityTags(publicActivityName+" Assigned To-Do", CommonData.AutomationTag, typist, false, true, testUser1);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyImFollowingTagsNewsStory(testUser2, "assigned themselves a to-do item named "+publicAssignedToDoName+" in the "+publicActivityName+" activity.","Activities","automation", false, true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}
	

	
	
	

}