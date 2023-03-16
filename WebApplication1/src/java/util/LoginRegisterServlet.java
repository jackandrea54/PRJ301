/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package util;

import dao.DAOAdmin;
import dao.DAOCustomer;
import entity.Admin;
import entity.Customer;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "LoginRegisterServlet", urlPatterns = {"/LoginRegisterServlet"})
public class LoginRegisterServlet extends HttpServlet {

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
            DAOCustomer dao = new DAOCustomer();
            HttpSession session = request.getSession();
            System.out.println("Session created at: " + new Date(session.getCreationTime()));
            String go = request.getParameter("go");
            if (go.equals("login")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String sql = "select * from Customer where username ='" + username + "' and password = '" + password + "'";
                Vector<Customer> vector = dao.getCustomer(sql);
                if (vector.size() == 0) {
                    DAOAdmin daoAdmin = new DAOAdmin();
                    sql = "select * from Admin where admin ='" + username + "' and password = '" + password + "'";
                    Vector<Admin> vecAd = daoAdmin.getAdmin(sql);
                    if (vecAd.size() == 0) {
                        response.sendRedirect("./Login.jsp?msg=fail");
//                        dispath(request, response, "Login.jsp");
                    } else {
                        Admin ad = vecAd.get(0);
                        session.setAttribute("admin", ad.getAdmin());
                        response.sendRedirect("./adminJSP/AdminIndex.jsp");
                    }
                } else {
                    Customer cus = vector.get(0);
                    session.setAttribute("cid", cus.getCid());
                    session.setAttribute("username", cus.getUsername());
                    response.sendRedirect("shop");
                }
            }
            if (go.equals("register")) {
                String cname = request.getParameter("cname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                int status = 1;
                Customer cus = new Customer(cname, username, password, address, phone, status);
                int n = dao.AddCustomer(cus);
                if(n < 0){
                    System.out.println("Register successfully");
                    response.sendRedirect("./Register.jsp?msg=fail");
                }else{
                    response.sendRedirect("./Login.jsp");
                }
            }
            if (go.equals("logout")) {
                session.invalidate();
                response.sendRedirect("./clientJSP/ClientIndex.jsp");
            }
        }
    }

    void dispath(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
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
