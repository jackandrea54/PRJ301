<%-- 
    Document   : Shop
    Created on : Mar 6, 2023, 10:52:52 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title> Shop Product</title>
        <!--===============================================================================================-->	
        <link rel="icon" type="image/png" href="https://previews.123rf.com/images/bsd555/bsd5551910/bsd555191001183/132900359-online-store-website-color-icon-marketplace-online-shopping-internet-trading-business-buying.jpg"/>
        <!--===============================================================================================-->
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
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/checkout.css">
        <!-- Owl-carousel-CSS -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui1.css">
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" media="all" />
        <!-- font-awesome-icons -->
        <link href="${pageContext.request.contextPath}/css/font-awesome.css" rel="stylesheet">
        <!-- //font-awesome-icons -->
        <link href="//fonts.googleapis.com/css?family=Montserrat:100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800"
              rel="stylesheet">
        <link href="//fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i,800" rel="stylesheet">
    </head>

    <body>
        <%@ page import = "entity.Product, java.sql.ResultSet" %>

        <%
            ResultSet rs = (ResultSet) request.getAttribute("dataPro");
            String title = (String) request.getAttribute("title");
        %>
        <!-- banner -->
        <div class="banner_top innerpage" id="home">
            <div class="wrapper_top_w3layouts">
                <div class="header_agileits">
                    <div class="logo inner_page_log">
                        <h1><a class="navbar-brand" href="./clientJSP/ClientIndex.jsp"><span>Downy</span> <i>Shoes</i></a></h1>
                    </div>
                    <div class="overlay overlay-contentpush">
                        <button type="button" class="overlay-close"><i class="fa fa-times" aria-hidden="true"></i></button>

                        <nav>
                            <ul>
                                <li><a href="./clientJSP/ClientIndex.jsp" class="active">Home</a></li>
                                <li><a href="shop">Shop Now</a></li>
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
                            <a href="./clientJSP/checkout.jsp">
                                <button class="top_shoe_cart" type="submit" name="submit" value=""><i class="fa fa-cart-arrow-down" aria-hidden="true"></i></button>
                            </a>
                        </div>
                        <div class = "user-info" >
                            <%if (session.getAttribute("cname") == null) {%>
                            <span>
                                <a href='./Login.jsp' style="color: white;">Login</a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='./Register.jsp' style="color: white;">Register</a> 
                            </span>

                            <%}else{%>
                            <span>
                                <a href="shop" style="color: white;">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <%=session.getAttribute("cname")%>
                                </a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='LoginRegisterServlet?go=logout' style="color: white;">Logout</a> 
                            </span>            
                            <%}%>
                        </div>
                    </div>
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
                    <form action="./shop" method="GET">
                        <input type="hidden" name="go" value="search">
                        <input type="search" placeholder="Click enter after typing..." name="pname" required="">
                    </form>
                </div>
            </div>
            <!-- //search -->
            <div class="clearfix"></div>
            <!-- /banner_inner -->
            <div class="services-breadcrumb_w3ls_agileinfo">
                <div class="inner_breadcrumb_agileits_w3">

                    <ul class="short">
                        <li><a href="./clientJSP/ClientIndex.jsp">Home</a><i>|</i></li>
                        <li><a href="shop">Shop</a></li>
                    </ul>
                </div>
            </div>
            <!-- //banner_inner -->
        </div>

        <!-- //banner -->
        <!-- top Products -->
        <div class="ads-grid_shop">
            <div class="shop_inner_inf">
                <!-- tittle heading -->

                <!-- //tittle heading -->
                <!-- product left -->
                <div class="side-bar col-md-3">
                    <div class="search-hotel">
                        <h3 class="agileits-sear-head">Search Here..</h3>
                        <form action="shop" method="GET">
                            <input type="hidden" name="go" value="search">
                            <input type="search" placeholder="Product name..." name="pname" required="">
                            <input type="submit" value=".">
                        </form>
                    </div>

                    <!--preference -->
                    <% 
                    ResultSet rsMenu = (ResultSet) request.getAttribute("dataMenu");
                    %>
                    <div class="left-side">
                        <h3 class="agileits-sear-head">Category</h3>
                        <form action="shop" method="GET">
                            <input type="hidden" name="go" value="displayProductByCategory">
                            <ul>
                                <%while (rsMenu.next()) {%> 
                                <li>
                                    <input type="checkbox" name="cateID" value="<%=rsMenu.getInt(1)%>">
                                    <span class="span"><%=rsMenu.getString(2)%></span>
                                </li>
                                <%}%>
                            </ul>
                            <div class="information-wrapper" style="margin-top: 5em;">
                                <button type="submit" class= "submit check_out">Filter</button>
                           </div>
                            
                        </form>
                    </div>
                    <!-- // preference -->




                </div>
                <!-- //product left -->
                <!-- product right -->
                <div class="left-ads-display col-md-9">
                    <div class="wrapper_top_shop">

                        <div class="clearfix"></div>
                        <!-- product-sec1 -->
                        <div class="product-sec1">
                            <!--/mens-->
                            <%while (rs.next()){%>
                            <%if(rs.getInt("status") == 1){%>
                            <div class="col-md-4 product-men">
                                <div class="product-shoe-info shoe" style="margin-top: 10px;">
                                    <div class="men-pro-item">
                                        <div class="men-thumb-item">
                                            <img src="<%=rs.getString(5)%>" alt="" style="height: 260px;">
                                            <div class="men-cart-pro">
                                                <div class="inner-men-cart-pro">
                                                    <a href="./clientJSP/single.jsp?pid=<%=rs.getString(1)%>" class="link-product-add-cart">Quick View</a>
                                                </div>
                                            </div>
                                            <span class="product-new-top">New</span>
                                        </div>
                                        <div class="item-info-product">
                                            <h4>
                                                <a href="./clientJSP/single.jsp?pid=<%=rs.getString(1)%>"><%=rs.getString(2)%></a>
                                            </h4>
                                            <div class="info-product-price">
                                                <div class="grid_meta">
                                                    <div class="product_price">
                                                        <div class="grid-price ">
                                                            <span class="money ">$<%=rs.getString(4)%></span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="shoe single-item hvr-outline-out">
                                                    <button type="submit" class="shoe-cart pshoe-cart" onclick="addToSession('<%=rs.getString(1)%>')">
                                                        <i class="fa fa-cart-plus" data-toggle="modal" data-target="#myModal1" style="color:white"></i>
                                                    </button>
                                                </div>
                                            </div>
                                            <div class="info-product-price" style="margin-top: 3em;">
                                                <div class="grid_meta" >
                                                    <div class="product_price">
                                                        <div class="grid-price ">
                                                            <span class="money "><%=rs.getString("description")%></span>
                                                        </div>
                                                    </div>
                                                </div>    
                                            </div>
                                            <div class="clearfix"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%}%>
                            <%}%>


                            <div class="clearfix"></div>

                        </div>

                        <!-- //product-sec1 -->
                        <div class="col-md-6 shop_left shp">
                            <img src="${pageContext.request.contextPath}/images/banner4.jpg" alt="">
                            <h6>21% off</h6>
                        </div>
                        <div class="col-md-6 shop_right shp">
                            <img src="${pageContext.request.contextPath}/images/banner1.jpg" alt="">
                            <h6>31% off</h6>
                        </div>
                        <div class="clearfix"></div>
                    </div>
                </div>
                <div class="clearfix"></div>
            </div>
        </div>
        <!-- //top products -->
        <div class="mid_slider_w3lsagile">
            <div class="col-md-3 mid_slider_text">
                <h5>Some More Product</h5>
            </div>
            <div class="col-md-9 mid_slider_info">
                <div id="myCarousel" class="carousel slide" data-ride="carousel">
                    <!-- Indicators -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="1" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="2" class=""></li>
                        <li data-target="#myCarousel" data-slide-to="3" class=""></li>
                    </ol>
                    <div class="carousel-inner" role="listbox">
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
                        <div class="item active">
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

        <!-- footer -->
        <div class="footer_agileinfo_w3">
            <div class="footer_inner_info_w3ls_agileits">
                <div class="col-md-3 footer-left">
                    <h2><a href="./clientJSP/ClientIndex.jsp"><span>D</span>owny Products </a></h2>
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
                                <li><a href="./clientJSP/ClientIndex.jsp">Home</a></li>
                            </ul>
                        </div>

                        <div class="col-md-5 sign-gd-two">
                            <h4>Web <span>Information</span></h4>
                            <div class="address">
                                <div class="address-grid">
                                    <div class="address-left">
                                        <i class="fa fa-phone" aria-hidden="true"></i>
                                    </div>
                                    <div class="address-right">
                                        <h6>Phone Number</h6>
                                        <p>+035 296 3942</p>
                                    </div>
                                    <div class="clearfix"> </div>
                                </div>
                                <div class="address-grid">
                                    <div class="address-left">
                                        <i class="fa fa-envelope" aria-hidden="true"></i>
                                    </div>
                                    <div class="address-right">
                                        <h6>Email Address</h6>
                                        <p>Email :<a href="mailto:example@email.com"> mail@example.com</a></p>
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

                <p class="copy-right-w3ls-agileits">&copy 2018 Downy Shoes. All rights reserved | Design by <a href="http://w3layouts.com/">w3layouts</a></p>
            </div>
        </div>
    </div>
    <!-- //footer -->
    <a href="#home" id="toTop" class="scroll" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
    <!-- js -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <!-- //js -->


    <!-- /nav -->
    <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/classie.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo1.js"></script>
    <!-- //nav -->
    <!--search-bar-->
    <script src="${pageContext.request.contextPath}/js/search.js"></script>
    <!--//search-bar-->
    <!-- price range (top products) -->
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <script>
                                                        //<![CDATA[ 
                                                        $(window).load(function () {
                                                            $("#slider-range").slider({
                                                                range: true,
                                                                min: 0,
                                                                max: 9000,
                                                                values: [50, 6000],
                                                                slide: function (event, ui) {
                                                                    $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
                                                                }
                                                            });
                                                            $("#amount").val("$" + $("#slider-range").slider("values", 0) + " - $" + $("#slider-range").slider("values", 1));

                                                        }); //]]>
    </script>
    <!-- //price range (top products) -->

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
    <!-- Add to session -->

    <script>
                                                        function addToSession(data) {
                                                            $.post("CartController?go=add", {"pid": data});
                                                        }
    </script>
    <!-- //Add to session -->

</body>
</html>
