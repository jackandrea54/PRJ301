<%-- 
    Document   : Index
    Created on : Feb 21, 2023, 10:32:56 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
        <title>LANDING PAGE</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <meta name="keywords" content="Downy Shoes Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
              Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
        <script type="application/x-javascript">
            addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
            }, false);

            function hideURLbar() {
            window.scrollTo(0, 1);
            }
        </script>
        <!-- //custom-theme -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/shop.css" type="text/css" media="screen" property="" />
        <link href="${pageContext.request.contextPath}/css/style7.css" rel="stylesheet" type="text/css" media="all" />
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- font-awesome-icons -->
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
        <!-- //font-awesome-icons -->
        <link href="//fonts.googleapis.com/css?family=Montserrat:100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800"
              rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
    </head>

    <body>
        <!-- banner -->
        <div class="banner_top" id="home">
            <div class="wrapper_top_w3layouts">

                <div class="header_agileits">
                    <div class="logo">
                        <h1><a class="navbar-brand" href="./ClientIndex.jsp"><span>Downy</span> <i>Shoes</i></a></h1>
                    </div>
                    <div class="overlay overlay-contentpush">
                        <button type="button" class="overlay-close"><i class="fa fa-times" aria-hidden="true"></i></button>

                        <nav>
                            <ul>
                                <li><a href="./ClientIndex.jsp" class="active">Home</a></li>
                                <li><a href="../shop">Shop Now</a></li>
                            </ul>
                        </nav>
                    </div>
                    <div class="mobile-nav-button">
                        <button id="trigger-overlay" type="button"><i class="fa fa-bars" aria-hidden="true"></i></button>
                    </div>
                    <!-- cart details -->
                    <div class="top_nav_right">
                        <div class="shoecart shoecart2 cart cart box_1">
                            <input type="hidden" name="cmd" value="_cart">
                            <input type="hidden" name="display" value="1">
                            <a href="./checkout.jsp">
                                <button class="top_shoe_cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
                            </a>
                        </div>
                        <div class = "user-info" >
                            <%if (session.getAttribute("username") == null) {%>
                            <span>
                                <a href='../Login.jsp' style="color: white;">Login</a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='../Register.jsp' style="color: white;">Register</a> 
                            </span>

                            <%}else{%>
                            <span>
                                <a href="#" style="color: white;">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <%=session.getAttribute("username")%>
                                </a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='../LoginRegisterServlet?go=logout' style="color: white;">Logout</a> 
                            </span>            
                            <%}%>
                        </div>    
                    </div>
                    <!-- //cart details -->
                    <!-- search -->
                    <div class="search_w3ls_agileinfo">
                        <div class="cd-main-header">
                            <ul class="cd-header-buttons">
                                <li><a class="cd-search-trigger" href="#cd-search"> <span></span></a></li>
                            </ul>
                        </div>
                        <div id="cd-search" class="cd-search">
                            <form action="../shop" method="GET">
                                <input type="hidden" name="go" value="search">
                                <input type="search" placeholder="Click enter after typing..." name="pname" required="">
                            </form>
                        </div>
                    </div>
                    <!-- //search -->

                    <div class="clearfix"></div>
                </div>
                <!-- /slider -->
                <div class="slider">
                    <div class="callbacks_container">
                        <ul class="rslides callbacks callbacks1" id="slider4">

                            <li>
                                <div class="banner-top2">
                                    <div class="banner-info-wthree">
                                        <h3>Nike</h3>
                                        <p>See how good they feel.</p>

                                    </div>

                                </div>
                            </li>
                            <li>
                                <div class="banner-top3">
                                    <div class="banner-info-wthree">
                                        <h3>Heels</h3>
                                        <p>For All Walks of Life.</p>

                                    </div>

                                </div>
                            </li>
                            <li>
                                <div class="banner-top">
                                    <div class="banner-info-wthree">
                                        <h3>Sneakers</h3>
                                        <p>See how good they feel.</p>

                                    </div>

                                </div>
                            </li>
                            <li>
                                <div class="banner-top1">
                                    <div class="banner-info-wthree">
                                        <h3>Adidas</h3>
                                        <p>For All Walks of Life.</p>

                                    </div>

                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="clearfix"> </div>
                </div>
                <!-- //slider -->
                <ul class="top_icons">
                    <li><a href="#"><span class="fa fa-facebook" aria-hidden="true"></span></a></li>
                    <li><a href="#"><span class="fa fa-twitter" aria-hidden="true"></span></a></li>
                    <li><a href="#"><span class="fa fa-linkedin" aria-hidden="true"></span></a></li>
                    <li><a href="#"><span class="fa fa-google-plus" aria-hidden="true"></span></a></li>

                </ul>
            </div>
        </div>
        <!-- //banner -->
        <!-- /girds_bottom-->
        <div class="grids_bottom">
            <div class="style-grids">
                <div class="col-md-6 style-grid style-grid-1">
                    <img src="${pageContext.request.contextPath}/images/b1.jpg" alt="shoe">
                </div>
            </div>
            <div class="col-md-6 style-grid style-grid-2">
                <div class="style-image-1_info">
                    <div class="style-grid-2-text_info">
                        <h3>Nike DOWNSHIFTER</h3>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur.sed quia non numquam eius modi
                            tempora incidunt ut labore et dolore .</p>
                        <div class="shop-button">
                            <a href="../shop">Shop Now</a>
                        </div>
                    </div>
                </div>
                <div class="style-image-2">
                    <img src="${pageContext.request.contextPath}/images/b2.jpg" alt="shoe">
                    <div class="style-grid-2-text">
                        <h3>Air force</h3>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- //grids_bottom-->
    <!-- /girds_bottom2-->
    <div class="grids_sec_2">
        <div class="style-grids_main">
            <div class="col-md-6 grids_sec_2_left">
                <div class="grid_sec_info">
                    <div class="style-grid-2-text_info">
                        <h3>Sneakers</h3>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur.sed quia non numquam eius modi
                            tempora incidunt ut labore .</p>
                        <div class="shop-button">
                            <a href="../shop">Shop Now</a>
                        </div>
                    </div>
                </div>
                <div class="style-image-2">
                    <img src="${pageContext.request.contextPath}/images/b4.jpg" alt="shoe">
                    <div class="style-grid-2-text">
                        <h3>Air force</h3>
                    </div>
                </div>
            </div>
            <div class="col-md-6 grids_sec_2_left">

                <div class="style-image-2">
                    <img src="${pageContext.request.contextPath}/images/b3.jpg" alt="shoe">
                    <div class="style-grid-2-text">
                        <h3>Air force</h3>
                    </div>
                </div>
                <div class="grid_sec_info last">
                    <div class="style-grid-2-text_info">
                        <h3>Sneakers</h3>
                        <p>Itaque earum rerum hic tenetur a sapiente delectus reiciendis maiores alias consequatur.sed quia non numquam eius modi
                            tempora incidunt ut labore .</p>
                        <div class="shop-button two">
                            <a href="../shop">Shop Now</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="clearfix"></div>
        </div>
    </div>
    <!-- //grids_bottom2-->
    <!-- /Properties -->
    <div class="mid_slider_w3lsagile">
        <div class="col-md-3 mid_slider_text">
            <h5>Some More Product</h5>
        </div>
        <div class="col-md-9 mid_slider_info">
            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="2" class=""></li>
                    <li data-target="#myCarousel" data-slide-to="3" class=""></li>
                </ol>
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g1.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/samsung.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g3.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g4.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g5.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g6.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g2.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g1.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g1.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g2.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g3.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g4.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                        </div>
                    </div>
                    <div class="item">
                        <div class="row">
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g1.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g2.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g3.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                            <div class="col-md-3 col-sm-3 col-xs-3 slidering">
                                <div class="thumbnail"><img src="${pageContext.request.contextPath}/images/g4.jpg" alt="Image" style="max-width:100%;"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                    <span class="fa fa-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                    <span class="fa fa-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
                <!-- The Modal -->

            </div>
        </div>

        <div class="clearfix"> </div>
    </div>
    <!--//banner -->

    <!-- footer -->
    <div class="footer_agileinfo_w3">
        <div class="footer_inner_info_w3ls_agileits">
            <div class="col-md-3 footer-left">
                <h2><a href="index.html"><span>D</span>owny Products </a></h2>
                <p>Lorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora.</p>
                <ul class="social-nav model-3d-0 footer-social social two">
                    <li>
                        <a href="#" class="facebook">
                            <div class="front"><i class="fa fa-facebook" aria-hidden="true"></i></div>
                            <div class="back"><i class="fa fa-facebook" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="twitter">
                            <div class="front"><i class="fa fa-twitter" aria-hidden="true"></i></div>
                            <div class="back"><i class="fa fa-twitter" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="instagram">
                            <div class="front"><i class="fa fa-instagram" aria-hidden="true"></i></div>
                            <div class="back"><i class="fa fa-instagram" aria-hidden="true"></i></div>
                        </a>
                    </li>
                    <li>
                        <a href="#" class="pinterest">
                            <div class="front"><i class="fa fa-linkedin" aria-hidden="true"></i></div>
                            <div class="back"><i class="fa fa-linkedin" aria-hidden="true"></i></div>
                        </a>
                    </li>
                </ul>
            </div>
            <div class="col-md-9 footer-right">
                <div class="sign-grds">
                    <div class="col-md-4 sign-gd">
                        <h4>Our <span>Information</span> </h4>
                        <ul>
                            <li><a href="index.html">Home</a></li>
                            <li><a href="about.html">About</a></li>
                            <li><a href="404.html">Services</a></li>
                            <li><a href="404.html">Short Codes</a></li>
                            <li><a href="contact.html">Contact</a></li>
                        </ul>
                    </div>

                    <div class="col-md-5 sign-gd-two">
                        <h4>Store <span>Information</span></h4>
                        <div class="address">
                            <div class="address-grid">
                                <div class="address-left">
                                    <i class="fa fa-phone" aria-hidden="true"></i>
                                </div>
                                <div class="address-right">
                                    <h6>Phone Number</h6>
                                    <p>+1 234 567 8901</p>
                                </div>
                                <div class="clearfix"> </div>
                            </div>
                            <div class="address-grid">
                                <div class="address-left">
                                    <i class="fa fa-envelope" aria-hidden="true"></i>
                                </div>
                                <div class="address-right">
                                    <h6>Email Address</h6>
                                    <p>Email :<a href="mailto:piddfae@gmail.com"> piddfae@gmail.com</a></p>
                                </div>
                                <div class="clearfix"> </div>
                            </div>
                            <div class="address-grid">
                                <div class="address-left">
                                    <i class="fa fa-map-marker" aria-hidden="true"></i>
                                </div>
                                <div class="address-right">
                                    <h6>Location</h6>
                                    <p>Broome St, NY 10002,California, USA.

                                    </p>
                                </div>
                                <div class="clearfix"> </div>
                            </div>
                        </div>
                    </div>

                    <div class="clearfix"></div>
                </div>
            </div>
            <div class="clearfix"></div>

            <p class="copy-right-w3ls-agileits">&copy 2018 Downy Shoes. All rights reserved | Design by <a href="http://w3layouts.com/">W3layouts</a></p>
        </div>
    </div>
    <!-- //footer -->


    <a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>


    <%-- 
    <% 
        ResultSet rsMenu = (ResultSet) request.getAttribute("dataMenu");
    %>
    <h1><p>Your session ID is <%=session.getId()%>.</p></h1>
    <!--        <h1><p>Your Creation Time is <%= session.getCreationTime()%>.</p></h1>
    <h1><p>Your Last Accessed Time is <%= session.getLastAccessedTime()%>.</p></h1>-->
    <ul>
        <%while (rsMenu.next()) {%> 
        <li><a href="../shop?go=displayProductByCategory&cateID=<%=rsMenu.getInt(1)%>"> <%=rsMenu.getString(2)%> </a></li>
            <%}%>
    </ul>   
    <div>
        <jsp:include page="ViewProduct.jsp"></jsp:include>
    </div>
    comment --%>


    <!-- js -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <!-- //js -->
    <!-- /nav -->
    <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/classie.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo1.js"></script>
    <!-- //nav -->
    <!-- cart-js -->
    <script src="${pageContext.request.contextPath}/js/minicart.js"></script>
    <script>
        shoe.render();

        shoe.cart.on('shoe_checkout', function (evt) {
            var items, len, i;

            if (this.subtotal() > 0) {
                items = this.items();

                for (i = 0, len = items.length; i < len; i++) {
                }
            }
        });
    </script>
    <!-- //cart-js -->
    <!--search-bar-->
    <script src="${pageContext.request.contextPath}/js/search.js"></script>
    <!--//search-bar-->
    <script src="${pageContext.request.contextPath}/js/responsiveslides.min.js"></script>
    <script>
        $(function () {
            $("#slider4").responsiveSlides({
                auto: true,
                pager: true,
                nav: true,
                speed: 1000,
                namespace: "callbacks",
                before: function () {
                    $('.events').append("<li>before event fired.</li>");
                },
                after: function () {
                    $('.events').append("<li>after event fired.</li>");
                }
            });
        });
    </script>
    <!-- js for portfolio lightbox -->
    <!-- start-smoth-scrolling -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/move-top.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({
                    scrollTop: $(this.hash).offset().top
                }, 1000);
            });
        });
    </script>
    <!-- //end-smoth-scrolling -->

    <script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap-3.1.1.min.js"></script>
</body>
</html>
