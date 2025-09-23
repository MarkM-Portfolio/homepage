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

-- 5724_S68                                              
USE HOMEPAGE;
GO

BEGIN TRANSACTION
GO


------------------------------------------------
-- INCLUDE FIX UP 462 FOR HOMEPAGE
------------------------------------------------

{include.hp-fixup462.sql}

------------------------------------------------
-- INCLUDE FIX UP 462 FOR HOMEPAGE
------------------------------------------------

{include.news-fixup462.sql}

------------------------------------------------
-- INCLUDE FIX UP 462 FOR NEWS
------------------------------------------------

{include.search-fixup462.sql}

------------------------------------------------------------------------------------------------------
-- 103084: OpenSocial @me/@self feed returns with error "We are unable to process your request" 
-- JUST ON-PREM
------------------------------------------------------------------------------------------------------
--i) set invalid ext id 
-- This step is not need for customers. I removed it to speed up migration

--ii) TRUNCATE LOGINNAME
DELETE FROM HOMEPAGE.LOGINNAME;
GO

---iii) DROP and CREATE UNIQUE constraint on EXID 
DROP INDEX PERSON_EXID ON HOMEPAGE.PERSON;
GO

CREATE UNIQUE INDEX PERSON_EXID
    ON HOMEPAGE.PERSON (EXID);
GO



------------------------------------------------------------------------------------------------------

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 462
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 462, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 461; 

------------------------------------------------------------------------------------------------

GO

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 462
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

COMMIT