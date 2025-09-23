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
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;

public class FVT_AtMentions_Microblogs_LinkToProfile extends
		FVT_AtMentionsMethods {

	User testUser1;
	User testUser2;

	@Test
	public void test_AtMentions_LinkToProfile_009() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);

		System.out.println("INFO: Start of Add Multiple @Mentions 003");

		// Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		// Login as a user
		Login(testUser1);

		// Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);

		// Mention user2 in a status update
		mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost,
				FVT_AtMentionsObjects.EmbeddedSharebox);

		clickLink(FVT_AtMentionsObjects.Post);

		sleep(1000);

		// Click on User2's name in the @Mention post
		clickLink("css=div.lotusPostContent span.vcard a.fn:contains(@"
				+ testUser2.getDisplayName() + ")");

		sleep(1000);

		// Store the current window handle
		String winHandleBefore = driver.getWindowHandle();

		// Perform the click operation that opens new window

		// Switch to new window opened
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchToWindowByHandle(winHandle);
		}

		// Verify that you are on User2's profile page
		assertTrue("Failed to open Profile", driver.isTextPresent("My Profile"));

		// Close the new window, if that window no more required
		driver.close();

		// Switch back to original browser (first window)
		driver.switchToWindowByHandle(winHandleBefore);

		// continue with original browser (first window)
		LogoutAndClose();

		System.out.println("INFO: End of FVT_AtMentions_LinkToProfile_009");

	}

}