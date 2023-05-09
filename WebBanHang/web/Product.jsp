<%-- 
    Document   : Product
    Created on : Mar 5, 2023, 12:32:16 AM
    Author     : LENOVO
--%>
<%@page import="DAL.DAO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>${dao.products.get(0).name}</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
        <link rel="shortcut icon" href="images/favicon.ico">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">		
        <script defer src="https://use.fontawesome.com/releases/v5.0.6/js/all.js"></script>
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

        <section class="featured-block text-center">
            <div class="container">
                <div class="row">
                    <!-- product img -->
                    <div class="col-md-6 text-center">
                        <div class="product-image mt-3">
                            <img class="img-fluid" id="frame" src="assets/images/${dao.products.get(0).thumbnail}">
                        </div>
                        <div class="product-thumbnails">
                            <c:forEach var="img" items="${dao.products}" step="5">
                                <a >
                                    <img class="mt-2 mr-2 img-fluid" src="assets/images/${img.thumbnail}" onclick="zoomout(this)"></a>
                                </c:forEach>
                        </div>
                    </div>
                    <!-- /product img -->
                    <!-- product detail -->
                    <div class="col-md-6 mt-5 mt-md-2 text-center text-md-left">
                        <h2 class="mb-3 mt-0">${dao.products.get(0).name}</h2>
                        <p class="lead mt-2 mb-3 primary-color">${dao.products.get(0).price}đ</p>
                        <h5 class="mt-4">Mô tả</h5>                        
                        <p>${dao.products.get(0).description}</p>
                        <!--add to cart-->
                        <form action="AddToCart" onsubmit="return confirmSubmit();" method="post" >
                            <input type="text" name="productName" style="display: none;" value="${dao.products.get(0).name}">
                            <input type="text" name="productPrice" style="display: none;" value="${dao.products.get(0).price}">
                            <div class="box-picker mb-4">
                                <label for="color" class="picker-label">Color:</label>
                                <div class="box-radios">
                                    <input type="radio" id="${dao.colors.get(0).name}" name="color" value="${dao.colors.get(0).id}" checked>
                                    <label for="${dao.colors.get(0).name}" class="boxes" style="background-color: ${dao.colors.get(0).colorcode}"></label>
                                    <c:forEach var="color" items="${dao.colors}" begin="1">
                                        <input type="radio" id="${color.name}" name="color" value="${color.id}">
                                        <label for="${color.name}" class="boxes" style="background-color: ${color.colorcode}"></label>
                                    </c:forEach>
                                </div>
                            </div>
                            <div class="size-picker box-picker mb-4">
                                <label for="size-picker" class="picker-label">Size:</label>
                                <select class="custom-select form-control" id="size-picker" name="size-picker" required="required">
                                    <c:forEach var="size" items="${dao.sizes}">
                                        <option value="${size.id}">${size.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="touchspin box-picker quan-picker mb-4">
                                <label for="quan" class="picker-label">Quantity:</label>
                                <div>
                                    <button type="button" class="btn btn-secondary" data-touchspin-down>-</button>
                                    <input type="number" data-touchspin-input style="text-align: center" id="quan" name="quantity" class="quan form-control" value="1" min="1">
                                    <button type="button" class="btn btn-secondary" data-touchspin-up>+</button>
                                </div>
                            </div>
                            <%if(!hasLogin){%>
                            <input class="btn btn-full-width btn-lg btn-outline-primary" type="button" onclick="denySubmit()" value="Add to cart">
                            <%}else{%>
                            <input class="btn btn-full-width btn-lg btn-outline-primary" type="submit" value="Add to cart">
                            <%}%>

                        </form>
                        <!--/add to cart-->

                    </div>
                    <!-- /product detail -->

                </div>
            </div>
        </div>
    </section>
    <div class="divider"></div>
    <!-- Related Products -->
    <section class="products text-center">
        <div class="container">
            <h3 class="mb-4">Related Products</h3>
            <div class="row">
                <c:forEach var="pro" items="${dao.productFilter}" begin="0" end="3">
                    <div class="col-sm-6 col-md-3 col-product">
                        <figure>
                            <img class="rounded-corners img-fluid" src="assets/images/${pro.thumbnail}"	width="240" height="240">
                            <figcaption>
                                <div class="thumb-overlay"><a href="Product?productName=${pro.name}" title="More Info">
                                        <i class="fas fa-search-plus"></i>
                                    </a></div>
                            </figcaption>
                        </figure>
                        <h4><a href="Product?productName=${pro.name}">${pro.name}</a></h4>
                        <p><span class="emphasis">${pro.price}đ</span></p>
                    </div>
                </c:forEach>
            </div>
        </div>
    </section>
    <!-- /Related Products -->

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
                </div>
        
                <div class="container-fluid">
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


    <script src="js/jquery-3.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>
    <script src="https://unpkg.com/@erwinstone/input-touchspin@1.0.3/dist/input-touchspin.min.js"></script>
    <script type="module">
                            import InputTouchspin from './path/to/input-touchspin.module.js'
    </script>
    <script>
                new InputTouchspin(document.querySelector('.touchspin'));
        function zoomout(obj) {
            var imgLink = obj.src;
            document.getElementById('frame').src = imgLink;
        }

        function confirmSubmit() {
            if (confirm("Số lượng của sản phẩm sẽ không thể thay đổi trong giỏ hàng (Nhưng vẫn có thể tăng số lượng sản phẩm khi thêm cùng sản phẩm vào giỏ hàng).\nBạn có muốn thêm vào giỏ hàng ?")) {
                return true;
            } else {
                return false;
            }
        }
        function denySubmit() {
            alert("Vui lòng đăng nhập tài khoản trước khi mua hàng.");
        }

    </script>


</body>
</html>