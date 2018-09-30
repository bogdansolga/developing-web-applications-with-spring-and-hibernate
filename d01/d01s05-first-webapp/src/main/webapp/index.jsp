<%@ page import="java.time.LocalTime" %>
<html>
    <title>Welcome to our first JSP page!</title>
    <body>
        <h2>The time is <% out.println(LocalTime.now()); %></h2>
        <h3> <% out.println("On port " + request.getServerPort()); %></h3>
    </body>
</html>
