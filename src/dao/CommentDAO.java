package dao;

import model.Comment;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class CommentDAO {

    public Comment createComment(Comment newComment) {
        return insertEntity(newComment);
    }

    public List<Comment> getComments() {
        return getAllEntities(Comment.class);
    }

    public void deleteComment(long id) {
        deleteByField(Comment.class, "id", id);
    }

    public Comment updateComment(Comment updatedComment) {
        return updateEntity(updatedComment);
    }

    public List<Comment> getComment(long id) {
        return getEntitiesByField("id", id, Comment.class);
    }
}
