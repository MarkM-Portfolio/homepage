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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 455 -----------------------------------
---------------------------------------------------------------------------------

-- Create a new fixup455. Fix table NR_SOURCE_TYPE_DEFAULT. It must be identical to the existing one (95652) 	
ALTER TABLE HOMEPAGE.NR_SOURCE_TYPE_DEFAULT
	ADD ORGANIZATION_ID VARCHAR2 (36);
COMMIT;

--95762: Based on 95427: create two indexes for on-prem, and SC. SQLServer, db2, oracle. Indexes are part of fixup455
CREATE UNIQUE INDEX NR_FOLLOWS_PER_RES_UIDX
	ON HOMEPAGE.NR_FOLLOWS (PERSON_ID, RESOURCE_ID)  TABLESPACE  NEWSINDEXTABSPACE;
COMMIT;

CREATE UNIQUE INDEX NR_RES_LU_UIDX
	ON HOMEPAGE.NR_RESOURCE (RESOURCE_ID, RESOURCE_TYPE, LAST_UPDATE DESC, CONTAINER_NAME DESC) TABLESPACE  NEWSINDEXTABSPACE;  
COMMIT;


---------------------------------------------------------------------------------
------------------------ END   NEWS FIXUP 455 -------------------------------------
---------------------------------------------------------------------------------

 

  
  
  
