<%--wscExemptBegin--%>
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
<%--wscExemptEnd--%>

<%@ page import="java.lang.Boolean" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.util.Properties" %>
<%@ page import="com.ibm.ventura.internal.config.helper.api.VenturaConfigurationHelper" %>
<%@ page import="com.ibm.ventura.internal.config.api.VenturaConfigurationProvider" %>
<%@ page import="com.ibm.lconn.core.gatekeeper.LCGatekeeper" %>
<%@ page import="com.ibm.lconn.core.gatekeeper.LCSupportedFeature" %>
<%@ page import="com.ibm.connections.highway.common.api.HighwayConstants" %>
<%@ page import="com.ibm.connections.highway.common.api.HighwayUserSessionInfo" %>
<%@ page import="com.ibm.connections.highway.client.api.HighwaySetup" %>
<%@ page import="com.ibm.connections.highway.client.api.HighwayClient" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="dboard" uri="http://www.ibm.com/homepage/tags"%>

<%
boolean isCloud = false;
boolean isOrientEnabled = false;
boolean isOrientDefault = false;
boolean isOrientDefaultCloud = false;
boolean isRedirEnabled = false;
final String isOrientHomepageSetting = "homepage.isOrientHomepage";

VenturaConfigurationHelper venturaConfig = VenturaConfigurationHelper.Factory.getInstance();
VenturaConfigurationProvider confProvider = VenturaConfigurationProvider.Factory.getInstance();
Properties genericProperties = venturaConfig.getGenericProperites();
URL orientUrl = null;

try {
	if (venturaConfig.getForceConfidentialCommunications() || request.isSecure()) {
		orientUrl = venturaConfig.getComponentConfig("orient").getSecureServiceUrl();
	}
	if (orientUrl == null && !venturaConfig.getForceConfidentialCommunications()) {
		orientUrl = venturaConfig.getComponentConfig("orient").getServiceUrl();
	}
} catch(Exception e) {
	orientUrl = null;
}
try
{
	if (LCGatekeeper.isEnabled(LCSupportedFeature.HOMEPAGE_ENABLE_ORIENT_AS_DEFAULT_CLOUD, request)) {
		HighwayUserSessionInfo highwayUserSessionInfo = HighwaySetup.createUserInfoFromRequest(request);
		HighwayClient hc = HighwaySetup.getHighwayClient(HighwayConstants.HOMEPAGE);
		Object highwayIsOrientHomepage = hc.getSetting(highwayUserSessionInfo, isOrientHomepageSetting, Boolean.FALSE); 
		if (highwayIsOrientHomepage instanceof String) {
			isOrientDefaultCloud = Boolean.parseBoolean((String) highwayIsOrientHomepage);
		}
	}
} catch (Exception e)
{
	isOrientDefaultCloud = false;
}

if(genericProperties != null) {
	isCloud = "SmartCloud".equals(genericProperties.getProperty("DeploymentModel")) && "true".equals(genericProperties.getProperty("LotusLive"));
	isOrientEnabled = orientUrl != null && confProvider.isServiceEnabled("orient");
	isOrientDefault = (isCloud && isOrientDefaultCloud) || (!isCloud && "true".equals(genericProperties.getProperty("com.ibm.orient.isOrientHomepage")));
	isRedirEnabled = ((isCloud && LCGatekeeper.isEnabled(LCSupportedFeature.HOMEPAGE_SWITCHER_CLOUD, request)) || (!isCloud && "true".equals(genericProperties.getProperty("com.ibm.orient.isHomepageSwitcherEnabled")))) && isOrientEnabled;
}

String orientUrlString = orientUrl == null ? "" : orientUrl.toString();

pageContext.setAttribute("isRedirEnabled", isRedirEnabled);
pageContext.setAttribute("isOrientDefault", isOrientDefault);
pageContext.setAttribute("isOrientEnabled", isOrientEnabled);
%>

<jsp:include page="common/homepageAuth.jspf"/>

<dboard:homepagePageState />

