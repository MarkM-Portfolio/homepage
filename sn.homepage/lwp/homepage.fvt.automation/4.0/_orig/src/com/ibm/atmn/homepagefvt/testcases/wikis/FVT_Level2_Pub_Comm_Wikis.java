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

package com.ibm.atmn.homepagefvt.testcases.wikis;

import org.junit.Assert;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.*;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.homepagefvt.tasks.common.CommonMethods;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesData;
import com.ibm.atmn.homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.Data;
import com.ibm.atmn.homepagefvt.tasks.wikis.FVT_WikisMethods;

	/*
 	* This is the FVT Public Wikis Testcases
	* Created By: Johann Ott
	* Date: 28/11/2011
	*/ 

public class FVT_Level2_Pub_Comm_Wikis extends FVT_WikisMethods{
	
		
		private static String PublicWikiCommunity = "";
	
		//Create a moderated comm wiki
		@Test
		public void testCreateANewWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
			
			// Created a new community with Public access

			//Now Get the DateTime
			String DateTimeStamp = CommonMethods.genDateBasedRandVal();
			
			//Create a Public community
			PublicWikiCommunity = CreateNewCommunity(FVT_CommunitiesData.PublicCommunityName+DateTimeStamp, FVT_CommunitiesData.CommunityHandle+DateTimeStamp, FVT_CommunitiesObjects.CommunityAccessOption1, "Members", FVT_CommunitiesObjects.CommunityMembersTypeAhead, FVT_CommunitiesObjects.selectedUserIdentifier, CommonData.LDAP_User_Typeahead+451, FVT_CommunitiesObjects.fullUserSearchIdentifier);
				
			//Add the wiki widget
			AddWidgetToOverview("Wikis");
							
			//Logout of Wiki
			Logout();	
			
			VerifyNewsStory("A new "+PublicWikiCommunity+" community wiki was created.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testCreateANewWiki_PublicComm");
		}
		
		
		//Create a moderated comm wiki page
		@Test (dependsOnMethods = { "testCreateANewWiki_PublicComm" })
		public void testCreateANewWikiPage_PublicComm () throws Exception {
			System.out.println("INFO: Start of testCreateANewWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Select rootpage to create child pages
			AddAPeer(FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki, FVT_WikisData.New_Content_For_Pub_Comm_Wiki);
			
			//Add a new Peer pages to the wiki homepage
			AddAChild(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki, FVT_WikisData.New_Content_For_Pub_Comm_Wiki);
			
			//Verify pages have been created
			VerifyPageExists(FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki);
			VerifyPageExists(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
				
			//Logout of Wiki
			Logout();	
			
			VerifyNewsStory(" created a new wiki page named "+FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testCreateANewWikiPage_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testCreateANewWikiPage_PublicComm" })
		public void testEditAWikiPage_PublicComm () throws Exception {
			
			System.out.println("INFO: Start of testEditAWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Pub_Comm_Wiki);
		
			//Edit the current page
			clickLink(WikisObjects.Edit_Button);
			
			//Validate UI of wiki editor
			ValidateWikiEditMode();
			
			//Validate tagging UI
			ValidateTaggingUIInEditMode();
			
			//Edit page title in Edit mode
			sel.type(WikisObjects.Page_Name_Textfield_In_Editor, FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Add some content in CKEditor
			AddContentintheEditorforanExistingPage(FVT_WikisData.New_Content_For_Public_Wiki);
			
			//Verify that new page title is correct
			Assert.assertTrue("FAIL: Changed page title isn't correct", sel.getText(WikisObjects.WikiHomePageTitleField).equals(FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki));
			
			//Verify that content was added correctly
			VerifyAddedContentInHomepage(FVT_WikisData.New_Content_For_Public_Wiki, CommonData.IC_LDAP_Username450);
			
				
			//Logout of Wiki
			Logout();	
			
			VerifyNewsStory(" edited the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testEditAWikiPage_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testEditAWikiPage_PublicComm" })
		public void testAddATagToWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testAddATagToWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Go to index
			clickLink("link=Index");
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Select 'Add a tag' link
			OpenTaggingEditor();
			
			//Add new tag to page
			AddATag(FVT_WikisData.Tag_For_Public_Wiki);
			
			//Verify tag has been added
			Assert.assertTrue("FAIL: Tag hasn't been added correctly", sel.isTextPresent(Data.Tag_For_Public_Wiki)); 
			
			//Logout of Wiki
			Logout();
			
			VerifyNewsStory(" tagged the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" with "+FVT_WikisData.Tag_For_Public_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testAddATagToWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testAddATagToWiki_PublicComm" })
		public void testRecommendAWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testRecommendAWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to moderated community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Go to index
			clickLink("link=Index");
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Recommend the current page
			RecommendCurrentPage();
						
			//Logout of Wiki
			Logout();	
		  	
			VerifyNewsStory(" recommended the wiki page "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
			
			System.out.println("INFO: End of testRecommendAWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testRecommendAWiki_PublicComm" })
		public void testAddCommentToWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testAddCommentToWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Go to index
			clickLink("link=Index");
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki);
			
			//Open Comments Page
			clickObject(FVT_WikisObjects.Comments_Tab);
			
			//Add a comment
			AddAComment(FVT_WikisData.Comment_For_Pub_Comm_Wiki);
	
			//Verify added comment
			VerifyCommentIsAdded(FVT_WikisData.Comment_For_Pub_Comm_Wiki);
			
			//Logout of Wiki
			Logout();	
			
			VerifyNewsStory(" commented on the wiki page Re: "+FVT_WikisData.New_Peer_Page_For_Edited_Pub_Comm_Wiki+" in the "+PublicWikiCommunity+" wiki.","Discover","Wikis", true);
		  	
			System.out.println("INFO: End of testAddCommentToWiki_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testAddCommentToWiki_PublicComm" }, groups = "broken")
		public void testDeleteWikiPage_PublicComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWikiPage_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
			
			//Go to wikis
			clickLink("link=Wiki");
			
			//Go to index
			clickLink("link=Index");
			
			//Open newly created peer page
			clickLink("link=" + FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
			//Delete specified page
			deletePage(FVT_WikisData.New_Child_Page_For_Pub_Comm_Wiki);
			
			//Open Page Index to see all available pages
			clickLink(FVT_WikisObjects.Index_Link);
			
			//Verify wiki page has been deleted successfully
			Assert.assertTrue("FAIL: Wiki page still exists", !sel.isTextPresent(FVT_WikisData.New_Child_Page_For_Mod_Comm_Wiki));
			
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWikiPage_PublicComm");
		}
		
		@Test (dependsOnMethods = { "testDeleteWikiPage_PublicComm" }, groups = "broken")
		public void testDeleteWiki_PublicComm () throws Exception {
			System.out.println("INFO: Start of testDeleteWiki_PublicComm");	
			
			//Load the component
			LoadComponent(CommonObjects.ComponentCommunities);
				
			//Login as a user to create the wiki (ie. Amy Jones450)
			Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);			
					
			assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
			
			clickLink("link=I'm an Owner");
		
			//Go to Public community
			clickLink("link=" + PublicWikiCommunity);
					
			//Recommend the current page
			DeleteCommunityWiki();
	
			//Logout of Wiki
			Logout();	
		  	
			System.out.println("INFO: End of testDeleteWiki_PublicComm");
		}
		
}
	
		