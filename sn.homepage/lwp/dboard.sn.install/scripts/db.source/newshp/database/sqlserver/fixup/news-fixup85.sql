-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2007, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-----------------------------------------------------------------------------------------------
-- 1) ADD COLUMN TO THE EMD_EMAIL_PREFS TABLE
-----------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.EMD_EMAIL_PREFS ADD REPLYTO_ENABLED NUMERIC(5,0) NOT NULL DEFAULT 1;

GO

-----------------------------------------------------------------------------------------------
-- 2) ADD COLUMNS TO THE EMD_TRANCHE TABLE
-----------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.EMD_TRANCHE ADD 
	IS_LOCKED_DAILY NUMERIC(5,0) NOT NULL DEFAULT 0,
	IS_LOCKED_WEEKLY NUMERIC(5,0) NOT NULL DEFAULT 0,
	LAST_STARTED_DAILY DATETIME,
	LAST_STARTED_WEEKLY DATETIME,
	LAST_EXEC_TIME_DAILY_MIN  NUMERIC(10 ,0),
	LAST_EXEC_TIME_WEEKLY_MIN  NUMERIC(10 ,0),
	LAST_RUNNER_DAILY nvarchar(256),
	LAST_RUNNER_WEEKLY nvarchar(256);

GO

---------------------------------------------------------------------------------
-- 3) MODIFY QUARANTINE TABLE
---------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.DELETED_STORIES_QUEUE ADD STATUS NUMERIC(5,0) NOT NULL DEFAULT 0;

GO

--------------------------------------------------------------------------------
-- 4) RENAMING TABLES
--------------------------------------------------------------------------------
DROP VIEW HOMEPAGE.NR_FOLLOWED_STORIES;

GO

ALTER TABLE 	HOMEPAGE.NR_RESPONSES_STORIES DROP CONSTRAINT CK_CAT1_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_RESPONSES_STORIES', 'NR_RESPONSES_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_RESPONSES_READERS ADD CONSTRAINT CK_CAT1_TYPE CHECK (CATEGORY_TYPE = 1);

GO

ALTER TABLE 	HOMEPAGE.NR_PROFILES_STORIES DROP CONSTRAINT CK_CAT2_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_PROFILES_STORIES', 'NR_PROFILES_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_PROFILES_READERS ADD CONSTRAINT CK_CAT2_TYPE CHECK (CATEGORY_TYPE = 2);

GO

ALTER TABLE 	HOMEPAGE.NR_COMMUNITIES_STORIES DROP CONSTRAINT CK_CAT3_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_COMMUNITIES_STORIES', 'NR_COMMUNITIES_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_COMMUNITIES_READERS ADD CONSTRAINT CK_CAT3_TYPE CHECK (CATEGORY_TYPE = 3);

GO

ALTER TABLE 	HOMEPAGE.NR_ACTIVITIES_STORIES DROP CONSTRAINT CK_CAT4_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_ACTIVITIES_STORIES', 'NR_ACTIVITIES_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_ACTIVITIES_READERS ADD CONSTRAINT CK_CAT4_TYPE CHECK (CATEGORY_TYPE = 4);

GO

ALTER TABLE 	HOMEPAGE.NR_BLOGS_STORIES DROP CONSTRAINT CK_CAT5_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_BLOGS_STORIES', 'NR_BLOGS_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_BLOGS_READERS ADD CONSTRAINT CK_CAT5_TYPE CHECK (CATEGORY_TYPE = 5);

GO

ALTER TABLE 	HOMEPAGE.NR_BOOKMARKS_STORIES DROP CONSTRAINT CK_CAT6_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_BOOKMARKS_STORIES', 'NR_BOOKMARKS_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_BOOKMARKS_READERS ADD CONSTRAINT CK_CAT6_TYPE CHECK (CATEGORY_TYPE = 6);

GO

ALTER TABLE 	HOMEPAGE.NR_FILES_STORIES DROP CONSTRAINT CK_CAT7_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_FILES_STORIES', 'NR_FILES_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_FILES_READERS ADD CONSTRAINT CK_CAT7_TYPE CHECK (CATEGORY_TYPE = 7);

GO

ALTER TABLE 	HOMEPAGE.NR_FORUMS_STORIES DROP CONSTRAINT CK_CAT8_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_FORUMS_STORIES', 'NR_FORUMS_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_FORUMS_READERS ADD CONSTRAINT CK_CAT8_TYPE CHECK (CATEGORY_TYPE = 8);

GO

ALTER TABLE 	HOMEPAGE.NR_WIKIS_STORIES DROP CONSTRAINT CK_CAT9_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_WIKIS_STORIES', 'NR_WIKIS_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_WIKIS_READERS ADD CONSTRAINT CK_CAT9_TYPE CHECK (CATEGORY_TYPE = 9);

GO

ALTER TABLE 	HOMEPAGE.NR_TAGS_STORIES DROP CONSTRAINT CK_CAT10_TYPE;
GO
EXEC sp_rename 'HOMEPAGE.NR_TAGS_STORIES', 'NR_TAGS_READERS';
GO
ALTER TABLE 	HOMEPAGE.NR_TAGS_READERS ADD CONSTRAINT CK_CAT10_TYPE CHECK (CATEGORY_TYPE = 10);

GO


--------------------------------------------------------------------------------------------------------------
-- CREATE THE VIEW FOR ALL THE CATEGORIES READERS TABLE
--------------------------------------------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.NR_CATEGORIES_READERS AS (
    SELECT * FROM HOMEPAGE.NR_RESPONSES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_PROFILES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_COMMUNITIES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_ACTIVITIES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_BLOGS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_BOOKMARKS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_FILES_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_FORUMS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_WIKIS_READERS
        UNION ALL
    SELECT * FROM HOMEPAGE.NR_TAGS_READERS
);

GO




