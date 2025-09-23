-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2017                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

INSERT INTO HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID, ORGANIZATION_EXID) VALUES ('00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000')@

--HOMEPAGE

-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.widgetx4a43x82aaxb00187218631' , '%panel.widget' , 2 , 0, 1)@

-- Insert the panel tab page update
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.updatex4a43x82aaxb00187218631' , '%panel.update' , 1 , 0, 1)@
 
-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.customx4a43x82aaxb00187218631' , '%panel.custom' , 2 , 1, 1)@

-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_panel.getstartx4a43x82aaxb001872186' , '%panel.getstart' , 1 , 0, 1)@

--
-- Insert special non-UI panels
--

-- hidden pane for gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.gadgetpanx11e1b0c40800200c9a66' , '%panel.gadgets' , 1 , 0, 1)@

-- hidden pane for EE gadgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.embeddedxx11e1b0c40800200c9a66' , '%panel.embedxp' , 1 , 0, 1)@

-- hidden pane for sharebox gadgets
INSERT INTO HOMEPAGE.HP_TAB 
                  (TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES            ('_noui.share_diax11e1b0c40800200c9a66' , '%panel.sharedialog' , 1 , 0, 1)@

-- hidden pane for iwidgets
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE, ENABLED)
VALUES 		('_noui.iwidgetpanx11e1b0c40800200c9a6' , '%panel.iwidgets' , 1 , 0, 1)@

 
-- Beginning Dogear widgets 

