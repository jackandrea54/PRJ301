/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOCustomer;
import entity.Customer;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
public class CustomerController extends HttpServlet {

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

        //PrintWriter in dc ca text va nhi phan (da dang)
        try ( PrintWriter out = response.getWriter()) {
            String go = request.getParameter("go");
            //if call servlet direct --> go = null
            if (go == null) {
                go = "listAll"; //Default value
            }

            if (go.equals("insert")) {
                String cname = request.getParameter("cname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String phone = request.getParameter("phone");
                int status = Integer.parseInt(request.getParameter("status"));

                Customer cus = new Customer(cname, username, password, phone, phone, status);
                int n = dao.AddCustomer(cus);
                if (n > 0) {
                    out.print("Inserted");
                }
                response.sendRedirect("CustomerControllerURL"); //Chay lai voi go = null -> listAll -> show Customer
            }

//            request.getParameter("NameFromClient"); //-- single value
//            request.getParameterNames("NameFromClient"); //-- array
//            out.println("<p>ID = " + cid + "</p>");
//            out.println("<p>name = " + cname + "</p>");
//            out.println("<p>username = " + username + "</p>");
//            out.println("<p>password = " + password + "</p>");
//            out.println("<p>phone = " + phone + "</p>");
//            out.println("<p>status = " + status + "</p>");
            if (go.equals("listAll")) {
                out.print("<table border = \"1\">\n"
                        + "            <caption>CUSTOMER LIST</caption>\n"
                        + "            <tr>\n"
                        + "                <th>CID</th>\n"
                        + "                <th>Customer name</th>\n"
                        + "                <th>Username</th>\n"
                        + "                <th>Address</th>\n"
                        + "                <th>Phone</th>\n"
                        + "                <th>Status</th>\n"
                        + "                <th>Update</th>\n"
                        + "                <th>Delete</th>\n"
                        + "            </tr>");

                Vector<Customer> vector = dao.getAllCustomer();
                for (Customer temp : vector) {
                    out.print("<tr>\n"
                            + "                <td>" + temp.getCid() + "</td>\n"
                            + "                <td>" + temp.getCname() + "</td>\n"
                            + "                <td>" + temp.getUsername() + "</td>\n"
                            + "                <td>" + temp.getAddress() + "</td>\n"
                            + "                <td>" + temp.getPhone() + "</td>\n"
                            + "                <td>" + (temp.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "                <td><a href=\"CustomerControllerURL?go=update&cid=" + temp.getCid() + "\">Update</a></td>\n"
                            + "                <td><a href=\"CustomerControllerURL?go=delete&cid=" + temp.getCid() + "\">Delete</a></td>\n"
                            + "</tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"CustomerManage.html\">Customer Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertCustomer.html\">Insert Customer</a></td>"
                        + "</tr>");
                out.println("</table>");
            }

            if (go.equals("delete")) {
                String cid = request.getParameter("cid");
                dao.removeCustomer(cid);
                response.sendRedirect("CustomerControllerURL");
            }

            if (go.equals("update")) {
                //Check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) { // hien thi form chua submit
                    // lay ra ban ghi can hien thi
                    String cid = request.getParameter("cid");
                    Vector<Customer> vec = dao.getCustomer("select * from Customer where cid ='" + cid + "'");
                    Customer cus = vec.get(0);
                    //display
                    out.print("<form action = \"CustomerControllerURL\" method = \"POST\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cid\">CID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cid\" id = \"cid\" value = \"" + cus.getCid() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cname\">cname</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cname\" id = \"cname\" value = \"" + cus.getCname() + "\"></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"username\">username</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"username\" id = \"username\" value = \"" + cus.getUsername() + "\"></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"password\">password</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"password\" id = \"password\" value = \"" + cus.getPassword() + "\"></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"address\">address</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"address\" id = \"address\" value = \"" + cus.getAddress() + "\"></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"phone\">phone</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"phone\" id = \"phone\" value = \"" + cus.getPhone() + "\"></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"status\">status</label></td>\n"
                            + "                    <td>\n"
                            + "                        <input type=\"radio\" name=\"status\" id = \"status\" value=\"1\" " + (cus.getStatus() == 1 ? "checked" : "") + "> Enable\n"
                            + "                        <input type=\"radio\" name=\"status\" id = \"status\" value=\"0\" " + (cus.getStatus() == 0 ? "checked" : "") + " > Disable\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Customer\" name=\"submit\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            \n"
                            + "            \n"
                            + "        </form>");
                } else {// da submit
                    String cid = request.getParameter("cid");
                    String cname = request.getParameter("cname");
                    String username = request.getParameter("username");
                    String password = request.getParameter("password");
                    String phone = request.getParameter("phone");
                    int status = Integer.parseInt(request.getParameter("status"));

                    Customer cus = new Customer(cid, cname, username, password, phone, phone, status);
                    int n = dao.updateCustomer(cus);
                    response.sendRedirect("CustomerControllerURL");
                }
            }

        }
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
