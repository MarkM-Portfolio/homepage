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

package com.ibm.atmn.wd_homepagefvt.appobjects.wikis;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FVT_WikisData {

	//Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
	
	 //Data for FVT
	public static final String Level_0_Public_Wiki = "FVT Level 0 Public Wiki "+genDateBasedRandVal();
	public static final String CI_Box_Public_Wiki = "FVT Level 2 Public Wiki "+genDateBasedRandVal();
	public static final String CI_Box_Public_Wiki2 = "FVT Level 2 Public Wiki 2 "+genDateBasedRandVal();
	public static final String CI_Box_Private_Wiki = "FVT Level 2 Private Wiki "+genDateBasedRandVal();
	public static final String CI_Box_Private_Wiki2 = "FVT Level 2 Private Wiki 2 "+genDateBasedRandVal();
	public static final String FVT_Level0_Test_Wikis = "";
	public static final String New_Peer_Page_For_Public_Wiki = "New_Peer_for_Public_Wiki_on_CI_Box";
	public static final String New_Peer_Page_For_Public_Wiki2 = "New_Peer_for_Public_Wiki_on_CI_Box2";
	public static final String New_Child_Page_For_Public_Wiki = "New_Child_for_Public_Wiki_on_CI_Box";
	public static final String New_Peer_Page_For_Private_Wiki = "New_Peer_for_Private_Wiki_on_CI_Box";
	public static final String New_Child_Page_For_Private_Wiki = "New_Child_for_Private_Wiki_on_CI_Box";
	public static final String New_Child_Page_For_Private_Wiki2 = "New_Child_for_Private_Wiki_on_CI_Box2";
	public static final String New_Owners_Peer_Page_For_Private_Wiki = "New_Owners_Peer_for_Private_Wiki_on_CI_Box";
	public static final String New_Owners_Peer_Page_For_Private_Wiki2 = "New_Owners_Peer_for_Private_Wiki_on_CI_Box2";
	public static final String New_Owners_Child_Page_For_Private_Wiki = "New_Owners_Child_for_Private_Wiki_on_CI_Box";
	public static final String New_Editors_Peer_Page_For_Private_Wiki = "New_Editors_Peer_for_Private_Wiki_on_CI_Box";
	public static final String New_Editors_Child_Page_For_Private_Wiki = "New_Editors_Child_for_Private_Wiki_on_CI_Box";
	public static final String New_Editors_Child_Page_For_Private_Wiki2 = "New_Editors_Child_for_Private_Wiki_on_CI_Box2";
	public static final String New_Content_For_Public_Wiki = "New content for Public Wiki";
	public static final String New_Content_For_Private_Wiki = "New content for Private Wiki";
	public static final String New_Content_For_Owners_Private_Wiki = "New content for Owners Private Wiki";
	public static final String New_Content_For_Editors_Private_Wiki = "New content for Editors Private Wiki";
	public static final String New_Content_For_Public_Wiki_Peer = "New content for Public Wiki Peer";
	public static final String New_Content_For_Public_Wiki_Child = "New content for Public Wiki Child";
	public static final String New_Content_For_Private_Wiki_Peer = "New content for Private Wiki Peer";
	public static final String New_Content_For_Private_Wiki_Child = "New content for Private Wiki Child";
	public static final String New_Content_For_Owners_Private_Wiki_Peer = "New content for Owners Private Wiki Peer";
	public static final String New_Content_For_Owners_Private_Wiki_Child = "New content for Owners Private Wiki Child";
	public static final String New_Content_For_Editors_Private_Wiki_Peer = "New content for Editors Private Wiki Peer";
	public static final String New_Content_For_Editors_Private_Wiki_Child = "New content for Editors Private Wiki Child";
	public static final String Comment_For_Public_Wiki = "Comment 1 - Public Wiki "+genDateBasedRandVal();
	public static final String Comment_For_Private_Wiki = "Comment 1 - Private Wiki "+genDateBasedRandVal();
	public static final String Comment_For_Owners_Private_Wiki = "Comment 1 - Owners Private Wiki";
	public static final String Comment_For_Editors_Private_Wiki = "Comment 1 - Editors Private Wiki";
	public static final String Comment_For_Readers_Private_Wiki = "Comment 1 - Readers Private Wiki";
	public static final String Tag_For_Public_Wiki = "automation";
	public static final String Tag_For_Private_Wiki = "automation";
	public static final String Tag_For_Owners_Private_Wiki = "ownersprivatewikitag";
	public static final String Tag_For_Editors_Private_Wiki = "editorsprivatewikitag";
	public static final String Tag_For_Readers_Private_Wiki = "readersprivatewikitag";
	
	
	//FVT
	public static final String New_Peer_Page_For_Mod_Comm_Wiki = "New_Peer_for_Mod_Comm_Wiki";
	public static final String New_Peer_Page_For_Edited_Mod_Comm_Wiki = "New_Peer_for_Edited_Mod_Comm_Wiki";
	public static final String New_Child_Page_For_Mod_Comm_Wiki = "New_Child_for_Mod_Comm_Wiki";
	public static final String New_Peer_Page_For_Pub_Comm_Wiki = "New_Peer_for_Pub_Comm_Wiki";
	public static final String New_Peer_Page_For_Edited_Pub_Comm_Wiki = "New_Peer_for_Edited_Pub_Comm_Wiki";
	public static final String New_Child_Page_For_Pub_Comm_Wiki = "New_Child_for_Pub_Comm_Wiki";
	public static final String New_Peer_Page_For_Priv_Comm_Wiki = "New_Peer_for_Priv_Comm_Wiki";
	public static final String New_Peer_Page_For_Edited_Priv_Comm_Wiki = "New_Peer_for_Edited_Priv_Comm_Wiki";
	public static final String New_Child_Page_For_Priv_Comm_Wiki = "New_Child_for_Priv_Comm_Wiki";
	public static final String New_Content_For_Mod_Comm_Wiki = "New content for Mod Comm Wiki";
	public static final String New_Content_For_Pub_Comm_Wiki = "New content for Public Comm Wiki";
	public static final String New_Content_For_Priv_Comm_Wiki = "New content for Priv Comm Wiki";
	public static final String Comment_For_Mod_Comm_Wiki = "Comment 1 - Mod Comm Wiki";
	public static final String Comment_For_Pub_Comm_Wiki = "Comment 1 - Pub Comm Wiki";
	public static final String Comment_For_Priv_Comm_Wiki = "Comment 1 - Priv Comm Wiki";
	
	
}

