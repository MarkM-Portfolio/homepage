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

ALTER TABLE HOMEPAGE.PERSON
	ADD PROF_TYPE nvarchar(128);

GO

-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- START: OAUTH TABLES
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- START: OAUTH Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

------------------------------------------------
-- TOKEN
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_TOKEN (
	ID nvarchar(36) NOT NULL,
	TOKEN nvarchar(1024) NOT NULL,
	SECRET nvarchar(1024)NOT NULL,
	EXPIRATION DATETIME,
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_TOKEN 
    ADD CONSTRAINT PK_OH_TOKEN_ID PRIMARY KEY(ID);
 
    

------------------------------------------------
-- PROVIDER
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_PROVIDER (
	ID nvarchar(36) NOT NULL,
	NAME nvarchar(256) NOT NULL,
	DESCRIPTION nvarchar(1024),
	REQUESTTOKENURL nvarchar(512) NOT NULL,
	AUTHORIZEURL nvarchar(512) NOT NULL,
	ACCESSTOKENURL nvarchar(512) NOT NULL,
	BASEURL nvarchar(512),
	MANAGEURL nvarchar(512),	
	REGISTERURL nvarchar(512),	
	SIGNMETHOD nvarchar(32),
	VERSION nvarchar(8),
	CREATEED DATETIME NOT NULL,
	MODIFIED DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_PROVIDER
    ADD CONSTRAINT PK_PROVIDER_ID PRIMARY KEY(ID);

CREATE UNIQUE INDEX PRVD_NAME_UNIQUE 
	ON HOMEPAGE.OH_PROVIDER (NAME);
	
------------------------------------------------
-- CLIENT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CLIENT (
	ID nvarchar(36) NOT NULL,
	NAME nvarchar(256) NOT NULL,
	TOKENID nvarchar(36) NOT NULL,
	PROVIDERID nvarchar(36) NOT NULL,
	DESCRIPTION nvarchar(1024),
	CALLBACKURL nvarchar(512),
	USERNAME nvarchar(256) NOT NULL,
	CREATEED DATETIME NOT NULL,
	MODIFIED DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_CLIENT
    ADD CONSTRAINT PK_CLIENT_ID PRIMARY KEY(ID);

CREATE UNIQUE INDEX CLNT_NAME_UNIQUE
	ON HOMEPAGE.OH_CLIENT (NAME);

ALTER TABLE HOMEPAGE.OH_CLIENT
	ADD CONSTRAINT FK_CLIENT_PRO_ID FOREIGN KEY (PROVIDERID)
	REFERENCES HOMEPAGE.OH_PROVIDER(ID);

ALTER TABLE HOMEPAGE.OH_CLIENT
	ADD CONSTRAINT FK_CLIENT_TOKEN_ID FOREIGN KEY (TOKENID)
	REFERENCES HOMEPAGE.OH_TOKEN(ID);

CREATE UNIQUE INDEX CLIENT_PROVIDER_IDX
    ON HOMEPAGE.OH_CLIENT (PROVIDERID);

CREATE UNIQUE INDEX CLIENT_TOKEN_IDX
    ON HOMEPAGE.OH_CLIENT (TOKENID);	

------------------------------------------------
-- CONTEXT
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_CONTEXT (
	ID nvarchar(36) NOT NULL,
	CLIENTID nvarchar(36) NOT NULL,
	USERID nvarchar(256),
	CALLBACKURL nvarchar(1024),
	REQUESTAUTHURL nvarchar(1024),
	TOKENID nvarchar(36) NOT NULL,
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME,
	SESSIONHANDLE nvarchar(256),
	REFERER nvarchar(512),
	EXPIRATION DATETIME,
	AUTHORIZED NUMERIC(5,0),
	COMPLETED NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_CONTEXT
    ADD CONSTRAINT PK_CONTEXT_ID PRIMARY KEY(ID);

ALTER TABLE HOMEPAGE.OH_CONTEXT
	ADD CONSTRAINT FK_CNTXT_CLIENT_ID FOREIGN KEY (CLIENTID)
	REFERENCES HOMEPAGE.OH_CLIENT(ID);

ALTER TABLE HOMEPAGE.OH_CONTEXT
	ADD CONSTRAINT FK_CNTXT_TOKEN_ID FOREIGN KEY (TOKENID)
	REFERENCES HOMEPAGE.OH_TOKEN(ID);

CREATE UNIQUE INDEX CONTEXT_CLIENT_IDX
    ON HOMEPAGE.OH_CONTEXT (CLIENTID);

CREATE UNIQUE INDEX CONTEXT_TOKEN_IDX
    ON HOMEPAGE.OH_CONTEXT (TOKENID);
	
------------------------------------------------
-- APPLICATION
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_APPLICATION (
	ID nvarchar(36) NOT NULL,
	NAME nvarchar(256) NOT NULL,
	DESCRIPTION nvarchar(1024),
	BASEURL nvarchar(512) NOT NULL,
	SECUREBASEURL nvarchar(512),
	PROVIDERID nvarchar(36) NOT NULL,
	CREATEED DATETIME NOT NULL,
	MODIFIED DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_APPLICATION
    ADD CONSTRAINT PK_APP_ID PRIMARY KEY(ID);

CREATE UNIQUE INDEX APP_NAME_UNIQUE
	ON HOMEPAGE.OH_APPLICATION (NAME);

ALTER TABLE HOMEPAGE.OH_APPLICATION
	ADD CONSTRAINT FK_APP_PROVIDER_ID FOREIGN KEY (PROVIDERID)
	REFERENCES HOMEPAGE.OH_PROVIDER(ID);

CREATE UNIQUE INDEX APP_PROVIDER_IDX
    ON HOMEPAGE.OH_APPLICATION (PROVIDERID);
	
------------------------------------------------
-- OAUTHACL
------------------------------------------------
CREATE TABLE HOMEPAGE.OH_OAUTHACL (
	ID nvarchar(36) NOT NULL,
	CONTEXTID nvarchar(36) NOT NULL,
	AUTHORIZETYPE NUMERIC(5,0),
	AUTHORIZEDAPPID nvarchar(36),
	CREATED DATETIME NOT NULL,
	MODIFIED DATETIME
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH_OAUTHACL
    ADD CONSTRAINT PK_OAUTHACL_ID PRIMARY KEY(ID);

ALTER TABLE HOMEPAGE.OH_OAUTHACL
	ADD CONSTRAINT FK_ACL_CONTEXT_ID FOREIGN KEY (CONTEXTID)
	REFERENCES HOMEPAGE.OH_CONTEXT(ID);

ALTER TABLE HOMEPAGE.OH_OAUTHACL
	ADD CONSTRAINT FK_ACL_APP_ID FOREIGN KEY (AUTHORIZEDAPPID)
	REFERENCES HOMEPAGE.OH_APPLICATION(ID);

CREATE UNIQUE INDEX ACL_CONTEXT_IDX
    ON HOMEPAGE.OH_OAUTHACL (CONTEXTID);

CREATE UNIQUE INDEX ACL_APP_IDX
    ON HOMEPAGE.OH_OAUTHACL (AUTHORIZEDAPPID);
    
GO
	
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
-- END OAUTH Schema
-- %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-- END: OAUTH TABLES
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
-------------------------------------------------------------------------
