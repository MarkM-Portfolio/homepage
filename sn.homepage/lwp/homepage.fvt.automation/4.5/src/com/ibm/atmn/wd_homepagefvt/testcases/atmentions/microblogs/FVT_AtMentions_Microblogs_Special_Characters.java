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

import java.util.Random;

import org.testng.annotations.Test;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;

public class FVT_AtMentions_Microblogs_Special_Characters extends
		FVT_AtMentionsMethods {

	@Test
	public void test_At_Mentions_47_UserIdSpecialCharacters() throws Exception {

		System.out
				.println("INFO: Start of test_At_Mentions_47_UserId_Special_Characters");

		// Create Array of Users
		String[][] users = new String[][] {
				{ "Combo .@2", "Pr0bl3m()* Combo .@2", "Combo .@2" },
				{ "C{fvt}Bracket", "Curly{fvt} Bracket", "bracket" },
				{ "Us&r", "Ampers&nd R&D Us&r", "Us&r" },
				{ "p%Sign%", "%Percent% %Sign%", "%Sign%" },
				{ "Face :-D", "Smiley :-) Face :-D", "face :-D" },
				{ "GÂççèÑtêë[Ú]üsêër",
						"GriffiÑ ÂççèÑtêë[Ú]üsêër <GriffiÑ ÂççèÑtêë[Ú]üsêër",
						"GriffiÑ ÂççèÑtêë[Ú]üsêër" },
				// {"OUser", "A'postrophe OUser","OUser"},
				{
						"ìíîïðñòóôõöøùúûüýþÿ",
						"ÂÃÄÀÁÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖØÙÚÛÜÝÞßàáâãäåæçèéêë ìíîïðñòóôõöøùúûüýþÿ",
						"ìíîïðñòóôõöøùúûüýþÿ" } };

		// Random User Generator
		Random r = new Random();
		int getUser1 = r.nextInt(6);
		int getUser2 = r.nextInt(6);

		// Get values for user 1
		String User1Login = users[getUser1][0], User1Name = users[getUser1][1], User1Password = users[getUser1][2];

		// Get values for user 2
		String User2Login = users[getUser2][0], User2Name = users[getUser2][1], User2Password = users[getUser2][2];

		// Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		// Login
		Login(User1Login, User1Password);

		// Open @Mentions
		clickLink(FVT_HomepageObjects.AtMentions);

		// Click on the @Mentions Box and type message
		driver.getSingleElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(
				FVT_AtMentionsData.MentionUserPostPrivate);

		// Enter user to mention
		mentionMultipleUsers(User2Name, FVT_AtMentionsData.MentionUserPost,
				FVT_AtMentionsObjects.EmbeddedSharebox);

		smartSleep(FVT_AtMentionsObjects.Post);

		// Click post button
		clickLink(FVT_AtMentionsObjects.Post);

		// Logout
		LogoutAndClose();

		// Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		// Login
		Login(User2Login, User2Password);

		// Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(
				NewsStoryData.MENTIONED_YOU, null, null, User1Name);

		// Verify under ImFollowing All
		clickLink(FVT_HomepageObjects.ImFollowing);
		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(atMentionsNewsStory));

		// Verify under ImFollowing Status Updates
		filterBy(FVT_NewsData.STATUS_UPDATES);
		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(User1Name + " mentioned " + User2Name
						+ " in a message."));

		// Logout
		LogoutAndClose();

		// Issue -> news story not appearing under my notifications - commented
		// out
		// verifyMyNotificationsNewsStory(testUser2, atMentionsNewsStory,
		// FVT_NewsData.FOR_ME, FVT_NewsData.STATUS_UPDATES, true);

		// Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		// Login
		Login(User2Login, User2Password);

		// Verify under Profiles Activity Stream
		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(atMentionsNewsStory));

		// Verify under Profiles Status Updates
		filterBy(FVT_NewsData.STATUS_UPDATES);
		assertTrue(
				"Fail - NewsStory not present under profile activity stream.",
				driver.isTextPresent(atMentionsNewsStory));

		// Logout
		Logout();

		System.out
				.println("INFO: End of test_@Mention22 MESSAGE DIRECTED IN A PUBLIC COMMUNITY STATUS UPDATE");

	}

}
