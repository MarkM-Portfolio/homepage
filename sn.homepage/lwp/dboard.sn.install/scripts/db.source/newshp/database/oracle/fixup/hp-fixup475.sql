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


ALTER TABLE HOMEPAGE.PERSON MODIFY(COMMUNITY_INTERNAL_ONLY DEFAULT 1);


 
 --- Update existing value for COMMUNITY_INTERNAL_ONLY to be 1 where MEMBER_TYPE is 1
 
  DECLARE CURSOR cur IS SELECT COMMUNITY_INTERNAL_ONLY FROM HOMEPAGE.PERSON WHERE MEMBER_TYPE = 1 FOR UPDATE; 
 
 
                BEGIN 
                        FOR rec IN cur LOOP 
                        	UPDATE HOMEPAGE.PERSON SET COMMUNITY_INTERNAL_ONLY = 1 WHERE CURRENT OF cur; 
                        END LOOP; 
                        COMMIT; 
                END; 
 / 
 COMMIT;

COMMIT;