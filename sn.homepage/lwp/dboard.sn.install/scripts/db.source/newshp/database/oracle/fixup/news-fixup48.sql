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

------------------------------------------------------------------------------------
-- 1) ENFORCE A UNIQUE FIELD ON READER_ID, STORY_ID TO AVOID THE USE OF DISTINCT
------------------------------------------------------------------------------------
-- Just to check dups records
-- SELECT COMM_PER_READER_ID, STORY_ID, COUNT(*)
-- FROM  HOMEPAGE.NR_COMM_PERSON_STORIES NR_COMM_PERSON_STORIES
-- GROUP BY COMM_PER_READER_ID, STORY_ID
-- HAVING count(*) > 1

-- CREATE A TEMP DUP TABLE
CREATE TABLE HOMEPAGE.TMP_COMM_PERSON_STORIES (
	COMM_PER_STORY_ID VARCHAR2(36) NOT NULL,
	COMM_PER_READER_ID VARCHAR2(36) NOT NULL,
	CONTAINER_ID VARCHAR2(256),
	ITEM_ID VARCHAR2(36),
	RESOURCE_TYPE NUMBER(5,0) NOT NULL,
	CATEGORY_TYPE NUMBER(5,0) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	SOURCE VARCHAR2(36) NOT NULL,
	STORY_ID VARCHAR2(36) NOT NULL
)
TABLESPACE "NEWSREGTABSPACE";

COMMIT;

-- INSERT A SIGNLE DUPLICATED RECORD TO SAVE
INSERT INTO HOMEPAGE.TMP_COMM_PERSON_STORIES (
    COMM_PER_STORY_ID, 
    COMM_PER_READER_ID, 
    CONTAINER_ID, ITEM_ID, 
    RESOURCE_TYPE, 
    CATEGORY_TYPE, 
    CREATION_DATE, 
    SOURCE, 
    STORY_ID
    )
    -- DUPS RECORDS TO SAVE
    SELECT  T2.COMM_PER_STORY_ID, 
            T2.COMM_PER_READER_ID, 
            T2.CONTAINER_ID, 
            T2.ITEM_ID, 
            T2.RESOURCE_TYPE, 
            T2.CATEGORY_TYPE, 
            T2.CREATION_DATE, 
            T2.SOURCE, 
            T2.STORY_ID
    FROM (
            SELECT  COMM_PER_READER_ID, STORY_ID, MAX(COMM_PER_STORY_ID) COMM_PER_STORY_ID
            FROM    HOMEPAGE.NR_COMM_PERSON_STORIES NR_COMM_PERSON_STORIES
            GROUP BY COMM_PER_READER_ID, STORY_ID
            HAVING COUNT(*) > 1
        ) T1, HOMEPAGE.NR_COMM_PERSON_STORIES T2
    WHERE T1.COMM_PER_STORY_ID = T2.COMM_PER_STORY_ID;

COMMIT;

-- REMOVE ALL THE DUPS RECORDS FROM THE ORIGINAL TABLE
DELETE FROM HOMEPAGE.NR_COMM_PERSON_STORIES
    WHERE COMM_PER_STORY_ID IN (
    SELECT  NR_COMM_PERSON_STORIES.COMM_PER_STORY_ID
    FROM    HOMEPAGE.NR_COMM_PERSON_STORIES NR_COMM_PERSON_STORIES,  HOMEPAGE.TMP_COMM_PERSON_STORIES TMP_COMM_PERSON_STORIES
    WHERE   NR_COMM_PERSON_STORIES.COMM_PER_READER_ID = TMP_COMM_PERSON_STORIES.COMM_PER_READER_ID AND
            NR_COMM_PERSON_STORIES.STORY_ID = TMP_COMM_PERSON_STORIES.STORY_ID
);

COMMIT;

-- CREATE THE UNIQUE CONSTRAINTS
ALTER TABLE HOMEPAGE.NR_COMM_PERSON_STORIES
    ADD CONSTRAINT UNIQUE_COMM_PERSON UNIQUE (COMM_PER_READER_ID, STORY_ID);

COMMIT;

