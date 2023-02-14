/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOCategory;
import entity.Category;
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
@WebServlet(name = "CategoryController", //Tuong ung voi the servlet trong xml
        urlPatterns = {"/CategoryControllerURL"}) //Tuong ung voi the servlet-mapping trong xml
public class CategoryController extends HttpServlet {

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
        DAOCategory dao = new DAOCategory();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CategoryController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CategoryController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            String go = request.getParameter("go");
            //if call servlet direct --> go = null
            if (go == null) {
                go = "listAll"; //Default value
            }

            if (go.equals("insert")) {
                String cateName = request.getParameter("cateName");
                int status = Integer.parseInt(request.getParameter("status"));
                Category cate = new Category(cateName, status);
                int n = dao.AddCategory(cate);
                if (n > 0) {
                    out.print("Inserted");
                }
                response.sendRedirect("CategoryControllerURL");
            }
            if (go.equals("listAll")) {
                out.print("<table border = \"1\">\n"
                        + "            <caption>CATEGORY LIST</caption>\n"
                        + "            <tr>\n"
                        + "                <th>Category ID</th>\n"
                        + "                <th>Category name</th>\n"
                        + "                <th>Status</th>\n"
                        + "                <th>Update</th>\n"
                        + "                <th>Delete</th>\n"
                        + "            </tr>");
                Vector<Category> vector = dao.getAllCategory();
                for (Category temp : vector) {
                    out.print("<tr>\n"
                            + "                <td>" + temp.getCateID() + "</td>\n"
                            + "                <td>" + temp.getCateName() + "</td>\n"
                            + "                <td>" + (temp.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "                <td><a href=\"CategoryControllerURL?go=update&cateID=" + temp.getCateID() + "\">Update</a></td>\n"
                            + "                <td><a href=\"CategoryControllerURL?go=delete&cateID=" + temp.getCateID() + "\">Delete</a></td>\n"
                            + "</tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"CategoryManage.html\">Category Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertCategory.html\">Insert Category</a></td>"
                        + "</tr>");
                out.print("</table>");
            }
            if (go.equals("delete")) {
                String cateID = request.getParameter("cateID");
                dao.removeCategory(cateID);
                response.sendRedirect("CategoryControllerURL");
            }
            if (go.equals("update")) {
                //Check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) { // hien thi form chua submit
                    // lay ra ban ghi can hien thi
                    String cateID = request.getParameter("cateID");
                    Vector<Category> vec = dao.getCategory("select * from Category where cateID ='" + cateID + "'");
                    Category cate = vec.get(0);
                    //display
                    out.print("<form action = \"CategoryControllerURL\" method = \"POST\">\n"
                            + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "            <table>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cateID\">Category ID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cateID\" id = \"cateID\" value = \"" + cate.getCateID() + "\" readonly></td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><label for=\"cateName\">Category ID</label></td>\n"
                            + "                    <td><input type=\"text\" name=\"cateName\" id = \"cateName\" value = \"" + cate.getCateName()+ "\"></td>\n"
                            + "                </tr>\n"        
                            + "                <tr>\n"
                            + "                    <td><label for=\"status\">status</label></td>\n"
                            + "                    <td>\n"
                            + "                        <input type=\"radio\" name=\"status\" id = \"status\" value=\"1\" " + (cate.getStatus() == 1 ? "checked" : "") + "> Enable\n"
                            + "                        <input type=\"radio\" name=\"status\" id = \"status\" value=\"0\" " + (cate.getStatus() == 0 ? "checked" : "") + " > Disable\n"
                            + "                    </td>\n"
                            + "                </tr>\n"
                            + "                <tr>\n"
                            + "                    <td><input type=\"submit\" value=\"Update Category\" name=\"submit\"></td>\n"
                            + "                </tr>\n"
                            + "            </table>\n"
                            + "            \n"
                            + "            \n"
                            + "        </form>");
                } else {// da submit
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    String cateName = request.getParameter("cateName");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Category cate = new Category(cateID, cateName, status);
                    int n = dao.update(cate);
                    if (n > 0) {
                        out.print("Updated");
                    }
                    response.sendRedirect("CategoryControllerURL");
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
