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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.imfollowing;




import java.util.ArrayList;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;



public class FVT_ImFollowing_Private_Activities extends FVT_ActivitiesMethods {

	FormInputHandler typist;
	private ArrayList<String> assertList;
	
	private static String privateActivityName = "";
	private static String privateEntryTitle = "";
	private static String privateToDoName = "";
	
	
	@Test 
	public void testCreateStandPrivateActivity() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity");
	
		typist = new FormInputHandler(driver);
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Created a new activity 
		privateActivityName = startAnActivity(FVT_ActivitiesData.Start_Private_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,CommonData.IC_LDAP_UserName451_Typeahead);
		
		//Go back to activities list
		clickLink(FVT_ActivitiesObjects.Activities_Tab);

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
		Logout();
		
		//driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		//VerifyNewsStory(" created a activity named "+privateActivityName+".","I'm Following","Activities", false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity");
		
	}
		
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity" })
	public void testFollowPrivateActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testFollowPrivateActivity");
				
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
						
			clickLink("link="+privateActivityName);
			
			//clickLink(FVT_ActivitiesObjects.FollowingActions);
			
			clickLink(FVT_ActivitiesObjects.FollowStandActivity);
			
			clickLink(FVT_HomepageObjects.Home);
			
			verifyNewsStory(" created a private activity named "+privateActivityName+".","I'm Following","Activities", false);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testFollowPrivateActivity");
			
		}
	
	
	@Test (dependsOnMethods = { "testFollowPrivateActivity" })
	public void testCreateStandPrivateActivity_PrivateEntry() throws Exception {
				
			
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity_PrivateEntry");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
			
		// Create a new entry within this activity
		privateEntryTitle = addEntryToActivity_Basic(privateActivityName+" Entry",typist, false);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory(" created the "+privateEntryTitle+" entry in the "+privateActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity_PrivateEntry");
			
	}
		
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_PrivateEntry" })
	public void testCreateStandPrivateActivity_Comment() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity_Comment");
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
		
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory("commented on the "+privateEntryTitle+" entry thread in the "+privateActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity_Comment");
			
	}
	
	@Test 	(dependsOnMethods = { "testCreateStandPrivateActivity_Comment" })
	public void testCreateStandPrivateActivity_EditComment() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity_EditComment");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		editCommentToActivityEntry(typist, false);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory(" updated the "+privateEntryTitle+" entry thread in the "+privateActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity_EditComment");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_EditComment" })
	public void testCreateStandPrivateActivity_ToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
	
		// Create a new to do
		privateToDoName = addToDoToActivity(privateActivityName+" To-Do",typist, false, false);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory(" created a to-do item named "+privateToDoName+" in the "+privateActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_ToDo" })
	public void testMarkToDoAsCompleted_PrivateActivity() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateActivity");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + privateActivityName);
	
		//Open To Do Items
		clickLink(FVT_ActivitiesObjects.ToDoItems);
		
		// Mark to do as complete	
		//mouseOverAndWait(FVT_ActivitiesObjects.MarkToDoCompleted);
		clickLink(FVT_ActivitiesObjects.MarkToDoCompleted);
			
		//Logout
		Logout();
		
		driver.close();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory("completed their own "+privateToDoName+" to-do item in the "+privateActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkToDoAsCompleted_PrivateActivity");
			
	}
	
	

}