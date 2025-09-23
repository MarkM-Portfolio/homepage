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

-------------------------------------------------------------------------------
-- ADDING TEMPLATE
-------------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('membership-9qgdh66pf82um3c6q1lQdfgt5','newMembers', 'memberAddedInternalIds', 'profilePhoto', 3);

---------------------------------------------------------------------------------- 
-- PRE FIX SCHEMA 
---------------------------------------------------------------------------------- 
ALTER TABLE HOMEPAGE.NR_RESOURCE 
DROP COLUMN CONTAINER_NAME; 

ALTER TABLE HOMEPAGE.NR_RESOURCE 
ADD CONTAINER_NAME VARCHAR2(256);

----------------------------------------------------------------------------------
-- FIX DUPLICATED RECORDS - ADDING CONSTRAINSTS
----------------------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_RESOURCE
    MODIFY RESOURCE_TYPE 
    NOT NULL;

ALTER TABLE HOMEPAGE.NR_RESOURCE
	ADD CONSTRAINT UNIQUE_RES UNIQUE (CONTAINER_ID, RESOURCE_TYPE);

--REORG TABLE HOMEPAGE.NR_RESOURCE USE NEWS4TMPTABSPACE; 

---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 1) START: MIGRATION FOR STATUS UPDATE *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- BUILDING RESOURCE TABLE WITH CONTAINER NAME FOR PROFILES 
---------------------------------------------------------------------------------- 
CREATE VIEW HOMEPAGE.PROFILE_SOURCES_NAME AS  ( 
    SELECT  NR_SOURCE.SOURCE_ID     SOURCE_ID, 
            B_CONTAINER_ID          CONTAINER_ID, 
            B_CONTAINER_NAME        CONTAINER_NAME 
    FROM    ( 
                SELECT  TEMP_A.A_CONTAINER_ID B_CONTAINER_ID, MAX(NR_NEWS_RECORDS.CONTAINER_NAME) B_CONTAINER_NAME 
                FROM    (   SELECT  NR_SOURCE.SOURCE_ID, MAX(NR_NEWS_RECORDS.CONTAINER_ID) A_CONTAINER_ID 
                            FROM HOMEPAGE.NR_SOURCE NR_SOURCE, HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
                            WHERE NR_NEWS_RECORDS.SOURCE = 'profiles' AND NR_SOURCE.CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID AND NR_SOURCE.SOURCE = 'profiles' 
                            GROUP BY NR_SOURCE.SOURCE_ID 
                        )   TEMP_A, 
                            HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
                WHERE       NR_NEWS_RECORDS.SOURCE = 'profiles' AND TEMP_A.A_CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID 
                GROUP BY    TEMP_A.A_CONTAINER_ID 
                ) TEMP_B, 
                HOMEPAGE.NR_SOURCE NR_SOURCE 
    WHERE NR_SOURCE.CONTAINER_ID = TEMP_B.B_CONTAINER_ID 
);

---------
-- START: FIX - create PROFILE_SOURCES_NAME_FILTERED
--------
CREATE VIEW HOMEPAGE.PROFILE_SOURCES_NAME_UNIQUE AS (
    SELECT CONTAINER_ID, CONTAINER_NAME, MAX (SOURCE_ID) SOURCE_ID
    FROM HOMEPAGE.PROFILE_SOURCES_NAME
    GROUP BY  CONTAINER_ID, CONTAINER_NAME
);

---------
-- END: FIX
--------  

-- INSERTING PROFILES RESOURCES WITH THE NAME 
INSERT INTO HOMEPAGE.NR_RESOURCE ( 
    RESOURCE_ID, 
    SOURCE, 
    CONTAINER_ID, 
    CONTAINER_NAME, 
    CONTAINER_URL, 
    CATEGORY_TYPE, 
    RESOURCE_TYPE 
) 
SELECT  SOURCE_ID, 
        'profiles', 
        CONTAINER_ID,  
        CONTAINER_NAME, 
        '' , 
        2 , -- profile type 
        10 -- person 
FROM    HOMEPAGE.PROFILE_SOURCES_NAME_UNIQUE; 
COMMIT; 

---------------------------------------------------------------------------------- 
-- BUILDING NETWORK TABLE FOR STATUS UPDATE 
---------------------------------------------------------------------------------- 
-- FROM the subscription TABLE we SELECT what IS implicit. thIS the USEr network 
CREATE VIEW HOMEPAGE.TMP_NETWORK AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID COLLEAGUE_ID 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE, HOMEPAGE.PERSON PERSON 
            WHERE SOURCE = 'profiles' AND PERSON.PERSON_ID = NR_SOURCE.CONTAINER_ID 
            ) TEMP 
    WHERE   IS_EXPLICIT = 0 AND IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
); 

-- to build the network relationship 
INSERT INTO HOMEPAGE.NR_NETWORK ( 
    NETWORK_ID, 
    PERSON_ID, 
    COLLEAGUE_ID 
) 
SELECT  (SUBSTR(TMP_NETWORK.PERSON_ID,1,18) || SUBSTR(TMP_NETWORK.COLLEAGUE_ID,1,18)), TMP_NETWORK.PERSON_ID,  TMP_NETWORK.COLLEAGUE_ID 
FROM    HOMEPAGE.TMP_NETWORK TMP_NETWORK; 
COMMIT; 

----------------------------------------------------------------------------------------------- 
-- BUILIDING FOLLOWER TABLES FOR PROFILES 
------------------------------------------------------------------------------------------------ 
-- FROM the subscription TABLE we SELECT what IS explicit. thIS the what an USEr IS following 
-- what an USEr IS following 
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID FOLLOWED_CONTAINER 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE, HOMEPAGE.PERSON PERSON 
            WHERE SOURCE = 'profiles' AND PERSON.PERSON_ID = NR_SOURCE.CONTAINER_ID 
            ) TEMP 
    WHERE   IS_EXPLICIT = 1 AND IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
); 

INSERT INTO HOMEPAGE.NR_FOLLOWS ( 
    FOLLOW_ID, 
    PERSON_ID, 
    RESOURCE_ID 
) 
SELECT  (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) FOLLOWS_ID, PERSON_ID, RESOURCE_ID 
FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE, HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS 
WHERE   NR_RESOURCE.CONTAINER_ID = TMP_FOLLOWS.FOLLOWED_CONTAINER; 
COMMIT; 


