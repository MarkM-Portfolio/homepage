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
SET CURRENT SCHEMA HOMEPAGE;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 459
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

-- empty file: we use it as SC will need a fixup

------------------------------------------------------------------------------------------------

-- 103724: [W3 PMR 54098,L6Q,000] duplicated external IDs which breaks search indexing - Removal of constraint on homepage.Person.EXID column 

--i) set invalid ext id 
CREATE VIEW HOMEPAGE.DUPS AS (
	SELECT P1.PERSON_ID, P1.EXID, P1.DISPLAYNAME, P1.USER_MAIL, P1.USER_MAIL_LOWER, P1.ORGANIZATION_ID
	FROM HOMEPAGE.PERSON P1,
		(
		SELECT EXID
		FROM HOMEPAGE.PERSON
		GROUP BY EXID
		HAVING COUNT(*) > 1
		)	P2
	WHERE P1.EXID = P2.EXID
);
COMMIT;

-- Invalidate
UPDATE HOMEPAGE.PERSON T1 
SET (T1.PERSON_ID, T1.EXID, T1.USER_MAIL, T1.USER_MAIL_LOWER) = 
	(
	SELECT 	T2.PERSON_ID, 'INVALID_' || SUBSTR(T2.PERSON_ID,1, 6) || SUBSTR(T2.EXID,7,20), 'INVALID_' || T2.USER_MAIL, 'INVALID_' || T2.USER_MAIL_LOWER 
	FROM 	HOMEPAGE.DUPS AS T2
	WHERE 	T1.PERSON_ID = T2.PERSON_ID AND T1.ORGANIZATION_ID IS NULL
	) 
WHERE EXISTS (
	SELECT 1 
	FROM HOMEPAGE.DUPS AS T2
	WHERE T1.PERSON_ID = T2.PERSON_ID AND T1.ORGANIZATION_ID IS NULL
);
COMMIT;

DROP VIEW HOMEPAGE.DUPS;
COMMIT;

--To be verified on DB2 for IBM i
--ii) TRUNCATE LOGINNAME
--TRUNCATE HOMEPAGE.LOGINNAME IMMEDIATE;
--COMMIT;


---iii) DROP and CREATE UNIQUE constraint on EXID 
DROP INDEX HOMEPAGE.PERSON_EXID;
COMMIT;

CREATE UNIQUE INDEX HOMEPAGE.PERSON_EXID
    ON HOMEPAGE.PERSON (EXID);
COMMIT;

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 459
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 459, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 458; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 459
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


