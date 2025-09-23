-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2014, 2015                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-----------------------------------------------------------------------------------
-- [Work Item 137609] There is no schema for 4 homepage DB indexes [o]
-----------------------------------------------------------------------------------

-- No need to fix for sqlserver

-----------------------------------------------------------------------------------
-- 136967: [fixup513] Switch to use CLOB INLINE for NR_STORIES_CONTENT
-----------------------------------------------------------------------------------

-- There is nothing to do for sqlserver. They are already ok

----------------------------------------------------------------------------------------
-- 137979: [fixup513] Remove un-used READERs tables (pre\post, appGrants, reorg etc.. )
----------------------------------------------------------------------------------------

DROP VIEW HOMEPAGE.NR_CATEGORIES_READERS;
GO


DROP TABLE HOMEPAGE.NR_RESPONSES_READERS;
GO

DROP TABLE HOMEPAGE.NR_PROFILES_READERS;
GO

DROP TABLE HOMEPAGE.NR_COMMUNITIES_READERS;
GO

DROP TABLE HOMEPAGE.NR_ACTIVITIES_READERS;
GO

DROP TABLE HOMEPAGE.NR_BLOGS_READERS;
GO

DROP TABLE HOMEPAGE.NR_BOOKMARKS_READERS;
GO

DROP TABLE HOMEPAGE.NR_FILES_READERS;
GO

DROP TABLE HOMEPAGE.NR_FORUMS_READERS;
GO

DROP TABLE HOMEPAGE.NR_WIKIS_READERS;
GO

DROP TABLE HOMEPAGE.NR_TAGS_READERS;
GO

DROP TABLE HOMEPAGE.NR_DISCOVERY_VIEW;
GO
