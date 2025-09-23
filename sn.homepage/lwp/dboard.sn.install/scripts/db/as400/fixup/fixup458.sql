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
SET CURRENT SCHEMA HOMEPAGE;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 458
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

-- empty file: we use it as SC will need a fixup

-- 101075: Add reorg table command to   HOMEPAGE.NR_AS_CRAWLER_STATUS and HOMEPAGE.NR_AS_COLLECTION_CONFIG
--reorg table HOMEPAGE.NR_AS_CRAWLER_STATUS;
--commit;

--reorg table HOMEPAGE.NR_AS_COLLECTION_CONFIG;
--commit;

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 458
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 458, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 457; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 458
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

