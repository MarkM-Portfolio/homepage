package com.ibm.lconn.homepage.web.controller;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/web")
public class DisplayHomeController {

    private static final String URL_REGEX =
            "^((((https?|ftps?|gopher|telnet|nntp)://)|(mailto:|news:))" +
            "(%[0-9A-Fa-f]{2}|[-()_.!~*';/?:@&=+$,A-Za-z0-9])+)" +
            "([).!';/?:,][[:blank:]])?$";
    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

    @RequestMapping(value = "/displayHomePage.action", method = RequestMethod.GET)
    public String displayHomePage(HttpServletRequest request, HttpServletResponse response) {

        return ViewConstants.VIEW_REDIRECTLEGACY;
    }

    @RequestMapping(value = "/homepageRedirectAction.action", method = RequestMethod.GET)
    public String homepageRedirect(HttpServletRequest request, HttpServletResponse response) {

        return ViewConstants.VIEW_REDIRECTLEGACY_HOMEREDIRECT;
    }

    @RequestMapping(value = "/pageHeader", method = RequestMethod.GET)
    public String homepageOrientHeader(HttpServletRequest request, HttpServletResponse response) {

        return ViewConstants.VIEW_HOMEPAGE_ORIENT_HEADER;
    }

    @RequestMapping(value = "/pageWrapper", method = RequestMethod.GET)
    public String homepageOrientHeader(HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "pageSrc", required = true) String pageSrc) throws IOException {

        Matcher matcher = URL_PATTERN.matcher(pageSrc);
        if (!matcher.matches()) {
            response.sendError(400, "Invalid page source provided");
            return ViewConstants.VIEW_ERROR;
        }

        request.getSession().setAttribute("pageSrc", pageSrc);
        return ViewConstants.VIEW_HOMEPAGE_PAGE_WRAPPER;
    }

}
