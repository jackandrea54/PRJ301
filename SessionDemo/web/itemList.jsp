<html>
<body>
<table width=50%>	
<tr><td>Item ID</td><td>Item name</td></tr>
  <%
  	for(int i =0;i<10;i++){
  		out.println("<tr>");
  		out.println("<td>"+i+"</td>");
  		out.println("<td><a href=add2Cart.jsp?id="+ i +">addCartItem"+(i+1)+"</a></td>");
  		out.println("</tr>");
  	}
  %>
</table>
</body>
</html>