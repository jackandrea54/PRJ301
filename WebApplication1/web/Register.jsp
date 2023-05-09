<%-- 
    Document   : Register
    Created on : Mar 7, 2023, 10:30:15 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Register Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/images/icons/favicon.ico"/>
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/bootstrap/css/bootstrap.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animate/animate.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/css-hamburgers/hamburgers.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/animsition/css/animsition.min.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/select2/select2.min.css">
        <!--===============================================================================================-->	
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.css">
        <!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/util.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
        <!--===============================================================================================-->
    </head>
    <body>

        <div class="limiter">
            <div class="container-login100" style="background-image: url('images/bg-01.jpg');">
                <div class="wrap-login100 p-l-110 p-r-110 p-t-62 p-b-33">
                    <form class="login100-form validate-form flex-sb flex-w" action="LoginRegisterServlet" method="POST" >
                        <input type="text" name="go" value="register" hidden>

                        <%
                            String msg = request.getParameter("msg"); 
                            if(msg != null){
                                out.print("<p style=\"color:red\">Register failed: wrong input register information or blank input</p>");
                            }
                        %>

                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Customer name
                            </span>
                        </div>        
                        <div class="wrap-input100 validate-input" data-validate = "Customer name is required">
                            <input class="input100" type="text" name="cname" required>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Username
                            </span>
                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Username is required">
                            <input class="input100" type="text" name="username" required>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Password
                            </span>

                        </div>

                        <div class="wrap-input100 validate-input" data-validate = "Password is required">
                            <input class="input100" type="text" name="pass" id="password" required>
                            <span class="focus-input100"></span>
                        </div>

                        <div class="p-t-13 p-b-9">
                            <span class="txt1">
                                Re-enter Password
                            </span>

                        </div>
                        <div class="wrap-input100 validate-input" data-validate = "Re-Password is required">
                            <input class="input100" type="text" name="re-pass" id="re-password" required>
                            <span class="focus-input100"></span>
                        </div>  

                        <span id='message' ></span>
                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Address
                            </span>
                        </div>        
                        <div class="wrap-input100 validate-input" data-validate = "Address is required">
                            <input class="input100" type="text" name="address" required>
                            <span class="focus-input100"></span>
                        </div>        

                        <div class="p-t-31 p-b-9">
                            <span class="txt1">
                                Phone
                            </span>
                        </div>        
                        <div class="wrap-input100 validate-input" data-validate = "Phone is required">
                            <input class="input100" type="text" pattern="0[1-9]+" name="phone" required>
                            <span class="focus-input100"></span>
                        </div>        



                        <div class="container-login100-form-btn m-t-17">
                            <button class="login100-form-btn" id="button">
                                Sign Up
                            </button>
                        </div>

                        <div class="w-full text-center p-t-55">
                            <span class="txt2">
                                Already a member?
                            </span>

                            <a href="Login.jsp" class="txt2 bo1">
                                Login now
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div id="dropDownSelect1"></div>

        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/jquery/jquery-3.2.1.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/animsition/js/animsition.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/bootstrap/js/bootstrap.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/select2/select2.min.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/daterangepicker/moment.min.js"></script>
        <script src="${pageContext.request.contextPath}/vendor/daterangepicker/daterangepicker.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/vendor/countdowntime/countdowntime.js"></script>
        <!--===============================================================================================-->
        <script src="${pageContext.request.contextPath}/js/main.js"></script>
        <script>
            $('#password, #re-password').on('keyup', function () {
                if ($('#password').val() == $('#re-password').val()) {
                    $('#message').html('Matching').css({'color': 'green', 'height': '1em', 'width': '100%'});
                } else
                    $('#message').html('Not Matching').css({'color': 'red', 'height': '1em', 'width': '100%'});
            });

        </script>

    </body>
</html>
