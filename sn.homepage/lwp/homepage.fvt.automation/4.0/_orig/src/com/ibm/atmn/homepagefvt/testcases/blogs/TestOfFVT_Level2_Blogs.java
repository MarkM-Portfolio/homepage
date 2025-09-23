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

package com.ibm.atmn.homepagefvt.testcases.blogs;


import org.testng.annotations.Test;

import com.ibm.atmn.homepagefvt.appobjects.common.CommonData;
import com.ibm.atmn.homepagefvt.appobjects.common.CommonObjects;
import com.ibm.atmn.homepagefvt.appobjects.blogs.FVT_BlogsObjects;
import com.ibm.atmn.homepagefvt.tasks.blogs.FVT_BlogsMethods;

import static org.testng.AssertJUnit.*;

public class TestOfFVT_Level2_Blogs extends FVT_BlogsMethods{
	/*
	 * This is a functional test for the Blogs Component of IBM Connections
	 */
	
	private static String PublicBlogCommunity = "Public Level 2 FVT Community 12339163241";
	private static String PublicBlogEntry = "Public Level 2 FVT Community 12339163241 Blog Entry";
	
	@Test
	public void testRecommendEntry_PublicComm() throws Exception {
		
		
		System.out.println("INFO: Start of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
	
		// Login to communities
		LoadComponent(CommonObjects.ComponentCommunities);
			
		Login(CommonData.IC_LDAP_Username450, CommonData.IC_LDAP_Password450);
		
		assertTrue("Fail: Communities is not open", sel.isTextPresent("Communities"));	
		
		clickLink("link=I'm an Owner");
		
		//Go to public community
		clickLink("link=" + PublicBlogCommunity);
		
		//Go to blogs
		clickLink("link=Blog");
		
		//Go to blog entry
		clickLink("link=" + PublicBlogEntry);
		
		//Recommend entry
		clickLink(FVT_BlogsObjects.BlogsEntryRecommend);
	
		//Logout
		Logout();
		
		VerifyNewsStory(" recommended their own "+PublicBlogEntry+" blog entry in the "+PublicBlogCommunity+" blog.","Discover","Blogs", true);
		
		System.out.println("INFO: End of Blogs FVT_Level_2 testRecommendEntry_PublicComm");
		
	}

	
	
}
