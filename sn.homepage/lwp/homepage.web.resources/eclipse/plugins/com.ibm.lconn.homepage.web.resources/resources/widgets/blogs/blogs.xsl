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
	exclude-result-prefixes="a app snx os xhtml thr">
	
	<xsl:output method="html" encoding="utf-8" />
	
	<xsl:param name="userid" />
	<xsl:param name="tags" />
	<xsl:param name="more" />
	<xsl:param name="noCommentsIcon" />
	<xsl:param name="commentsIcon" />
	<xsl:param name="noLikeAlt" />
	<xsl:param name="likeAlt" />
	<xsl:param name="fullLikeAlt" />
	<xsl:param name="noCommentsAlt" />
	<xsl:param name="commentsAlt" />
	<xsl:param name="blankGifPath" />
	<xsl:param name="ariaTag" />
	<xsl:param name="entryTitle" />

	<xsl:template match="/a:feed">
			<xsl:apply-templates select="a:entry" />	
	</xsl:template>

	
	<xsl:template match="a:link"><xsl:value-of select="@href"/></xsl:template>

	<xsl:template match="a:entry">
		<div class="entry">
			<xsl:variable name="this-id" select="generate-id(.)"/>
			
			<div class="icons">
				<img class="picture" alt="">
					<!-- use this value for picture api in the future -->
					<xsl:attribute name="userid">
						<xsl:value-of select="a:author/snx:userid"/>
					</xsl:attribute>									
				</img>
			</div>
			<div class="entryBody">
				<h4 style="margin-bottom:5px;">
					<a target="_blank" class="bidiAware" tabindex="0">
						<xsl:attribute name="href">
							<xsl:apply-templates select="a:link[./@rel='alternate']" />
		  				</xsl:attribute>
		  				<xsl:attribute name="title">
		  					<xsl:value-of select="$entryTitle"/>
		  				</xsl:attribute>			
						<xsl:attribute name="aria-describedby">
							<xsl:value-of select="concat($this-id,'_datestamp',' ',$this-id,'_likeAltText',' ',$this-id,'_likeCount',' ',$this-id,'_commentAltText',' ',$this-id,'_commentCount')"/>
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
								<xsl:value-of select="a:author/a:name"/>
							</span>
							<span class="x-lconn-userid lotusHidden">
								<xsl:value-of select="a:author/snx:userid"/>					
							</span>
						</span>
					</span>
					<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
					<span class="meta published lotusLeft" role="presentation">
						<xsl:attribute name="id">
							<xsl:value-of select="concat($this-id,'_datestamp')"/>
						</xsl:attribute>		
						<xsl:value-of select="a:published"/>
					</span>
					<span class="divider lotusLeft" role="img" aria-hidden="true">|</span>
					<a href="javascript:void(0);" class="showContent" role="button">
						<xsl:attribute name="title">
							<xsl:value-of select="$more"/>
						</xsl:attribute>
						<xsl:value-of select="$more"/>
					</a>
				</span>
				
					<!-- display the comments -->
					<!-- in blog we use this matching: snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/comment'] -->
					<!-- in blogcentral we use this matching: a:link/@thr:count -->
					
					<span class="meta lotusRight">
						<xsl:choose>
							<xsl:when test="snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/comment']=0">
								<img class="lconnSprite lconnSprite-iconNoComments16" 
									 alt="{$noCommentsAlt}" 
									 title="{$noCommentsAlt}"> 
									<xsl:attribute name="src">
										<xsl:value-of select="$blankGifPath" />
									</xsl:attribute>
								</img>
								<span class="lotusAltText" style="z-index: -1">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_commentAltText')"/>
									</xsl:attribute>		
									<xsl:value-of select="$noCommentsAlt"/>
								</span>
							</xsl:when>						
							<xsl:otherwise>
								<img class="lconnSprite lconnSprite-iconComment16" 
									 alt="{$commentsAlt}" 
									 title="{$commentsAlt}"> 
									 <xsl:attribute name="src">
										<xsl:value-of select="$blankGifPath" />
									</xsl:attribute>
								</img>
								<span class="lotusAltText" style="z-index: -1">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_commentAltText')"/>
									</xsl:attribute>		
									<xsl:value-of select="$commentsAlt"/>
								</span>
							</xsl:otherwise>
						</xsl:choose>&#160;	
						<span>
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_commentCount')"/>
							</xsl:attribute>		
							<xsl:value-of select="snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/comment']" />
						</span>									
						<!-- <xsl:value-of select="a:link[@rel='replies']/@thr:count" />-->
					</span>
					<span class="lotusRight divider" role="img" aria-hidden="true">&#160;</span>        
					<!-- display the raccomandations -->
					<!-- in blog we use this matching: snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/recommendations'] -->
					<!-- in blogcentral we use this matching: r:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/rating'] -->
					<span class="meta lotusRight">
						<xsl:choose>
							<xsl:when test="snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/recommendations']=0       or r:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/rating']=0 ">
								<img class="lotusIconLike" 
									 alt="{$noLikeAlt}"
									 title="{$noLikeAlt}">
									 <xsl:attribute name="src">
										<xsl:value-of select="$blankGifPath" />
									</xsl:attribute>
								</img>
								<span class="lotusAltText" style="z-index: -1">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_likeAltText')"/>
									</xsl:attribute>		
									<xsl:value-of select="$noLikeAlt"/>
								</span>
							</xsl:when>
							<xsl:when test="snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/recommendations'] &lt; 20 or r:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/rating'] &lt; 20" >
								<img class="lotusIconLike" 
									alt="{$likeAlt}"
									title="{$likeAlt}">
									 <xsl:attribute name="src">
										<xsl:value-of select="$blankGifPath" />
									 </xsl:attribute>
								</img>
								<span class="lotusAltText" style="z-index: -1">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_likeAltText')"/>
									</xsl:attribute>		
									<xsl:value-of select="$likeAlt"/>
								</span>
							</xsl:when>						
							<xsl:otherwise>
								<img class="lotusIconLike" 
									alt="{$fullLikeAlt}"
									title="{$fullLikeAlt}">
									<xsl:attribute name="src">
										<xsl:value-of select="$blankGifPath" />
									</xsl:attribute>
								</img>
								<span class="lotusAltText" style="z-index: -1">
									<xsl:attribute name="id">
										<xsl:value-of select="concat($this-id,'_likeAltText')"/>
									</xsl:attribute>		
									<xsl:value-of select="$fullLikeAlt"/>
								</span>
							</xsl:otherwise>
						</xsl:choose>&#160;						
						<span>
							<xsl:attribute name="id">
								<xsl:value-of select="concat($this-id,'_likeCount')"/>
							</xsl:attribute>		
							<xsl:value-of select="snx:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/recommendations']" />
							<xsl:value-of select="r:rank[@scheme='http://www.ibm.com/xmlns/prod/sn/rating']" />
						</span>
					</span>

					
				<span class="bodyHiddenContent" style="display: none">
					<div class="entry">
					<h3>
					<a  target="_blank" class="bidiAware">
						<xsl:attribute name="title">
							<xsl:value-of select="$entryTitle" />
						</xsl:attribute>
						<xsl:attribute name="href">
							<xsl:value-of select="a:link[@rel='alternate']/@href" />
						</xsl:attribute>
	 					<xsl:attribute name="aria-describedby">
							<xsl:value-of select="concat($this-id,'_detailsDateStamp',' ',$this-id,'_detailsContent')"/>		
						</xsl:attribute>
						<xsl:attribute name="data-ariaforchange">true</xsl:attribute>
						<xsl:value-of select="a:title" />
					</a>
					</h3>
					<span class="lotusLeft">
						<span class="vcard">
	   						<span class="x-lconn-username lotusHidden">
								<xsl:value-of select="a:author/a:name"/>
							</span>
							<span class="x-lconn-userid lotusHidden">
								<xsl:value-of select="a:author/snx:userid"/>					
							</span>
						</span>
					</span>
					<span class="meta divider lotusLeft" role="img" aria-hidden="true">|</span>
					<span class="meta published lotusLeft">
						<xsl:attribute name="id">
							<xsl:value-of select="concat($this-id,'_detailsDateStamp')"/>
						</xsl:attribute>	
						<xsl:attribute name="data-nodeforchange">true</xsl:attribute>		
						<xsl:value-of select="a:published"/>
					</span>
					<span class="meta divider lotusLeft" role="img" aria-hidden="true">|</span>					
					<span style="margin-bottom:5px;">
						<span><span class="lotusTags"><xsl:value-of select="$tags"/>&#160;<xsl:for-each select="a:category">
							<a target="_blank" class="bidiAware" aria-describedby="ariaTagDescription">
							<xsl:attribute name="href">
								<xsl:value-of select="substring-before(../a:link[@rel='alternate']/@href,'entry')" />tags/<xsl:call-template name="recClean"><xsl:with-param name="val" select="@term" /></xsl:call-template>
							</xsl:attribute>
							<xsl:attribute name="title">
								<xsl:value-of select="@term"/>
							</xsl:attribute>
							<xsl:attribute name="aria-label">
								<xsl:value-of select="translate($ariaTag, '${0}', @term)"/>
							</xsl:attribute>
							<xsl:value-of select="@term"/>
							</a><xsl:if test="position()!=last()">,&#160;</xsl:if>
						</xsl:for-each><br />
						</span></span>
					</span><br />
					<div>
						<xsl:attribute name="lang">
							<xsl:value-of select="/a:feed/a:entry/@xml:lang"/>
						</xsl:attribute>
						<xsl:attribute name="id">
							<xsl:value-of select="concat($this-id,'_detailsContent')"/>
						</xsl:attribute>	
						<xsl:attribute name="data-nodeforchange">true</xsl:attribute>		
						<xsl:choose>
							<xsl:when test="a:content">
								<xsl:value-of select="a:content"/>							
							</xsl:when>
							<xsl:otherwise>
								<div>&#160;</div>
							</xsl:otherwise>
						</xsl:choose>
					</div>
					<br />
					</div>
				</span>
			</div> <!-- close div class entryBody -->
		</div> <!-- close div class entry -->
	</xsl:template>
	<xsl:template name="recClean">
		<xsl:param name="val" />
		<xsl:choose>
			<xsl:when test="$val='+'">%2b</xsl:when>
			<xsl:when test="contains($val,'%')">
				<xsl:call-template name="encode_percentage_sign">
							<xsl:with-param name="str" select="$val" />
				</xsl:call-template>
			</xsl:when>			
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
    <xsl:template name="encode_percentage_sign">
        <xsl:param name="str"/>
        <xsl:choose>
            <xsl:when test="contains($str,'%')">
                <xsl:value-of select="substring-before($str,'%')"/>%25<xsl:call-template name="encode_percentage_sign">
                    <xsl:with-param name="str" select="substring(substring-after($str,'%'),1)"/>
                </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
                <xsl:value-of select="$str"/>
            </xsl:otherwise>
        </xsl:choose>
    </xsl:template>	
</xsl:stylesheet>
