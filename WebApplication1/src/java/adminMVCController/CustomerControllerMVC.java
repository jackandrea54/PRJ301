/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOCustomer;
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
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CustomerControllerMVC", urlPatterns = {"/CustomerControllerMVC"})
public class CustomerControllerMVC extends HttpServlet {

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
        DAOCustomer dao = new DAOCustomer();
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") != null) {
                String go = request.getParameter("go");
                //if call servlet direct --> go = null
                if (go == null) {
                    go = "listAll"; //Default value
                }
                if (go.equals("listAll")) {
                    Vector<Customer> vector = dao.getAllCustomer();
                    request.setAttribute("dataCus", vector);
                    request.setAttribute("title", "List of Customer");
                    dispath(request, response, "/adminJSP/ViewCustomer.jsp");
                }
                if (go.equals("insert")) {
                    String cid = request.getParameter("cid");
                    String cname = request.getParameter("cname");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String phone = request.getParameter("phone");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Customer cus = new Customer(cid, cname, username, password, phone, phone, status);
                    int n = dao.AddCustomer(cus);
                    dispath(request, response, "CustomerControllerMVC?go=listAll");
                }

                if (go.equals("delete")) {
                    String cid = request.getParameter("cid");
                    dao.removeCustomer(cid);
                    dispath(request, response, "CustomerControllerMVC?go=listAll");
                }

                if (go.equals("update")) {
                    //Check hien thi form hay update bang submit
                    String submit = request.getParameter("submit");
                    if (submit == null) { // hien thi form chua submit
                        String cid = request.getParameter("cid");
                        Vector<Customer> vec = dao.getCustomer("select * from Customer where cid ='" + cid + "'");
                        Customer cus = vec.get(0);
                        request.setAttribute("dataCus", cus);
                        dispath(request, response, "/adminJSP/UpdateCustomer.jsp");
                    } else {// da submit
                        String cid = request.getParameter("cid");
                        String cname = request.getParameter("cname");
                        String username = request.getParameter("username");
                        String password = request.getParameter("password");
                        String phone = request.getParameter("phone");
                        int status = Integer.parseInt(request.getParameter("status"));
                        Customer cus = new Customer(cid, cname, username, password, phone, phone, status);
                        int n = dao.updateCustomer(cus);
                        dispath(request, response, "CustomerControllerMVC?go=listAll");
                    }
                }

                if (go.equals("search")) {
                    String cid = request.getParameter("cid");
                    String sql = "select * from Customer where cid ='" + cid + "'";
                    Vector<Customer> vector = dao.getCustomer(sql);
                    String titleTable = "List of Customer";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataCus", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewCustomer.jsp");
                }
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
