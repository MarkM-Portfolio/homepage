/* ***************************************************************** */
/*                                                                   */
/* HCL Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright HCL Technologies Limited. 2010, 2021                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ibm.lconn.homepage.web.controller.ViewConstants;

@Controller
@RequestMapping(value = "/admin/AStest")
public class AsTestController {

    @RequestMapping(value = "/PostEvent", method = RequestMethod.GET)
    public String gettestAsPostPage(HttpServletRequest request, HttpServletResponse response) {

        return ViewConstants.VIEW_HOMEPAGE_MAIN_TESTASPOSTPAGE;

    }

}
