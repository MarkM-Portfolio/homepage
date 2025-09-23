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

package com.ibm.atmn.wd_homepagefvt.testcases.atmentions;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;


public class FVT_Message_Directed_To_User extends FVT_AtMentionsMethods{

	User testUser1;
	User testUser2;
	User testUser3;
	
	@Test
	public void test_AtMention_20_Message_Directed_In_Status_Update () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		 		
		System.out.println("INFO: Start of test_@Mention20 Message directed in Status Update appears in view");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open @Mentions		
		clickLink(FVT_HomepageObjects.AtMentions);
	
		//Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);
		
		//Enter text and user to mention
		mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Logout
		LogoutAndClose();	
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU, null, null, testUser1.getDisplayName());
		
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, true);
	  	
		System.out.println("INFO: End of test_@Mention20 Message directed in Status Update appears in view");
	}
	
	@Test (dependsOnMethods = { "test_AtMention_20_Message_Directed_In_Status_Update" })
	public void test_AtMention_30_Message_Directed_Status_Update_Comment () throws Exception {
		 
		 		
		System.out.println("INFO: Start of test_@Mention30 Message_Directed_Status_Update_Comment");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open @Mentions		
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_AtMentionsData.MentionUserPost+testUser3.getDisplayName()+")";
		
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Create a Comment with an @Mention to user2
		mentionUser(testUser2, FVT_AtMentionsData.StatusUpdateComment, FVT_AtMentionsObjects.EmbeddedCommentBox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		LogoutAndClose();
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_BOARD_MESSAGE_COMMENT, testUser2.getDisplayName(), null, testUser1.getDisplayName());
		
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, true);
		
		System.out.println("INFO: End of test_@Mention30 Message_Directed_Status_Update_Comment");
	}
	 
	@Test
	public void test_AtMention_21_Message_Directed_In_Board_Message() throws Exception{
	
		System.out.println("INFO: Start of MESSAGE DIRECTED IN A BOARD MESSAGE APPEARS IN VIEW");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user
		Login(testUser1);			
		
		//Search for users profile
		searchUser(testUser2);
				
		//Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);
		
		//Enter text and user to mention
		mentionUser(testUser3, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Logout
		LogoutAndClose();	
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_MESSAGE_BOARD, testUser2.getDisplayName(), null, testUser1.getDisplayName());
		
		System.out.println(atMentionsNewsStory);
		
		//Verify news story does NOT appear in User2 @mention feed
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, false);
		
		//Verify news story appears in User3 @mention feed
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser3, true);
	  	
		System.out.println("INFO: END OF MESSAGE DIRECTED IN A BOARD MESSAGE APPEARS IN VIEW");
		
		
	}
	
	
	@Test
	public void test_AtMention_31_Board_Message_Comment() throws Exception{
	
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of MESSAGE DIRECTED IN A BOARD MESSAGE COMMENT APPEARS IN VIEW");
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user
		Login(testUser1);			
		
		//Search for users profile
		searchUser(testUser2);
				
		//Click on the @Mentions Box
		clickLink(FVT_AtMentionsObjects.EmbeddedSharebox);
		
		//Enter text and user to mention
		mentionUser(testUser2, FVT_AtMentionsData.StatusUpdate, FVT_AtMentionsObjects.EmbeddedSharebox);

		sleep(1000);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		LogoutAndClose();		
		
		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);
			
		//Login as a user
		Login(testUser1);			
		
		//Search for users profile
		searchUser(testUser2);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_AtMentionsData.StatusUpdate+testUser2.getDisplayName()+")";
		
		System.out.println(statusUpdateBox);
		
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Create a Comment with an @Mention to user2
		mentionUser(testUser3, FVT_AtMentionsData.StatusUpdateComment, FVT_AtMentionsObjects.EmbeddedCommentBox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Logout
		LogoutAndClose();
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_BOARD_MESSAGE_COMMENT, testUser2.getDisplayName(), null, testUser1.getDisplayName());
		
		System.out.println(atMentionsNewsStory);
		
		//Verify news story does NOT appear in User2 @mention feed
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, false);
		
		//Verify news story appears in User3 @mention feed
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser3, true);
	  	
		System.out.println("INFO: END OF MESSAGE DIRECTED IN A BOARD MESSAGE COMMENT APPEARS IN VIEW");
		
		
	}
	
	
}