--------------------------------------------------------------------------------------------------------------- 
-- A - BUILDING STATUS UPDATE NETWORK + FOLLOWED (NOTe in the pASt who IS in your network IS automated followed) 
--------------------------------------------------------------------------------------------------------------- 
---------------------------------------------------------------- 
-- A-1) insert all the status updates releted to the USEr network 
---------------------------------------------------------------- 
-- profiles.status.updated 
INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
    NEWS_STATUS_NETWORK_ID, 
    READER_ID, 
    ACTOR_UUID, 
    BRIEF_DESC, 
    ITEM_URL, 
    ITEM_ID, 
    EVENT_NAME, 
    TARGET_SUBJECT_ID, 
    IS_WALL_POST, 
    CREATION_DATE, 
    UPDATE_DATE, 
    N_COMMENTS, 
    IS_NETWORK_NEWS, 
    IS_FOLLOW_NEWS 
) 
SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID, 
        NR_NEWS_RECORDS.READER_ID, 
        NR_NEWS_RECORDS.ACTOR_UUID, 
        NR_NEWS_RECORDS.BRIEF_DESC, 
        NR_NEWS_RECORDS.ENTRY_URL, 
		'MIGRATED' || SUBSTR(NR_NEWS_RECORDS.EVENT_RECORD_UUID,1,27), 
        NR_NEWS_RECORDS.EVENT_NAME, 
        '', -- TARGET_SUBJECT_ID 
        0, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        0, 
        1,  -- IS_NETWORK_NEWS 
        1   -- IS_FOLLOW_NEWS 
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NOT NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.status.updated'; 
COMMIT; 

---------------------------------------------------------------- 
-- A-1b) insert all MY status updates releted to myself 
---------------------------------------------------------------- 
INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
    NEWS_STATUS_NETWORK_ID, 
    READER_ID, 
    ACTOR_UUID, 
    BRIEF_DESC, 
    ITEM_URL, 
    ITEM_ID, 
    EVENT_NAME, 
    TARGET_SUBJECT_ID, 
    IS_WALL_POST, 
    CREATION_DATE, 
    UPDATE_DATE, 
    N_COMMENTS, 
    IS_NETWORK_NEWS, 
    IS_FOLLOW_NEWS 
)
SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID, 
        NR_NEWS_RECORDS.READER_ID, 
        NR_NEWS_RECORDS.ACTOR_UUID, 
        NR_NEWS_RECORDS.BRIEF_DESC, 
        NR_NEWS_RECORDS.ENTRY_URL, 
		'MIGRATED' || SUBSTR(NR_NEWS_RECORDS.EVENT_RECORD_UUID,1,27), 
        NR_NEWS_RECORDS.EVENT_NAME, 
        '', -- TARGET_SUBJECT_ID 
        0, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        0, 
        1,  -- IS_NETWORK_NEWS 
        0   -- IS_FOLLOW_NEWS 
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.PERSON PERSON
WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.status.updated' AND IS_PUBLIC = 1 AND
        ACTOR_UUID = PERSON.PERSON_ID;
COMMIT;

UPDATE HOMEPAGE.NR_NEWS_STATUS_NETWORK SET READER_ID = ACTOR_UUID WHERE READER_ID IS NULL;
COMMIT;

---------------------------------------------------------------- 
-- A-1c) insert all MY network updates 
---------------------------------------------------------------- 
-- profiles.status.updated 
-- performing this insertions two times because there is a bidirectional reletionship

-- PERSON - COLLEAGUE
-- COLLEAGUE - PERSON
INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
    NEWS_STATUS_NETWORK_ID, 
    READER_ID, 
    ACTOR_UUID, 
    BRIEF_DESC, 
    ITEM_URL, 
    ITEM_ID, 
    EVENT_NAME, 
    TARGET_SUBJECT_ID, 
    IS_WALL_POST, 
    CREATION_DATE, 
    UPDATE_DATE, 
    N_COMMENTS, 
    IS_NETWORK_NEWS, 
    IS_FOLLOW_NEWS 
) 
SELECT  (   SUBSTR(NR_NETWORK.PERSON_ID,1,12) || 
            SUBSTR(NR_NEWS_RECORDS.ACTOR_UUID,1,12) ||
            SUBSTR(NR_NEWS_RECORDS.NEWS_RECORDS_ID,1,12)
        ) NEWS_STATUS_NETWORK_ID, 
        NR_NETWORK.PERSON_ID, 
        NR_NEWS_RECORDS.ACTOR_UUID, 
        NR_NEWS_RECORDS.BRIEF_DESC, 
        NR_NEWS_RECORDS.ENTRY_URL, 
        'MIGRATED' || SUBSTR(NR_NEWS_RECORDS.EVENT_RECORD_UUID,1,27), 
        NR_NEWS_RECORDS.EVENT_NAME, 
        '', -- TARGET_SUBJECT_ID 
        0, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        NR_NEWS_RECORDS.CREATION_DATE, 
        0, 
        1,  -- IS_NETWORK_NEWS 
        0   -- IS_FOLLOW_NEWS 
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS,
        HOMEPAGE.NR_NETWORK NR_NETWORK
WHERE   NR_NEWS_RECORDS.EVENT_NAME = 'profiles.status.updated' AND NR_NEWS_RECORDS.READER_ID IS NULL AND 
        NR_NEWS_RECORDS.ACTOR_UUID = NR_NETWORK.COLLEAGUE_ID;

COMMIT;

-------------------------------------- 
---- A-2) INSERT STATUS UPDATE WALLPOST 
-------------------------------------- 
---- 'profiles.wallpost.created' 
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID, 
--        NR_NEWS_RECORDS.ACTOR_UUID, -- READER ID 
--        NR_NEWS_RECORDS.ACTOR_UUID, -- ACTOR_UUID 
--        NR_NEWS_RECORDS.BRIEF_DESC, 
--        NR_NEWS_RECORDS.ENTRY_URL, 
--        NR_NEWS_RECORDS.EVENT_RECORD_UUID, 
--        NR_NEWS_RECORDS.EVENT_NAME, 
--        NR_NEWS_RECORDS.ACTOR_UUID, -- TARGET_SUBJECT_ID 
--        1, -- IS_WALL_POST 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        0, 
--        1,  -- IS_NETWORK_NEWS 
--        1   -- IS_FOLLOW_NEWS 
--FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
--WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NOT NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created'; 
--COMMIT; 
--
-------------------------------------- 
---- A-3) INSERT STATUS UPDATE WALLPOST 
-------------------------------------- 
---- 'profiles.wallpost.created.you' 
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID, 
--        NR_NEWS_RECORDS.READER_ID, -- READER ID 
--        NR_NEWS_RECORDS.ACTOR_UUID, -- ACTOR_UUID 
--        NR_NEWS_RECORDS.BRIEF_DESC, 
--        NR_NEWS_RECORDS.ENTRY_URL, 
--        NR_NEWS_RECORDS.EVENT_RECORD_UUID, 
--        NR_NEWS_RECORDS.EVENT_NAME, 
--        NR_NEWS_RECORDS.READER_ID, -- TARGET_SUBJECT_ID 
--        1, -- IS_WALL_POST 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        0, 
--        1,  -- IS_NETWORK_NEWS 
--        1   -- IS_FOLLOW_NEWS 
--FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
--WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NOT NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created.you'; 
--COMMIT; 
--
-------------------------------------- 
---- A-4) INSERT STATUS UPDATE WALLPOST 
-------------------------------------- 
---- 'profiles.wallpost.created.their' 
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID, 
--        NR_NEWS_RECORDS.READER_ID, -- READER ID 
--        NR_NEWS_RECORDS.ACTOR_UUID, -- ACTOR_UUID 
--        NR_NEWS_RECORDS.BRIEF_DESC, 
--        NR_NEWS_RECORDS.ENTRY_URL, 
--        NR_NEWS_RECORDS.EVENT_RECORD_UUID, 
--        NR_NEWS_RECORDS.EVENT_NAME, 
--        NR_NEWS_RECORDS.READER_ID, -- TARGET_SUBJECT_ID 
--        1, -- IS_WALL_POST 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        NR_NEWS_RECORDS.CREATION_DATE, 
--        0, 
--        1,  -- IS_NETWORK_NEWS 
--        1   -- IS_FOLLOW_NEWS 
--FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
--WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NOT NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created.their'; 
--COMMIT; 



