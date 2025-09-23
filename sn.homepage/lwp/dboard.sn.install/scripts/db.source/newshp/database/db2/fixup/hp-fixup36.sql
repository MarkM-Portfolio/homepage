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

----------------------------------------------------------------------
-- 1) ADDING the new widget - UPDATE activities to do list (review) 
----------------------------------------------------------------------
UPDATE 	HOMEPAGE.WIDGET 
SET 	WIDGET_URL='web/widgets/activitiesTodoList/activitiesTodoList.xml', WIDGET_SECURE_URL='web/widgets/activitiesTodoList/activitiesTodoList.xml'
WHERE 	WIDGET_ID='activities-sidebar7x4229x8';

INSERT INTO HOMEPAGE.WIDGET (WIDGET_ID,WIDGET_TITLE,WIDGET_TEXT,WIDGET_URL,WIDGET_ICON,WIDGET_ENABLED,WIDGET_SYSTEM,WIDGET_HOMEPAGE_SPECIFIC,WIDGET_PREVIEW_IMAGE,WIDGET_CATEGORY,WIDGET_IS_DEFAULT_OPENED,WIDGET_MULTIPLE_INSTANCES,WIDGET_MARKED_CACHABLE,WIDGET_SECURE_URL,WIDGET_SECURE_ICON) VALUES ('recommend7x4f6hd93kd9','%widget.sand.recommend.name','%widget.sand.recommend.desc','web/widgets/sand/recommend.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png',1,0,1,'${HOMEPAGE_CONTEXT_ROOT}/web/jsp/palette/images/profiles_my_colleagues.jpg','SAND',1,0,0,'web/widgets/sand/recommend.xml','${HOMEPAGE_CONTEXT_ROOT}/images/default_buttons/png/iconRecommend16.png');

INSERT INTO HOMEPAGE.HP_WIDGET_TAB (WIDGET_TAB_ID,WIDGET_ID,TAB_ID,TYPE) VALUES ('UPDATES_recommend-sidebar','recommend7x4f6hd93kd9','_panel.updatex4a43x82aaxb00187218631','primary');

INSERT INTO HOMEPAGE.PREREQ 
			(PREREQ_ID,APP_ID,WIDGET_ID) 
VALUES 		('9t1a20f1xc4cax6cc4x8b0bx51af2ddef2cd','sand','recommend7x4f6hd93kd9');

----------------------------------------------------------------------
-- 2) ADDING TO THE PERSON TABLE A LAST_UPDATE ATTRIBUTE 
----------------------------------------------------------------------
ALTER TABLE HOMEPAGE.PERSON
	ADD LAST_UPDATE TIMESTAMP;

----------------------------------------------------------------------
-- 3) REFACTORING OF THE NOTIFICATIONS tables 
--		DB script update to store internal IDs for users in notification tables
----------------------------------------------------------------------
------------------------------------  NT_NOTIFICATION -------------------------------------
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD SENDER_ID VARCHAR(36);

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
ADD FIRST_RECIPIENT_ID VARCHAR(36);

-- add index (ADDED) - it is new index (we need to remove it after) FIX
CREATE INDEX HOMEPAGE.NT_NOTIFICATION_FP_INDEX
	ON HOMEPAGE.NT_NOTIFICATION(FIRST_RECIPIENT_EXID);	

RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION" FOR INDEXES ALL;

-- sender_id
UPDATE HOMEPAGE.NT_NOTIFICATION    	
SET SENDER_ID = (   SELECT  HOMEPAGE.PERSON.PERSON_ID
                    FROM    HOMEPAGE.PERSON
                    WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION.SENDER_EXID)
WHERE EXISTS
  (     SELECT  HOMEPAGE.PERSON.PERSON_ID
        FROM    HOMEPAGE.PERSON
        WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION.SENDER_EXID);

COMMIT;        

