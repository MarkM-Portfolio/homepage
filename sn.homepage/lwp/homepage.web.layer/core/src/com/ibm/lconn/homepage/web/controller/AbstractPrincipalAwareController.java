/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller;

import static java.util.logging.Level.FINER;

import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public abstract class AbstractPrincipalAwareController extends AbstractWebController {

    private static final long serialVersionUID = -2737090756685055091L;
    private final static String CLASS_NAME = AbstractPrincipalAwareController.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    /**
     * TODO : VB: what's that for???
     */
    private String flag;
    
    private boolean isTestAdminRoleSet = false;

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getFlag() {
        return flag;
    }

    public boolean isUserInAdminRole() {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "isUserInAdminRole");

        boolean isAdmin = false || isTestAdminRoleSet;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            for (GrantedAuthority authority : auth.getAuthorities()) {
                System.out.println("    " + authority.getAuthority().toLowerCase());
                if (StringUtils.isEmpty(authority.getAuthority())) {
                    continue;
                }
                if ("admin".equals(authority.getAuthority().toLowerCase())) {
                    isAdmin = true;
                    break;
                }
            }
        }

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "isUserInAdminRole", isAdmin);

        return isAdmin;
    }
    
    public void setAdminRoleForTest() {
        isTestAdminRoleSet = true;
    }

    protected void addPagedAttributesToModel(Model model) {
        super.addPagedAttributesToModel(model);
    }

}
