<html>
<body>
<%
	String id = request.getParameter("id");
	Object value = session.getAttribute(id);
	if(value==null){
		session.setAttribute(id,"1");
	}
	else{
		int count = Integer.parseInt(value.toString())+1;
		session.setAttribute(id,String.valueOf(count));
		
	}	
%>
<h1>Item with id=<%=id%> was added to the Shopping Cart</h1>
<h2><a href="showCart.jsp">Show Shopping Cart</h2>

</body>
</html>