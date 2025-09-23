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


{SQL_GRANT_START} HOMEPAGE.BOARD_MENTIONS {SQL_GRANT_STOP}

GO

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee1', 'mentionee1', 'profilePhoto', 'mentionee1', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee2', 'mentionee2', 'profilePhoto', 'mentionee2', 1);

INSERT INTO HOMEPAGE.NR_TEMPLATE (TEMPLATE_ID, NAME, FORMAT, DATA_SOURCE_STRING, NO_VALUES) 
VALUES ('mentionee3', 'mentionee3', 'profilePhoto', 'mentionee3', 1);

GO

UPDATE HOMEPAGE.PERSON SET MEMBER_TYPE = 3 WHERE PERSON_ID = '00000000-0000-0000-0000-000000000000';

GO

--------------------------------------------------------------------------------- 
--------------------------    Start Mentions Migration --------------------------
--------------------------------------------------------------------------------- 
CREATE VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS AS (
select  M.BOARD_MENTIONS_ID AS CATEGORY_READER_ID, S.ITEM_AUTHOR_UUID AS READER_ID, 19 AS CATEGORY_TYPE,
	  S.SOURCE AS SOURCE, S.CONTAINER_ID AS CONTAINER_ID, 
	  S.ITEM_ID AS ITEM_ID, S.ITEM_ID AS ROLLUP_ENTRY_ID,
	  0 AS RESOURCE_TYPE, E.CREATION_DATE AS CREATION_DATE, 
	  S.STORY_ID AS STORY_ID, S.SOURCE_TYPE AS SOURCE_TYPE,
	  1 AS USE_IN_ROLLUP, 0 AS IS_NETWORK, 0 AS IS_FOLLOWER,
	  S.EVENT_TIME AS EVENT_TIME, 0 AS IS_STORY_COMM,
	  1 AS IS_BROADCAST, '00000000-0000-0000-0000-000000000000' AS ORGANIZATION_ID, S.ACTOR_UUID AS ACTOR_UUID,  
	  null AS ROLLUP_AUTHOR_ID, 1 AS IS_VISIBLE,
	null AS MAX_UPDATE_FOR_READER
from          HOMEPAGE.HOMEPAGE.BOARD_ENTRIES E,
              HOMEPAGE.HOMEPAGE.NR_STORIES S,
              HOMEPAGE.HOMEPAGE.BOARD_MENTIONS M
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
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.MENTIONS_NOTIFICATIONS TO HOMEPAGEUSER;
GO
COMMIT;

---------------------------
-- Enable xp cmdshell
---------------------------
EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE
---------------------------

BEGIN TRANSACTION
GO

declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = 'C:\format.fmt'
set @data_file = 'C:\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.MENTIONS_NOTIFICATIONS'
set @out_table = 'HOMEPAGE.NR_NOTIFICATION_SENT_READERS'

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command

-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -U HOMEPAGEUSER -P $(password)'
EXEC master..xp_cmdshell @backup_command

--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

DROP VIEW HOMEPAGE.MENTIONS_NOTIFICATIONS;
GO
COMMIT;

---------------------------
-- Disable xp cmdshell
---------------------------
EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 0
RECONFIGURE
---------------------------

BEGIN TRANSACTION
GO

--------------------------------------------------------------------------------- 
---------------------------    End Mentions Migration ---------------------------
--------------------------------------------------------------------------------- 


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 206 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
