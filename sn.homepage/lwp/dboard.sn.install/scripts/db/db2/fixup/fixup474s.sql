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
CONNECT TO HOMEPAGE@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup474s - start' FROM SYSIBM.SYSDUMMY1@

--120933: [fixup474s] Migration steps to allow Reenable ORG ID filter on NR_COMM_PERSON_FOLLOW

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup474s - start UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  NR_COMM_PERSON_FOLLOW' FROM SYSIBM.SYSDUMMY1@

UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  NR_COMM_PERSON_FOLLOW
   SET ORGANIZATION_ID = 	(	SELECT ORGANIZATION_ID 
                      	  		FROM 	HOMEPAGE.PERSON PERSON 
                      	  		WHERE 	PERSON.PERSON_ID = NR_COMM_PERSON_FOLLOW.PERSON_ID
                      	  	)
   WHERE EXISTS				(	
   								SELECT 1 
   								FROM 	HOMEPAGE.PERSON PERSON 
                 				WHERE 	PERSON.PERSON_ID = NR_COMM_PERSON_FOLLOW.PERSON_ID
                 			)@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup474s - end UPDATE HOMEPAGE.NR_COMM_PERSON_FOLLOW  NR_COMM_PERSON_FOLLOW' FROM SYSIBM.SYSDUMMY1@

                 			
{include.news-fixup474.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 474
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 474, RELEASEVER = '5.0.0.0' 
WHERE   DBSCHEMAVER = 473@

------------------------------------------------------------------------------------------------

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup474s - end' FROM SYSIBM.SYSDUMMY1@


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@