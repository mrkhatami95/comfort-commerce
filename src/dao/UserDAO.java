package dao;

import model.User;

import java.util.List;

import static dao.DAOManager.*;

/**
 * @author madeli
 * 11/20/20
 */
public class UserDAO {

    public User findUserByUsername(String username) {
        return getEntity("user", "username", username, new User());
    }

    public User createUser(User newUser) {
        return insertEntity(newUser);
    }

    public List<User> getUsers() {
        return getListOfEntities("user", new User());
    }

    public void deleteUser(long id) {
        String sql = "DELETE FROM user where id = ?";

        // TODO: 11/24/20 Make it more general for all fields
        delete(id, sql);
    }

    public User updateUser(User updatedUser) {
        return updateEntity(updatedUser);
    }

    public User getUser(long id) {
        return getEntity("product", "id", id, new User());
    }
}
