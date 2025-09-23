-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2017, 2017                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              

-- Static widgets moved from Profiles to Common
UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/common-tags.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/common-tags.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/common-tags.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/follow.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/follow.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/follow.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends-fullpage.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends-fullpage.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/friends-fullpage.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/friends.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/linkroll.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/linkroll.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/linkroll.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multiWidget.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multiWidget.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/multiWidget.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multifeedreader.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multifeedreader.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/multifeedreader.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/profile-details.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/profile-details.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/profile-details.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/report-chain.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/report-chain.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/report-chain.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/tags.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/tags.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/tags.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/wall.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/wall.xml'
WHERE   WIDGET_URL = '{profiles}/widget-catalog/wall.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_TITLE = 'FeaturedSurveys'
WHERE   WIDGET_URL = '{webresources}/web/com.ibm.form.integrations.formiwidget/SingleSurvey.xml';


----------------
--- urlWidgets
----------------
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('urlWid-15c7-487c-b87f-cdab3d4be983', 'urlWidget', '', '{webresources}/web/com.ibm.social.urliWidget.web.resources/widget/urlWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.social.urliWidget.web.resources/widget/urlWidget.xml', '', 0, 'intranet_access', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('urlWid-9599-4d3a-8d7c-3ea03134498c', 'urlWid-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000');


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('urlSWi-15c7-487c-b87f-cdab3d4be983', 'urlWidget', '', '{webresources}/web/lconn.urlwidget/descriptor/SingleIframeWidget.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/lconn.urlwidget/descriptor/SingleIframeWidget.xml', '', 0, 'intranet_access', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('urlSWi-9599-4d3a-8d7c-3ea03134498c', 'urlSWi-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000');


----------------
--- Testing widgets
----------------
INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES      ('stock3-15c7-487c-b87f-cdab3d4be983', 'Stock3', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/iwidgets/simple_stock/stock3.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/web/com.ibm.lconn.gadget/test/container/iwidgets/simple_stock/stock3.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('stock3-9599-4d3a-8d7c-3ea03134498c', 'stock3-15c7-487c-b87f-cdab3d4be983', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000');

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('ShareboxExample-a1fa-b1e6e787aaa1', 'ShareboxExample', '', '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/ShareboxExample.xml', NULL, 1, 1, 0, NULL, ' ', 0, 0, 0, '{webresources}/web/com.ibm.lconn.gadget/test/container/gadgets/ShareboxExample.xml', NULL, 1, 0,'custom', 0);

INSERT INTO HOMEPAGE.PREREQ
            (PREREQ_ID, APP_ID, WIDGET_ID)
VALUES      ('ShareboxExample-ab2e-918f58076c0f', 'news', 'ShareboxExample-a1fa-b1e6e787aaa1');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
        (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ShareboxExample-8d7c-3ea03134498c', 'ShareboxExample-a1fa-b1e6e787aaa1', '_noui.gadgetpanx11e1b0c40800200c9a66', 'primary', '00000000-0000-0000-0000-000000000000');


GO
