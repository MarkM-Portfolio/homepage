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
--					start	FIXUP 462
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

--102272: [fixup462] Renable event gadget in SC
UPDATE HOMEPAGE.WIDGET SET WIDGET_ENABLED = 1 WHERE WIDGET_ID = 'commuevtxe7c4x4e08xab54x80e7a4eb8933';
COMMIT;

-- 102921: Enable recommendation widget
UPDATE HOMEPAGE.WIDGET SET WIDGET_ENABLED = 1 WHERE WIDGET_ID = 'recommend7x4f6hd93kd9';
COMMIT;


TRUNCATE HOMEPAGE.HP_WIDGET_INST IMMEDIATE;
COMMIT;

{include.hp-fixup462.sql}

{include.news-fixup462.sql}

{include.search-fixup462.sql}


--104763: [S22 candidate] OSC 124755: Replace LOAD with IMPORT in fixup462

-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-- [start] 101375: [fixup462] Inconsistent location of some READER tables in NEWS32TABSPACE - Fixing jusr NR_SAVED_READERS
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP CHECK CK_CAT_SAVED_TYPE;
COMMIT;

ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP PRIMARY KEY;
COMMIT;
    
ALTER TABLE HOMEPAGE.NR_SAVED_READERS  DROP CONSTRAINT FK_SAV_READERS_STR;
	
ALTER TABLE HOMEPAGE.NR_SAVED_READERS DROP CONSTRAINT FK_SAVED_READERS_ORG_ID;
    
--  [start indexes] NR_SAVED_READERS
DROP  INDEX HOMEPAGE.SAVED_READERS_STR_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_ITM_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_CD_IX;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_SRC_IX; 
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_RLL_IX; 
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_STR_RDR;
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_DEL_SERV_IX; 
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_ROLLUP_IX; 
COMMIT;

DROP  INDEX HOMEPAGE.SAVED_READERS_RIR_IX;

DROP  INDEX HOMEPAGE.SAVED_READERS_RLL_BRD_VIS;

-- RENAME      
RENAME TABLE HOMEPAGE.NR_SAVED_READERS TO SAVED_TO_DELETE;
COMMIT;

CREATE TABLE HOMEPAGE.NR_SAVED_READERS (
	CATEGORY_READER_ID VARCHAR(36)  DEFAULT ' ' NOT NULL,
	READER_ID VARCHAR(39) NOT NULL,
	CATEGORY_TYPE SMALLINT NOT NULL,
	SOURCE VARCHAR(36) NOT NULL,
	CONTAINER_ID VARCHAR(256),
	ITEM_ID VARCHAR(256),
	ROLLUP_ENTRY_ID VARCHAR(256),
	RESOURCE_TYPE SMALLINT NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	STORY_ID VARCHAR(36) NOT NULL,
	SOURCE_TYPE SMALLINT,
	USE_IN_ROLLUP SMALLINT,
	IS_NETWORK	SMALLINT,
	IS_FOLLOWER	SMALLINT,
	EVENT_TIME TIMESTAMP,
	IS_STORY_COMM SMALLINT,
	IS_BROADCAST SMALLINT,
	ORGANIZATION_ID VARCHAR(36) DEFAULT '00000000-0000-0000-0000-000000000000',
	OPERATION_ID VARCHAR(512),
	ACTOR_UUID VARCHAR(256),
	IS_VISIBLE SMALLINT DEFAULT 1 NOT NULL,
	ROLLUP_AUTHOR_ID VARCHAR (256),
	CONSTRAINT   	CK_CAT_SAVED_TYPE
    				CHECK
    				(CATEGORY_TYPE = 13)	
)
IN NEWS4TABSPACE;

SELECT 'EXPORT_START' AS STEP, CURRENT_TIMESTAMP NOW FROM HOMEPAGE.HOMEPAGE_SCHEMA FETCH FIRST 1 ROWS ONLY;


        EXPORT TO data.tmp.ixf OF IXF METHOD N
                (
                CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME,
                IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, OPERATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID
                )
        SELECT
                CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME,
                IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, OPERATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID
        FROM HOMEPAGE.SAVED_TO_DELETE WITH UR;
        COMMIT;

SELECT 'IMPORT_START' AS STEP, CURRENT_TIMESTAMP NOW FROM HOMEPAGE.HOMEPAGE_SCHEMA FETCH FIRST 1 ROWS ONLY;

        IMPORT FROM data.tmp.ixf OF IXF METHOD N
                (
                CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME,
                IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, OPERATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID
                )
        ALLOW NO ACCESS COMMITCOUNT 2000
        INSERT INTO HOMEPAGE.NR_SAVED_READERS
                (
                CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME,
                IS_STORY_COMM, IS_BROADCAST, ORGANIZATION_ID, OPERATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID
                );
        COMMIT;


SELECT 'COMPLETED' AS STEP, CURRENT_TIMESTAMP NOW FROM HOMEPAGE.HOMEPAGE_SCHEMA FETCH FIRST 1 ROWS ONLY;

--105085: Removing SET INTEGRITY FOR HOMEPAGE.NR_SAVED_READERS IMMEDIATE CHECKED;
--SET INTEGRITY FOR HOMEPAGE.NR_SAVED_READERS IMMEDIATE CHECKED;

DROP TABLE HOMEPAGE.SAVED_TO_DELETE;
COMMIT;


--

ALTER TABLE HOMEPAGE.NR_SAVED_READERS 
    ADD CONSTRAINT PK_SAVED_READERS PRIMARY KEY (CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_SAVED_READERS
	ADD CONSTRAINT FK_SAV_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);  
	
ALTER TABLE HOMEPAGE.NR_SAVED_READERS ADD CONSTRAINT FK_SAVED_READERS_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
    
--  [start indexes] NR_SAVED_READERS
CREATE  INDEX HOMEPAGE.SAVED_READERS_STR_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_ITM_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ITEM_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_CD_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID, CREATION_DATE DESC); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_SRC_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, ORGANIZATION_ID,  USE_IN_ROLLUP,  IS_VISIBLE, IS_BROADCAST,  CREATION_DATE DESC,  ROLLUP_ENTRY_ID,  SOURCE_TYPE, STORY_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_RLL_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, IS_VISIBLE, USE_IN_ROLLUP); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_STR_RDR 
 	ON HOMEPAGE.NR_SAVED_READERS (STORY_ID, READER_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_DEL_SERV_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (CREATION_DATE DESC); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_ROLLUP_IX 
 	ON HOMEPAGE.NR_SAVED_READERS (ROLLUP_ENTRY_ID, READER_ID); 
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_RIR_IX
 	ON HOMEPAGE.NR_SAVED_READERS (READER_ID, IS_VISIBLE, ROLLUP_ENTRY_ID DESC, CREATION_DATE DESC);
COMMIT;

CREATE  INDEX HOMEPAGE.SAVED_READERS_RLL_BRD_VIS 
 	ON HOMEPAGE.NR_SAVED_READERS (ROLLUP_AUTHOR_ID, IS_BROADCAST, USE_IN_ROLLUP, IS_VISIBLE); 
COMMIT;


--  [end indexes] NR_SAVED_READERS

REORG TABLE HOMEPAGE.NR_SAVED_READERS;
COMMIT;        

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_SAVED_READERS TO USER LCUSER;
COMMIT;

-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-- [end] 101375: [fixup462] Inconsistent location of some READER tables in NEWS32TABSPACE - Fixing jusr NR_SAVED_READERS
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------
-------------------------------------------------------------------------------------------------------------



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 462
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 462, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 461; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 460
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