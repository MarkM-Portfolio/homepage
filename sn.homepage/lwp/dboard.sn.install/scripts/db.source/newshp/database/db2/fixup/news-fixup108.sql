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
------------------------ START NEWS FIXUP 108 -----------------------------------
---------------------------------------------------------------------------------


--74350: Comment of comment in Activity entry still appears in response after parent comment is deleted 
CREATE INDEX HOMEPAGE.STORIES_ITEM_ENTRY_CORR_ID
    ON HOMEPAGE.NR_STORIES (ENTRY_CORRELATION_ID); 

COMMIT;    

--74742: [Performance] AS crawling / indexing is extremely slow 
CREATE INDEX HOMEPAGE.NR_AS_SEEDLIST_IDX 
	ON HOMEPAGE.NR_AS_SEEDLIST (UPDATE_DATE DESC, STORY_ID, IS_DELETED, IS_VISIBLE);  
	
COMMIT;	
	
CREATE UNIQUE INDEX HOMEPAGE.NR_NETWORK_PER_COL_IDX
	ON HOMEPAGE.NR_NETWORK (PERSON_ID, COLLEAGUE_ID);	
	
COMMIT;	
    
---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 108 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
