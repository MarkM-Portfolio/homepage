-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- SEARCH

TRUNCATE TABLE HOMEPAGE.SR_INDEX_MANAGEMENT IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_RESUME_TOKENS IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_INDEX_DOCS IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_FILESCONTENT IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_STATS IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_STRING_STATS IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_NUMBER_STATS IMMEDIATE@
COMMIT@

TRUNCATE TABLE HOMEPAGE.SR_TIMER_STATS IMMEDIATE@
COMMIT@

