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

CREATE INDEX HOMEPAGE.EMD_RES_PREF_PER_ID
    ON HOMEPAGE.EMD_RESOURCE_PREF (PERSON_ID);

runstats on table HOMEPAGE.EMD_RESOURCE_PREF with distribution and detailed indexes all allow write access;

ALTER TABLE HOMEPAGE.NR_NEWS_STATUS_CONTENT
ADD UPDATE_DATE TIMESTAMP;

reorg table HOMEPAGE.NR_NEWS_STATUS_CONTENT use NEWS4TMPTABSPACE;

--------------------------------------------------------------------------------------------------
--------------- START: UPDATE CONTENT FOR NR_NEWS_STATUS_CONTENT ---------------------------------
--------------------------------------------------------------------------------------------------

-- 1) fixup49 CREATION_DATE
UPDATE HOMEPAGE.NR_NEWS_STATUS_CONTENT  NR_NEWS_STATUS_CONTENT SET CREATION_DATE = 
    (
        SELECT  DISTINCT(NR_NEWS_STATUS_NETWORK.CREATION_DATE)
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
        WHERE   NR_NEWS_STATUS_NETWORK.ITEM_ID = NR_NEWS_STATUS_CONTENT.ITEM_ID
    );

COMMIT;    

-- 2) fixup49 UPDATE_DATE
UPDATE HOMEPAGE.NR_NEWS_STATUS_CONTENT  NR_NEWS_STATUS_CONTENT SET UPDATE_DATE = 
    (
        SELECT  DISTINCT(NR_NEWS_STATUS_NETWORK.UPDATE_DATE)
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
        WHERE   NR_NEWS_STATUS_NETWORK.ITEM_ID = NR_NEWS_STATUS_CONTENT.ITEM_ID
    );

COMMIT;    

-- 3) fixup49 TARGET_SUBJECT_ID
UPDATE HOMEPAGE.NR_NEWS_STATUS_CONTENT  NR_NEWS_STATUS_CONTENT SET TARGET_SUBJECT_ID = 
    (
        SELECT  DISTINCT(NR_NEWS_STATUS_NETWORK.TARGET_SUBJECT_ID)
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
        WHERE   NR_NEWS_STATUS_NETWORK.ITEM_ID = NR_NEWS_STATUS_CONTENT.ITEM_ID
    );

COMMIT;    

-- 4) fixup49  ACTOR_UUID
UPDATE HOMEPAGE.NR_NEWS_STATUS_CONTENT  NR_NEWS_STATUS_CONTENT SET  ACTOR_UUID = 
    (
        SELECT  DISTINCT(NR_NEWS_STATUS_NETWORK.ACTOR_UUID)
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
        WHERE   NR_NEWS_STATUS_NETWORK.ITEM_ID = NR_NEWS_STATUS_CONTENT.ITEM_ID
    );

COMMIT;    

-- 5) fixup49  ITEM_URL
UPDATE HOMEPAGE.NR_NEWS_STATUS_CONTENT  NR_NEWS_STATUS_CONTENT SET  ITEM_URL = 
    (
        SELECT  DISTINCT(NR_NEWS_STATUS_NETWORK.ITEM_URL)
        FROM    HOMEPAGE.NR_NEWS_STATUS_NETWORK NR_NEWS_STATUS_NETWORK
        WHERE   NR_NEWS_STATUS_NETWORK.ITEM_ID = NR_NEWS_STATUS_CONTENT.ITEM_ID
    );

COMMIT;    

--------------------------------------------------------------------------------------------------
--------------- END: UPDATE CONTENT FOR NR_NEWS_STATUS_CONTENT -----------------------------------
--------------------------------------------------------------------------------------------------

