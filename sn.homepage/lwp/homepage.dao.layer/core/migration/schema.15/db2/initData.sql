-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- Script for database population
-- Widget Catalog
-- karim.heredia@ie.ibm.com
--


CONNECT TO HOMEPAGE;
 
-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('_panel.widgetx4a43x82aaxb00187218631' , '%panel.widget' , 2 , 0);

-- Insert the panel tab page update
INSERT INTO HOMEPAGE.HP_TAB 
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('_panel.updatex4a43x82aaxb00187218631' , '%panel.update' , 1 , 0);
 
-- Insert the panel tab page widget
INSERT INTO HOMEPAGE.HP_TAB
			(TAB_ID, DEFAULT_NAME, DEFAULT_N_COLUMNS, IS_NAME_CHANGEABLE)
VALUES 		('_panel.customx4a43x82aaxb00187218631' , '%panel.custom' , 2 , 1); 

-- Beginning Dogear widgets 

-- Dogear Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('dogear46x0a77x4a43x82aaxb00187218631', '%widget.dogear.name', '%widget.dogear.desc', 'web/widgets/dogear/dogear.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7b1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dogear46x0a77x4a43x82aaxb00187218631');

-- Dogear MyBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('dembk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.personal.name', '%widget.dogear.personal.desc', 'web/widgets/dogear/personal/mybookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7c1a34f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dembk46x0a77x4a43x82aaxb00187218631');

-- Dogear PopularBookmarks widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('depbk46x0a77x4a43x82aaxb00187218631', '%widget.dogear.popular.name', '%widget.dogear.popular.desc', 'web/widgets/dogear/popular/popularbookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7w1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'depbk46x0a77x4a43x82aaxb00187218631');

-- Dogear RecentBookmarks widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('derbk46x0a77x4a43x82aaxb00187218631',  '%widget.dogear.recent.name', '%widget.dogear.recent.desc', 'web/widgets/dogear/recent/recentbookmarks.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7t1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'derbk46x0a77x4a43x82aaxb00187218631');

-- Dogear WatchList widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('dewl46x0a77x4a43x82aaxb00187218631', '%widget.dogear.watchlist.name', '%widget.dogear.watchlist.desc', 'web/widgets/dogear/watching/watchlist.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBookmarks16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'DOGEAR',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7m1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dewl46x0a77x4a43x82aaxb00187218631');

-- End Dogear widgets



-- Beginning Activities widgets

-- Activities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('activitixa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.name', '%widget.activities.desc', 'web/widgets/activities/activities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4574xbf4ax10e389b5b4e3', 'activities', 'activitixa187x491dxa4bfx2e1261d0b6ec');

-- Activities MyActivities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('myactxa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.personal.name', '%widget.activities.personal.desc', 'web/widgets/activities/personal/myactivities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4874xbf4ax10e389b5b4e3', 'activities', 'myactxa187x491dxa4bfx2e1261d0b6ec');

-- Activities PublicActivities widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pubactxa187x491dxa4bfx2e1261d0b6ec',  '%widget.activities.public.name', '%widget.activities.public.desc', 'web/widgets/activities/pub/publicactivities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0v9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'pubactxa187x491dxa4bfx2e1261d0b6ec');

-- Sidebar Activities TODO widget - in update page
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('activities-sidebar7x4229x8',  '%widget.activities.sidebar.todo.name', '%widget.activities.sidebar.todo.desc', 'web/widgets/activitiesSideBar/activities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconActivities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'ACTIVITIES', 1,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0z9ccx9ffex4523xbf4ax10e389b5b4e3', 'activities', 'activities-sidebar7x4229x8');

-- End Activities widgets



-- Beginning Communities widgets

-- Communities Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('communitxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.name', '%widget.communities.desc', 'web/widgets/communities/communities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 1,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9317x4195x90c0x696c1b6da4ff', 'communities', 'communitxe7c4x4e08xab54x80e7a4eb8933');

-- Communities MyCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('mycommunxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.personal.name', '%widget.communities.personal.desc', 'web/widgets/communities/personal/mycommunities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de6624x9317x4195x90c0x696c1b6da4ff', 'communities', 'mycommunxe7c4x4e08xab54x80e7a4eb8933');

-- Communities PublicCommunities widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pubcommuxe7c4x4e08xab54x80e7a4eb8933',  '%widget.communities.public.name', '%widget.communities.public.desc', 'web/widgets/communities/pub/publiccommunities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconCommunities16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'COMMUNITIES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9677y4195x90c0x696c1b6da4ff', 'communities', 'pubcommuxe7c4x4e08xab54x80e7a4eb8933');

