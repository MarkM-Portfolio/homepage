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

public enum SourceType {
	
	ACTIVITIES(Component.ACTIVITIES.getName()),
	BLOGS(Component.BLOGS.getName()),
	BOOKMARKS("bookmarks"),
	COMMUNITIES(Component.COMMUNITIES.getName()),
	FILES(Component.FILES.getName()),
	FORUMS(Component.FORUMS.getName()),
	PROFILES(Component.PROFILES.getName()),	
	WIKIS(Component.WIKIS.getName()),
	TAGS("tags"),
	RESPONSES("responses");

	private String name;
	
	private SourceType(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
}
