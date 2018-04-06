<%-- 
    Document   : byUniv
    Created on : 30 mars 2018, 14:02:16
    Author     : jtrescartes
--%>

<%@page import="Models.DemandesMobilites"%>
<%@page import="Models.Universites"%>
<%@page import="Models.Diplomes"%>
<%@page import="java.util.ArrayList"%>
<%@include file="header.html" %>
<section class="container">
    <h2>Consultation des demandes de mobilités</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=byStudent">Par étudiant</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="DemandesMobilitesController?action=byUniv">Par université</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=byDiploma">Par diplomes</a>
        </li>
    </ul>    
    
    <form method="post" action="DemandesMobilitesController">
        <label><b>Université</b> : </label>
        <select id="univ" name="univ">
            <option value="0" selected>Selectionnez</option>
            <%
                ArrayList<Universites> listUni = Universites.getAll();
                for(Universites uni : listUni) {                    
                    out.print("<option value='"+uni.getCODEU()+"'>"+uni.getNOMU()+"</option>");
                }
            %>
        </select>
        <input type="submit" value="Consulter">
        <input type="hidden" name="action" value="consultByUniv">
    </form>    
        
        <%
            ArrayList<DemandesMobilites> dem = (ArrayList<DemandesMobilites>)session.getAttribute("demCon");
            if(session.getAttribute("demCon") != null && dem.size() > 0) {
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
