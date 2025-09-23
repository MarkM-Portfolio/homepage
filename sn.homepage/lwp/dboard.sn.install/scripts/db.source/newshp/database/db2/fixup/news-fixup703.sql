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

-----------------------------------------------------------------------
-- CREATE NEW INDEXES FOR L3T_EXT_META2
-----------------------------------------------------------------------
CREATE INDEX HOMEPAGE.EXT_META2_UNIDNKEY_IDX ON HOMEPAGE.L3T_EXT_META2 (UNID, NODE_KEY)@
CREATE INDEX HOMEPAGE.EXT_META2_PARENT_IDX ON HOMEPAGE.L3T_EXT_META2 (PARENT)@
COMMIT@
