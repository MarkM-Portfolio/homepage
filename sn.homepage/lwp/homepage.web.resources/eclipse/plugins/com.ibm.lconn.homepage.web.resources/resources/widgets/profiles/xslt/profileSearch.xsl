<?xml version="1.0" encoding="utf-8"?>
<!-- ***************************************************************** -->
<!--                                                                   -->
<!-- IBM Confidential                                                  -->
<!--                                                                   -->
<!-- OCO Source Materials                                              -->
<!--                                                                   -->
<!-- Copyright IBM Corp. 2008, 2015                                    -->
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
	
	<xsl:template match="/a:feed">
			<xsl:for-each select="/a:feed/a:entry">
				<div class="entry">
					<div class="icons" style="float:left">
						<span></span>
					</div>
					<div class="entryBody">
						<span class="vcard">
							<a class="fn lotusPerson bidiAware">
								<xsl:attribute name="href">
									<xsl:value-of select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" />html/profileView.do?uid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-profile-uid']"/>
								</xsl:attribute>
								<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div/xhtml:a[@class='fn url']" />
							</a>
							<span class="x-lconn-userid" style="display:none;">
								<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />
							</span>
						</span>
						&#160;
						<span>
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='title']" />
						</span>
						&#160;
						<span>
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='adr work postal']/xhtml:span[@class='locality']" />
							&#160;
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='adr work postal']/xhtml:div[@class='country-name']" />
						</span>
					</div>
				</div>
			</xsl:for-each>
	</xsl:template>	
	
</xsl:stylesheet>
	
