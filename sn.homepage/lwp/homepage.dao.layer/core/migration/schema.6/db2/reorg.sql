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

reorg table HOMEPAGE.HOMEPAGE_SCHEMA  use TEMPSPACE1;
reorg table HOMEPAGE.PERSON use TEMPSPACE1;
reorg table HOMEPAGE.USER_WIDGET_PREF INDEX HOMEPAGE.USERID_WIDGET_UNIQUE use TEMPSPACE1;
reorg table HOMEPAGE.WIDGET use TEMPSPACE1;
reorg table HOMEPAGE.PREREQ use TEMPSPACE1;
reorg table HOMEPAGE.LOGINNAME use TEMPSPACE1;

reorg indexes all for table HOMEPAGE.HOMEPAGE_SCHEMA;
reorg indexes all for table HOMEPAGE.PERSON;
reorg indexes all for table HOMEPAGE.USER_WIDGET_PREF;
reorg indexes all for table HOMEPAGE.WIDGET;
reorg indexes all for table HOMEPAGE.PREREQ;
reorg indexes all for table HOMEPAGE.LOGINNAME;

connect reset;
terminate;
