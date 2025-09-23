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

-- Need to create a new tabspace

-- NEWS4KBP ---------------------------------------------------------------
CREATE BUFFERPOOL NEWS4KBP IMMEDIATE SIZE 16000 AUTOMATIC PAGESIZE 4K;

-- NEWS 4K TABLETABLESPACE 
CREATE SYSTEM TEMPORARY TABLESPACE NEWS4TMPTABSPACE
        PAGESIZE 4K MANAGED BY SYSTEM 
        USING ( 'NEWS4TMPTABSPACE' ) 
        EXTENTSIZE 16 PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS4KBP
        FILE SYSTEM CACHING;
              
CREATE LARGE TABLESPACE NEWS4TABSPACE 
        PAGESIZE 4K MANAGED BY DATABASE
        USING (FILE 'NEWS4TABSPACE' 12800)
        EXTENTSIZE 16
        PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS4KBP
        AUTORESIZE YES
        INCREASESIZE 40 M
        MAXSIZE NONE; 

-- NEWS8KBP ----------------------------------------------------------
CREATE BUFFERPOOL NEWS8KBP IMMEDIATE SIZE 16000 AUTOMATIC PAGESIZE 8K;

-- NEWS 8K TABLETABLESPACE 
CREATE SYSTEM TEMPORARY TABLESPACE NEWS8TMPTABSPACE
        PAGESIZE 8K MANAGED BY SYSTEM 
        USING ( 'NEWS8TMPTABSPACE' ) 
        EXTENTSIZE 16 PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS8KBP
        FILE SYSTEM CACHING;
              
CREATE LARGE TABLESPACE NEWS8TABSPACE 
        PAGESIZE 8K MANAGED BY DATABASE
        USING (FILE 'NEWS8TABSPACE' 12800)
        EXTENTSIZE 16
        PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS8KBP
        AUTORESIZE YES
        INCREASESIZE 40 M
        MAXSIZE NONE;
           
-- NEWS32KBP ----------------------------------------------------------
CREATE BUFFERPOOL NEWS32KBP IMMEDIATE SIZE 16000 AUTOMATIC PAGESIZE 32K;

-- NEWS 32K TABLETABLESPACE
CREATE SYSTEM TEMPORARY TABLESPACE NEWS32TMPTABSPACE
        PAGESIZE 32K MANAGED BY SYSTEM 
        USING ( 'NEWS32TMPTABSPACE' ) 
        EXTENTSIZE 16 PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS32KBP
        FILE SYSTEM CACHING;
              
CREATE LARGE TABLESPACE NEWS32TABSPACE 
        PAGESIZE 32K MANAGED BY DATABASE
        USING (FILE 'NEWS32TABSPACE' 12800)
        EXTENTSIZE 16
        PREFETCHSIZE AUTOMATIC
        BUFFERPOOL NEWS32KBP
        AUTORESIZE YES
        INCREASESIZE 40 M
        MAXSIZE NONE;

-- NEWSCONTBFP
CREATE BUFFERPOOL NEWSCONTBFP IMMEDIATE SIZE 1000 AUTOMATIC PAGESIZE 4K;

-- NEWSCONT4KTABSPACE
CREATE LARGE TABLESPACE NEWSCONT4KTABSPACE 
	PAGESIZE 4K  MANAGED BY DATABASE
	USING (FILE 'NEWSCONT_TABSPACE' 12800) 
	EXTENTSIZE 8 
	PREFETCHSIZE 8 
	BUFFERPOOL NEWSCONTBFP;
	   