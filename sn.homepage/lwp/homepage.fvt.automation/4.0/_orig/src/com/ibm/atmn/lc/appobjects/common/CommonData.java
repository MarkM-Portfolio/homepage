/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential */
/*                                                                   */
/* OCO Source Materials */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2013 */
/*                                                                   */
/* The source code for this program is not published or otherwise */
/* divested of its trade secrets, irrespective of what has been */
/* deposited with the U.S. Copyright Office. */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.lc.appobjects.common;

public class CommonData {

	/*
	 * Users
	 */

	//Lotus Live LDAP user
	//ajonesX@bluebox.lotus.com
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
	public static final String IC_WRONG_USERNAME = "Amy Jomes1";
	public static final String IC_RIGHT_PASSWORD = "jones1";
	public static final String IC_WRONG_PASSWORD = "jomes1";

	public static final String LDAP_Admin_Username = "ajones";
	public static final String LDAP_Admin_Password = "jones";
	public static final String LDAP_FullUsername = "Amy Jones";

	public static final String IC_LDAP_Admin_Username = LDAP_Admin_Username + "1";
	public static final String IC_LDAP_Admin_Password = LDAP_Admin_Password + "1";
	public static final String IC_LDAP_Admin_UserName_withMailID = LDAP_User_Name + "1" + LDAP_UserName_withMailID1 + "66" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_Admin_Username_DisplayonMyWikitab = "Amy Jones1's Wikis";

	public static final String IC_LDAP_Username = LDAP_User_Name + "66";
	public static final String IC_LDAP_Password = LDAP_User_Password + "66";
	public static final String IC_LDAP_UserName_withMailID = LDAP_User_Name + "66" + LDAP_UserName_withMailID1 + "66" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_Username_DisplayonMyWikitab = "Amy Jones66's Wikis";
	public static final String IC_LDAP_Username_Fullname = LDAP_FullUsername + "66";

	public static final String IC_LDAP_Username77 = LDAP_User_Name + "77";
	public static final String IC_LDAP_Password77 = LDAP_User_Password + "77";
	public static final String IC_LDAP_Username_Fullname77 = LDAP_FullUsername + "77";

	public static final String IC_LDAP_Username88 = LDAP_User_Name + "88";
	public static final String IC_LDAP_Password88 = LDAP_User_Password + "88";
	public static final String IC_LDAP_Username_Fullname88 = LDAP_FullUsername + "88";

	public static final String IC_LDAP_Username111 = LDAP_User_Name + "111";
	public static final String IC_LDAP_Password111 = LDAP_User_Password + "111";
	public static final String IC_LDAP_UserName111_withMailID = LDAP_User_Name + "111" + LDAP_UserName_withMailID1 + "111" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_UserName111_Typeahead = LDAP_User_Typeahead + "111";

	public static final String IC_LDAP_Username222 = LDAP_User_Name + "222";
	public static final String IC_LDAP_Password222 = LDAP_User_Password + "222";
	public static final String IC_LDAP_UserName222_withMailID = LDAP_User_Name + "222" + LDAP_UserName_withMailID1 + "222" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_UserName222_Typeahead = LDAP_User_Typeahead + "222";

	public static final String IC_LDAP_Username333 = LDAP_User_Name + "333";
	public static final String IC_LDAP_Password333 = LDAP_User_Password + "333";
	public static final String IC_LDAP_UserName333_withMailID = LDAP_User_Name + "333" + LDAP_UserName_withMailID1 + "333" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_UserName333_Typeahead = LDAP_User_Typeahead + "333";

	public static final String IC_LDAP_Username444 = LDAP_User_Name + "444";
	public static final String IC_LDAP_Password444 = LDAP_User_Password + "444";
	public static final String IC_LDAP_UserName444_withMailID = "Amy Jones444 <ajones14@janet.iris.com>";
	public static final String IC_LDAP_UserName444_Typeahead = LDAP_User_Typeahead + "444";

