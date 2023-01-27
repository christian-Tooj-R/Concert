package connection;

import java.sql.*;

public class ConnectOracle {
  public Connection getConnection() throws Exception {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/ORCL", "Tooj", "concert");
    return con;
  }

}