-- Dogear Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dogear46x0a77x4a43x82aaxb00187218631', '%widget.dogear.name', '%widget.dogear.desc', '{webresources}/web/lconn.homepage/widgets/bookmarks/dogear.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 1, 0, '{webresources}/web/lconn.homepage/widgets/bookmarks/dogear.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7b1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dogear46x0a77x4a43x82aaxb00187218631')@

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dembk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.personal.name', '%widget.dogear.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/personal/mybookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7c1a34f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dembk46x0a77x4a43x82aaxb00187218631')@

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('depbk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.popular.name', '%widget.dogear.popular.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/popular/popularbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7w1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'depbk46x0a77x4a43x82aaxb00187218631')@

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('derbk46x0a77x4a43x82aaxb00187218631',  '%widget.dogear.recent.name', '%widget.dogear.recent.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/recent/recentbookmarks.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7t1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'derbk46x0a77x4a43x82aaxb00187218631')@

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('dewl46x0a77x4a43x82aaxb00187218631', '%widget.dogear.watchlist.name', '%widget.dogear.watchlist.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/watching/watchlist.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7m1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dewl46x0a77x4a43x82aaxb00187218631')@

-- End Dogear widgets



-- Beginning Activities widgets

-- Activities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('activitixa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.name', '%widget.activities.desc', '{webresources}/web/lconn.homepage/widgets/activities/activities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1, 0, '{webresources}/web/lconn.homepage/widgets/activities/activities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4574xbf4ax10e389b5b4e3', 'activities', 'activitixa187x491dxa4bfx2e1261d0b6ec')@

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myactxa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.personal.name', '%widget.activities.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/personal/myactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4874xbf4ax10e389b5b4e3', 'activities', 'myactxa187x491dxa4bfx2e1261d0b6ec')@

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pubactxa187x491dxa4bfx2e1261d0b6ec',  '%widget.activities.public.name', '%widget.activities.public.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/pub/publicactivities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0v9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'pubactxa187x491dxa4bfx2e1261d0b6ec')@

-- Sidebar Activities TODO widget - in update page
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('activities-sidebar7x4229x8',  '%widget.activities.sidebar.todo.name', '%widget.activities.sidebar.todo.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activitiesTodoList.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0z9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'activities-sidebar7x4229x8')@

-- End Activities widgets

-- Beginning Communities widgets

-- Communities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('communitxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.name', '%widget.communities.desc', '{webresources}/web/lconn.homepage/widgets/communities/communities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  0, '{webresources}/web/lconn.homepage/widgets/communities/communities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9317x4195x90c0x696c1b6da4ff', 'communities', 'communitxe7c4x4e08xab54x80e7a4eb8933')@

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('mycommunxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.personal.name', '%widget.communities.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/personal/mycommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6624x9317x4195x90c0x696c1b6da4ff', 'communities', 'mycommunxe7c4x4e08xab54x80e7a4eb8933')@

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pubcommuxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.public.name', '%widget.communities.public.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/pub/publiccommunities.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9677y4195x90c0x696c1b6da4ff', 'communities', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933')@

-- End Communities widgets




-- Beginning Blogs widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('blogs448xcd34x4565x9469x9c34fcefe48c', '%widget.blogs.name', '%widget.blogs.desc', '{webresources}/web/lconn.homepage/widgets/blogs/blogs.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'BLOGS', 1, 0, '{webresources}/web/lconn.homepage/widgets/blogs/blogs.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ac8ce708x3f95x4357xa3c9xc8673baab246', 'blogs', 'blogs448xcd34x4565x9469x9c34fcefe48c')@

-- End Blogs widget




-- Beginning Profiles widgets

-- Profiles Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('profilesxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.name', '%widget.profiles.desc', '{webresources}/web/lconn.homepage/widgets/profiles/profiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 1, 0, '{webresources}/web/lconn.homepage/widgets/profiles/profiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4ad0x807ex2aa4373360bd', 'profiles', 'profilesxaac7x4229x87bbx9a1c3551c591')@

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myprofisxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.personal.name', '%widget.profiles.personal.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/personal/myprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7451x4ad0x807ex2aa4373360bd', 'profiles', 'myprofisxaac7x4229x87bbx9a1c3551c591')@

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('colprofsxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.colleagues.name', '%widget.profiles.colleagues.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES',  0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/colleagues/colleagueprofile.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7891x4aw0x807ex2aa4373360bd', 'profiles', 'colprofsxaac7x4229x87bbx9a1c3551c591')@

-- End Profiles widgets



-- Beginning Wiki Widget

-- My Wiky widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('mywikiz1xaac7x4229x87BBx91ac3551c591', '%widget.mywiki.name', '%widget.mywiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/mywiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4awk5020ex2aa4373360bd', 'wikis', 'mywikiz1xaac7x4229x87BBx91ac3551c591')@

-- Popular Wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('pop-wiki1xaac7x4229x87BBx91ac3551c5', '%widget.popwiki.name', '%widget.popwiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/popularwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b8997620ojaw08d20ex2o4837336r8j', 'wikis', 'pop-wiki1xaac7x4229x87BBx91ac3551c5')@

-- Latest wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('latest-wiki5jz1xaac7x4229x87BBx91ac', '%widget.latestwiki.name', '%widget.latestwiki.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/wikis/latestwiki.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('45thpb899762998dw08ee96x20of33o0Ur8j', 'wikis', 'latest-wiki5jz1xaac7x4229x87BBx91ac')@


-- My Files widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('myFilesPb86locI7vRV4yY1KKawZvE8Qul88', '%widget.files.name', '%widget.files.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml','${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  1, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/files.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('U9PdeKPWtviSGv2QP57ZRady4RHYBPyXCL9a','files','myFilesPb86locI7vRV4yY1KKawZvE8Qul88')@

-- Files shared with me widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET)
VALUES      ('sharedFilesV4fv72LD5NAcGv2nbrex0ExEq', '%widget.sharedfiles.name', '%widget.sharedfiles.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml','${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  0, 0, '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/files/sharedFiles.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('JlNdbgUL4kAwMEFAEAwlYXQ3YBOWnBry4x6Y','files','sharedFilesV4fv72LD5NAcGv2nbrex0ExEq')@

-- Sand widget
INSERT INTO HOMEPAGE.WIDGET 
			(WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT,WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_MARKED_CACHABLE, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET) 
VALUES 		('recommend7x4f6hd93kd9','%widget.sand.recommend.name','%widget.sand.recommend.desc','${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png',1,1,1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg','SAND',1,0,0,'${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/sand/recommend.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png',0)@

INSERT INTO HOMEPAGE.PREREQ 
			(PREREQ_ID,APP_ID,WIDGET_ID) 
VALUES 		('9t1a20f1xc4cax6cc4x8b0bx51af2ddef2cd','sand','recommend7x4f6hd93kd9')@


--Community Event Widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('commuevtxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.event.name', '%widget.communities.event.desc', '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  0, '${COMMON_CONTEXT_ROOT}/web/lconn.calendar/CalendarGadget.xml', '${COMMON_CONTEXT_ROOT}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',1, 39,'custom', -1)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6654x9317x4195x90c0x696c1b6da4ff', 'communities', 'commuevtxe7c4x4e08xab54x80e7a4eb8933')@

--
-- CONN MAIL
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('405a4f26-fa08-4cef-a995-7d90fbe2634f','%widget.connmail.name','%widget.connmail.desc','{connectionsmail}/gadgets/inbox.xml',NULL,1,1,0,NULL,'CONNECTIONSMAIL',0,0,0,'{connectionsmail}/gadgets/inbox.xml',NULL,1, 43, 'custom', -1)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0d0a920d-3772-48bf-84e3-069676aea87b', 'connectionsmail', '405a4f26-fa08-4cef-a995-7d90fbe2634f')@


--
-- SHARE Microblogging
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('826e0a39-d231-49bd-a1fa-b1e6e787aaa1','%widget.connshare.microblog.name','%widget.connshare.microblog.desc','${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml',NULL,1,1,0,NULL,'CONNECTIONSSHARE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/lconn.news.microblogging.sharebox/globalMicrobloggingForm.xml',NULL,1,39,'custom', 0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('0c28301d-40c6-49b9-ab2e-918f58076c0f', 'news', '826e0a39-d231-49bd-a1fa-b1e6e787aaa1')@


--
-- SHARE Files
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('8426c915-6836-4a4b-a3f5-2db0ced9909f','%widget.connshare.files.name','%widget.connshare.files.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml',NULL,1,1,0,NULL,'CONNECTIONSSHARE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.sharebox/UploadFile.xml',NULL,1,39,'custom', 1)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('37c6cd92-6ef5-43b9-8022-89a051e37e9a','files','8426c915-6836-4a4b-a3f5-2db0ced9909f')@


--
-- Connections EE
--
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('aad20aa1-c0fa-48ef-bd05-8abe630c0012','%widget.connee.name','%widget.connee.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml',NULL,1,1,0,NULL,'CONNECTIONSEE',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.ee/ConnectionsEE.xml',NULL,1,7,'custom', -1)@


--95519: Pre-register the ActivityStream gadget in initData, fixup, migration for Homepage DB. (95015)
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('7514c563-1a38-4f91-b0c2-881288dd7dcc','%widget.activitystream.name','%widget.activitystream.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,1,0,NULL,'CONNECTIONSAS',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,7,'custom', -1)@


----------------
--- iWidgets used by Communities and Profiles
----------------
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('947aa812-a3e9-4cd2-a12a-e10237bffb83', 'Blog', '', '{blogs}/static/{version}!{locale}/iwidgets/blog/blogsWidget.jsp', '', 1, 1, 0,  '', ' ',  0, 1, '{blogs}/static/{version}!{locale}/iwidgets/blog/blogsWidget.jsp', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('a361de3f-7b07-4999-b5eb-5c4b085dfcd9', '947aa812-a3e9-4cd2-a12a-e10237bffb83', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('b0bd7811-7c10-42f1-9ea6-c8af83385d0a', 'IdeationBlog', '', '{blogs}/static/{version}!{locale}/iwidgets/ideationblog/Ideations.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{blogs}/static/{version}!{locale}/iwidgets/ideationblog/Ideations.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('c38e7b84-0cb1-46bb-8da2-b95a3b819d05', 'b0bd7811-7c10-42f1-9ea6-c8af83385d0a', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('b1ea602e-272f-433f-b58d-da54439d9957', 'Calendar', '', '{communities}/calendar/Calendar.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{communities}/calendar/Calendar.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('2f2d864f-65e9-4c45-bffe-82db316d5e69', 'b1ea602e-272f-433f-b58d-da54439d9957', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('e74f6a74-acb9-4be8-8b0a-8c0e8f34e7ce', 'RelatedCommunities', '', '{communities}/recomm/Recomm.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{communities}/recomm/Recomm.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('a962fc97-22e0-44c2-9b52-036dd10d4d50', 'e74f6a74-acb9-4be8-8b0a-8c0e8f34e7ce', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('1ee8c389-569a-4a9d-835f-aa78f7ee1a4f', 'Files', '', '{files}/static/{version}!{locale}/iwidgets/CommunityReferentialWidget/widget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{files}/static/{version}!{locale}/iwidgets/CommunityReferentialWidget/widget.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('c92c8227-4455-4d28-bf99-9aecfad71421', '1ee8c389-569a-4a9d-835f-aa78f7ee1a4f', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('5d62c2ce-592a-4fba-9c48-01c11bbe9aa1', 'commonTags', '', '{deploymentConfig}/static/common/widgets/descriptors/common-tags.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/common-tags.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('3c1fc492-4eaf-4a3f-9bb2-a06410c4c6c7', '5d62c2ce-592a-4fba-9c48-01c11bbe9aa1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('92927768-3986-4c46-ad77-fe02804a5a1d', 'follow', '', '{deploymentConfig}/static/common/widgets/descriptors/follow.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/follow.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('8042fb1d-d736-4b03-9747-88f201a74cce', '92927768-3986-4c46-ad77-fe02804a5a1d', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('481c91aa-f555-4f3f-80eb-329875374050', 'friendsFullPage', '', '{deploymentConfig}/static/common/widgets/descriptors/friends-fullpage.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/friends-fullpage.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('a465d4bc-ffb6-415b-9445-80c2049af706', '481c91aa-f555-4f3f-80eb-329875374050', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('fb6d48ea-1a99-4651-b758-ed581ea5c425', 'friends', '', '{deploymentConfig}/static/common/widgets/descriptors/friends.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/friends.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('922d60ae-dc75-4672-9293-95940ddb0bd3', 'fb6d48ea-1a99-4651-b758-ed581ea5c425', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('26e0ad32-207c-4750-a3ea-f947c9bfc641', 'linkRoll', '', '{deploymentConfig}/static/common/widgets/descriptors/linkroll.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/linkroll.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('b7df2a56-f8ec-4986-8189-471bd9daddf8', '26e0ad32-207c-4750-a3ea-f947c9bfc641', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('5a582d93-4fc4-40cc-9fb8-43d4b1af2828', 'multiWidget', '', '{deploymentConfig}/static/common/widgets/descriptors/multiWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/multiWidget.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('586448cb-ccd9-470d-8ed6-7a6960883d1a', '5a582d93-4fc4-40cc-9fb8-43d4b1af2828', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('1b7150c4-3cfb-42bb-aa53-db7ec145ae5a', 'multiFeedReader', '', '{deploymentConfig}/static/common/widgets/descriptors/multifeedreader.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/multifeedreader.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('14fa6122-9312-4ccb-a4ee-3d517ed53418', '1b7150c4-3cfb-42bb-aa53-db7ec145ae5a', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('1d9a766b-33d7-48eb-9cf7-ce4d335079b6', 'backgroundInfo', '', '{deploymentConfig}/static/common/widgets/descriptors/profile-details.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/profile-details.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('e954370e-916a-4313-bc30-b7405bc82c37', '1d9a766b-33d7-48eb-9cf7-ce4d335079b6', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('212661b6-0b7f-40a7-bd53-bec8f238730a', 'reportStructure', '', '{deploymentConfig}/static/common/widgets/descriptors/report-chain.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/report-chain.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('6825d5f9-5cb2-46cc-8c3f-d341a5004015', '212661b6-0b7f-40a7-bd53-bec8f238730a', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('bb8e89f5-abf5-4faf-87c4-917dff8f1c4e', 'socialTags', '', '{deploymentConfig}/static/common/widgets/descriptors/tags.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/tags.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ec96d56b-6d93-4850-b278-29c253012f96', 'bb8e89f5-abf5-4faf-87c4-917dff8f1c4e', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('ea3191e9-982b-43f0-9c49-7ecc666b7bdc', 'board', '', '{deploymentConfig}/static/common/widgets/descriptors/wall.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{deploymentConfig}/static/common/widgets/descriptors/wall.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('78e8d2bd-cf13-472e-a9f4-f6353bc909fa', 'ea3191e9-982b-43f0-9c49-7ecc666b7bdc', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('dd414900-15c7-487c-b87f-cdab3d4be983', 'SharepointFiles', '', '{webresources}/web/com.ibm.ic.webeditors.sharepoint/SharepointFrame.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.ic.webeditors.sharepoint/SharepointFrame.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('e8a54199-9599-4d3a-8d7c-3ea03134498c', 'dd414900-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('89bc1cfe-fa96-4301-8a1c-33f7a4eafec0', 'RecentUpdates', '', '{webresources}/web/com.ibm.social.as.lconn/widgets/communitiesActivityStream.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.social.as.lconn/widgets/communitiesActivityStream.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('c7a6fa5d-4d90-4827-9e28-70414981d03a', '89bc1cfe-fa96-4301-8a1c-33f7a4eafec0', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('ef6e7137-db28-4c34-a0ee-625fd757fd22', 'Updates', '', '{webresources}/web/com.ibm.social.as.lconn/widgets/profilesActivityStream.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.social.as.lconn/widgets/profilesActivityStream.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('f062a7dc-3463-40c1-995b-8ff492647c54', 'ef6e7137-db28-4c34-a0ee-625fd757fd22', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('294aa3ce-878d-4815-83e7-57ac0985accc', 'Members', '', '{webresources}/web/lconn.comm/communityMembers/communityMembers.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/communityMembers/communityMembers.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('8fe3de86-8263-4ee6-8d9f-337961c32496', '294aa3ce-878d-4815-83e7-57ac0985accc', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('9f6ada79-f395-4981-b52a-a965b334d52e', 'description', '', '{webresources}/web/lconn.comm/description/description.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/description/description.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('f941aba6-03cf-43b3-8a3b-2af928050b8e', '9f6ada79-f395-4981-b52a-a965b334d52e', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('7910424c-cf06-4635-889a-afc757be6d47', 'ImportantBookmarks', '', '{webresources}/web/lconn.comm/importantBookmarks/importantBookmarks.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/importantBookmarks/importantBookmarks.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('67b4894f-025b-44a2-83f7-ac4d2c6a12a3', '7910424c-cf06-4635-889a-afc757be6d47', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('9ce24226-b332-4869-a74c-d23ae01f1e3d', 'Tags', '', '{webresources}/web/lconn.comm/tags/tags.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/tags/tags.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('9206d7ed-6608-4b2d-900b-bac53a0b3bb2', '9ce24226-b332-4869-a74c-d23ae01f1e3d', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('3f5b5682-7d8d-43e5-9b54-fad91cfa5571', 'widgetPalette', '', '{webresources}/web/lconn.comm/widgets/applicationPalette/applicationPalette.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/widgets/applicationPalette/applicationPalette.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('868eb038-403a-4df6-8b32-a0bbc873f2f8', '3f5b5682-7d8d-43e5-9b54-fad91cfa5571', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('4f5f33cf-1802-479a-8eff-f5b25786ed97', 'Feeds', '', '{webresources}/web/lconn.comm/widgets/feedReader/feedreader.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/widgets/feedReader/feedreader.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('45e24176-43d5-41cf-9baa-4e10277149c3', '4f5f33cf-1802-479a-8eff-f5b25786ed97', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('cbc63569-03f3-4697-a99c-babc68e38889', 'CommunitiesTestWidget', '', '{webresources}/web/lconn.comm/widgets/multiInstance/multiInstance.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/widgets/multiInstance/multiInstance.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('83089d4d-60c5-42a7-9d59-33db8ae8831f', 'cbc63569-03f3-4697-a99c-babc68e38889', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('d480a725-dc92-4250-b254-eb8550fb52b2', 'SubcommunityNav', '', '{webresources}/web/lconn.comm/widgets/subcommunityNav/subcommunitynav.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.comm/widgets/subcommunityNav/subcommunitynav.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('1fd47de5-1738-4109-bdc4-a449459ffd44', 'd480a725-dc92-4250-b254-eb8550fb52b2', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('d5b57d9a-c6a3-4b35-8f21-c98248efb04e', 'Activities', '', '{webresources}/web/lconn.communityactivities/ActivityList.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.communityactivities/ActivityList.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ac1f4446-2a30-47ad-9955-8ca49f18247d', 'd5b57d9a-c6a3-4b35-8f21-c98248efb04e', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('012cc5d7-1322-4337-9185-8ad97ed3c07b', 'Forum', '', '{webresources}/web/lconn.forums/widgets/topicList.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.forums/widgets/topicList.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('012cc5d7-1322-4337-9185-8ad97ed3c07b', '012cc5d7-1322-4337-9185-8ad97ed3c07b', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('6ac10392-2503-4330-93a8-9efcd418d5c1', 'Gallery', '', '{webresources}/web/lconn.gallery/Gallery.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.gallery/Gallery.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('8e58609c-f67c-4582-a690-3195cb829e5a', '6ac10392-2503-4330-93a8-9efcd418d5c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('160057d4-401d-439e-a24b-d980631f9a52', 'RichContent', '', '{webresources}/web/lconn.rich/descriptor/RichContent.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.rich/descriptor/RichContent.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('90b5295c-5d18-4d80-befd-ebc853d39789', '160057d4-401d-439e-a24b-d980631f9a52', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('a9efd2be-16e6-48d1-a439-60d6c470b891', 'sand_recomComm', '', '{webresources}/web/lconn.sand/descriptors/CommunityRecommend.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.sand/descriptors/CommunityRecommend.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('85fa85e9-65a8-4a14-92e0-bddaafbce136', 'a9efd2be-16e6-48d1-a439-60d6c470b891', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('ed87c856-bc7a-4951-90f1-0d01ea5cdb1b', 'sand_DYK', '', '{webresources}/web/lconn.sand/descriptors/DYK.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.sand/descriptors/DYK.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('48e5fd48-d1bf-467f-93af-ad5036d7fe02', 'ed87c856-bc7a-4951-90f1-0d01ea5cdb1b', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('60aee112-27bc-4314-8788-c02f4a2d94e0', 'sand_recomItems', '', '{webresources}/web/lconn.sand/descriptors/Recommend.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.sand/descriptors/Recommend.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('30d7bd2c-2bff-4192-96a8-0c1279b025fe', '60aee112-27bc-4314-8788-c02f4a2d94e0', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('06c6f0c0-3df2-4983-8e3f-f17fc513e049', 'sand_thingsInCommon', '', '{webresources}/web/lconn.sand/descriptors/sharedLC.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.sand/descriptors/sharedLC.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('c9d0cb17-455b-4513-88ad-4d5eb08d5841', '06c6f0c0-3df2-4983-8e3f-f17fc513e049', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('fd2b2b12-b1db-4875-b696-d69816830017', 'sand_socialPath', '', '{webresources}/web/lconn.sand/descriptors/socialPath.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.sand/descriptors/socialPath.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('829e8dc7-cc8f-4e85-8403-feb817a5c154', 'fd2b2b12-b1db-4875-b696-d69816830017', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('ab6ec039-b3c5-44e6-9642-41de2b9eaad7', 'Library', '', '{webresources}/web/quickr.lw/widgetDefs/LibraryWidget_QCS_Connections.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/quickr.lw/widgetDefs/LibraryWidget_QCS_Connections.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('c9645ebb-1af4-4ed4-bd0c-a2627e8f301d', 'ab6ec039-b3c5-44e6-9642-41de2b9eaad7', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('676fca25-6450-44be-9755-3752fd4e58c1', 'CommunityWiki', '', '{wikis}/static/iwidgets/{version}/CommunityWidget/WikiWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{wikis}/static/iwidgets/{version}/CommunityWidget/WikiWidget.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('daffddc3-06d6-405e-9a46-4c3d13916b74', '676fca25-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('surveys-6450-44be-9755-3752fd4e58c1', 'Surveys', '%Surveys.desc', '{webresources}/web/com.ibm.form.integrations.formiwidget/Surveys.xml', '{webresources}/web/com.ibm.form.integrations.formiwidget/images/survey_widget_icon.png', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.form.integrations.formiwidget/Surveys.xml', '{webresources}/web/com.ibm.form.integrations.formiwidget/images/survey_widget_icon.png', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('surveys-06d6-405e-9a46-4c3d13916b74', 'surveys-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('feat-surveys-9755-3752fd4e58c1', 'FeaturedSurveys', '%Surveys.desc', '{webresources}/web/com.ibm.form.integrations.formiwidget/SingleSurvey.xml', '{webresources}/web/com.ibm.form.integrations.formiwidget/images/survey_widget_icon.png', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.form.integrations.formiwidget/SingleSurvey.xml', '{webresources}/web/com.ibm.form.integrations.formiwidget/images/survey_widget_icon.png', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('feat-surveys-9a46-4c3d13916b74', 'feat-surveys-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('Hello-6450-44be-9755-3752fd4e58c1', 'HelloWorld', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/helloworld.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.lconn.gadget/test/container/helloworld.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('Hello-06d6-405e-9a46-4c3d13916b74', 'Hello-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('EEHello-6450-44be-9755-3752fd4e58c1', 'EEHelloWorld', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/EEHelloWorld.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/EEHelloWorld.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('EEHello-06d6-405e-9a46-4c3d13916b74', 'EEHello-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('Hello-Gadget-9755-3752fd4e58c1', 'HelloWorldGadget', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/helloWorldGadget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.lconn.gadget/test/container/helloWorldGadget.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('Hello-Gadget-9a46-4c3d13916b74', 'Hello-Gadget-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('File-6450-44be-9755-3752fd4e58c1', 'TestFile', '', '{webresources}/web/com.ibm.social.ee/File.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.social.ee/File.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('File-06d6-405e-9a46-4c3d13916b74', 'File-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@

INSERT INTO HOMEPAGE.WIDGET
		(WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES ('ICEC-6450-44be-9755-3752fd4e58c1', 'Highlights', '', '{webresources}/../../xcc/templates/iWidgetXCCCommunityDefinition.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/../../xcc/templates/iWidgetXCCCommunityDefinition.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
		(WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ICEC-06d6-405e-9a46-4c3d13916b74', 'ICEC-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@

INSERT INTO HOMEPAGE.WIDGET
		(WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES ('SHAREPOINT-4800-9850-4c3a909aa61b', 'SharepointLibrary', '', '{webresourcesSvcRef}/../../spo/SharepointWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresourcesSvcRef}/../../spo/SharepointWidget.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
		(WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('SHAREPOINT-4ae7-a175-1b535acc1ba2', 'SHAREPOINT-4800-9850-4c3a909aa61b', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@

----------------
--- urlWidgets
----------------
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('urlWid-15c7-487c-b87f-cdab3d4be983', 'urlWidget', '', '{webresources}/web/com.ibm.social.urliWidget.web.resources/widget/urlWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.social.urliWidget.web.resources/widget/urlWidget.xml', '', 0, 'intranet_access', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('urlWid-9599-4d3a-8d7c-3ea03134498c', 'urlWid-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('urlSWi-15c7-487c-b87f-cdab3d4be983', 'urlWidget', '', '{webresources}/web/lconn.urlwidget/descriptor/SingleIframeWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.urlwidget/descriptor/SingleIframeWidget.xml', '', 0, 'intranet_access', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('urlSWi-9599-4d3a-8d7c-3ea03134498c', 'urlSWi-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


----------------
--- Testing widgets
----------------
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('stock3-15c7-487c-b87f-cdab3d4be983', 'Stock3', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/iwidgets/simple_stock/stock3.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.lconn.gadget/test/container/iwidgets/simple_stock/stock3.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('stock3-9599-4d3a-8d7c-3ea03134498c', 'stock3-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('ShareboxExample-a1fa-b1e6e787aaa1', 'ShareboxExample', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/ShareboxExample.xml', NULL, 1, 1, 0, NULL, ' ', 0, 0, 0, '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/ShareboxExample.xml', NULL, 1, 0,'custom', 0)@

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ShareboxExample-ab2e-918f58076c0f', 'news', 'ShareboxExample-a1fa-b1e6e787aaa1')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ShareboxExample-8d7c-3ea03134498c', 'ShareboxExample-a1fa-b1e6e787aaa1', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary', '00000000-0000-0000-0000-000000000000')@


----------------
--- INSERT THE DATA INTO THE WIDGET_TAB TABLE
----------------
-- Dogear Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dogear7x4229x87BBx91ac3551c5', 'dogear46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dembkx7x4229x87BBx91ac3551c5', 'dembk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_depbkx7x4229x87BBx91ac3551c5', 'depbk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_derbkx7x4229x87BBx91ac3551c5', 'derbk46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_dewlx7x4229x87BBx91ac3551c5', 'dewl46x0a77x4a43x82aaxb00187218631', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Activities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_activitixa187x491dxa4bfx2e1', 'activitixa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myactxa187x491dxa4bfx2e1261', 'myactxa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubactxa187x491dxa4bfx2e126', 'pubactxa187x491dxa4bfx2e1261d0b6ec', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Communities Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_communitxe7c4x4e08xab54x80e', 'communitxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mycommunxe7c4x4e08xab54x80e', 'mycommunxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pubcommuxe7c4x4e08xab54x80e', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Blogs widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_blogs448xcd34x4565x9469x9c3', 'blogs448xcd34x4565x9469x9c34fcefe48c', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Profiles Original Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_profilesxaac7x4229x87bbx9a1', 'profilesxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_myprofisxaac7x4229x87bbx9a1', 'myprofisxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_colprofsxaac7x4229x87bbx9al', 'colprofsxaac7x4229x87bbx9a1c3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- My Wiky widget                
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_mywikiz1xaac7x4229x87BBx91a', 'mywikiz1xaac7x4229x87BBx91ac3551c591', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Popular Wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_pop-wiki1xaac7x4229x87BBx91', 'pop-wiki1xaac7x4229x87BBx91ac3551c5', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Latest wiki
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_latest-wiki5jz1xaac7x4229x8', 'latest-wiki5jz1xaac7x4229x87BBx91ac', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Files Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_HXMb14Y53n2XAuLeHgrZI3f1CEGhN', 'myFilesPb86locI7vRV4yY1KKawZvE8Qul88', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Files sharded with me widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('WIDGET_FfQvWEJHX1TBvSCW0PS8ayfbPlZ1k', 'sharedFilesV4fv72LD5NAcGv2nbrex0ExEq', '_panel.widgetx4a43x82aaxb00187218631', 'primary')@

-- Sidebar activities widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_activities-sidebar', 'activities-sidebar7x4229x8', '_panel.updatex4a43x82aaxb00187218631', 'primary')@

-- Sidebar recommend widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
		(WIDGET_TAB_ID,WIDGET_ID,TAB_ID,TYPE) 
VALUES 	('UPDATES_recommend-sidebar','recommend7x4f6hd93kd9','_panel.updatex4a43x82aaxb00187218631','primary')@

--Community Event Widget
INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES  ('UPDATES_communityevent-sidebar', 'commuevtxe7c4x4e08xab54x80e7a4eb8933', '_panel.updatex4a43x82aaxb00187218631', 'primary')@


-- CONN MAIL

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('d8361d6b-0021-41de-b562-55ca27a3e954','405a4f26-fa08-4cef-a995-7d90fbe2634f','_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

-- SHARE Microblogging

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('825edb79-8b86-45c3-afbb-4891355755b2','826e0a39-d231-49bd-a1fa-b1e6e787aaa1','_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('5394716f-273c-4a0e-9845-5069275ab029','826e0a39-d231-49bd-a1fa-b1e6e787aaa1','_noui.share_diax11e1b0c40800200c9a66','primary')@

-- SHARE Files

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('ecd31763-d711-4a57-9e14-eb6b3d71daee','8426c915-6836-4a4b-a3f5-2db0ced9909f', '_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('c1f5196b-4f94-49ec-bc38-f7d8dacc0bb4','8426c915-6836-4a4b-a3f5-2db0ced9909f','_noui.share_diax11e1b0c40800200c9a66','primary')@

-- Connections EE

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('985f431a-3c00-4301-a93b-d375181d9814', 'aad20aa1-c0fa-48ef-bd05-8abe630c0012', '_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('8cd18252-ec33-4f8f-97df-b62f3bb49e28','aad20aa1-c0fa-48ef-bd05-8abe630c0012','_noui.embeddedxx11e1b0c40800200c9a66','primary')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('61532c5f-664f-40c0-bd09-abf337b43312','commuevtxe7c4x4e08xab54x80e7a4eb8933','_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

--95519: Pre-register the ActivityStream gadget in initData, fixup, migration for Homepage DB. (95015)
INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('e2070ae0-1ca4-4de6-a550-9d7b3ea5fa84', '7514c563-1a38-4f91-b0c2-881288dd7dcc', '_noui.gadgetpanx11e1b0c40800200c9a66','primary')@

----------------
--- END THE DATA INTO THE WIDGET_TAB TABLE
----------------

---------------------------------------------------------
-- Insert a "fake" user in PERSON table to be used in public stories
---------------------------------------------------------
INSERT INTO HOMEPAGE.PERSON
		(PERSON_ID,DISPLAYNAME,EXID,STATE,MEMBER_TYPE)
VALUES  ('00000000-0000-0000-0000-000000000001','%anyone','00000000-0000-0000-0000-000000000001',0,2)@


INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW VALUES ('00000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000001',1,'00000000-0000-0000-0000-000000000000')@

