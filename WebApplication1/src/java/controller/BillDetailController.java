/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBillDetail;
import dao.DAOProduct;
import entity.BillDetail;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "BillDetailController", urlPatterns = {"/BillDetailControllerURL"})
public class BillDetailController extends HttpServlet {

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
        DAOBillDetail dao = new DAOBillDetail();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BillDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillDetailController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll"; //Default value
            }
            
            if (go.equals("insert")) {
                String bid = request.getParameter("bid");
                String pid = request.getParameter("pid");
                int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                //Get price then add
                DAOProduct daoPro = new DAOProduct();
                Vector<Product> vec = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                Product product = vec.get(0);
                double buyPrice = product.getPrice();
                BillDetail billDetail = new BillDetail(bid, pid, buyQuantity, buyPrice, buyQuantity * buyPrice);
                
                int n = dao.AddBillDetail(billDetail);
                if (n > 0) {
                    out.println("Inserted");
                }
                response.sendRedirect("BillDetailControllerURL");
            }

            if (go.equals("listAll")) {
                out.print("<table border = \"1\">\n"
                        + "            <caption>BILL DETAIL LIST</caption>\n"
                        + "            <tr>\n"
                        + "                <th>Bill ID</th>\n"
                        + "                <th>Product ID</th>\n"
                        + "                <th>Buy Quantity</th>\n"
                        + "                <th>Buy Price</th>\n"
                        + "                <th>Subtotal</th>\n"
                        + "                <th>Update</th>\n"
                        + "                <th>Delete</th>\n"
                        + "            </tr>");

                Vector<BillDetail> vector = dao.getAllBillDetail();

                for (BillDetail temp : vector) {
                    out.print("<tr>\n"
                            + "                <td>" + temp.getBid() + "</td>\n"
                            + "                <td>" + temp.getPid() + "</td>\n"
                            + "                <td>" + temp.getBuyQuantity() + "</td>\n"
                            + "                <td>" + temp.getBuyPrice() + "</td>\n"
                            + "                <td>" + temp.getSubtotal() + "</td>\n"
                            + "                <td><a href=\"BillDetailControllerURL?go=update&bid=" + temp.getBid() + "&pid=" + temp.getPid() + "\">Update</a></td>\n"
                            + "                <td><a href=\"BillDetailControllerURL?go=delete&bid=" + temp.getBid() + "&pid=" + temp.getPid() + "\">Delete</a></td>\n"
                            + "</tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"BillDetailManage.html\">Bill Detail Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertBillDetail.html\">Insert Bill Detail</a></td>"
                        + "</tr>");
                out.print("</table>");
            }
            if (go.equals("delete")) {
                String bid = request.getParameter("bid");
                String pid = request.getParameter("pid");
                dao.removeBillDetail(bid, pid);
                response.sendRedirect("BillDetailControllerURL");
            }
            if (go.equals("update")) {
                //Check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) { // hien thi form chua submit
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    Vector<BillDetail> vec = dao.getBillDetail("select * from BillDetail where  bid='" + bid + "' and pid = '" + pid + "'");
                    BillDetail billDetail = vec.get(0);
                    out.print("<form action=\"BillDetailControllerURL\"  method = \"POST\">\n"
                            + "        <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "        <table>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"bid\">Bill ID</label></td>\n"
                            + "                <td><input type=\"text\" name=\"bid\" id = \"bid\" value = \"" + billDetail.getBid() + "\" readonly></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"pid\">Product ID</label></td>\n"
                            + "                <td><input type=\"text\" name=\"pid\" id = \"pid\" value = \"" + billDetail.getPid() + "\" readonly></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"buyQuantity\">Quantity</label></td>\n"
                            + "                <td><input type=\"text\" name=\"buyQuantity\" id = \"buyQuantity\" value = \"" + billDetail.getBuyQuantity() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><input type=\"submit\" value=\"Update Bill Detail\" name=\"submit\"></td>\n"
                            + "            </tr>\n"
                            + "            \n"
                            + "        </table>\n"
                            + "    </form>");
                } else {
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    //Get price then add
                    DAOProduct daoPro = new DAOProduct();
                    Vector<Product> vec = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                    Product product = vec.get(0);
                    double buyPrice = product.getPrice();
                    BillDetail billDetail = new BillDetail(bid, pid, buyQuantity, buyPrice, buyQuantity * buyPrice);
                    
                    int n = dao.update(billDetail);
                    if (n > 0) {
                        out.println("updated");
                    }
                    response.sendRedirect("BillDetailControllerURL");
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
