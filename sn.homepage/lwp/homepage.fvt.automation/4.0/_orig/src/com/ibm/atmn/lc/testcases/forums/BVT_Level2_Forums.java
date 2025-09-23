/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.testcases.forums;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import com.ibm.atmn.base.User;
import com.ibm.atmn.base.UserAllocation;
import com.ibm.atmn.lc.appobjects.common.CommonObjects;
import com.ibm.atmn.lc.appobjects.forums.ForumsData;
import com.ibm.atmn.lc.appobjects.forums.ForumsObjects;
import com.ibm.atmn.lc.tasks.forums.ForumsMethods;

/*
 * Part 1
 * 
 * 1. After Login Connections with a normal user, go to Apps -> Forums 2. Find "Start a Forum" button, and click to
 * create a forum 3. Input information of Forum, and save. 4. Page go into the Forum 5. Find "Start a Topic" button, and
 * click to create a topic 6. Input all information of topic and save 7. Topic page shows, including all infomation and
 * action links 8. Back to "My Forums", the forum is in the list 9. Click the forum title to enter into it 10. In the
 * Forum page, there is a widget at the left navigation, called Owner, and there is a link called "Add Owners"
 * 
 * 
 * 
 * Part 2
 * 
 * 1. Login Connections with another user 2. Click to "Public Forums" 3. The Forum created in Part 1 is there. 4. Click
 * to enter into it 5. In the Forum page, the topic created in Part 1 is in the topic list. 6. Click to enter into it 7.
 * Click reply to reply it, save 8. Back to the Forum page, then the replies account shows as 1.
 */

@Listeners( { com.ibm.atmn.base.ListenerForErrors.class })
public class BVT_Level2_Forums extends ForumsMethods {

	private String part1ForumName = "";
	private String part1TopicTitle = "";
	private User user1;
	private User user2;

	@Test(groups = { "forums", "level-twos", "forums-level-twos" })
	public void forumsPart1() throws Exception {

		System.out.println("INFO: Start of Forums Test Part 1");

		UserAllocation allocator = UserAllocation.getInstance();
		user1 = allocator.getUser();
		user2 = allocator.getUser();
		
		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user (ie. Amy Jones66)
		Login(user1.getUid(), user1.getPassword());

		// Start Forums BVT (Level 2) Part 1
		clickBannerLink(CommonObjects.Banner_Apps_Forums);
		clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);

		// Start a forum
		clickLink(ForumsObjects.Start_A_Forum);
		String forumRandomNumber = genDateBasedRandVal();
		part1ForumName = ForumsData.Start_A_Forum_InputText_Name_Data + forumRandomNumber;
		sel.type(ForumsObjects.Start_A_Forum_InputText_Name, part1ForumName);
		sel.type(ForumsObjects.Start_A_Forum_InputText_Tags, ForumsData.Start_A_Forum_InputText_Tags_Data + forumRandomNumber);
		sel.type(ForumsObjects.Start_A_Forum_Textarea_Description, ForumsData.Start_A_Forum_Textarea_Description_Data + forumRandomNumber);
		clickLink(CommonObjects.SaveButton);

		// Start a topic
		clickLink(ForumsObjects.Start_A_Topic);
		String topicRandomNumber = genDateBasedRandVal();
		part1TopicTitle = ForumsData.Start_A_Topic_InputText_Title_Data + topicRandomNumber;
		sel.type(ForumsObjects.Start_A_Topic_InputText_Title, part1TopicTitle);
		if (Math.round(Math.random()) == 0) {
			sel.click(ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
		}

		sel.type(ForumsObjects.Start_A_Topic_InputText_Tags, ForumsData.Start_A_Topic_InputText_Tags_Data + topicRandomNumber);
		// type description
		typeNativeInCkEditor(ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... " + part1ForumName);
		// Save form
		clickLink(CommonObjects.SaveButton);

		// For testing without using CK Editor window:
		/*
		 * if(sel.isElementPresent(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk )){
		 * clickLink(Objects.Start_A_Topic_InputButton_ConfirmationDialogueOk ); }
		 */

		// Go to main page of the forum created above
		clickLink(ForumsObjects.My_Forums_Tab);
		clickLink(CommonObjects.Centre_Content_Filter_Tabs_Tab1);
		// Click link to the page of the forum created above
		clickLink("link=" + part1ForumName);

		// Verify 'add owners' link is present
		AssertJUnit.assertTrue(sel.isTextPresent("Add Owners"));

		// Logout of Connections
		Logout();

		System.out.println("INFO: End of Forums Test Part 1");

	}

	@Test(groups = { "forums", "level-twos", "forums-level-twos" }, dependsOnMethods = { "forumsPart1" })
	public void forumsPart2() throws Exception {

		System.out.println("INFO: Start of Forums Test Part 2");

		if (part1TopicTitle.length() == 0) {
			throw new Exception("TEST ERROR: BVT_Level2_Forums testPart1 must be run before testPart2");
		}

		// Load the component
		LoadComponent(CommonObjects.ComponentForums);

		// Login as a user other than the user in test part 1 (ie. Amy Jones85)
		Login(user2.getUid(), user2.getPassword());

		// Start Forums BVT (Level 2) Part 2
		clickBannerLink(CommonObjects.Banner_Apps_Forums);

		// Go to topic
		clickLink(ForumsObjects.Public_Forums_Tab);
		clickLink("link=" + part1ForumName);
		clickLink("link=" + part1TopicTitle);

		// Reply to topic
		clickLink(ForumsObjects.Reply_to_topic);
		// Type text using native keystrokes (typing will occur in focused
		// window)
		typeNativeInCkEditor("Reply to topic... " + part1TopicTitle + ", In forum... " + part1ForumName + ".");
		// Save form
		clickLink(CommonObjects.SaveButton);

		// Go to main page of forum created above
		clickLink("link=" + part1ForumName);

		// Verify that the count of replies to the topic created above is
		// exactly '1' (only the reply just created).
		AssertJUnit.assertTrue("FAIL: Number of replies not equal to '1'", sel.getText(ForumsObjects.First_Topic_Number_of_Replies).compareToIgnoreCase("1") == 0);

		// Logout of Connections
		Logout();

		System.out.println("INFO: End of Forums Test Part 2");

	}

}
