/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package clientMVCController;

import dao.DAOBill;
import dao.DAOBillDetail;
import dao.DAOCustomer;
import dao.DAOProduct;
import entity.Bill;
import entity.BillDetail;
import entity.Product;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

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
            HttpSession session = request.getSession();
            DAOBill daoBill = new DAOBill();
            DAOBillDetail daoBillDe = new DAOBillDetail();
            DAOCustomer daoCus = new DAOCustomer();
            String go = request.getParameter("go");

            if (session.getAttribute("cid") != null) {
                if (go.equals("show")) {
                    Vector<Product> vecPro = new Vector<>();
                    Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String pid = em.nextElement().toString(); //get key
                        int slNo = 1;
                        if (!(pid.equals("cid") || pid.equals("username"))) { //If the key is the value of pid then
                            int quantity = Integer.parseInt(session.getAttribute(pid).toString()); //get product quantity
                            //Add Product infomation
                            DAOProduct daoPro = new DAOProduct();
                            Vector<Product> vecTemp = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                            Product product = vecTemp.get(0);
                            out.println("<tr class=\"rem\">\n"
                                    + "								<td class=\"invert\" id=slNo" + slNo + ">" + slNo + "</td>\n"
                                    + "								<td class=\"invert-image\"><a href=\"./single.jsp?pid=" + product.getPid() + "\"><img src=\"" + product.getImage() + "\" alt=\" \" class=\"img-responsive\" style= \"height: 10em;\"></a></td>\n"
                                    + "								<td class=\"invert\">\n"
                                    + "									<div class=\"quantity\">\n"
                                    + "										<div class=\"quantity-select\">\n"
                                    + "											<div class=\"entry value\"><input type=\"text\" id=\"quantity\" name=\"quantity\" value = \"" + quantity + "\" style=\"max-width:100%;\"></div>\n"
                                    + "										</div>\n"
                                    + "									</div>\n"
                                    + "								</td>\n"
                                    + "								<td class=\"invert\">" + product.getPname() + "</td>\n"
                                    + "\n"
                                    + "								<td class=\"invert\">$" + (product.getPrice() * quantity ) + "</td>\n"
                                    + "								<td class=\"invert\">\n"
                                    + "									<div class=\"rem\">\n"
                                    + "										<div class=\"close1\"> </div>\n"
                                    + "									</div>\n"
                                    + "\n"
                                    + "								</td>\n"
                                    + "							</tr>");
                            slNo++;
                        }
                    }
                }

                //Show the total money for checkout
                if (go.equals("showTotal")) {
                    Vector<Product> vecPro = new Vector<>();
                    Enumeration em = session.getAttributeNames();
                    DAOProduct daoPro = new DAOProduct();
                    double total = 0;
                    while (em.hasMoreElements()) {
                        String pid = em.nextElement().toString(); //get key
                        if (!(pid.equals("cid") || pid.equals("username"))) { //If the key is the value of pid then
                            int quantity = Integer.parseInt(session.getAttribute(pid).toString()); //get product quantity
                            //Add Product infomation
                            Vector<Product> vecTemp = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                            Product product = vecTemp.get(0);
//                        product.setQuantity(quantity); //Change the quantity
//                        vecPro.add(product);
                            double totalPro = quantity * product.getPrice();
                            total += totalPro;
                            out.print("<li>" + product.getPname() + "    <i>-</i> <span>$" + (product.getPrice() * quantity) + "</span></li>");
                        }
                    }
                    out.print("<li>Total <i>-</i> <span>$" + total + "</span></li>");
                }

                //Add new item to cart
                if (go.equals("add")) {
                    int quantity = 0;
                    String quantityString = request.getParameter("quantity");
                    if (quantityString == null) {
                        quantity = 1;
                    } else {
                        try {
                            quantity = Integer.parseInt(quantityString); //Try if the string is a integer
                        } catch (Exception e) {
                        }
                    }
                    if (quantity != 0) {
                        String pid = request.getParameter("pid");
                        Object value = session.getAttribute(pid);
                        if (value == null) {
                            session.setAttribute(pid, quantity);
                        } else {
                            int count = (Integer) value + quantity;
                            session.setAttribute(pid, count);
                        }
                    }
                }

                //Show cart for checkout
                //Checkout the cart
                if (go.equals("checkout")) {
                    String username = (String) session.getAttribute("username");
                    String cid = (String) session.getAttribute("cid");
                    String name = request.getParameter("name");
                    String phone = request.getParameter("phone");
                    String address = request.getParameter("address");
                    String note = request.getParameter("note");
                    System.out.println(name + phone + address);
                    Bill bill = null;
                    String bid = "";

                    //Get customer info
//                    Vector<Customer> vector = daoCus.getCustomer("select * from Customer where cid = '" + cid + "'");
//                    Customer cus = vector.get(0);
                    Enumeration em = session.getAttributeNames();
                    while (em.hasMoreElements()) {
                        String pid = em.nextElement().toString(); //get key

                        if (!(pid.equals("cid") || pid.equals("username"))) { //If the key is the value of pid then
                            int quantity = Integer.parseInt(session.getAttribute(pid).toString()); //get product quantity
                            //Create a new bill with the Customer ID
                            if (bill == null) {
                                bill = new Bill(address, phone, note, 0, 1, cid);
                                bid = String.valueOf(daoBill.AddAndGetBill(bill)); //Get the new created Bill ID 
                            }

                            //Get Product infomation
                            DAOProduct daoPro = new DAOProduct();
                            Vector<Product> vec = daoPro.getProduct("select * from Product where  pid = '" + pid + "'");
                            Product product = vec.get(0);
                            double buyPrice = product.getPrice();

                            //Create a new Bill Detail
                            BillDetail billDe = new BillDetail(bid, pid, quantity, buyPrice, quantity * buyPrice);
                            daoBillDe.AddBillDetail(billDe);
                        }
                    }
                    //Finish transport all order, terminate all session cart but 
                    //cid and username
                    session.invalidate();
                    session = request.getSession();
                    session.setAttribute("cid", cid);
                    session.setAttribute("username", username);
                    response.sendRedirect("./shop");
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
