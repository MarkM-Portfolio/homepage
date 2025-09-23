<%--wscExemptBegin--%>
<%-- ***************************************************************** --%>
<%--                                                                   --%>
<%-- IBM Confidential                                                  --%>
<%--                                                                   --%>
<%-- OCO Source Materials                                              --%>
<%--                                                                   --%>
<%-- Copyright IBM Corp. 2011, 2015                                    --%>
<%--                                                                   --%>
<%-- The source code for this program is not published or otherwise    --%>
<%-- divested of its trade secrets, irrespective of what has been      --%>
<%-- deposited with the U.S. Copyright Office.                         --%>
<%--                                                                   --%>
<%-- ***************************************************************** --%>
<%--wscExemptEnd--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="common/homepageAuth.jspf"/>

<c:if test="${isPerson}">
	<script>
		function getHashValue() {
	        var hashValue = location.hash;
	        if ( hashValue.length > 0 ) {
	            if ( hashValue.charAt(0) == "#" ) {
	                hashValue = hashValue.substring(1);

	                // If the hashValue actually has some content
	                if(hashValue.length > 0){
	                	return hashValue;
	                }
	            }
	        }
		}
	</script>
	
	<%-- This page exists purely to support old urls that may be saved / bookmarked.
	     The URLs that cause this page to be loaded are not generated any more.
	     The old URLs are:
	        - /homepage/web/gettingStartedPage.action
	        - /homepage/web/displayHomePage.action
	        - /homepage/web/homepageRedirectAction.action
	     In the case of displayHomePage & homepageRedirectAction there may be
	     URL fragments that we need to include in re-direct (via client side redirect) --%>
	
    <% request.setAttribute("jumpTo", request.getQueryString()); %>
    <c:choose>
        <c:when test="${jumpTo=='gettingStarted'}">
            <meta HTTP-EQUIV="REFRESH" content="0; url=${pageContext.request.contextPath}/web/gettingStarted/"/>
        </c:when>
        <c:when test="${jumpTo=='updates'}">
            <script>
                var hashValue = getHashValue();
                var newUrl = "${pageContext.request.contextPath}" + "/web/updates/";

                if ( hashValue ) {
                    newUrl += "#" + hashValue;
                }

                window.location.replace(newUrl);
            </script>
        </c:when>
        <c:when test="${jumpTo=='homepageRedirectAction'}">
            <%
                request.setAttribute("isGettingStartedDisplayEnabled",
                        com.ibm.lconn.homepage.services.helper.GettingStartedPreferenceHelper.isGettingStartedDisplayEnabled(
                                (String)session.getAttribute("user.info.internal.id"),
                                request.getRemoteUser()));
	    		request.setAttribute("isDefaultWidgets",
	    				com.ibm.lconn.homepage.services.helper.DefaultHomepageTabHelper.isWidgetsDefault());
            %>

            <script type="text/javascript">
                var newUrl;

                var hashValue = getHashValue();
                if ( hashValue ) {  // Redirect to the 'Updates' page
                    newUrl = "${pageContext.request.contextPath}" + "/web/updates/#" + hashValue;
                } else {
                    var gettingStartedEnabled = '${isGettingStartedDisplayEnabled}';
                    var widgetsDefault = '${isWidgetsDefault}';
                    
                    if ( gettingStartedEnabled.toLowerCase() === 'true' ) {
                        newUrl = "${pageContext.request.contextPath}" + "/web/gettingStarted/";
                    } else if ( widgetsDefault.toLowerCase() === 'true' ) {
                    	newUrl = "${pageContext.request.contextPath}" + "/web/widgets/";
                    } else {
                        newUrl = "${pageContext.request.contextPath}" + "/web/updates/";
                    }
                }

                if ( newUrl ) {
                    window.location.replace(newUrl);
                }
            </script>
        </c:when>
    </c:choose>
</c:if>
