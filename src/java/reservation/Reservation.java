/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package reservation;

import chrono.Chrono;
import connection.ConnectOracle;
import java.lang.reflect.Field;
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
public class Reservation extends Requete {
    String Id;
    Date Currdate;
    String Heure;
    int Attente;

    public Reservation() {
    }

    /*public void testThread() {
        System.out.println("Miandry 5 seconde bg");
        java.util.Timer chrono1 = new java.util.Timer();
        chrono1.schedule(new Chrono(5,null), 1000, 1000);
    }*/ public String getTable() throws Exception {
        Object[] obj = this.select(new ConnectOracle().getConnection(), "");

        String table = "", line = "<tr>", head = "<th>";

        Field[] field = this.getClass().getDeclaredFields();

        for (int j = 0; j < field.length-1; j++) {
            if (j == 0) {
                head = head + field[j].getName() + "</th>";
            } else {
                head = head + "<th>" + field[j].getName() + "</th>";
            }
        }
        for (int i = 0; i < obj.length; i++) {
            for (int j = 0; j < field.length-1; j++) {
                table = table + "<td>"
                        + obj[i].getClass().getMethod("get" + field[j].getName()).invoke(obj[i]) + "</td>";
            }
            if (i == 0) {
                line = line + table + "</tr>";
            } else {
                line = line + "<tr>" + table + "</tr>";
            }
            table = "";
        }
        return "<thead><tr>" + head + "</tr></thead><tbody>" + line + "</tbody>";

    }
    

    public Reservation(String zone, String Place, String Attente) throws Exception {
        this.setId(zone + Place);
        this.setCurrdate(null);
        this.setHeure(null);
        this.setAttente(Attente);
    }
    public void Creer(Connection connect,int delai) throws Exception {
        if (connect == null) {
            connect = new ConnectOracle().getConnection();
        }
        this.insert(connect);
        if(this.getAttente()==1){
            java.util.Timer chrono1 = new java.util.Timer();
            chrono1.schedule(new Chrono(delai,connect), 1000, 1000);
        }else{
            connect.commit();
            connect.close();
        }
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
        if (Currdate != null) {
            this.Currdate = Currdate;
        } else {
            Connection connect = null;
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

    public void setHeure(String Heure) throws Exception {
        /*
         * if(!Currdate.equals(null)){
         * this.Heure = Heure;
         * System.out.println("Ato ndray ehh");
         * }else{
         */
        Connection connect = null;
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
        // }
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
