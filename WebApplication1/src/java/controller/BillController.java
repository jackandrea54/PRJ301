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
import java.util.Vector;

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
            
            String bid = request.getParameter("bid");
            String recAddress = request.getParameter("recAddress");
            String recPhone = request.getParameter("recPhone");
            String note = request.getParameter("note");
            double totalMoney = Double.parseDouble(request.getParameter("totalMoney"));
            int status = Integer.parseInt(request.getParameter("status"));
            String cid = request.getParameter("cid");
            Bill bill = new Bill(bid, recAddress, recPhone, note, totalMoney, status, cid);
            int n = dao.AddBill(bill);
            if (n > 0) {
                out.println("Inserted");
            }
            
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
                    + "            </tr>");

            Vector<Bill> vector = dao.getAllBill();

            for (Bill temp : vector) {
                out.print("<tr>\n"
                        + "                <td>" + temp.getBid()+ "</td>\n"
                        + "                <td>" + temp.getDateCreate() + "</td>\n"
                        + "                <td>" + temp.getRecAddress()+ "</td>\n"
                        + "                <td>" + temp.getRecPhone()+ "</td>\n"
                        + "                <td>" + temp.getNote() + "</td>\n"
                        + "                <td>" + temp.getTotalMoney() + "</td>\n"
                        + "                <td>" + (temp.getStatus() == 1 ? "Enable" : "Disable") + "</td>\n"
                        + "                <td>" + temp.getCid()+ "</td>\n"
                        + "</tr>");
            }
            out.print("</table>");
            
            
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