-- first_recipient_id
UPDATE HOMEPAGE.NT_NOTIFICATION    	
SET FIRST_RECIPIENT_ID = (   SELECT  HOMEPAGE.PERSON.PERSON_ID
                    FROM    HOMEPAGE.PERSON
                    WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION.FIRST_RECIPIENT_EXID)
WHERE EXISTS
  (     SELECT  HOMEPAGE.PERSON.PERSON_ID
        FROM    HOMEPAGE.PERSON
        WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION.FIRST_RECIPIENT_EXID);

COMMIT;

-- drop (REMOVE) the temp index FIX
DROP INDEX HOMEPAGE.NT_NOTIFICATION_FP_INDEX;       

-- sender
DROP INDEX HOMEPAGE.NT_NOTIFICATION_EXID_INDEX;

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
DROP COLUMN SENDER_EXID;

REORG TABLE	HOMEPAGE.NT_NOTIFICATION;

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
    ADD CONSTRAINT "FK_SENDER_ID" FOREIGN KEY ("SENDER_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

REORG TABLE	HOMEPAGE.NT_NOTIFICATION;
	
-- recipient
ALTER TABLE HOMEPAGE.NT_NOTIFICATION
DROP COLUMN FIRST_RECIPIENT_EXID;

REORG TABLE	HOMEPAGE.NT_NOTIFICATION;

ALTER TABLE HOMEPAGE.NT_NOTIFICATION
    ADD CONSTRAINT "FK_F_RECIPIENT_ID" FOREIGN KEY ("FIRST_RECIPIENT_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

REORG TABLE	HOMEPAGE.NT_NOTIFICATION;
--RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION" FOR INDEXES ALL;

-- add index
CREATE INDEX HOMEPAGE.NT_NOTIFICATION_EXID_INDEX
	ON HOMEPAGE.NT_NOTIFICATION(SENDER_ID);

REORG TABLE	HOMEPAGE.NT_NOTIFICATION;
--RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION" FOR INDEXES ALL;

 
------------------ NT_NOTIFICATION_RECIPIENT --------------
-- to remove LOLLO
RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;	
	
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
ADD RECIPIENT_ID VARCHAR(36);

UPDATE HOMEPAGE.NT_NOTIFICATION_RECIPIENT    	
SET RECIPIENT_ID = (    SELECT  HOMEPAGE.PERSON.PERSON_ID
                        FROM    HOMEPAGE.PERSON
                        WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION_RECIPIENT.RECIPIENT_EXID)
WHERE EXISTS
  (     SELECT  HOMEPAGE.PERSON.PERSON_ID
        FROM    HOMEPAGE.PERSON
        WHERE   HOMEPAGE.PERSON.EXID = HOMEPAGE.NT_NOTIFICATION_RECIPIENT.RECIPIENT_EXID);

COMMIT;        
        
DROP INDEX HOMEPAGE.NT_NOTIF_RECT_EXID_INDEX;        

ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
DROP COLUMN RECIPIENT_EXID;

REORG TABLE	HOMEPAGE.NT_NOTIFICATION_RECIPIENT; 
--RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;


-- add fk
ALTER TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT
    ADD CONSTRAINT "FK_RECIPIENT_ID" FOREIGN KEY ("RECIPIENT_ID")
	REFERENCES HOMEPAGE.PERSON("PERSON_ID");

REORG TABLE	HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

-- add index
CREATE INDEX HOMEPAGE.NT_NOT_RECIPIENT_INDEX
	ON HOMEPAGE.NT_NOTIFICATION_RECIPIENT(RECIPIENT_ID);

--REORG TABLE	HOMEPAGE.NT_NOTIFICATION;
--REORG TABLE	HOMEPAGE.NT_NOTIFICATION_RECIPIENT;
--RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" FOR INDEXES ALL;
--RUNSTATS ON TABLE "HOMEPAGE"."NT_NOTIFICATION" FOR INDEXES ALL;
    