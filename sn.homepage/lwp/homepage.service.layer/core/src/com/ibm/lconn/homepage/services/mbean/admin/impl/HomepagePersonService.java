/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright  HCL Technologies Limited. 2008, 2021                   */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.services.mbean.admin.impl;

import static java.util.logging.Level.FINER;
import static java.util.logging.Level.FINEST;
import static java.util.logging.Level.INFO;
import static java.util.logging.Level.SEVERE;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import com.ibm.lconn.homepage.dao.interfaces.IUIDao;
import com.ibm.lconn.homepage.services.utils.ModelConverter;
import com.ibm.lconn.homepage.utils.ResourceBundle;
import com.ibm.lconn.hpnews.data.dao.interfaces.IPersonDao;
import com.ibm.lconn.hpnews.data.model.Person;
import com.ibm.lconn.hpnews.service.impl.context.ApplicationContext;
import com.ibm.lconn.hpnews.service.waltz.IWaltzSyncService;
import com.ibm.lconn.hpnews.spring.context.BeanFactoryHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * @author Lorenzo Notarfonzo
 * 
 */
public class HomepagePersonService implements HomepagePersonServiceMBean {

    private final static String CLASS_NAME = HomepagePersonService.class.getName();
    private static Logger logger = Logger.getLogger(CLASS_NAME);

    @Autowired
    private ResourceBundle serviceResourceBundle;
    @Autowired
	@Qualifier("HPNEWS-DATABASE:personDao")
    private IPersonDao personDao;
    @Autowired
	@Qualifier("HPNEWS-SERVICE:waltzSyncService")
    private IWaltzSyncService waltzSyncService;
    @Autowired
    private ModelConverter modelConverter;
    @Autowired
    private IUIDao uiDao;

    /**
     * The command resets WELCOME_MODE flag for all users to its default value
     * 
     * @returns informational message
     */
    public String resetWelcomeFlagAllMembers() {
        if (logger.isLoggable(FINER))
            logger.entering(CLASS_NAME, "resetWelcomeFlagAllMembers");

        int recordsUpdated = uiDao.resetWelcomeModeFlag(null);

        String userMsg = serviceResourceBundle.getString("info.homepagepersonservice.result",
                recordsUpdated);

        if (logger.isLoggable(FINER))
            logger.exiting(CLASS_NAME, "resetWelcomeFlagAllMembers");
        return userMsg;

    }

