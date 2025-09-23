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

package com.ibm.atmn.wd_homepagefvt.testcases.files.saved;




import org.testng.annotations.Test;
import static org.testng.AssertJUnit.*;



import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.files.FVT_FilesObjects;
import com.ibm.atmn.homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.homepagefvt.tasks.files.FVT_FilesMethods;



public class FVT_Saved_AllUpdates_Files extends FVT_FilesMethods {


	

	private static String PublicFile = "";
	
	
		
	@Test 
	public void testMarkingFilesStoriesAsSaved_AllUpdates() throws Exception {
			
			
		System.out.println("INFO: Start of Communities Level 2 testMarkingFilesStoriesAsSaved_AllUpdates");	
		
		//Upload file 
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		//Generate random file name
		PublicFile = "FVT Public File "+genDateBasedRandVal();
		
		//Upload private file
		uploadFile(PublicFile, ".jpg", "Desert.jpg", "Public");
				
		//Logout of Connections
		Logout();	
		
		//Follow File
		
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Log in
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		//Go to public files
		clickLink("link=Public Files");
		
		//Go to public files
		clickLink("link="+PublicFile+".jpg");
		
		//Follow
		clickLink("link=Follow");
	
		//Logout
		Logout();
			
		//Save the file news story
		
		LoadComponent(CommonObjects.ComponentNews);
		
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		clickLink(FVT_HomepageObjects.Discover);
			
		FilterBy("All Updates");
			
		cautiousFocus("css=div.lotusPostContent:contains('created the file "+PublicFile+".')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the story saved
		
		clickLink(FVT_HomepageObjects.Home);
			
		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('created the file "+PublicFile+".')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
			
		//Check that the story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
			
		assertTrue("Fail: text is not present!!", sel.isTextPresent("created the file "+PublicFile+"."));
			
		//Delete the file news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created the file "+PublicFile+".')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the save this link is visible again
		
		clickLink(FVT_HomepageObjects.Discover);
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('created the file "+PublicFile+".')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
				
		System.out.println("INFO: End of Files FVT_Level_2 testMarkingFilesStoriesAsSaved_AllUpdates");
			
	}
	
	
	
	
	@Test (dependsOnMethods = { "testMarkingFilesStoriesAsSaved_AllUpdates" })
	public void testMarkingFilesStoriesAsSaved2_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testMarkingFilesStoriesAsSaved2_AllUpdates");
	
		//Update file 
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
		
		//Login as a user (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		clickLink(FVT_FilesObjects.MyFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");		
		
		//Upload public file
		updateFile(PublicFile, ".jpg", "Desert.jpg", "Public");
		
		//Logout of Connections
		Logout();	
		
		//Save the file news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);

		FilterBy("All Updates");

		cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		clickLink(FVT_HomepageObjects.SaveThis);
		
		//Check that the file is saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");
		
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));			
		
		//Check that the file news story appears in Saved
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent("edited the file "+PublicFile+".jpg."));
		
		//Delete the files news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));
		
		//Check that the Save this link is visible again 
		
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('edited the file "+PublicFile+".jpg.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		Logout();
		
		System.out.println("INFO: End of Files FVT_Level_2 testMarkingFilesStoriesAsSaved2_AllUpdates");
		
	}
	
	@Test (dependsOnMethods = { "testMarkingFilesStoriesAsSaved2_AllUpdates" })
	public void testMarkingFilesStoriesAsSaved3_AllUpdates() throws Exception {
		
		
		System.out.println("INFO: Start of Files FVT_Level_2 testMarkingFilesStoriesAsSaved3_AllUpdates");
	
		//comment on file 
		
		//Load the component
		LoadComponent(CommonObjects.ComponentFiles);
			
		//Login as a user (ie. Amy Jones451)
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		assertTrue("Fail: Files is not open", sel.isTextPresent("Public Files"));
		
		//Check for any error messages
		CheckForErrorsOnPage();
		
		clickLink(FVT_FilesObjects.PublicFiles);
		
		//Generate random file name
		clickLink("link="+PublicFile+".jpg");
		
		//Add Tag
		AddAComment();
		
		//Logout of Connections
		Logout();	
		
		//Save the file news story
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your file "+PublicFile+".jpg.')");

		clickLink(FVT_HomepageObjects.SaveThis);
	
		//Check that the file news story saved
		
		clickLink(FVT_HomepageObjects.Home);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the file "+PublicFile+".jpg.')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink(FVT_HomepageObjects.Saved);
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent("commented on the file "+PublicFile+".jpg."));
		
		//Delete the file news story
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the file "+PublicFile+".jpg.')");

		clickLink(FVT_NewsObjects.DeleteSavedStory);
		
		clickLink(FVT_NewsObjects.RemoveSavedStory);

		assertTrue("Fail: Text not present!", sel.isTextPresent("Entry has been successfully removed from your Saved list."));	

		//Check that the Save this link is visible again
		
		clickLink(FVT_HomepageObjects.ForMe);
		
		FilterBy("All Updates");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on your file "+PublicFile+".jpg.')");

		assertTrue("Fail: text is not present!!", sel.isElementPresent(FVT_NewsObjects.SaveThis));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Files FVT_Level_2 testMarkingFilesStoriesAsSaved3_AllUpdates");
		
	}
	

}