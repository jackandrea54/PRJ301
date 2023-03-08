<%-- 
    Document   : ViewCustomer
    Created on : Feb 14, 2023, 10:44:11 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CUSTOMER LIST</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <%@ page import = "entity.Customer, java.util.Vector" %>
        <% 
            String title = (String) request.getAttribute("title");
            Vector<Customer> vector = (Vector<Customer>) request.getAttribute("dataCus");
        %>    
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertCustomer.jsp">Insert Customer</a></div>
            <div><a href="CustomerControllerMVC">View All Customer</a></div>
            <div class="search-box">
                <form action="CustomerControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="cid" placeholder="Search Customer ID">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>        
        <table border = "1">
            <caption>${title}</caption>
            <tr>
                <th>CID</th>
                <th>Customer name</th>
                <th>Username</th>
                <th>Password</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <% for (Customer temp : vector) { %>
            <tr>
                <td><%=temp.getCid()%></td>
                <td><%=temp.getCname()%></td>
                <td><%=temp.getUsername()%> </td>
                <td><%=temp.getPassword()%> </td>
                <td><%=temp.getAddress()%> </td>
                <td><%=temp.getPhone()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable") %> </td>
                <td><a href="CustomerControllerMVC?go=update&cid=<%=temp.getCid()%>">Update</a></td>
                <td><a href="CustomerControllerMVC?go=delete&cid=<%=temp.getCid()%>">Delete</a></td>
            </tr>
            <%}%>    
         </table>
    </body>
</html>
