/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.model;

public enum Component {

	ACTIVITIES("activities"),
	BLOGS("blogs"),
	BOOKMARKS("dogear"),
	COMMUNITIES("communities"),
	FILES("files"),
	FORUMS("forums"),
	HOMEPAGE("homepage"),
	HELP("help"),
	NEWS("news"),
	PERSON_TAG("personTag"),
	PROFILES("profiles"),
	QUICKR("quickr"),
	SAMETIME("sametime"),
	SEARCH("search"),
	WIKIS("wikis");

	private String name;
	
	private Component(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
