package dao;

import model.Role;

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
public class RoleDAO {

    public Role createRole(Role newRole) {
        Role result = null;
        String sql = "INSERT INTO role (role_name) VALUES(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newRole.getRoleName());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newRole.setId(key);
                    result = newRole;
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public List<Role> getRoles() {
        List<Role> listRole = new ArrayList<>();

        String sql = "SELECT * FROM role";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String roleName = resultSet.getString("role_name");

                Role role = new Role(id, roleName);
                listRole.add(role);
            }
            return listRole;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteRole(long id) {
        String sql = "DELETE FROM role where id = ?";

        delete(id, sql);

    }

    public Role updateRole(Role updatedRole) {
        Role result = null;
        String sql = "UPDATE role SET role_name = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedRole.getRoleName());
            ps.setLong(2, updatedRole.getId());

            if (ps.executeUpdate() > 0)
                result = updatedRole;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public Role getRole(long id) {
        Role role = null;
        String sql = "SELECT * FROM role WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String roleName = rs.getString("role_name");
                    role = new Role(id, roleName);
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return role;
    }
}
