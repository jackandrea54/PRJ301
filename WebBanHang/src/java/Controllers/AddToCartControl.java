/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.*;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "AddToCartControl", urlPatterns = {"/AddToCart"})
public class AddToCartControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String productName = request.getParameter("productName");
        int price = Integer.parseInt(request.getParameter("productPrice"));
        int colorId = Integer.parseInt(request.getParameter("color"));
        int sizeId = Integer.parseInt(request.getParameter("size-picker"));
        int quan = Integer.parseInt(request.getParameter("quantity"));
        //lay id product tu database
        int productId = d.loadProductIdByAttributes(productName, colorId, sizeId);
        //lay userId tu cookie
        int userId = 0;
        Cookie[] cookies = request.getCookies();// Get an array of cookies
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) { // Check if cookie exists 
                    userId = d.loadAccountIdByUsername(cookie.getValue());// Get the value of the cookie
                }
            }
        }
        //tao obj gio hang neu chua co gio hang tren session
        HttpSession session = request.getSession(false);
        // kiem tra session có id = userid ton tai
        if (session.getAttribute(userId+"") != null) {
            Cart cart = (Cart) session.getAttribute(userId + "");
            //them item vao do hang
            cart.addItem(productId, productName, price, quan);
            session.setAttribute(userId + "", cart);
        } else {
            //tao cart moi
            Cart newCart = new Cart(userId);
            //them item vao do hang
            newCart.addItem(productId, productName, price, quan);
            session = request.getSession();
            session.setAttribute(userId + "", newCart);
            session.setMaxInactiveInterval(1800);
        }
        
//        Cart cart = (Cart) session.getAttribute(userId+"");
//        Map<Integer, CartItem> items = cart.getItems();
//        request.setAttribute("cart", cart);
//        request.setAttribute("cartItems", items);
//        request.getRequestDispatcher("test.jsp").forward(request, response);

    // get the previous page URL from the "referer" header
    String previousPage = request.getHeader("referer");
    // redirect back to the previous page
    response.sendRedirect(previousPage);    
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
