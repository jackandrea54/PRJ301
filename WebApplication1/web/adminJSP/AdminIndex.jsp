<%if (session.getAttribute("admin")==null) { 
    out.print("YOU ARE NOT ALLOWED TO BE HERE, TURN BACK IMMEDIATELY, WE HAVE CONTACT YOUR LOCAL OFFICAL AUTHORITY"); %>
    <%}else{%>
        <%@page contentType="text/html" pageEncoding="UTF-8" %>
            <!DOCTYPE html>
            <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title>JSP Page</title>
                <style>
                    a:link,
                    a:visited {
                        background-color: #f44336;
                        color: white;
                        padding: 14px 25px;
                        text-align: center;
                        text-decoration: none;
                        display: inline-block;
                    }

                    a:hover,
                    a:active {
                        background-color: orange;
                    }
                </style>
            </head>

            <body style="background-color: black">
                <h1>
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <%=session.getAttribute("username")%>
                </h1>
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
                    <span>
                        <a href='../LoginRegisterServlet?go=logout' style="color: white;">Logout</a>
                    </span>
                </div>


            </body>
            </body>

            </html>
            <%}%>