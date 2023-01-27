<%-- 
    Document   : TraitReservation
    Created on : 26 janv. 2023, 22:17:10
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="reservation.Reservation"%>
<%
    
    Reservation reservation=new Reservation(request.getParameter("id"),request.getParameter("num"),request.getParameter("attente"));
    reservation.Creer(null);
    response.sendRedirect("Reservation.jsp");
    
%>
