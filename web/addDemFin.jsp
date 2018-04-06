<%-- 
    Document   : addDemFin
    Created on : 28 mars 2018, 16:21:18
    Author     : julien
--%>

<%@page import="Models.Contrats"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.html" %>
<section class="container">
    <h2>Demandes Financieres</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link" href="DemandesFinancieresController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link active" href="DemandesFinancieresController?action=add">Ajouter une demande</a>
        </li>
    </ul>
    
    <form method="post" action="DemandesFinancieresController">
        <label><b>Contrat</b> : </label>
        <select id="contrat" name="contrat">
            <option value="null">Selectionnez</option>
            <%
                ArrayList<Contrats> listCon = Contrats.getAll();
                for(Contrats con : listCon) {
                    out.print("<option value='"+con.getIDCONTRAT()+"'>"+con.getIDCONTRAT()+"</option>");
                }
            %>
        </select>
        <br>        
        <label><b>Date</b> : </label>
        <input type="date" id="dt" name="dt" required="required">
        <br>
        <label><b>Montant</b> : </label>
        <input type="number" id="money" name="money" required="required">
        <br>
        <input type="submit" value="Ajouter">
        <input type="hidden" name="action" value="addFinal">
    </form>
</section>
<%@include file="footer.html" %>
