/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOProduct;
import entity.Product;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "ProductControllerMVC", urlPatterns = {"/ProductControllerMVC"})
public class ProductControllerMVC extends HttpServlet {

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
            DAOProduct dao = new DAOProduct();
            String go = request.getParameter("go");
            if (go == null) {
                go = "listAll"; // default value
            }
            if (go.equals("listAll")) {
                String sql = "select * from Product as a join Category "
                        + " as b on a.cateId=b.cateId";
//                Vector<Product> vector = dao.getProduct(sql);
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Product";
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataPro", rs);
                request.setAttribute("title", titleTable);
//                //Call jsp (khong dung sendDirect duoc vi no khong truyen duoc doi tuong)
//                RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/ViewProduct.jsp");
//                //Run
//                dispath.forward(request, response);
                dispath(request, response, "/adminJSP/ViewProduct.jsp");
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
                dispath(request, response, "ProductControllerMVC?go=listAll");
            }
            if (go.equals("delete")) {
                String pid = request.getParameter("pid");
                dao.removeProduct(pid);
                dispath(request, response, "ProductControllerMVC?go=listAll");
            }
            if (go.equals("update")) {
                String submit = request.getParameter("submit");
                if (submit == null) {
                    String pid = request.getParameter("pid");
                    int cateID = Integer.parseInt(request.getParameter("cateID"));
                    String sql = "select * from Product where pid = '" + pid + "' ";
                    Vector<Product> vector = dao.getProduct(sql);
                    ResultSet rs = dao.getData("select * from Category");
                    request.setAttribute("dataPro", vector);
                    request.setAttribute("cateID", cateID);
                    request.setAttribute("dataCate", rs);
                    dispath(request, response, "/adminJSP/UpdateProduct.jsp");
                } else {
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

                    dispath(request, response, "ProductControllerMVC?go=listAll");
                }
            }
            if (go.equals("search")) {
                String pname = request.getParameter("pname");
                String sql = "select * from Product as a join Category as b on a.cateId=b.cateId where pname ='" + pname + "'";
//                Vector<Product> vector = dao.getProduct(sql);
                ResultSet rs = dao.getData(sql);
                String titleTable = "List of Product";
                //Chuan bi du lieu cho jsp
                request.setAttribute("dataPro", rs);
                request.setAttribute("title", titleTable);
//                //Call jsp (khong dung sendDirect duoc vi no khong truyen duoc doi tuong)
//                RequestDispatcher dispath = request.getRequestDispatcher("/adminJSP/ViewProduct.jsp");
//                //Run
//                dispath.forward(request, response);
                dispath(request, response, "/adminJSP/ViewProduct.jsp");
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
