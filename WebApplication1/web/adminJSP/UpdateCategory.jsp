<%-- 
    Document   : UpdateCategory
    Created on : Feb 20, 2023, 11:16:27 PM
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
        <%@ page import = "entity.Category" %>
        <%
            Category cate = (Category) request.getAttribute("dataCate");
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertCategory.jsp">Insert Category</a></div>
            <div><a href="CategoryControllerMVC">View All Category</a></div>
        </div>
        <form action = "CategoryControllerMVC" method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="cateID">Category ID</label></td>
                    <td><input type="text" name="cateID" id = "cateID" value = "<%= cate.getCateID()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="cateName">Category ID</label></td>
                    <td><input type="text" name="cateName" id = "cateName" value = "<%= cate.getCateName()%>"></td>
                </tr>        
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" <%= (cate.getStatus() == 1 ? "checked" : "")%> > Enable
                        <input type="radio" name="status" id = "status" value="0" <%= (cate.getStatus() == 0 ? "checked" : "")%>  > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Category" name="submit"></td>
                </tr>
            </table>

        </form>
    </body>
</html>
