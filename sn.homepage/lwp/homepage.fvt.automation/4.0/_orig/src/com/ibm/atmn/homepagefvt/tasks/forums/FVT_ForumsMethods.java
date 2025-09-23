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

package com.ibm.atmn.homepagefvt.tasks.forums;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.homepagefvt.appobjects.forums.FVT_ForumsObjects;

public class FVT_ForumsMethods extends com.ibm.atmn.homepagefvt.tasks.communities.FVT_CommunitiesMethods {

	public void waitForElementToExist(String element, int maxAttempts) {

		int attempts = 0;
		while (sel.isElementPresent(element) != true && attempts < maxAttempts) {

			sleep(100);
			attempts++;
			System.out.println(attempts);

		}
	}
	
	public void StartAForum(String ForumName) {

		// Start a forum
		clickLink(FVT_ForumsObjects.Start_A_Forum);
		String forumRandomNumber = genDateBasedRandVal();
		sel.type(FVT_ForumsObjects.Start_A_Forum_InputText_Name, ForumName);
		sel.type(FVT_ForumsObjects.Start_A_Forum_InputText_Tags, FVT_ForumsData.Start_A_Forum_InputText_Tags_Data
				+ forumRandomNumber);
		sel.type(FVT_ForumsObjects.Start_A_Forum_Textarea_Description, FVT_ForumsData.Start_A_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void UpdateAForum() {

		// Update a forum
		clickLink(FVT_ForumsObjects.Forum_MoreActions);
		clickLink(FVT_ForumsObjects.Edit_Forum);
		
		sel.type(FVT_ForumsObjects.Start_A_Forum_Textarea_Description, FVT_ForumsData.Updated_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void StartATopic(String TopicName) {

		// Start a topic
	clickLink(FVT_ForumsObjects.Start_A_Topic);
	String topicRandomNumber = genDateBasedRandVal();
	sel.type(FVT_ForumsObjects.Start_A_Topic_InputText_Title, TopicName);
	if (Math.round(Math.random()) == 0) {
		sel.click(FVT_ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
	}

	sel.type(FVT_ForumsObjects.Start_A_Topic_InputText_Tags, FVT_ForumsData.Start_A_Topic_InputText_Tags_Data
			+ topicRandomNumber);
	// type description
	typeNativeInCkEditor(FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber);
	// Save form
	clickLink(CommonObjects.SaveButton);
		
	}
	
	
	public void ReplyToTopic(String Topic, String Forum) {

		// Reply to topic
		clickLink(FVT_ForumsObjects.Reply_to_topic);
		// Type text using native keystrokes (typing will occur in focused
		// window)
		typeNativeInCkEditor("Reply to topic... " + Topic + ", In forum... " + Forum + ".");
		// Save form
		clickLink(CommonObjects.SaveButton);
		
	}
	
	


}