/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.Account;
import Models.Cart;
import Models.CartItem;
import Models.Order;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author LENOVO
 */
public class CheckOutControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay userid tu cookie
        d.loadCategory();
        d.loadSize();
        int userId = 0;
        Cookie[] cookies = request.getCookies();// Get an array of cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) { // Check if cookie exists 
                    userId = d.loadAccountIdByUsername(cookie.getValue());// Get the value of the cookie
                }
            }
        }
        //lay cart co id = userId và cac item trong cart
        HttpSession session = request.getSession(false);
        Cart cart = new Cart();
        Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();
        Map<Integer, Product> products = new HashMap<Integer, Product>();
        //kiem tra session voi userId ton tai ko
        if (session.getAttribute(userId + "") != null) {
            cart = (Cart) session.getAttribute(userId + "");
            items = cart.getItems();
            for (int i : items.keySet()) {
                products.put(i, d.loadProductById(i));
            }
        }

        Account acc = d.loadAccountById(userId);
        request.setAttribute("account", acc);

        //set map các item vào cartItems
        request.setAttribute("products", products);
        request.setAttribute("cartItems", items);
        request.setAttribute("cart", cart);
        request.setAttribute("dao", d);
        request.getRequestDispatcher("CheckOut.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phoneNumber");
        String address = request.getParameter("address");
        String note = request.getParameter("note");
        int userId = 0;
        Cookie[] cookies = request.getCookies();// Get an array of cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) { // Check if cookie exists 
                    userId = d.loadAccountIdByUsername(cookie.getValue());// Get the value of the cookie
                }
            }
        }

        //lay cart co id = userId và cac item trong cart
        HttpSession session = request.getSession(false);
        Cart cart = new Cart();
        Map<Integer, CartItem> items = new HashMap<Integer, CartItem>();
        Map<Integer, Product> products = new HashMap<Integer, Product>();
        //kiem tra session voi userId ton tai ko
        if (session.getAttribute(userId + "") != null) {
            cart = (Cart) session.getAttribute(userId + "");
            items = cart.getItems();
            for (int i : items.keySet()) {
                products.put(i, d.loadProductById(i));
            }
        }
        
        //insert order
        int orderId = 0;
//       orderId = d.insertOrder(userId, fullname, email, phone, address, note);

        //insert order detail
        //loop to each cart items
        ArrayList<CartItem> ite = new ArrayList();
        for(int productId : items.keySet()){
//            d.insertOrderDetail(orderId, productId, items.get(productId).getQuantity());

        }
        request.setAttribute("order", new Order(orderId, userId, fullname, email, phone, address, note, "", 4));
        
        request.getRequestDispatcher("CheckOutSuccess").forward(request, response);
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
