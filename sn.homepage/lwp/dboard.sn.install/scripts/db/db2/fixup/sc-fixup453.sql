-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
CONNECT TO HOMEPAGE;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 453
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

{include.news-fixup453.sql}


INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM, WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED, WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON, IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('7514c563-1a38-4f91-b0c2-881288dd7dcc','%widget.activitystream.name','%widget.activitystream.desc','${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,1,0,NULL,'CONNECTIONSAS',0,0,0,'${COMMON_CONTEXT_ROOT}/web/com.ibm.social.as/gadget/ActivityStream.xml',NULL,1,7,'intranet_access', -1);
COMMIT;

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('e2070ae0-1ca4-4de6-a550-9d7b3ea5fa84', '7514c563-1a38-4f91-b0c2-881288dd7dcc', '_noui.gadgetpanx11e1b0c40800200c9a66','primary');
COMMIT;


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 453
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 453, RELEASEVER = '5.0.0.0 SC' 
WHERE   DBSCHEMAVER = 452; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 453
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;