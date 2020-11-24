package dao;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
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

    public static Object getEntityFromResultSet(Object obj, ResultSet rs) throws SQLException {
        // TODO: 11/21/20 change the name of table columns same as model fields.
        // FIXME: 11/21/20 test the method, change all methods
        // TODO: 11/24/20 write reverse method


        Class cl = obj.getClass();
        try {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String fieldName = rs.getMetaData().getColumnName(i);
                Field field = cl.getField(fieldName);
                int fieldAccessModifier = field.getModifiers();
                if (Modifier.isPrivate(fieldAccessModifier)) {
                    field.setAccessible(true);
                    field.set(obj, rs.getObject(i));
                    field.setAccessible(false);
                } else {
                    field.set(obj, rs.getObject(i));
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return obj;
    }


}
