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

-- 1) ADDING TO THE PERSON TABLE A SAND_LAST_UPDATE ATTRIBUTE 
ALTER TABLE HOMEPAGE.PERSON
    ADD COLUMN SAND_LAST_UPDATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

--UPDATE HOMEPAGE.PERSON SET SAND_LAST_UPDATE=CURRENT_TIMESTAMP;

--ALTER TABLE HOMEPAGE.PERSON 
--ALTER COLUMN  SAND_LAST_UPDATE SET NOT NULL;

COMMIT;

reorg table HOMEPAGE.PERSON;

CREATE INDEX HOMEPAGE.PERSON_SAND_LAST_UPDATE
    ON HOMEPAGE.PERSON (SAND_LAST_UPDATE ASC);

COMMIT;


RUNSTATS ON TABLE "HOMEPAGE"."PERSON" FOR INDEXES ALL;

-- SPR #RAPA86XGVU Brief Description:*   SAND: Migration: Recommendations widget should show up in 
-- Updates page by default for all new users and existing 2.5 users

-- delete existing widgets (just to be sure) from the ui of the users
DELETE FROM HOMEPAGE.HP_WIDGET_INST WHERE WIDGET_ID = 'recommend7x4f6hd93kd9';

-- inserting for all the users the new widget so it is showed into the ui
INSERT INTO HOMEPAGE.HP_WIDGET_INST 
    (
        WIDGET_INST_ID,
        WIDGET_ID,
        TAB_INST_ID,
        WIDGET_SETTING,
        CONTAINER,
        ORDER_SEQUENCE,
        IS_FIXED,
        IS_TOGGLED,
        LAST_MODIFIED,
        LAST_UPDATED
    ) 
SELECT  ('recom' || SUBSTR(TAB_INST_ID,1,30)) WIDGET_INST_ID,
        'recommend7x4f6hd93kd9' WIDGET_ID,
        TAB_INST_ID TAB_INST_ID,
        '' WIDGET_SETTING,
        '1' CONTAINER,
        1 ORDER_SEQUENCE,
        0 IS_FIXED,
        1 IS_TOGGLED,
        CURRENT TIMESTAMP LAST_MODIFIED,
        CURRENT TIMESTAMP LAST_UPDATED
FROM    HOMEPAGE.HP_TAB_INST WHERE TAB_NAME = '%panel.update';


    

 