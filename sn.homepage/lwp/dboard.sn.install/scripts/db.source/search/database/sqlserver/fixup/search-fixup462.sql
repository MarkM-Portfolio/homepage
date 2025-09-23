-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2013, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

---------------------------------------------------------------------------------
------------------------ START SEARCH -------------------------------------------
---------------------------------------------------------------------------------

--101536: [fixup462] [Search] Remove on DELETE CASCADE  for: FK_*_STAT_ID
ALTER TABLE HOMEPAGE.SR_STRING_STATS DROP CONSTRAINT FK_STR_STAT_ID;
GO

ALTER TABLE HOMEPAGE.SR_NUMBER_STATS DROP CONSTRAINT FK_NUM_STAT_ID;
GO

ALTER TABLE HOMEPAGE.SR_TIMER_STATS DROP CONSTRAINT FK_TMR_STAT_ID;
GO

ALTER TABLE HOMEPAGE.SR_STRING_STATS ADD CONSTRAINT FK_STR_STAT_ID FOREIGN KEY (STAT_ID) REFERENCES HOMEPAGE.SR_STATS (STAT_ID);
GO

ALTER TABLE HOMEPAGE.SR_NUMBER_STATS ADD CONSTRAINT FK_NUM_STAT_ID FOREIGN KEY (STAT_ID) REFERENCES HOMEPAGE.SR_STATS(STAT_ID);
GO

ALTER TABLE HOMEPAGE.SR_TIMER_STATS ADD CONSTRAINT FK_TMR_STAT_ID FOREIGN KEY (STAT_ID) REFERENCES HOMEPAGE.SR_STATS(STAT_ID);
GO

CREATE INDEX SR_STRING_STAT_IDX ON HOMEPAGE.SR_STRING_STATS(STAT_ID);

CREATE INDEX SR_NUMBER_STAT_IDX ON HOMEPAGE.SR_NUMBER_STATS(STAT_ID);

CREATE INDEX SR_TIMER_STAT_IDX ON HOMEPAGE.SR_TIMER_STATS(STAT_ID);

GO

---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------
