-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2011, 2015                                    
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

--START 40252: SPR#WDWU8AAA3P : search cluster nodes will insert dup scheduler task when scheduler table is empty

----------------------------------------
--  HOMEPAGE.LOTUSCONNECTIONSTASK
----------------------------------------

DELETE FROM HOMEPAGE.LOTUSCONNECTIONSTASK
GO    


ALTER TABLE HOMEPAGE.LOTUSCONNECTIONSTASK
    ADD CONSTRAINT UNIQUE_LCT_NAME UNIQUE (NAME)
GO    


--END 40252: SPR#WDWU8AAA3P : search cluster nodes will insert dup scheduler task when scheduler table is empty


--START 40269: PERF, homepage db,  this read sql needs index on sr_index_docs

----------------------------------------
--  HOMEPAGE.SR_INDEX_DOCS
----------------------------------------


CREATE INDEX SR_INDEX_DOCS_LLT4_IDX ON HOMEPAGE.SR_INDEX_DOCS(UPDATE_TIME ASC, ACTION DESC, CRAWLING_VERSION, SERVICE)
GO

--END  40269: PERF, homepage db,  this read sql needs index on sr_index_docs


---------------------------------------------------------------------------------
------------------------ END SEARCH ---------------------------------------------
---------------------------------------------------------------------------------