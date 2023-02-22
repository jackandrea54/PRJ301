<%-- 
    Document   : ViewBill
    Created on : Feb 15, 2023, 11:58:15 PM
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
        <%@ page import = "dao.DAOBill,entity.Bill,java.util.Vector" %>
        
        <% 
           Vector<Bill> vector = (Vector<Bill>) request.getAttribute("dataBill");
           String title = (String) request.getAttribute("title");
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertBill.jsp">Insert Bill</a></div>
            <div><a href="BillControllerMVC">View All Bill</a></div>
            <div class="search-box">
                <form action="BillControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="bid" placeholder="Search bill ID...">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>
        <table border = "1">
            <caption>${title}</caption>
            <tr>
                <th>Bill ID</th>
                <th>Date Create</th>
                <th>Address</th>
                <th>Phone</th>
                <th>Note</th>
                <th>Total Money</th>
                <th>Status</th>
                <th>Customer ID</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            
            <%for (Bill temp : vector) {%>
            <tr>
                <td><%=temp.getBid()%></td>
                <td><%=temp.getDateCreate()%></td>
                <td><%=temp.getRecAddress()%></td>
                <td><%=temp.getRecPhone()%></td>
                <td><%=temp.getNote()%></td>
                <td><%=temp.getTotalMoney()%></td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%></td>
                <td><%=temp.getCid()%></td>
                <td><a href="BillControllerMVC?go=update&bid=<%=temp.getBid()%> ">Update</a></td>
                <td><a href="BillControllerMVC?go=delete&bid=<%=temp.getBid()%> ">Delete</a></td>
            </tr>
            <%}%>
  
        </table>               
    </body>
</html>
