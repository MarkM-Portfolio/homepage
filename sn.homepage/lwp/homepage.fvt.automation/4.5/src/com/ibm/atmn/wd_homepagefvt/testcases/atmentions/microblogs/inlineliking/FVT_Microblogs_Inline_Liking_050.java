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

package com.ibm.atmn.wd_homepagefvt.testcases.atmentions.microblogs.inlineliking;

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;

import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;

public class FVT_Microblogs_Inline_Liking_050 extends FVT_AtMentionsMethods {
	
User testUser1;
	
	@Test
	public void Inline_Liking_050_Microblog_EE_shows_correct_likes () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of Inline Liking 050 Microblog EE shows correct number of likes");		
		
		//Load Homepage
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login User1
		Login(testUser1);
		
		//Click Status Updates
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Type a status update
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_NewsData.UpdateStatus);
		
		//Post Status Update
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Refresh page
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		//Click Like button
		clickLink(FVT_NewsObjects.Like);
		
		sleep(1000);
		
		//Open EE
		Open_EE(FVT_NewsData.UpdateStatus);

		//Verify that the like appears in the EE
		assertTrue("Fail - could not verify like in EE", driver.isTextPresent("You like this"));
		
		//close EE
		switchFromEE();
		
		//Refresh page
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		//Unlike Status
		if(driver.isElementPresent("css=a#TOGGLE_com_ibm_oneui_controls_Like_0.lotusLikeAction")){
			
			clickLink("css=a#TOGGLE_com_ibm_oneui_controls_Like_0.lotusLikeAction");
		}
		else{
			
			clickLink("xpath=//*[@id='TOGGLE_com_ibm_oneui_controls_Like_1']");
			
		}
		
		
		//Open EE
		Open_EE(FVT_NewsData.UpdateStatus);
		
		//Verify that no like appears in the EE
		assertTrue("Fail - could not verify like in EE", driver.isTextPresent("Like"));
		
		//Switch from EE
		switchFromEE();
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of Inline Liking 050 Microblog EE shows correct number of likes");
	
	}
}
