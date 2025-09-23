<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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

<%@ page session="false" contentType="text/html; charset=UTF-8"
	buffer="128kb"%>

<%@page import="java.io.PrintWriter"%>
<%@page import="java.io.StringWriter"%>
<%@page isErrorPage="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bidi" uri="http://www.ibm.com/lconn/tags/bidiutil"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="lc-cache" uri="http://www.ibm.com/connections/core/cache"%>

<html xmlns="http://www.w3.org/1999/xhtml"
	lang="<%=request.getLocale().getLanguage()%>"
	xml:lang="<%=request.getLocale().getLanguage()%>">
<head>
<jsp:include page='defaultCSS.jspf' flush="false" />

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="private,max-age=0" />

<title><fmt:message key="jsp.errorpage.title" /></title>

<jsp:include page="/web/jsp/common/initGlobals.jspf" flush="false" />

</head>
<body class="lotusError lotusSpritesOn">
	<jsp:useBean id="now" class="java.util.Date" />

	<div class="lotusErrorBox">

	<div class="lotusErrorContent">
		
		<div class="lotusErrorForm">
			<img src="<lc-cache:uri template="{webResourcesRoot}/web/com.ibm.oneui.styles/css/images/iconErrorLarge.gif" />" 
				 alt="<fmt:message key="jsp.errorpage.icon.alt"/> "/>
			<h1><fmt:message key="jsp.errorpage.message" /></h1>
			<p><fmt:message key="jsp.errorpage.error" /></p>
			<form method="GET" action="${pageContext.request.contextPath}">
				<div class="lotusBtnContainer">
					<input type="submit" class="lotusBtn lotusBtnSpecial lotusLeft"	value="<fmt:message key="jsp.errorpage.back" />" />
					<span>
						<a class="lotusAction" href="#" onclick="javascript:toggleErrorMsg(this);">
						<fmt:message key="jsp.errorpage.details" /></a>
					</span>
					<span id="showErrorMsg" style="display:none"><fmt:message key="jsp.errorpage.details" /></span>
					<span id="hideErrorMsg" style="display:none"><fmt:message key="jsp.errorpage.hide.details" /></span>			
				</div>
			</form>
		</div>
		
		<%-- stackTrace --%>
		<div id="stackTrace" style="display: none">
			<ul>
				<li><fmt:message key="error.notauthorized.exception"/></li>
			</ul>
		</div>
		<%-- end stackTrace --%>
	</div>
	</div>
</body>
</html>
