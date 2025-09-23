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
RUNSTATS ON TABLE HOMEPAGE.USER_WIDGET_PREF;
RUNSTATS ON TABLE HOMEPAGE.WIDGET;
RUNSTATS ON TABLE HOMEPAGE.PREREQ; 
RUNSTATS ON TABLE HOMEPAGE.LOGINNAME;

RUNSTATS ON TABLE HOMEPAGE.HOMEPAGE_SCHEMA	FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.PERSON			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.USER_WIDGET_PREF FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.WIDGET			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.PREREQ			FOR INDEXES ALL;
RUNSTATS ON TABLE HOMEPAGE.LOGINNAME		FOR INDEXES ALL;

connect reset;
terminate;
