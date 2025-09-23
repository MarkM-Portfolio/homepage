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

package com.ibm.atmn.homepagefvt.testcases.activities;





import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.homepagefvt.appobjects.activities.FVT_ActivitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.base.FormInputHandler;
import com.ibm.atmn.homepagefvt.tasks.activities.FVT_ActivitiesMethods;



public class TestOfFVT_Level2_Activities extends FVT_ActivitiesMethods {


	

	
	
	private FormInputHandler typist = getFormInputHandler();
	

	private static String publicActivityName = "FVT Level2 Public Activity 1350140013";
	private static String publicActivityPublicEntryTitle = "FVT Level2 Public Activity 1350140013 Entry";
	
		
	@Test 
	public void testMarkingActivityStoriesAsSavedPart3() throws Exception {
		
		
		System.out.println("INFO: Start of Activities FVT_Level_2 testMarkingActivityStoriesAsSavedPart3");
	
		// Login to activities
		LoadComponent(CommonObjects.ComponentActivities);
				
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
		assertTrue("Fail: Activities is not open", sel.isTextPresent("Activities"));	
			
		// Open a private activity you are a member of
		clickLink("link=" + publicActivityName);
				
		clickLink(FVT_ActivitiesObjects.ExpandActivityEntry);
		
		// Create a new comment within this entry
		addCommentToActivityEntry(typist, false);
			
		//Logout
		Logout();
		
		
		
		LoadComponent(CommonObjects.ComponentNews);

		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);

		clickLink("link=For me");
		
		FilterBy("Activities");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");

		clickLink("link=Save this");
	
		clickLink("link=Home");
		
		clickLink("link=Discover");
		
		cautiousFocus("css=div.lotusPostContent:contains('commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity.')");
	
		assertTrue("Fail: Link still there!!", sel.isElementPresent("css=a[title='Already Saved']"));
		
		clickLink("link=Saved");
		
		assertTrue("Fail: text is not present!!", sel.isTextPresent(" commented on the "+publicActivityPublicEntryTitle+" entry in the "+publicActivityName+" activity."));
		
		//Logout
		Logout();
		
		
		System.out.println("INFO: End of Activities FVT_Level_2 testMarkingActivityStoriesAsSavedPart3");
		
	}
	

}