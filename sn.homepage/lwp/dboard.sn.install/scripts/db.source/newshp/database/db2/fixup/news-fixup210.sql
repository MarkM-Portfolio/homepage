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

-- 87913: AS-Search Seedlist retrieval is very slow while initial crawling on W3 production
-- Removing four v4.0 existing indexes: HOMEPAGE.NR_SL_UD_DELETED_VIS, HOMEPAGE.NR_SL_UD_DELETED, HOMEPAGE.NR_SL_CD_DELETED, HOMEPAGE.NR_SL_UD_STR 
-- Adding: CREATE INDEX HOMEPAGE.NR_SL_UD_STR_DELETED_VIS ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE, STORY_ID, IS_DELETED, IS_VISIBLE); 
DROP INDEX HOMEPAGE.NR_SL_UD_DELETED_VIS;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_UD_DELETED;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_CD_DELETED;
COMMIT;

DROP INDEX HOMEPAGE.NR_SL_UD_STR;
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_STR_DELETED_VIS 
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, STORY_ID, IS_DELETED, IS_VISIBLE); 
COMMIT;

 

  
  
  