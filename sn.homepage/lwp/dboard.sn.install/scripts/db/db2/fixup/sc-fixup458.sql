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
--					start	FIXUP 458
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

UPDATE HOMEPAGE.WIDGET SET WIDGET_ENABLED = 0 WHERE WIDGET_ID = 'activities-sidebar7x4229x8';
COMMIT;

UPDATE HOMEPAGE.WIDGET SET WIDGET_ENABLED = 0 WHERE WIDGET_ID = 'commuevtxe7c4x4e08xab54x80e7a4eb8933';
COMMIT;

-- 97243: Truncate HP_WIDGET_INST table in fixup458 
TRUNCATE HOMEPAGE.HP_WIDGET_INST IMMEDIATE;
COMMIT;

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 458
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 458, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 457; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 457
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