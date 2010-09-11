<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.swradioafrica.model.SWRadioProperties" %>

<% SWRadioProperties properties = (SWRadioProperties)request.getAttribute("properties"); %>
<% String message = (String)request.getAttribute("message"); %>

<html>
  <head>
      <title>Properties</title>
      <link rel="stylesheet" href="/styles/main.css" type="text/css" />
      
  </head>
  <body>
      <h1>Account details can be modified below</h1>
      <% if (message != null) {%>
      <div class="success"><%= message %></div>
      <% } %>
<form action="" method="POST">
<p><h2>Twitter Username:</h2><br/>
<input type="text" name="twitterUsername" value="<%= properties.twitterUsername %>" size="100"/></p>

<p><h2>Twitter consumerKey:</h2><br/>
<input type="text" name="consumerKey" value="<%= properties.consumerKey %>" size="100"/></p>

<p><h2>Twitter consumerSecret:</h2><br/>
<input type="text" name="consumerSecret" value="<%= properties.consumerSecret %>" size="100"/></p>

<p><h2>Twitter AccessToken:</h2><br/>
<input type="text" name="twitterAccessToken" value="<%= properties.twitterAccessToken %>" size="100"/></p>

<p><h2>Twitter AccessTokenSecret:</h2><br/>
<input type="text" name="twitterAccessTokenSecret" value="<%= properties.twitterAccessTokenSecret %>" size="100"/></p>

<p><h2>j.mp Username:</h2><br/>
<input type="text" name="JMPUsername" value="<%= properties.JMPUsername %>" size="100"/></p>

<p><h2>j.mp key:</h2><br/>
<input type="text" name="JMPKey" value="<%= properties.JMPKey %>" size="100"/></p>

<input type="submit" value="Submit" />
</form>

<div><a href="/admin">Return to Admin Home page</a></div>
</body>
</html>