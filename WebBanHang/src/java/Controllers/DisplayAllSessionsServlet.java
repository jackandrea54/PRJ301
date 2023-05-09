package Controllers;

import Models.Cart;
import Models.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletContext;
import java.io.IOException;
import java.util.Enumeration;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/DisplayAllSessionsServlet")
public class DisplayAllSessionsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        Enumeration keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            out.print(key + ": ");
            Cart cart = (Cart) session.getAttribute(key);
            out.print(cart.getAccountId());
            for(CartItem item : cart.getItems().values()){
                out.println(item.getName() + item.getQuantity());
            }
        }
        out.close();
    }
}