--------------------------------------------------------------------------------------------------------------- 
-- B - BUILDING STATUS UPDATE FOLLOWED 
--------------------------------------------------------------------------------------------------------------- 
CREATE VIEW HOMEPAGE.TMP_FOLLOWS_CONTAINER AS ( 
    SELECT  FOLLOW_ID, PERSON_ID, CONTAINER_ID, CONTAINER_NAME 
    FROM    HOMEPAGE.NR_FOLLOWS NR_FOLLOWS, HOMEPAGE.NR_RESOURCE NR_RESOURCE 
    WHERE   NR_FOLLOWS.RESOURCE_ID = NR_RESOURCE.RESOURCE_ID 
); 



---------------------------------------------------------------- 
-- B-1) insert all the status updates releted to the USEr network 
---------------------------------------------------------------- 
-- profiles.status.updated 
CREATE VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED AS (            
    SELECT  SUBSTR(NR_NEWS_RECORDS.NEWS_RECORDS_ID,1,18) || SUBSTR(TMP_FOLLOWS_CONTAINER.PERSON_ID,1,18)  NEWS_STATUS_NETWORK_ID, 
            TMP_FOLLOWS_CONTAINER.PERSON_ID         READER_ID, 
            NR_NEWS_RECORDS.ACTOR_UUID              ACTOR_UUID, 
            NR_NEWS_RECORDS.BRIEF_DESC              BRIEF_DESC, 
            NR_NEWS_RECORDS.ENTRY_URL               ITEM_URL, 
			'MIGRATED' || SUBSTR(NR_NEWS_RECORDS.EVENT_RECORD_UUID,1,27)			       				ITEM_ID, 
            NR_NEWS_RECORDS.EVENT_NAME              EVENT_NAME, 
            ''         								TARGET_SUBJECT_ID, -- target subject 
            0                                       IS_WALL_POST, -- IS wall post 
            NR_NEWS_RECORDS.CREATION_DATE           CREATION_DATE, 
            NR_NEWS_RECORDS.CREATION_DATE           UPDATE_DATE, 
            0                                       N_COMMENTS, 
            0                                       IS_NETWORK_NEWS,  -- thIS IS NOT a network news 
            1                                       IS_FOLLOW_NEWS  -- thIS IS just a followed news 
    FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, 
            HOMEPAGE.TMP_FOLLOWS_CONTAINER TMP_FOLLOWS_CONTAINER 
    WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.status.updated' AND 
            TMP_FOLLOWS_CONTAINER.CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID 
); 

CREATE VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED AS ( 
    SELECT      NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS, MAX(NEWS_STATUS_NETWORK_ID) MAX_NEWS_STATUS_NETWORK_ID 
    FROM        HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED 
    GROUP BY    NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS 
); 
 
INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
    NEWS_STATUS_NETWORK_ID, 
    READER_ID, 
    ACTOR_UUID, 
    BRIEF_DESC, 
    ITEM_URL, 
    ITEM_ID, 
    EVENT_NAME, 
    TARGET_SUBJECT_ID, 
    IS_WALL_POST, 
    CREATION_DATE, 
    UPDATE_DATE, 
    N_COMMENTS, 
    IS_NETWORK_NEWS, 
    IS_FOLLOW_NEWS 
) 
SELECT      NEWS_STATUS_NETWORK_ID, 
            READER_ID, 
            ACTOR_UUID, 
            BRIEF_DESC, 
            ITEM_URL, 
            ITEM_ID, 
            EVENT_NAME, 
            TARGET_SUBJECT_ID, 
            IS_WALL_POST, 
            CREATION_DATE, 
            UPDATE_DATE, 
            N_COMMENTS, 
            IS_NETWORK_NEWS, 
            IS_FOLLOW_NEWS 
FROM HOMEPAGE.TMP_FOLLOWED_FILTERED; 
COMMIT; 


