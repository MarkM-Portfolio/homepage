/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2008, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.utils.impl; 

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;
 
import com.ibm.lconn.homepage.services.utils.WASAdminService;
import com.ibm.websphere.management.AdminService;
import com.ibm.websphere.management.AdminServiceFactory;

/**
 * 
 * @author Lorenzo Notarfonzo
 * @author Guo Du
 *
 */
public class WASAdminServiceImpl implements WASAdminService {

	private final static String CLASS_NAME = WASAdminServiceImpl.class.getName();
	private static Logger logger = Logger.getLogger(CLASS_NAME);

	private AdminService adminService;

	public String getDefaultDomain() {
		return retriveAdminService().getDefaultDomain();
	}

	public String getCellName() {
		return retriveAdminService().getCellName();
	}

	public String getNodeName() {
		return retriveAdminService().getNodeName();
	}

	public String getProcessName() {
		return retriveAdminService().getProcessName();
	}

	private AdminService retriveAdminService() {
		if (logger.isLoggable(FINER))
			logger.entering(CLASS_NAME, "retriveAdminService");
		// lazy get adminService from AdminServiceFactory
		if(adminService==null){
			adminService = AdminServiceFactory.getAdminService();
    		if (logger.isLoggable(FINER))
    			logger.log(FINER, "inited adminService from AdminServiceFactory");
		}
		if (logger.isLoggable(FINER))
			logger.exiting(CLASS_NAME, "retriveAdminService");
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}
}
