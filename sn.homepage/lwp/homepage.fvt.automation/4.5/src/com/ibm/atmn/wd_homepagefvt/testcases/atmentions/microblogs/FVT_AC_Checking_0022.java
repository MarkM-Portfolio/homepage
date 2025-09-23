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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;


public class FVT_AC_Checking_0022 extends FVT_AtMentionsMethods {
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String ModeratedCommunity = "";
	
	@Test
	public void AC_Checking_22_Like_ModCom_Comment () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of AC_Checking_22_User_Cannot_Like_ModCom_Comment");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
		
		Login(testUser1);
			
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);

		//Now Get the DateTime
		String DateTimeStamp = CommonMethods.genDateBasedRandVal();
		
		//Create a Moderated community
		ModeratedCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, testUser2.getEmail());

		//Click on Status Updates
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		//Enter Status
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_NewsData.UpdateStatus);
		
		clickLink(FVT_AtMentionsObjects.Post);
		
		//refresh the page
		clickLink(FVT_CommunitiesObjects.StatusUpdates);
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		Actions builder = new Actions(wd);
		
		//Create identifier for status update
		String statusUpdateBox = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains("+FVT_NewsData.UpdateStatus+")";
		
		//find status update and click corresponding like button
		builder.moveToElement((WebElement) driver.getFirstElement(statusUpdateBox).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_AtMentionsObjects.Comment).getBackingObject()).click().build().perform();	
		
		//Create a Comment
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedCommentBox).type(FVT_AtMentionsData.StatusUpdateComment);

		smartSleep(FVT_AtMentionsObjects.Post);
		
		//Click post button
		clickLink(FVT_AtMentionsObjects.Post);
		
		//logout
		LogoutAndClose();
		
		//Load Homepage
		LoadComponent(CommonObjects.ComponentHomepage);
		
		//Login
		Login(testUser3);
		
		//Open Discover
		clickLink(FVT_HomepageObjects.Discover);
		
		//Filter by Communities
		filterBy("Communities");
		
		//find status update and click corresponding like button
		driver.getFirstElement("css=a:contains('Like'):nth(1)").click();
		
		sleep(500);
		
		//verify error message appears
		assertTrue("FAIL - error message did not appear", driver.isTextPresent("Join the community '"+ModeratedCommunity+"' to like this comment"));
		
		sleep(2000);
		
		//Logout
		LogoutAndClose();
		
		System.out.println("INFO: End of AC_Checking_22_User_Cannot_Like_ModCom_Comment");
		
	}

}
