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
	xmlns:atom="http://www.w3.org/2005/Atom"
	xmlns:app="http://purl.org/atom/app#"
	xmlns:snx="http://www.ibm.com/xmlns/prod/sn"
	xmlns:os="http://a9.com/-/spec/opensearch/1.1/"
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:thr="http://purl.org/syndication/thread/1.0"
	exclude-result-prefixes="atom app snx os xhtml thr">

	<xsl:output method="html" encoding="utf-8" />
	<xsl:param name="copy" />
	<xsl:param name="edit" />
	<xsl:param name="userid" />
	<xsl:param name="tags" />
	<xsl:param name="details" />
	<xsl:param name="dogearTitle" />
	
	<xsl:template name="dogearFeed" match="/atom:feed">
	
		<xsl:if test="atom:entry!=''" >
		
			<xsl:for-each select="atom:entry">
			
				<div class="entry">		
					<xsl:variable name="this-id" select="generate-id(.)"/>
					
					<div class="icons">
						<img height="16" width="16" alt="">							
							<xsl:attribute name="src">
								<xsl:value-of select="substring-before(/atom:feed/atom:link[@rel='self']/@href,'atom')"/>favicon?host=<xsl:value-of select="substring-after(atom:link/@href,'://')" />
							</xsl:attribute>
						</img>
					</div>
					
					<div class="entryBodySmall">
						<h4>
							<a target="_blank" class="bidiAware">
								<xsl:for-each select="atom:link[not(@rel)]">
									<xsl:attribute name="href"><xsl:value-of select="@href" /></xsl:attribute>
								</xsl:for-each>
								<xsl:attribute name="title"><xsl:value-of select="$dogearTitle"/></xsl:attribute>
			 					<xsl:attribute name="aria-describedby">
									<xsl:value-of select="concat($this-id,'_datestamp')"/>
								</xsl:attribute>
								<xsl:choose>
									<xsl:when test="substring-before(substring(atom:title,1,40),' ')=''">
										<xsl:value-of select="substring(atom:title,1,40)"/>
									</xsl:when>
									<xsl:otherwise>
										<xsl:value-of select="atom:title"/>
									</xsl:otherwise>
								</xsl:choose>
							</a>
						</h4>
						<span role="list">										
							<xsl:for-each select="atom:author">
								<span class="lotusLeft" role="listitem">
									<span class="vcard">
										<span class="x-lconn-username lotusHidden">
											<xsl:value-of select="atom:name"/>
										</span>	
										<span class="x-lconn-userid lotusHidden">
											<xsl:value-of select="snx:userid" />
										</span>
									</span>
								</span>
							</xsl:for-each>
							<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
							<span class="dogAtomDate meta lotusLeft" role="presentation">
								<xsl:attribute name="id">
									<xsl:value-of select="concat($this-id,'_datestamp')"/>
								</xsl:attribute>		
								<xsl:value-of select="atom:published" />
							</span>
							<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
							<a href="javascript:void(0);" class="dogDetails" role="button">
								<xsl:attribute name="title">
									<xsl:value-of select="$details"/>
								</xsl:attribute>
								<xsl:attribute name="location">
									<xsl:value-of select="atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/same']/@href"/>
								</xsl:attribute>
								<xsl:value-of select="$details"/>
							</a>
						</span>	
					</div>		
				</div>			
			</xsl:for-each>
		</xsl:if>
	</xsl:template>

	<xsl:template name="entry" match="/atom:entry"></xsl:template>

</xsl:stylesheet>