-- End Communities widgets




-- Beginning Blogs widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('blogs448xcd34x4565x9469x9c34fcefe48c', '%widget.blogs.name', '%widget.blogs.desc', 'web/widgets/blogs/blogs.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconBlogs16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'BLOGS', 1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ac8ce708x3f95x4357xa3c9xc8673baab246', 'blogs', 'blogs448xcd34x4565x9469x9c34fcefe48c');

-- End Blogs widget




-- Beginning Profiles widgets

-- Profiles Original Widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('profilesxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.name', '%widget.profiles.desc', 'web/widgets/profiles/profiles.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4ad0x807ex2aa4373360bd', 'profiles', 'profilesxaac7x4229x87bbx9a1c3551c591');

-- Profiles MyProfile widget - opened by default
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID,  WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('myprofisxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.personal.name', '%widget.profiles.personal.desc', 'web/widgets/profiles/personal/myprofile.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES', 0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7451x4ad0x807ex2aa4373360bd', 'profiles', 'myprofisxaac7x4229x87bbx9a1c3551c591');

-- Profiles ColleagueProfile widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('colprofsxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.colleagues.name', '%widget.profiles.colleagues.desc', 'web/widgets/profiles/colleagues/colleagueprofile.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconProfiles16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'PROFILES',  0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7891x4aw0x807ex2aa4373360bd', 'profiles', 'colprofsxaac7x4229x87bbx9a1c3551c591');

-- End Profiles widgets



-- Beginning Wiki Widget

-- My Wiky widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES)
VALUES      ('mywikiz1xaac7x4229x87BBx91ac3551c591', '%widget.mywiki.name', '%widget.mywiki.desc', 'web/widgets/wiki/mywiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png', 1, 1, 1, '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4awk5020ex2aa4373360bd', 'wikis', 'mywikiz1xaac7x4229x87BBx91ac3551c591');

-- Popular Wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED,  WIDGET_MULTIPLE_INSTANCES)
VALUES      ('pop-wiki1xaac7x4229x87BBx91ac3551c5', '%widget.popwiki.name', '%widget.popwiki.desc', 'web/widgets/wiki/popularwiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  0,  1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b8997620ojaw08d20ex2o4837336r8j', 'wikis', 'pop-wiki1xaac7x4229x87BBx91ac3551c5');

