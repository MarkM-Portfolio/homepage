-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015, 2017                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

------------------------------------------------------------------------------
-- Migration News : Event Synchronization
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGN_EVENT_SYNC (
	EVENT_ID VARCHAR2(36) NOT NULL,
	EVENT BLOB,
	CREATION_DATE TIMESTAMP NOT NULL,
    SOURCE VARCHAR2(36) NOT NULL,
    TAGS VARCHAR2(2048),
    URL VARCHAR2(2048),
    ATTEMPTS SMALLINT DEFAULT 0 NOT NULL
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.MIGN_EVENT_SYNC 
	ADD CONSTRAINT PK_MIGN_EVENT_SYNC PRIMARY KEY(EVENT_ID);
  	 	
CREATE INDEX HOMEPAGE.MIGN_EVENT_SYNC_DATE_IDX
	ON HOMEPAGE.MIGN_EVENT_SYNC (CREATION_DATE ASC) TABLESPACE "HOMEPAGEINDEXTABSPACE";

COMMIT;

------------------------------------------------------------------------------
-- Migration Profiles Tool : Migration People
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE (
    ID              VARCHAR2(36) NOT NULL,
    USERID          VARCHAR2(36),
    EMAIL           VARCHAR2(256),
    ORGID           VARCHAR2(36),
    PRIORITY        NUMBER(5,0)  DEFAULT '0',
    STATE           NUMBER(19,0)  DEFAULT '0',
    CREATETIME      TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL ,
    STATEUPDATETIME TIMESTAMP     DEFAULT SYSTIMESTAMP NOT NULL ,
    MIGRATIONTYPE   VARCHAR2(36)  DEFAULT 'migrate'
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE 
	ADD CONSTRAINT MIGP_MIGRATIONPEOPLE_PK PRIMARY KEY (ID);

CREATE INDEX HOMEPAGE.MIGP_MP_NEXT_PEOPLE_IDX
    ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (PRIORITY DESC, STATE, STATEUPDATETIME) TABLESPACE "HOMEPAGEINDEXTABSPACE";

CREATE INDEX HOMEPAGE.MIGP_MP_FIND_IDX
    ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (USERID, EMAIL) TABLESPACE "HOMEPAGEINDEXTABSPACE";

COMMIT;

------------------------------------------------------------------------------
-- Migration Profiles Tool : Populate Status
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_POPULATESTATUS (
    ID              VARCHAR2(36) NOT NULL,
    CURRENTURL      VARCHAR2(256),
    TIME            TIMESTAMP,
    MIGRATIONTYPE   VARCHAR2(36) DEFAULT 'migrate'
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.MIGP_POPULATESTATUS 
	ADD CONSTRAINT MIGP_POPULATESTATUS_PK PRIMARY KEY (ID);

COMMIT;

------------------------------------------------------------------------------
-- Migration Profiles Tool : Profile Key Map
------------------------------------------------------------------------------
	
CREATE TABLE HOMEPAGE.MIGP_PROFILEKEYMAP (
    ID              VARCHAR2(36) NOT NULL,
    PROFILEKEY      VARCHAR2(36) NOT NULL,
    EXTERNALID      VARCHAR2(36) NOT NULL,
    ORGID           VARCHAR2(36) NOT NULL,
    CREATED         TIMESTAMP
)
TABLESPACE "NEWSREGTABSPACE";

ALTER TABLE HOMEPAGE.MIGP_PROFILEKEYMAP 
	ADD CONSTRAINT MIGP_PROFILEKEYMAP_PK PRIMARY KEY (ID);

CREATE UNIQUE INDEX HOMEPAGE.MIGP_PKM_PROFILEKEY_UIDX
    ON HOMEPAGE.MIGP_PROFILEKEYMAP (PROFILEKEY) TABLESPACE "HOMEPAGEINDEXTABSPACE";

CREATE UNIQUE INDEX HOMEPAGE.MIGP_PKM_EXTERNALID_UIDX
    ON HOMEPAGE.MIGP_PROFILEKEYMAP (EXTERNALID) TABLESPACE "HOMEPAGEINDEXTABSPACE";

COMMIT;
