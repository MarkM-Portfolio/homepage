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
	<xsl:param name="tStamp" />
	<xsl:param name="photoURL"/>
	<xsl:param name="actURL"/>
	<xsl:param name="blogURL"/>
	<xsl:param name="dogURL"/>
	<xsl:param name="userid" />
	<xsl:param name="tags"/>
	<xsl:param name="profilesURL"/>
	<xsl:param name="lang" />	
	<xsl:param name="langBlogs" />
	
	<xsl:template match="/a:feed">
		<xsl:choose>
			<xsl:when test="$choice ='numActUpdates'">
				<xsl:value-of select="count(/a:feed/a:entry)" />
			</xsl:when>
			<xsl:when test="$choice ='numDogUpdates'">
				<xsl:value-of select="count(/a:feed/a:entry)" />
			</xsl:when>
			<xsl:when test="$choice ='numBlogUpdates'">
				<xsl:for-each select="/a:feed/a:entry">
					<xsl:choose>
						<xsl:when test="a:author/snx:userid=$userid">
							<div class="blogNodes"></div>
						</xsl:when>
					</xsl:choose>
				</xsl:for-each>
			</xsl:when>
			<xsl:when test="$choice ='newActivities'">
				<xsl:call-template name="newActivities">
					<xsl:with-param name="tStamp" select="$tStamp" />
					<xsl:with-param name="tags" select="$tags" />
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$choice ='newBlogs'">
				<xsl:call-template name="getNewBlogs">
					<xsl:with-param name="tStamp" select="$tStamp" />
					<xsl:with-param name="tags" select="$tags" />
					<xsl:with-param name="userid" select="$userid" />
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$choice ='newDogear'">
				<xsl:call-template name="getNewDogears">
					<xsl:with-param name="tStamp" select="$tStamp" />
					<xsl:with-param name="tags" select="$tags" />
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$choice ='friends'">
				<xsl:call-template name="getFriends">
					<xsl:with-param name="tStamp" select="$tStamp" />
					<xsl:with-param name="photoURL" select="$photoURL" />
					<xsl:with-param name="actURL" select="$actURL" />
					<xsl:with-param name="blogURL" select="$blogURL" />
					<xsl:with-param name="dogURL" select="$dogURL" />
					<xsl:with-param name="profilesURL" select="$profilesURL" />	
					<xsl:with-param name="lang" select="$lang" />	
					<xsl:with-param name="langBlogs" select="$langBlogs" />				
				</xsl:call-template>
			</xsl:when>
			<xsl:when test="$choice ='contentRoot'">
				<xsl:call-template name="getContextRoot"/>
			</xsl:when>
			<!-- add pending invitation -->
			<xsl:when test="$choice='pending'">
				<xsl:value-of select="/a:feed/snx:pendingInvitations"/>
			</xsl:when>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template name="getFriends">
		<xsl:param name="photoURL"/>
		<xsl:param name="actURL"/>
		<xsl:param name="blogURL"/>
		<xsl:param name="dogURL"/>
		<xsl:param name="tStamp"/>
		<xsl:param name="profilesURL"/>
		<xsl:param name="lang" />	
		<xsl:param name="langBlogs" />
		
		<xsl:for-each select="/a:feed/a:entry">
		<div class="entry">
			<div class="lotusLeft">
				<img class="icon" alt="" style="width:35px; height:35px">
					<xsl:attribute name="src">
						<xsl:value-of select="$photoURL"/>uid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-profile-uid']"/>
					</xsl:attribute>
				</img>
			</div>
			<div class="entryBody">
				<h4><span class="vcard">					
					<a class="fn lotusPerson bidiAware">
						<xsl:attribute name="href">
							<xsl:value-of select="$profilesURL"/>/html/profileView.do?uid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-profile-uid']"/>
						</xsl:attribute>
						<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div/xhtml:a[@class='fn url']" />
					</a>
					<span class="x-lconn-userid" style="display:none;"><xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" /></span>
				</span></h4>
				<span class="info" role="list">
					<a href="javascript:void(0);" class="newActivities lotusLeft" role="button">
						<xsl:attribute name="location">
							<xsl:value-of select="$actURL"/>userid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />&amp;since=<xsl:value-of select="$tStamp"/>
						</xsl:attribute>
						<xsl:attribute name="userid">
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />
						</xsl:attribute>&#160;
					</a>
					<span class="divider lotusLeft" role="img" aria-hidden="true">&#160;</span>
					<a href="javascript:void(0);" class="newBlogs lotusLeft" role="button">
						<xsl:attribute name="userid">
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />
						</xsl:attribute>
						<xsl:attribute name="location">
							<xsl:value-of select="$blogURL"/><xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />?ps=10&amp;since=<xsl:value-of select="$tStamp"/>&amp;lang=<xsl:value-of select="$langBlogs"/>
						</xsl:attribute>&#160;
					</a>
					<span class="divider lotusLeft" role="img" aria-hidden="true">&#160;</span>
					<a href="javascript:void(0);" class="newDogear lotusLeft" role="button">
						<xsl:attribute name="location">
							<xsl:value-of select="$dogURL"/>?userid=<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />&amp;lang=<xsl:value-of select="$lang"/>&amp;since=<xsl:value-of select="$tStamp"/>
						</xsl:attribute>
						<xsl:attribute name="userid">
							<xsl:value-of select="a:content/xhtml:div/xhtml:span/xhtml:div[@class='x-lconn-userid']" />
						</xsl:attribute>&#160;
					</a>
				</span>
			</div>
		</div>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="getNewDogears">
		<xsl:param name="tStamp"/>
		<xsl:for-each select="/a:feed/a:entry">

		<div class="entry">
			<div class="icons">
				<img height="16" width="16">
					<xsl:attribute name="src">
						<xsl:value-of 
							select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" 
						/>favicon?host=<xsl:value-of select="substring-before(substring-after(a:link/@href,'://'),'/')" />
					</xsl:attribute>
				</img>
			</div>
			<div class="entryBodySmall">
				<h4>
				<a target="_blank">
					<xsl:for-each
						select="a:link[not(@rel)]">

						<xsl:attribute name="href">
							<xsl:value-of select="@href" />
						</xsl:attribute>
					</xsl:for-each>
					<span id="bookmarkTitle" class="bidiAware">
						<xsl:value-of select="a:title" />
					</span>
				</a>
				</h4>
				<span role="list">
					<span class="lotusLeft" role="listitem">
						<xsl:for-each select="a:author">
							<span class="vcard">
								<span class="x-lconn-username lotusHidden">
									<xsl:value-of select="a:name"/>
								</span>	
								<span class="x-lconn-userid lotusHidden">
									<xsl:value-of select="snx:userid" />
								</span>
							</span>
						</xsl:for-each>
					</span>
					<span class="divider lotusLeft meta" role="img" aria-hidden="true">|</span>
					<span class="dateSpan lotusLeft meta" role="listitem">
						<xsl:value-of select="a:published" />
					</span>
					<span class="divider lotusLeft meta" role="img" aria-hidden="true">|</span>
					<span role="listitem"><span class="lotusTags">
						<xsl:value-of select="$tags"/>
						<span role="list"> 
							<xsl:for-each select="a:category">
								<xsl:if test="@term!='bookmark'">
									<a class="filterTag" target="_blank" role="listitem">
										<xsl:attribute name="href">
											<xsl:value-of select="../a:author/a:uri"/>&amp;tag=<xsl:call-template name="recClean"><xsl:with-param name="val" select="@term"/></xsl:call-template>
										</xsl:attribute>
										<xsl:attribute name="title">
											<xsl:value-of select="@term"/>
										</xsl:attribute>
										<xsl:value-of select="@term"/>
									</a><xsl:if test="position()!=last()">,&#160;</xsl:if>
								</xsl:if>
							</xsl:for-each>
						</span>
					</span></span>
				</span>
				<div class="contentNode" >
					<xsl:value-of select="a:content" />
				</div>
			</div>		
		</div>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="newActivities">
		<xsl:param name="tStamp"/>
		<xsl:for-each select="/a:feed/a:entry">
		<div class="entry">
			<xsl:if test="snx:icon">
			<div class="icons">
				<img alt="" title="" border="0" style="margin-right:5px;">
					<xsl:attribute name="src">
						<xsl:value-of select="snx:icon" />
					</xsl:attribute>
				</img>
			</div>
			</xsl:if>
			<div class="entryBody">
				<h4>
					<a href="#" target="_blank" class="bidiAware">
						<xsl:attribute name="href">
							<xsl:value-of select="a:link[@type='application/xhtml+xml']/@href" />
						</xsl:attribute>
						<xsl:value-of select="a:title" />
					</a>	
				</h4>
				<span role="list">
					<span class="vcard" role="listitem">
						<span class="x-lconn-username lotusHidden">
							<xsl:value-of select="a:contributor/a:name"/>
						</span>	
 					<span class="x-lconn-userid lotusHidden"><xsl:value-of select="a:contributor/snx:userid" /></span>
					</span>
					<span class="divider" role="img" aria-hidden="true">|</span>
					<span class="dateSpan lotusMeta" role="listitem"><xsl:value-of select="a:updated"/></span>
				</span>
				<xsl:choose>
					<xsl:when test="a:content">
						<xsl:choose>
							<xsl:when test="a:content != ' '">
								<p class="activities-escape-html">
									<xsl:value-of
										select="a:content" />
								</p>
							</xsl:when>
							<xsl:when test="a:summary != ''">
								<p class="activities-escape-html">
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
	<xsl:template name="getNewBlogs">
		<xsl:param name="tStamp"/>
		<xsl:param name="userid"/>
		
		<xsl:for-each select="/a:feed/a:entry">
		<xsl:choose>
		<xsl:when test="a:author/snx:userid = $userid">
		<div class="entry">
			<div class="entryBody">
				<h4 style="margin-bottom:5px;" class="bidiAware">
					<a target="_blank">
						<xsl:attribute name="href">
							<xsl:apply-templates select="a:link[@rel='alternate']/@href" />
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
					<span class="vcard" role="listitem">
						<span class="x-lconn-username lotusHidden">
							<xsl:value-of select="a:author/a:name"/>
						</span>	
						<span class="x-lconn-userid lotusHidden"><xsl:value-of select="a:author/snx:userid" /></span>
					</span>
					<span class="divider" role="img" aria-hidden="true">|</span>
					<span class="dateSpan lotusMeta" role="listitem"> <xsl:value-of select="a:published"/> </span><br />
				</span>
				<span class="content" 
					style="font-size:1.1em;height:1.1em;display:block;overflow:hidden;lineheight:1em;margin-bottom:2px;padding-bottom:2px;">
						<xsl:choose>
						<xsl:when test="substring-before(substring(a:content,1,40),' ')=''">
							<xsl:value-of select="substring(a:content,1,40)"/>
						</xsl:when>
						<xsl:otherwise>
							<xsl:value-of select="substring(a:content,1,100)"/>
						</xsl:otherwise>
						</xsl:choose>
				</span>	
			</div> <!-- close div class entryBody -->
		</div> <!-- close div class entry -->
		</xsl:when>
		</xsl:choose>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="getContextRoot">
		<xsl:value-of select="substring-before(/a:feed/a:link[@rel='self']/@href,'atom')" />
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
