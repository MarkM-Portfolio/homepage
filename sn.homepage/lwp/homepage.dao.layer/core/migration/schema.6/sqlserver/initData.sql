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

--------------------------------------
-- CONNECT TO HOMEPAGE
--------------------------------------
USE HOMEPAGE
GO

-- Beginning Dogear widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('dogear46x0a77x4a43x82aaxb00187218631', '%widget.dogear.name', '%widget.dogear.desc', 'web/widgets/dogear/dogear.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/dogear_icon.gif', '1', '1', '1');

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('7b1a60f1xc9cax4cc4x8b0bx51af2ddef2cd', 'dogear', 'dogear46x0a77x4a43x82aaxb00187218631');

-- End Dogear widget

-- Beginning Activities widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('activitixa187x491dxa4bfx2e1261d0b6ec', '%widget.activities.name', '%widget.activities.desc', 'web/widgets/activities/activities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/activities_icon.gif', '1', '1', '1');

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('fab0b9ccx9ffex4574xbf4ax10e389b5b4e3', 'activities', 'activitixa187x491dxa4bfx2e1261d0b6ec');

-- End Activities widget

-- Beginning Communities widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('communitxe7c4x4e08xab54x80e7a4eb8933', '%widget.communities.name', '%widget.communities.desc', 'web/widgets/communities/communities.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/communities_icon.gif', '1', '1', '1');

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('73de8824x9317x4195x90c0x696c1b6da4ff', 'communities', 'communitxe7c4x4e08xab54x80e7a4eb8933');

-- End Communities widget

-- Beginning Blogs widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('blogs448xcd34x4565x9469x9c34fcefe48c', '%widget.blogs.name', '%widget.blogs.desc', 'web/widgets/blogs/blogs.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/blogs_icon.gif', '1', '1', '1');

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ac8ce708x3f95x4357xa3c9xc8673baab246', 'blogs', 'blogs448xcd34x4565x9469x9c34fcefe48c');

-- End Blogs widget

-- Beginning Profiles widget

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('profilesxaac7x4229x87bbx9a1c3551c591', '%widget.profiles.name', '%widget.profiles.desc', 'web/widgets/profiles/profiles.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/profiles_icon.gif', '1', '1', '1');

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('4a848b83x7621x4ad0x807ex2aa4373360bd', 'profiles', 'profilesxaac7x4229x87bbx9a1c3551c591');

-- End Profiles widget
--Beginning Wiki widget
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC)
VALUES      ('wiki5jz1xaac7x4229x87BBx91ac3551c591', '%widget.wiki.name', '%widget.wiki.desc', 'web/widgets/wiki/wiki.xml', '${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/wiki_icon.gif', '1', '1', '1');
              
INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('db0633734aa2xe708x0da4x1267x38b488a4', 'wiki', 'wiki5jz1xaac7x4229x87BBx91ac3551c591');

-- End wiki widget

--------------------------------------
-- DISCONNECT
--------------------------------------
