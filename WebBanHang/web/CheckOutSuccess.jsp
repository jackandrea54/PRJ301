<%-- 
    Document   : Home
    Created on : Feb 19, 2023, 11:35:36 PM
    Author     : LENOVO
--%>
<%@page import="DAL.DAO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Checkout</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">		
        <script src="https://kit.fontawesome.com/ad151c54e9.js" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="assets/css/style.css">
    </head>
    <body>

        <!--navbar-->
        <section class="header text-center">
            <nav class="navbar navbar-expand-lg navbar-light navbar-custom">
                <div class="container">
                    <!-- brand -->
                    <a class="navbar-brand" href="Home"><i class="fas fa-shopping-bag primary-color mr-1"></i> Tired</a>
                    <!-- /brand -->
                    <!-- responsive navbar -->
                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-1" aria-controls="navbar-1" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse pull-xs-right justify-content-end" id="navbar-1">
                        <ul class="navbar-nav mt-2 mt-md-0">
                            <li class="nav-item"><a class="nav-link" href="Home">Home <span class="sr-only">(current)</span></a></li>

                            <%  Cookie[] cookies = request.getCookies();// Get an array of cookies
                            DAO dao = (DAO)request.getAttribute("dao");
                            String username = "";
                             boolean hasLogin = false;
                             if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                    if (cookie.getName().equals("username")) { // Check if cookie exists
                                        username = cookie.getValue(); // Get the value of the cookie
                                        hasLogin = true;
                                }
                                }
                                }
                            %>
                            <%if(dao.loadRoleAccountByUsername(username)==1){%>
                            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Managements</a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="ManageProduct">Manage Products</a>
                                </div>
                            </li>
                            <%}%>
                            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Categories</a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:forEach var="category" items="${dao.categories}">
                                        <a class="dropdown-item" href="CatalogFilter?category-${category.id}=on">${category.name}</a>
                                    </c:forEach>
                                    <!--                                                                <div class="dropdown-divider"></div>
                                                                                                    <a class="dropdown-item" href="catalog.html">Catalog</a>
                                                                                                    <a class="dropdown-item" href="item.html">Item Detail</a>
                                                                                                    <a class="dropdown-item" href="cart.html">Cart</a>
                                                                                                    <div class="dropdown-divider"></div>
                                                                                                    <a class="dropdown-item" href="contact.html">Contact</a>-->
                                </div>
                            </li>                 <!--                            <li class="nav-item active">
                                <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                            </li>-->
                            <!--                            <li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Collections</a>
                                                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                                <a class="dropdown-item" href="index.html">Homepage</a>
                                                                <div class="dropdown-divider"></div>
                                                                <a class="dropdown-item" href="catalog.html">Catalog</a>
                                                                <a class="dropdown-item" href="item.html">Item Detail</a>
                                                                <a class="dropdown-item" href="cart.html">Cart</a>
                                                                <div class="dropdown-divider"></div>
                                                                <a class="dropdown-item" href="contact.html">Contact</a>
                                                            </div>
                                                        </li>-->
                            <!--                            <li class="nav-item"><a class="nav-link" href="contact.html">Contact</a></li>-->
                            <li class="nav-item">
                                <a class="nav-link" href="#">   
                                    <form action="CatalogSearch" method="get">
                                        <input type="text" name="searchbar" style="height: 1.5em; border: 2px #ccc solid" class="rounded" placeholder="Search" value="${searchbar}" required/>
                                        <button style="margin: 0; padding: 3px; margin-bottom: 5px;" class="btn icon-fallback-text"><i style="color: rgba(0,0,0,.5)" class=" fa-solid fa-magnifying-glass"></i></button>
                                    </form>
                                </a>
                            </li>

                            <%if(hasLogin){%>
                            <li class="nav-item" id="login"><a class="nav-link" href="#">Hi, <%= username %></a></li>
                            <li class="nav-item" id="logout"><a class="nav-link" href="LogoutControl">Logout</a></li>
                                <%
                                }else{
                                %>
                            <li class="nav-item" id="login"><a class="nav-link" href="Login">Login</a></li>
                                <%
                                        }
                                %>
                                <% if(hasLogin){%>
                            <li class="nav-item"><a class="nav-link" href="Cart"><i class="fa-solid fa-cart-shopping"></i></a></li>
                                    <%}else{%>
                            <li class="nav-item"><a class="nav-link" href="Login"><i class="fa-solid fa-cart-shopping"></i></a></li>
                                    <%}
                                    %>                        </ul>
                    </div>
                    <!-- /responsive navbar -->
                </div>
            </nav>
        </section>
        <!--/navbar-->

        <!-- product in cart -->
        <section class="text-center cart">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 mb-3 mb-m-1 text-md-left"><a href="Home"><i class="fas fa-arrow-left mr-2"></i> Continue Shopping</a></div>
                    <div class="col-sm-6 text-md-right"></div>
                </div>
                <div class="row">
                    <div class="col-12 text-center">
                        <h2 class="mt-5 mb-2">Purchase Successfully</h2>
                        <br><br>
                        <div class="col-sm-12 mt-3 mb-5 ">
                            <h3 class="">Your Order</h3>
                        </div>
                        <table id="cart" class="table table-condensed" >
                            <thead>
                                <tr>
                                    <th style="width:60%">Product</th>
                                    <th style="width:12%">Price</th>
                                    <th style="width:10%">Quantity</th>
                                    <th style="width:16%"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${cartItems.size()!=0}">
                                    <c:forEach items="${cart.items}" var="item">
                                        <tr>
                                            <td data-th="Product">
                                                <div class="row">
                                                    <div class="col-md-3 text-left">
                                                        <img src="assets/images/${products.get(item.value.productId).thumbnail}" alt="" class="img-fluid">
                                                    </div>
                                                    <div class="col-md-9 text-left mt-sm-2">
                                                        <h4>${item.value.name}</h4>
                                                        <p>Size: ${dao.sizes.get(products.get(item.value.productId).size_id-1).name}</p>
                                                    </div>
                                                </div>
                                            </td>
                                            <td data-th="Price">${item.value.pricePerItem}</td>
                                            <td data-th="Quantity">
                                                <!--                                        <input type="number" class="form-control text-center" value="1">-->
                                                <p>${item.value.quantity}</p>
                                            </td>
                                            <td class="actions" data-th="">
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    <!-- product in cart -->
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </section>

        <!-- form info-->
        <section>
            <div class="divider"></div>
            <div class="container">
                <div class="row contact-details">
                    <dl class="col-sm-8">

                        <div class="row">
                            <dt class="col-sm-3">Fullname</dt>
                            <dd class="col-sm-9">${order.fullname}</dd>

                            <dt class="col-sm-3">Email</dt>
                            <dd class="col-sm-9">${order.email}</dd>

                            <dt class="col-sm-3">Phone Number</dt>
                            <dd class="col-sm-9">${order.phone_number}</dd>

                            <dt class="col-sm-3">Address</dt>
                            <dd class="col-sm-9">${order.address}</dd>

                            <dt class="col-sm-3">Note</dt>
                            <dd class="col-sm-9">${order.note}</dd>

                    </dl>

                    <div class="col-sm-4 mb-5 text-center text-md-left">
                        <h3>Total Price:</h3>
                        <h1 class="pt-3 pb-3">${cart.getTotalPrice()}đ</h1>
                    </div>
                </div>

            </div>
        </section>

        <!--search bar -->
        <!--    <div class="divider"></div>
        
            <section class="cta text-center">
                <div class="container">
                    <h3 class="mt-0 mb-4">Sign up now to save 10% on your first order</h3>
                    <form class="subscribe">
                        <div class="form-group row pt-3">
                            <div class="col-sm-8 mb-3">
                                <input type="text" class="form-control" id="inputName" placeholder="Your Name">
                            </div>
                            <div class="col-sm-4">
                                <button type="submit" class="btn btn-lg btn-outline-primary">Sign me up</button>
                            </div>
                        </div>
                    </form>
                </div>
            </section>-->
        <!-- /search bar -->

        <footer class="footer">
            <!--        <div class="container">
                        <div class="row mb-5 text-center">
                            <div class="col-sm-6 col-md-3 pt-2">
                                <h5>Women's</h5>
                                <ul class="nav-footer">
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                </ul>
                            </div>
                            <div class="col-sm-6 col-md-3 pt-2">
                                <h5>Men's</h5>
                                <ul class="nav-footer">
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                </ul>
                            </div>
                            <div class="col-sm-6 col-md-3 pt-2">
                                <h5>Girl's</h5>
                                <ul class="nav-footer">
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                </ul>
                            </div>
                            <div class="col-sm-6 col-md-3 pt-2">
                                <h5>Boy's</h5>
                                <ul class="nav-footer">
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                    <li class="nav-item"><a class="nav-link" href="#">Link Item</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>-->


            <!--        <div class="container-fluid">
                        <div class="divider"></div>
                    </div>-->

            <div class="container">
                <div class="row">
                    <div class="col-md-6 text-center text-md-left mt-2 mb-3 pt-1">
                        <p class="copyright">Copyright &copy; Example. Theme by <a href="https://medialoot.com">Medialoot</a>.</p>
                    </div>
                    <div class="col-md-6 text-center text-md-right mb-4">
                        <ul class="social">
                            <li><a href="#" title="Facebook"><i class="fab fa-facebook-f"></i></a></li>
                            <li><a href="#" title="Twitter"><i class="fab fa-twitter"></i></a></li>
                            <li><a href="#" title="Google+"><i class="fab fa-google"></i></a></li>
                            <li><a href="#" title="Dribbble"><i class="fab fa-dribbble"></i></a></li>
                            <li><a href="#" title="Instagram"><i class="fab fa-instagram"></i></a></li>
                            <div class="clear"></div>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>					

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
        <script src="assets/js/myjs.js"></script>
    </body>
</html>
