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

package com.ibm.atmn.wd_homepagefvt.testcases.news.actionrequired;

import static org.testng.AssertJUnit.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;

public class FVT_ActionRequired_00011 extends FVT_ActivitiesMethods{
	
	User testUser1;
	User testUser2;
	
	FormInputHandler typist;	
	private static String PrivateActivityName = "";	
	private static String publicActivityName = "";
	private static String totalActions = null;
	
	
	@Test
	public void testCreateStandPrivateActivity() throws Exception {
		
		//testUser1 = userAllocator.getUser(this);
		//testUser2 = userAllocator.getUser(this);

		totalActions = countActionsRequired(testUser2, 2);
	
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPrivateActivity");
		
		typist = new FormInputHandler(driver);
			
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Created a new activity 
		PrivateActivityName = startAnActivity(FVT_ActivitiesData.Start_Private_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
		//Logout
		LogoutAndClose();
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPrivateActivity");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity" })
	public void testCreateStandPrivateActivity_ToDo() throws Exception {
				
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login
		Login(testUser1);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + PrivateActivityName);
	
		// Create a new to do
		addToDoToActivity(PrivateActivityName+" To-Do",typist, false, true, testUser2);
			
		//Logout
		LogoutAndClose();

		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_ToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPrivateActivity_ToDo"})
	public void testCreateStandPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
					
			Login(testUser1);

			// Created a new activity 
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
			//Go back to activities list
			clickLink(FVT_ActivitiesObjects.Activities_Tab);
	
			sleep(1000);
			
			//Expand top activity (activity created above)
			clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);

			//Go back to the main page of the activity created above
			clickLink("link=" + publicActivityName);
			
			//expand activity description
			clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
			
			changeActivityAccess();
			
			//Logout
			LogoutAndClose();
						
			System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity");
			
		}
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity"})
	public void testCreateStandPublicActivity_AssignedToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);

		// login
		Login(testUser1);
			
		//verify Activities screen is open
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		// Create a new to do
		addToDoToActivity(publicActivityName+" Assigned To-Do", typist, false, true, testUser2);
			
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}
	
	@Test (dependsOnMethods = {"testCreateStandPublicActivity_AssignedToDo"})
	public void testActionRequiredBadgeCount(){
		
		System.out.println("INFO: Start of testActionRequiredBadgeCount");
		
		//Load Component News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login User
		Login(testUser2);
		
		//Check that actions required badge contains correct number
		assertTrue("FAIL: Number of Actions Required not correct", driver.getFirstElement(FVT_HomepageObjects.ActionRequiredBadge).getText().compareToIgnoreCase(totalActions) == 0);
		
		//Click on Getting Started
		clickLink(FVT_HomepageObjects.GettingStarted);
		
		//Check Action required badge is present on Getting started Page
		assertTrue("FAIL: Number of Actions Required not correct", driver.getFirstElement(FVT_HomepageObjects.ActionRequiredBadge).getText().compareToIgnoreCase(totalActions) == 0);
		
		//Click on Getting Started
		clickLink(FVT_HomepageObjects.MyPage);
				
		//Check Action required badge is present on Getting started Page
		assertTrue("FAIL: Number of Actions Required not correct", driver.getFirstElement(FVT_HomepageObjects.ActionRequiredBadge).getText().compareToIgnoreCase(totalActions) == 0);
				
		//Logout
		LogoutAndClose();		
		
		System.out.println("INFO: End of testActionRequiredBadgeCount");
	}
	
	@Test (dependsOnMethods = {"testActionRequiredBadgeCount"})
	public void testActionRequired_deleteEvent(){
		
		System.out.println("INFO: Start of testActionRequiredBadgeCount after Delete");
		
		//Load Component News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login User
		Login(testUser2);
		
		//Open
		clickLink(FVT_HomepageObjects.ActionRequiredEvents);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);

		//Click on comment button belonging to relevant Status Update
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.ActionRequiredNewsStory).getBackingObject()).moveToElement((WebElement) 
		driver.getFirstElement(FVT_HomepageObjects.ActionRequiredDelete).getBackingObject()).click().build().perform();	
		
		//Delete an Action Required Event
		smartSleep(FVT_HomepageObjects.RemoveActionRequired);
		clickLink(FVT_HomepageObjects.RemoveActionRequired);
		
		//Verify News Story was removed
		assertTrue("Fail - Action Required News Story not removed", driver.isTextPresent("Entry has been successfully removed from your Action Required list."));
		
		//Convert Actions string to int and remove 1
		int original = Integer.parseInt(totalActions);
		original = original -1;
		
		//Convert new value back to string
		String newValue = Integer.toString(original);
		
		//Check that actions required badge contains correct number
		assertTrue("FAIL: Number of Actions Required not correct", driver.getFirstElement("xpath=//*[@id='lconn_homepage_as_badge_ActionRequiredBadge_0']").getText().compareToIgnoreCase(newValue) == 0);
		
		//logout
		LogoutAndClose();
				
		System.out.println("INFO: End of testActionRequiredBadgeCount after Delete");
	}
	
	
}