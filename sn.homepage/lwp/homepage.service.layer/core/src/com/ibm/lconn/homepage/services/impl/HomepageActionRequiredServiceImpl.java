/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2012, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.impl;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.dao.interfaces.IHomepageActionRequiredDao;
import com.ibm.lconn.homepage.services.IHomepageActionRequiredService;

/**
 * Class providing services for ActionRequired function in Homepage.
 * @author Jim Antill
 *
 */

public class HomepageActionRequiredServiceImpl implements IHomepageActionRequiredService {
	private final static String CLASS_NAME = HomepageActionRequiredServiceImpl.class.getName();	
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	@Autowired
	private IHomepageActionRequiredDao homepageActionRequiredDao;
	
	@Override
	public long getActionRequiredTotalByExtId(String personExtId) {
		
		if (logger.isLoggable(FINER)) {
			logger.entering(CLASS_NAME, 
							"getActionRequiredTotalByExtId",personExtId);
		}
		
		long count = this.homepageActionRequiredDao.getActionRequiredTotalByExtId(personExtId);
		
		if (logger.isLoggable(FINER)) {
			logger.exiting(CLASS_NAME, 
							"getActionRequiredTotalByExtId",count);
		}
		
		return count;
	}
	
	public void setHomepageActionRequiredDao(IHomepageActionRequiredDao d) {
		this.homepageActionRequiredDao = d;
	}
}