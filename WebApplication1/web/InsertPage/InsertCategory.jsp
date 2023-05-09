<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>InsertCategory</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Nav.css " type="text/css">
</head>
<body>
    <div class="topnav">
        <div><a href="../adminJSP/AdminIndex.jsp">Admin Menu</a></div>
        <div><a href="../CategoryControllerMVC">View All Category</a></div>
    </div>
    <form action = "../CategoryControllerMVC" method = "POST">
        <input type="hidden" name="go" value="insert">
        <table>
            <tr>
                <td><label for="cateName">Category Name</label></td>
                <td><input type="text" name="cateName" id = "cateName" required></td>
            </tr>
            <tr>
                <td><label for="status">status</label></td>
                <td>
                    <input type="radio" name="status" id = "status" value="1" checked > Enable
                    <input type="radio" name="status" id = "status" value="0" checked > Disable
                </td>
            </tr>
            <tr>
                <td><input type="submit" value="Insert Category" name="submit"></td>
                <td><input type="reset" value="Reset"></td>
            </tr>
        </table>
    </form>
    
</body>
</html>