------------------------------------------------------------------ 
---- B-2) INSERT STATUS UPDATE WALLPOST 
------------------------------------------------------------------ 
--DROP VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED; 
--CREATE VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED AS (            
--    SELECT  SUBSTR(NR_NEWS_RECORDS.NEWS_RECORDS_ID,1,18) || SUBSTR(TMP_FOLLOWS_CONTAINER.PERSON_ID,1,18)  NEWS_STATUS_NETWORK_ID, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID         READER_ID, 
--            NR_NEWS_RECORDS.ACTOR_UUID              ACTOR_UUID, 
--            NR_NEWS_RECORDS.BRIEF_DESC              BRIEF_DESC, 
--            NR_NEWS_RECORDS.ENTRY_URL               ITEM_URL, 
--            NR_NEWS_RECORDS.EVENT_RECORD_UUID       ITEM_ID, 
--            NR_NEWS_RECORDS.EVENT_NAME              EVENT_NAME, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID         TARGET_SUBJECT_ID, -- target subject 
--            0                                       IS_WALL_POST, -- IS wall post 
--            NR_NEWS_RECORDS.CREATION_DATE           CREATION_DATE, 
--            NR_NEWS_RECORDS.CREATION_DATE           UPDATE_DATE, 
--            0                                       N_COMMENTS, 
--            0                                       IS_NETWORK_NEWS,  -- thIS IS NOT a network news 
--            1                                       IS_FOLLOW_NEWS  -- thIS IS just a followed news 
--    FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, 
--            HOMEPAGE.TMP_FOLLOWS_CONTAINER TMP_FOLLOWS_CONTAINER 
--    WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created' AND 
--            TMP_FOLLOWS_CONTAINER.CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID 
--); 
--
--DROP VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--CREATE VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED AS ( 
--    SELECT      NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS, MAX(NEWS_STATUS_NETWORK_ID) MAX_NEWS_STATUS_NETWORK_ID 
--    FROM        HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED 
--    GROUP BY    NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS 
--); 
---- profiles.wallpost.created 
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT      NEWS_STATUS_NETWORK_ID, 
--            READER_ID, 
--            ACTOR_UUID, 
--            BRIEF_DESC, 
--            ITEM_URL, 
--            ITEM_ID, 
--            EVENT_NAME, 
--            TARGET_SUBJECT_ID, 
--            IS_WALL_POST, 
--            CREATION_DATE, 
--            UPDATE_DATE, 
--            N_COMMENTS, 
--            IS_NETWORK_NEWS, 
--            IS_FOLLOW_NEWS 
--FROM HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--COMMIT; 
--
-------------------------------------- 
---- B-3) INSERT STATUS UPDATE WALLPOST 
-------------------------------------- 
---- 'profiles.wallpost.created.you' 
--DROP VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED; 
--CREATE VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED AS ( 
--    SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID NEWS_STATUS_NETWORK_ID, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID READER_ID, 
--            NR_NEWS_RECORDS.ACTOR_UUID ACTOR_UUID, 
--            NR_NEWS_RECORDS.BRIEF_DESC BRIEF_DESC, 
--            NR_NEWS_RECORDS.ENTRY_URL ITEM_URL, 
--            NR_NEWS_RECORDS.EVENT_RECORD_UUID ITEM_ID, 
--            NR_NEWS_RECORDS.EVENT_NAME EVENT_NAME, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID TARGET_SUBJECT_ID, -- target subject 
--            1 IS_WALL_POST, -- IS wall post 
--            NR_NEWS_RECORDS.CREATION_DATE CREATION_DATE, 
--            NR_NEWS_RECORDS.CREATION_DATE UPDATE_DATE, 
--            0 N_COMMENTS, 
--            0 IS_NETWORK_NEWS,  -- thIS IS NOT a network news 
--            1 IS_FOLLOW_NEWS  -- thIS IS just a followed news 
--    FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.TMP_FOLLOWS_CONTAINER TMP_FOLLOWS_CONTAINER 
--    WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created.you' AND 
--            TMP_FOLLOWS_CONTAINER.CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID 
--); 
--
--DROP VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--CREATE VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED AS ( 
--    SELECT      NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS, MAX(NEWS_STATUS_NETWORK_ID) MAX_NEWS_STATUS_NETWORK_ID 
--    FROM        HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED 
--    GROUP BY    NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS 
--); 
--
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT      NEWS_STATUS_NETWORK_ID, 
--            READER_ID, 
--            ACTOR_UUID, 
--            BRIEF_DESC, 
--            ITEM_URL, 
--            ITEM_ID, 
--            EVENT_NAME, 
--            TARGET_SUBJECT_ID, 
--            IS_WALL_POST, 
--            CREATION_DATE, 
--            UPDATE_DATE, 
--            N_COMMENTS, 
--            IS_NETWORK_NEWS, 
--            IS_FOLLOW_NEWS 
--FROM HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--COMMIT; 
--
-------------------------------------- 
---- B-4) INSERT STATUS UPDATE WALLPOST 
-------------------------------------- 
---- 'profiles.wallpost.created.their' 
--DROP VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED; 
--CREATE VIEW HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED AS ( 
--    SELECT  NR_NEWS_RECORDS.NEWS_RECORDS_ID NEWS_STATUS_NETWORK_ID, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID READER_ID, 
--            NR_NEWS_RECORDS.ACTOR_UUID ACTOR_UUID, 
--            NR_NEWS_RECORDS.BRIEF_DESC BRIEF_DESC, 
--            NR_NEWS_RECORDS.ENTRY_URL ITEM_URL, 
--            NR_NEWS_RECORDS.EVENT_RECORD_UUID ITEM_ID, 
--            NR_NEWS_RECORDS.EVENT_NAME EVENT_NAME, 
--            TMP_FOLLOWS_CONTAINER.PERSON_ID TARGET_SUBJECT_ID, -- target subject 
--            1 IS_WALL_POST, -- IS wall post 
--            NR_NEWS_RECORDS.CREATION_DATE CREATION_DATE, 
--            NR_NEWS_RECORDS.CREATION_DATE UPDATE_DATE, 
--            0 N_COMMENTS, 
--            0 IS_NETWORK_NEWS,  -- thIS IS NOT a network news 
--            1 IS_FOLLOW_NEWS  -- thIS IS just a followed news 
--    FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, HOMEPAGE.TMP_FOLLOWS_CONTAINER TMP_FOLLOWS_CONTAINER 
--    WHERE   NR_NEWS_RECORDS.SOURCE = 'profiles' AND READER_ID IS NULL AND NR_NEWS_RECORDS.EVENT_NAME = 'profiles.wallpost.created.you' AND 
--            TMP_FOLLOWS_CONTAINER.CONTAINER_ID = NR_NEWS_RECORDS.CONTAINER_ID 
--); 
--
--DROP VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--CREATE VIEW HOMEPAGE.TMP_FOLLOWED_FILTERED AS ( 
--    SELECT      NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS, MAX(NEWS_STATUS_NETWORK_ID) MAX_NEWS_STATUS_NETWORK_ID 
--    FROM        HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED 
--    GROUP BY    NEWS_STATUS_NETWORK_ID, READER_ID, ACTOR_UUID, BRIEF_DESC, ITEM_URL, ITEM_ID, EVENT_NAME,  TARGET_SUBJECT_ID, IS_WALL_POST, 
--                CREATION_DATE, UPDATE_DATE, N_COMMENTS, IS_NETWORK_NEWS, IS_FOLLOW_NEWS 
--); 
--
--INSERT INTO HOMEPAGE.NR_NEWS_STATUS_NETWORK ( 
--    NEWS_STATUS_NETWORK_ID, 
--    READER_ID, 
--    ACTOR_UUID, 
--    BRIEF_DESC, 
--    ITEM_URL, 
--    ITEM_ID, 
--    EVENT_NAME, 
--    TARGET_SUBJECT_ID, 
--    IS_WALL_POST, 
--    CREATION_DATE, 
--    UPDATE_DATE, 
--    N_COMMENTS, 
--    IS_NETWORK_NEWS, 
--    IS_FOLLOW_NEWS 
--) 
--SELECT      NEWS_STATUS_NETWORK_ID, 
--            READER_ID, 
--            ACTOR_UUID, 
--            BRIEF_DESC, 
--            ITEM_URL, 
--            ITEM_ID, 
--            EVENT_NAME, 
--            TARGET_SUBJECT_ID, 
--            IS_WALL_POST, 
--            CREATION_DATE, 
--            UPDATE_DATE, 
--            N_COMMENTS, 
--            IS_NETWORK_NEWS, 
--            IS_FOLLOW_NEWS 
--FROM HOMEPAGE.TMP_FOLLOWED_FILTERED; 
--COMMIT; 

DROP VIEW  HOMEPAGE.PROFILE_SOURCES_NAME_UNIQUE;
DROP VIEW  HOMEPAGE.PROFILE_SOURCES_NAME; 
DROP VIEW  HOMEPAGE.TMP_NETWORK; 
DROP VIEW  HOMEPAGE.TMP_FOLLOWS; 
DROP VIEW  HOMEPAGE.TMP_FOLLOWS_CONTAINER; 
DROP VIEW  HOMEPAGE.TMP_STATUS_UPDATED_FOLLOWED; 
DROP VIEW  HOMEPAGE.TMP_FOLLOWED_FILTERED; 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 1) END: MIGRATION FOR STATUS UPDATE *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 

