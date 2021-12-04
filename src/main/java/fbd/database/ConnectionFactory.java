package fbd.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author garren
 */
public class ConnectionFactory {

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost/fbd",
                    "fbd",
                    "St1nks@#2187");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static void main(String[] args) throws SQLException {;
//        Connection con = getConnection();
//    }
}
