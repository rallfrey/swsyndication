<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>SW Radio Africa syndication service</title>
  </head>

  <body>
    <h1>SW Radio Africa syndication service</h1>
    <ul>
        <li><a href="/admin/addurl.jsp">Send URL</a></li>
        <li><a href="/admin/properties">Edit account details</a></li>
    </ul>
    <ul>
        <li><a href="/rss">View RSS feed</a></li>
        <li><a href="/newssitemap.xml">Download Google News sitemap</a></li>        
        <%
            UserService userService = UserServiceFactory.getUserService();
            User user = userService.getCurrentUser();
            if (user != null) {
        %>
        <p>Logged in as: <%= user.getEmail() %>! (You can
        <a href="<%= userService.createLogoutURL(request.getRequestURI()) %>">sign out</a>.)</p>
        <%
            } else {
        %>
        <p>Please 
        <a href="<%= userService.createLoginURL(request.getRequestURI()) %>">Sign in</a>
        to continue.</p>
        <%
            }
        %>

    </ul>
  </body>
</html>
