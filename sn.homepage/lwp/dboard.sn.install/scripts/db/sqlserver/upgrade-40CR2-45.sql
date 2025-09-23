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
USE HOMEPAGE
GO

BEGIN TRANSACTION
GO

---------------------------------------------------------------------
-- [START] 87961: [migration] DB2: 4.0 - 4.5 migration failed with HOMPAGE db
---------------------------------------------------------------------

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_SOURCE_TYPE SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_AGGREGATED_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_RESPONSES_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_PROFILES_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_COMMUNITIES_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_ACTIVITIES_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_BLOGS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_BOOKMARKS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_FILES_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_FORUMS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_WIKIS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_TAGS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_STATUS_UPDATE_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_EXTERNAL_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_ACTIONABLE_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_DISCOVERY_VIEW SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_PROFILES_VIEW SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_NOTIFICATION_SENT_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_SAVED_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO

UPDATE HOMEPAGE.NR_TOPICS_READERS SET ORGANIZATION_ID = NULL WHERE ORGANIZATION_ID IS NOT NULL;
GO
---------------------------------------------------------------------
-- [END] 87961: [migration] DB2: 4.0 - 4.5 migration failed with HOMPAGE db
---------------------------------------------------------------------

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++ HOMEPAGE +++++++++++++++++++++++++++++++++++++
-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIXUP 204 FOR HP
------------------------------------------------

{include.hp-fixup204.sql}


------------------------------------------------
-- INCLUDE FIXUP 205 FOR HP
------------------------------------------------

{include.hp-fixup205.sql}

------------------------------------------------
-- INCLUDE FIXUP 207 FOR HP
------------------------------------------------

{include.hp-fixup207.sql}


------------------------------------------------
-- INCLUDE FIXUP 208 FOR HP
------------------------------------------------

{include.hp-fixup208.sql}


-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++ NEWS +++++++++++++++++++++++++++++++++++++++++
-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIXUP 201 FOR NEWS
------------------------------------------------

{include.news-fixup201.sql}

------------------------------------------------
-- INCLUDE FIXUP 202 FOR NEWS
------------------------------------------------

-- Removed include news-fixup202.sql. CR2 already got this

------------------------------------------------
-- INCLUDE FIXUP 203 FOR NEWS
------------------------------------------------

-- Removed include news-fixup203.sql. CR2 already got this

------------------------------------------------
-- INCLUDE FIXUP 204 FOR NEWS
------------------------------------------------

{include.news-fixup204.sql}


------------------------------------------------
-- INCLUDE FIXUP 205 FOR NEWS
------------------------------------------------

{include.news-fixup205.sql}

------------------------------------------------
-- INCLUDE FIXUP 207 FOR NEWS
------------------------------------------------

{include.news-fixup207.sql}

------------------------------------------------
-- INCLUDE FIXUP 208 FOR NEWS
------------------------------------------------

{include.news-fixup208.sql}

------------------------------------------------
-- INCLUDE FIXUP 209 FOR NEWS
------------------------------------------------

{include.news-fixup209.sql}

------------------------------------------------
-- INCLUDE FIXUP 210 FOR NEWS
------------------------------------------------

{include.news-fixup210.sql}

-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++ SEARCH +++++++++++++++++++++++++++++++++++++++
-- ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--Empty the SR_INDEX_DOCS table.
TRUNCATE TABLE HOMEPAGE.SR_INDEX_DOCS
GO  

--Empty the SR_FILESCONTENT table.
TRUNCATE TABLE HOMEPAGE.SR_FILESCONTENT
GO

--Empty the SR_TIMER_STATS table.
DELETE FROM HOMEPAGE.SR_TIMER_STATS
GO

--Empty the SR_NUMBER_STATS table.
DELETE FROM  HOMEPAGE.SR_NUMBER_STATS
GO

--Empty the SR_STRING_STATS table.
DELETE FROM  HOMEPAGE.SR_STRING_STATS
GO

--Empty the SR_STATS table.
DELETE FROM HOMEPAGE.SR_STATS
GO

--Empty the SR_RESUME_TOKENS table.
DELETE FROM HOMEPAGE.SR_RESUME_TOKENS
GO

--Empty the SR_INDEX_MANAGEMENT table.
DELETE FROM HOMEPAGE.SR_INDEX_MANAGEMENT
GO

--Empty the scheduler tables.

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMGR
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSLMPR
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK
GO

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTREG
GO

----------------------------------------
-- SR_ECM_DOCUMENT_TYPE_LABELS
----------------------------------------


--START 74517: Create script that creates DB table that hold document type labels

CREATE TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS(
        LABEL_ID NVARCHAR(36) NOT NULL,
        LABEL_NAME NVARCHAR(256) NOT NULL,
        DOCUMENT_TYPES  NVARCHAR(MAX) NOT NULL,
        UPDATE_TIME  DATETIME NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
    ADD CONSTRAINT PK_LABEL_ID PRIMARY KEY (LABEL_ID)
GO

ALTER TABLE HOMEPAGE.SR_ECM_DOCUMENT_TYPE_LABELS
    ADD CONSTRAINT UNIQUE_LABEL_NAME UNIQUE (LABEL_NAME)
GO

--END 74517: Create script that creates DB table that hold document type labels

--START Task 78083 Prepare DB table for storing post filtering service.

----------------------------------------
-- SR_POST_FILTERING_SERVICE
----------------------------------------

CREATE TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE(
    PFS_ID NVARCHAR(36) NOT NULL,
    SERVICE_NAME NVARCHAR(36) NOT NULL,
    URL NVARCHAR(2048) NOT NULL
)
ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
   ADD CONSTRAINT PFS_ID PRIMARY KEY (PFS_ID)
GO


ALTER TABLE HOMEPAGE.SR_POST_FILTERING_SERVICE
    ADD CONSTRAINT UNIQUE_SERVICE_NAME UNIQUE (SERVICE_NAME);
GO

--END Task 78083 Prepare DB table for storing post filtering service.


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------

------------------------------------------------
-- INCLUDE FIXUP 201 FOR SEARCH
------------------------------------------------

{include.search-fixup201.sql}


------------------------------------------------
-- INCLUDE FIXUP 207 FOR SEARCH
------------------------------------------------

{include.search-fixup207.sql}


------------------------------------------------
-- INCLUDE FIXUP 208 FOR SEARCH
------------------------------------------------

{include.search-fixup208.sql}

--Empty the SR_INDEX_DOCS table.
TRUNCATE TABLE  HOMEPAGE.SR_INDEX_DOCS
GO

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 210
------------------------------------------------------------------------------------------------
-- CR2 was at 132 schema ver.
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 210 , RELEASEVER = '4.5.0.0'
WHERE   DBSCHEMAVER = 132;

GO


COMMIT;
