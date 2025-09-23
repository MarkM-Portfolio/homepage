-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

INSERT INTO HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID, ORGANIZATION_EXID) VALUES ('00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000');

--HOMEPAGE

-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.widgetx4a43x82aaxb00187218631', '%panel.widget', 2, 0, 1);

-- Insert the panel tab page update
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.updatex4a43x82aaxb00187218631', '%panel.update', 1, 0, 1);
 
-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.customx4a43x82aaxb00187218631', '%panel.custom', 2, 1, 1);

-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.getstartx4a43x82aaxb001872186', '%panel.getstart', 1, 0, 1);

--
-- Insert special non-UI panels needed for gadgets
--

-- hidden pane for gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.gadgetpanx11e1b0c40800200c9a66', '%panel.gadgets', 1, 0, 1);

-- hidden pane for EE gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.embeddedxx11e1b0c40800200c9a66', '%panel.embedxp', 1, 0, 1);

-- hidden pane for sharebox gadgets
INSERT INTO HOMEPAGE.HP_TAB 
                  (TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES            ('_noui.share_diax11e1b0c40800200c9a66', '%panel.sharedialog', 1, 0, 1);

 
-- Beginning Dogear widgets 

-- Dogear Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dogear46x0a77x4a43x82aaxb00187218631', '%widget.dogear.name', '%widget.dogear.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7b1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dogear46x0a77x4a43x82aaxb00187218631');

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dembk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.personal.name', '%widget.dogear.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7c1a34f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dembk46x0a77x4a43x82aaxb00187218631');

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('depbk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.popular.name', '%widget.dogear.popular.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7w1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'depbk46x0a77x4a43x82aaxb00187218631');

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('derbk46x0a77x4a43x82aaxb00187218631',  '%widget.dogear.recent.name', '%widget.dogear.recent.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7t1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'derbk46x0a77x4a43x82aaxb00187218631');

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dewl46x0a77x4a43x82aaxb00187218631', '%widget.dogear.watchlist.name', '%widget.dogear.watchlist.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7m1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dewl46x0a77x4a43x82aaxb00187218631');

-- End Dogear widgets



-- Beginning Activities widgets

-- Activities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('activitixa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.name', '%widget.activities.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4574xbf4ax10e389b5b4e3', 'activities', 'activitixa187x491dxa4bfx2e1261d0b6ec');

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myactxa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.personal.name', '%widget.activities.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4874xbf4ax10e389b5b4e3', 'activities', 'myactxa187x491dxa4bfx2e1261d0b6ec');

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pubactxa187x491dxa4bfx2e1261d0b6ec',  '%widget.activities.public.name', '%widget.activities.public.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0v9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'pubactxa187x491dxa4bfx2e1261d0b6ec');

-- Sidebar Activities TODO widget - in update page
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('activities-sidebar7x4229x8',  '%widget.activities.sidebar.todo.name', '%widget.activities.sidebar.todo.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0z9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'activities-sidebar7x4229x8');

-- End Activities widgets

-- Beginning Communities widgets

-- Communities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('communitxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.name', '%widget.communities.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9317x4195x90c0x696c1b6da4ff', 'communities', 'communitxe7c4x4e08xab54x80e7a4eb8933');

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('mycommunxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.personal.name', '%widget.communities.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6624x9317x4195x90c0x696c1b6da4ff', 'communities', 'mycommunxe7c4x4e08xab54x80e7a4eb8933');

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pubcommuxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.public.name', '%widget.communities.public.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9677y4195x90c0x696c1b6da4ff', 'communities', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933');

-- End Communities widgets




-- Beginning Blogs widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('blogs448xcd34x4565x9469x9c34fcefe48c', '%widget.blogs.name', '%widget.blogs.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'BLOGS', 1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ac8ce708x3f95x4357xa3c9xc8673baab246', 'blogs', 'blogs448xcd34x4565x9469x9c34fcefe48c');

-- End Blogs widget




-- Beginning Profiles widgets

-- Profiles Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('profilesxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.name', '%widget.profiles.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4ad0x807ex2aa4373360bd', 'profiles', 'profilesxaac7x4229x87bbx9a1c3551c591');

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myprofisxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.personal.name', '%widget.profiles.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7451x4ad0x807ex2aa4373360bd', 'profiles', 'myprofisxaac7x4229x87bbx9a1c3551c591');

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('colprofsxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.colleagues.name', '%widget.profiles.colleagues.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES',  0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7891x4aw0x807ex2aa4373360bd', 'profiles', 'colprofsxaac7x4229x87bbx9a1c3551c591');

-- End Profiles widgets



-- Beginning Wiki Widget

-- My Wiky widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('mywikiz1xaac7x4229x87BBx91ac3551c591', '%widget.mywiki.name', '%widget.mywiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4awk5020ex2aa4373360bd', 'wikis', 'mywikiz1xaac7x4229x87BBx91ac3551c591');

-- Popular Wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pop-wiki1xaac7x4229x87BBx91ac3551c5', '%widget.popwiki.name', '%widget.popwiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b8997620ojaw08d20ex2o4837336r8j', 'wikis', 'pop-wiki1xaac7x4229x87BBx91ac3551c5');

-- Latest wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('latest-wiki5jz1xaac7x4229x87BBx91ac', '%widget.latestwiki.name', '%widget.latestwiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('45thpb899762998dw08ee96x20of33o0Ur8j', 'wikis', 'latest-wiki5jz1xaac7x4229x87BBx91ac');


-- My Files widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myFilesPb86locI7vRV4yY1KKawZvE8Qul88', '%widget.files.name', '%widget.files.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('U9PdeKPWtviSGv2QP57ZRady4RHYBPyXCL9a', 'files', 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88');

-- Files shared with me widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('sharedFilesV4fv72LD5NAcGv2nbrex0ExEq', '%widget.sharedfiles.name', '%widget.sharedfiles.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('JlNdbgUL4kAwMEFAEAwlYXQ3YBOWnBry4x6Y', 'files', 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq');

-- Sand widget
INSERT INTO HOMEPAGE.WIDGET 
			(WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_MARKED_CACHABLE, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET) 
VALUES 		('recommend7x4f6hd93kd9', '%widget.sand.recommend.name', '%widget.sand.recommend.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'SAND', 1, 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png', 0);

INSERT INTO HOMEPAGE.PREREQ 
			(PREREQ_ID, APP_ID, WIDGET_ID) 
VALUES 		('9t1a20f1xc4cax6cc4x8b0bx51af2ddef2cd', 'sand', 'recommend7x4f6hd93kd9');


--Community Event Widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('commuevtxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.event.name', '%widget.communities.event.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 39, 'custom', -1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6654x9317x4195x90c0x696c1b6da4ff', 'communities', 'commuevtxe7c4x4e08xab54x80e7a4eb8933');

--
-- CONN MAIL
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('405a4f26-fa08-4cef-a995-7d90fbe2634f', '%widget.connmail.name', '%widget.connmail.desc', '{connectionsmail}/gadgets/inbox.xml', NULL, 1, 1, 0, NULL, 'CONNECTIONSMAIL', 0, 0, 0, '{connectionsmail}/gadgets/inbox.xml', NULL, 1, 43, 'custom', -1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0d0a920d-3772-48bf-84e3-069676aea87b', 'connectionsmail', '405a4f26-fa08-4cef-a995-7d90fbe2634f');


--
-- SHARE Microblogging
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('826e0a39-d231-49bd-a1fa-b1e6e787aaa1', '%widget.connshare.microblog.name', '%widget.connshare.microblog.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml', NULL, 1, 1, 0, NULL, 'CONNECTIONSSHARE', 0, 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml', NULL, 1, 39, 'intranet_access', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0c28301d-40c6-49b9-ab2e-918f58076c0f', 'news', '826e0a39-d231-49bd-a1fa-b1e6e787aaa1');


--
-- SHARE Files
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('8426c915-6836-4a4b-a3f5-2db0ced9909f', '%widget.connshare.files.name', '%widget.connshare.files.desc', '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml', NULL, 1, 1, 0, NULL, 'CONNECTIONSSHARE', 0, 0, 0, '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml', NULL, 1, 39, 'custom', 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('37c6cd92-6ef5-43b9-8022-89a051e37e9a', 'files', '8426c915-6836-4a4b-a3f5-2db0ced9909f');


--
-- Connections EE
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('aad20aa1-c0fa-48ef-bd05-8abe630c0012', '%widget.connee.name', '%widget.connee.desc', '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml', NULL, 1, 1, 0, NULL, 'CONNECTIONSEE', 0, 0, 0, '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml', NULL, 1, 7, 'custom', -1);



--95519: Pre-register the ActivityStream gadget in initData, fixup, migration for Homepage DB. (95015)
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('7514c563-1a38-4f91-b0c2-881288dd7dcc', '%widget.activitystream.name', '%widget.activitystream.desc', '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml', NULL, 1, 1, 0, NULL, 'CONNECTIONSAS', 0, 0, 0, '${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml', NULL, 1, 7, 'custom', -1);


----------------
--- INSERT THE DATA INTO THE WIDGET_TAB TABLE
----------------
-- Dogear Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dogear7x4229x87BBx91ac3551c5', 'dogear46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dembkx7x4229x87BBx91ac3551c5', 'dembk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_depbkx7x4229x87BBx91ac3551c5', 'depbk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_derbkx7x4229x87BBx91ac3551c5', 'derbk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dewlx7x4229x87BBx91ac3551c5', 'dewl46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_activitixa187x491dxa4bfx2e1', 'activitixa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myactxa187x491dxa4bfx2e1261', 'myactxa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubactxa187x491dxa4bfx2e126', 'pubactxa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_communitxe7c4x4e08xab54x80e', 'communitxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mycommunxe7c4x4e08xab54x80e', 'mycommunxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubcommuxe7c4x4e08xab54x80e', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Blogs widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_blogs448xcd34x4565x9469x9c3', 'blogs448xcd34x4565x9469x9c34fcefe48c', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_profilesxaac7x4229x87bbx9a1', 'profilesxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myprofisxaac7x4229x87bbx9a1', 'myprofisxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_colprofsxaac7x4229x87bbx9al', 'colprofsxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- My Wiky widget                
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mywikiz1xaac7x4229x87BBx91a', 'mywikiz1xaac7x4229x87BBx91ac3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Popular Wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pop-wiki1xaac7x4229x87BBx91', 'pop-wiki1xaac7x4229x87BBx91ac3551c5', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Latest wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_latest-wiki5jz1xaac7x4229x8', 'latest-wiki5jz1xaac7x4229x87BBx91ac', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Files Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_HXMb14Y53n2XAuLeHgrZI3f1CEGhN', 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Files sharded with me widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_FfQvWEJHX1TBvSCW0PS8ayfbPlZ1k', 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq', '_panel.widgetx4a43x82aaxb00187218631', 'primary');

-- Sidebar activities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_activities-sidebar', 'activities-sidebar7x4229x8', '_panel.updatex4a43x82aaxb00187218631', 'primary');

-- Sidebar recommend widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
		(WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE) 
VALUES 	('UPDATES_recommend-sidebar', 'recommend7x4f6hd93kd9', '_panel.updatex4a43x82aaxb00187218631', 'primary');

--Community Event Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_communityevent-sidebar', 'commuevtxe7c4x4e08xab54x80e7a4eb8933', '_panel.updatex4a43x82aaxb00187218631', 'primary');


-- CONN MAIL

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('d8361d6b-0021-41de-b562-55ca27a3e954', '405a4f26-fa08-4cef-a995-7d90fbe2634f', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

-- SHARE Microblogging

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('825edb79-8b86-45c3-afbb-4891355755b2', '826e0a39-d231-49bd-a1fa-b1e6e787aaa1', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('5394716f-273c-4a0e-9845-5069275ab029', '826e0a39-d231-49bd-a1fa-b1e6e787aaa1', '_noui.share_diax11e1b0c40800200c9a66', 'primary');

-- SHARE Files

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('ecd31763-d711-4a57-9e14-eb6b3d71daee', '8426c915-6836-4a4b-a3f5-2db0ced9909f', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('c1f5196b-4f94-49ec-bc38-f7d8dacc0bb4', '8426c915-6836-4a4b-a3f5-2db0ced9909f', '_noui.share_diax11e1b0c40800200c9a66', 'primary');

-- Connections EE

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('985f431a-3c00-4301-a93b-d375181d9814', 'aad20aa1-c0fa-48ef-bd05-8abe630c0012', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('8cd18252-ec33-4f8f-97df-b62f3bb49e28', 'aad20aa1-c0fa-48ef-bd05-8abe630c0012', '_noui.embeddedxx11e1b0c40800200c9a66', 'primary');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('61532c5f-664f-40c0-bd09-abf337b43312', 'commuevtxe7c4x4e08xab54x80e7a4eb8933', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

--95519: Pre-register the ActivityStream gadget in initData, fixup, migration for Homepage DB. (95015)
INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('e2070ae0-1ca4-4de6-a550-9d7b3ea5fa84', '7514c563-1a38-4f91-b0c2-881288dd7dcc', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary');

----------------
--- END THE DATA INTO THE WIDGET_TAB TABLE
----------------

---------------------------------------------------------
-- Insert a "fake" user in PERSON table to be used in public stories
---------------------------------------------------------
INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID, DISPLAYNAME, EXID, STATE, MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001', '%anyone', '00000000-0000-0000-0000-000000000001', 0, 2);


INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW VALUES ('00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', '00000000-0000-0000-0000-000000000001', 1, '00000000-0000-0000-0000-000000000000');

