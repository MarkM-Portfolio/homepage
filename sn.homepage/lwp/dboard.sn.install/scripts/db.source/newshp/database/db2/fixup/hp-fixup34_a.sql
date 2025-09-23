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

-- a) increase the tabspace  PAGESIZE size
CREATE BUFFERPOOL NT16KBP IMMEDIATE SIZE 16000 AUTOMATIC PAGESIZE 16K;

-- HOMEPAGE NOTIFICATION HPNT16TABSPACE
CREATE SYSTEM TEMPORARY TABLESPACE HPNT16TMPTABSPACE
	PAGESIZE 16K MANAGED BY SYSTEM 
	USING ( 'HPNT16TMPTABSPACE' ) 
	EXTENTSIZE 8 PREFETCHSIZE AUTOMATIC
	BUFFERPOOL NT16KBP
	FILE SYSTEM CACHING;

CREATE LARGE TABLESPACE HPNT16TABSPACE 
	PAGESIZE 16K MANAGED BY DATABASE
	USING (FILE 'HPNT16TABSPACE' 12800)
	EXTENTSIZE 8
	PREFETCHSIZE AUTOMATIC
	BUFFERPOOL NT16KBP
	AUTORESIZE YES
	INCREASESIZE 20 M
	MAXSIZE NONE;	