---------------------------------------------------------------------------------- 
-- **************************************************************************** -- 
-- **************************************************************************** -- 
-- **************************************************************************** -- 
---------------------------------------------------------------------------------- 




---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 2) START: MIGRATION FOR FOLLOWED TAGS *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 

-- INSERTING TAG RESOURCES WITH THE NAME 
INSERT INTO HOMEPAGE.NR_RESOURCE ( 
    RESOURCE_ID, 
    SOURCE, 
    CONTAINER_ID, 
    CONTAINER_NAME, 
    CONTAINER_URL, 
    CATEGORY_TYPE, 
    RESOURCE_TYPE 
) 
SELECT  SOURCE_ID, 
        'tag', 
        CONTAINER_ID,  
        CONTAINER_NAME, 
        '' , 
        10 , -- tags category typw 
        13 -- tag reSOURCE type 
FROM    HOMEPAGE.NR_SOURCE NR_SOURCE 
WHERE   NR_SOURCE.SOURCE = 'tag'; 
COMMIT; 

-- CREATING THE FOLLOWS RELETIONSHIP FOR TAG 
INSERT INTO HOMEPAGE.NR_FOLLOWS ( 
    FOLLOW_ID, 
    PERSON_ID, 
    RESOURCE_ID 
) 
SELECT  (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) FOLLOWS_ID, 
        PERSON_ID, 
        RESOURCE_ID 
FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
        HOMEPAGE.NR_RESOURCE NR_RESOURCE 
WHERE   NR_SUBSCRIPTION.IS_ACTIVE = 1 AND NR_SUBSCRIPTION.IS_EXPLICIT = 1 AND 
        NR_RESOURCE.RESOURCE_ID = NR_SUBSCRIPTION.SOURCE_ID AND 
        NR_RESOURCE.CATEGORY_TYPE = 10; 

-- sanitize the SOURCE tag. moving tag to be tags 
update HOMEPAGE.NR_RESOURCE set SOURCE='tags' WHERE SOURCE='tag'; 
COMMIT; 

---------------------------------------------------- 
-- WORKING ON NR_FOLLOWED_STORIES AND NR_STORIES 
---------------------------------------------------- 

-- creating a VIEW to see all the folloed tags 
CREATE VIEW HOMEPAGE.TMP_FOLLOWED_TAGS AS ( 
    SELECT  PERSON_ID, CONTAINER_ID, CONTAINER_NAME 
    FROM    HOMEPAGE.NR_FOLLOWS NR_FOLLOWS, HOMEPAGE.NR_RESOURCE NR_RESOURCE 
    WHERE   NR_FOLLOWS.RESOURCE_ID = NR_RESOURCE.RESOURCE_ID AND NR_RESOURCE.CATEGORY_TYPE = 10 
); 

-- create a VIEW to see all the readed stories tags 
CREATE VIEW HOMEPAGE.TMP_READED_TAGS AS ( 
SELECT  SUBSTR(NR_NEWS_RECORDS.NEWS_RECORDS_ID,1,18) || SUBSTR(TMP_FOLLOWED_TAGS.PERSON_ID,1,18)              FOLLOWED_STORY_ID, 
        TMP_FOLLOWED_TAGS.PERSON_ID                 READER_ID, 
        10                                          CATEGORY_TYPE, 
        NR_NEWS_RECORDS.SOURCE                      SOURCE, 
        TMP_FOLLOWED_TAGS.CONTAINER_ID              CONTAINER_ID, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           ITEM_ID, 
        13                                          RESOURCE_TYPE, 
        NR_NEWS_RECORDS.CREATION_DATE               CREATION_DATE, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           STORY_ID, 
        NR_NEWS_RECORDS.EVENT_NAME                  EVENT_NAME, 
        NR_NEWS_RECORDS.CONTAINER_NAME              CONTAINER_NAME, 
        NR_NEWS_RECORDS.CONTAINER_URL               CONTAINER_URL, 
        NR_NEWS_RECORDS.EVENT_NAME                  ITEM_NAME, 
        NR_NEWS_RECORDS.ENTRY_URL                   ITEM_URL, 
        NR_NEWS_RECORDS.ENTRY_ATOM_URL              ITEM_ATOM_URL, 
        ''                                          ITEM_CORRELATION_ID, 
        NR_NEWS_RECORDS.BRIEF_DESC                  BRIEF_DESC, 
        NR_NEWS_RECORDS.ACTOR_UUID                  ACTOR_UUID, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           EVENT_RECORD_UUID, 
        NR_NEWS_RECORDS.TAGS                        TAGS, 
        NR_NEWS_RECORDS.META_TEMPLATE               META_TEMPLATE, 
        NR_NEWS_RECORDS.TEXT_META_TEMPLATE          TEXT_META_TEMPLATE, 
        ''                                          R_META_TEMPLATE, 
        ''                                          R_TEXT_META_TEMPLATE, 
        0                                           N_COMMENTS, 
        0                                           N_RECOMMANDATIONS 
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS,  HOMEPAGE.TMP_FOLLOWED_TAGS TMP_FOLLOWED_TAGS 
WHERE   NR_NEWS_RECORDS.READER_ID IS NULL AND 
        NR_NEWS_RECORDS.IS_CONTAINER = 1 AND 
        NR_NEWS_RECORDS.SOURCE LIKE 'tag.%' AND 
        NR_NEWS_RECORDS.CONTAINER_ID = TMP_FOLLOWED_TAGS.CONTAINER_ID 
); 
COMMIT; 

