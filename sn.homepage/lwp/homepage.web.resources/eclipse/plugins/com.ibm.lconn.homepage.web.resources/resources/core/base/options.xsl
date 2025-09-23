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
	xmlns:xhtml="http://www.w3.org/1999/xhtml"
	xmlns:hp="http://www.ibm.com/homepage">	
	
	<xsl:output method="html" encoding="utf-8" />
	
	<xsl:param name="choice" />

	<xsl:template match="/">
		<xsl:choose>
			<xsl:when test="$choice = 'map'">				
				[
					<xsl:call-template name="generate_map" />
				]
			</xsl:when>	
			<xsl:otherwise>
				<xsl:call-template name="generate_html_view" />
			</xsl:otherwise>
		</xsl:choose>				
	</xsl:template>	
	
	<xsl:template name="generate_map">
		<xsl:for-each select="hp:option">	
			<xsl:choose>
				<xsl:when test="@type = 'select'">
					<xsl:variable name="choiceId" select="@defaultChoice"/>
					{
						"type": "<xsl:value-of select="@type"/>",
						"id": "<xsl:value-of select="@id"/>",
						"defaultValue2": "<xsl:value-of select="$choiceId"/>",
						"defaultValue": "<xsl:value-of select="choice[@id = $choiceId]/@value"/>",
						"caption": "<xsl:value-of select="@caption" />",
						"choices": [<xsl:call-template name="generate_choice_options" />]
					}
					<xsl:if test="position() != last()">,</xsl:if>
				</xsl:when>			
				<xsl:otherwise>
					{
						"type": "<xsl:value-of select="@type"/>",
						"id": "<xsl:value-of select="@id"/>",
						"defaultValue": "<xsl:value-of select='@defaultValue'/>",
						"caption": "<xsl:value-of select="@caption" />"	
					}
					<xsl:if test="position() != last()">,</xsl:if>
				</xsl:otherwise>
			</xsl:choose>
		</xsl:for-each>
	</xsl:template>

	<xsl:template name="generate_choice_options">
		<xsl:for-each select="choice">
		{
			"id": "<xsl:value-of select="@id"/>",
			"value": "<xsl:value-of select="@value"/>",
		}
		<xsl:if test="position() != last()">,</xsl:if>
		</xsl:for-each>
	</xsl:template>
	
	<xsl:template name="generate_html_view">
	
		<div class="form-option lotusForm2">
			<div class="lotusFormBody">
			<xsl:for-each select="/hp:options/hp:option">
				<div class="lotusFormField">		
					<span class="option-caption lotusLeft">
					<xsl:if test="@hp:required"><span class="lotusFormRequired">*</span></xsl:if>
					<xsl:value-of select="@hp:caption" /></span><br/>
					<xsl:choose>
						<xsl:when test="@hp:type = 'select'">
							<xsl:variable name="choiceId" select="@defaultChoice"/>
							<select class="widgetOptionScroll">
								<xsl:attribute name="name">
									<xsl:value-of select="@hp:id"/>
								</xsl:attribute>
								<xsl:attribute name="aria-label">
									<xsl:value-of select="@hp:caption" />
								</xsl:attribute>
								<xsl:call-template name="generate_html_list_options"></xsl:call-template>
							</select>
						</xsl:when>			
						<xsl:otherwise>						
							<input class="widgetOptionText">
								<xsl:attribute name="type">
									<xsl:value-of select="@hp:type"/>
								</xsl:attribute>
								<xsl:attribute name="name">
									<xsl:value-of select="@hp:id"/>
								</xsl:attribute>
								<xsl:attribute name="value">
									<xsl:value-of select="@hp:defaultValue"/>
								</xsl:attribute>
							</input>
							&#160;
						</xsl:otherwise>
					</xsl:choose>	
				</div>
				
			</xsl:for-each>
			</div>
			
			<div class="lotusFormFooter">
				<input type="button" value="Save" class="save-button lotusFormButton" />
            <input type="button" value="Cancel" class="cancel-button lotusFormButton" />
			</div>
		</div>
		
	</xsl:template>
	
	<xsl:template name="generate_html_list_options">
		<xsl:for-each select="hp:choice">
			<option>
				<xsl:attribute name="value"><xsl:value-of select="@hp:id" /></xsl:attribute>
				<xsl:value-of select="@hp:value" />
			</option>
		</xsl:for-each>	
	</xsl:template>
</xsl:stylesheet>
