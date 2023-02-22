<%-- 
    Document   : Customer
    Created on : Feb 14, 2023, 10:25:40 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <!-- Scriplets -->
        <!<!-- Tat ca code scriplet sau khi duoc dich sang servlet se duoc cho sang chung 1 method
        "This code will be placed in the generated servlet method: _jspService()"-->
        
        <%int a = 10;
        out.print("a = " + a); 
        %>
        <%for(int i = 10; i<=100; i+=10){%>
            <hr width = "<%=i%>">
        <%}%>
        
        <!<!--Expressions -->
        <h2 style="color: red">a = <%=a%> </h2>
        
        <!<!-- Muon khai bao phuong thuc phai dung "%!" de no duoc day ra ben ngoai  
        neu su dung "%" thoi thi toan bo code do se duoc cho vao mot phuong thuc chung-->
        
        <!-- Declaration: Declares a variable or method valid in the scripting language used in the JSP page.  -->
        <%!
        public final double total(double x, double y){
            return x+y;
        }
        %> 
        
        <% double x = 10.2, y = 11.2;%>
        <h2> x + y =  <%=total(x,y)%></h2>
    </body>
</html>
