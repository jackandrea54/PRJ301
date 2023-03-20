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
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>  
        <!--<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>-->
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
                <form class="searchForm" action="BillControllerMVC" method="GET">
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
                <th>Details</th>
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
                <td>
                    <%= (temp.getStatus()== 0 ? "Wait" : (temp.getStatus()== 1 ? "Process" : "Done"))%>
<!--                    <form class="formUpdate" action="BillControllerMVC"  method = "POST">
                        <input type="hidden" name="go" value="update">
                        <input type="hidden" name="bid"  value ="<%=temp.getBid()%>">
                        <input type="hidden" name="recAddress"  value = "<%= temp.getRecAddress()%>">
                        <input type="hidden" name="recPhone"  value = "<%= temp.getRecPhone()%>">
                        <input type="hidden" name="note"  value = "<%= temp.getNote()%>">
                        <input type="hidden" name="cid"  value = "<%= temp.getCid()%>">
                        
                        <input type="radio" name="status"  value="0" <%= (temp.getStatus()== 0 ? "checked" : "")%>> Wait
                        <input type="radio" name="status"  value="1" <%= (temp.getStatus()== 1 ? "checked" : "")%>> Process
                        <input type="radio" name="status"  value="2" <%= (temp.getStatus()== 2 ? "checked" : "")%> > Done
                        <input type="submit" class="submit" name="submit" value = "update">
                    </form>-->
                </td>
                <td><%=temp.getCid()%></td>
                <td><a href="BillDetailControllerMVC?go=search&bid=<%=temp.getBid()%> ">Details</a></td>
                <td><a href="BillControllerMVC?go=update&bid=<%=temp.getBid()%> ">Update</a></td>
                <td>
                    <% if(temp.getStatus() == 0){%>
                    <a href="BillControllerMVC?go=delete&bid=<%=temp.getBid()%>">Delete</a>
                    <%}%>
                </td>
            </tr>
            <%}%>

        </table>   


        <script type='text/javascript'>
            $(document).ready(function () {
                $('.formUpdate input[type="radio"]').change(function () {
                    $(this).closest('.submit').submit;
                });
            });
        </script>
    </body>
</html>
