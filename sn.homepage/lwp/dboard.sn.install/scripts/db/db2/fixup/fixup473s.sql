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
CONNECT TO HOMEPAGE@

-- 115634: [sc-fixup470] Homepage DB inconsistecy between smartcloud and on prem

DROP TABLE HOMEPAGE.LOTUSCONNECTIONS1LMGR@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS1LMPR@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS1TASK@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS1TREG@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS2LMGR@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS2LMPR@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS2TASK@
DROP TABLE HOMEPAGE.LOTUSCONNECTIONS2TREG@

COMMIT@

{include.news-fixup473.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 473
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 473, RELEASEVER = '5.0.0.0' 
WHERE   DBSCHEMAVER = 472@

------------------------------------------------------------------------------------------------

COMMIT@

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 470
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@