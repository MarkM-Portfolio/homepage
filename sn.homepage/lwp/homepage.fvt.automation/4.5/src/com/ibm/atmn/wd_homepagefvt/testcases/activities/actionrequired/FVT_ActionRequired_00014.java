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

import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;

public class FVT_ActionRequired_00014 extends FVT_ActivitiesMethods {

	FormInputHandler typist;
	private ArrayList<String> assertList;
	
	private static String PublicActivityName = "";
	private static String PublicAssignedToDoName = "";
	
	private User testUser1;
	private User testUser2;
	
	@Test 
	public void testCreateStandPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity");
		
			// Users allocated within the testcase as there is a dependency 
			
			testUser1 = userAllocator.getUser(this);
			testUser2 = userAllocator.getUser(this);
			
			Reporter.log(testUser1.getDisplayName());
			Reporter.log(testUser2.getDisplayName());
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			//Login
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
		PublicAssignedToDoName = addToDoToActivity(PublicActivityName+" Assigned To-Do",typist, false, true, testUser1);
			
		//Logout
		LogoutAndClose();
		
		//Create newsstory string
		String ActionNewsStory = replaceNewsStory(NewsStoryData.ASSIGNED_YOU_TODO, PublicAssignedToDoName, PublicActivityName, testUser1.getDisplayName());
		
		//verify newsstory
		//verifyNewsStory(testUser1, ActionNewsStory, FVT_NewsData.ACTION_REQUIRED_VIEW, null, true);
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(testUser1);
		
		clickLink("css=a#_actionRequired");
		
		System.out.println(ActionNewsStory);
		
		assertTrue("Fail - NewsStory not visible", driver.isTextPresent(ActionNewsStory));
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_AssignedToDo" })
	public void testDelete_AssignedToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login(testUser1);	
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + PublicActivityName);
	
		// Click on To-DO
		driver.getFirstElement("css=[id^='activityPageNodeContainer'][id$='_miniCheckBox']").click();
			
		//Logout
		LogoutAndClose();

		//Create newsstory string
		String ActionNewsStory = replaceNewsStory(NewsStoryData.ASSIGNED_YOU_TODO, PublicAssignedToDoName, PublicActivityName, testUser1.getDisplayName());
		
		//verify newsstory
		verifyNewsStory(testUser1, ActionNewsStory, FVT_NewsData.ACTION_REQUIRED_VIEW, null, false);
		
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}
}