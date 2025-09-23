package com.ibm.lconn.homepage.model;
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


public enum Metric{

	NO_VISITORS("homepage.metric.totals.visitor",1,"user", "trend"),
	NO_STORIES("homepage.metric.totals.stories.added",2,"stories", "trend"),
	NO_SENT_NOTIF("homepage.metric.totals.sentNotifications",3,"notifications", "trend"),
	NO_REC_NOTIF("homepage.metric.totals.receivedNotifications",4,"notifications","trend"),
	NO_STORIES_SAVED("homepage.metric.totals.stories.saved",5,"stories","trend"),
	NO_CUSTOMIZED_PAGES("homepage.metric.totals.customizedPages",6,"misc","scalar"),
	POPULAR_WIDGETS("homepage.metric.topWidgets",7,"widgets","list"),
	NO_AVAIL_WIDGETS("homepage.metric.totals.available.widgets",8,"widgets","scalar"),
	NO_ENABLED_WIDGETS("homepage.metric.totals.enabled.widgets",9,"widgets","scalar"),

	NO_PROFILES_BOARD_POSTS_WITHCOMMENT("profiles.metric.board.comment.count",10,"board.profiles", "scalar"),
	NO_PROFILES_BOARD_POSTS_TODAY("profiles.metric.board.count.today",11,"board.profiles", "scalar"),
	NO_PROFILES_BOARD_POSTS_TOTAL("profiles.metric.board.total.count",12,"board.profiles", "scalar"),
	NO_PROFILES_BOARD_USERS_WITHPOST("profiles.metric.user.board.count",13,"board.profiles", "scalar"),
	
	NO_COMMUNITIES_BOARD_POSTS_WITHCOMMENT("communities.metric.board.comment.count",14,"board.communities","scalar"),
	NO_COMMUNITIES_BOARD_POSTS_TODAY("communities.metric.board.count.today",15,"board.communities","scalar"),
	NO_COMMUNITIES_BOARD_POSTS_TOTAL("communities.metric.board.total.count",16,"board.communities","scalar"),
	
	NO_ALL_BOARD_POSTS_WITHCOMMENT("all.metric.board.comment.count",17,"board","scalar"),
	NO_ALL_BOARD_POSTS_TODAY("all.metric.board.count.today",18,"board","scalar"),
	NO_ALL_BOARD_POSTS_TOTAL("all.metric.board.total.count",19,"board","scalar");
	
	private String resourceKey;
	private int type; 
	private String category;
	private String style; //trend, list, scalar
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	Metric(String resource, int type, String category, String style){
		this.resourceKey = resource;
		this.type = type;
		this.category = category;
		this.style = style;

	}
	public String getResourceKey(){
		return this.resourceKey;
	}
	public int getType(){
		return this.type;
	}
	public String getCategory(){
		return this.category;
	}
}
