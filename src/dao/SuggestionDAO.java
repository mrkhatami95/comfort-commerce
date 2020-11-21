package dao;

import model.Suggestion;

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
public class SuggestionDAO {

    public Suggestion createSuggestion(Suggestion newSuggestion) {
        Suggestion result = null;
        String sql = "INSERT INTO shop.suggestion (user_id, basket_id, factor_id, suggestion_category_id) VALUES(?,?,?,?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setLong(1, newSuggestion.getUserId());
            ps.setLong(2, newSuggestion.getBasketId());
            ps.setLong(3, newSuggestion.getFactorId());
            ps.setLong(4, newSuggestion.getSuggestionCategoryId());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newSuggestion.setId(key);
                    result = newSuggestion;
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public List<Suggestion> listAllSuggestion() {
        List<Suggestion> listSuggestion = new ArrayList<>();

        String sql = "SELECT * FROM suggestion";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                long userId = resultSet.getLong("user_id");
                long basketId = resultSet.getLong("basket_id");
                long factorId = resultSet.getLong("factor_id");
                long suggestionCategoryId = resultSet.getLong("suggestion_category_id");

                Suggestion suggestion = new Suggestion(id, userId, basketId, factorId, suggestionCategoryId);
                listSuggestion.add(suggestion);
            }
            return listSuggestion;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
            return null;
        }

    }

    public void deleteSuggestion(long id) {
        String sql = "DELETE FROM suggestion where id = ?";

        delete(id, sql);

    }

    public Suggestion updateSuggestion(Suggestion updatedSuggestion) {
        Suggestion result = null;
        String sql = "UPDATE suggestion SET user_id = ?, basket_id = ?, factor_id = ?, suggestion_category_id = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, updatedSuggestion.getUserId());
            ps.setLong(2, updatedSuggestion.getBasketId());
            ps.setLong(3, updatedSuggestion.getFactorId());
            ps.setLong(4, updatedSuggestion.getSuggestionCategoryId());
            ps.setLong(5, updatedSuggestion.getId());

            if (ps.executeUpdate() > 0)
                result = updatedSuggestion;

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return result;
    }

    public Suggestion getSuggestion(long id) {
        Suggestion suggestion = null;
        String sql = "SELECT * FROM suggestion WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    long userId = rs.getLong("user_id");
                    long basketId = rs.getLong("basket_id");
                    long factorId = rs.getLong("factor_id");
                    long suggestionCategoryId = rs.getLong("suggestion_category_id");
                    suggestion = new Suggestion(id, userId, basketId, factorId, suggestionCategoryId);
                }

            }

        } catch (SQLException e) {
            for (Throwable t : e)
                System.err.println(t.getMessage());
        }

        return suggestion;
    }
}
