-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2015                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}


-------------------------------------------------
-- HOMEPAGE.L3T_EXT_BINDS
-------------------------------------------------

CREATE TABLE HOMEPAGE.L3T_EXT_BINDS (
		EXT_BIND_ID VARCHAR(36) CCSID 1208 NOT NULL,
		EXT_ID VARCHAR(36) CCSID 1208 NOT NULL,
		EXT_POINT_ID VARCHAR(36) CCSID 1208 NOT NULL,
		ORGANIZATION_ID VARCHAR(36) CCSID 1208 NOT NULL
);

ALTER TABLE HOMEPAGE.L3T_EXT_BINDS ADD CONSTRAINT HOMEPAGE.PK_EXT_BIND_ID PRIMARY KEY (EXT_BIND_ID);
CREATE UNIQUE INDEX HOMEPAGE.UNQ_EXT_BIND_IDX ON HOMEPAGE.L3T_EXT_BINDS (EXT_ID, EXT_POINT_ID, ORGANIZATION_ID);