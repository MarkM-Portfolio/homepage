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
	
	<xsl:param name="feed" />
	<xsl:param name="bookmark" />
	<xsl:param name="forum" />
	<xsl:param name="forumsEnabled" />
	<xsl:param name="tags" />
	<xsl:param name="logoAlt" />
	<xsl:param name="commTitle" />
	
	<xsl:template name="entries" match="/atom:feed">
		<xsl:for-each select="atom:entry">
		<div class="entry">
			<div class="icons">
				<img style="width:32px;height:32px;valign:top;" alt="">
					<xsl:for-each select="atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/logo']">
						<xsl:attribute name="src">
							<xsl:value-of select="@href" />
						</xsl:attribute>
					</xsl:for-each>						
				</img>
			</div>
			<div class="entryBodyLarge">				
				<h4>
				<a target="_blank" class="bidiAware">
						<xsl:attribute name="href">
							<xsl:value-of select="atom:link[@rel='alternate']/@href" />
						</xsl:attribute>
						<xsl:attribute name="title">
							<xsl:value-of select="$commTitle" />
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
					<span class="dateSpan" role="listitem">
						<xsl:value-of select="atom:updated"/>
					</span>
				</span>
				<br/>
				<span role="list">
				<span role="listitem">
					<a href="javascript:void(0);" class="communityFeedLink lotusAction" role="button">
						<xsl:for-each select="atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/feeds']">
							<xsl:attribute name="location">
								<xsl:value-of select="@href" />
						 	</xsl:attribute>
						</xsl:for-each>
						<xsl:attribute name="title">
							<xsl:value-of select="$commTitle" />&#160;:&#160;<xsl:value-of select="$feed" />
						</xsl:attribute>
						<xsl:value-of select="$feed" /></a>
				</span><span class="divider" role="img" aria-hidden="true">|</span>
				<span role="listitem"> 
					<a href="javascript:void(0);" class="communityBookmarkLink lotusAction" role="button">
						<xsl:for-each select="atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/bookmarks']">
							<xsl:attribute name="location">
								<xsl:value-of select="@href" />
						 	</xsl:attribute>
						</xsl:for-each>
						<xsl:attribute name="title">
								<xsl:value-of select="$commTitle" />&#160;:&#160;<xsl:value-of select="$bookmark" />
						</xsl:attribute>
						<xsl:value-of select="$bookmark" /></a>
				</span>
				<xsl:choose>
					<xsl:when test="$forumsEnabled and atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/forum-topics']">
						<span class="divider" role="img" aria-hidden="true">|</span>
						<span role="listitem">
							<a href="javascript:void(0);" class="communityForumLink lotusAction" role="button">
							<xsl:attribute name="location">
								<xsl:value-of select="atom:link[@rel='http://www.ibm.com/xmlns/prod/sn/forum-topics']/@href" />&amp;ps=10				</xsl:attribute>
							<xsl:attribute name="title">
								<xsl:value-of select="$commTitle" />&#160;:&#160;<xsl:value-of select="$forum" />
							</xsl:attribute>
							<xsl:value-of select="$forum" /></a>
						</span>	
					</xsl:when>
					<xsl:otherwise>
					</xsl:otherwise>
				</xsl:choose>
				</span>		
			</div>
		</div>
		</xsl:for-each>
	</xsl:template>
	
</xsl:stylesheet>
