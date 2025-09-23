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

-- Missing fk for NR_NEWS_WATCHLIST
ALTER TABLE HOMEPAGE.NR_NEWS_WATCHLIST
	ADD CONSTRAINT "FK_F_WSOURCE_ID" FOREIGN KEY ("SOURCE_ID")
	REFERENCES HOMEPAGE.NR_SOURCE_WATCHED("SOURCE_ID");
	
-- REMOVING NR_FOLLOW_GROUP table
DROP TABLE HOMEPAGE.NR_FOLLOW_GROUP;

-- REFACTORING THE NR_FOLLOW table; 
DROP TABLE HOMEPAGE.NR_FOLLOW;

-- REMOVING NR_CATEGORY table
DROP TABLE HOMEPAGE.NR_CATEGORY;

------------------------------------------------
-- NR_FOLLOW
------------------------------------------------
CREATE TABLE HOMEPAGE.NR_FOLLOW (
	FOLLOW_ID VARCHAR(36) NOT NULL,
	PERSON_ID VARCHAR(36) NOT NULL,
	SOURCE_ID VARCHAR(36) NOT NULL,
	CATEGORY_TYPE SMALLINT NOT NULL
)
IN NEWSTABSPACE;

ALTER TABLE HOMEPAGE.NR_FOLLOW 
    ADD CONSTRAINT "PK_FOLLOW_ID" PRIMARY KEY("FOLLOW_ID");

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT "FK_F_PERSON_ID" FOREIGN KEY ("PERSON_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT "FK_F_SOURCE_ID" FOREIGN KEY ("SOURCE_ID")
	REFERENCES HOMEPAGE.NR_SOURCE_WATCHED("SOURCE_ID");

ALTER TABLE HOMEPAGE.NR_FOLLOW
	ADD CONSTRAINT "FK_CATEGORY_TYPE" FOREIGN KEY ("CATEGORY_TYPE")
	REFERENCES HOMEPAGE.NR_CATEGORY_TYPE("CATEGORY_TYPE");

runstats on table HOMEPAGE.NR_FOLLOW with distribution and detailed indexes all allow write access;
{DB2_GRANT_START} HOMEPAGE.NR_FOLLOW {DB2_GRANT_STOP}

-- MIGRATE DATA TO THE FOLLOW TABLE
-- CREATE THE RELETIONSHIP FOR PROFILES SOURCE INTO THE FOLLOW TABLE
INSERT INTO HOMEPAGE.NR_FOLLOW (
    FOLLOW_ID,
    PERSON_ID,
    SOURCE_ID,
    CATEGORY_TYPE    
)
SELECT  SUBSTR(NR_SUBSCRIPTION.PERSON_ID,1,10) || 
        SUBSTR(NR_SOURCE.SOURCE_ID,1,10) ||  
        SUBSTR((SUBSTR((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME || NR_SUBSCRIPTION.PERSON_ID),2,36)),1,14),
        NR_SUBSCRIPTION.PERSON_ID,
        NR_SOURCE.SOURCE_ID,
		1
FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
        HOMEPAGE.NR_SOURCE NR_SOURCE, 
        HOMEPAGE.NR_CATEGORY_TYPE NR_CATEGORY_TYPE        
WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID  AND 
        NR_SUBSCRIPTION.IS_EXPLICIT = 1 AND
        NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
        NR_CATEGORY_TYPE.CATEGORY_TYPE = 1 AND -- 1 is profile
        NR_SOURCE.SOURCE = 'profiles';

---- MIGRATE DATA TO THE FOLLOW TABLE
---- CREATE THE RELETIONSHIP FOR TAG SOURCE INTO THE FOLLOW TABLE
--INSERT INTO HOMEPAGE.NR_FOLLOW (
--    FOLLOW_ID,
--    PERSON_ID,
--    SOURCE_ID,
--    CATEGORY_TYPE    
--)
--SELECT  SUBSTR(NR_SUBSCRIPTION.PERSON_ID,1,10) || 
--        SUBSTR(NR_SOURCE.SOURCE_ID,1,10) ||  
--        SUBSTR((SUBSTR((NR_CATEGORY_TYPE.CATEGORY_TYPE_NAME || NR_SUBSCRIPTION.PERSON_ID),2,36)),1,14),
--        NR_SUBSCRIPTION.PERSON_ID,
--        NR_SOURCE.SOURCE_ID,
--		2
--FROM    HOMEPAGE.NR_SUBSCRIPTION NR_SUBSCRIPTION, 
--        HOMEPAGE.NR_SOURCE NR_SOURCE, 
--        HOMEPAGE.NR_CATEGORY_TYPE NR_CATEGORY_TYPE        
--WHERE   NR_SUBSCRIPTION.SOURCE_ID = NR_SOURCE.SOURCE_ID  AND 
--        NR_SUBSCRIPTION.IS_EXPLICIT = 1 AND
--        NR_SUBSCRIPTION.IS_ACTIVE = 1 AND
--        NR_CATEGORY_TYPE.CATEGORY_TYPE = 2 AND -- 2 is tags
--        NR_SOURCE.SOURCE = 'tag';        

COMMIT;
