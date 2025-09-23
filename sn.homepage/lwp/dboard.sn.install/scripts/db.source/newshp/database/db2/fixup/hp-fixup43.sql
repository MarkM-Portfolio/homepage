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

UPDATE HOMEPAGE.PERSON SET IS_ACTIVE = 1;
 
ALTER TABLE HOMEPAGE.PERSON
	ALTER COLUMN IS_ACTIVE SET NOT NULL;

reorg table HOMEPAGE.PERSON use TEMPSPACE1;

UPDATE HOMEPAGE.HP_TAB SET ENABLED = 1;

-- giving grants again

-- HOME PAGE
{DB2_GRANT_START} HOMEPAGE.HOMEPAGE_SCHEMA {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.PERSON {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.SNCORE_PERSON {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.LOGINNAME {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.PREREQ {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.WIDGET  {DB2_GRANT_STOP}


{DB2_GRANT_START} HOMEPAGE.HP_UI  {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.HP_TAB  {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.HP_TAB_INST  {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.HP_WIDGET_INST  {DB2_GRANT_STOP}
{DB2_GRANT_START} HOMEPAGE.HP_WIDGET_TAB  {DB2_GRANT_STOP}