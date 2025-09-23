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

-- 1) Adding the new resource bookmark for EMD:
INSERT INTO HOMEPAGE.NR_RESOURCE_TYPE (RESOURCE_TYPE_ID, RESOURCE_TYPE, RESOURCE_TYPE_NAME, RESOURCE_TYPE_DESC)
VALUES ('bookmarks_0f1xc9cax4cc4x8cdb0bx51f2d', 14, '%bookmarks', 'bookmarks');

-- 2) move stories to the new tables:

----------------------------------------------------------------------------------
-- NR_COMM_PERSON_FOLLOW
----------------------------------------------------------------------------------

-- INSERT 1 RECORD FOR EACH PERSON
INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW (COMM_PERSON_FOLLOW_ID, PERSON_ID, PERSON_COMMUNITY_ID)
    SELECT  MAX(COMM_FOLLOW_ID) COMM_PERSON_FOLLOW_ID,
            PERSON_ID PERSON_ID,         
            PERSON_ID PERSON_COMMUNITY_ID
    FROM    HOMEPAGE.NR_COMM_FOLLOW NR_COMM_FOLLOW
    GROUP BY PERSON_ID;

COMMIT;

-- RENAME TO AVOID PK CONFLICTS
UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW SET COMM_PERSON_FOLLOW_ID = SUBSTR(COMM_PERSON_FOLLOW_ID,1,18) || SUBSTR(PERSON_ID,1,18);

COMMIT;

-- INSERT THE OLD EXISTING RECORDS
INSERT INTO HOMEPAGE.NR_COMM_PERSON_FOLLOW (COMM_PERSON_FOLLOW_ID, PERSON_ID, PERSON_COMMUNITY_ID)
    SELECT  COMM_FOLLOW_ID COMM_PERSON_FOLLOW_ID,
            PERSON_ID PERSON_ID,         
            COMMUNITY_ID PERSON_COMMUNITY_ID
    FROM HOMEPAGE.NR_COMM_FOLLOW NR_COMM_FOLLOW;

COMMIT;

----------------------------------------------------------------------------------
-- NR_COMM_PERSON_FOLLOW
----------------------------------------------------------------------------------

-- COPYING BACK THE OLD NR_COMM_STORIES
INSERT INTO HOMEPAGE.NR_COMM_PERSON_STORIES (COMM_PER_STORY_ID, COMM_PER_READER_ID, CONTAINER_ID, ITEM_ID, RESOURCE_TYPE, CATEGORY_TYPE, CREATION_DATE, SOURCE, STORY_ID)
    SELECT  NR_COMM_STORIES.COMM_STORY_ID COMM_PER_STORY_ID,
            NR_COMM_STORIES.COMMUNITY_ID COMM_PER_READER_ID, 
            NR_COMM_STORIES.CONTAINER_ID CONTAINER_ID, 
            NR_COMM_STORIES.ITEM_ID ITEM_ID, 
            NR_COMM_STORIES.RESOURCE_TYPE RESOURCE_TYPE, 
            2 CATEGORY_TYPE, 
            NR_COMM_STORIES.CREATION_DATE CREATION_DATE, 
            'communities' SOURCE, 
            NR_COMM_STORIES.STORY_ID STORY_ID
    FROM HOMEPAGE.NR_COMM_STORIES NR_COMM_STORIES;

COMMIT;

-- COPYING BACK THE OLD NR_FOLLOWED_STORIES
INSERT INTO HOMEPAGE.NR_COMM_PERSON_STORIES (COMM_PER_STORY_ID, COMM_PER_READER_ID, CONTAINER_ID, ITEM_ID, RESOURCE_TYPE, CATEGORY_TYPE, CREATION_DATE, SOURCE, STORY_ID)
    SELECT  NR_FOLLOWED_STORIES.FOLLOWED_STORY_ID COMM_PER_STORY_ID,
            NR_FOLLOWED_STORIES.READER_ID COMM_PER_READER_ID, 
            NR_FOLLOWED_STORIES.CONTAINER_ID CONTAINER_ID, 
            NR_FOLLOWED_STORIES.ITEM_ID ITEM_ID, 
            NR_FOLLOWED_STORIES.RESOURCE_TYPE RESOURCE_TYPE, 
            NR_FOLLOWED_STORIES.CATEGORY_TYPE CATEGORY_TYPE,  
            NR_FOLLOWED_STORIES.CREATION_DATE CREATION_DATE, 
            NR_FOLLOWED_STORIES.SOURCE SOURCE, 
            NR_FOLLOWED_STORIES.STORY_ID STORY_ID
    FROM HOMEPAGE.NR_FOLLOWED_STORIES NR_FOLLOWED_STORIES;

COMMIT;      