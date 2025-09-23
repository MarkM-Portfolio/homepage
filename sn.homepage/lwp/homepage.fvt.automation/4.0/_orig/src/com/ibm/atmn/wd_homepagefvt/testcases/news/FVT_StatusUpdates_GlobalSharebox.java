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

package com.ibm.atmn.wd_homepagefvt.testcases.news;



import static org.testng.AssertJUnit.*;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.wdbase.core.data.User;


public class FVT_StatusUpdates_GlobalSharebox extends FVT_CommunitiesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	
	private static String StatusUpdate = "";
	


	@Test 
	public void testStatusUpdateGlobalShareboxAll() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateGlobalShareboxAll");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		clickLink(FVT_NewsObjects.Share);
		
		//builder.moveToElement((WebElement) driver.getSingleElement(FVT_NewsObjects.Share).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_NewsObjects.ShareSomething).getBackingObject()).click().build().perform();
		//builder.moveToElement((WebElement) driver.getSingleElement(FVT_NewsObjects.ShareSomething).getBackingObject()).click().build().perform();
		
	
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateGlobalShareboxAll");
	}
	
	
	@Test (dependsOnMethods = { "testStatusUpdateAll" })
	public void testStatusUpdateGlobalShareboxMyUpdates() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateGlobalShareboxMyUpdates");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("My Updates");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateGlobalShareboxMyUpdates");
	}
	
	
}