    /**
     * The command resets WELCOME_MODE flag for the specified user to its
     * default value. The user is identified by email
     * 
     * @param email
     *            E-mail for the user to reset welcome mode
     * @returns informational message
     */
    public String resetWelcomeFlagMemberByEmail(String email) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "resetWelcomeFlagMemberByEmail", email);
        }
        Person person = personDao.getByEmail(email);

        String userMsg = null;
        int recordsUpdated = 0;
        if (person != null) {
            recordsUpdated = resetWelcomeFlag(person);
        }

        if (recordsUpdated == 1) {
            userMsg = serviceResourceBundle.getString("info.homepagepersonservice.result", 1);
        } else {
            userMsg = serviceResourceBundle.getString(
                    "error.mbean.person.service.email.notfound.hp", email);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "resetWelcomeFlagMemberByEmail", userMsg);
        }
        return userMsg;

    }

    /**
     * The command resets WELCOME_MODE flag for the specified user to its
     * default value. The user is identified by login name
     * 
     * @param loginName
     *            Login name for the user to reset welcome mode
     * @returns informational message
     */
    public String resetWelcomeFlagMemberByLoginName(String loginName) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "resetWelcomeFlagMemberByLoginName", loginName);
        }

        Person person = personDao.getPersonWithLoginNamesByLoginName(loginName);

        String userMsg = null;
        int recordsUpdated = 0;
        if (person != null) {
            recordsUpdated = resetWelcomeFlag(person);
        }

        if (recordsUpdated == 1) {
            userMsg = serviceResourceBundle.getString("info.homepagepersonservice.result", 1);
        } else {
            userMsg = serviceResourceBundle.getString(
                    "error.mbean.person.service.loginname.notfound.hp", loginName);
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "resetWelcomeFlagMemberByEmail", userMsg);
        }
        return userMsg;

    }

    /**
     * The command resets WELCOME_MODE flag for the batch of users to its
     * default value. The user emails are listed in a specified file
     * 
     * @param repositoryLocation
     *            Name of the file with user emails (one email per line)
     * 
     * @returns informational message
     */
    public String resetWelcomeFlagBatchMembersByEmail(String repositoryLocation) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail", repositoryLocation);
        }

        String userMsg;
        int recordsUpdated = 0;

        try {
            List<String> userMails = readLines(repositoryLocation);
            if (userMails.size() > 0) {
                for (String email : userMails) {
                    Person person = personDao.getByEmail(email);
                    if (person != null) {
                        if (logger.isLoggable(FINEST)) {
                            logger.logp(FINEST, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail",
                                    "Update person with this email: {0}", person.getUserMail());
                        }
                        recordsUpdated += uiDao.resetWelcomeModeFlag(person.getPersonId());
                    } else {
                        String msg = serviceResourceBundle.getString(
                                "error.mbean.person.service.email.notfound.hp", email);
                        if (logger.isLoggable(SEVERE)) {
                            logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail",
                                    msg);
                        }
                    }
                }
            }

            if (userMails.size() > recordsUpdated) {
                userMsg = serviceResourceBundle.getString("warn.mbean.person.service.notallfound",
                        recordsUpdated, userMails.size());
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail", userMsg);
                }
            } else {
                userMsg = serviceResourceBundle.getString("info.homepagepersonservice.result",
                        recordsUpdated);

                if (logger.isLoggable(INFO)) {
                    logger.logp(INFO, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail", userMsg);
                }
            }
        } catch (FileNotFoundException e) {
            userMsg = serviceResourceBundle.getString("error.mbean.person.service.filenotfound",
                    repositoryLocation);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail", userMsg);
            }
        } catch (IOException e) {
            userMsg = serviceResourceBundle.getString("error.mbean.person.service.fileerror",
                    repositoryLocation, e.getMessage());
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail", userMsg);
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "resetWelcomeFlagBatchMembersByEmail");
        }
        return userMsg;

    }

    /**
     * The command resets WELCOME_MODE flag for the batch of users to its
     * default value. The user login names are listed in a specified file
     * 
     * @param repositoryLocation
     *            Name of the file with user login names (one name per line)
     * 
     * @returns informational message
     */
    public String resetWelcomeFlagBatchMembersByLoginName(String repositoryLocation) {

        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName",
                    repositoryLocation);
        }

        String userMsg;
        int recordsUpdated = 0;

        try {
            List<String> userLogins = readLines(repositoryLocation);
            if (userLogins.size() > 0) {
                for (String loginName : userLogins) {
                    Person person = personDao.getPersonWithLoginNamesByLoginName(loginName);
                    if (person != null) {
                        if (logger.isLoggable(FINEST)) {
                            logger.logp(FINEST, CLASS_NAME,
                                    "resetWelcomeFlagBatchMembersByLoginName",
                                    "Update person with this email: {0}", person.getUserMail());
                        }
                        recordsUpdated += uiDao.resetWelcomeModeFlag(person.getPersonId());
                    } else {
                        String msg = serviceResourceBundle.getString(
                                "error.mbean.person.service.email.notfound.hp", loginName);
                        if (logger.isLoggable(SEVERE)) {
                            logger.logp(SEVERE, CLASS_NAME,
                                    "resetWelcomeFlagBatchMembersByLoginName", msg);
                        }
                    }
                }
            }

            if (userLogins.size() > recordsUpdated) {
                userMsg = serviceResourceBundle.getString("warn.mbean.person.service.notallfound",
                        recordsUpdated, userLogins.size());
                if (logger.isLoggable(SEVERE)) {
                    logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName",
                            userMsg);
                }
            } else {
                userMsg = serviceResourceBundle.getString("info.homepagepersonservice.result",
                        recordsUpdated);

                if (logger.isLoggable(INFO)) {
                    logger.logp(INFO, CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName",
                            userMsg);
                }
            }
        } catch (FileNotFoundException e) {
            userMsg = serviceResourceBundle.getString("error.mbean.person.service.filenotfound",
                    repositoryLocation);
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName", userMsg);
            }
        } catch (IOException e) {
            userMsg = serviceResourceBundle.getString("error.mbean.person.service.fileerror",
                    repositoryLocation, e.getMessage());
            if (logger.isLoggable(SEVERE)) {
                logger.logp(SEVERE, CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName", userMsg);
            }
        }

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "resetWelcomeFlagBatchMembersByLoginName");
        }
        return userMsg;

    }

    private int resetWelcomeFlag(Person person) {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "resetWelcomeFlag", person);
        }
        int result = 0;
        if (person != null) {
            result = uiDao.resetWelcomeModeFlag(person.getPersonId());
        }
        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "resetWelcomeFlag", result);
        }
        return result;
    }

    private List<String> readLines(String fileName) throws FileNotFoundException, IOException {
        if (logger.isLoggable(FINER)) {
            logger.entering(CLASS_NAME, "readLines", fileName);
        }
        List<String> result = new LinkedList<String>();

        File linkFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(linkFile));

        String line;
        while ((line = reader.readLine()) != null) {
            String trimmedLine = line.trim();
            if (trimmedLine.length() > 0) {
                result.add(trimmedLine);
            }
        }

        reader.close();

        if (logger.isLoggable(FINER)) {
            logger.exiting(CLASS_NAME, "readLines", result.size());
        }
        return result;
    }

    public ResourceBundle getServiceResourceBundle() {
        return serviceResourceBundle;
    }

    public void setServiceResourceBundle(ResourceBundle serviceResourceBundle) {
        this.serviceResourceBundle = serviceResourceBundle;
    }

    public IWaltzSyncService getWaltzSyncService() {
        return waltzSyncService;
    }

    public void setWaltzSyncService(IWaltzSyncService waltzSyncService) {
        this.waltzSyncService = waltzSyncService;
    }

    public IPersonDao getPersonDao() {
        return personDao;
    }

    public void setPersonDao(IPersonDao personDao) {
        this.personDao = personDao;
    }

    public ModelConverter getModelConverter() {
        return modelConverter;
    }

    public void setModelConverter(ModelConverter modelConverter) {
        this.modelConverter = modelConverter;
    }

    public IUIDao getUiDao() {
        return uiDao;
    }

    public void setUiDao(IUIDao uiDao) {
        this.uiDao = uiDao;
    }

}
