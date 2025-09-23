-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
-- SPR# MPML7UVM9X
-- Missing index on SR_FILESCONTENT causes full table scan
CREATE INDEX "HOMEPAGE"."SR_FILESCONTENT_IS_CURRENT_IDX" 
	ON HOMEPAGE.SR_FILESCONTENT(IS_CURRENT) TABLESPACE "HOMEPAGEINDEXTABSPACE";

-- SPR #SPII7UCHBD
-- Missing index on NR_NEWS_RECORDS during the watchlist result create some performance issue
CREATE INDEX "HOMEPAGE"."NR_NEWS_WATCHLIST_IDX"
	ON HOMEPAGE.NR_NEWS_RECORDS(CONTAINER_ID,SOURCE,IS_CONTAINER,READER_ID) TABLESPACE "NEWSINDEXTABSPACE";

-- LL requirement
CREATE VIEW HOMEPAGE.SNCORE_PERSON (SNC_INTERNAL_ID, SNC_IDKEY, SNC_EMAIL_LOWER, SNC_DISPLAY_NAME) 
    AS SELECT PERSON_ID, EXID, lower(USER_MAIL) USER_MAIL, DISPLAYNAME FROM HOMEPAGE.PERSON;
    
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 2.5.1
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 23 , RELEASEVER = '2.5.1.0'
WHERE   DBSCHEMAVER = 21;


COMMIT;
--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;