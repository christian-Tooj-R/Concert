
<%@page import="reservation.Reservation"%>
<%@page import="categorie.Categorie"%>
<%
    Categorie categorie=new Categorie();
    categorie.setId(request.getParameter("id"));
    Object[] tabCategorie = categorie.select(null,"");
    String valeur1="hidden" ,valeur2="";
    if(request.getParameter("reserve") != null){
        valeur1="submit";
        valeur2="hidden";
    }
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Reservation</title>
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/css/styles.css">
</head>

<body>
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <div style="box-shadow: 0.5px 0.5px 5px 1px;width: 305.6px;height: 266px;border-width: 12px;border-color: rgb(33, 37, 41);border-top-color: rgb(33,border-right-color: 37,border-bottom-color: 41);border-left-color: 37;margin-top: 27px;margin-left: 34px;"><label class="form-label" style="font-size: 31px;font-family: cursive;margin-left: 98px;margin-top: 22px;"><% out.print("ZONE "+((Categorie)tabCategorie[0]).getId()); %></label></div>
            </div>
            <div class="col"><label class="form-label" style="font-size: 21px;">Reservation&nbsp;</label>
                <form action="TraitReservation.jsp" method="post">
                <div></div><label class="form-label">Place :</label>
                <select name="num">
                    <optgroup label="Numero de places">
                        <% for(int i=((Categorie)tabCategorie[0]).getNdebut();i<=((Categorie)tabCategorie[0]).getNfin();i++){ %>
                            <option value="<% out.print(i); %>"><% out.print(i); %></option>
                        <% } %>
                    </optgroup>
                </select>
                <input type="hidden" value="<% out.print(((Categorie)tabCategorie[0]).getId()); %>" name="id">
                <button class="btn btn-primary" type="button" <% out.print(valeur2); %> style="margin-top: 90px;margin-right: 8px;margin-left: -164px;" ><a href="Reservation.jsp?reserve=1" style="text-decoration: none;color: white;">Reserver</a></button>
                
                <button class="btn btn-primary" type="submit" <% out.print(valeur1); %> style="margin-top: 90px;margin-right: 8px;margin-left: -164px;" value="0" name="attente">Confirmer Maintenant</button>
                <button class="btn btn-primary" type="submit" <% out.print(valeur1); %> style="margin-top: 90px;margin-right: 8px;" value="1" name="attente">Confirmer Plus tard</button>
                </form>
            </div>
        </div>
    </div>
    <script src="assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/js/script.js"></script>
</body>

</html>