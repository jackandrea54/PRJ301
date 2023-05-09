<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="UTF-8">
    <title>Registration</title>
    <link rel="stylesheet" href="assets/css/register.css">
     <meta name="viewport" content="width=device-width, initial-scale=1.0">
   </head>
<body>
  <div class="container">
    <div class="title">Registration</div>
    <br>
    <div style="color: red">${error}</div>
    <div class="content">
        <form action="Register" method="post">
        <div class="user-details">
          <div class="input-box">
            <span class="details">Full Name</span>
            <input type="text" name="fullname" placeholder="Enter your name" value="${fullnameError}" required>
          </div>
          <div class="input-box">
            <span class="details">Username</span>
            <input type="text" name="username" placeholder="Enter your username" value="${usernameError}" required>
          </div>
          <div class="input-box">
            <span class="details">Email</span>
            <input type="email" name="email" placeholder="Enter your email" value="${emailError}" required>
          </div>
          <div class="input-box">
            <span class="details">Phone Number</span>
            <input type="tel" name="phoneNumber" placeholder="Enter your phone number" name="phone" value="${phoneNumberError}" required>
          </div>
          <div class="input-box">
            <span class="details">Password</span>
            <input type="password" name="password" placeholder="Enter your password" required>
          </div>
          <div class="input-box">
            <span class="details">Confirm Password</span>
            <input type="password" name="confirmPass" placeholder="Confirm your password" required>
          </div>
        </div>
        
        <div class="button">
          <input type="submit" value="Register">
        </div>
        <div>
          <p>Already have account ? Login <a href="Login">here</a></p>
        </div>
      </form>
    </div>
  </div>
</form>
</body>

</html>
