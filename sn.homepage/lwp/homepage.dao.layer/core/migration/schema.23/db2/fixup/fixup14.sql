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
	FILESCONTENT_ID VARCHAR(36) NOT NULL,
	URL VARCHAR(256) NOT NULL,
	COMPONENT_UUID VARCHAR(36) NOT NULL,
	COMPONENT VARCHAR(36) NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	LAST_MODIFIED_DATE TIMESTAMP NOT NULL,
	LAST_ACCESSED_DATE TIMESTAMP NOT NULL,
	IS_CURRENT SMALLINT NOT NULL,
	CONTENT CLOB(200 K) NOT NULL,
	FILE_SIZE BIGINT NOT NULL,
	CONTENT_SIZE BIGINT NOT NULL,
	INPUT_MIME_TYPE VARCHAR(36) NOT NULL
)
IN HOMEPAGETABSPACE;

-----------------------------------------------------------------------------------------------------------
-- 2) Adding search constraints for table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT "PK_FILESCONTENT_ID" PRIMARY KEY ("FILESCONTENT_ID");

ALTER TABLE HOMEPAGE.SR_FILESCONTENT
	ADD CONSTRAINT UNIQUE_COMP_UUID UNIQUE ("COMPONENT_UUID","COMPONENT");

-----------------------------------------------------------------------------------------------------------
-- 3) Grant the user for the new table SR_FILESCONTENT
-----------------------------------------------------------------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_FILESCONTENT TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_ALLTASKSDEF TO USER LCUSER;

-----------------------------------------------------------------------------------------------------------
-- 4) Upgrade fixup number to 14
-----------------------------------------------------------------------------------------------------------

-- Updating the schema version from 13 to 14
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 14
WHERE   DBSCHEMAVER = 13;

COMMIT;

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;
