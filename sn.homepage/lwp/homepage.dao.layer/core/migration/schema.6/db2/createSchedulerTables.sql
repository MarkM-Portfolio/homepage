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

CONNECT TO HOMEPAGE;

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ("TASKID" BIGINT NOT NULL ,
              "VERSION" VARCHAR(5) NOT NULL ,
              "ROW_VERSION" INTEGER NOT NULL ,
              "TASKTYPE" INTEGER NOT NULL ,
              "TASKSUSPENDED" SMALLINT NOT NULL ,
              "CANCELLED" SMALLINT NOT NULL ,
              "NEXTFIRETIME" BIGINT NOT NULL ,
              "STARTBYINTERVAL" VARCHAR(254) ,
              "STARTBYTIME" BIGINT ,
              "VALIDFROMTIME" BIGINT ,
              "VALIDTOTIME" BIGINT ,
              "REPEATINTERVAL" VARCHAR(254) ,
              "MAXREPEATS" INTEGER NOT NULL ,
              "REPEATSLEFT" INTEGER NOT NULL ,
              "TASKINFO" BLOB(102400) LOGGED NOT COMPACT ,
              "NAME" VARCHAR(254) NOT NULL ,
              "AUTOPURGE" INTEGER NOT NULL ,
              "FAILUREACTION" INTEGER ,
              "MAXATTEMPTS" INTEGER ,
              "QOS" INTEGER ,
              "PARTITIONID" INTEGER ,
              "OWNERTOKEN" VARCHAR(200) NOT NULL ,
              "CREATETIME" BIGINT NOT NULL )  IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK ADD PRIMARY KEY ("TASKID");

CREATE INDEX HOMEPAGE.LOTUSCONNECTIONSTASK_IDX1 ON HOMEPAGE.LOTUSCONNECTIONSTASK ("TASKID",
              "OWNERTOKEN") ;

CREATE INDEX HOMEPAGE.LOTUSCONNECTIONSTASK_IDX2 ON HOMEPAGE.LOTUSCONNECTIONSTASK ("NEXTFIRETIME" ASC,
               "REPEATSLEFT",
               "PARTITIONID") CLUSTER;

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ("REGKEY" VARCHAR(254) NOT NULL ,
              "REGVALUE" VARCHAR(254) ) IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTREG ADD PRIMARY KEY ("REGKEY");

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR (LEASENAME VARCHAR(254) NOT NULL,
               LEASEOWNER VARCHAR(254) NOT NULL,
               LEASE_EXPIRE_TIME  BIGINT,
              DISABLED VARCHAR(5))IN "HOMEPAGETABSPACE";

ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSLMGR ADD PRIMARY KEY ("LEASENAME");

CREATE TABLE HOMEPAGE.LOTUSCONNECTIONSLMPR (LEASENAME VARCHAR(254) NOT NULL,
              NAME VARCHAR(254) NOT NULL,
              VALUE VARCHAR(254) NOT NULL)IN "HOMEPAGETABSPACE";

CREATE INDEX "LOTUSCONNECTIONSLMPR_IDX1" ON HOMEPAGE.LOTUSCONNECTIONSLMPR (LEASENAME,
               NAME);

--------------------------------------
-- grants for scheduler tables
--------------------------------------

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTASK TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSTREG  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMGR  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOTUSCONNECTIONSLMPR TO USER LCUSER;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- DISCONNECT
--------------------------------------
CONNECT RESET;
DISCONNECT ALL;	
