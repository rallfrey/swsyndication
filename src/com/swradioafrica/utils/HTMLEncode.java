/*
 *  Java HTML Tidy - JTidy
 *  HTML parser and pretty printer
 *
 *  Copyright (c) 1998-2000 World Wide Web Consortium (Massachusetts
 *  Institute of Technology, Institut National de Recherche en
 *  Informatique et en Automatique, Keio University). All Rights
 *  Reserved.
 *
 *  Contributing Author(s):
 *
 *     Dave Raggett <dsr@w3.org>
 *     Andy Quick <ac.quick@sympatico.ca> (translation to Java)
 *     Gary L Peskin <garyp@firstech.com> (Java development)
 *     Sami Lempinen <sami@lempinen.net> (release management)
 *     Fabrizio Giustina <fgiust at users.sourceforge.net>
 *     Vlad Skarzhevskyy <vlads at users.sourceforge.net> (JTidy servlet  development)
 *
 *  The contributing author(s) would like to thank all those who
 *  helped with testing, bug fixes, and patience.  This wouldn't
 *  have been possible without all of you.
 *
 *  COPYRIGHT NOTICE:
 *
 *  This software and documentation is provided "as is," and
 *  the copyright holders and contributing author(s) make no
 *  representations or warranties, express or implied, including
 *  but not limited to, warranties of merchantability or fitness
 *  for any particular purpose or that the use of the software or
 *  documentation will not infringe any third party patents,
 *  copyrights, trademarks or other rights.
 *
 *  The copyright holders and contributing author(s) will not be
 *  liable for any direct, indirect, special or consequential damages
 *  arising out of any use of the software or documentation, even if
 *  advised of the possibility of such damage.
 *
 *  Permission is hereby granted to use, copy, modify, and distribute
 *  this source code, or portions hereof, documentation and executables,
 *  for any purpose, without fee, subject to the following restrictions:
 *
 *  1. The origin of this source code must not be misrepresented.
 *  2. Altered versions must be plainly marked as such and must
 *     not be misrepresented as being the original source.
 *  3. This Copyright notice may not be removed or altered from any
 *     source or altered source distribution.
 *
 *  The copyright holders and contributing author(s) specifically
 *  permit, without fee, and encourage the use of this source code
 *  as a component for supporting the Hypertext Markup Language in
 *  commercial products. If you use this source code in a product,
 *  acknowledgment is not required but would be appreciated.
 *
 */
package com.swradioafrica.utils;
/*
 * Created on 28.08.2004 by vlads
 */
import java.util.Hashtable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Converts a String to HTML by converting all special characters to
 * HTML-entities.
 *
 * @author Vlad Skarzhevskyy <a href="mailto:skarzhevskyy@hotmail.com">skarzhevskyy@hotmail.com </a>
 * @version $Revision: 1.1 $ ($Author: vlads $)
 */

public class HTMLEncode
{

    private HTMLEncode()
    {

    }

    private static final String[] ENTITIES =
    {
            ">", "&gt;",
            "<", "&lt;",
            "&", "&amp;",
            "\"", "&quot;",
            "'", "&#039;",
            "\\", "&#092;"
    };

    private static Hashtable<String, String> entityTableEncode = null;

    protected static synchronized void buildEntityTables()
    {
        entityTableEncode = new Hashtable<String, String>(ENTITIES.length);

        for (int i = 0; i < ENTITIES.length; i += 2)
        {
            if (!entityTableEncode.containsKey(ENTITIES[i]))
            {
                entityTableEncode.put(ENTITIES[i], ENTITIES[i + 1]);
            }
        }
    }

    public final static String encode(String s)
    {
        return encode(s, "\n");
    }
    /**
     * Converts a String to HTML by converting all special characters to
     * HTML-entities.
     */
    public final static String encode(String s, String cr)
    {
        if (entityTableEncode == null)
        {
            buildEntityTables();
        }
        if (s == null)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer(s.length() * 2);
        char ch;
        for (int i = 0; i < s.length(); ++i)
        {
            ch = s.charAt(i);
            if ((ch >= 63 && ch <= 90) || (ch >= 97 && ch <= 122))
            {
                sb.append(ch);
            }
            else if (ch == '\n')
            {
                sb.append(cr);
            }
            else
            {
                sb.append(encodeSingleChar(String.valueOf(ch)));
            }
        }
        return sb.toString();
    }

    /**
     * Converts a single character to HTML
     */
    private static String encodeSingleChar(String ch)
    {
        String s = (String) entityTableEncode.get(ch);
        return (s == null) ? ch : s;
    }

    /**
     * Converts a String to valid HTML HREF by converting all special characters to
     * HTML-entities.
     * @todo copy implementation.
     */
    protected static String encodeHREFParam(String value)
    {
        try
        {
            // Java 1.4
            return URLEncoder.encode(value, "UTF-8");
        }
        catch (NoSuchMethodError e)
        {
            return encodeHREFParamJava13(value);
        }
        catch (UnsupportedEncodingException e)
        {
            throw new RuntimeException("UTF-8 not supported", e);
        }
    }

    @SuppressWarnings("deprecation")
	protected static String encodeHREFParamJava13(String value)
    {
        return URLEncoder.encode(value);
    }

    public static String encodeHREFQuery(String url, String[] args)
    {
        StringBuffer out = new StringBuffer(128);
        out.append(url);

        if ((args != null) && (args.length > 0))
        {
            out.append("?");
            for (int i = 0; i < (args.length + 1) / 2; i++)
            {
                int k = i * 2;
                if (k != 0)
                {
                   out.append("&");
                }
                out.append(encodeHREFParam(args[k]));
                if (k + 1 < args.length)
                {
                    out.append("=");
                    out.append(encodeHREFParam(args[k + 1]));
                }
            }
        }
        return out.toString();
    }
}