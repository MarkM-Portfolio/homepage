-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2013, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

CREATE TABLE HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE (
	RECOMMEND_CACHE_USERPROFILE_ID VARCHAR(36) NOT NULL,	
	PERSON_ID VARCHAR(36) NOT NULL,
	CACHED_VALUE_IN_JSON VARCHAR(3000) DEFAULT 'none' NOT NULL,
	UPDATE_DATE TIMESTAMP NOT NULL
)
IN HOMEPAGETABSPACE@

ALTER TABLE HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE
	ADD CONSTRAINT PK_RCUP_ID PRIMARY KEY (RECOMMEND_CACHE_USERPROFILE_ID)@

ALTER TABLE HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE
	ADD CONSTRAINT FK_RCUP_PERSON_ID FOREIGN KEY (PERSON_ID) REFERENCES HOMEPAGE.PERSON (PERSON_ID)@

CREATE UNIQUE INDEX HOMEPAGE.SR_UNIQUE_RCUP_PERSON_ID  
	ON HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE (PERSON_ID)@
	
CREATE UNIQUE INDEX HOMEPAGE.SR_UNIQUE_RCUP_PERSON_ID_UDATE  
	ON HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE (PERSON_ID, UPDATE_DATE DESC)@	
	
RUNSTATS ON TABLE HOMEPAGE.SR_RECOMMEND_CACHE_USERPROFILE@

