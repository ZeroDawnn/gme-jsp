/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.Contrats;
import Models.DemandesMobilites;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author jtrescartes
 */
public class GlobalController extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        
        //Le dispatcher de requêtes
        RequestDispatcher rd = null;
        
        //Vérification session
        if(session == null){
            rd = request.getRequestDispatcher("/error.html");
            rd.forward(request,response);
        }else{ //Tout va bien => faire routage
            //String controller = request.getParameter("controller") --> version propre
            String action = request.getParameter("action");
            
            if(action.equalsIgnoreCase("progCont")){
                rd = request.getRequestDispatcher("/progCont.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("consultContrat")){
                if(request.getParameter("programme") != "0"){                    
                    int idP = Integer.parseInt(request.getParameter("programme"));
                    ArrayList<Contrats> listeCont = Contrats.getAllByProgram(idP);
                    session.setAttribute("contratAssoc", listeCont);
                    rd = request.getRequestDispatcher("/progCont.jsp");
                    rd.forward(request, response);
                } 
            }
            else{
                rd = request.getRequestDispatcher("/error.html");
                rd.forward(request,response);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GlobalController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(GlobalController.class.getName()).log(Level.SEVERE, null, ex);
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
