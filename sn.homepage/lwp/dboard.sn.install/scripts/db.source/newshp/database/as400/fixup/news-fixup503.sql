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

----------------------------------------------------------------------------------
-- 147873: SC: Network Followers view NOT displaying followers. IC20 delivery
----------------------------------------------------------------------------------

-- 1)
/*
EXPORT TO data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, CREATION_DATE,  UPDATE_DATE,  ACTOR_UUID,  LAST_CONTRIBUTOR_ID
)
 SELECT
       ENTRY_ID, CREATION_DATE,  UPDATE_DATE,  ACTOR_UUID,  LAST_CONTRIBUTOR_ID
 FROM HOMEPAGE.BOARD_ENTRIES ;

COMMIT;

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
        ENTRY_ID, CREATION_DATE,  UPDATE_DATE,  ACTOR_UUID,  LAST_CONTRIBUTOR_ID
) COMMITCOUNT 1000
        INSERT_UPDATE INTO HOMEPAGE.BOARD_ENTRIES
                (
                ENTRY_ID, CREATION_DATE_DISPLAY, UPDATE_DATE_DISPLAY, ACTOR_UUID_DISPLAY, LAST_CONTRIBUTOR_ID_DISPLAY
                );
COMMIT;
*/
UPDATE HOMEPAGE.BOARD_ENTRIES SET CREATION_DATE_DISPLAY = CREATION_DATE, UPDATE_DATE_DISPLAY = UPDATE_DATE, ACTOR_UUID_DISPLAY = ACTOR_UUID, LAST_CONTRIBUTOR_ID_DISPLAY = LAST_CONTRIBUTOR_ID;
COMMIT;

--2)
/*
EXPORT TO data.tmp.ixf OF IXF METHOD N (
        COMMENT_ID, ACTOR_UUID,  LAST_CONTRIBUTOR_ID,  CREATION_DATE,  UPDATE_DATE
)
 SELECT
       COMMENT_ID, ACTOR_UUID,  LAST_CONTRIBUTOR_ID,  CREATION_DATE,  UPDATE_DATE
 FROM HOMEPAGE.BOARD_COMMENTS ;

COMMIT;

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
        COMMENT_ID, ACTOR_UUID,  LAST_CONTRIBUTOR_ID,  CREATION_DATE,  UPDATE_DATE
) COMMITCOUNT 1000
        INSERT_UPDATE INTO HOMEPAGE.BOARD_COMMENTS
                (
                COMMENT_ID, AUTHOR_UUID_DISPLAY, LAST_CONTRIBUTOR_ID_DISPLAY, CREATION_DATE_DISPLAY, UPDATE_DATE_DISPLAY
                );
COMMIT;
*/
UPDATE HOMEPAGE.BOARD_COMMENTS SET AUTHOR_UUID_DISPLAY = ACTOR_UUID, LAST_CONTRIBUTOR_ID_DISPLAY = LAST_CONTRIBUTOR_ID, CREATION_DATE_DISPLAY = CREATION_DATE, UPDATE_DATE_DISPLAY = UPDATE_DATE;
COMMIT;

-- 3)
/*
EXPORT TO data.tmp.ixf OF IXF METHOD N (
        RECOMMENDATION_ID, RECOMMENDER_ID,  CREATION_DATE
)
 SELECT
       RECOMMENDATION_ID, RECOMMENDER_ID,  CREATION_DATE
 FROM HOMEPAGE.BOARD_RECOMMENDATIONS ;

COMMIT;

IMPORT FROM data.tmp.ixf OF IXF METHOD N (
        RECOMMENDATION_ID, RECOMMENDER_ID,  CREATION_DATE
) COMMITCOUNT 1000
        INSERT_UPDATE INTO HOMEPAGE.BOARD_RECOMMENDATIONS
                (
                RECOMMENDATION_ID, RECOMMENDER_ID_DISPLAY, CREATION_DATE_DISPLAY
                );
COMMIT;
*/
UPDATE HOMEPAGE.BOARD_RECOMMENDATIONS SET RECOMMENDER_ID_DISPLAY = RECOMMENDER_ID, CREATION_DATE_DISPLAY = CREATION_DATE;

DROP INDEX HOMEPAGE.CREATION_ITEM_IDX;

CREATE INDEX HOMEPAGE.CREATION_ITEM_IDX 
	ON HOMEPAGE.BOARD_ENTRIES (CREATION_DATE_DISPLAY DESC, ITEM_ID DESC);
	
COMMIT;	

