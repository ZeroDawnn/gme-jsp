<%-- 
    Document   : indexDF
    Created on : 28 mars 2018, 16:08:00
    Author     : julien
--%>

<%@page import="Models.DemandesFinancieres"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.html" %>
<section class="container">
    <h2>Demandes Financières</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="DemandesFinancieresController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesFinancieresController?action=add">Ajouter une demande</a>
        </li>
    </ul>        
    <table class="table">
        <thead>
            <th scope="col">Référence</th>
            <th scope="col">Contrat</th>
            <th scope="col">Date de depot</th>
            <th scope="col">Montant</th>
            <th scope="col">Etat</th>
            <th scope="col">Actions</th>
        </thead>
        <tbody>
        <%
            ArrayList<DemandesFinancieres> listeDemFin = DemandesFinancieres.getAll();
            for(DemandesFinancieres demfin : listeDemFin) {
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
        %>            
        </tbody>
    </table>
</section>
<%@include file="footer.html" %>