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
	xmlns:a="http://www.w3.org/2005/Atom"	
	xmlns:app="http://purl.org/atom/app#"
	xmlns:snx="http://www.ibm.com/xmlns/prod/sn"
	xmlns:os="http://a9.com/-/spec/opensearch/1.1/"
	exclude-result-prefixes="a app snx os">

	<xsl:output method="html" encoding="utf-8" />
	<xsl:param name="nofeeds" />
	<xsl:param name="nodescription1" />
	<xsl:param name="nodescription2" />
	<xsl:param name="tags" />
	<xsl:param name="blankGif" />
	<xsl:param name="commForumsTitle" />

	<xsl:template match="/a:feed">
		<xsl:if test="os:totalResults=0">
			<xsl:variable name="noitems-id" select="generate-id(.)"/>
		
			<div style="border-bottom: solid 1px #ddd;">
				<span style="font-size:1em;font-weight:bold;" tabindex="0">
					<xsl:attribute name="aria-describedby">
						<xsl:value-of select="concat($noitems-id,'_description')"/>
					</xsl:attribute>
				
					<xsl:value-of select="$nofeeds" />
				</span>
				<br />					
				<div style="margin-bottom:3px;padding-left:3px;">
					<xsl:attribute name="id">
						<xsl:value-of select="concat($noitems-id,'_description')"/>
					</xsl:attribute>
					<xsl:value-of select="$nodescription1" /><br />
				</div>
			</div>
		</xsl:if>
		<xsl:for-each select="a:entry">
			
			<div class="entry">
				<xsl:variable name="this-id" select="generate-id(.)"/>
			
				<div class="entryBody" style="margin-left: 5px;">
					
					<xsl:element name="h4">
						 <xsl:attribute name="style">margin: 5px 5px 5px 5px; overflow: hidden; white-space: nowrap</xsl:attribute>
						 <xsl:attribute name="title"><xsl:value-of select="a:title" /></xsl:attribute>
						 <span class="sprite">
							<img class="lconnSprite lconnSprite-iconComment16" src="{$blankGif}">
								<xsl:attribute name="title"><xsl:value-of select="$commForumsTitle" /></xsl:attribute>
								<xsl:attribute name="alt"><xsl:value-of select="$commForumsTitle" /></xsl:attribute>
							</img>
							
							<span class="lotusAltText"><xsl:value-of select="a:title" /></span>
						</span>&#160;
						 <a>
							 <xsl:attribute name="href"><xsl:value-of select="a:link[@type = 'text/html']/@href" /></xsl:attribute>
							 <xsl:attribute name="target">_blank</xsl:attribute>
							 <xsl:attribute name="class">bidiAware</xsl:attribute>
		 					 <xsl:attribute name="aria-describedby">
							 	<xsl:value-of select="concat($this-id,'_datestamp',' ',$this-id,'_content')"/>
							 </xsl:attribute>
	
							 <xsl:value-of select="a:title" />
						 </a>
					</xsl:element>
					<span role="list">
						<span class="vcard" role="listitem">
							<span class="x-lconn-username lotusHidden">
								<xsl:value-of select="a:author/a:name"/>
							</span>										
							<span class="x-lconn-userid lotusHidden">
								<xsl:value-of select="a:author/snx:userid" />
							</span>
						</span>						

						<span class="divider" role="img" aria-hidden="true">|</span>
						<span class="dateSpan" role="listitem">
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_datestamp')"/>
							</xsl:attribute>		
							<xsl:attribute name="role">
								<xsl:text>presentation</xsl:text>
							</xsl:attribute>
						
							<xsl:value-of select="a:updated" />
						</span>
					</span>
					<br />	
					<span class="bidiAware">
						<xsl:attribute name="id">
							<xsl:value-of select="concat($this-id,'_content')"/>
						</xsl:attribute>
						<xsl:attribute name="role">
							<xsl:text>presentation</xsl:text>
						</xsl:attribute>
						<xsl:choose>
							<xsl:when test="a:content">
								<xsl:choose>
									<xsl:when test="a:content != ''">
										<xsl:value-of
											select="a:content" />
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="a:title" />
									</xsl:otherwise>
								</xsl:choose>
							</xsl:when>
							<xsl:otherwise>
								<xsl:choose>
									<xsl:when test="a:summary != ''">
										<xsl:value-of
											select="a:summary" />
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="a:title" />
									</xsl:otherwise>
								</xsl:choose>
							</xsl:otherwise>
						</xsl:choose>
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
