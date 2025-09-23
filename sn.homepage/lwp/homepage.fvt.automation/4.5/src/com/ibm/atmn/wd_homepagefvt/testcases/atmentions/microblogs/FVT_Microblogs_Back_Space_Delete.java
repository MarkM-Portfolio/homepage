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

import org.openqa.selenium.Keys;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;

public class FVT_Microblogs_Back_Space_Delete extends FVT_AtMentionsMethods{
	
	User testUser1;
	User testUser2;
	
	@Test
	 public void testAtMentions_15_Back_Space_Delete () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of test_At_Mentions_15_Backspace_Delete");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open @Mentions		
		clickLink(FVT_HomepageObjects.StatusUpdates);
	
		//Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);
		
		mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox);
		
		sleep(1000);
		
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(Keys.BACK_SPACE);
		
		assertTrue("@Mention has not been deleted", !driver.isTextPresent("@"+testUser2.getDisplayName()));
		
		LogoutAndClose();
		
		
	}

}
