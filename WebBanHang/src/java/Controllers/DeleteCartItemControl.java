/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import Models.Cart;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "DeleteCartItemControl", urlPatterns = {"/DeleteCartItem"})
public class DeleteCartItemControl extends HttpServlet {

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
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        int itemId = Integer.parseInt(request.getParameter("itemId"));
        HttpSession session = request.getSession(false);
        // kiem tra session có id = userid ton tai
        if (session.getAttribute(accountId + "") != null) {
            Cart cart = (Cart) session.getAttribute(accountId + "");
            //xoa item khoi gio hang
            cart.removeItem(itemId);
            session.setAttribute(accountId + "", cart);
        }
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
