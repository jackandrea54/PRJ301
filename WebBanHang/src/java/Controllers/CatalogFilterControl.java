/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
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
@WebServlet(name = "CatalogFilterControl", urlPatterns = {"/CatalogFilter"})
public class CatalogFilterControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        d.loadCollection();
        d.loadColor();
        d.loadCategory();
        //-------------------------------filter---------------------------------
        ArrayList<Integer> collections = new ArrayList<>();
        //get checked checkbox
        for (int i = 1; i < d.getCollections().size() + 1; i++) {
            if (request.getParameter("collection-" + i) != null) {
                collections.add(i);
            }
        }

        ArrayList<Integer> categories = new ArrayList<>();
        //get checked checkbox
        for (int i = 1; i < d.getCategories().size() + 1; i++) {
            if (request.getParameter("category-" + i) != null) {
                categories.add(i);
            }
        }

        ArrayList<Integer> colors = new ArrayList<>();
        //get checked checkbox
        for (int i = 1; i < d.getColors().size() + 1; i++) {
            if (request.getParameter("color-" + i) != null) {
                colors.add(i);
            }
        }

        String priceStart = request.getParameter("price-min");
        String priceEnd = request.getParameter("price-max");
        int pStart = -1;
        int pEnd = -1;

        //check price is empty
        if (priceStart != null) {
            if (!priceStart.trim().isEmpty()) {
                pStart = Integer.parseInt(priceStart);
            } else {
                pStart = -1;
            }
        } else {
            pStart = -1;
        }

        if (priceEnd != null) {
            if (!priceEnd.trim().isEmpty()) {
                pEnd = Integer.parseInt(priceEnd);
            } else {
                pEnd = -1;
            }
        } else {
            pEnd = -1;
        }

        request.setAttribute("start", pStart);
        request.setAttribute("end", pEnd);

        //check if user use filter
        if (!collections.isEmpty()
                || !categories.isEmpty()
                || !colors.isEmpty()
                || pStart != -1 || pEnd != -1) {
            d.loadProductWithFilter(categories, collections, colors, pStart, pEnd);
        }

        //set productList to session
        HttpSession session = request.getSession();
        session.setAttribute("productList", d.getProductFilter());
        session.setAttribute("productBuyQuantity", d.getProductBuyQuantity());
        request.getRequestDispatcher("Catalog").forward(request, response);
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
