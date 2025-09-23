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

DROP INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IIX;
COMMIT;

CREATE  INDEX HOMEPAGE.DISCOVERY_VIEW_RLL_IIX 
 	ON HOMEPAGE.NR_DISCOVERY_VIEW (USE_IN_ROLLUP, IS_VISIBLE, ORGANIZATION_ID, CREATION_DATE DESC) TABLESPACE  NEWSINDEXTABSPACE; 
COMMIT;