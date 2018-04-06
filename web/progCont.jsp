<%-- 
    Document   : progCont
    Created on : 30 mars 2018, 15:43:44
    Author     : jtrescartes
--%>

<%@page import="Models.Contrats"%>
<%@page import="Models.Programmes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.html" %>
<section class="container">
    <h2>Consultation programmes et contrats associés</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="GlobalController?action=progCont">Programmes -> Contrats</a>
        </li>       
    </ul>    
    
    <form method="post" action="GlobalController">
        <label><b>Programme</b> : </label>
        <select id="programme" name="programme">
            <option value="0" selected>Selectionnez</option>
            <%
                ArrayList<Programmes> listpro = Programmes.getAll();
                for(Programmes pro : listpro) {
                    out.print("<option value='"+pro.getIDP()+"'>"+pro.getINTITULEP()+"</option>");
                }
            %>
        </select>
        <input type="submit" value="Consulter">
        <input type="hidden" name="action" value="consultContrat">
    </form>    
        
       <%
            ArrayList<Contrats> con = (ArrayList<Contrats>)session.getAttribute("contratAssoc");
            
            if(session.getAttribute("contratAssoc") != null && con.size() > 0) {
                out.print("<table class='table'>");
                out.print("<thead>");                
                out.print("<th scope='col'>IDContrat</th>");
                out.print("<th scope='col'>Diplome</th>");
                out.print("<th scope='col'>Référence DEMMOB</th>");
                out.print("<th scope='col'>Programme</th>");
                out.print("<th scope='col'>Duree</th>");
                out.print("<th scope='col'>Etat</th>");
                out.print("<th scope='col'>Ordre</th>");
                out.print("</thead>");
                out.print("<tbody>");
                for(Contrats contA : con) {
                    out.print("<tr>");
                        out.print("<td>"+contA.getIDCONTRAT()+"</td>");
                        out.print("<td>"+contA.getCODEDIP()+"</td>");
                        out.print("<td>"+contA.getREFDEMMOB()+"</td>");
                        out.print("<td>"+contA.getIDP()+"</td>");
                        out.print("<td>"+contA.getDUREE()+"</td>");
                        out.print("<td>"+contA.getETATCONTRAT()+"</td>");
                        out.print("<td>"+contA.getORDRE()+"</td>");
                        out.print("<td>");
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
