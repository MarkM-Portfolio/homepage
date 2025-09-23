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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis;


import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;
import com.thoughtworks.selenium.Selenium;



//Created by Johann Ott

public class TestOfFVT_Level2_Wikis extends FVT_WikisMethods{
	
	private static String PublicWikiCommunity = "Moderated Level 2 FVT Community 2331050461";
	
	public void testDeleteWiki_PublicComm () throws Exception {
		System.out.println("INFO: Start of testDeleteWiki_PublicComm");	
		
		//Load the component
		LoadComponent(CommonObjects.ComponentCommunities);
			
		//Login as a user to create the wiki (ie. Amy Jones450)
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
				
		assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
		
		clickLink(FVT_CommunitiesObjects.ImAnOwner);
	
		//Go to Public community
		clickLink("link=" + PublicWikiCommunity);
				
		//Recommend the current page
		DeleteCommunityWiki();

		//Logout of Wiki
		Logout();	
	  	
		System.out.println("INFO: End of testDeleteWiki_PublicComm");
	}
	
	 @Test
	 public void testAtMention () throws Exception {
		System.out.println("INFO: Start of testAtMention");	
		
		User testUser1;
		User testUser2;
		
		testUser1 = userAllocator.getUser(this);
		testUser2 = userAllocator.getUser(this);
		
		//Load the component
		LoadComponent(CommonObjects.ComponentHomepage);
			
		//Login as a user
		Login(testUser1);			
				
		//Open @Mentions		
		clickLink("xpath=//*[@id='_atMentions']");
	
		//Click on the @Mentions Box
		clickLink("xpath=//*[@id='mentionstextAreaNode_0']");
		
		//Enter text and user to mention
		driver.getSingleElement("xpath=//*[@id='mentionstextAreaNode_0']").click();
		driver.getSingleElement("xpath=//*[@id='mentionstextAreaNode_0']").type("This is an FVT test post @");
		
		//User must be inputted by character to ensure Selenium can find specific users
		for(int x = 0; x<testUser2.getDisplayName().length(); x++){
			
			char c = testUser2.getDisplayName().charAt(x);
			
			String s = Character.toString(c);

			driver.getSingleElement("xpath=//*[@id='mentionstextAreaNode_0']").type(s);
			
			sleep(500);
			
			if(s.equals(" ")){
				
				sleep(10000);
			}
			
		}
			
		//*[@id="mentionsTypeaheadField_0_popup0"]
		
		driver.getSingleElement("xpath=//*[@id='mentionsTypeaheadField_0_popup1']").click();
		// <li id="mentionsTypeaheadField_0_popup0" class="dijitMenuItem"
		
		//Click on selected user to mention
		//driver.getSingleElement(FVT_CommunitiesObjects.selectedUserIdentifier).click();

		//Logout
		LogoutAndClose();	
	  	
		System.out.println("INFO: End of testAtMention");
	}
	
}

