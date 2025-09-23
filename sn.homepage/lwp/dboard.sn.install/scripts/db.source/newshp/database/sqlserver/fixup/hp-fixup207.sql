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
------------------------ START HP FIXUP 207 --------
----------------------------------------------------

-- 82712: update SQL files to change microblogging gadget from INLINE -> iframe

-- Reopening - there is an error in the migration scripts. The update should be made on the  '%widget.connshare.microblog.name' widget, not on the '%widget.connshare.files.name'
-- REDO
UPDATE HOMEPAGE.WIDGET SET WIDGET_POLICY_FLAGS = 39 WHERE WIDGET_TITLE = '%widget.connshare.microblog.name';
GO

-----------------------------------------------------------------------
-- ADDING new HOMEPAGE.MT_ORGANIZATION table
-----------------------------------------------------------------------
CREATE TABLE HOMEPAGE.MT_ORGANIZATION (
    ORGANIZATION_ID nvarchar (36) NOT NULL,
    ORGANIZATION_EXID nvarchar (256) -- note is nullable for premises IC deployment
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.MT_ORGANIZATION
    ADD CONSTRAINT PK_ORGANIZATION_ID PRIMARY KEY(ORGANIZATION_ID);
    
INSERT INTO HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID) VALUES ('00000000-0000-0000-0000-000000000000');

GO

----------------------------------------------------------------------------------------
-- [START] Adding ORGANIZATION_ID to all the tables
----------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.PERSON ADD DEFAULT '00000000-0000-0000-0000-000000000000' FOR ORGANIZATION_ID;
GO

ALTER TABLE HOMEPAGE.LOGINNAME ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.HP_UI ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.HP_TAB ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.HP_TAB_INST ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.WIDGET ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_TAB ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.HP_WIDGET_INST ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.PREREQ ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.NT_REPLYTO_RECIPIENT ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO

ALTER TABLE HOMEPAGE.MT_METRIC_STAT ADD ORGANIZATION_ID nvarchar(36)  DEFAULT '00000000-0000-0000-0000-000000000000'; 
GO


----------------------------------------------------------------------------------------
-- [END] Adding ORGANIZATION_ID to all the tables
----------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.PERSON ADD LAST_CONN_VISIT DATETIME;
GO

CREATE INDEX PERSON_LAST_CONN_VISIT
    ON HOMEPAGE.PERSON(LAST_CONN_VISIT DESC);	
GO

----------------------------------------------------
------------------------ START END FIXUP 207 -------
----------------------------------------------------