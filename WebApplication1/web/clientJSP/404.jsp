<!--
author: W3layouts
author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html lang="zxx">

    <head>
        <title>Downy Shoes an Ecommerce Category Bootstrap Responsive Website Template | 404 :: w3layouts</title>
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
        <div class="banner_top innerpage" id="home">
            <div class="wrapper_top_w3layouts">
                <div class="header_agileits">
                    <div class="logo inner_page_log">
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
                            <%if (session.getAttribute("cname") == null) {%>
                            <span>
                                <a href='../Login.jsp' style="color: white;">Login</a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='../Register.jsp' style="color: white;">Register</a> 
                            </span>

                            <%}else{%>
                            <span>
                                <a href="./404.jsp" style="color: white;">
                                    <i class="fa fa-user" aria-hidden="true"></i>
                                    <%=session.getAttribute("cname")%>
                                </a>
                            </span>
                            <i style="color: white;">|</i> 
                            <span>
                                <a href='../LoginRegisterServlet?go=logout' style="color: white;">Logout</a> 
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
                    <form action="#" method="post">
                        <input name="Search" type="search" placeholder="Click enter after typing...">
                    </form>
                </div>
            </div>
            <!-- //search -->
            <div class="clearfix"></div>
            <!-- /banner_inner -->
            <div class="services-breadcrumb_w3ls_agileinfo">
                <div class="inner_breadcrumb_agileits_w3">

                    <ul class="short">
                        <li><a href="./ClientIndex.jsp" class="active">Home</a></li>
                        <li><a href="../shop">Shop Now</a></li>
                    </ul>
                </div>
            </div>
            <!-- //banner_inner -->
        </div>

        <!-- //banner -->
        <!-- top Products -->
        <div class="ads-grid_shop">
            <div class="shop_inner_inf">
                <div class="error_page">
                    <h4>404</h4>
                    <p>This link dead link</p>
                    <form action="../shop" method="GET">
                        <input type="hidden" name="go" value="search">
                        <input class="serch" type="search" name="pname" placeholder="Search here" required="">
                        <button class="btn1"><i class="fa fa-search" aria-hidden="true"></i></button>
                        <div class="clearfix"> </div>
                    </form>
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

                    </ul>
                    <a href="./ClientIndex.jsp" class="b-home">Back to Home</a>
                </div>
            </div>
        </div>
        <!-- footer -->
        <div class="footer_agileinfo_w3">
            <div class="footer_inner_info_w3ls_agileits">
                <div class="col-md-3 footer-left">
                    <h2><a href="./ClientIndex.jsp"><span>D</span>owny Shoes </a></h2>
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
                                <li><a href="./ClientIndex.jsp">Home</a></li>
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
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-2.1.4.min.js"></script>
    <!-- //js -->

    <!-- /nav -->
    <script src="${pageContext.request.contextPath}/js/modernizr-2.6.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/classie.js"></script>
    <script src="${pageContext.request.contextPath}/js/demo1.js"></script>
    <!-- //nav -->
    <!-- script for responsive tabs -->
    <script src="${pageContext.request.contextPath}/js/easy-responsive-tabs.js"></script>
    <script>
$(document).ready(function () {
    $('#horizontalTab').easyResponsiveTabs({
        type: 'default', //Types: default, vertical, accordion           
        width: 'auto', //auto or any width like 600px
        fit: true, // 100% fit in a container
        closed: 'accordion', // Start closed if in accordion view
        activate: function (event) { // Callback function if tab is switched
            var $tab = $(this);
            var $info = $('#tabInfo');
            var $name = $('span', $info);
            $name.text($tab.text());
            $info.show();
        }
    });
    $('#verticalTab').easyResponsiveTabs({
        type: 'vertical',
        width: 'auto',
        fit: true
    });
});
    </script>


    <!--search-bar-->
    <script src="${pageContext.request.contextPath}/js/search.js"></script>
    <!--//search-bar-->
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