-- COPYING BACK THE SINGLE RECORDS
INSERT INTO HOMEPAGE.NR_COMM_PERSON_STORIES (
    COMM_PER_STORY_ID, 
    COMM_PER_READER_ID, 
    CONTAINER_ID, 
    ITEM_ID, 
    RESOURCE_TYPE, 
    CATEGORY_TYPE, 
    CREATION_DATE, 
    SOURCE, 
    STORY_ID
    )
    SELECT  COMM_PER_STORY_ID, 
            COMM_PER_READER_ID, 
            CONTAINER_ID, 
            ITEM_ID, 
            RESOURCE_TYPE, 
            CATEGORY_TYPE, 
            CREATION_DATE, 
            SOURCE, 
            STORY_ID
    FROM    HOMEPAGE.TMP_COMM_PERSON_STORIES;

COMMIT;

-- REMOVE THE TEMP TABLE
DROP TABLE HOMEPAGE.TMP_COMM_PERSON_STORIES;

COMMIT;

-- reorg table HOMEPAGE.NR_COMM_PERSON_STORIES use NEWS4TMPTABSPACE;

-------------------------------------------------
--2) NR_NEWS_SAVED IS_COMMUNITY_STORY
-------------------------------------------------
ALTER TABLE HOMEPAGE.NR_NEWS_SAVED
    ADD IS_COMMUNITY_STORY NUMBER(5,0) DEFAULT 0;

-- reorg table HOMEPAGE.NR_NEWS_SAVED use NEWSTMPTABSPACE;    

CREATE INDEX HOMEPAGE.NR_NEWS_SAVED_CREAT_IS_COM
    ON HOMEPAGE.NR_NEWS_SAVED (CREATION_DATE DESC, READER_ID, IS_COMMUNITY_STORY) TABLESPACE "NEWSINDEXTABSPACE";

UPDATE HOMEPAGE.NR_NEWS_SAVED SET IS_COMMUNITY_STORY = 0;

UPDATE HOMEPAGE.NR_NEWS_SAVED SET IS_COMMUNITY_STORY = 1 WHERE SOURCE = 'communities';

-------------------------------------------------
--3) NR_STORIES IS_COMMUNITY_STORY
-------------------------------------------------
ALTER TABLE HOMEPAGE.NR_STORIES
    ADD IS_COMMUNITY_STORY NUMBER(5,0) DEFAULT 0;

-- reorg table HOMEPAGE.NR_STORIES use NEWS32TMPTABSPACE;

CREATE INDEX HOMEPAGE.NR_STORIES_CREAT_IS_COM
    ON HOMEPAGE.NR_STORIES (CREATION_DATE DESC, IS_COMMUNITY_STORY) TABLESPACE "NEWSINDEXTABSPACE";    

UPDATE HOMEPAGE.NR_STORIES SET IS_COMMUNITY_STORY = 0;

UPDATE HOMEPAGE.NR_STORIES SET IS_COMMUNITY_STORY = 1 WHERE SOURCE = 'communities';

--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
-- SPR #LNOO88DP2R Move outside java migration utility the creation of view. 
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------

----------------------------------------------------------------------
-- CLEANUP DATABASE FROM AN OLD MIGRATION
----------------------------------------------------------------------
-- ALL the migrated stories have the story id = to the event record uuid

-- delete saved migrated
DELETE FROM HOMEPAGE.NR_NEWS_SAVED WHERE NEWS_STORY_ID IN ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES  WHERE STORY_ID = EVENT_RECORD_UUID );

COMMIT;

-- delete discovery
DELETE FROM HOMEPAGE.NR_NEWS_DISCOVERY WHERE NEWS_STORY_ID IN ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES  WHERE STORY_ID = EVENT_RECORD_UUID );

COMMIT;

-- delete community person stories
DELETE FROM HOMEPAGE.NR_COMM_PERSON_STORIES WHERE STORY_ID IN ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES  WHERE STORY_ID = EVENT_RECORD_UUID );

COMMIT;

-- delete profiles and tags stories
DELETE FROM HOMEPAGE.NR_FOLLOWED_STORIES WHERE STORY_ID IN ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES WHERE STORY_ID = EVENT_RECORD_UUID );

COMMIT;

-- delete stories
DELETE FROM HOMEPAGE.NR_STORIES WHERE STORY_ID = EVENT_RECORD_UUID;

