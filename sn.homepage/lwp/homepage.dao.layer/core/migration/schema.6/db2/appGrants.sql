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


CONNECT TO HOMEPAGE;

-- grant connect

GRANT CONNECT ON DATABASE TO USER LCUSER;

-- grants for homepage tables

GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.HOMEPAGE_SCHEMA TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.PERSON TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.LOGINNAME TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.USER_WIDGET_PREF  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.WIDGET  TO USER LCUSER;
GRANT DELETE,INSERT,SELECT,UPDATE ON HOMEPAGE.PREREQ TO USER LCUSER;


COMMIT;

CONNECT RESET;

