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



--- Update COMMUNITY_INTERNAL_ONLY to have 1 as default value


ALTER TABLE HOMEPAGE.PERSON ALTER COLUMN COMMUNITY_INTERNAL_ONLY SET DEFAULT 1;



--- Update existing value for COMMUNITY_INTERNAL_ONLY to be 1 where MEMBER_TYPE is 1
/*
EXPORT TO data.tmp.ixf OF IXF METHOD N (
        PERSON_ID, COMMUNITY_INTERNAL_ONLY
)
SELECT PERSON_ID, 1 FROM HOMEPAGE.PERSON WHERE MEMBER_TYPE = 1 WITH UR;
COMMIT;

IMPORT FROM data.tmp.ixf OF IXF METHOD N 
	        ( 
	        PERSON_ID, COMMUNITY_INTERNAL_ONLY 
	        ) 
ALLOW NO ACCESS COMMITCOUNT 2000 
INSERT_UPDATE INTO HOMEPAGE.PERSON 
	        ( 
	        PERSON_ID, COMMUNITY_INTERNAL_ONLY 
	        ) ; 
*/
UPDATE HOMEPAGE.PERSON SET COMMUNITY_INTERNAL_ONLY = 1 WHERE MEMBER_TYPE = 1;

COMMIT; 
