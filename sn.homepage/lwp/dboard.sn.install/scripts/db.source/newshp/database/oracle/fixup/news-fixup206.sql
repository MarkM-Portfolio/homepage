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


{ORA_GRANT_START} HOMEPAGE.BOARD_MENTIONS {ORA_GRANT_STOP}

COMMIT;

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
------------------------    START Mentions Migration ------------------------
--------------------------------------------------------------------------------- 
CREATE VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS AS (
select  M.BOARD_MENTIONS_ID AS CATEGORY_READER_ID, S.ITEM_AUTHOR_UUID AS READER_ID, 19 AS CATEGORY_TYPE,
	  S.SOURCE AS SOURCE, S.CONTAINER_ID AS CONTAINER_ID, 
	  S.ITEM_ID AS ITEM_ID, S.ITEM_ID AS ROLLUP_ENTRY_ID,
	  0 AS RESOURCE_TYPE, E.CREATION_DATE AS CREATION_DATE, 
	  S.STORY_ID AS STORY_ID, S.SOURCE_TYPE AS SOURCE_TYPE,
	  1 AS USE_IN_ROLLUP, 0 AS IS_NETWORK, 0 AS IS_FOLLOWER,
	  S.EVENT_TIME AS EVENT_TIME, 0 AS IS_STORY_COMM,
	  1 AS IS_BROADCAST, '00000000-0000-0000-0000-000000000000' AS ORGANIZATION_ID, 
	  S.ACTOR_UUID AS ACTOR_UUID,  
	  null AS ROLLUP_AUTHOR_ID, 1 AS IS_VISIBLE,
	null AS MAX_UPDATE_FOR_READER
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
	  1, 0, 0,
	  S.EVENT_TIME, 0,
	  1, '00000000-0000-0000-0000-000000000000', S.ACTOR_UUID, 
	  null, 1, null
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

DECLARE
CURSOR s_cur IS
SELECT *
FROM HOMEPAGE.MENTIONS_NOTIFICATIONS;

TYPE fetch_array IS TABLE OF s_cur%ROWTYPE;
s_array fetch_array;

BEGIN
    OPEN s_cur;
    LOOP
    FETCH s_cur BULK COLLECT INTO s_array LIMIT 1000;

    FORALL i IN 1..s_array.COUNT
    INSERT INTO HOMEPAGE.NR_NOTIFICATION_SENT_READERS VALUES s_array(i);
    COMMIT;

    EXIT WHEN s_cur%NOTFOUND;
    END LOOP;
    CLOSE s_cur;
    COMMIT;
END; 
/
COMMIT;

DROP VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS;
COMMIT;


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 206 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
