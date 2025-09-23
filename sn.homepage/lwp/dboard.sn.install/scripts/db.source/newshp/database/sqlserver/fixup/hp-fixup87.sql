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

---------------------------------------------------------
-- Adding column to trace the LAST_ACTIONABLE_VISIT
---------------------------------------------------------
ALTER TABLE HOMEPAGE.HP_UI
	ADD LAST_ACTIONABLE_VISIT DATETIME;
GO	

UPDATE HOMEPAGE.HP_UI SET LAST_ACTIONABLE_VISIT = CURRENT_TIMESTAMP;
GO