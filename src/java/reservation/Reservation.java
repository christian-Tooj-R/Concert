/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation;

import connection.ConnectOracle;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import requete.Requete;

/**
 *
 * @author Christian
 */
public class Reservation extends Requete{
   String Id; 
   Date Currdate;
   String Heure;
   int Attente;

    public Reservation() {
    }

    public Reservation(String zone,String Place, String Attente) throws Exception{
        this.setId(zone + Place);
        this.setCurrdate(null);
        this.setHeure(null);
        this.setAttente(Attente);
    }

    public void Delai(Connection connect,int duree)throws Exception{
        Reservation reserve=new Reservation();
        reserve.setAttente(1);
        Object[] obj = reserve.select(connect,"");
        for(Object o : obj){
            if(verif(o,duree)){
            Reservation reserv=new Reservation();
                reserv.setHeure(((Reservation)o).getHeure());
                reserv.delete(connect);
            }
        }
    }
    public boolean verif(Object o,int time){
        if(getDiffTime(((Reservation)o).getHeure(),this.getHeure()) == time){
            return true;
        }
        return false;
    }
   public void Creer(Connection connect)throws Exception{
       if (connect == null || connect.isClosed()) {
             connect = new ConnectOracle().getConnection();
       }
       this.insert(connect);
       if(!connect.isClosed()){
           connect.close();
       }
   }
    public int sum(int[] tab) {
        int rep = tab[0];
        for (int i = 1; i < tab.length; i++) {
            rep = rep + tab[i];
        }
        return rep;
    }
   public int getDiffTime(String ancien, String recent) {
        String[] anc = ancien.split(":");
        String[] rec = recent.split(":");
        int i = 0;
        int calc = 10000;
        int[] a = new int[anc.length];
        int[] b = new int[anc.length];
        int less = 0;
        while (i < a.length) {
            a[i] = Integer.parseInt(anc[i]) * calc;
            b[i] = Integer.parseInt(rec[i]) * calc;
            if (a[1] != b[1]) {
                less = 39 * Integer.parseInt(rec[1]);
            }
            calc = calc / 100;
            i++;
        }
        if ((sum(b) - sum(a)) - less <= 0) {
            return 0;
        }
        return (sum(b) - sum(a)) - less;
    }
    public String getId() {
        return Id;
    }

    public void setId(String IdPlace) {
        this.Id = IdPlace;
    }

    public Date getCurrdate() {
        return Currdate;
    }

    public void setCurrdate(Date Currdate) throws Exception {
        if(Currdate != null){
            this.Currdate = Currdate;
        }else{
        Connection connect=null;
            if (connect == null || connect.isClosed()) {
             connect = new ConnectOracle().getConnection();
            }
            connect.setAutoCommit(false);
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery("select sysdate from dual");
            res.next();
            Date thedate = res.getDate(1);
            stmt.close();
            connect.close();
            this.Currdate = thedate;
        }
    }

    public String getHeure() {
        return Heure;
    }

    public void setHeure(String Heure) throws Exception{
      /*  if(!Currdate.equals(null)){
            this.Heure = Heure;
            System.out.println("Ato ndray ehh");
        }else{*/
        Connection connect=null;
            if (connect == null || connect.isClosed()) {
             connect = new ConnectOracle().getConnection();
            }
            System.out.println("Tena nakato");
            connect.setAutoCommit(false);
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery("select to_char(sysdate,'HH24:MI:SS AM') from dual");
            res.next();
            String Hours = res.getString(1);
            stmt.close();
            connect.close();
            this.Heure = Hours;
        //}
    }

    public int getAttente() {
        return Attente;
    }

    
    public void setAttente(String Attente) {
        setAttente(Integer.parseInt(Attente));
    }
    
    public void setAttente(int Attente) {
        this.Attente = Attente;
    }
    
}
