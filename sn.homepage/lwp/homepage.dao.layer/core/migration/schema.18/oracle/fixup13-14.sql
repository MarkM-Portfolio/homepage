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

connect to HOMEPAGE;

-----------------------------------------------------------------------------------------------------------
-- 1) Adding search table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
CREATE TABLE HOMEPAGE.SR_FILESCONTENT (
	FILESCONTENT_ID VARCHAR2(36) NOT NULL,
	URL VARCHAR2(256) NOT NULL,
	COMPONENT_UUID VARCHAR2(36) NOT NULL,
	COMPONENT VARCHAR2(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	LAST_MODIFIED_DATE TIMESTAMP NOT NULL,
	LAST_ACCESSED_DATE TIMESTAMP NOT NULL,
	IS_CURRENT NUMBER(5,0) NOT NULL,
	CONTENT CLOB NOT NULL,
	FILE_SIZE NUMBER(19 ,0) NOT NULL,
	CONTENT_SIZE NUMBER(19 ,0) NOT NULL,
	INPUT_MIME_TYPE VARCHAR2(36) NOT NULL
)
TABLESPACE "HOMEPAGEREGTABSPACE";

-----------------------------------------------------------------------------------------------------------
-- 2) Adding search constraints for table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD (CONSTRAINT "PK_FILESCONTENT_ID" PRIMARY KEY ("FILESCONTENT_ID")
	USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE");

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE ("COMPONENT_UUID","COMPONENT");

-----------------------------------------------------------------------------------------------------------
-- 3) Grant the user for the new table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
ALTER TABLE "HOMEPAGE"."SR_FILESCONTENT" ENABLE ROW MOVEMENT;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_FILESCONTENT TO HOMEPAGEUSER_ROLE;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_ALLTASKSDEF TO HOMEPAGEUSER_ROLE;

-----------------------------------------------------------------------------------------------------------
-- 4) Upgrade fixup number to 14
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 13 to 14
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 14
WHERE   DBSCHEMAVER = 13;

COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;