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

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;


import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
//import com.ibm.atmn.wdbase.core.data.User;



public class FVT_ImFollowing_Miscellaneous_Activities extends FVT_ActivitiesMethods {

	User testUser1;
	User testUser2;
	User testUser3;
	
	FormInputHandler typist;
	private ArrayList<String> assertList;
	

	private static String publicActivityName = "";
	private static String publicActivityPublicEntryTitle = "";
	private static String publicActivityPublicEntryTitle2 = "";
	
		
		
	@Test
	public void testLoginUsers() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testLoginUsers");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser2);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(testUser3);
		
		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testLoginUsers");
		
	}
	
	@Test (dependsOnMethods = { "testLoginUsers" })
	public void testCreateStandPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			
			assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
			// Created a new activity 
			
			publicActivityName = startAnActivity(FVT_ActivitiesData.Start_Public_Activity_InputText_Name_Data+genDateBasedRandVal(),typist,testUser2.getEmail());
			
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
			Logout();
			
			//Verify that news story appears in  Activity Steam -> Discover
			//VerifyNewsStory(" created a public activity named "+publicActivityName+".","Discover","Activities", true);
			
			System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity");
			
		}
	
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity" })
	public void testUser1FollowPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testUser1FollowPublicActivity");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			clickLink(FVT_ActivitiesObjects.ActiveActivities);
			
			clickLink("link="+publicActivityName);
			
			//clickLink(FVT_ActivitiesObjects.FollowingActions);
			sleep(1000);
			
			clickLink(FVT_ActivitiesObjects.FollowStandActivity);
						
			System.out.println("INFO: End of Activities FVT_Level_2 testUser1FollowPublicActivity");
			
		}
	
	@Test (dependsOnMethods = { "testUser1FollowPublicActivity" })
	public void testUser2FollowPublicActivity() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testUser2FollowPublicActivity");
			
			typist = new FormInputHandler(driver);
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentActivities);
				
			Login(testUser3);
			//Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
			
			clickLink(FVT_ActivitiesObjects.ActiveActivities);
			
			clickLink("link="+publicActivityName);
			
			sleep(1000);
			
			clickLink(FVT_ActivitiesObjects.FollowStandActivity);
						
			System.out.println("INFO: End of Activities FVT_Level_2 testUser2FollowPublicActivity");
			
		}
	
	
	@Test (dependsOnMethods = { "testUser2FollowPublicActivity" })
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
		Logout();
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_PublicEntry");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_PublicEntry" })
	public void testUnfollowFromNewsFeed() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testUnfollowFromNewsFeed");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			clickLink(FVT_HomepageObjects.Updates);
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Activities");
			
			if(!driver.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.")){
				
				sleep(60000);
				
				clickLink(FVT_HomepageObjects.ImFollowing);

				filterBy("Activities");
			}
			
			assertTrue("Activities entry story not appearing in Im Following View.", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			String ActivitiesStory = "css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')";
			
			Reporter.log("ActivitiesStory :>" + ActivitiesStory);
			
			sleep(2000);
			//builder.moveToElement((WebElement) driver.getSingleElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
			
			// Finds the most recent activity and hovers over it
			WebElement commentBox = wd.findElement(By.className("lotusPostContent"));
			Actions hoverOverCommentBox = builder.moveToElement(commentBox);
			hoverOverCommentBox.perform();
			
			// Clicks on the stop following link when its made visible
			WebElement stopFollowing = wd.findElement(By.partialLinkText("Stop Following"));
			stopFollowing.click();
			
			// Saves the changes
			driver.getFirstElement(FVT_ActivitiesObjects.SaveBtn).click();
			
			assertTrue("Stop Following link still there!", driver.isElementPresent(FVT_ActivitiesObjects.StopFollowingCheckBox));
			
			System.out.println("INFO: End of Activities FVT_Level_2 testUnfollowFromNewsFeed");
			
		}
	
	
	@Test (dependsOnMethods = { "testUnfollowFromNewsFeed" })
	public void testCreateStandPublicActivity_SecondPublicEntry() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_SecondPublicEntry");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentActivities);
			
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
		
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
		
		// Create a new entry within this activity
		addEntryToActivity_Basic(publicActivityName+" Entry",typist, false);
		publicActivityPublicEntryTitle2 = publicActivityName+" Entry";
		
		//Logout
		Logout();
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_SecondPublicEntry");
		
	}
	
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_SecondPublicEntry" })
	public void testVerifyNoActivityStoryInNF() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testVerifyNoActivityStoryInNF");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			clickLink(FVT_HomepageObjects.Updates);
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Activities");

			assertTrue("Activities entry story not appearing in Im Following View.", !driver.isTextPresent(" created the "+publicActivityPublicEntryTitle2+genDateBasedRandVal()+" entry in the "+publicActivityName+" activity."));
			
			System.out.println("INFO: End of Activities FVT_Level_2 testVerifyNoActivityStoryInNF");
			
		}
	
	@Test (dependsOnMethods = { "testVerifyNoActivityStoryInNF" })
	public void testChangeActivityToPrivateAccess() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testChangeActivityToPublicAccess");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		//expand activity description
		clickLink(FVT_ActivitiesObjects.ActivityOutline_More_ExpandDescription);
		
		//Change from Private to Public
		changeActivityAccess("Private");
			
		//Logout
		Logout();
		
			
		System.out.println("INFO: End of Activities FVT_Level_2 testChangeActivityToPublicAccess");
			
	}
	
	@Test (dependsOnMethods = { "testCreateStandPublicActivity_SecondPublicEntry" })
	public void testVerifyNoActivityStoryInNF_SecondUser() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testVerifyNoActivityStoryInNF");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			Login(testUser3);
			//Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
			clickLink(FVT_HomepageObjects.Updates);
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Activities");
			
			assertTrue("Activities entry story appearing in Im Following View.", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName+" activity."));
			
			
			System.out.println("INFO: End of Activities FVT_Level_2 testVerifyNoActivityStoryInNF");
			
		}
	
	

}