COMMIT;
----------------------------------------------------------------
----------------------------------------------------------------
----------------------------------------------------------------

----------------------------------------------------------------------
-- PERFORM A MIGRATION DATABASE FROM AN OLD MIGRATION
----------------------------------------------------------------------

----------------------------------------------------
-- NR_NEWS_SAVED
----------------------------------------------------
INSERT INTO HOMEPAGE.NR_NEWS_SAVED ( 
    NEWS_RECORDS_ID, 
    EVENT_NAME, 
    READER_ID, 
    SOURCE, 
    CONTAINER_ID, 
    CONTAINER_NAME, 
    CONTAINER_URL, 
    ENTRY_NAME, 
    ENTRY_URL, 
    ENTRY_ATOM_URL, 
    CREATION_DATE, 
    BRIEF_DESC, 
    IS_BRIEF_DESC_RTL, 
    ACTOR_UUID, 
    EVENT_RECORD_UUID, 
    TAGS, 
    META_TEMPLATE, 
    TEXT_META_TEMPLATE, 
    IS_CONTAINER, 
    ITEM_ID, 
    ITEM_CORRELATION_ID, 
    N_COMMENTS, 
    N_RECOMMANDATIONS, 
    GROUP_TYPE, 
    NEWS_STORY_ID 
) 
    SELECT 
        NEWS_RECORDS_ID, 
        EVENT_NAME, 
        READER_ID, 
        SOURCE, 
        CONTAINER_ID, 
        CONTAINER_NAME, 
        CONTAINER_URL, 
        ENTRY_NAME, 
        ENTRY_URL, 
        ENTRY_ATOM_URL, 
        CREATION_DATE, 
        BRIEF_DESC, 
        IS_BRIEF_DESC_RTL, 
        ACTOR_UUID, 
        EVENT_RECORD_UUID, 
        TAGS, 
        META_TEMPLATE, 
        TEXT_META_TEMPLATE, 
        IS_CONTAINER, 
        ITEM_ID, 
        ITEM_CORRELATION_ID, 
        0, 
        0, 
        0, 
        EVENT_RECORD_UUID 
    FROM HOMEPAGE.NR_NEWS_RECORDS 
    WHERE IS_SAVED = 1;

COMMIT;
----------------------------------------------------------------
----------------------------------------------------------------
----------------------------------------------------------------

------------------------------------------------------------
-- NR_NEWS_DISCOVERY
------------------------------------------------------------
INSERT INTO HOMEPAGE.NR_NEWS_DISCOVERY 
( 
            NEWS_RECORDS_ID, 
            EVENT_NAME, 
            SOURCE, 
            CONTAINER_ID, 
            CONTAINER_NAME, 
            CONTAINER_URL, 
            ENTRY_NAME, 
            ENTRY_URL, 
            ENTRY_ATOM_URL, 
            CREATION_DATE, 
            BRIEF_DESC, 
            IS_BRIEF_DESC_RTL, 
            ACTOR_UUID, 
            EVENT_RECORD_UUID, 
            TAGS, 
            META_TEMPLATE, 
            TEXT_META_TEMPLATE, 
            ITEM_ID, 
            ITEM_CORRELATION_ID, 
            N_COMMENTS, 
            N_RECOMMANDATIONS, 
            GROUP_TYPE, 
            NEWS_STORY_ID, 
            IS_COMMUNITY_STORY 
) 
    SELECT 
            NEWS_RECORDS_ID, 
            EVENT_NAME, 
            SOURCE, 
            CONTAINER_ID, 
            CONTAINER_NAME, 
            CONTAINER_URL, 
            ENTRY_NAME, 
            ENTRY_URL, 
            ENTRY_ATOM_URL, 
            CREATION_DATE, 
            BRIEF_DESC, 
            IS_BRIEF_DESC_RTL, 
            ACTOR_UUID, 
            EVENT_RECORD_UUID, 
            TAGS, 
            META_TEMPLATE, 
            TEXT_META_TEMPLATE, 
            ITEM_ID, 
            ITEM_CORRELATION_ID, 
            0, 
            0, 
            0, 
            EVENT_RECORD_UUID, 
            0 
    FROM    HOMEPAGE.NR_NEWS_RECORDS 
    WHERE   HOMEPAGE.NR_NEWS_RECORDS.IS_PUBLIC = 1 AND READER_ID IS NULL AND IS_CONTAINER = 0;

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_DISCOVERY 
SET CONTAINER_URL = '{communities}' || SUBSTR(CONTAINER_URL,INSTR(CONTAINER_URL,'/service/html'))
WHERE EVENT_NAME = 'files.file.created' AND CONTAINER_URL LIKE 'http://%';

