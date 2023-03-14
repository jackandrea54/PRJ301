/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOAdmin;
import entity.Admin;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AdminControllerMVC", urlPatterns = {"/AdminControllerMVC"})
public class AdminControllerMVC extends HttpServlet {

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
        DAOAdmin dao = new DAOAdmin();
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(3600);
        if (session.getAttribute("admin") != null) {
            try ( PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                String go = request.getParameter("go");
                //if call servlet direct --> go = null
                if (go == null) {
                    go = "listAll"; //Default value
                }
                if (go.equals("listAll")) {
                    Vector<Admin> vector = dao.getAllAdmin();
                    String titleTable = "List of Admin";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataAdmin", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewAdmin.jsp");
                }
                if (go.equals("insert")) {
                    String admin = request.getParameter("admin");
                    String password = request.getParameter("password");
                    Admin ad = new Admin(admin, password);
                    dao.AddAdmin(ad);
                    dispath(request, response, "AdminControllerMVC?go=listAll");
                }
                if (go.equals("delete")) {
                    String admin = request.getParameter("admin");
                    dao.removeAdmin(admin);
                    dispath(request, response, "AdminControllerMVC?go=listAll");
                }
                if (go.equals("update")) {
                    String submit = request.getParameter("submit");
                    if (submit == null) {
                        String admin = request.getParameter("admin");
                        String sql = "select * from Admin where admin = '" + admin + "' ";
                        Vector<Admin> vector = dao.getAdmin(sql);
                        Admin ad = vector.get(0);
                        request.setAttribute("dataAdmin", ad);
                        dispath(request, response, "/adminJSP/UpdateAdmin.jsp");
                    } else {
                        String admin = request.getParameter("admin");
                        String password = request.getParameter("password");

                        Admin ad = new Admin(admin, password);
                        int n = dao.update(ad);

                        dispath(request, response, "AdminControllerMVC?go=listAll");
                    }
                }
                if (go.equals("search")) {
                    String admin = request.getParameter("admin");
                    String sql = "select * from Admin  where admin ='" + admin + "'";
                    Vector<Admin> vector = dao.getAdmin(sql);
                    String titleTable = "List of Admin";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataAdmin", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewAdmin.jsp");
                }
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
