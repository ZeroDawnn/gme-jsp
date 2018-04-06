<%-- 
    Document   : addDemMob
    Created on : 28 mars 2018, 14:25:48
    Author     : julien
--%>

<%@page import="Models.Etudiants"%>
<%@page import="Models.Diplomes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<section class="container">
    <h2>Demandes de Mobilités</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="DemandesMobilitesController?action=add">Ajouter une demande</a>
        </li>
    </ul>
    
    <form method="post" action="DemandesMobilitesController">
        <label><b>Etudiant</b> : </label>
        <select id="etudiant" name="etudiant">
            <option value="0">Selectionnez</option>
            <%
                ArrayList<Etudiants> listEtu = Etudiants.getAll();
                for(Etudiants etu : listEtu) {
                    out.print("<option value='"+etu.getNUMETUDIANT()+"'>"+etu.getPRENOMET()+" "+etu.getNOMET()+"</option>");
                }
            %>
        </select>
        <br>
        <label><b>Diplomes</b> : </label>
        <select id="diplome" name="diplome">
            <option value="0">Selectionnez</option>
            <%
                ArrayList<Diplomes> listDip = Diplomes.getAll();
                for(Diplomes dip : listDip) {
                    out.print("<option value='"+dip.getCODEDIP()+"'>"+dip.getINTITULEDIP()+"</option>");
                }
            %>
        </select>
        <br>
        <label><b>Date</b> : </label>
        <input type="date" id="dt" name="dt" required="required">
        <br>
        <input type="submit" value="Ajouter">
        <input type="hidden" name="action" value="addFinal">
    </form>
</section>
<%@include file="footer.html" %>
