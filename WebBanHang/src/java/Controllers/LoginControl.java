/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class LoginControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
//        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //lay du lieu tu trang login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //lay pass tu database
        String checkPassword = d.getPassword(username);
        //check pass word in database, if true save username to database
        if (password.equals(checkPassword)) {
            //creating cookie object  
            Cookie user = new Cookie("username", username);
            
            response.addCookie(user);//adding cookie in the response  
            response.sendRedirect("Home");
        } else {
            //if user's input name is not empty but password is not correct, save username for try again 
            if (!username.trim().equals("")) {
                request.setAttribute("userWrong", username);
            }
            request.setAttribute("error", "Wrong username or password!");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
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
