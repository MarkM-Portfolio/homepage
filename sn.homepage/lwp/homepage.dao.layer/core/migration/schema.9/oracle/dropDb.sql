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

DROP USER HOMEPAGE CASCADE; 
DROP TABLESPACE HOMEPAGEREGTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
DROP TABLESPACE HOMEPAGEINDEXTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;

DROP TABLESPACE HPNTTMPTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
DROP TABLESPACE HPNTREGTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
DROP TABLESPACE HPNTINDEXTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;

DROP TABLESPACE NEWSTMPTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
DROP TABLESPACE NEWSREGTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;
DROP TABLESPACE NEWSINDEXTABSPACE INCLUDING CONTENTS AND DATAFILES CASCADE CONSTRAINTS;


QUIT;
