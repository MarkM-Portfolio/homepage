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
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;

public class FVT_AtMentions_Microblogs_Add_Multiple_AtMentions extends
		FVT_AtMentionsMethods {

	private User testUser1;
	private User testUser2;
	private User testUser3;
	private User testUser4;
	private User testUser5;
	private User testUser6;

	private static String PublicCommunity = "";

	@Test
	public void Add_Multiple_AtMentions_003() throws Exception {

		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		testUser5 = userAllocator.getUser(this);
		testUser6 = userAllocator.getUser(this);

		System.out.println("INFO: Start of Add Multiple @Mentions 003");

		// Load the component
		LoadComponent(CommonObjects.ComponentCommunities);

		// Login as a user
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

		// Create an array of user names
		String[] users = new String[5];
		users[0] = testUser2.getDisplayName();
		users[1] = testUser3.getDisplayName();
		users[2] = testUser4.getDisplayName();
		users[3] = testUser5.getDisplayName();
		users[4] = testUser6.getDisplayName();

		// Click on Embedded sharebox and type message
		driver.getSingleElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(
				"This is an FVT_AtMentions test post " + DateTimeStamp);

		// Enter 5 users with @Mentions
		for (int x = 0; x <= 4;) {

			mentionMultipleUsers(users[x], FVT_AtMentionsData.MentionUserPost,
					FVT_AtMentionsObjects.EmbeddedSharebox);

			x++;
		}

		// Post Message
		clickLink(FVT_AtMentionsObjects.Post);

		// Logout
		LogoutAndClose();

		// Verify NewsStory for each user
		String atMentionsNewsStory = replaceNewsStory(
				NewsStoryData.MENTIONED_YOU_COMMUNITY, PublicCommunity, null,
				testUser1.getDisplayName());

		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, true);
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser3, true);
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser4, true);
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser5, true);
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser6, true);

	}

}