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

connect to HOMEPAGE@

------------------------------------------------

{include.news-postfixup710.7.sql}

INSERT INTO HOMEPAGE.WIDGET
		(WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY,  WIDGET_IS_DEFAULT_OPENED, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, PROXY_POLICY, SHARE_ORDER, ORGANIZATION_ID, WIDGET_MARKED_CACHABLE, WIDGET_POLICY_FLAGS, WIDGET_EXT_PROPERTIES, SERVER_ACCESS, ADDITIONAL_FEATURES)
VALUES ('ICEC-6450-44be-9755-3752fd4e58c1', 'Highlights', '', '{webresources}/../../xcc/templates/iWidgetXCCCommunityDefinition.xml', '', 1, 1, 0,  '', ' ',  0, 1, '{webresources}/../../xcc/templates/iWidgetXCCCommunityDefinition.xml', '', 0, 'custom', -1, '00000000-0000-0000-0000-000000000000', 1, 0, '', '', '')@

INSERT INTO HOMEPAGE.HP_WIDGET_TAB
		(WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE, ORGANIZATION_ID)
VALUES  ('ICEC-06d6-405e-9a46-4c3d13916b74', 'ICEC-6450-44be-9755-3752fd4e58c1', '_noui.iwidgetpanx11e1b0c40800200c9a6', 'primary', '00000000-0000-0000-0000-000000000000')@


UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 710, POSTSCHEMAVER = '710.7', RELEASEVER = '6.0.0.0CR3'
WHERE   DBSCHEMAVER = 710@

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT@
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@