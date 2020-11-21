package dao;

import model.User;

import java.math.BigDecimal;
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
public class UserDAO {

    public User findUserByUsername(String username) {
        User result = null;
        String sql = "SELECT * FROM shop.user WHERE username = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong("id");
                    String password = rs.getString("password");
                    BigDecimal budget = rs.getBigDecimal("budget");
                    long basketId = rs.getLong("basket_id");
                    int statusId = rs.getInt("status_id");
                    long addressId = rs.getLong("address_id");
                    long roleId = rs.getLong("role_id");
                    String phoneNumber = rs.getString("phone_number");

                    result = new User(id, username, password, budget, basketId, statusId, addressId, roleId, phoneNumber);
                }
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return result;
    }

    public User createUser(User newUser) {
        User result = null;
        String sql = "INSERT INTO shop.user (username, password, budget, basket_id, status_id, address_id, role_id, phone_number)"
                + " VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newUser.getUsername());
            ps.setString(2, newUser.getPassword());
            ps.setBigDecimal(3, newUser.getBudget());
            ps.setLong(4, newUser.getBasketId());
            ps.setInt(5, newUser.getStatusId());
            ps.setLong(6, newUser.getAddressId());
            ps.setLong(7, newUser.getRoleId());
            ps.setString(8, newUser.getPhoneNumber());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newUser.setId(key);
                    result = newUser;
                }

            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return result;
    }

    public List<User> getUsers() {
        List<User> listUser = new ArrayList<>();

        String sql = "SELECT * FROM user";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                BigDecimal budget = resultSet.getBigDecimal("budget");
                long basketId = resultSet.getLong("basket_id");
                int statusId = resultSet.getInt("status_id");
                long addressId = resultSet.getLong("address_id");
                long roleId = resultSet.getLong("role_id");
                String phoneNumber = resultSet.getString("phone_number");

                User user = new User(id, username, password, budget, basketId, statusId, addressId, roleId, phoneNumber);
                listUser.add(user);
            }
            return listUser;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteUser(long id) {
        String sql = "DELETE FROM user where id = ?";

        delete(id, sql);

    }

    public User updateUser(User updatedUser) {
        User result = null;
        String sql = "UPDATE user SET username=?, password=?, budget=?, basket_id=?, status_id=?, address_id=?, role_id=?, phone_number=? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedUser.getUsername());
            ps.setString(2, updatedUser.getPassword());
            ps.setBigDecimal(3, updatedUser.getBudget());
            ps.setLong(4, updatedUser.getBasketId());
            ps.setInt(5, updatedUser.getStatusId());
            ps.setLong(6, updatedUser.getAddressId());
            ps.setLong(7, updatedUser.getRoleId());
            ps.setString(8, updatedUser.getPhoneNumber());
            ps.setLong(9, updatedUser.getId());

            if (ps.executeUpdate() > 0)
                result = updatedUser;

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return result;
    }

    public User getUser(long id) {
        User user = null;
        String sql = "SELECT * FROM user WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    BigDecimal budget = rs.getBigDecimal("budget");
                    long basketId = rs.getLong("basket_id");
                    int statusId = rs.getInt("status_id");
                    long addressId = rs.getLong("address_id");
                    long roleId = rs.getLong("role_id");
                    String phoneNumber = rs.getString("phone_number");

                    user = new User(id, username, password, budget, basketId, statusId, addressId, roleId, phoneNumber);

                }

            }

        } catch (SQLException e) {

            System.err.println(e.getMessage());
        }

        return user;
    }
}
