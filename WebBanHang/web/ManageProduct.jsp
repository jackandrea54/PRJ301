<%-- 
    Document   : Home
    Created on : Feb 19, 2023, 11:35:36 PM
    Author     : LENOVO
--%>
<%@page import="DAL.DAO" %>
<%@page import="Models.*" %>
<%@page import="java.util.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Manage Products</title>
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


        <!-- search bar -->
        <section>
            <div class="container">
                <form action="ManageProductSearch" method="get" >
                    <div class="form-group row pt-5">
                        <div class="btn col-sm-2"><p>Search By:</p></div>
                        <select class="custom-select form-control col-sm-2" id="searchOption" name="searchOption" required="required">
                            <option value="0">Id</option>
                            <option value="1">Name</option>
                        </select>
                        <div class="col-sm-5">
                            <input type="number" class="form-control" id="searchText" name="searchText" placeholder="" min="1" max="${dao.loadNumberOfProducts()}">
                        </div>
                        <div class="col-sm-3">
                            <button type="submit" class="btn btn-lg btn-outline-primary">Search</button>
                        </div>
                    </div>
                </form>
            </div>
        </section>
        <!-- /search bar -->

        <!-- navigator -->
        <% PageView p = (PageView)request.getAttribute("page");
        
        int idUpdate =0;
        try{
            idUpdate = Integer.parseInt(request.getAttribute("update")+"");
            }catch(Exception e){
            idUpdate =0;
            }
        Product st = null;%>

        <div class="container d-flex justify-content-between">
            <h4 class="">Found <span class="primary-color">${dao.products.size()}</span> Products</h4>
            <%if(!dao.getProducts().isEmpty()){%>
            <h4 class="">Page: <span class="primary-color">${page.index+1}</span>/${page.totalPage}</h4>
            <%}%>
        </div>

        <%if(!dao.getProducts().isEmpty()){%>
        <div class="divider"></div>

        <div class="container text-center pb-3">

            <div class="btn-group">
                <c:if test="${page.index!=0}">
                    <a class="btn btn-lg btn-white" href ='ManageProduct?index=${0}&nrpp=${page.nrpp}'>Home</a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index-1}&nrpp=${page.nrpp}"><span class="fa fa-arrow-left"></span></a>   
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index-5}&nrpp=${page.nrpp}">-5</a>
                </c:if>
                <c:forEach var="i" begin="${page.pageStart}" end = "${page.pageEnd}">
                    <a class="btn btn-lg btn-white" ${page.index==i?"style='background-color: #444342; color: white!important;'":""} href="ManageProduct?index=${i}&nrpp=${page.nrpp}">${i+1}</a>
                </c:forEach>
                <c:if test="${page.index<page.totalPage-1}">
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index+5}&nrpp=${page.nrpp}">+5</a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index+1}&nrpp=${page.nrpp}"><span class="fa fa-arrow-right"></span></a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.totalPage-1}&nrpp=${page.nrpp}">End</a>
                </c:if>
            </div>
        </div>
        <%}%>
        <!-- /navigator -->


        <!-- Display product info -->
        <%if(!dao.getProducts().isEmpty()){%>
        <table class="table">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Category</th>
                    <th scope="col">Collection</th>
                    <th scope="col">Color</th>
                    <th scope="col">Size</th>
                    <th scope="col">Discount</th>
                    <th scope="col">Thumbnail</th>
                    <th scope="col">Description</th>
                    <th scope="col">Status</th>
                    <th scope="col">Create at</th>
                    <th scope="col"></th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <% for (int i = p.getBegin(); i <= p.getEnd();i++) {
                Product pro = dao.getProducts().get(i);
    if(pro.getId()==idUpdate)
        st = pro;
                %>
                <tr>
                    <th scope="row"><%=pro.getId()%></th>
                    <td><%=pro.getName()%></td>
                    <td><%=pro.getPrice()%> </td>
                    <td><%=dao.getCategories().get(pro.getCategory_id()-1).getName()%></td>
                    <td>
                        <%=dao.getCollections().get(pro.getCollection_id()-1).getName()%></td>
                    <td><%=dao.getColors().get(pro.getColor_id()-1).getName()%></td>
                    <td><%=dao.getSizes().get(pro.getSize_id()-1).getName()%></td>
                    <td><%=pro.getDiscount()%></td>
                    <td><%=pro.getThumbnail()%></td>
                    <td><%=pro.getDescription().length()<51?pro.getDescription():pro.getDescription().substring(0,50)+"..."%> </td>
                    <td><%=dao.getStats().get(pro.getStatus()-1).getName()%></td>
                    <td><%=pro.getCreate_at()%></td>
                    <td><a href='ManageProduct?type=0&id=<%=pro.getId()%>&index=${page.index}&nrpp=${page.nrpp}'>Update</a></td>
                    <td><a href='ManageProduct?type=1&id=<%=pro.getId()%>&index=${page.index}&nrpp=${page.nrpp}' onclick="return confirmNotification(0)">Delete</a></td>
                </tr>
                <%}
                %>
            </tbody>
        </table>
        <!-- Display product info -->


        <hr/>
        <div class="container text-center">
            <div class="btn-group">
                <c:if test="${page.index!=0}">
                    <a class="btn btn-lg btn-white" href ='ManageProduct?index=${0}&nrpp=${page.nrpp}'>Home</a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index-1}&nrpp=${page.nrpp}"><span class="fa fa-arrow-left"></span></a>   
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index-5}&nrpp=${page.nrpp}">-5</a>
                </c:if>
                <c:forEach var="i" begin="${page.pageStart}" end = "${page.pageEnd}">
                    <a class="btn btn-lg btn-white" ${page.index==i?"style='background-color: #444342; color: white!important;'":""} href="ManageProduct?index=${i}&nrpp=${page.nrpp}">${i+1}</a>
                </c:forEach>
                <c:if test="${page.index<page.totalPage-1}">
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index+5}&nrpp=${page.nrpp}">+5</a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.index+1}&nrpp=${page.nrpp}"><span class="fa fa-arrow-right"></span></a>
                    <a class="btn btn-lg btn-white" href="ManageProduct?index=${page.totalPage-1}&nrpp=${page.nrpp}">End</a>
                </c:if>
            </div>
        </div>
        <%}%>

        <div class="divider"></div>

        <!-- input form -->
        <div class="container">
            <form id="manageProductForm" action="ManageProduct" method="post">
                <input type="number" name="id" value="<%=(st==null?0:st.getId())%>" style="display: none;">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" placeholder="Name" name="name" value="<%=(st==null?"":st.getName())%>" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="price">Price</label>
                        <input type="number" class="form-control" id="price" name="price" placeholder="Price" value="<%=(st==null?"":st.getPrice())%>" min="0" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-3">
                        <label for="category">Category</label>
                        <select id="category" class="form-control" name="category">
                            <% for (Category cat : dao.getCategories())
                            {%>
                            <option value=<%=cat.getId()%>
                                    <%=st==null?"":(st.getCategory_id()==cat.getId()?"selected":"")
                                    %>        ><%=cat.getName()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="collection">Collection</label>
                        <select id="collection" class="form-control" name="collection">
                            <% for (Models.Collection collect : dao.getCollections())
                            {%>
                            <option value=<%=collect.getId()%>
                                    <%=st==null?"":(st.getCollection_id()==collect.getId()?"selected":"")
                                    %>        ><%=collect.getName()%></option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="color">Color</label>
                        <select id="color" class="form-control" name="color">
                            <% for (Color col : dao.getColors())
                            {%>
                            <option value=<%=col.getId()%>
                                    <%=st==null?"":(st.getColor_id()==col.getId()?"selected":"")
                                    %>        ><%=col.getName()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-5">
                        <p>Size</p>
                        <% for (Size si : dao.getSizes())
                            {%>
                        <div class="custom-control custom-checkbox custom-control-inline">
                            <input class="custom-control-input" type="checkbox" id="<%=si.getId()%>" name="size-<%=si.getId()%>" value="<%=si.getId()%>" <%=st==null?"":(st.getSize_id()==si.getId()?"checked":"")%>>
                            <label class="custom-control-label" for="<%=si.getId()%>"><%=si.getName()%></label>
                        </div>
                        <%}%>
                        <button id="checkAllButton" class="btn btn-primary" type="button">Choose all</button>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="discount">Discount</label>
                        <input type="number" class="form-control" id="discount" placeholder="Ex: input 10 for 10%" name="discount" value="<%=(st==null?"":st.getDiscount())%>"  min="0" max="100" required>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="thumbnail">Thumbnail</label>
                        <input type="text" class="form-control" id="thumbnail" name="thumbnail" placeholder="input filename" value="<%=(st==null?"":st.getThumbnail())%>" required>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="status">Status</label>
                        <select id="status" class="form-control" name="status">
                            <% for (int i = 0; i < 3;i++ )
                            { Status stat = dao.getStats().get(i);
                            %>
                            <option value=<%=stat.getId()%>
                                    <%=st==null?"":(st.getStatus()==stat.getId()?"selected":"")
                                    %>        ><%=stat.getName()%></option>
                            <%}%>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" style="height: 140px !important; resize: none;" rows="5" id="description" name="description" required><%=(st==null?"":st.getDescription())%></textarea>
                </div>
                <div class="d-flex justify-content-end">
                    <button type="submit" class="btn btn-primary mx-2" name="btnInsert" id="btnInsert">Insert</button>
                    <%boolean isUpdate = false;
if(!dao.getProducts().isEmpty()&&idUpdate!=0){
                    isUpdate = true;%>
                    <%}%>
                    <button type="submit" class="btn btn-primary mx-2" name="btnUpdate" id="btnUpdate"  <%=isUpdate==true?"":"style='display: none;'"%>>Update</button>
                </div>
            </form>
        </div>
        <!-- /input form -->

        <div class="divider"></div>

        <!--        search bar -->
        <!--

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
        <!--         /search bar -->

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
        <script>
                        function checkAllButton() {
                            const checkAllButton = document.getElementById("checkAllButton");
                            const checkboxes = document.querySelectorAll('input[type="checkbox"]');
                            checkAllButton.addEventListener("click", function () {
                                checkboxes.forEach((checkbox) => {
                                    checkbox.checked = true;
                                });
                            });
                        }

                        function setInputFieldType() {
                            const selectOption = document.getElementById("searchOption");
                            const inputField = document.getElementById("searchText");
                            selectOption.addEventListener("change", function () {
                                if (selectOption.value === "0") {
                                    inputField.type = "number";
                                    inputField.min = 1;
                                } else if (selectOption.value === "1") {
                                    inputField.type = "text";
                                }
                            });
                        }

                        function confirmNotification(option) {
                            if (option === 0) {
                                if (window.confirm("Bạn có muốn xóa sản phẩm này ?")) {
                                    return true;
                                } else {
                                    return false;
                                }
                            }
                        }

                        function confirmForm() {
                            const form = document.getElementById("manageProductForm");
                            const btnUpdate = document.getElementById('btnUpdate');
                            const btnInsert = document.getElementById('btnInsert');
                            const checkboxes = form.querySelectorAll('input[type="checkbox"]');
                            //check if user click update
                            btnUpdate.addEventListener('click', function (event) {
                                const numChecked = Array.from(checkboxes).filter(checkbox => checkbox.checked).length;
                                // check if user check atleast 1 option
                                if (numChecked === 0) {
                                    alert('Vui lòng chọn ít nhất 1 size.');
                                    event.preventDefault();
                                }
                                // if more than 1 checkbox is checked
                                else if (numChecked > 1) {
                                    alert('Update sản phẩm chỉ được chọn 1 size.');
                                    event.preventDefault();
                                } else {
                                    if (!window.confirm("Bạn có muốn update sản phẩm này ?")) {
                                        event.preventDefault();
                                    }
                                }
                            });

                            btnInsert.addEventListener('click', function (event) {
                                const numChecked = Array.from(checkboxes).filter(checkbox => checkbox.checked).length;
                                // check if user check atleast 1 option
                                if (numChecked === 0) {
                                    alert('Vui lòng chọn ít nhất 1 size.');
                                    event.preventDefault();
                                } else {
                                    if (!window.confirm("Bạn có muốn thêm sản phẩm này ?")) {
                                        event.preventDefault();
                                    }
                                }
                            });
                        }


                        checkAllButton();
                        setInputFieldType();
                        confirmForm();
        </script>
    </body>
</html>
