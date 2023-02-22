<%-- 
    Document   : Index
    Created on : Feb 21, 2023, 10:32:56 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            ResultSet rsMenu = (ResultSet) request.getAttribute("dataMenu");
        %>
        <ul>
            <%while (rsMenu.next()) {%> 
            <li><a href="ClientController?go=displayProductByCategory&cateID=<%=rsMenu.getInt(1)%>"> <%=rsMenu.getString(2)%> </a></li>
            <%}%>
        </ul>   
        <div>
            <jsp:include page="ViewProduct.jsp"></jsp:include>
        </div>
    </body>
</html>
