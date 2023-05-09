/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.PageView;
import Models.Product;
import jakarta.servlet.ServletContext;
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
public class CatalogControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        d.loadCollection();
        d.loadColor();
        d.loadCategory();
        //----------------------------chia trang-------------------------------
        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 0;
        }

        int nrpp = 9;
        try {
            nrpp = Integer.parseInt(request.getParameter("nrpp"));
            nrpp = nrpp < 0 ? 0 : nrpp;
        } catch (Exception e) {
        }

        //lay productList tu session
        HttpSession session = request.getSession();
        ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("productList");

        //-------------------------sort by--------------------------------------
        int sort = 0;
        ArrayList<Product> productListSort;
        PageView p = new PageView();

        try {
            sort = Integer.parseInt(request.getParameter("sort"));
        } catch (Exception e) {
        }
        //sort = 0 -> relevance
        if (sort == 0) {
            productListSort = productList;
            request.setAttribute("productList", productListSort);
            p = new PageView(productListSort.size(), nrpp, index);
            request.setAttribute("sort", sort);
        }
        //sort = 1 -> price descrease, sort product price decrease
        if (sort == 1) {
            productListSort = productList;
            //sort product in list decrease by price
            for (int i = 0; i < productListSort.size() - 1; i++) {
                for (int j = i + 1; j < productListSort.size(); j++) {
                    if (productListSort.get(i).getPrice() < productListSort.get(j).getPrice()) {
                        Product temp = productListSort.get(i);
                        productListSort.set(i, productListSort.get(j));
                        productListSort.set(j, temp);
                    }
                }
            }
            request.setAttribute("productList", productListSort);
            p = new PageView(productListSort.size(), nrpp, index);
            request.setAttribute("sort", sort);

        }
        //sort = 2 -> price increase, sort product price increase
        if (sort == 2) {
            productListSort = productList;
            //sort product in list increase by price
            for (int i = 0; i < productListSort.size() - 1; i++) {
                for (int j = i + 1; j < productListSort.size(); j++) {
                    if (productListSort.get(i).getPrice() > productListSort.get(j).getPrice()) {
                        Product temp = productListSort.get(i);
                        productListSort.set(i, productListSort.get(j));
                        productListSort.set(j, temp);
                    }
                }
            }
            request.setAttribute("productList", productListSort);
            p = new PageView(productListSort.size(), nrpp, index);
            request.setAttribute("sort", sort);

        }
        //sort = 3 -> best selling, sort product most appear in order
        if (sort == 3) {
            ArrayList<Integer> productBuyQuantity = (ArrayList<Integer>) session.getAttribute("productBuyQuantity");
            productListSort = productList;
            //sort product in list decrease buy quantity
            for (int i = 0; i < productBuyQuantity.size() - 1; i++) {
                for (int j = i + 1; j < productBuyQuantity.size(); j++) {
                    if (productBuyQuantity.get(i) < productBuyQuantity.get(j)) {
                        int t = productBuyQuantity.get(i);
                        productBuyQuantity.set(i, productBuyQuantity.get(j));
                        productBuyQuantity.set(j, t);

                        Product temp = productListSort.get(i);
                        productListSort.set(i, productListSort.get(j));
                        productListSort.set(j, temp);
                    }
                }
            }
            request.setAttribute("productList", productListSort);
            p = new PageView(productListSort.size(), nrpp, index);
            request.setAttribute("sort", sort);
        }

        //sort = 4 -> new arrival, ko xuat hien trong chon sort by
        if (sort == 4) {
            d.loadProductDateDesc();
            productListSort = d.getProductsRecent();
            session.setAttribute("productList", productListSort);
            request.setAttribute("productList", productListSort);
            p = new PageView(productListSort.size(), nrpp, index);
        }

        p.calculate();//get page begin, end
        request.setAttribute("page", p);
        request.setAttribute("dao", d);
        request.getRequestDispatcher("Catalog.jsp").forward(request, response);
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
