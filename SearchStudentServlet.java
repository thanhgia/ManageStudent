/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gia.servlet;

import gia.student.StudentDAO;
import gia.student.StudentDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "SearchStudentServlet", urlPatterns = {"/SearchStudentServlet"})
public class SearchStudentServlet extends HttpServlet {
    
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        String searchValue = request.getParameter("txtSearch").trim();
        int searchStatus = -1;
        String url = MANAGESTUDENT_PAGE;
        
        try {
            HttpSession session = request.getSession(false);
            if (session != null) {
                
                if (!searchValue.equalsIgnoreCase("")) {
                    
                    StudentDAO dao = new StudentDAO();

                    //check searchValue is status or not 
                    if (searchValue.equalsIgnoreCase("studying")) {
                        searchStatus = 0;
                    } else if (searchValue.equalsIgnoreCase("break")) {
                        searchStatus = 1;
                    } else if (searchValue.equalsIgnoreCase("dropout")) {
                        searchStatus = 2;
                    }

                    //search student by id
                    dao.searchStudentById(searchValue);

                    // searchStatus > -1 call searchStudentByStatus
                    if (searchStatus >= 0) {
                        dao.searchStudentByStatus(searchStatus);
                    }

                    //search student by substring of name
                    dao.searchStudentByFirstAndLastName(searchValue);
                    dao.searchStudentByFullName(searchValue);

                    //get list student 
                    List<StudentDTO> result = dao.getListStudent();
                    
                    if (result != null) {
                        session.removeAttribute("LASTSEARCH");
                        session.setAttribute("LASTSEARCH", searchValue);
                        request.setAttribute("RESULT", result);
                        request.setAttribute("NUMBERSTUDENT", result.size());
                        url = MANAGESTUDENT_PAGE;
                    }
                } else {
                    url = MANAGESTUDENT_PAGE;
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
