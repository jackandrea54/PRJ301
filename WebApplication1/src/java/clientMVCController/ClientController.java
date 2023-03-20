/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package clientMVCController;

import dao.DAOProduct;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ClientController", urlPatterns = {"/shop"})
public class ClientController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOProduct dao = new DAOProduct();
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll"; // default value
            }
            if (go.equals("listAll")) {
                //Data for product
                String sqlPro = "select * from Product as a join Category "
                        + " as b on a.cateId=b.cateId";
                ResultSet rsPro = dao.getData(sqlPro);
                String titleTable = "List of Product";
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataPro", rsPro);
                request.setAttribute("title", titleTable);
                //Data for menu
                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataMenu", rsMenu);
                //Call jsp
                dispath(request, response, "./clientJSP/Shop.jsp");
            }
            if (go.equals("displayProductByCategory")) {
                String [] cateID = request.getParameterValues("cateID");
                if (cateID == null) {
                    response.sendRedirect("shop");
                }else{
                    String sqlPro = "select  * from Product as a join Category "
                            + " as b on a.cateID = b.cateID where a.cateID = " + cateID[0] + " ";
                    for (int i = 1; i < cateID.length; i++) {
                        sqlPro += "or a.cateID = '" + cateID[i] + "'";
                    }
                    ResultSet rsPro = dao.getData(sqlPro);
                    String titleTable = "List of Product";
                    //Data cho product
                    request.setAttribute("dataPro", rsPro);
                    //Data for menu
                    String sqlMenu = "select * from Category";
                    ResultSet rsMenu = dao.getData(sqlMenu);
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataMenu", rsMenu);
                    //Call jsp
                    RequestDispatcher dispath = request.getRequestDispatcher("/clientJSP/Shop.jsp");
                    dispath.forward(request, response); 
                }
                
            }
            if (go.equals("search")) {
                String pname = request.getParameter("pname");
                String sql = "select a.*, b.cateName from Product as a join Category as b on a.cateId=b.cateId where pname like '%" + pname + "%'";
//                Vector<Product> vector = dao.getProduct(sql);
                ResultSet rs = dao.getData(sql);
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataPro", rs);
//              //Data for menu
                String sqlMenu = "select * from Category";
                ResultSet rsMenu = dao.getData(sqlMenu);
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataMenu", rsMenu);
//                //Run
//                dispath.forward(request, response);
                dispath(request, response, "./clientJSP/Shop.jsp");
            }
        }
    }
    
    void dispath(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        //run
        disp.forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
