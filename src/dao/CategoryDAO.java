package dao;

import model.Category;

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
public class CategoryDAO {

    public Category createCategory(Category newCategory) {
        Category result = null;
        String sql = "INSERT INTO shop.category (name, `desc`) VALUES(?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newCategory.getName());
            ps.setString(2, newCategory.getDesc());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newCategory.setId(key);
                    result = newCategory;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Category> listAllCategory() {
        List<Category> listCategory = new ArrayList<>();

        String sql = "SELECT * FROM category";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String desc = resultSet.getString("desc");


                Category category = new Category(id, name, desc);
                listCategory.add(category);
            }
            return listCategory;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteCategory(long id) {
        String sql = "DELETE FROM category where id = ?";

        delete(id, sql);

    }


    public Category updateCategory(Category updatedCategory) {
        Category result = null;
        String sql = "UPDATE category SET name = ?, desc = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedCategory.getName());
            ps.setString(2, updatedCategory.getDesc());
            ps.setLong(3, updatedCategory.getId());

            if (ps.executeUpdate() > 0)
                result = updatedCategory;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Category getCategory(long id) {
        Category category = null;
        String sql = "SELECT * FROM category WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String name = rs.getString("name");
                    String desc = rs.getString("desc");
                    category = new Category(id, name, desc);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return category;
    }
}
