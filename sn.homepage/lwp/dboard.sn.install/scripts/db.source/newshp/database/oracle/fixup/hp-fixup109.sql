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

DROP TABLE HOMEPAGE.OH2P_CLIENTCFG;

CREATE TABLE HOMEPAGE.OH2P_CLIENTCFG (
	COMPONENTID VARCHAR2(128) NOT NULL, 
	CLIENTID VARCHAR2(256) NOT NULL, 
	CLIENTSECRET VARCHAR2(256), 
	DISPLAYNAME VARCHAR2(256) NOT NULL, 
	REDIRECTURI VARCHAR2(2048), 
	ENABLED NUMBER(5)
)
TABLESPACE "HOMEPAGEREGTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID) USING INDEX TABLESPACE "HOMEPAGEINDEXTABSPACE";

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG ENABLE ROW MOVEMENT;

COMMIT;