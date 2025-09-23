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

CREATE UNIQUE INDEX HOMEPAGE.NR_STORIES_ER_UUID
	ON HOMEPAGE.NR_STORIES(EVENT_RECORD_UUID);

COMMIT;	

DROP INDEX HOMEPAGE.NR_STORIES_CREAT_IS_COM;

COMMIT;

CREATE INDEX HOMEPAGE.NR_STORIES_DATE
	ON HOMEPAGE.NR_STORIES(CREATION_DATE DESC);

COMMIT;	

reorg indexes all for table HOMEPAGE.NR_STORIES;
