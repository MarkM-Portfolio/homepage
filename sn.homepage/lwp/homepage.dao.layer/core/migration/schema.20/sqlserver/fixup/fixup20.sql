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

USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO

-- 1) refactoring NR_READER_IS_TOP_STORY_IDX
DROP INDEX NR_READER_IS_TOP_STORY_IDX ON HOMEPAGE.NR_NEWS_RECORDS;
GO

CREATE INDEX NR_READER_IS_TOP_STORY_IDX 
	ON HOMEPAGE.NR_NEWS_RECORDS (CREATION_DATE DESC, NEWS_RECORDS_ID, READER_ID, IS_TOP_STORY);
GO

-- 2) new: NR_READER_TOPSTORY_SRC_IDX  (this is the most important and it improves several queries)
CREATE INDEX NR_READER_TOPSTORY_SRC_IDX 
	ON HOMEPAGE.NR_NEWS_RECORDS (CREATION_DATE DESC, NEWS_RECORDS_ID, READER_ID, IS_TOP_STORY, SOURCE);
GO

-- 3) refactoring: very important too
DROP INDEX NR_NEWS_RECORDS_SOURCE_IDX ON HOMEPAGE.NR_NEWS_RECORDS;
GO

CREATE INDEX NR_NEWS_RECORDS_SOURCE_IDX 
	ON HOMEPAGE.NR_NEWS_RECORDS (CREATION_DATE DESC, NEWS_RECORDS_ID, READER_ID, IS_CONTAINER, IS_PUBLIC, SOURCE);
GO

-- 4) important to retrieve saved story
DROP INDEX NR_READER_IS_SAVED_IDX ON HOMEPAGE.NR_NEWS_RECORDS;

CREATE INDEX NR_READER_IS_SAVED_IDX 
	ON HOMEPAGE.NR_NEWS_RECORDS(CREATION_DATE DESC, READER_ID, IS_SAVED);
GO


-------------------------------------------------------------------------------
-- Updating the schema version from 19 to 20
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 20
WHERE   DBSCHEMAVER = 19;
GO

COMMIT;