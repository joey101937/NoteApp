/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Database.DatabaseController;
import Database.entities.Note;
import Database.entities.User;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Joseph
 */
@WebServlet(name = "HomeServlet", urlPatterns = {"/HomeServlet"})
public class HomeServlet extends HttpServlet {

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
        String url = "home.jsp";
        try {
            switch (request.getParameter("action")) {
                case "signin":  //signing in for first time visiting site
                    System.out.println("signing in... " + request.getParameter("username"));
                    User u = DatabaseController.getUserByName(request.getParameter("username"));
                    if (u == null) {
                        //new user, not found in DB, so create new one
                        System.out.println("creating a new user");
                        u = new User(request.getParameter("username"));
                        DatabaseController.save(u);
                    }
                    request.getSession().setAttribute("theUser", u);
                    request.getSession().setAttribute("notes", DatabaseController.getNotesByUser(u));
                    loadHomePage(request,response);
                    return;
                case "loadHome": //loads home.jsp
                    loadHomePage(request,response);
                    return;
                case "signout": //removes user from session and returns to login page
                    request.getSession().setAttribute("theUser", null);
                    url = "/index.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                    return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            url = "/index.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        }
    }

    private void loadHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("theUser") == null) {
            String url = "/index.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
            System.out.println("returning to index, null user");
            return;
        }
        //set active note(s)
        User theUser = (User)request.getSession().getAttribute("theUser");
        System.out.println("loading homepage for user " + theUser);
        request.getSession().setAttribute("notes", DatabaseController.getNotesByUser(theUser));
        System.out.println("done getting that user's notes");
        if (request.getSession().getAttribute("activeNote") == null) {
            List<Note> userNotes = DatabaseController.getNotesByUser((User) request.getSession().getAttribute("theUser"));
            if (!userNotes.isEmpty()) {
                request.getSession().setAttribute("activeNote", userNotes.get(0));
            }
        }
        String url = "/home.jsp";
        getServletContext().getRequestDispatcher(url).forward(request, response);
        return;
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
