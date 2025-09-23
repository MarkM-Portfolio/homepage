-- ***************************************************************** 
--                                                                   
-- IBM Confidential                                                  
--                                                                   
-- OCO Source Materials                                              
--                                                                   
-- Copyright IBM Corp. 2016                                   
--                                                                   
-- The source code for this program is not published or otherwise    
-- divested of its trade secrets, irrespective of what has been      
-- deposited with the U.S. Copyright Office.                         
--                                                                   
-- ***************************************************************** 

-- {COPYRIGHT}

-- -----------------------------------------------------------------
-- Defect 173268, 183264:
-- -----------------------------------------------------------------

-- Find any rows in the NR_STORIES table that reference a row in NR_ENTRIES where we have a duplicate
-- i.e. 2+ rows in NR_ENTRIES with the same ITEM_ID and ORGANIZATION_ID
-- Update those stories with the ENTRY_ID matching the story ITEM_ID or ITEM_CORRELATION_ID (for comments/replies)
-- in the NR_ENTRIES_ARCHIVE table where no duplicate rows exist for ITEM_ID and ORGANIZATION_ID

UPDATE HOMEPAGE.NR_STORIES S SET S.ENTRY_ID =
(
   SELECT
   EA.ENTRY_ID
   FROM HOMEPAGE.NR_ENTRIES_ARCHIVE EA
   WHERE S.ITEM_ID=EA.ITEM_ID
   AND S.ORGANIZATION_ID=EA.ORGANIZATION_ID
)
WHERE S.ENTRY_ID IN
(
   SELECT
   ENTRY_ID
   FROM
   (
      (
         SELECT
         ITEM_ID, ORGANIZATION_ID
         FROM HOMEPAGE.NR_ENTRIES
         GROUP BY ITEM_ID, ORGANIZATION_ID HAVING COUNT(*) > 1
      )
      DUPITEMIDS
      INNER JOIN HOMEPAGE.NR_ENTRIES E ON DUPITEMIDS.ITEM_ID = E.ITEM_ID
      AND DUPITEMIDS.ORGANIZATION_ID = E.ORGANIZATION_ID
      AND E.ENTRY_ID NOT IN ( SELECT ENTRY_ID FROM HOMEPAGE.NR_ENTRIES_ARCHIVE )
   )
)@
COMMIT@

-- Remove the duplicate ENTRY_ID rows from NR_ENTRIES

DELETE
FROM HOMEPAGE.NR_ENTRIES E
WHERE E.ENTRY_ID IN
(
   SELECT
   ENTRY_ID
   FROM
   (
      (
         SELECT
         ITEM_ID, ORGANIZATION_ID
         FROM HOMEPAGE.NR_ENTRIES
         GROUP BY ITEM_ID, ORGANIZATION_ID HAVING COUNT(*) > 1
      )
      DUPITEMIDS
      INNER JOIN HOMEPAGE.NR_ENTRIES E ON DUPITEMIDS.ITEM_ID = E.ITEM_ID
      AND DUPITEMIDS.ORGANIZATION_ID = E.ORGANIZATION_ID
      AND E.ENTRY_ID NOT IN ( SELECT ENTRY_ID FROM HOMEPAGE.NR_ENTRIES_ARCHIVE )
   )
)@
COMMIT@

-- drop old index
DROP INDEX HOMEPAGE.NR_ENTRIES_ITEMORG@
COMMIT@

-- recreate as a unique index
CREATE UNIQUE INDEX HOMEPAGE.NR_ENTRIES_ITEMORG 
	ON HOMEPAGE.NR_ENTRIES(ITEM_ID, ORGANIZATION_ID)@
COMMIT@

-- drop old index
DROP INDEX HOMEPAGE.NR_ENTRIES_AR_ITEM@
COMMIT@

-- create new unique index (as per NR_ENTRIES table)
CREATE UNIQUE INDEX HOMEPAGE.NR_ENTRIES_AR_ITEMORG
 	ON HOMEPAGE.NR_ENTRIES_ARCHIVE(ITEM_ID, ORGANIZATION_ID)@ 
COMMIT@
