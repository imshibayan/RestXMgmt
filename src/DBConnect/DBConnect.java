
package DBConnect;
import java.sql.*;

public class DBConnect {
    
    private static final String username = "root";
    private static final String password = "";
    
    public static Connection getConnection() {
        Connection con = null;
        String connectionUrl = "jdbc:mysql://localhost:3306/restx?useTimezone=true&serverTimezone=UTC";

        try {
            con = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connection successful.");
        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        }
        return con;
    }
}