-- Latest wiki
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('latest-wiki5jz1xaac7x4229x87BBx91ac', '%widget.latestwiki.name', '%widget.latestwiki.desc', 'web/widgets/wiki/latestwiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconWikis16.png', 1, 1, 1,  '${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'WIKI',  1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('45thpb899762998dw08ee96x20of33o0Ur8j', 'wikis', 'latest-wiki5jz1xaac7x4229x87BBx91ac');

-- My Files widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('myFilesPb86locI7vRV4yY1KKawZvE8Qul88', '%widget.files.name', '%widget.files.desc', 'web/widgets/files/files.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  1, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('U9PdeKPWtviSGv2QP57ZRady4RHYBPyXCL9a','files','myFilesPb86locI7vRV4yY1KKawZvE8Qul88');

-- Files shared with me widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES)
VALUES      ('sharedFilesV4fv72LD5NAcGv2nbrex0ExEq', '%widget.sharedfiles.name', '%widget.sharedfiles.desc', 'web/widgets/files/sharedFiles.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconFiles16.png', 1, 1, 1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg', 'FILES',  0, 1);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('JlNdbgUL4kAwMEFAEAwlYXQ3YBOWnBry4x6Y','files','sharedFilesV4fv72LD5NAcGv2nbrex0ExEq');

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


----------------
--- END THE DATA INTO THE WIDGET_TAB TABLE
----------------






------------ 
--- START INSERT TEMPLATES
------------

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actor-Bei35oPldKwZTaR7aAiPFw4L08CyRW','actor', 'actorExtID', 'person', 1); 

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actorProfile-CAsyXgPhQd7N3wSMw7C0IUe','actorProfiles', 'actorExtID', 'profilePhoto', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('target-Ah3vEOPQZDEpgpwcQ2JNaHFfMnMcG','subject', 'targetSubjectExtIDs', 'profilePhoto', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEnt-anXIr0xP82OSZnuoYbAZa2M5AsRFr', 'activityEntry', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actEntComm-WEJHX1TBvSCW0PS8ayfbPlZ1k','activityEntryWithComment', 'activity.entry.name;activity.entry.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-WHHEhu6HTkRWtCQPylcUdSENF4mU', 'activityContainerName', 'activity.name;activity.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actCont-Mg2xRFBLmRqg3Zj7Etrdttiyc6OH','activityContainerNameACL', 'activity.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('actToDo-psOHtvbY4lOJv8jOXJH5YgLoGYP7','toDoEntry', 'activity.todo.name;activity.todo.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blog-Itf9INx14G6bbCWRYNRpLhSawJXF8Qs','blogContainerName', 'blog.name;blog.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogNew-qsgRrfp88AKWys4wcm4gIuZm3T2p','newBlogContainer', 'blog.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogEntry-ubpjmKeG6Vi8XpQXYKVx3mtCqm','blogEntry', 'blog.entry.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogComment-92JYbZEOrk0CBLEJHYwkrqPA','blogEntryWithComment', 'blog.entry.name;blog.comment.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-Qf0I5rSEaImjaCcds3HYi4SeCGYCDCN','newCommunityContainer', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-new-IduwKhHDNCLdmJFi0G7lwrE86IJ','communityContainerName', 'community.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-topic6V808ijHf9TRgUSUqVVZoSOGg5','topicEntryName', 'community.topic.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-book-VSWMFF7uZVaE11XRe4Q1ElJIc6','communityBookmarkEntryName', 'community.bookmark.name;community.bookmark.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('comm-feed-Ja6XFBT7kyfEJv07Unitc0MsJT','feedEntryName', 'community.feed.name;community.feed.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('dogear-book-DXAWZl1FIGMuMr4Ea4Lejbum','dogearBookmarkEntry', 'dogear.bookmark.title;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('tags-a7YmLTHTY2FdHKPsHU0wbAZCkgYrkk1','tag', 'tags', 'plain', 3);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profileEntry-Vrh9M5YNmxNogF2oERZWyRE','profileLinkEntry', 'profiles.link.title;profiles.link.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('profile-status-HNX0o3AF32cFuPWhtYMjl','profileStatusEntry', 'status', 'plain', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-9qgQLuYLDdh66pf82um3c6q1lQ0gZl5','wikiContainerName','wiki.name;wiki.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-page-P75oD6oAdgoWCRNt4YYh7hZfuM','wikiEntryPage', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('wiki-comment-Ye2xdX8pbYM996pc7je30LI','wikiEntryPageCommented', 'wiki.page.name;wiki.page.url', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('file-bbqZpR6oxvE2sYAXxasKS0EtS1mDg4h','fileEntry', 'file.name;htmlURL', 'link', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('collection-7jEWoKkWx8ucNTo6Z7AndhRFh','collectionContainer', 'files.collection.name;htmlURL', 'link', 1);

------------
--- END INSERT TEMPLATES
------------

--------------
--- START INIT EMAIL DIGEST JOB
--------------

INSERT INTO HOMEPAGE.EMD_JOBS 
        (JOB_ID, JOB_FREQUENCY, JOB_FREQUENCY_SCALE, N_PERSONS_TO_UPDATE, AVG_EXEC_TIME_SECONDS)
VALUES  ('1_job_freq_l_day_fddsfdsx4a43x82aaxb', 1, 'D', 0, 0);

INSERT INTO HOMEPAGE.EMD_JOBS 
        (JOB_ID, JOB_FREQUENCY, JOB_FREQUENCY_SCALE, N_PERSONS_TO_UPDATE, AVG_EXEC_TIME_SECONDS)
VALUES  ('2_job_freq_7_day_fddsfdsx4a43x82aaxb', 7, 'D', 0, 0);

--------------
--- END INIT EMAIL DIGEST JOB
--------------

COMMIT;
	
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
CONNECT RESET;
TERMINATE; 
