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

package com.ibm.atmn.wd_homepagefvt.tasks.atmentions;

import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import com.ibm.atmn.waffle.core.Element;
import com.ibm.atmn.waffle.extensions.user.User;
import com.ibm.atmn.wd_homepagefvt.tasks.communities.FVT_CommunitiesMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsData;
import com.ibm.atmn.wd_homepagefvt.appobjects.atmentions.FVT_AtMentionsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.homepage.FVT_HomepageObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.news.FVT_NewsObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.FVT_ProfilesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.profiles.ProfilesObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

@SuppressWarnings("unused")
public class FVT_AtMentionsMethods extends FVT_CommunitiesMethods {
	
	public String mentionUser(User user, String message, String shareBoxType ) throws Exception{

		String name = user.getDisplayName();
		
		driver.getSingleElement(shareBoxType).type(message);
		
		//Username must be inputted by character to ensure Selenium can find specific users
		for(int x = 0; x<name.length(); x++){
			
			char c = name.charAt(x);
			
			String s = Character.toString(c);

			driver.getSingleElement(shareBoxType).type(s);
			
			sleep(500);
			
			if(s.equals(" ")){
				
				sleep(10000);
			}
		}	
		
		boolean Found = false;

		// Get the dropdown element
		WebDriver wd = (WebDriver) driver.getBackingObject();
		// TODO make generic to allow use in all type-ahead Person or Community fields
		//WebElement NewElement = wd.findElement(By.id("lconn_share_widget_MemberInput_0_people_dropdown"));
		//id="lconn_share_widget_MemberInput_0_communities_popup"
		WebElement NewElement = wd.findElement(By.cssSelector("[id^='mentionsTypeaheadNode_'][id$='_popup']"));

		// First check that the user required is present in the returned text

		// Return all the option selectors from the dropdown
		List<WebElement> DList = NewElement.findElements(By.cssSelector("[class='dijitMenuItem'][role='option']"));
		Reporter.log("Getting all links from dropdown element");
		String test = "";
		String test2 = "";
		Integer newint = 0;
		for (WebElement a:DList){
			Reporter.log(a.toString());
			Reporter.log("Id " + a.getAttribute("id"));	
			Reporter.log("Text " + a.getText());
			test = a.getText();
			test = test.trim();

			// Example return string : Amy Jones2 <ajones2@janet.iris.com>
			String[] test1 = test.split("<");
			test2 = test1[0].trim();			
			newint = test2.compareTo(user.getDisplayName());

			// Check for the user text and select that user
			if ( newint == 0){
				Reporter.log("Selecting " + a.getText());
				a.click();
				Found = true;
				break;
			}
		}
		if (Found == false){
			throw new Exception(user.getDisplayName() + " not selected successfully.");
			//System.out.println((user.getDisplayName() + " may not have been selected successfully."));
			//clickLink(FVT_AtMentionsObjects.Post);
		}
		
		return message+user.getDisplayName();

	
	}
	
	
	public void mentionMultipleUsers(String user, String message, String shareBoxType ) throws Exception{

		String name = user;
		
		driver.getSingleElement(shareBoxType).type(" @");
		
		//Username must be inputted by character to ensure Selenium can find specific users
		for(int x = 0; x<name.length(); x++){
			
			char c = name.charAt(x);
			
			String s = Character.toString(c);

			driver.getSingleElement(shareBoxType).type(s);
			
			sleep(500);
			
			if(s.equals(" ")){
				
				sleep(10000);
			}
		}
		
		boolean Found = false;

		// Get the dropdown element
		WebDriver wd = (WebDriver) driver.getBackingObject();
		// TODO make generic to allow use in all type-ahead Person or Community fields
		//WebElement NewElement = wd.findElement(By.id("lconn_share_widget_MemberInput_0_people_dropdown"));
		//id="lconn_share_widget_MemberInput_0_communities_popup"
		WebElement NewElement = wd.findElement(By.cssSelector("[id^='mentionsTypeaheadField_'][id$='_popup']"));

		// First check that the user required is present in the returned text

		// Return all the option selectors from the dropdown
		List<WebElement> DList = NewElement.findElements(By.cssSelector("[class='dijitMenuItem'][role='option']"));
		Reporter.log("Getting all links from dropdown element");
		String test = "";
		String test2 = "";
		Integer newint = 0;
		for (WebElement a:DList){
			Reporter.log(a.toString());
			Reporter.log("Id " + a.getAttribute("id"));	
			Reporter.log("Text " + a.getText());
			test = a.getText();
			test = test.trim();

			// Example return string : Amy Jones2 <ajones2@janet.iris.com>
			String[] test1 = test.split("<");
			test2 = test1[0].trim();			
			newint = test2.compareTo(user);

			// Check for the user text and select that user
			if ( newint == 0){
				Reporter.log("Selecting " + a.getText());
				a.click();
				Found = true;
				break;
			}
		}
		if (Found == false){
			throw new Exception(user + " not selected successfully.");
			//System.out.println((user.getDisplayName() + " may not have been selected successfully."));
			//clickLink(FVT_AtMentionsObjects.Post);
		}

	
	}
	
	
	public void searchUser(User user) throws Exception{
		
		//Search for user by name
		
		driver.getFirstElement(ProfilesObjects.ProfilesSearch).click();
		driver.getFirstElement(ProfilesObjects.ProfilesSearchForUser).type(user.getDisplayName());
		clickLink(ProfilesObjects.ProfileSearch);
		
		if (driver.isElementPresent(ProfilesObjects.EditProfileHeader)){
			Thread.sleep(500);
		}
		else{
			Thread.sleep(3000);
		}
		assertTrue(driver.isTextPresent(user.getDisplayName()));
	
		// Click on user profile name in search results 
		
		clickLink(FVT_ProfilesObjects.SelectProfile);
		

	}
	
	
	public void LikeStatus() throws Exception{
		
		clickLink(FVT_HomepageObjects.Discover);
		
		filterBy("Status Updates");
		
		smartSleep(FVT_NewsObjects.Like);
		
		driver.getFirstElement(FVT_NewsObjects.Like).click();
		
		sleep(500);

	}
}