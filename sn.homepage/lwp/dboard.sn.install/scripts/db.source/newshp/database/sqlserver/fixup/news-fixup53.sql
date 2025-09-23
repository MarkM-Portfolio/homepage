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

CREATE UNIQUE INDEX NR_STORIES_ER_UUID
	ON HOMEPAGE.NR_STORIES(EVENT_RECORD_UUID);
GO

DROP INDEX NR_STORIES_CREAT_IS_COM ON HOMEPAGE.NR_STORIES;
GO

CREATE INDEX NR_STORIES_DATE
	ON HOMEPAGE.NR_STORIES(CREATION_DATE DESC);
GO	
