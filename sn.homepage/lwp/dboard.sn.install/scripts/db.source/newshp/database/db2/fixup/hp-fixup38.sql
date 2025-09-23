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

------------------------------------------------------------
-- 2) MOVING NT_NOTIFICATION_RECIPIENT to HPNT16TABSPACE
------------------------------------------------------------
CREATE TABLE HOMEPAGE.TMP_NT_NOT_RECIPIENT (
		  ID VARCHAR(36) NOT NULL,
		  NOTIFICATION_ID VARCHAR(36) NOT NULL,
		  RECIPIENT_ID VARCHAR(36),
		  IS_DELETED SMALLINT	  
)
IN HPNT16TABSPACE;

insert into  HOMEPAGE.TMP_NT_NOT_RECIPIENT (
      ID,
      NOTIFICATION_ID, 
      RECIPIENT_ID, 
      IS_DELETED	
)
select 
        ID,
        NOTIFICATION_ID, 
        RECIPIENT_ID, 
        IS_DELETED
from    HOMEPAGE.NT_NOTIFICATION_RECIPIENT;
COMMIT;

DROP TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

------------------------------------------------
-- NT_NOTIFICATION_RECIPIENT
------------------------------------------------
CREATE TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT (
		  ID VARCHAR(36) NOT NULL,
		  NOTIFICATION_ID VARCHAR(36) NOT NULL,
		  RECIPIENT_ID VARCHAR(36),
		  IS_DELETED SMALLINT	  
)
IN HPNT16TABSPACE;

-- giving grants
{DB2_GRANT_START} HOMEPAGE.NT_NOTIFICATION_RECIPIENT {DB2_GRANT_STOP}
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNT16TMPTABSPACE;
RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;

-- copying back the original NT_NOTIFICATION_RECIPIENT
insert into  HOMEPAGE.NT_NOTIFICATION_RECIPIENT (
      ID,
      NOTIFICATION_ID, 
      RECIPIENT_ID, 
      IS_DELETED	
)
select 
        ID,
        NOTIFICATION_ID, 
        RECIPIENT_ID, 
        IS_DELETED
from    HOMEPAGE.TMP_NT_NOT_RECIPIENT;
COMMIT;

-- reorg the tables
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNT16TMPTABSPACE;
RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;

-- adding constraints
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
	ADD CONSTRAINT "PK_NOTIF_RECIP" PRIMARY KEY ("ID");

ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT 
	ADD CONSTRAINT "FK_RECIP_NOTIF" FOREIGN KEY ("NOTIFICATION_ID")
	REFERENCES HOMEPAGE.NT_NOTIFICATION ("NOTIFICATION_ID")
	ON DELETE CASCADE;

ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
    ADD CONSTRAINT "FK_RECIPIENT_ID" FOREIGN KEY ("RECIPIENT_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

CREATE INDEX HOMEPAGE.NT_NOT_RECIPIENT_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT(RECIPIENT_ID);
	
CREATE INDEX HOMEPAGE.NT_NOTIF_RECT_NID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT(NOTIFICATION_ID);

-- reorg table
reorg table HOMEPAGE.NT_NOTIFICATION_RECIPIENT use HPNT16TMPTABSPACE;
RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;	

-- drop old table
DROP TABLE HOMEPAGE.TMP_NT_NOT_RECIPIENT;

-----------------------------------------------
-- DROPPING TABSPACE AND BUFFERPOOL
-----------------------------------------------
DROP TABLESPACE HPNTTABSPACE; 
DROP TABLESPACE HPNTTMPTABSPACE;
DROP BUFFERPOOL NT8KBP;
