-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

SET CURRENT SCHEMA HOMEPAGE;

{include.news-fixup500.sql}
{include.search-fixup500.sql}
-- [news-fixup501.sql] empty
{include.news-fixup502.sql}
{include.news-fixup503.sql}
-- [news-fixup504.sql] smart cloud specific
-- [news-fixup510.sql] to check, looks like SC specific
{include.news-fixup510.sql}

--[news-fixup511.sql] START

INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW VALUES ('00000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000001','00000000-0000-0000-0000-000000000001',1,'00000000-0000-0000-0000-000000000000');

--[news-fixup511.sql] END

-- [news-fixup512.sql] smart cloud specific
{include.news-fixup513.sql}

--[news-fixup514.sql] START

-- 139069: Refactoring of two unique indexes
DROP INDEX HOMEPAGE.SEQ_NUMBER_UNIQUE;

CREATE UNIQUE INDEX HOMEPAGE.SEQ_NUMBER_UNIQUE
    ON HOMEPAGE.EMD_TRANCHE (SEQ_NUMBER, ORGANIZATION_ID);
    
COMMIT;

-- 139426: DAO: Table storing whether a given story is read by a given user
CREATE TABLE HOMEPAGE.NR_READ_STATUS (
	READ_STATUS_ID VARCHAR(36) CCSID 1208 NOT NULL,
	PERSON_ID VARCHAR(36) CCSID 1208 NOT NULL,
	STORY_ID VARCHAR(36) CCSID 1208 NOT NULL,
	CREATION_DATE TIMESTAMP NOT NULL,
	ORGANIZATION_ID VARCHAR(36) CCSID 1208 NOT NULL
);

ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT HOMEPAGE.PK_READ_STATUS_ID PRIMARY KEY(READ_STATUS_ID);
  	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT HOMEPAGE.FK_READ_STATUS_PER FOREIGN KEY (PERSON_ID)
	REFERENCES HOMEPAGE.PERSON (PERSON_ID);  
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT HOMEPAGE.FK_READ_STATUS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID);
	
ALTER TABLE HOMEPAGE.NR_READ_STATUS
  	ADD CONSTRAINT HOMEPAGE.FK_READ_STATUS_ORG FOREIGN KEY (ORGANIZATION_ID)
	REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);
	
CREATE UNIQUE INDEX HOMEPAGE.READ_STATUS_PER_STR_UNQ_IDX
	ON HOMEPAGE.NR_READ_STATUS (PERSON_ID, STORY_ID);
	
CREATE INDEX HOMEPAGE.READ_STATUS_ORG_IDX
	ON HOMEPAGE.NR_READ_STATUS (ORGANIZATION_ID);
	
COMMIT;	


--[news-fixup514.sql] END

{include.news-fixup515.sql}
-- [news-fixup516.sql] empty
{include.news-fixup517.sql}


{include.news-fixup601.sql}
{include.news-fixup602.sql}
{include.news-fixup603.sql}
{include.search-fixup603.sql}
{include.news-fixup604.sql}
{include.news-fixup605.sql}
{include.hp-fixup605.sql}
{include.news-fixup606.sql}
{include.hp-fixup607.sql}
{include.news-fixup700.sql}
{include.news-fixup701.sql}
{include.news-fixup702.sql}
{include.news-fixup703.sql}
{include.news-fixup704.sql}

------------------------------------------------
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_READ_STATUS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_ENTRIES_ROLLUP_ACTION TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_ENTRIES_ROLLUP_PERSON TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_ORGANIZATION TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_COMPANIES TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_USERS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_PACKAGES TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_COMPANY_ENTITLEMENTS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_COMPANY_PKG_PREFS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_EXT_META2 TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_PACKAGE_DETAILS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_PACKAGE_LOCATION TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_USER_ENTITLEMENTS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.L3T_USER_PKG_PREFS TO LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.SR_FILECONTENTINDEXINGTASK TO LCUSER;
GRANT SELECT ON HOMEPAGE.SR_ALLTASKSDEF TO LCUSER;

------------------------------------------------

UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 704, RELEASEVER = '5.5.0.0'
WHERE   DBSCHEMAVER = 479;

--------------------------------------
-- COMMIT
--------------------------------------

COMMIT;
--------------------------------------
-- FLUSH
--------------------------------------
--FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
--connect reset;
--terminate;