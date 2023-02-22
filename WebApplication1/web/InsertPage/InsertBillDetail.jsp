<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert Bill Detail</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
</head>
<body>
    <div class="topnav">
        <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
        <div><a href="../BillDetailControllerMVC">View All Bill Detail</a></div>
    </div>
    <form action="../BillDetailControllerMVC"  method = "POST">
        <input type="hidden" name="go" value="insert">
        <table>
            <tr>
                <td><label for="bid">Bill ID</label></td>
                <td><input type="text" name="bid" id = "bid"></td>
            </tr>
            <tr>
                <td><label for="pid">Product ID</label></td>
                <td><input type="text" name="pid" id = "pid"></td>
            </tr>
            <tr>
                <td><label for="buyQuantity">Quantity</label></td>
                <td><input type="text" name="buyQuantity" id = "buyQuantity"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Insert Bill Detail" name="submit"></td>
                <td><input type="reset" value="Reset"></td>
            </tr>
            
        </table>
    </form>
    
</body>
</html>