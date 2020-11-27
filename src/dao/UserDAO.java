package dao;

import model.User;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class UserDAO {

    public List<User> findUserByUsername(String username) {
        return getEntitiesByField("username", username, User.class);
    }

    public User createUser(User newUser) {
        return insertEntity(newUser);
    }

    public List<User> getUsers() {
        return getAllEntities(User.class);
    }

    public void deleteUser(long id) {
        deleteByField(User.class, "id", id);
    }

    public User updateUser(User updatedUser) {
        return updateEntity(updatedUser);
    }

    public List<User> getUser(long id) {
        return getEntitiesByField("id", id, User.class);
    }
}
