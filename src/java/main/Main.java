/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import reservation.Reservation;

/**
 *
 * @author Christian
 */
public class Main {
    public static void main(String[] args){
        try {
            Reservation reservation=new Reservation("A","2","0");
            reservation.Creer(null);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
