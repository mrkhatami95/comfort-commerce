package dao;

import model.Color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static dao.DAOManager.delete;
import static dao.DAOManager.getConnection;

/**
 * @author madeli
 * 11/20/20
 */
public class ColorDAO {

    public Color createColor(Color newColor) {
        Color result = null;
        String sql = "INSERT INTO color (color) VALUES(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newColor.getColor());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newColor.setId(key);
                    result = newColor;
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public List<Color> getColors() {
        List<Color> listColor = new ArrayList<>();

        String sql = "SELECT * FROM color";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String colorDes = resultSet.getString("color");

                Color color = new Color(id, colorDes);
                listColor.add(color);
            }
            return listColor;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteColor(long id) {
        String sql = "DELETE FROM color where id = ?";

        delete(id, sql);

    }

    public Color updateColor(Color updatedColor) {
        Color result = null;
        String sql = "UPDATE color SET color = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedColor.getColor());
            ps.setLong(2, updatedColor.getId());

            if (ps.executeUpdate() > 0)
                result = updatedColor;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public Color getColor(long id) {
        Color color = null;
        String sql = "SELECT * FROM color WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String colorDes = rs.getString("color");
                    color = new Color(id, colorDes);
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return color;
    }
}
