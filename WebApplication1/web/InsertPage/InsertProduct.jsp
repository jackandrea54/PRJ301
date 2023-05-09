<%-- 
    Document   : InsertProduct
    Created on : Feb 21, 2023, 11:50:59 AM
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
        <%@ page import="dao.DAOProduct,dao.DAOCategory,java.sql.ResultSet,java.util.Vector,entity.Product,entity.Category" %>
        <%
            DAOProduct daoPro = new DAOProduct();
            DAOCategory daoCate = new DAOCategory();
            Vector<Product> vectorPro = daoPro.getAllProduct();
            Vector<Category> vectorCate = daoCate.getAllCategory();
        %>
        <div class="topnav">
            <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="../ProductControllerMVC">View All Product</a></div>
        </div>
        <form action="../ProductControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="pid">Product ID</label></td>
                    <td><input type="text" name="pid" id = "pid" required></td>
                </tr>
                <tr>
                    <td><label for="pname">Product Name</label></td>
                    <td><input type="text" name="pname" id = "pname" required></td>
                </tr>
                <tr>
                    <td><label for="quantity">quantity</label></td>
                    <td><input type="number" name="quantity" id = "quantity" min="1" required></td>
                </tr>
                <tr>
                    <td><label for="price">price</label></td>
                    <td><input type="number" step="0.1" name="price" id = "price" required></td>
                </tr>
                <tr>
                    <td><label for="image">image</label></td>
                    <td><input type="text" name="image" id = "image" required></td>
                </tr>
                <tr>
                    <td><label for="description">description</label></td>
                    <td><input type="text" name="description" id = "description" required></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" checked > Enable
                        <input type="radio" name="status" id = "status" value="0" checked > Disable
                    </td>
                </tr>
                <tr>
                    <td><label for="cateID">Category</label></td>
                    <td>
                        <select name="cateID" id="cateID">);
                            <%for(Category cate : vectorCate) {%>
                            <option value="<%=cate.getCateID()%>"> <%= cate.getCateName() %> </option>
                            <%}%>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Product" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>
                
            </table>
        </form>
    </body>
</html>
