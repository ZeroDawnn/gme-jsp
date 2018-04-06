<%-- 
    Document   : editDemFin
    Created on : 28 mars 2018, 16:21:30
    Author     : julien
--%>

<%@page import="Models.DemandesFinancieres"%>
<%@page import="Models.Contrats"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<section class="container">
    <%
        DemandesFinancieres dm = (DemandesFinancieres)session.getAttribute("dem");
    %>
    <h2>Demandes Financieres</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="DemandesFinancieresController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesFinancieresController?action=add">Ajouter une demande</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="DemandesFinancieresController?action=edit&id=<%= dm.getREFDEMFIN() %>">Editer une demande</a>
        </li>
    </ul>
    
    <form method="post" action="DemandesFinancieresController">
        <label><b>Reference</b> : <%= dm.getREFDEMFIN() %></label>
        <br>
        <label><b>Contrat</b> : <%= dm.getIDCONTRAT() %></label>        
        <br>        
        <label><b>Date</b> : <%= dm.getDATEDEPOTDEMFIN() %></label>
        <br>
        <label><b>Montant</b> : </label>
        <input type="number" id="moneyUpd" name="moneyUpd" value="<%= dm.getMONTANTACCORDE() %>" required="required">
        <br>
        <label><b>Etat</b> : </label>
        <input type="text" id="etatUpd" name="etatUpd" value="<%= dm.getETATDEMFIN() %>" required="required">
        <br>
        <input type="submit" value="Editer">
        <input type="hidden" name="id" value="<%= dm.getREFDEMFIN() %>">
        <input type="hidden" name="action" value="editFinal">
    </form>
</section>
<%@include file="footer.html" %>
