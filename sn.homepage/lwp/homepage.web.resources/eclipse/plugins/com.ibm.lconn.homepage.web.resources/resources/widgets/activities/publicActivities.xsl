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

	<xsl:param name="updated" />
	<xsl:param name="by" />

	<xsl:template match="/">

		<xsl:for-each select="/a:feed/a:entry">
			<div class="entry">
			
				<xsl:variable name="this-id" select="generate-id(.)"/>
				
				<div class="icons" style="height: 20px;">
					<img alt="" title="" border="0" style="margin-right:5px;">
						<xsl:attribute name="src">
							<xsl:value-of select="snx:icon" />
						</xsl:attribute>
					</img>
				</div>
				<div class="entryBody" style="margin-left: 25px;">
					<h4>
						<a href="#" target="_blank" class="bidiAware">
							<xsl:attribute name="href">
								<xsl:value-of
									select="a:link[@type='application/xhtml+xml']/@href" />
							</xsl:attribute>
		 					<xsl:attribute name="aria-describedby">
								<xsl:value-of select="concat($this-id,'_datestamp',' ',$this-id,'_content',' ',$this-id,'_summary')"/>
							</xsl:attribute>
							<xsl:value-of select="a:title" />
						</a>
					</h4>
					<span role="list">
						<span class="vcard" role="listitem">
							<span class="x-lconn-username lotusHidden">
								<xsl:value-of select="a:contributor/a:name"/>
							</span>
							<span class="x-lconn-userid lotusHidden">
								<xsl:value-of select="a:contributor/snx:userid"/>
							</span>
						</span>						
	
						<span class="divider" role="img" aria-hidden="true">|</span>
						<span class="activities-date" role="presentation">
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_datestamp')"/>
							</xsl:attribute>		
							<xsl:value-of select="a:updated" />
						</span>
					</span>
					<xsl:choose>
						<xsl:when test="a:content">
							<xsl:choose>
								<xsl:when test="a:content != ' '">
									<p class="activities-escape-html bidiAware">
										<xsl:attribute name="id">
											<xsl:value-of select="concat($this-id,'_content')"/>
										</xsl:attribute>		
										<xsl:value-of
											select="a:content" />
									</p>
								</xsl:when>
								<xsl:when test="a:summary != ''">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_summary')"/>
									</xsl:attribute>		
									<p class="activities-escape-html bidiAware">
										<xsl:value-of
											select="a:summary" />
									</p>
								</xsl:when>
							</xsl:choose>
						</xsl:when>
					</xsl:choose>
				</div>
			</div>
		</xsl:for-each>
	</xsl:template>
</xsl:stylesheet>
