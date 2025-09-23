/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2011, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.dao.ibatis;

import static java.util.logging.Level.FINER;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.dao.interfaces.IHomepageActionRequiredDao;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.data.dao.impl.ibatis.CrudDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Class that provides services for Homepage startup actions.
 * 
 * @author Jim Antill
 *
 */

public class HomepageActionRequiredDao extends CrudDao implements IHomepageActionRequiredDao {
    private final static String CLASS_NAME = HomepageActionRequiredDao.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ResourceBundle databaseResourceBundle;

    public long getActionRequiredTotalByExtId(String personExtId) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "getActionRequiredTotalByExtId", personExtId);
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("schema", getSchema());
        params.put("personExtId", personExtId);

        long actionRequiredCount = (Long) sqlMapClientOperations.queryForObject("getCountActionRequiredByExternalID-NR_ACTIONABLE_READERS", params);

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "getActionRequiredTotalByExtId", actionRequiredCount);
        }

        return actionRequiredCount;
    }

    public String getSchema() {
        return "HOMEPAGE";
    }

    public ResourceBundle getDatabaseResourceBundle() {
        return databaseResourceBundle;
    }

    public void setDatabaseResourceBundle(ResourceBundle databaseResourceBundle) {
        this.databaseResourceBundle = databaseResourceBundle;
    }

    @Override
    public String getTableName() {
        // Intentionally left blank
        return null;
    }
}
