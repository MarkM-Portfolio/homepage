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
connect to HOMEPAGE;

-------------------------------------------------------------------------------
-- SPR #MCRS7S6LFF -  Incorrect unique index HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE in db scripts
-------------------------------------------------------------------------------
DROP INDEX  HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE;

CREATE UNIQUE INDEX HOMEPAGE.NR_SUBSCRIPTION_IX_UNIQUE
	ON HOMEPAGE.NR_SUBSCRIPTION("PERSON_ID" ASC, "SOURCE_ID" ASC);

-------------------------------------------------------------------------------
-- SPR: #RHLI7S7BAZ - News Repository: News discovery need to be optimized, long response time for query 
-------------------------------------------------------------------------------
CREATE INDEX HOMEPAGE.NR_NEWS_RECORDS_IX1 
	ON HOMEPAGE.NR_NEWS_RECORDS (CREATION_DATE DESC, NEWS_RECORDS_ID, READER_ID, IS_CONTAINER, IS_PUBLIC );
	
-------------------------------------------------------------------------------
-- CHANGE THE DATA TYPE
-------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.TMP (
    TMP_NEWS_RECORDS_ID VARCHAR(36) NOT NULL,
    TMP_TAGS VARCHAR(1024),
    TMP_META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL,
    TMP_TEXT_META_TEMPLATE VARCHAR(1024)
) 
IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.TMP
    ADD CONSTRAINT "PK_TMP" PRIMARY KEY("TMP_NEWS_RECORDS_ID");

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
ADD COLUMN TAGS VARCHAR(1024);

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
DROP COLUMN META_TEMPLATE;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ADD COLUMN META_TEMPLATE VARCHAR(4096) DEFAULT '' NOT NULL;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
DROP COLUMN TEXT_META_TEMPLATE;

ALTER TABLE HOMEPAGE.NR_NEWS_RECORDS
ADD COLUMN TEXT_META_TEMPLATE VARCHAR(1024);

-- RUNSTATS REORG
RUNSTATS ON TABLE HOMEPAGE.NR_NEWS_RECORDS;
RUNSTATS ON TABLE HOMEPAGE.TMP;
RUNSTATS ON TABLE HOMEPAGE.NR_NEWS_RECORDS	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.TMP	FOR INDEXES ALL;

reorg table HOMEPAGE.NR_NEWS_RECORDS use NEWSTMPTABSPACE;
reorg table HOMEPAGE.TMP use NEWSTMPTABSPACE;
reorg indexes all for table HOMEPAGE.NR_NEWS_RECORDS;
reorg indexes all for table HOMEPAGE.TMP;

-- MOVING BACK THE DATA
-- Note: in case you receive this error:
-- DB21034E  The command was processed as an SQL statement because it was not a 
-- valid Command Line Processor command.  During SQL processing it returned:
-- SQL0964C  The transaction log for the database is full.  SQLSTATE=57011

-- try before to run the script to update the LOGFILSIZ
-- "db2 update database configuration for HOMEPAGE using LOGFILSIZ 8192"
-- "db2 update database configuration for HOMEPAGE using LOGPRIMARY 50"
-- "db2 update database configuration for HOMEPAGE using LOGSECOND 50"



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
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;
