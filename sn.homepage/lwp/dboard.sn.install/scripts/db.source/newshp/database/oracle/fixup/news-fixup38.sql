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

----------------------------------------------------------------------
-- 0) ADDING UNIQUE CONSTRAINTS FOR NR_FOLLOWS TABLE
----------------------------------------------------------------------
CREATE UNIQUE INDEX HOMEPAGE.NR_FOLLOWS_IDX
    ON HOMEPAGE.NR_FOLLOWS (RESOURCE_ID, PERSON_ID) TABLESPACE "NEWSINDEXTABSPACE";

----------------------------------------------------------------------
-- 1) REMOVING UN-USED TABLE
----------------------------------------------------------------------
DROP TABLE HOMEPAGE.NR_NEWS_STORY;
DROP TABLE HOMEPAGE.NR_NEWS_WATCHLIST;
DROP TABLE HOMEPAGE.NR_NEWS_TOP_UPDATES;
DROP TABLE HOMEPAGE.NR_FOLLOW;
DROP TABLE HOMEPAGE.NR_GROUP_SOURCE;
DROP TABLE HOMEPAGE.NR_PERSON_SOURCE;
DROP TABLE HOMEPAGE.NR_GROUP;
DROP TABLE HOMEPAGE.NR_GROUP_TYPE;
DROP TABLE HOMEPAGE.NR_EVENT_RECORDS;
-- DROP TABLE HOMEPAGE.NR_NEWS_RECORDS;
-- DROP TABLE HOMEPAGE.NR_SUBSCRIPTION;
-- DROP TABLE HOMEPAGE.NR_SOURCE;

