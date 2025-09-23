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

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.homepagefvt.tasks.wikis.FVT_WikisMethods;
import static org.testng.AssertJUnit.*;
//Created by Johann Ott

public class TestOfFVT_Level2_Wikis extends FVT_WikisMethods{
	
	private static String PublicWikiCommunity = "Public Level 2 FVT Community 12346145340";
	
	@Test 
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

