package busResv;
import java.sql.*;

public class DbConnection {
     private static final String url = "jdbc:mysql://localhost:3306/busresv";
     private static final String username = "root";
     private static final String password = "swetha";
     
     //throws is used so that if any error occurs, it won't handle it in this method,
     //instead the error will throw in the function where we call this method, so that function will handle it.
    public static Connection getConnection() throws SQLException{ //method
          return DriverManager.getConnection(url,username,password);
}
}
