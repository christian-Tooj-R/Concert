<%-- 
    Document   : ListeReservationAttente
    Created on : 27 janv. 2023, 13:52:22
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="reservation.Reservation"%>
<%
    Reservation reservation=new Reservation();
    out.print(request.getParameter("attente"));
    reservation.setAttente(request.getParameter("attente"));
%>
<!DOCTYPE html>
<html>
   <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shr ink-to-fit=no">
    <title>Stockage</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="height: 603px;">
    <div class="table-responsive">
        <table class="table">
            <%   out.print(reservation.getTable());  %>
        </table>
    </div>
    </body>
</html>
