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

package com.ibm.atmn.wd_homepagefvt.testcases.activities;


import static org.testng.AssertJUnit.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.server.SeleniumServer;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;
import com.thoughtworks.selenium.Selenium;



public class TestOfFVT_Level2_Activities extends FVT_ActivitiesMethods {
	
	FormInputHandler typist;
	private ArrayList<String> assertList;

	private static String PrivateCommunity = "Private Level 2 FVT Community 2341345121";	
	private static String PrivateCommunityActivity = "FVT Automation Test Activity 2341347211";	
	private static String PrivateActivityEntry = "FVT Automation Test Activity 2341347211 Entry";
	private static String PrivateActivityToDo= "";
	private static String publicActivityName = "FVT Level2 Public Activity 0251008361";
	private static String publicAssignedToDoName;

	/*
	@Test 
	public void testCreateStandPublicActivity_AssignedToDo() throws Exception {
		
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
		
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		Login("sadams3","adams3");
		
		User testUser1 = 
			
		assertTrue("Fail: Activities is not open", driver.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
	
		// Create a new to do
		publicAssignedToDoName = addToDoToActivity(publicActivityName+" Assigned To-Do",typist, false, true, testUser1);
			
		//Logout
		LogoutAndClose();
		
		//Verify that news story appears in  Activity Steam -> Discover
		verifyNewsStory(testUser2, "assigned themselves a to-do item named "+publicAssignedToDoName+" in the "+publicActivityName+" activity.","I'm Following","Activities", true);
			
		System.out.println("INFO: End of Activities FVT_Level_2 testCreateStandPublicActivity_AssignedToDo");
			
	}
	
	
	@Test 
	public void testUnfollowFromNewsFeed() throws Exception {
			
			
			System.out.println("INFO: Start of Activities FVT_Level_2 testUnfollowFromNewsFeed");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentHomepage);
				
			Login("sadams8","adams8");
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			String publicActivityPublicEntryTitle = "FVT Level2 Public Activity 0301758121 Entry";
			
			clickLink(FVT_HomepageObjects.ImFollowing);

			filterBy("Activities");
			
			Reporter.log("::>> created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.");
			
			assertTrue("Activities entry story not appearing in Im Following View.", driver.isTextPresent("created the FVT Level2 Public Activity 0301758121 Entry entry in the FVT Level2 Public Activity 0301758121 activity."));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			WebElement commentBox = wd.findElement(By.className("lotusPostContent"));
			
			Actions builder = new Actions(wd);
			Actions hoverOverCommentBox = builder.moveToElement(commentBox);
			
			//String ActivitiesStory = "css=div.lotusPostContent:contains('created the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')";
			//String ActivitiesStory = "css=[dojoattachpoint='newsItemNode']:nth(0)";
			
			//Reporter.log("ActivitiesStory :>" + ActivitiesStory);

			hoverOverCommentBox.perform();
			WebElement stopFollowing = wd.findElement(By.partialLinkText("Stop Following"));
			stopFollowing.click();
			//List<WebElement> e = wd.findElements(arg0) driver.getElements("css=[class='lotusPostContent']");
			
			
			
		//	for(Element x:e){
			//	Reporter.log("Debug Links ::>> "+x.getTagName()+" :>  " + x.getText());
			//	builder.moveToElement((WebElement)x).wait(5);
				//builder.click();
				//break;
			//}
			
			//driver.isElementPresent("Is ActivitiesStory present :>  "+ActivitiesStory);
			//driver.isElementPresent("Is StopFollowing present :>  "+FVT_HomepageObjects.StopFollowing);
			
			sleep(2000);
			//getWhenVisible(By.cssSelector(a:contains('Stop Following'):nth(0), 10);
			//builder.moveToElement((WebElement) driver.getSingleElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
			//builder.moveToElement((WebElement) driver.getSingleElement(ActivitiesStory).getBackingObject()).moveToElement((WebElement) driver.getFirstElement("css= li#com_ibm_social_as_item_RollupNewsItem_0 div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostMore ul.lotusInlinelist li a:nth(1)").getBackingObject()).click().build().perform();
			
			driver.getFirstElement(FVT_ActivitiesObjects.SaveBtn).click();
			
			assertTrue("Stop Following link still there!", driver.isElementPresent(FVT_ActivitiesObjects.StopFollowingCheckBox));
			
			System.out.println("INFO: End of Activities FVT_Level_2 testUnfollowFromNewsFeed");
			
		}
		*/
}
	
	