<c:if test="${isPerson}">
	<c:choose>
		<c:when test="${isRedirEnabled && !sessionScope['user.info.isExternal']}">
			<script type="text/javascript">
				var isCloud = <%= isCloud %>;
				var isOrientDefault = <%= isOrientDefault %>;
				var orientUrl = "<%= orientUrlString %>";
				if (orientUrl && orientUrl.charAt(orientUrl.length - 1) != "/") {
					orientUrl += "/";
				}
				var shouldCheckUserPref = true;
				var defaultHomeLink = {};
				
				try {
					defaultHomeLink = JSON.parse(window.localStorage.getItem("defaultHomeLink"));
					var oneWeekAgo = new Date();
					shouldCheckUserPref = defaultHomeLink.timestamp < oneWeekAgo.setDate(oneWeekAgo.getDate() - 7);
				} catch (e) {
					// Exception with local storage or json parsing
					defaultHomeLink = {};
					shouldCheckUserPref = true;
					if (window.localStorage) {
						window.localStorage.removeItem("defaultHomeLink")
					}
				}
				
				if (shouldCheckUserPref) {
					function redirectToDefault() {
						// Redirect to the last known Connections Homepage state
						var redirectUrl = "${pageContext.request.contextPath}${ConnectionsHomepageState}";
						
						if (defaultHomeLink.value === "orient" || (!defaultHomeLink.value && isOrientDefault)) {
							redirectUrl = orientUrl;
						}
						
						window.location.replace(redirectUrl);
					}
					var tokenXhr = new XMLHttpRequest();
					tokenXhr.open("HEAD", orientUrl + "auth/token", true);
					tokenXhr.onload = function () {
						var token = tokenXhr.getResponseHeader('authorization');
						var readPostData = {
							query: "query {"
								+	"userprefs {"
								+		"applications {"
								+			"orient_me {"
								+				"defaultHomeLink"
								+			"}"
								+		"}"
								+	"}"
								+ "}",
							variables: {}
						};
						var userPrefXhr = new XMLHttpRequest();
						userPrefXhr.open("POST", orientUrl + "api/mwgraphql", true);
						userPrefXhr.setRequestHeader("Content-Type", "application/json");
						userPrefXhr.setRequestHeader("authorization", token);
						userPrefXhr.responseType = "json";
						userPrefXhr.timeout = 5000;
						userPrefXhr.onload = function () {
							var response = userPrefXhr.response;
							try {
								if (response.userprefs.applications.orient_me.defaultHomeLink === "orient") {
									window.location.replace(orientUrl);
								} else {
									// Redirect to the last known Connections Homepage state
									window.location.replace("${pageContext.request.contextPath}${ConnectionsHomepageState}");
								}
							} catch (e) {
								redirectToDefault();
							}
						};
						userPrefXhr.onerror = redirectToDefault;
						userPrefXhr.ontimeout = redirectToDefault;
						userPrefXhr.send(JSON.stringify(readPostData));
					};
					tokenXhr.onerror = redirectToDefault;
					tokenXhr.ontimeout = redirectToDefault;
					tokenXhr.timeout = 3000;
					tokenXhr.send();
				} else {
					if (defaultHomeLink.value === "orient") {
						window.location.replace(orientUrl);
					} else {
						// Redirect to the last known Connections Homepage state
						window.location.replace("${pageContext.request.contextPath}${ConnectionsHomepageState}");
					}
				}
			</script>
		</c:when>
		<c:when test="${isOrientDefault && !isRedirEnabled && isOrientEnabled}">
			<script>
				var orientUrl = "<%= orientUrlString %>";
				if (orientUrl && orientUrl.charAt(orientUrl.length - 1) != "/") {
					orientUrl += "/";
				}
				window.location.replace(orientUrl);
			</script>
		</c:when>
		<c:otherwise>
			<script>
				// Redirect to the last known Connections Homepage state
				window.location.replace("${pageContext.request.contextPath}${ConnectionsHomepageState}");
			</script>
		</c:otherwise>
	</c:choose>
</c:if>