---- NR_STORIES insert stories releted to the tags 
--INSERT INTO HOMEPAGE.NR_STORIES ( 
--    STORY_ID, 
--    EVENT_NAME, 
--    SOURCE, 
--    CONTAINER_ID, 
--    CONTAINER_NAME, 
--    CONTAINER_URL, 
--    ITEM_NAME, 
--    ITEM_URL, 
--    ITEM_ID, 
--    ITEM_CORRELATION_ID, 
--    CREATION_DATE, 
--    BRIEF_DESC, 
--    ACTOR_UUID, 
--    EVENT_RECORD_UUID, 
--    TAGS, 
--    META_TEMPLATE, 
--    R_META_TEMPLATE, 
--    R_TEXT_META_TEMPLATE, 
--    N_COMMENTS, 
--    N_RECOMMANDATIONS 
--) 
--SELECT  TMP_READED_TAGS.STORY_ID, 
--        EVENT_NAME, 
--        SOURCE, 
--        CONTAINER_ID, 
--        CONTAINER_NAME, 
--        CONTAINER_URL, 
--        ITEM_NAME, 
--        ITEM_URL, 
--        ITEM_ID, 
--        ITEM_CORRELATION_ID, 
--        CREATION_DATE, 
--        BRIEF_DESC, 
--        ACTOR_UUID, 
--        EVENT_RECORD_UUID, 
--        TAGS, 
--        META_TEMPLATE, 
--        R_META_TEMPLATE, 
--        R_TEXT_META_TEMPLATE, 
--        N_COMMENTS, 
--        N_RECOMMANDATIONS 
--FROM    ( 
--        SELECT STORY_ID, MAX(FOLLOWED_STORY_ID) A_FOLLOWED_STORY_ID 
--        FROM HOMEPAGE.TMP_READED_TAGS TMP_READED_TAGS 
--        GROUP by TMP_READED_TAGS.STORY_ID 
--        )   TEMP_A, 
--        HOMEPAGE.TMP_READED_TAGS TMP_READED_TAGS 
--WHERE   TEMP_A.A_FOLLOWED_STORY_ID = TMP_READED_TAGS.FOLLOWED_STORY_ID; 
--COMMIT; 
--
---- NR_FOLLOWED_STORIES insert readers for tags 
--INSERT INTO HOMEPAGE.NR_FOLLOWED_STORIES ( 
--    FOLLOWED_STORY_ID, 
--    READER_ID, 
--    CATEGORY_TYPE, 
--    SOURCE, 
--    CONTAINER_ID, 
--    ITEM_ID, 
--    RESOURCE_TYPE, 
--    CREATION_DATE, 
--    STORY_ID 
--) 
--SELECT      FOLLOWED_STORY_ID, 
--            READER_ID, 
--            CATEGORY_TYPE, 
--            SOURCE, 
--            CONTAINER_ID, 
--            ITEM_ID, 
--            RESOURCE_TYPE, 
--            CREATION_DATE, 
--            STORY_ID 
--FROM HOMEPAGE.TMP_READED_TAGS;    


DROP VIEW HOMEPAGE.TMP_READED_TAGS; 
DROP VIEW HOMEPAGE.TMP_FOLLOWED_TAGS; 

---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 2) END: MIGRATION FOR FOLLOWED TAGS *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 

---------------------------------------------------------------------------------- 
-- **************************************************************************** -- 
-- **************************************************************************** -- 
-- **************************************************************************** -- 
---------------------------------------------------------------------------------- 



---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 3) START: MIGRATION FOR FOLLOWED PEOPLE *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
---------------------------------------------------- 
-- WORKING ON NR_FOLLOWED_STORIES AND NR_STORIES 
---------------------------------------------------- 

-- creating a VIEW to see all the folloed profiles 
CREATE VIEW HOMEPAGE.TMP_FOLLOWED_PROFILES AS ( 
    SELECT  PERSON_ID, CONTAINER_ID, CONTAINER_NAME 
    FROM    HOMEPAGE.NR_FOLLOWS NR_FOLLOWS, HOMEPAGE.NR_RESOURCE NR_RESOURCE 
    WHERE   NR_FOLLOWS.RESOURCE_ID = NR_RESOURCE.RESOURCE_ID AND NR_RESOURCE.CATEGORY_TYPE = 2 
); 

-- create a VIEW to see all the readed stories tags 
CREATE VIEW HOMEPAGE.TMP_READED_PROFILES AS ( 
SELECT  SUBSTR(NR_NEWS_RECORDS.NEWS_RECORDS_ID,1,18) || SUBSTR(TMP_FOLLOWED_PROFILES.PERSON_ID,1,18)  FOLLOWED_STORY_ID, 
        TMP_FOLLOWED_PROFILES.PERSON_ID             READER_ID, 
        2                                           CATEGORY_TYPE, -- profiles 
        NR_NEWS_RECORDS.SOURCE                      SOURCE, 
        TMP_FOLLOWED_PROFILES.CONTAINER_ID          CONTAINER_ID, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           ITEM_ID, 
        10                                          RESOURCE_TYPE, -- person 
        NR_NEWS_RECORDS.CREATION_DATE               CREATION_DATE, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           STORY_ID, 
        NR_NEWS_RECORDS.EVENT_NAME                  EVENT_NAME, 
        NR_NEWS_RECORDS.CONTAINER_NAME              CONTAINER_NAME, 
        NR_NEWS_RECORDS.CONTAINER_URL               CONTAINER_URL, 
        NR_NEWS_RECORDS.EVENT_NAME                  ITEM_NAME, 
        NR_NEWS_RECORDS.ENTRY_URL                   ITEM_URL, 
        NR_NEWS_RECORDS.ENTRY_ATOM_URL              ITEM_ATOM_URL, 
        ''                                          ITEM_CORRELATION_ID, 
        NR_NEWS_RECORDS.BRIEF_DESC                  BRIEF_DESC, 
        NR_NEWS_RECORDS.ACTOR_UUID                  ACTOR_UUID, 
        NR_NEWS_RECORDS.EVENT_RECORD_UUID           EVENT_RECORD_UUID, 
        NR_NEWS_RECORDS.TAGS                        TAGS, 
        NR_NEWS_RECORDS.META_TEMPLATE               META_TEMPLATE, 
        NR_NEWS_RECORDS.TEXT_META_TEMPLATE          TEXT_META_TEMPLATE, 
        ''                                          R_META_TEMPLATE, 
        ''                                          R_TEXT_META_TEMPLATE, 
        0                                           N_COMMENTS, 
        0                                           N_RECOMMANDATIONS 
FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS,  HOMEPAGE.TMP_FOLLOWED_PROFILES TMP_FOLLOWED_PROFILES 
WHERE   NR_NEWS_RECORDS.READER_ID IS NULL 
        AND ( 
                (NR_NEWS_RECORDS.IS_CONTAINER = 1 AND NR_NEWS_RECORDS.SOURCE = 'profiles' AND  NR_NEWS_RECORDS.CONTAINER_ID =  TMP_FOLLOWED_PROFILES.CONTAINER_ID ) 
                OR 
                (NR_NEWS_RECORDS.IS_PUBLIC = 1 AND NR_NEWS_RECORDS.ACTOR_UUID = TMP_FOLLOWED_PROFILES.CONTAINER_ID) 
        ) 
); 

-- to have unique values 
CREATE VIEW HOMEPAGE.TMP_READED_PROFILES_FILTERED AS ( 
SELECT      FOLLOWED_STORY_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, RESOURCE_TYPE, 
            CREATION_DATE, STORY_ID, EVENT_NAME, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, ITEM_CORRELATION_ID, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, 
            TAGS, META_TEMPLATE, TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS, MAX(FOLLOWED_STORY_ID) MAX_FOLLOWED_STORY_ID 
FROM        HOMEPAGE.TMP_READED_PROFILES TMP_READED_PROFILES 
GROUP BY    FOLLOWED_STORY_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, RESOURCE_TYPE, 
            CREATION_DATE, STORY_ID, EVENT_NAME, CONTAINER_NAME, CONTAINER_URL, ITEM_NAME, ITEM_URL, ITEM_ATOM_URL, ITEM_CORRELATION_ID, BRIEF_DESC, ACTOR_UUID, EVENT_RECORD_UUID, 
            TAGS, META_TEMPLATE, TEXT_META_TEMPLATE, R_META_TEMPLATE, R_TEXT_META_TEMPLATE, N_COMMENTS, N_RECOMMANDATIONS 
); 


