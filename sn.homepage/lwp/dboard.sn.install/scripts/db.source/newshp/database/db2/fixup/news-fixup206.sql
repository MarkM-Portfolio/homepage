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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 206 -----------------------------------
---------------------------------------------------------------------------------


{DB2_GRANT_START} HOMEPAGE.BOARD_MENTIONS {DB2_GRANT_STOP}

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee1', 'mentionee1', 'profilePhoto', 'mentionee1', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee2', 'mentionee2', 'profilePhoto', 'mentionee2', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee3', 'mentionee3', 'profilePhoto', 'mentionee3', 1);

COMMIT;	

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 3 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';
COMMIT;

--------------------------------------------------------------------------------- 
---------------------------  Start Mentions Migration   -------------------------
--------------------------------------------------------------------------------- 
CREATE VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS AS (
select M.BOARD_MENTIONS_ID AS CATEGORY_READER_ID, S.ITEM_AUTHOR_UUID AS READER_ID, 19 AS CATEGORY_TYPE,
	  S.SOURCE AS SOURCE, S.CONTAINER_ID AS CONTAINER_ID, 
	  S.ITEM_ID AS ITEM_ID, S.ITEM_ID AS ROLLUP_ENTRY_ID,
	  0 AS RESOURCE_TYPE, E.CREATION_DATE AS CREATION_DATE, 
	  S.STORY_ID AS STORY_ID, S.SOURCE_TYPE AS SOURCE_TYPE,
	  1 AS USE_IN_ROLLUP, 0 AS IS_NETWORK, 0 AS IS_FOLLOWER,
	  S.EVENT_TIME AS EVENT_TIME, 0 AS IS_STORY_COMM,
	  1 AS IS_BROADCAST, '00000000-0000-0000-0000-000000000000' AS ORGANIZATION_ID,
	  S.ACTOR_UUID AS ACTOR_UUID, 
	  1 AS IS_VISIBLE, null AS ROLLUP_AUTHOR_ID
from          HOMEPAGE.BOARD_ENTRIES E,
              HOMEPAGE.NR_STORIES S,
              HOMEPAGE.BOARD_MENTIONS M
WHERE S.ITEM_ID = E.ITEM_ID
AND S.ITEM_ID = M.ITEM_ID
 AND S.EVENT_NAME IN('community.wall.notification.mention',
'community.wall.comment.notification.mention',
'profiles.status.notification.mention',
'profiles.wallpost.notification.mention',
'profiles.status.comment.notification.mention',
'profiles.wallpost.comment.notification.mention')
UNION
select M.BOARD_MENTIONS_ID, S.ITEM_AUTHOR_UUID, 19,
	  S.SOURCE, S.CONTAINER_ID, 
	  S.ITEM_ID, S.ITEM_ID,
	  0, C.CREATION_DATE, 
	  S.STORY_ID, S.SOURCE_TYPE,
	  1, 0 AS IS_NETWORK, 0,
	  S.EVENT_TIME, 0,
	  1, '00000000-0000-0000-0000-000000000000', S.ACTOR_UUID, 
	  1, null
from          HOMEPAGE.BOARD_COMMENTS C,
              HOMEPAGE.NR_STORIES S,
              HOMEPAGE.BOARD_MENTIONS M
WHERE S.ITEM_ID = C.ITEM_ID
AND S.ITEM_ID = M.ITEM_ID
 AND S.EVENT_NAME IN('community.wall.notification.mention',
'community.wall.comment.notification.mention',
'profiles.status.notification.mention',
'profiles.wallpost.notification.mention',
'profiles.status.comment.notification.mention',
'profiles.wallpost.comment.notification.mention')
);
COMMIT;

EXPORT TO data.tmp.ixf OF IXF
METHOD N (
CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID,
ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE,
STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER,
EVENT_TIME, IS_STORY_COMM,
IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID)
SELECT CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID,
ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE,
STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER,
EVENT_TIME, IS_STORY_COMM,
IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID
FROM HOMEPAGE.MENTIONS_NOTIFICATIONS;
COMMIT;

IMPORT FROM data.tmp.ixf OF IXF
METHOD N (
CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID,
ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE,
STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER,
EVENT_TIME, IS_STORY_COMM,
IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID)
ALLOW NO ACCESS COMMITCOUNT 2000
INSERT INTO HOMEPAGE.NR_NOTIFICATION_SENT_READERS (
CATEGORY_READER_ID, READER_ID, CATEGORY_TYPE, SOURCE, CONTAINER_ID,
ITEM_ID, ROLLUP_ENTRY_ID, RESOURCE_TYPE, CREATION_DATE,
STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER,
EVENT_TIME, IS_STORY_COMM,
IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, IS_VISIBLE, ROLLUP_AUTHOR_ID);
COMMIT;

DROP VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS;
COMMIT;

--------------------------------------------------------------------------------- 
---------------------------    End Mentions Migration ---------------------------
--------------------------------------------------------------------------------- 


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 206 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
