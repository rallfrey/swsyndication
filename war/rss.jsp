<%@ page contentType="text/xml;charset=UTF-8" language="java" %>
<%@ page import="com.swradioafrica.model.ContentItem" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>

<% List<ContentItem> contentItems = (List<ContentItem>)request.getAttribute("contentItems"); %>
<rss xmlns:content="http://purl.org/rss/1.0/modules/content/" version="2.0">
	<channel>
		<title>SW Radio Africa</title>
		<link>http://www.swradioafrica.com</link>
		<description>The independent voice of Zimbabwe</description>
		<language>en-gb</language>
		<lastBuildDate><%= new Date() %></lastBuildDate>

		<% for(ContentItem item : contentItems) { %>
		<item>
			<title><%= item.getEscapedTitle() %> | SW Radio Africa news - The Independent Voice of Zimbabwe</title>
			<link><%= item.url %></link>
			<description>&lt;p class="font-family: Arial, Helvetica, sans-serif; font-size: 14pt; color: #990000; font-weight: bold"&gt;SW Radio Africa news - The Independent Voice of Zimbabwe&lt;/p&gt;
				<%= item.getEscapedBody() %></description>
			<pubDate><%= item.getPublishedDateRSS822() %></pubDate>
		</item>
		<% } %>
	</channel>
</rss>