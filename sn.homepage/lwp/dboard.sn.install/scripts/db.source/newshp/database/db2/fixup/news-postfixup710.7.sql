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
	EVENT_ID VARCHAR(36) NOT NULL,
	EVENT BLOB(3670016),
	CREATION_DATE TIMESTAMP NOT NULL,
    SOURCE VARCHAR(36) NOT NULL,
    TAGS VARCHAR(2048),
    URL VARCHAR(2048),
    ATTEMPTS SMALLINT DEFAULT 0 NOT NULL
)
IN NEWSTABSPACE@

ALTER TABLE HOMEPAGE.MIGN_EVENT_SYNC
  	ADD CONSTRAINT PK_MIGN_EVENT_SYNC PRIMARY KEY (EVENT_ID)@

CREATE INDEX HOMEPAGE.MIGN_EVENT_SYNC_DATE_IDX
	ON HOMEPAGE.MIGN_EVENT_SYNC (CREATION_DATE ASC)@

COMMIT@

RUNSTATS ON TABLE HOMEPAGE.MIGN_EVENT_SYNC@

------------------------------------------------------------------------------
-- Migration Profiles Tool : Migration People
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE (
    ID        		VARCHAR(36) NOT NULL,
    USERID    		VARCHAR(36),
    EMAIL     		VARCHAR(256),
    ORGID	    	VARCHAR(36),
    PRIORITY  		INTEGER  DEFAULT '0',
    STATE	    	BIGINT  DEFAULT '0',
    CREATETIME      TIMESTAMP DEFAULT CURRENT TIMESTAMP,
    STATEUPDATETIME TIMESTAMP DEFAULT CURRENT TIMESTAMP,
    MIGRATIONTYPE   VARCHAR(36) DEFAULT 'migrate'
)
IN NEWS4TABSPACE@

ALTER TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE 
	ADD CONSTRAINT MIGP_MIGRATIONPEOPLE_PK PRIMARY KEY (ID)@

CREATE INDEX HOMEPAGE.MIGP_MP_NEXT_PEOPLE_IDX
    ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (PRIORITY DESC, STATE, STATEUPDATETIME)@

CREATE INDEX HOMEPAGE.MIGP_MP_FIND_IDX
    ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (USERID, EMAIL)@

COMMIT@

RUNSTATS ON TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE@

------------------------------------------------------------------------------
-- Migration Profiles Tool : Populate Status
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_POPULATESTATUS (
    ID              VARCHAR(36) NOT NULL,
    CURRENTURL      VARCHAR(256),
    TIME            TIMESTAMP,
    MIGRATIONTYPE   VARCHAR(36) DEFAULT 'migrate'
)
IN NEWS4TABSPACE@

ALTER TABLE HOMEPAGE.MIGP_POPULATESTATUS 
	ADD CONSTRAINT MIGP_POPULATESTATUS_PK PRIMARY KEY (ID)@

COMMIT@

RUNSTATS ON TABLE HOMEPAGE.MIGP_POPULATESTATUS@

------------------------------------------------------------------------------
-- Migration Profiles Tool : Profile Key Map
------------------------------------------------------------------------------
	
CREATE TABLE HOMEPAGE.MIGP_PROFILEKEYMAP (
    ID              VARCHAR(36) NOT NULL,
    PROFILEKEY      VARCHAR(36) NOT NULL,
    EXTERNALID      VARCHAR(36) NOT NULL,
    ORGID           VARCHAR(36) NOT NULL,
    CREATED         TIMESTAMP
)
IN NEWS4TABSPACE@

ALTER TABLE HOMEPAGE.MIGP_PROFILEKEYMAP 
	ADD CONSTRAINT MIGP_PROFILEKEYMAP_PK PRIMARY KEY (ID)@

CREATE UNIQUE INDEX HOMEPAGE.MIGP_PKM_PROFILEKEY_UIDX
    ON HOMEPAGE.MIGP_PROFILEKEYMAP (PROFILEKEY)@

CREATE UNIQUE INDEX HOMEPAGE.MIGP_PKM_EXTERNALID_UIDX
    ON HOMEPAGE.MIGP_PROFILEKEYMAP (EXTERNALID)@

COMMIT@

RUNSTATS ON TABLE HOMEPAGE.MIGP_PROFILEKEYMAP@
