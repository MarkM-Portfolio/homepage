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
------------------------ START NEWS FIXUP 208 -----------------------------------
---------------------------------------------------------------------------------

-- 85451: Modify an entry in NR_TEMPLATE table
UPDATE HOMEPAGE.NR_TEMPLATE SET DATA_SOURCE_STRING = 'itemName;itemDirectHttpPath' WHERE NAME = 'dogearBookmarkEntry';
GO

--85804: [fixup208] Add a new index to support like counting for board comments during seedlist generation
CREATE INDEX CREATION_DATE_ITEM_IDX
	ON HOMEPAGE.BOARD_COMMENTS (CREATION_DATE ASC, ITEM_ID);
GO

-- 85789: Remove the columns ORGANIZATION_ID in the next fixup as it is not needed in NR_AS_COLLECTION_CONFIG and NR_AS_CRAWLER_STATUS
DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.NR_AS_COLLECTION_CONFIG') and parent_column_id=3)
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO


ALTER TABLE HOMEPAGE.NR_AS_COLLECTION_CONFIG DROP COLUMN ORGANIZATION_ID;
GO

DECLARE @CONST_NAME as nvarchar(128), @DROP_STMT as nvarchar(2000)
SET @CONST_NAME = (select name from sys.default_constraints where parent_object_id=OBJECT_ID('HOMEPAGE.NR_AS_CRAWLER_STATUS') and parent_column_id=3)
SET @DROP_STMT = N'ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS DROP CONSTRAINT ' + @CONST_NAME
EXEC (@DROP_STMT)
GO


ALTER TABLE HOMEPAGE.NR_AS_CRAWLER_STATUS DROP COLUMN ORGANIZATION_ID;
GO

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

GO

-- 86051: [fixup208] Migration for EMD records 
--------------------------------------------------------------------------------- 
------------------------ START 4.0 -> 4.5 EMD Migration -------------------------
--------------------------------------------------------------------------------- 

 
-- Delete the duplicate EMD_RESOURCE_TYPE rows 
DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 5; 

GO

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 6; 

GO

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 9; 

GO

DELETE FROM HOMEPAGE.EMD_RESOURCE_PREF 
WHERE RESOURCE_TYPE = 12; 
 
-- Start mapping to temp number 

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 902  
WHERE RESOURCE_TYPE = 2; 

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 903 
WHERE RESOURCE_TYPE = 3;  
GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 904 
WHERE RESOURCE_TYPE = 4;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 905 
WHERE RESOURCE_TYPE = 10;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 906 
WHERE RESOURCE_TYPE = 11;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 907 
WHERE RESOURCE_TYPE = 13; 

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 908 
WHERE RESOURCE_TYPE = 14; 

GO

-- Remap temp numbers to 4.5 category format 
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 4 
WHERE RESOURCE_TYPE = 902; 

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 5 
WHERE RESOURCE_TYPE = 903;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 3 
WHERE RESOURCE_TYPE = 904;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 2 
WHERE RESOURCE_TYPE = 905;  

GO

UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 9 
WHERE RESOURCE_TYPE = 906;  
 
GO 
  
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 10 
WHERE RESOURCE_TYPE = 907; 
 
GO 
 
UPDATE HOMEPAGE.EMD_RESOURCE_PREF 
SET RESOURCE_TYPE = 6 
WHERE RESOURCE_TYPE = 908; 
 
GO 

-- 86174: Make addition to the initData.sql for new {blogDuplicateEntry} template element
INSERT INTO HOMEPAGE.NR_TEMPLATE
		(TEMPLATE_ID, NAME, DATA_SOURCE_STRING, FORMAT, NO_VALUES)
VALUES	('blogDuplicateEntry','blogDuplicateEntry', 'duplicateTo.name;duplicateTo.htmlPath', 'link', 1);

GO

--------------------------------------------------------------------------------- 
------------------------    END 4.0 -> 4.5 EMD Migration ------------------------
--------------------------------------------------------------------------------- 

---------------------------------------------------------------------------------
------------------------ END NEWS FIXUP 208 -------------------------------------
---------------------------------------------------------------------------------
