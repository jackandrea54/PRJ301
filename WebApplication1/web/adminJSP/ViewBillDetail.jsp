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
        <%@ page import = "entity.BillDetail, java.util.Vector" %>
        <% 
            Vector<BillDetail> vector = (Vector<BillDetail>) request.getAttribute("dataBillDe");
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
                    <th>Update</th>
                    <th>Delete</th>
                </tr>

                <% for (BillDetail temp : vector) { %>
                <tr>
                    <td><%=temp.getBid() %></td>
                    <td><%= temp.getPid() %></td>
                    <td><%= temp.getBuyQuantity() %></td>
                    <td><%= temp.getBuyPrice() %></td>
                    <td><%= temp.getSubtotal() %></td>
                    <td><a href="BillDetailControllerMVC?go=update&bid=<%= temp.getBid() %>&pid=<%= temp.getPid() %>">Update</a></td>
                    <td><a href="BillDetailControllerMVC.jsp?go=delete&bid=<%= temp.getBid() %>&pid=<%= temp.getPid() %>">Delete</a></td>
                </tr>
                <%}%> 
            </table>
    </body>
</html>
