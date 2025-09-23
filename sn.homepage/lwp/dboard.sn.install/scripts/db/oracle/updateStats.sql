-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2014, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 


-- 5724_S68 
BEGIN 
 
 
dbms_stats.gather_schema_stats('HOMEPAGE', cascade => TRUE, options => 'GATHER AUTO', method_opt => 'for all columns size auto', estimate_percent => 10); 
 
END; 
/ 
 
QUIT; 
