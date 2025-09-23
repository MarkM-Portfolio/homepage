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

package com.ibm.atmn.lc.appobjects.moderation;

public class ModerationObjects {

	
	public static String RequireApproval = "css=div a span nobr:contains('Require Approval')";
	public static String Rejected = "css=div a span nobr:contains('Rejected')";
	
	// Require Approval view buttons // Rejected view buttons
	public static String Approve = "css=span.lotusBtn a:contains('Approve')";
	public static String Reject = "css=span.lotusBtn a:contains('Reject')";
	public static String Delete = "css=span.lotusBtn a:contains('Delete')";
	
	// Moderation view tree : Content Approval
	public static String ContentApproval="link=Content Approval";	
	
	// Moderation view tree : Flagged Content
	public static String FlaggedContent="link=Flagged Content";

	public static String Moderation_Blogs="link=Blogs";
	public static String Moderation_Files="link=Files";
	public static String Moderation_Forums="link=Forums";

	public static String Moderation_Blogs_Entries = "css=span.dijitTreeContent a.dijitTreeLabel:contains('Entries'):nth(0)";
	public static String Moderation_Files_Content = "link=Content";
	public static String Moderation_Forums_Posts = "link=Posts";
	
	
	// Moderation items view
	public static String FileToApprove = "css=tr.vcard td p a:contains('PLACEHOLDER')";
	
	
	
}