CREATE VIEW HOMEPAGE.TMP_READED_STORIES_PROFILES AS ( 
    SELECT  TMP_READED_PROFILES_FILTERED.STORY_ID STORY_ID, 
        EVENT_NAME, 
        SOURCE, 
        CONTAINER_ID, 
        CONTAINER_NAME, 
        CONTAINER_URL, 
        ITEM_NAME, 
        ITEM_URL, 
        ITEM_ID, 
        ITEM_CORRELATION_ID, 
        CREATION_DATE, 
        BRIEF_DESC, 
        ACTOR_UUID, 
        EVENT_RECORD_UUID, 
        TAGS, 
        META_TEMPLATE, 
        R_META_TEMPLATE, 
        R_TEXT_META_TEMPLATE, 
        N_COMMENTS, 
        N_RECOMMANDATIONS 
    FROM    ( 
            SELECT STORY_ID, MAX(FOLLOWED_STORY_ID) A_FOLLOWED_STORY_ID 
            FROM HOMEPAGE.TMP_READED_PROFILES_FILTERED TMP_READED_PROFILES_FILTERED 
            GROUP by TMP_READED_PROFILES_FILTERED.STORY_ID 
            )   TEMP_A, 
            HOMEPAGE.TMP_READED_PROFILES_FILTERED TMP_READED_PROFILES_FILTERED 
    WHERE   TEMP_A.A_FOLLOWED_STORY_ID = TMP_READED_PROFILES_FILTERED.FOLLOWED_STORY_ID AND 
            TMP_READED_PROFILES_FILTERED.STORY_ID NOT in ( 
                                                    SELECT  NR_STORIES.STORY_ID STORY_ID 
                                                    FROM    HOMEPAGE.NR_STORIES NR_STORIES, 
                                                            HOMEPAGE.TMP_READED_PROFILES_FILTERED TMP_READED_PROFILES_FILTERED 
                                                    WHERE   TMP_READED_PROFILES_FILTERED.STORY_ID = TMP_READED_PROFILES_FILTERED.STORY_ID 
                                                ) 
); 

---- NR_STORIES insert stories releted to the tags 
--INSERT INTO HOMEPAGE.NR_STORIES ( 
--    STORY_ID, 
--    EVENT_NAME, 
--    SOURCE, 
--    CONTAINER_ID, 
--    CONTAINER_NAME, 
--    CONTAINER_URL, 
--    ITEM_NAME, 
--    ITEM_URL, 
--    ITEM_ID, 
--    ITEM_CORRELATION_ID, 
--    CREATION_DATE, 
--    BRIEF_DESC, 
--    ACTOR_UUID, 
--    EVENT_RECORD_UUID, 
--    TAGS, 
--    META_TEMPLATE, 
--    R_META_TEMPLATE, 
--    R_TEXT_META_TEMPLATE, 
--    N_COMMENTS, 
--    N_RECOMMANDATIONS 
--) 
--SELECT  * 
--FROM HOMEPAGE.TMP_READED_STORIES_PROFILES; 
--COMMIT; 
--
---- NR_FOLLOWED_STORIES insert readers for profiles 
--INSERT INTO HOMEPAGE.NR_FOLLOWED_STORIES ( 
--    FOLLOWED_STORY_ID, 
--    READER_ID, 
--    CATEGORY_TYPE, 
--    SOURCE, 
--    CONTAINER_ID, 
--    ITEM_ID, 
--    RESOURCE_TYPE, 
--    CREATION_DATE, 
--    STORY_ID 
--) 
--SELECT      FOLLOWED_STORY_ID, 
--            READER_ID, 
--            CATEGORY_TYPE, 
--            SOURCE, 
--            CONTAINER_ID, 
--            ITEM_ID, 
--            RESOURCE_TYPE, 
--            CREATION_DATE, 
--            STORY_ID 
--FROM HOMEPAGE.TMP_READED_PROFILES_FILTERED;
COMMIT; 

DROP VIEW HOMEPAGE.TMP_FOLLOWED_PROFILES; 
DROP VIEW HOMEPAGE.TMP_READED_PROFILES; 
DROP VIEW HOMEPAGE.TMP_READED_PROFILES_FILTERED; 
DROP VIEW HOMEPAGE.TMP_READED_STORIES_PROFILES; 
COMMIT; 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 
-- ***************** 3) END: MIGRATION FOR FOLLOWED PEOPLE *********************** 
---------------------------------------------------------------------------------- 
---------------------------------------------------------------------------------- 

-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-- SPR #DMCE85VK79 Homepage: Upgrade/Migration 2.5.0.2 to 3.0 Beta1 - Person watchlisted in 2.5 display Follow link in Profiles 3.0 
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------
-----------------------------------------------------------------------------------------------

--------------------------------------------------
-- 1) START WATCHLIST CASE (PERSON)
--------------------------------------------------

------------------------------------------------------------------------
-- A) WATCHLIST CASE (PERSON): ADDING SOURCES WITH NO STORIES LINKED
------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_RESOURCE (
    RESOURCE_ID,
    SOURCE,
    CONTAINER_ID,
    CONTAINER_NAME,
    CONTAINER_URL,
    CATEGORY_TYPE,
    RESOURCE_TYPE,
    LAST_UPDATE
)
    SELECT  DISTINCT NR_SOURCE.SOURCE_ID         RESOURCE_ID,
            NR_SOURCE.SOURCE            SOURCE,
            NR_SOURCE.CONTAINER_ID      CONTAINER_ID,
            NR_SOURCE.CONTAINER_NAME    CONTAINER_NAME,
            NR_SOURCE.CONTAINER_URL     CONTAINER_URL,
            2                           CATEGORY_TYPE,
            10                          RESOURCE_TYPE,
            NR_SOURCE.LAST_UPDATE       LAST_UPDATE
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            HOMEPAGE.NR_SOURCE NR_SOURCE
    WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID AND
            NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
            NR_SOURCE.SOURCE = 'profiles' AND 
            NR_SOURCE.SOURCE_ID NOT IN      (   SELECT  NR_RESOURCE.RESOURCE_ID
                                                FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE 
                                            )
                                         	AND
			NR_SOURCE.CONTAINER_ID NOT IN 	(   SELECT  NR_RESOURCE.CONTAINER_ID
												FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE
											);  
COMMIT;

------------------------------------------------------------------------
-- B) WATCHLIST CASE (PERSON):  CREATE A VIEW FOR WATCHLIST STORIES WHERE WE USE IS_ACTIVE = 1
------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID FOLLOWED_CONTAINER 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE, HOMEPAGE.PERSON PERSON 
            WHERE SOURCE = 'profiles' AND PERSON.PERSON_ID = NR_SOURCE.CONTAINER_ID 
            ) TEMP 
    WHERE   IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
);
COMMIT;

