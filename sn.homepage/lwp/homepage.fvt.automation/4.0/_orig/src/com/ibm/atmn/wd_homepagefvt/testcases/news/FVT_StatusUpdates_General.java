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


public class FVT_StatusUpdates_General extends FVT_CommunitiesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate = "";
	private static String PublicCommunity = "";
	
	

	//@Test 
	public void testStatusUpdateAll() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateAll");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
				
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_HomepageObjects.SaveThis_StatusUpdates).getBackingObject()).click().build().perform();
				
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateAll");
	}
	
	//@Test 
	public void testRemoveComment() throws Exception {

		System.out.println("INFO: Start of News Level 2 testStatusUpdateAll");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
				
		//Comment on Status
		
		CommentOnStatus(StatusUpdate);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getSingleElement(FVT_NewsObjects.DeleteComment).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveComment);
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testStatusUpdateAll");
	}
	
	@Test 
	public void testRemoveStatus() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRemoveStatus");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser();
		Login(testUser1);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
				
		clickLink(FVT_HomepageObjects.Refresh_StatusUpdates);
		
		//Save status
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
		
		builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.DeleteStatus).getBackingObject()).click().build().perform();
		
		clickLink(FVT_NewsObjects.RemoveStatus);
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testRemoveStatus");
	}
	
	
	
}
