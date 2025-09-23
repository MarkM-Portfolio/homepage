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

import org.junit.Assert;
import org.testng.annotations.Test;

import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.common.NewsStoryData;
import com.ibm.atmn.wd_homepagefvt.appobjects.communities.FVT_CommunitiesObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;



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
}

