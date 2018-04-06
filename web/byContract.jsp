<%-- 
    Document   : byContract
    Created on : 30 mars 2018, 16:55:56
    Author     : jtrescartes
--%>

<%@page import="Models.DemandesFinancieres"%>
<%@page import="Models.Contrats"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.html" %>
<section class="container">
    <h2>Consultation des demandes financieres</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="DemandesFinancieresController?action=byContract">Par contrat</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesFinancieresController?action=byProgram">Par programme</a>
        </li>
    </ul>    
    
    <form method="post" action="DemandesFinancieresController">
        <label><b>Contrat</b> : </label>
        <select id="contrat" name="contrat">
            <option value="0" selected>Selectionnez</option>
            <%
                ArrayList<Contrats> listCon = Contrats.getAll();
                for(Contrats con : listCon) {
                    out.print("<option value='"+con.getIDCONTRAT()+"'>"+con.getIDCONTRAT()+"</option>");
                }
            %>
        </select>
        <input type="submit" value="Consulter">
        <input type="hidden" name="action" value="consultByContract">
    </form>    
        
        <%
            ArrayList<DemandesFinancieres> dem = (ArrayList<DemandesFinancieres>)session.getAttribute("demCon");
            if(session.getAttribute("demCon") != null && dem.size() > 0) {
                out.print("<table class='table'>");
                out.print("<thead>");                
                out.print("<th scope='col'>Référence</th>");
                out.print("<th scope='col'>Contrat</th>");
                out.print("<th scope='col'>Date de depot</th>");
                out.print("<th scope='col'>Montant</th>");
                out.print("<th scope='col'>Etat</th>");
                out.print("</thead>");
                out.print("<tbody>");
                for(DemandesFinancieres demfin : dem) {
                    out.print("<tr>");
                        out.print("<td>"+demfin.getREFDEMFIN()+"</td>");
                        out.print("<td>"+demfin.getIDCONTRAT()+"</td>");
                        out.print("<td>"+demfin.getDATEDEPOTDEMFIN()+"</td>");
                        out.print("<td>"+demfin.getMONTANTACCORDE()+"€</td>");
                        out.print("<td>"+demfin.getETATDEMFIN()+"</td>");                    
                        out.print("<td>");
                        out.print("<a href='DemandesFinancieresController?action=edit&id="+demfin.getREFDEMFIN()+"'><span class='oi oi-pencil'></span></a>");
                        out.print(" <a href='DemandesFinancieresController?action=delete&id="+demfin.getREFDEMFIN()+"'><span class='oi oi-trash'></a>");
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
