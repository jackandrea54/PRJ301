<%-- Document : ViewProduct Created on : Feb 16, 2023, 12:11:08 AM Author : ADMIN --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>

    <body>
        <%@ page import="entity.Product, java.sql.ResultSet" %>
        <% ResultSet rs=(ResultSet) request.getAttribute("dataPro"); String title=(String)
            request.getAttribute("title"); 
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertProduct.jsp">Insert Product</a></div>
            <div><a href="ProductControllerMVC">View All Product</a></div>
            <div class="search-box">
                <form action="ProductControllerMVC" method="GET">
                    <label>Search</label>
                    <input type="text" name="pname" placeholder="Search...">
                    <input type="submit" name="go" value="search">
                </form>
            </div>
        </div>
        <table border="1">
            <caption>Product list</caption>
            <tr>
                <th>Product ID</th>
                <th>Category</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price </th>
                <th>image</th>
                <th>Description</th>
                <th>Status</th>
                <th>Update</th>
                <th>Delete</th>
            </tr>
            <%while (rs.next()) {%>
            <tr>
                <td>
                    <%=rs.getString(1)%>
                </td>
                <td>
                    <%=rs.getString(10)%>
                </td>
                <td>
                    <%=rs.getString(2)%>
                </td>
                <td>
                    <%=rs.getString(3)%>
                </td>
                <td>
                    <%=rs.getString(4)%>
                </td>
                <td><img src="<%=rs.getString(5)%>" width="100" height="150"></td>
                <td>
                    <%=rs.getString(6)%>
                </td>
                <td>
                    <%=(rs.getInt(7)==1 ? "Enable" : "Disable" )%>
                </td>
                <td><a
                        href="ProductControllerMVC?go=update&pid=<%=rs.getString(1)%>&cateID=<%=rs.getInt(8)%>">Update</a>
                </td>
                <td><a href="ProductControllerMVC?go=delete&pid=<%=rs.getString(1)%>">Delete</a></td>
            </tr>
            <%}%>

        </table>
    </body>

</html>