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
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisData;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.FVT_WikisObjects;
import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.ibm.atmn.wd_homepagefvt.tasks.wikis.FVT_WikisMethods;



//Created by Johann Ott

public class TestOfFVT_Level2_Wikis extends FVT_WikisMethods{
	
	
	@Test 
	public void testRecommendAPageForPublicWiki () throws Exception {
		System.out.println("INFO: Start of testRecommendAPageForPublicWiki");	

		//Load the component
		LoadComponent(CommonObjects.ComponentWikis);
		
		//Enter creators username & password
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);	
		
		//Open newly created Wiki
		OpenWikiFromMyWikisTab("FVT Level 2 Public Wiki 04118093617");

		//Like Wiki Page
		LikeCurrentPage();
		
	  	//Logout of Wiki
	  	Logout();
	  	
	  	driver.close();
	  	
		verifyNewsStory(" liked the wiki page Welcome to "+FVT_WikisData.CI_Box_Public_Wiki+" in the "+FVT_WikisData.CI_Box_Public_Wiki+" wiki.","Discover","I'm Following", true);
	  	
		System.out.println("INFO: End of testRecommendAPageForPublicWiki");
	}
}

