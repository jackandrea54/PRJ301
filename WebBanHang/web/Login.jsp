<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">

    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <!---<title> Responsive Registration Form | CodingLab </title>--->
        <link rel="stylesheet" href="assets/css/login.css">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>

    <body>
        <div class="container">
            <div class="title">Login</div>
            <br>
            <div style="color: red;">${error}</div>
            <div class="content">
                <form action="Login" method="post">
                    <div class="user-details">
                        <div class="input-box">
                            <span class="details">Username</span>
                            <input type="text" name="username" placeholder="Enter your username" value= "${userWrong}" required>
                        </div>
                        <div class="input-box">
                            <span class="details">Password</span>
                            <input type="password" name="password" placeholder="Enter your password" required>
                        </div>
                    </div>

                    <div class="button">
                        <input type="submit" value="Login">
                    </div>
                    <div>
                        <p>Don't have account ? Register <a href="Register">here</a></p>
                    </div>
                </form>
            </div>
        </div>
    </form>

</body>

</html>