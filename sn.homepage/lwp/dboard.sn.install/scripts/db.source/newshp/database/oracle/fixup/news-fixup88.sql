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

-----------------------------------------------
-- FIX CONSTRAINT ON SOURCE_TYPE FOR NR_ENTRIES_EXTERNAL
-----------------------------------------------
ALTER TABLE HOMEPAGE.NR_ENTRIES_EXTERNAL DROP CONSTRAINT CK_ENT_SRC10_TYPE;

ALTER TABLE HOMEPAGE.NR_ENTRIES_EXTERNAL ADD CONSTRAINT CK_ENT_SRC100_TYPE
    													CHECK
    													(SOURCE_TYPE >= 100);
COMMIT;

-----------------------------------------------
-- ADD ROLLUP_ENTRY_ID FOREIGN KEY TO READER TABLES
-----------------------------------------------
ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_RESPONSES_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_PROFILES_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_ACTIVITIES_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_BLOGS_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_BOOKMARKS_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_FILES_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_FORUMS_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_WIKIS_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_TAGS_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_STATUS_UPDATE_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_EXTERNAL_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;

ALTER TABLE HOMEPAGE.NR_ACTIONABLE_READERS ADD ROLLUP_ENTRY_ID VARCHAR2(36);
COMMIT;
