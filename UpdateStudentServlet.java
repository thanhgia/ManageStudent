/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gia.servlet;

import gia.student.StudentDAO;
import gia.student.StudentError;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "UpdateStudentServlet", urlPatterns = {"/UpdateStudentServlet"})
public class UpdateStudentServlet extends HttpServlet {

    private final String MANAGESTUDENT_PAGE = "manage.jsp";
    private final String LOGIN_PAGE = "login.html";

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
        //input unicode
        request.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String url = MANAGESTUDENT_PAGE;
        StudentError errors = new StudentError();
        boolean foundError = false;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                String id = request.getParameter("txtId");
                String classes = request.getParameter("txtClass").trim();
                String addr1 = request.getParameter("txtAddress1").trim();
                String addr2 = request.getParameter("txtAddress2").trim();
                String status = request.getParameter("cbStatus");
                String phone = request.getParameter("txtPhone").trim();
                String lastSearchValue = request.getParameter("txtLastSearchValue").trim();
                if (classes.length() <= 0 || classes.length() > 6) {
                    foundError = true;
                    errors.setClassLengthError("Class is not null and less than 6 chacracter");
                }
                if (addr1.length() <= 0 || addr1.length() > 250) {
                    foundError = true;
                    errors.setAddress1LengthError("Address1 is not null and less than 250 chacracter");
                }
                if (phone.length() <= 0 || phone.length() > 11) {
                    foundError = true;
                    errors.setPhoneLengthError("Phone is not null and less than 11 chacracter");
                } else {

                    if (!phone.matches("^[0-9]{1,11}")) {
                        foundError = true;
                        errors.setPhoneWrongFormat("Phone wrong format");
                    }

                }
                if (foundError) {
                    request.setAttribute("ERRORUPDATE", errors);
                    request.setAttribute("ERRORINID", id);
                    if (lastSearchValue.isEmpty()) {
                        url = "ShowAllStudentServlet?btAction=Show All";
                    } else {
                        url = "SearchStudentServlet?btAction=Search&txtSearch=" + lastSearchValue;
                    }
                } else {
                    int stt = Integer.parseInt(status);
                    StudentDAO dao = new StudentDAO();
                    boolean result = dao.updateStudent(id, classes, addr1, addr2, stt, phone);
                    if (result) {
                        request.setAttribute("RESULTUPDATE", "Update success");
                        if (lastSearchValue.isEmpty()) {
                            url = "ShowAllStudentServlet?btAction=Show All";
                        } else {
                            url = "SearchStudentServlet?btAction=Search&txtSearch=" + lastSearchValue;
                        }
                    }
                }
            } else {
                url = LOGIN_PAGE;
            }

        } catch (SQLException ex) {
            log(this.getClass().getName() + "_SQL" + ex.getMessage());
        } catch (NamingException ex) {
             log(this.getClass().getName()+ "_Naming" + ex.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
//            response.sendRedirect(url);
            out.close();
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
