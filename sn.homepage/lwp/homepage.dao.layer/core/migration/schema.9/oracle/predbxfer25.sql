-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2009, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- *********************************i******************************* 

-- CONSTRAINTS KEYS

-- HOMEPAGE 
ALTER TABLE "HOMEPAGE"."LOGINNAME" DROP CONSTRAINT "FK_LN_PERSON_ID";

ALTER TABLE "HOMEPAGE"."HP_UI" DROP CONSTRAINT "FK_UI_PERSON_ID";

ALTER TABLE "HOMEPAGE"."HP_TAB_INST" DROP CONSTRAINT "FK_UI_ID";

ALTER TABLE "HOMEPAGE"."HP_TAB_INST" DROP CONSTRAINT "FK_TAB_INST_TAB_ID";

ALTER TABLE "HOMEPAGE"."HP_WIDGET_INST" DROP CONSTRAINT "FK_WIDGET_ID";

ALTER TABLE "HOMEPAGE"."HP_WIDGET_INST" DROP CONSTRAINT "FK_TAB_INST_ID";

ALTER TABLE "HOMEPAGE"."HP_WIDGET_TAB" DROP CONSTRAINT "FK_WID_TAB_WID_ID";

ALTER TABLE "HOMEPAGE"."HP_WIDGET_TAB" DROP CONSTRAINT "FK_WID_TAB_TAB_ID";

ALTER TABLE "HOMEPAGE"."PREREQ" DROP CONSTRAINT "FK_PREREQ_WIDGET";

ALTER TABLE "HOMEPAGE"."NT_NOTIFICATION_RECIPIENT" DROP CONSTRAINT "FK_RECIP_NOTIF";

ALTER TABLE "HOMEPAGE"."LOGINNAME" DROP CONSTRAINT "LOGINNAME_UNIQUE";

DROP INDEX  "HOMEPAGE"."PERSON_EXID";

-- NEWS RECORDS 
ALTER TABLE "HOMEPAGE"."NR_SUBSCRIPTION" DROP CONSTRAINT "FK_PERSON_ID";

ALTER TABLE "HOMEPAGE"."NR_SUBSCRIPTION" DROP CONSTRAINT "FK_SOURCE_ID";

ALTER TABLE "HOMEPAGE"."NR_SOURCE" DROP CONSTRAINT "SOURCE_UNIQUE";

DROP INDEX  "HOMEPAGE"."NR_SUBSCRIPTION_IX_UNIQUE";

COMMIT;

--------------------------------------
-- DISCONNECT
--------------------------------------
DISCONNECT ALL;

QUIT;
