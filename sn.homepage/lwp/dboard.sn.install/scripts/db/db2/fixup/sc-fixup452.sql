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
--					start	FIXUP 452
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------

-----------------------------------------------------------------------------------------------------------------------
-- Before to set NULL orgId we need to make sure that there are no records with the same EXID
-- In that case we invalidate them
-----------------------------------------------------------------------------------------------------------------------

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


--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------------


-- 94500: Disable "Recommendations" widget for Homepage in SmartCloud
UPDATE HOMEPAGE.WIDGET SET WIDGET_ENABLED = '0' WHERE WIDGET_ID = 'recommend7x4f6hd93kd9';
COMMIT;


INSERT INTO HOMEPAGE.MT_ORGANIZATION 
    (ORGANIZATION_ID, ORGANIZATION_EXID)
    SELECT '00000000-0000-0000-0000-000000000000', '00000000-0000-0000-0000-000000000000'
    FROM sysibm.sysdummy1 
    WHERE NOT EXISTS ( SELECT 1 FROM HOMEPAGE.MT_ORGANIZATION WHERE ORGANIZATION_ID = '00000000-0000-0000-0000-000000000000')
    FETCH FIRST 1 ROW ONLY;
COMMIT;


-- 95037: Add SQL queries to set meetings widget in fixup (late request)

DELETE FROM HOMEPAGE.HP_WIDGET_INST;
COMMIT;

DELETE FROM HOMEPAGE.HP_WIDGET_TAB WHERE HOMEPAGE.HP_WIDGET_TAB.WIDGET_ID IN
( SELECT HOMEPAGE.WIDGET.WIDGET_ID FROM HOMEPAGE.WIDGET WHERE HOMEPAGE.WIDGET.WIDGET_TITLE = 'MeetingGadget');
COMMIT;

DELETE FROM HOMEPAGE.WIDGET WHERE HOMEPAGE.WIDGET.WIDGET_TITLE = 'MeetingGadget';
COMMIT;

INSERT INTO HOMEPAGE.WIDGET
            (WIDGET_ID, WIDGET_TITLE, WIDGET_TEXT, WIDGET_URL, WIDGET_ICON, WIDGET_ENABLED, WIDGET_SYSTEM,
            WIDGET_HOMEPAGE_SPECIFIC, WIDGET_PREVIEW_IMAGE, WIDGET_CATEGORY, WIDGET_IS_DEFAULT_OPENED,
            WIDGET_MARKED_CACHABLE, WIDGET_MULTIPLE_INSTANCES, WIDGET_SECURE_URL, WIDGET_SECURE_ICON,
            IS_GADGET, WIDGET_POLICY_FLAGS, PROXY_POLICY, SHARE_ORDER)
VALUES      ('1520aa1-c2fa-48ef-be05-8dee630c0054','%widget.smartcloud.meetings.name','%widget.smartcloud.meetings.desc','{toreplace_SC_meetings}',
NULL,1,0,0,NULL,'SMARTCLOUD',1,0,0,''
,NULL,1,7,'intranet_access', -1);
COMMIT;

INSERT INTO HOMEPAGE.HP_WIDGET_TAB 
            (WIDGET_TAB_ID, WIDGET_ID, TAB_ID, TYPE)
VALUES      ('1143431a-6700-4201-a93b-d425151d1234', '1520aa1-c2fa-48ef-be05-8dee630c0054', '_panel.updatex4a43x82aaxb00187218631','primary');
COMMIT;


------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 452
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 452, RELEASEVER = '5.0.0.0 SC' 
WHERE   DBSCHEMAVER = 451; 

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 452
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