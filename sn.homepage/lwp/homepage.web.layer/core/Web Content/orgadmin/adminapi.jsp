<%@page import="java.net.URL"%>
<%@page import="com.ibm.lconn.homepage.orgadmin.CloudPathUtils"%>
<%@page import="com.ibm.lconn.homepage.orgadmin.HighwayAdminRequest"%>
<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2018                                          --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>

<%
	boolean isOrgAdmin = request.isUserInRole("org-admin");

    boolean isCloud = CloudPathUtils.isS2SRequest(request, response);
	
	if (isCloud || isOrgAdmin) {

        HighwayAdminRequest highwayRequest = new HighwayAdminRequest(request, response);

        if (request.getMethod().equalsIgnoreCase("POST"))

            highwayRequest.updateC2Setting();

    } else {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    }
%>

