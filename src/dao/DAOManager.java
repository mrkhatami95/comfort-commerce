package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DAOManager {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost/shop";
            String username = "root";
            String password = "admin";

            try {
                Class.forName(driver); // load MySQL driver
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }

            connection = DriverManager.getConnection(dbURL, username, password);
        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }
        return connection;
    }

    public static void delete(long id, String sql) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            if (ps.executeUpdate() == 0)
                throw new SQLException("ID = " + id + " not found");

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }
    }


}
