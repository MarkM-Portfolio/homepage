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
CONNECT TO HOMEPAGE;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 455
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIX UP 455 FOR SEARCH
------------------------------------------------

{include.news-fixup455.sql}

-- 96131: Remove default org id 0000 on PERSON table in SC only
ALTER TABLE HOMEPAGE.PERSON ALTER ORGANIZATION_ID DROP DEFAULT;
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '0%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '1%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '2%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '3%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '4%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '5%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '6%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '7%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '8%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE '9%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'a%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'b%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'c%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'd%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'e%';
COMMIT;

UPDATE HOMEPAGE.PERSON SET ORGANIZATION_ID=NULL WHERE  PERSON_ID LIKE 'f%';
COMMIT;


--96288: Invalidate MT_ORGANIZATION records
UPDATE HOMEPAGE.MT_ORGANIZATION T1 
SET T1.ORGANIZATION_EXID =
	(
	SELECT 	'INVALID_' || SUBSTR(T2.ORGANIZATION_EXID,1, 20)
	FROM 	HOMEPAGE.MT_ORGANIZATION AS T2
	WHERE 	T1.ORGANIZATION_ID = T2.ORGANIZATION_ID
	) 
WHERE EXISTS (
	SELECT 1 
	FROM HOMEPAGE.MT_ORGANIZATION AS T2
	WHERE T1.ORGANIZATION_ID = T2.ORGANIZATION_ID
	AND T1.ORGANIZATION_ID <> '00000000-0000-0000-0000-000000000000'
);
COMMIT;

-- 96378: Truncate all Activity Stream and Microblog tables
{include.sc-45-50-purge-as-tables.sql}


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 455
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 455, RELEASEVER = '5.0.0.0 SC' 
WHERE   DBSCHEMAVER = 454; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 455
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate;