-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}


-----------------------------------------------------------------------
-- Add column DELETEABLE to HOMEPAGE.L3T_PACKAGES
-----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.L3T_PACKAGES ADD DELETEABLE NUMERIC(5,0) NOT NULL DEFAULT 1;
GO
