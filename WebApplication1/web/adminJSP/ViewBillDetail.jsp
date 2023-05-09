<%-- 
    Document   : ViewBillDetail
    Created on : Feb 15, 2023, 5:30:26 PM
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
        <%@ page import = "entity.BillDetail, java.sql.ResultSet" %>
        <% 
            ResultSet rs = (ResultSet) request.getAttribute("dataBillDe");
            String title = (String) request.getAttribute("title");
        %>  
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertBillDetail.jsp">Insert Bill Detail</a></div>
            <div><a href="BillDetailControllerMVC">View All Bill Detail</a></div>
            <div class="search-box">
                <form action="BillDetailControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="bid" placeholder="Search Bill ID">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>
        <table border = "1">
            <caption>${title}</caption>
                <tr>
                    <th>Bill ID</th>
                    <th>Product ID</th>
                    <th>Buy Quantity</th>
                    <th>Buy Price</th>
                    <th>Subtotal</th>
                    <th>Customer ID</th>
                    <th>Customer Name</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>

                <%while (rs.next()) {%>
                <tr>
                    <td><%=rs.getString(1) %></td>
                    <td><%= rs.getString(2) %></td>
                    <td><%= rs.getString(3) %></td>
                    <td><%= rs.getString(4) %></td>
                    <td><%= rs.getString(5) %></td>
                    <td><%= rs.getString(6) %></td>
                    <td><%= rs.getString(7) %></td>
                    <td><a href="BillDetailControllerMVC?go=update&bid=<%= rs.getString(1) %>&pid=<%= rs.getString(2) %>">Update</a></td>
                    <td><a href="BillDetailControllerMVC?go=delete&bid=<%= rs.getString(1) %>&pid=<%= rs.getString(2) %>">Delete</a></td>
                </tr>
                <%}%> 
            </table>
    </body>
</html>
