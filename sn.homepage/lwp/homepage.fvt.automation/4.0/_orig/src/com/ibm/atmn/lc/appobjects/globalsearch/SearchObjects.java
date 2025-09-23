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

package com.ibm.atmn.lc.appobjects.globalsearch;

public class SearchObjects {

	//All Checkboxes in form
	public static String ActivitiesCheckbox = "css=#activities_checkbox";
	public static String BlogsCheckbox = "css=#blogs_checkbox";
	public static String BookmarkCheckbox = "css=#dogear_checkbox";
	public static String CommunitiesCheckbox = "css=#communities_checkbox";
	public static String FilesCheckbox = "css=#files_checkbox";
	public static String ForumsCheckbox = "css=#forums_checkbox";
	public static String ProfilesCheckbox = "css=#profiles_checkbox";
	public static String WikisCheckbox = "css=#wikis_checkbox";
	public static String StatusUpdatesCheckbox = "css=#status_updates_checkbox";

	//Text fields
	public static String AdvancedSearchKeywords = "css=input[id='advancedQuery']";
	public static String AdvancedSearchPerson = "css=input[id='peopleCatcher']";
	public static String AdvancedSearchTag = "css=input[name='tag']";
	public static String AdvancedSearchTitle = "css=input[id='titleQuery']";

	//Search button
	public static String SearchButton = "css=input[value='Search']";

	//Verify Search results
	public static String ReturnedTag = "css=ul.lotusInlinelist li.lotusLast a";
	public static String ReturnedUsername = "css=span.lotusPerson a.fn";
	public static String ReturnedObjectName = "css=tr.lotusFirst td.lotusLastCell h4 a";
	public static String ReturnedDescription = "css=span.searchSummaryHighlight";

}
