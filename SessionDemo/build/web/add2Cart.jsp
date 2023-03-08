<html>
<body>
<%
	String id = request.getParameter("id"); 
//     id - key
//        get infor of product: name, price, images
//         create product 
	Object value = session.getAttribute(id); //getKey(id)
//         Product pro=(Product)session.getAttribute(id);
//     int quan=session.getAttribute(id);
//	the first time the product is selected
	if(value==null){
//            set quantity of product is 1
		//put name-value pair into session object (see HttpSession)
		session.setAttribute(id,"1"); // put(key,value)
//                pro.setQuantity(1);
//                session.setAttribute(id,pro);
//                session.setAttribute(id,1);
	}
	//the second/third... time the product is selected
	else{
		int count = Integer.parseInt(value.toString())+1;
//                pro.setQuantitty(pro.getQuantity()+1);
		//put name-value pair into session object (see HttpSession)
		session.setAttribute(id,String.valueOf(count));
//                session.setAttribute(id,pro);
                
		
	}	
%>
<h1>Item with id=<%=id%> was added to the Shopping Cart</h1>
<h2><a href="showCart.jsp">Show Shopping Cart</h2>

</body>
</html>