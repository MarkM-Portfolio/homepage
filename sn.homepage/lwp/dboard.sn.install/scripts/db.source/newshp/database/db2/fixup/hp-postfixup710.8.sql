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

-- Regression on being able to customize profiles widgets
UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/common-tags.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/common-tags.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/common-tags.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/follow.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/follow.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/follow.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/friends-fullpage.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/friends-fullpage.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends-fullpage.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/friends.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/friends.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/friends.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/linkroll.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/linkroll.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/linkroll.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/multiWidget.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/multiWidget.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multiWidget.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/multifeedreader.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/multifeedreader.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/multifeedreader.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/profile-details.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/profile-details.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/profile-details.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/report-chain.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/report-chain.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/report-chain.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/tags.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/tags.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/tags.xml'@

UPDATE  HOMEPAGE.WIDGET SET WIDGET_URL = '{deploymentConfig}/static/common/widgets/descriptors/wall.xml', WIDGET_SECURE_URL = '{deploymentConfig}/static/common/widgets/descriptors/wall.xml'
WHERE   WIDGET_URL = '{webresources}/web/lconn.profiles.widgets/descriptors/wall.xml'@


COMMIT@