------------------------------------------------------------------------
-- C) WATCHLIST CASE (PERSON):  INSERTING FOLLOWING RELETIONSHIP FOR PERSON
------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_FOLLOWS ( 
    FOLLOW_ID, 
    PERSON_ID, 
    RESOURCE_ID 
) 
SELECT  (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) FOLLOWS_ID, PERSON_ID, RESOURCE_ID 
FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE, HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS 
WHERE   NR_RESOURCE.CONTAINER_ID = TMP_FOLLOWS.FOLLOWED_CONTAINER AND
        (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) NOT IN (
                                                                        SELECT FOLLOW_ID
                                                                        FROM HOMEPAGE.NR_FOLLOWS
                                                                    );
COMMIT;

DROP VIEW HOMEPAGE.TMP_FOLLOWS;

COMMIT;
--------------------------------------------------
-- 1) END WATCHLIST CASE (PERSON)
--------------------------------------------------

--------------------------------------------------
-- 2) START WATCHLIST CASE (TAGS)
--------------------------------------------------

------------------------------------------------------------------------
-- A) WATCHLIST CASE (TAGS): ADDING SOURCES WITH NO STORIES LINKED
------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_RESOURCE (
    RESOURCE_ID,
    SOURCE,
    CONTAINER_ID,
    CONTAINER_NAME,
    CONTAINER_URL,
    CATEGORY_TYPE,
    RESOURCE_TYPE,
    LAST_UPDATE
)
    SELECT  DISTINCT NR_SOURCE.SOURCE_ID         RESOURCE_ID,
            NR_SOURCE.SOURCE            SOURCE,
            NR_SOURCE.CONTAINER_ID      CONTAINER_ID,
            NR_SOURCE.CONTAINER_NAME    CONTAINER_NAME,
            NR_SOURCE.CONTAINER_URL     CONTAINER_URL,
            10                          CATEGORY_TYPE,
            13                          RESOURCE_TYPE,
            NR_SOURCE.LAST_UPDATE       LAST_UPDATE
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            HOMEPAGE.NR_SOURCE NR_SOURCE
    WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID AND
            NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
            NR_SOURCE.SOURCE = 'tag' AND 
            NR_SOURCE.SOURCE_ID NOT IN      (   SELECT  NR_RESOURCE.RESOURCE_ID
                                                FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE 
                                            )
                                     	AND
			NR_SOURCE.CONTAINER_ID NOT IN 	(   SELECT  NR_RESOURCE.CONTAINER_ID
												FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE
											);                                            
COMMIT;

------------------------------------------------------------------------
-- B) WATCHLIST CASE (TAGS):  CREATE A VIEW FOR WATCHLIST STORIES WHERE WE USE IS_ACTIVE = 1
------------------------------------------------------------------------
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID FOLLOWED_CONTAINER 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE
            WHERE SOURCE = 'tag'
            ) TEMP 
    WHERE   IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
);
COMMIT;

------------------------------------------------------------------------
-- C) WATCHLIST CASE (TAGS):  INSERTING FOLLOWING RELETIONSHIP FOR PERSON
------------------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_FOLLOWS ( 
    FOLLOW_ID, 
    PERSON_ID, 
    RESOURCE_ID 
) 
SELECT  (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) FOLLOWS_ID, PERSON_ID, RESOURCE_ID 
FROM    HOMEPAGE.NR_RESOURCE NR_RESOURCE, HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS 
WHERE   NR_RESOURCE.CONTAINER_ID = TMP_FOLLOWS.FOLLOWED_CONTAINER AND
        (SUBSTR(PERSON_ID,1,18) || SUBSTR(RESOURCE_ID,1,18)) NOT IN (
                                                                        SELECT FOLLOW_ID
                                                                        FROM HOMEPAGE.NR_FOLLOWS
                                                                    );
COMMIT;

DROP VIEW HOMEPAGE.TMP_FOLLOWS;

COMMIT;

UPDATE HOMEPAGE.NR_RESOURCE SET SOURCE='tags' WHERE SOURCE='tag'; 

COMMIT;
--------------------------------------------------
-- 2) END WATCHLIST CASE (TAGS)
--------------------------------------------------

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
-- START: CLEANUP DUPLICATED STORIES
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------

-- if there are stories that are or in network or in follow we need to normalize them, putting to 1,1 and deleteing the dups
UPDATE HOMEPAGE.NR_NEWS_STATUS_NETWORK SET IS_NETWORK_NEWS = 1, IS_FOLLOW_NEWS = 1 WHERE NEWS_STATUS_NETWORK_ID IN (
        SELECT  A.NEWS_STATUS_NETWORK_ID
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK A,
                HOMEPAGE.NR_NEWS_STATUS_NETWORK B
        WHERE   A.READER_ID = B.READER_ID AND A.ACTOR_UUID = B.ACTOR_UUID AND 
                A.IS_NETWORK_NEWS = 1 AND A.IS_FOLLOW_NEWS = 0 AND 
                B.IS_NETWORK_NEWS = 0 AND B.IS_FOLLOW_NEWS = 1 AND
                A.ITEM_ID = B.ITEM_ID
);

COMMIT;

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK WHERE NEWS_STATUS_NETWORK_ID IN (
    SELECT NEWS_STATUS_NETWORK_ID
    FROM (
        SELECT  A.NEWS_STATUS_NETWORK_ID, B.IS_NETWORK_NEWS, B.IS_FOLLOW_NEWS
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK A,
                HOMEPAGE.NR_NEWS_STATUS_NETWORK B
        WHERE   A.READER_ID = B.READER_ID AND A.ACTOR_UUID = B.ACTOR_UUID AND A.IS_NETWORK_NEWS = 1 AND A.IS_FOLLOW_NEWS = 1 AND 
                B.IS_NETWORK_NEWS = 0 AND B.IS_FOLLOW_NEWS = 1 AND
                A.ITEM_ID = B.ITEM_ID
    ) TEMP
    WHERE TEMP.IS_NETWORK_NEWS = 0 AND TEMP.IS_FOLLOW_NEWS = 1
);

COMMIT;

DELETE FROM HOMEPAGE.NR_NEWS_STATUS_NETWORK WHERE NEWS_STATUS_NETWORK_ID IN (
    SELECT NEWS_STATUS_NETWORK_ID
    FROM (
        SELECT  A.NEWS_STATUS_NETWORK_ID, B.IS_NETWORK_NEWS, B.IS_FOLLOW_NEWS
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK A,
                HOMEPAGE.NR_NEWS_STATUS_NETWORK B
        WHERE   A.READER_ID = B.READER_ID AND A.ACTOR_UUID = B.ACTOR_UUID AND A.IS_NETWORK_NEWS = 1 AND A.IS_FOLLOW_NEWS = 1 AND 
                B.IS_NETWORK_NEWS = 1 AND B.IS_FOLLOW_NEWS = 0 AND
                A.ITEM_ID = B.ITEM_ID
    ) TEMP
    WHERE TEMP.IS_NETWORK_NEWS = 1 AND TEMP.IS_FOLLOW_NEWS = 0
);

COMMIT;

--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
-- END: CLEANUP DUPLICATED STORIES
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------
