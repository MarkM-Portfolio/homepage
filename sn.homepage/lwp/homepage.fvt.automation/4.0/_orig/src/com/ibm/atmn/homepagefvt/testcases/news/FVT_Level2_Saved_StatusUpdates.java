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

package com.ibm.atmn.homepagefvt.testcases.news;



import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_Saved_StatusUpdates extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	private static String StatusUpdate1 = "";
	private static String StatusUpdate2 = "";
	private static String StatusUpdate3 = "";
	private static String StatusUpdate4 = "";
	private static String StatusComment1 = "";
	private static String StatusComment2 = "";
	private static String StatusComment3 = "";

	
	@Test
	public void testSavedStatusUpdatesPart1 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart1");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate1 = StatusUpdate();
	
		//Save the status update news story
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate1+"')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the Save this button has disappeared from news story
		clickLink(FVT_HomepageObjects.Home);

		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate1+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that news story appears in saved
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(StatusUpdate1));
		
		//Delete Story
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate1+"')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that save this button is available again
		clickLink(FVT_HomepageObjects.Discover);
		
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate1+"')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout of homepage
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart1");
	}
	
	@Test (dependsOnMethods = { "testSavedStatusUpdatesPart1" })
	public void testSavedStatusUpdatesPart2 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart2");	
		
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate2 = StatusUpdate();
		
		//Logout of profiles
		Logout();
			
		//User 2 comment on status update 
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		clickLink("css=div.lotusPostContent:contains('"+StatusUpdate2+"')");
		
		StatusComment1 = CommentOnStatus_EE(StatusUpdate2);
		
		//Logout of profiles
		Logout();	
		
		//User 1 save the comment news story
		
		//Load News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Go To Home
		clickLink(FVT_HomepageObjects.Home);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.ForMe);
		
		//Filter by People
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusComment1+"')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that story was saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.ForMe);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusComment1+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that news story appears in saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(StatusUpdate2));
		
		//Delete Story
		
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate2+"')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that save this link is available again in For Me
		
		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("Status Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusComment1+"')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart2");
	}
	
	@Test 
	public void testSavedStatusUpdatesPart3 () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart3");	
		
		
		//User 1 follow User 2
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Follow Person
		FollowPerson(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//User 2 update status
		
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate3 = StatusUpdate();
		
		//Logout of profiles
		Logout();
		
		//Check that story appears in User 1's I'm following and click save
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		//Filter by People
		FilterBy("Status Updates");
		
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that story is saved 
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate3+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		//Go to Saved and check story appears there
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(StatusUpdate3));
		
		//Comment on status update
		
		StatusComment2 = CommentOnStatus(StatusUpdate3);
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(StatusComment2));
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart3");
	}
	
	@Test 
	public void testSavedStatusUpdatesPart4 () throws Exception {
		
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedStatusUpdatesPart4");	
		
		//User 1 follow User 2
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Follow Person
		FollowPerson(CommonData.IC_LDAP_Username_Fullname452);
		
		//Logout of profiles
		Logout();
		
		//User 2 updates status
		
		//Load the component
		LoadComponent(CommonObjects.ComponentNews);
			
		//Login as a user (ie. Amy Jones452)
		Login(CommonData.IC_LDAP_Username452, CommonData.IC_LDAP_Password452);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Update Status
		StatusUpdate4 = StatusUpdate();
		
		//Logout of profiles
		Logout();
			
		//Save news story
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		//Filter by People
		FilterBy("Status Updates");
		
		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that news story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate4+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		//Check that news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(StatusUpdate4));
		
		//Comment on status update
		
		StatusComment3 = CancelComment(StatusUpdate4);
		
		assertTrue("Fail: Cancel did not work!!", !sel.isTextPresent(StatusComment3));
		
		//Logout of profiles
		Logout();
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedStatusUpdatesPart4");
	}
	
	
}