COMMIT;

----------------------------------------------------------------
----------------------------------------------------------------
----------------------------------------------------------------

--------------------------------
-- NR_TAGS_STORIES
--------------------------------
-- a) create TMP_FOLLOWS
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

-- b) create TMP_FOLLOWED_STORIES
CREATE VIEW HOMEPAGE.TMP_FOLLOWED_STORIES AS ( 
    SELECT  NR_NEWS_RECORDS.EVENT_RECORD_UUID   STORY_ID, 
            NR_NEWS_RECORDS.EVENT_NAME          EVENT_NAME, 
            NR_NEWS_RECORDS.SOURCE              SOURCE, 
            NR_NEWS_RECORDS.CONTAINER_ID        CONTAINER_ID, 
            NR_NEWS_RECORDS.CONTAINER_NAME      CONTAINER_NAME, 
            NR_NEWS_RECORDS.CONTAINER_URL       CONTAINER_URL, 
            ''                                  ITEM_NAME, 
            ''                                  ITEM_URL, 
            ''                                  ITEM_ATOM_URL, 
            ''                                  ITEM_ID, 
            ''                                  ITEM_CORRELATION_ID, 
            NR_NEWS_RECORDS.CREATION_DATE       CREATION_DATE, 
            NR_NEWS_RECORDS.BRIEF_DESC	        BRIEF_DESC, 
            NR_NEWS_RECORDS.ACTOR_UUID          ACTOR_UUID, 
            NR_NEWS_RECORDS.EVENT_RECORD_UUID   EVENT_RECORD_UUID, 
            NR_NEWS_RECORDS.TAGS                TAGS, 
            NR_NEWS_RECORDS.META_TEMPLATE       META_TEMPLATE, 
            NR_NEWS_RECORDS.TEXT_META_TEMPLATE  TEXT_META_TEMPLATE, 
            ''                                  R_META_TEMPLATE, 
            ''                                  R_TEXT_META_TEMPLATE, 
            0                                   N_COMMENTS, 
            0                                   N_RECOMMANDATIONS, 
            0                                   IS_COMMUNITY_STORY 
    FROM ( 
            SELECT  EVENT_RECORD_UUID               STORY_ID, 
                    EVENT_NAME                      EVENT_NAME, 
                    MAX(NEWS_RECORDS_ID)            NEWS_RECORDS_ID 
            FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, 
                    ( 
                        SELECT DISTINCT (PERSON_ID) PERSON_ID, FOLLOWED_CONTAINER 
                        FROM HOMEPAGE.TMP_FOLLOWS 
                    ) FOLLOWERS 
            WHERE   NR_NEWS_RECORDS.IS_CONTAINER = 1 AND 
                    NR_NEWS_RECORDS.SOURCE LIKE 'tag.%' AND 
                    NR_NEWS_RECORDS.CONTAINER_ID = FOLLOWERS.FOLLOWED_CONTAINER AND 
                    NR_NEWS_RECORDS.READER_ID IS NULL 
            GROUP BY EVENT_RECORD_UUID, EVENT_NAME 
        ) T2, HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
    WHERE T2.NEWS_RECORDS_ID = NR_NEWS_RECORDS.NEWS_RECORDS_ID 
);

COMMIT;

