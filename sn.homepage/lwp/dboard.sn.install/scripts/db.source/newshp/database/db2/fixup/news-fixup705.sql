-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015, 2016                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- Defect 168360:
-- -----------------------------------------------------------------

DROP INDEX HOMEPAGE.ENTRIES_ROLLUP_ACT_AUT_IDX@
COMMIT@

CREATE INDEX HOMEPAGE.ENTR_ROLLUP_ENTRYACT_IDX 
	ON HOMEPAGE.NR_ENTRIES_ROLLUP_ACTION (ROLLUP_ENTRY_ID, ACTION)@
COMMIT@

CREATE INDEX HOMEPAGE.ENTR_ROLLUP_PER_ENTRUDATE_IDX 
	ON HOMEPAGE.NR_ENTRIES_ROLLUP_PERSON (ENTRY_ROLLUP_ACTION_ID, UPDATE_DATE DESC)@
COMMIT@
