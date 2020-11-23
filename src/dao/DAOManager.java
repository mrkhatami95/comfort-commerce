package dao;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.Map;
import java.util.Objects;

public class DAOManager {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost/shop";
            String username = "root";
            String password = "pass";

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

    public static Object getEntityFromResultSet(Object entity, ResultSet rs) throws SQLException {
        // TODO: 11/21/20
        // FIXME: 11/21/20

        // ResultSet properties
        int columnCount = rs.getMetaData().getColumnCount();
        String columnName = rs.getMetaData().getColumnName(1);
        int columnType = rs.getMetaData().getColumnType(1);
        String columnTypeName = rs.getMetaData().getColumnTypeName(1);


        // Model properties
        System.out.println(entity.getClass().getDeclaredFields()[0].getType().getSimpleName());
        System.out.println(entity.getClass().getDeclaredFields()[0].getName());

        return null;
    }


}
