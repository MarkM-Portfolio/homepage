/* ***************************************************************** */
/*                                                                   */
/* IBM Confidential                                                  */
/*                                                                   */
/* OCO Source Materials                                              */
/*                                                                   */
/* Copyright IBM Corp. 2006, 2015                                    */
/*                                                                   */
/* The source code for this program is not published or otherwise    */
/* divested of its trade secrets, irrespective of what has been      */
/* deposited with the U.S. Copyright Office.                         */
/*                                                                   */
/* ***************************************************************** */

package com.ibm.lconn.web.taglib.jsp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.servlet.jsp.JspException;

public class JavascriptStringTag extends AbstractBodyTransformer
{
	private static final long serialVersionUID = -1055222551284521294L;

	protected String transform(String s) throws JspException
	{
		return quoteJavascriptString(s);
	}

	public static String quoteJavascriptString(String s) throws JspException
	{
		try
		{

			if (s == null || s.length() == 0)
				return "\"\"";

			StringBuffer out = new StringBuffer();
			BufferedReader in = new BufferedReader(new StringReader(s));
			String line;
			boolean cont = false;
			while ((line = in.readLine()) != null)
			{
				if (cont)
					out.append("+\n");
				out.append('"');
				for (int i = 0, n = line.length(); i < n; i++)
				{
					char c = line.charAt(i);
					if (c == '"' || c == '\\')
						out.append('\\');
					out.append(c);
				}
				out.append('"');
				cont = true;
			}

			return out.toString();
		}
		catch (IOException e)
		{
			throw new JspException(e);
		}
	}
}
