<%-- 
    Document   : ViewAdmin
    Created on : Feb 15, 2023, 5:26:46 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "dao.DAOAdmin" %>
<%@ page import = "entity.Admin, java.util.Vector" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>

        <% 
            Vector<Admin> vector = (Vector<Admin>) request.getAttribute("dataAdmin");
            String title = (String) request.getAttribute("title");
        %>    
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertAdmin.jsp">Insert Admin</a></div>
            <div><a href="AdminControllerMVC">View All Admin</a></div>
            <div class="search-box">
                <form action="AdminControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="admin" placeholder="Search...">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>
        <table border = "1">
            <caption>${title}</caption>
            <tr>
                <th>ADMIN</th>
                <th>PASSWORD</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>

            <%for (Admin admin : vector) {%>
            <tr>
                <td><%=admin.getAdmin()%></td>
                <td><%=admin.getPassword()%></td>
                <td><a href="AdminControllerMVC?go=update&admin=<%=admin.getAdmin()%>">Update</a></td>
                <td><a href="AdminControllerMVC?go=delete&admin=<%=admin.getAdmin()%>">Delete</a></td>
            </tr>
        </tr>
        <%}%> 
      
    </table>
</body>
</html>
