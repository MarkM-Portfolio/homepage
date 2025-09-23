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

-- DB2   	ORACLE
-- CHAR 	-> CHAR
-- VARCHAR	-> VARCHAR2
-- SMALLINT	-> NUMBER(5,0)
-- INTEGER	-> NUMBER(10 ,0)
-- BIGINT	-> NUMBER(19 ,0)
-- CLOB(max)	-> CLOB
-- BLOB(max)	-> BLOB
-- TIMESTAMP	-> TIMESTAMP

--------------------------------------
-- CREATE THE TABLES
--------------------------------------

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSTASK"("TASKID" NUMBER(19) NOT NULL,
               "VERSION" VARCHAR2(5) NOT NULL,
               "ROW_VERSION" NUMBER(10) NOT NULL,
               "TASKTYPE" NUMBER(10) NOT NULL,
               "TASKSUSPENDED" NUMBER(1) NOT NULL,
               "CANCELLED" NUMBER(1) NOT NULL,
               "NEXTFIRETIME" NUMBER(19) NOT NULL,
               "STARTBYINTERVAL" VARCHAR2(254),
               "STARTBYTIME" NUMBER(19),
               "VALIDFROMTIME" NUMBER(19),
               "VALIDTOTIME" NUMBER(19),
               "REPEATINTERVAL" VARCHAR2(254),
               "MAXREPEATS" NUMBER(10) NOT NULL,
               "REPEATSLEFT" NUMBER(10) NOT NULL,
               "TASKINFO" BLOB,
               "NAME" VARCHAR2(254),
               "AUTOPURGE" NUMBER(10) NOT NULL,
               "FAILUREACTION" NUMBER(10),
               "MAXATTEMPTS" NUMBER(10),
               "QOS" NUMBER(10),
               "PARTITIONID" NUMBER(10),
               "OWNERTOKEN" VARCHAR2(200) NOT NULL,
               "CREATETIME" NUMBER(19) NOT NULL,
               PRIMARY KEY ("TASKID") ) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSTASK_IDX1" ON "HOMEPAGE"."LOTUSCONNECTIONSTASK" ("TASKID",
              "OWNERTOKEN") ;

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSTASK_IDX2" ON "HOMEPAGE"."LOTUSCONNECTIONSTASK" ("NEXTFIRETIME" ASC,
               "REPEATSLEFT",
               "PARTITIONID") ;

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSTREG" ("REGKEY" VARCHAR2(254) NOT NULL ,
               "REGVALUE" VARCHAR2(254) ,
               PRIMARY KEY ( "REGKEY" )) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMGR" ("LEASENAME" VARCHAR2(254) NOT NULL,
               "LEASEOWNER" VARCHAR2(254),
               "LEASE_EXPIRE_TIME" NUMBER(19),
               "DISABLED" VARCHAR2(254),
               PRIMARY KEY ( "LEASENAME" )) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE TABLE "HOMEPAGE"."LOTUSCONNECTIONSLMPR" ("LEASENAME" VARCHAR2(254) NOT NULL,
               "NAME" VARCHAR2(254) NOT NULL,
               "VALUE" VARCHAR2(254) NOT NULL ) TABLESPACE "HOMEPAGEREGTABSPACE";

CREATE INDEX "HOMEPAGE"."LOTUSCONNECTIONSLMPR_IDX1" ON "HOMEPAGE"."LOTUSCONNECTIONSLMPR" ("LEASENAME",
               "NAME") ;


COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
