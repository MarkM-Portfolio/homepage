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

package com.ibm.atmn.wd_homepagefvt.testcases.profiles.saved;



import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Saved_Profiles extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	
	private static String BoardEntry1 = "";
	private static String BoardEntry2 = "";
	
	@Test
	public void testSavedProfilesPart1 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedProfilesPart1");	
		
		//Add board entry
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		BoardEntry1 = AddBoardEntry_OtherWall(CommonData.IC_LDAP_Username_Fullname452);
		
		//Save the profiles news story
		
		//Load News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.Discover);
		
		//Filter by People
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry1+"')").hover();

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry1+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(BoardEntry1));
		
		//Delete Story
		
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry1+"')").hover();

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry1+"')").hover();

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedProfilesPart1");
	}
	
	@Test (dependsOnMethods = { "testSavedProfilesPart1" })
	public void testSavedProfilesPart2 () throws Exception {
		System.out.println("INFO: Start of Profiles Level 2 FVT testSavedProfilesPart2");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		//Update Status
		BoardEntry2 = AddBoardEntry_OtherWall(CommonData.IC_LDAP_Username_Fullname451);
		
		//Logout of profiles
		Logout();	
		
		//Save the profiles news story
		
		//Load News
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go To Home
		clickLink(FVT_HomepageObjects.Home);
		
		//Go to Discover View
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		//Filter by People
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry2+"')").hover();

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story Saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry2+"')").hover();
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", driver.isTextPresent(BoardEntry2));
		
		//Delete Story
		
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry2+"')").hover();

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link appears again  
		
		clickLink(FVT_HomepageObjects.MyNotifications);
		
		filterBy("People");
		
		driver.getSingleElement("css=div.lotusPostContent:contains('"+BoardEntry2+"')").hover();

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedProfilesPart2");
	}
	
}
