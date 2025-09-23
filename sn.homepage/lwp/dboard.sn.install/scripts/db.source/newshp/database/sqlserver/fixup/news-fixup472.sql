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

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW DROP CONSTRAINT PK_CV_READERS;

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW DROP CONSTRAINT FK_CV_READERS_STR;

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW DROP CONSTRAINT FK_CV_READERS_ORG_ID;

DROP  INDEX COMMUNITIES_VIEW_STR_IX ON HOMEPAGE.NR_COMMUNITIES_VIEW; 


DROP  INDEX COMMUNITIES_VIEW_ITM_IX ON HOMEPAGE.NR_COMMUNITIES_VIEW; 


DROP  INDEX COMMUNITIES_VIEW_CD_IX ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_RLL_IIX ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_STR_RDR ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_DEL_SERV_IX ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_ROLLUP_IIIX ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_RLL_BRD_VVI ON HOMEPAGE.NR_COMMUNITIES_VIEW;


DROP  INDEX COMMUNITIES_VIEW_COMM_IDX ON HOMEPAGE.NR_COMMUNITIES_VIEW;

GO

-- warning c&p: just in sqlserver the order for the last three columns is: ROLLUP_AUTHOR_ID, IS_VISIBLE, COMMUNITY_ID
CREATE VIEW HOMEPAGE.NR_COMMUNITIES_READERS_IN AS (
	SELECT 
    	CATEGORY_READER_ID, READER_ID, 23 CATEGORY_TYPE, SOURCE, CONTAINER_ID, ITEM_ID, ROLLUP_ENTRY_ID, 
		RESOURCE_TYPE, CREATION_DATE, STORY_ID, SOURCE_TYPE, USE_IN_ROLLUP, IS_NETWORK, IS_FOLLOWER, EVENT_TIME, IS_STORY_COMM, 
		IS_BROADCAST, ORGANIZATION_ID, ACTOR_UUID, ROLLUP_AUTHOR_ID, IS_VISIBLE, COMMUNITY_ID
    FROM HOMEPAGE.NR_COMMUNITIES_READERS
);
GO

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.NR_COMMUNITIES_READERS_IN TO HOMEPAGEUSER;
GO

-- MUST COMMIT BEFORE ENABLING CMDSHELL --
COMMIT

-----------------------------------
-- Enable xp_cmdshell
----------------------------------

EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 1
RECONFIGURE
----------------------------------

BEGIN TRANSACTION
GO

DECLARE 	@cmd varchar(255), 	@rc int, @tmpLocation varchar(255)
select 		@cmd='echo %TMP%'
create table #output (output varchar(255))
insert #output EXEC @rc = master..xp_cmdshell @cmd
select @tmpLocation=output from #output where output is not null
drop table #output 


declare @backup_command varchar(8000), @format_command varchar(8000), @bulk_insert_command varchar(8000) 
declare @format_file varchar(8000), @data_file varchar(8000)
set @format_file = @tmpLocation +'\format.fmt'
set @data_file = @tmpLocation + '\data.dat'

declare @in_view varchar(8000), @out_table varchar(8000)
set @in_view = 'HOMEPAGE.HOMEPAGE.NR_COMMUNITIES_READERS_IN' -- The view name need also to be specified with the database name and the schema name
set @out_table = 'HOMEPAGE.NR_COMMUNITIES_VIEW' -- Schema name and table name

-- create the format file
set @format_command = 'bcp '+@in_view+' format nul -S '+@@servername+' -T -n -f "'+@format_file+'"'
EXEC master..xp_cmdshell @format_command

-- backup the data
set @backup_command = 'bcp '+@in_view+' out "'+@data_file+'" -n -S '+@@servername+' -T'
EXEC master..xp_cmdshell @backup_command

--insert back the record
set @bulk_insert_command = 'BULK INSERT '+@out_table+' FROM "'+@data_file+'" WITH (DATAFILETYPE="native", FORMATFILE="'+@format_file+'")'
EXEC (@bulk_insert_command) 
GO

COMMIT;
GO

-----------------------------------
-- Disable xp_cmdshell
----------------------------------

EXEC master.dbo.sp_configure 'show advanced options', 1
RECONFIGURE
EXEC master.dbo.sp_configure 'xp_cmdshell', 0
RECONFIGURE
----------------------------------

BEGIN TRANSACTION
GO

ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW
    ADD CONSTRAINT PK_CV_READERS PRIMARY KEY(CATEGORY_READER_ID);
    
ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW 
	ADD CONSTRAINT FK_CV_READERS_STR FOREIGN KEY (STORY_ID)
	REFERENCES HOMEPAGE.NR_STORIES (STORY_ID); 
	
ALTER TABLE HOMEPAGE.NR_COMMUNITIES_VIEW ADD CONSTRAINT FK_CV_READERS_ORG_ID FOREIGN KEY (ORGANIZATION_ID) REFERENCES HOMEPAGE.MT_ORGANIZATION (ORGANIZATION_ID);

--  [start indexes] NR_COMMUNITIES_VIEW
CREATE  INDEX COMMUNITIES_VIEW_STR_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (STORY_ID); 
GO

CREATE  INDEX COMMUNITIES_VIEW_ITM_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (ITEM_ID); 
GO

CREATE  INDEX COMMUNITIES_VIEW_CD_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (STORY_ID, CREATION_DATE DESC); 
GO

CREATE  INDEX COMMUNITIES_VIEW_RLL_IIX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (READER_ID, IS_VISIBLE, CREATION_DATE DESC); 
GO

CREATE  INDEX COMMUNITIES_VIEW_STR_RDR 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (STORY_ID, READER_ID); 
GO

CREATE  INDEX COMMUNITIES_VIEW_DEL_SERV_IX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (CREATION_DATE DESC); 
GO

CREATE  INDEX COMMUNITIES_VIEW_ROLLUP_IIIX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (ROLLUP_ENTRY_ID, READER_ID, IS_VISIBLE, CREATION_DATE DESC); 
GO

CREATE  INDEX COMMUNITIES_VIEW_RLL_BRD_VVI 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (ROLLUP_AUTHOR_ID, IS_BROADCAST, IS_VISIBLE); 
GO


CREATE  INDEX COMMUNITIES_VIEW_COMM_IDX 
 	ON HOMEPAGE.NR_COMMUNITIES_VIEW (COMMUNITY_ID); 
GO


