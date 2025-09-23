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

package com.ibm.atmn.wd_homepagefvt.testcases.news;



import static org.testng.AssertJUnit.*;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.profiles.FVT_ProfilesMethods;
import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;


public class FVT_Repost_OwnStatus extends FVT_ProfilesMethods{
	
	//Created by Johann Ott - 14 Nov 2011
	
	User testUser1;
	User testUser2;
	User testUser3;
	
	private static String StatusUpdate = "";
	private static String StatusComment = "";
	
	@Test 
	public void testFollowUser() throws Exception {

		System.out.println("INFO: Start of News Level 2 testFollowUser");

		//Load the component
		LoadComponent(CommonObjects.ComponentProfiles);

		//Login as a user (ie. Amy Jones66)
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		
		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		FollowPerson(testUser1.getEmail());

		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testFollowUser");
	}
	

	
	

	@Test (dependsOnMethods = { "testFollowUser" })
	public void testRepostOwnStatus() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRepostOwnStatus");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		//Post Status
		StatusUpdate = statusUpdate(StatusUpdate);
		
		//Repost status
		repostStatus(StatusUpdate);
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testRepostOwnStatus");
	}
	
	@Test (dependsOnMethods = { "testRepostOwnStatus" })
	public void testVerifyRepost() throws Exception {

		System.out.println("INFO: Start of News Level 2 testVerifyRepost");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
	
		//Check News Feed
		
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		WebDriver wd = (WebDriver) driver.getBackingObject();
		
		Actions builder = new Actions(wd);
			
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		//assertTrue("Update text not showing in repost item", isTextPresent(StatusUpdate));
		
		String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		filterBy_StatusUpdates("My Updates");
		
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		filterBy("All");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		filterBy("Status Updates");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		//Logout of Wiki
		Logout();

		Login(testUser2);
		//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
	
		//Check News Feed
		
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		filterBy_StatusUpdates("People");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		clickLink(FVT_NewsObjects.AllUpdates);
		
		filterBy("All");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		filterBy("Status Updates");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		filterBy("People");
		
		assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
		
		//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
		
		builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
		
		assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
		
		//Logout of Wiki
		Logout();
		
		System.out.println("INFO: End of News Level 2 testVerifyRepost");
	}
	
	@Test (dependsOnMethods = { "testVerifyRepost" })
	public void testRepostLikeOwnStatus() throws Exception {

		System.out.println("INFO: Start of News Level 2 testRepostLikeOwnStatus");

		//Load the component
		LoadComponent(CommonObjects.ComponentNews);

		//Login as a user (ie. Amy Jones66)
		//testUser1 = userAllocator.getUser();
		
		Login(testUser1);
		//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
		
		//verify sharebox displays
		clickLink(FVT_NewsObjects.StatusUpdates);
		
		filterBy_StatusUpdates("All");
		
		assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
	
		StatusUpdate = statusUpdate(StatusUpdate);
		
		Open_EE(StatusUpdate);
	
		//Swtich to Embedded Experience
		//switchToEE();
		
		//Click on the Like button
		Like();
		
		//Check that Likes = 1
		assertTrue("FAIL: Number of likes not equal to '1'", driver.getSingleElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("1") == 0);
		
		//Repost
		clickLink(FVT_NewsObjects.Repost);
		
		//Swtich from Embedded Experience
		switchFromEE();
	
		//Logout of Wiki
		Logout();

		System.out.println("INFO: End of News Level 2 testRepostLikeOwnStatus");
		
	}
		
		
		@Test (dependsOnMethods = { "testRepostLikeOwnStatus" })
		public void testVerifyRepostLike() throws Exception {

			System.out.println("INFO: Start of News Level 2 testVerifyRepostLike");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
			//Check News Feed
		
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			//String RepostLike = "css=div.activityStreamNewsItemContainer div.lotusPostContent:contains('"+StatusUpdate+"') div.lotusMeta ul.lotusInlinelist li:contains('1 like')";
			String RepostLike = FVT_NewsObjects.RepostLike;

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			//assertTrue("Update text not showing in repost item", driver.isElementPresent("css=[class='lotusLikeText'][role='presentation'][dojoattachpoint='inlineLikeCount']:contains('1')"));
			//if (driver.getFirstElement("css=[class='lotusLikeText'][role='presentation'][dojoattachpoint='inlineLikeCount']").getText().compareTo("1") == 1)

			
			filterBy_StatusUpdates("My Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			//Logout of Wiki
			Logout();

			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
			//Check News Feed
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			filterBy_StatusUpdates("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			filterBy("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostLike));
			
			//Logout of Wiki
			Logout();
			
			System.out.println("INFO: End of News Level 2 testVerifyRepostLike");
		}
		
		
		@Test (dependsOnMethods = { "testVerifyRepostLike" })
		public void testRepostCommentOwnStatus() throws Exception {

			System.out.println("INFO: Start of News Level 2 testRepostCommentOwnStatus");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
			
			//verify sharebox displays
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
			StatusUpdate = statusUpdate(StatusUpdate);
			
			//Comment on Status
			
			StatusComment = CommentOnStatus(StatusUpdate);
			
			//Repost
			
			repostStatus(StatusUpdate);
		
			//Logout of Wiki
			Logout();

			System.out.println("INFO: End of News Level 2 testRepostCommentOwnStatus");
		}
		
		@Test (dependsOnMethods = { "testRepostCommentOwnStatus" })
		public void testVerifyRepostComment() throws Exception {

			System.out.println("INFO: Start of News Level 2 testVerifyRepostComment");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
			//Check News Feed
		
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			String RepostComment = "css=div.activityStreamNewsItemContainer ul.lotusCommentList li.lotusCommentItem div.lotusPost div.lotusPostContent div.lotusPostDetails div.asLinkContainer:contains('"+StatusComment+"')";
			
			assertTrue("Comment text not showing in repost item", driver.isElementPresent(RepostComment));
			
			filterBy_StatusUpdates("My Updates");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			//Logout of Wiki
			Logout();

			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
			//Check News Feed
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			filterBy_StatusUpdates("People");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			filterBy("People");
			
			assertTrue("Repost text not present", driver.isTextPresent(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostComment));
			
			//Logout of Wiki
			Logout();
			
			System.out.println("INFO: End of News Level 2 testVerifyRepostComment");
		}
		
		//@Test (dependsOnMethods = { "testVerifyRepostComment" }, groups={"notbvt3"})
		public void testRepostOwnStatus_Hashtag() throws Exception {

			System.out.println("INFO: Start of News Level 2 testRepostOwnStatus_Hashtag");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			StatusUpdate = FVT_NewsData.UpdateStatus_Hashtag+CommonMethods.genDateBasedRandVal();
			
			//verify sharebox displays
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
			StatusUpdate = statusUpdate(StatusUpdate);
			
			//Repost
			
			repostStatus(StatusUpdate);
		
			//Logout of Wiki
			Logout();

			System.out.println("INFO: End of News Level 2 testRepostOwnStatus_Hashtag");
		}
		
		//@Test (dependsOnMethods = { "testRepostOwnStatus_Hashtag" }, groups={"notbvt3"})
		public void testVerifyRepost_Hashtag() throws Exception {

			System.out.println("INFO: Start of News Level 2 testVerifyRepost_Hashtag");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
			//Check News Feed
		
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			String RepostHashtag = "css=div.lotusPostContent div.lotusPostAction div.activityStreamSummary div.lotusPostContent a:contains('#fvt')";

			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			filterBy_StatusUpdates("My Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			//Logout of Wiki
			Logout();

			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
			//Check News Feed
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			filterBy_StatusUpdates("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			filterBy("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Hashtag not a link!", driver.isElementPresent(RepostHashtag));
			
			//Logout of Wiki
			Logout();
			
			System.out.println("INFO: End of News Level 2 testVerifyRepost_Hashtag");
		}
		
		
		//@Test 
		public void testRepostOwnStatus_TextFile() throws Exception {

			System.out.println("INFO: Start of News Level 2 testRepostOwnStatus_TextFile");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			StatusUpdate = FVT_NewsData.UpdateStatus+CommonMethods.genDateBasedRandVal();
			
			//verify sharebox displays
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
			StatusUpdate = statusUpdate_FileAttached(StatusUpdate, "Text.txt");
			
			String ExpectedValue = "The message was successfully posted.";
			assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
			
			
			//Verify that update appears in AS
			//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
			
			//Repost
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
			
			builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).click().build().perform();
					
			clickLink(FVT_NewsObjects.Repost);
		
			//Logout of Wiki
			Logout();

			System.out.println("INFO: End of News Level 2 testRepostOwnStatus_TextFile");
		}
		
		
		//@Test (dependsOnMethods = { "testRepostOwnStatus_TextFile" })
		public void testVerifyRepost_TextFile() throws Exception {

			System.out.println("INFO: Start of News Level 2 testVerifyRepost_TextFile");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
			//Check News Feed
		
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			String TextFileRepost = "css=div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostDetails h4.lotusTitle a:contains('Text.txt')";
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			filterBy_StatusUpdates("My Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			//Logout of Wiki
			Logout();

			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
			//Check News Feed
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			filterBy_StatusUpdates("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			filterBy("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(TextFileRepost));
			
			//Logout of Wiki
			Logout();
			
			System.out.println("INFO: End of News Level 2 testVerifyRepost_TextFile");
		}
		
		//@Test 
		public void testRepostOwnStatus_ImageFile() throws Exception {

			System.out.println("INFO: Start of News Level 2 testRepostOwnStatus_ImageFile");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
			
			StatusUpdate = FVT_NewsData.UpdateStatus_Hashtag+CommonMethods.genDateBasedRandVal();
			
			//verify sharebox displays
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("FAIL: Sharebox not visible", driver.isElementPresent(FVT_NewsObjects.EnterStatusUpdate));
		
			StatusUpdate = statusUpdate_FileAttached(StatusUpdate, "Desert.jpg");
			
			String ExpectedValue = "The message was successfully posted.";
			assertTrue("FAIL: The status message has not posted correctly", driver.isTextPresent(ExpectedValue));
			
			//Verify that update appears in AS
			//assertTrue("FAIL: Status Update not appearing in Activty Stream", driver.isTextPresent(StatusUpdate));
			
			//Repost
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			String StatusUpdatePost = "css=div.lotusPostContent:contains('"+StatusUpdate+"')";
			
			builder.moveToElement((WebElement) driver.getSingleElement(StatusUpdatePost).getBackingObject()).click().build().perform();
					
			clickLink(FVT_NewsObjects.Repost);
		
			//Logout of Wiki
			Logout();

			System.out.println("INFO: End of News Level 2 testRepostOwnStatus_ImageFile");
		}
		
		//@Test (dependsOnMethods = { "testRepostOwnStatus_ImageFile" })
		public void testVerifyRepost_ImageFile() throws Exception {

			System.out.println("INFO: Start of News Level 2 testVerifyRepost_ImageFile");

			//Load the component
			LoadComponent(CommonObjects.ComponentNews);

			//Login as a user (ie. Amy Jones66)
			//testUser1 = userAllocator.getUser();
			
			Login(testUser1);
			//Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
			//Check News Feed
		
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			String RepostText = driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getText();
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			WebDriver wd = (WebDriver) driver.getBackingObject();
			
			Actions builder = new Actions(wd);
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			String RepostUpdate = FVT_NewsObjects.RepostUpdate.replace("_Replace_", StatusUpdate);

			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			String ImageFileRepost = "css=div.activityStreamNewsItemContainer div.lotusPostContent div.lotusPostDetails h4.lotusTitle a:contains('Desert.jpg')";
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			filterBy_StatusUpdates("My Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			//Logout of Wiki
			Logout();

			Login(testUser2);
			//Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
			//Check News Feed
			
			clickLink(FVT_NewsObjects.StatusUpdates);
			
			filterBy_StatusUpdates("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			filterBy_StatusUpdates("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			clickLink(FVT_NewsObjects.AllUpdates);
			
			filterBy("All");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			filterBy("Status Updates");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			filterBy("People");
			
			assertTrue("Repost text not present", RepostText.equalsIgnoreCase(testUser1.getDisplayName()+" reposted:"));
			
			//assertTrue("Repost icon not present", driver.isElementPresent(FVT_NewsObjects.RepostIcon));
			
			builder.moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.FocusOnComponent).getBackingObject()).moveToElement((WebElement) driver.getFirstElement(FVT_NewsObjects.RepostIcon).getBackingObject()).build().perform();
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(RepostUpdate));
			
			assertTrue("Update text not showing in repost item", driver.isElementPresent(ImageFileRepost));
			
			//Logout of Wiki
			Logout();
			
			System.out.println("INFO: End of News Level 2 testVerifyRepost_ImageFile");
		}
		
		
	
}