-- c) copy stories
INSERT INTO HOMEPAGE.NR_STORIES ( 
    STORY_ID, 
    EVENT_NAME, 
    SOURCE, 
    CONTAINER_ID, 
    CONTAINER_NAME, 
    CONTAINER_URL, 
    ITEM_NAME, 
    ITEM_ATOM_URL, 
    ITEM_ID, 
    ITEM_CORRELATION_ID, 
    CREATION_DATE, 
    BRIEF_DESC, 
    ACTOR_UUID, 
    EVENT_RECORD_UUID, 
    TAGS, 
    META_TEMPLATE, 
    TEXT_META_TEMPLATE, 
    R_TEXT_META_TEMPLATE, 
    N_COMMENTS, 
    N_RECOMMANDATIONS, 
    IS_COMMUNITY_STORY 
) 
    SELECT  STORY_ID, 
            EVENT_NAME, 
            SOURCE, 
            CONTAINER_ID, 
            CONTAINER_NAME, 
            CONTAINER_URL, 
            ITEM_NAME, 
            ITEM_ATOM_URL, 
            ITEM_ID, 
            ITEM_CORRELATION_ID, 
            CREATION_DATE, 
            BRIEF_DESC, 
            ACTOR_UUID, 
            EVENT_RECORD_UUID, 
            TAGS, 
            META_TEMPLATE, 
            TEXT_META_TEMPLATE, 
            R_TEXT_META_TEMPLATE, 
            N_COMMENTS, 
            N_RECOMMANDATIONS, 
            IS_COMMUNITY_STORY 
    FROM HOMEPAGE.TMP_FOLLOWED_STORIES;

COMMIT;

-- d) copy tags stories
INSERT INTO HOMEPAGE.NR_FOLLOWED_STORIES ( 
    FOLLOWED_STORY_ID, 
    READER_ID, 
    CATEGORY_TYPE, 
    SOURCE, 
    CONTAINER_ID, 
    ITEM_ID, 
    RESOURCE_TYPE, 
    CREATION_DATE, 
    STORY_ID 
) 
SELECT (SUBSTR(TMP_FOLLOWED_STORIES.STORY_ID,1,18) || SUBSTR(TMP_FOLLOWS.PERSON_ID,1,18))        FOLLOWED_STORY_ID, 
        TMP_FOLLOWS.PERSON_ID                       READER_ID, 
        10                                          CATEGORY_TYPE, 
        TMP_FOLLOWED_STORIES.SOURCE                 SOURCE, 
        TMP_FOLLOWED_STORIES.CONTAINER_ID           CONTAINER_ID, 
        TMP_FOLLOWED_STORIES.ITEM_ID                ITEM_ID, 
        13                                          RESOURCE_TYPE, 
        TMP_FOLLOWED_STORIES.CREATION_DATE          CREATION_DATE, 
        TMP_FOLLOWED_STORIES.STORY_ID               STORY_ID 
FROM    HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS, HOMEPAGE.TMP_FOLLOWED_STORIES TMP_FOLLOWED_STORIES 
WHERE   TMP_FOLLOWS.FOLLOWED_CONTAINER = TMP_FOLLOWED_STORIES.CONTAINER_ID;

COMMIT;

-- e) SANITIZE
DELETE FROM HOMEPAGE.NR_STORIES WHERE TEXT_META_TEMPLATE = '' OR TEXT_META_TEMPLATE IS NULL OR TEXT_META_TEMPLATE = ' ';

COMMIT;

-- f) drop HOMEPAGE.TMP_FOLLOWED_STORIES
DROP VIEW HOMEPAGE.TMP_FOLLOWED_STORIES;

-- g) drop HOMEPAGE.TMP_FOLLOWS
DROP VIEW HOMEPAGE.TMP_FOLLOWS;

COMMIT;

----------------------------------------------------------------
----------------------------------------------------------------
----------------------------------------------------------------

--------------------------------
-- NR_PROFILES_STORIES
--------------------------------
-- a) create TMP_FOLLOWS
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID FOLLOWED_CONTAINER 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE 
            WHERE SOURCE = 'profiles' 
            ) TEMP 
    WHERE   IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
); 

