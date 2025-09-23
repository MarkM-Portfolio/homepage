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

import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;


public class FVT_Microblogs_Inline_Liking extends FVT_AtMentionsMethods{

	User testUser1;
	User testUser2;
	User testUser3;
	User testUser4;
	User testUser5;
	
	@Test
	public void Inline_Liking_013_like_Control_Appears () throws Exception {
		 
		testUser1 = userAllocator.getUser(this);

		 		
		System.out.println("INFO: Start of test Microblogs_Inline_Liking_013_like_Control_Appears_in_Activity ");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open Status Updates	
		clickLink(FVT_HomepageObjects.StatusUpdates);
	
		//Click on the Status updates
		driver.getFirstElement(FVT_AtMentionsObjects.EmbeddedSharebox).type(FVT_NewsData.UpdateStatus);
		
		//Click post
		clickLink(FVT_AtMentionsObjects.Post);
		
		//refreash page
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//verify Like button appears
		assertTrue("Fail - Like not present", isTextPresent("Like"));
		
		//logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Microblogs_Inline_Liking_014_like_Control_Appears_in_Activity ");	
		
	}
	
	@Test (dependsOnMethods = { "Inline_Liking_013_like_Control_Appears" })
	public void Inline_Liking_020_Number_of_likes () throws Exception {
		
		testUser2 = userAllocator.getUser(this);
		testUser3 = userAllocator.getUser(this);
		testUser4 = userAllocator.getUser(this);
		testUser5 = userAllocator.getUser(this);
		
		System.out.println("INFO: Start of test Microblogs_Inline_Liking_020_Number_of_likes_Status_Update ");	
	
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open Status Updates	
		clickLink(FVT_HomepageObjects.StatusUpdates);
		
		//Like the status
		driver.getFirstElement("link=Like").click();
		
		//Logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Like status with user2
		
		//Login
		Login(testUser2);
		
		LikeStatus();
		
		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Like status with user3
		
		//Login
		Login(testUser3);
		
		//Like the status
		LikeStatus();
		
		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Like status with user4
		
		//Login
		Login(testUser4);
		
		//Like the status
		LikeStatus();
		
		//logout
		LogoutAndClose();
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);

		//Verify status has been liked 4 times
		
		//Login
		Login(testUser5);
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Status Updates");
		
		//Check that Likes = 4
		assertTrue("FAIL: Number of likes not equal to '4'", driver.getFirstElement(FVT_NewsObjects.Likes).getText().compareToIgnoreCase("4") == 0);
		
		//logout
		LogoutAndClose();
		
		System.out.println("INFO: End of test Microblogs_Inline_Liking_020_Number_of_likes_Status_Update ");	
	}
}
