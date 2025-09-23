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

package com.ibm.atmn.homepagefvt.testcases.profiles;



import static org.testng.AssertJUnit.*;

import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.profiles.FVT_ProfilesMethods;

public class FVT_Level2_Saved_Profiles extends FVT_ProfilesMethods{
	
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
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry1+"')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry1+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(BoardEntry1));
		
		//Delete Story
		
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry1+"')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry1+"')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
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
		clickLink(FVT_HomepageObjects.ForMe);
		
		//Filter by People
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry2+"')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the news story Saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.ForMe);
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry2+"')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent(BoardEntry2));
		
		//Delete Story
		
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry2+"')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link appears again  
		
		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("People");
		
		cautiousFocus("css=div.lotusPostContent:contains('"+BoardEntry2+"')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout of profiles
		Logout();	
		
		System.out.println("INFO: End of Profiles Level 2 FVT testSavedProfilesPart2");
	}
	
}
