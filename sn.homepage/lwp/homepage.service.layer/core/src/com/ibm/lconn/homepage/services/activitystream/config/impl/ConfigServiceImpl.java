/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2011, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.activitystream.config.impl;

import static java.util.logging.Level.FINER;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.json.java.JSONArray;
import com.ibm.json.java.JSONObject;
import com.ibm.lconn.core.gatekeeper.LCGatekeeper;
import com.ibm.lconn.core.gatekeeper.LCGatekeeperException;
import com.ibm.lconn.core.gatekeeper.LCSupportedFeature;
import com.ibm.lconn.homepage.services.INewsService;
import com.ibm.lconn.homepage.services.ServiceException;
import com.ibm.lconn.homepage.services.activitystream.config.IConfigService;
import com.ibm.lconn.homepage.services.utils.CnxNewUIEnabledThreadLocal;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.ASConfig;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.FilterOption;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.Filters;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.UserInfo;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.util.ASAppNameHelper;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.util.ThirdPartyApp;
import com.ibm.lconn.services.gadgets.osapiclient.activitystream.config.util.ThirdPartyAppHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper;
import com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper.ComponentEntry;
import com.ibm.lconn.core.web.util.UIConfigHelper;

/**
 * Activity Stream configuration object service. Essentially builds activity
 * stream JSON object and returns it from getConfigObject(). In order to build
 * filters for each config object view, it first builds a generic filters list
 * (that holds every filter possible). Then, a subset of available filters is
 * built by gauging the components installed on the current server. Finally, a
 * configuration object is constructed containing all the necessary config
 * properties. This object is static as it can be reused by all.
 * 
 * @author Robert Campion, BrianOG
 */

public class ConfigServiceImpl implements IConfigService {
	private static final String EXCLUDE_NETWORK = "excludeNetwork";
	private final static String CLASS_NAME = ConfigServiceImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private static List<String> apps = null;
	
	private final static String PROP_NONETWORK = "com.ibm.social.as.noNetwork";
	private final static String GUEST_ORG = "0";
	@Autowired
	private ResourceBundle activityStreamConfigResourceBundle;
	
	// service for getting tags information
	@Autowired
	private INewsService newsService;

	// Shortcut to keep code clean(er(ish))
	private ASAppNameHelper names = ASConfig.AppNames;
	
	private VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory.getInstance();


