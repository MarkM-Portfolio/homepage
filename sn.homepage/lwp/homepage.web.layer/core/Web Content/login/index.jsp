<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/2000/REC-xhtml1-20000126/DTD/xhtml1-transitional.dtd">
<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2008, 2015                                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>

<%--wscExemptBegin--%>
<%--wscExemptEnd--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper" %>
<%@ page import="com.ibm.ventura.internal.config.api.VenturaConfigurationProvider;" %>

<%
	request.setAttribute("loginPostUri", request.getContextPath() + "/j_security_check");
	request.setAttribute("loginError", Boolean.valueOf(request.getParameter("error")));
	request.setAttribute("loginUri", request.getContextPath()+"/login");
%>

<jsp:forward page="/nav/templates/login.jsp" />
