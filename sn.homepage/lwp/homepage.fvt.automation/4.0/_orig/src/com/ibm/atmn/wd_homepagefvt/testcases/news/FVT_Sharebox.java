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


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.news.FVT_CommonNewsMethods;
import com.ibm.atmn.wdbase.core.data.User;


public class FVT_Sharebox extends FVT_CommonNewsMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	
	
	private String StatusUpdate = "";
	

	
	//@Test
	public void testVerifyShareboxAppears() throws Exception {

		System.out.println("INFO: Start of News Level 2 testVerifyShareboxAppears");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		//Login(testUser1);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		clickLink(FVT_NewsObjects.ActionRequired);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		clickLink(FVT_NewsObjects.MyNotifications);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		clickLink(FVT_NewsObjects.Discover);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testVerifyShareboxAppears");
	}
	
	//@Test
	public void testPostStatus_AllUpdatesSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatus_AllUpdatesSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser2 = userAllocator.getUser();
		//Login(testUser2);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.AllUpdates);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatus_AllUpdatesSharebox");
	}
	
	//@Test
	public void testPostStatusHashtag_StatusUpdatesSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusHashtag_StatusUpdatesSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser3 = userAllocator.getUser();
		//Login(testUser3);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_Hashtag+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		filterBy_StatusUpdates("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));	
		
		filterBy_StatusUpdates("My Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));	
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusHashtag_StatusUpdatesSharebox");
	}
	
	//@Test
	public void testPostStatusSpecial_DiscoverSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusSpecial_DiscoverSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser4 = userAllocator.getUser();
		//Login(testUser4);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_Special+CommonMethods.genDateBasedRandVal();
		
		clickLink(FVT_NewsObjects.Discover);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("People");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusSpecial_DiscoverSharebox");
	}

	//@Test
	public void testPostStatus1000_MyNotificationsSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatus1000_MyNotificationsSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser5 = userAllocator.getUser();
		//Login(testUser5);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_1000;
		
		clickLink(FVT_NewsObjects.MyNotifications);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("People");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatus1000_MyNotificationsSharebox");
	}
	
	@Test
	public void testPostStatusGreater1000_ActionRequiredSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusGreater1000_ActionRequiredSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser6 = userAllocator.getUser();
		//Login(testUser6);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_Greater1000;
		
		clickLink(FVT_NewsObjects.ActionRequired);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(StatusUpdate);

		//Verify post not displaying
		assertTrue("FAIL: Post visible", !driver.getSingleElement(FVT_NewsObjects.PostStatus).isVisible());	
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusGreater1000_ActionRequiredSharebox");
	}
	
	//@Test
	public void testPostStatusSecurity_SavedSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusSecurity_SavedSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser7 = userAllocator.getUser();
		//Login(testUser7);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_Security;
		
		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusSecurity_SavedSharebox");
	}
	
	//@Test
	public void testPostStatusLink_DiscoverSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusSpecial_DiscoverSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser8 = userAllocator.getUser();
		//Login(testUser8);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus_Link;
		
		clickLink(FVT_NewsObjects.Discover);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		statusUpdate(StatusUpdate);
		
		String ExpectedValue = "The message was successfully posted.";
		assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		filterBy("People");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusSpecial_DiscoverSharebox");
	}
	
	//@Test
	public void testPostStatus_ResetSharebox() throws Exception {

		System.out.println("INFO: Start of News Level 2 testPostStatusSpecial_DiscoverSharebox");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser8 = userAllocator.getUser();
		//Login(testUser8);
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		clickLink(FVT_NewsObjects.Discover);
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
		//Enter and Save a status update
		driver.getSingleElement(FVT_NewsObjects.EnterStatusUpdate).type(StatusUpdate);
		
		clickLink(FVT_NewsObjects.ResetStatus);
		
		filterBy("All");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", !driver.isTextPresent(StatusUpdate));
		
		filterBy("Status Updates");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", !driver.isTextPresent(StatusUpdate));
		
		filterBy("People");
		
		//Verify that update appears in AS
		assertTrue("FAIL: Status Update not appearing in Activty Stream", !driver.isTextPresent(StatusUpdate));
		
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testPostStatusSpecial_DiscoverSharebox");
	}
	
}
