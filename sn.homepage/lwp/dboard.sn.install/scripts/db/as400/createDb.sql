-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
-- CREATE THE SCHEMA
--------------------------------------    
        
CREATE SCHEMA HOMEPAGE;

--------------------------------------
-- CREATE THE SCHEMA TABLE
--------------------------------------

CREATE TABLE HOMEPAGE.HOMEPAGE_SCHEMA (
	COMPKEY VARCHAR(36) CCSID 1208 NOT NULL,
	DBSCHEMAVER INTEGER NOT NULL,
	PRESCHEMAVER VARCHAR(10) CCSID 1208 DEFAULT '0' NOT NULL,
	POSTSCHEMAVER VARCHAR(10) CCSID 1208 DEFAULT '0' NOT NULL,
	RELEASEVER VARCHAR(32) CCSID 1208 NOT NULL DEFAULT ''
);

--RUNSTATS ON TABLE "HOMEPAGE"."HOMEPAGE_SCHEMA";
--RUNSTATS ON TABLE "HOMEPAGE"."HOMEPAGE_SCHEMA" FOR INDEXES ALL;

--------------------------------------
-- STARTING HOMEPAGE TABLES DEFINITIONS
--------------------------------------
{include.hp-createDb.sql}
--------------------------------------
-- END HOMEPAGE TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- STARTING NEWS TABLES DEFINITIONS
--------------------------------------
{include.news-createDb.sql}
--------------------------------------
-- END NEWS TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- STARTING SEARCH TABLES DEFINITIONS
--------------------------------------
{include.search-createDb.sql}
--------------------------------------
-- END SEARCH TABLES DEFINITIONS
--------------------------------------

--------------------------------------
-- INSERT THE SCHEMA VERSION
--------------------------------------
INSERT INTO HOMEPAGE.HOMEPAGE_SCHEMA (COMPKEY, DBSCHEMAVER, RELEASEVER) 
VALUES ('HOMEPAGE', 704, '5.5.0.0');

COMMIT;
	
--------------------------------------
-- FLUSH
--------------------------------------
--FLUSH PACKAGE CACHE DYNAMIC;

------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------
-- END THE DEFINITION FOR THE SEARCH DATABASE
------------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------------

--------------------------------------
-- TERMINATE
--------------------------------------
--connect reset;
--terminate;
