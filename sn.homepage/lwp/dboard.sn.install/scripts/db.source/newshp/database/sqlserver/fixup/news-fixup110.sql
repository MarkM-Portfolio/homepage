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

---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 110 -----------------------------------
---------------------------------------------------------------------------------

ALTER TABLE HOMEPAGE.OH2P_CACHE 
	ALTER COLUMN USERNAME nvarchar(256);
GO

CREATE INDEX OH2P_CACHE_CNT 
	ON HOMEPAGE.OH2P_CACHE (USERNAME, CLIENTID) INCLUDE (COMPONENTID);
GO


--https://swgjazz.ibm.com:8001/jazz/resource/itemName/com.ibm.team.workitem.WorkItem/76700
-- This is just for SQLServer
ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG 
	ALTER COLUMN XML_DATA NVARCHAR(MAX) NOT NULL;
GO

ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS 
	ALTER COLUMN XML_DATA  NVARCHAR(MAX) NOT NULL;
GO

-- 76830: Missing index on CREATION_DATE column in HOMEPAGE.NR_AS_SEEDLIST
CREATE INDEX NR_SL_CD_DELETED
	ON HOMEPAGE.NR_AS_SEEDLIST (CREATION_DATE DESC);
GO

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 110 -------------------------------------
---------------------------------------------------------------------------------
 

  
  
  
