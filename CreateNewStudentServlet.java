/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gia.servlet;

import gia.student.StudentDAO;
import gia.student.StudentDTO;
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
@WebServlet(name = "CreateNewStudentServlet", urlPatterns = {"/CreateNewStudentServlet"})
public class CreateNewStudentServlet extends HttpServlet {

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
        String id = null;
        String url = MANAGESTUDENT_PAGE;
        StudentError errors = new StudentError();
        boolean foundError = false;

        String lastSearchValue = null;

        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                id = request.getParameter("txtId").trim();
                String firstName = request.getParameter("txtFirstName").trim();
                String middleName = request.getParameter("txtMiddleName").trim();
                String lastName = request.getParameter("txtLastName").trim();
                boolean sex = false;
                if (request.getParameter("chkSex") != null) {
                    sex = true;
                }
                String addr1 = request.getParameter("txtAddress1").trim();
                String addr2 = request.getParameter("txtAddress2").trim();
                String phone = request.getParameter("txtPhone").trim();
                String classes = request.getParameter("txtClass").trim();
                
                String button = request.getParameter("button");
                lastSearchValue = (String) session.getAttribute("LASTSEARCH");
                
                if (id.length() <= 0 || id.length() >= 10) {
                    foundError = true;
                    errors.setIdLengthError("Id is not null and less than 10 chacracter");
                }
                if (firstName.length() <= 0 || firstName.length() > 20) {
                    foundError = true;
                    errors.setFirstNameLengthError("First name is not null and less than 20 chacracter");
                }
                if (middleName.length() > 20) {
                    foundError = true;
                    errors.setMiddleNameLengthError("Middle name is less than 20 chacracter");
                }
                if (lastName.length() <= 0 || lastName.length() > 20) {
                    foundError = true;
                    errors.setLastNameLengthError("Last name is not null and less than 20 chacracter");
                }
                if (addr1.length() <= 0 || addr1.length() > 250) {
                    foundError = true;
                    errors.setAddress1LengthError("Address1 is not null and less than 250 chacracter");
                }
                if (phone.length() < 0 || phone.length() > 11) {
                    foundError = true;
                    errors.setPhoneLengthError("Phone is not null and less than 11 chacracter");
                } else {

                    if (!phone.matches("^[0-9]{1,11}")) {
                        foundError = true;
                        errors.setPhoneWrongFormat("Phone wrong format");
                    }

                }
                if (classes.length() <= 0 || classes.length() > 6) {
                    foundError = true;
                    errors.setClassLengthError("Class is not null and less than 6 chacracter");
                }

                if (foundError) {
                    request.setAttribute("ERROR", errors);
                    if (button.equalsIgnoreCase("Search")) {
                        url = "SearchStudentServlet?btAction=Search&txtSearch=" + lastSearchValue;
                    } else if (button.equalsIgnoreCase("Show all")) {
                        url = "ShowAllStudentServlet?btAction=Show All";
                    }
                } else {

                    StudentDTO dto = new StudentDTO(id, firstName, middleName, lastName, classes, 1, addr1, addr2, phone, sex);
                    StudentDAO dao = new StudentDAO();
                    boolean result = dao.insertStudent(dto);
                    if (result) {
                        if (button.equalsIgnoreCase("Search")) {
                            url = "SearchStudentServlet?btAction=Search&txtSearch=" + lastSearchValue;
                        } else if (button.equalsIgnoreCase("Show all")) {
                            url = "ShowAllStudentServlet?btAction=Show All";
                        }
                        request.setAttribute("INSERTSUCCESS", "Create success");
                    }

                }
            } else {
                url = LOGIN_PAGE;
            }
        } catch (SQLException ex) {
            log(this.getClass().getName() + "_SQL" + ex.getMessage());
            if (ex.getMessage().contains("duplicate")) {
                errors.setIdIsExist(id + " is existed");
                request.setAttribute("ERROR", errors);
            }
        } catch (NamingException ex) {
            log(this.getClass().getName()+ "_Naming" + ex.getMessage());
            
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