-- b) create TMP_FOLLOWED_STORIES
CREATE VIEW HOMEPAGE.TMP_FOLLOWED_STORIES AS ( 
    SELECT  NR_NEWS_RECORDS.EVENT_RECORD_UUID   STORY_ID, 
            NR_NEWS_RECORDS.EVENT_NAME          EVENT_NAME, 
            NR_NEWS_RECORDS.SOURCE              SOURCE, 
            NR_NEWS_RECORDS.CONTAINER_ID        CONTAINER_ID, 
            NR_NEWS_RECORDS.CONTAINER_NAME      CONTAINER_NAME, 
            NR_NEWS_RECORDS.CONTAINER_URL       CONTAINER_URL, 
            ''                                  ITEM_NAME, 
            ''                                  ITEM_URL, 
            ''                                  ITEM_ATOM_URL, 
            ''                                  ITEM_ID, 
            ''                                  ITEM_CORRELATION_ID, 
            NR_NEWS_RECORDS.CREATION_DATE       CREATION_DATE, 
            NR_NEWS_RECORDS.BRIEF_DESC	        BRIEF_DESC, 
            NR_NEWS_RECORDS.ACTOR_UUID          ACTOR_UUID, 
            NR_NEWS_RECORDS.EVENT_RECORD_UUID   EVENT_RECORD_UUID, 
            NR_NEWS_RECORDS.TAGS                TAGS, 
            NR_NEWS_RECORDS.META_TEMPLATE       META_TEMPLATE, 
            NR_NEWS_RECORDS.TEXT_META_TEMPLATE  TEXT_META_TEMPLATE, 
            ''                                  R_META_TEMPLATE, 
            ''                                  R_TEXT_META_TEMPLATE, 
            0                                   N_COMMENTS, 
            0                                   N_RECOMMANDATIONS, 
            0                                   IS_COMMUNITY_STORY 
    FROM ( 
            SELECT  EVENT_RECORD_UUID               STORY_ID, 
                    MIN(NEWS_RECORDS_ID)            NEWS_RECORDS_ID 
            FROM    HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS, 
                    ( 
                        SELECT DISTINCT (PERSON_ID) PERSON_ID, FOLLOWED_CONTAINER 
                        FROM HOMEPAGE.TMP_FOLLOWS 
                    ) FOLLOWERS 
            WHERE   (NR_NEWS_RECORDS.ACTOR_UUID = FOLLOWERS.FOLLOWED_CONTAINER AND NR_NEWS_RECORDS.IS_PUBLIC = 1 ) OR 
                    (NR_NEWS_RECORDS.ACTOR_UUID = FOLLOWERS.FOLLOWED_CONTAINER AND NR_NEWS_RECORDS.SOURCE = 'profiles') 
            GROUP BY EVENT_RECORD_UUID 
        ) T2, HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS 
    WHERE T2.NEWS_RECORDS_ID = NR_NEWS_RECORDS.NEWS_RECORDS_ID 
); 

COMMIT;

-- c) copy stories
INSERT INTO HOMEPAGE.NR_STORIES ( 
    STORY_ID, 
    EVENT_NAME, 
    SOURCE, 
    CONTAINER_ID, 
    CONTAINER_NAME, 
    CONTAINER_URL, 
    ITEM_NAME, 
    ITEM_ATOM_URL, 
    ITEM_ID, 
    ITEM_CORRELATION_ID, 
    CREATION_DATE, 
    BRIEF_DESC, 
    ACTOR_UUID, 
    EVENT_RECORD_UUID, 
    TAGS, 
    META_TEMPLATE, 
    TEXT_META_TEMPLATE, 
    R_TEXT_META_TEMPLATE, 
    N_COMMENTS, 
    N_RECOMMANDATIONS, 
    IS_COMMUNITY_STORY 
) 
    SELECT  STORY_ID, 
            EVENT_NAME, 
            SOURCE, 
            CONTAINER_ID, 
            CONTAINER_NAME, 
            CONTAINER_URL, 
            ITEM_NAME, 
            ITEM_ATOM_URL, 
            ITEM_ID, 
            ITEM_CORRELATION_ID, 
            CREATION_DATE, 
            BRIEF_DESC, 
            ACTOR_UUID, 
            EVENT_RECORD_UUID, 
            TAGS, 
            META_TEMPLATE, 
            TEXT_META_TEMPLATE, 
            R_TEXT_META_TEMPLATE, 
            N_COMMENTS, 
            N_RECOMMANDATIONS, 
            IS_COMMUNITY_STORY 
    FROM HOMEPAGE.TMP_FOLLOWED_STORIES WHERE STORY_ID NOT IN ( SELECT STORY_ID FROM HOMEPAGE.NR_STORIES);
		
