/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DemandesFinancieres;
import Models.DemandesMobilites;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
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
 * @author julien
 */
public class DemandesFinancieresController extends HttpServlet {

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
        //La session en cours
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
            
            if(action.equalsIgnoreCase("all")){
                rd = request.getRequestDispatcher("/indexDF.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("add")){
                rd = request.getRequestDispatcher("/addDemFin.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("addFinal")){
                DemandesFinancieres dem = new DemandesFinancieres();
                dem.setIDCONTRAT(Integer.parseInt(request.getParameter("contrat")));
                dem.setDATEDEPOTDEMFIN(Date.valueOf(request.getParameter("dt")));
                dem.setMONTANTACCORDE(Double.parseDouble(request.getParameter("money")));
                dem.setETATDEMFIN("déposée");
                boolean returnAdd = dem.add();
                if(returnAdd) {
                    session.setAttribute("message", "true");
                    session.setAttribute("demAdded", dem);
                    rd = request.getRequestDispatcher("/indexDF.jsp");
                    rd.forward(request, response);
                } 
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request,response);
                }
            }
            else if(action.equalsIgnoreCase("edit")){
                int demfinID = Integer.parseInt(request.getParameter("id"));
                DemandesFinancieres dem = DemandesFinancieres.getDemFin(demfinID);
                session.setAttribute("dem", dem);
                rd = request.getRequestDispatcher("/editDemFin.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("editFinal")){
                int demfinID = Integer.parseInt(request.getParameter("id"));
                DemandesFinancieres dem = DemandesFinancieres.getDemFin(demfinID); 
                dem.setMONTANTACCORDE(Double.parseDouble(request.getParameter("moneyUpd")));
                dem.setETATDEMFIN(request.getParameter("etatUpd"));
                boolean returnUpdate = dem.update(demfinID);
                if(returnUpdate) {
                    session.setAttribute("message", "true");
                    session.setAttribute("demUpdated", dem);
                    rd = request.getRequestDispatcher("/indexDF.jsp");
                    rd.forward(request, response);
                }  
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }                
            }
            else if(action.equalsIgnoreCase("delete")){
                int demfinID = Integer.parseInt(request.getParameter("id"));
                boolean returnDelete = DemandesFinancieres.delete(demfinID);
                if(returnDelete) {
                    session.setAttribute("message", "true");
                    rd = request.getRequestDispatcher("/indexDF.jsp");
                    rd.forward(request, response);
                }  
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }
            }
            else if(action.equalsIgnoreCase("byContract")){
                rd = request.getRequestDispatcher("/byContract.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("consultByContract")){
                if(request.getParameter("contrat") != "0"){                    
                    int idCon = Integer.parseInt(request.getParameter("contrat"));
                    ArrayList<DemandesFinancieres> listeDemFinByCon = DemandesFinancieres.getAllByContract(idCon);
                    session.setAttribute("demCon", listeDemFinByCon);
                    rd = request.getRequestDispatcher("/byContract.jsp");
                    rd.forward(request, response);
                }
            }
            else if(action.equalsIgnoreCase("byProgram")){
                rd = request.getRequestDispatcher("/byProgram.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("consultByprogram")){
                if(request.getParameter("programme") != "0"){                    
                    int idCon = Integer.parseInt(request.getParameter("programme"));
                    ArrayList<DemandesFinancieres> listeDemFinByPro = DemandesFinancieres.getAllByProgram(idCon);
                    session.setAttribute("demPro", listeDemFinByPro);
                    rd = request.getRequestDispatcher("/byProgram.jsp");
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
            Logger.getLogger(DemandesFinancieresController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DemandesFinancieresController.class.getName()).log(Level.SEVERE, null, ex);
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
