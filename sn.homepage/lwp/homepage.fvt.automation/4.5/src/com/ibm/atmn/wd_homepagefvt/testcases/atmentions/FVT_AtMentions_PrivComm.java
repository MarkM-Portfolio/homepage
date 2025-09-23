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

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;


public class FVT_AtMentions_PrivComm extends FVT_AtMentionsMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String PrivateCommunity = "";
	
	@Test
	public void Status_Update_PrivComm_24 () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		 		
		System.out.println("INFO: Start of test_@Mention25 MESSAGE DIRECTED IN A PRIVATE COMMUNITY STATUS UPDATE - MEMBER");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a public community
		PrivateCommunity = CreateNewCommunity(FVT_CommunitiesData.PrivateCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption3, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		//Enter text and user to mention
		mentionUser(testUser2, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox );

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Logout
		LogoutAndClose();	
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_COMMUNITY, PrivateCommunity, null, testUser1.getDisplayName());
				
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, true);
		
		System.out.println("INFO: End of test_@Mention24 MESSAGE DIRECTED IN A PRIVATE COMMUNITY STATUS UPDATE - MEMBER");
	}
	
	@Test	(dependsOnMethods = { "Status_Update_PrivComm_24" })
	public void Status_Update_PrivComm_25 () throws Exception {
		 
		System.out.println("INFO: Start of test_@Mention25 MESSAGE DIRECTED IN A PRIVATE COMMUNITY STATUS UPDATE - NON MEMBER");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		// Created a new community with public access

		clickLink("link=" +PrivateCommunity);
		
		//Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		//Enter text and user to mention
		mentionUser(testUser3, FVT_AtMentionsData.MentionUserPost, FVT_AtMentionsObjects.EmbeddedSharebox );

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Logout
		LogoutAndClose();	
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_COMMUNITY, PrivateCommunity, null, testUser1.getDisplayName());
				
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser3, false);
		
		System.out.println("INFO: End of test_@Mention25 MESSAGE DIRECTED IN A PRIVATE COMMUNITY STATUS UPDATE - NON MEMBER");
	}
	
	//@Test (dependsOnMethods = { "Status_Update_PrivComm_24" })
	public void Status_Update_Comment_PrivComm_34() throws Exception{
	
	
		
		System.out.println("INFO: Start of test_@Mention32 COMMENT DIRECTED IN A MODERATED COMMUNITY STATUS UPDATE");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Login user
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//click I'm an owner
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Open the community
		clickLink("link=" + PrivateCommunity);
		
		//Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_AtMentionsData.MentionUserPost+testUser2.getDisplayName()+")";
		
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Create a Comment with an @Mention to user2
		mentionUser(testUser2, FVT_AtMentionsData.StatusUpdateComment, FVT_AtMentionsObjects.EmbeddedCommentBox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		LogoutAndClose();
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_COMMENT_COMM, PrivateCommunity, null, testUser1.getDisplayName());
		
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser2, true);
		
		System.out.println("INFO: End of test_@Mention32 COMMENT DIRECTED IN A MODERATED COMMUNITY STATUS UPDATE");


		}
	
	

	@Test (dependsOnMethods = { "Status_Update_PrivComm_25" })
	public void Status_Update_Comment_PrivComm_35() throws Exception{
	
	
		
		System.out.println("INFO: Start of test_@Mention32 COMMENT DIRECTED IN A MODERATED COMMUNITY STATUS UPDATE");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		//Login user
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		//click I'm an owner
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
		
		//Open the community
		clickLink("link=" + PrivateCommunity);
		
		//Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_AtMentionsData.MentionUserPostPrivate+testUser3.getDisplayName()+")";
		
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Create a Comment with an @Mention to user2
		mentionUser(testUser3, FVT_AtMentionsData.StatusUpdateComment, FVT_AtMentionsObjects.EmbeddedCommentBox);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		LogoutAndClose();
		
		//Verify NewsStory
		String atMentionsNewsStory = replaceNewsStory(NewsStoryData.MENTIONED_YOU_COMMENT_COMM, PrivateCommunity, null, testUser1.getDisplayName());
		
		verifyNewsStory(atMentionsNewsStory, "@Mentions", null, testUser3, false);
		
		System.out.println("INFO: End of test_@Mention32 COMMENT DIRECTED IN A MODERATED COMMUNITY STATUS UPDATE");


		}
	

}