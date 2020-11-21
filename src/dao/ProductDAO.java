package dao;

import model.Product;

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
public class ProductDAO {

    public Product createProduct(Product newProduct) {
        Product result = null;
        String sql = "INSERT INTO shop.product (name, description, price, color_id, discount_id, count, category_id, comment_id) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newProduct.getName());
            ps.setString(2, newProduct.getDescription());
            ps.setLong(3, newProduct.getPrice());
            ps.setLong(4, newProduct.getColorId());
            ps.setLong(5, newProduct.getDiscountId());
            ps.setLong(6, newProduct.getCount());
            ps.setLong(7, newProduct.getCategoryId());
            ps.setLong(8, newProduct.getCommentId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newProduct.setId(key);
                    result = newProduct;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Product> listAllProduct() {
        List<Product> listProduct = new ArrayList<>();

        String sql = "SELECT * FROM product";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                long price = resultSet.getLong("price");
                long colorId = resultSet.getLong("color_id");
                long discountId = resultSet.getLong("discount_id");
                long count = resultSet.getLong("count");
                long categoryId = resultSet.getLong("category_id");
                long commentId = resultSet.getLong("comment_id");

                Product product = new Product(id, name, description, price, colorId, discountId, count, categoryId, commentId);
                listProduct.add(product);
            }
            return listProduct;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteProduct(long id) {
        String sql = "DELETE FROM product where id = ?";

        delete(id, sql);

    }

    public Product updateProduct(Product updatedProduct) {
        Product result = null;
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, color_id = ?, discount_id = ?, count = ?, category_id = ?, comment_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedProduct.getName());
            ps.setString(2, updatedProduct.getDescription());
            ps.setLong(3, updatedProduct.getPrice());
            ps.setLong(4, updatedProduct.getColorId());
            ps.setLong(5, updatedProduct.getDiscountId());
            ps.setLong(6, updatedProduct.getCount());
            ps.setLong(7, updatedProduct.getCategoryId());
            ps.setLong(8, updatedProduct.getCommentId());
            ps.setLong(9, updatedProduct.getId());


            if (ps.executeUpdate() > 0)
                result = updatedProduct;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Product getProduct(long id) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    long price = rs.getLong("price");
                    long colorId = rs.getLong("color_id");
                    long discountId = rs.getLong("discount_id");
                    long count = rs.getLong("count");
                    long categoryId = rs.getLong("category_id");
                    long commentId = rs.getLong("comment_id");
                    product = new Product(id, name, description, price, colorId, discountId, count, categoryId, commentId);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return product;
    }
}
