<%-- 
    Document   : UpdateCustomer
    Created on : Feb 21, 2023, 12:37:28 AM
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
        <%@ page import="entity.Customer"  %>
        <%
            Customer cus = (Customer) request.getAttribute("dataCus");
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertCustomer.jsp">Insert Customer</a></div>
            <div><a href="CustomerControllerMVC">View All Customer</a></div>
            
        </div>  
        <form action = "CustomerControllerMVC" method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="cid">Customer ID</label></td>
                    <td><input type="text" name="cid" id = "cid" value = "<%= cus.getCid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="cname">Customer Name</label></td>
                    <td><input type="text" name="cname" id = "cname" value = "<%= cus.getCname()%>"></td>
                </tr>
                <tr>
                    <td><label for="username">username</label></td>
                    <td><input type="text" name="username" id = "username" value = "<%= cus.getUsername()%>"></td>
                </tr>
                <tr>
                    <td><label for="password">password</label></td>
                    <td><input type="text" name="password" id = "password" value = "<%= cus.getPassword()%>"></td>
                </tr>
                <tr>
                    <td><label for="address">address</label></td>
                    <td><input type="text" name="address" id = "address" value = "<%= cus.getAddress()%>"></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="text" name="phone" id = "phone" value = "<%= cus.getPhone()%>"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%= (cus.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%= (cus.getStatus() == 0 ? "checked" : "")%>  > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Customer" name="submit"></td>
                </tr>
            </table>

        </form>
    </body>
</html>
