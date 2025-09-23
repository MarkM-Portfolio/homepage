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

-- 111032: Backport to 4.5CR4: [Must for August] [Performance|DB2] ActivityStream search indexing is very slow (90881)
-- Changes based on news-fixup457.sql
DROP INDEX HOMEPAGE.NR_SL_UD_STR_DELETED_VIS;
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED_VIS
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, IS_DELETED, IS_VISIBLE);
COMMIT;
	
CREATE INDEX HOMEPAGE.NR_SL_UD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC);
COMMIT;	
	
CREATE INDEX HOMEPAGE.NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC);	
COMMIT;

CREATE INDEX HOMEPAGE.NR_SL_UD_STR
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE ASC, STORY_ID);
COMMIT;	
  