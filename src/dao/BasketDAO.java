package dao;

import model.Basket;

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
public class BasketDAO {

    public Basket createBasket(Basket newBasket) {
        Basket result = null;
        String sql = "INSERT INTO shop.basket (product_id, user_id, count) VALUES(?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, newBasket.getProductId());
            ps.setLong(2, newBasket.getUserId());
            ps.setLong(3, newBasket.getCount());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getInt(1);
                    newBasket.setId(key);
                    result = newBasket;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Basket> listAllBasket() {
        List<Basket> listBasket = new ArrayList<>();

        String sql = "SELECT * FROM basket";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long productId = resultSet.getLong("product_id");
                long userId = resultSet.getLong("user_id");
                long count = resultSet.getLong("count");

                Basket basket = new Basket(id, productId, userId, count);
                listBasket.add(basket);
            }
            return listBasket;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteBasket(long id) {
        String sql = "DELETE FROM basket where id = ?";

        delete(id, sql);

    }

    public Basket updateBasket(Basket updatedBasket) {
        Basket result = null;
        String sql = "UPDATE basket SET product_id = ?, user_id = ?, count = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, updatedBasket.getProductId());
            ps.setLong(2, updatedBasket.getUserId());
            ps.setLong(3, updatedBasket.getCount());
            ps.setLong(4, updatedBasket.getId());

            if (ps.executeUpdate() > 0)
                result = updatedBasket;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Basket getBasket(long id) {
        Basket basket = null;
        String sql = "SELECT * FROM basket WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    long productId = rs.getLong("product_id");
                    long userId = rs.getLong("user_id");
                    long count = rs.getLong("count");
                    basket = new Basket(id, productId, userId, count);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return basket;
    }
}
