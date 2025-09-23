/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2012, 2013                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.atmn.wd_homepagefvt.appobjects.communities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.ibm.atmn.wd_homepagefvt.tasks.common.CommonMethods;

public class FVT_CommunitiesData {
	
	//Generate a random number from date & time
	public static String genTimeRandVal() {
		//Create format class
		SimpleDateFormat tmformat = new SimpleDateFormat("hhmmss");

		return tmformat.format(new Date());
	}
	
	public static String getRandom(int length) {
		UUID uuid = UUID.randomUUID();
		String myRandom = uuid.toString();
		return myRandom.substring(length);
	}

	
	public static String ModeratedCommunityName = "Moderated Level 2 FVT Community ";
	public static String PrivateCommunityName = "Private Level 2 FVT Community ";
	public static String PublicCommunityName = "Public Level 2 FVT Community ";
	
	public static String CommunityName = "Community Level 2 FVT Community ";
	public static String CommunityTag = "automation";
	public static String CommunityHandle = "Handle";
	public static String CommunityDescription = "This is a test description for the community level 2 FVT";
	
	public static String CommunityNameForSearch = "Search Community for Level 2 FVT 1234";
	public static String CommunityTagForSearch = "communitysearch1234";
	public static String CommunityHandleForSearch = "Handle1234";
	public static String CommunityDescriptionForSearch = "This is a test description for the search level 2 FVT";
	
	//Data used for creating a bookmark
	public static String BookmarkURL = "http://www.google.ie";
	public static String BookmarkName = "Google Ireland";
	public static String BookmarkDescription = "This is a test for the community FVT";
	public static String BookmarkTag = "automation";
	public static String EditBookmarkURL = "http://www.w3.ibm.com";
	public static String EditBookmarkName = "IBM W3 page";
	public static String EditBookmarkDescription = "This is a edited test for the community FVT";
	public static String EditBookmarkTag = "EditedCommunityBookmarkTag";
	
	//Forums
	public static String ForumTopicTitle = "Community FVT topic"+CommonMethods.genDateBasedRandVal();
	public static String ForumTopicTag = "CommunityForumTag"+CommonMethods.genDateBasedRandVal();
	public static String ForumTopicDescription = "This is a test description for the community FVT";
	
	//Feeds
	public static String FeedsURL = "communities/service/atom/communities/my";
	public static String EditedFeedsURL = "communities/service/atom/communities/all";
	public static String FeedsTitle = "FVT Test Feed";
	public static String EditedFeedsTitle = "FVT Test Feed Edited";
	public static String FeedsDescription = "This is a test feed for the community FVT";
	public static String EditedFeedsDescription = "Edited Test Description for this feed in this community";
	public static String FeedsTag = "automation";
	public static String EditedFeedsTag = "EditedCommunitiesFeedsTag"+CommonMethods.genDateBasedRandVal();
	
	//Event
	
	public static String UpdatedEventDescription = "This is an updated calendar event description.";
	public static String UpdatedEventLocation = "IBM Dublin";
	public static String EventCommentText = "This is a comment on a calendar event.";
	
	public static String CommunityMemberInvited = "You have successfully invited the following people to this community:";
	public static String CommunityMemberRevokePrompt = "Are you sure you want to revoke the invitation for ";
	public static String CommunityNoPendingInvitations = "This community has no pending invitations.";

}
