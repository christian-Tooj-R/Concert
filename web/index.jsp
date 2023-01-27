
<%@page import="reservation.Reservation"%>
<%@page import="categorie.Categorie"%>
<%
    Object[] tabCategorie = new Categorie().select(null,"");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Concert</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body style="width: 589.6px;">
    <div class="container">
        <div class="row">
            <div class="col-md-6" style="width: 596.8px;height: 47px;">
                <div>
                    <div style="box-shadow: 0.5px 0.5px 5px 1px;width: 163.6px;height: 59px;border-width: 12px;border-color: rgb(33, 37, 41);border-top-color: rgb(33,border-right-color: 37,border-bottom-color: 41);border-left-color: 37;margin-top: 25px;margin-left: 194px;"><label class="form-label" style="margin-left: 51px;margin-top: 13px;">SCENE</label></div>
                    <div style="width: 581.6px;">
                     <% for(Object o : tabCategorie){ %>
                        <a href="Reservation.jsp?id=<% out.print(((Categorie)o).getId()); %>">
                            <div style="box-shadow: 0.5px 0.5px 5px 1px;width: 493.6px;height: 110px;border-width: 12px;border-color: rgb(33, 37, 41);border-top-color: rgb(33,border-right-color: 37,border-bottom-color: 41);border-left-color: 37;margin-top: 27px;margin-left: 34px;">
                                <label class="form-label" style="font-size: 37px;font-family: cursive;margin-left: 166px;margin-top: 22px;"><% out.print("ZONE "+((Categorie)o).getId()); %></label>
                            </div>
                        </a> 
                     <% } %>
                    </div>
                </div>
            </div>
            <div class="col-md-6" style="margin-left: 596px;width: 350.8px;height: 248px;margin-top: 137px;"><label class="col-form-label">Effectuez une Reservation en cliquant sur une zone</label></div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
</body>

</html>