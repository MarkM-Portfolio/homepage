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

package com.ibm.atmn.lc.appobjects.mediagallery;

import com.ibm.atmn.lc.appobjects.common.CommonObjects;

public class MediaGalleryObjects {

	public static String MediaGalleryWidgetDropdown = "css=h2.ibmDndDragHandle a";
	public static String MediaGalleryUploadLink = "css=a[title='Upload a photo or a video']";
	public static String MediaGalleryUploadPhoto = "css= tbody.dijitReset tr:contains('New Photo')";
	public static String MediaGalleryUploadVideo = "css= tbody.dijitReset tr:contains('New Video')";
	public static String Upload_Button = "css=input[value='Upload']";
	public static String ProgressBar = "css=div[class='dijitProgressBarTile']";
	public static String MediaGalleryViewAllLink = "css=a[title='Switch to gallery view']";
	public static String MediaGalleryViewCount = "css=div.lotusPaging div.lotusLeft";

	//Upload Dialog
	public static String MediaGalleryUploadPhotoDialog = "h1.lotusDraggable span:contains('Upload Photo')";
	public static String MediaGalleryUploadVideoDialog = "h1.lotusDraggable span:contains('Upload Video')";
	public static String MediaGalleryFileUploadFilename = "name=_label";
	public static String MediaGalleryFileUploadTags = "name=uploadFileTaggerTypeAhead";
	public static String MediaGalleryFileUploadDescription = "name=_description";

	public static String MediaGalleryUploadPhotoButton = "span.lotusBtn a:contains('Upload Photo')";
	public static String MediaGalleryUploadVideoButton = "span.lotusBtn a:contains('Upload Video')";

	//Media Gallery object
	public static String UploadPhotoButton = "css=a[title='Upload a photo from your computer']";
	public static String UploadVideoButton = "css=a[title='Upload a video from your computer']";

	public static String RecommendMediaGalleryFile = "css=a.qkrRecommendLink span";

	//Media Gallery Uploaded file tabs
	public static String CommentsTab = "link=Comments";
	public static String AboutThisFileTab = "link=About this file";
	public static String CustomPropetiesTab = "link=Custom Properties";

	//Media Gallery Comments
	public static String AddAComment = "css=div.lotusChunk a:contains('Add a comment')";
	public static String AddACommentTextArea = "css=textarea[name='description']";
	public static String AddACommentSave = CommonObjects.SaveButton;
	public static String AddACommentEdit = "css=a.editBtn";//div.lotusActions ul.lotusInlinelist li.lotusFirst 
	public static String AddACommentDelete = "css=a.deleteBtn";//div.lotusActions ul.lotusInlinelist li.lotusFirst 
	public static String AddACommentOK = CommonObjects.OKButton;
	public static String AddedComment = "css=div.lotusPostDetails div.lotusChunk div.qkrWideInner";

	//Media Gallery Uploaded file
	public static String SinglePhotoUpload = "css=h4 a.entry-title";
	public static String SingleUpload = "css=h4 a.entry-title";

	//Media Gallery Uploaded file display name
	public static String MediaGalleryOpenItem = "css=h1.lotusLeft span.qkrFileSummaryTitle";

	//Media Gallery Delete
	public static String MediaGalleryDeleteFile = "css=div.lotusBtnContainer span.lotusBtn a:contains('Delete')";
	public static String ConfirmDelete_Button = "css=input[value='Delete']";

	//Media Gallery Edit Properties
	public static String MediaGalleryEditProperties = "css=div.lotusBtnContainer span.lotusBtn a:contains('Edit properties')";
	public static String MediaGalleryEditPropertiesOk = "css=input[value='OK']";

	//Media Gallery Recommendation
	public static String MediaGalleryRecommendation = "";

	//About this file contents
	public static String AboutThisFileContent = "css=div.qkrDocSummary div div div#about_0.lotusSection table.lotusVertTable tbody";

	//About this photo details
	public static String AboutThisUpload = "css=td.qkrGallerySummAboutTxt";
	public static String AboutPhotoSize = "css=td.qkrGallerySummAboutTxt div span";
	public static String AboutPhotoDecription = "css=td.qkrGallerySummAboutTxt div.qkrWide div.qkrWideInner span";

	public static String MediaGalleryTags = "css=span.lotusTags";

}
