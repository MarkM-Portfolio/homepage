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


----------------------------------------------------------------------------------
-- 147873: SC: Network Followers view NOT displaying followers. IC20 delivery
----------------------------------------------------------------------------------

UPDATE HOMEPAGE.BOARD_ENTRIES SET 	
	CREATION_DATE_DISPLAY = CREATION_DATE, 
	UPDATE_DATE_DISPLAY = UPDATE_DATE, 
	ACTOR_UUID_DISPLAY = ACTOR_UUID, 
	LAST_CONTRIBUTOR_ID_DISPLAY = LAST_CONTRIBUTOR_ID;
GO

UPDATE HOMEPAGE.BOARD_COMMENTS SET 
	AUTHOR_UUID_DISPLAY = ACTOR_UUID, 
	LAST_CONTRIBUTOR_ID_DISPLAY = LAST_CONTRIBUTOR_ID, 
	CREATION_DATE_DISPLAY = CREATION_DATE, 
	UPDATE_DATE_DISPLAY = UPDATE_DATE;							
GO						

UPDATE HOMEPAGE.BOARD_RECOMMENDATIONS SET 	
	RECOMMENDER_ID_DISPLAY = RECOMMENDER_ID, 
	CREATION_DATE_DISPLAY = CREATION_DATE;									
GO							

DROP INDEX CREATION_ITEM_IDX ON HOMEPAGE.BOARD_ENTRIES;
GO

CREATE INDEX CREATION_ITEM_IDX 
	ON HOMEPAGE.BOARD_ENTRIES (CREATION_DATE_DISPLAY DESC, ITEM_ID DESC);
GO	
