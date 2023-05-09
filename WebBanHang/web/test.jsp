<%-- 
    Document   : test
    Created on : Mar 7, 2023, 12:22:12 PM
    Author     : LENOVO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${ite}" var="item">
            <h4>${item.productId}</h4>
            <h3>${item.quantity}</h3>
        </c:forEach>
    </body>
</html>
