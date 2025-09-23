<?xml version="1.0" encoding="utf-8"?>

<!-- ***************************************************************** -->
<!--                                                                   -->
<!-- IBM Confidential                                                  -->
<!--                                                                   -->
<!-- OCO Source Materials                                              -->
<!--                                                                   -->
<!-- Copyright IBM Corp. 2001, 2015                                    -->
<!--                                                                   -->
<!-- The source code for this program is not published or otherwise    -->
<!-- divested of its trade secrets, irrespective of what has been      -->
<!-- deposited with the U.S. Copyright Office.                         -->
<!--                                                                   -->
<!-- ***************************************************************** -->

<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:a="http://www.w3.org/2005/Atom"
	xmlns:app="http://purl.org/atom/app#"
	xmlns:snx="http://www.ibm.com/xmlns/prod/sn"
	xmlns:os="http://a9.com/-/spec/opensearch/1.1/"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:thr="http://purl.org/syndication/thread/1.0"
	exclude-result-prefixes="a app snx os xhtml thr">
	
	<xsl:output method="html" encoding="utf-8" />
	
	<xsl:param name="choice" />
	<xsl:param name="node" />
	<xsl:param name="edit" />
	<xsl:param name="profile" />
	<xsl:param name="email" />
	<xsl:param name="telOffice" />
	<xsl:param name="imageProfilesAlt" />
		
	<xsl:template match="/a:feed">
		<xsl:choose>
			<!-- single user profile -->
			<xsl:when test="$choice = 'userProfile'">
				<xsl:call-template name="getMyProfileNode">
					<xsl:with-param name="edit" select="$edit"/>
					<xsl:with-param name="profile" select="$profile"/>
					<xsl:with-param name="email" select="$email"/>
					<xsl:with-param name="telOffice" select="$telOffice"/>
					<xsl:with-param name="imageProfilesAlt" select="$imageProfilesAlt" />
				</xsl:call-template>
			</xsl:when>
				<!-- search Result -->
			<xsl:when test="$choice = 'searchResult'">
					<xsl:call-template name="getSearchResult" />
			</xsl:when>
			<!-- page info -->
			<xsl:when test="$choice = 'totalEntries'">
				<xsl:call-template name="getTotalEntries" />
			</xsl:when>
			<xsl:when test="$choice = 'currentIndex'">
				<xsl:call-template name="getCurrentIndex" />
			</xsl:when>
			<xsl:when test="$choice = 'contextRoot'">
				<xsl:call-template name="getContextRoot" />
			</xsl:when>
			<xsl:when test="$choice='pending'">
				<xsl:value-of select="/a:feed/snx:pendingInvitations"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="getMyProfileNode">
			<div class="entry">
				<div class="icons">
					<img style="width:75px;height:75px;" alt="">
						<xsl:variable name="imageSrc">
							<xsl:value-of select="a:entry/a:content/xhtml:div/xhtml:span/xhtml:div/xhtml:img/@src"/>
						</xsl:variable>
					
						<xsl:attribute name="src">
							<xsl:choose>
								<xsl:when test="$imageSrc = ''"></xsl:when>
								<xsl:otherwise>
									<xsl:value-of select="$imageSrc"/>
								</xsl:otherwise>
							</xsl:choose>
						</xsl:attribute>
					</img>
				</div>
				<div class="profilesEntryBodyLarge entryBodyAuto">
				<xsl:for-each select="/a:feed/a:entry/a:content/xhtml:div/xhtml:span">
					<!-- name -->
					<div role="list">	
						<!-- vcard -->
						<span class="lotusLeft" role="listitem">
							<span class="vcard">								
								<span class="x-lconn-username lotusHidden">
									<xsl:value-of select="xhtml:div/xhtml:a[@class='fn url']"/>
								</span>								
								<span class="x-lconn-userid lotusHidden">
									<xsl:value-of select="xhtml:div[@class='x-lconn-userid']" />
								</span>
							</span>
						</span>
						<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>					
						<a target="_blank" class="lotusLeft" role="link">
							<xsl:attribute name="href">
								<xsl:value-of select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" />html/myProfileView.do</xsl:attribute>
							<xsl:attribute name="class">lotusAction</xsl:attribute>
							<xsl:value-of select="$profile"/>
						</a>
						<span class="divider" role="img" aria-hidden="true">|</span>					
						<a target="_blank" role="link">
							<xsl:attribute name="href">
								<xsl:value-of select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" />html/editMyProfileView.do</xsl:attribute>
							<xsl:attribute name="class">lotusAction</xsl:attribute>
							<xsl:value-of select="$edit"/>
						</a>
					</div>
					
					<!-- job title -->
					<div><xsl:value-of select="xhtml:div[@class='title']" /></div>
					<!-- location -->
					<div><xsl:value-of select="xhtml:div[@class='adr work postal']/xhtml:span[@class='locality']" />&#160;
					<xsl:value-of select="xhtml:div[@class='adr work postal']/xhtml:div[@class='country-name']" /></div>
					<!-- tel -->
					<div><xsl:value-of select="$telOffice"/><xsl:value-of select="xhtml:div[@class='tel']/xhtml:span[@class='value']" /></div>
					<!-- job email -->
					<xsl:choose>
						<xsl:when test="xhtml:div/xhtml:a[@class='email']">
							<div><xsl:value-of select="$email"/><a>
							<xsl:attribute name="href">mailto:<xsl:value-of select="xhtml:div/xhtml:a[@class='email']" /></xsl:attribute>
							<xsl:value-of select="xhtml:div/xhtml:a[@class='email']" />
							</a></div>
						</xsl:when>
					</xsl:choose>
				</xsl:for-each>
				</div>
			</div>
	</xsl:template>
	
	<xsl:template name="getContextRoot">
		<xsl:value-of select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" />
	</xsl:template>
	
	<xsl:template name="getSearchResult">
			<xsl:for-each select="/a:feed/a:entry">
				<div class="entry">
					<div class="entryBodySmall">
						<!-- vcard -->
						<h4>
						<span class="vcard">
							<a class="fn lotusPerson bidiAware" target="_blank">
							<xsl:attribute name="href">
								<xsl:call-template name="getContextRoot" />html/profileView.do?uid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-profile-uid']"/>
							</xsl:attribute>
								<xsl:value-of select="a:content/xhtml:div/xhtml:span[@class='vcard']/xhtml:div/xhtml:a[@class='fn url']" />
							</a>
							<span class="x-lconn-userid" style="display:none;">
								<xsl:value-of select="a:content/xhtml:div/xhtml:span[@class='vcard']/xhtml:div[@class='x-lconn-userid']" />
							</span>
						</span>
						</h4>
						<xsl:choose>
							<xsl:when test="a:content/xhtml:div/xhtml:span/xhtml:div[@class='title']=''">

							</xsl:when>
							<xsl:otherwise>
								<span class="lotusLeft">
									<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='title']" />
								</span>
								<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
							</xsl:otherwise>
						</xsl:choose>
						<xsl:choose>
							<xsl:when test="a:content/xhtml:div/xhtml:span/xhtml:div[@class='adr work postal']/xhtml:div[@class='country-name']=''">

							</xsl:when>
							<xsl:otherwise>
								<span class="meta">
									<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='adr work postal']/xhtml:div[@class='country-name']" />
								</span>
							</xsl:otherwise>
						</xsl:choose>
					</div>
				</div>
			</xsl:for-each>
	</xsl:template>	
	
	<xsl:template name="getTotalEntries">
		<xsl:value-of select="/a:feed/os:totalResults" />
	</xsl:template>
	
	<xsl:template name="getCurrentIndex">
		<xsl:value-of select="/a:feed/os:startIndex" />
	</xsl:template>
	
</xsl:stylesheet>
