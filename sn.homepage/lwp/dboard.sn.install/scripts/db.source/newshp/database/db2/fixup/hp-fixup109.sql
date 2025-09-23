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
	COMPONENTID VARCHAR(128) NOT NULL, 
	CLIENTID VARCHAR(256) NOT NULL, 
	CLIENTSECRET VARCHAR(256), 
	DISPLAYNAME VARCHAR(256) NOT NULL, 
	REDIRECTURI VARCHAR(2048), 
	ENABLED SMALLINT
)
IN HPNT16TABSPACE;

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID);

COMMIT;