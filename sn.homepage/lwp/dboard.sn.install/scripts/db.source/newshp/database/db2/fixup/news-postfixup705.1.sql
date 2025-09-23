-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- Defect 177030:
-- -----------------------------------------------------------------

-- drop old indexes
DROP INDEX HOMEPAGE.NR_COMM_PERSON_F@
COMMIT@

-- recreate new indexes
CREATE INDEX HOMEPAGE.NR_COMM_PERSON_F 
	ON HOMEPAGE.NR_COMM_PERSON_FOLLOW (PERSON_ID, IS_READER_COMM, PERSON_COMMUNITY_ID, ORGANIZATION_ID)@    
COMMIT@
