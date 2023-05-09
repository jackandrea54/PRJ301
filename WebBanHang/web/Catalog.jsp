<%-- 
    Document   : Catalog
    Created on : Mar 1, 2023, 1:08:15 AM
    Author     : LENOVO
--%>
<%@page import="DAL.DAO" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Catalog</title>
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

        <!--filter option -->
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-lg-3 sidebar-filter">
                    <h3 class="mt-5 mb-5">Found <span class="primary-color">${productList.size()}</span> Products</h3>

                    
                    <form action="CatalogFilter" onsubmit="return validateForm()" method="get">
                        <h6 class="text-uppercase">Collections</h6>
                        <c:forEach var="collection" items="${dao.collections}">
                            <div class="filter-checkbox">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="collection-${collection.id}" name="collection-${collection.id}">
                                    <label class="custom-control-label" for="collection-${collection.id}">${collection.name}</label>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="divider"></div>

                        <h6 class="text-uppercase">Categories</h6>
                        <c:forEach var="category" items="${dao.categories}">
                            <div class="filter-checkbox">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="category-${category.id}" name="category-${category.id}">
                                    <label class="custom-control-label" for="category-${category.id}">${category.name}</label>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="divider"></div>

                        <h6 class="text-uppercase">Colors</h6>
                        <c:forEach var="color" items="${dao.colors}">
                            <div class="filter-checkbox">
                                <div class="custom-control custom-checkbox">
                                    <input type="checkbox" class="custom-control-input" id="color-${color.id}" name="color-${color.id}">
                                    <label class="custom-control-label" for="color-${color.id}">${color.name}&nbsp;&nbsp;<div class="color-box" style="background-color: ${color.colorcode};"></div></label>
                                </div>
                            </div>
                        </c:forEach>
                        <div class="divider"></div>

                        <h6 class="text-uppercase">Price</h6>
                        <div class="price-filter">
                            <input type="number" class="form-control" placeholder="TỪ (đ)" name="price-min" id="price-min" min="0">
                            <br />
                            <input type="number" class="form-control" placeholder="ĐẾN (đ)" name="price-max" id="price-max" min="0">
                        </div>
                        <div class="divider"></div>
                        <input type="submit" class="btn btn-lg btn-full-width btn-primary mt-2" value="apply">
                        </div>
                    </form>

                    <!--/filter option-->

                    <!--product showcase-->            
                    <div class="col-md-8 col-lg-9">
                        <c:if test="${productList.size()!=0}">
                            <section class="products">
                                <div class="container">
                                    <div class="row sorting mb-5">
                                        <div class="col-12">
                                            <div class="dropdown float-left">
                                                <label class="mr-2">Sort by:</label>
                                                <a class="btn btn-lg btn-white dropdown-toggle" data-toggle="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sort==0 ? "Relevance" : sort==1 ? "Price Descending" : sort==2 ? "Price Ascending" : sort==3 ? "Best Selling":"Relevance"} <span class="caret"></span></a>
                                                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                                    <a class="dropdown-item" href="Catalog?sort=0">Relevance</a>
                                                    <a class="dropdown-item" href="Catalog?sort=1">Price Descending</a>
                                                    <a class="dropdown-item" href="Catalog?sort=2">Price Ascending</a>
                                                    <a class="dropdown-item" href="Catalog?sort=3">Best Selling</a>
                                                </div>
                                            </div>
                                            <!--choose page-->
                                            <div class="btn-group float-right ml-3">
                                                <c:if test="${page.index!=0}">
                                                    <!--previous button -->
                                                    <a class="btn btn-lg btn-white" href="Catalog?index=${page.index-1}&nrpp=${page.nrpp}&sort=${sort}"><span class="fa fa-arrow-left"></span></a>
                                                    </c:if>
                                                <!--choose page by num-->
                                                <c:forEach var="i" begin="${page.pageStart}" end = "${page.pageEnd}">
                                                    <a class="btn btn-lg btn-white" ${page.index==i?"style='background-color: #444342; color: white!important;'":""} href="Catalog?index=${i}&nrpp=${page.nrpp}&sort=${sort}">${i+1}</a>
                                                </c:forEach>
                                                <c:if test="${page.index<page.totalPage-1}">
                                                    <!--next button -->
                                                    <a class="btn btn-lg btn-white" href="Catalog?index=${page.index+1}&nrpp=${page.nrpp}&sort=${sort}"><span class="fa fa-arrow-right"></span></a>
                                                    </c:if>
                                            </div>
                                            <!--/choose page-->
                                            <!--                                    <div class="btn-group float-right ml-3">
                                                                                    <button type="button" class="btn btn-lg btn-white"><span class="fa fa-arrow-left"></span></button>
                                                                                    <button type="button" class="btn btn-lg btn-white"><span class="fa fa-arrow-right"></span></button>
                                                                                </div>-->

                                            <!--                                    <div class="dropdown float-right">
                                                                                    <label class="mr-2">View:</label>
                                                                                    <a class="btn btn-lg btn-white dropdown-toggle" data-toggle="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">12 <span class="caret"></span></a>
                                                                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown"><a class="dropdown-item" href="#">12</a>
                                                                                        <a class="dropdown-item" href="#">24</a>
                                                                                        <a class="dropdown-item" href="#">48</a>
                                                                                        <a class="dropdown-item" href="#">96</a></div>
                                                                                </div>-->
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="pro" items="${productList}" begin="${page.begin}" end = "${page.end}">
                                            <div class="col-md-6 col-lg-4 col-product">
                                                <figure>
                                                    <img class="rounded-corners img-fluid" src="assets/images/${pro.thumbnail}"	>
                                                    <figcaption>
                                                        <div class="thumb-overlay"><a href="Product?productName=${pro.name}" title="More Info">
                                                                <i class="fas fa-search-plus"></i>
                                                            </a></div>
                                                    </figcaption>
                                                </figure>
                                                <h4 class="mb-1"><a href="Product?productName=${pro.name}">${pro.name}</a></h4>
                                                <p><span class="emphasis">${pro.price}đ</span></p>
                                            </div>
                                        </c:forEach>               
                                    </div>
                                    <div class="row sorting mb-5">
                                        <div class="col-12">
                                            <!--choose page-->
                                            <div class="btn-group float-right ml-3">
                                                <c:if test="${page.index!=0}">
                                                    <!--previous button -->
                                                    <a class="btn btn-lg btn-white" href="Catalog?index=${page.index-1}&nrpp=${page.nrpp}&sort=${sort}"><span class="fa fa-arrow-left"></span></a>
                                                    </c:if>
                                                <!--choose page by num-->
                                                <c:forEach var="i" begin="${page.pageStart}" end = "${page.pageEnd}">
                                                    <a class="btn btn-lg btn-white" ${page.index==i?"style='background-color: #444342; color: white!important;'":""} href="Catalog?index=${i}&nrpp=${page.nrpp}&sort=${sort}">${i+1}</a>
                                                </c:forEach>
                                                <c:if test="${page.index<page.totalPage-1}">
                                                    <!--next button -->
                                                    <a class="btn btn-lg btn-white" href="Catalog?index=${page.index+1}&nrpp=${page.nrpp}&sort=${sort}"><span class="fa fa-arrow-right"></span></a>
                                                    </c:if>
                                            </div>
                                            <!--/choose page-->

                                            <!--                                    <div class="dropdown float-right">
                                                                                    <label class="mr-2">View:</label>
                                                                                    <a class="btn btn-white btn-lg dropdown-toggle" data-toggle="dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">12 <span class="caret"></span></a>
                                                                                    <div class="dropdown-menu" aria-labelledby="navbarDropdown"><a class="dropdown-item" href="#">12</a>
                                                                                        <a class="dropdown-item" href="#">24</a>
                                                                                        <a class="dropdown-item" href="#">48</a>
                                                                                        <a class="dropdown-item" href="#">96</a></div>
                                                                                </div>-->
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </c:if>
                    </div>
                </div>
            </div>
            <!--/product showcase-->            

            <!--        <div class="divider"></div>-->

            <!--        <section class="cta text-center">
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
                <!--            <div class="container">
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

            <!-- Placed at the end of the document so the pages load faster -->
            <script src="js/jquery-3.1.1.min.js"></script>
            <script src="bootstrap/js/bootstrap.min.js"></script>
            <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb" crossorigin="anonymous"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js" integrity="sha384-b/U6ypiBEHpOf/4+1nzFpr53nxSS+GLCkfwBdFNTxtclqqenISfwAzpKaMNFNmj4" crossorigin="anonymous"></script>

            <script>
                        function validateForm() {
                            let min = document.getElementById("price-min").value;
                            let max = document.getElementById("price-max").value;
                            if (min === "" || max === "") {
                                return true;
                            }
                            if (min >= max) {
                                alert("Khoảng giá tiền không hợp lệ.\nSố tiền bắt đầu phải nhỏ hơn số tiền kết thúc");
                                return false;
                            }
                            return true;
                        }


            </script>

    </body>
</html>
