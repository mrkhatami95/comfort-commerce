package dao;

import model.Discount;

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
public class DiscountDAO {

    public Discount createDiscount(Discount newDiscount) {
        Discount result = null;
        String sql = "INSERT INTO discount (code, discount_value, is_used) VALUES(?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newDiscount.getCode());
            ps.setLong(2, newDiscount.getDiscountValue());
            ps.setBoolean(3, newDiscount.isUsed());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newDiscount.setId(key);
                    result = newDiscount;
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public List<Discount> getDiscounts() {
        List<Discount> listDiscount = new ArrayList<>();

        String sql = "SELECT * FROM discount";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String code = resultSet.getString("code");
                long discountValue = resultSet.getLong("discount_value");
                boolean isUsed = resultSet.getBoolean("is_used");

                Discount discount = new Discount(id, code, discountValue, isUsed);
                listDiscount.add(discount);
            }
            return listDiscount;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteDiscount(long id) {
        String sql = "DELETE FROM discount where id = ?";

        delete(id, sql);

    }

    public Discount updateDiscount(Discount updatedDiscount) {
        Discount result = null;
        String sql = "UPDATE discount SET code = ?, discount_value = ?, is_used = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedDiscount.getCode());
            ps.setLong(2, updatedDiscount.getDiscountValue());
            ps.setBoolean(3, updatedDiscount.isUsed());
            ps.setLong(4, updatedDiscount.getId());

            if (ps.executeUpdate() > 0)
                result = updatedDiscount;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public Discount getDiscount(long id) {
        Discount discount = null;
        String sql = "SELECT * FROM discount WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String code = rs.getString("code");
                    long discountValue = rs.getLong("discount_value");
                    boolean isUsed = rs.getBoolean("is_used");
                    discount = new Discount(id, code, discountValue, isUsed);
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return discount;
    }
}
