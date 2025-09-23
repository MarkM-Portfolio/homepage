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

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup475s - start' FROM SYSIBM.SYSDUMMY1@

-- FIX NR_FOLLOWS READING ORG_ID FROM PERSON TABLE
SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup475s - start UPDATE HOMEPAGE.NR_FOLLOWS NR_FOLLOWS' FROM SYSIBM.SYSDUMMY1@

UPDATE HOMEPAGE.NR_FOLLOWS NR_FOLLOWS
   SET ORGANIZATION_ID =  
   (
				SELECT  DISTINCT(ORGANIZATION_ID)
				FROM    HOMEPAGE.PERSON PERSON
				WHERE   NR_FOLLOWS.PERSON_ID = PERSON.PERSON_ID AND PERSON.EXID NOT LIKE 'INVALID%'
   )
   WHERE EXISTS                         
   (
				SELECT 1
				FROM    HOMEPAGE.PERSON PERSON
				WHERE   NR_FOLLOWS.PERSON_ID = PERSON.PERSON_ID AND PERSON.EXID NOT LIKE 'INVALID%'
  	)@

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup475s - end UPDATE HOMEPAGE.NR_FOLLOWS NR_FOLLOWS' FROM SYSIBM.SYSDUMMY1@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup475s - start UPDATE HOMEPAGE.NR_RESOURCE NR_RESOURCE' FROM SYSIBM.SYSDUMMY1@
  	
-- FIX NR_RESOURCE READING ORG_ID FROM FOLLOWS TABLE
UPDATE HOMEPAGE.NR_RESOURCE NR_RESOURCE
   SET ORGANIZATION_ID =        
   	(
	            	SELECT  DISTINCT(NR_FOLLOWS.ORGANIZATION_ID)
	            	FROM    HOMEPAGE.NR_FOLLOWS NR_FOLLOWS, HOMEPAGE.PERSON PERSON
	            	WHERE   NR_FOLLOWS.RESOURCE_ID = NR_RESOURCE.RESOURCE_ID AND NR_FOLLOWS.PERSON_ID = PERSON.PERSON_ID AND PERSON.EXID NOT LIKE 'INVALID%'
	)
   WHERE EXISTS                         
   	(
			 	SELECT 1
				FROM    HOMEPAGE.NR_FOLLOWS NR_FOLLOWS, HOMEPAGE.PERSON PERSON
				WHERE   NR_FOLLOWS.RESOURCE_ID = NR_RESOURCE.RESOURCE_ID AND NR_FOLLOWS.PERSON_ID = PERSON.PERSON_ID AND PERSON.EXID NOT LIKE 'INVALID%'
	)@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup475s - end UPDATE HOMEPAGE.NR_RESOURCE NR_RESOURCE' FROM SYSIBM.SYSDUMMY1@

{include.hp-fixup475.sql}

{include.news-fixup475.sql}

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 475
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 475, RELEASEVER = '5.0.0.0' 
WHERE   DBSCHEMAVER = 474@

------------------------------------------------------------------------------------------------

COMMIT@

SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup475s - end' FROM SYSIBM.SYSDUMMY1@


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@