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
-- CONSTRAINTS KEYS

ALTER TABLE "HOMEPAGE"."PREREQ" DROP CONSTRAINT "FK_PREREQ_WIDGET";

ALTER TABLE "HOMEPAGE"."USER_WIDGET_PREF" DROP CONSTRAINT "FK_WIDGET_ID";

ALTER TABLE "HOMEPAGE"."USER_WIDGET_PREF" DROP CONSTRAINT "FK_USER_ID";

ALTER TABLE "HOMEPAGE"."LOGINNAME" DROP CONSTRAINT "FK_PERSON_ID";

DROP INDEX "HOMEPAGE"."USERID_WIDGET_UNIQUE";

COMMIT;

QUIT;