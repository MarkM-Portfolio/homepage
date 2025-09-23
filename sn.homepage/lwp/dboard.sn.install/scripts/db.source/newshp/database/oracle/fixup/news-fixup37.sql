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

----------------------------------------------------------------------
-- 3) ADDING LAST UPDATE FOR RESORUCE TABLE
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.NR_RESOURCE
	ADD LAST_UPDATE TIMESTAMP;

UPDATE HOMEPAGE.NR_RESOURCE SET LAST_UPDATE = CURRENT_TIMESTAMP;

