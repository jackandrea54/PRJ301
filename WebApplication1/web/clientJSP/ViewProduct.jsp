<%-- 
    Document   : ViewProduct
    Created on : Feb 16, 2023, 12:11:08 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@ page import = "entity.Product, java.sql.ResultSet" %>
        
        <%
            ResultSet rs = (ResultSet) request.getAttribute("dataPro");
            String title = (String) request.getAttribute("title");
        %>
        
        
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th>Product ID</th>
                <th>Category</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price </th>
                <th>image</th>
                <th>Description</th>
                <th>Status</th>
            </tr>
            <%while (rs.next()) {%>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(10)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getString(4)%></td>
                <td><img src= "<%=rs.getString(5)%>" width="100" height="150"></td>
                <td><%=rs.getString(6)%></td>
                <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ClientController">Product Manage</a></td>
            </tr>
        </table>   
    </body>
</html>
