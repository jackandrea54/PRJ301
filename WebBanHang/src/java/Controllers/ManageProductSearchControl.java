/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ManageProductSearchControl", urlPatterns = {"/ManageProductSearch"})
public class ManageProductSearchControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int option = Integer.parseInt(request.getParameter("searchOption"));
        String searchText = request.getParameter("searchText").trim();
        ArrayList<Product> productList = new ArrayList<>();
        //check if searchText is null
        if (searchText == null || searchText.isEmpty()) {
            d.loadProductsAll();
            productList = d.getProducts();
        } else {
            //get product by option
            if (option == 0) {
                Product temp = d.loadProductById(Integer.parseInt(searchText));
                productList.add(temp);
            }
            if (option == 1) {
                d.loadProductByName(searchText);
                productList = d.getProducts();
            }
        }

        HttpSession session = request.getSession();
        session.setAttribute("manageProductList", productList);
        request.getRequestDispatcher("ManageProduct").forward(request, response);
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
