package dao;

import model.Comment;

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
public class CommentDAO {

    public Comment createComment(Comment newComment) {
        Comment result = null;
        String sql = "INSERT INTO shop.comment (comment) VALUES(?)";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, newComment.getComment());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    long key = rs.getLong(1);
                    newComment.setId(key);
                    result = newComment;
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public List<Comment> getComments() {
        List<Comment> listComment = new ArrayList<>();

        String sql = "SELECT * FROM comment";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String commentDes = resultSet.getString("comment");

                Comment comment = new Comment(id, commentDes);
                listComment.add(comment);
            }
            return listComment;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
            return null;
        }

    }

    public void deleteComment(long id) {
        String sql = "DELETE FROM comment where id = ?";

        delete(id, sql);

    }

    public Comment updateComment(Comment updatedComment) {
        Comment result = null;
        String sql = "UPDATE comment SET comment = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, updatedComment.getComment());
            ps.setLong(2, updatedComment.getId());

            if (ps.executeUpdate() > 0)
                result = updatedComment;

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return result;
    }

    public Comment getComment(long id) {
        Comment comment = null;
        String sql = "SELECT * FROM comment WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    String commentDes = rs.getString("comment");
                    comment = new Comment(id, commentDes);
                }

            }

        } catch (SQLException e) {
            
                System.err.println(e.getMessage());
        }

        return comment;
    }
}
