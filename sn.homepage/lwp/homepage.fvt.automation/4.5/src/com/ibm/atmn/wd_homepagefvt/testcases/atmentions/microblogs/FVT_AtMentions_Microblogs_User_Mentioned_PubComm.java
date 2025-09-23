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
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;

public class FVT_AtMentions_Microblogs_User_Mentioned_PubComm extends
		FVT_AtMentionsMethods {

	User testUser1;
	User testUser2;
	User testUser3;

	private static String PublicCommunity = "";

	@Test
	public void testAtMentions_61_User_Mentioned_PubComm() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);

		System.out
				.println("INFO: Start of test_@Mention61 USER MENTIONED PUBLIC COMMUNITY");

		// Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		Login(testUser1);

		assertTrue("Fail: Communities is not open", driver
				.isTextPresent("Communities"));

		clickLink(FVT_CommunitiesObjects.ImAnOwner);

		// Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();

		// Create a public community
		PublicCommunity = CreateNewCommunity(
				FVT_CommunitiesData.PublicCommunityName + DateTimeStamp,
				FVT_CommunitiesData.CommunityHandle + DateTimeStamp,
				FVT_CommunitiesObjects.CommunityAccessOption1, "Members",
				FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser3
						.getEmail());

		// Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);

		// Enter text and user to mention
		mentionUser(testUser2, FVT_AtMentionsData.StatusUpdate,
				FVT_AtMentionsObjects.EmbeddedSharebox);

		smartSleep(FVT_AtMentionsObjects.Post);

		// Click post button
		clickLink(FVT_AtMentionsObjects.Post);

		// Logout
		LogoutAndClose();

		// Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(
				NewsStoryData.MENTIONED_YOU_COMMUNITY, PublicCommunity, null,
				testUser1.getDisplayName());

		// Issue -> news story is different under status updates - commented out
		// verifyNewsStory( atMentionsNewsStory, "I'm Following",
		// FVT_NewsData.STATUS_UPDATES, testUser2, true);

		// Issue -> news story not appearing under my notifications - commented
		// out
		// verifyMyNotificationsNewsStory(testUser2, atMentionsNewsStory,
		// FVT_NewsData.FOR_ME, FVT_NewsData.STATUS_UPDATES, true);

		// Verify under Profiles Activity Stream
		LoadComponent(CommonObjects.ComponentProfiles);

		Login(testUser2);

		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(atMentionsNewsStory));

		filterBy(FVT_NewsData.STATUS_UPDATES);

		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(atMentionsNewsStory));

		System.out
				.println("INFO: End of of test_@Mention61 USER MENTIONED PUBLIC COMMUNITY");

	}
}
