<html>
<body>
<h1>Shopping Cart Details</h1>
<table width=50%>
<tr><td>Item ID</td><td>Number of item in Shopping Cart</td></tr>
<%
	java.util.Enumeration em = session.getAttributeNames();
//        getkeys()
//	for(;em.hasMoreElements();){
        while(em.hasMoreElements()){
		String id= em.nextElement().toString(); //get key
                
//                if(!id.equals("usename"))
               
		//get value from session object (see HttpSession)
		String count=session.getAttribute(id).toString(); //get value
//                 Product pro=(Product)session.getAttribute(id);
		out.println("<tr>");
		out.println("<td>"+id+"</td>");
		out.println("<td>"+count+"</td>");
  		out.println("</tr>");
	}
%>
</table>
<h2><a href="../ClientController">Items List</h2>
<br>
<h2><a href="checkout.jsp">Check-out</h2>


</body>
</html>