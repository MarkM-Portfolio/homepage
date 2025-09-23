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

CREATE INDEX HOMEPAGE.BRD_IS_DEL_CDATE_ACT_ENT
	ON HOMEPAGE.BOARD_ENTRIES (SL_IS_DELETED, CREATION_DATE DESC, ACTOR_UUID, ENTRY_TYPE)@
	
COMMIT@

runstats on table HOMEPAGE.BOARD_ENTRIES with distribution and detailed indexes all allow write access@

COMMIT@
