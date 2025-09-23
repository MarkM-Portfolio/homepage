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
-- SPR #JSTH8EZJXT - Removing dups records from HP_UI table and others issues like EMD delviery locks
---------------------------------------------------------------------------------

DELETE FROM HOMEPAGE.HP_WIDGET_INST WHERE WIDGET_INST_ID IN 
(
	SELECT HP_WIDGET_INST.WIDGET_INST_ID
	FROM HOMEPAGE.HP_WIDGET_INST HP_WIDGET_INST,
		(
			SELECT  	HP_TAB_INST.TAB_INST_ID
			FROM 	HOMEPAGE.HP_TAB_INST HP_TAB_INST,
					(
						SELECT UI_ID 
						FROM HOMEPAGE.HP_UI HP_UI 
						INNER JOIN (
								SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
								GROUP BY PERSON_ID 
								HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
						WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	) TMP_UI
			WHERE	HP_TAB_INST.UI_ID = TMP_UI.UI_ID
		) TMP_TAB_INST
	WHERE HP_WIDGET_INST.TAB_INST_ID = TMP_TAB_INST.TAB_INST_ID
);

COMMIT;

DELETE FROM HOMEPAGE.HP_TAB_INST WHERE TAB_INST_ID IN 
(
	SELECT  HP_TAB_INST.TAB_INST_ID
	FROM HOMEPAGE.HP_TAB_INST HP_TAB_INST,
		(
			SELECT UI_ID 
			FROM HOMEPAGE.HP_UI HP_UI 
			INNER JOIN (
					SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
					GROUP BY PERSON_ID 
					HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
			WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	) TMP_UI
	WHERE	HP_TAB_INST.UI_ID = TMP_UI.UI_ID
);

COMMIT;

DELETE FROM HOMEPAGE.HP_UI WHERE UI_ID IN
(
	SELECT UI_ID 
	FROM HOMEPAGE.HP_UI HP_UI 
	INNER JOIN (
			SELECT PERSON_ID,MAX(LAST_VISIT) AS MAX_LAST_VISIT 	FROM HOMEPAGE.HP_UI 
			GROUP BY PERSON_ID 
			HAVING COUNT(*) > 1) T ON HP_UI.PERSON_ID = T.PERSON_ID
	WHERE HP_UI.LAST_VISIT < T.MAX_LAST_VISIT	
);

COMMIT;

DROP INDEX HOMEPAGE.HP_UI_PERSON_ID_INDEX;

CREATE UNIQUE INDEX HOMEPAGE.HP_UI_UNQ
	ON HOMEPAGE.HP_UI (PERSON_ID) TABLESPACE "HPNTINDEXTABSPACE";

COMMIT;

---------------------------------------------------------------------------------
--------------------------------------------------------------------------------- 