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
GO

CREATE TABLE HOMEPAGE.OH2P_CLIENTCFG (
	COMPONENTID nvarchar(128) NOT NULL, 
	CLIENTID nvarchar(256) NOT NULL, 
	CLIENTSECRET nvarchar(256), 
	DISPLAYNAME nvarchar(256) NOT NULL, 
	REDIRECTURI nvarchar(2048), 
	ENABLED NUMERIC(5,0)
) ON [PRIMARY]
GO

ALTER TABLE HOMEPAGE.OH2P_CLIENTCFG 
	ADD CONSTRAINT PK_COMPIDCLIENTID PRIMARY KEY (COMPONENTID,CLIENTID);

GO