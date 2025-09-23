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

package com.ibm.atmn.homepagefvt.tasks.news;

import static org.testng.AssertJUnit.*;

import java.awt.event.KeyEvent;

import org.testng.Assert;

import com.ibm.atmn.homepagefvt.tasks.common.FVT_CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsData;
import com.ibm.atmn.homepagefvt.appobjects.news.NewsData;
import com.ibm.atmn.homepagefvt.appobjects.news.NewsObjects;
import com.ibm.atmn.homepagefvt.appobjects.profiles.FVT_ProfilesData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.news.FVT_NewsObjects;



public class FVT_CommonNewsMethods extends FVT_CommonMethods{

	/**Click specified link*/
	/*public void clickLink(String Link) throws Exception{
		//Click the link provided
		selenium.click(Link);
		Thread.sleep(3500);
	}*/
	
	public void AddBoardEntry() throws Exception{
		//Add an entry to the board
		sel.type(NewsObjects.ProfilesBoard, FVT_ProfilesData.ProfilesBoardEntryNewsBVT);
		clickLink(NewsObjects.PostStatus);
	}
	
	
	/* public void VerifyNewsStory(String DateTimeStamp, String component, String AccessType) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username77, CommonData.IC_LDAP_Password77);
		
		//Click on the updates tab
		clickLink("link=Updates");
		
		//Click on the Discover link
		clickLink("link=Discover");
		
		//Filter by component from Drop-down
		FilterBy(component);
		
		
		if(AccessType=="Private"){
			//Verify that the activity is there
				assertFalse(sel.isTextPresent("FVT Automation Test Activity "+DateTimeStamp));		
			}
			else {
				//Verify that the activity is there
				assertTrue(sel.isTextPresent("FVT Automation Test Activity "+DateTimeStamp));	
			}
		
	
		//Logout
		Logout();
				
	} */
	
	
	public void navigateHomepage(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="I'm following"){
			//Click on the Discover link
			clickLink("link=I'm following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Discover link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Discover link
			clickLink("link=Saved");
			}
		else if (NavOption=="For me"){
			//Click on the Discover link
			clickLink("link=For me");
			}
		else if (NavOption=="Discover"){
		//Click on the Discover link
		clickLink("link=Discover");
		}
			
		
		//Filter by component from Drop-down
		FilterBy(component);
	
		//Logout
		Logout();
		
		
				
	}
	
	
	public void VerifyNewsStory(String NewsStory, String NavOption, String component, boolean Visible) throws Exception{
		//Load the URL to the people view in Homepage
		LoadComponent(CommonObjects.ComponentNews);
		
		//Login again
		Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
		
		
		if (NavOption=="I'm following"){
			//Click on the Discover link
			clickLink("link=I'm following");
		}
		else if (NavOption=="Action Required"){
			//Click on the Discover link
			clickLink("link=Action Required");
			}
		else if (NavOption=="Saved"){
			//Click on the Discover link
			clickLink("link=Saved");
			}
		else if (NavOption=="For me"){
			//Click on the Discover link
			clickLink("link=For me");
			}
		else if (NavOption=="Discover"){
		//Click on the Discover link
		clickLink("link=Discover");
		}
			
		
		//Filter by component from Drop-down
		FilterBy(component);
		
		Thread.sleep(2000);
		
		if(Visible==false){
			//Verify that the news story is there
			if(sel.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
			}
			//	Assert.assertFalse(sel.isTextPresent(NewsStory));	
			//}
		else{
			
			if(!sel.isTextPresent(NewsStory)){
				Assert.fail("fail");
			}
				//Verify that the news story is there
				//Assert.assertTrue(sel.isTextPresent(NewsStory));	
		}

		//Logout
		Logout();
		
		
				
	}
	
	public void FilterBy(String component) throws Exception{
	
		// if else statements that selects component from "filter by" drop-down menu
		if(component=="All Updates"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterAll);
		}
		else if(component=="Status Updates"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterStatusUpdates);
		}
		else if(component=="Activities"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterActivities);
		}
		else if(component=="Blogs"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterBlogs);
		}
		else if(component=="Bookmarks"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterBookmarks);
		}
		else if(component=="Communities"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterCommunities);
		}
		else if(component=="Files"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterFiles);
		}
		else if(component=="Forums"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterForums);
		}
		else if(component=="People"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterProfiles);
		}
		else if(component=="Wikis"){
			sel.select(FVT_NewsObjects.FilterBy, FVT_NewsObjects.FilterWikis);
		}
		

		
		
	}
	
	public String StatusUpdate() throws Exception{
		
		
		String StatusUpdate = FVT_NewsData.UpdateStatus;
		
		cautiousFocus(FVT_NewsObjects.StatusUpdate);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_F));
		Thread.sleep(1000);
		//Enter and Save a status update
		sel.type(FVT_NewsObjects.StatusUpdate, StatusUpdate);
		clickLink(FVT_NewsObjects.PostStatus);
		
		return StatusUpdate;
		
	}
	
	public String CommentOnStatus_EE(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		Thread.sleep(2000);
		sel.type(FVT_NewsObjects.EE_StatusComment, StatusComment);
		clickLink(CommonObjects.SaveButton);
		return StatusComment;

	}
	
	public String CommentOnStatus(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		
		// Click on the Add a comment link for entry
		clickLink("link=Comment");
		
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.StatusComment));
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.PostComment));
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.CancelComment));
		
		cautiousFocus(FVT_NewsObjects.StatusComment);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_F));
		Thread.sleep(1000);
		//Enter and Save a status update
		sel.type(FVT_NewsObjects.StatusComment, StatusComment);
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate+"')");
		cautiousFocus(FVT_NewsObjects.StatusComment);
		clickLink(FVT_NewsObjects.PostComment);
		
		return StatusComment;
	

	}
	
	public String CancelComment(String StatusUpdate) throws Exception {
		
		String StatusComment = FVT_NewsData.StatusComment;
		
		
		// Click on the Add a comment link for entry
		clickLink("link=Comment");
		
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.StatusComment));
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.PostComment));
		//
		assertTrue("Fail: Link still there!!", sel.isElementPresent(FVT_NewsObjects.CancelComment));
		
		cautiousFocus(FVT_NewsObjects.StatusComment);
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_SPACE));
		Thread.sleep(1000);
		sel.keyPressNative(String.valueOf(KeyEvent.VK_F));
		Thread.sleep(1000);
		//Enter and Save a status update
		sel.type(FVT_NewsObjects.StatusComment, StatusComment);
		cautiousFocus("css=div.lotusPostContent:contains('"+StatusUpdate+"')");
		cautiousFocus(FVT_NewsObjects.StatusComment);
		clickLink(FVT_NewsObjects.CancelComment);
		
		return StatusComment;
	

	}
	
	public void VerifyStatusUpdatedInDiscover() throws Exception{
		//Click on the Status Updates link to open the Status Updates page
		clickLink(NewsObjects.LeftNavDiscover);
		
		//Now verify that the update is displayed
		assertTrue("FAIL: the status update is not appearing as expected", sel.isTextPresent(CommonData.LDAP_FullUsername+"1"));
		assertTrue("FAIL: the status update is not appearing as expected", sel.isTextPresent(NewsData.UpdateStatus));
		
	}
	

	
	public void AddACommentToEntry() throws Exception {
		// Click on the Add a comment link for entry
		clickLink(NewsObjects.BlogsAddACommentLink);

		// Fill in the comment form
		sel.type(NewsObjects.BlogsCommentTextArea, NewsData.BlogsCommentText);

		// Submit comment
		clickLink(NewsObjects.BlogsCommentSubmit);

	}
	


		
}
