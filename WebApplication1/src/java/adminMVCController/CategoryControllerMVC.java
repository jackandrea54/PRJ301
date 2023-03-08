/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package adminMVCController;

import dao.DAOCategory;
import entity.Category;
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
@WebServlet(name = "CategoryControllerMVC", urlPatterns = {"/CategoryControllerMVC"})
public class CategoryControllerMVC extends HttpServlet {

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
            HttpSession session = request.getSession();
            if (session.getAttribute("admin") != null) {
                String go = request.getParameter("go");
                //if call servlet direct --> go = null
                if (go == null) {
                    go = "listAll"; //Default value
                }
                if (go.equals("listAll")) {
                    Vector<Category> vector = dao.getAllCategory();
                    String titleTable = "List of Category";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataCate", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewCategory.jsp");
                }
                if (go.equals("insert")) {
                    String cateName = request.getParameter("cateName");
                    int status = Integer.parseInt(request.getParameter("status"));
                    Category cate = new Category(cateName, status);
                    int n = dao.AddCategory(cate);
                    if (n > 0) {
                        out.print("Inserted");
                    }
                    dispath(request, response, "CategoryControllerMVC?go=listAll");
                }

                if (go.equals("delete")) {
                    String cateID = request.getParameter("cateID");
                    dao.removeCategory(cateID);
                    dispath(request, response, "CategoryControllerMVC?go=listAll");
                }
                if (go.equals("update")) {
                    //Check hien thi form hay update bang submit
                    String submit = request.getParameter("submit");
                    if (submit == null) { // hien thi form chua submit
                        // lay ra ban ghi can hien thi
                        String cateID = request.getParameter("cateID");
                        Vector<Category> vec = dao.getCategory("select * from Category where cateID ='" + cateID + "'");
                        Category cate = vec.get(0);
                        request.setAttribute("dataCate", cate);
                        dispath(request, response, "/adminJSP/UpdateCategory.jsp");
                    } else {// da submit
                        int cateID = Integer.parseInt(request.getParameter("cateID"));
                        String cateName = request.getParameter("cateName");
                        int status = Integer.parseInt(request.getParameter("status"));
                        Category cate = new Category(cateID, cateName, status);
                        int n = dao.update(cate);
                        dispath(request, response, "CategoryControllerMVC?go=listAll");
                    }
                }
                if (go.equals("search")) {
                    String cateName = request.getParameter("cateName");
                    String sql = "select * from Category where cateName ='" + cateName + "'";
                    Vector<Category> vector = dao.getCategory(sql);
                    String titleTable = "List of Category";
                    //Chuan bi du lieu cho jsp
                    request.setAttribute("dataCate", vector);
                    request.setAttribute("title", titleTable);
                    dispath(request, response, "/adminJSP/ViewCategory.jsp");
                }
            }
        }
    }

    void dispath(HttpServletRequest request, HttpServletResponse response, String url)
            throws ServletException, IOException {
        RequestDispatcher disp = request.getRequestDispatcher(url);
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
