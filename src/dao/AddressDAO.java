package dao;

import model.Address;

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
public class AddressDAO {

    public Address createAddress(Address newAddress) {
        Address result = null;
        String sql = "INSERT INTO shop.address (address) VALUES(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newAddress.getAddress());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newAddress.setId(key);
                    result = newAddress;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Address> listAllAddress() {
        List<Address> listAddress = new ArrayList<>();

        String sql = "SELECT * FROM address";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String addressDes = resultSet.getString("address");

                Address address = new Address(id, addressDes);
                listAddress.add(address);
            }
            return listAddress;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteAddress(long id) {
        String sql = "DELETE FROM address where id = ?";

        delete(id, sql);

    }

    public Address updateAddress(Address updatedAddress) {
        Address result = null;
        String sql = "UPDATE address SET address = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedAddress.getAddress());
            ps.setLong(2, updatedAddress.getId());

            if (ps.executeUpdate() > 0)
                result = updatedAddress;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Address getAddress(long id) {
        Address address = null;
        String sql = "SELECT * FROM address WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String addressDes = rs.getString("address");
                    address = new Address(id, addressDes);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return address;
    }
}