	public static final String IC_LDAP_Owner_Username = LDAP_User_Name + "77";
	public static final String IC_LDAP_Owner_Password = LDAP_User_Password + "77";
	public static final String IC_LDAP_Owner_withMailID = LDAP_User_Name + "77" + LDAP_UserName_withMailID1 + "77" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_Owner_DisplayonMyWikitab = LDAP_User_Name + "77's Wikis";
	public static final String Owner_Username_Typeahead = LDAP_User_Typeahead + "77";

	public static final String IC_LDAP_Editor_Username = LDAP_User_Name + "88";
	public static final String IC_LDAP_Editor_Password = LDAP_User_Password + "88";
	public static final String IC_LDAP_Editor_withMailID = LDAP_User_Name + "88" + LDAP_UserName_withMailID1 + "88" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_Editor_DisplayonMyWikitab = LDAP_User_Name + "88's Wikis";
	public static final String Editor_Username_Typeahead = LDAP_User_Typeahead + "88";

	public static final String IC_LDAP_Reader_Username = LDAP_User_Name + "99";
	public static final String IC_LDAP_Reader_Password = LDAP_User_Password + "99";
	public static final String IC_LDAP_Reader_withMailID = LDAP_User_Name + "99" + LDAP_UserName_withMailID1 + "99" + LDAP_UserName_withMailID2;
	public static final String IC_LDAP_Reader_DisplayonMyWikitab = LDAP_User_Name + "99's Wikis";
	public static final String Reader_Username_Typeahead = LDAP_User_Typeahead + "99";

	public static final String IC_LDAP_Owner_Username_Groups = LDAP_User_Name + "5";
	public static final String IC_LDAP_Owner_Password_Groups = LDAP_User_Password + "5";

	public static final String IC_LDAP_Owner_Username1_Groups = LDAP_User_Name + "4";
	public static final String IC_LDAP_Owner_Password1_Groups = LDAP_User_Password + "4";

	public static final String IC_LDAP_Owner_Username2_Groups = LDAP_User_Name + "3";
	public static final String IC_LDAP_Owner_Password2_Groups = LDAP_User_Password + "3";

	public static final String IC_LDAP_Editor_Username_Groups = LDAP_User_Name + "200";
	public static final String IC_LDAP_Editor_Password_Groups = LDAP_User_Password + "200";

	public static final String IC_LDAP_Editor_Username1_Groups = LDAP_User_Name + "201";
	public static final String IC_LDAP_Editor_Password1_Groups = LDAP_User_Password + "201";

	public static final String IC_LDAP_Editor_Username2_Groups = LDAP_User_Name + "202";
	public static final String IC_LDAP_Editor_Password2_Groups = LDAP_User_Password + "202";

	public static final String IC_LDAP_Reader_Username_Groups = "buser1";
	public static final String IC_LDAP_Reader_Password_Groups = "user1";
	public static final String IC_LDAP_Reader_Username_Groups_Fullname = "Bill User1";

	public static final String IC_LDAP_Reader_Username1_Groups = "buser2";
	public static final String IC_LDAP_Reader_Password1_Groups = "user2";
	public static final String IC_LDAP_Reader_Username1_Groups_Fullname = "Bill User2";

	public static final String IC_LDAP_Owners_Group = "AmyOneThruFive";
	public static final String IC_LDAP_Owners_Group_Typeahead = "Amy";
	public static final String IC_LDAP_Editors_Group = "20ABUsers";
	public static final String IC_LDAP_Editors_Group_TypeAhead = "20";
	public static final String IC_LDAP_Readers_Group = "busergroup";
	public static final String IC_LDAP_Readers_Group_TypeAhead = "buserg";

	public static final String IC_LDAP_Creator_Username = LDAP_User_Name + "444";
	public static final String IC_LDAP_Creator_Password = LDAP_User_Password + "444";

	public static final String IC_LDAP_Creator_Username2 = LDAP_User_Name + "333";
	public static final String IC_LDAP_Creator_Password2 = LDAP_User_Password + "333";

}
