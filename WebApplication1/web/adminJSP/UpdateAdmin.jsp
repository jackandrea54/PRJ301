<%-- 
    Document   : UpdateAdmin.jsp
    Created on : Feb 16, 2023, 5:07:34 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <%@ page import="entity.Admin" %>
        <%
            Admin ad = (Admin)request.getAttribute("dataAdmin");
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertAdmin.jsp">Insert Admin</a></div>
            <div><a href="AdminControllerMVC">View All Admin</a></div>
        </div>
        <h1>UPDATE FOR ADMIN</h1>
        <form action="AdminControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="admin">Admin account</label></td>
                    <td><input type="text" name="admin" id = "admin" value = "<%=ad.getAdmin()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="text" name="password" id = "password" value = "<%=ad.getPassword()%>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Admin" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
