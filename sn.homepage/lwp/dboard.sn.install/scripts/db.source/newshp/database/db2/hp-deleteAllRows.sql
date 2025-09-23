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
-- ***************************************************************** 

-- {COPYRIGHT}


-- REMOVING RECORDS -----------------

commit;



DELETE FROM HOMEPAGE.HOMEPAGE_SCHEMA;
commit;

DELETE FROM HOMEPAGE.LOGINNAME;
commit;

DELETE FROM HOMEPAGE.HP_WIDGET_INST;
commit;

DELETE FROM HOMEPAGE.HP_TAB_INST;
commit;

DELETE FROM HOMEPAGE.HP_WIDGET_TAB; 
commit;

DELETE FROM HOMEPAGE.HP_UI;
commit;

DELETE FROM HOMEPAGE.HP_TAB;
commit;

DELETE FROM HOMEPAGE.PREREQ;
commit;

DELETE FROM HOMEPAGE.WIDGET;
commit;

DELETE FROM HOMEPAGE.NT_NOTIFICATION_RECIPIENT;
commit;

DELETE FROM HOMEPAGE.NT_NOTIFICATION;
commit;

DELETE FROM HOMEPAGE.PERSON;
commit;

DELETE FROM HOMEPAGE.METRIC_STAT;
commit;

DELETE FROM HOMEPAGE.NT_REPLYTO_RECIPIENT;
commit;

DELETE FROM HOMEPAGE.NT_REPLYTO;
commit;

DELETE FROM HOMEPAGE.MTCONFIG;
commit;


