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

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Properties;

import com.ibm.atmn.wd_homepagefvt.appobjects.wikis.WikisObjects;
import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.SeleniumException;


@SuppressWarnings("unused")
public class Data extends Initialize{
	
    private static final String BUNDLE = "data.properties";
    private static final Properties properties;
    static
    {
      properties = new Properties();
      try
      {
        properties.load(WikisObjects.class.getResourceAsStream(BUNDLE));
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
    
    //Generate a random number from date & time
	public static String genDateBasedRandVal(){
    	//Create format class
    	SimpleDateFormat tmformat = new SimpleDateFormat("MMDDHHmmss");

    	return tmformat.format(new Date());
    }
    
    //Data for BVT 
	public static final String Level_0_Public_Wiki = "BVT Level 0 Public Wiki "+genDateBasedRandVal();
	public static final String CI_Box_Public_Wiki = "BVT Level 2 Public Wiki "+genDateBasedRandVal();
	public static final String CI_Box_Public_Wiki2 = "BVT Level 2 Public Wiki 2 "+genDateBasedRandVal();
	public static final String CI_Box_Private_Wiki = "BVT Level 2 Private Wiki"+genDateBasedRandVal();
	public static final String CI_Box_Private_Wiki2 = "BVT Level 2 Private Wiki 2 "+genDateBasedRandVal();
	public static final String BVT_Level0_Test_Wikis = "";
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
	public static final String Comment_For_Public_Wiki = "Comment 1 - Public Wiki";
	public static final String Comment_For_Private_Wiki = "Comment 1 - Private Wiki";
	public static final String Comment_For_Owners_Private_Wiki = "Comment 1 - Owners Private Wiki";
	public static final String Comment_For_Editors_Private_Wiki = "Comment 1 - Editors Private Wiki";
	public static final String Comment_For_Readers_Private_Wiki = "Comment 1 - Readers Private Wiki";
	public static final String Tag_For_Public_Wiki = "publicwikitag";
	public static final String Tag_For_Private_Wiki = "privatewikitag";
	public static final String Tag_For_Owners_Private_Wiki = "ownersprivatewikitag";
	public static final String Tag_For_Editors_Private_Wiki = "editorsprivatewikitag";
	public static final String Tag_For_Readers_Private_Wiki = "readersprivatewikitag";
	
	//LDAP user
	public static final String LDAP_User_Name = "ajones";
	public static final String LDAP_User_Password = "jones";
	public static final String LDAP_User_Typeahead = "Jones";
	public static final String LDAP_UserName_withMailID1 = "<ajones";
	public static final String LDAP_UserName_withMailID2 = "@janet.iris.com>";
	//LDAP Groups
	public static final String LDAP_Group_Name_Owner = "AmyOneThruFive";
	public static final String LDAP_Group_Name_Owner_TypeAhead = "Amy";
	public static final String LDAP_Group_Name_Editor = "20ABUsers";
	public static final String LDAP_Group_Name_Editor_TypeAhead = "20";
	public static final String LDAP_Group_Name_Reader = "busergroup";
	public static final String LDAP_Group_Name_Reader_TypeAhead = "buserg";
	//User Roles
	public static final String Owner_Role = "Owner";
	public static final String Editor_Role = "Editor";
	public static final String Reader_Role = "Reader";
	public static final String Anonymous_User = "Anonymous";
	public static final String Creator_Role = "Creator";
	public static final String Page_Creator_Role = "Page Creator";
	
	//data section for LDAP specific users
	public static final String WIKI_WRONG_USERNAME = "Amy Jomes1";
	public static final String WIKI_RIGHT_PASSWORD = "jones1";
	public static final String WIKI_WRONG_PASSWORD = "jomes1";
	
	public static final String Wiki_LDAP_Admin_Username = LDAP_User_Name+"1";
	public static final String Wiki_LDAP_Admin_Password = LDAP_User_Password+"1";
	
	public static final String Wiki_LDAP_Username = LDAP_User_Name+"66";
	public static final String Wiki_LDAP_Password = LDAP_User_Password+"66";
	public static final String Wiki_LDAP_UserName_withMailID = LDAP_User_Name+"66" +LDAP_UserName_withMailID1+"66"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Username_DisplayonMyWikitab = "Amy Jones66's Wikis";
	
	public static final String Wiki_LDAP_Username111 = LDAP_User_Name+"111";
	public static final String Wiki_LDAP_Password111 = LDAP_User_Password+"111";
	public static final String Wiki_LDAP_UserName111_withMailID = LDAP_User_Name+"111" +LDAP_UserName_withMailID1+"111"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_UserName111_Typeahead = LDAP_User_Typeahead+"111";
	
	public static final String Wiki_LDAP_Username222 = LDAP_User_Name+"222";
	public static final String Wiki_LDAP_Password222 = LDAP_User_Password+"222";
	public static final String Wiki_LDAP_UserName222_withMailID = LDAP_User_Name+"222" +LDAP_UserName_withMailID1+"222"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_UserName222_Typeahead = LDAP_User_Typeahead+"222";
	
	public static final String Wiki_LDAP_Username333 = LDAP_User_Name+"333";
	public static final String Wiki_LDAP_Password333 = LDAP_User_Password+"333";
	public static final String Wiki_LDAP_UserName333_withMailID = LDAP_User_Name+"333" +LDAP_UserName_withMailID1+"333"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_UserName333_Typeahead = LDAP_User_Typeahead+"333";
	
	public static final String Wiki_LDAP_Username444 = LDAP_User_Name+"444";
	public static final String Wiki_LDAP_Password444 = LDAP_User_Password+"444";
	public static final String Wiki_LDAP_UserName444_withMailID = "Amy Jones444 <ajones14@janet.iris.com>";
	public static final String Wiki_LDAP_UserName444_Typeahead = LDAP_User_Typeahead+"444";
	
	public static final String Wiki_LDAP_Owner_Username = LDAP_User_Name+"77";
	public static final String Wiki_LDAP_Owner_Password = LDAP_User_Password+"77";
	public static final String Wiki_LDAP_Owner_withMailID = LDAP_User_Name+"77" +LDAP_UserName_withMailID1+"77"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Owner_DisplayonMyWikitab = LDAP_User_Name+"77's Wikis";
	public static final String Owner_Username_Typeahead = LDAP_User_Typeahead+"77";
	
	public static final String Wiki_LDAP_Owner_Username450 = LDAP_User_Name+"450";
	public static final String Wiki_LDAP_Owner_Password450 = LDAP_User_Password+"450";
	public static final String Wiki_LDAP_Owner_withMailID450 = LDAP_User_Name+"450" +LDAP_UserName_withMailID1+"450"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Owner_DisplayonMyWikitab450 = LDAP_User_Name+"450's Wikis";
	public static final String Owner_Username_Typeahead450 = LDAP_User_Typeahead+"450";
	
	public static final String Wiki_LDAP_Editor_Username = LDAP_User_Name+"88";
	public static final String Wiki_LDAP_Editor_Password = LDAP_User_Password+"88";
	public static final String Wiki_LDAP_Editor_withMailID = LDAP_User_Name+"88" +LDAP_UserName_withMailID1+"88"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Editor_DisplayonMyWikitab = LDAP_User_Name+"88's Wikis";
	public static final String Editor_Username_Typeahead = LDAP_User_Typeahead+"88";
	
	public static final String Wiki_LDAP_Reader_Username = LDAP_User_Name+"99";
	public static final String Wiki_LDAP_Reader_Password = LDAP_User_Password+"99";
	public static final String Wiki_LDAP_Reader_withMailID = LDAP_User_Name+"99" +LDAP_UserName_withMailID1+"99"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Reader_DisplayonMyWikitab = LDAP_User_Name+"99's Wikis";
	public static final String Reader_Username_Typeahead = LDAP_User_Typeahead+"99";
	
	public static final String Wiki_LDAP_Reader_Username451 = LDAP_User_Name+"451";
	public static final String Wiki_LDAP_Reader_Password451 = LDAP_User_Password+"451";
	public static final String Wiki_LDAP_Reader_withMailID451 = LDAP_User_Name+"451" +LDAP_UserName_withMailID1+"451"+LDAP_UserName_withMailID2;
	public static final String Wiki_LDAP_Reader_DisplayonMyWikitab451 = LDAP_User_Name+"451's Wikis";
	public static final String Reader_Username_Typeahead451 = LDAP_User_Typeahead+"451";
	
	public static final String Wiki_LDAP_Owner_Username_Groups = LDAP_User_Name+"5";
	public static final String Wiki_LDAP_Owner_Password_Groups = LDAP_User_Password+"5";
	
	public static final String Wiki_LDAP_Owner_Username1_Groups = LDAP_User_Name+"4";
	public static final String Wiki_LDAP_Owner_Password1_Groups = LDAP_User_Password+"4";
	
	public static final String Wiki_LDAP_Owner_Username2_Groups = LDAP_User_Name+"3";
	public static final String Wiki_LDAP_Owner_Password2_Groups = LDAP_User_Password+"3";
	
	public static final String Wiki_LDAP_Editor_Username_Groups = LDAP_User_Name+"200";
	public static final String Wiki_LDAP_Editor_Password_Groups = LDAP_User_Password+"200";
	
	public static final String Wiki_LDAP_Editor_Username1_Groups = LDAP_User_Name+"201";
	public static final String Wiki_LDAP_Editor_Password1_Groups = LDAP_User_Password+"201";
	
	public static final String Wiki_LDAP_Editor_Username2_Groups = LDAP_User_Name+"202";
	public static final String Wiki_LDAP_Editor_Password2_Groups = LDAP_User_Password+"202";
	
	public static final String Wiki_LDAP_Reader_Username_Groups = "buser1";
	public static final String Wiki_LDAP_Reader_Password_Groups = "user1";
	public static final String Wiki_LDAP_Reader_Username_Groups_Fullname = "Bill User1";
	
	public static final String Wiki_LDAP_Reader_Username1_Groups = "buser2";
	public static final String Wiki_LDAP_Reader_Password1_Groups = "user2";
	public static final String Wiki_LDAP_Reader_Username1_Groups_Fullname = "Bill User2";
	
	public static final String Wiki_LDAP_Owners_Group = "AmyOneThruFive";
	public static final String Wiki_LDAP_Owners_Group_Typeahead = "Amy";
	public static final String Wiki_LDAP_Editors_Group = "20ABUsers";
	public static final String Wiki_LDAP_Editors_Group_TypeAhead = "20";
	public static final String Wiki_LDAP_Readers_Group = "busergroup";
	public static final String Wiki_LDAP_Readers_Group_TypeAhead = "buserg";
	
	public static final String Wiki_LDAP_Creator_Username = LDAP_User_Name+"444";
	public static final String Wiki_LDAP_Creator_Password = LDAP_User_Password+"444";
	
	public static final String Wiki_LDAP_Creator_Username2 = LDAP_User_Name+"333";
	public static final String Wiki_LDAP_Creator_Password2 = LDAP_User_Password+"333";
	
}    
