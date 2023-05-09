<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>InsertBill</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
    </head>
    <body>
        <div class="topnav">
            <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
            <div><a href="../BillControllerMVC">View All Bill</a></div>
        </div>
        <form action="../BillControllerMVC"  method = "POST">
            <input type="hidden" name="go" value="insert">
            <table>
                <tr>
                    <td><label for="recAddress">Address</label></td>
                    <td><input type="text" name="recAddress" id = "recAddress" required></td>
                </tr>
                <tr>
                    <td><label for="recPhone">recPhone</label></td>
                    <td><input type="text" pattern="0[1-9]+" name="recPhone" id = "recPhone" required></td>
                </tr>
                <tr>
                    <td><label for="note">note</label></td>
                    <td><input type="text" name="note" id = "note" required></td>
                </tr>
                <tr>
                    <td><label for="status">status</label></td>
                    <td>
                        <input type="radio" name="status" id = "status" value="0" checked required> Wait
                        <input type="radio" name="status" id = "status" value="1" checked > Process
                        <input type="radio" name="status" id = "status" value="2" checked > Done
                    </td>
                </tr>
                <tr>
                    <td><label for="cid">Customer ID</label></td>
                    <td><input type="text" name="cid" id = "cid" required></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Insert Bill" name="submit"></td>
                    <td><input type="reset" value="Reset"></td>
                </tr>

            </table>
        </form>
    </body>
</html>