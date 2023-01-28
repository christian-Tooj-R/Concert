package chrono;

import java.util.TimerTask;

import connection.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import reservation.Reservation;

public class Chrono extends TimerTask {
    int counter;
    Connection connect;
    String heure;
    Reservation reservation;
    public Chrono(int temps,String hours) {
        this.counter=temps;
        this.heure=hours;
        this.reservation=new Reservation();
    }


    @Override
    public void run() {
        try {
            this.reservation.setAttente(1);
            this.reservation.setHour(this.heure);
           Object[] obj = this.reservation.select(null,"Heure");
           if(this.counter == 0){
               Reservation reserve=new Reservation();
               reserve.setHeure(((Reservation)obj[0]).getHeure());
               reserve.delete(null);
               this.cancel();
           }
            this.counter--;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    


}
