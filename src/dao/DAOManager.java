package dao;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

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


    private static <T> T getEntityFromResultSet(T entity, ResultSet rs) throws SQLException {
        // TODO: 11/21/20 change the name of table columns same as model fields.
        // FIXME: 11/21/20 change all methods of DAO

        Class<?> obj = entity.getClass();
        try {
            for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                String fieldName = rs.getMetaData().getColumnName(i);
                Field field = obj.getDeclaredField(fieldName); // For private fields
                field.setAccessible(true);
                field.set(entity, rs.getObject(i));
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return entity;
    }

    public static <T> T getEntity(String tableName, String columnName, Object columnValue, T entity) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + columnName + " = ?";
        T result = null;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, columnValue);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next())
                    result = getEntityFromResultSet(entity, rs);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public static <T> List<T> getListOfEntities(String tableName, T entity) {
        String sql = "SELECT * FROM " + tableName;
        List<T> results = new ArrayList<>();


        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                results.add(getEntityFromResultSet(entity, rs));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return results;
    }

    private static <T> String getSqlInsertByEntity(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        StringJoiner columns = new StringJoiner(",");
        StringJoiner questionMarks = new StringJoiner(",");
        for (Field field : fields) {
            if (!field.getName().equals("id")) {
                columns.add(field.getName());
                questionMarks.add("?");
            }
        }

        String tableName = entity.getClass().getSimpleName().toLowerCase();
        return "INSERT INTO " + tableName + " (" + columns.toString() + ") VALUES (" + questionMarks + ")";
    }

    private static <T> String getSqlUpdateByEntity(T entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        StringJoiner columns = new StringJoiner(",");
        for (Field field : fields)
            if (!field.getName().equals("id"))
                columns.add(field.getName() + "=?");

        String tableName = entity.getClass().getSimpleName().toLowerCase();
        return "UPDATE " + tableName + " SET " + columns + " WHERE id = ?";
    }


    public static <T> T insertEntity(T entity) {
        String sql = getSqlInsertByEntity(entity);
        T result = null;

        Class<?> obj = entity.getClass();
        Field[] fields = entity.getClass().getDeclaredFields();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            for (int i = 1; i < fields.length; i++) {
                Field f = obj.getDeclaredField(fields[i].getName());
                f.setAccessible(true);
                ps.setObject(i, f.get(entity));
            }
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    Method m = entity.getClass().getMethod("setId", Long.TYPE);
                    m.invoke(entity, key);
                    result = entity;
                }

            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return result;
    }


    public static <T> T updateEntity(T entity) {
        String sql = getSqlUpdateByEntity(entity);
        T result = null;

        Class<?> obj = entity.getClass();
        Field[] fields = entity.getClass().getDeclaredFields();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 1; i < fields.length; i++) {
                Field f = obj.getDeclaredField(fields[i].getName());
                f.setAccessible(true);
                ps.setObject(i, f.get(entity));
            }
            Field f = obj.getDeclaredField(fields[0].getName()); // id
            f.setAccessible(true);
            ps.setObject(fields.length, f.get(entity));

            if (ps.executeUpdate() > 0)
                result = entity;


        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }

        return result;
    }


}
