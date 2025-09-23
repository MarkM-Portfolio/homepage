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

package com.ibm.atmn.wd_homepagefvt.tasks.forums;

import org.testng.xml.LaunchSuite.ExistingSuite;

import org.testng.Reporter;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.forums.FVT_ForumsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;

public class FVT_ForumsMethods extends FVT_CommunitiesMethods {

	
	public void startAForum(String ForumName) {

		// Start a forum
		clickLink(FVT_ForumsObjects.Start_A_Forum);
		driver.getSingleElement(FVT_ForumsObjects.Start_A_Forum_InputText_Name).click();
		driver.getSingleElement(FVT_ForumsObjects.Start_A_Forum_InputText_Name).type(ForumName);
		driver.getSingleElement(FVT_ForumsObjects.Start_A_Forum_InputText_Tags).type(FVT_ForumsData.Start_A_Forum_InputText_Tags_Data);
		driver.getSingleElement(FVT_ForumsObjects.Start_A_Forum_Textarea_Description).type(FVT_ForumsData.Start_A_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void updateAForum() {

		// Update a forum
		clickLink(FVT_ForumsObjects.Forum_MoreActions);
		clickLink(FVT_ForumsObjects.Edit_Forum);
		
		driver.getSingleElement(FVT_ForumsObjects.Start_A_Forum_Textarea_Description).type(FVT_ForumsData.Updated_Forum_Textarea_Description_Data);
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void startATopic(String TopicName) {

		// Start a topic
	clickLink(FVT_ForumsObjects.Start_A_Topic);
	String topicRandomNumber = genDateBasedRandVal();
	driver.getSingleElement(FVT_ForumsObjects.Start_A_Topic_InputText_Title).type(TopicName);	
	driver.getSingleElement(FVT_ForumsObjects.Start_A_Topic_InputText_Tags).type(FVT_ForumsData.Start_A_Topic_InputText_Tags_Data);
	
	if (Math.round(Math.random()) == 0) {
		clickLink(FVT_ForumsObjects.Start_A_Topic_InputCheckbox_MarkAsQuestion);
	}
			
	// type description
	typeNativeInCkEditor(FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber);
	
	
	sleep(2000);
	// Save form
	//driver.getFirstElement(CommonObjects.SaveButton).click();
	clickLink(CommonObjects.SaveButton);
	
	
		
	}
	
	public void replyToTopic(String Topic, String Forum) {

		// Reply to topic
		clickLink(FVT_ForumsObjects.Reply_to_topic);
		// Type text using native keystrokes (typing will occur in focused
		// window)
		typeNativeInCkEditor("Reply to topic... " + Topic + ", In forum... " + Forum + ".");
		// Save form
		driver.getSingleElement(CommonObjects.SaveButton).hover();
		clickLink(CommonObjects.SaveButton);
	}
	
	
	public void updateTopic(String ForumName) {
	
		driver.getFirstElement(FVT_ForumsObjects.Edit_Topic).click();
		String topicRandomNumber = genDateBasedRandVal();
		typeNativeInCkEditor(" "+FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
				+ ForumName);
		// Save form
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void updateReply(String ForumName) {
		
		driver.getFirstElement(FVT_ForumsObjects.Edit_Reply).click();
		String topicRandomNumber = genDateBasedRandVal();
		typeNativeInCkEditor(" "+FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
				+ ForumName);
		// Save form
		clickLink(CommonObjects.SaveButton);
		
	}
	
	public void updateReply2(String ForumName) {
		
		driver.getFirstElement(FVT_ForumsObjects.Edit_Reply2).click();
		String topicRandomNumber = genDateBasedRandVal();
		typeNativeInCkEditor(" "+FVT_ForumsData.CK_Editor_Textarea_RichText_Data + topicRandomNumber + ". \n\nIn forum... "
				+ ForumName);
		// Save form
		clickLink(CommonObjects.SaveButton);
		
	}
	
	

	public void openForum(String ForumName) {

		smartSleep(FVT_ForumsObjects.Public_Forums_Tab);
		
		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		
		clickLink("link=" + ForumName);
		
	}
	
	public void followForum(String ForumName) {

		clickLink(FVT_ForumsObjects.Public_Forums_Tab);
		
		clickLink("link=" + ForumName);
		
		clickLink(FVT_ForumsObjects.Follow);
		
		clickLink(FVT_ForumsObjects.FollowForum);
		
	}


}