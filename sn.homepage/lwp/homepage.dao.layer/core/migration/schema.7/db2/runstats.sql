-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2008, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

connect to HOMEPAGE;

RUNSTATS ON TABLE HOMEPAGE.HOMEPAGE_SCHEMA;
RUNSTATS ON TABLE HOMEPAGE.PERSON;
RUNSTATS ON TABLE HOMEPAGE.LOGINNAME;
RUNSTATS ON TABLE HOMEPAGE.HP_UI;
RUNSTATS ON TABLE HOMEPAGE.HP_TAB;
RUNSTATS ON TABLE HOMEPAGE.HP_TAB_INST;
RUNSTATS ON TABLE HOMEPAGE.HP_WIDGET_INST;
RUNSTATS ON TABLE HOMEPAGE.WIDGET;
RUNSTATS ON TABLE HOMEPAGE.WIDGET_TAB;
RUNSTATS ON TABLE HOMEPAGE.PREREQ;
RUNSTATS ON TABLE HOMEPAGE.NT_NOTIFICATION;
RUNSTATS ON TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT;

RUNSTATS ON TABLE HOMEPAGE.NR_SOURCE;
RUNSTATS ON TABLE HOMEPAGE.NR_SUBSCRIPTION;
RUNSTATS ON TABLE HOMEPAGE.NR_NEWS_RECORDS;
RUNSTATS ON TABLE HOMEPAGE.NR_TEMPLATE;


RUNSTATS ON TABLE HOMEPAGE.HOMEPAGE_SCHEMA 	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.PERSON			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.LOGINNAME		FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.HP_UI			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.HP_TAB			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.HP_TAB_INST 		FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.HP_WIDGET_INST	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.WIDGET			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.WIDGET_TAB		FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.PREREQ			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.NT_NOTIFICATION	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.NT_NOTIFICATION_RECIPIENT	FOR INDEXES ALL;

RUNSTATS ON TABLE HOMEPAGE.NR_SOURCE		FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.NR_SUBSCRIPTION	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.NR_NEWS_RECORDS	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.NR_TEMPLATE		FOR INDEXES ALL;


COMMIT;
	
--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC;

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset;
terminate; 
