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


package com.ibm.atmn.wd_homepagefvt.testcases.files;




import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.files.FVT_FilesMethods;
import static org.testng.AssertJUnit.*;


public class TestOfFVT_Level2_Files extends FVT_FilesMethods{
	
	
	private static String PublicFile = "FVT Public File 1005163104";
	
	@Test
	public void testMarkingFilesStoriesAsSavedPart2() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testMarkingFilesStoriesAsSavedPart2");
	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", driver.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");		
		
		//Upload public file
		updateFile(PublicFile, ".jpg", "Desert.jpg", "Private");
		
		//Logout of Connections
		Logout();	
		
		//
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		filterBy("Files");

		//cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("Files");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");
		
		assertTrue("Fail: Link still there!!", driver.isElementPresent("css=a[title='Already Saved']"));			
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", driver.isTextPresent("edited the file "+PublicFile+".jpg."));
		
		
		/*
		//Logout
		Logout();
		
		//
		
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);

		clickLink("link=Saved");
		
		*/
		
		filterBy("Files");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", driver.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		filterBy("Files");
		
		//cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		assertTrue("Fail: text is not present!!", driver.isElementPresent(FVT_HomepageObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Files FVT_Level_2 testMarkingFilesStoriesAsSavedPart2");
		
	}
	
}
