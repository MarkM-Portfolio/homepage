-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016, 2017                             
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
	EVENT_ID nvarchar(36) NOT NULL,
	EVENT varbinary(MAX),
	CREATION_DATE DATETIME2(3) NOT NULL,
    SOURCE nvarchar(36) NOT NULL,
    TAGS nvarchar(2048),
    URL nvarchar(2048),
    ATTEMPTS NUMERIC(5,0) NOT NULL DEFAULT 0 
)ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MIGN_EVENT_SYNC 
	ADD CONSTRAINT PK_MIGN_EVENT_SYNC PRIMARY KEY(EVENT_ID);	 	

CREATE INDEX MIGN_EVENT_SYNC_DATE_IDX
	ON HOMEPAGE.MIGN_EVENT_SYNC (CREATION_DATE ASC);
GO

------------------------------------------------------------------------------
-- Migration Profiles Tool : Migration People
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE (
    ID          	NVARCHAR(36) NOT NULL,
    USERID      	NVARCHAR(36),
    EMAIL       	NVARCHAR(256),
    ORGID         	NVARCHAR(36),
    PRIORITY    	INTEGER  DEFAULT '0',
    STATE         	BIGINT  DEFAULT '0',
    CREATETIME      DATETIME2(3) DEFAULT CURRENT_TIMESTAMP,
    STATEUPDATETIME DATETIME2(3) DEFAULT CURRENT_TIMESTAMP,
    MIGRATIONTYPE   NVARCHAR(36) DEFAULT 'migrate'

)ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MIGP_MIGRATIONPEOPLE 
	ADD CONSTRAINT MIGP_MIGRATIONPEOPLE_PK PRIMARY KEY (ID);

CREATE INDEX MIGP_MP_NEXT_PEOPLE_IDX 
	ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (PRIORITY DESC, STATE, STATEUPDATETIME);

CREATE INDEX MIGP_MP_FIND_IDX 
	ON HOMEPAGE.MIGP_MIGRATIONPEOPLE (USERID, EMAIL);
GO

------------------------------------------------------------------------------
-- Migration Profiles Tool : Populate Status
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_POPULATESTATUS (
    ID              NVARCHAR(36) NOT NULL,
    CURRENTURL      NVARCHAR(256),
    TIME            DATETIME2(3),
    MIGRATIONTYPE   NVARCHAR(36) DEFAULT 'migrate'

)ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MIGP_POPULATESTATUS 
	ADD CONSTRAINT MIGP_POPULATESTATUS_PK PRIMARY KEY (ID);
GO

------------------------------------------------------------------------------
-- Migration Profiles Tool : Profile Key Map
------------------------------------------------------------------------------

CREATE TABLE HOMEPAGE.MIGP_PROFILEKEYMAP (
    ID            NVARCHAR(36) NOT NULL,
    PROFILEKEY    NVARCHAR(36) NOT NULL,
    EXTERNALID    NVARCHAR(36) NOT NULL,
    ORGID         NVARCHAR(36) NOT NULL,
    CREATED       DATETIME2(3)
)ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MIGP_PROFILEKEYMAP 
	ADD CONSTRAINT MIGP_PROFILEKEYMAP_PK PRIMARY KEY (ID);

CREATE UNIQUE INDEX MIGP_PKM_PROFILEKEY_UIDX 
	ON HOMEPAGE.MIGP_PROFILEKEYMAP (PROFILEKEY);

CREATE UNIQUE INDEX MIGP_PKM_EXTERNALID_UIDX 
	ON HOMEPAGE.MIGP_PROFILEKEYMAP (EXTERNALID);
GO
