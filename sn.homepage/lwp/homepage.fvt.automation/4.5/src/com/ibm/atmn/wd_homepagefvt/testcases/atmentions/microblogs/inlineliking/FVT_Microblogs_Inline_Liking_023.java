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

package com.ibm.atmn.wd_homepagefvt.testcases.atmentions.microblogs.inlineliking;

import static org.testng.AssertJUnit.assertTrue;

import com.ibm.atmn.wd_homepagefvt.tasks.atmentions.FVT_AtMentionsMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;

public class FVT_Microblogs_Inline_Liking_023 extends FVT_AtMentionsMethods{
	
	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	
	@Test
	public void Inline_Liking_023_Number_of_likes_Activity_Comment() throws Exception{
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of test Microblogs_Inline_Liking_023_Number_of_likes_Comment");	
		
		
		//Load Homepage Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login User1
		Login(testUser1);
		
		//open ImFollowing
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		//Type Status update
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_AtMentionsData.MentionUserPostPrivate);
		
		sleep(1000);
		
		//Click post
		clickLink(FVT_AtMentionsObjects.Post);		
		
		//Click on ImFollowing
		clickLink(FVT_HomepageObjects.ImFollowing);
				
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);
		
		//Find the Status Update and click the comment button
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_AtMentionsData.MentionUserPostPrivate+")";
		
		//Click on comment button belonging to relevant Status Update
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) 
		driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Type the status comment
		driver.getSingleElement("css=div#mentionstextAreaNode_1.lotusText").type(FVT_AtMentionsData.StatusUpdateCommentGeneral);
		
		//click Post	
		clickLink(FVT_AtMentionsObjects.Post);
		
		//Click on ImFollowing Tab
		clickLink(FVT_HomepageObjects.ImFollowing);
		
		sleep(1000);
		
		//Click on the Like button
		clickLink("css=a:contains('Like'):nth(1)");
		
		//Logout
		LogoutAndClose();		
		
		//Load Homepage Component
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login User2
		Login(testUser2);
		
		//Open Discover News
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Status Updates");
		
		//Like the status
		clickLink("css=a:contains('Like'):nth(1)");
		
		LogoutAndClose();
		
		//Load Profiles Page
		LoadComponent(CommonObjects.ComponentProfiles);
		
		//Login User3
		Login(testUser3);
		
		//Go to User1 profile
		searchUser(testUser1);
		
		sleep(1000);
		
		//Like the status
		clickLink("css=a:contains('Like'):nth(1)");
		
		//Logout
		LogoutAndClose();
		
		//Load Homepage
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//login User4
		Login(testUser4);
		
		//Open discover
		clickLink(FVT_HomepageObjects.Discover);
		
		//Filter by Status Updates
		filterBy(FVT_NewsObjects.StatusUpdates);
				
		sleep(1000);
		
		//Check that Likes = 3
		assertTrue("FAIL: Number of likes not equal to '3'", driver.getSingleElement(FVT_NewsObjects.Likes2).getText().compareToIgnoreCase("3") == 0);
		
		//logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Microblogs_Inline_Liking_023_Number_of_likes_Comment");	
		
	}

}
