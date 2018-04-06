<%-- 
    Document   : index
    Created on : 26 mars 2018, 22:40:37
    Author     : julien
--%>

<%@page import="Models.Diplomes"%>
<%@page import="Models.Etudiants"%>
<%@page import="Controllers.DemandesMobilitesController"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Models.DemandesMobilites"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@include file="header.html" %>
<section class="container">
    <h2>Demandes de Mobilités</h2>
    <ul class="nav nav-tabs">
        <li class="nav-item">
            <a class="nav-link active" href="DemandesMobilitesController?action=all">Consulter les demandes</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="DemandesMobilitesController?action=add">Ajouter une demande</a>
        </li>
    </ul>    
    <%
        /*if(session.getAttribute("message") == "true") {
            out.print("<div class='alert alert-success alert-dismissible fade show' role='alert'><strong>Succès</strong> La demande a bien été enregistrée.<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
        }
        else if(session.getAttribute("message") == "false") {
            out.print("<div class='alert alert-danger alert-dismissible fade show' role='alert'><strong>Succès</strong> Un problème est survenu. La demande n'a pas pu été enregistrée.<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>&times;</span></button></div>");
        }*/
    %>
    <table class="table">
        <thead>
            <th scope="col">Référence</th>
            <th scope="col">Etudiant</th>
            <th scope="col">Diplomes</th>
            <th scope="col">Date de dépot</th>
            <th scope="col">Etat</th>
            <th scope="col">Actions</th>
        </thead>
        <tbody>
        <%
            ArrayList<DemandesMobilites> listeDemMob = DemandesMobilites.getAll();
            for(DemandesMobilites demmob : listeDemMob) {
                out.print("<tr>");
                    Etudiants etu = new Etudiants();
                    Diplomes dip = new Diplomes();
                    etu = etu.getOne(demmob.getNUMETUDIANT());
                    dip = dip.getOne(demmob.getCODEDIP());
                    out.print("<td>"+demmob.getREFDEMMOB()+"</td>");
                    out.print("<td>"+etu.getPRENOMET()+" "+etu.getNOMET()+"</td>");
                    out.print("<td>"+dip.getINTITULEDIP()+"</td>");
                    out.print("<td>"+demmob.getDATEDEPOTDEMMOB()+"</td>");
                    out.print("<td>"+demmob.getETATDEMMOB()+"</td>");
                    out.print("<td>");
                    out.print("<a href='DemandesMobilitesController?action=edit&id="+demmob.getREFDEMMOB()+"'><span class='oi oi-pencil'></span></a>");
                    out.print(" <a href='DemandesMobilitesController?action=delete&id="+demmob.getREFDEMMOB()+"'><span class='oi oi-trash'></a>");
                    out.print("</td>");
                    
                out.print("</tr>");
            }
        %>            
        </tbody>
    </table>
</section>
<%@include file="footer.html" %>
