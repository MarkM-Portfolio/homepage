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
------------------------ START NEWS FIXUP 209 -----------------------------------
---------------------------------------------------------------------------------

-- 86306: Libraries app registration - Update DB migration to add new row to NR_SOURCE for Libraries app
INSERT INTO HOMEPAGE.NR_SOURCE_TYPE 	
	(SOURCE_TYPE_ID, SOURCE_TYPE, SOURCE, DISPLAY_NAME, IMAGE_URL, PUBLISHED, UPDATED, IS_ENABLED, SUMMARY, URL, URL_SSL, IMAGE_URL_SSL)
VALUES 							
	('ecm_files', 	10000, 	'ecm_files', 'Libraries', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png', null, null, 1, null, '{ecm_files}', '{ecm_files}', '{webresources}/web/com.ibm.oneui3.styles/imageLibrary/Icons/ComponentsGray/LibraryManagedGray16.png'); 

COMMIT;

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 209 -------------------------------------
---------------------------------------------------------------------------------
