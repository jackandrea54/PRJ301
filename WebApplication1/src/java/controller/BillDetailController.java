/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DAOBillDetail;
import entity.BillDetail;
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
            
            String bid = request.getParameter("bid");
            String pid = request.getParameter("pid");
            int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
            double buyPrice = Double.parseDouble(request.getParameter("buyPrice"));
            
            BillDetail billDetail = new BillDetail(bid, pid, buyQuantity, buyPrice, buyQuantity * buyPrice);
            int n = dao.AddBillDetail(billDetail);
            if (n > 0) {
                out.println("Inserted");
            }
            
            out.print("<table border = \"1\">\n"
                    + "            <caption>PRODUCT LIST</caption>\n"
                    + "            <tr>\n"
                    + "                <th>Bill ID</th>\n"
                    + "                <th>Product ID</th>\n"
                    + "                <th>Buy Quantity</th>\n"
                    + "                <th>Buy Price</th>\n"
                    + "                <th>Subtotal</th>\n"
                    + "            </tr>");

            Vector<BillDetail> vector = dao.getAllBillDetail();

            for (BillDetail temp : vector) {
                out.print("<tr>\n"
                        + "                <td>" + temp.getBid()+ "</td>\n"
                        + "                <td>" + temp.getPid() + "</td>\n"
                        + "                <td>" + temp.getBuyQuantity()+ "</td>\n"
                        + "                <td>" + temp.getBuyPrice()+ "</td>\n"
                        + "                <td>" + temp.getSubtotal()+ "</td>\n"
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
