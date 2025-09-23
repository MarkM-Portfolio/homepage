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

USE HOMEPAGE;
GO

--------------------------------------
-- CREATE THE TABLES
--------------------------------------
BEGIN TRANSACTION
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ( [TASKID] BIGINT NOT NULL ,
               [VERSION] NVARCHAR(5) NOT NULL ,
               [ROW_VERSION] INT NOT NULL ,
               [TASKTYPE] INT NOT NULL ,
               [TASKSUSPENDED] TINYINT NOT NULL ,
               [CANCELLED] TINYINT NOT NULL ,
               [NEXTFIRETIME] BIGINT NOT NULL ,
               [STARTBYINTERVAL] NVARCHAR(254) NULL ,
               [STARTBYTIME] BIGINT NULL ,
               [VALIDFROMTIME] BIGINT NULL ,
               [VALIDTOTIME] BIGINT NULL ,
               [REPEATINTERVAL] NVARCHAR(254) NULL ,
               [MAXREPEATS] INT NOT NULL ,
               [REPEATSLEFT] INT NOT NULL ,
               [TASKINFO] IMAGE NULL ,
               [NAME] NVARCHAR(254) NOT NULL ,
               [AUTOPURGE] INT NOT NULL ,
               [FAILUREACTION] INT NULL ,
               [MAXATTEMPTS] INT NULL ,
               [QOS] INT NULL ,
               [PARTITIONID] INT NULL ,
               [OWNERTOKEN] NVARCHAR(200) NOT NULL ,
               [CREATETIME] BIGINT NOT NULL ) 
GO

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK WITH NOCHECK ADD CONSTRAINT LOTUSCONNECTIONSTASK_PK PRIMARY KEY  NONCLUSTERED ( [TASKID] ) 
GO

CREATE INDEX LOTUSCONNECTIONSTASK_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSTASK ( [TASKID],
               [OWNERTOKEN] ) 
GO

CREATE CLUSTERED INDEX LOTUSCONNECTIONSTASK_IDX2 ON HOMEPAGE.LOTUSCONNECTIONSTASK ( [NEXTFIRETIME] ASC,
               [REPEATSLEFT],
               [PARTITIONID] )
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ( [REGKEY] NVARCHAR(254) NOT NULL ,
               [REGVALUE] NVARCHAR(254) NULL ,
               PRIMARY KEY ( [REGKEY] ) )
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR ( [LEASENAME] NVARCHAR(254) NOT NULL,
               [LEASEOWNER] NVARCHAR(254) NOT NULL,
               [LEASE_EXPIRE_TIME] BIGINT,
               [DISABLED] NVARCHAR(5) )
GO

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR WITH NOCHECK ADD CONSTRAINT LOTUSCONNECTIONSLMGR_PK PRIMARY KEY  NONCLUSTERED ( [LEASENAME] ) 
GO

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR ( [LEASENAME] NVARCHAR(224) NOT NULL,
               [NAME] NVARCHAR(224) NOT NULL,
               [VALUE] NVARCHAR(254) NOT NULL )
GO

CREATE INDEX LOTUSCONNECTIONSLMPR_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSLMPR ( [LEASENAME],
               [NAME] ) 
GO

--------------------------------------
-- GRANT TO HOMEPAGE USER
--------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMPR TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMGR TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTREG TO HOMEPAGEUSER
GO
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTASK TO HOMEPAGEUSER
GO


--------------------------------------
-- DISCONNECT
--------------------------------------
COMMIT


