/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categorie;

import requete.Requete;

/**
 *
 * @author Christian
 */
public class Place extends Requete{
   String Id ;
   int PlaceLibre;
   int PlacePrise;
   double Prix;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public int getPlaceLibre() {
        return PlaceLibre;
    }

    public void setPlaceLibre(int PlaceLibre) {
        this.PlaceLibre = PlaceLibre;
    }

    public int getPlacePrise() {
        return PlacePrise;
    }

    public void setPlacePrise(int PlacePrise) {
        this.PlacePrise = PlacePrise;
    }

    public double getPrix() {
        return Prix;
    }

    public void setPrix(double Prix) {
        this.Prix = Prix;
    }
    
}
