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

package com.ibm.atmn.wd_homepagefvt.testcases.activities.actionrequired;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;

public class FVT_ActionRequired_00016 extends FVT_ActivitiesMethods {

	FormInputHandler typist;
	private ArrayList<String> assertList;
	
	private static String PrivateActivityName = "";
	private static String PrivateAssignedToDoName = "";
	private static String PublicActivityName = "";
	private static String PublicAssignedToDoName = "";
	
	private User testUser1;
	private User testUser2;
		
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
	public void testCreateStandPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity");
		
			// Users allocated within the testcase as there is a dependency 
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser1);
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			PublicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
			//Go back to activities list
			clickLink(FVT_ActivitiesObjects.Activities_Tab);
	
			//Expand top activity (activity created above)
			clickLink(FVT_ActivitiesObjects.ActivityHome_ExpandActivityDetails_TopActivity);
			
			//Check all 'start activity' form entries were saved and are now displayed
			assertList = typist.getAssertList();
			assertAllTextPresent(assertList);
			typist.dumpList();
			
			//Go back to the main page of the activity created above
			clickLink("link=" + PublicActivityName);
			
			//expand activity description
			clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
			
			changeActivityAccess();
			
			//Logout
			Logout();
		
			System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity");
			
		}
	
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity" })
	public void testCreateStandPublicActivity_AssignedToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);	
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + PublicActivityName);
	
		// Create a new to do
		PublicAssignedToDoName = addToDoToActivity(PublicActivityName+" Assigned To-Do",typist, false, true, testUser2);
			
		//Logout
		Logout();
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}


	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_AssignedToDo"})
	public void testVerifyBadgeIsPresent() throws Exception {
			
			
			System.out.println("INFO: Start of Action Required FVT_Level_2 testVerifyBadgeIsPresent");
		
			// Users allocated within the testcase as there is a dependency 
			//testUser2 = userAllocator.getUser();
		
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			Login(testUser2);
			
			//Check that badge is not visible in all views
			verifyActionRequiredBadgeVisible();
			
			//Remove one Action Required story
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			String ActionRequiredStory = "css=div.lotusPostContent:contains('assigned you a to-do item named "+PublicAssignedToDoName+" in the "+PublicActivityName+" activity.')";
			
			//builder.moveToElement((WebElement) BlogsStory).moveToElement((WebElement) SaveThis).click().perform();
			builder.moveToElement((WebElement) driver.getFirstElement(ActionRequiredStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteActionRequiredStory).getBackingObject()).click().build().perform();
			
			assertTrue("FAIL: Text not present", driver.isTextPresent("Do you want to remove this entry from action required?"));
			
			clickLink(FVT_NewsObjects.RemoveActionRequiredStory);
			
			assertTrue("FAIL: Text not present", driver.isTextPresent("Entry has been successfully removed from your Action Required list."));
		
			//Logout
			Logout();
			
			System.out.println("INFO: End of Action Required FVT_Level_2 testVerifyBadgeIsPresent");
			
		}

}