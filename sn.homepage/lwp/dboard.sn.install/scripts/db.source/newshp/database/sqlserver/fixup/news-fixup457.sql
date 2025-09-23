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
------------------------ START NEWS FIXUP 457 -----------------------------------
---------------------------------------------------------------------------------

DROP INDEX NR_SL_UD_STR_DELETED_VIS ON HOMEPAGE.NR_AS_SEEDLIST;
GO

CREATE INDEX NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE);
GO

CREATE INDEX NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC);
GO

CREATE INDEX NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC);	
GO

CREATE INDEX NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID);
GO

-- Just sql server
CREATE INDEX NR_SL_UD_STR_DELETED_VIS_DESC
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, STORY_ID, IS_DELETED, IS_VISIBLE);    
GO

CREATE INDEX NR_SL_UD_STR_DELETED_VIS_ASC
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID, IS_DELETED, IS_VISIBLE);	
GO	

---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 457 -------------------------------------
---------------------------------------------------------------------------------
