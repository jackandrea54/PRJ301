<%-- 
    Document   : UpdateBill
    Created on : Feb 20, 2023, 4:35:00 PM
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
        <%@ page import="entity.Bill" %>
        <%
            Bill bill = (Bill)request.getAttribute("dataBill");
        %>
        <h1>UPDATE FOR BILL</h1>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertBill.jsp">Insert Bill</a></div>
            <div><a href="BillControllerMVC">View All Bill</a></div>
        </div>
        <form action="BillControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="bid">Bill ID</label></td>
                    <td><input type="text" name="bid" id = "bid" value = "<%= bill.getBid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="recAddress">Address</label></td>
                    <td><input type="text" name="recAddress" id = "recAddress" value = "<%= bill.getRecAddress()%>" required></td>
                </tr>
                <tr>
                    <td><label for="recPhone">Phone number</label></td>
                    <td><input type="text" pattern="0[1-9]+" name="recPhone" id = "recPhone" value = "<%= bill.getRecPhone()%>" required></td>
                </tr>
                <tr>
                    <td><label for="note">Note</label></td>
                    <td><input type="text" name="note" id = "note" value = "<%= bill.getNote()%>" required></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <% if(bill.getStatus() != 2){%>
                        <input type="radio" name="status" id = "status" value="0" <%= (bill.getStatus()== 0 ? "checked" : "")%>> Wait
                        <input type="radio" name="status" id = "status" value="1" <%= (bill.getStatus()== 1 ? "checked" : "")%>> Process
                        <%}%>
                        <input type="radio" name="status" id = "status" value="2" <%= (bill.getStatus()== 2 ? "checked" : "")%> > Done
                    </td>
                </tr>
                <tr>
                    <td><label for="cid">Customer ID</label></td>
                    <td><input type="text" name="cid" id = "cid" value = "<%= bill.getCid()%>" readonly></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Bill" name="submit"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
