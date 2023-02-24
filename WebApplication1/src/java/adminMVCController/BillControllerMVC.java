/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOBill;
import entity.Bill;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "BillControllerMVC", urlPatterns = {"/BillControllerMVC"})
public class BillControllerMVC extends HttpServlet {

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
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll";
            }
            if (go.equals("listAll")) {
                request.setAttribute("dataBill", dao.getAllBill());
                request.setAttribute("title", "List of Bill");
                dispath(request, response, "/adminJSP/ViewBill.jsp");
            }
            if (go.equals("insert")) {
                String id = request.getParameter("bid");
                String recAddress = request.getParameter("recAddress");
                String recPhone = request.getParameter("recPhone");
                String note = request.getParameter("note");
                int status = Integer.parseInt(request.getParameter("status"));
                String cid = request.getParameter("cid");
                Bill bill = new Bill(id, recAddress, recPhone, note, status, status, cid);
                dao.AddBill(bill);
                dispath(request, response, "BillControllerMVC?go=listAll");
            }
            if (go.equals("delete")) {
                String bid = request.getParameter("bid");
                dao.removeBill(bid);
                dispath(request, response, "BillControllerMVC?go=listAll");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String bid = request.getParameter("bid");
                    String sql = "select * from Bill where bid = '" + bid + "' ";
                    Vector<Bill> vector = dao.getBill(sql);
                    Bill bill = vector.get(0);
                    request.setAttribute("dataBill", bill);
                    dispath(request, response, "/adminJSP/UpdateBill.jsp");
                } else {
                    String bid = request.getParameter("bid");
                    String recAddress = request.getParameter("recAddress");
                    String recPhone = request.getParameter("recPhone");
                    String note = request.getParameter("note");
                    double totalMoney = 0;
                    String sql = "SELECT B.bid, sum(subtotal) as totalMoney from Bill as B, BillDetail as BD where B.bid = BD.bid and B.bid = '" + bid + "' group by B.bid ";
                    ResultSet rs = dao.getData(sql);
                    if (rs.next()) {
                        totalMoney = rs.getDouble(2);
                    } else {
                        totalMoney = 0;
                    }
                    int status = Integer.parseInt(request.getParameter("status"));
                    String cid = request.getParameter("cid");
                    Bill bill = new Bill(bid, recAddress, recPhone, note, totalMoney, status, cid);
                    int n = dao.update(bill);
                    if (n > 0) {
                        out.println("Updated");
                    }
                    dispath(request, response, "BillControllerMVC?go=listAll");
                }
            }
            if (go.equals("search")) {
                String bid = request.getParameter("bid");
                String sql = "select * from Bill  where bid ='" + bid + "'";
                Vector<Bill> vector = dao.getBill(sql);
                String titleTable = "List of Bill";
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataBill", vector);
                request.setAttribute("title", titleTable);
                dispath(request, response, "/adminJSP/ViewBill.jsp");
            }

        } catch (SQLException ex) {
            Logger.getLogger(BillControllerMVC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void dispath(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
        //run
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
