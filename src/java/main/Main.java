/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import connection.ConnectOracle;
import java.sql.Connection;
import reservation.Reservation;

/**
 *
 * @author Christian
 */
public class Main {
    public static void main(String[] args){
        try {
            Connection connect=new ConnectOracle().getConnection();
            Reservation reservation=new Reservation();
         //   reservation.print(reservation.select(null, ""));
          //  reservation.Creer(connect, 5);
            reservation.setHeure("000");
          //  System.out.println(reservation.getAttente());
            reservation.delete(connect);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
