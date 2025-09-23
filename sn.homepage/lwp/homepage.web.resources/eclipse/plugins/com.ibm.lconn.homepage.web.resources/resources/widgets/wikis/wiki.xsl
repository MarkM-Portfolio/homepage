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
	xmlns:r="http://purl.org/atompub/rank/1.0"
	xmlns:app="http://purl.org/atom/app#"
	xmlns:snx="http://www.ibm.com/xmlns/prod/sn"
	xmlns:os="http://a9.com/-/spec/opensearch/1.1/"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:thr="http://purl.org/syndication/thread/1.0"
	xmlns:td="urn:ibm.com/td"
	xmlns:opensearch="http://a9.com/-/spec/opensearch/1.1/"
	exclude-result-prefixes="a app snx os xhtml thr opensearch r td">
	
	<xsl:output method="html" encoding="utf-8" />	
	
	<xsl:param name="tags" />
	<xsl:param name="noRecommendAlt" />
	<xsl:param name="recommendAlt" />
	<xsl:param name="fullRecommendAlt" />
	<xsl:param name="noCommentsAlt" />
	<xsl:param name="commentsAlt" />
	<xsl:param name="wikiMode" />
	
	<xsl:template match="/a:feed">
			<xsl:apply-templates select="opensearch:totalResults" />
			<xsl:apply-templates select="a:entry" />	
	</xsl:template>

	
	<xsl:template match="a:link"><xsl:value-of select="@href"/></xsl:template>
	<xsl:template match="opensearch:totalResults"><span class="meta totalEntries" style="display : none"><xsl:value-of select="."></xsl:value-of></span></xsl:template>
	<xsl:template match="a:entry">
		<div class="entry">
			<div class="wikiEntryBody">
				<!-- 
				<div>
					<h4 style="margin-bottom:5px;" dojoType="lconn.homepage.html.WidgetLink">
						<xsl:attribute name="linkUrl"><xsl:value-of select="substring-before(a:link[./@rel='alternate']/@href,'/page')" /></xsl:attribute>
						<xsl:attribute name="linkTitle"><xsl:value-of select="a:title"/></xsl:attribute>
						<xsl:attribute name="linkLabel">
							<xsl:value-of select="a:title"/>
						</xsl:attribute>
					</h4>
				</div>
				-->
				
				<h4 style="margin-bottom:5px;">
					<a target="_blank" class="bidiAware">
						<xsl:attribute name="href">
							<xsl:apply-templates select="a:link[./@rel='alternate']" />
		  				</xsl:attribute>
		  				<xsl:attribute name="title">
		  					<xsl:value-of select="a:title"/>
		  				</xsl:attribute>			
						<xsl:choose>
							<xsl:when test="substring-before(substring(a:title,1,40),' ')=''">
								<xsl:value-of select="substring(a:title,1,40)"/>
							</xsl:when>
							<xsl:otherwise>
								<xsl:value-of select="a:title"/>
							</xsl:otherwise>
						</xsl:choose>
					</a>
				</h4>
				<span role="list">
					<span class="lotusLeft" role="listitem">
						<span class="vcard">  						
							<span class="x-lconn-username lotusHidden">
								<xsl:value-of select="td:modifier/a:name"/>
							</span>	  						
  							<span class="x-lconn-userid lotusHidden">
  								<xsl:value-of select="td:modifier/snx:userid"/>
  							</span>
						</span>
					</span>
					<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
					<span class="meta published lotusLeft" role="listitem">
						<xsl:choose>
							<xsl:when test="$wikiMode='MyWikis'">
								<xsl:value-of select="a:updated"/>
							</xsl:when>
							<xsl:when test="$wikiMode='PopularWikis'">
								<xsl:value-of select="a:updated"/>
							</xsl:when>
							<xsl:when test="$wikiMode='LatestWikiEntries'">
								<xsl:value-of select="a:updated"/>
							</xsl:when>													
							<xsl:otherwise>
								 <xsl:value-of select="a:published"/> 					
							</xsl:otherwise>
					   </xsl:choose>
					</span>
					<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
					<span role="listitem">
						<span class="lotusTags"><xsl:value-of select="$tags"/>&#160;
							<span role="list">
							<xsl:for-each select="a:category">
								<xsl:choose>
									<xsl:when test="@scheme='tag:ibm.com,2006:td/type'">																	
									</xsl:when>
									<xsl:when test="@term=''">
									</xsl:when>
									<xsl:otherwise>
										<a target="_blank" class="tag bidiAware" role="listitem">
											<xsl:attribute name="href">
												<xsl:choose>
													<xsl:when test="$wikiMode='MyWikis'"><xsl:value-of select="substring-before(../a:link[@rel='alternate']/@href,'wikis/home')" />wikis/home/mywikis?</xsl:when>
													<xsl:when test="$wikiMode='PopularWikis'"><xsl:value-of select="substring-before(../a:link[@rel='alternate']/@href,'wikis/home')" />wikis/home?</xsl:when>
													<xsl:when test="$wikiMode='LatestWikiEntries'"><xsl:value-of select="substring-before(../a:link[@rel='alternate']/@href,'wikis/home')" />wikis/home?</xsl:when>													
													<xsl:otherwise>
													</xsl:otherwise>
												</xsl:choose>
											</xsl:attribute>
											<xsl:attribute name="title">
												<xsl:value-of select="@term"/>
											</xsl:attribute>
											<xsl:value-of select="@term"/>
										</a>
										<xsl:choose>
											<xsl:when test="position()=last()">&#x0020;&#160;</xsl:when>
											<xsl:when test="position()!=1">,&#x0020;&#160;</xsl:when>
										</xsl:choose>
									</xsl:otherwise>
								</xsl:choose>	
							</xsl:for-each>
							</span><br />
						</span>
					</span>
				</span>					
			</div> <!-- close div class entryBody -->
		</div> <!-- close div class entry -->
	</xsl:template>
	
</xsl:stylesheet>
