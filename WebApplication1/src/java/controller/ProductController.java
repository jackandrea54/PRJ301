/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOProduct;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "ProductController", urlPatterns = {"/ProductControllerURL"})
public class ProductController extends HttpServlet {

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
        DAOProduct dao = new DAOProduct();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            String go = request.getParameter("go");
            // if call servlet direct --> go=null
            if (go == null) {
                go = "listAll"; // default value
            }

            if (go.equals("insert")) {
                String pid = request.getParameter("pid");
                String pname = request.getParameter("pname");
                double price = Double.parseDouble(request.getParameter("price"));
                int quantity = Integer.parseInt(request.getParameter("quantity"));
                String image = request.getParameter("image");
                String description = request.getParameter("description");
                int status = Integer.parseInt(request.getParameter("status"));
                int cateID = Integer.parseInt(request.getParameter("cateID"));

                Product prod = new Product(pid, pname, quantity, price, image, description, status, cateID);
                int n = dao.AddProduct(prod);
                if (n > 0) {
                    out.println("Inserted");
                }
                response.sendRedirect("ProductControllerURL");
            }

            if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category "
                        + " as b on a.cateId=b.cateId";
                ResultSet rs = dao.getData(sql);
                out.print("<table border=\"1\">\n"
                        + "    <caption>Product list</caption>\n"
                        + "    <tr>\n"
                        + "        <th>productID</th>\n"
                        + "        <th>Category</th>\n"
                        + "        <th>Product Name</th>\n"
                        + "        <th>Quantity</th>\n"
                        + "        <th>Price </th>\n"
                        + "        <th>image</th>\n"
                        + "        <th>Description</th>\n"
                        + "        <th>Status</th>\n"
                        + "        <th>Update</th>\n"
                        + "        <th>Delete</th>\n"
                        + "    </tr>");
                while (rs.next()) {
                    out.print("<tr>\n"
                            + "        <td>" + rs.getString(1) + "</td>\n"
                            + "        <td>" + rs.getString(10) + "</td>\n"
                            + "        <td>" + rs.getString(2) + "</td>\n"
                            + "        <td>" + rs.getString(3) + "</td>\n"
                            + "        <td>" + rs.getString(4) + "</td>\n"
                            + "        <td><img src=\"" + rs.getString(5) + "\" width=\"100\" height=\"150\"></td>\n"
                            + "        <td>" + rs.getString(6) + "</td>\n"
                            + "        <td>" + (rs.getInt(7) == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "        <td><a href=\"ProductControllerURL?go=update&pid=" + rs.getString(1) + "&cateID=" + rs.getInt(8) + "\">update</a></td>\n"
                            + "        <td><a href=\"ProductControllerURL?go=delete&pid=" + rs.getString(1) + "\">Delete</a></td>\n"
                            + "    </tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"ProductManage.html\">Product Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertProduct.html\">Insert Product</a></td>"
                        + "</tr>");
                out.print("</table>");
                

            }

            if (go.equals("delete")) {
                String pid = request.getParameter("pid");
                dao.removeProduct(pid);
                response.sendRedirect("ProductControllerURL");
            }

            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pid = request.getParameter("pid");
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    ResultSet rsPro = dao.getData("select * from Product where pid='" + pid + "'");
                    ResultSet rsCate = dao.getData("select * from category");

                    if (rsPro.next()) {
                        out.print("<form action=\"ProductControllerURL\"  method = \"POST\">\n"
                                + "            <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                                + "            <table>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"pid\">Product ID</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"pid\" id = \"pid\" value=\"" + rsPro.getString(1) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"pname\">Product Name</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"pname\" id = \"pname\" value=\"" + rsPro.getString(2) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"quantity\">quantity</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"quantity\" id = \"quantity\" value=\"" + rsPro.getString(3) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"price\">price</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"price\" id = \"price\" value=\"" + rsPro.getString(4) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"image\">image</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"image\" id = \"image\" value=\"" + rsPro.getString(5) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"description\">description</label></td>\n"
                                + "                    <td><input type=\"text\" name=\"description\" id = \"description\" value=\"" + rsPro.getString(6) + "\"></td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"status\">status</label></td>\n"
                                + "                    <td>\n"
                                + "                         <input type=\"radio\" name=\"status\" id=\"\" value=\"1\" " + (rsPro.getInt("status") == 1 ? "checked" : "") + ">Enable\n"
                                + "                         <input type=\"radio\" name=\"status\" id=\"\" value=\"0\" " + (rsPro.getInt("status") == 0 ? "checked" : "") + ">Disable\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><label for=\"cateID\">Category</label></td>\n"
                                + "                    <td>\n"
                                + "                        <select name=\"cateID\" id=\"cateID\">\n");
                        //Print the category name
                        while (rsCate.next()) {
                            out.print("<option value=\"" + rsCate.getInt(1) + "\"" + (rsCate.getInt(1) == cateID ? "selected" : "") + ">" + rsCate.getString(2) + "</option>");
                        }
                        out.print(
                                "                        </select>\n"
                                + "                    </td>\n"
                                + "                </tr>\n"
                                + "                <tr>\n"
                                + "                    <td><input type=\"submit\" value=\"Update Product\" name=\"submit\"></td>\n"
                                + "                    <td><input type=\"reset\" value=\"Reset\"></td>\n"
                                + "                </tr>\n"
                                + "                \n"
                                + "            </table>\n"
                                + "        </form>");
                    }

                } else {
                    //update here
                    String pid = request.getParameter("pid");
                    String pname = request.getParameter("pname");
                    double price = Double.parseDouble(request.getParameter("price"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    String image = request.getParameter("image");
                    String description = request.getParameter("description");
                    int status = Integer.parseInt(request.getParameter("status"));
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    Product prod = new Product(pid, pname, quantity, price, image, description, status, cateID);
                    int n = dao.update(prod);
                    response.sendRedirect("ProductControllerURL");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