COMMIT;

-- d) copy profiles stories
INSERT INTO HOMEPAGE.NR_FOLLOWED_STORIES ( 
FOLLOWED_STORY_ID, 
READER_ID, 
CATEGORY_TYPE, 
SOURCE, 
CONTAINER_ID, 
ITEM_ID, 
RESOURCE_TYPE, 
CREATION_DATE, 
STORY_ID 
) 
    SELECT SUBSTR(TMP_FOLLOWS.PERSON_ID,1,18) || SUBSTR(TMP_FOLLOWED_STORIES.STORY_ID,1,18)       FOLLOWED_STORY_ID, 
            TMP_FOLLOWS.PERSON_ID                       READER_ID, 
            2                                           CATEGORY_TYPE, 
            TMP_FOLLOWED_STORIES.SOURCE                 SOURCE, 
            TMP_FOLLOWED_STORIES.CONTAINER_ID           CONTAINER_ID, 
            TMP_FOLLOWED_STORIES.ITEM_ID                ITEM_ID, 
            10                                          RESOURCE_TYPE, 
            TMP_FOLLOWED_STORIES.CREATION_DATE          CREATION_DATE, 
            TMP_FOLLOWED_STORIES.STORY_ID               STORY_ID 
    FROM    HOMEPAGE.TMP_FOLLOWS TMP_FOLLOWS, HOMEPAGE.TMP_FOLLOWED_STORIES TMP_FOLLOWED_STORIES 
    WHERE   TMP_FOLLOWS.FOLLOWED_CONTAINER = TMP_FOLLOWED_STORIES.ACTOR_UUID; 

COMMIT;

-- e) SANITIZE
DELETE FROM HOMEPAGE.NR_STORIES WHERE TEXT_META_TEMPLATE = '' OR TEXT_META_TEMPLATE IS NULL OR TEXT_META_TEMPLATE = ' ';

COMMIT;

-- f) drop HOMEPAGE.TMP_FOLLOWED_STORIES
DROP VIEW HOMEPAGE.TMP_FOLLOWED_STORIES;

-- g) drop HOMEPAGE.TMP_FOLLOWS
DROP VIEW HOMEPAGE.TMP_FOLLOWS;

COMMIT;

----------------------------------------------------------------
----------------------------------------------------------------
----------------------------------------------------------------

--------------------------------
-- NR_COMM_PERSON_STORIES
--------------------------------

-- a) create tmp_follows view
CREATE VIEW HOMEPAGE.TMP_FOLLOWS AS ( 
    SELECT  NR_SUBSCRIPTION.PERSON_ID PERSON_ID, CONTAINER_ID FOLLOWED_CONTAINER 
    FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
            ( 
            SELECT NR_SOURCE.CONTAINER_ID, NR_SOURCE.SOURCE_ID 
            FROM HOMEPAGE.NR_SOURCE NR_SOURCE 
            WHERE SOURCE = 'tag' or  SOURCE = 'profiles' 
            ) TEMP 
    WHERE   IS_ACTIVE = 1 AND NR_SUBSCRIPTION.SOURCE_ID = TEMP.SOURCE_ID 
);

-- b) create tmp_followed_stories
CREATE VIEW HOMEPAGE.TMP_FOLLOWED_STORIES AS ( 
    SELECT  NR_STORIES.STORY_ID         STORY_ID, 
            T3.COMM_PER_READER_ID       COMM_PER_READER_ID, 
            T3.RESOURCE_TYPE            RESOURCE_TYPE, 
            NR_STORIES.CONTAINER_ID     CONTAINER_ID, 
            NR_STORIES.ITEM_ID          ITEM_ID, 
            NR_STORIES.CREATION_DATE    CREATION_DATE, 
            NR_STORIES.SOURCE           SOURCE 
    FROM HOMEPAGE.NR_STORIES NR_STORIES, ( 
        SELECT  T2.STORY_ID                 STORY_ID, 
                T2.READER_ID                COMM_PER_READER_ID, 
                MAX(T2.RESOURCE_TYPE)       RESOURCE_TYPE 
        FROM ( 
                SELECT  READER_ID,  MAX(STORY_ID) STORY_ID 
                FROM    HOMEPAGE.NR_FOLLOWED_STORIES NR_FOLLOWED_STORIES 
                WHERE   (NR_FOLLOWED_STORIES.CATEGORY_TYPE = 2 AND NR_FOLLOWED_STORIES.RESOURCE_TYPE = 10) OR 
                        (NR_FOLLOWED_STORIES.CATEGORY_TYPE = 10 AND NR_FOLLOWED_STORIES.RESOURCE_TYPE = 13) 
                GROUP BY READER_ID, STORY_ID 
            ) T1, HOMEPAGE.NR_FOLLOWED_STORIES T2 
        WHERE T1.STORY_ID = T2.STORY_ID 
        GROUP BY T2.STORY_ID, T2.READER_ID 
    ) T3 
    WHERE NR_STORIES.STORY_ID = T3.STORY_ID 
);

