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

-- 5724_S68                                              

CONNECT TO HOMEPAGE@

{include.news-fixup701.sql}

-----------------------------------------------------------------------
-- Update default packages to set DELETEABLE to 0
-----------------------------------------------------------------------
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='0540433e-d180-47e4-a542-1719cbfb89c7'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='08c5e2f7-1b6e-4b38-a4d8-3cdcf4f7dfb5'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='7bc67cb8-5dd4-44a5-b7ca-e97903f909db'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='83446f26-7638-442e-a6f6-e44d4e50c8d4'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='3ae55cb4-3cf8-4eee-bb5f-2d76079f113b'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='f3cf0035-05a6-4f39-899f-f43216724ef9'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='e7e069c4-81d9-4992-adf3-c712999e5993'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='1d06d631-2ea0-42dc-ac35-0f236cb5dc6d'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='c14c75dd-f3ea-4c2a-b47b-32ea6f591d7c'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='11274369-a56c-43c8-abe2-8a84ae998101'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='2b088081-68b8-4800-a0d9-3ee5d66ded7b'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='78f1a6ea-cb3e-4594-909c-78da69a31257'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='d64c6561-c865-413c-9dea-f932a6281194'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='46e6c383-c6e0-4eb0-9640-cbc87df631f9'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='e27e5384-cb82-4df4-9bff-db505a8a933c'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='3abb4bf2-6f28-467f-ac13-cbb16ecd21d8'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='c54eac4d-9274-413d-8360-595b5f870509'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='5dd9e7b5-9472-4bf4-b607-705569797203'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='343bf660-77e3-4ea6-8b64-5c87fcca9814'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='c8f7f8cc-fed7-4efd-9873-07d598b68ef1'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='df4d849a-dbd1-4568-9b49-d596457d6f50'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='9970d7df-a100-4cc7-9b94-ea7d232cee80'@
UPDATE HOMEPAGE.L3T_PACKAGES SET DELETEABLE = 0 WHERE PACKAGE_ID='758d18ef-07e1-4c32-996b-3eaa726e0730'@

------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 701
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 701, RELEASEVER = '6.0.0.0' 
WHERE   DBSCHEMAVER = 700@


------------------------------------------------------------------------------------------------




COMMIT@

--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@

--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@