<%-- 
    Document   : AdminIndex
    Created on : Feb 21, 2023, 12:08:51 PM
    Author     : ADMIN
--%>
<%if (session.getAttribute("admin") == null) {
    out.print("YOU ARE NOT ALLOWED TO BE HERE, TURN BACK IMMEDIATELY, "
    + "\nWE HAVE CONTACT YOUR LOCAL OFFICAL AUTHORITY");
%>
<%}else{%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            a:link, a:visited {
                background-color: #f44336;
                color: white;
                padding: 14px 25px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
            }

            a:hover, a:active {
                background-color: red;
            }
        </style>
    </head>
    <body>
        <div style="display: flex; justify-content: space-evenly">
            <p>
                <a href="../AdminControllerMVC?go=listAll">Show Admin</a>
            </p>
            <p>
                <a href="../BillControllerMVC?go=listAll">Show Bill</a>
            </p>
            <p>
                <a href="../BillDetailControllerMVC?go=listAll">Show Bill Detail</a>
            </p>
            <p>
                <a href="../CategoryControllerMVC?go=listAll">Show Category</a>
            </p>
            <p>
                <a href="../CustomerControllerMVC?go=listAll">Show Customer </a>
            </p>
            <p>
                <a href="../ProductControllerMVC?go=listAll">Show Product </a>
            </p>
        </div>
    </body>
</body>
</html>
<%}%>