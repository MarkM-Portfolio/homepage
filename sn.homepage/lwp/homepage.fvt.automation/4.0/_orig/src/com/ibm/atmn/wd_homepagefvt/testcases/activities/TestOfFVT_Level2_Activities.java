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

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wdbase.FormInputHandler;
import com.ibm.atmn.wd_homepagefvt.tasks.activities.FVT_ActivitiesMethods;



public class TestOfFVT_Level2_Activities extends FVT_ActivitiesMethods {
	
	FormInputHandler typist;
	

	private static String publicActivityPublicEntryTitle2 = "FVT Level2 Public Activity 1150141052 Entry";
	private static String publicActivityName2 = "FVT Level2 Public Activity 1150141052";

	

	@Test 
	public void testSavedActivities_CommentingAfterSaving_Part4() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part4");
	
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
		clickLink(FVT_NewsObjects.Discover);
		
		filterBy("Activities");
		
		sleep(2000);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" commented on the "+publicActivityPublicEntryTitle2+" entry thread in the "+publicActivityName2+" activity."));
	
		clickLink(FVT_NewsObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent(" created the "+publicActivityPublicEntryTitle2+" entry in the "+publicActivityName2+" activity."));
	
		Logout();
		
		System.out.println("INFO: End of Activities FVT_Level_2 testSavedActivities_CommentingAfterSaving_Part4");
	
	}
}
	
	
