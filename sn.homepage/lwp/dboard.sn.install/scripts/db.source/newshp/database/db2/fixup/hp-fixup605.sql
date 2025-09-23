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

-- 149321

DROP INDEX HOMEPAGE.PERSON_STATE_IDX@

CREATE INDEX HOMEPAGE.PERSON_STATE_IDX
  ON HOMEPAGE.PERSON(MEMBER_TYPE, LAST_UPDATE ASC, STATE, PERSON_ID)@  
COMMIT@	
	








