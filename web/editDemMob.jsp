<%-- 
    Document   : editDemMob
    Created on : 28 mars 2018, 14:30:40
    Author     : julien
--%>

<%@page import="Models.DemandesMobilites"%>
<%@page import="Models.Etudiants"%>
<%@page import="Models.Diplomes"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<section class="container">
    <%
        DemandesMobilites dm = (DemandesMobilites)session.getAttribute("dem");
        Etudiants etu = new Etudiants();
        etu = etu.getOne(dm.getNUMETUDIANT());
        Diplomes dip = new Diplomes();
        dip = dip.getOne(dm.getCODEDIP());
    %>
    <h2>Demandes de Mobilités</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=add">Ajouter une demande</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="DemandesMobilitesController?action=edit&id=<%= dm.getREFDEMMOB() %>">Editer une demande</a>
        </li>
    </ul>
    
    <form method="post" action="DemandesMobilitesController">
        <label><b>Reference</b> : <%= dm.getREFDEMMOB() %></label>
        <br>
        <label><b>Etudiant</b> : <%= etu.getPRENOMET()+ " " + etu.getNOMET() %></label>        
        <br>
        <label><b>Diplomes</b> : <%= dip.getINTITULEDIP() %></label>        
        <br>
        <label><b>Date</b> : <%= dm.getDATEDEPOTDEMMOB() %></label>
        <br>
        <label><b>Etat</b> : </label>
        <input type="text" id="etatUpd" name="etatUpd" value="<%= dm.getETATDEMMOB() %>" required="required">
        <br>
        <input type="submit" value="Editer">
        <input type="hidden" name="id" value="<%= dm.getREFDEMMOB() %>">
        <input type="hidden" name="action" value="editFinal">
    </form>
</section>
<%@include file="footer.html" %>
