/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBill;
import entity.Bill;
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
@WebServlet(name = "BillController", urlPatterns = {"/BillControllerURL"})
public class BillController extends HttpServlet {

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
        DAOBill dao = new DAOBill();

        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BillController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BillController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");

            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll"; //Default value
            }
            if (go.equals("insert")) {
                String bid = request.getParameter("bid");
                String recAddress = request.getParameter("recAddress");
                String recPhone = request.getParameter("recPhone");
                String note = request.getParameter("note");
                //Start value of total is 0
                double totalMoney = 0;
                int status = Integer.parseInt(request.getParameter("status"));
                String cid = request.getParameter("cid");
                Bill bill = new Bill(bid, recAddress, recPhone, note, totalMoney, status, cid);
                int n = dao.AddBill(bill);
                if (n > 0) {
                    out.println("Inserted");
                }
                response.sendRedirect("BillControllerURL");
            }
            if (go.equals("listAll")) {
                out.print("<table border = \"1\">\n"
                        + "            <caption>PRODUCT LIST</caption>\n"
                        + "            <tr>\n"
                        + "                <th>Bill ID</th>\n"
                        + "                <th>Date Create</th>\n"
                        + "                <th>Address</th>\n"
                        + "                <th>Phone</th>\n"
                        + "                <th>Note</th>\n"
                        + "                <th>Total Money</th>\n"
                        + "                <th>Status</th>\n"
                        + "                <th>Customer ID</th>\n"
                        + "                <th>Update</th>\n"
                        + "                <th>Delete</th>\n"
                        + "            </tr>");

                Vector<Bill> vector = dao.getAllBill();

                for (Bill temp : vector) {
                    out.print("<tr>\n"
                            + "                <td>" + temp.getBid() + "</td>\n"
                            + "                <td>" + temp.getDateCreate() + "</td>\n"
                            + "                <td>" + temp.getRecAddress() + "</td>\n"
                            + "                <td>" + temp.getRecPhone() + "</td>\n"
                            + "                <td>" + temp.getNote() + "</td>\n"
                            + "                <td>" + temp.getTotalMoney() + "</td>\n"
                            + "                <td>" + (temp.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                            + "                <td>" + temp.getCid() + "</td>\n"
                            + "                <td><a href=\"BillControllerURL?go=update&bid=" + temp.getBid() + "\">Update</a></td>\n"
                            + "                <td><a href=\"BillControllerURL?go=delete&bid=" + temp.getBid() + "\">Delete</a></td>\n"
                            + "</tr>");
                }
                out.println("<tr>"
                        + "<td><a href=\"BillManage.html\">Bill  Manage</a></td>"
                        + "<td><a href=\"./InsertPage/InsertBill.html\">Insert Bill</a></td>"
                        + "</tr>");
                out.print("</table>");
            }
            if (go.equals("delete")) {
                String bid = request.getParameter("bid");
                int n = dao.removeBill(bid);
                response.sendRedirect("BillControllerURL");
            }

            if (go.equals("update")) {
                //Check hien thi form hay update bang submit
                String submit = request.getParameter("submit");
                if (submit == null) { // hien thi form chua submit
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    Vector<Bill> vec = dao.getBill("select * from Bill where  bid= '" + bid + "'");
                    Bill bill = vec.get(0);
                    out.print("<form action=\"BillControllerURL\"  method = \"POST\">\n"
                            + "        <input type=\"hidden\" name=\"go\" value=\"update\">\n"
                            + "        <table>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"bid\">Bill ID</label></td>\n"
                            + "                <td><input type=\"text\" name=\"bid\" id = \"bid\" value = \"" + bill.getBid() + "\" readonly></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"recAddress\">Address</label></td>\n"
                            + "                <td><input type=\"text\" name=\"recAddress\" id = \"recAddress\" value = \"" + bill.getRecAddress() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"recPhone\">Phone number</label></td>\n"
                            + "                <td><input type=\"text\" name=\"recPhone\" id = \"recPhone\" value = \"" + bill.getRecPhone() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"note\">Note</label></td>\n"
                            + "                <td><input type=\"text\" name=\"note\" id = \"note\" value = \"" + bill.getNote() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"status\">status</label></td>\n"
                            + "                <td>\n"
                            + "                    <input type=\"radio\" name=\"status\" id = \"status\" value=\"1\" " + (bill.getStatus() == 1 ? "checked" : "") + "> Enable\n"
                            + "                    <input type=\"radio\" name=\"status\" id = \"status\" value=\"0\" " + (bill.getStatus() == 0 ? "checked" : "") + " > Disable\n"
                            + "                </td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><label for=\"cid\">Customer ID</label></td>\n"
                            + "                <td><input type=\"text\" name=\"cid\" id = \"cid\" value = \"" + bill.getCid() + "\"></td>\n"
                            + "            </tr>\n"
                            + "            <tr>\n"
                            + "                <td><input type=\"submit\" value=\"Update Bill \" name=\"submit\"></td>\n"
                            + "            </tr>\n"
                            + "            \n"
                            + "        </table>\n"
                            + "    </form>");
                } else {
                    String bid = request.getParameter("bid");
                    String recAddress = request.getParameter("recAddress");
                    String recPhone = request.getParameter("recPhone");
                    String note = request.getParameter("note");
                    String sql = "SELECT B.bid, sum(subtotal) as totalMoney from Bill as B, BillDetail as BD where B.bid = BD.bid and B.bid = '" + bid + "' group by B.bid ";
                    ResultSet rs = dao.getData(sql);
                    if (rs.next()) {
                        double totalMoney = rs.getDouble(2);
                        out.print(totalMoney + "");
                        int status = Integer.parseInt(request.getParameter("status"));
                        String cid = request.getParameter("cid");
                        Bill bill = new Bill(bid, recAddress, recPhone, note, totalMoney, status, cid);
                        int n = dao.update(bill);
                        if (n > 0) {
                            out.println("Updated");
                        }
                    }
                    response.sendRedirect("BillControllerURL");
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BillController.class.getName()).log(Level.SEVERE, null, ex);
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
