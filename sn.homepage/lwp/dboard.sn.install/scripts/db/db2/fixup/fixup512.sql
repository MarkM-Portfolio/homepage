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


-- Blank script for SC work
-- 140279: PC1, Homepage DB, SmartCloud, Additional index compression could be used for HP indexes.
-- 134812: [fixup512s] Add index compression


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 512
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 512, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 511@

------------------------------------------------------------------------------------------------




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