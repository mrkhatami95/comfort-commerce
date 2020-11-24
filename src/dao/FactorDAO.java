package dao;

import model.Factor;

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
public class FactorDAO {

    public Factor createFactor(Factor newFactor) {
        Factor result = null;
        String sql = "INSERT INTO factor (basket_id, user_id, price, date, delivery) VALUES(?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, newFactor.getBasketId());
            ps.setLong(2, newFactor.getUserId());
            ps.setLong(3, newFactor.getPrice());
            ps.setLong(4, newFactor.getDate());
            ps.setLong(5, newFactor.getDelivery());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newFactor.setId(key);
                    result = newFactor;
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public List<Factor> getFactors() {
        List<Factor> listFactor = new ArrayList<>();

        String sql = "SELECT * FROM factor";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long basketId = resultSet.getLong("basket_id");
                long userId = resultSet.getLong("user_id");
                long price = resultSet.getLong("price");
                long date = resultSet.getLong("date");
                int delivery = resultSet.getInt("delivery");

                Factor factor = new Factor(id, basketId, userId, price, date, delivery);
                listFactor.add(factor);
            }
            return listFactor;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteFactor(long id) {
        String sql = "DELETE FROM factor where id = ?";

        delete(id, sql);

    }

    public Factor updateFactor(Factor updatedFactor) {
        Factor result = null;
        String sql = "UPDATE factor SET basket_id = ?, user_id = ?, price = ?, date = ?, delivery = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, updatedFactor.getBasketId());
            ps.setLong(2, updatedFactor.getUserId());
            ps.setLong(3, updatedFactor.getPrice());
            ps.setLong(4, updatedFactor.getDate());
            ps.setInt(5, updatedFactor.getDelivery());
            ps.setLong(6, updatedFactor.getId());

            if (ps.executeUpdate() > 0)
                result = updatedFactor;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public Factor getFactor(long id) {
        Factor factor = null;
        String sql = "SELECT * FROM factor WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    long basketId = rs.getLong("basket_id");
                    long userId = rs.getLong("user_id");
                    long price = rs.getLong("price");
                    long date = rs.getLong("date");
                    int delivery = rs.getInt("delivery");
                    factor = new Factor(id, basketId, userId, price, date, delivery);
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return factor;
    }
}
