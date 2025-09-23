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

connect to HOMEPAGE;

-------------------------------------------------------------------------------
-- SPR #MCRS7S6LFF -  Incorrect unique index HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE in db scripts
-------------------------------------------------------------------------------
DROP INDEX  HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE;

CREATE UNIQUE INDEX HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE
	ON HOMEPAGE.NR_SUBSCRIPTION("PERSON_ID" ASC, "SOURCE_ID" ASC) TABLESPACE "NEWSINDEXTABSPACE";

-------------------------------------------------
-- SPR: #RHLI7S7BAZ - News Repository: News discovery need to be optimized, long response time for query 
-------------------------------------------------------------------------------
CREATE INDEX "HOMEPAGE"."NR_NEWS_RECORDS_IX1" 
	ON HOMEPAGE.NR_NEWS_RECORDS ("CREATION_DATE" DESC, "NEWS_RECORDS_ID", "READER_ID", "IS_CONTAINER", "IS_PUBLIC") TABLESPACE "NEWSINDEXTABSPACE";


CREATE TABLE HOMEPAGE.TMP (
    TMP_NEWS_RECORDS_ID VARCHAR2(36) NOT NULL,
    TMP_TAGS VARCHAR2(1024),
    TMP_META_TEMPLATE VARCHAR2(4000) DEFAULT '' NOT NULL,
    TMP_TEXT_META_TEMPLATE VARCHAR2(1024)
) 
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.TMP
    ADD (CONSTRAINT "PK_TMP" PRIMARY KEY ("TMP_NEWS_RECORDS_ID")
    USING INDEX TABLESPACE "NEWSINDEXTABSPACE");    

-- DATA BACKUP
INSERT INTO HOMEPAGE.TMP
(	SELECT NR_NEWS_RECORDS.NEWS_RECORDS_ID, NR_NEWS_RECORDS.TAGS, NR_NEWS_RECORDS.META_TEMPLATE, NR_NEWS_RECORDS.TEXT_META_TEMPLATE 
	FROM HOMEPAGE.NR_NEWS_RECORDS NR_NEWS_RECORDS
);

COMMIT;

-- REMOVE AND ADD THE OLD COLUMN WITH THE VARCHAR DATATYPE
ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
DROP COLUMN TAGS;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ADD TAGS VARCHAR2(1024);

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
DROP COLUMN META_TEMPLATE;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ADD META_TEMPLATE VARCHAR2(4000) DEFAULT '' NOT NULL;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
DROP COLUMN TEXT_META_TEMPLATE;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ADD TEXT_META_TEMPLATE VARCHAR2(1024);

UPDATE HOMEPAGE.NR_NEWS_RECORDS 
SET TAGS = (    SELECT TMP.TMP_TAGS FROM HOMEPAGE.TMP TMP
                WHERE TMP.TMP_NEWS_RECORDS_ID = HOMEPAGE.NR_NEWS_RECORDS.NEWS_RECORDS_ID
           );

COMMIT;           

UPDATE HOMEPAGE.NR_NEWS_RECORDS 
SET META_TEMPLATE = (    SELECT TMP.TMP_META_TEMPLATE FROM HOMEPAGE.TMP TMP
                WHERE TMP.TMP_NEWS_RECORDS_ID = HOMEPAGE.NR_NEWS_RECORDS.NEWS_RECORDS_ID
           );

COMMIT;

UPDATE HOMEPAGE.NR_NEWS_RECORDS 
SET TEXT_META_TEMPLATE = (    SELECT TMP.TMP_TEXT_META_TEMPLATE FROM HOMEPAGE.TMP TMP
                WHERE TMP.TMP_NEWS_RECORDS_ID = HOMEPAGE.NR_NEWS_RECORDS.NEWS_RECORDS_ID
           );

COMMIT;

DROP TABLE HOMEPAGE.TMP;

-------------------------------------------------------------------------------
-- Updating the schema version from 16 to 17
-------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 17
WHERE   DBSCHEMAVER = 16;



COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
