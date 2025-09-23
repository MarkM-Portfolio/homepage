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
 
-- 1) Update PERSON table SAND_OPT SMALLINT DEFAULT 1	NOT NULL
ALTER TABLE HOMEPAGE.PERSON
    ADD SAND_OPT NUMBER(5,0) DEFAULT 1;

UPDATE HOMEPAGE.PERSON SET SAND_OPT = 1;

COMMIT;

-- 2) Drop IS_ACTIVE from PERSON table
ALTER TABLE HOMEPAGE.PERSON
    DROP COLUMN IS_ACTIVE;
