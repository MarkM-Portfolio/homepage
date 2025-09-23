/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2010, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.homepage.web.controller.home;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.lconn.homepage.services.INewsService;
import com.ibm.lconn.homepage.web.controller.AbstractHomepageController;

public abstract class AbstractNewsController extends AbstractHomepageController {
    private static final long serialVersionUID = -9080527815510500142L;

    protected static final int DEFAULT_PAGE_SIZE = 20;

    @Autowired
    private INewsService newsService;

    public void setNewsService(INewsService newsService) {
        this.newsService = newsService;
    }

    public INewsService getNewsService() {
        return newsService;
    }

}
