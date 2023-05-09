<%-- 
    Document   : UpdateBillDetail
    Created on : Feb 22, 2023, 6:20:41 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Bill Detail</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <%@ page import="entity.BillDetail" %>
        <%
            BillDetail billDetail = (BillDetail)request.getAttribute("dataBillDe");
        %>
        <h1>UPDATE FOR BILL DETAIL</h1>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertBillDetail.jsp">Insert Bill Detail</a></div>
            <div><a href="BillDetailControllerMVC">View All Bill Detail</a></div>
        </div>
        <form action="BillDetailControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="bid">Bill ID</label></td>
                    <td><input type="text" name="bid" id = "bid" value = "<%= billDetail.getBid() %>" readonly></td>
                </tr>
                <tr>
                    <td><label for="pid">Product ID</label></td>
                    <td><input type="text" name="pid" id = "pid" value = "<%= billDetail.getPid() %>" readonly></td>
                </tr>
                <tr>
                    <td><label for="buyQuantity">Quantity</label></td>
                    <td><input type="number" name="buyQuantity" id = "buyQuantity" value = "<%= billDetail.getBuyQuantity() %>"></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Bill Detail" name="submit"></td>
                </tr>

            </table>
        </form>
    </body>
</html>
