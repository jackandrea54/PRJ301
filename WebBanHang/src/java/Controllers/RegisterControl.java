/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controllers;

import DAL.DAO;
import MyLibs.libs;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
public class RegisterControl extends HttpServlet {

    DAO d;

    public void init() {
        d = new DAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("Register.jsp").forward(request, response);
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
        request.setCharacterEncoding("UTF-8");
        String fullname = request.getParameter("fullname");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        String confirmPass = request.getParameter("confirmPass");

        //kiem tra input nguoi dung
        //kiem tra dau cach
        if (fullname == null || fullname.trim().isEmpty()
                || username == null || username.trim().isEmpty()
                || email == null || email.trim().isEmpty()
                || phoneNumber == null || phoneNumber.trim().isEmpty()
                || password == null || password.trim().isEmpty()
                || confirmPass == null || confirmPass.trim().isEmpty()) {
            request.setAttribute("error", "You must fill all field!");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else {
            //fullname chua moi letter va ko co dau cach dau ten
            if (!libs.isValidFullname(fullname)) {
                request.setAttribute("fullnameError", fullname);
                request.setAttribute("usernameError", username);
                request.setAttribute("emailError", email);
                request.setAttribute("phoneNumberError", phoneNumber);
                request.setAttribute("error", "Invalid fullname!");
                request.getRequestDispatcher("Register.jsp").forward(request, response);
            } else {
                //check if username has character and number 
                if (username.length() > 15 || !libs.isIncludeAlphabet(username) || !libs.isIncludeDigits(username) || libs.isIncludeSpecialChars(username)) {
                    request.setAttribute("fullnameError", fullname);
                    request.setAttribute("usernameError", username);
                    request.setAttribute("emailError", email);
                    request.setAttribute("phoneNumberError", phoneNumber);
                    request.setAttribute("error", "Username must contain at most 15 characters and contain only letters and numbers");
                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                } else {
                    //kiem tra username da ton tai trong database
                    if (d.checkUsernameSame(username)) {
                        request.setAttribute("fullnameError", fullname);
                        request.setAttribute("usernameError", username);
                        request.setAttribute("emailError", email);
                        request.setAttribute("phoneNumberError", phoneNumber);
                        request.setAttribute("error", "Username has already exist!");
                        request.getRequestDispatcher("Register.jsp").forward(request, response);
                    } else {
                        //kiem tra phone number dung
                        if (!libs.isValidPhoneNumber(phoneNumber)) {
                            request.setAttribute("fullnameError", fullname);
                            request.setAttribute("usernameError", username);
                            request.setAttribute("emailError", email);
                            request.setAttribute("phoneNumberError", phoneNumber);
                            request.setAttribute("error", "Invalid Phone Number!");
                            request.getRequestDispatcher("Register.jsp").forward(request, response);
                        } else {
                            //check if password contains letters, digits, special char, not contain whitespace, atleast 8 characters
                            if (!libs.isValidPassword(password)) {
                                request.setAttribute("fullnameError", fullname);
                                request.setAttribute("usernameError", username);
                                request.setAttribute("emailError", email);
                                request.setAttribute("phoneNumberError", phoneNumber);
                                request.setAttribute("error", "Password must contain letters, digits, special characters and at least 8 characters");
                                request.getRequestDispatcher("Register.jsp").forward(request, response);
                            } else {
                                //kiem tra pass trung voi confirm
                                if (!password.equals(confirmPass)) {
                                    request.setAttribute("fullnameError", fullname);
                                    request.setAttribute("usernameError", username);
                                    request.setAttribute("emailError", email);
                                    request.setAttribute("phoneNumberError", phoneNumber);
                                    request.setAttribute("error", "Password and Confirm Password is not the same!");
                                    request.getRequestDispatcher("Register.jsp").forward(request, response);
                                } else {
                                    d.insertAccount(fullname, username, email, phoneNumber, password, 2);
                                    response.sendRedirect("Login");
                                }
                            }
                        }
                    }
                }
            }
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
