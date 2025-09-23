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
-- HOMEPAGE.L3T_PACKAGE_DETAILS
-------------------------------------------------

ALTER TABLE HOMEPAGE.L3T_PACKAGE_DETAILS ALTER COLUMN POSITION_TYPE SET DATA TYPE VARCHAR(36) CCSID 1208;

COMMIT;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [START] fixup700 - start reorg table HOMEPAGE.L3T_PACKAGE_DETAILS' FROM SYSIBM.SYSDUMMY1;

--REORG TABLE HOMEPAGE.L3T_PACKAGE_DETAILS;

--SELECT CURRENT_TIMESTAMP, 'CURRENT_TIMESTAMP: [END] fixup700 - start end table HOMEPAGE.L3T_PACKAGE_DETAILS' FROM SYSIBM.SYSDUMMY1;

--COMMIT;