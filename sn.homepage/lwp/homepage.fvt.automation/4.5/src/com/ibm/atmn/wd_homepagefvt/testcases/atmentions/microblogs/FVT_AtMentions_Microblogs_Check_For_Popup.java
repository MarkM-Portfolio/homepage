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

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;

public class FVT_AtMentions_Microblogs_Check_For_Popup extends
		FVT_AtMentionsMethods {

	private User testUser1;
	private User testUser2;

	@Test
	public void test_At_Mentions_10_CheckForPopup() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		System.out.println("INFO: Start of test_At_Mentions_10_CheckForPopup");

		// Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		// Login as a user
		Login(testUser1);

		// Open @Mentions
		clickLink(FVT_HomepageObjects.StatusUpdates);

		// Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);

		// Enter text and user to mention
		String PopupSelected = mentionUser(testUser2,
				FVT_AtMentionsData.MentionUserPost,
				FVT_AtMentionsObjects.EmbeddedSharebox);

		assertTrue(driver.isTextPresent(PopupSelected));

		/*
		 * 
		 * Need to add test for global sharebox
		 * 
		 * 
		 * sleep(1000);
		 * 
		 * clickLink("css=li#headerSharebox.shareboxLi a.lotusBannerBtn");
		 * 
		 * WebDriver wd = (WebDriver) driver.getBackingObject();
		 * 
		 * wd.switchTo().frame(0);
		 * 
		 * 
		 * driver.getFirstElement("css=div#mentionstextAreaNode_0.lotusText").click
		 * ();
		 * 
		 * mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost,
		 * "css=div#mentionstextAreaNode_0.lotusText");
		 * 
		 * assertTrue(driver.isTextPresent(PopupSelected));
		 */

		// Logout
		LogoutAndClose();

	}

}