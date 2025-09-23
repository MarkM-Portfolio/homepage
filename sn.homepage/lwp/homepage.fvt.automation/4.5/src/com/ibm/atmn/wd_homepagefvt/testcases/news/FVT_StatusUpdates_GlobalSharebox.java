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
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.waffle.extensions.user.User;


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
		testUser1 = userAllocator.getUser(this);
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		clickLink(FVT_NewsObjects.Share);
				
		sleep(1000);
		
		// Needed to switch frame focus to share-box frame
		//wd.switchTo().frame(1);
		switchToEE();
		
		//assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate_ShareFrame));
		//Reporter.log("Found Status = "+driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));	
		smartSleep(FVT_NewsObjects.EnterStatusUpdate_ShareFrame);
		
		
		StatusUpdate = statusUpdate_GlobalSharebox(StatusUpdate);

		//driver.getFirstElement(FVT_NewsObjects.StatusPostBtn).click();
		
		// Needed to switch frame focus back to main window
		//wd.switchTo().defaultContent();
		switchFromEE();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);

		sleep(1000);
		
		filterBy_StatusUpdates("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		
		String StatusUpdatePost = FVT_NewsObjects.StatusUpdatePost.replace("_Replace_", StatusUpdate);
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//	builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateGlobalShareboxAll");
	}
	
	
	@Test
	public void testStatusUpdateGlobalShareboxMyUpdates() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateGlobalShareboxMyUpdates");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser(this);
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("My Updates");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = FVT_NewsObjects.StatusUpdatePost.replace("_Replace_", StatusUpdate);
		
		builder.moveToElement((WebElement) driver.getFirstElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
		
		//Stop Following Poster
		
		//builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.StopFollowing).getBackingObject()).click().build().perform();
		
		//Verify that stop following dialog appears
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(FVT_NewsData.StopFollowingDialog));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateGlobalShareboxMyUpdates");
	}
	
	
}
