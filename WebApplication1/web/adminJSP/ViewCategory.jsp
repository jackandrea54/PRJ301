<%-- 
    Document   : ViewCategory
    Created on : Feb 16, 2023, 12:21:46 AM
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
        <%@ page import = "entity.Category, java.util.Vector" %>
        <% 
           Vector<Category> vector = (Vector<Category>) request.getAttribute("dataCate");
           String title = (String) request.getAttribute("title");
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertCategory.jsp">Insert Category</a></div>
            <div><a href="CategoryControllerMVC">View All Category</a></div>
            <div class="search-box">
                <form action="CategoryControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="cateName" placeholder="Search Category Name">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>
        <table border = "1">
            <caption>${title}</caption>
            <tr>
                <th>Category ID</th>
                <th>Category name</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            
            <%for (Category temp : vector) {%>
            <tr>
                <td><%=temp.getCateID()%></td>
                <td><%=temp.getCateName()%> </td>
                <td><%=(temp.getStatus() == 1 ? "Enable" : "Disable")%></td>
                <td><a href="CategoryControllerMVC?go=update&cateID=<%=temp.getCateID()%>">Update</a></td>
                <td><a href="CategoryControllerMVC?go=delete&cateID=<%=temp.getCateID()%>">Delete</a></td>
            </tr>
            <%}%>
        </table>    

    </body>
</html>
