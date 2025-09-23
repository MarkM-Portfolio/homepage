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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="bidi" uri="http://www.ibm.com/lconn/tags/bidiutil"%>

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

<jsp:include page="/web/jsp/common/error.jspf" flush="false" />

</body>
</html>
