<!DOCTYPE html>

<html>
    <head>
        <title>InsertCustomer</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <div class="topnav">
            <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="../CustomerControllerMVC">View All Customer</a></div>
        </div>
        <form action = "../CustomerControllerMVC" method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="cname">Customer Name</label></td>
                    <td><input type="text" name="cname" id = "cname" required></td>
                </tr>
                <tr>
                    <td><label for="username">username</label></td>
                    <td><input type="text" name="username" id = "username" required></td>
                </tr>
                <tr>
                    <td><label for="password">password</label></td>
                    <td><input type="text" name="password" id = "password" required></td>
                </tr>
                <tr>
                    <td><label for="address">address</label></td>
                    <td><input type="text" name="address" id = "address" required></td>
                </tr>
                <tr>
                    <td><label for="phone">phone</label></td>
                    <td><input type="text" pattern="0[1-9]+" name="phone" id = "phone" required></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="1" checked > Enable
                        <input type="radio" name="status" id = "status" value="0" checked > Disable
                    </td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Customer" name="submit"></td>
                    <td><input type="reset" value="reset"></td>
                </tr>
            </table>
            
            
        </form>
        

        
    </body>
</html>
