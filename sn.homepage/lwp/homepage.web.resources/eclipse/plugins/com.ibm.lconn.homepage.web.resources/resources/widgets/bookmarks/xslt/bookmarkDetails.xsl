<?xml version="1.0" encoding="utf-8"?>
<!-- ***************************************************************** -->
<!--                                                                   -->
<!-- IBM Confidential                                                  -->
<!--                                                                   -->
<!-- OCO Source Materials                                              -->
<!--                                                                   -->
<!-- Copyright IBM Corp. 2007, 2016                                    -->
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
	<xsl:param name="bookmarkletPath" />
	<xsl:param name="ariaTag" />
	<xsl:param name="ariaTagDescription"/>
	<xsl:param name="openNewWindowStr" />
	
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
						<a target="_blank" role="presentation">
							<xsl:for-each
								select="atom:link[not(@rel)]">

								<xsl:attribute name="href">
									<xsl:value-of select="@href" />
								</xsl:attribute>
							</xsl:for-each>
		 					<xsl:attribute name="aria-describedby">
								<xsl:value-of select="concat($this-id,'_dateStamp',' ',$this-id,'_contentNode')"/>
							</xsl:attribute>
							<span class="bidiAware">
								<xsl:value-of select="atom:title" />
							</span>
						</a>
						</h4>
						
						<span class="lotusLeft">
							<xsl:for-each select="atom:author">
								<span class="vcard">
									<span class="x-lconn-username lotusHidden">
										<xsl:value-of select="atom:name"/>
									</span>										
									<span class="x-lconn-userid lotusHidden">
										<xsl:value-of select="snx:userid" />
									</span>
								</span>
							</xsl:for-each>
						</span>
						<span class="divider lotusLeft meta" role="img" aria-hidden="true">|</span>
						<span class="dogAtomDate lotusLeft meta">
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_dateStamp')"/>
							</xsl:attribute>		
							<xsl:value-of select="atom:published" />
						</span>
						<span class="divider lotusLeft meta" role="img" aria-hidden="true">|</span>
						<label class="lotusAccess" role="presentation">
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_ariaTagDescription')" />
							</xsl:attribute>
							<xsl:value-of select="$ariaTagDescription"/>
						</label>
						<span><span class="lotusTags">
							<xsl:value-of select="$tags"/> 
								<span>
								<!--  Only set the role on the parent span if we have at least one item. -->
								<!--  And only do it ONCE, else WebKit gets upset -->
								<xsl:if test="count(atom:category[@term!='bookmark']) &gt; 0">
									<xsl:attribute name="role">list</xsl:attribute>
								</xsl:if>
								<xsl:for-each select="atom:category">
									<xsl:if test="@term!='bookmark'">
										<a class="filterTag bidiAware" target="_blank" role="listitem">
											<xsl:attribute name="aria-describedby">
												<xsl:value-of select="concat($this-id,'_ariaTagDescription')" />
											</xsl:attribute>
											<xsl:attribute name="href">
												<xsl:value-of select="../atom:author/atom:uri"/>&amp;tag=
												<xsl:call-template name="recClean">
													<xsl:with-param name="val" select="@term"/>
												</xsl:call-template>
											</xsl:attribute>
											<xsl:attribute name="title">
												<xsl:value-of select="@term"/>
											</xsl:attribute>
											<xsl:attribute name="aria-label">
												<xsl:value-of select="translate($ariaTag, '${0}', @term)"/>
											</xsl:attribute>
											<xsl:value-of select="@term"/>
										</a>
										<xsl:if test="position()!=last()">,&#160;</xsl:if>
									</xsl:if>
								</xsl:for-each>
								</span>
						</span></span>

						<div class="contentNode bidiAware" >
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id, '_contentNode')" />
							</xsl:attribute>
							<xsl:value-of select="atom:content" />
						</div>
						

						<span>
							<xsl:choose>
								<xsl:when test="atom:author/snx:userid=$userid">
									<span class="lotusAccess" role="presentation">
										<xsl:attribute name="id">
											<xsl:value-of select="concat($this-id, '_openNewWindowStr')" />
										</xsl:attribute>
										<xsl:value-of select="$openNewWindowStr" />
									</span>
									<a href="javascript:;" class="dogEditLink lotusAction">
										<xsl:attribute name="onclick" >var dogPopWin=window.open('<xsl:value-of select="$bookmarkletPath" />/post?url=' + encodeURIComponent('<xsl:value-of select="atom:link/@href" />') + '&amp;showDogearOnly=true&amp;edit=true&amp;userid=<xsl:value-of select="$userid"/>&amp;parentid=<xsl:value-of select="substring-after(atom:id,'link:')" />&amp;ver=ignore','bob','toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=600,height=600,left = 50,top = 50');
										</xsl:attribute>
										<xsl:attribute name="aria-describedby">
											<xsl:value-of select="concat($this-id, '_openNewWindowStr')" />
										</xsl:attribute>
										<xsl:value-of select="$edit" />
									</a>
								</xsl:when>
								<xsl:otherwise>
									<span class="lotusAccess" role="presentation">
										<xsl:attribute name="id">
											<xsl:value-of select="concat($this-id, '_openNewWindowStr')" />
										</xsl:attribute>
										<xsl:value-of select="$openNewWindowStr" />
									</span>
									<a href="javascript:;" class="dogCopyLink lotusAction">
										<xsl:attribute name="onclick" >var dogPopWin=window.open('<xsl:value-of select="$bookmarkletPath" />/post?url=' + encodeURIComponent('<xsl:value-of select="atom:link/@href" />') + '&amp;showDogearOnly=true&amp;edit=true&amp;parentid=<xsl:value-of select="substring-after(atom:id,'link:')" />&amp;ver=ignore','bob','toolbar=0,scrollbars=0,location=0,statusbar=0,menubar=0,resizable=1,width=600,height=600	,left = 50,top = 50');
										</xsl:attribute>
										<xsl:attribute name="aria-describedby">
											<xsl:value-of select="concat($this-id, '_openNewWindowStr')" />
										</xsl:attribute>
										<xsl:value-of select="$copy" />
										</a>
								</xsl:otherwise>
							</xsl:choose>
						</span>

					</div>		
				</div>			
			</xsl:for-each>
		</xsl:if>
	</xsl:template>

	<xsl:template name="entry" match="/atom:entry"></xsl:template>
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
