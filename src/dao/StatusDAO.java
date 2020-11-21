package dao;

import model.Status;

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
public class StatusDAO {

    public Status createStatus(Status newStatus) {
        Status result = null;
        String sql = "INSERT INTO shop.status (status) VALUES(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newStatus.getStatus());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    int key = rs.getInt(1);
                    newStatus.setId(key);
                    result = newStatus;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Status> listAllStatus() {
        List<Status> listStatus = new ArrayList<>();

        String sql = "SELECT * FROM status";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String statusDes = resultSet.getString("status");

                Status status = new Status(id, statusDes);
                listStatus.add(status);
            }
            return listStatus;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteStatus(int id) {
        String sql = "DELETE FROM status where id = ?";

        delete(id, sql);

    }

    public Status updateStatus(Status updatedStatus) {
        Status result = null;
        String sql = "UPDATE status SET status = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedStatus.getStatus());
            ps.setInt(2, updatedStatus.getId());

            if (ps.executeUpdate() > 0)
                result = updatedStatus;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Status getStatus(int id) {
        Status status = null;
        String sql = "SELECT * FROM status WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String statusDes = rs.getString("status");
                    status = new Status(id, statusDes);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return status;
    }
}
