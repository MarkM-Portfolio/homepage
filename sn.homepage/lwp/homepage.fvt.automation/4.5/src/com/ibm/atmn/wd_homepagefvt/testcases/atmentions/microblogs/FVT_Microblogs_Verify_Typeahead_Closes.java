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

package com.ibm.atmn.wd_homepagefvt.testcases.atmentions.microblogs;

import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;

public class FVT_Microblogs_Verify_Typeahead_Closes extends FVT_AtMentionsMethods{
	
	
	private User testUser1;
	private User testUser2;
	
	@Test
	public void test_At_Mentions_33_CheckTypeAheadCloses() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of test_At_Mentions_33_CheckTypeAheadCloses");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user
		Login(testUser1);		
		
		//Go to User2's profilw
		searchUser(testUser2);
	
		//Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);
		
		//Enter text and user to mention
		mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox);

		sleep(500);
		
		//Check if the typeahead has closed
		assertTrue("Fail - Typeahead did not close", !driver.isTextPresent("Person not listed? Use full search..."));

		sleep(1000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test_At_Mentions_33_CheckTypeAheadCloses");
		
	}
	

}
