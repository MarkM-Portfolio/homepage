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

package com.ibm.atmn.wd_homepagefvt.testcases.wikis.imfollowing.tags;

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_ImFollowing_Tags_Mod_Comm_Wikis extends FVT_WikisMethods{
	
		
		private static String ModeratedWikiCommunity = "";
	
		//Create a moderated comm wiki
		@Test
		public void testFollowTag() throws Exception {
			
			
			System.out.println("INFO: Start of Blogs FVT_Level_2 testFollowTag");
			
			// Login to communities
			LoadComponent(CommonObjects.ComponentNews);
				
			//Login(testUser1);
			Login(CommonData.IC_LDAP_Username451, CommonData.IC_LDAP_Password451);
			
			//Follow Tag
			followTag(CommonData.AutomationTag);
			
			System.out.println("INFO: End of Blogs FVT_Level_2 testFollowTag");
			
		}
	
	
		@Test (dependsOnMethods = { "testFollowTag" })
		public void testCreateANewWiki_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWiki_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			clickLink(FVT_CommunitiesObjects.ImAnOwner);
			
			// Created a new community with moderated access

			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a moderated community
			ModeratedWikiCommunity = CreateNewCommunity(FVT_CommunitiesData.ModeratedCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption2, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
			//Add the wiki widget
			AddWidgetToOverview("Wikis");
							
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory("A "+ModeratedWikiCommunity+" community wiki was created.","Wikis","automation", true, true);

			System.out.println("INFO: End of testCreateANewWiki_ModeratedComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWiki_ModeratedComm" })
		public void testCreateANewWikiPage_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWikiPage_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			Thread.sleep(1000);
			
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Mod_Comm_Wiki, FVT_WikisData.New_Content_For_Mod_Comm_Wiki);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Mod_Comm_Wiki, FVT_WikisData.New_Content_For_Mod_Comm_Wiki);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Mod_Comm_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Mod_Comm_Wiki);
			
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory(" created a wiki page named "+FVT_WikisData.New_Peer_Page_For_Mod_Comm_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis", "automation",true, true);
			
			System.out.println("INFO: End of testCreateANewWikiPage_ModeratedComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWikiPage_ModeratedComm" })
		public void testEditAWikiPage_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testEditAWikiPage_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Mod_Comm_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).clear();
			driver.getSingleElement(WikisObjects.Page_Name_Textfield_In_Editor).type(FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			//Assert.assertTrue("FAIL: Changed page title isn't correct", driver.isTextPresent(FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki));
			
			//Verify that content was added correctly
			//VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
			//Logout of Wiki
			Logout();	
			
			driver.close();
		  	
			verifyImFollowingTagsNewsStory(" edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis",  "automation",true, true);
			
			System.out.println("INFO: End of testEditAWikiPage_ModeratedComm");
		}
		
		
		@Test (dependsOnMethods = { "testEditAWikiPage_ModeratedComm" })
		public void testAddATagToWiki_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testAddATagToWiki_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", driver.isTextPresent(FVT_WikisData.Tag_For_Public_Wiki)); 
			
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory(" tagged the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis", "automation",true, false);
		  	
			System.out.println("INFO: End of testAddATagToWiki_ModeratedComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_ModeratedComm" })
		public void testRecommendAWiki_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testRecommendAWiki_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki);
			
			//Like the current page
			LikeCurrentPage();
						
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory(" liked the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis", "automation",true, true);
		  	
			System.out.println("INFO: End of testRecommendAWiki_ModeratedComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_ModeratedComm" })
		public void testAddCommentToWiki_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testAddCommentToWiki_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Mod_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Mod_Comm_Wiki);
			
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory(" commented on the wiki page Re: "+FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis", "automation",true, true);
		  	
			System.out.println("INFO: End of testAddCommentToWiki_ModeratedComm");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToWiki_ModeratedComm" })
		public void testEditCommentWiki_ModeratedComm () throws Exception {
			System.out.println("INFO: Start of testEditCommentWiki_ModeratedComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", driver.isTextPresent("Communities"));	
			
			//Open wiki in community
			openCommunity(ModeratedWikiCommunity, FVT_CommunitiesObjects.ImAnOwner, FVT_CommunitiesObjects.LeftNavWiki);
			
			//Go to index
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki);
			
			//Open Comments Page
			clickLink(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			editComment(FVT_WikisData.Comment_For_Mod_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Mod_Comm_Wiki);
			
			//Logout of Wiki
			Logout();	
			
			driver.close();
			
			verifyImFollowingTagsNewsStory("updated the wiki page Re: "+FVT_WikisData.New_Peer_Page_For_Edited_Mod_Comm_Wiki+" in the "+ModeratedWikiCommunity+" wiki.","Wikis","automation",true, true);
		  	
			System.out.println("INFO: End of testEditCommentWiki_ModeratedComm");
		}
		

		
}
	
		