
        <%@ page import = "entity.Product, java.sql.ResultSet" %>
        
        <%
            ResultSet rs = (ResultSet) request.getAttribute("dataPro");
            String title = (String) request.getAttribute("title");
        %>
        
        
        <table border="1">
            <caption>${title}</caption>
            <tr>
                <th>Product ID</th>
                <th>Category</th>
                <th>Product Name</th>
                <th>Quantity</th>
                <th>Price </th>
                <th>image</th>
                <th>Description</th>
                <th>Status</th>
                <th>Add to Cart</th>
            </tr>
            <%while (rs.next()) {%>
            <tr>
                <td><%=rs.getString(1)%></td>
                <td><%=rs.getString(10)%></td>
                <td><%=rs.getString(2)%></td>
                <td><%=rs.getString(3)%></td>
                <td><%=rs.getString(4)%></td>
                <td><img src= "<%=rs.getString(5)%>" width="100" height="150"></td>
                <td><%=rs.getString(6)%></td>
                <td><%=(rs.getInt(7) == 1 ? "Enable" : "Disable")%></td>
                <td><a href=./clientJSP/add2Cart.jsp?pid="<%=rs.getString(1)%>">Add</a></td>
            </tr>
            <%}%>
            <tr>
                <td><a href="ClientController">Product Menu</a></td>
            </tr>
        </table>   

