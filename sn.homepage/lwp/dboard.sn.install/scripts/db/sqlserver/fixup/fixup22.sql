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
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-- SPR# MPML7UVM9X
-- Missing index on SR_FILESCONTENT causes full table scan
CREATE INDEX SR_FILESCONTENT_IS_CURRENT_IDX 
	ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT);
GO

-- SPR #SPII7UCHBD
-- Missing index on NR_NEWS_RECORDS during the watchlist result create some performance issue
CREATE INDEX NR_NEWS_WATCHLIST_IDX
	ON HOMEPAGE.NR_NEWS_RECORDS(CONTAINER_ID,SOURCE,IS_CONTAINER,READER_ID);
GO

-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 2.5.1.0
-- NOTE: In the prev. createDb.sql ( which is into 2.5 gold the last version was wrong setted to 20 from the createDb.sql and 21 from upgrade25.sql and fixup )
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 22 , RELEASEVER = '2.5.1.0'
WHERE   DBSCHEMAVER = 20 OR DBSCHEMAVER = 21;
GO

COMMIT;