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
        <title>JSP Page</title>
    </head>
    <body>
        <%@ page import = "dao.DAOCustomer" %>
        <%@ page import = "entity.Customer, java.util.Vector" %>
        <% 
            DAOCustomer dao = new DAOCustomer();
            Vector<Customer> vector = dao.getCustomer("select * from Customer");
        %>    

        <table border = "1">
            <caption>CUSTOMER LIST</caption>
            <tr>
                <th>CID</th>
                <th>Customer name</th>
                <th>Username</th>
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
                <td><%=temp.getAddress()%> </td>
                <td><%=temp.getPhone()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable") %> </td>
                <td><a href="ViewCustomer.jsp?go=update&cid=" <%=temp.getCid()%>">Update</a></td>
                <td><a href="ViewCustomer.jsp?go=delete&cid=" <%=temp.getCid()%>">Delete</a></td>
            </tr>
            <%}%>    
    </body>
</html>
