package chrono;

import java.util.TimerTask;

import connection.*;
import java.sql.*;

public class Chrono extends TimerTask {
    int counter;
    Connection connect;

    public Chrono(int temps,Connection conn) {
        this.counter=temps;
        this.connect=conn;
    }


    @Override
    public void run() {
        try {
           if(this.counter == 0){
               this.connect.rollback();
               this.connect.close();
               this.cancel();
           }
            this.counter--;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
