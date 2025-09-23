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

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;
import com.ibm.atmn.wdbase.core.data.User;



public class FVT_StatusUpdates_RollUp extends FVT_CommonNewsMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate = "";
	private static String StatusComment = "";
	private static String StatusComment2 = "";
	

	//@Test
	public void testRollUp_SingleComment() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRollUp_SingleComment");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		/* USER 1 Post Status Update*/
		
		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();
		
		/* USER 2 Comment on Status Update*/
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("All");
		
		//Comment on Status

		StatusComment = CommentOnStatus(StatusUpdate);
	
		//Logout of Wiki
		Logout();
		
		/* USER 1 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		//
		filterBy("All");
		
		Thread.sleep(1000);
	
		String UserText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
		
		System.out.println(UserText);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		//
		filterBy("Status Updates");
		
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));

		filterBy("Profiles");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();
		
		
		/* USER 2 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		//
		
		filterBy("All");
		
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));

	
		//
		
		filterBy("Status Updates");
	
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//
		filterBy("People");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();

		
		
		System.out.println("INFO: End of News Level 2 testRollUp_SingleComment");
	}
	
	
	//@Test //(dependsOnMethods = { "testVerifyRollUp_SingleComment" })
	public void testRollUp_TwoComments() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRollUp_TwoComments");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		/* USER 1 Post Status Update*/
		
		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();
		
		/* USER 2 Comment on Status Update*/
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("All");
		
		//Comment on Status
		
		StatusComment = CommentOnStatus(StatusUpdate);
	
		//Logout of Wiki
		Logout();
		
		/* USER 3 Comment on Status Update*/
		
		Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("All");
		
		//Comment on Status
		
		StatusComment2 = FVT_NewsData.StatusComment2;
		
		StatusComment2 = CommentOnStatus(StatusUpdate, StatusComment2);
	
		
		//Logout of Wiki
		Logout();
		
		/* USER 1 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		//
		
		filterBy("All");
		
		Thread.sleep(1000);
		
		String UserText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		//
		
		filterBy("Status Updates");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		//
		filterBy("Profiles");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		//Logout of Wiki
		Logout();
		
		
		/* USER 3 Verify AS */
		
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		//
		
		filterBy("All");
		
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		//
		
		filterBy("Status Updates");
	
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		//
		//FilterBy("People");
		
		//Thread.sleep(1000);
	
		//assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones452 commented on Amy Jones450's board entry."));
		
		//assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		//assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		//assertTrue("Status Comment 2 not appearing correctly!",driver.isTextPresent(StatusComment2));
		
		
		/* USER 3 Delete Comment */
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		filterBy("All");
		
		
		deleteComment(StatusComment2);
		
		Logout();
		
		/* USER 1 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		//
		
		filterBy("All");
		
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));

	
		//
		
		filterBy("Status Updates");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//
		filterBy("Profiles");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();
		
		
		/* USER 2 Verify AS */
		
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		//
		
		filterBy("All");
		
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
	
		//
		
		filterBy("Status Updates");
	
		Thread.sleep(1000);
		
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//
		filterBy("People");
		
		Thread.sleep(1000);
	
		assertTrue("Repost text not present", UserText.equalsIgnoreCase("Amy Jones451 commented on Amy Jones450's board entry."));
		
		assertTrue("Status Update not appearing correctly!",driver.isTextPresent(StatusUpdate));
		assertTrue("Status Comment not appearing correctly!",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();
		
		

		System.out.println("INFO: End of News Level 2 testRollUp_TwoComments");
	}
	

	//@Test
	public void testRollUp_SingleLike() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRollUp_SingleLike");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		/* USER 1 Post Status Update*/
		
		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();
		
		/* USER 2 Comment on Status Update*/
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("All");
		
		//Like Status
		
		//click on status update to display EE
		driver.getSingleElement("css=div.lotusPostContent div.lotusPostAction:contains("+StatusUpdate+")").click();
		
		//verify that EE id displayed
		assertTrue("FAIL: EE not visible", driver.isElementPresent(FVT_NewsObjects.EE_Header));
	
		//Swtich to Embedded Experience
		switchToEE();
		
		//Click on the Like button
		Like();
		
		//Check that Likes = 1
		assertTrue("FAIL: Number of likes not equal to '1'", driver.getSingleElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("1") == 0);
		
		//Swtich from Embedded Experience
		switchFromEE();
	
		//Logout of Wiki
		Logout();
		
		/* USER 1 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		//
		
		filterBy("All");
		
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		
	
		//
		
		filterBy("Status Updates");
		
		Thread.sleep(2000);
		
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		
		//
		filterBy("Profiles");
		
		Thread.sleep(2000);
	
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();
		
		/* USER 2 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		//
		
		filterBy("All");
		
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));

	
		//
		
		filterBy("Status Updates");
	
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		
		
		//
		filterBy("People");
	
		//assertTrue("ERROR",driver.isTextPresent("Amy Jones451 commented on Amy Jones450's board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testRollUp_SingleLike");
	}
	
	@Test
	public void testRollUp_FileComment() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRollUp_FileComment");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		/* USER 1 Post Status Update*/
		
		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_File+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate_FileAttached(StatusUpdate, "Desert.jpg");
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		//Verify that update appears in AS
		//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();
		
		/* USER 2 Comment on Status Update*/
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("All");
		
		//Comment on Status

		StatusComment = CommentOnStatus(StatusUpdate);
	
		//Logout of Wiki
		Logout();
		
		/* USER 1 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.Discover);
		//
		filterBy("All");
		
		Thread.sleep(1000);
	
		//assertTrue("ERROR",driver.isTextPresent("commented on"));
		//assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));
		//
		filterBy("Status Updates");
		
		Thread.sleep(1000);
		
		assertTrue("ERROR",driver.isTextPresent("commented on"));
		assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));

		filterBy("People");
		
		Thread.sleep(1000);
	
		assertTrue("ERROR",driver.isTextPresent("commented on"));
		assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();
		
		
		/* USER 2 Verify AS */
		
		//Login as a user (ie. Amy Jones66)
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		//
		
		filterBy("All");
		
		assertTrue("ERROR",driver.isTextPresent("commented on"));
		assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));
	
		//
		
		filterBy("Status Updates");
	
		
		assertTrue("ERROR",driver.isTextPresent("commented on"));
		assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));
		
		//
		filterBy("People");
	
		assertTrue("ERROR",driver.isTextPresent("commented on"));
		assertTrue("ERROR",driver.isTextPresent("board entry."));
		assertTrue("ERROR",driver.isTextPresent(StatusUpdate));
		assertTrue("ERROR",driver.isTextPresent(StatusComment));
		
		//Logout of Wiki
		Logout();

		
		
		System.out.println("INFO: End of News Level 2 testRollUp_FileComment");
	}
	
}