COMMIT;

INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW ( 
    COMM_PERSON_FOLLOW_ID, 
    PERSON_ID, 
    PERSON_COMMUNITY_ID 
) 
SELECT SUBSTR(STORY_ID,1,18)  || SUBSTR(A,1,18) STORY_ID, A PERSON_ID, B PERSON_COMMUNITY_ID 
FROM ( 
    SELECT MAX(STORY_ID) STORY_ID, COMM_PER_READER_ID A, COMM_PER_READER_ID B 
    FROM HOMEPAGE.TMP_FOLLOWED_STORIES 
    WHERE COMM_PER_READER_ID NOT IN ( 
        SELECT PERSON_ID 
        FROM HOMEPAGE.NR_COMM_PERSON_FOLLOW 
    	) 
    GROUP BY COMM_PER_READER_ID 
    )  T;

COMMIT;

INSERT INTO HOMEPAGE.NR_COMM_PERSON_STORIES ( 
    COMM_PER_STORY_ID, 
    COMM_PER_READER_ID, 
    CONTAINER_ID, 
    ITEM_ID, 
    RESOURCE_TYPE, 
    CATEGORY_TYPE, 
    CREATION_DATE, 
    SOURCE, 
    STORY_ID 
) 
    SELECT  SUBSTR(COMM_PER_READER_ID,1,18) || SUBSTR(STORY_ID,1,18)      COMM_PER_STORY_ID, 
            COMM_PER_READER_ID, 
            MAX (CONTAINER_ID) CONTAINER_ID, 
            ITEM_ID, 
            RESOURCE_TYPE, 
            2, 
            CREATION_DATE, 
            SOURCE, 
            STORY_ID 
    FROM HOMEPAGE.TMP_FOLLOWED_STORIES WHERE RESOURCE_TYPE = 10
    GROUp BY COMM_PER_READER_ID, CONTAINER_ID, ITEM_ID, RESOURCE_TYPE,  CREATION_DATE, SOURCE, STORY_ID;

COMMIT;

INSERT INTO HOMEPAGE.NR_COMM_PERSON_STORIES ( 
    COMM_PER_STORY_ID, 
    COMM_PER_READER_ID, 
    CONTAINER_ID, 
    ITEM_ID, 
    RESOURCE_TYPE, 
    CATEGORY_TYPE, 
    CREATION_DATE, 
    SOURCE, 
    STORY_ID 
) 
    SELECT  SUBSTR(COMM_PER_READER_ID,1,18) || SUBSTR(STORY_ID,1,18)       COMM_PER_STORY_ID, 
            COMM_PER_READER_ID, 
            CONTAINER_ID, 
            ITEM_ID, 
            RESOURCE_TYPE, 
            10, 
            CREATION_DATE, 
            SOURCE, 
            STORY_ID 
    FROM HOMEPAGE.TMP_FOLLOWED_STORIES WHERE RESOURCE_TYPE = 13;

COMMIT;

-- f) drop HOMEPAGE.TMP_FOLLOWED_STORIES
DROP VIEW HOMEPAGE.TMP_FOLLOWED_STORIES;

-- g) drop HOMEPAGE.TMP_FOLLOWS
DROP VIEW HOMEPAGE.TMP_FOLLOWS;

COMMIT;
