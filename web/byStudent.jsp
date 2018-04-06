<%-- 
    Document   : byStudent
    Created on : 29 mars 2018, 22:13:01
    Author     : julien
--%>

<%@page import="Models.DemandesMobilites"%>
<%@page import="Models.Etudiants"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.html" %>
<section class="container">
    <h2>Consultation des demandes de mobilités</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="DemandesMobilitesController?action=byStudent">Par étudiant</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=byUniv">Par université</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=byDiploma">Par diplomes</a>
        </li>
    </ul>    
    
    <form method="post" action="DemandesMobilitesController">
        <label><b>Etudiant</b> : </label>
        <select id="etudiant" name="etudiant" required>
            <option value="0" selected>Selectionnez</option>
            <%
                ArrayList<Etudiants> listEtu = Etudiants.getAll();
                for(Etudiants etu : listEtu) {                    
                    out.print("<option value='"+etu.getNUMETUDIANT()+"'>"+etu.getPRENOMET()+" "+etu.getNOMET()+"</option>");
                }
            %>
        </select>
        <input type="submit" value="Consulter">
        <input type="hidden" name="action" value="consultByStudent">
    </form>    
    
        <%
            ArrayList<DemandesMobilites> dem = (ArrayList<DemandesMobilites>)session.getAttribute("demStu");
            if(session.getAttribute("demStu") != null && dem.size() > 0) {
                out.print("<table class='table'>");
                out.print("<thead>");                
                out.print("<th scope='col'>Référence</th>");
                out.print("<th scope='col'>Etudiant</th>");
                out.print("<th scope='col'>Diplomes</th>");
                out.print("<th scope='col'>Date de dépot</th>");
                out.print("<th scope='col'>Etat</th>");
                out.print("<th scope='col'>Actions</th>");
                out.print("</thead>");
                out.print("<tbody>");
                for(DemandesMobilites demmob : dem) {
                    out.print("<tr>");
                        out.print("<td>"+demmob.getREFDEMMOB()+"</td>");
                        out.print("<td>"+demmob.getNUMETUDIANT()+"</td>");
                        out.print("<td>"+demmob.getCODEDIP()+"</td>");
                        out.print("<td>"+demmob.getDATEDEPOTDEMMOB()+"</td>");
                        out.print("<td>"+demmob.getETATDEMMOB()+"</td>");
                        out.print("<td>");
                        out.print("<a href='DemandesMobilitesController?action=edit&id="+demmob.getREFDEMMOB()+"'><span class='oi oi-pencil'></span></a>");
                        out.print(" <a href='DemandesMobilitesController?action=delete&id="+demmob.getREFDEMMOB()+"'><span class='oi oi-trash'></a>");
                        out.print("</td>");

                    out.print("</tr>");
                }
                out.print("</tbody>");
                out.print("</table>");
            }
            else {
                out.print("<p><i>Aucun résultats</i></p>");
            }
        %>            
        
</section>
<%@include file="footer.html" %>
