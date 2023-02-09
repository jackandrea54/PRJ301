/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOAdmin;
import entity.Admin;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AdminController", urlPatterns = {"/AdminControllerURL"})
public class AdminController extends HttpServlet {

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

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AdminController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AdminController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");

            String go = request.getParameter("go");
            //if call servlet direct --> go = null
            if (go == null) {
                go = "listAll"; //Default value
            }
            if (go.equals("insert")) {
                String admin = request.getParameter("admin");
                String password = request.getParameter("password");
                Admin ad = new Admin(admin, password);
                dao.AddAdmin(ad);
                response.sendRedirect("AdminControllerURL");
            }
            if (go.equals("listAll")) {
                out.print("<table border = \"1\">\n"
                        + "            <caption>ADMIN LIST</caption>\n"
                        + "            <tr>\n"
                        + "                <th>ADMIN</th>\n"
                        + "                <th>Update</th>\n"
                        + "                <th>Delete</th>\n"
                        + "            </tr>");

                Vector<Admin> vector = dao.getAllAdmin();
                for (Admin temp : vector) {
                    out.print("<tr>\n"
                            + "                <td>" + temp.getAdmin() + "</td>\n"
                            + "                <td><a href=\"AdminControllerURL?go=update&admin=" + temp.getAdmin() + "\">Update</a></td>\n"
                            + "                <td><a href=\"AdminControllerURL?go=delete&admin=" + temp.getAdmin() + "\">Delete</a></td>\n"
                            + "</tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"AdminManage.html\">Admin Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertAdmin.html\">Insert Admin</a></td>"
                        + "</tr>");
                out.print("</table>");
            }
            if (go.equals("delete")) {
                String admin = request.getParameter("admin");
                dao.removeAdmin(admin);
                response.sendRedirect("AdminControllerURL");
            }
            if (go.equals("update")) {
                //Check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) { // hien thi form chua submit
                    // lay ra ban ghi can hien thi
                    String admin = request.getParameter("admin");
                    Vector<Admin> vec = dao.getAllAdmin("select * from admin where admin ='" + admin + "'");
                    Admin ad = vec.get(0);
                    //display
                    out.print("<form action=\"AdminControllerURL\"  method = \"POST\">\n"
                            + "        <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "        <table>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"admin\">Admin account</label></td>\n"
                            + "                <td><input type=\"text\" name=\"admin\" id = \"admin\" value = \"" + ad.getAdmin()+ "\" readonly></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"password\">Password</label></td>\n"
                            + "                <td><input type=\"text\" name=\"password\" id = \"password\" value = \"" +ad.getPassword()+"\"></td>\n"
                            + "            </tr>\n"
                            + "            \n"
                            + "            <tr>\n"
                            + "                <td><input type=\"submit\" value=\"Update Admin\" name=\"submit\"></td>\n"
                            + "                <td><input type=\"reset\" value=\"Reset\"></td>\n"
                            + "            </tr>\n"
                            + "            \n"
                            + "        </table>\n"
                            + "    </form>");
                } else {// da submit
                    String admin = request.getParameter("admin");
                    String password = request.getParameter("password");
                    Admin ad = new Admin(admin, password);
                    dao.update(ad);
                    response.sendRedirect("AdminControllerURL");
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
