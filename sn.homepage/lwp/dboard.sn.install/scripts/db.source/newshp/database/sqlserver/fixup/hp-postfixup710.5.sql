-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2017, 2017                             
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              


-- MyPage widgets need to use the webresources dereference to render properly when the context roots are all changed to unique values
UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.homepage/widgets/activities/activities.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.homepage/widgets/activities/activities.xml'
WHERE   WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/activities/activities.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.homepage/widgets/blogs/blogs.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.homepage/widgets/blogs/blogs.xml'
WHERE   WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/blogs/blogs.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.homepage/widgets/communities/communities.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.homepage/widgets/communities/communities.xml'
WHERE   WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/communities/communities.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.homepage/widgets/bookmarks/dogear.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.homepage/widgets/bookmarks/dogear.xml'
WHERE   WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/bookmarks/dogear.xml';

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{webresources}/web/lconn.homepage/widgets/profiles/profiles.xml', WIDGET_SECURE_URL = '{webresources}/web/lconn.homepage/widgets/profiles/profiles.xml'
WHERE   WIDGET_URL = '${COMMON_CONTEXT_ROOT}/web/lconn.homepage/widgets/profiles/profiles.xml';


GO
