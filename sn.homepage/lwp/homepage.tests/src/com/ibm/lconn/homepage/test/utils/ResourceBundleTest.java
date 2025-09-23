/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2009, 2022                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.test.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Locale;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ibm.lconn.homepage.model.Category;
import com.ibm.lconn.homepage.utils.ResourceBundle;

public class ResourceBundleTest {

    @Autowired
    private static ResourceBundle databaseResourceBundle;
    @Autowired
    private static ResourceBundle jspResourceBundle;
    @Autowired
    private static ResourceBundle catalogServiceUIResourceBundle;
    protected static ClassPathXmlApplicationContext context;

    @BeforeClass
    public static void initSpring() {
        String[] configLocations = { "spring/homepage/resources/ResourceBundleContext.xml" };
        context = new ClassPathXmlApplicationContext(configLocations);
        assertNotNull(context);

        databaseResourceBundle = (ResourceBundle) context.getBean("databaseResourceBundle");
        assertNotNull(databaseResourceBundle);

        jspResourceBundle = (ResourceBundle) context.getBean("jspResourceBundle");
        assertNotNull(jspResourceBundle);

        catalogServiceUIResourceBundle = (ResourceBundle) context.getBean("catalogServiceUIResourceBundle");
        assertNotNull(catalogServiceUIResourceBundle);
    }

    public void setCatalogServiceUIResourceBundle(ResourceBundle catalogServiceUIResourceBundle) {
        this.catalogServiceUIResourceBundle = catalogServiceUIResourceBundle;
    }

    public void setJspResourceBundle(ResourceBundle jspResourceBundle) {
        this.jspResourceBundle = jspResourceBundle;
    }

    public void setDatabaseResourceBundle(ResourceBundle databaseResourceBundle) {
        this.databaseResourceBundle = databaseResourceBundle;
    }

    @Test
    public void testDatabaseBundle() {
        assertEquals("CLFRQ0312E: An error occurred while retrieving notifications received for the user referenced by this ID 123456789.",
                databaseResourceBundle.getString("error.notification.getNotificationsReceivedByPerson", "123456789"));
    }

    @Test
    public void testJspBundle() {
        assertEquals("You searched for this term: testing junit", jspResourceBundle.getString("jsp.search.searched.for", "testing junit"));
    }

    @Test
    public void testCategory() {
        assertEquals("Bookmarks", Category.DOGEAR.toLocalizedString(catalogServiceUIResourceBundle, Locale.getDefault()));
    }

}
