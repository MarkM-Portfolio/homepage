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
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;

public class FVT_Microblogs_Inline_Liking_070 extends FVT_AtMentionsMethods {
	
	User testUser1;
	
	@Test
	public void Inline_Liking_070_Microblogs_0_Likes() throws Exception{

		System.out.println("INFO: Start of test Microblogs_Inline_Liking_070_Microblogs_0_Likes");
		
		testUser1 = userAllocator.getUser(this);
	
		//Load Component Homepage
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login User1
		Login(testUser1);
		
		//Click on Status Updates
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Click on the Status updates
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_NewsData.UpdateStatus);
		
		//Click post
		clickLink(FVT_AtMentionsObjects.Post);
		
		sleep(2000);
		
		//Click on the Like icon
		driver.getFirstElement("css=a.lotusLikeCount img.lotusIconLike").click();
		
		sleep(1000);
		
		//Verify that noone has liked the status update
		assertTrue("Fail - '0 people like this' expected", driver.isTextPresent("0 people like this"));
		
		//logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Microblogs_Inline_Liking_070_Microblogs_0_Likes");
	}

}
