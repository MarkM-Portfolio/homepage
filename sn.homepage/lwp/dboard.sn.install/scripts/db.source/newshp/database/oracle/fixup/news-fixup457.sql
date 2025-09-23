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

DROP INDEX HOMEPAGE.NR_SL_UD_STR_DELETED_VIS;
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;
	
CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;	
	
CREATE INDEX HOMEPAGE.NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;	
	
CREATE INDEX HOMEPAGE.NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;

-- moving two existing indexes to the righ tabspace (JUST ORACLE)
DROP INDEX HOMEPAGE.NR_SL_STR_UNIQUE;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_ITEM_ID_IX;
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.NR_SL_STR_UNIQUE
	ON HOMEPAGE.NR_AS_SEEDLIST(STORY_ID) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_ITEM_ID_IX
	ON HOMEPAGE.NR_AS_SEEDLIST (ITEM_ID) TABLESPACE "NEWSINDEXTABSPACE";	
COMMIT;

---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 457 -------------------------------------
---------------------------------------------------------------------------------
