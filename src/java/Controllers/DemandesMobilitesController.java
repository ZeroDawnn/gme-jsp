/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Models.DemandesMobilites;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.min;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
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
public class DemandesMobilitesController extends HttpServlet {

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
                rd = request.getRequestDispatcher("/index.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("add")){
                rd = request.getRequestDispatcher("/addDemMob.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("addFinal")){                
                DemandesMobilites dem = new DemandesMobilites();
                dem.setNUMETUDIANT(Integer.parseInt(request.getParameter("etudiant")));
                dem.setCODEDIP(Integer.parseInt(request.getParameter("diplome")));
                dem.setDATEDEPOTDEMMOB(Date.valueOf(request.getParameter("dt")));
                dem.setETATDEMMOB("déposée");
                boolean returnAdd = dem.add();
                if(returnAdd) {
                    session.setAttribute("message", "true");
                    session.setAttribute("demAdded", dem);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }  
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }                
            }
            else if(action.equalsIgnoreCase("edit")){
                int demmobID = Integer.parseInt(request.getParameter("id"));
                DemandesMobilites dem = DemandesMobilites.getDemMob(demmobID);
                session.setAttribute("dem", dem);
                rd = request.getRequestDispatcher("/editDemMob.jsp");
                rd.forward(request, response);
            }
            else if(action.equalsIgnoreCase("editFinal")){
                int demmobID = Integer.parseInt(request.getParameter("id"));
                DemandesMobilites dem = DemandesMobilites.getDemMob(demmobID);  
                dem.setETATDEMMOB(request.getParameter("etatUpd"));
                boolean returnUpdate = dem.update(demmobID);
                if(returnUpdate) {
                    session.setAttribute("message", "true");
                    session.setAttribute("demUpdated", dem);
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }  
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }                
            }
            else if(action.equalsIgnoreCase("delete")){
                int demmobID = Integer.parseInt(request.getParameter("id"));
                boolean returnDelete = DemandesMobilites.delete(demmobID);
                if(returnDelete) {
                    session.setAttribute("message", "true");
                    rd = request.getRequestDispatcher("/index.jsp");
                    rd.forward(request, response);
                }  
                else {
                    session.setAttribute("message", "false");
                    rd = request.getRequestDispatcher("/error.html");
                    rd.forward(request, response);
                }
            }
            else if(action.equalsIgnoreCase("byStudent")){
                rd = request.getRequestDispatcher("/byStudent.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("consultByStudent")){
                if(request.getParameter("etudiant") != "0"){                    
                    int numEtu = Integer.parseInt(request.getParameter("etudiant"));
                    ArrayList<DemandesMobilites> listeDemMobByStu = DemandesMobilites.getAllByStudent(numEtu);
                    session.setAttribute("demStu", listeDemMobByStu);
                    rd = request.getRequestDispatcher("/byStudent.jsp");
                    rd.forward(request, response);
                }            
            }
            else if(action.equalsIgnoreCase("byUniv")){
                rd = request.getRequestDispatcher("/byUniv.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("consultByUniv")){
                if(request.getParameter("univ") != "0"){                    
                    int numUni = Integer.parseInt(request.getParameter("univ"));
                    ArrayList<DemandesMobilites> listeDemMobByUni = DemandesMobilites.getAllByUniv(numUni);
                    session.setAttribute("demUni", listeDemMobByUni);
                    rd = request.getRequestDispatcher("/byUniv.jsp");
                    rd.forward(request, response);
                }          
            }
            else if(action.equalsIgnoreCase("byDiploma")){
                rd = request.getRequestDispatcher("/byDiploma.jsp");
                rd.forward(request,response);
            }
            else if(action.equalsIgnoreCase("consultByDiploma")){
                if(request.getParameter("diplome") != "0"){                    
                    int codeDip = Integer.parseInt(request.getParameter("diplome"));
                    ArrayList<DemandesMobilites> listeDemMobByDip = DemandesMobilites.getAllByDiploma(codeDip);
                    session.setAttribute("demDip", listeDemMobByDip);
                    rd = request.getRequestDispatcher("/byDiploma.jsp");
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
            Logger.getLogger(DemandesMobilitesController.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DemandesMobilitesController.class.getName()).log(Level.SEVERE, null, ex);
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
