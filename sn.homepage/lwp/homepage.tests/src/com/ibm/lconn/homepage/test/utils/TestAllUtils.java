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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ 
    com.ibm.lconn.homepage.test.utils.ResourceBundleTest.class, 
    com.ibm.lconn.homepage.test.utils.UIServicesUtilsTest.class, 
    com.ibm.lconn.homepage.test.utils.UrlDecoderTest.class })

/**
 * 
 * To test the service layer
 * 
 * @author Piyush Jain
 */
public class TestAllUtils {

    public static File createTempFileWithContent(String prefix, String content) throws IOException {
        File tempFile = File.createTempFile(prefix, ".tmp");
        FileWriter fstream = new FileWriter(tempFile, false);
        BufferedWriter out = new BufferedWriter(fstream);
        try {
            out.write(content);
        } finally {
            out.close();
        }
        return tempFile;
    }

}
