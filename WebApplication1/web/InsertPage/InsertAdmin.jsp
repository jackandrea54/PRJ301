<%-- 
    Document   : InsertAdmin
    Created on : Feb 22, 2023, 5:35:35 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert Admin</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <div class="topnav">
            <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="../AdminControllerMVC">View All Admin</a></div>
        </div>
        <form action="../AdminControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="admin">Admin account</label></td>
                    <td><input type="text" name="admin" id = "admin"></td>
                </tr>
                <tr>
                    <td><label for="password">Password</label></td>
                    <td><input type="text" name="password" id = "password"></td>
                </tr>

                <tr>
                    <td><input type="submit" value="Insert Admin" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>

            </table>
        </form>

    </body>
</html>
