/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import Models.PageView;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class ManageProductControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        d.loadCollection();
        d.loadColor();
        d.loadCategory();
        d.loadSize();
        d.loadStats();
        int index = 0;
        try {
            index = Integer.parseInt(request.getParameter("index"));
        } catch (Exception e) {
            index = 0;
        }

        int nrpp = 10;
        try {
            nrpp = Integer.parseInt(request.getParameter("nrpp"));
            nrpp = nrpp < 0 ? 0 : nrpp;
        } catch (Exception e) {
        }

        Object obj = request.getParameter("type");//update
        Object obj2 = request.getParameter("id");
        //check if user use update/delete
        if (obj != null) {
            //check if user choose update
            if ((obj + "").equals("0")) {
                //if update id not nul, set update as product with id's info
                if (obj2 != null) {
                    request.setAttribute("update", obj2 + "");
                } else {
                    request.removeAttribute("update");
                }
                //check if user choose delete
            } else if ((obj + "").equals("1")) {
                if (obj2 != null) {
                    d.deleteProduct(Integer.parseInt(obj2+""));
                }
            }
        } else {
        }

//get product list
        HttpSession session = request.getSession();
        //check if product list is in session
        if (session.getAttribute("manageProductList") == null) {
            d.loadProductsAll();
        } else {
            d.setProducts((ArrayList<Product>) session.getAttribute("manageProductList"));
        }
        PageView p = new PageView(d.getProducts().size(), nrpp, index);
        p.calculate();
        request.setAttribute("page", p);
        request.setAttribute("dao", d);
        request.getRequestDispatcher("ManageProduct.jsp").forward(request, response);
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
        d.loadSize();
        String name = request.getParameter("name");
        int price = Integer.parseInt(request.getParameter("price"));
        int category = Integer.parseInt(request.getParameter("category"));
        int collection = Integer.parseInt(request.getParameter("collection"));
        int color = Integer.parseInt(request.getParameter("color"));
        ArrayList<Integer> sizes = new ArrayList<>();
        //get checked checkbox
        for (int i = 1; i < d.getSizes().size() + 1; i++) {
            if (request.getParameter("size-" + i) != null) {
                sizes.add(i);
            }
        }
        int discount = Integer.parseInt(request.getParameter("discount"));
        String thumbnail = request.getParameter("thumbnail");
        int status = Integer.parseInt(request.getParameter("status"));
        String description = request.getParameter("description");
        Object btnIn = request.getParameter("btnInsert");
        Object btnUp = request.getParameter("btnUpdate");
        boolean update = false;
        
        //check product is exist in list
        int productId = Integer.parseInt(request.getParameter("id"));
        for (Product st : d.getProducts()) {
            if (st.getId() == productId) {
                update = true;
            }
        }
        //check user click insert
        if (btnIn != null && !update) {
            for (int i = 0; i < sizes.size(); i++) {
                int size = sizes.get(i);
                d.insertProduct(name, price, category, collection, color, size, discount, thumbnail, status, description);
            }
        }
        if (btnUp != null && update) {
            d.updateProduct(productId, name, price, category, collection, color, sizes.get(0), discount, thumbnail, status, description);
        }
        doGet(request, response);

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
