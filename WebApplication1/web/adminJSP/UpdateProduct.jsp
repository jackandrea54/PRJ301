<%-- 
    Document   : UpdateProduct
    Created on : Feb 16, 2023, 8:49:11 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Category</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <%@ page import="java.sql.ResultSet,java.util.Vector,entity.Product" %>
        <%
            Vector<Product> vector = (Vector<Product>)request.getAttribute("dataPro");
            int cateID = (int) request.getAttribute("cateID");
            ResultSet rs = (ResultSet) request.getAttribute("dataCate");
            Product pro = vector.get(0);
        %>
        <div class="topnav">
            <div><a href="./adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="./InsertPage/InsertProduct.jsp">Insert Product</a></div>
            <div><a href="ProductControllerMVC">View All Product</a></div>
        </div>
        
        <form action="ProductControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="update">
            <table>
                <tr>
                    <td><label for="pid">Product ID</label></td>
                    <td><input type="text" name="pid" id = "pid" value="<%=pro.getPid()%>" readonly></td>
                </tr>
                <tr>
                    <td><label for="pname">Product Name</label></td>
                    <td><input type="text" name="pname" id = "pname" value="<%= pro.getPname()%>"></td>
                </tr>
                <tr>
                    <td><label for="quantity">quantity</label></td>
                    <td><input type="number" name="quantity" id = "quantity" value="<%= pro.getQuantity() %>"></td>
                </tr>
                <tr>
                    <td><label for="price">price</label></td>
                    <td><input type="number" step="0.1" name="price" id = "price" value="<%= pro.getPrice() %>"></td>
                </tr>
                <tr>
                    <td><label for="image">image</label></td>
                    <td><input type="text" name="image" id = "image" value="<%= pro.getImage() %>"></td>
                </tr>
                <tr>
                    <td><label for="description">description</label></td>
                    <td><input type="text" name="description" id = "description" value="<%= pro.getDescription() %>"></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id="" value="1"  <%=(pro.getStatus() == 1 ? "checked" : "")%>>Enable
                        <input type="radio" name="status" id="" value="0"  <%=(pro.getStatus() == 0 ? "checked" : "")%>>Disable
                    </td>
                </tr>
                <tr>
                    <td><label for="cateID">Category</label></td>
                    <td>
                        <select name="cateID" id="cateID">);
                            <%while (rs.next()) {%>
                            <option value="<%=rs.getInt(1)%>" <%=((rs.getInt(1) == cateID) ? "selected" : "") %>> <%= rs.getString(2) %> </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Update Product" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
            </table>
        </form>
    </body>
</html>
