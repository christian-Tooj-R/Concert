/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package categorie;

import java.sql.Connection;
import requete.Requete;

/**
 *
 * @author Christian
 */
public class Categorie extends Requete{
   String Id;
   int Ndebut;
   int Nfin;
   

   public void Generate(Connection connect,int nbrPlace)throws Exception{
       Object[] obj=this.select(connect,"");
       Categorie categorie=new Categorie();
       int deb=1;
       for(Object o : obj){
           categorie.setNdebut(deb);
           categorie.setNfin(nbrPlace);
           categorie.update(connect,"Id",((Categorie)o).getId());
           deb+=nbrPlace;
           nbrPlace+=nbrPlace;
       }
   }

    public int getNdebut() {
        return Ndebut;
    }

    public void setNdebut(int Ndebut) {
        this.Ndebut = Ndebut;
    }

    public int getNfin() {
        return Nfin;
    }

    public void setNfin(int Nfin) {
        this.Nfin = Nfin;
    }

  
   
    public String getId() {
        return Id;
    }

    public void setId(String IdCategorie) {
        this.Id = IdCategorie;
    }

}
