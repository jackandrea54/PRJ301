/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOBillDetail;
import dao.DAOProduct;
import entity.BillDetail;
import entity.Product;
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
@WebServlet(name = "BillDetailControllerMVC", urlPatterns = {"/BillDetailControllerMVC"})
public class BillDetailControllerMVC extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            DAOBillDetail dao = new DAOBillDetail();
            HttpSession session = request.getSession();
            session.setMaxInactiveInterval(3600);
            if (session.getAttribute("admin") != null) {
                String go = request.getParameter("go");
                if (go == null) {
                    go = "listAll"; //Default value
                }
                if (go.equals("listAll")) {
                    request.setAttribute("dataBillDe", dao.getAllBillDetail());
                    request.setAttribute("title", "List of Bill Detail");
                    dispath(request, response, "/adminJSP/ViewBillDetail.jsp");
                }
                if (go.equals("insert")) {
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    int buyQuantity = Integer.parseInt(request.getParameter("buyQuantity"));
                    //Get price then add
                    DAOProduct daoPro = new DAOProduct();
                    Vector<Product> vec = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                    try {
                        Product product = vec.get(0); //try catch vi cai nay de bi out of index do không tôn tai product nào
                        double buyPrice = product.getPrice();
                        BillDetail billDetail = new BillDetail(bid, pid, buyQuantity, buyPrice, buyQuantity * buyPrice);
                        int n = dao.AddBillDetail(billDetail);
                        dispath(request, response, "BillDetailControllerMVC?go=listAll");
                    } catch (Exception e) {
                        out.print(e);
                        dispath(request, response, "BillDetailControllerMVC?go=listAll");
                    }
                }

                if (go.equals("delete")) {
                    String bid = request.getParameter("bid");
                    String pid = request.getParameter("pid");
                    dao.removeBillDetail(bid, pid);
                    dispath(request, response, "BillDetailControllerMVC?go=search&bid="+bid);
                }
                if (go.equals("update")) {
                    //Check hien thi form hay update bang submit
                    String submit = request.getParameter("submit");
                    if (submit == null) { // hien thi form chua submit
                        String bid = request.getParameter("bid");
                        String pid = request.getParameter("pid");
                        Vector<BillDetail> vec = dao.getBillDetail("select * from BillDetail where  bid='" + bid + "' and pid = '" + pid + "'");
                        BillDetail billDetail = vec.get(0);
                        request.setAttribute("dataBillDe", billDetail);
                        dispath(request, response, "/adminJSP/UpdateBillDetail.jsp");
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
                        dispath(request, response, "BillDetailControllerMVC?go=listAll");
                    }
                }
                if (go.equals("search")) {
                    String bid = request.getParameter("bid");
                    String sql = "select * from BillDetail where bid ='" + bid + "'";
                    Vector<BillDetail> vector = dao.getBillDetail(sql);
                    String titleTable = "List of Bill Detail";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataBillDe", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewBillDetail.jsp");
                }
            }
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
