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

----------------------------------------------------
------------------------ START HP FIXUP 460 --------
----------------------------------------------------

-- 96761: 4.6 side stream - sc and on prem. -  fixup460 Add PREF_LANG column to PERSON table and DAO
ALTER TABLE HOMEPAGE.PERSON ADD PREF_LANG nvarchar(5);
GO

--------------------------------------------------------------
-- 98833: [fixup460] Update tables for highway
--------------------------------------------------------------
ALTER TABLE HOMEPAGE.MT_CFG_SETTINGS ADD ROLE nvarchar(36);
GO

ALTER TABLE HOMEPAGE.MT_CFG_DEFINITIONS ADD ALLOW_ROLE NUMERIC(5, 0);
GO

DROP INDEX UNQ_ORG_NAME	ON HOMEPAGE.MT_CFG_SETTINGS;
GO

CREATE UNIQUE INDEX UNQ_ORG_NAME
	ON HOMEPAGE.MT_CFG_SETTINGS (NAME, ORGANIZATION_ID, ROLE);
GO
	

----------------------------------------
-- 99825: Fixing FK issues
----------------------------------------

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP CONSTRAINT CLNT_FK;
GO

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT DROP CONSTRAINT CLNT_TOKEN_FK;
GO

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT DROP CONSTRAINT CONTEXT_CLNT_FK;
GO

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT DROP CONSTRAINT FK_CLIENT_PRO_NAME;
GO

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING DROP CONSTRAINT FK_BINDING_CLIENT;
GO

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT ADD CONSTRAINT CLNT_FK FOREIGN KEY (PROVIDERID) REFERENCES HOMEPAGE.OAUTH1_PROVIDER (ID);
GO

ALTER TABLE HOMEPAGE.OAUTH1_CLIENT ADD CONSTRAINT CLNT_TOKEN_FK FOREIGN KEY (TOKENID) REFERENCES HOMEPAGE.OAUTH1_TOKEN (ID);
GO

ALTER TABLE HOMEPAGE.OAUTH1_CONTEXT ADD CONSTRAINT CONTEXT_CLNT_FK FOREIGN KEY (CLIENTID) REFERENCES HOMEPAGE.OAUTH1_CLIENT (ID);
GO

ALTER TABLE HOMEPAGE.OAUTH2_CLIENT ADD CONSTRAINT FK_CLIENT_PRO_NAME FOREIGN KEY (PROVIDER_NAME) REFERENCES HOMEPAGE.OAUTH2_PROVIDER (NAME);
GO

ALTER TABLE HOMEPAGE.OAUTH2_GADGET_BINDING ADD CONSTRAINT FK_BINDING_CLIENT FOREIGN KEY (CLIENT_NAME) REFERENCES HOMEPAGE.OAUTH2_CLIENT (NAME);
GO

----------------------------------------------------
------------------------ END   HP FIXUP 460 -------
----------------------------------------------------