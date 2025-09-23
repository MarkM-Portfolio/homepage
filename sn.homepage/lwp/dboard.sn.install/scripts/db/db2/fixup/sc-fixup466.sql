-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2001, 2015                                    
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- 5724_S68                                              
CONNECT TO HOMEPAGE@

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					start	FIXUP 466
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++

------------------------------------------------
-- INCLUDE FIX UP 466
------------------------------------------------

{include.hp-fixup466.sql}


--------------------------------------------------------------------------------------------------------------
-- 133438: [ Fix in Hand ] [S26 Approved] PC1, S26, Homepage DB: The 087_fixup464.sql script drops and rebuild indexes, 
-- but does not regenerate statistics. 
-- This can lead to un-recorded stats and a lapse in query plans.
--------------------------------------------------------------------------------------------------------------
runstats on table HOMEPAGE.NR_AGGREGATED_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_RESPONSES_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_PROFILES_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_COMMUNITIES_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ACTIVITIES_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_BLOGS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_BOOKMARKS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_FILES_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_FORUMS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_WIKIS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_TAGS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_STATUS_UPDATE_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_EXTERNAL_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_ACTIONABLE_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NOTIFICATION_SENT_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_NOTIFICATION_RECEIV_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_SAVED_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_TOPICS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_MENTIONS_READERS with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_DISCOVERY_VIEW with distribution and detailed indexes all allow write access@
COMMIT@
runstats on table HOMEPAGE.NR_PROFILES_VIEW with distribution and detailed indexes all allow write access@
COMMIT@



------------------------------------------------------------------------------------------------
-- UPDATE SCHEMA VERSION AND RELEASE VERSION to 466
------------------------------------------------------------------------------------------------
UPDATE  HOMEPAGE.HOMEPAGE_SCHEMA SET DBSCHEMAVER = 466, RELEASEVER = '4.6.0.0' 
WHERE   DBSCHEMAVER = 465@

------------------------------------------------------------------------------------------------

COMMIT;

-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++
--					end	FIXUP 466
-- +++++++++++++++++++++++++++++++++++++++++++++
-- +++++++++++++++++++++++++++++++++++++++++++++


--------------------------------------
-- FLUSH
--------------------------------------
FLUSH PACKAGE CACHE DYNAMIC@


--------------------------------------
-- TERMINATE
--------------------------------------
connect reset@
terminate@