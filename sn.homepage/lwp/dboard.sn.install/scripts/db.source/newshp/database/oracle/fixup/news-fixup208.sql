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

-- US COMMIT;vernment Users Restricted Rights - Use, duplication or       
-- {COPYRIGHT}


---------------------------------------------------------------------------------
------------------------ START NEWS FIXUP 208 -----------------------------------
---------------------------------------------------------------------------------

-- 85451: Modify an entry in NR_TEMPLATE table
UPDATE HOMEPAGE.NR_TEMPLATE SET DATA_SOURCE_STRING = 'itemName;itemDirectHttpPath' WHERE NAME = 'dogearBookmarkEntry';
COMMIT;

--85804: [fixup208] Add a new index to support like counting for board comments during seedlist generation
CREATE INDEX HOMEPAGE.CREATION_DATE_ITEM_IDX
	ON HOMEPAGE.BOARD_COMMENTS (CREATION_DATE ASC, ITEM_ID) TABLESPACE "NEWSINDEXTABSPACE";
COMMIT;	

-- 85789: Remove the columns ORGANIZATION_ID in the next fixup as it is not needed in NR_AS_COLLECTION_CONFIG and NR_AS_CRAWLER_STATUS
ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG DROP COLUMN ORGANIZATION_ID;
COMMIT;

ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS DROP COLUMN ORGANIZATION_ID;
COMMIT;

------------------------------
-- 85943: Update all the records in NR_SOURCE_TYPE having {webresources} in URL_SSL and IMAGE_URL_SSL
------------------------------
-- activities
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconActivities16.png'
WHERE SOURCE = 'activities';

-- blogs
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconBlogs16.png'
WHERE SOURCE = 'blogs';

-- communities
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconCommunities16.png'
WHERE SOURCE = 'communities';

-- wikis
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconWikis16.png'
WHERE SOURCE = 'wikis';

-- profiles
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconProfiles16.png'
WHERE SOURCE = 'profiles';

-- homepage
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconHome16.png'
WHERE SOURCE = 'homepage';

-- dogear
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconBookmarks16.png'
WHERE SOURCE = 'dogear';

-- files
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconFiles16.png'
WHERE SOURCE = 'files';

-- forums
UPDATE HOMEPAGE.NR_SOURCE_TYPE SET 
	IMAGE_URL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png',
	IMAGE_URL_SSL = '{webresources}/web/com.ibm.lconn.core.styles/images/iconForums16.png'
WHERE SOURCE = 'forums';

COMMIT;

-- 86051: [fixup208] Migration for EMD records 
--------------------------------------------------------------------------------- 
------------------------ START 4.0 -> 4.5 EMD Migration -------------------------
--------------------------------------------------------------------------------- 

 
-- Delete the duplicate EMD_RESOURCE_TYPE rows 
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 5; 

COMMIT;

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 6; 

COMMIT;

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 9; 

COMMIT;

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 12; 
 
-- Start mapping to temp number 

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 902  
WHERE RESOURCE_TYPE = 2; 

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 903 
WHERE RESOURCE_TYPE = 3;  
COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 904 
WHERE RESOURCE_TYPE = 4;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 905 
WHERE RESOURCE_TYPE = 10;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 906 
WHERE RESOURCE_TYPE = 11;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 907 
WHERE RESOURCE_TYPE = 13; 

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 908 
WHERE RESOURCE_TYPE = 14; 

COMMIT;

-- Remap temp numbers to 4.5 cateCOMMIT;ry format 
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 4 
WHERE RESOURCE_TYPE = 902; 

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 5 
WHERE RESOURCE_TYPE = 903;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 3 
WHERE RESOURCE_TYPE = 904;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 2 
WHERE RESOURCE_TYPE = 905;  

COMMIT;

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 9 
WHERE RESOURCE_TYPE = 906;  
 
COMMIT; 
  
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 10 
WHERE RESOURCE_TYPE = 907; 
 
COMMIT; 
 
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 6 
WHERE RESOURCE_TYPE = 908; 
 
COMMIT; 

-- 86174: Make addition to the initData.sql for new {blogDuplicateEntry} template element
INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogDuplicateEntry','blogDuplicateEntry', 'duplicateTo.name;duplicateTo.htmlPath', 'link', 1);

COMMIT;

--------------------------------------------------------------------------------- 
------------------------    END 4.0 -> 4.5 EMD Migration ------------------------
--------------------------------------------------------------------------------- 


---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 208 -------------------------------------
---------------------------------------------------------------------------------
