<?xml version="1.0" encoding="utf-8"?>
<!-- ***************************************************************** -->
<!--                                                                   -->
<!-- IBM Confidential                                                  -->
<!--                                                                   -->
<!-- OCO Source Materials                                              -->
<!--                                                                   -->
<!-- Copyright IBM Corp. 2007, 2015                                    -->
<!--                                                                   -->
<!-- The source code for this program is not published or otherwise    -->
<!-- divested of its trade secrets, irrespective of what has been      -->
<!-- deposited with the U.S. Copyright Office.                         -->
<!--                                                                   -->
<!-- ***************************************************************** -->


<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:atom="http://www.w3.org/2005/Atom"	
	xmlns:app="http://purl.org/atom/app#"
	xmlns:snx="http://www.ibm.com/xmlns/prod/sn"
	xmlns:os="http://a9.com/-/spec/opensearch/1.1/"
	exclude-result-prefixes="atom app snx os">

	<xsl:output method="html" encoding="utf-8" />
	<xsl:param name="nofeeds" />
	<xsl:param name="nodescription1" />
	<xsl:param name="nodescription2" />
	<xsl:param name="tags" />
	<xsl:param name="blankGif" />
	<xsl:param name="ariaTag" />
	<xsl:param name="ariaTagDescription" />
	<xsl:param name="commFeedsTitle" />

	<xsl:template match="/atom:feed">
		<xsl:if test="os:totalResults=0">
			<xsl:variable name="nocontent-id" select="generate-id(.)"/>
		
			<div style="border-bottom: solid 1px #ddd;">
				<span style="font-size:1em;font-weight:bold;">
					<xsl:value-of select="$nofeeds" />
				</span>
				<br />					
				<div style="margin-bottom:3px;padding-left:3px;" role="presentation">
					<xsl:attribute name="id">
						<xsl:value-of select="concat($nocontent-id,'_nocontent')"/>
					</xsl:attribute>
					<xsl:value-of select="$nodescription1" /><br />
					<xsl:value-of select="$nodescription2" />
					<a  target="_blank">
						<xsl:attribute name="href">
							<xsl:value-of select="atom:link[@rel='alternate']/@href" />
						</xsl:attribute>
						<xsl:attribute name="aria-describedby">
							<xsl:value-of select="concat($nocontent-id,'_nocontent')"/>
						</xsl:attribute>
						<xsl:value-of select="atom:title" />
					</a>
				</div>
			</div>
		</xsl:if>

		<label id="ariaTagDescription" class="lotusAccess" role="presentation">
			<xsl:value-of select="$ariaTagDescription"/>
		</label>
		
		
		<xsl:for-each select="atom:entry">
			<div class="entry">
				<xsl:variable name="this-id" select="generate-id(.)"/>
					
				<div class="entryBody">
					<h4 style="margin-bottom:3px;">
						<span class="sprite">
							<img class="lconnSprite lconnSprite-iconFeed16" src="{$blankGif}">
								<xsl:attribute name="title"><xsl:value-of select="$commFeedsTitle" /></xsl:attribute>
								<xsl:attribute name="alt"><xsl:value-of select="$commFeedsTitle" /></xsl:attribute>
							</img>
							
							<span class="lotusAltText"><xsl:value-of select="atom:title" /></span>
						</span>&#160;
					<a class="widgetFeedLink" target="_blank">
						<xsl:attribute name="href">
							<xsl:value-of select="atom:link/@href" />
						</xsl:attribute>
						<xsl:attribute name="aria-describedby">
							<xsl:value-of select="concat($this-id,'_timestamp',' ',concat($this-id,'_summary'))"/>
						</xsl:attribute>
						
						<span class="bidiAware"><xsl:value-of select="atom:title" /></span>
					</a>
					</h4>
					<span role="list">
						<span class="lotusLeft" role="listitem">
							<span class="vcard">
								<span class="x-lconn-username lotusHidden">
									<xsl:value-of select="atom:contributor/atom:name"/>
								</span>										
								<span class="x-lconn-userid lotusHidden">
									<xsl:value-of select="atom:contributor/snx:userid" />
								</span>
							</span> 
						</span>
						<span class="lotusLeft divider" role="img" aria-hidden="true">|</span>
						<span class="dateSpan lotusLeft meta" role="presentation">
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_timestamp')"/>
							</xsl:attribute>
						
							<xsl:value-of select="atom:updated" />
						</span>
						<span class="lotusLeft divider" role="img" aria-hidden="true">|</span>
						<span role="listitem">
							<span class="lotusTags">
								<xsl:value-of select="$tags" />
								<span role="list">
								<xsl:for-each select="atom:category">
									<xsl:choose>
										<xsl:when test="@scheme='http://www.ibm.com/xmlns/prod/sn/flags'">
										</xsl:when>
										<xsl:when test="@scheme='http://www.ibm.com/xmlns/prod/sn/type'">
										</xsl:when>
										<xsl:otherwise>
											<span role="listitem">					
												<a class="filterTag bidiAware" target="_blank">
													<xsl:attribute name="href">
														<xsl:value-of select="/atom:feed/atom:link[@rel='alternate']/@href"/>&amp;tag=<xsl:call-template name="recClean">
															<xsl:with-param name="val" select="@term"/>
														</xsl:call-template>
													</xsl:attribute>
													<xsl:attribute name="title">
														<xsl:value-of select="@term"/>
													</xsl:attribute>
													<xsl:attribute name="aria-describedby">
														<xsl:text>ariaTagDescription</xsl:text>
													</xsl:attribute>
													<xsl:attribute name="aria-label">
														<xsl:value-of select="translate($ariaTag, '${0}', @term)"/>
													</xsl:attribute>
													
													<xsl:value-of select="@term"/>
												</a><xsl:if test="position()!=last()">,&#160;</xsl:if>
											</span>
										</xsl:otherwise>
									</xsl:choose>
								</xsl:for-each>
								</span>&#160;
							</span>
						</span>
					</span><br />
					<span class="bidiAware">
						<xsl:attribute name="id">
							<xsl:value-of select="concat($this-id,'_summary')"/>
						</xsl:attribute>
						<xsl:value-of select="atom:summary" />
					</span>
				</div>
			</div>
		</xsl:for-each>
	</xsl:template>
	<xsl:template name="recClean">
		<xsl:param name="val" />
		<xsl:choose>
			<xsl:when test="$val='+'">%2b</xsl:when>
			<xsl:when test="starts-with($val,'+')">
				<xsl:call-template name="recClean">
							<xsl:with-param name="val" select="concat('%2b',substring-after($val,'+'))" />
				</xsl:call-template>
			</xsl:when>
			<xsl:otherwise>
				<xsl:choose>
					<xsl:when test="substring-before($val,'+')=''"><xsl:value-of select="$val"/></xsl:when>
					<xsl:otherwise>
						<xsl:call-template name="recClean">
							<xsl:with-param name="val" select="concat(substring-before($val,'+'),'%2b',substring-after($val,'+'))" />
						</xsl:call-template>
					</xsl:otherwise>
				</xsl:choose>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
</xsl:stylesheet>