	/**
	 * helper method to check if feature is enabled vi gatekeeper
	 * @param feature
	 * @param orgId
	 * @return
	 */
	private boolean isFeatureEnabled(LCSupportedFeature feature,String orgId){
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "isFeatureEnabled");
		}

		boolean isEnabled = false;
		try {
			if ( LCGatekeeper.isEnabled(feature, orgId)) {
				isEnabled = true;
			}
		} catch (LCGatekeeperException e) {
			logger.log(Level.WARNING, e.getMessage() );
		}

		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "isFeatureEnabled");
		}

		return isEnabled;
	}

	/**
	 * Build the My Stream view.
	 * @return {View}
	 */
	private FilterOption buildMyStreamView(Locale locale, UserInfo user) {
			
		String labelKey = this.shouldUseNewUI() ? "latestUpdatesCnx8Label" : "mystreamLabel";
		String label = getStr(labelKey, locale);
		String descriptionKey = this.shouldUseNewUI() ? "latestUpdatesCnx8Description" : "mystreamDescription";
		String description = getStr("mystreamDescription", locale);
		
		// Create and return the My Stream view
		FilterOption myStreamFilter = new FilterOption(label);
		
		Filters fs = buildFilters(locale);
		fs.setType(Filters.Type.LINKS);
		
		if (!shouldUseNewUI()) {
			fs.setOption("imFollowing", buildImFollowingView(locale, user));
			if( enabled("microblogging") ) { fs.setOption("statusUpdates", buildStatusUpdatesView(locale, user)); }
			fs.setOption("discover", buildDiscoveryView(locale, user));
		} else {
			// use new filter layout
			fs.setOption("updates", buildImFollowingView(locale, user));
			if ( enabled("microblogging") ) {
				fs.setOption("atMentions", buildAtMentionsView(locale));
			}
			fs.setOption("myNotifications", buildMyNotificationsView(locale, user));
			fs.setOption("actionRequired", buildActionRequiredView(locale, user));
			fs.setOption("saved", buildSavedView(locale, user));
		}
		
		myStreamFilter.setFilters(fs);
		myStreamFilter.setDescription(description);
		
		return myStreamFilter;
	}

	/**
	 * Build the I'm Following config view. 
	 * @return {View}
	 */
	private FilterOption buildImFollowingView(Locale locale, UserInfo user){
		Filters fs = buildFilters(locale);
	
		fs.setOption("all", bldF(ASConfig.AppNames.getAllUpdates(locale), null, null, ASConfig.AppIds.ALL, false, false, new String[] {ASConfig.Extensions.SHAREBOX_STATUS_UPDATE_EXTENSION}));
		
		/** Defect 77225 - Don't load the StatusUpdateExtension in the people filter. We don't want to display fake status updates in this view
		 *  as a SU by the current user wouldn't be displayed in the view.
		 */
		
		FilterOption contacts = bldAF(names.getPeople(locale), 	ASConfig.AppIds.CAT_PEOPLE); // TODO undo once the contacts source type has been removed and replaced with profile
		contacts.setParam(EXCLUDE_NETWORK, "true");
		
		if ( enabled("microblogging") ){
			FilterOption mb = bldF(names.getStatusUpdates(locale), null, null, ASConfig.AppIds.CAT_STATUS, false, false, new String[] {ASConfig.Extensions.SHAREBOX_STATUS_UPDATE_EXTENSION});
			mb.setParam(EXCLUDE_NETWORK, "true");
			fs.setOption("statusUpdates", mb);			
		}
		if ( enabled("activities" ) ) 	fs.setOption("activities", 		bldAF(names.getActivities(locale), 		ASConfig.AppIds.ACTIVITIES));
		if ( enabled("blogs")	)		fs.setOption("blogs",			bldAF(names.getBlogs(locale), 			ASConfig.AppIds.BLOGS));
		if ( enabled("dogear") )		fs.setOption("bookmarks",		bldAF(names.getBookmarks(locale), 		ASConfig.AppIds.BOOKMARKS));
		if ( enabled("communities") ){	
			FilterOption commFilter = bldAF(names.getCommunities(locale), 	ASConfig.AppIds.CAT_COMMUNITIES);
			commFilter.setParam(EXCLUDE_NETWORK, "true");
			fs.setOption("communities", commFilter);
		}
		if ( enabled("files") )			fs.setOption("files",			bldAF(names.getFiles(locale), 			ASConfig.AppIds.FILES));
		if ( enabled("forums") )		fs.setOption("forums",			bldAF(names.getForums(locale), 			ASConfig.AppIds.FORUMS));
		if ( enabled("ecm_files") )		fs.setOption("libraries",		bldAF(names.getLibraries(locale),		ASConfig.AppIds.LIBRARIES));
		if ( enabled("profiles") )		
		{
			FilterOption profiles = bldAF(names.getPeople(locale), 	ASConfig.AppIds.CAT_PEOPLE);
			profiles.setParam(EXCLUDE_NETWORK, "true");
			fs.setOption("people", 	profiles);
		}
		
		if ( enabled("wikis") )			fs.setOption("wikis", 			bldAF(names.getWikis(locale), 			ASConfig.AppIds.WIKIS));
		
		if(!user.isExternalUser()){
			fs.setOption("tags", buildTagsFilter(locale, user));
		}
		
		addThirdPartyApps(user, fs, locale, contacts);
			
		String label = null;
		if ( isGuest(user) ) {
			label = getStr("mystreamLabel", locale); 
		} else {
			label = getStr("imfollowingLabel", locale);
		}
		String description = getStr("imfollowingDescription", locale);
		
		// Create and return the I'm Following view
		FilterOption imFollowingView = new FilterOption(label);
		imFollowingView.setUserId(ASConfig.UserIds.ME);
		imFollowingView.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
		imFollowingView.addExtension(ASConfig.Extensions.UNFOLLOW_EXTENSION);
		imFollowingView.addExtension(ASConfig.Extensions.MICROBLOG_DELETION_EXTENSION);
		imFollowingView.addExtension(ASConfig.Extensions.SEARCH_BAR_EXTENSION);
		imFollowingView.setFilters(fs);
		imFollowingView.setLabel(label);
		imFollowingView.setDescription(description);
		imFollowingView.setParam("rollup", "true");
		
		return imFollowingView;
	}
	
	/**
	 * Build the Tags config filter. 
	 * @return {View}
	 */
	private FilterOption buildTagsFilter(Locale locale, UserInfo user) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "buildTagsFilter", new Object[]{locale, user});
		}
		Filters fs = new Filters();
		fs.setMenuTitle(ASConfig.CommonStrs.getSecondaryFilterMenuTitle(locale));

		fs.setOption("all", new FilterOption(getStr("filter.tags.all", locale)));
		try {
			JSONObject tags = newsService.getTagSubscriptions(((String) user.getJsonObject().get("id")), locale);
			
			JSONArray tagsArray = ((JSONArray) tags.get("items"));
			
			for (int i = 0; i < tagsArray.size(); ++i) {
	        	JSONObject tag = ((JSONObject) tagsArray.get(i));
	        	String tagText = ((String) tag.get("text"));
	        	FilterOption tagOption = new FilterOption(tagText);
	        	tagOption.setParam("filterBy", ASConfig.FilterBy.TAG);
	        	tagOption.setParam("filterOp", ASConfig.FilterOp.EQUALS);
	        	tagOption.setParam("filterValue", tagText);
	        	fs.setOption(tagText, tagOption);
			}
		} catch(ServiceException e) {
			if (logger.isLoggable(FINER)) {
				logger.log(FINER, "error thrown while fetching tags from news", e);
			}
		}
		
		return bldF(names.getTags(locale), null, null, ASConfig.AppIds.CAT_TAGS, false, false, new String[] { ASConfig.Extensions.TAG_MANAGER_EXTENSION}, fs);
	}

	/**
	 * Build up the Status Updates view
	 * @return
	 */
	private FilterOption buildStatusUpdatesView(Locale locale, UserInfo user) {
		VenturaConfigurationHelper vch = VenturaConfigurationHelper.Factory.getInstance();
		Properties props = vch.getGenericProperites();
		boolean noNetwork = props.containsKey(PROP_NONETWORK) ? Boolean.parseBoolean(props.getProperty(PROP_NONETWORK)) : false;
		
		Filters fs = buildFilters(locale);
		
		fs.setOption("all", bldF(getStr("fitler.statusupdates.all", locale), null, ASConfig.GroupIds.ALL, ASConfig.AppIds.CAT_STATUS, false, false, new String[] {"lconn.homepage.as.extension.UnfollowExtension", "com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"}));

		if ( !noNetwork && !user.isExternalUser() ) {
			fs.setOption("networkAndFollow", bldF(getStr("filter.statusupdates.mynetworkandfollow", locale), null, ASConfig.GroupIds.FOLLOW_NETWORK, ASConfig.AppIds.CAT_STATUS, false, false));
		
			fs.setOption("myNetwork", bldF(getStr("filter.statusupdates.mynetwork", locale), null, ASConfig.GroupIds.FRIENDS, ASConfig.AppIds.CAT_STATUS, false, false));
		}
		
		if ( !noNetwork && !user.isExternalUser() ) {
			fs.setOption("people", bldF(getStr("filter.statusupdates.people", locale), null, ASConfig.GroupIds.FOLLOWING, ASConfig.AppIds.CAT_STATUS, false, false, new String[] {"lconn.homepage.as.extension.UnfollowExtension"}));			
		}
		
		fs.setOption("myUpdates", bldF(getStr("filter.statusupdates.myupdates", locale), null, ASConfig.GroupIds.SELF, ASConfig.AppIds.CAT_STATUS, false, false, new String[] {"com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"}));
		
		if ( enabled("communities") ) {
			fs.setOption("communities", bldF(names.getCommunities(locale), null, ASConfig.GroupIds.ALL, ASConfig.AppIds.COMMUNITIES, false, true, new String[] {"lconn.homepage.as.extension.UnfollowExtension"}));
		}
		
		String label = getStr("statusupdatesLabel", locale);
		String description = getStr("statusupdatesDescription", locale);
		
		FilterOption view = new FilterOption(label);
		view.setUserId(ASConfig.UserIds.ME);
		view.setFilters(fs);
		view.setLabel(label);
		view.setDescription(description);
		view.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
		view.addExtension(ASConfig.Extensions.MICROBLOG_DELETION_EXTENSION);
		view.addExtension(ASConfig.Extensions.SEARCH_BAR_EXTENSION);
		view.setParam("rollup", "true");
		return view;
	}
	

	/**
	 * Build the Action Required view (requires an extension class).
	 * @param availableFilters
	 * @return {View}
	 */
	private FilterOption buildActionRequiredView(Locale locale, UserInfo user){
		Filters subFilters = buildFilters(locale);
		
		subFilters.setOption("all", bldF(names.getAllUpdates(locale),null, null, ASConfig.AppIds.ALL, false, false, new String[] { "lconn.homepage.as.extension.UpdateBadgeExtension"}));
		
		FilterOption contacts = bldAF(names.getProfiles(locale), 	ASConfig.AppIds.PROFILES); // TODO undo once the contacts source type has been removed and replaced with profiles
		
		if ( enabled("microblogging") )	subFilters.setOption("statusUpdates", 	bldF(names.getStatusUpdates(locale), null, null, ASConfig.AppIds.ALL, false, true));
		if ( enabled("activities") ) 	subFilters.setOption("activities", 		bldAF(names.getActivities(locale), 	ASConfig.AppIds.ACTIVITIES));
		if ( enabled("blogs") ) 		subFilters.setOption("blogs", 			bldAF(names.getBlogs(locale), 		ASConfig.AppIds.BLOGS));
		if ( enabled("dogear") )		subFilters.setOption("bookmarks", 		bldAF(names.getBookmarks(locale), 	ASConfig.AppIds.BOOKMARKS));
		if ( enabled("communities") )	subFilters.setOption("communities",		bldAF(names.getCommunities(locale), ASConfig.AppIds.CAT_COMMUNITIES));
		if ( enabled("files") )			subFilters.setOption("files", 			bldAF(names.getFiles(locale), 		ASConfig.AppIds.FILES));
		if ( enabled("forums") )		subFilters.setOption("forums", 			bldAF(names.getForums(locale), 		ASConfig.AppIds.FORUMS));
		if ( enabled("ecm_files") )		subFilters.setOption("libraries", 		bldAF(names.getLibraries(locale), 	ASConfig.AppIds.LIBRARIES));
		if ( enabled("profiles") )		subFilters.setOption("profiles", 		bldAF(names.getProfiles(locale), 	ASConfig.AppIds.PROFILES));
		if ( enabled("wikis") )			subFilters.setOption("wikis", 			bldAF(names.getWikis(locale), 		ASConfig.AppIds.WIKIS));
		
		addThirdPartyApps(user, subFilters, locale, contacts);
		
		String label = getStr("actionrequiredLabel", locale);
		String description = getStr("actionrequiredDescription", locale);

		// nest so that the second level/header menu is the same
		Filters fs = buildFilters(locale);
		fs.setType(Filters.Type.LINKS);
		
		FilterOption nested = buildDescF(label, description);
		nested.setFilters(subFilters);
		fs.setOption("actionRequired", nested);
		
		FilterOption actionRequiredView = new FilterOption(label);
		actionRequiredView.setGroupId(ASConfig.GroupIds.ACTIONS);
		actionRequiredView.setFilters(fs);
		actionRequiredView.setDescription(description);
		actionRequiredView.addExtension(ASConfig.Extensions.ACTION_REQUIRED_VIEW_EXTENSION);
		actionRequiredView.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
		actionRequiredView.addExtension(ASConfig.Extensions.ACTIVITY_REPLY_EXTENSION);
		actionRequiredView.addExtension(ASConfig.Extensions.NETWORK_INVITE_EXTENSION);
		return actionRequiredView;
	}
	
	/**
	 * Build the Saved view (requires an extension class).
	 * @return {View}
	 */
	private FilterOption buildSavedView(Locale locale, UserInfo user){
		
		Filters subFilters = buildFilters(locale);

		subFilters.setOption("all", bldAF(names.getAllUpdates(locale), ASConfig.AppIds.ALL));
		
		FilterOption contacts = bldAF(names.getProfiles(locale), 	ASConfig.AppIds.PROFILES); // TODO undo once the contacts source type has been removed and replaced with profile
		
		if ( enabled("microblogging") )	subFilters.setOption("statusUpdates", 	bldF(names.getStatusUpdates(locale), null, null, ASConfig.AppIds.ALL, false, true));
		if ( enabled("activities") ) 	subFilters.setOption("activities", 		bldAF(names.getActivities(locale), 	ASConfig.AppIds.ACTIVITIES));
		if ( enabled("blogs") ) 		subFilters.setOption("blogs", 			bldAF(names.getBlogs(locale), 		ASConfig.AppIds.BLOGS));
		if ( enabled("dogear") )		subFilters.setOption("bookmarks", 		bldAF(names.getBookmarks(locale), 	ASConfig.AppIds.BOOKMARKS));
		if ( enabled("communities") )	subFilters.setOption("communities",		bldAF(names.getCommunities(locale), ASConfig.AppIds.CAT_COMMUNITIES));
		if ( enabled("files") )			subFilters.setOption("files", 			bldAF(names.getFiles(locale), 		ASConfig.AppIds.FILES));
		if ( enabled("forums") )		subFilters.setOption("forums", 			bldAF(names.getForums(locale),  	ASConfig.AppIds.FORUMS));
		if ( enabled("ecm_files") )		subFilters.setOption("libraries", 		bldAF(names.getLibraries(locale),  	ASConfig.AppIds.LIBRARIES));
		if ( enabled("profiles") )		subFilters.setOption("profiles", 		bldAF(names.getProfiles(locale), 	ASConfig.AppIds.PROFILES));
		if ( enabled("wikis") )			subFilters.setOption("wikis", 			bldAF(names.getWikis(locale), 		ASConfig.AppIds.WIKIS));
		
		addThirdPartyApps(user, subFilters, locale, contacts);

		String label = getStr("savedLabel", locale);
		String description = getStr("savedDescription", locale);
		

		// nest so that the second level/header menu is the same
		Filters fs = buildFilters(locale);
		fs.setType(Filters.Type.LINKS);
		
		FilterOption nested = buildDescF(label, description);
		nested.setFilters(subFilters);
		fs.setOption("saved", nested);
		
		FilterOption savedView = new FilterOption(label);
		savedView.setGroupId(ASConfig.GroupIds.SAVED);
		savedView.setFilters(fs);
		savedView.setDescription(description);
		savedView.addExtension(ASConfig.Extensions.SAVED_VIEW_EXTENSION);
		savedView.addExtension(ASConfig.Extensions.ACTIVITY_REPLY_EXTENSION);
		savedView.addExtension(ASConfig.Extensions.DISABLE_DYNAMIC_LOAD_EXTENSION);
		savedView.addExtension(ASConfig.Extensions.GLOBAL_SEARCH_HASHTAG_EXTENSION);
		return savedView;
	}
	
	
	/**
	 * Build up the My Notifications view
	 * @param locale
	 * @return
	 */
	private FilterOption buildMyNotificationsView(Locale locale, UserInfo user) {
		
		Filters subF = buildFilters(locale);
		subF.setOption("all", bldAF(names.getAllUpdates(locale), ASConfig.AppIds.ALL));
		
		FilterOption contacts =  bldAF(names.getProfiles(locale), 	  ASConfig.AppIds.PROFILES); // TODO undo once the contacts source type has been removed and replaced with profile
		
		if ( enabled("activities") )  subF.setOption("activities",  bldAF(names.getActivities(locale),  ASConfig.AppIds.ACTIVITIES));
		if ( enabled("blogs") )		  subF.setOption("blogs", 	  bldAF(names.getBlogs(locale), 	  ASConfig.AppIds.BLOGS));
		if ( enabled("dogear") )	  subF.setOption("bookmarks",	  bldAF(names.getBookmarks(locale),   ASConfig.AppIds.BOOKMARKS));
		if ( enabled("communities") ) subF.setOption("communities", bldAF(names.getCommunities(locale), ASConfig.AppIds.CAT_COMMUNITIES));
		if ( enabled("files") )		  subF.setOption("files", 	  bldAF(names.getFiles(locale), 	  ASConfig.AppIds.FILES));
		if ( enabled("forums") )	  subF.setOption("forums", 	  bldAF(names.getForums(locale), 	  ASConfig.AppIds.FORUMS));
		if ( enabled("ecm_files") )	  subF.setOption("libraries",   bldAF(names.getLibraries(locale),	  ASConfig.AppIds.LIBRARIES));
		if ( enabled("profiles") )	  subF.setOption("profiles", 	 bldAF(names.getProfiles(locale), 	  ASConfig.AppIds.PROFILES));
		if ( enabled("wikis") )		  subF.setOption("wikis",		  bldAF(names.getWikis(locale), 	  ASConfig.AppIds.WIKIS));
		
		addThirdPartyApps(user, subF, locale, contacts);
		
		// set up the first filter (filter by For/From Me)
		Filters firstFilter = new Filters();
		firstFilter.setType(Filters.Type.LINKS);
		firstFilter.setMenuTitle(ASConfig.CommonStrs.getSecondaryFilterMenuTitle(locale));
		FilterOption forMeFilter = bldF(getStr("filter.mynotifications.forme",  locale), null, ASConfig.GroupIds.RESPONSES, null, false, false, null, subF);
		forMeFilter.setDescription(getStr("myNotificationForMeDescription", locale));
		firstFilter.setOption("forme", forMeFilter );
		FilterOption fromMeFilter = bldF(getStr("filter.mynotifications.fromme", locale), null, ASConfig.GroupIds.NOTESFROMME, null, false, false, null, subF);
		fromMeFilter.setDescription(getStr("myNotificationFromMeDescription", locale));
		firstFilter.setOption("fromme", fromMeFilter);
		
		String label = getStr("mynotificationsLabel", locale);
		String description = getStr("mynotificationsDescription", locale);
		
		// create the view
		FilterOption view = new FilterOption(label);
		view.setUserId(ASConfig.UserIds.ME);
		view.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);

		if (isFeatureEnabled(LCSupportedFeature.NEWS_ROLLUP_BY_ACTION_MODEL_MY_NOTIFICATIONS, user.getUserOrg())) {
			view.addExtension(ASConfig.Extensions.ACTIVITY_REPLY_ON_MENTIONS_EXTENSION);
			view.addExtension(ASConfig.Extensions.MICROBLOG_DELETION_EXTENSION);
		} else {
			view.addExtension(ASConfig.Extensions.ACTIVITY_REPLY_EXTENSION);
		}

		view.addExtension(ASConfig.Extensions.DISABLE_DYNAMIC_LOAD_EXTENSION);
		view.addExtension(ASConfig.Extensions.GLOBAL_SEARCH_HASHTAG_EXTENSION);
		view.setFilters(firstFilter);
		view.setDescription(description);
		
		return view;
	}
	
	/**
	 * Build the @mentions view
	 * @param locale
	 * @return
	 */
	private FilterOption buildAtMentionsView(Locale locale) {
		

		Filters fs = buildFilters(locale);
		fs.setType(Filters.Type.LINKS);
		
		String label = getStr("atmentionsLabel", locale);
		String description = getStr("atmentionsDescription", locale);
		
		// nest so that the second level/header menu is the same
		fs.setOption("atMentions", buildDescF(label, description));
		
		FilterOption view = new FilterOption(label);
		view.setUserId(ASConfig.UserIds.ME);
		view.setGroupId(ASConfig.GroupIds.ATMENTIONS);
		view.setAppId(ASConfig.AppIds.ALL);
		view.setFilters(fs);
		view.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
		view.addExtension(ASConfig.Extensions.ACTIVITY_REPLY_EXTENSION);
		view.addExtension(ASConfig.Extensions.COMMENT_LINK_TO_EE_EXTENSION);
		view.setDescription(description);
		return view;
	}

	/** 
	 * The Discovery view needs to have extension classes added to the All and People actions.
	 * @param userId String giving id of the user we're building the view for.
	 * @param Locale locale to be used.  
	 */ 
	private FilterOption buildDiscoveryView(Locale locale, UserInfo user) {
		Filters fs = buildFilters(locale);
		
		fs.setOption("all", bldF(names.getAllUpdates(locale), null, null, ASConfig.AppIds.ALL, false, false, new String[] {"com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"}));
		
		FilterOption contacts = bldF(names.getProfiles(locale), null, null, ASConfig.AppIds.PROFILES, false, false, new String[] {"com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"});
		// TODO undo once the contacts source type has been removed and replaced with profile
		
		if ( enabled("microblogging"))	fs.setOption("statusUpdates", bldF(names.getStatusUpdates(locale), null, null, ASConfig.AppIds.ALL, false, true, new String[] {"com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"}));
		if ( enabled("activities") ) 	fs.setOption("activities", 	bldAF(names.getActivities(locale), 	ASConfig.AppIds.ACTIVITIES));
		if ( enabled("blogs") )			fs.setOption("blogs", 		bldAF(names.getBlogs(locale), 		ASConfig.AppIds.BLOGS));
		if ( enabled("dogear") )		fs.setOption("bookmarks",	bldAF(names.getBookmarks(locale), 	ASConfig.AppIds.BOOKMARKS));
		if ( enabled("communities") )	fs.setOption("communities", bldAF(names.getCommunities(locale),	ASConfig.AppIds.CAT_COMMUNITIES));
		if ( enabled("files") )			fs.setOption("files", 		bldAF(names.getFiles(locale), 		ASConfig.AppIds.FILES));
		if ( enabled("forums") )		fs.setOption("forums", 		bldAF(names.getForums(locale), 		ASConfig.AppIds.FORUMS));
		if ( enabled("ecm_files") )		fs.setOption("libraries", 	bldAF(names.getLibraries(locale), 	ASConfig.AppIds.LIBRARIES));
		if ( enabled("profiles") )		fs.setOption("profiles", 	bldF(names.getProfiles(locale), null, null, ASConfig.AppIds.PROFILES, false, false, new String[] {"com.ibm.social.as.lconn.extension.ShareboxStatusUpdateExtension"}));
		if ( enabled("wikis") )			fs.setOption("wikis",		bldAF(names.getWikis(locale), 		ASConfig.AppIds.WIKIS));
		
		addThirdPartyApps(user, fs, locale, contacts);

		String labelKey = this.shouldUseNewUI() ? "discoverCnx8Label" : "discoverLabel";
		String label = getStr(labelKey, locale);
		String descriptionKey = this.shouldUseNewUI() ? "discoverCnx8Description" : "discoverDescription";
		String description = getStr(descriptionKey, locale);
		
		FilterOption view = new FilterOption(label);
		view.setUserId(ASConfig.UserIds.PUBLIC);
		view.setFilters(fs);
		view.setLabel(label);
		view.setDescription(description);
		view.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
		view.addExtension(ASConfig.Extensions.MICROBLOG_DELETION_EXTENSION);
		view.addExtension(ASConfig.Extensions.SEARCH_BAR_EXTENSION);
		view.setParam("rollup", "true");
		return view;
	}
	
	/**
         * Build the Top Updates view (requires an extension class).
         * @return {View}
         */
        private FilterOption buildTopUpdatesView(Locale locale, UserInfo user){
			Filters subFilters = buildFilters(locale);

			subFilters.setOption("all", bldAF(names.getAllUpdates(locale), ASConfig.AppIds.ALL));

			FilterOption contacts = bldAF(names.getProfiles(locale),        ASConfig.AppIds.PROFILES); // TODO undo once the contacts source type has been removed and replaced with profile

			if ( enabled("microblogging") ) subFilters.setOption("statusUpdates",   		bldF(names.getStatusUpdates(locale), null, null, ASConfig.AppIds.ALL, false, true));
			if ( enabled("activities") )    subFilters.setOption("activities",              bldAF(names.getActivities(locale),      ASConfig.AppIds.ACTIVITIES));
			if ( enabled("blogs") )         subFilters.setOption("blogs",                   bldAF(names.getBlogs(locale),           ASConfig.AppIds.BLOGS));
			if ( enabled("dogear") )        subFilters.setOption("bookmarks",               bldAF(names.getBookmarks(locale),       ASConfig.AppIds.BOOKMARKS));
			if ( enabled("communities") )   subFilters.setOption("communities",             bldAF(names.getCommunities(locale), 	ASConfig.AppIds.CAT_COMMUNITIES));
			if ( enabled("files") )         subFilters.setOption("files",                   bldAF(names.getFiles(locale),           ASConfig.AppIds.FILES));
			if ( enabled("forums") )        subFilters.setOption("forums",                  bldAF(names.getForums(locale),          ASConfig.AppIds.FORUMS));
			if ( enabled("ecm_files") )     subFilters.setOption("libraries",               bldAF(names.getLibraries(locale),       ASConfig.AppIds.LIBRARIES));
			if ( enabled("profiles") )      subFilters.setOption("profiles",                bldAF(names.getProfiles(locale),        ASConfig.AppIds.PROFILES));
			if ( enabled("wikis") )         subFilters.setOption("wikis",                   bldAF(names.getWikis(locale),           ASConfig.AppIds.WIKIS));

			addThirdPartyApps(user, subFilters, locale, contacts);
			String label = getStr("topUpdatesLabel", locale);
			String description = getStr("topUpdatesDescription", locale);

			// nest so that the second level/header menu is the same
			Filters fs = buildFilters(locale);
			fs.setType(Filters.Type.LINKS);

			FilterOption nested = buildDescF(label, description);
			nested.setFilters(subFilters);
			fs.setOption("topUpdates", nested);

			FilterOption topUpdatesView = new FilterOption(label);
			topUpdatesView.setGroupId(ASConfig.GroupIds.SAVED);
			topUpdatesView.setFilters(fs);
			topUpdatesView.setDescription(description);
			topUpdatesView.addExtension(ASConfig.Extensions.SAVED_ACTION_EXTENSION);
			topUpdatesView.addExtension(ASConfig.Extensions.MICROBLOG_DELETION_EXTENSION);
			topUpdatesView.addExtension(ASConfig.Extensions.SEARCH_BAR_EXTENSION);
			return topUpdatesView;
        }
		
	/**
	 * Create the configuration object for ActivityStream by locale
	 */
	public JSONObject getConfigObject(Locale locale, UserInfo user){
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "getConfigObject", new Object[]{locale, user});
		}
		
		initThirdPartyApps();
		
		ASConfig config = buildStandardASConfigTemplate(locale, user);					
		
		JSONObject configJSON = null;
		if(config != null) {			
			configJSON = config.getJsonObject();
			
			if ( isGuest(user) ) {
				cullGuestOptions(configJSON);
			}
		
			if ( user.isExternalUser() ) {
				cullVisitorOptions(configJSON);
			}
		}
							
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "getConfigObject", configJSON);
		}		
		return configJSON;
	}
	
	private boolean isGuest(UserInfo user) {
		if ( logger.isLoggable(FINER) ) {
			logger.entering(CLASS_NAME,  "isGuest", user);
		}
		
		String orgId = user.getUserOrg();
		boolean isGuest = orgId != null && orgId.equals(GUEST_ORG);
		
		if ( logger.isLoggable(FINER) ) {
			logger.exiting(CLASS_NAME, "isGuest", isGuest);
		}
		return isGuest;
	}
	
	private void cullGuestOptions(JSONObject configJSON) {
		JSONObject views = ((JSONObject) ((JSONObject) configJSON.get("filters")).get("options"));
		
		JSONObject myStreamFilterOptions = ((JSONObject) ((JSONObject) ((JSONObject) views.get("myStream")).get("filters")).get("options"));
		myStreamFilterOptions.remove("discover");
		myStreamFilterOptions.remove("statusUpdates");
		views.remove("atMentions");
		cullFilters(views);
		cullFilters(myStreamFilterOptions);
		JSONObject savedFilterOptions = ((JSONObject) ((JSONObject) ((JSONObject) views.get("saved")).get("filters")).get("options"));
		cullFilters(savedFilterOptions);
		JSONObject actionRequiredFilterOptions = ((JSONObject) ((JSONObject) ((JSONObject) views.get("actionRequired")).get("filters")).get("options"));
		cullFilters(actionRequiredFilterOptions);
		JSONObject notificationFilterOptions = ((JSONObject) ((JSONObject) ((JSONObject) views.get("myNotifications")).get("filters")).get("options"));
		cullFilters(notificationFilterOptions);
	}
	
	private void cullVisitorOptions(JSONObject configJSON) {
		
		JSONObject views = ((JSONObject) ((JSONObject) configJSON.get("filters")).get("options"));
		
		JSONObject myStreamFilterOptions = ((JSONObject) ((JSONObject) ((JSONObject) views.get("myStream")).get("filters")).get("options"));
		
		myStreamFilterOptions.remove("discover");	
	}
	
	private void cullFilters(JSONObject views) {

		Iterator<?> keys = views.keySet().iterator();

        while( keys.hasNext() ){
            String key = (String)keys.next();
        	JSONObject view = ((JSONObject) views.get(key));
        	JSONObject filters = ((JSONObject) view.get("filters"));
        	if(filters != null) {
        		filters = ((JSONObject) filters.get("options"));
            	filters.remove("statusUpdates");
            	filters.remove("blogs");
            	filters.remove("communities");
            	filters.remove("forums");
            	filters.remove("profiles");
            	filters.remove("wikis");
            	filters.remove("tags");
            	filters.remove("bookmarks");
            	filters.remove("people");
        	}
        }
	}

	/**
	 * Build the ASConfig object for a specific locale and user.
	 * 
	 * @param locale
	 * @param availableFilters
	 * @return
	 */
	private ASConfig buildStandardASConfigTemplate(Locale locale, UserInfo user) {
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, "buildStandardASConfigTemplate", locale);
		}
		
		ASConfig config = new ASConfig();
		// Set all the config properties	
		config.setUserId(ASConfig.UserIds.ME);
		config.setGroupId(ASConfig.GroupIds.ALL);
		config.setAppId(ASConfig.AppIds.ALL);
		if (CnxNewUIEnabledThreadLocal.isEnabled()) {

                    if (enabled("orient")) {
                        // Add Top Updates component for CNX8 in case OrientMe is available
                        config.setFilter("topUpdates", buildTopUpdatesView(locale, user));
                    }
                    config.setFilter("myStream", buildMyStreamView(locale, user));
                    config.setFilter("discover", buildDiscoveryView(locale, user));
                    // From CNX8 forward, My Notifications, Action Required and Saved will be removed from Homepage functionality in case OrientMe is available (= Top Updates is displayed)
                } else {
                    config.setFilter("myStream", buildMyStreamView(locale, user));
                    if ( enabled("microblogging") ) {
						config.setFilter("atMentions", buildAtMentionsView(locale));
                    }
                    config.setFilter("myNotifications", buildMyNotificationsView(locale, user));
                    config.setFilter("actionRequired", buildActionRequiredView(locale, user));
                    config.setFilter("saved", buildSavedView(locale, user));
                }
		
		config.addExtension(ASConfig.Extensions.COMMENT_EXTENSION);
		config.addExtension(ASConfig.Extensions.DIRTY_CHECK_EXTENSION);
		config.addExtension(ASConfig.Extensions.GADGET_PRELOADER_EXTENSION);
		config.addExtension(ASConfig.Extensions.TRENDING_EXTENSION);
		config.addExtension(ASConfig.Extensions.REPOST_EXTENSION);
        config.addExtension(ASConfig.Extensions.BADGE_UPDATE_MY_NOTIFICATIONS_EXTENSION);
        config.addExtension(ASConfig.Extensions.BADGE_UPDATE_MENTIONS_EXTENSION);

		config.setEEManager(ASConfig.Extensions.EE_MANAGER);
		config.setDirtyFlagChecker(true);
		config.setUserInfo(user);
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, "buildStandardASConfigTemplate", config);
		}
		return config;
	}
	
	private boolean shouldUseNewUI() {
		return CnxNewUIEnabledThreadLocal.isEnabled();
	}

    /**
	 * Utility method to build a fitler with just an appId
	 * @param title for the filter
	 * @param appId
	 * @return
	 */
	private FilterOption bldAF(String title, String appId) {
		return bldF(title, null, null, appId, false, false, null);
	}
	
	private FilterOption buildDescF(String title, String description) {
		// Create and return the I'm Following view
		FilterOption fop = bldF(title, null, null, null, false, false, null);
		fop.setDescription(description);
		
		return fop;
	}
	
	/**
	 * Utility method to build a fitler with just an appId & subFilters
	 * @param title title for the filter
	 * @param appId appId if required, else null
	 * @param subFilters subFilters if required, else null
	 * @return
	 */
	private FilterOption bldAF(String title, String appId, Filters subFilters) {
		return bldF(title, null, null, appId, false, false, null, subFilters);
	}
	
	/**
	 * Utility method to build a filter with optional: userId, groupId, appId, rollup param, broadcast param
	 * @param title title for the filter
	 * @param userId userId if required, else null
	 * @param groupId groupId if required, else null
	 * @param appId appId if required, else null
	 * @param rollup true if rollup=true param required, else false 
	 * @param broadcast true if broadcast=true param required, else false
	 * @return
	 */
	private FilterOption bldF(String title, String userId, String groupId, String appId, boolean rollup, boolean broadcast) {
		return bldF(title, userId, groupId, appId, rollup, broadcast, null, null);
	}
	
	
	private FilterOption bldF(String title, String userId, String groupId, String appId, boolean rollup, boolean broadcast, String[] extensions) {
		return bldF(title, userId, groupId, appId, rollup, broadcast, extensions, null);
	}

	/**
	 * Utility method to build a filter with optional: userId, groupId, appId, rollup param, broadcast param, extensions list
	 * @param title title for the filter
	 * @param userId userId if required, else null
	 * @param groupId groupId if required, else null
	 * @param appId appId if required, else null
	 * @param rollup true if rollup=true param required, else false 
	 * @param broadcast true if broadcast=true param required, else false
	 * @param extensions array of extension class names if required, else null
	 * @return
	 */
	private FilterOption bldF(String title, String userId, String groupId, String appId, 
			boolean rollup, boolean broadcast, String[] extensions, Filters subFilters) {
		FilterOption fop = new FilterOption(title);
		
		if ( userId != null )		fop.setUserId(userId);
		if ( groupId != null )		fop.setGroupId(groupId);
		if ( appId != null ) 		fop.setAppId(appId);
		if ( rollup )				fop.setParam("rollup", "true");
		if ( broadcast ) 			fop.setParam("broadcast", "true");
		
		if ( extensions != null && extensions.length > 0 ) {
			for ( String ext: extensions ) {
				fop.addExtension(ext);
			}
		}
		
		if ( subFilters != null ) {
			fop.setFilters(subFilters);
		}
		
		return fop;
	}
	
	/**
	 * Utility method to create filters object, with default label
	 * @param locale
	 * @return
	 */
	private Filters buildFilters(Locale locale) {
		Filters filters = new Filters();
		filters.setMenuTitle("");
		return filters;
	}
	
	/**
	 * Checks to see if a service is enabled in LotusConnections-config.xml
	 * @param serviceName
	 * @return
	 */
	private boolean isServiceEnabled(String serviceName) {		
		ComponentEntry component = vch.getComponentConfig(serviceName);
		return ( component != null && ( component.isUrlEnabled() || component.isSecureUrlEnabled() ) );
	}
	
	/**
	 * Utility method to check if app enabled.
	 * Used to shorten code to make it clean(er(ish))
	 * @param appId
	 * @return
	 */
	private boolean enabled(String appId) {
		return isServiceEnabled(appId);
	}
	
	/*
	* Verifies whether the Connections new UI is enabled based on LCC config settings and a new UI cookie
	* @return true if new UI is enabled (for the user), false otherwise
	*/
	public boolean checkConnectionsNewUIEnabled(HttpServletRequest request) {
		//CNXSERV-14488-Changes to reuse the isCNX8UI function from infra repo in Homepage
		boolean _cnxNewUiEnabled = false;
		_cnxNewUiEnabled=UIConfigHelper.INSTANCE.isCNX8UI(request);
		CnxNewUIEnabledThreadLocal.setEnabled(_cnxNewUiEnabled);
		return _cnxNewUiEnabled;
	}

	/**
		* Utility method to check if a generic property is set to true or false
		* @param propertyId id of the property to check
		* @return true if value set to 'true', false if otherwise
		*/
	private boolean isGenericPropertyEnabled(String propertyId) {

		if (logger.isLoggable(Level.FINE)) {
			logger.entering(CLASS_NAME, "isGenericPropertyEnabled >", propertyId);
		}
		Properties genericProperties = vch.getGenericProperites();
		if (genericProperties != null) {
			boolean isEnabled = (boolean) "true".equalsIgnoreCase(genericProperties.getProperty(propertyId));
			if (logger.isLoggable(Level.FINE)) {
				logger.entering(CLASS_NAME, "isGenericPropertyEnabled <", isEnabled);
			}
			return isEnabled;
		}
		if (logger.isLoggable(Level.FINE)) {
			logger.entering(CLASS_NAME, "isGenericPropertyEnabled <", "false (no generic properties available)");
		}
		return false;
	}
	
	/**
	 * Retreived list of registered ThirdPartyApps
	 */
	private void initThirdPartyApps() {
		ThirdPartyAppHelper.setComponentName("homepage");		
	}
	
	/**
	 * Adds the list of ThirdPartyApps to a Filters list
	 * @param fs
	 */
	private void addThirdPartyApps(UserInfo user, Filters fs, Locale locale, FilterOption profiles) {
		addThirdPartyApps(user, fs, null, locale, profiles);
	}
	
	/**
	 * Adds the list of ThirdPartyApps to a Filters list, with optional sub filters
	 * @param fs Filters
	 * @param subFilter subFilters if required, else null
	 */
	private void addThirdPartyApps(UserInfo user, Filters fs, Filters subFilter, Locale locale, FilterOption contacts) {
		if ( !isGuest(user) ) {
			List<ThirdPartyApp> thirdPartyApps = ThirdPartyAppHelper.getThirdPartyApplications(locale);
			for ( ThirdPartyApp app : thirdPartyApps ) 
			{
				if ((app.getAppId() != null) && (app.getAppId().equalsIgnoreCase("contacts")))
				{
					contacts.setLabel(app.getLabel());
					fs.setOption("contacts", 	contacts);
				}
				else 
					fs.setOption(app.getAppId(), bldAF(app.getLabel(), app.getAppId(), subFilter));
			}
		}
	}

	/**
	 * Helper method returning a string from activityStreamConfigResourceBundle
	 * @param key 
	 * @return
	 */
	private String getStr(String key, Locale locale){
		return activityStreamConfigResourceBundle.getString(key, locale);
	}
	
	public ResourceBundle getActivityStreamConfigResourceBundle() {
		return activityStreamConfigResourceBundle;
	}

	public void setActivityStreamConfigResourceBundle(ResourceBundle activityStreamConfigResourceBundle) {
		this.activityStreamConfigResourceBundle = activityStreamConfigResourceBundle;
	}
	
	public INewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(INewsService newsService) {
		this.newsService = newsService;
	}
}
